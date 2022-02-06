/*
 * ImageFilter.java
 *
 * Created on 3 de junio de 2010, 10:31 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_vta_productos_er;

/**
 *
 * @author brojasa
 */
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ImageFilter extends FileFilter {
    final static String jpeg = "jpeg";
    final static String jpg = "jpg";
    final static String gif = "gif";
    final static String png = "png";
    
    // Accept all directories and all gif, jpg, or png files.
    public boolean accept(File f) {

        if (f.isDirectory()) {
            return true;
        }

        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            String extension = s.substring(i+1).toLowerCase();
            if (png.equals(extension) ||
                gif.equals(extension) ||
                jpeg.equals(extension) ||
                jpg.equals(extension)) {
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }
    
    // The description of this filter
    public String getDescription() {
        return "Im�genes Solamente";
    }
}
