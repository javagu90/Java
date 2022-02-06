/*
 * JTextTextField.java
 *
 * Created on 27 de abril de 2007, 01:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package subProcesos;

import java.awt.Toolkit;
import javax.swing.JTextField;

/**
 *
 * subclase de JTextField que acepta solo numeros, 
 * llama a setBeepOnError() para abilitar el beep del sistema al teclear un caracter invalido
 * @author vgonzalez
 */

 public class JTextCardField extends JTextField 
{
  private boolean beep_on_error = false;
/** Creates a new instance of JTextTextField */  
  public JTextCardField()
  {
    super();
    initializeForText();
  }
  
  private void initializeForText()
  {
    setDocument(new javax.swing.text.PlainDocument()
    {
      public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws    javax.swing.text.BadLocationException 
      {
        StringBuffer buf = new StringBuffer(str);
        int size = buf.length();
        char c;
        if (this.getLength()<10)
        {
                if (size>1)
                {
                   //es un setText() y no se valida nada solo se agrega el texto al campo
                   super.insertString(offs, buf.toString().toUpperCase(), a);
                }
                else
                {
                for (int i = 0; i < size; i++)
                {
                  c = buf.charAt(i);

                  // permite ingresar solo digitos
                  if (!Character.isDigit(c) && !Character.isLetter(c))
                  {
                      // beep on error
                      setBeepOnError(true);
                      if (beep_on_error){Toolkit.getDefaultToolkit().beep();}
                         buf.deleteCharAt(i);
                  }
                }//for
                super.insertString(offs, buf.toString().toUpperCase(), a);

               }//else
        }//else(<10)
        else
          {
              setBeepOnError(true);
              if (beep_on_error){Toolkit.getDefaultToolkit().beep();}
          }
            
      }
    });}
  
  public void setBeepOnError(boolean beep)
  {
    beep_on_error = beep;
  }
}