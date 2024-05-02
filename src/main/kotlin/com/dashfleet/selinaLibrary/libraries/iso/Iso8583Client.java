package com.dashfleet.selinaLibrary.libraries.iso;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quechua.iso8583.IsoMessage;
import com.quechua.iso8583.IsoType;
import com.quechua.iso8583.MessageFactory;
import com.quechua.iso8583.impl.SimpleTraceGenerator;
import com.quechua.iso8583.parse.ConfigParser;

public class Iso8583Client {

    private static final Logger log = LoggerFactory.getLogger(Iso8583Client.class);
    private static final String[] data = new String[]{
        "1234567890", "5432198765", "1020304050", "abcdefghij", "AbCdEfGhIj",
        "1a2b3c4d5e", "A1B2C3D4E5", "a0c0d0f0e0", "j5k4m3nh45"
    };
    private static final BigDecimal[] amounts = new BigDecimal[]{
        new BigDecimal("10"), new BigDecimal("20.50"), new BigDecimal("37.44")
    };
    private static MessageFactory mfact;
    private static ConcurrentHashMap<String, IsoMessage> pending = new ConcurrentHashMap<String, IsoMessage>();

    public static String sendData(String host, int port, String frame) throws Exception {
        String Field61 = null;
        try {
            Random rng = new Random(System.currentTimeMillis());
            log.info("Reading config");
            mfact = ConfigParser.createFromClasspathConfig("config.xml");
            mfact.setAssignDate(true);
            mfact.setTraceNumberGenerator(new SimpleTraceGenerator((int) (System.currentTimeMillis() % 10000)));
            //mfact.setCustomField(48, new ProductEncoder());
            log.info("Connecting to server");
            Socket sock = new Socket(host, port);

            // crear mensaje
            IsoMessage req = mfact.newMessage(0x200);
            req.setValue(4, amounts[rng.nextInt(amounts.length)], IsoType.AMOUNT, 0);
            req.setValue(12, req.getObjectValue(7), IsoType.TIME, 0);
            req.setValue(13, req.getObjectValue(7), IsoType.DATE4, 0);
            req.setValue(15, req.getObjectValue(7), IsoType.DATE4, 0);
            req.setValue(17, new Date(System.currentTimeMillis() + (86400 * 720)), IsoType.DATE_EXP, 0);
            req.setValue(37, System.currentTimeMillis() % 1000000, IsoType.NUMERIC, 12);
            req.setValue(41, data[rng.nextInt(data.length)], IsoType.ALPHA, 16);

            req.setValue(61, frame, IsoType.LLLVAR, frame.length());
            pending.put(req.getField(11).toString(), req);

            log.info("Sending request {}", req.getField(11));
            req.write(sock.getOutputStream(), 2);
            Thread.sleep(1000);
            log.info("Waiting for responses");
            byte[] lenbuf = new byte[10240];
            try {
                log.info("try");
               
                
                sock.getInputStream().read(lenbuf);
                Thread.sleep(10000);
                log.info("buf->" + new String(lenbuf));
                log.info("buf[0]->" + Byte.toString(lenbuf[0]));
                int size = ((lenbuf[0] & 0xff) << 8) | (lenbuf[1] & 0xff);
                byte[] buf = new byte[size];
                log.info("size -> " + size);
                if (sock.getInputStream().read(buf) == size) {
                    try {
                        String respHeader = mfact.getIsoHeader(0x200);
                        IsoMessage resp = mfact.parseMessage(buf, respHeader == null ? 12 : respHeader.length());
                        //Field61 = resp.getField(61);
                        log.info("Read response {} conf {}: {}", new Object[]{resp.getField(11), resp.getField(38), new String(buf)});
                        pending.remove(resp.getField(11).toString());
                        Field61 = resp.getField(61).toString();
                    } catch (ParseException ex) {
                        log.error("Parsing response", ex);
                    }
                } else {
                    pending.clear();
                }
            } catch (IOException ex) {
                log.error(String.format("Reading responses, %d pending", pending.size()), ex);
            } finally {
                if (sock != null) {
                    try {
                        sock.close();
                    } catch (IOException ex) {
                    }
                }
            }
            log.debug("DONE.");
        } catch (Exception ex) {
            log.error("Message Exp:", ex);
        }
        return Field61;
    }
}
