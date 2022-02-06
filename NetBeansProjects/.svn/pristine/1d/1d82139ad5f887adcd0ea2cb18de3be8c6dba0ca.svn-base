package tms_plantillas;
import java.awt.Toolkit;
import javax.swing.JTextField;


 /**
  * subclase de JTextField que acepta solo numeros, 
  * llama a setBeepOnError() para abilitar el beep del sistema al teclear un caracter invalido
  */


public class JNumberTextField extends JTextField 
{
  private boolean beep_on_error = false;
  
  public JNumberTextField()
  {
    super();
    initializeForNumbers();
  }
  
  private void initializeForNumbers()
  {
    setDocument(new javax.swing.text.PlainDocument()
    {
      public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws    javax.swing.text.BadLocationException 
      {
        StringBuffer buf = new StringBuffer(str);
        int size = buf.length();
        char c;
        if (size>1)
        {
           //es un setText() y no se valida nada solo se agrega el texto al campo
           super.insertString(offs, buf.toString(), a);
        }
        else
        {
        for (int i = 0; i < size; i++)
        {
          c = buf.charAt(i);
          
          // permite ingresar solo digitos
          if (!Character.isDigit(c))
          {
              // beep on error
              setBeepOnError(true);
              if (beep_on_error){Toolkit.getDefaultToolkit().beep();}
                 buf.deleteCharAt(i);
          }
        }//for
        super.insertString(offs, buf.toString(), a);
      
       }//else
      }

    });
  }
  
  public void setBeepOnError(boolean beep)
  {
    beep_on_error = beep;
  }
}
