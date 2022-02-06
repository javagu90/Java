package tms_venta;

import java.util.Date;
import java.util.Vector;

/**
 * Los Objetos de esta Clase contienen los parametros necesarios e informacion 
 * referente al inicio de sesion, informacion de la pc, donde se tiene la coneccion 
 * estos valores son utilizados para la actualizacion de folios y informacion del taquillero
 * Nombre de la empresa y metodos de acceso a esta clase
 * @author IvanE. Morales
 * @version 1.01 01 Mar 2007
 */

public class JCls_Sesion_Usuario {
    private String ipAS;
    private int portAS;
    private String nombreEsquemaPropio;
    private double ServicioTarifaSencillo;
    private double ServicioTarifaRedondo;
//Datos de la sesion
    private long corteId;
//Datos del usuario en la sesion
    private long usuarioId;
    private String usuarioNum;
    private String usuarioNom;
//datos iniciales y de boletaje boolean datosinicio;
    private String[][] folioPreimpreso;
    private String[][] empresasOfertantes;
    private String[][] empresasConFolios;
    private Object[][] empresasImpresora;
    private String EmpresaPrincipal;
    private int indiceEmpresaPrincipal;

    private String Prefijo;
    private String terminalNombre;
    private String terminalPrefijoId;
    private String terminalPrefijoIdRem;
    private long terminalId;
    private long CajaUbicacionId;
    private long CajaId;
    private String CajaMAC;
    private String Caja;
    private String CajaNumero;
    private double dAvisoRecoleccion;
    private double dLimVenta;
    private double fondoMax;
    private String funcionUsuario;
    private boolean FolioUnico;
    private boolean digitaTB;
    private boolean sysCobroBancario;
    /*******/
    private int diaBab;
    private int menCor;
    private int senCor;
    private int estCor;
    private int proCor;
    private int corCor;
    private int osrCor;
    private int dispMenCor;
    private int dispSenCor;
    private int dispEstCor;
    private int dispProCor;
    private int dispCorCor;
    private int dispOsrCor;
    private int bolNvtImp;
    private int asTope;
    private int minAntRv;
    private boolean snAsiento;
    private int minDesCan;
    private Date iniVac;
    private Date finVac;
    private boolean perVac;
    private double mtomintjt;
    private String AutorizadoPorNumeroUsuario;
    private long AutorizadoPorIdUsuario;
    private String listaServiciosVenta;
    private long sesionId;
    private boolean aplicacionVenta;
    private String monedaTX;
    private String monedaTS;
    private double tipoCambioDiv;
    private String strTipoCambioDiv;
    private String msgImpVOU;
    private int digitosCupon;
    private String impresoraEP;
    
