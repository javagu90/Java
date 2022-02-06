/*
 * ValidaCampos.java
 *
 * Created on 27 de noviembre de 2008, 09:51 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.transacciones;

import tmspcweb.clases.asientosRequest;
import tmspcweb.clases.cancelacionBoletosRequest;
import tmspcweb.clases.cancelacionRequest;
import tmspcweb.clases.confirmaReservacionRequest;
import tmspcweb.clases.corridasRequest;
import tmspcweb.clases.datos.AsientoAutobus;
import tmspcweb.clases.itinerariosRequest;
import tmspcweb.clases.loginRequest;
import tmspcweb.clases.logoutRequest;
import tmspcweb.clases.reservacionRequest;

/**
 * Clase que volida los datos que componen una solicitud y verifica si existen
 * datos de regreso.
 * @author ocruz
 */
public class ValidaCampos {
    
    /**
     * Crea una nueva instancia de ValidaCampos.
     */
    public ValidaCampos() {
    }

    /**
     * Volida los campos para solicitar Inicio de Sesion.
     * @param lr Clase loginRequest.
     * @return Un String. Si es null no hay error; en otro caso, contiene el nombre del campo
     * que tiene un valor incorrecto.
     */
    public String SolicitudLogin(loginRequest lr){
        /*
         estaciontrabajo, numUsuario, contrasenia, uid
         **/
        if(lr.getSesion().getNumUsuario()==null || lr.getSesion().getNumUsuario().equals("")) return "numUsuario";
        if(lr.getSesion().getContrasenia()==null || lr.getSesion().getContrasenia().equals("")) return "contrasenia";
        if(lr.getUid()==null || lr.getUid().equals("")) return "uid";
        if(lr.getSesion().getIdEstacionTrabajo()==null || lr.getSesion().getIdEstacionTrabajo().equals("")) return "idEstacionTrabajo";
        
        return null;
    }
    
    /**
     * Volida los campos para solicitar Corridas.
     * @param cr Clase corridasRequest.
     * @return Clase corridasResponse.
     */
    public String SolicitudCorridas(corridasRequest cr){
        /*
         uid, numUsuario, corteId, servicio, origen, destino,
         fechaSalida, horarioSalida, fechaRegreso, horarioRegreso
         **/
        if(cr.getUid()==null || cr.getUid().equals("")) return "uid";
        if(cr.getSesion().getNumUsuario()==null || cr.getSesion().getNumUsuario().equals("")) return "numSocio";
        if(cr.getSesion().getIdSesion()==null || cr.getSesion().getIdSesion().equals("")) return "idSesion";
        //if(cr.getTipoServicio()==null || cr.getTipoServicio().getClave()==null || cr.getTipoServicio().getClave().equals("")) return "tipoServicio";
        //if(cr.getTipoServicio()==null || cr.getTipoServicio().getClave()==null) return "tipoServicio";
        if(cr.getOrigen()==null || cr.getOrigen().getClave()==null || cr.getOrigen().getClave().equals("")) return "origen";
        if(cr.getDestino()==null || cr.getDestino().getClave()==null || cr.getDestino().getClave().equals("")) return "destino";
        if(cr.getFechaSalida()==null || cr.getFechaSalida().equals("")) return "fechaSalida";
        if(cr.getHorarioSalida()<0 || cr.getHorarioSalida()>4) return "horarioSalida";
        if(cr.getFechaRegreso()!=null && !cr.getFechaRegreso().equals(""))
            if(cr.getHorarioRegreso()<0 || cr.getHorarioRegreso()>4) return "horarioRegreso";
        return null;
    }
    
