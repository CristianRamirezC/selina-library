package com.dashfleet.selinaLibrary.libraries.gps;

import com.dashfleet.selinaLibrary.libraries.avlghost.GPSReader;
import com.dashfleet.selinaLibrary.libraries.avlghost.Sentence;
import com.dashfleet.selinaLibrary.libraries.db.ConexionDB;
import com.dashfleet.selinaLibrary.libraries.git.Gitcommand;
import com.dashfleet.selinaLibrary.libraries.iso.Iso8583Client;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Base64;
import java.util.Properties;
import com.dashfleet.selinaLibrary.libraries.ocss.nmea.api.NMEAClient;
import com.dashfleet.selinaLibrary.libraries.ocss.nmea.api.NMEAEvent;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.dashfleet.selinaLibrary.libraries.org.json.JSONArray;
import com.dashfleet.selinaLibrary.libraries.org.json.JSONObject;
import com.dashfleet.selinaLibrary.libraries.org.json.JSONTokener;
import java.util.zip.Inflater;
import com.dashfleet.selinaLibrary.libraries.struct.Parada;
import com.dashfleet.selinaLibrary.libraries.struct.Programacion;

public class GPSInfo extends NMEAClient {

    private Logger logger = Logger.getLogger("pahntom-avl");
    private Programacion PROGRAMACION;
    private long ultimoEnvioTrama = 0, UPGRADE_TIME;
    private String velocidad, latitud, longitud, satelites, grados_azimuth;
    private String CODIGO_BUS, CODIGO_EQUIPO = getMotherboardSN(), ID_TARJETA = "0", CODIGO_OPERADOR;
    private String ID_LINEA, ID_LINEA_TRAYECTO, ID_MISION, ESTADO_TRAYECTO, ID_RECAUDO, LATITUD_LLEGADA, LONGITUD_LLEGADA, DISTANCIA_LLEGADA;
    private int ADQUISICION = 0, ENTRADAS = 0, SALIDAS = 0;
    private String host;
    private int port;
    private String estado_data, estado_gps, alarma = "0", tipoTracker = "";
    private double dist;
    private byte QUALITY = 0;
    private boolean enviarNMEA = false, ModeTracker = false, ThState = true;
    private String NMEAFRAME = "";
    private Sentence.GGA gga = null;
    private Sentence.GSV gsv = null;
    private Sentence.VTG vtg = null;
    private int CHECKED_MID, CHECKED_STOPID;
    private long CHECKED_TID, CHECKED_STOP_TIMESTAMP;
    private boolean CHECKED_STOP = false;

    public GPSInfo(String prefix, String[] ntypes) {
        super(prefix, ntypes);
        try {
//            PropertyConfigurator.configure("/usr/local/phantom/avl/logger.properties");
//            new File("logs").mkdir();
//            logger.info("--- Iniciando... ----");
        } catch (Exception e) {
            e.printStackTrace();
//            System.exit(1);
        }
//        try {
//            Properties prop = new Properties();
//            FileInputStream archivo = new FileInputStream("properties/gpsconn.properties");
//            prop.load(archivo);
//            archivo.close();
//            String SERIAL_PORT = prop.getProperty("puerto");
//            UPGRADE_TIME = Long.parseLong(prop.getProperty("UPGRADE_TIME"));
//            initClient();
//            setReader(new GPSReader(getListeners(), SERIAL_PORT, 9600));
//            logger.info("--- CONECTADO AL GPS ---");
//            prop = new Properties();
//            archivo = new FileInputStream("properties/conf.properties");
//            prop.load(archivo);
//            archivo.close();
//            CODIGO_BUS = prop.getProperty("NUMERO_BUS");
//            logger.debug("Código BUS: \\" + CODIGO_BUS + "\\");
//            try {
//                FileInputStream propfile = new FileInputStream("properties/host.properties");
//                prop = new Properties();
//                prop.load(propfile);
//                propfile.close();
//                host = prop.getProperty("HOST");
//                port = Integer.parseInt(prop.getProperty("PORT"));
//                logger.debug("HOST:PORT: \\" + host + ":" + port + "\\");
//            } catch (Exception e) {
//                logger.error("", e);
//            }
//            //PROGRAMACION = ConexionDB.cargarProgramacion(obtenerFecha());
//            testLoad();
//            startWorking();
//            new Thread(new ScheduleFollow()).start();
//
//        } catch (Exception e) {
//            e.printStackTrace();
////            System.exit(1);
//        }
    }