    private String dbgSetUrl;
    private String Bs_User;
    private String Bs_Pwd;
    private String Bs_Company;
    private String Bs_Branch;
    protected final String Bs_Country;
    protected final String Cc_Type;
    protected final String Cc_TypeAMEX;
    private String Tx_MerchantP;
    private String Tx_MerchantS;
    private String Tx_MerchantAMEX;
    private String Tx_MerchantAMEXMOTO;
    protected final String Tx_OperationTypeP;
    protected final String Tx_OperationTypeS;
    protected final String Tx_Currency;
    /**
     * Constructor que inicializa los Objetos de la clase
     */
    public JCls_Sesion_Usuario(long usuarioId, String usuarioNumero, String usuarioNombre, long pSesionId, String pMenuNombre) {
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
        setServicioTarifaSencillo(0);
        setServicioTarifaRedondo(0);
        setListaRutasNoVenta("-1");
        setEmpresaPrincipal("Estrella Roja");
        setIndiceEmpresaPrincipal(0);
        setAutorizadoPorIdUsuario(141);
        setAutorizadoPorNumeroUsuario("9000");
        setUsuarioId(usuarioId);
        setUsuarioNum(usuarioNumero);
        setUsuarioNom(usuarioNombre);
        setSesionId(pSesionId);
        setAplicacionVenta(pMenuNombre.contains("CENTER")?false:true);
        setFolioUnico(true);
        setFolioPreimpreso(null);
        setPrefijo("AA");
        setTerminalNombre("");
        setTerminalPrefijoId("");
        setTerminalPrefijoIdRem("");
        setTerminalId(-1);
        setCajaUbicacionId(-1);
        setCajaId(-1);
        setCajaMAC("00-00");
        setCaja("");
        setAvisoRecoleccion(0.0);
        setLimVenta(0.0);
        setFondoMax(0.0);
        setCorteId(184);      
        setFuncionUsuario("");
        setDiasValBab(365);
        setMenCor(0);
        setSenCor(0);
        setEstCor(0);
        setProCor(0);
        setCorCor(0);
        setOsrCor(0);
        setDispMenCor(0);
        setDispSenCor(0);
        setDispEstCor(0);
        setDispProCor(0);
        setDispCorCor(0);
        setDispOsrCor(0);
        setBolNvtImp(5);
        setAsTope(10);
        setMinAntRv(60);
        setSnAsiento(true);
        setMinDesCan(120);
        setIniVac(null);
        setFinVac(null);
        setPerVac(false);
        setMtoMinTjt(0.0);
        setDigitaTB(false);
        setSysCobroBancario(true);
        setMonedaTX("MXP");
        setMonedaTS("$");
        setTipoCambioDiv(10);
        setStrTipoCambioDiv("10");
    }
    
    public void setServicioTarifaSencillo(double ServicioTarifa){
        System.out.println("entra   setServicioTarifaSencillo: "+ServicioTarifa);
        this.ServicioTarifaSencillo = ServicioTarifa;
    }
    
    public double getServicioTarifaSencillo(){
        return this.ServicioTarifaSencillo;
    }
    
    public void setServicioTarifaRedondo(double ServicioTarifa){
        System.out.println("entra setServicioTarifaRedondo: "+ServicioTarifa);
        this.ServicioTarifaRedondo = ServicioTarifa;
    }
    
    public double getServicioTarifaRedondo(){
        return this.ServicioTarifaRedondo;
    }
    
    public void setEmpresaPrincipal(String EmpresaPrincipal){
        this.EmpresaPrincipal = EmpresaPrincipal;
    }
    
    public String getEmpresaPrincipal(){
        return this.EmpresaPrincipal;
    }
    
    public void setIndiceEmpresaPrincipal(int indiceEmpresaPrincipal){
        this.indiceEmpresaPrincipal = indiceEmpresaPrincipal;
    }
    
    public int getIndiceEmpresaPrincipal(){
        return this.indiceEmpresaPrincipal;
    }
    
    public long getCorteId() {
        return corteId;
    }
    
    /**
     * Metodo que enlaza el valor del Dato en el Objeto
     * @param sesionId Objeto que Contiene el nuevo valor para el dato
     */
    public void setCorteId(long corteId) {
        this.corteId = corteId;
    }
    
    /**
     * Metodo que devuelve el valor del Dato en el Objeto
     * @return Objeto que regresa el valor del dato del objeto
     */
    public long getUsuarioId() {
        return usuarioId;
    }
    
    /**
     * Metodo que enlaza el valor del Dato en el Objeto
     * @param usuarioId Objeto que Contiene el nuevo valor para el dato
     */
    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public long getAutorizadoPorIdUsuario() {
        return this.AutorizadoPorIdUsuario;
    }
    
    /**
     * Metodo que enlaza el valor del Dato en el Objeto
     * @param usuarioId Objeto que Contiene el nuevo valor para el dato
     */
    public void setAutorizadoPorIdUsuario(long supervisorId) {
        this.AutorizadoPorIdUsuario = supervisorId;
    }
    
    public String getAutorizadoPorNumeroUsuario() {
        return this.AutorizadoPorNumeroUsuario;
    }
    
