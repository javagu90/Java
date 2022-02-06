package tmslecturaviaxer.util;
        
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
/*
 * CopyFile.java
 *
 * Created on 16 de junio de 2008, 03:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

public class CopyFile {
    public CopyFile(){

    }
   
    public boolean Copy(String address, String localFileName) {
        OutputStream out = null;
        URLConnection conn = null;
        InputStream  in = null;

        try {
            //URL url = new URL(address);
            out = new BufferedOutputStream(new FileOutputStream(localFileName));
            //conn = url.openConnection();
            //in = conn.getInputStream();
            in = new BufferedInputStream(new FileInputStream("c:\\boleteraTMS\\Respaldos\\creado.011"));
            byte[] buffer = new byte[1024];
            int numRead;
            long numWritten = 0;
            while ((numRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, numRead);
                    numWritten += numRead;
        }
            System.out.println(localFileName + "\t" + numWritten);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        } finally {
            try {
                if (in != null) {
                        in.close();
                }
                if (out != null) {
                        out.close();
                }
            } catch (IOException ioe) {
            }

        }

    }

 

        //download("http://192.168.16.159:8080/LoginLite-app-client.jar","C:\\PQTMP\\LoginLite-app-client.jar");

   

}
