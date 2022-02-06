/*
 * sesionVenta.java
 *
 * Created on 16 de febrero de 2010, 01:23 PM 
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsacumularestrellas;

import java.awt.Color;
import java.util.Vector;
import soliciutdAcumularEstrellas.TMSAcumularEstrellasFacadeRemote;
import subProcesosAcum.PcInfo;
import wsLealtad.TMSSocioIntimoWS;
import wsLealtad.TMSSocioIntimoWSService;

/**
 *
 * @author vgonzalez
 */
public class sesionVenta {
    private TMSAcumularEstrellasFacadeRemote tmsVtaFacade; 
    private Terminal terminalVenta;
    private Terminal terminalLocal;
    private long usuarioId =-1;
    private boolean permiteMotoLealtad = true;
    private String empresId = "";
    private String servicioId = "";
    private String origenId = "";
    private String destinoId = "";
    private String empresaNombre = "";
    private String dbgSetUrl;
    private String Bs_User;
    private String Bs_Pwd;
    private String Bs_Company;
    private String Bs_Branch;
    private  String Bs_Country;
    private  String Cc_Type;
    private  String Cc_TypeAMEX;
    private String Tx_MerchantP;
    private String Tx_MerchantS;
    private String Tx_MerchantAMEX;
    private String Tx_MerchantAMEXMOTO;
    private  String Tx_OperationTypeP;
    private  String Tx_OperationTypeS;
    private  String Tx_Currency;
    private String usuarioNum;
    private String usuarioLealtad = "";
    private String passwordLealtad ="";
    private String terminalLealtad= "";
    private TMSSocioIntimoWSService service;
    private TMSSocioIntimoWS port;
    private JDlgAceptar DialogoAceptar = new JDlgAceptar();
    private Object[][] boletosLealtad;
    private PcInfo estaCaja;
    private String caja="";
    private String cajaId="";
    private String tiposPagoValidos ="";
    private boolean usarWSLelatad = true;
    private boolean activoEMVFull = false;
    private boolean lectorSocioIntimo = false;
    
    
    /** Creates a new instance of sesionVenta */
    public sesionVenta(TMSAcumularEstrellasFacadeRemote ptmsVtaFacade ) {
        this.setTmsVtaFacade(ptmsVtaFacade);
        dbgSetUrl= "0";//"https://ssl.e-pago.com.mx"; //"https://qa.mitec.com.mx"; "https://dev.mitec.com.mx";
        Bs_Company="0";//"0072" //"0670";
        Bs_Country="MEX";
        Cc_Type="V/MC";
        Cc_TypeAMEX="AMEX";
        Bs_Branch = "0";
        Bs_Pwd = "";
        Bs_User = "";
        Tx_MerchantP="0";
        Tx_MerchantS="0";
        Tx_MerchantAMEX="0";
        Tx_OperationTypeP="11";
        Tx_OperationTypeS="12";
        Tx_Currency="MXN";
        estaCaja = new PcInfo();
        setCaja(estaCaja.getHostName().toUpperCase());
    }

    
    public void setParametros(Vector vector){
        //System.out.println("Vector parametros: "+vector);
        this.setBs_Branch(vector.get(0).toString());
        this.setTx_MerchantP(vector.get(1).toString());
        this.setTx_MerchantAMEX(vector.get(2).toString());
        this.setTx_MerchantAMEXMOTO(vector.get(3).toString());
        this.setTx_MerchantS(vector.get(4).toString());
        this.setBs_Pwd(vector.get(5).toString());
        this.setDbgSetUrl(vector.get(6).toString());
        this.setBs_User(vector.get(7).toString());
        this.setTerminalLealtad(vector.get(8).toString());
        this.setPasswordLealtad(vector.get(9).toString());
        this.setUsuarioLealtad(vector.get(10).toString());
        this.setActivoEMVFull(this.tmsVtaFacade.buscaParametroCajaLealtad(""+this.getCajaId(), "PVTA_EMVFULLACTIVO").equals("S")?true:false);
        this.setLectorSocioIntimo(this.tmsVtaFacade.buscaParametroCajaLealtad(""+this.getCajaId(), "PVTA_LECTARSOCINT").equals("S")?true:false);

    } 
    
    public wsLealtad.TMSSocioIntimoWS getWSPort(){
            try { // Call Web Service Operation
                service = new wsLealtad.TMSSocioIntimoWSService();
                port = service.getTMSSocioIntimoWSPort();// getTMSLealtadWSPort();
            } catch (Exception ex) {
                System.out.println("Excepcion al crear el WS");
                ex.printStackTrace();
                    DialogoAceptar.mostrarDialogo("Eror de conexion WS","<html>¡No existe una conexión con el WS de Lealtad!.<br>       Contacte al administrador del sistema.</html>",Color.RED);
            }
        return this.port;
    }
    
