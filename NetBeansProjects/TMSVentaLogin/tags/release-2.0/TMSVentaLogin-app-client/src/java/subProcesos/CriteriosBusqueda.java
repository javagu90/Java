/*
 * CriteriosBusqueda.java
 *
 * Created on 31 de agosto de 2007, 08:42 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package subProcesos;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import tms_venta.SesionVenta;

/**
 *
 * @author ocruz
 */
public class CriteriosBusqueda{
        private static final long Dias=86400;
        private String strOrigen;
        private String strDestino;
        private String Servicio;
        private Timestamp dtFecha;
        private Timestamp dtHoraActual;
        private Timestamp dtHora;
        private Timestamp dtHoy;
        private Timestamp dtDateAux;
        private SimpleDateFormat formatoCompleto = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        private SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        private Calendar cldFecha = Calendar.getInstance();
        private String strEmpresa;
        
        public CriteriosBusqueda(Date xDateAux, String pStrOrigen, String pServicio,String pStrDestino,
                String pDtFecha, String pDtHora, String pStrEmpresa, SesionVenta pSesionVenta){
            strOrigen=pStrOrigen;
            strDestino=pStrDestino;
            Servicio=pServicio;
            dtFecha=null;
            dtHoraActual=null;
            dtHora=null;
            dtHoy=null;
            strEmpresa=pStrEmpresa;
            dtDateAux=new Timestamp(xDateAux.getTime());
            
            String strAux1;
            String strAux2;
            String strAux3;
            
            strAux3=formatoFecha.format(dtDateAux);
            
            if(!pDtFecha.equals("")){ // fecha
                if(!pDtHora.equals("")){ //F & H
                    strAux1=pDtFecha + " " + pDtHora;
                    strAux2=pDtFecha + " 23:59";
                    try {
                        this.dtHoy = new Timestamp(formatoCompleto.parse(strAux1).getTime());
                        this.dtFecha=new Timestamp(formatoCompleto.parse(strAux2).getTime());
                    } catch (ParseException ex) {
                        strAux1=formatoCompleto.format(dtDateAux);
                        try {
                            this.dtHoy = new Timestamp(formatoCompleto.parse(strAux1).getTime());
                            this.dtFecha=new Timestamp(formatoCompleto.parse(strAux1).getTime());
                        } catch (ParseException exn) {
                            ;
                        }
                    }
                } else{ //F & null
                    if(pDtFecha.equals(strAux3)){ // a)
                        strAux3=formatoHora.format(dtDateAux);
                        strAux1=pDtFecha + " " + strAux3;
                        strAux2=pDtFecha + " 23:59";
                        try {
                            this.dtHoy = new Timestamp(formatoCompleto.parse(strAux1).getTime());
                            this.dtFecha=new Timestamp(formatoCompleto.parse(strAux2).getTime());
                        } catch (ParseException ex) {
                            strAux1=formatoCompleto.format(dtDateAux);
                            try {
                                this.dtHoy = new Timestamp(formatoCompleto.parse(strAux1).getTime());
                                this.dtFecha=new Timestamp(formatoCompleto.parse(strAux1).getTime());
                            } catch (ParseException exn) {
                                ;
                            }
                        }
                    } else{ // b)
                        strAux1=pDtFecha + " 00:00";
                        strAux2=pDtFecha + " 23:59";
                        try {
                            this.dtHoy = new Timestamp(formatoCompleto.parse(strAux1).getTime());
                            this.dtFecha=new Timestamp(formatoCompleto.parse(strAux2).getTime());
                        } catch (ParseException ex) {
                            strAux1=formatoCompleto.format(dtDateAux);
                            try {
                                this.dtHoy = new Timestamp(formatoCompleto.parse(strAux1).getTime());
                                this.dtFecha=new Timestamp(formatoCompleto.parse(strAux1).getTime());
                            } catch (ParseException exn) {
                                ;
                            }
                        }
                    }
                }
            } else{ //fecha nula
                if(!pDtHora.equals("")){ //null & H
                    strAux3=formatoFecha.format(dtDateAux);
                    strAux1=strAux3 + " " + pDtHora;
                    strAux3=formatoFecha.format(cldFecha.getTime());
                    strAux2=strAux3 + " 23:59";
                    try {
                        this.dtHoy = new Timestamp(formatoCompleto.parse(strAux1).getTime());
                        this.dtFecha=new Timestamp(formatoCompleto.parse(strAux2).getTime());
                    } catch (ParseException ex) {
                        strAux1=formatoCompleto.format(dtDateAux);
                        try {
                            this.dtHoy = new Timestamp(formatoCompleto.parse(strAux1).getTime());
                            this.dtFecha=new Timestamp(formatoCompleto.parse(strAux1).getTime());
                        } catch (ParseException exn) {
                            ;
                        }
                    }
                } else{ //null & null
                    strAux3=formatoFecha.format(dtDateAux);
                    strAux1=strAux3 + " " + formatoHora.format(dtDateAux);
                    strAux3=formatoFecha.format(cldFecha.getTime());
                    strAux2=strAux3 + " 23:59";
                    try {
                        this.dtHoy = new Timestamp(formatoCompleto.parse(strAux1).getTime());
                        this.dtFecha=new Timestamp(formatoCompleto.parse(strAux2).getTime());
                    } catch (ParseException ex) {
                        strAux1=formatoCompleto.format(dtDateAux);
                        try {
                            this.dtHoy = new Timestamp(formatoCompleto.parse(strAux1).getTime());
                            this.dtFecha=new Timestamp(formatoCompleto.parse(strAux1).getTime());
                        } catch (ParseException exn) {
                            ;
                        }
                    }
                }
            }
            
            if(strEmpresa.equals("TODOS")) this.strEmpresa="%";
            if(strDestino.equals("TODOS")) this.strDestino="%";
            if(Servicio.equals("TODOS")) this.Servicio="%";
        }
        
        public String getEmpresa(){ return strEmpresa; }
        public String getOrigen(){ return strOrigen; }
        public String getDestino(){ return strDestino; }
        public String getServicio(){ return Servicio; }
        public Timestamp getHoy(){ return dtHoy; }
        public Timestamp getFecha(){ return dtFecha; }
        public Timestamp getHoraActual(){ return dtHoraActual; }
        public Timestamp getHora(){ return dtHora; }
    }
