/*
 * JTextTextField.java
 *
 * Created on 27 de abril de 2007, 01:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package subProcesos;

import javax.swing.JTextField;

/**
 *
 * subclase de JTextField que acepta solo numeros, 
 * llama a setBeepOnError() para abilitar el beep del sistema al teclear un caracter invalido
 * @author vgonzalez
 */

 public class JTextTextFieldMaxCharAlfa extends JTextField 
{
  private int maxLength;
  private boolean beep_on_error = false;
/** Creates a new instance of JTextTextField */  
  public JTextTextFieldMaxCharAlfa(int pMaxLength)
  {
    super();
    this.maxLength=pMaxLength;
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
         if (size>1)
           {
               //es un setText() y no se valida nada solo se agrega el texto al campo
               super.insertString(offs, buf.toString(), a);
           }
         else
          {
            if ( offs < maxLength){ // maxLength
            //for (int i = 0; i < size; i++)
            //{
              c = buf.charAt(0);

              // permite ingresar cualquier caracter
              if (Character.isLetter(c)) 
                  super.insertString(offs, buf.toString().toUpperCase() , a);
              else
                  if(c==' ' || Character.isDigit(c))
                    super.insertString(offs, buf.toString() , a);
            //}//for
            }
          }//else
      }
    });}
  
  public void setBeepOnError(boolean beep)
  {
    beep_on_error = beep;
  }
}