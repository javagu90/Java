package tmslistapasajeros;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FileExtensions extends FileFilter{
    
    String desc, ext;
    public FileExtensions(String desc, String ext) {
        this.desc = desc;
        this.ext = ext;
    }
    
    public boolean accept(File file) {
        return file.getName().toLowerCase().endsWith("."+ext);
  }

    public String getDescription() {
        return desc+"(*."+ext+")";
    }
}