    /**
     * Volida los campos para solicitar Itinerarios.
     * @param ir Clase itinerariosRequest.
     * @return Clase itinerariosResponse.
     */
    public String SolicitudItinerarios(itinerariosRequest ir){
        /*
        uid, corteId, negocio, empresa, numCorrida, origen, destino, horaSalida, tipoCorrida
        */
        if(ir.getUid()==null || ir.getUid().equals("")) return "uid";
        if(ir.getSesion().getIdSesion()==null || ir.getSesion().getIdSesion().equals("")) return "idSesion";
        if(ir.getDatosIda().getNegocio()==null || ir.getDatosIda().getNegocio().getNombre().equals("")) return "negocio";
        if(ir.getDatosIda().getEmpresa()==null || ir.getDatosIda().getEmpresa().getNombre().equals("")) return "empresa";
        if(ir.getDatosIda().getNumCorrida()==null || ir.getDatosIda().getNumCorrida().equals("")) return "numCorrida";
        if(ir.getDatosIda().getOrigen()==null || ir.getDatosIda().getOrigen().getClave()==null || ir.getDatosIda().getOrigen().getClave().equals("")) return "origen";
        if(ir.getDatosIda().getDestino()==null || ir.getDatosIda().getDestino().getClave()==null || ir.getDatosIda().getDestino().getClave().equals("")) return "destino";
        if(ir.getDatosIda().getHoraSalida()==null || ir.getDatosIda().getHoraSalida().equals("")) return "horaSalida";
        if(ir.getDatosIda().getTipoCorrida()==null || ir.getDatosIda().getTipoCorrida().equals("")) return "tipoCorrida";
        if(ir.getDatosRegreso()!=null && (ir.getDatosRegreso().getNumCorrida()!=null && !ir.getDatosRegreso().getNumCorrida().equals(""))){
            if(ir.getDatosRegreso().getNegocio()==null || ir.getDatosRegreso().getNegocio().getNombre().equals("")) return "DatosRegreso negocio";
            if(ir.getDatosRegreso().getEmpresa()==null || ir.getDatosRegreso().getEmpresa().getNombre().equals("")) return "DatosRegreso empresa";
            if(ir.getDatosRegreso().getOrigen()==null || ir.getDatosRegreso().getOrigen().getClave()==null || ir.getDatosRegreso().getOrigen().getClave().equals("")) return "DatosRegreso origen";
            if(ir.getDatosRegreso().getDestino()==null || ir.getDatosRegreso().getDestino().getClave()==null || ir.getDatosRegreso().getDestino().getClave().equals("")) return "DatosRegreso destino";
            if(ir.getDatosRegreso().getHoraSalida()==null || ir.getDatosRegreso().getHoraSalida().equals("")) return "DatosRegreso horaSalida";
            if(ir.getDatosRegreso().getTipoCorrida()==null || ir.getDatosRegreso().getTipoCorrida().equals("")) return "DatosRegreso tipoCorrida";
        }
        return null;
    }