    class ThreadTracker extends Thread {

        public ThreadTracker(String str) {
            super(str);
        }

        public void run() {
            ThState = false;
            logger.debug("ModoTracker.");

            try {
                if (ModeTracker) {
                    EnableTracker();
                }
            } catch (Exception e) {
                logger.error("", e);
            }
            System.out.println("DONE! " + getName());
            ThState = true;
        }

        public void EnableTracker() {
            try {
                tipoTracker = "";
                BufferedReader lee = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("\n\n Tracker: Digite s=Parada ó c=Control de Paso.");
                tipoTracker = lee.readLine();
                System.out.println("Selección ejecutada:" + tipoTracker);
                if (tipoTracker.equals("s")) {
                    System.out.println("Enviando Track Parada:" + tipoTracker);
                    long T = System.currentTimeMillis() / 1000;
                    EnviarTrama(T, true, "s");
                } else if (tipoTracker.equals("c")) {
                    System.out.println("Enviando Track CheckPoint:" + tipoTracker);
                    long T = System.currentTimeMillis() / 1000;
                    EnviarTrama(T, true, "c");
                } else {
                    System.out.println("Selección Invalida");
                }
                tipoTracker = "";
            } catch (Exception exp) {
            }
        }
    }

    class ScheduleFollow implements Runnable {

