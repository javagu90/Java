package subProcesosAcum;

import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


public class JTxtFieldNumeros extends JTextField {
   
    private boolean beep_on_error = false;
    private int maxChar;
    
    public JTxtFieldNumeros() {
        super();
        setMaxChar(10);
        this.setDocument(new PlainDocumentFolios());
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JTxtFieldNumeros(int lengMax ) {
        super();
        setMaxChar(lengMax);
        this.setDocument(new PlainDocumentFolios());
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initializeFolios(){
    
    }
    
    public void setBeepOnError(boolean beep)
    {
      beep_on_error = beep;
    }

    private void jbInit() throws Exception {
        
        this.setHorizontalAlignment(JTextField.RIGHT);
       
    }

    private int getMaxChar() {
        return maxChar;
    }

    public void setMaxChar(int maxChar) {
        this.maxChar = maxChar;
    }


    class PlainDocumentFolios extends PlainDocument{
   
   public void insertString(int offs, String str, AttributeSet a) throws 
        BadLocationException{
        if (str == null) {
             return;
         }
         else if (str != null && getMaxChar() > 0 && this.getLength() + str.length() > getMaxChar())
        {
                Toolkit.getDefaultToolkit().beep();
            return;
        }
        StringBuffer Folio = new StringBuffer(str);
        char num;
         for(int i = 0; i < str.length(); i++){ 
           num = Folio.charAt(i);
           if(!Character.isDigit(num)){
              Folio.deleteCharAt(i);
              }
         }
         super.insertString(offs, new String(Folio.toString()), a);
        }
    }
    
    
}
/**
 * 
 * */