    /**
     * Volida los campos para solicitar Asientos.
     * @param ar Clase asientosRequest.
     * @return Clase asientosResponse.
     */
    public String SolicitudAsientos(asientosRequest ar){
        /*
        uid, corteId, asientos disponibles, origen, destino, negocio, empresa, fechaSalida, horaSalida, numCorrida, cupoAutobus
        */
        if(ar.getUid()==null || ar.getUid().equals("")) return "uid";
        if(ar.getSesion().getIdSesion()==null || ar.getSesion().getIdSesion().equals("")) return "idSesion";
        if(ar.getDatosIda().getOrigen()==null || ar.getDatosIda().getOrigen().getClave()==null || ar.getDatosIda().getOrigen().getClave().equals("")) return "origen";
        if(ar.getDatosIda().getDestino()==null || ar.getDatosIda().getDestino().getClave()==null || ar.getDatosIda().getDestino().getClave().equals("")) return "destino";
        if(ar.getDatosIda().getNegocio()==null || ar.getDatosIda().getNegocio().getNombre().equals("")) return "negocio";
        if(ar.getDatosIda().getEmpresa()==null || ar.getDatosIda().getEmpresa().getNombre().equals("")) return "empresa";
        if(ar.getDatosIda().getFechaSalida()==null || ar.getDatosIda().getFechaSalida().equals("")) return "fechaSalida";
        if(ar.getDatosIda().getHoraSalida()==null || ar.getDatosIda().getHoraSalida().equals("")) return "horaSalida";
        if(ar.getDatosIda().getNumCorrida()==null || ar.getDatosIda().getNumCorrida().equals("")) return "numCorrida";
        if(ar.getDatosIda().getCupoAutobus()<1 || ar.getDatosIda().getCupoAutobus()>55) return "cupoAutobus";
        if(ar.getDatosRegreso()!=null && (ar.getDatosRegreso().getNumCorrida()!=null && !ar.getDatosRegreso().getNumCorrida().equals(""))){
            if(ar.getDatosRegreso().getOrigen()==null || ar.getDatosRegreso().getOrigen().getClave()==null || ar.getDatosRegreso().getOrigen().getClave().equals("")) return "Regreso origen";
            if(ar.getDatosRegreso().getDestino()==null || ar.getDatosRegreso().getDestino().getClave()==null || ar.getDatosRegreso().getDestino().getClave().equals("")) return "Regreso destino";
            if(ar.getDatosRegreso().getNegocio()==null || ar.getDatosRegreso().getNegocio().getNombre().equals("")) return "Regreso negocio";
            if(ar.getDatosRegreso().getEmpresa()==null || ar.getDatosRegreso().getEmpresa().getNombre().equals("")) return "Regreso empresa";
            if(ar.getDatosRegreso().getFechaSalida()==null || ar.getDatosRegreso().getFechaSalida().equals("")) return "Regreso fechaSalida";
            if(ar.getDatosRegreso().getHoraSalida()==null || ar.getDatosRegreso().getHoraSalida().equals("")) return "Regreso horaSalida";
            if(ar.getDatosRegreso().getCupoAutobus()<1 || ar.getDatosRegreso().getCupoAutobus()>55) return "Regreso cupoAutobus";
        }
        return null;
    }
    