        public ScheduleFollow() {
            new Thread(this).start();
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                }
                Parada p;
                double LAT = Double.parseDouble(latitud), LNG = Double.parseDouble(longitud);
                double dist;
                boolean encontrada = false;
                for (int i = 0; i < PROGRAMACION.tablas.size(); i++) {
                    for (int j = 0; i < PROGRAMACION.tablas.get(i).paradas.size() && !encontrada; j++) {
                        p = PROGRAMACION.tablas.get(i).paradas.get(j);
                        if (p.checked == 0) {
                            dist = calcularDistancia(p.latitud, p.longitud, LAT, LNG);
                            if (dist <= 20) {
                                encontrada = true;
                                try {
                                    p.checked = System.currentTimeMillis() / 1000;
                                    System.out.println("Se pisa: " + PROGRAMACION.tablas.get(i).paradas.get(j).nombre);
                                    if (ConexionDB.chequearParada(p)) {
                                        CHECKED_STOP = true;
                                        CHECKED_MID = PROGRAMACION.mid;
                                        CHECKED_TID = PROGRAMACION.tablas.get(i).tid;
                                        CHECKED_STOPID = p.id;
                                        CHECKED_STOP_TIMESTAMP = p.checked;
                                    }
                                } catch (Exception e) {
                                    logger.error("Error actualizando parada " + p.id + ": ", e);
                                    p.checked = 0;
                                    break;
                                }
                                if (PROGRAMACION.tablas.get(i).paradas.size() - 1 == j) {
                                    System.out.println("PROXIMA PARADA: YA NO HAY!");
                                } else {
                                    System.out.println("PROXIMA PARADA: " + PROGRAMACION.tablas.get(i).paradas.get(j + 1).nombre);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void dataDetectedEvent(NMEAEvent event) {
        try {
            String line = event.getContent();
            NMEAFRAME = event.getContent();
            //logger.debug("Got: \\" + line + "\\");
            Sentence sen = Sentence.Parse(line);
            switch (sen.type) {
                case GGA:
                    gga = (Sentence.GGA) sen;
                    String head = "GGA : " + (gga.valid == true ? "OK" : "FAIL");
                    QUALITY = gga.quality;
                    if (QUALITY == (byte) 0) {
                        head = head + "  ";
                    }
                    if (QUALITY == (byte) 1) {
                        head = head + "+";
                    }
                    if (QUALITY == (byte) 2 || QUALITY == (byte) 3) {
                        head = head + "++";
                    }
//                    logger.debug(head + " : \\" + gga.lat + "," + gga.lng + "," + gga.quality + "," + gga.satellites + "\\");
                    latitud = gga.lat + "";
                    longitud = gga.lng + "";
                    satelites = gga.satellites + "";
                    break;
                case GSV:
                    gsv = (Sentence.GSV) sen;
                    head = "GSV : " + (gsv.valid == true ? "OK" : "FAIL");
//                    logger.debug(head + " : \\" + gsv.sentences + "," + gsv.sentence_number + "," + gsv.elevation_degrees + "," + gsv.azimuth_degrees + "\\");
                    grados_azimuth = gsv.azimuth_degrees + "";
                    break;
                case VTG:
                    vtg = (Sentence.VTG) sen;
                    head = "VTG : " + (vtg.valid == true ? "OK" : "FAIL");
//                    logger.debug(head + " : \\" + vtg.nmea);
                    velocidad = vtg.vel_km + "";
                    break;
            }
            long T = System.currentTimeMillis() / 1000;
            if (ultimoEnvioTrama == 0 || T - ultimoEnvioTrama > UPGRADE_TIME) {
                logger.debug("Cumplido el tiempo");
                if (gga != null && gga.valid && gsv != null && gsv.valid) {
                estado_gps = "1";
                logger.debug("Tiempo Envio: " + UPGRADE_TIME + " Ultima Trama: " + ultimoEnvioTrama + " T: " + T);
                EnviarTrama(T, true, "0");
                logger.debug("Enviado GPS OK");                
//                logger.debug("VALID: LAT, LNG, VEL: \\" + latitud + ", " + longitud + ", " + velocidad + "\\");
//                logger.debug("VALID: AZDEG: \\" + grados_azimuth + "\\");              
                
                gga = null;
                gsv = null;
                }else{                
//                logger.debug("INVALID: LAT, LNG, VEL: \\" + latitud + ", " + longitud + ", " + velocidad + "\\");
//                logger.debug("INVALID: AZDEG: \\" + grados_azimuth + "\\");            
                logger.debug("Enviado GPS OFF");                
                EnviarTrama(T, false, "0");
                }
            }
            if (ModeTracker && ThState) {
                new ThreadTracker("Jamaica").start();
            }
        } catch (Exception exp) {
            logger.error("Event Tramas:", exp);
            exp.printStackTrace();
        }
    }

    public void EnviarTrama(long T, boolean type, String tracker) {
        if (!type) {
            latitud = "";
            longitud = "";
            satelites = "";
            velocidad = "";
            grados_azimuth = "0";
//            QUALITY = "0";
        }
        String ver_curr = "7.2";//Gitcommand.comprobarVersion();                            
        String ret = null;
        try {
            logger.debug("Enviando trama tipo 1x...");
            String trama = "writeavl?data=";
            JSONObject obj = new JSONObject();
            obj.put("b", CODIGO_BUS);
//        obj.put("e", CODIGO_EQUIPO);
            obj.put("m", T);
            if (QUALITY > (byte) 0) {
                String lat = latitud, lon = longitud;
                if (lat.length() >= 8) {
                    lat = lat.substring(0, 7);
                }
                if (lon.length() >= 10) {
                    lon = lon.substring(0, 9);
                }
                obj.put("lt", lat);
                obj.put("ln", lon);
                obj.put("s", satelites);
                obj.put("v", velocidad);
            } else {
                obj.put("lt", "");
                obj.put("ln", "");
                obj.put("s", satelites);
                obj.put("v", "");
            }

            obj.put("azd", grados_azimuth);
            obj.put("q", QUALITY);
//        obj.put("a", ADQUISICION);
//        obj.put("t", ID_TARJETA == null ? "0" : ID_TARJETA);
            obj.put("c", CODIGO_OPERADOR == null ? "0" : CODIGO_OPERADOR);
//        obj.put("te", ENTRADAS);
//        obj.put("ts", SALIDAS);
            obj.put("l", ID_LINEA == null ? "0" : ID_LINEA);
            obj.put("ly", ID_LINEA_TRAYECTO == null ? "0" : ID_LINEA_TRAYECTO);
//        obj.put("es", "e");
//        obj.put("mi", ID_MISION == null ? "0" : ID_MISION);
//        obj.put("rid", "");
            obj.put("ver", ver_curr);
            if (CHECKED_STOP) {
                JSONObject sched = new JSONObject();
                sched.put("mid", CHECKED_MID);
                sched.put("tid", CHECKED_TID);
                JSONObject v = new JSONObject();
                v.put(CHECKED_STOPID + "", CHECKED_STOP_TIMESTAMP);
                sched.put("v", v);
                obj.put("sched", sched);
                CHECKED_STOP = false;
            }
            if (enviarNMEA) {
                obj.put("nmea", NMEAFRAME);
            }
            if (ModeTracker) {
                obj.put("ptr", tracker);
            }
//        obj.put("dte", dist == -1 ? "" : (dist + ""));
//        obj.put("vt", "0");
            trama += obj;
            logger.debug("TRAMA ENVIADA: \\" + trama + "\\");
            ret = Iso8583Client.sendData(host, port, trama);
            logger.debug("RESPUESTA OBTENIDA: \\" + ret + "\\");
            ultimoEnvioTrama = System.currentTimeMillis() / 1000;
            logger.debug("Tiempo Actual: " + ultimoEnvioTrama);
            /*
            Respuesta AVL Trama
             */
            JSONObject respuesta = new JSONObject(new JSONTokener(ret));
            if (respuesta.get("status").equals("OK")) {
                ultimoEnvioTrama = System.currentTimeMillis() / 1000;
                JSONObject comandos = respuesta.getJSONObject("response");
                if (comandos.has("exec")) {
                    logger.debug("Parseando exec...");
                    JSONArray execs = null;
                    try {
                        execs = comandos.getJSONArray("exec");
                        logger.debug("Llega array de comandos (longitud: " + execs.length() + ").");
                        for (int i = 0; i < execs.length(); i++) {
                            JSONObject command = execs.getJSONObject(i);
                            String cmd = command.getString("command");
                            JSONArray params = command.optJSONArray("param");
                            if (cmd.equals("softwareUpdate")) {
                                String ver_server = params.getString(0);
                                logger.info("Versión actual: \\" + ver_curr + "\\; versión server: \\" + ver_server + "\\");
                                if (ver_curr != null) {
                                    if (!ver_curr.equals(ver_server)) {
                                        String resp_upd = Gitcommand.actualizarVersion();
                                        logger.debug("--RESULTADO UPGRADE-- \\" + resp_upd + "\\");
                                        logger.info("Preparando envíode comando REBOOT a recaudo...");
                                        rebootPhantom();
                                    }
                                }
                            } else if (cmd.equals("sendNMEA")) {
                                enviarNMEA = Boolean.parseBoolean(params.getString(0));
                                logger.info("Se habilita el envío de tramas NMEA: " + enviarNMEA);
                            } else if (cmd.equals("updateFreq")) {
                                UPGRADE_TIME = Long.parseLong(params.getString(0));
                                logger.info("Cambiado el tiempo a:" + params.getString(0));
                            } else if (cmd.equals("pTracker")) {
                                int rsp = Integer.parseInt(params.getString(0));
                                if (rsp == 1) {
                                    ModeTracker = true;
                                    logger.info("Enable ModeTracker:" + params.getString(0));
                                } else {
                                    ModeTracker = false;
                                    logger.info("Disable ModeTracker:" + params.getString(0));
                                }
                            } else if (cmd.equals("updateProg")) {
                                logger.info("Se actualizará la programación.");
                                testLoad();
                            }
                        }
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                } else {
                    logger.debug("No hay exec para ejecutar.");
                }
            }
        } catch (Exception exp) {
            logger.debug("EnviarTrama->" + exp.getMessage());
        }
    }

    public String decodificar(String data) {
        String respuesta = null;
        try {
//            BASE64Decoder b64dec = new BASE64Decoder();
//            byte[] output = b64dec.decodeBuffer(data);
            byte[] output = Base64.getDecoder().decode(data);

            Inflater decompresser = new Inflater();
            decompresser.setInput(output);
            byte[] result = new byte[512 * 1024];
            int resultLength = decompresser.inflate(result);
            decompresser.end();

            respuesta = new String(result, 0, resultLength);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error decodificando: ", e);
        }
        logger.info("Respuesta decodificada: \\" + respuesta + "\\");
        return respuesta;
    }

    public String solicitarProgramacion(String _codigoBus, String compresion) {
        try {
            JSONObject json = new JSONObject();
            json.put("b", _codigoBus);
            json.put("c", compresion);
            String trama = "fetchschedule?data=" + json;
            logger.debug("TRAMA ENVIADA: \\" + trama + "\\");
            String rsp = Iso8583Client.sendData(host, port, trama);
            logger.debug("RPTA: " + rsp);
            return rsp;
        } catch (Exception e) {
            return null;
        }
    }

    public void testLoad() {
        JSONObject jsonobj = null;
        String status = null;
        String respuesta = null;
        String compresion = "1";
        JSONObject response = null;
        try {
            respuesta = solicitarProgramacion(CODIGO_BUS, compresion);
            if (respuesta == null) {
                throw new Exception("Respuesta de servicio offline (null).");
            }
            logger.debug("Respuesta desde el host: \\" + respuesta.length() + "\\" + respuesta + "\\");
            jsonobj = new JSONObject(new JSONTokener(respuesta));
            status = (String) jsonobj.get("status");
            logger.debug("status: \\" + status + "\\");
            if (!status.equals("OK")) {
                logger.warn("Respuesta no exitosa.");
                return;
            }
            String responseEnc = jsonobj.getString("response");
            response = new JSONObject(new JSONTokener(decodificar(responseEnc)));
            String fecha = response.getString("fecha");
            String mid = response.getString("mid");
            String pso = response.getString("pso");
            System.out.println("fecha=" + fecha + "; mid=" + mid + "; pso=" + pso);
            ConexionDB.conectar();
            ConexionDB.con.setAutoCommit(false);
            int prog_id = ConexionDB.guardarDatosProgramacion(mid, pso, fecha);
            System.out.println("prog_id=" + prog_id);
            JSONArray tablas = response.getJSONArray("tabla");
            int tam = tablas.length();
            System.out.println("tablas: " + tam);
            JSONObject tabla;
            int tab_id;
            JSONObject operador, parada, linea;
            JSONArray thorario;
            String nombre_linea, codigo_linea, hora_inicio, hora_fin, tarea, nombre_operador, codigo_operador, clave_operador;
            int id_parada, tiempo_parada, evento_parada;
            String nombre_parada, tipo_parada;
            double latitud_parada, longitud_parada;
            long tid;
            for (int i = 0; i < tam; i++) {
                tabla = tablas.getJSONObject(i);
                tid = tabla.getLong("tid");
                if (tabla.isNull("linea")) {
                    nombre_linea = null;
                    codigo_linea = null;
                } else {
                    linea = tabla.getJSONObject("linea");
                    nombre_linea = linea.getString("nombre");
                    codigo_linea = linea.getString("codigo");
                }
                if (tabla.isNull("operador")) {
                    nombre_operador = null;
                    codigo_operador = null;
                    clave_operador = null;
                } else {
                    operador = tabla.getJSONObject("operador");
                    nombre_operador = operador.getString("nombre");
                    codigo_operador = operador.getString("codigo");
                    clave_operador = operador.getString("clave");
                }
                hora_inicio = tabla.getString("hini");
                hora_fin = tabla.getString("hfin");
                tarea = tabla.getString("tarea");
                System.out.println("Se almacenará la tabla: {'" + tid + "', '" + nombre_linea + "', '" + codigo_linea + "', '" + nombre_operador + "', '" + codigo_operador + "', '" + clave_operador + "', '" + hora_inicio + "', '" + hora_fin + "', '" + tarea + "', '" + prog_id + "'}");
                tab_id = ConexionDB.guardarTabla(tid, nombre_linea, codigo_linea, nombre_operador, codigo_operador, clave_operador, hora_inicio, hora_fin, tarea, prog_id);
                System.out.println("tab_id=" + tab_id);
                thorario = tabla.getJSONArray("thorario");
                for (int j = 0; j < thorario.length(); j++) {
                    parada = thorario.getJSONObject(j);
                    id_parada = parada.getInt("id");
                    tiempo_parada = parada.getInt("tiempo");
                    evento_parada = parada.getInt("evento");
                    nombre_parada = parada.getString("nombre");
                    tipo_parada = parada.getString("tipo");
                    latitud_parada = parada.getDouble("lat");
                    longitud_parada = parada.getDouble("lon");
                    ConexionDB.guardarParada(id_parada, nombre_parada, latitud_parada, longitud_parada, tipo_parada, tiempo_parada, evento_parada, tab_id);
                }
            }
            ConexionDB.con.commit();
            PROGRAMACION = ConexionDB.cargarProgramacion(fecha);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                ConexionDB.con.rollback();
                ConexionDB.con.setAutoCommit(true);
            } catch (Exception e1) {
                logger.error("Error en roolback: ", e);
            }
            logger.error("", e);
            return;
        } finally {
            ConexionDB.desconectar();
        }
    }

    /******************************/
    public void rebootPhantom() {
        try {
            FileInputStream propfile = new FileInputStream("recaudo.properties");
            Properties prop = new Properties();
            prop.load(propfile);
            propfile.close();
            Socket s = new Socket("localhost", Integer.parseInt(prop.getProperty("PORT")));
            s.setSoTimeout(1500);
            ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s.getInputStream());
            output.writeObject("REBOOT_PHANTOM");
            logger.info("Comando REBOOT enviado a Recaudo.");
            String r = (String) input.readObject();
            logger.info("Respuesta desde recaudo: \\" + r + "\\");
            output.close();
            input.close();
            s.close();
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /*public String solicitarIdRecaudo(String host, int port, String id_linea, String id_linea_trayecto, String id_mision, String codigo_operador) {
    String trama = "writeavl?data=";
    String ver_curr = Gitcommand.comprobarVersion();
    JSONObject obj = new JSONObject();
    try {
    long T = System.currentTimeMillis() / 1000;
    obj.put("b", CODIGO_BUS);
    obj.put("e", CODIGO_EQUIPO);
    obj.put("m", T);
    //obj.put("lt", latitud);
    //obj.put("ln", longitud);
    //obj.put("s", satelites);
    //obj.put("v", velocidad);
    obj.put("q", QUALITY);
    obj.put("a", ADQUISICION);
    obj.put("t", ID_TARJETA == null ? "0" : ID_TARJETA);
    obj.put("c", codigo_operador);
    obj.put("te", 0);
    obj.put("ts", 0);
    obj.put("l", id_linea);
    obj.put("ly", id_linea_trayecto);
    obj.put("es", "e");
    obj.put("mi", id_mision);
    obj.put("rid", "0");
    obj.put("ver", ver_curr);
    JSONArray vts = new JSONArray();
    obj.put("vt", vts);
    trama += obj;
    logger.debug("TRAMA RID ENVIADA: \\" + trama + "\\");
    String resp = Iso8583Client.sendData(host, port, trama);
    return resp;
    } catch (Exception e) {
    logger.warn("", e);
    return null;
    }
    }*/
    public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        double r = 6378.7;
        return Math.acos(Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.cos(Math.toRadians(lon2 - lon1))) * r * 1000;
    }

//    public static void main(String[] args) {
//        String prefix = "GP";
//        String[] ntypes = {"GGA", "GSV", "VTG"};
//        new GPSInfo(prefix, ntypes);
//    }

    public static String getMotherboardSN() {
        return "";

    }
    /*
    public static String getMotherboardSNXp() {
    String result = "";
    try {
    File file = File.createTempFile("realhowto", ".vbs");
    file.deleteOnExit();
    FileWriter fw = new java.io.FileWriter(file);
    String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
    + "Set colItems = objWMIService.ExecQuery _ \n"
    + "   (\"Select * from Win32_BaseBoard\") \n"
    + "For Each objItem in colItems \n"
    + "    Wscript.Echo objItem.SerialNumber \n"
    + "    exit for  ' do the first cpu only! \n"
    + "Next \n";
    
    fw.write(vbs);
    fw.close();
    Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
    BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
    String line;
    while ((line = input.readLine()) != null) {
    result += line;
    }
    input.close();
    } catch (Exception e) {
    e.printStackTrace();
    }
    return result.trim();
    }*/
}
