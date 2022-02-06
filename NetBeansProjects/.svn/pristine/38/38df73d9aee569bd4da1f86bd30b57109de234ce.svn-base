/*
 * JClsAbreSocketAS.java
 *
 * Created on 13 de mayo de 2008, 12:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_cortesterminal.util;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author ocruz
 */
public class JClsAbreSocketAS {
    private String ipAS;
    private int portAS;
    
    /** Creates a new instance of JClsAbreSocketAS */
    public JClsAbreSocketAS(String pIpAS, int pPortAS) {
        setIpAS(pIpAS);
        setPortAS(pPortAS);
    }
    
    public boolean abreSocketAS(){
        Socket s = null;
        try {
            s = new Socket(getIpAS(), getPortAS());
            return true;
        }catch( IOException e ) {
            return false;
        }catch(Exception err){
            return false;
        }
    }
    
    private void setIpAS(String valor){ this.ipAS = valor; }
    private void setPortAS(int valor){ this.portAS = valor; }
    
    private String getIpAS(){ return this.ipAS; }
    private int getPortAS(){ return this.portAS; }
}