    /**
     * Volida los campos para solicitar Reservacion.
     * @param rr Clase reservacionRequest.
     * @return Clase reservacionResponse.
     */
    public String SolicitudReservacion(reservacionRequest rr){
        /*
        uid, usuario, corteId, origen, destino, negocio, empresa, fechaSalida, horaSalida, numCorrida, cupoAutobus,
        asientosDisponibles, tipoCorrida, tarifas
        */
        if(rr.getUid()==null || rr.getUid().equals("")) return "uid";
        if(rr.getSesion().getIdSesion()==null || rr.getSesion().getIdSesion().equals("")) return "idSesion";
        if(rr.getSesion().getNumUsuario()==null || rr.getSesion().getNumUsuario().equals("")) return "numSocio";
        if(rr.getDatosIda().getOrigen()==null || rr.getDatosIda().getOrigen().getClave()==null || rr.getDatosIda().getOrigen().getClave().equals("")) return "origen";
        if(rr.getDatosIda().getDestino()==null || rr.getDatosIda().getDestino().getClave()==null || rr.getDatosIda().getDestino().getClave().equals("")) return "destino";
        if(rr.getDatosIda().getNegocio()==null || rr.getDatosIda().getNegocio().getNombre().equals("")) return "negocio";
        if(rr.getDatosIda().getEmpresa()==null || rr.getDatosIda().getEmpresa().getNombre().equals("")) return "empresa";
        if(rr.getDatosIda().getFechaSalida()==null || rr.getDatosIda().getFechaSalida().equals("")) return "fechaSalida";
        if(rr.getDatosIda().getHoraSalida()==null || rr.getDatosIda().getHoraSalida().equals("")) return "horaSalida";
        if(rr.getDatosIda().getNumCorrida()==null || rr.getDatosIda().getNumCorrida().equals("")) return "numCorrida";
        if(rr.getDatosIda().getCupoAutobus()<1 || rr.getDatosIda().getCupoAutobus()>55) return "cupoAutobus";
        if(rr.getDatosIda().getAsientosDisponibles()==null || rr.getDatosIda().getAsientosDisponibles().equals("")) return "AsientosDisponibles";
        if(rr.getDatosIda().getTipoCorrida()==null || rr.getDatosIda().getTipoCorrida().equals("")) return "tipoCorrida";
        if(rr.getDatosIda().getTarifaAdulto()<=0) return "TarifaAdulto";
        if(rr.getDatosIda().getTarifaNinio()<=0) return "TarifaNinio";
        if(rr.getDatosIda().getTarifaEstudiante()<=0) return "TarifaEstudiante";
        if(rr.getDatosIda().getTarifaMaestro()<=0) return "TarifaMaestro";
        if(rr.getDatosIda().getTarifaINSEN()<=0) return "TarifaINSEN";
        if(rr.getDatosIda().getAsientosSeleccionados()==null) return "AsientosSeleccionados";
        AsientoAutobus temp [] = rr.getDatosIda().getAsientosSeleccionados();
        for(int k = 0; k < rr.getDatosIda().getAsientosSeleccionados().length; k++)
            if(temp[k].getNumeroAsiento() > rr.getDatosIda().getCupoAutobus())
                return "numeroAsiento";
        if(rr.getDatosRegreso()!=null && (rr.getDatosRegreso().getNumCorrida()!=null && !rr.getDatosRegreso().getNumCorrida().equals(""))){
            if(rr.getDatosRegreso().getOrigen()==null || rr.getDatosRegreso().getOrigen().getClave()==null || rr.getDatosRegreso().getOrigen().getClave().equals("")) return "Regreso origen";
            if(rr.getDatosRegreso().getDestino()==null || rr.getDatosRegreso().getDestino().getClave()==null || rr.getDatosRegreso().getDestino().getClave().equals("")) return "Regreso destino";
            if(rr.getDatosRegreso().getNegocio()==null || rr.getDatosRegreso().getNegocio().getNombre().equals("")) return "Regreso negocio";
            if(rr.getDatosRegreso().getEmpresa()==null || rr.getDatosRegreso().getEmpresa().getNombre().equals("")) return "Regreso empresa";
            if(rr.getDatosRegreso().getFechaSalida()==null || rr.getDatosRegreso().getFechaSalida().equals("")) return "Regreso fechaSalida";
            if(rr.getDatosRegreso().getHoraSalida()==null || rr.getDatosRegreso().getHoraSalida().equals("")) return "Regreso horaSalida";
            if(rr.getDatosRegreso().getNumCorrida()==null || rr.getDatosRegreso().getNumCorrida().equals("")) return "Regreso numCorrida";
            if(rr.getDatosRegreso().getCupoAutobus()<1 || rr.getDatosRegreso().getCupoAutobus()>55) return "Regreso cupoAutobus";
            if(rr.getDatosRegreso().getAsientosDisponibles()==null || rr.getDatosRegreso().getAsientosDisponibles().equals("")) return "Regreso AsientosDisponibles";
            if(rr.getDatosRegreso().getTipoCorrida()==null || rr.getDatosRegreso().getTipoCorrida().equals("")) return "Regreso tipoCorrida";
            if(rr.getDatosRegreso().getTarifaAdulto()<=0) return "Regreso TarifaAdulto";
            if(rr.getDatosRegreso().getTarifaNinio()<=0) return "Regreso TarifaNinio";
            if(rr.getDatosRegreso().getTarifaEstudiante()<=0) return "Regreso TarifaEstudiante";
            if(rr.getDatosRegreso().getTarifaMaestro()<=0) return "Regreso TarifaMaestro";
            if(rr.getDatosRegreso().getTarifaINSEN()<=0) return "Regreso TarifaINSEN";
            if(rr.getDatosRegreso().getAsientosSeleccionados()==null) return "Regreso AsientosSeleccionados";
            AsientoAutobus temp1 [] = rr.getDatosRegreso().getAsientosSeleccionados();
            for(int k = 0; k < rr.getDatosRegreso().getAsientosSeleccionados().length; k++)
                if(temp1[k].getNumeroAsiento() > rr.getDatosRegreso().getCupoAutobus())
                    return "numeroAsiento";
        }
        return null;
    }
    
