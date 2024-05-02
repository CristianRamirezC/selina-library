package com.dashfleet.selinaLibrary.libraries.db;

import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.dashfleet.selinaLibrary.libraries.struct.Parada;
import com.dashfleet.selinaLibrary.libraries.struct.Programacion;
import com.dashfleet.selinaLibrary.libraries.struct.Tabla;

public class ConexionDB {

    private static Logger logger = Logger.getLogger("phantom-avl");
    private static String DB_STRING;
    public static Connection con;

    static {
        try {
            FileInputStream archivo = new FileInputStream("/usr/local/phantom/avl/db.properties");
            Properties prop = new Properties();
            prop.load(archivo);
            archivo.close();
            DB_STRING = prop.getProperty("DB_STRING");
            Class.forName("org.sqlite.JDBC");
            logger.debug("Driver de la base de datos cargado.");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void conectar() throws Exception {
        con = DriverManager.getConnection("jdbc:sqlite:" + DB_STRING);
    }

    public static ArrayList<Object[]> ejecutarConsulta(String consulta, Object[] params) {
        ArrayList<Object[]> resp = null;
        try {
            PreparedStatement stat = con.prepareStatement(consulta);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    stat.setObject(i + 1, params[i]);
                }
            }
            ResultSet rs = stat.executeQuery();
            resp = new ArrayList<Object[]>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            int i;
            while (rs.next()) {
                Object[] aux = new Object[cols];
                for (i = 0; i < cols; i++) {
                    aux[i] = rs.getObject(i + 1);
                }
                resp.add(aux);
            }
            //desconectar(con);
            return resp;
        } catch (Exception e) {
            /*try {
            desconectar(con);
            } catch (Exception ex) {
            }*/
            e.printStackTrace();
        }
        return resp;
    }

