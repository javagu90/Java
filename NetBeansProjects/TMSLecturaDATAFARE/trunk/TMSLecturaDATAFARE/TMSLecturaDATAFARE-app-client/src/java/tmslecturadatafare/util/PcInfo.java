/*
 * PcInfo.java
 *
 * Created on 3 de septiembre de 2007, 02:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmslecturadatafare.util;

import java.net.InetAddress;

/**
 *
 * @author vgonzalez
 */
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