    /**
     * Volida los campos para solicitar Confirmar Reservacion.
     * @param crr Clase conformaReservacionRequest.
     * @return Clase confirmaReservacionResponse.
     */
    public String SolicitudConfirmaReservacion(confirmaReservacionRequest crr){
        /*
        uid, usuario, corteId, origen, negocio, numCorrida, fecha, asientosSeleccionados, folioReservacion, tipoPago
        */
        if(crr.getUid()==null || crr.getUid().equals("")) return "uid";
        if(crr.getSesion().getIdSesion()==null || crr.getSesion().getIdSesion().equals("")) return "idSesion";
        if(crr.getSesion().getNumUsuario()==null || crr.getSesion().getNumUsuario().equals("")) return "numSocio";
        if(crr.getSesion().getClaveCaja()==null || crr.getSesion().getClaveCaja().equals("")) return "claveClaja";
        if(crr.getDatosIda().getOrigen()==null || crr.getDatosIda().getOrigen().getClave()==null || crr.getDatosIda().getOrigen().getClave().equals("")) return "origen";
        if(crr.getDatosIda().getNegocio()==null || crr.getDatosIda().getNegocio().getNombre().equals("")) return "negocio";
        if(crr.getDatosIda().getFecha()==null || crr.getDatosIda().getFecha().equals("")) return "fechaSalida";
        if(crr.getDatosIda().getNumCorrida()==null || crr.getDatosIda().getNumCorrida().equals("")) return "numCorrida";
        if(crr.getDatosIda().getAsientosSeleccionados()==null) return "AsientosSeleccionados";
        if(crr.getDatosIda().getFolioReservacionNegocio()==null || crr.getDatosIda().getFolioReservacionNegocio().equals("")) return "FolioReservacionNegocio";
        if(crr.getDatosIda().getTipoPago()==null || crr.getDatosIda().getTipoPago().equals("")) return "TipoPago";
        if(crr.getDatosRegreso()!=null && (crr.getDatosRegreso().getFolioReservacionNegocio()!=null && !crr.getDatosRegreso().getFolioReservacionNegocio().equals(""))){
            if(crr.getDatosRegreso().getOrigen()==null || crr.getDatosRegreso().getOrigen().getClave()==null || crr.getDatosRegreso().getOrigen().getClave().equals("")) return "Regreso origen";
            if(crr.getDatosRegreso().getNegocio()==null || crr.getDatosRegreso().getNegocio().getNombre().equals("")) return "Regreso negocio";
            if(crr.getDatosRegreso().getFecha()==null || crr.getDatosRegreso().getFecha().equals("")) return "Regreso fecha";
            if(crr.getDatosRegreso().getNumCorrida()==null || crr.getDatosRegreso().getNumCorrida().equals("")) return "Regreso numCorrida";
            if(crr.getDatosRegreso().getAsientosSeleccionados()==null) return "Regreso AsientosSeleccionados";
            if(crr.getDatosRegreso().getTipoPago()==null || crr.getDatosRegreso().getTipoPago().equals("")) return "Regreso TipoPago";
        }
        return null;
    }
    