    public static Boolean ejecutarSentencia(String sql, Object[] params) {
        int rs = 0;
        //Connection con = null;
        try {
            //con = conectar();
            PreparedStatement stat = con.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    stat.setObject(i + 1, params[i]);
                }
            }
            rs = stat.executeUpdate();
            //desconectar(con);
        } catch (Exception e) {
            /*try {
            desconectar(con);
            } catch (Exception ex) {
            }*/
            e.printStackTrace();
        }
        return rs > 0;
    }

    /*public static ArrayList<Object[]> obtenerVentasPendientes() throws Exception {
    String sql = "select id_tarifa,forma_pago,sum(cantidad),max(fecha_hora) from venta where enviado=0 order by fecha_hora";
    return ejecutarConsulta(sql, null);
    }
    
    public static ArrayList<Object[]> obtenerVentasPendientes(String rid) throws Exception {
    String sql = "select id_tarifa,forma_pago,sum(cantidad),max(fecha_hora) from venta where rid=? and enviado=0 order by fecha_hora";
    return ejecutarConsulta(sql, new Object[]{rid});
    }
    
    public static ArrayList<Object[]> obtenerLRIDsPendientes() throws Exception {
    String sql = "select lrid,id_linea,id_linea_trayecto,id_mision,codigo_operador,codigo_tarjeta from ridlocal where rrid='' and lrid not in(select rid from propiedades)";
    return ejecutarConsulta(sql, null);
    }
    
    public static Object[] obtenerDatosEnvioAVL() throws Exception {
    String sql = "select * from propiedades";
    ArrayList<Object[]> array = ejecutarConsulta(sql, null);
    if (array.isEmpty()) {
    return null;
    }
    return array.get(0);
    }
    
    public static void eliminarRegistrosEnviados(String ultimo) throws Exception {
    String sql = "update venta set enviado=1 where fecha_hora<=?";
    ejecutarSentencia(sql, new Object[]{ultimo});
    }
    
    public static void cerrarRID(String rid) throws Exception {
    String sql = "update ridlocal set enviado='1' where rrid=?";
    ejecutarSentencia(sql, new Object[]{rid});
    }*/

    /*public static void guardarGPS(String lat, String lon, String sat) throws Exception {
    String sql = "update propiedades set latitud=?,longitud=?,satelites=?";
    if (!ejecutarSentencia(sql, new Object[]{lat, lon, sat})) {
    sql = "insert into propiedades (latitud, longitud, satelites) values(?,?,?)";
    ejecutarSentencia(sql, new Object[]{lat, lon, sat});
    }
    }*/

    /*public static String[] obtenerPosicion() throws Exception {
    String sql = "select latitud,longitud from propiedades";
    ArrayList<Object[]> ret = ConexionDB.ejecutarConsulta(sql, null);
    if (ret == null || ret.isEmpty()) {
    return null;
    }
    return new String[]{(String)ret.get(0)[0], (String)ret.get(0)[1]};
    }*/

    /*public static void guardarVelocidad(String vel) throws Exception {
    String sql = "update propiedades set velocidad=?";
    if (!ejecutarSentencia(sql, new Object[]{vel})) {
    sql = "insert into propiedades (velocidad) values(?)";
    ejecutarSentencia(sql, new Object[]{vel});
    }
    }*/

    /*public static void guardarNuevoRID(String rid) throws Exception {
    String sql = "update propiedades set rid=?";
    if (!ejecutarSentencia(sql, new Object[]{rid})) {
    sql = "insert into propiedades (rid) values(?)";
    ejecutarSentencia(sql, new Object[]{rid});
    }
    }
    
    public static void actualizarRID(String arid, String nrid) throws Exception {
    String sql = "update ridlocal set rrid=? where lrid=?";
    ejecutarSentencia(sql, new Object[]{nrid, arid});
    }
    
    public static void actualizarRegistros(String arid, String nrid) throws Exception {
    String sql = "update venta set rid=? where rid=?";
    ejecutarSentencia(sql, new Object[]{nrid, arid});
    }
    
    public static void guardarEstadoTrayecto(String estado) throws Exception {
    String sql = "update propiedades set estado_trayecto=?";
    if (!ejecutarSentencia(sql, new Object[]{estado})) {
    sql = "insert into propiedades (estado_trayecto) values(?)";
    ejecutarSentencia(sql, new Object[]{estado});
    }
    }
    
    public static void resetCierre() {
    String sql = "update propiedades set cierre=0";
    if (!ejecutarSentencia(sql, null)) {
    sql = "insert into propiedades (cierre) values(0)";
    ejecutarSentencia(sql, null);
    }
    }*/
    public static int guardarDatosProgramacion(String mid, String pso, String fecha) throws Exception {
        String sql = "insert into programacion(mid,pso,fecha) values(?,?,?)";
        ejecutarSentencia(sql, new Object[]{mid, pso, fecha});
        sql = "select max(consecutivo) from programacion where pso=?";
        ArrayList<Object[]> resp = ejecutarConsulta(sql, new Object[]{pso});
        return (Integer) resp.get(0)[0];
    }

    public static int guardarTabla(long id, String nombre_linea, String codigo_linea, String nombre_operador, String codigo_operador, String clave_operador, String hora_inicio, String hora_fin, String tarea, int prog_id) throws Exception {
        String sql = "insert into tabla(id,nombre_linea,codigo_linea,nombre_operador,codigo_operador,clave_operador,hora_inicio,hora_fin,tarea,cons_programacion) values(?,?,?,?,?,?,?,?,?,?)";
        ejecutarSentencia(sql, new Object[]{id, nombre_linea, codigo_linea, nombre_operador, codigo_operador, clave_operador, hora_inicio, hora_fin, tarea, prog_id});
        sql = "select consecutivo from tabla where id=?";
        ArrayList<Object[]> resp = ejecutarConsulta(sql, new Object[]{id});
        return (Integer) resp.get(0)[0];
    }

    public static void guardarParada(int id, String nombre, double latitud, double longitud, String tipo, int tiempo, int evento, int tab_id) throws Exception {
        String sql = "insert into parada(id,nombre,latitud,longitud,tipo,tiempo,evento,checked,cons_tabla) values(?,?,?,?,?,?,?,?,?)";
        ejecutarSentencia(sql, new Object[]{id, nombre, latitud, longitud, tipo, tiempo, evento, tab_id});
    }

    public static Programacion cargarProgramacion(String fecha) throws Exception {
        String sql = "select consecutivo,mid,pso from programacion where fecha like ?";
        ArrayList<Object[]> datos = ejecutarConsulta(sql, new Object[]{fecha});
        if (datos.isEmpty()) {
            return null;
        }
        Programacion p = new Programacion();
        p.fecha = fecha;
        p.consecutivo = (Integer) datos.get(0)[0];
        p.mid = Integer.parseInt((String) datos.get(0)[1]);
        p.pso = (String) datos.get(0)[2];
        sql = "select consecutivo,codigo_linea,nombre_linea,nombre_operador,codigo_operador,clave_operador,hora_inicio,hora_fin,tarea,id from tabla where cons_programacion=?";
        datos = ejecutarConsulta(sql, new Object[]{p.consecutivo});
        ArrayList<Object[]> datos1;
        if (!datos.isEmpty()) {
            p.tablas = new ArrayList<Tabla>();
            sql = "select consecutivo,id,nombre,latitud,longitud,tipo,tiempo,evento,checked from parada where cons_tabla=? order by tiempo";
            for (int i = 0; i < datos.size(); i++) {
                Tabla t = new Tabla();
                t.consecutivo = (Integer) datos.get(i)[0];
                t.codigo_linea = (String) datos.get(i)[1];
                t.nombre_linea = (String) datos.get(i)[2];
                t.nombre_operador = (String) datos.get(i)[3];
                t.codigo_operador = (String) datos.get(i)[4];
                t.clave_operador = (String) datos.get(i)[5];
                t.hora_inicio = (String) datos.get(i)[6];
                t.hora_fin = (String) datos.get(i)[7];
                t.tarea = (String) datos.get(i)[8];
                t.tid = (Integer) datos.get(i)[9];
                t.cons_programacion = p.consecutivo;
                datos1 = ejecutarConsulta(sql, new Object[]{t.consecutivo});
                t.paradas = new ArrayList<Parada>();
                for (int j = 0; j < datos1.size(); j++) {
                    Parada par = new Parada();
                    par.consecutivo = (Integer) datos1.get(i)[0];
                    par.id = Integer.parseInt((String) datos1.get(i)[1]);
                    par.nombre = (String) datos1.get(i)[2];
                    par.latitud = (Double) datos1.get(i)[3];
                    par.longitud = (Double) datos1.get(i)[4];
                    par.tipo = (String) datos1.get(i)[5];
                    par.tiempo = (Integer) datos1.get(i)[6];
                    par.evento = (Integer) datos1.get(i)[7];
                    par.checked = (Long) datos1.get(i)[8];
                    par.cons_tabla = t.consecutivo;
                    t.paradas.add(par);
                }
                p.tablas.add(t);
            }
        }
        return p;
    }

    public static boolean chequearParada(Parada p) throws Exception {
        String sql = "update parada set checked=? where consecutivo=?";
        return ejecutarSentencia(sql, new Object[]{p.checked, p.consecutivo});
    }

    public static void desconectar() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