    public void detenerWS(){
                service = null;
                port = null;
    }      
    
    public void insertaRegistroLealtad(int index, String producto, String num_Tarjeta, String referencia, String unidad_negocio,String tipo,String fecha){
        Object[][] lb = this.getBoletosLealtad();
        if(this.getTerminalVenta().getNombre().equals(this.getTerminalLocal().getNombre()))
            this.getTmsVtaFacade().insertaRegistroLealtad("-1",((tipo.equals("R"))?"000000" :lb[index][19].toString() ),producto,this.getTerminalLealtad(), tipo,num_Tarjeta,referencia,this.getUsuarioLealtad(), this.getPasswordLealtad(), lb[index][5].toString(), lb[index][2].toString(),this.getCaja(),unidad_negocio,"0", this.getUsuarioId(),"","",fecha,""+this.getTerminalLocal().getId() );
        else
            this.getTmsVtaFacade().insertaRegistroLealtad("-1",((tipo.equals("R"))?"000000" :lb[index][19].toString() ),producto,this.getTerminalLealtad(), tipo,num_Tarjeta,referencia,this.getUsuarioLealtad(), this.getPasswordLealtad(), lb[index][5].toString(), lb[index][2].toString(),this.getCaja(),unidad_negocio,"0", this.getUsuarioId(),"",this.getTerminalVenta().getDbLink(),fecha, ""+this.getTerminalVenta().getId());
//            this.getTmsVtaFacade().insertaRegistroLealtad("-1",((tipo.equals("R"))?"000000" :lb[index][19].toString() ),producto,this.getTerminalLealtad(), tipo,num_Tarjeta,referencia,this.getUsuarioLealtad(), this.getPasswordLealtad(), lb[index][5].toString(), lb[index][2].toString(),this.getCaja(),unidad_negocio,"0", this.getUsuarioId(),this.getTerminalVenta().getEsquema(),this.getTerminalVenta().getDbLink());
    }      
    
    public String getFechaHoraSistemaVentaLealtad(){ return this.getTmsVtaFacade()._ObtieneFechaHoraBDLealtad(); }    
    
    public TMSAcumularEstrellasFacadeRemote getTmsVtaFacade() {
        return tmsVtaFacade;
    }    

    public void setTmsVtaFacade(TMSAcumularEstrellasFacadeRemote tmsVtaFacade) {
        this.tmsVtaFacade = tmsVtaFacade;
    }

     public boolean isPermiteMotoLealtad() {
        return permiteMotoLealtad;
    }

    public void setPermiteMotoLealtad(boolean permiteMotoLealtad) {
        this.permiteMotoLealtad = permiteMotoLealtad;
    }

    public String getEmpresId() {
        return empresId;
    }

    public void setEmpresId(String empresId) {
        this.empresId = empresId;
    }

    public String getServicioId() {
        return servicioId;
    }

    public void setServicioId(String servicioId) {
        this.servicioId = servicioId;
    }

    public String getOrigenId() {
        return origenId;
    }

    public void setOrigenId(String origenId) {
        this.origenId = origenId;
    }

