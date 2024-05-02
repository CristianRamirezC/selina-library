/**
* Simple GPS Reading class
* @author Maciek Ruckgaber <maciekrb@gmail.com>
**/
package com.dashfleet.selinaLibrary.libraries.avlghost;
import com.dashfleet.selinaLibrary.libraries.ocss.nmea.api.NMEAReader;
import com.dashfleet.selinaLibrary.libraries.ocss.nmea.api.NMEAEvent;
import java.util.ArrayList;

import gnu.io.*; //javax.comm.*;
import java.util.*;
import java.io.*;

public class GPSReader extends NMEAReader // Notice the extended class
{
  private String commPort;
  private int commPortSpeed;

  public GPSReader(ArrayList al,String commPort,int commPortSpeed)
  {
    super(al);
    this.commPort = commPort;
    this.commPortSpeed = commPortSpeed;
  }

  public void read() // The read() method must be overwritten
  {
    super.enableReading(); 

    //Check port availability
    CommPortIdentifier com = null;
    try{ com = CommPortIdentifier.getPortIdentifier(this.commPort); }
    catch(NoSuchPortException e){
      System.err.println("Port " + this.commPort + " is in use");
      return;
    }
    
    //Open port
    
    SerialPort thePort = null;
    try {
        thePort = (SerialPort) com.open(
            "AvlGps", // Name of the application asking for the port 
            10000   // Wait max. 10 sec. to acquire port
        );
    } catch(PortInUseException e) {
        System.err.println("Port already in use: " + e);
        System.exit(1);
    }    
    
    
    /*
    CommPort thePort = null;
    try { 
        System.out.println("--- Open Port 0.0 ---");
        thePort = com.open("PortOpener", 10); 
        System.out.println("--- Open Port 0 ---");
    }
    catch (Exception e)
    {
      System.err.println("Port Open Exception:" + e.getMessage());
      return;
    }
    */
    //Setup
    int portType = com.getPortType();
    if (portType == com.PORT_SERIAL)
    {
        System.out.println("--- Set Params ---");
        SerialPort sp = (SerialPort)thePort;
      try
      {
        // Settings 
        sp.setSerialPortParams(this.commPortSpeed,
                               SerialPort.DATABITS_8,
                               SerialPort.STOPBITS_1,
                               SerialPort.PARITY_NONE);
      }
      catch (UnsupportedCommOperationException ucoe)
      {
        System.err.println("Unsupported Comm Operation");
        return;
      }
    }
    // Reading on Serial Port
    try
    {
      byte[] buffer = new byte[4096];
      InputStream theInput = thePort.getInputStream();
      System.out.println("Reading serial port...");
      while (canRead()) // Loop
      {
        int bytesRead = theInput.read(buffer);
        if (bytesRead == -1)
          break;
        //System.out.println("Read " + bytesRead + " characters");
        // Count up to the first not null
        int nn = bytesRead;
        for (int i=0; i<Math.min(buffer.length, bytesRead); i++)
        {
          if (buffer[i] == 0)
          {
            nn = i;
            break;
          }
        }
        byte[] toPrint = new byte[nn];
        for (int i=0; i<nn; i++)
          toPrint[i] = buffer[i];
        // Broadcast event
        super.fireDataRead(new NMEAEvent(this, new String(toPrint))); // Broadcast the event
      }
      System.out.println("Stop Reading serial port.");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
