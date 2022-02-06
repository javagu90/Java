package com.alidasoftware.pos.util;
/**
 *  Nombre: Debug
 *  Descripción: Maneja los mensajes a la consola de debug... se puede configurar para que use un archivo
 *  Uso:
 *  
 *  //default modo System.out:
 *  Debug.on = false;
 *  //imprime mensajes con el usuario NA
 *  Debug.print(new Exception().getStackTrace()[0], "esto no se imprime" );
 *  Debug.on = true;
 *  Debug.print(new Exception().getStackTrace()[0], "esto si se imprime" );
 *  
 *   //cambiar a modo file
 *  String testOutFile = "foo.out";
 *  Debug.setLog(testOutFile );
 *  
 *  Debug.on = false;
 *  //imprime mensajes con el usuario PEDRO
 *  Debug.print(User.PEDRO, new Exception().getStackTrace()[0], "esto NO va al file" );
 *  Debug.on = true;
 *  Debug.print(User.PEDRO, new Exception().getStackTrace()[0], "Esto va al file" );
 *  
 *   // cambiar a modo System.out:
 *  Debug.setLog(System.out );
 *  Debug.print(User.PEDRO, new Exception().getStackTrace()[0], "Bye." );
 *  //un ejemplo de la salida de la función es:
 *  [POS-DEBUG][PEDRO][com.alidasoftware.pos.facade.UsuarioFacade/updateUsuario:58] Mensaje de salida
 */

public class Debug {
  public static enum User{NA,PEDRO,RAUL,ADRIANA,JUAN};
  public static boolean on = true;
  /**
   * Stream default System.out
   */
  protected static java.io.PrintStream dest = System.out;
  /**
   * La salida se va a un Stream
   * @param out Si queremos que sea la consola, asignar "System.out"
   */
  public static void setLog( java.io.PrintStream out ) { dest = out; }
  /**
   * La salida es un archivo
   * @param filename nombre del archivo Log
   * @throws java.io.IOException si hay algun problema excepción
   */
  public static void setLog( String filename ) throws java.io.IOException {
    setLog( new java.io.PrintStream(
	      new java.io.FileOutputStream(
                new java.io.File( filename ) ) ) );
    }
  /**
   * Imprime mensaje al Stream seleccionado
   * @param msg Mensaje a mostrar
   * @param traza información de la instrucción ejecutada, siempre debe ser "new Exception().getStackTrace()[0]"
   */
  public static void print(StackTraceElement traza, String msg) {	 
	    if (on){
	    	dest.println("[POS-DEBUG]["+User.NA.toString()+"][" + traza.getClassName()+"/"+traza.getMethodName()+":"+traza.getLineNumber()  + "] " + msg);	
	    }
	  }
  /**
   * Imprime mensaje al Stream seleccionado
   * @param usuario Usuario que muestra este mensaje de log
   * @param msg Mensaje a mostrar
   * @param traza información de la instrucción ejecutada, siempre debe ser "new Exception().getStackTrace()[0]"
   */
  public static void print(User usuario, StackTraceElement traza, String msg) {	 
	    if (on){
	    	dest.println("[POS-DEBUG]["+usuario.toString()+"][" + traza.getClassName()+"/"+traza.getMethodName()+":"+traza.getLineNumber()  + "] " + msg);	
	    }
	  }

}