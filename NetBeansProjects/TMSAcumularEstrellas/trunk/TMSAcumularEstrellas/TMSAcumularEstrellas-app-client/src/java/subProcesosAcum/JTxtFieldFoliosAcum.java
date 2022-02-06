package subProcesosAcum;

import java.util.Vector;

import javax.swing.JTextField;


/**
 * subclase de JTextField que acepta solo numeros, 
 * llama a setBeepOnError() para abilitar el beep del sistema al teclear un caracter invalido
 */

 public class JTxtFieldFoliosAcum extends JTextField 
 {
   private static final int DIGITOS_MAX=121;
   
   public JTxtFieldFoliosAcum(){
    super();
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
          StringBuffer asientos = new StringBuffer(str.toUpperCase());
          char c,cb,cb2;
          if(str.length()==1){ //con esto evitamos que se cicle con un settext
          for(int i = 0; i < str.length(); i++){ 
          c = asientos.charAt(i);
              if(Character.isDigit(c) || Character.isLetter(c)){
//              if(Character.isDigit(c)){
                  //se obtiene el elemento anterior guardado en el TextField
                  if(offs > DIGITOS_MAX){
                       cb= this.getText(0,offs).charAt(offs-1);
                       if((Character.isDigit(cb) || Character.isLetter(cb))&& offs >= 2) {
                            //se obtiene el elemento guardado antepenultimo en el TextField
                            cb2= this.getText(0,offs).charAt(offs-2);
                            if(Character.isDigit(cb2) || Character.isLetter(cb2))
                            {
                               asientos.deleteCharAt(i);
                            }
                        }
                   }
              }else if((c==' '|| c=='-') && offs>=1){
                      cb= this.getText(0,offs).charAt(offs-1);
                      //se obtiene el elemento anterior guardado en el textField
//                      if(!Character.isDigit(cb) || !Character.isLetter(cb)){
                      if(!Character.isDigit(cb)){
                          asientos.deleteCharAt(i);
                      }
              }else if(c!='\n'){asientos.deleteCharAt(i);}
          }//for fin
          }//endif para evitar ciclado
          
          super.insertString(offs, new String(asientos.toString().toUpperCase()), a);
         }
       });
     }
   
   public boolean isok(){
    return true;
   }  
}