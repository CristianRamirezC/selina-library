package com.dashfleet.selinaLibrary.libraries.avlghost;
import com.dashfleet.selinaLibrary.libraries.ocss.nmea.api.NMEAReader;
import com.dashfleet.selinaLibrary.libraries.ocss.nmea.api.NMEAEvent;
import java.util.ArrayList;
import java.io.FileInputStream;

/**
 * A Simulator, taking its inputs from a file
 */
public class NMEALogReader extends NMEAReader // Notice the extended class
{
  private String logFile;

  public NMEALogReader(ArrayList al,String logFile)
  {
    super(al);
    this.logFile = logFile;
  }

  public void read() // The read() method must be overwritten
  {
    // Simulation
    super.enableReading(); // Then, canRead() will return true
    try
    {
      FileInputStream fis = new FileInputStream(this.logFile);
      while (canRead())    // canRead() is a method of the super class
      {
        double size = Math.random();
        int dim = (int)(750 * size);
        byte[] ba = new byte[dim];
        int l = fis.read(ba);
        if (l != -1 && dim > 0)
        {
          fireDataRead(new NMEAEvent(this, new String(ba))); // Broadcast the event
          try { Thread.sleep(500); } catch (Exception ignore) {}
        }
        else
        {
          System.out.println("===== Reseting Reader =====");
          fis.close();
          fis = new FileInputStream(this.logFile);
        }
      }
    }
    catch (Exception e)
    {
     e.printStackTrace();
    }
  }
}
        
