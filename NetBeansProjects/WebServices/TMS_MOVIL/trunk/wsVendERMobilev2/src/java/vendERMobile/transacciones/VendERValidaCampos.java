/*
 * VendERValidaCampos.java
 *
 * Created on 4 de septiembre de 2009, 12:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.transacciones;

import vendERMobile.clases.abrirCerrarVentaAbordo.AbrirCerrarVentaAbordoRequest;
import vendERMobile.clases.asientos.AsientosRequest;
import vendERMobile.clases.cancelarBoletos.CancelarBoletosRequest;
import vendERMobile.clases.canjeBoletoAbierto.CanjeBoletoAbiertoRequest;
import vendERMobile.clases.corridas.CorridasRequest;
import vendERMobile.clases.datos.AsientoAutobus;
import vendERMobile.clases.itinerarios.ItinerariosRequest;
import vendERMobile.clases.itinerariosruta.ItinerariosPorRutaRequest;
import vendERMobile.clases.logIn.LoginReq;
import vendERMobile.clases.ocuparAsientos.OcuparAsientosRequest;
import vendERMobile.clases.permisos.PermisosRequest;
import vendERMobile.clases.tiposNoPermitidos.TipoNoPermitidosRequest;
import vendERMobile.clases.transferirBoletos.TransferirBoletosRequest;
import vendERMobile.clases.venderBoletos.VenderBoletosRequest;

/**
 *
 * @author asolis
 */
public class VendERValidaCampos {
    
    /**
     * Creates a new instance of VendERValidaCampos
     */
    public VendERValidaCampos() {}
    
    public String solicitudLogin(LoginReq lr){
        /*
         estaciontrabajo, numUsuario, contrasenia, uid
         **/
        if(lr.getSesion().getNumUsuario()==null || lr.getSesion().getNumUsuario().equals("")) return "numUsuario";
        if(lr.getSesion().getContrasenia()==null || lr.getSesion().getContrasenia().equals("")) return "contrasenia";
        if(lr.getSesion().getIdSucursal()==null || lr.getSesion().getIdSucursal().equals("")) return "Mac (sucursal)";
        if(lr.getSesion().getIdEstacionTrabajo()==null || lr.getSesion().getIdEstacionTrabajo().equals("")) return "idEstacionTrabajo";
        return null;
    }
    
    public String solicitudPermisos(PermisosRequest pr){        
        if(pr.getUid()== null || pr.getUid().equals("")) return "uid";
        if(!(pr.getNumUsuario()== null || pr.getNumUsuario().equals(""))&&((pr.getContrasenia() == null)|| pr.getContrasenia().equals(""))) return "contrasenia";
        if((pr.getNumUsuario()== null || pr.getNumUsuario().equals(""))&&!((pr.getContrasenia() == null)|| pr.getContrasenia().equals(""))) return "numUusario";
        return null;
    }
    
    public String solicitudParametrosIniciales(String uid){        
        if(uid== null || uid.equals("")) return "uid";       
        return null;
    }
    
    public String solicitudTarifas(String uid, int ruta){        
        if(uid== null || uid.equals("")) return "uid";
        if((String.valueOf(ruta).length() == 0) && (ruta <= 0)) return "IdRuta";
        return null;
    }
    
    public String solicitudRecoleccion(String uid, String pSupervisorNumero, String pSupervisorContrasenia, String pMontoReco, String pCtdMonto, String formaPago, String formaPagoId){
        if(uid== null || uid.equals("")) return "uid";
        if(pMontoReco== null || pMontoReco.equals("")) return "importeRecoleccion";
        if(Float.parseFloat(pMontoReco) <= 0) return "importeRecoleccion";
        if(pCtdMonto== null || pCtdMonto.equals("")) return "cantidadRecoleccion";
        if(Integer.parseInt(pCtdMonto) <= 0) return "cantidadRecoleccion";
        if(formaPagoId== null || formaPagoId.equals("")) return "idTipoPago";        
        if(formaPago== null || formaPago.equals("")) return "tipoPagoNombre";           
        return null;
    }
            
