package com.dashfleet.selinaLibrary.libraries.ocss.nmea.api;

import java.util.ArrayList;

/**
 * A View. Must be extended to be used from the client.
 * The typical sequence would look like this:
 * <pre>
 * public class CustomClient extends NMEAClient 
 * {
 *   public CustomClient(String s, String[] sa)
 *   {
 *     super(s, sa);
 *   }
 *   
 *   public static void main(String[] args)
 *   {
 *     String prefix = "II";
 *     String[] array = {"HDM", "GLL", "XTE", "MWV", "VHW"};
 *     CustomClient customClient = new CustomClient(prefix, array); // Extends NMEAReader
 *     customClient.initClient();
 *     customClient.setReader(new CustomReader(customClient.getListeners()));
 *     customClient.startWorking();
 *   }
 * } 
 * </pre>
 */
public abstract class NMEAClient {

    private transient ArrayList NMEAListeners = new ArrayList(2);
    private NMEAParser parser;
    private NMEAReader reader;
    private String devicePrefix = "";
    private String[] sentenceArray = null;

    public NMEAClient() // This one is useless
    {
    }

    /**
     * Create the client
     * @param prefix the Device Identifier
     * @param sentence the String Array containing the NMEA sentence identifiers to read
     */
    public NMEAClient(String prefix,
            String[] sentence) {
        setDevicePrefix(prefix);
        setSentenceArray(sentence);
    }

    public void initClient() {
        this.addNMEAListener(new NMEAListener() {

            public void dataDetected(NMEAEvent e) {
                dataDetectedEvent(e);
            }
        });
        parser = new NMEAParser(NMEAListeners);
        parser.setNmeaPrefix(this.getDevicePrefix());
        parser.setNmeaSentence(this.getSentenceArray());
    }

    public void setDevicePrefix(String s) {
        this.devicePrefix = s;
    }

    public String getDevicePrefix() {
        return this.devicePrefix;
    }

    public void setSentenceArray(String[] sa) {
        this.sentenceArray = sa;
    }

    public String[] getSentenceArray() {
        return this.sentenceArray;
    }

    public void setParser(NMEAParser p) {
        this.parser = p;
    }

    public NMEAParser getParser() {
        return this.parser;
    }

    public void setReader(NMEAReader r) {
        this.reader = r;
    }

    public NMEAReader getReader() {
        return this.reader;
    }

    public ArrayList getListeners() {
        return this.NMEAListeners;
    }

    public void startWorking() {
        this.reader.start();
        this.parser.start();
    }

    /**
     * This one must be overwritten to customize the behavior of the client,
     * like the destination of the data.
     */
    public abstract void dataDetectedEvent(NMEAEvent e);
//  {
//    System.out.println("Client received [" + e.getContent() + "] (length:" + parser.getNmeaStream().length() + ")");
//  }

    public synchronized void addNMEAListener(NMEAListener l) {
        if (!NMEAListeners.contains(l)) {
            NMEAListeners.add(l);
        }
    }

    public synchronized void removeNMEAListener(NMEAListener l) {
        NMEAListeners.remove(l);
    }
}