    /**
     * Metodo que enlaza el valor del Dato en el Objeto
     * @param usuarioId Objeto que Contiene el nuevo valor para el dato
     */
    public void setAutorizadoPorNumeroUsuario(String supervisorNumero) {
        this.AutorizadoPorNumeroUsuario = supervisorNumero;
    }
    
    /**
     * Metodo que devuelve el valor del Dato en el Objeto
     * @return Objeto que regresa el valor del dato del objeto
     */
    public String getUsuarioNum() {
        return usuarioNum;
    }
    
    /**
     * Metodo que enlaza el valor del Dato en el Objeto
     * @param usuarionum Objeto que Contiene el nuevo valor para el dato
     */
    public void setUsuarioNum(String usuarionum) {
        this.usuarioNum = usuarionum;
    }
    
    /**
     * Metodo que devuelve el valor del Dato en el Objeto
     * @return Objeto que regresa el valor del dato del objeto
     */
    public String getUsuarioNom() {
        return usuarioNom;
    }
    
    /**
     * Metodo que enlaza el valor del Dato en el Objeto
     * @param usuarioNom Objeto que Contiene el nuevo valor para el dato
     */
    public void setUsuarioNom(String usuarioNom) {
        this.usuarioNom = usuarioNom;
    }

    /**
     * Metodo que devuelve el valor del Dato en el Objeto
     * @return Objeto que regresa el valor del dato del objeto
     */
    public String[][] getFolioPreimpreso() {
        return this.folioPreimpreso;
    }
    
    /**
     * Metodo que enlaza el valor del Dato en el Objeto
     * @param boletajeInicial Objeto que Contiene el nuevo valor para el dato
     */
    public void setFolioPreimpreso(String[][] folioPreimpreso) {
        this.folioPreimpreso = folioPreimpreso;
    }

    public String getPrefijo() {
        return Prefijo;
    }
    
    public void setPrefijo(String prefijo) {
        this.Prefijo = prefijo;
    }
    
    public void setCajaId(long pCajaId){
        this.CajaId=pCajaId;
    }
    
    public long getCajaId(){
        return this.CajaId;
    }
    
    public void setCajaUbicacionId(long pCajaUbicacionId){
        this.CajaUbicacionId=pCajaUbicacionId;
    }
    
    public long getCajaUbicacionId(){
        return this.CajaUbicacionId;
    }
    
    public void setCajaNumero(String pCajaNumero){
        this.CajaNumero=pCajaNumero;
    }
    
    public String getCajaNumero(){
        return this.CajaNumero;
    }
    
    public void setTerminalPrefijoId(String pTerminalPrefijoId){
        this.terminalPrefijoId=pTerminalPrefijoId;
    }
    
    public String getTerminalPrefijoId(){
        return this.terminalPrefijoId;
    }
    
    public void setTerminalPrefijoIdRem(String pTerminalPrefijoIdRem){
        this.terminalPrefijoIdRem=pTerminalPrefijoIdRem;
    }
    
    public String getTerminalPrefijoIdRem(){
        return this.terminalPrefijoIdRem;
    }
    
    public void setTerminalNombre(String pTerminalNombre){
        this.terminalNombre=pTerminalNombre;
    }
        
    public String getTerminalNombre(){
        return this.terminalNombre;
    }
    
    public double getAvisoRecoleccion() {
        return dAvisoRecoleccion;
    }
    
    public void setAvisoRecoleccion(double pAvisoRecoleccion) {
        this.dAvisoRecoleccion = pAvisoRecoleccion;
    }
    
    public double getLimVenta() {
        return dLimVenta;
    }
    
    public void setLimVenta(double pLimVenta) {
        this.dLimVenta = pLimVenta;
    }
    
    public String getFuncionUsuario() {
        return funcionUsuario;
    }
    
    public void setFuncionUsuario(String funcionUsuario) {
        this.funcionUsuario = funcionUsuario;
    }

    public int getDiasValBab() {
        return diaBab;
    }
    
    public void setDiasValBab(int diaBab) {
        this.diaBab = diaBab;
    }
    
    public int getMenCor(){ return this.menCor; }
    