    public String solicitudCorridas(CorridasRequest cr){
        /*
         uid, numUsuario, corteId, servicio, origen, destino,
         fechaSalida, horarioSalida, fechaRegreso, horarioRegreso
         **/
        if(cr.getUid()==null || cr.getUid().equals("")) return "uid";
        //if(cr.getSesion().getNumUsuario()==null || cr.getSesion().getNumUsuario().equals("")) return "numUsuario";
        //if(cr.getSesion().getIdSesion()==null || cr.getSesion().getIdSesion().equals("")) return "idSesion";        
        //if(cr.getTipoServicio()==null || cr.getTipoServicio().getClave()==null) return "tipoServicio.Clave";
        if(cr.getOrigen()==null || cr.getOrigen().getClave()==null || cr.getOrigen().getClave().equals("")) return "Origen.Clave";
        if(cr.getDestino()==null || cr.getDestino().getClave()==null || cr.getDestino().getClave().equals("")) return "Destino.Clave";
        if(cr.getFechaSalida()==null || cr.getFechaSalida().equals("")) return "fechaSalida";
        //if(cr.getHorarioSalida() == null || cr.getHorarioSalida().equals("null")) return "horarioSalida";
        return null;
    }
   
    public String solicitudItinerarios(ItinerariosRequest ir){
        if(ir.getUid()==null || ir.getUid().equals("")) return "uid";        
        if(ir.getDatosIda().getRutaId() == null || ir.getDatosIda().getRutaId().equals("null")) return "DatosIda.claveCorrida";
        if(ir.getDatosIda().getOrigen().getClave() == null || ir.getDatosIda().getOrigen().getClave().equals("null")) return "DatosIda.claveCorrida.Origen.Clave";
        return null;
    }
    
    public String solicitudItinerarios(ItinerariosPorRutaRequest ir){
        if(ir.getUid()==null || ir.getUid().equals("")) return "uid";        
        //if(ir.getRutaId() == null ||ir.getRutaId().equals("")) return "rutaId";                
        return null;
    }
    
    public String solicitudAsientos(AsientosRequest ar){
        if(ar.getUid()==null || ar.getUid().equals("")) return "uid";        
        if(ar.getCorrida().getClaveCorrida() == null || ar.getCorrida().getClaveCorrida().equals("null")) return "claveCorrida";
        if(ar.getCorrida().getPlantilla() == null || ar.getCorrida().getPlantilla().equals("null")) return "Plantilla Id";
        if(ar.getCorrida().getOrigen().getClave() == null || ar.getCorrida().getOrigen().getClave().equals("null")) return "Plantilla Id";
        return null;
    }
    
    public String solicitudOcuparAsientos(OcuparAsientosRequest or){
        /*String uid, String claveCorrida, String mapaAsientos, String mapaAsientosPasajero, String Origen, String numeroUsuario*/
        if(or.getUid()==null || or.getUid().equals("")) return "uid";        
        if(or.getCorridas().getClaveCorrida() == null || or.getCorridas().getClaveCorrida().equals("null")) return "claveCorrida";
        if(or.getMapaAsientosOcupados() == null || or.getMapaAsientosOcupados().equals("null")) return "MapaAsientosOcupados";
        if(or.getMapaAsientosPasajero() == null || or.getMapaAsientosPasajero().equals("null")) return "MapaAsientosPasajero";
        if(or.getCorridas().getOrigen() == null || or.getCorridas().getOrigen().equals("null")) return "Origen";
        if(or.getCorridas().getOrigen().getClave() == null || or.getCorridas().getOrigen().getClave().equals("null")) return "Origen";
        if(or.getModoOcupacion() == null || or.getModoOcupacion().equals("null") || (!or.getModoOcupacion().toUpperCase().equals("L") && !or.getModoOcupacion().toUpperCase().equals("O")) ) return "ModoOcupacion";
        return null;
    }
    
