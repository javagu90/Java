/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;


import javax.jws.WebService;
import javax.jws.WebMethod;

import javax.ejb.Stateless;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.swing.JOptionPane;

/**
 *
 * @author Javy
 */
@WebService(serviceName = "ExportarExcel")
@Stateless()
@ManagedBean
@RequestScoped
public class ExportarExcel {

        /*Datos de conexion*/
    private String db;
    private String user;
    private String pass;
    private String url;
    private Connection conect;
    File archivo;

    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "writeExcel")
    public void writeExcel() {
    
            //this.db="webmilab_sedesol";
            //this.user="javagu90";
        this.db="placeho6_sedesol";
        this.user="placeho6_webmila";
        this.pass="webmilab";
        this.url="jdbc:mysql://webmilab.com:3306/"+this.db;
        try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conect = DriverManager.getConnection( this.url, this.user , this.pass );
         if ( conect!=null ){
            System.out.println("Conexión a la base de datos "+this.db+"...... Listo ");
         }
      }catch(SQLException | ClassNotFoundException e){
         System.err.println( e.getMessage() );
      }
        this.archivo=new File("c:\\temporal\\outputcomplete.xls");
        
        
        int row=2;
        int columnas=0;
        String NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'adicional'";
        
        /*formato de fuente para el contenido del texto*/
        WritableFont wf= new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
        WritableCellFormat cf= new WritableCellFormat(wf);
        
        WritableFont wf2= new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
        WritableCellFormat cf2= new WritableCellFormat(wf2);
        
        try {
            cf2.setBorder(Border.ALL, BorderLineStyle.THIN);
            cf.setBorder(Border.ALL, BorderLineStyle.THIN);
        } catch (WriteException ex) {
            Logger.getLogger(ExportarExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /*Interfaz para una hoja de cálculo*/
        WritableSheet excelSheet= null;
        WritableWorkbook workbook=null;
        
        /*Estableciendo la configuración regional para generar la hoja de cálculo*/
        WorkbookSettings wbSettings= new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        
        try {
            workbook = Workbook.createWorkbook( archivo, wbSettings );
            //hoja con nombre de la tabla
            workbook.createSheet( "Adicional", 0 );
            excelSheet = workbook.getSheet(0);
            Label encabezadoidAdicional  = new Label( 0 , 1, "idAdicional" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTipo  = new Label( 1 , 1, "tipo" , cf2 );
            Label encabezadoOrigen  = new Label( 2 , 1, "origen" , cf2 );
            Label encabezadoDestino  = new Label( 3 , 1, "destino" , cf2 );
            Label encabezadoKilometro  = new Label( 4 , 1, "kilometro" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMetro  = new Label( 5 , 1, "metro" , cf2 );
            Label encabezadoAdministracion  = new Label( 6 , 1, "administracion" , cf2 );
            Label encabezadoTransito  = new Label( 7 , 1, "transito" , cf2 );
            Label encabezadoCodigo  = new Label( 8 , 1, "codigo" , cf2 );
            Label encabezadoMargen  = new Label( 9 , 1, "margen" , cf2 );
            Label encabezadoDomicilio_idDomicilio  = new Label( 10 , 1, "Domicilio_idDomicilio" , cf2 );
            try {
                     excelSheet.addCell( encabezadoidAdicional );
                     excelSheet.addCell( encabezadoTipo );
                     excelSheet.addCell( encabezadoOrigen );
                     excelSheet.addCell( encabezadoDestino);
                     excelSheet.addCell( encabezadoKilometro );
                     excelSheet.addCell( encabezadoMetro );
                     excelSheet.addCell( encabezadoAdministracion );
                     excelSheet.addCell( encabezadoTransito);
                     excelSheet.addCell( encabezadoCodigo );
                     excelSheet.addCell( encabezadoMargen );
                     excelSheet.addCell( encabezadoDomicilio_idDomicilio);
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (IOException ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        String sql = "SELECT * FROM adicional ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idAdicional  = new Label( 0 , row, res.getString( "idAdicional" ) , cf );                  
                  Label tipo= new Label( 1 , row, res.getString( "tipo" ) , cf );                  
                  Label origen= new Label( 2 , row, res.getString( "origen" ) , cf );
                  Label destino= new Label( 3 , row, res.getString( "destino" ) , cf );
                  Label kilometro  = new Label( 4 , row, res.getString( "kilometro" ) , cf );                  
                  Label metro= new Label( 5 , row, res.getString( "metro" ) , cf );                  
                  Label administracion= new Label( 6 , row, res.getString( "administracion" ) , cf );
                  Label transito= new Label( 7 , row, res.getString( "transito" ) , cf );
                  Label codigo  = new Label( 8 , row, res.getString( "codigo" ) , cf );
                  Label margen  = new Label( 9 , row, res.getString( "margen" ) , cf );
                  Label Domicilio_idDomicilio  = new Label( 10 , row, res.getString( "Domicilio_idDomicilio" ) , cf );
                 row ++;                  
                 try {
                     excelSheet.addCell( idAdicional );
                     excelSheet.addCell( tipo );
                     excelSheet.addCell( origen );
                     excelSheet.addCell( destino );
                     excelSheet.addCell( kilometro );
                     excelSheet.addCell( metro );
                     excelSheet.addCell( administracion );
                     excelSheet.addCell( transito );
                     excelSheet.addCell( codigo );
                     excelSheet.addCell( margen );
                     excelSheet.addCell( Domicilio_idDomicilio );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //segunda pestaña        
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'aparatos'";
            row=2;
            workbook.createSheet( "Aparatos", 1 );
            excelSheet = workbook.getSheet(1);
            Label encabezadoidAparatos  = new Label( 0 , 1, "idAparatos" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoValor  = new Label( 1 , 1, "valor" , cf2 );
            Label encabezadoSituacion  = new Label( 2 , 1, "situacion" , cf2 );
            Label encabezadoEncuesta_idEncuesta  = new Label( 3 , 1, "Encuesta_idEncuesta" , cf2 );
            try {
                     excelSheet.addCell( encabezadoidAparatos );
                     excelSheet.addCell( encabezadoValor );
                     excelSheet.addCell( encabezadoSituacion );
                     excelSheet.addCell( encabezadoEncuesta_idEncuesta);
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM aparatos ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idAparatos  = new Label( 0 , row, res.getString( "idAparatos" ) , cf );                  
                  Label valor= new Label( 1 , row, res.getString( "valor" ) , cf );                  
                  Label situacion= new Label( 2 , row, res.getString( "situacion" ) , cf );
                  Label encuesta_idEncuesta= new Label( 3 , row, res.getString( "Encuesta_idEncuesta" ) , cf );
                 row ++;                  
                 try {
                     excelSheet.addCell( idAparatos );
                     excelSheet.addCell( valor );
                     excelSheet.addCell( situacion );
                     excelSheet.addCell( encuesta_idEncuesta );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //tercera pestaña        
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'areas'";
            row=2;
            workbook.createSheet( "Areas", 2 );
            excelSheet = workbook.getSheet(2);
            Label encabezadoidArea  = new Label( 0 , 1, "idArea" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoidEncuesta  = new Label( 1 , 1, "idEncuesta" , cf2 );
            Label encabezadoFigura  = new Label( 2 , 1, "figura" , cf2 );
            Label encabezadoMedida1  = new Label( 3 , 1, "medida1" , cf2 );
            Label encabezadoMedida2  = new Label( 4 , 1, "medida2" , cf2 );
            Label encabezadoSubtotal  = new Label( 5 , 1, "subtotal" , cf2 );
            Label encabezadoTipo  = new Label( 6 , 1, "tipo" , cf2 );
            try {
                     excelSheet.addCell( encabezadoidArea );
                     excelSheet.addCell( encabezadoidEncuesta );
                     excelSheet.addCell( encabezadoFigura );
                     excelSheet.addCell( encabezadoMedida1);
                     excelSheet.addCell( encabezadoMedida2);
                     excelSheet.addCell( encabezadoSubtotal);
                     excelSheet.addCell( encabezadoTipo);
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM areas ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idArea  = new Label( 0 , row, res.getString( "idArea" ) , cf );                  
                  Label idEncuesta= new Label( 1 , row, res.getString( "idEncuesta" ) , cf );                  
                  Label figura= new Label( 2 , row, res.getString( "figura" ) , cf );
                  Label medida1= new Label( 3 , row, res.getString( "medida1" ) , cf );
                  Label medida2= new Label( 4 , row, res.getString( "medida2" ) , cf );
                  Label subtotal= new Label( 5 , row, res.getString( "subtotal" ) , cf );
                  Label tipo= new Label( 6 , row, res.getString( "tipo" ) , cf );
                 row ++;                  
                 try {
                     excelSheet.addCell( idArea );
                     excelSheet.addCell( idEncuesta );
                     excelSheet.addCell( figura );
                     excelSheet.addCell( medida1 );
                     excelSheet.addCell( medida2 );
                     excelSheet.addCell( subtotal );
                     excelSheet.addCell( tipo );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
         
        //cuarta pestaña        
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'comunicacion'";
            row=2;
            workbook.createSheet( "Comunicación", 3 );
            excelSheet = workbook.getSheet(3);
            Label encabezadoClaveMensaje  = new Label( 0 , 1, "claveMensaje" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoFiltros  = new Label( 1 , 1, "filtros" , cf2 );
            
            try {
                     excelSheet.addCell( encabezadoClaveMensaje );
                     excelSheet.addCell( encabezadoFiltros );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM comunicacion ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label claveMensaje  = new Label( 0 , row, res.getString( "claveMensaje" ) , cf );                  
                  Label filtros= new Label( 1 , row, res.getString( "filtros" ) , cf );                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( claveMensaje );
                     excelSheet.addCell( filtros );
                  } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        
        //quinta pestaña        
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'croquis'";
            row=2;
            workbook.createSheet( "Croquis", 4 );
            excelSheet = workbook.getSheet(4);
            Label encabezadoidCroquis  = new Label( 0 , 1, "IDcroquis" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoImagen  = new Label( 1 , 1, "imagen" , cf2 );
            Label encabezadoTipo  = new Label( 2 , 1, "tipo" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoVivienda_idVivienda  = new Label( 3 , 1, "vivienda_IdVivienda" , cf2 );
            try {
                     excelSheet.addCell( encabezadoidCroquis );
                     excelSheet.addCell( encabezadoImagen );
                     excelSheet.addCell( encabezadoTipo );
                     excelSheet.addCell( encabezadoVivienda_idVivienda );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM croquis ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idCroquis  = new Label( 0 , row, res.getString( "idCroquis" ) , cf );                  
                  Label imagen= new Label( 1 , row, res.getString( "imagen" ) , cf );
                  Label tipo  = new Label( 0 , row, res.getString( "tipo" ) , cf );                  
                  Label vivienda_idVivienda= new Label( 1 , row, res.getString( "vivienda_idVivienda" ) , cf );                  
                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idCroquis );
                     excelSheet.addCell( imagen );
                     excelSheet.addCell( tipo );
                     excelSheet.addCell( vivienda_idVivienda );
                  } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        
        //sexta pestaña        
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'cuartos'";
            row=2;
            workbook.createSheet( "Cuartos", 5 );
            excelSheet = workbook.getSheet(5);
            Label encabezadoidCuartos  = new Label( 0 , 1, "IDcuartos" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNumero  = new Label( 1 , 1, "numero" , cf2 );
            Label encabezadoUso  = new Label( 2 , 1, "uso" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoPiso  = new Label( 3 , 1, "piso" , cf2 );
            Label encabezadoMuroCarga  = new Label( 4 , 1, "muroCarga" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMuroSeparacion  = new Label( 5 , 1, "muroSeparacion" , cf2 );
            Label encabezadoMatMuro  = new Label( 6 , 1, "matMuro" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoEstructuraTecho  = new Label( 7 , 1, "estructuraTecho" , cf2 );
            Label encabezadomatEstructuraTecho  = new Label( 8 , 1, "matEstructuraTecho" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTechumbre  = new Label( 9 , 1, "techumbre" , cf2 );
            Label encabezadomatTechumbre  = new Label( 10 , 1, "matTechumbre" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoEncuesta_idEncuesta  = new Label( 11 , 1, "Encuesta_idEncuesta" , cf2 );
            try {
                     excelSheet.addCell( encabezadoidCuartos );
                     excelSheet.addCell( encabezadoNumero );
                     excelSheet.addCell( encabezadoUso );
                     excelSheet.addCell( encabezadoPiso );
                     excelSheet.addCell( encabezadoMuroCarga );
                     excelSheet.addCell( encabezadoMuroSeparacion );
                     excelSheet.addCell( encabezadoMatMuro );
                     excelSheet.addCell( encabezadoEstructuraTecho );
                     excelSheet.addCell( encabezadomatEstructuraTecho );
                     excelSheet.addCell( encabezadoTechumbre );
                     excelSheet.addCell( encabezadomatTechumbre );
                     excelSheet.addCell( encabezadoEncuesta_idEncuesta );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM cuartos ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idCuartos  = new Label( 0 , row, res.getString( "idCuartos" ) , cf );                  
                  Label numero= new Label( 1 , row, res.getString( "numero" ) , cf );
                  Label uso  = new Label( 2 , row, res.getString( "uso" ) , cf );                  
                  Label piso= new Label( 3 , row, res.getString( "piso" ) , cf );                  
                  Label muroCarga  = new Label( 4 , row, res.getString( "muroCarga" ) , cf );                  
                  Label muroSeparacion= new Label( 5 , row, res.getString( "muroSeparacion" ) , cf );
                  Label matMuro  = new Label( 6 , row, res.getString( "matMuro" ) , cf );                  
                  Label estructuraTecho= new Label( 7 , row, res.getString( "estructuraTecho" ) , cf ); 
                  Label matEstructuraTecho  = new Label( 8 , row, res.getString( "matEstructuraTecho" ) , cf );                  
                  Label techumbre= new Label( 9 , row, res.getString( "techumbre" ) , cf );
                  Label matTechumbre  = new Label( 10 , row, res.getString( "matTechumbre" ) , cf );                  
                  Label Encuesta_idEncuesta= new Label( 11 , row, res.getString( "Encuesta_idEncuesta" ) , cf ); 
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idCuartos );
                     excelSheet.addCell( numero );
                     excelSheet.addCell( uso );
                     excelSheet.addCell( piso );
                     excelSheet.addCell( muroCarga );
                     excelSheet.addCell( muroSeparacion );
                     excelSheet.addCell( matMuro );
                     excelSheet.addCell( estructuraTecho );
                     excelSheet.addCell( matEstructuraTecho );
                     excelSheet.addCell( techumbre );
                     excelSheet.addCell( matTechumbre );
                     excelSheet.addCell( Encuesta_idEncuesta );
                  } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //septima pestaña        
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'discapacidades'";
            row=2;
            workbook.createSheet( "Discapacidades", 6 );
            excelSheet = workbook.getSheet(6);
            Label encabezadoidDiscapacidades  = new Label( 0 , 1, "idDiscapacidades" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoOrigen  = new Label( 1 , 1, "origen" , cf2 );
            Label encabezadoValorC1  = new Label( 2 , 1, "valorC1" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoValorC2  = new Label( 3 , 1, "valorC2" , cf2 );
            Label encabezadoIntegrantes_idIntegrantes  = new Label( 4 , 1, "Integrantes_idIntegrantes" , cf2 ); //columna, fila, elemento, formato
            try {
                     excelSheet.addCell( encabezadoidDiscapacidades );
                     excelSheet.addCell( encabezadoOrigen );
                     excelSheet.addCell( encabezadoValorC1 );
                     excelSheet.addCell( encabezadoValorC2 );
                     excelSheet.addCell( encabezadoIntegrantes_idIntegrantes );
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM discapacidades ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idDiscapacidades  = new Label( 0 , row, res.getString( "idDiscapacidades" ) , cf );                  
                  Label origen= new Label( 1 , row, res.getString( "origen" ) , cf );
                  Label valorC1  = new Label( 2 , row, res.getString( "valorC1" ) , cf );                  
                  Label valorC2= new Label( 3 , row, res.getString( "valorC2" ) , cf );                  
                  Label Integrantes_idIntegrantes  = new Label( 4 , row, res.getString( "Integrantes_idIntegrantes" ) , cf );                  
                 
                 row ++;                  
                 try {
                     excelSheet.addCell( idDiscapacidades );
                     excelSheet.addCell( origen );
                     excelSheet.addCell( valorC1 );
                     excelSheet.addCell( valorC2 );
                     excelSheet.addCell( Integrantes_idIntegrantes );
                     
                  } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //octava pestaña        
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'dispositivos'";
            row=2;
            workbook.createSheet( "Dispositivos", 7 );
            excelSheet = workbook.getSheet(7);
            Label encabezadoContador  = new Label( 0 , 1, "contador" , cf2 ); //columna, fila, elemento, formato
            try {
                     excelSheet.addCell( encabezadoContador );
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM dispositivos ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label contador  = new Label( 0 , row, res.getString( "contador" ) , cf );                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( contador );
                     
                  } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        } 
        
        //novena pestaña        
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'domicilio'";
            row=2;
            workbook.createSheet( "Domicilio", 8 );
            excelSheet = workbook.getSheet(8);
            Label encabezadoidDomicilio  = new Label( 0 , 1, "IDdomicilio" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTipoVialidad  = new Label( 1 , 1, "tipoVialidad" , cf2 );
            Label encabezadoNombreVialidad  = new Label( 2 , 1, "nombreVialidad" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNumExterior  = new Label( 3 , 1, "numExterior" , cf2 );
            Label encabezadoLetraExterior  = new Label( 4 , 1, "LetraExterior" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoConocido  = new Label( 5 , 1, "conocido" , cf2 );
            Label encabezadoNumExteriorAnterior  = new Label( 6 , 1, "numExteriorAnterior" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNumInterior  = new Label( 7 , 1, "numInterior" , cf2 );
            Label encabezadoLetraInterior  = new Label( 8 , 1, "letraInterior" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTipoAsentamiento  = new Label( 9 , 1, "tipoAsentamiento" , cf2 );
            Label encabezadoNombreAsentamiento  = new Label( 10 , 1, "nomAsentamiento" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoEntreTipoV1  = new Label( 11 , 1, "EntreTipoV1" , cf2 );
            
            Label encabezadoEntreNombreV1  = new Label( 12 , 1, "EntreNombreV1" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoEntreTipoV2  = new Label( 13 , 1, "EntreTipoV2" , cf2 );
            Label encabezadoEntreNombreV2  = new Label( 14 , 1, "EntreNombreV2" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoPosteriorTipo  = new Label( 15 , 1, "PosteriorTipo" , cf2 );
            Label encabezadoPosteriorNombre  = new Label( 16 , 1, "PosteriorNombre" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoDescripcion  = new Label( 17 , 1, "Descripcion" , cf2 );
            Label encabezadoCodigoPostal  = new Label( 18 , 1, "CódigoPostal" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMunicipio  = new Label( 19 , 1, "Municipio" , cf2 );
            Label encabezadoClaveMunicipio  = new Label( 20 , 1, "ClaveMunicipio" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoLocalidad  = new Label( 21 , 1, "Localidad" , cf2 );
            Label encabezadoClaveLocalidad  = new Label( 22 , 1, "ClaveLocalidad" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoClaveAGEB  = new Label( 23 , 1, "ClaveAGEB" , cf2 );
            
            Label encabezadoClaveManzana  = new Label( 24 , 1, "ClaveManzana" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoClavePoligono  = new Label( 25 , 1, "ClavePoligono" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoLatitud  = new Label( 26 , 1, "Latitud" , cf2 );
            Label encabezadoLongitud  = new Label( 27 , 1, "Longitud" , cf2 );
            Label encabezadoEncuesta_idEncuesta= new Label( 28 , 1, "Encuesta_idEncuesta" , cf2 ); 
                 
            try {
                     excelSheet.addCell( encabezadoidDomicilio );
                     excelSheet.addCell( encabezadoTipoVialidad );
                     excelSheet.addCell( encabezadoNombreVialidad );
                     excelSheet.addCell( encabezadoNumExterior );
                     excelSheet.addCell( encabezadoLetraExterior );
                     excelSheet.addCell( encabezadoConocido );
                     excelSheet.addCell( encabezadoNumExteriorAnterior );
                     excelSheet.addCell( encabezadoNumInterior );
                     excelSheet.addCell( encabezadoLetraInterior );
                     excelSheet.addCell( encabezadoTipoAsentamiento );
                     excelSheet.addCell( encabezadoNombreAsentamiento );
                     excelSheet.addCell( encabezadoEntreTipoV1 );
                     excelSheet.addCell( encabezadoEntreNombreV1 );
                     excelSheet.addCell( encabezadoEntreTipoV2 );
                     excelSheet.addCell( encabezadoEntreNombreV2 );
                     excelSheet.addCell( encabezadoPosteriorTipo );
                     excelSheet.addCell( encabezadoPosteriorNombre );
                     excelSheet.addCell( encabezadoDescripcion );
                     excelSheet.addCell( encabezadoCodigoPostal );
                     excelSheet.addCell( encabezadoMunicipio );
                     excelSheet.addCell( encabezadoClaveMunicipio );
                     excelSheet.addCell( encabezadoLocalidad );
                     excelSheet.addCell( encabezadoClaveLocalidad );
                     excelSheet.addCell( encabezadoClaveAGEB );
                     excelSheet.addCell( encabezadoClaveManzana);
                     excelSheet.addCell( encabezadoClavePoligono );
                     excelSheet.addCell( encabezadoLatitud );
                     excelSheet.addCell( encabezadoLongitud );
                     excelSheet.addCell( encabezadoEncuesta_idEncuesta );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM domicilio ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idDomicilio  = new Label( 0 , row, res.getString( "idDomicilio" ) , cf );                  
                  Label tipoVialidad= new Label( 1 , row, res.getString( "tipoVialidad" ) , cf );
                  Label nombreVialidad  = new Label( 2 , row, res.getString( "nombreVialidad" ) , cf );                  
                  Label numExterior= new Label( 3 , row, res.getString( "numExterior" ) , cf );                  
                  Label letraExterior  = new Label( 4 , row, res.getString( "letraExterior" ) , cf );                  
                  Label conocido= new Label( 5 , row, res.getString( "conocido" ) , cf );
                  Label numExteriorAnterior  = new Label( 6 , row, res.getString( "numExteriorAnterior" ) , cf );                  
                  Label numInterior= new Label( 7 , row, res.getString( "numInterior" ) , cf ); 
                  Label letraInterior  = new Label( 8 , row, res.getString( "letraInterior" ) , cf );                  
                  Label tipoAsentamiento= new Label( 9 , row, res.getString( "tipoAsentamiento" ) , cf );
                  Label nombreAsentamiento  = new Label( 10 , row, res.getString( "nombreAsentamiento" ) , cf );                  
                  Label entreTipoV1= new Label( 11 , row, res.getString( "entreTipoV1" ) , cf );
                  Label entreNombreV1  = new Label( 12 , row, res.getString( "entreNombreV1" ) , cf );                  
                  Label entreTipoV2= new Label( 13 , row, res.getString( "entreTipoV2" ) , cf );
                  Label entreNombreV2  = new Label( 14 , row, res.getString( "entreNombreV2" ) , cf );                  
                  Label posteriorTipo= new Label( 15, row, res.getString( "posteriorTipo" ) , cf );                  
                  Label posteriorNombre  = new Label( 16 , row, res.getString( "posteriorNombre" ) , cf );                  
                  Label descripcion= new Label( 17 , row, res.getString( "descripcion" ) , cf );
                  Label codigoPostal  = new Label( 18 , row, res.getString( "codigoPostal" ) , cf );                  
                  Label municipio= new Label( 19 , row, res.getString( "municipio" ) , cf ); 
                  Label claveMunicipio  = new Label( 20 , row, res.getString( "claveMunicipio" ) , cf );                  
                  Label localidad= new Label( 21 , row, res.getString( "localidad" ) , cf );
                  Label claveLocalidad  = new Label( 22 , row, res.getString( "claveLocalidad" ) , cf );                  
                  Label claveAGEB= new Label( 23 , row, res.getString( "claveAGEB" ) , cf ); 
                  Label claveManzana  = new Label( 24 , row, res.getString( "claveManzana" ) , cf );                  
                  Label clavePoligono= new Label( 25 , row, res.getString( "clavePoligono" ) , cf );
                  Label latitud  = new Label( 26 , row, res.getString( "latitud" ) , cf );                  
                  Label longitud= new Label( 27 , row, res.getString( "longitud" ) , cf );                  
                  Label Encuesta_idEncuesta  = new Label( 28 , row, res.getString( "Encuesta_idEncuesta" ) , cf );                  
                                    
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idDomicilio );
                     excelSheet.addCell( tipoVialidad );
                     excelSheet.addCell( nombreVialidad );
                     excelSheet.addCell( numExterior );
                     excelSheet.addCell( letraExterior );
                     excelSheet.addCell( conocido );
                     excelSheet.addCell( numExteriorAnterior );
                     excelSheet.addCell( numInterior );
                     excelSheet.addCell( letraInterior );
                     excelSheet.addCell( tipoAsentamiento );
                     excelSheet.addCell( nombreAsentamiento );
                     
                     excelSheet.addCell( entreTipoV1 );
                     excelSheet.addCell( entreNombreV1 );
                     excelSheet.addCell( entreTipoV2 );
                     excelSheet.addCell( entreNombreV2 );
                     excelSheet.addCell( posteriorTipo );
                     excelSheet.addCell( posteriorNombre );
                     excelSheet.addCell( descripcion );
                     excelSheet.addCell( codigoPostal );
                     excelSheet.addCell( municipio );
                     excelSheet.addCell( claveMunicipio );
                     excelSheet.addCell( localidad );
                     
                     excelSheet.addCell( claveLocalidad );
                     excelSheet.addCell( claveAGEB );
                     excelSheet.addCell( claveManzana );
                     excelSheet.addCell( clavePoligono );
                     excelSheet.addCell( latitud );
                     excelSheet.addCell( longitud );
                     excelSheet.addCell( Encuesta_idEncuesta );
                  } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //decima pestaña
       
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'encuesta'";
            row=2;
            workbook.createSheet( "Encuesta", 9 );
            excelSheet = workbook.getSheet(9);
            Label encabezadoidEncuesta  = new Label( 0 , 1, "idEncuesta" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoFolioCUIS  = new Label( 1 , 1, "FolioCUIS" , cf2 );
            Label encabezadoFolioSEDESOL  = new Label( 2 , 1, "FolioSEDESOL" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoFolioPrograma  = new Label( 3 , 1, "FolioPrograma" , cf2 );
            Label encabezadoFolioInterno  = new Label( 4 , 1, "FolioInterno" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoAmpliacion  = new Label( 5 , 1, "Ampliacion" , cf2 );
            Label encabezadoMejoramiento  = new Label( 6 , 1, "Mejoramiento" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoAdquisicion  = new Label( 7 , 1, "Adquisicion" , cf2 );
            Label encabezadoEdificacion  = new Label( 8 , 1, "Edificacion" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoOtraVivienda  = new Label( 9 , 1, "OtraVivienda" , cf2 );
            Label encabezadoFoto1  = new Label( 10 , 1, "Foto1" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoFoto2  = new Label( 11 , 1, "Foto2" , cf2 );
            Label encabezadoFoto3  = new Label( 12 , 1, "Foto3" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoFoto4  = new Label( 13 , 1, "Foto4" , cf2 );
            Label encabezadoCroquis1  = new Label( 14 , 1, "Croquis1" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoCroquis2  = new Label( 15 , 1, "Croquis2" , cf2 );
            Label encabezadoCroquis3  = new Label( 16 , 1, "Croquis3" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoCroquis4  = new Label( 17 , 1, "Croquis4" , cf2 );
            Label encabezadoNumVisitas  = new Label( 18 , 1, "NumVisitas" , cf2 ); //columna, fila, elemento, formato
                 
            try {
                     excelSheet.addCell( encabezadoidEncuesta );
                     excelSheet.addCell( encabezadoFolioCUIS );
                     excelSheet.addCell( encabezadoFolioSEDESOL );
                     excelSheet.addCell( encabezadoFolioPrograma );
                     excelSheet.addCell( encabezadoFolioInterno );
                     excelSheet.addCell( encabezadoAmpliacion );
                     excelSheet.addCell( encabezadoMejoramiento );
                     excelSheet.addCell( encabezadoAdquisicion );
                     excelSheet.addCell( encabezadoEdificacion );
                     excelSheet.addCell( encabezadoOtraVivienda );
                     excelSheet.addCell( encabezadoFoto1 );
                     excelSheet.addCell( encabezadoFoto2 );
                     excelSheet.addCell( encabezadoFoto3 );
                     excelSheet.addCell( encabezadoFoto4 );
                     excelSheet.addCell( encabezadoCroquis1 );
                     excelSheet.addCell( encabezadoCroquis2 );
                     excelSheet.addCell( encabezadoCroquis3 );
                     excelSheet.addCell( encabezadoCroquis4 );
                     excelSheet.addCell( encabezadoNumVisitas );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM encuesta ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idEncuesta  = new Label( 0 , row, res.getString( "idEncuesta" ) , cf );                  
                  Label folioCUIS= new Label( 1 , row, res.getString( "folioCUIS" ) , cf );
                  Label folioSEDESOL  = new Label( 2 , row, res.getString( "folioSEDESOL" ) , cf );                  
                  Label folioPrograma= new Label( 3 , row, res.getString( "folioPrograma" ) , cf );                  
                  Label folioInterno  = new Label( 4 , row, res.getString( "folioInterno" ) , cf );                  
                  Label ampliacion= new Label( 5 , row, res.getString( "ampliacion" ) , cf );
                  Label mejoramiento  = new Label( 6 , row, res.getString( "mejoramiento" ) , cf );                  
                  Label adquisicion= new Label( 7 , row, res.getString( "adquisicion" ) , cf ); 
                  Label edificacion  = new Label( 8 , row, res.getString( "edificacion" ) , cf );                  
                  Label otraVivienda= new Label( 9 , row, res.getString( "otraVivienda" ) , cf );
                  Label foto1  = new Label( 10 , row, res.getString( "foto1" ) , cf );                  
                  Label foto2= new Label( 11 , row, res.getString( "foto2" ) , cf );
                  Label foto3  = new Label( 12 , row, res.getString( "foto3" ) , cf );                  
                  Label foto4= new Label( 13 , row, res.getString( "foto4" ) , cf );
                  Label croquis1  = new Label( 14 , row, res.getString( "croquis1" ) , cf );                  
                  Label croquis2= new Label( 15, row, res.getString( "croquis2" ) , cf );                  
                  Label croquis3  = new Label( 16 , row, res.getString( "croquis3" ) , cf );                  
                  Label croquis4= new Label( 17 , row, res.getString( "croquis4" ) , cf );
                  Label numVisitas  = new Label( 18 , row, res.getString( "numVisitas" ) , cf );                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idEncuesta );
                     excelSheet.addCell( folioCUIS );
                     excelSheet.addCell( folioSEDESOL );
                     excelSheet.addCell( folioPrograma );
                     excelSheet.addCell( folioInterno );
                     excelSheet.addCell( ampliacion );
                     excelSheet.addCell( mejoramiento );
                     excelSheet.addCell( adquisicion );
                     excelSheet.addCell( edificacion );
                     excelSheet.addCell( otraVivienda );
                     excelSheet.addCell( foto1 );
                     
                     excelSheet.addCell( foto2 );
                     excelSheet.addCell( foto3 );
                     excelSheet.addCell( foto4 );
                     excelSheet.addCell( croquis1 );
                     excelSheet.addCell( croquis2 );
                     excelSheet.addCell( croquis3 );
                     excelSheet.addCell( croquis4 );
                     excelSheet.addCell( numVisitas );
                     
                  } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //undecima pestaña
        
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'encuestador'";
            row=2;
            workbook.createSheet( "Encuestador", 10 );
            excelSheet = workbook.getSheet(10);
            Label encabezadoidEncuestador  = new Label( 0 , 1, "idEncuestador" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoClaveEncuestador  = new Label( 1 , 1, "ClaveEncuestador" , cf2 );
            Label encabezadoNombre  = new Label( 2 , 1, "Nombre" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoPaterno  = new Label( 3 , 1, "A. Paterno" , cf2 );
            Label encabezadoMaterno  = new Label( 4 , 1, "A. Materno" , cf2 ); //columna, fila, elemento, formato
               
            try {
                     excelSheet.addCell( encabezadoidEncuestador );
                     excelSheet.addCell( encabezadoClaveEncuestador );
                     excelSheet.addCell( encabezadoNombre );
                     excelSheet.addCell( encabezadoPaterno );
                     excelSheet.addCell( encabezadoMaterno );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM encuestador ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idEncuestador  = new Label( 0 , row, res.getString( "idEncuestador" ) , cf );                  
                  Label claveEncuestador= new Label( 1 , row, res.getString( "claveEncuestador" ) , cf );
                  Label nombre  = new Label( 2 , row, res.getString( "nombre" ) , cf );                  
                  Label paterno= new Label( 3 , row, res.getString( "paterno" ) , cf );                  
                  Label materno  = new Label( 4 , row, res.getString( "materno" ) , cf );                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idEncuestador );
                     excelSheet.addCell( claveEncuestador );
                     excelSheet.addCell( nombre );
                     excelSheet.addCell( paterno );
                     excelSheet.addCell( materno );
                     
                  } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //duodecima pestaña
        
        
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'geografica'";
            row=2;
            workbook.createSheet( "Geografica", 11 );
            excelSheet = workbook.getSheet(11);
           
            Label encabezadoidGeografica  = new Label( 0 , 1, "idGeografica" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMunicipio  = new Label( 1 , 1, "Municipio" , cf2 );
            Label encabezadoClaveMunicipio  = new Label( 2 , 1, "ClaveMunicipio" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoLocalidad  = new Label( 3 , 1, "Localidad" , cf2 );
            Label encabezadoClaveLocalidad  = new Label( 4 , 1, "ClaveLocalidad" , cf2 ); //columna, fila, elemento, formato
             Label encabezadoClaveAGEB  = new Label( 5 , 1, "ClaveAGEB" , cf2 );
            Label encabezadoClaveManzana  = new Label( 6 , 1, "ClaveManzana" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoClavePoligono  = new Label( 7 , 1, "ClavePoligono" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoLatitud  = new Label( 8 , 1, "Latitud" , cf2 );
            Label encabezadoLongitud  = new Label( 9 , 1, "Longitud" , cf2 );
            Label encabezadoEncuesta_idEncuesta= new Label( 10 , 1, "Encuesta_idEncuesta" , cf2 ); 
           
           
            try {
                     excelSheet.addCell( encabezadoidGeografica );
                     excelSheet.addCell( encabezadoMunicipio );
                     excelSheet.addCell( encabezadoClaveMunicipio );
                     excelSheet.addCell( encabezadoLocalidad );
                     excelSheet.addCell( encabezadoClaveLocalidad );
                     excelSheet.addCell( encabezadoClaveAGEB );
                     excelSheet.addCell( encabezadoClaveManzana);
                     excelSheet.addCell( encabezadoClavePoligono );
                     excelSheet.addCell( encabezadoLatitud );
                     excelSheet.addCell( encabezadoLongitud );
                     excelSheet.addCell( encabezadoEncuesta_idEncuesta );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM geografica ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idGeografica  = new Label( 0 , row, res.getString( "idGeografica" ) , cf );                  
                  Label municipio= new Label( 1 , row, res.getString( "municipio" ) , cf ); 
                  Label claveMunicipio  = new Label( 2 , row, res.getString( "claveMunicipio" ) , cf );                  
                  Label localidad= new Label( 3 , row, res.getString( "localidad" ) , cf );
                  Label claveLocalidad  = new Label( 4 , row, res.getString( "claveLocalidad" ) , cf );                  
                  Label claveAGEB= new Label( 5 , row, res.getString( "claveAGEB" ) , cf ); 
                  Label claveManzana  = new Label( 6 , row, res.getString( "claveManzana" ) , cf );                  
                  Label clavePoligono= new Label( 7 , row, res.getString( "clavePoligono" ) , cf );
                  Label latitud  = new Label( 8 , row, res.getString( "latitud" ) , cf );                  
                  Label longitud= new Label( 9 , row, res.getString( "longitud" ) , cf );                  
                  Label Encuesta_idEncuesta  = new Label( 10 , row, res.getString( "Encuesta_idEncuesta" ) , cf );                  
                                    
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idGeografica );
                     excelSheet.addCell( municipio );
                     excelSheet.addCell( claveMunicipio );
                     excelSheet.addCell( localidad );
                     excelSheet.addCell( claveLocalidad );
                     excelSheet.addCell( claveAGEB );
                     excelSheet.addCell( claveManzana );
                     excelSheet.addCell( clavePoligono );
                     excelSheet.addCell( latitud );
                     excelSheet.addCell( longitud );
                     excelSheet.addCell( Encuesta_idEncuesta );
                  } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //PESTAÑA 13
        
        
         try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'hogar'";
            row=2;
            workbook.createSheet( "Hogar", 12 );
            excelSheet = workbook.getSheet(12);
            Label encabezadoidHogar  = new Label( 0 , 1, "IDHogar" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNumIntegrantes  = new Label( 1 , 1, "NumIntegrantes" , cf2 );
            Label encabezadoRemesas  = new Label( 2 , 1, "Remesas" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoComidas  = new Label( 3 , 1, "Comidas" , cf2 );
            Label encabezadoA  = new Label( 4 , 1, "A" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoB  = new Label( 5 , 1, "B" , cf2 );
            Label encabezadoC  = new Label( 6 , 1, "C" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoD  = new Label( 7 , 1, "D" , cf2 );
            Label encabezadoE  = new Label( 8 , 1, "E" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoF  = new Label( 9 , 1, "F" , cf2 );
            Label encabezadoG  = new Label( 10 , 1, "G" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoH  = new Label( 11 , 1, "H" , cf2 );
            Label encabezadoI  = new Label( 12 , 1, "I" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoJ  = new Label( 13 , 1, "J" , cf2 );
            Label encabezadoK  = new Label( 14 , 1, "K" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoL  = new Label( 15 , 1, "L" , cf2 );
            Label encabezadoM  = new Label( 16 , 1, "M" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoN  = new Label( 17 , 1, "N" , cf2 );
            Label encabezadoO  = new Label( 18 , 1, "O" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoP  = new Label( 19 , 1, "P" , cf2 );
            Label encabezadoMA  = new Label( 20 , 1, "MA" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMB  = new Label( 21 , 1, "MB" , cf2 );
            Label encabezadoMC  = new Label( 22 , 1, "MC" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMD  = new Label( 23 , 1, "MD" , cf2 );
            Label encabezadoME  = new Label( 24 , 1, "ME" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMF  = new Label( 25 , 1, "MF" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMenorA  = new Label( 26 , 1, "MenorA" , cf2 );
            Label encabezadoMenorB  = new Label( 27 , 1, "MenorB" , cf2 );
            Label encabezadoMenorC= new Label( 28 , 1, "MenorC" , cf2 ); 
            Label encabezadoMenorD= new Label( 29 , 1, "MenorD" , cf2 ); 
            Label encabezadoMenorE= new Label( 30 , 1, "MenorE" , cf2 );
            Label encabezadoMenorF= new Label( 31 , 1, "MenorF" , cf2 );
            Label encabezadoSalud1= new Label( 32 , 1, "Salud1" , cf2 ); 
            Label encabezadoSalud2= new Label( 33 , 1, "Salud2" , cf2 ); 
            Label encabezadoEncuesta_idEncuesta= new Label( 34 , 1, "Encuesta_idEncuesta" , cf2 ); 
                 
            try {
                     excelSheet.addCell( encabezadoidHogar );
                     excelSheet.addCell( encabezadoNumIntegrantes );
                     excelSheet.addCell( encabezadoRemesas );
                     excelSheet.addCell( encabezadoComidas );
                     excelSheet.addCell( encabezadoA );
                     excelSheet.addCell( encabezadoB );
                     excelSheet.addCell( encabezadoC );
                     excelSheet.addCell( encabezadoD );
                     excelSheet.addCell( encabezadoE );
                     excelSheet.addCell( encabezadoF );
                     excelSheet.addCell( encabezadoG );
                     excelSheet.addCell( encabezadoH );
                     excelSheet.addCell( encabezadoI );
                     excelSheet.addCell( encabezadoJ );
                     excelSheet.addCell( encabezadoK );
                     excelSheet.addCell( encabezadoL );
                     excelSheet.addCell( encabezadoM );
                     excelSheet.addCell( encabezadoN );
                     excelSheet.addCell( encabezadoO );
                     excelSheet.addCell( encabezadoP );
                     excelSheet.addCell( encabezadoMA );
                     excelSheet.addCell( encabezadoMB );
                     excelSheet.addCell( encabezadoMC );
                     excelSheet.addCell( encabezadoMD );
                     excelSheet.addCell( encabezadoME );
                     excelSheet.addCell( encabezadoMF );
                     excelSheet.addCell( encabezadoMenorA);
                     excelSheet.addCell( encabezadoMenorB);
                     excelSheet.addCell( encabezadoMenorC);
                     excelSheet.addCell( encabezadoMenorD);
                     excelSheet.addCell( encabezadoMenorE);
                     excelSheet.addCell( encabezadoMenorF);
                     excelSheet.addCell( encabezadoSalud1 );
                     excelSheet.addCell( encabezadoSalud2 );
                     excelSheet.addCell( encabezadoEncuesta_idEncuesta );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM hogar ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idHogar  = new Label( 0 , row, res.getString( "idHogar" ) , cf );                  
                  Label numIntegrantes= new Label( 1 , row, res.getString( "numIntegrantes" ) , cf );
                  Label remesas  = new Label( 2 , row, res.getString( "remesas" ) , cf );                  
                  Label comidas= new Label( 3 , row, res.getString( "comidas" ) , cf );                  
                  Label A  = new Label( 4 , row, res.getString( "A" ) , cf );                  
                  Label B= new Label( 5 , row, res.getString( "B" ) , cf );
                  Label C  = new Label( 6 , row, res.getString( "C" ) , cf );                  
                  Label D= new Label( 7 , row, res.getString( "D" ) , cf ); 
                  Label E  = new Label( 8 , row, res.getString( "E" ) , cf );                  
                  Label F= new Label( 9 , row, res.getString( "F" ) , cf );
                  Label G  = new Label( 10 , row, res.getString( "G" ) , cf );                  
                  Label H= new Label( 11 , row, res.getString( "H" ) , cf );
                  Label I  = new Label( 12 , row, res.getString( "I" ) , cf );                  
                  Label J= new Label( 13 , row, res.getString( "J" ) , cf );
                  Label K  = new Label( 14 , row, res.getString( "K" ) , cf );                  
                  Label L= new Label( 15, row, res.getString( "L" ) , cf );                  
                  Label M  = new Label( 16 , row, res.getString( "M" ) , cf );                  
                  Label N= new Label( 17 , row, res.getString( "N" ) , cf );
                  Label O  = new Label( 18 , row, res.getString( "O" ) , cf );                  
                  Label P= new Label( 19 , row, res.getString( "P" ) , cf ); 
                  Label MA  = new Label( 20 , row, res.getString( "MA" ) , cf );                  
                  Label MB= new Label( 21 , row, res.getString( "MB" ) , cf );
                  Label MC  = new Label( 22 , row, res.getString( "MC" ) , cf );                  
                  Label MD= new Label( 23 , row, res.getString( "MD" ) , cf ); 
                  Label ME  = new Label( 24 , row, res.getString( "ME" ) , cf );                  
                  Label MF= new Label( 25 , row, res.getString( "MF" ) , cf );
                  Label menorA  = new Label( 26 , row, res.getString( "menorA" ) , cf );                  
                  Label menorB= new Label( 27 , row, res.getString( "menorB" ) , cf );                  
                  Label menorC= new Label( 28 , row, res.getString( "menorC" ) , cf );                  
                  Label menorD= new Label( 29 , row, res.getString( "menorD" ) , cf );                  
                  Label menorE= new Label( 30 , row, res.getString( "menorE" ) , cf );                  
                  Label menorF= new Label( 31 , row, res.getString( "menorF" ) , cf );                  
                  Label salud1= new Label( 32 , row, res.getString( "salud1" ) , cf );                  
                  Label salud2= new Label( 33 , row, res.getString( "salud2" ) , cf );                  
                  Label Encuesta_idEncuesta  = new Label( 34 , row, res.getString( "Encuesta_idEncuesta" ) , cf );                  
                                       
                 row ++;                  
                 try {
                     excelSheet.addCell( idHogar );
                     excelSheet.addCell( numIntegrantes );
                     excelSheet.addCell( remesas );
                     excelSheet.addCell( comidas );
                     excelSheet.addCell( A );
                     excelSheet.addCell( B );
                     excelSheet.addCell( C );
                     excelSheet.addCell( D );
                     excelSheet.addCell( E );
                     excelSheet.addCell( F );
                     excelSheet.addCell( G );
                     
                     excelSheet.addCell( H );
                     excelSheet.addCell( I );
                     excelSheet.addCell( J );
                     excelSheet.addCell( K );
                     excelSheet.addCell( L );
                     excelSheet.addCell( M );
                     excelSheet.addCell( N );
                     excelSheet.addCell( O );
                     excelSheet.addCell( P );
                     excelSheet.addCell( MA );
                     excelSheet.addCell( MB );
                     
                     excelSheet.addCell( MC );
                     excelSheet.addCell( MD );
                     excelSheet.addCell( ME );
                     excelSheet.addCell( MF );
                     excelSheet.addCell( menorA );
                     excelSheet.addCell( menorB );
                     excelSheet.addCell( menorC );
                     excelSheet.addCell( menorD );
                     excelSheet.addCell( menorE );
                     excelSheet.addCell( menorF );
                     excelSheet.addCell( salud1 );
                     excelSheet.addCell( salud2 );
                     excelSheet.addCell( Encuesta_idEncuesta );
                  } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //14
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'usuarios'";
            row=2;
            workbook.createSheet( "Usuarios", 13 );
            excelSheet = workbook.getSheet(13);
           
            Label encabezadoidUsuarios  = new Label( 0 , 1, "idUsuarios" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNombre  = new Label( 1 , 1, "Nombre" , cf2 );
            Label encabezadoPassword  = new Label( 2 , 1, "Password" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoAdministrador  = new Label( 3 , 1, "Administrador" , cf2 );
            
            try {
                     excelSheet.addCell( encabezadoidUsuarios );
                     excelSheet.addCell( encabezadoNombre );
                     excelSheet.addCell( encabezadoPassword );
                     excelSheet.addCell( encabezadoAdministrador );
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM usuarios ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idUsuarios  = new Label( 0 , row, res.getString( "idUsuarios" ) , cf );                  
                  Label nombre= new Label( 1 , row, res.getString( "nombre" ) , cf ); 
                  Label password  = new Label( 2 , row, res.getString( "password" ) , cf );                  
                  Label administrador= new Label( 3 , row, res.getString( "administrador" ) , cf );
                                    
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idUsuarios );
                     excelSheet.addCell( nombre );
                     excelSheet.addCell( password );
                     excelSheet.addCell( administrador );
                   } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        
        //15
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'servicios'";
            row=2;
            workbook.createSheet( "Servicios", 14 );
            excelSheet = workbook.getSheet(14);
           
            Label encabezadoidServicios  = new Label( 0 , 1, "idServicios" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoValor  = new Label( 1 , 1, "Valor" , cf2 );
            Label encabezadoIntegrantes_idIntegrantes  = new Label( 2 , 1, "Integrantes_idIntegrantes" , cf2 ); //columna, fila, elemento, formato
           
            try {
                     excelSheet.addCell( encabezadoidServicios );
                     excelSheet.addCell( encabezadoValor );
                     excelSheet.addCell( encabezadoIntegrantes_idIntegrantes );        
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM servicios ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idServicios  = new Label( 0 , row, res.getString( "idServicios" ) , cf );                  
                  Label valor= new Label( 1 , row, res.getString( "valor" ) , cf ); 
                  Label Integrantes_idIntegrantes  = new Label( 2 , row, res.getString( "Integrantes_idIntegrantes" ) , cf );                  
                 
                 row ++;                  
                 try {
                     excelSheet.addCell( idServicios );
                     excelSheet.addCell( valor );
                     excelSheet.addCell( Integrantes_idIntegrantes );
                   } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //16
        
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'prestaciones'";
            row=2;
            workbook.createSheet( "Prestaciones", 15 );
            excelSheet = workbook.getSheet(15);
           
            Label encabezadoidPrestaciones  = new Label( 0 , 1, "idPrestaciones" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoValor  = new Label( 1 , 1, "Valor" , cf2 );
            Label encabezadoIntegrantes_idIntegrantes  = new Label( 2 , 1, "Integrantes_idIntegrantes" , cf2 ); //columna, fila, elemento, formato
           
            try {
                     excelSheet.addCell( encabezadoidPrestaciones );
                     excelSheet.addCell( encabezadoValor );
                     excelSheet.addCell( encabezadoIntegrantes_idIntegrantes );        
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM prestaciones ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idPrestaciones  = new Label( 0 , row, res.getString( "idPrestaciones" ) , cf );                  
                  Label valor= new Label( 1 , row, res.getString( "valor" ) , cf ); 
                  Label Integrantes_idIntegrantes  = new Label( 2 , row, res.getString( "Integrantes_idIntegrantes" ) , cf );                  
                 
                 row ++;                  
                 try {
                     excelSheet.addCell( idPrestaciones );
                     excelSheet.addCell( valor );
                     excelSheet.addCell( Integrantes_idIntegrantes );
                   } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //17
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'pensiones'";
            row=2;
            workbook.createSheet( "Pensiones", 16 );
            excelSheet = workbook.getSheet(16);
           
            Label encabezadoidPensiones  = new Label( 0 , 1, "idPensiones" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoValor  = new Label( 1 , 1, "Valor" , cf2 );
            Label encabezadoIntegrantes_idIntegrantes  = new Label( 2 , 1, "Integrantes_idIntegrantes" , cf2 ); //columna, fila, elemento, formato
           
            try {
                     excelSheet.addCell( encabezadoidPensiones );
                     excelSheet.addCell( encabezadoValor );
                     excelSheet.addCell( encabezadoIntegrantes_idIntegrantes );        
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM pensiones ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idPensiones  = new Label( 0 , row, res.getString( "idPensiones" ) , cf );                  
                  Label valor= new Label( 1 , row, res.getString( "valor" ) , cf ); 
                  Label Integrantes_idIntegrantes  = new Label( 2 , row, res.getString( "Integrantes_idIntegrantes" ) , cf );                  
                 
                 row ++;                  
                 try {
                     excelSheet.addCell( idPensiones );
                     excelSheet.addCell( valor );
                     excelSheet.addCell( Integrantes_idIntegrantes );
                   } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //18
         try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'municipios'";
            row=2;
            workbook.createSheet( "Municipios", 17 );
            excelSheet = workbook.getSheet(17);
           
            Label encabezadoidMunicipios  = new Label( 0 , 1, "idMunicipios" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoClave  = new Label( 1 , 1, "Clave" , cf2 );
            Label encabezadoNombre  = new Label( 2 , 1, "Nombre" , cf2 ); //columna, fila, elemento, formato
           
            try {
                     excelSheet.addCell( encabezadoidMunicipios );
                     excelSheet.addCell( encabezadoClave );
                     excelSheet.addCell( encabezadoNombre );        
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM municipios ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idMunicipios  = new Label( 0 , row, res.getString( "idMunicipios" ) , cf );                  
                  Label clave= new Label( 1 , row, res.getString( "clave" ) , cf ); 
                  Label nombre  = new Label( 2 , row, res.getString( "nombre" ) , cf );                  
                 
                 row ++;                  
                 try {
                     excelSheet.addCell( idMunicipios );
                     excelSheet.addCell( clave );
                     excelSheet.addCell( nombre );
                   } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //19
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'localidades'";
            row=2;
            workbook.createSheet( "Localidades", 18 );
            excelSheet = workbook.getSheet(18);
           
            Label encabezadoidLocalidades  = new Label( 0 , 1, "idLocalidades" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMunicipios_clave  = new Label( 1 , 1, "Municipios_clave" , cf2 );
            Label encabezadoClave  = new Label( 2 , 1, "Clave" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNombre  = new Label( 3 , 1, "Nombre" , cf2 ); //columna, fila, elemento, formato
            try {
                     excelSheet.addCell( encabezadoidLocalidades );
                     excelSheet.addCell( encabezadoMunicipios_clave );
                     excelSheet.addCell( encabezadoClave );
                     excelSheet.addCell( encabezadoNombre );        
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM localidades ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idLocalidades  = new Label( 0 , row, res.getString( "idLocalidades" ) , cf );                  
                  Label municipios_clave  = new Label( 1 , row, res.getString( "Municipios_clave" ) , cf );                  
                  Label clave= new Label( 2 , row, res.getString( "clave" ) , cf ); 
                  Label nombre  = new Label( 3 , row, res.getString( "nombre" ) , cf );                  
                 
                 row ++;                  
                 try {
                     excelSheet.addCell( idLocalidades );
                     excelSheet.addCell( municipios_clave );
                     excelSheet.addCell( clave );
                     excelSheet.addCell( nombre );
                   } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //20
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'pisos'";
            row=2;
            workbook.createSheet( "Pisos", 19 );
            excelSheet = workbook.getSheet(19);
           
            Label encabezadoidPisos  = new Label( 0 , 1, "idPisos" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTipo  = new Label( 1 , 1, "Tipo" , cf2 );
            Label encabezadoLargo  = new Label( 2 , 1, "Largo" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoAncho  = new Label( 3 , 1, "Ancho" , cf2 );
            Label encabezadoSubTotal  = new Label( 4 , 1, "Subtotal" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoidEncuesta  = new Label( 5 , 1, "idEncuesta" , cf2 );
            Label encabezadoNumCuarto  = new Label( 6 , 1, "numCuarto" , cf2 ); //columna, fila, elemento, formato
            
            try {
                     excelSheet.addCell( encabezadoidPisos );
                     excelSheet.addCell( encabezadoTipo );
                     excelSheet.addCell( encabezadoLargo );
                     excelSheet.addCell( encabezadoAncho );
                     excelSheet.addCell( encabezadoSubTotal );
                     excelSheet.addCell( encabezadoidEncuesta );
                     excelSheet.addCell( encabezadoNumCuarto);
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM pisos ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idPisos  = new Label( 0 , row, res.getString( "idPisos" ) , cf );                  
                  Label tipo= new Label( 1 , row, res.getString( "tipo" ) , cf ); 
                  Label largo  = new Label( 2 , row, res.getString( "largo" ) , cf );                  
                  Label ancho= new Label( 3 , row, res.getString( "ancho" ) , cf );
                  Label subtotal  = new Label( 4 , row, res.getString( "subtotal" ) , cf );                  
                  Label idEncuesta= new Label( 5 , row, res.getString( "idEncuesta" ) , cf ); 
                  Label numCuarto  = new Label( 6 , row, res.getString( "numCuarto" ) , cf );                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idPisos );
                     excelSheet.addCell( tipo );
                     excelSheet.addCell( largo );
                     excelSheet.addCell( ancho );
                     excelSheet.addCell( subtotal );
                     excelSheet.addCell( idEncuesta );
                     excelSheet.addCell( numCuarto );
                    } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //21
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'totales'";
            row=2;
            workbook.createSheet( "Totales", 20 );
            excelSheet = workbook.getSheet(20);
           
            Label encabezadoidTotales  = new Label( 0 , 1, "idTotales" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoSubtotalA  = new Label( 1 , 1, "SubtotalA" , cf2 );
            Label encabezadoSubtotalB  = new Label( 2 , 1, "SubtotalB" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTotal  = new Label( 3 , 1, "Total" , cf2 );
            Label encabezadoTipo  = new Label( 4 , 1, "Tipo" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoidEncuesta  = new Label( 5 , 1, "idEncuesta" , cf2 );
            Label encabezadoObservaciones  = new Label( 6 , 1, "Observaciones" , cf2 ); //columna, fila, elemento, formato
            
            try {
                     excelSheet.addCell( encabezadoidTotales );
                     excelSheet.addCell( encabezadoSubtotalA );
                     excelSheet.addCell( encabezadoSubtotalB );
                     excelSheet.addCell( encabezadoTotal );
                     excelSheet.addCell( encabezadoTipo );
                     excelSheet.addCell( encabezadoidEncuesta );
                     excelSheet.addCell( encabezadoObservaciones);
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM totales ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idTotales  = new Label( 0 , row, res.getString( "idTotales" ) , cf );                  
                  Label subtotalA= new Label( 1 , row, res.getString( "subtotalA" ) , cf ); 
                  Label subtotalB  = new Label( 2 , row, res.getString( "subtotalB" ) , cf );                  
                  Label total= new Label( 3 , row, res.getString( "total" ) , cf );
                  Label tipo  = new Label( 4 , row, res.getString( "tipo" ) , cf );                  
                  Label idEncuesta= new Label( 5 , row, res.getString( "idEncuesta" ) , cf ); 
                  Label observaciones  = new Label( 6 , row, res.getString( "observaciones" ) , cf );                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idTotales );
                     excelSheet.addCell( subtotalA );
                     excelSheet.addCell( subtotalB );
                     excelSheet.addCell( total );
                     excelSheet.addCell( tipo );
                     excelSheet.addCell( idEncuesta );
                     excelSheet.addCell( observaciones );
                    } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //22
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'muros'";
            row=2;
            workbook.createSheet( "Muros", 21 );
            excelSheet = workbook.getSheet(21);
           
            Label encabezadoidMuros  = new Label( 0 , 1, "idMuros" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNumCuarto  = new Label( 1 , 1, "NumCuarto" , cf2 );
            Label encabezadoTipoMuro  = new Label( 2 , 1, "TipoMuro" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTipoArea  = new Label( 3 , 1, "TipoArea" , cf2 );
            Label encabezadoLargo  = new Label( 4 , 1, "Largo" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoAlto  = new Label( 5 , 1, "Alto" , cf2 );
            Label encabezadoSubtotal  = new Label( 6 , 1, "Subtotal" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoidEncuesta  = new Label( 7 , 1, "idEncuesta" , cf2 ); //columna, fila, elemento, formato
            
            try {
                     excelSheet.addCell( encabezadoidMuros );
                     excelSheet.addCell( encabezadoNumCuarto );
                     excelSheet.addCell( encabezadoTipoMuro );
                     excelSheet.addCell( encabezadoTipoArea );
                     excelSheet.addCell( encabezadoLargo );
                     excelSheet.addCell( encabezadoAlto );
                     excelSheet.addCell( encabezadoSubtotal);
                     excelSheet.addCell( encabezadoidEncuesta );
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM muros ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idMuros  = new Label( 0 , row, res.getString( "idMuros" ) , cf );                  
                  Label numCuarto= new Label( 1 , row, res.getString( "numCuarto" ) , cf ); 
                  Label tipoMuro  = new Label( 2 , row, res.getString( "tipoMuro" ) , cf );                  
                  Label tipoArea= new Label( 3 , row, res.getString( "tipoArea" ) , cf );
                  Label largo  = new Label( 4 , row, res.getString( "largo" ) , cf );                  
                  Label alto= new Label( 5 , row, res.getString( "alto" ) , cf ); 
                  Label subtotal  = new Label( 6 , row, res.getString( "subtotal" ) , cf );                  
                  Label idEncuesta  = new Label( 7 , row, res.getString( "idEncuesta" ) , cf );                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idMuros );
                     excelSheet.addCell( numCuarto );
                     excelSheet.addCell( tipoMuro );
                     excelSheet.addCell( tipoArea );
                     excelSheet.addCell( largo );
                     excelSheet.addCell( alto );
                     excelSheet.addCell( subtotal );
                     excelSheet.addCell( idEncuesta );
                    } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //23
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'techos'";
            row=2;
            workbook.createSheet( "Techos", 22 );
            excelSheet = workbook.getSheet(22);
           
            Label encabezadoidTechos  = new Label( 0 , 1, "idTechos" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNumTecho  = new Label( 1 , 1, "NumTecho" , cf2 );
            Label encabezadoTipo  = new Label( 2 , 1, "Tipo" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoAMayor  = new Label( 3 , 1, "A.Mayor" , cf2 );
            Label encabezadoAMenor  = new Label( 4 , 1, "A. Menor" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoLargo  = new Label( 5 , 1, "Largo" , cf2 );
            Label encabezadoAncho  = new Label( 6 , 1, "Ancho" , cf2 );
            Label encabezadoSubtotal  = new Label( 7 , 1, "Subtotal" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoidEncuesta  = new Label( 8 , 1, "idEncuesta" , cf2 ); //columna, fila, elemento, formato
            
            try {
                     excelSheet.addCell( encabezadoidTechos );
                     excelSheet.addCell( encabezadoNumTecho );
                     excelSheet.addCell( encabezadoTipo );
                     excelSheet.addCell( encabezadoAMayor );
                     excelSheet.addCell( encabezadoAMenor );
                     excelSheet.addCell( encabezadoLargo );
                     excelSheet.addCell( encabezadoAncho );
                     excelSheet.addCell( encabezadoSubtotal);
                     excelSheet.addCell( encabezadoidEncuesta );
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM techos ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idTechos  = new Label( 0 , row, res.getString( "idTechos" ) , cf );                  
                  Label numTecho= new Label( 1 , row, res.getString( "numTecho" ) , cf ); 
                  Label tipo  = new Label( 2 , row, res.getString( "tipo" ) , cf );                  
                  Label Amayor= new Label( 3 , row, res.getString( "Amayor" ) , cf );                
                  Label Amenor= new Label( 4 , row, res.getString( "Amenor" ) , cf );
                  Label largo  = new Label( 5 , row, res.getString( "largo" ) , cf );                  
                  Label ancho= new Label( 6 , row, res.getString( "ancho" ) , cf ); 
                  Label subtotal  = new Label( 7 , row, res.getString( "subtotal" ) , cf );                  
                  Label idEncuesta  = new Label( 8 , row, res.getString( "idEncuesta" ) , cf );                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idTechos );
                     excelSheet.addCell( numTecho );
                     excelSheet.addCell( tipo );
                     excelSheet.addCell( Amayor );
                     excelSheet.addCell( Amenor );
                     excelSheet.addCell( largo );
                     excelSheet.addCell( ancho );
                     excelSheet.addCell( subtotal );
                     excelSheet.addCell( idEncuesta );
                    } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //24
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'visita'";
            row=2;
            workbook.createSheet( "Visita", 23 );
            excelSheet = workbook.getSheet(23);
           
            Label encabezadoidVisita  = new Label( 0 , 1, "idVisita" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNumero  = new Label( 1 , 1, "Numero" , cf2 );
            Label encabezadoFecha  = new Label( 2 , 1, "Fecha" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoResultado  = new Label( 3 , 1, "Resultado" , cf2 );
            Label encabezadoObservaciones  = new Label( 4 , 1, "Observaciones" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoEncuestador_idEncuestador  = new Label( 5 , 1, "Encuestador_idEncuestador" , cf2 );
            Label encabezadoEncuesta_idEncuesta  = new Label( 6 , 1, "Encuesta_idEncuesta" , cf2 );
            Label encabezadoHoraInicio  = new Label( 7 , 1, "HoraInicio" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoHoraTermino  = new Label( 8 , 1, "HoraTermino" , cf2 ); //columna, fila, elemento, formato
            
            try {
                     excelSheet.addCell( encabezadoidVisita );
                     excelSheet.addCell( encabezadoNumero );
                     excelSheet.addCell( encabezadoFecha );
                     excelSheet.addCell( encabezadoResultado );
                     excelSheet.addCell( encabezadoObservaciones );
                     excelSheet.addCell( encabezadoEncuestador_idEncuestador );
                     excelSheet.addCell( encabezadoEncuesta_idEncuesta );
                     excelSheet.addCell( encabezadoHoraInicio);
                     excelSheet.addCell( encabezadoHoraTermino );
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM visita ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idVisita  = new Label( 0 , row, res.getString( "idVisita" ) , cf );                  
                  Label numero= new Label( 1 , row, res.getString( "numero" ) , cf ); 
                  Label  fecha  = new Label( 2 , row, res.getString( "fecha" ) , cf );                  
                  Label resultado= new Label( 3 , row, res.getString( "resultado" ) , cf );                
                  Label observaciones= new Label( 4 , row, res.getString( "observaciones" ) , cf );
                  Label Encuestador_idEncuestador  = new Label( 5 , row, res.getString( "Encuestador_idEncuestador" ) , cf );                  
                  Label Encuesta_idEncuesta= new Label( 6 , row, res.getString( "Encuesta_idEncuesta" ) , cf ); 
                  Label horaInicio  = new Label( 7 , row, res.getString( "horaInicio" ) , cf );                  
                  Label horaTermino  = new Label( 8 , row, res.getString( "horaTermino" ) , cf );                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idVisita );
                     excelSheet.addCell( numero );
                     excelSheet.addCell( fecha );
                     excelSheet.addCell( resultado );
                     excelSheet.addCell( observaciones );
                     excelSheet.addCell( Encuestador_idEncuestador );
                     excelSheet.addCell( Encuesta_idEncuesta );
                     excelSheet.addCell( horaInicio );
                     excelSheet.addCell( horaTermino );
                    } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //25
         try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'terrenos'";
            row=2;
            workbook.createSheet( "Terrenos", 24 );
            excelSheet = workbook.getSheet(24);
           
            Label encabezadoidTerreno  = new Label( 0 , 1, "idTerreno" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTotalGeneral  = new Label( 1 , 1, "TotalGeneral" , cf2 );
            Label encabezadoSuelo  = new Label( 2 , 1, "Suelo" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoUbicacion  = new Label( 3 , 1, "Ubicacion" , cf2 );
            Label encabezadoObservaciones  = new Label( 4 , 1, "Observaciones" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoidEncuesta  = new Label( 5 , 1, "idEncuesta" , cf2 );
            Label encabezadoFigura  = new Label( 6 , 1, "Figura" , cf2 );
            Label encabezadoMedida1  = new Label( 7 , 1, "Medida1" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMedida2  = new Label( 8 , 1, "Medida2" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTotal  = new Label( 9 , 1, "Total" , cf2 ); //columna, fila, elemento, formato
            
            try {
                     excelSheet.addCell( encabezadoidTerreno );
                     excelSheet.addCell( encabezadoTotalGeneral );
                     excelSheet.addCell( encabezadoSuelo );
                     excelSheet.addCell( encabezadoUbicacion );
                     excelSheet.addCell( encabezadoObservaciones );
                     excelSheet.addCell( encabezadoidEncuesta );
                     excelSheet.addCell( encabezadoFigura );
                     excelSheet.addCell( encabezadoMedida1);
                     excelSheet.addCell( encabezadoMedida2);
                     excelSheet.addCell( encabezadoTotal );
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM terrenos ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idTerreno  = new Label( 0 , row, res.getString( "idTerreno" ) , cf );                  
                  Label totalGeneral= new Label( 1 , row, res.getString( "totalGeneral" ) , cf ); 
                  Label  suelo  = new Label( 2 , row, res.getString( "suelo" ) , cf );                  
                  Label ubicacion= new Label( 3 , row, res.getString( "ubicacion" ) , cf );                
                  Label observaciones= new Label( 4 , row, res.getString( "observaciones" ) , cf );
                  Label idEncuesta  = new Label( 5 , row, res.getString( "idEncuesta" ) , cf );                  
                  Label figura= new Label( 6 , row, res.getString( "figura" ) , cf ); 
                  Label medida1  = new Label( 7 , row, res.getString( "medida1" ) , cf );
                  Label medida2  = new Label( 8 , row, res.getString( "medida2" ) , cf );                  
                  Label total  = new Label( 9 , row, res.getString( "total" ) , cf );                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idTerreno );
                     excelSheet.addCell( totalGeneral );
                     excelSheet.addCell( suelo );
                     excelSheet.addCell( ubicacion );
                     excelSheet.addCell( observaciones );
                     excelSheet.addCell( idEncuesta );
                     excelSheet.addCell( figura );
                     excelSheet.addCell( medida1 );
                     excelSheet.addCell( medida2 );
                     excelSheet.addCell( total );
                    } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //26
         try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'pdzp'";
            row=2;
            workbook.createSheet( "PDZP", 25 );
            excelSheet = workbook.getSheet(25);
           
            Label encabezadoidPDZP  = new Label( 0 , 1, "idPDZP" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoInteresado  = new Label( 1 , 1, "Interesado" , cf2 );
            Label encabezadoNumMujeres  = new Label( 2 , 1, "NumMujeres" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNumHombres  = new Label( 3 , 1, "NumHombres" , cf2 );
            Label encabezadoTratamientos  = new Label( 4 , 1, "tratamientos" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoOtro  = new Label( 5 , 1, "Otro" , cf2 );
            Label encabezadoNecesidad1  = new Label( 6 , 1, "Necesidad1" , cf2 );
            Label encabezadoNecesidad2  = new Label( 7 , 1, "Necesidad2" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNecesidad3  = new Label( 8 , 1, "Necesidad3" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNecesidad4  = new Label( 9 , 1, "Necesidad4" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNecesidad5  = new Label( 10 , 1, "Necesidad5" , cf2 );
            Label encabezadoAguasResiduales  = new Label( 11 , 1, "AguasResiduales" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNecesidadComunidad  = new Label( 12 , 1, "NecesidadComunidad" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoEncuesta_idEncuesta  = new Label( 13 , 1, "Encuesta_idEncuesta" , cf2 ); //columna, fila, elemento, formato
            
            try {
                     excelSheet.addCell( encabezadoidPDZP );
                     excelSheet.addCell( encabezadoInteresado );
                     excelSheet.addCell( encabezadoNumMujeres );
                     excelSheet.addCell( encabezadoNumHombres );
                     excelSheet.addCell( encabezadoTratamientos );
                     excelSheet.addCell( encabezadoOtro );
                     excelSheet.addCell( encabezadoNecesidad1 );
                     excelSheet.addCell( encabezadoNecesidad2);
                     excelSheet.addCell( encabezadoNecesidad3);
                     excelSheet.addCell( encabezadoNecesidad4 );
                     excelSheet.addCell( encabezadoNecesidad5 );
                     excelSheet.addCell( encabezadoAguasResiduales);
                     excelSheet.addCell( encabezadoNecesidadComunidad);
                     excelSheet.addCell( encabezadoEncuesta_idEncuesta );
            
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM pdzp ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idPDZP  = new Label( 0 , row, res.getString( "idPDZP" ) , cf );                  
                  Label interesado= new Label( 1 , row, res.getString( "interesado" ) , cf ); 
                  Label  numMujeres  = new Label( 2 , row, res.getString( "numMujeres" ) , cf );                  
                  Label numHombres= new Label( 3 , row, res.getString( "numHombres" ) , cf );                
                  Label tratamientos= new Label( 4 , row, res.getString( "tratamientos" ) , cf );
                  Label otro  = new Label( 5 , row, res.getString( "otro" ) , cf );                  
                  Label necesidad1= new Label( 6 , row, res.getString( "necesidad1" ) , cf ); 
                  Label necesidad2  = new Label( 7 , row, res.getString( "necesidad2" ) , cf );
                  Label necesidad3  = new Label( 8 , row, res.getString( "necesidad3" ) , cf );                  
                  Label necesidad4  = new Label( 9 , row, res.getString( "necesidad4" ) , cf );                  
                  Label necesidad5= new Label( 10 , row, res.getString( "necesidad5" ) , cf ); 
                  Label aguasResiduales  = new Label( 11 , row, res.getString( "aguasResiduales" ) , cf );
                  Label necesidadComunidad  = new Label( 12 , row, res.getString( "necesidadComunidad" ) , cf );                  
                  Label Encuesta_idEncuesta  = new Label( 13 , row, res.getString( "Encuesta_idEncuesta" ) , cf );                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idPDZP );
                     excelSheet.addCell( interesado );
                     excelSheet.addCell( numMujeres );
                     excelSheet.addCell( numHombres );
                     excelSheet.addCell( tratamientos );
                     excelSheet.addCell( otro );
                     excelSheet.addCell( necesidad1 );
                     excelSheet.addCell( necesidad2 );
                     excelSheet.addCell( necesidad3 );
                     excelSheet.addCell( necesidad4 );
                      excelSheet.addCell( necesidad5 );
                     excelSheet.addCell( aguasResiduales );
                     excelSheet.addCell( necesidadComunidad );
                     excelSheet.addCell( Encuesta_idEncuesta );
                    } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //27
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'vivienda'";
            row=2;
            workbook.createSheet( "Vivienda", 26 );
            excelSheet = workbook.getSheet(26);
           
            Label encabezadoidVivienda  = new Label( 0 , 1, "idVivienda" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTipo  = new Label( 1 , 1, "Tipo" , cf2 );
            Label encabezadoNumHogares  = new Label( 2 , 1, "NumHogares" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNumPersonas  = new Label( 3 , 1, "NumPersonas" , cf2 );
            Label encabezadoNumCuartos  = new Label( 4 , 1, "NumCuartos" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoParaDormir  = new Label( 5 , 1, "ParaDormir" , cf2 );
            Label encabezadoCocinanDuermen  = new Label( 6 , 1, "CocinanDuermen" , cf2 );
            Label encabezadoPiso  = new Label( 7 , 1, "Piso" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoPisoTierra  = new Label( 8 , 1, "PisoTierra" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTecho  = new Label( 9 , 1, "Techo" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMuros  = new Label( 10 , 1, "Muros" , cf2 );
            Label encabezadoTipoBanio  = new Label( 11 , 1, "TipoBanio" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoBanioExclusivo  = new Label( 12 , 1, "BanioExclusivo" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoObtAgua  = new Label( 13 , 1, "ObtAgua" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoDrenaje = new Label( 14 , 1, "Drenaje" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoBasura  = new Label( 15 , 1, "Basura" , cf2 );
            Label encabezadoCombustible  = new Label( 16 , 1, "Combustible" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoAparatoCocinar  = new Label( 17 , 1, "AparatoCocinar" , cf2 );
            Label encabezadoFogon  = new Label( 18 , 1, "Fogon" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoElectricidad = new Label( 19 , 1, "Electricidad" , cf2 );
            Label encabezadoNumFocos  = new Label( 20 , 1, "NumFocos" , cf2 );
            Label encabezadoPropiedad  = new Label( 21 , 1, "Propiedad" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoEscrituras  = new Label( 22 , 1, "Escrituras" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoEncuesta_idEncuesta  = new Label( 23 , 1, "Encuesta_idEncuesta" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoGastos  = new Label( 24 , 1, "Gastos" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoAlimentos  = new Label( 25 , 1, "Alimentos" , cf2 );
            Label encabezadoTelefono  = new Label( 26 , 1, "Telefono" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoCelular  = new Label( 27 , 1, "Celular" , cf2 ); //columna, fila, elemento, formato
            
             
            try {
                     excelSheet.addCell( encabezadoidVivienda );
                     excelSheet.addCell( encabezadoTipo );
                     excelSheet.addCell( encabezadoNumHogares );
                     excelSheet.addCell( encabezadoNumPersonas );
                     excelSheet.addCell( encabezadoNumCuartos );
                     excelSheet.addCell( encabezadoParaDormir );
                     excelSheet.addCell( encabezadoCocinanDuermen );
                     excelSheet.addCell( encabezadoPiso);
                     excelSheet.addCell( encabezadoPisoTierra);
                     excelSheet.addCell( encabezadoTecho );
                     excelSheet.addCell( encabezadoMuros );
                     excelSheet.addCell( encabezadoTipoBanio);
                     excelSheet.addCell( encabezadoBanioExclusivo);
                     excelSheet.addCell( encabezadoObtAgua);
                     excelSheet.addCell( encabezadoDrenaje );
                     excelSheet.addCell( encabezadoBasura );
                     excelSheet.addCell( encabezadoCombustible );
                     excelSheet.addCell( encabezadoAparatoCocinar );
                     excelSheet.addCell( encabezadoFogon );
                     excelSheet.addCell( encabezadoElectricidad );
                     excelSheet.addCell( encabezadoNumFocos );
                     excelSheet.addCell( encabezadoPropiedad);
                     excelSheet.addCell( encabezadoEscrituras);
                     excelSheet.addCell( encabezadoEncuesta_idEncuesta );
                     excelSheet.addCell( encabezadoGastos );
                     excelSheet.addCell( encabezadoAlimentos );
                     excelSheet.addCell( encabezadoTelefono);
                     excelSheet.addCell( encabezadoCelular);
                     
            
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM vivienda ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idVivienda  = new Label( 0 , row, res.getString( "idVivienda" ) , cf );                  
                  Label tipo= new Label( 1 , row, res.getString( "tipo" ) , cf ); 
                  Label  numHogares  = new Label( 2 , row, res.getString( "numHogares" ) , cf );                  
                  Label numPersonas= new Label( 3 , row, res.getString( "numPersonas" ) , cf );                
                  Label numCuartos= new Label( 4 , row, res.getString( "numCuartos" ) , cf );
                  Label paraDormir  = new Label( 5 , row, res.getString( "paraDormir" ) , cf );                  
                  Label cocinanDuermen= new Label( 6 , row, res.getString( "cocinanDuermen" ) , cf ); 
                  Label piso  = new Label( 7 , row, res.getString( "piso" ) , cf );
                  Label pisoTierra  = new Label( 8 , row, res.getString( "pisoTierra" ) , cf );                  
                  Label techo  = new Label( 9 , row, res.getString( "techo" ) , cf );                  
                  Label muros= new Label( 10 , row, res.getString( "muros" ) , cf ); 
                  Label tipoBanio  = new Label( 11 , row, res.getString( "tipoBanio" ) , cf );
                  Label banioExclusivo  = new Label( 12 , row, res.getString( "BanioExclusivo" ) , cf );                  
                  Label obtAgua  = new Label( 13 , row, res.getString( "obtAgua" ) , cf );
                  Label drenaje = new Label( 14 , row, res.getString( "drenaje" ) , cf );                  
                  Label basura= new Label( 15 , row, res.getString( "basura" ) , cf ); 
                  Label combustible  = new Label( 16 , row, res.getString( "combustible" ) , cf );                  
                  Label aparatoCocinar= new Label( 17 , row, res.getString( "aparatoCocinar" ) , cf );                
                  Label fogon= new Label( 18 , row, res.getString( "fogon" ) , cf );
                  Label electricidad  = new Label( 19 , row, res.getString( "electricidad" ) , cf );                  
                  Label numFocos= new Label( 20 , row, res.getString( "numFocos" ) , cf ); 
                  Label propiedad  = new Label( 21 , row, res.getString( "propiedad" ) , cf );
                  Label escrituras  = new Label( 22 , row, res.getString( "escrituras" ) , cf );                  
                  Label Encuesta_idEncuesta  = new Label( 23 , row, res.getString( "Encuesta_idEncuesta" ) , cf );
                  Label gastos= new Label( 24 , row, res.getString( "gastos" ) , cf ); 
                  Label alimentos  = new Label( 25 , row, res.getString( "alimentos" ) , cf );
                  Label telefono  = new Label( 26 , row, res.getString( "telefono" ) , cf );                  
                  Label celular  = new Label( 27 , row, res.getString( "celular" ) , cf );                  
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idVivienda );
                     excelSheet.addCell( tipo );
                     excelSheet.addCell( numHogares );
                     excelSheet.addCell( numPersonas );
                     excelSheet.addCell( numCuartos );
                     excelSheet.addCell( paraDormir );
                     excelSheet.addCell( cocinanDuermen );
                     excelSheet.addCell( piso );
                     excelSheet.addCell( pisoTierra );
                     excelSheet.addCell( techo );
                      excelSheet.addCell( muros );
                     excelSheet.addCell( tipoBanio );
                     excelSheet.addCell( banioExclusivo );
                     excelSheet.addCell( obtAgua );
                  excelSheet.addCell( drenaje );
                     excelSheet.addCell( basura );
                     excelSheet.addCell( combustible );
                     excelSheet.addCell( aparatoCocinar );
                     excelSheet.addCell( fogon );
                     excelSheet.addCell( electricidad );
                     excelSheet.addCell( numFocos );
                     excelSheet.addCell( propiedad );
                     excelSheet.addCell( escrituras );
                     excelSheet.addCell( Encuesta_idEncuesta );
                      excelSheet.addCell( gastos );
                     excelSheet.addCell( alimentos );
                     excelSheet.addCell( telefono );
                     excelSheet.addCell( celular );   
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        //28
        try {
            //hoja con nombre de la tabla
            NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'integrantes'";
            row=2;
            workbook.createSheet( "Integrantes", 27 );
            excelSheet = workbook.getSheet(27);
           
            Label encabezadoidIntegrantes  = new Label( 0 , 1, "idIntegrantes" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoRenglon  = new Label( 1 , 1, "Renglon" , cf2 );
            Label encabezadoNombre  = new Label( 2 , 1, "Nombre" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoPaterno  = new Label( 3 , 1, "A. Paterno" , cf2 );
            Label encabezadoMaterno  = new Label( 4 , 1, "A. Materno" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoReside  = new Label( 5 , 1, "Reside" , cf2 );
            Label encabezadoEdad  = new Label( 6 , 1, "Edad" , cf2 );
            Label encabezadoFechaNac  = new Label( 7 , 1, "FechaNac" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoParentesco  = new Label( 8 , 1, "Parentesco" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTieneCurp  = new Label( 9 , 1, "TieneCURP" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoCURP  = new Label( 10 , 1, "CURP" , cf2 );
            Label encabezadoActa  = new Label( 11 , 1, "Acta" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoSexo  = new Label( 12 , 1, "Sexo" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoEdoNac  = new Label( 13 , 1, "EdoNac" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoSalud1 = new Label( 14 , 1, "Salud1" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoSalud2  = new Label( 15 , 1, "Salud2" , cf2 );
            Label encabezadoAfiliacion1  = new Label( 16 , 1, "Afiliacion1" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoAfiliacion2  = new Label( 17 , 1, "Afiliacion2" , cf2 );
            Label encabezadoLenguaIndigena  = new Label( 18 , 1, "LenguaIndigena" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoOtraLengua = new Label( 19 , 1, "OtraLengua" , cf2 );
            Label encabezadoEspaniol  = new Label( 20 , 1, "Espaniol" , cf2 );
            Label encabezadoIndigena  = new Label( 21 , 1, "Indigena" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoLeer  = new Label( 22 , 1, "Leer" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoNivel  = new Label( 23 , 1, "Nivel" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoGrado  = new Label( 24 , 1, "Grado" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoEstudiante  = new Label( 25 , 1, "Estudiante" , cf2 );
            Label encabezadoEstadoCivil  = new Label( 26 , 1, "EstadoCivil" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMesPasado  = new Label( 27 , 1, "MesPasado" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoMotivoNoEstudia  = new Label( 28 , 1, "MotivoNoEstudia" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoActMesPasado  = new Label( 29 , 1, "ActMesPasado" , cf2 );
            Label encabezadoRazonNoTrabajo  = new Label( 30 , 1, "RazonNoTrabajo" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoTrabajoPrincipal  = new Label( 31 , 1, "TrabajoPrincpal" , cf2 );
            Label encabezadoSupervisor  = new Label( 32 , 1, "Supervisor" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoCuentaPropia  = new Label( 33 , 1, "CuentaPropia" , cf2 );
            Label encabezadoPago  = new Label( 34 , 1, "Pago" , cf2 );
            Label encabezadoCantidadPago  = new Label( 35 , 1, "CantidadPago" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoFrecuenciaPago  = new Label( 36 , 1, "FrecuenciaPago" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoInapam  = new Label( 37 , 1, "INAPAM" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoOportunidades  = new Label( 38 , 1, "Oportunidades" , cf2 );
            Label encabezadoCorreo  = new Label( 39 , 1, "Correo" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoEncuesta_idEncuesta  = new Label( 40 , 1, "Encuesta_idEncuesta" , cf2 ); //columna, fila, elemento, formato
        
             
            try {
                     excelSheet.addCell( encabezadoidIntegrantes );
                     excelSheet.addCell( encabezadoRenglon );
                     excelSheet.addCell( encabezadoNombre );
                     excelSheet.addCell( encabezadoPaterno );
                     excelSheet.addCell( encabezadoMaterno );
                     excelSheet.addCell( encabezadoReside );
                     excelSheet.addCell( encabezadoEdad );
                     excelSheet.addCell( encabezadoFechaNac);
                     excelSheet.addCell( encabezadoParentesco);
                     excelSheet.addCell( encabezadoTieneCurp );
                     excelSheet.addCell( encabezadoCURP );
                     excelSheet.addCell( encabezadoActa);
                     excelSheet.addCell( encabezadoSexo);
                     excelSheet.addCell( encabezadoEdoNac);
                     excelSheet.addCell( encabezadoSalud1 );
                     excelSheet.addCell( encabezadoSalud2 );
                     excelSheet.addCell( encabezadoAfiliacion1 );
                     excelSheet.addCell( encabezadoAfiliacion2 );
                     excelSheet.addCell( encabezadoLenguaIndigena );
                     excelSheet.addCell( encabezadoOtraLengua );
                     excelSheet.addCell( encabezadoEspaniol );
                     excelSheet.addCell( encabezadoIndigena);
                     excelSheet.addCell( encabezadoLeer);
                     
                     excelSheet.addCell( encabezadoNivel );
                     excelSheet.addCell( encabezadoGrado );
                     excelSheet.addCell( encabezadoEstudiante);
                     excelSheet.addCell( encabezadoEstadoCivil);
                     excelSheet.addCell( encabezadoMesPasado );
                     excelSheet.addCell( encabezadoMotivoNoEstudia );
                     excelSheet.addCell( encabezadoActMesPasado );
                     excelSheet.addCell( encabezadoRazonNoTrabajo );
                     excelSheet.addCell( encabezadoTrabajoPrincipal );
                     excelSheet.addCell( encabezadoSupervisor );
                     excelSheet.addCell( encabezadoCuentaPropia );
                     excelSheet.addCell( encabezadoPago);
                     excelSheet.addCell( encabezadoCantidadPago);
                     excelSheet.addCell( encabezadoFrecuenciaPago );
                     excelSheet.addCell( encabezadoInapam );
                     excelSheet.addCell( encabezadoOportunidades);
                     excelSheet.addCell( encabezadoCorreo);
                     excelSheet.addCell( encabezadoEncuesta_idEncuesta );
            
                } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (Exception ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        sql = "SELECT * FROM integrantes ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label idIntegrantes  = new Label( 0 , row, res.getString( "idIntegrantes" ) , cf );                  
                  Label renglon= new Label( 1 , row, res.getString( "renglon" ) , cf ); 
                  Label nombre  = new Label( 2 , row, res.getString( "nombre" ) , cf );                  
                  Label paterno= new Label( 3 , row, res.getString( "paterno" ) , cf );                
                  Label materno= new Label( 4 , row, res.getString( "materno" ) , cf );
                  Label reside  = new Label( 5 , row, res.getString( "reside" ) , cf );                  
                  Label edad= new Label( 6 , row, res.getString( "edad" ) , cf ); 
                  Label fechaNac  = new Label( 7 , row, res.getString( "fechaNac" ) , cf );
                  Label parentesco  = new Label( 8 , row, res.getString( "parentesco" ) , cf );                  
                  Label tieneCurp  = new Label( 9 , row, res.getString( "tieneCurp" ) , cf );                  
                  Label CURP= new Label( 10 , row, res.getString( "CURP" ) , cf ); 
                  Label acta  = new Label( 11 , row, res.getString( "acta" ) , cf );
                  Label sexo  = new Label( 12 , row, res.getString( "sexo" ) , cf );                  
                  Label edoNac  = new Label( 13 , row, res.getString( "edoNac" ) , cf );
                  Label salud1 = new Label( 14 , row, res.getString( "salud1" ) , cf );                  
                  Label salud2= new Label( 15 , row, res.getString( "salud2" ) , cf ); 
                  Label afiliacion1  = new Label( 16 , row, res.getString( "afiliacion1" ) , cf );                  
                  Label afiliacion2= new Label( 17 , row, res.getString( "afiliacion2" ) , cf );                
                  Label lenguaIndigena= new Label( 18 , row, res.getString( "lenguaIndigena" ) , cf );
                  Label otraLengua  = new Label( 19 , row, res.getString( "otraLengua" ) , cf );                  
                  Label espaniol= new Label( 20 , row, res.getString( "espaniol" ) , cf ); 
                  Label indigena  = new Label( 21 , row, res.getString( "indigena" ) , cf );
                  Label leer  = new Label( 22 , row, res.getString( "leer" ) , cf );                  
                  Label nivel= new Label( 23 , row, res.getString( "nivel" ) , cf ); 
                  Label grado  = new Label( 24 , row, res.getString( "grado" ) , cf );
                  Label estudiante  = new Label( 25 , row, res.getString( "estudiante" ) , cf );                  
                  Label estadoCivil  = new Label( 26 , row, res.getString( "estadoCivil" ) , cf );                  
                  Label mesPasado  = new Label( 27 , row, res.getString( "mesPasado" ) , cf );                  
                  Label motivoNoEstudia= new Label( 28 , row, res.getString( "motivoNoEstudia" ) , cf ); 
                  Label actMesPasado  = new Label( 29 , row, res.getString( "actMesPasado" ) , cf );                  
                  Label razonNoTrabajo= new Label( 30 , row, res.getString( "razonNoTrabajo" ) , cf );                
                  Label trabajoPrincipal= new Label( 31 , row, res.getString( "trabajoPrincipal" ) , cf );
                  Label supervisor  = new Label( 32 , row, res.getString( "supervisor" ) , cf );                  
                  Label cuentaPropia= new Label( 33 , row, res.getString( "cuentaPropia" ) , cf ); 
                  Label pago  = new Label( 34 , row, res.getString( "pago" ) , cf );
                  Label cantidadPago  = new Label( 35 , row, res.getString( "cantidadPago" ) , cf );                  
                  Label frecuenciaPago  = new Label( 36 , row, res.getString( "frecuenciaPago" ) , cf );                  
                  Label inapam= new Label( 37 , row, res.getString( "inapam" ) , cf ); 
                  Label oportunidades  = new Label( 38 , row, res.getString( "oportunidades" ) , cf );
                  Label correo  = new Label( 39, row, res.getString( "correo" ) , cf );                  
                 Label Encuesta_idEncuesta  = new Label( 40 , row, res.getString( "Encuesta_idEncuesta" ) , cf );
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( idIntegrantes );
                     excelSheet.addCell( renglon );
                     excelSheet.addCell( nombre );
                     excelSheet.addCell( paterno );
                     excelSheet.addCell( materno );
                     excelSheet.addCell( reside );
                     excelSheet.addCell( edad );
                     excelSheet.addCell( fechaNac );
                     excelSheet.addCell( parentesco );
                     excelSheet.addCell( tieneCurp );
                      excelSheet.addCell( CURP );
                     excelSheet.addCell( acta );
                     excelSheet.addCell( sexo );
                     excelSheet.addCell( edoNac );
                  excelSheet.addCell( salud1 );
                     excelSheet.addCell( salud2 );
                     excelSheet.addCell( afiliacion1 );
                     excelSheet.addCell( afiliacion2 );
                     excelSheet.addCell( lenguaIndigena );
                     excelSheet.addCell( otraLengua );
                     excelSheet.addCell( espaniol );
                     excelSheet.addCell( indigena );
                     excelSheet.addCell( leer );
                     
                      excelSheet.addCell( nivel );
                     excelSheet.addCell( grado );
                     excelSheet.addCell( estudiante );
                     excelSheet.addCell( estadoCivil ); 
                     excelSheet.addCell( mesPasado );
                     excelSheet.addCell( motivoNoEstudia );
                     excelSheet.addCell( actMesPasado );
                     excelSheet.addCell( razonNoTrabajo );
                     excelSheet.addCell( trabajoPrincipal );
                     excelSheet.addCell( supervisor );
                     excelSheet.addCell( cuentaPropia );
                     excelSheet.addCell( pago );
                     excelSheet.addCell( cantidadPago );
                     excelSheet.addCell( frecuenciaPago );
                      excelSheet.addCell( inapam );
                     excelSheet.addCell( oportunidades );
                     excelSheet.addCell( correo );
                 excelSheet.addCell( Encuesta_idEncuesta );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Obtenemos el numero de columnas de la tabla*/
         try{
             PreparedStatement pstm = conect.prepareStatement( NUMERO_COLUMNAS );
             ResultSet res = pstm.executeQuery();
             res.next();
             columnas=res.getInt("columnas");
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         /*Redimenciona el tamaño de las columnas*/
        for (int i = 0; i < columnas; i++) {  
            CellView cv = excelSheet.getColumnView(i);  
            cv.setAutosize(true);  
            excelSheet.setColumnView(i, cv);  
        }
        
        
        
          /*Escribe el archivo excel en disco*/
        try {
            workbook.write();
            workbook.close();
            System.out.println(  "Escribiendo en disco....Listo"  );         
        } catch (IOException ex) {
            System.err.println(  ex.getMessage() );
        }
        catch (WriteException ex) {
           System.err.println(  ex.getMessage() );
        }

        System.out.println(  "Proceso completado...."  );
   
    }
    
    
    /*FUNCION ESCRIBIR SABANA*/
    
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "escribeSabana")
    public void escribeSabana() {
    
            /*this.db="webmilab_sedesol";
        this.user="javagu90";
        this.pass="heil";
        this.url="jdbc:mysql://localhost/"+this.db;*/
                this.db="placeho6_sedesol";
        this.user="placeho6_webmila";
        this.pass="webmilab";
        this.url="jdbc:mysql://webmilab.com:3306/"+this.db;
        try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conect = DriverManager.getConnection( this.url, this.user , this.pass );
         if ( conect!=null ){
            System.out.println("Conexión a la base de datos "+this.db+"...... Listo ");
         }
      }catch(SQLException | ClassNotFoundException e){
         System.err.println( e.getMessage() );
      }
        this.archivo=new File("c:\\temporal\\output.xls");
        
        
        int row=1, O1=0,O2=0,O3=0,O4=0,O5=0,O9=0;
       
        /*formato de fuente para el contenido del texto*/
        WritableFont wf= new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD);
        WritableCellFormat cf= new WritableCellFormat(wf);
        
        WritableFont wf2= new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD);
        WritableCellFormat cf2= new WritableCellFormat(wf2);

        
        /*Interfaz para una hoja de cálculo*/
        WritableSheet excelSheet= null;
        WritableWorkbook workbook=null;
        
        /*Estableciendo la configuración regional para generar la hoja de cálculo*/
        WorkbookSettings wbSettings= new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        
        try {
            workbook = Workbook.createWorkbook( archivo, wbSettings );
            //hoja con nombre de la tabla
            workbook.createSheet( "Exportación", 0 );
            excelSheet = workbook.getSheet(0);
            Label e1= new Label( 0 , 0, "CONSECUTIVO" , cf2 );
            Label e2= new Label( 1 , 0, "EXPEDIENTE" , cf2 );
            Label e3= new Label( 2 , 0, "CAJA" , cf2 );
            Label e4= new Label( 3 , 0, "PATERNO" , cf2 ); //columna, fila, elemento, formato
            Label e5= new Label( 4 , 0, "MATERNO" , cf2 );
            Label e6= new Label( 5 , 0, "NOMBRE" , cf2 );
            Label e7= new Label( 6 , 0, "MEJORAMIENTO" , cf2 );
            Label e8= new Label( 7 , 0, "AMPLIACION" , cf2 );
            Label e9= new Label( 8 , 0, "ADQUSICION" , cf2 );
            Label e10= new Label( 9 , 0, "EDIFICACION" , cf2 );
            Label e11= new Label( 10 , 0, "OTRA VIVIENDA EN PROPIEDAD" , cf2 );
            Label e12= new Label( 11 , 0, "NOMBRE ENCUESTADOR" , cf2 );
            Label e13= new Label( 12 , 0, "PATERNO ENCUESTADOR" , cf2 );
            Label e14= new Label( 13 , 0, "MATERNO ENCUESTADOR" , cf2 ); //columna, fila, elemento, formato
            Label e15= new Label( 14 , 0, "ENTIDAD" , cf2 );
            Label e16= new Label( 15 , 0, "CLAVE ENTIDAD" , cf2 );
            Label e17= new Label( 16 , 0, "CLAVE AGEB" , cf2 );
            Label e18= new Label( 17 , 0, "MUNICIPIO" , cf2 );
            Label e19= new Label( 18 , 0, "CLAVE MUNICIPIO" , cf2 );
            Label e20= new Label( 19 , 0, "CLAVE MANZANA" , cf2 );
            Label e21= new Label( 20 , 0, "LOCALIDAD" , cf2 );
            Label e22= new Label( 21 , 0, "CLAVE LOCALIDAD" , cf2 );
            Label e23= new Label( 22 , 0, "CLAVE POLIGONO" , cf2 );
            Label e24= new Label( 23 , 0, "TIPO DE VIALIDAD" , cf2 ); //columna, fila, elemento, formato
            Label e25= new Label( 24 , 0, "NOMBRE DE VIALIDAD" , cf2 );
            Label e26= new Label( 25 , 0, "NO EXTERIOR" , cf2 );
            Label e27= new Label( 26 , 0, "LETRA" , cf2 );
            Label e28= new Label( 27 , 0, "NO EXTERIOR ANTERIOR" , cf2 );
            Label e29= new Label( 28 , 0, "NO INTERIOR ANTERIOR" , cf2 );
            Label e30= new Label( 29 , 0, "LETRA ANTERIOR" , cf2 );
            Label e31= new Label( 30 , 0, "CODIGO POSTAL" , cf2 );
            Label e32= new Label( 31 , 0, "TIPO DE ASENTAMIENTO HUMANO" , cf2 );
            Label e33= new Label( 32 , 0, "NOMBRE DE ASENTAMIENTO HUMANO" , cf2 );
            Label e34= new Label( 33 , 0, "TIPO DE VIALIDAD" , cf2 ); //columna, fila, elemento, formato
            Label e35= new Label( 34 , 0, "NOMBRE DE VIALIDAD" , cf2 );
            Label e36= new Label( 35 , 0, "TIPO DE VIALIDAD" , cf2 );
            Label e37= new Label( 36 , 0, "NOMBRE DE VIALIDAD" , cf2 );
            Label e38= new Label( 37 , 0, "TIPO DE VIALIDAD" , cf2 );
            Label e39= new Label( 38 , 0, "NOMBRE DE VIALIDAD" , cf2 );
            Label e40= new Label( 39 , 0, "DESCRIPCION DE LA UBICACION" , cf2 );
            Label e41= new Label( 40 , 0, "TIPO DE VIVIENDA" , cf2 );
            Label e42= new Label( 41 , 0, "NO DE PERSONAS EN VIVIENDA" , cf2 );
            Label e43= new Label( 42 , 0, "NO DE HOGARES" , cf2 );
            Label e44= new Label( 43 , 0, "PERSONAS EN EL HOGAR" , cf2 ); //columna, fila, elemento, formato
            Label e45= new Label( 44 , 0, "COMPARTEN GASTOS" , cf2 );
            Label e46= new Label( 45 , 0, "HABITAN LA MISMA VIVIENDA" , cf2 );
            Label e47= new Label( 46 , 0, "TELEFONO" , cf2 );
            Label e48= new Label( 47 , 0, "NO" , cf2 );
            Label e49= new Label( 48 , 0, "NOMBRE" , cf2 );
            Label e50= new Label( 49 , 0, "PATERNO" , cf2 );
            Label e51= new Label( 50 , 0, "MATERNO" , cf2 );
            Label e52= new Label( 51 , 0, "RES" , cf2 );
            Label e53= new Label( 52 , 0, "EDAD" , cf2 );
            Label e54= new Label( 53 , 0, "FECHA NACIMIENTO" , cf2 ); //columna, fila, elemento, formato
            //Label e55= new Label( 54 , 0, "MES" , cf2 );
            //Label e56= new Label( 55 , 0, "AÑO" , cf2 );
            Label e57= new Label( 56 , 0, "PARENTESCO" , cf2 );
            Label e58= new Label( 57 , 0, "REN INFO" , cf2 );
            Label e59= new Label( 58 , 0, "TIENE CURP" , cf2 );
            Label e60= new Label( 59 , 0, "CURP" , cf2 );
            Label e61= new Label( 60 , 0, "TIENE ACTA" , cf2 );
            Label e62= new Label( 61 , 0, "SEXO" , cf2 );
            Label e63= new Label( 62 , 0, "EDO NAC" , cf2 );
            Label e64= new Label( 63 , 0, "SERV SALUD 1" , cf2 ); //columna, fila, elemento, formato
            Label e65= new Label( 64 , 0, "SERV SALUD 2" , cf2 );
            Label e66= new Label( 65 , 0, "AFIL 1" , cf2 );
            Label e67= new Label( 66 , 0, "AFIL 2" , cf2 );
            Label e68= new Label( 67 , 0, "tiene" , cf2 );
            Label e69= new Label( 68 , 0, "ORIGEN" , cf2 );
            Label e70= new Label( 69 , 0, "tiene" , cf2 );
            Label e71= new Label( 70 , 0, "ORIGEN" , cf2 );
            Label e72= new Label( 71 , 0, "tiene" , cf2 );
            Label e73= new Label( 72 , 0, "ORIGEN" , cf2 );
            Label e74= new Label( 73 , 0, "tiene" , cf2 ); //columna, fila, elemento, formato
            Label e75= new Label( 74 , 0, "ORIGEN" , cf2 );
            Label e76= new Label( 75 , 0, "tiene" , cf2 );
            Label e77= new Label( 76 , 0, "ORIGEN" , cf2 );
            Label e78= new Label( 77 , 0, "tiene" , cf2 );
            Label e79= new Label( 78 , 0, "ORIGEN" , cf2 );
            Label e80= new Label( 79 , 0, "tiene" , cf2 );
            Label e81= new Label( 80 , 0, "ORIGEN" , cf2 );
            Label e82= new Label( 81 , 0, "tiene" , cf2 );
            Label e83= new Label( 82 , 0, "ORIGEN" , cf2 );
            Label e84= new Label( 83 , 0, "tiene" , cf2 );
            Label e85= new Label( 84 , 0, "ORIGEN" , cf2 );
            
            Label e86= new Label( 85 , 0, "LENGUA" , cf2 );
            Label e87= new Label( 86 , 0, "ESPECIFICAR" , cf2 );
            Label e88= new Label( 87 , 0, "HABLA ESP" , cf2 ); //columna, fila, elemento, formato
            Label e89= new Label( 88 , 0, "INDIG" , cf2 );
            Label e90= new Label( 89 , 0, "LEER/ESCR" , cf2 );
            Label e91= new Label( 90 , 0, "NIVEL" , cf2 );
            Label e92= new Label( 91 , 0, "GRADO" , cf2 );
            Label e93= new Label( 92 , 0, "ASIST ESC" , cf2 );
            Label e94= new Label( 93 , 0, "MO DEJO ESC" , cf2 );
            Label e95= new Label( 94 , 0, "ESTADO CIVIL" , cf2 );
            Label e96= new Label( 95 , 0, "QUE HIZO MES PAS" , cf2 );
            Label e97= new Label( 96 , 0, "VERIF MES PAS" , cf2 );
            Label e98= new Label( 97 , 0, "RAZON NO TRAB" , cf2 ); //columna, fila, elemento, formato
            Label e99= new Label( 98 , 0, "TRAB PRINC MES PASADO" , cf2 );
            Label e100= new Label( 99 , 0, "JEFE/SUPERV" , cf2 );
            Label e101= new Label( 100 , 0, "TRAB POR SU CUENTA" , cf2 );
            Label e102= new Label( 101 , 0, "PRESTACION 1" , cf2 );
            Label e103= new Label( 102 , 0, "PRESTACION 2" , cf2 );
            Label e104= new Label( 103 ,0, "PRESTACION 3" , cf2 );
            Label e105= new Label( 104 , 0, "PRESTACION 4" , cf2 );
            Label e106= new Label( 105 , 0, "PRESTACION 5" , cf2 );
            Label e107= new Label( 106 , 0, "PRESTACION 6" , cf2 );
            Label e108= new Label( 107 , 0, "PRESTACION 7" , cf2 ); //columna, fila, elemento, formato
            Label e109= new Label( 108 , 0, "PRESTACION 8" , cf2 );
            Label e110= new Label( 109 , 0, "PRESTACION 9" , cf2 );
            Label e111= new Label( 110 , 0, "REC PAGO" , cf2 );
            Label e112= new Label( 111 , 0, "DINERO QUE RECIBE" , cf2 );
            Label e113= new Label( 112 , 0, "CADA CUANTO RECIBE" , cf2 );
            Label e114= new Label( 113 , 0, "TIENE CONTRATO VOLUNTARIAMENTE 1" , cf2 );
            Label e115= new Label( 114 , 0, "TIENE CONTRATO VOLUNTARIAMENTE 2" , cf2 );
            Label e116= new Label( 115 , 0, "TIENE CONTRATO VOLUNTARIAMENTE 3" , cf2 );
            Label e117= new Label( 116 , 0, "TIENE CONTRATO VOLUNTARIAMENTE 4" , cf2 );
            Label e118= new Label( 117 , 0, "TIENE CONTRATO VOLUNTARIAMENTE 5" , cf2 ); //columna, fila, elemento, formato
            Label e119= new Label( 118 , 0, "TIENE CONTRATO VOLUNTARIAMENTE 6" , cf2 );
            Label e120= new Label( 119 , 0, "TIENE CONTRATO VOLUNTARIAMENTE 9" , cf2 );
            Label e121= new Label( 120 , 0, "RECIBE DINERO POR 1" , cf2 );
            Label e122= new Label( 121 , 0, "RECIBE DINERO POR 2" , cf2 );
            Label e123= new Label( 122 , 0, "RECIBE DINERO POR 3" , cf2 );
            Label e124= new Label( 123 , 0, "RECIBE DINERO POR 4" , cf2 );
            Label e125= new Label( 124 , 0, "RECIBE DINERO POR 9" , cf2 );
            Label e126= new Label( 125 , 0, "TIENE INAPAM" , cf2 );
            Label e127= new Label( 126 , 0, "AM OPORTUNIDADES" , cf2 );
            Label e128= new Label( 127 , 0, "ATENCION MEDICA HOGAR 1" , cf2 ); //columna, fila, elemento, formato
            Label e129= new Label( 128 , 0, "ATENCION MEDICA HOGAR 2" , cf2 );
            Label e130= new Label( 129 , 0, "REMESAS" , cf2 );
            Label e131= new Label( 130 , 0, "COMIDAS AL DIA" , cf2 );
            Label e132= new Label( 131 , 0, "ALGUN INTEGRANTE DE 18 AÑOS O MAS 1" , cf2 );
            Label e133= new Label( 132 , 0, "ALGUN INTEGRANTE DE 18 AÑOS O MAS 2" , cf2 );
            Label e134= new Label( 133 , 0, "ALGUN INTEGRANTE DE 18 AÑOS O MAS 3" , cf2 );
            Label e135= new Label( 134 , 0, "ALGUN INTEGRANTE DE 18 AÑOS O MAS 4" , cf2 );
            Label e136= new Label( 135 , 0, "ALGUN INTEGRANTE DE 18 AÑOS O MAS 5" , cf2 );
            Label e137= new Label( 136 , 0, "ALGUN INTEGRANTE DE 18 AÑOS O MAS 6" , cf2 );
            Label e138= new Label( 137 , 0, "ALGUN INTEGRANTE MENOR DE 18 AÑOS 1" , cf2 ); //columna, fila, elemento, formato
            Label e139= new Label( 138 , 0, "ALGUN INTEGRANTE MENOR DE 18 AÑOS 2" , cf2 );
            Label e140= new Label( 139 , 0, "ALGUN INTEGRANTE MENOR DE 18 AÑOS 3" , cf2 );
            Label e141= new Label( 140 , 0, "ALGUN INTEGRANTE MENOR DE 18 AÑOS 4" , cf2 );
            Label e142= new Label( 141 , 0, "ALGUN INTEGRANTE MENOR DE 18 AÑOS 5" , cf2 );
            Label e143= new Label( 142 , 0, "ALGUN INTEGRANTE MENOR DE 18 AÑOS 6" , cf2 );
            Label e144= new Label( 143 , 0, "NUMERO DE CUARTOS" , cf2 );
            Label e145= new Label( 144 , 0, "CUARTOS PARA DORMIR" , cf2 );
            Label e146= new Label( 145 , 0, "DUERMEN DONDE COCINAN" , cf2 );
            Label e147= new Label( 146 , 0, "MAT PISOS" , cf2 );
            Label e148= new Label( 147 , 0, "PISOS DE TIERRA" , cf2 ); //columna, fila, elemento, formato
            Label e149= new Label( 148 , 0, "MAT TECHOS" , cf2 );
            Label e150= new Label( 149 , 0, "MAT MUROS" , cf2 );
            Label e151= new Label( 150 , 0, "TIPO DE BAÑO" , cf2 );
            Label e152= new Label( 151 , 0, "BAÑO EXCLUSIVO" , cf2 );
            Label e153= new Label( 152 , 0, "OBTEN DE AGUA" , cf2 );
            Label e154= new Label( 153 , 0, "TIPO DE DRENAJE" , cf2 );
            Label e155= new Label( 154 , 0, "BASURA" , cf2 );
            Label e156= new Label( 155 , 0, "COMBUS PARA COCINAR" , cf2 );
            Label e157= new Label( 156 , 0, "APARATO COCINA" , cf2 );
            Label e158= new Label( 157 , 0, "FOGON DENTRO/FUERA" , cf2 ); //columna, fila, elemento, formato
            Label e159= new Label( 158 , 0, "ensere 1" , cf2 );
            Label e160= new Label( 159 , 0, "SIRVE 1" , cf2 );
            Label e161= new Label( 160 , 0, "ensere 2" , cf2 );
            Label e162= new Label( 161 , 0, "SIRVE 2" , cf2 );
            Label e163= new Label( 162 , 0, "ensere 3" , cf2 );
            Label e164= new Label( 163 , 0, "SIRVE 3" , cf2 );
            Label e165= new Label( 164 , 0, "ensere 4" , cf2 );
            Label e166= new Label( 165 , 0, "SIRVE 4" , cf2 );
            Label e167= new Label( 166 , 0, "ensere 5" , cf2 );
            Label e168= new Label( 167 , 0, "SIRVE 5" , cf2 ); //columna, fila, elemento, formato
            Label e169= new Label( 168 , 0, "ensere 6" , cf2 );
            Label e170= new Label( 169 , 0, "SIRVE 6" , cf2 );
            Label e171= new Label( 170 , 0, "ensere 7" , cf2 );
            Label e172= new Label( 171 , 0, "SIRVE 7" , cf2 );
            Label e173= new Label( 172 , 0, "ensere 8" , cf2 );
            Label e174= new Label( 173 , 0, "SIRVE 8" , cf2 );
            Label e175= new Label( 174 , 0, "ensere 9" , cf2 );
            Label e176= new Label( 175 , 0, "SIRVE 9" , cf2 );
            Label e177= new Label( 176 , 0, "ensere 10" , cf2 );
            Label e178= new Label( 177 , 0, "SIRVE 10" , cf2 ); //columna, fila, elemento, formato
            Label e179= new Label( 178 , 0, "LUZ" , cf2 );
            Label e180= new Label( 179 , 0, "NUMERO DE FOCOS" , cf2 );
            Label e181= new Label( 180 , 0, "LA VIVIENDA ES" , cf2 );
            Label e182= new Label( 181 , 0, "ESCRITURAS 1" , cf2 );
            Label e183= new Label( 182 , 0, "ESCRITURAS 2" , cf2 );
            Label e184= new Label( 183 , 0, "CODIGO DE RESULTADO" , cf2 );
            Label e185= new Label( 184 , 0, "CAPTURISTA" , cf2 );
            Label e186= new Label( 185 , 0, "FEHA" , cf2 );
       
            
            try {
                     /*excelSheet.addCell( e1 );
                     excelSheet.addCell( e2 );
                     excelSheet.addCell( e3 );
                     excelSheet.addCell( e4);
                     excelSheet.addCell( e5 );
                     excelSheet.addCell( e6 );*/
                     excelSheet.addCell( e7 );
                     excelSheet.addCell( e8 );
                     excelSheet.addCell( e9 );
                     excelSheet.addCell( e10 );
                     excelSheet.addCell( e11 );
                     excelSheet.addCell( e12 );
                     excelSheet.addCell( e13 );
                     excelSheet.addCell( e14 );
                     /*excelSheet.addCell( e15 );
                     excelSheet.addCell( e16 );*/
                     excelSheet.addCell( e17 );
                     excelSheet.addCell( e18 );
                     excelSheet.addCell( e19 );
                     excelSheet.addCell( e20 );
                     excelSheet.addCell( e21 );
                     excelSheet.addCell( e22 );
                     excelSheet.addCell( e23 );
                     excelSheet.addCell( e24 );
                     excelSheet.addCell( e25 );
                     excelSheet.addCell( e26 );
                     excelSheet.addCell( e27 );
                     excelSheet.addCell( e28 );
                     /*excelSheet.addCell( e29 );
                     excelSheet.addCell( e30 );*/
                     excelSheet.addCell( e31 );
                     excelSheet.addCell( e32 );
                     excelSheet.addCell( e33 );
                     excelSheet.addCell( e34 );
                     excelSheet.addCell( e35 );
                     excelSheet.addCell( e36 );
                     excelSheet.addCell( e37 );
                     excelSheet.addCell( e38 );
                     excelSheet.addCell( e39 );
                     excelSheet.addCell( e40 );
                     excelSheet.addCell( e41 );
                     excelSheet.addCell( e42 );
                     excelSheet.addCell( e43 );
                     excelSheet.addCell( e44 );
                     excelSheet.addCell( e45 );
                     //excelSheet.addCell( e46 );
                     excelSheet.addCell( e47 );
                     //excelSheet.addCell( e48 );
                     excelSheet.addCell( e49 );
                     excelSheet.addCell( e50 );
                     excelSheet.addCell( e51 );
                     excelSheet.addCell( e52 );
                     excelSheet.addCell( e53 );
                     excelSheet.addCell( e54 );
                     //excelSheet.addCell( e55 );
                     //excelSheet.addCell( e56 );
                     excelSheet.addCell( e57 );
                     excelSheet.addCell( e58 );
                     excelSheet.addCell( e59 );
                     excelSheet.addCell( e60 );
                     excelSheet.addCell( e61 );
                     excelSheet.addCell( e62 );
                     excelSheet.addCell( e63 );
                     excelSheet.addCell( e64 );
                     excelSheet.addCell( e65 );
                     excelSheet.addCell( e66 );
                     excelSheet.addCell( e67 );
                     excelSheet.addCell( e68 );
                     excelSheet.addCell( e69 );
                     excelSheet.addCell( e70 );
                     excelSheet.addCell( e71 );
                     excelSheet.addCell( e72 );
                     excelSheet.addCell( e73 );
                     excelSheet.addCell( e74 );
                     excelSheet.addCell( e75 );
                     excelSheet.addCell( e76 );
                     excelSheet.addCell( e77 );
                     excelSheet.addCell( e78 );
                     excelSheet.addCell( e79 );
                     excelSheet.addCell( e80 );
                     excelSheet.addCell( e81 );
                     excelSheet.addCell( e82 );
                     excelSheet.addCell( e83 );
                     excelSheet.addCell( e84 );
                     excelSheet.addCell( e85 );
                     excelSheet.addCell( e86 );
                     excelSheet.addCell( e87 );
                     excelSheet.addCell( e88 );
                     excelSheet.addCell( e89 );
                     excelSheet.addCell( e90 );
                     excelSheet.addCell( e91 );
                     excelSheet.addCell( e92 );
                     excelSheet.addCell( e93 );
                     excelSheet.addCell( e94 );
                     excelSheet.addCell( e95 );
                     excelSheet.addCell( e96 );
                     excelSheet.addCell( e97 );
                     excelSheet.addCell( e98 );
                     excelSheet.addCell( e99 );
                     excelSheet.addCell( e100 );
                     excelSheet.addCell( e101 );
                     excelSheet.addCell( e102 );
                     excelSheet.addCell( e103 );
                     excelSheet.addCell( e104);
                     excelSheet.addCell( e105 );
                     excelSheet.addCell( e106 );
                     excelSheet.addCell( e107 );
                     excelSheet.addCell( e108 );
                     excelSheet.addCell( e109 );
                     excelSheet.addCell( e110 );
                     excelSheet.addCell( e111 );
                     excelSheet.addCell( e112 );
                     excelSheet.addCell( e113 );
                     excelSheet.addCell( e114 );
                     excelSheet.addCell( e115 );
                     excelSheet.addCell( e116 );
                     excelSheet.addCell( e117 );
                     excelSheet.addCell( e118 );
                     excelSheet.addCell( e119 );
                     excelSheet.addCell( e120 );
                     excelSheet.addCell( e121 );
                     excelSheet.addCell( e122 );
                     excelSheet.addCell( e123 );
                     excelSheet.addCell( e124 );
                     excelSheet.addCell( e125 );
                     excelSheet.addCell( e126 );
                     excelSheet.addCell( e127 );
                     excelSheet.addCell( e128 );
                     excelSheet.addCell( e129 );
                     excelSheet.addCell( e130 );
                     excelSheet.addCell( e131 );
                     excelSheet.addCell( e132 );
                     excelSheet.addCell( e133 );
                     excelSheet.addCell( e134 );
                     excelSheet.addCell( e135 );
                     excelSheet.addCell( e136 );
                     excelSheet.addCell( e137 );
                     excelSheet.addCell( e138 );
                     excelSheet.addCell( e139 );
                     excelSheet.addCell( e140 );
                     excelSheet.addCell( e141 );
                     excelSheet.addCell( e142 );
                     excelSheet.addCell( e143 );
                     excelSheet.addCell( e144 );
                     excelSheet.addCell( e145 );
                     excelSheet.addCell( e146 );
                     excelSheet.addCell( e147 );
                     excelSheet.addCell( e148 );
                     excelSheet.addCell( e149 );
                     excelSheet.addCell( e150 );
                     excelSheet.addCell( e151 );
                     excelSheet.addCell( e152 );
                     excelSheet.addCell( e153 );
                     excelSheet.addCell( e154 );
                     excelSheet.addCell( e155 );
                     excelSheet.addCell( e156 );
                     excelSheet.addCell( e157 );
                     excelSheet.addCell( e158 );
                     excelSheet.addCell( e159 );
                     excelSheet.addCell( e160 );
                     excelSheet.addCell( e161 );
                     excelSheet.addCell( e162 );
                     excelSheet.addCell( e163 );
                     excelSheet.addCell( e164 );
                     excelSheet.addCell( e165 );
                     excelSheet.addCell( e166 );
                     excelSheet.addCell( e167 );
                     excelSheet.addCell( e168 );
                     excelSheet.addCell( e169 );
                     excelSheet.addCell( e170 );
                     excelSheet.addCell( e171 );
                     excelSheet.addCell( e172 );
                     excelSheet.addCell( e173 );
                     excelSheet.addCell( e174 );
                     excelSheet.addCell( e175 );
                     excelSheet.addCell( e176 );
                     excelSheet.addCell( e177 );
                     excelSheet.addCell( e178 );
                     excelSheet.addCell( e179 );
                     excelSheet.addCell( e180 );
                     excelSheet.addCell( e181 );
                     excelSheet.addCell( e182 );
                     /*excelSheet.addCell( e183);
                     excelSheet.addCell( e184 );
                     excelSheet.addCell( e185 );
                     excelSheet.addCell( e186 );*/

            } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (IOException ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL */
        String sql = "Select * from encuesta as en inner join cuartos as cu on (en.idEncuesta=cu.Encuesta_idEncuesta) inner join domicilio as dom on (en.idEncuesta=dom.Encuesta_idEncuesta) inner join hogar as ho on (en.idEncuesta=cu.Encuesta_idEncuesta) inner join integrantes as inte on (en.idEncuesta=inte.Encuesta_idEncuesta) inner join servicios as se on (inte.idIntegrantes=se.Integrantes_idIntegrantes)inner join prestaciones as pre on (inte.idIntegrantes=pre.Integrantes_idIntegrantes) inner join pensiones as pe on (inte.idIntegrantes=pe.Integrantes_idIntegrantes)inner join discapacidades as dis on (inte.idIntegrantes=dis.Integrantes_idIntegrantes) inner join pdzp as pdzp on (en.idEncuesta=pdzp.Encuesta_idEncuesta) inner join visita as vi on (en.idEncuesta=vi.Encuesta_idEncuesta) inner join encuestador as encu on (vi.Encuestador_idEncuestador=encu.idEncuestador) inner join vivienda as vivi on (en.idEncuesta=vivi.Encuesta_idEncuesta) group by (en.idEncuesta)";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  //Label c1  = new Label( 0 , row, res.getString( "idAdicional" ) , cf );                  
                  //Label c2= new Label( 1 , row, res.getString( "tipo" ) , cf );                  
                  //Label c3= new Label( 2 , row, res.getString( "origen" ) , cf );
                  //Label c4= new Label( 3 , row, res.getString( "destino" ) , cf );
                  //Label c5  = new Label( 4 , row, res.getString( "kilometro" ) , cf );                  
                  //Label c6= new Label( 5 , row, res.getString( "metro" ) , cf );                  
                  Label c7= new Label( 6 , row, res.getString( "mejoramiento" ) , cf );
                  Label c8= new Label( 7 , row, res.getString( "ampliacion" ) , cf );
                  Label c9= new Label( 8 , row, res.getString( "adquisicion" ) , cf );
                  Label c10= new Label( 9 , row, res.getString( "edificacion" ) , cf );
                  Label c11= new Label( 10 , row, res.getString( "otraVivienda" ) , cf );
                  Label c12= new Label( 11 , row, res.getString( "encu.nombre" ) , cf );                  
                  Label c13= new Label( 12 , row, res.getString( "encu.paterno" ) , cf );
                  Label c14= new Label( 13 , row, res.getString( "encu.materno" ) , cf );
                  //Label c15= new Label( 14 , row, res.getString( "kilometro" ) , cf );                  
                  //Label c16= new Label( 15 , row, res.getString( "metro" ) , cf );                  
                  Label c17= new Label( 16 , row, res.getString( "claveAGEB" ) , cf );
                  Label c18= new Label( 17 , row, res.getString( "municipio" ) , cf );
                  Label c19= new Label( 18 , row, res.getString( "claveMunicipio" ) , cf );
                  Label c20= new Label( 19 , row, res.getString( "claveManzana" ) , cf );
                  Label c21= new Label( 20 , row, res.getString( "localidad" ) , cf );
                  Label c22= new Label( 21 , row, res.getString( "claveLocalidad" ) , cf );                  
                  Label c23= new Label( 22 , row, res.getString( "clavePoligono" ) , cf );
                  Label c24= new Label( 23 , row, res.getString( "tipoVialidad" ) , cf );
                  Label c25= new Label( 24 , row, res.getString( "nombreVialidad" ) , cf );                  
                  Label c26= new Label( 25 , row, res.getString( "numExterior" ) , cf );                  
                  Label c27= new Label( 26 , row, res.getString( "letraExterior" ) , cf );
                  Label c28= new Label( 27 , row, res.getString( "numExteriorAnterior" ) , cf );
                  //Label c29= new Label( 28 , row, res.getString( "codigoPostal" ) , cf );
                  //Label c30= new Label( 29 , row, res.getString( "margen" ) , cf );
                  Label c31= new Label( 30 , row, res.getString( "codigoPostal" ) , cf );
                  Label c32= new Label( 31 , row, res.getString( "tipoAsentamiento" ) , cf );                  
                  Label c33= new Label( 32 , row, res.getString( "nombreAsentamiento" ) , cf );
                  Label c34= new Label( 33 , row, res.getString( "entreTipoV1" ) , cf );
                  Label c35= new Label( 34 , row, res.getString( "entreNombreV1" ) , cf );                  
                  Label c36= new Label( 35 , row, res.getString( "entreTipoV2" ) , cf );                  
                  Label c37= new Label( 36 , row, res.getString( "entreNombreV2" ) , cf );
                  Label c38= new Label( 37 , row, res.getString( "posteriorTipo" ) , cf );
                  Label c39= new Label( 38 , row, res.getString( "posteriorNombre" ) , cf );
                  Label c40= new Label( 39 , row, res.getString( "descripcion" ) , cf );
                  Label c41= new Label( 40 , row, res.getString( "tipo" ) , cf );
                  Label c42= new Label( 41 , row, res.getString( "numPersonas" ) , cf );                  
                  Label c43= new Label( 42 , row, res.getString( "numHogares" ) , cf );
                  Label c44= new Label( 43 , row, res.getString( "numIntegrantes" ) , cf );
                  Label c45= new Label( 44 , row, res.getString( "gastos" ) , cf );                  
                  //Label c46= new Label( 5 , row, res.getString( "metro" ) , cf );                  
                  Label c47= new Label( 46 , row, res.getString( "telefono" ) , cf );
                  //Label c48= new Label( 47 , row, res.getString( "inte.nombre" ) , cf );
                  Label c49= new Label( 48 , row, res.getString( "inte.nombre" ) , cf );
                  Label c50= new Label( 49 , row, res.getString( "inte.paterno" ) , cf );
                  Label c51= new Label( 50 , row, res.getString( "inte.materno" ) , cf );
                  Label c52= new Label( 51 , row, res.getString( "reside" ) , cf );                  
                  Label c53= new Label( 52 , row, res.getString( "edad" ) , cf );
                  Label c54= new Label( 53 , row, res.getString( "fechaNac" ) , cf );
                  //Label c55= new Label( 54 , row, res.getString( "parentesco" ) , cf );                  
                  //Label c56= new Label( 55 , row, res.getString( "metro" ) , cf );                  
                  Label c57= new Label( 56 , row, res.getString( "parentesco" ) , cf );
                  //Label c58= new Label( 57 , row, res.getString( "tieneCURP" ) , cf );
                  Label c59= new Label( 58 , row, res.getString( "tieneCurp" ) , cf );
                  Label c60= new Label( 59 , row, res.getString( "CURP" ) , cf );
                  Label c61= new Label( 60 , row, res.getString( "acta" ) , cf );
                  Label c62= new Label( 61 , row, res.getString( "sexo" ) , cf );                  
                  Label c63= new Label( 62 , row, res.getString( "edoNac" ) , cf );
                  Label c64= new Label( 63 , row, res.getString( "inte.salud1" ) , cf );
                  Label c65= new Label( 64 , row, res.getString( "inte.salud2" ) , cf );                  
                  Label c66= new Label( 65 , row, res.getString( "afiliacion1" ) , cf );                  
                  Label c67= new Label( 66 , row, res.getString( "afiliacion2" ) , cf );
                  /*
                 */
                  Label c86= new Label( 85 , row, res.getString( "lenguaIndigena" ) , cf );                  
                  Label c87= new Label( 86 , row, res.getString( "otraLengua" ) , cf );
                  Label c88= new Label( 87 , row, res.getString( "espaniol" ) , cf );
                  Label c89= new Label( 88 , row, res.getString( "indigena" ) , cf );                  
                  Label c90= new Label( 89 , row, res.getString( "leer" ) , cf );                  
                  Label c91= new Label( 90 , row, res.getString( "nivel" ) , cf );
                  Label c92= new Label( 91 , row, res.getString( "grado" ) , cf );
                  Label c93= new Label( 92 , row, res.getString( "estudiante" ) , cf );
                  Label c94= new Label( 93 , row, res.getString( "motivoNoEstudia" ) , cf );
                  Label c95= new Label( 94 , row, res.getString( "estadoCivil" ) , cf );
                  Label c96= new Label( 95 , row, res.getString( "actMesPasado" ) , cf );                  
                  Label c97= new Label( 96 , row, res.getString( "mesPasado" ) , cf );
                  Label c98= new Label( 97 , row, res.getString( "razonNoTrabajo" ) , cf );
                  Label c99= new Label( 98 , row, res.getString( "trabajoPrincipal" ) , cf );                  
                  Label c100= new Label( 99 , row, res.getString( "supervisor" ) , cf );                  
                  Label c101= new Label( 100 , row, res.getString( "cuentaPropia" ) , cf );
  
                  Label c111= new Label( 110 , row, res.getString( "pago" ) , cf );
                  Label c112= new Label( 111 , row, res.getString( "cantidadPago" ) , cf );
                  Label c113= new Label( 112 , row, res.getString( "frecuenciaPago" ) , cf );

                  Label c126= new Label( 125 , row, res.getString( "inapam" ) , cf );                  
                  Label c127= new Label( 126 , row, res.getString( "oportunidades" ) , cf );
                  Label c128= new Label( 127 , row, res.getString( "ho.salud1" ) , cf );
                  Label c129= new Label( 128 , row, res.getString( "ho.salud2" ) , cf );                  
                  Label c130= new Label( 129 , row, res.getString( "remesas" ) , cf );                  
                  Label c131= new Label( 130 , row, res.getString( "comidas" ) , cf );
                  Label c132= new Label( 131 , row, res.getString( "MA" ) , cf );
                  Label c133= new Label( 132 , row, res.getString( "MB" ) , cf );
                  Label c134= new Label( 133 , row, res.getString( "MC" ) , cf );
                  Label c135= new Label( 134 , row, res.getString( "MD" ) , cf );
                  Label c136= new Label( 135 , row, res.getString( "ME" ) , cf );                  
                  Label c137= new Label( 136 , row, res.getString( "MF" ) , cf );
                  Label c138= new Label( 137 , row, res.getString( "menorA" ) , cf );
                  Label c139= new Label( 138 , row, res.getString( "menorB" ) , cf );                  
                  Label c140= new Label( 139 , row, res.getString( "menorC" ) , cf );                  
                  Label c141= new Label( 140 , row, res.getString( "menorD" ) , cf );
                  Label c142= new Label( 141 , row, res.getString( "menorE" ) , cf );
                  Label c143= new Label( 142 , row, res.getString( "menorF" ) , cf );
                  Label c144= new Label( 143 , row, res.getString( "numCuartos" ) , cf );
                  Label c145= new Label( 144 , row, res.getString( "paraDormir" ) , cf );
                  Label c146= new Label( 145 , row, res.getString( "cocinanDuermen" ) , cf );                  
                  Label c147= new Label( 146 , row, res.getString( "vivi.piso" ) , cf );
                  Label c148= new Label( 147 , row, res.getString( "pisoTierra" ) , cf );
                  Label c149= new Label( 148 , row, res.getString( "techo" ) , cf );                  
                  Label c150= new Label( 149 , row, res.getString( "muros" ) , cf );                  
                  Label c151= new Label( 150 , row, res.getString( "tipoBanio" ) , cf );
                  Label c152= new Label( 151 , row, res.getString( "banioExclusivo" ) , cf );
                  Label c153= new Label( 152 , row, res.getString( "obtAgua" ) , cf );
                  Label c154= new Label( 153 , row, res.getString( "drenaje" ) , cf );
                  Label c155= new Label( 154 , row, res.getString( "basura" ) , cf );
                  Label c156= new Label( 155 , row, res.getString( "combustible" ) , cf );                  
                  Label c157= new Label( 156 , row, res.getString( "aparatoCocinar" ) , cf );
                  Label c158= new Label( 157 , row, res.getString( "fogon" ) , cf );
                  Label c179= new Label( 178 , row, res.getString( "electricidad" ) , cf );                  
                  Label c180= new Label( 179 , row, res.getString( "numFocos" ) , cf );                  
                  Label c181= new Label( 180 , row, res.getString( "propiedad" ) , cf );
                  Label c182= new Label( 181 , row, res.getString( "escrituras" ) , cf );
                 
                 row++;
                              
                 try {
                     
                     excelSheet.addCell( c7 );
                     excelSheet.addCell( c8 );
                     excelSheet.addCell( c9 );
                     excelSheet.addCell( c10 );
                     excelSheet.addCell( c11 );
                     excelSheet.addCell( c12 );
                     excelSheet.addCell( c13 );
                     excelSheet.addCell( c14 );
                     excelSheet.addCell( c17 );
                     excelSheet.addCell( c18 );
                     excelSheet.addCell( c19 );
                     excelSheet.addCell( c20 );
                     excelSheet.addCell( c21 );
                     excelSheet.addCell( c22 );
                     excelSheet.addCell( c23 );
                     excelSheet.addCell( c24 );
                     excelSheet.addCell( c25 );
                     excelSheet.addCell( c26 );
                     excelSheet.addCell( c27 );
                     excelSheet.addCell( c28 );
                     //excelSheet.addCell( c29 );
                     //excelSheet.addCell( c30 );
                     excelSheet.addCell( c31 );
                     excelSheet.addCell( c32 );
                     excelSheet.addCell( c33 );
                     excelSheet.addCell( c34 );
                     excelSheet.addCell( c35 );
                     excelSheet.addCell( c36 );
                     excelSheet.addCell( c37 );
                     excelSheet.addCell( c38 );
                     excelSheet.addCell( c39 );
                     excelSheet.addCell( c40 );
                     excelSheet.addCell( c41 );
                     excelSheet.addCell( c42 );
                     excelSheet.addCell( c43 );
                     excelSheet.addCell( c44 );
                     excelSheet.addCell( c45 );
                    //excelSheet.addCell( c46 );
                     excelSheet.addCell( c47 );
                     //excelSheet.addCell( c48 );
                     excelSheet.addCell( c49 );
                     excelSheet.addCell( c50 );
                     excelSheet.addCell( c51 );
                     excelSheet.addCell( c52 );
                     excelSheet.addCell( c53 );
                     excelSheet.addCell( c54 );
                     //excelSheet.addCell( c55 );
                     //excelSheet.addCell( c56 );
                     excelSheet.addCell( c57 );
                     //excelSheet.addCell( c58 );
                     excelSheet.addCell( c59 );
                     excelSheet.addCell( c60 );
                     excelSheet.addCell( c61 );
                     excelSheet.addCell( c62 );
                     excelSheet.addCell( c63 );
                     excelSheet.addCell( c64 );
                     excelSheet.addCell( c65 );
                     excelSheet.addCell( c66 );
                     excelSheet.addCell( c67 );
                     excelSheet.addCell( c86 );
                     excelSheet.addCell( c87 );
                     excelSheet.addCell( c88 );
                     excelSheet.addCell( c89 );
                     excelSheet.addCell( c90 );
                     excelSheet.addCell( c91 );
                     excelSheet.addCell( c92 );
                     excelSheet.addCell( c93 );
                     excelSheet.addCell( c94 );
                     excelSheet.addCell( c95 );
                     excelSheet.addCell( c96 );
                     excelSheet.addCell( c97 );
                     excelSheet.addCell( c98 );
                     excelSheet.addCell( c99 );
                     excelSheet.addCell( c100 );
                     excelSheet.addCell( c101 );
                     excelSheet.addCell( c111 );
                     excelSheet.addCell( c112 );
                     excelSheet.addCell( c113 );
                     excelSheet.addCell( c126 );
                     excelSheet.addCell( c127 );
                     excelSheet.addCell( c128 );
                     excelSheet.addCell( c129 );
                     excelSheet.addCell( c130 );
                     excelSheet.addCell( c131 );
                     excelSheet.addCell( c132 );
                     excelSheet.addCell( c133 );
                     excelSheet.addCell( c134 );
                     excelSheet.addCell( c135 );
                     excelSheet.addCell( c136 );
                     excelSheet.addCell( c137 );
                     excelSheet.addCell( c138 );
                     excelSheet.addCell( c139 );
                     excelSheet.addCell( c140 );
                     excelSheet.addCell( c141 );
                     excelSheet.addCell( c142 );
                     excelSheet.addCell( c143 );
                     excelSheet.addCell( c144 );
                     excelSheet.addCell( c145 );
                     excelSheet.addCell( c146 );
                     excelSheet.addCell( c147 );
                     excelSheet.addCell( c148 );
                     excelSheet.addCell( c149 );
                     excelSheet.addCell( c150 );
                     excelSheet.addCell( c151 );
                     excelSheet.addCell( c152 );
                     excelSheet.addCell( c153 );
                     excelSheet.addCell( c154 );
                     excelSheet.addCell( c155 );
                     excelSheet.addCell( c156 );
                     excelSheet.addCell( c157 );
                     excelSheet.addCell( c158 );
                     excelSheet.addCell( c179 );
                     excelSheet.addCell( c180 );
                     excelSheet.addCell( c181 );
                     excelSheet.addCell( c182 );
                     
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
              
             res.close();
             
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
            JOptionPane.showMessageDialog(null, e.toString());
        }
     
         
         sql = "SELECT O1, O2, O3, O4, O5, O6, O7, O8, O9, Integrantes_idIntegrantes\n" +
"FROM vista_origen_pivotFINAL AS vorigen\n" +
"INNER JOIN integrantes AS inte ON ( inte.idIntegrantes = vorigen.Integrantes_idIntegrantes ) ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
             row=1;
              while(res.next())
              {
                  
                  Label c69= new Label( 68 , row, res.getString( "vorigen.O1" ) , cf );
                  Label c71= new Label( 70 , row, res.getString( "vorigen.O2" ) , cf );
                  Label c73= new Label( 72 , row, res.getString( "vorigen.O3" ) , cf );
                  Label c75= new Label( 74 , row, res.getString( "vorigen.O4" ) , cf );                  
                  Label c77= new Label( 76 , row, res.getString( "vorigen.O5" ) , cf );
                  Label c79= new Label( 78 , row, res.getString( "vorigen.O6" ) , cf );
                  Label c81= new Label( 80 , row, res.getString( "vorigen.O7" ) , cf );
                  Label c83= new Label( 82 , row, res.getString( "vorigen.O8" ) , cf );
                  Label c85= new Label( 84 , row, res.getString( "vorigen.O9" ) , cf );
                 
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( c69 );
                     excelSheet.addCell( c71 );
                     excelSheet.addCell( c73 );
                     excelSheet.addCell( c75 );
                     excelSheet.addCell( c77 );
                     excelSheet.addCell( c79 );
                     excelSheet.addCell( c81 );
                     excelSheet.addCell( c83 );
                     excelSheet.addCell( c85 ); 
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         
                  
         sql = "SELECT V1, V2, V3, V4, V5, V6, V9, Integrantes_idIntegrantes\n" +
"FROM vista_serviciosf_pivotfinal AS vspf\n" +
"INNER JOIN integrantes AS inte ON (inte.idIntegrantes = vspf.Integrantes_idIntegrantes )";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
             row=1;
              while(res.next())
              {
                  
                  Label c114= new Label( 113 , row, res.getString( "vspf.V1" ) , cf );
                  Label c115= new Label( 114 , row, res.getString( "vspf.V2" ) , cf );
                  Label c116= new Label( 115 , row, res.getString( "vspf.V3" ) , cf );                  
                  Label c117= new Label( 116 , row, res.getString( "vspf.V4" ) , cf );
                  Label c118= new Label( 117 , row, res.getString( "vspf.V5" ) , cf );
                  Label c119= new Label( 118 , row, res.getString( "vspf.V6" ) , cf );                  
                  Label c120= new Label( 119 , row, res.getString( "vspf.V9" ) , cf ); 
                 
                 row ++;                  
                 try {
                     excelSheet.addCell( c114 );
                     excelSheet.addCell( c115 );
                     excelSheet.addCell( c116 );
                     excelSheet.addCell( c117 );
                     excelSheet.addCell( c118 );
                     excelSheet.addCell( c119 );
                     excelSheet.addCell( c120 );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         
         
         sql = "SELECT V1, V2, V3, V4, V5, V6, V7, V8, V9, Integrantes_idIntegrantes\n" +
"FROM vista_prestacionesf_pivotfinal AS vppf\n" +
"INNER JOIN integrantes AS inte ON ( inte.idIntegrantes = vppf.Integrantes_idIntegrantes )";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
             row=1;
              while(res.next())
              {
                  
                  Label c102= new Label( 101 , row, res.getString( "vppf.V1" ) , cf );
                  Label c103= new Label( 102 , row, res.getString( "vppf.V2" ) , cf );
                  Label c104= new Label(103 , row, res.getString( "vppf.V3" ) , cf );
                  Label c105= new Label( 104 , row, res.getString( "vppf.V4" ) , cf );                  
                  Label c106= new Label( 105 , row, res.getString( "vppf.V5" ) , cf );                  
                  Label c107= new Label( 106 , row, res.getString( "vppf.V6" ) , cf );
                  Label c108= new Label( 107 , row, res.getString( "vppf.V7" ) , cf );
                  Label c109= new Label( 108 , row, res.getString( "vppf.V8" ) , cf );                  
                  Label c110= new Label( 109 , row, res.getString( "vppf.V9" ) , cf ); 
                 
                 row ++;                  
                 try {
                     excelSheet.addCell( c102 );
                     excelSheet.addCell( c103 );
                     excelSheet.addCell( c104 );
                     excelSheet.addCell( c105 );
                     excelSheet.addCell( c106 );
                     excelSheet.addCell( c107 );
                     excelSheet.addCell( c108 );
                     excelSheet.addCell( c109 );
                     excelSheet.addCell( c110 );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         
          sql = "SELECT V1, V2, V3, V4, V5, Integrantes_idIntegrantes\n" +
"FROM vista_pensionesf_pivotfinal AS vpenpf\n" +
"INNER JOIN integrantes AS inte ON ( inte.idIntegrantes = vpenpf.Integrantes_idIntegrantes )";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
             row=1;
              while(res.next())
              {
                  
                  Label c121= new Label( 120 , row, res.getString( "vpenpf.V1" ) , cf );
                  Label c122= new Label( 121 , row, res.getString( "vpenpf.V2" ) , cf );
                  Label c123= new Label( 122 , row, res.getString( "vpenpf.V3" ) , cf );
                  Label c124= new Label( 123 , row, res.getString( "vpenpf.V4" ) , cf );
                  Label c125= new Label( 124 , row, res.getString( "vpenpf.V5" ) , cf ); 
                 
                 row ++;                  
                 try {
                     excelSheet.addCell( c121 );
                     excelSheet.addCell( c122 );
                     excelSheet.addCell( c123 );
                     excelSheet.addCell( c124 );
                     excelSheet.addCell( c125 );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         
         sql = "SELECT V1, V2, V3, V4, V5, V6, V7, V8, V9, V10, Encuesta_idEncuesta\n" +
"FROM vista_aparatosf_pivotfinal AS vapf\n" +
"INNER JOIN encuesta AS encu ON ( encu.idEncuesta = vapf.Encuesta_idEncuesta )";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
             row=1;
              while(res.next())
              {
                  Label c159= new Label( 158 , row, res.getString( "vapf.V1" ) , cf );                  
                  Label c161= new Label( 160 , row, res.getString( "vapf.V2" ) , cf );
                  Label c163= new Label( 162 , row, res.getString( "vapf.V3" ) , cf );
                  Label c165= new Label( 164 , row, res.getString( "vapf.V4" ) , cf );
                  Label c167= new Label( 166 , row, res.getString( "vapf.V5" ) , cf );
                  Label c169= new Label( 168 , row, res.getString( "vapf.V6" ) , cf );                  
                  Label c171= new Label( 170 , row, res.getString( "vapf.V7" ) , cf );
                  Label c173= new Label( 172 , row, res.getString( "vapf.V8" ) , cf );
                  Label c175= new Label( 174 , row, res.getString( "vapf.V9" ) , cf );
                  Label c177= new Label( 176 , row, res.getString( "vapf.V10" ) , cf );
                 
                 row ++;                  
                 try {
                     excelSheet.addCell( c159 );
                     excelSheet.addCell( c161 );
                     excelSheet.addCell( c163 );
                     excelSheet.addCell( c165 );
                     excelSheet.addCell( c167 );
                     excelSheet.addCell( c169 );
                     excelSheet.addCell( c171 );
                     excelSheet.addCell( c173 );
                     excelSheet.addCell( c175 );
                     excelSheet.addCell( c177 );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         
          sql = "SELECT V1, V2, V3, V4, V5, V6, V7, V8, V9, Integrantes_idIntegrantes\n" +
"FROM vista_discapacidadesf_pivotfinal AS vdispf\n" +
"INNER JOIN integrantes AS inte ON ( inte.idIntegrantes = vdispf.Integrantes_idIntegrantes )";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
             row=1;
              while(res.next())
              {
                  Label c68= new Label( 67 , row, res.getString( "vdispf.V1" ) , cf );
                  Label c70= new Label( 69 , row, res.getString( "vdispf.V2" ) , cf );
                  Label c72= new Label( 71 , row, res.getString( "vdispf.V3" ) , cf );                  
                  Label c74= new Label( 73 , row, res.getString( "vdispf.V4" ) , cf );
                  Label c76= new Label( 75 , row, res.getString( "vdispf.V5" ) , cf );                  
                  Label c78= new Label( 77 , row, res.getString( "vdispf.V6" ) , cf );
                  Label c80= new Label( 79 , row, res.getString( "vdispf.V7" ) , cf );
                  Label c82= new Label( 81 , row, res.getString( "vdispf.V8" ) , cf );
                  Label c84= new Label( 83 , row, res.getString( "vdispf.V9" ) , cf );
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( c68 );
                     excelSheet.addCell( c70 );
                     excelSheet.addCell( c72 );
                     excelSheet.addCell( c74 );
                     excelSheet.addCell( c76 );
                     excelSheet.addCell( c78 );
                     excelSheet.addCell( c80 );
                     excelSheet.addCell( c82 );
                     excelSheet.addCell( c84 );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         
         sql = "SELECT S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, Encuesta_idEncuesta\n" +
"FROM  `vista_sirvenok_pivotFINAL` AS vsirven\n" +
"INNER JOIN encuesta AS encu ON ( vsirven.Encuesta_idEncuesta = encu.idEncuesta ) ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
             row=1;
              while(res.next())
              {
                  Label c160= new Label( 159 , row, res.getString( "vsirven.S1" ) , cf );                  
                  Label c162= new Label( 161 , row, res.getString( "vsirven.S2" ) , cf );
                  Label c164= new Label( 163 , row, res.getString( "vsirven.S3" ) , cf );
                  Label c166= new Label( 165 , row, res.getString( "vsirven.S4" ) , cf );                  
                  Label c168= new Label( 167 , row, res.getString( "vsirven.S5" ) , cf );
                  Label c170= new Label( 169 , row, res.getString( "vsirven.S6" ) , cf );                  
                  Label c172= new Label( 171 , row, res.getString( "vsirven.S7" ) , cf );
                  Label c174= new Label( 173 , row, res.getString( "vsirven.S8" ) , cf );
                  Label c176= new Label( 175 , row, res.getString( "vsirven.S9" ) , cf );                  
                  Label c178= new Label( 177 , row, res.getString( "vsirven.S10" ) , cf );
                  
                 row ++;                  
                 try {
                     excelSheet.addCell( c160 );
                     excelSheet.addCell( c162 );
                     excelSheet.addCell( c164 );
                     excelSheet.addCell( c166 );
                     excelSheet.addCell( c168 );
                     excelSheet.addCell( c170 );
                     excelSheet.addCell( c172 );
                     excelSheet.addCell( c174 );
                     excelSheet.addCell( c176 );
                     excelSheet.addCell( c178 );
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
              }
             res.close();         
         }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
         
         
         
         /*escribiendo en disco */
        try {
            workbook.write();
            workbook.close();
            System.out.println(  "Escribiendo en disco....Listo"  );         
        } catch (IOException ex) {
            System.err.println(  ex.getMessage() );
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        catch (WriteException ex) {
           System.err.println(  ex.getMessage() );
           JOptionPane.showMessageDialog(null, ex.toString());
        }

        System.out.println(  "Proceso completado...."  );
   
    }
    
}