    public int getSenCor(){ return this.senCor; }
    
    public int getEstCor(){ return this.estCor; }
    
    public int getProCor(){ return this.proCor; }
    
    public int getCorCor(){ return this.corCor; }
    
    public int getOsrCor(){ return this.osrCor; }
    
    public void setMenCor(int MenCor){ this.menCor = MenCor; }
    
    public void setSenCor(int SenCor){ this.senCor = SenCor; }
    
    public void setEstCor(int EstCor){ this.estCor = EstCor; }
    
    public void setProCor(int ProCor){ this.proCor=ProCor; }
    
    public void setCorCor(int CorCor){ this.corCor=CorCor; }
    
    public void setOsrCor(int OsrCor){ this.osrCor=OsrCor; }
    
    public void setBolNvtImp(int bolNvtImp){ this.bolNvtImp=bolNvtImp; }
    
    public void setAsTope(int asTope){ this.asTope=asTope; }
    
    public void setMinAntRv(int minAntRv){ this.minAntRv=minAntRv; }
    
    public void setSnAsiento(boolean SnAsiento){ this.snAsiento=SnAsiento; }
    
    public void setMinDesCan(int minDesCan){ this.minDesCan=minDesCan; }
    
    public int getBolNvtImp(){ return bolNvtImp; }
    
    public int getAsTope(){ return asTope; }
    
    public int getMinAntRv(){ return minAntRv; }
    
    public boolean getSnAsiento(){ return snAsiento; }
    
    public int getMinDesCan(){ return minDesCan; }
    
    public Date getIniVac(){ return iniVac; }
    
    public Date getFinVac(){ return finVac; }
    
    public void setIniVac(Date IniVac){ this.iniVac=IniVac; }
    
    public void setFinVac(Date FinVac){ this.finVac=FinVac; }
    
    public boolean getPerVac(){ return perVac; }
    
    public void setPerVac(boolean PerVac){ this.perVac=PerVac; }
    
    public void setMtoMinTjt(double dval){ this.mtomintjt = dval; }
    
    public double getMtoMinTjt(){ return this.mtomintjt; }
    
    public void setDigitaTB(boolean dtb){ this.digitaTB = dtb; }
    
    public boolean getDigitaTB(){ return this.digitaTB; }
    
    public void setSysCobroBancario(boolean scb){ this.sysCobroBancario = scb; }
    
    public boolean getSysCobroBancario(){ return this.sysCobroBancario; }
    
    public double getFondoMax(){ return this.fondoMax; }
    
    public void setFondoMax(double pFondoMaximo){ this.fondoMax = pFondoMaximo; }
    
    public void setFolioUnico(boolean pFolioUnico){ this.FolioUnico = pFolioUnico; }
    
    public boolean getFolioUnico(){ return this.FolioUnico; }
    
    public String getCaja() {
        return this.Caja;
    }
    
    public void setCaja(String pCaja) {
        this.Caja = pCaja;
    }
    
    public String getCajaMAC() {
        return this.CajaMAC;
    }
    
    public void setCajaMAC(String pCajaMAC) {
        this.CajaMAC = pCajaMAC;
    }
    
    public void setListaRutasNoVenta(String lsv){
        if(lsv==null || lsv.equals("")){
            this.listaServiciosVenta="-1";
            return;
        }
        String[] xs = lsv.split(",");
        listaServiciosVenta=xs[0];
        for(int i=1; i<xs.length; i++)
            listaServiciosVenta = listaServiciosVenta + ","+xs[i];
    }
    
    public String getListaRutasNoVenta(){ return this.listaServiciosVenta; }
    
    public long getSesionId() {
        return this.sesionId;
    }
    
    /**
     * Metodo que enlaza el valor del Dato en el Objeto
     * @param usuarioId Objeto que Contiene el nuevo valor para el dato
     */
    public void setSesionId(long pSesionId) {
        this.sesionId = pSesionId;
    }
    
    public int getDispMenCor(){ return this.dispMenCor; }
    