    public String solicitudVenderBoleto(VenderBoletosRequest vbr, boolean bolAbierto){       
        if(vbr.getUid()==null || vbr.getUid().equals("")) return "uid";        
        if(!bolAbierto) {            
            if(vbr.getCorridas().getClaveCorrida() == null || vbr.getCorridas().getClaveCorrida().equals("null")) return "Corridas.claveCorrida";            
            if(vbr.getCorridas().getOrigen().getClave() == null || vbr.getCorridas().getOrigen().getClave().equals("null")) return "Corridas.Origen";
            if(vbr.getCorridas().getDestino().getClave() == null || vbr.getCorridas().getDestino().getClave().equals("null")) return "Corridas.Destino";
            if(vbr.getCorridas().getTipoServicio().getNombre() == null || vbr.getCorridas().getTipoServicio().getNombre().equals("null")) return "Corridas.ServicioNombre";
            if(vbr.getCorridas().getEmpresa().getNombre() == null || vbr.getCorridas().getEmpresa().getNombre().equals("null")) return "Corridas.EmpresaNombre";               
        }else{
            if(vbr.getOrigen().getClave()== null || vbr.getOrigen().getClave().equals("null")) return "Origen";
            if(vbr.getDestino().getClave()== null || vbr.getDestino().getClave().equals("null")) return "Destino";
            if(vbr.getTipoServicio().getNombre()== null || vbr.getTipoServicio().getNombre().equals("null")) return "ServicioNombre";
            if(vbr.getEmpresaNombre().getNombre() == null || vbr.getEmpresaNombre().getNombre().equals("null")) return "EmpresaNombre";        
        }
        if(vbr.getSesion().getIdEstacionTrabajo() == null || vbr.getSesion().getIdEstacionTrabajo().equals("null")) return "IdEstacionTrabajo";        
        if(vbr.getSesion().getIdSesion() == null || vbr.getSesion().getIdSesion().equals("null")) return "IdSesion";
        if(vbr.getSesion().getNumUsuario() == null || vbr.getSesion().getNumUsuario().equals("null")) return "numeroUsuario";
        for(int k = 0; k < vbr.getAsientosSeleccionados().length; k++) {
            if(vbr.getAsientosSeleccionados()[k].getNombreOcupante() == null || vbr.getAsientosSeleccionados()[k].getNombreOcupante().equals("null")) return "NombreOcupante";
            if(vbr.getAsientosSeleccionados()[k].getTipoPasajero() == null || vbr.getAsientosSeleccionados()[k].getTipoPasajero().equals("null")) return "TipoPasajero";
            if(vbr.getAsientosSeleccionados()[k].getImporteBoleto() <= 0.0)return "ImporteBoleto"; 
            if(!bolAbierto) 
                if((String.valueOf(vbr.getAsientosSeleccionados()[k].getNumeroAsiento()) == null) ||(Integer.parseInt(vbr.getAsientosSeleccionados()[k].getNumeroAsiento()) <= 0)) return "numeroAsiento";            
        }
        if(vbr.getTiposPago().getTipoPagoClave() == null || vbr.getTiposPago().getTipoPagoClave().equals("null")) return "tipoPago";        
        return null;
    }
    
    public String solicitudCancelarBoleto(CancelarBoletosRequest cbr){       
        if(cbr.getUid()==null || cbr.getUid().equals("")) return "uid";        
        if(cbr.getSesion().getIdEstacionTrabajo() == null || cbr.getSesion().getIdEstacionTrabajo().equals("null")) return "IdEstacionTrabajo";
        if(cbr.getSesion().getIdSesion() == null || cbr.getSesion().getIdSesion().equals("null")) return "IdSesion";
        //if(cbr.getSesion().getNumUsuario() == null || cbr.getSesion().getNumUsuario().equals("null")) return "numeroUsuario";
        for(int k = 0; k < cbr.getAsientosSeleccionados().length; k++) {
            if(cbr.getAsientosSeleccionados()[k].getFolioPreImpresoBoleto() == null || cbr.getAsientosSeleccionados()[k].getFolioPreImpresoBoleto().equals("null")) return "NombreOcupante";
          //  if(cbr.getAsientosSeleccionados()[k].getTipoPasajero() == null || cbr.getAsientosSeleccionados()[k].getTipoPasajero().equals("null")) return "TipoPasajero";
           // if(cbr.getAsientosSeleccionados()[k].getImporteBoleto() <= 0.0)return "ImporteBoleto";            
        }
        return null;
    }
    
