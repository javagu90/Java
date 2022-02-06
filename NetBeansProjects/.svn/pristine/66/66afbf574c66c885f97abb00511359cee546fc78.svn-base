/*
 * PcInfo.java
 *
 * Created on 22 de mayo de 2007, 05:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_cortesterminal.util;

/**
 *
 * @author imunoz
 */
import java.net.InetAddress;

public class PcInfo {
    private String hostName;
    private String hostAddress;
    public PcInfo() {
        try {
            InetAddress i = InetAddress.getLocalHost();
            setHostName(i.getHostName());    // name
            setHostAddress(i.getHostAddress()); // IP address only
        }catch(Exception e){e.printStackTrace();}
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }
}