    public String getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(String destinoId) {
        this.destinoId = destinoId;
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    public String getDbgSetUrl() {
        return dbgSetUrl;
    }

    public void setDbgSetUrl(String a) {
        this.dbgSetUrl = a;
        if(this.dbgSetUrl.contains("dev.")) setBs_Company("0072");
        else setBs_Company("0670");
        //this.dbgSetUrl = dbgSetUrl;
    }

    public String getBs_User() {
        return Bs_User;
    }

    public void setBs_User(String Bs_User) {
        this.Bs_User = Bs_User;
    }

    public String getBs_Pwd() {
        return Bs_Pwd;
    }

    public void setBs_Pwd(String Bs_Pwd) {
        this.Bs_Pwd = Bs_Pwd;
    }

    public String getBs_Company() {
        return Bs_Company;
    }

    public void setBs_Company(String Bs_Company) {
        this.Bs_Company = Bs_Company;
    }

    public String getBs_Branch() {
        return Bs_Branch;
    }

    public void setBs_Branch(String Bs_Branch) {
        this.Bs_Branch = Bs_Branch;
    }

    public String getBs_Country() {
        return Bs_Country;
    }

    public void setBs_Country(String Bs_Country) {
        this.Bs_Country = Bs_Country;
    }

    public String getCc_Type() {
        return Cc_Type;
    }

    public void setCc_Type(String Cc_Type) {
        this.Cc_Type = Cc_Type;
    }

    public String getCc_TypeAMEX() {
        return Cc_TypeAMEX;
    }

    public void setCc_TypeAMEX(String Cc_TypeAMEX) {
        this.Cc_TypeAMEX = Cc_TypeAMEX;
    }

    public String getTx_MerchantP() {
        return Tx_MerchantP;
    }

    public void setTx_MerchantP(String Tx_MerchantP) {
        this.Tx_MerchantP = Tx_MerchantP;
    }

    public String getTx_MerchantS() {
        return Tx_MerchantS;
    }

    public void setTx_MerchantS(String Tx_MerchantS) {
        this.Tx_MerchantS = Tx_MerchantS;
    }

    public String getTx_MerchantAMEX() {
        return Tx_MerchantAMEX;
    }

    public void setTx_MerchantAMEX(String Tx_MerchantAMEX) {
        this.Tx_MerchantAMEX = Tx_MerchantAMEX;
    }

    public String getTx_MerchantAMEXMOTO() {
        return Tx_MerchantAMEXMOTO;
    }

    public void setTx_MerchantAMEXMOTO(String Tx_MerchantAMEXMOTO) {
        this.Tx_MerchantAMEXMOTO = Tx_MerchantAMEXMOTO;
    }

    public String getTx_OperationTypeP() {
        return Tx_OperationTypeP;
    }

    public void setTx_OperationTypeP(String Tx_OperationTypeP) {
        this.Tx_OperationTypeP = Tx_OperationTypeP;
    }

    public String getTx_OperationTypeS() {
        return Tx_OperationTypeS;
    }

    public void setTx_OperationTypeS(String Tx_OperationTypeS) {
        this.Tx_OperationTypeS = Tx_OperationTypeS;
    }

    public String getTx_Currency() {
        return Tx_Currency;
    }

    public void setTx_Currency(String Tx_Currency) {
        this.Tx_Currency = Tx_Currency;
    }

    public String getUsuarioNum() {
        return usuarioNum;
    }

    public void setUsuarioNum(String usuarioNum) {
        this.usuarioNum = usuarioNum;
    }

    public String getUsuarioLealtad() {
        return usuarioLealtad;
    }

    public void setUsuarioLealtad(String usuarioLealtad) {
        this.usuarioLealtad = usuarioLealtad;
    }

    public String getPasswordLealtad() {
        return passwordLealtad;
    }

    public void setPasswordLealtad(String passwordLealtad) {
        this.passwordLealtad = passwordLealtad;
    }

    public String getTerminalLealtad() {
        return terminalLealtad;
    }

    public void setTerminalLealtad(String terminalLealtad) {
        this.terminalLealtad = terminalLealtad;
    }

  

    public Object[][] getBoletosLealtad() {
        return this.boletosLealtad;
    }
    
    public void setBoletosLealtad(Object[][] bol) {
       this.boletosLealtad = bol;
    }    

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getTiposPagoValidos() {
        return tiposPagoValidos;
    }

    public void setTiposPagoValidos(String tiposPagoValidos) {
        this.tiposPagoValidos = tiposPagoValidos;
    }

    public Terminal getTerminalVenta() {
        return terminalVenta;
    }

    public void setTerminalVenta(Terminal terminalVenta) {
        this.terminalVenta = terminalVenta;
    }

    public Terminal getTerminalLocal() {
        return terminalLocal;
    }

    public void setTerminalLocal(Terminal terminalLocal) {
        this.terminalLocal = terminalLocal;
    }

    public String getCajaId() {
        return cajaId;
    }

    public void setCajaId(String cajaId) {
        this.cajaId = cajaId;
    }

    public boolean isUsarWSLelatad() {
        return usarWSLelatad;
    }

    public void setUsarWSLelatad(boolean usarWSLelatad) {
        this.usarWSLelatad = usarWSLelatad;
    }

   /**
     * @return the activoEMVFull
     */
    public boolean isActivoEMVFull() {
        return activoEMVFull;
    }

    /**
     * @param activoEMVFull the activoEMVFull to set
     */
    public void setActivoEMVFull(boolean activoEMVFull) {
        this.activoEMVFull = activoEMVFull;
    }

    /**
     * @return the lectorSocioIntimo
     */
    public boolean isLectorSocioIntimo() {
        return lectorSocioIntimo;
    }

    /**
     * @param lectorSocioIntimo the lectorSocioIntimo to set
     */
    public void setLectorSocioIntimo(boolean lectorSocioIntimo) {
        this.lectorSocioIntimo = lectorSocioIntimo;
    }

}