    public String solicitudTransferirBoleto(TransferirBoletosRequest tbr){              
        if(tbr.getUid()==null || tbr.getUid().equals("")) return "uid";        
        if(tbr.getCorridasDestino() == null) return "corridaDestino";
            if(tbr.getCorridasDestino().getClaveCorrida() == null || tbr.getCorridasDestino().getClaveCorrida().equals("null")) return "Corridas.claveCorrida";
            if(tbr.getCorridasDestino().getOrigen().getClave() == null || tbr.getCorridasDestino().getOrigen().getClave().equals("null")) return "Corridas.Origen";
            if(tbr.getCorridasDestino().getDestino().getClave() == null || tbr.getCorridasDestino().getDestino().getClave().equals("null")) return "Corridas.Destino";
            if(tbr.getCorridasDestino().getTipoServicio().getNombre() == null || tbr.getCorridasDestino().getTipoServicio().getNombre().equals("null")) return "Corridas.ServicioNombre";
            if(tbr.getCorridasDestino().getEmpresa().getNombre() == null || tbr.getCorridasDestino().getEmpresa().getNombre().equals("null")) return "Corridas.EmpresaNombre";                        
        if(tbr.getSesion().getIdEstacionTrabajo() == null || tbr.getSesion().getIdEstacionTrabajo().equals("null")) return "IdEstacionTrabajo";
        if(tbr.getSesion().getIdSesion() == null || tbr.getSesion().getIdSesion().equals("null")) return "IdSesion";
        if(tbr.getSesion().getNumUsuario() == null || tbr.getSesion().getNumUsuario().equals("null")) return "numeroUsuario";
        if(tbr.getAsientosSeleccionadosOrigen() == null) return "AsientosSeleccionadosOrigen";
        for(int k = 0; k < tbr.getAsientosSeleccionadosOrigen().length; k++) {
            if(tbr.getAsientosSeleccionadosOrigen()[k].getFolioPreImpresoBoleto() == null || tbr.getAsientosSeleccionadosOrigen()[k].getFolioPreImpresoBoleto().equals("null")) return "FolioPreImpresoBoleto";
            //if(tbr.getAsientosSeleccionadosOrigen()[k].getTipoPasajero() == null || tbr.getAsientosSeleccionadosOrigen()[k].getTipoPasajero().equals("null")) return "TipoPasajero";
            //if(tbr.getAsientosSeleccionadosOrigen()[k].getImporteBoleto() <= 0.0)return "ImporteBoleto";             
            if((tbr.getAsientosSeleccionadosOrigen()[k].getNumeroAsiento() == null) ||(Integer.parseInt(tbr.getAsientosSeleccionadosOrigen()[k].getNumeroAsiento()) <= 0)) return "numeroAsiento";            
        }
        if(tbr.getAsientosSeleccionadosDestino() == null) return "AsientosSeleccionadosDestino";
        for(int k = 0; k < tbr.getAsientosSeleccionadosDestino().length; k++) {
            if(tbr.getAsientosSeleccionadosDestino()[k].getNombreOcupante() == null || tbr.getAsientosSeleccionadosDestino()[k].getNombreOcupante().equals("null")) return "NombreOcupante";
            if(tbr.getAsientosSeleccionadosDestino()[k].getTipoPasajero() == null || tbr.getAsientosSeleccionadosDestino()[k].getTipoPasajero().equals("null")) return "TipoPasajero";
            if(tbr.getAsientosSeleccionadosDestino()[k].getImporteBoleto() <= 0.0)return "ImporteBoleto";             
            if((tbr.getAsientosSeleccionadosDestino()[k].getNumeroAsiento() == null) ||(Integer.parseInt(tbr.getAsientosSeleccionadosDestino()[k].getNumeroAsiento()) <= 0)) return "numeroAsiento";            
        }
        return null;
    }
    
