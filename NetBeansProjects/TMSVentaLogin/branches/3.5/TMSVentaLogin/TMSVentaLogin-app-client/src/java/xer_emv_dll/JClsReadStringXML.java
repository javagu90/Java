/*
 * JClsReadStringXML.java
 *
 * Created on 1 de octubre de 2008, 04:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xer_emv_dll;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author ocruz
 */
public class JClsReadStringXML {
    Document documento = null;
    /**
     * Creates a new instance of JClsReadStringXML
     */
    public JClsReadStringXML() {
    }
    
    private boolean abrirStringXML(String strXML){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ( );
        try{
           DocumentBuilder builder = factory.newDocumentBuilder();           
           documento = builder.parse(new InputSource(new StringReader(strXML)));
        }catch (Exception ex){
           // Algún tipo de error: fichero no accesible, formato de XML incorrecto, etc.
           ex.printStackTrace();
           return false;
        }
        return true;
    }
    
    public String getValorNodo(String strXML, String tag, String nombreAtributo){
        if(!abrirStringXML(strXML)) return null;
        NodeList listaNodos = documento.getElementsByTagName(tag);  
        for(int i=0;i<listaNodos.getLength();i++){  
           Node nodo = listaNodos.item(i);  
           if (nodo instanceof Element){  
              return nodo.getTextContent();
           }  
        } 
        return null;
    }
}