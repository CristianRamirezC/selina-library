package com.dashfleet.selinaLibrary.libraries.avlghost;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * NMEA Standard Spec Description
 *
 * NMEA Record
 * GGA : Global positioning system fixed data
 * GLL : Geographic position – latitude/longitude
 * GSA : GNSS DOP and active satellites
 * GSV : GNSS satellites in view
 * RMC : Recommended minimum specific GNSS data
 * VTG :  Course over gfround and ground speed
 *
 * ------------------------------------------------------------------------------------------
 * GGA : $GPGGA,161229.487,3723.2475,N,12158.3416,W ,1,07,1.0,9.0,M,,,,0000*18
 * ------------------------------------------------------------------------------------------
 * Name                     Example      Units    Description
 * ------------------------------------------------------------------------------------------
 * Message ID               $GPGGA                GGA protocol header
 * UTC Position             161229.487            hhmmss.sss
 * Latitude                 3727.2475             ddmm.mmmm
 * N/S Indicator            N                     N=north or S=south
 * Longitude                12158.3416            dddmm.mmmm
 * E/W Indicator            W                     E=east or W=west
 * Position fix indicator   1                     Fix Quality :
 *                                                  0 : fix not valid
 *                                                  1 : GPS SPS Mode, fix valid
 *                                                  2 : Diferential GPS, SPS Mode, fix valid
 *                                                  3 : GPS PPS Mode, fix valid
 * Satellites used          07                    Range 0 to 12
 * HDOP                     1.0                   Horizontal Dilution of Precision
 * MSL Altitude             9.0          meters
 * Units                    M            meters
 * Geoid Separation                      meters
 * Units                    M            meters
 * Age of Diff. Corr.                    second   Null field when DGPS is not used
 * Dif. Ref. Station ID
 * Checksum                 *18
 * <CR><LF>                                       End of message termination
 * ------------------------------------------------------------------------------------------
 *
 * ------------------------------------------------------------------------------------------
 * GLL : $GPGLL, 3723.2475,N,12158.3416,W,161229.487,A*2C
 * ------------------------------------------------------------------------------------------
 * Name                     Example      Units    Description
 * ------------------------------------------------------------------------------------------
 * Message ID               $GPGLL                GLL protocol header
 * Latitude                 3723.2475             ddmm.mmmm
 * N/S Indicator            N                     N=north or S=south
 * Longitude                12158.3416            dddmm.mmmm
 * E/W indicator            W                     E=east or W=west
 * UTC position             161229.487            hhmmss.sss
 * Status                   A                     A=data valid or V=data not valid
 * Checksum                 *2C
 * <CR><LF>                                       End of message termination
 * ------------------------------------------------------------------------------------------
 *
 * ------------------------------------------------------------------------------------------
 * GSA : $GPGSA, A,3,07,02,26,27,09,04,15,,,,,,1.8,1.0,1.5*33
 * ------------------------------------------------------------------------------------------
 * Name                     Example      Units    Description
 * ------------------------------------------------------------------------------------------
 * Message ID               $GPGSA                GSA protocol header
 * Mode 1                   A                     1: fix not available, 2 : 2D, 3 : 3D
 * Mode 2                   3                     M : Manual, forced to operate 2D or 3D
 *                                                A : Auto, allowed to auto switch 2D or 3D
 * Satellite used           07                    Sv on channel 1
 * Sateliite used           02                    Sv on channel 2
 * ...
 * Satellite used                                 Sv on channel 12
 * PDOP                     1.8                   Position dilution of precision
 * HDOP                     1.0                   Horizontal dilution of precision
 * VDOP                     1.5                   Vertical dilution of precision
 * Checksum                 *33
 * <CR><LF>                                       End of message termination
 * ------------------------------------------------------------------------------------------
 *
 * ------------------------------------------------------------------------------------------
 * VTG : $GPVTG,309.62,T,,M,0.13,N,0,2,K*6E
 * ------------------------------------------------------------------------------------------
 * Name                     Example      Units    Description
 * ------------------------------------------------------------------------------------------
 * Message ID               $GPVTG                VTG protocol header
 * Course                   309.62       degrees  Measured heading
 * Reference                T                     True
 * Course                                degrees  Measured heading
 * Reference                M                     Magnetic
 * Speed                    0.13         knots    Measured horizontal speed
 * Units                    N                     knots
 * Speed                    0.2          km/hr    Measured horizontal speed
 * Units                    K                     Kilometer per hour
 * Checksum                 *6E
 * <CR><LF>                                       End of message termination
 * ------------------------------------------------------------------------------------------
 *
 * Original class by Scott Emmons
 * https://github.com/scotte/yotreporter/branches/master
 *
 * Class Modified by
 * Maciek Ruckgaber <maciekrb@gmail.com>
 * Added :
 *  - Documentation
 *  - Sentence checksums
 *  - Sentence validation
 *  - GGA support
 **/