    public int getDispSenCor(){ return this.dispSenCor; }
    
    public int getDispEstCor(){ return this.dispEstCor; }
    
    public int getDispProCor(){ return this.dispProCor; }
    
    public int getDispCorCor(){ return this.dispCorCor; }
    
    public int getDispOsrCor(){ return this.dispOsrCor; }
    
    public void setDispMenCor(int MenCor){ this.dispMenCor = MenCor; }
    
    public void setDispSenCor(int SenCor){ this.dispSenCor = SenCor; }
    
    public void setDispEstCor(int EstCor){ this.dispEstCor = EstCor; }
    
    public void setDispProCor(int ProCor){ this.dispProCor=ProCor; }
    
    public void setDispCorCor(int CorCor){ this.dispCorCor=CorCor; }
    
    public void setDispOsrCor(int OsrCor){ this.dispOsrCor=OsrCor; }

    public void setTerminalId(long i) {
        this.terminalId = i;
    }
    
    public long getTerminalId() {
        return this.terminalId;
    }

    public String getNombreEsquemaPropio() {
        return this.nombreEsquemaPropio;
    }

    public void setNombreEsquemaPropio(String nombreEsquemaPropio) {
        this.nombreEsquemaPropio = nombreEsquemaPropio;
    }
    
    public void setIpAS(String pIpAS){ this.ipAS = pIpAS; }
    
    public void setPortAS(String pPortAS){ this.portAS = Integer.valueOf(pPortAS); }
    
    public String getIpAS(){ return this.ipAS; }
    
    public int getPortAS(){ return this.portAS; }
    
    public void setMonedaTX(String string) {
        this.monedaTX = string;
        setMonedaTS(this.monedaTX.equals("MXP") ? "$" : this.monedaTX);
    }
    
    public String getMonedaTX() { return this.monedaTX; }
    
    private void setMonedaTS(String string) { this.monedaTS = string; }
    
    public String getMonedaTS() { return this.monedaTS; }

    public double getTipoCambioDiv() { return tipoCambioDiv; }

    public void setTipoCambioDiv(double tipoCambioDiv) { this.tipoCambioDiv = tipoCambioDiv; }
    
    public String getStrTipoCambioDiv() { return strTipoCambioDiv; }

    public void setStrTipoCambioDiv(String pTipoCambioDiv) {
        this.strTipoCambioDiv = pTipoCambioDiv;
        setTipoCambioDiv(Double.valueOf(this.strTipoCambioDiv));
    }

    private void setAplicacionVenta(boolean b) { this.aplicacionVenta = b; }
    
    public boolean getAplicacionVenta() { return this.aplicacionVenta; }
    
    public String[][] getEmpresasOfertantes() { return empresasOfertantes; }
    
    public void setEmpresasOfertantes(String[][] empresas) { this.empresasOfertantes = empresas;
    for(int i=0; i<this.empresasOfertantes.length; i++)
    {
        System.out.println("empresas["+i+"][0]"+this.empresasOfertantes[i][0]);
        System.out.println("empresas["+i+"][1]"+this.empresasOfertantes[i][1]);
        System.out.println("empresas["+i+"][2]"+this.empresasOfertantes[i][2]);
    }
    }
    
    public String[][] getEmpresasConFolios() { return empresasConFolios; }
    
    public void setEmpresasConFolios(String[][] empresas) { this.empresasConFolios = empresas; }
    
    public Object[][] getEmpresasImpresora() { return empresasImpresora; }
    