    /**
     * Volida los campos para solicitar Cancelar Reservacion.
     * @param cnr Clase cancelacionRequest.
     * @return Clase cancelacionResponse.
     */
    public String SolicitudCancelaReservacion(cancelacionRequest cnr){
        /*
        uid, usuario, corteId, origen, negocio, numCorrida, fecha, asientosSeleccionados, folioReservacion, motivo
        */
        if(cnr.getUid()==null || cnr.getUid().equals("")) return "uid";
        if(cnr.getSesion().getIdSesion()==null || cnr.getSesion().getIdSesion().equals("")) return "idSesion";
        if(cnr.getSesion().getNumUsuario()==null || cnr.getSesion().getNumUsuario().equals("")) return "numSocio";
        if(cnr.getFolioReservacionNegocio()==null || cnr.getFolioReservacionNegocio().equals("")) return "FolioReservacionNegocio";
        if(cnr.getMotivoCancelacion()==null || cnr.getMotivoCancelacion().equals("")) return "MotivoCancelacion";
        if(cnr.getDatosIda().getOrigen()==null || cnr.getDatosIda().getOrigen().getClave()==null || cnr.getDatosIda().getOrigen().getClave().equals("")) return "origen";
        if(cnr.getDatosIda().getNegocio()==null || cnr.getDatosIda().getNegocio().getNombre().equals("")) return "negocio";
        if(cnr.getDatosIda().getFecha()==null || cnr.getDatosIda().getFecha().equals("")) return "fechaSalida";
        if(cnr.getDatosIda().getNumCorrida()==null || cnr.getDatosIda().getNumCorrida().equals("")) return "numCorrida";
        if(cnr.getDatosIda().getAsientosSeleccionados()==null) return "AsientosSeleccionados";
        /*if(cnr.getDatosRegreso()!=null && cnr.getDatosRegreso().getAsientosSeleccionados()!=null){
            if(cnr.getDatosRegreso().getOrigen()==null || cnr.getDatosRegreso().getOrigen().getClave()==null || cnr.getDatosRegreso().getOrigen().getClave().equals("")) return "Regreso origen";
            if(cnr.getDatosRegreso().getNegocio()==null || cnr.getDatosRegreso().getNegocio().getNombre().equals("")) return "Regreso negocio";
            if(cnr.getDatosRegreso().getFecha()==null || cnr.getDatosRegreso().getFecha().equals("")) return "Regreso fechaSalida";
            if(cnr.getDatosRegreso().getNumCorrida()==null || cnr.getDatosRegreso().getNumCorrida().equals("")) return "Regreso numCorrida";
        }*/
        return null;
    }
    
     /**
     * Verifica si existen datos de regreso para cancelar boleto.
     * @param crr Clase confirmaReservacionRequest.
     * @return Clase confirmaReservacionResponse.
     */
    public String SolicitudCancelacionBoletos(cancelacionBoletosRequest crr){
        if(crr.getSesion().getIdSesion()==null || crr.getSesion().getIdSesion().equals("")) return "idSesion";
        if(crr.getSesion().getNumUsuario()==null || crr.getSesion().getNumUsuario().equals("")) return "numSocio";
        if(crr.getSesion().getIdEstacionTrabajo() == null || crr.getSesion().getIdEstacionTrabajo().equals("")) return "idEstacionTrabajo";
        if(crr.getDatosIda().getNumCorrida()==null || crr.getDatosIda().getNumCorrida().equals("")) return "numCorrida";
        if(crr.getDatosIda().getOrigen()==null || crr.getDatosIda().getOrigen().getClave()==null || crr.getDatosIda().getOrigen().getClave().equals("")) return "origen";
        if(crr.getDatosIda().getOrigen().getId()==null || crr.getDatosIda().getOrigen().getId().equals("")) return "origen_id";
        if(crr.getDatosIda().getAsientosSeleccionados()==null) return "AsientosSeleccionados";
        AsientoAutobus temp[] = crr.getDatosIda().getAsientosSeleccionados();
        for(int k = 0; k < temp.length; k++) {
            if(temp[k].getFolioBoletoNegocio() == null || temp[k].getFolioBoletoNegocio().equals("")) return "AsientosSeleccionados.folios";
            if(temp[k].getTipoAsiento() == null || temp[k].getTipoAsiento().equals("")) return "AsientosSeleccionados.tipo";
        }
        
        if(cancelacionBoletosnRegreso(crr)){
            if(crr.getDatosRegreso().getNumCorrida()==null || crr.getDatosRegreso().getNumCorrida().equals("")) return "numCorrida";
            if(crr.getDatosRegreso().getOrigen()==null || crr.getDatosRegreso().getOrigen().getClave()==null || crr.getDatosRegreso().getOrigen().getClave().equals("")) return "origen";
            if(crr.getDatosRegreso().getOrigen().getId()==null || crr.getDatosRegreso().getOrigen().getId().equals("")) return "origen_id";
            if(crr.getDatosRegreso().getAsientosSeleccionados()==null) return "AsientosSeleccionados";
            temp = crr.getDatosRegreso().getAsientosSeleccionados();
            for(int k = 0; k < temp.length; k++) {
                if(temp[k].getFolioBoletoNegocio() == null || temp[k].getFolioBoletoNegocio().equals("")) return "AsientosSeleccionados.folios";
                if(temp[k].getTipoAsiento() == null || temp[k].getTipoAsiento().equals("")) return "AsientosSeleccionados.tipo";
            }
        }
        return null;
    }

