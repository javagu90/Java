package subProcesos;

import java.util.Vector;

import javax.swing.JTextField;


/**
 * subclase de JTextField que acepta solo numeros, 
 * llama a setBeepOnError() para abilitar el beep del sistema al teclear un caracter invalido
 */

 public class JTxtFieldAsientos extends JTextField 
 {
   private boolean beep_on_error = false;
   
   public JTxtFieldAsientos(){
    super();
    //this.setInputVerifier(new PassVerifier());
    initializeForAsientos();
   }
   
   private void initializeForAsientos(){
         setDocument(new javax.swing.text.PlainDocument(){
         public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws 
         javax.swing.text.BadLocationException{
         //
          if (str == null) {
              return;
          }
          StringBuffer asientos = new StringBuffer(str); //tipopas = str.toCharArray();
          char c,cb,cb2;
          if(str.length()==1){ //con esto evitamos que se cicle con un settext
          for(int i = 0; i < str.length(); i++){ 
          c = asientos.charAt(i);
              if(Character.isDigit(c)){
//se obtiene el elemento anterior guardado en el TextField
                  if(offs > 1){
                       cb= this.getText(0,offs).charAt(offs-1);
                       if(Character.isDigit(cb) && offs >= 2) {
//se obtiene el elemento guardado antepenultimo en el TextField
                            cb2= this.getText(0,offs).charAt(offs-2);
                            if(Character.isDigit(cb2))
                            {
                               asientos.deleteCharAt(i);
                            }
                        }
                   }
              }else if((c==' '|| c=='-') && offs>=1){
                      cb= this.getText(0,offs).charAt(offs-1);
//se obtiene el elemento anterior guardado en el textField
                      if(!Character.isDigit(cb)){
                          asientos.deleteCharAt(i);
                      }
              }else if(c!='\n'){asientos.deleteCharAt(i);}
          }//for fin
          }//enif para evitar ciclado
          
          super.insertString(offs, new String(asientos.toString()), a);
         }
       });
     }
   
   public void setBeepOnError(boolean beep)
   {
     beep_on_error = beep;
   }

   public boolean isok(){
    return true;
   }  
}