public class Sentence {

    public static enum SENTENCE {        
        GLL, GGA, HDM, GSV, VTG, UNKNOWN
    };
    // Type of sentence (GLL,GGA,HDM,etc)
    public SENTENCE type = null;
    // Full text of NMEA sentence
    public String nmea = null;
    // Sentence split from comma delimited fields
    public String fields[] = null;
    // Valid
    public boolean valid = false;
    
    static Logger logger = Logger.getLogger("phantom-avl");        
    
    //Constructor
    protected Sentence(String nmea) {
        try{
        this.nmea = nmea;
        //System.out.println("RAW NMEA: " + nmea);
        this.type = this.parseType(nmea);
        this.fields = nmea.split("[,*]");
        }catch(Exception exp){
            logger.debug("Sentence Contructor:" + exp.getMessage());
        }
    }

    public static Sentence Parse(String nmea) {
        Sentence ret = null;
        try{
            SENTENCE type = parseType(nmea);

            switch (type) {
                case GLL:
                    ret = new GLL(nmea);
                    break;

                case HDM:
                    ret = new HDM(nmea);
                    break;

                case GGA:
                    ret = new GGA(nmea);
                    break;

                case GSV:
                    ret = new GSV(nmea);
                    break;

                case VTG:
                    ret = new VTG(nmea);
                    break;

                case UNKNOWN:
                    ret = new UNKNOWN(nmea);
                    break;
            }
        }catch(Exception exp){
            logger.debug("Sentence Parse:" + exp.getMessage());
        }        
        return ret;
    }

    // Utility parsing methods
    public static SENTENCE parseType(String nmea) {
        SENTENCE ret = null;
        try {
            ret = SENTENCE.valueOf(nmea.substring(3, 6));
        } catch (IllegalArgumentException e) {
            ret = SENTENCE.UNKNOWN;
        }
        return ret;
    }

