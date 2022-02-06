/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
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

/**
 *
 * @author Javy
 */
@ManagedBean
@RequestScoped
public class Export {
    
    /*Datos de conexion*/
    private String db;
    private String user;
    private String pass;
    private String url;
    private Connection conect;
    File archivo;
    
    /*Constructor*/
    public Export()
    {
        this.db="mydb";
        this.user="javagu90";
        this.pass="heil";
        this.url="jdbc:mysql://localhost/"+this.db;
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
    }
    
    /*Metodo para obtener los registros de la bd y crear el archivo excel*/
    public void writeExcel()
    {
        int row=2;
        int columnas=0;
        final String NUMERO_COLUMNAS="SELECT COUNT(*) as columnas FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_schema = 'mydb' AND table_name = 'usuarios'";
        
        /*formato de fuente para el contenido del texto*/
        WritableFont wf= new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
        WritableCellFormat cf= new WritableCellFormat(wf);
        
        WritableFont wf2= new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
        WritableCellFormat cf2= new WritableCellFormat(wf2);
        
        try {
            cf2.setBorder(Border.ALL, BorderLineStyle.THIN);
            cf.setBorder(Border.ALL, BorderLineStyle.THIN);
        } catch (WriteException ex) {
            Logger.getLogger(Export.class.getName()).log(Level.SEVERE, null, ex);
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
            workbook.createSheet( "Pestaña 1", 0 );
            excelSheet = workbook.getSheet(0);
            Label encabezadoNombre  = new Label( 0 , 1, "Nombre" , cf2 ); //columna, fila, elemento, formato
            Label encabezadoApellidos  = new Label( 1 , 1, "Apellidos" , cf2 );
            Label encabezadoCorreo  = new Label( 2 , 1, "Correo" , cf2 );
            Label encabezadoTipo  = new Label( 3 , 1, "Tipo de Usuario" , cf2 );
            try {
                     excelSheet.addCell( encabezadoNombre );
                     excelSheet.addCell( encabezadoApellidos );
                     excelSheet.addCell( encabezadoCorreo );
                     excelSheet.addCell( encabezadoTipo);
                     
                 } catch (WriteException ex) {
                     System.err.println(  ex.getMessage() );
                 } 
            System.out.println(  "creando hoja excel.....Listo"  );            
        } catch (IOException ex) {
            System.err.println( ex.getMessage() );
        }
        
        /*Consulta SQL*/ 
        String sql = "SELECT nombre , apellidos , correo, tipo_usuario FROM usuarios ";
         try{
             PreparedStatement pstm = conect.prepareStatement( sql );
             ResultSet res = pstm.executeQuery();
             System.out.println(  "obteniendo registros.....Listo"  );
              while(res.next())
              {
                  Label nombre  = new Label( 0 , row, res.getString( "nombre" ) , cf );                  
                  Label apellidos= new Label( 1 , row, res.getString( "apellidos" ) , cf );                  
                  Label correo= new Label( 2 , row, res.getString( "correo" ) , cf );
                  Label tipoUser= new Label( 3 , row, res.getString( "tipo_usuario" ) , cf );
                 row ++;                  
                 try {
                     excelSheet.addCell( nombre );
                     excelSheet.addCell( apellidos );
                     excelSheet.addCell( correo );
                     excelSheet.addCell( tipoUser );
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
}