    public void setEmpresasImpresora(Object[][] empresas) {
        this.empresasImpresora = empresas;
        int i, j;
        int cont=0;
        boolean siEP=false;
        boolean si=false;
        
        for(i=0; i<this.empresasImpresora.length; i++)
            if(this.empresasImpresora[i][3].toString().equals("VOUCHERS") && this.empresasImpresora[i][0].toString().equals(getEmpresaPrincipal())){
                if(this.empresasImpresora[i][2].toString().equals("RED")) this.empresasImpresora[i][6]=this.empresasImpresora[i][1].toString();
                else  this.empresasImpresora[i][6]=this.empresasImpresora[i][2].toString();
                this.empresasImpresora[i][7]="S";
                setImpresoraEP(this.empresasImpresora[i][6].toString());
                System.out.println("Empresa principal tiene impresora de voucher: "+this.empresasImpresora[i][2].toString()+" - "+getImpresoraEP());
                siEP=true;
            }
        
        for(i=0; i<this.empresasImpresora.length; i++)
            if(this.empresasImpresora[i][3].toString().equals("VOUCHERS")){
                if(this.empresasImpresora[i][2].toString().equals("RED")) this.empresasImpresora[i][6]=this.empresasImpresora[i][1].toString();
                else  this.empresasImpresora[i][6]=this.empresasImpresora[i][2].toString();
                this.empresasImpresora[i][7]="S";
                if(!siEP) setImpresoraEP(this.empresasImpresora[i][6].toString());
                
                System.out.println("ImpVou: "+this.empresasImpresora[i][2].toString()+" - "+this.empresasImpresora[i][6].toString()+" ---EP: "+getImpresoraEP());
                si=true;
            }
        if(siEP || si) setMsgImpVOU("");
        else setMsgImpVOU("NO HAY");
    }
    
    public String getMsgImpVOU() { return msgImpVOU; }
    
    public void setMsgImpVOU(String MsgImpVOU) { this.msgImpVOU = MsgImpVOU; }

    public int getDigitosCupon() {
        return digitosCupon;
    }

    public void setDigitosCupon(int digitosCupon) {
        this.digitosCupon = digitosCupon;
    }

    public String getImpresoraEP() {
        return this.impresoraEP;
    }

    public void setImpresoraEP(String impresora) {
        this.impresoraEP = impresora;
    }

    public String getDbgSetUrl() {
        return dbgSetUrl;
    }
    
    public void setDbgSetUrl(String a) {
        this.dbgSetUrl = a;
        if(this.dbgSetUrl.contains("dev.")) setBs_Company("0072");
        else setBs_Company("0670");
    }

    public String getBs_User() {
        return Bs_User;
    }

    public String getBs_Pwd() {
        return Bs_Pwd;
    }

    public String getBs_Company() {
        return Bs_Company;
    }
    
    public void setBs_Company(String a) {
        this.Bs_Company = a;
    }

    public String getBs_Branch() {
        return Bs_Branch;
    }
    
    public void setBs_User(String a) {
        this.Bs_User = a;
    }

    public void setBs_Pwd(String a) {
        this.Bs_Pwd = a;
    }

    public void setBs_Branch(String a) {
        this.Bs_Branch = a;
    }

    public String getBs_Country() {
        return Bs_Country;
    }

    public String getCc_Type() {
        return Cc_Type;
    }

    public String getTx_OperationTypeP() {
        return Tx_OperationTypeP;
    }

    public String getTx_OperationTypeS() {
        return Tx_OperationTypeS;
    }

    public String getTx_Currency() {
        return Tx_Currency;
    }

    public String getCc_TypeAMEX() {
        return Cc_TypeAMEX;
    }
    
    public void setTx_MerchantP(String a) {
        this.Tx_MerchantP = a;
        //setTx_MerchantS(this.Tx_MerchantP);
    }

    public void setTx_MerchantAMEXMOTO(String a) {
        this.Tx_MerchantAMEXMOTO = a;
    }
    
    public String getTx_MerchantP() {
        return Tx_MerchantP;
    }
    
    public void setTx_MerchantS(String a) {
        this.Tx_MerchantS = a;
    }

    public String getTx_MerchantS() {
        return Tx_MerchantS;
    }
    
    public void setTx_MerchantAMEX(String a) {
        this.Tx_MerchantAMEX = a;
    }

    public String getTx_MerchantAMEX() {
        return Tx_MerchantAMEX;
    }

    public String getTx_MerchantAMEXMOTO() {
        return Tx_MerchantAMEXMOTO;
    }

}