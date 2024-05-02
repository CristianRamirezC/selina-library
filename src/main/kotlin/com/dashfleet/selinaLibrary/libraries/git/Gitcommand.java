package com.dashfleet.selinaLibrary.libraries.git;

import java.io.*;
import java.util.Map;
import org.apache.log4j.Logger;

public class Gitcommand {

    private static Logger logger = Logger.getLogger("phantom");

    public static String comprobarVersion() {
        try {
            String line, resp = "";
            ProcessBuilder pb = new ProcessBuilder();
            pb = pb.command("C:\\Program Files\\Git\\bin\\git.exe", "log", "-1", "--pretty=%h");
            pb = pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                resp += line + "\n";
            }
            input.close();
            logger.debug("Comando de comprobación ejecutado.");
            return resp.trim();
        } catch (Exception e) {
            logger.error("Error en ejecución de comando GIT: ", e);
            return null;
        }
    }

    public static String actualizarVersion() {
        try {
            /*
            //String line, resp = "";
            ProcessBuilder pb = new ProcessBuilder();
            pb = pb.command("C:\\Program Files\\Git\\bin\\git.exe", "pull", "origin", "master");
            pb = pb.redirectErrorStream(true);
            pb = pb.directory(new File("C:\\phantom\\"));
            Process p = pb.start();
            //BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            logger.debug("Ejecutando comando de actualización:");
            //while ((line = input.readLine()) != null) {
            //logger.debug(line);
            //resp += line + "\n";
            //}
            int res = p.waitFor();
            //input.close();
            logger.debug("Comando de actualización ejecutado. Resultado: \\" + res + "\\");
            //return resp.trim();
            return res + "";*/
            //String cmd = "git --git-dir=/Users/maciekrb/Devel/phantom-bin log -1 --pretty=%h";
            //ProcessBuilder pb = new ProcessBuilder("git","log","-1","--pretty=%h");
            ProcessBuilder pb = new ProcessBuilder("git", "pull", "origin", "master");
            File execPath = new File("c:\\phantom");
            pb.directory(execPath);

            Map env = pb.environment();
            env.put("HOMEPATH", "\\Documents and Settings\\Administrator");
            env.put("WINDIR", "C:\\Windows");
            env.put("COMMONPROGRAMFILES", "C:\\Program Files\\Common Files");
            env.put("USERNAME", "Administrator");
            env.put("PATH", "C:\\Documents and Settings\\Administrator\\bin:.:\\usr\\local\\bin:\\mingw\\bin:\\bin:\\C:\\Windows\\system32:\\C:\\Windows:\\C:\\Windows\\System32\\Wbem:\\C:\\phantom\\jdk1.6.0_22\\bin\\:\\usr\\cmd:\\usr\\bin");
            env.put("HOME", "c:\\Documents and Settings\\Administrator");
            env.put("USERPROFILE", "C:\\Documents and Settings\\Administrator");
            env.put("SYSTEMROOT", "C:\\Windows");
            Process process = pb.start();

            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;

            //System.out.printf("Salida del comando %s :",cmd);

            String salida = "";
            while ((line = br.readLine()) != null) {
                logger.debug(line);
                salida += line + '\n';
            }

            InputStream er = process.getErrorStream();
            InputStreamReader esr = new InputStreamReader(er);
            BufferedReader ebr = new BufferedReader(esr);

            while ((line = ebr.readLine()) != null) {
                logger.debug(line);
                salida += line + '\n';
            }

            //System.out.println("Exit status : " + process.exitValue());
            return salida + "Exit status: " + process.exitValue();
        } catch (Exception e) {
            logger.error("Error en ejecución de comando GIT: ", e);
            return null;
        }
    }
}