    /**
     * Volida los campos para solicitar Terminar Sesion.
     * @param lr Clase logoutRequest.
     * @return Clase logoutResponse.
     */
    public String SolicitudLogout(logoutRequest lr){
        /*
         idSesion, uid
         **/
        if(lr.getSesion().getIdSesion()==null || lr.getSesion().getIdSesion().equals("")) return "idSesion";
        if(lr.getUid()==null || lr.getUid().equals("")) return "uid";
        
        return null;
    }
    
    /**
     * Verifica si existen datos de regreso para itinerarios.
     * @param ir Clase itinerariosRequest.
     * @return Clase itinerariosResponse.
     */
    public boolean ItinerariosRegreso(itinerariosRequest ir){
        if(ir.getDatosRegreso()!=null && (ir.getDatosRegreso().getNumCorrida()!=null && !ir.getDatosRegreso().getNumCorrida().equals(""))) return true;
        return false;
    }

    /**
     * Verifica si existen datos de regreso para asientos.
     * @param ar Clase asientosRequest.
     * @return Clase asientosResponse.
     */
    public boolean AsientosRegreso(asientosRequest ar){
        if(ar.getDatosRegreso()!=null && (ar.getDatosRegreso().getNumCorrida()!=null && !ar.getDatosRegreso().getNumCorrida().equals(""))) return true;
        return false;
    }
    
    /**
     * Verifica si existen datos de regreso para reservacion.
     * @param rr Clase reservacionRequest.
     * @return Clase reservacionRequest.
     */
    public boolean ReservacionRegreso(reservacionRequest rr){
        if(rr.getDatosRegreso()!=null && (rr.getDatosRegreso().getNumCorrida()!=null && !rr.getDatosRegreso().getNumCorrida().equals(""))) return true;
        return false;
    }
    
    /**
     * Verifica si existen datos de regreso para confirmar reservacion.
     * @param crr Clase confirmaReservacionRequest.
     * @return Clase confirmaReservacionResponse.
     */
    public boolean ComfirmaReservacionRegreso(confirmaReservacionRequest crr){
        //System.out.println("crr "+crr.getDatosRegreso());
        if(crr.getDatosRegreso()!=null && (crr.getDatosRegreso().getFolioReservacionNegocio()!=null && !crr.getDatosRegreso().getFolioReservacionNegocio().equals(""))) return true;
        return false;
    }
    
    /**
     * Verifica si existen datos de regreso para cancelar reservacion.
     * @param cnr Clase cancelacionRequest.
     * @return Clase cancelacionResponse.
     */
    public boolean CancelaReservacionRegreso(cancelacionRequest cnr){
        //if(cnr.getDatosRegreso()!=null && cnr.getDatosRegreso().getAsientosSeleccionados()!=null) return true;
        return false;
    }
    
     /**
     * Verifica si existen datos de regreso para cancelar boletos.
     * @param crr Clase confirmaReservacionRequest.
     * @return Clase confirmaReservacionResponse.
     */
    public boolean cancelacionBoletosnRegreso(cancelacionBoletosRequest crr){
        System.out.println("Datos Regreso crr "+crr.getDatosRegreso());
        if(crr.getDatosRegreso()!=null && (crr.getDatosRegreso().getNumCorrida()!=null && !crr.getDatosRegreso().getNumCorrida().equals("")) && crr.getViajeRedondo().equals("TRUE")) return true;
        return false;
    }
}