    public String solicitudCanjearBoletoAbierto(CanjeBoletoAbiertoRequest tbr){              
        if(tbr.getUid()==null || tbr.getUid().equals("")) return "uid";        
        if(tbr.getCorridasDestino() == null) return "corridaDestino";
            if(tbr.getCorridasDestino().getClaveCorrida() == null || tbr.getCorridasDestino().getClaveCorrida().equals("null")) return "Corridas.claveCorrida";
            if(tbr.getCorridasDestino().getOrigen().getClave() == null || tbr.getCorridasDestino().getOrigen().getClave().equals("null")) return "Corridas.Origen";
            if(tbr.getCorridasDestino().getDestino().getClave() == null || tbr.getCorridasDestino().getDestino().getClave().equals("null")) return "Corridas.Destino";
            if(tbr.getCorridasDestino().getTipoServicio().getNombre() == null || tbr.getCorridasDestino().getTipoServicio().getNombre().equals("null")) return "Corridas.ServicioNombre";
            if(tbr.getCorridasDestino().getEmpresa().getNombre() == null || tbr.getCorridasDestino().getEmpresa().getNombre().equals("null")) return "Corridas.EmpresaNombre";                        
        if(tbr.getSesion().getIdEstacionTrabajo() == null || tbr.getSesion().getIdEstacionTrabajo().equals("null")) return "IdEstacionTrabajo";
        if(tbr.getSesion().getIdSesion() == null || tbr.getSesion().getIdSesion().equals("null")) return "IdSesion";
        if(tbr.getSesion().getNumUsuario() == null || tbr.getSesion().getNumUsuario().equals("null")) return "numeroUsuario";
        if(tbr.getAsientosSeleccionadosOrigen() == null) return "AsientosSeleccionadosOrigen";
        for(int k = 0; k < tbr.getAsientosSeleccionadosOrigen().length; k++) {
            if(tbr.getAsientosSeleccionadosOrigen()[k].getFolioPreImpresoBoleto() == null || tbr.getAsientosSeleccionadosOrigen()[k].getFolioPreImpresoBoleto().equals("null")) return "FolioPreImpresoBoleto()";
            //if(tbr.getAsientosSeleccionadosOrigen()[k].getTipoPasajero() == null || tbr.getAsientosSeleccionadosOrigen()[k].getTipoPasajero().equals("null")) return "TipoPasajero";
            //if(tbr.getAsientosSeleccionadosOrigen()[k].getImporteBoleto() <= 0.0)return "ImporteBoleto";                         
        }
        if(tbr.getAsientosSeleccionadosDestino() == null) return "AsientosSeleccionadosDestino";
        for(int k = 0; k < tbr.getAsientosSeleccionadosDestino().length; k++) {
            if(tbr.getAsientosSeleccionadosDestino()[k].getNombreOcupante() == null || tbr.getAsientosSeleccionadosDestino()[k].getNombreOcupante().equals("null")) return "NombreOcupante";
            if(tbr.getAsientosSeleccionadosDestino()[k].getTipoPasajero() == null || tbr.getAsientosSeleccionadosDestino()[k].getTipoPasajero().equals("null")) return "TipoPasajero";
            if(tbr.getAsientosSeleccionadosDestino()[k].getImporteBoleto() <= 0.0)return "ImporteBoleto";             
            if((tbr.getAsientosSeleccionadosDestino()[k].getNumeroAsiento() == null) ||(Integer.parseInt(tbr.getAsientosSeleccionadosDestino()[k].getNumeroAsiento()) <= 0)) return "numeroAsiento";            
        }
        return null;
    }
    
    public String solicitudAbrirCerrarVentaAbordo(AbrirCerrarVentaAbordoRequest abr){       
        if(abr.getUid()==null || abr.getUid().equals("")) return "uid";        
        if(abr.getNumAutobus() == null || abr.getNumAutobus().equals("null")) return "NumAutobus";
        if(abr.getClaveModo() == null || abr.getClaveModo().equals("null")) return "ClaveModo";
        if(abr.getClaveOperador() == null || abr.getClaveOperador().equals("null")) return "ClaveOperador";
        if(abr.getFolioTarjetaViaje() == null || abr.getFolioTarjetaViaje().equals("null")) return "FolioTarjetaViaje";        
        return null;
    }
    
    public String solicitudTiposNoPermitidos(TipoNoPermitidosRequest tnpr){       
        if(tnpr.getUid()==null || tnpr.getUid().equals("")) return "uid";        
        if(tnpr.getOrigen().getId() <= 0 ) return "Origen Id";
        if(tnpr.getCorridaId() == null || tnpr.getCorridaId().equals("null")) return "CorridaId";       
        return null;
    }
}
