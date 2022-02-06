package tms_plantillas;

import java.awt.Toolkit;
import javax.swing.JTextField;

/**
 * subclase de JTextField que acepta entradas de numeros y '.'  
 * llama a setBeepOnError() para abilitar el beep del sistema al teclear un caracter invalido
 */

public class JCuantityTextField extends JTextField 
{
    private boolean beep_on_error = false;
    private  char c;

    public JCuantityTextField()
  {
    super();
    initializeForCuantity();
  }
  
  private void initializeForCuantity()
   {
    setDocument(new javax.swing.text.PlainDocument()
    {
      public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws    javax.swing.text.BadLocationException 
      {
        StringBuffer buf = new StringBuffer(str);
        int size = buf.length();
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
          // permite solo numeros y '.'  
           if (!Character.isDigit(c) && c != '.')
          {
              // beep de error
              setBeepOnError(true);
              if (beep_on_error){
                 Toolkit.getDefaultToolkit().beep(); 
              }
                 buf.deleteCharAt(i);
          }
        }//for
         //permite que solo exista un solo '.'
         if(c=='.' && this.getText(0,this.getLength()).indexOf(".") >= 0)
             Toolkit.getDefaultToolkit().beep();
         else
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