    public static long parseTime(String time) {
        long ret = System.currentTimeMillis();

        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("hhmmss");
            Date date = (Date) formatter.parse(time);
            ret = date.getTime();
        } catch (Exception exp) {
            logger.debug("Sentence parseTime:" + exp.getMessage());
        }
        return ret;
    }

    public static byte parseByte(String val) {
        byte ret = 0;
        try {
            if(val == null || val.equals(""))
               val = "0";            
            ret = Byte.parseByte(val);
        } catch (Exception exp) {
            logger.debug("Sentence parseByte:" + exp.getMessage());
        }
        return ret;
    }

    public static int parseInteger(String val) {
        int ret = 0;
        try {
            if(val == null || val.equals(""))
               val = "0";            
            ret = Integer.parseInt(val);
        } catch (Exception exp) {
            logger.debug("Sentence parseInteger:" + exp.getMessage());
        }
        return ret;
    }

    public static double parseDouble(String val) {
        double ret = 0;
        try {
            if(val == null || val.equals(""))
               val = "0";
                ret = Double.parseDouble(val);
        } catch (Exception exp) {
            logger.debug("Sentence parseDouble:" + exp.getMessage());
        }
        return ret;
    }

    /** 
     * Validates NMEA sentences
     *
     * @param String sentece NMEA sentence
     * @return true if the checksum is valid, false if it is invalid
     */
    private static boolean isValid(String sentence) {
        boolean valid = false;

        //calculate message length and subtract one (the carriage return)
        int slen = sentence.length();

        //if the message length is greater than 4 characters
        if (slen > 4) {
            //check if there is a checksum in this message by seeing where the asterisk is
            //(it should be at the third last position in the message)
            if (sentence.charAt(slen - 3) == '*') {
                //System.out.println("Received checksum : " + sentence.substring(slen - 2, slen));
                // perform NMEA checksum calculation
                String chk_s = getChecksum(sentence.substring(0, slen - 3));
                // compare checksum to encoded checksum in msg
                valid = sentence.substring(slen - 2, slen).equals(chk_s);
                return (valid);
            } else {
                // message doesn't have a checksum: do not accept it
                //   System.out.println("    message has no checksum.");
                return false;
            }
        }
        //(if the message length is greater than 4 characters)
        // don't accept messages without checksum
        return false;
    }

    /**
     * Trims the checksum off an NMEA message, then
     * recalculates the checksum
     * to compare it with the one passed along with the message later
     *
     * @param msg String containing the full NMEA message (including checksum)
     * @return String containing the checksum
     */
    private static String getChecksum(String msg) {
        // perform NMEA checksum calculation
        int chk = 0;
        //run through each character of the message length
        //and XOR the value of chk with the byte value
        //of the character that is being evaluated
        for (int i = 1; i < msg.length(); i++) {
            chk ^= msg.charAt(i);
        }

        //convert the retreived integer to a HexString in uppercase
        String chk_s = Integer.toHexString(chk).toUpperCase();

        // checksum must be 2 characters!
        // if it falls short, add a zero before the checksum
        while (chk_s.length() < 2) {
            chk_s = "0" + chk_s;
        }

        //show the calculated checksum
        // System.out.println("    calculated checksum : " + chk_s);

        //return the calculated checksum
        return chk_s;
    }

    private static Double getPoint(double d, String s) {
        double point = 0;
        try {
            s.toUpperCase();
            int intL = (int) d / 100;
            double m = ((d / 100.0) - intL) * 100.0;
            m *= (100.0 / 60.0);
            point = intL + (m / 100.0);
            if (s.equals("S") || s.equals("W")) {
                point *= -1.0;
            }
        } catch (Exception exp) {
            logger.debug("Sentence::getPoint :" + exp.getMessage());
        }
        return point;
    }

    
    //Sentence Definition
    //VTG : Velocity made good - speed in knots/speed in kms
    /* Structure is 
        $GPVTG,054.7,T,034.4,M,005.5,N,010.2,K*48

    where:
        VTG          Track made good and ground speed
        054.7,T      True track made good (degrees)
        034.4,M      Magnetic track made good
        005.5,N      Ground speed, knots {5}
        010.2,K      Ground speed, Kilometers per hour {7}
        *48          Checksum
     */
    public static class VTG extends Sentence {

        public double vel_kn;
        public double vel_km;

        public VTG(String nmea) {
            super(nmea);
            try{
                valid = isValid(nmea);
                if (valid == true) {
                    vel_kn = parseDouble(fields[5]);
                    vel_km = parseDouble(fields[7]);
                }
                //logger.info("NMEA VTG:" + nmea + " -> valid = " + valid + " -> velocidad kms=" + vel_km);
            }catch (Exception exp){
                logger.debug("Sentence::VTG :" + exp.getMessage());            
            }
        }
    }

    //Sentence Definition
    //GLL : Geographic position – latitude/longitude
    /* Structure is 
     *  $aaGLL,llll.ll,a,gggg.gg,a,hhmmss.ss,A*hh
     *         |       | |       | |         | |
     *         |       | |       | |         | Checksum
     *         |       | |       | |         A|V 
     *         |       | |       | [5]UTC of position (fix time)
     *         |       | |       [4]Long sign :E/W
     *         |       | [3]Longitude
     *         |       [2]Lat sign :N/S
     *         [1]Latitude
     */
    public static class GLL extends Sentence {

        public double lat;
        public double lng;
        public long time;

        public GLL(String nmea) {
            super(nmea);
            try{
                time = parseTime(fields[5]);
                valid = isValid(nmea);
                if (valid == true) {
                    lat = getPoint(parseDouble(fields[1]), fields[2]);
                    lng = getPoint(parseDouble(fields[3]), fields[4]);
                }
            }catch (Exception exp){
                logger.debug("Sentence::GLL :" + exp.getMessage());            
            }
        }
    }

    //GGA : Global positioning system fixed data
    /* Structure is 
     * $aaGGA,hhmmss.ss,llll.lll,a,gggg.ggg,a,d,nn,d.d,mmm.m,M,hh.h,M,E,E,*74
     *        |         |        | |        | | |  |   |       |
     *        |         |        | |        | | |  |   |       |
     *        |         |        | |        | | |  |   |       [11]Mean sea level
     *        |         |        | |        | | |  |   [9]Altitude above sea level (meters)
     *        |         |        | |        | | |  [8]Horiz. dilution of position
     *        |         |        | |        | | [7]Satellites tracked
     *        |         |        | |        | [6]Fix quality
     *        |         |        | |        [5]Lng sign :E/W
     *        |         |        | [4]Longitude
     *        |         |        [3]Lat sing :N/S
     *        |         [2]Latitute
     *        [1]Fix Taken at
     */
    public static class GGA extends Sentence {

        private Logger logger = Logger.getLogger("pahntom-avl");        
        public double lat;
        public double lng;
        public byte quality;
        public long time;
        public byte satellites;
        public double altitude;

        public GGA(String nmea) {
            super(nmea);
            try{
                time = parseTime(fields[1]);
                quality = parseByte(fields[6]);
                satellites = parseByte(fields[7]);
                altitude = parseDouble(fields[9]);
                valid = isValid(nmea);
                if (valid == true) {
                    lat = getPoint(parseDouble(fields[2]), fields[3]);
                    lng = getPoint(parseDouble(fields[4]), fields[5]);
                }
            }catch (Exception exp){
                logger.debug("Sentence::GGA :" + exp.getMessage());            
            }               
        }
    }

    public static class GSV extends Sentence {

        public byte sentences;
        public byte sentence_number;
        public byte satellites;
        public byte satellite_prn_number;
        public int elevation_degrees;
        public int azimuth_degrees;
        public int srn;

        public GSV(String nmea) {
            super(nmea);
            try{
                sentences = parseByte(fields[1]);
                sentence_number = parseByte(fields[2]);
                satellites = parseByte(fields[3]);
                satellite_prn_number = parseByte(fields[4]);
                elevation_degrees = parseInteger(fields[5]);
                azimuth_degrees = parseInteger(fields[6]);
                valid = isValid(nmea);
                /*if (valid == true) {
                lat = getPoint(parseDouble(fields[2]), fields[3]);
                lng = getPoint(parseDouble(fields[4]), fields[5]);
                }*/
            }catch (Exception exp){
                logger.debug("Sentence::GSV :" + exp.getMessage());            
            }                
        }
    }

    public static class HDM extends Sentence {

        public double heading;

        public HDM(String nmea) {
            super(nmea);
            try{
                heading = parseDouble(fields[1]);
            }catch (Exception exp){
                logger.debug("Sentence::GGA :" + exp.getMessage());            
            }
        }
    }

    public static class UNKNOWN extends Sentence {

        public UNKNOWN(String nmea) {
            super(nmea);
        }
    }
}
