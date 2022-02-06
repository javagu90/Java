package tms_venta;

import AsignacionVenta.jDlgAutorizaSupervisor;
import AsignacionVenta.jDlgDatosIniciales;  
import AsignacionVenta.jDlgRefoliar;
import DialogosX.JDlgAceptar;
import TMSVtaProductosER.entidad.ProductoCarrito;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream; 
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.ejb.EJBException;
import javax.swing.JLabel;
import subProcesos.PcInfo;
import subProcesos.RelojVisualVta;
import subProcesos.buscarDireccionMAC;
import tms_encriptacion.EncriptarMD5;
import tms_venta.entidad.TmsAuditoriaTbl;
import tms_venta.entidad.TmsBDConfigTbl;
import tms_venta.entidad.TmsBoletosVentaTbl;
import tms_venta.entidad.TmsEstadosV;
import tms_venta.entidad.TmsVtaRvnV;
import tms_venta.solicitud.TmsVtaFacadeRemote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;
import tms_venta.entidad.TmsAutobusPlantLineasTbl;
import tms_venta.entidad.TmsAutobusPlantillasEncTbl;
import tms_venta.entidad.TmsComponenteBusTbl;
import tms_venta.entidad.TmsReservacionesTbl;
import tms_venta.entidad.TmsTipopagosV;
import tms_venta.entidad.TmsTiposPasajeroTbl;
import tms_venta.util.NoBloqueoFoliosException;
import tms_vta_productos_er.cantidad_a_letras;
import util.Promocion;
import xer_emv_dll.rc4;

public class SesionVenta {
   public final int tTempos=13;
   public int iTempos=0;
   public String TipoBoleto;
   public String strCiudadOrigen;
   public float msLocal;
   public float msRemoto;
   private String[] _strBoletos=null;
   private String _strBoleto=null;
   private String[] strCnxReal;
   private List<TmsBoletosVentaTbl> _BoletosVenta=null;
   private Vector _empresasRegistranVenta=null;
   private Vector _folioBol=null;
   public String strDestinoX;
   public String edoCorte;
   public boolean modoCorte;
   private long idUltFolVend;
   private long idRecol;
   private long idInises;
   private long idFondini;
   private long idFolini;
   private long idFolfin;
   private long idRefolini;
   private long idRefolfin;
   private long idFinses;
   private long idSesionActividad;
   private javax.swing.JLabel etiqueta=null;
   public boolean infoFresca=false;
   private long ultimoBoletoId=0;
   private long PLANTILLA_DEFAULT;
   public String OrigenConexionReal;
   public String prefijoConexionReal;
   public double EFECTIVO_CAJA=0;
   private SimpleDateFormat formatoDateServer = new SimpleDateFormat("yyyy-MM-dd HH:mm");
   private List<TmsTiposPasajeroTbl> tmsTiposPasajeroTbl;
   private List<TmsTiposPasajeroTbl> tiposPasajeLealtad;
   private List<TmsComponenteBusTbl> componentes;
   private List<TmsAutobusPlantillasEncTbl> encabezados;
   private List<TmsAutobusPlantLineasTbl> lineas;
   private Vector xxx;
   private List<TmsBDConfigTbl> tmsBaseDatosConfigTbl;
   private Vector vOrigenes;
   private Object[] OrigenesDBLink;
   private Vector OrigenDBLink;
   private Vector vServicios;
   private Vector vS;
   private Vector vSReal;
   private Vector VectorServicios;
   private Vector vDestinos;
   private Vector vEstados;
   private List<TmsTipopagosV> formasPago = null;
    
   private JCls_Sesion_Usuario userCon = null;
   private long[] folioActual;

   private Vector tps = null;
   private String DBLink = "";
   private String DBLinkAuxiliar = "";
   private String esquema = "";
   private String tipoTransaccion;
   private String tipoTransaccionAuxiliar;
   
   private TmsVtaFacadeRemote tmsVtaFacade;
   private Context context=null;
                                
   private int RestaSen=0;
   private int RestaEst=0;
   private int RestaPro=0;
   private int RestaCor=0;

   private SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
   private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
   private SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
   private SimpleDateFormat formatoDebugFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
   public SimpleDateFormat formatoFechaHoraBD = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
   private Object[][] objFormasPago_BA=null;
   private Object[][] objFormasPagoBA=null;
   
   private double ImporteVtaActual;
   private long ReservacionId;
   private long BoletoRelacionadoId;
   
   private long boletoIdCancelado;
   private long boletoIdCanceladoRem;
   private TmsBoletosVentaTbl tmsBoletosVentaTbl=null;
   private List<TmsBoletosVentaTbl> variosTmsBoletosVentaTbl=null;
   
    // PARAMETROS GLOBALES E IMPRESORAS
    private Vector codigos = null;
    private Vector valores = null;
    
    private PcInfo estaCaja;
    private Vector vectorBDconfig;
    private String estadoCorte;
    private Date[] sesionFecha;
    private boolean nuevaSesionSinFondoInicial;
    private boolean cargarSinDatoInicial;
    private boolean esNuevaSesion;
    private jDlgDatosIniciales dlgDatosIni;
    private String[][] foliosSesion;
    private String[][] FuncionesUsuario = null;
    private TmsAuditoriaTbl aplicaDatosAuditoria = null;
    private JDlgAceptar DialogoAceptar = new JDlgAceptar();
    private List<TmsEstadosV> tmsEstadosV;
    private Vector tramos;
    private Vector tramos1;
    private Vector tarifas;
    private Vector tarifas1;
    private Date fechaHoraSistemaVenta;
    private long folioSistema;
    private RelojVisualVta thX=null;
    
    private final int es_ESPERA = -2;
    private final int es_SALIDA_INMEDIATA = -1;
    private final int es_NORMAL = 0;
    private int ESTADO_DEL_SISTEMA = es_NORMAL;
    public static boolean b_decode_data;
    private boolean CorridaCerrada;
    private String DBLinkX;
    private Date dtFechaDespachoTarjeta;
    /*
     * VARIABLES PARA CADENA DE ASIENTOS Y TIPOS_PASAJE
     */
    private String CadenaAsientos;
    private String CadenaTiposPasaje;

    private String valorTipo;
    
    public String rback_numOperacion;
    public String rback_numAutorizacion;
    
    public String rback_numCuenta;
    public String rback_nombre;
    public String rback_fecha;
    public String rback_monto;
    public String rback_nombreTx="CANCELACION";
    public String rback_tipoTx="002";
    public String rback_terminalCaja;
    public String rback_aprobacion;
    public String rback_mt;
    public String rback_usuarioNum;
    public String rback_usuarioNombre;
    public String rback_KS;
    public String rback_vRequest;
    public String rback_vIP;
    public int rback_vPort;
    public int rback_vSeconds;
    
    public Vector vRTP=new Vector();
    public Vector vCRTP=new Vector();
    public String cadTipoPasaje;   
    // transaccion en cobro
    public final String ctxVENTA="VT";
    public final String ctxCHORARIO="HO";
    public final String ctxRESERVACION="RV";
    public final String ctxVENTA_RESERVACION="VR";
    public final String ctxVENTA_BA="VA";
    public final String ctxCANJE_BA="AC";
    public final String ctxVENTA_BR="Vr";
    private String vtaRef="";
    private String nombreAutorizado = "";
    private String referenciaOriginal = "";
    private String referenciaCanjeBA = "";
    private boolean CambHorRer = false;

    private String qry = "";
    private String curDir;
    public File TextFile;
    public FileWriter TextOut;    
    private Vector ocurrioError = new Vector();
    private List<TmsBoletosVentaTbl> boletoACanjear = null;
    private String referenciadoId = "";
    private Object[][] BoletosLealtad;
    private wsLealtad.TMSSocioIntimoWSService service;//TMSLealtadWSService service;
    private wsLealtad.TMSSocioIntimoWS port; 
    private String usuarioLealtad = "";
    private String passwordLealtad =""; 
    private String terminalLealtad= "";
    private String empresaLealtad = "";
    private String empresaRedencion= "";
    private String tiposPagoLealtad = "";
    //private boolean cobroMultiple = false;
    private boolean permiteMotoLealtad = false;
    private String numeroTarjetaSocio =""; 
  
    private boolean banderaWS = false;
    private Vector foliosBoletos;
    private long terminalLealtadIdO;
    private long terminalLealtadIdD;
    private String terminalLealtadNombreO;

    private boolean usarWSLelatad = true;
    private boolean impVouComercio = false;
    private boolean activoEMVFull = false;
    private boolean lectorSocioIntimo = false;
    private rc4 enc;
    private List<Promocion> rutasConPromocionVigente = new ArrayList<Promocion>();
    private boolean rutaConPromocion = false;
    private boolean solicitaCP = false;
    private Vector agenciasViaje = null;
    private BigDecimal clienteCreId;
    private BigDecimal clienteSEDENAId;
    private String CP = "";
    private String tipoTDC="";
    private Vector DatosInicales;
    private List<ProductoCarrito> productosCarrito = new ArrayList<ProductoCarrito>();
    private boolean ventaJuconi = false;
    private Vector nombresServicios=null;
    private Vector impresionNombreBoleto=null;
    private boolean bolRedCer = false;
    private String dblinkRedIda;
    private String origenCorridaRedIda;
    private String origenCorridaRedReg;
    private String origenRedIda;
    private String origenRedReg;
    private boolean vtaBolBco = false;
    private boolean cobroCargo = false;
    private String tipoPagoCargo = "";
    private String aprobacionCargo = "";
    private String productoCARGOHOClave="CARGOHO";
    private long productoCARGOHOId=-1;
    private String productoCARGOHONombre="";
    private String productoCARGOHOEmpresa="";
    private String productoCARGOCNClave="CARGOCN";
    private long productoCARGOCNId=-1;
    private String productoCARGOCNNombre="";
    private String productoCARGOCNEmpresa="";
    private Vector rutasConPromocionRedondoTTP;
    private boolean voucherBlanco =false;
    private String impresoraNombreVoucherBlanco ="";



    
    public SesionVenta(JCls_Sesion_Usuario userConection){
        this.setUserCon(userConection);
        try {
            if(!abreSocketAS()){
                DialogoAceptar.mostrarDialogo("¡No existe una conexión válida con la Base de Datos(AS)!","Contacte al administrador del sistema.",Color.RED);
                this._SetEstadoSistema(es_SALIDA_INMEDIATA);
                return;
            }
            enc = new rc4();
            System.out.println("+++++++++ Inicia"+new Date() +" getInitialContext +++++++++");
            context = getInitialContext();
            System.out.println("--------- Termina"+new Date() +" getInitialContext ---------");
	    tmsVtaFacade = (TmsVtaFacadeRemote)context.lookup("tms_venta.solicitud.TmsVtaFacadeRemote");
            setUsarWSLelatad(tmsVtaFacade.usarWSLealtad());
            System.out.println("usar WS de Lealtad: "+this.isUsarWSLelatad());
            System.out.println("Intenta crear la instancia del WS");
         if(isUsarWSLelatad())
         {
            try { // Call Web Service Operation
                service = new wsLealtad.TMSSocioIntimoWSService();
                port = service.getTMSSocioIntimoWSPort();
                 System.out.println("Instancia del WS creada");
                // TODO initialize WS operation arguments here
//                java.lang.String numeroOperacion = "";
//                // TODO process result here
//                wsLealtad.OperacionesResponse result = port.getOperacion(numeroOperacion);
//                result.getStatus().isSuccess();
//                System.out.println("Result = "+result);
            } catch (Exception ex) {
                System.out.println("Excepcion al crear el WS");
                //ex.printStackTrace();
                DialogoAceptar.mostrarDialogo("Error de conexion WS","<html>¡No existe una conexión con el WS de Lealtad!.<br>       Contacte al administrador del sistema.</html>",Color.RED);
                // TODO handle custom exceptions here
            }
        }
            System.out.println("Despues de crear la instancia del WS");
            
            this._SetEstadoSistema(es_NORMAL);
//            tmsVtaFacade.setCaja(this.userCon.getCaja());
            CorridaCerrada = false;
            //VAGL 19/12/2008
            curDir = System.getProperty("user.home");
            System.out.println("Ruta Relativa: "+curDir);
            TextFile = new File(curDir+"\\LogVenta"+tmsVtaFacade.getMesAnioBD()+".log");
            ////////////////////
            
        } catch (Exception ex) {
            ex.printStackTrace();
            DialogoAceptar.mostrarDialogo("¡No existe una conexión válida con la Base de Datos.!","Contacte al administrador del sistema.",Color.RED);
            this._SetEstadoSistema(es_SALIDA_INMEDIATA);
        }
    }
    
    
    public wsLealtad.TMSSocioIntimoWS getWSPort(){
//            try { // Call Web Service Operation
//                service = new wsLealtad.TMSSocioIntimoWSService();
//                port = service.getTMSSocioIntimoWSPort();// getTMSLealtadWSPort();
//                banderaWS= false;
//            } catch (Exception ex) {
//                System.out.println("Excepcion al crear el WS");
//                ex.printStackTrace();
//                if(!banderaWS)
//                {
//                    DialogoAceptar.mostrarDialogo("Error de conexion WS","<html>¡No existe una conexión con el WS de Lealtad!.<br>       Contacte al administrador del sistema.</html>",Color.RED);
//                    banderaWS= true;
//                }
//                // TODO handle custom exceptions here
//            }
    return this.port;
    }
    
   
    public void detenerWS(){
                //service = null;
                //port = null;
    }    
    
    
    public void reiniciarLookup(){
            System.out.println("verifica si ocurrio un error: "+ocurrioError);
            //if(ocurrioError.size()==0)
            //    return;
            //context = null;
            tmsVtaFacade = null;
        try {
            //context = getInitialContext();
            tmsVtaFacade= (TmsVtaFacadeRemote)context.lookup("tms_venta.solicitud.TmsVtaFacadeRemote");
            
            ocurrioError= new Vector();
            System.out.println("termino de reinicial el lookup...");
        } catch (NamingException ex) {
            //ocurrioError = true;
            System.out.println("Esta excepcion es en reiniciarLookup y no reinicia el vector ocurrioError...");
            ex.printStackTrace();
        }
	    
    }
    
    public void setFechaHoraBD(RelojVisualVta pThX){
        this.thX = pThX;
    }
    
    public void armaCadenaAsientos(Vector bv) {
        CadenaAsientos="";
        
        for(int i=0; i<bv.size(); i++)
            CadenaAsientos=CadenaAsientos+","+bv.get(i).toString();
    }
    
    public void armaCadenaTiposPasaje(Vector bv) {
        CadenaTiposPasaje="";
        
        for(int i=0; i<bv.size(); i++)
            CadenaTiposPasaje=CadenaTiposPasaje+","+bv.get(i).toString();
    }
    
    public String getCadenaAsientos(){ return this.CadenaAsientos; }
    
    public String getCadenaTiposPasaje(){ return this.CadenaTiposPasaje; }
    
    public int proceso(long MENU_ID){
        msLocal = (float)0.5;
        msRemoto = (float)0.7;
        estaCaja = new PcInfo();
        int error=0;
        error=FuncionesDeUsuario(MENU_ID);
        if(error!=0) return error;
        error=ConfigBD();
        if(error!=0) return error;
        System.out.println("  +++++++++ Inicia "+new Date()+" getParametrosIniciales+++++++++");
        error=getParametrosIniciales(getUserCon().getUsuarioId(),estaCaja.getHostName().toUpperCase(), getUserCon().getTerminalNombre());
        System.out.println("  --------- Termina"+new Date()+" getParametrosIniciales ---------");
        if(error!=0) return error;
        error=existeTerminal();
        if(error!=0) return error;
        System.out.println("  +++++++++ Inicia "+new Date()+" getParametrosIniciales2+++++++++");
        error=getParametrosIniciales2(getUserCon().getCaja());
        System.out.println("  --------- Termina"+new Date()+" getParametrosIniciales2 ---------");
        if(error!=0) return error;
        System.out.println("  +++++++++ Inicia "+new Date()+" getParametrosIniciales3+++++++++");
        error=getParametrosIniciales3(getUserCon().getCajaId());
        System.out.println("  --------- Termina"+new Date()+" getParametrosIniciales3 ---------");
        if(error!=0) return error;

        this.setVtaBolBco(tmsVtaFacade.buscaParametroCajaLealtad(""+this.userCon.getCajaId(), "P_VTA_BOL_BCO").equals("S")?true:false);
        System.out.println("Venta de Boleto Blanco : "+this.isVtaBolBco(""));

        System.out.println("  +++++++++ Inicia "+new Date()+" InicializarSesionVenta+++++++++");
        error=InicializarSesionVenta();
        System.out.println("  --------- Termina"+new Date()+" InicializarSesionVenta ---------");
        if(error!=0) return error;
        this.setTipoTransaccion("L");
        System.out.println("  +++++++++ Inicia "+new Date()+" setTipoTransaccion+++++++++");
        error=getTramosTarifas();
        System.out.println("  --------- Termina"+new Date()+" setTipoTransaccion ---------");
        if(error!=0) return error;
        System.out.println("  +++++++++ Inicia "+new Date()+" obtieneTmsEstadosV+++++++++");
        error=obtieneTmsEstadosV();
        System.out.println("  --------- Termina"+new Date()+" obtieneTmsEstadosV ---------");
        if(error!=0) return error;
        System.out.println("  +++++++++ Inicia "+new Date()+" Rutas+++++++++");
        error=Rutas();
        System.out.println("  --------- Termina"+new Date()+" Rutas ---------");
        if(error!=0) return error;
        System.out.println("  +++++++++ Inicia "+new Date()+" Plantillas+++++++++");
        error=Plantillas();
        System.out.println("  --------- Termina"+new Date()+" Plantillas ---------");
        if(error!=0) return error;
        System.out.println("  +++++++++ Inicia "+new Date()+" TiposPagos+++++++++");
        error=TiposPagos();
        System.out.println("  --------- Termina"+new Date()+" TiposPagos ---------");
        if(error!=0) return error;
        //error=queryTmsTiposPasajeroTblFindAble2();
        System.out.println("  +++++++++ Inicia "+new Date()+" queryTmsTiposPasajeroTblFindAll+++++++++");
        error=queryTmsTiposPasajeroTblFindAll();
        System.out.println("  --------- Termina"+new Date()+" queryTmsTiposPasajeroTblFindAll ---------");
        if(error!=0) return error;
        setImporteVenta(0);
        setReservacionId(0);
        setBoletoRelacionadoId(0);
        System.out.println("isAplicacionVenta: "+getUserCon().getAplicacionVenta());
        if(getUserCon().getAplicacionVenta()) this.FoliosActuales();
        System.out.println("EFC_"+EFECTIVO_CAJA);
        if(EFECTIVO_CAJA==0){
            EFECTIVO_CAJA+=getUserCon().getFondoMax();
            System.out.println("EFC0F_"+EFECTIVO_CAJA);
        }
            this.setUsuarioLealtad(tmsVtaFacade.buscaParametroLealtad(""+this.userCon.getTerminalId(), "P_SOCUSR"));
            this.setPasswordLealtad(tmsVtaFacade.buscaParametroLealtad(""+this.userCon.getTerminalId(), "P_SOCPWD"));
            this.setTerminalLealtad(tmsVtaFacade.buscaParametroLealtad(""+this.userCon.getTerminalId(), "P_SOCIDSUC"));
            this.setImpVouComercio(tmsVtaFacade.buscaParametroLealtad(""+this.userCon.getTerminalId(), "P_VTAIMPVOUCOMER").equals("S")?true:false);
            //this.setRutasConPromocionVigente(tmsVtaFacade.parametrosPorRutaPromocion());
            Vector v = tmsVtaFacade.parametrosPorRutaPromocion(this.userCon.getTerminalId());
            for(int i=0; i<v.size();i++)
            {
                Vector vr = (Vector)v.get(i);
                int index = -1;
                int count = 0;
                for(Promocion p : this.getRutasConPromocionVigente())
                {

                    if(p.getRutaId() == Long.valueOf(vr.get(1).toString()))
                    {
                        System.out.println("Encontro la ruta "+vr.get(1).toString()+" en el indice "+i);
                        index = count;
                        break;
                    }
                    count++;
                }
                if(index!=-1)
                    this.getRutasConPromocionVigente().get(index).setValor(vr.get(0).toString(),vr.get(2).toString());
                else
                {
                    Promocion promo =  new Promocion();
                    System.out.println("Agrega la ruta: "+(vr.get(1).toString()));
                    promo.setRutaId(Long.valueOf(vr.get(1).toString()));
                    promo.setValor(vr.get(0).toString(),vr.get(2).toString());
                    this.getRutasConPromocionVigente().add(promo);
                }
            }

            setRutasConPromocionRedondoTTP(tmsVtaFacade.rutasConPromocionRedondoTTP());
            if(getRutasConPromocionRedondoTTP()==null)
                setRutasConPromocionRedondoTTP(new Vector());
            System.out.println("rutasConPromocionRedondoTTP: "+ this.getRutasConPromocionRedondoTTP());
            //String res = tmsVtaFacade.buscaParametroLealtad(""+this.userCon.getTerminalId(), "P_VTAPGOSDIV");
            //this.setCobroMultiple((res.equals("") || res.equals("N")) ?false :true);
            String p = tmsVtaFacade.buscaParametroCajaLealtad(""+this.userCon.getCajaId(), "P_VMCARDLEALTAD");
            this.setActivoEMVFull(tmsVtaFacade.buscaParametroCajaLealtad(""+this.userCon.getCajaId(), "PVTA_EMVFULLACTIVO").equals("S")?true:false);
            this.setLectorSocioIntimo(tmsVtaFacade.buscaParametroCajaLealtad(""+this.userCon.getCajaId(), "PVTA_LECTARSOCINT").equals("S")?true:false);
            if(p.equals("") || p.equals("N"))
                this.setPermiteMotoLealtad(false);
            else
                this.setPermiteMotoLealtad(true);
            System.out.println("UsuarioLealtad: "+ this.getUsuarioLealtad());
            System.out.println("PasswordLealtad: "+ this.getPasswordLealtad());
            System.out.println("TerminalLealtad: "+ this.getTerminalLealtad());
            //System.out.println("CobroMultiple: "+ this.isCobroMultiple());
        this.empresaLealtad = tmsVtaFacade.buscaEmpresasLealtad();
        this.empresaRedencion = tmsVtaFacade.buscaEmpresasRedencion();
        this.tiposPagoLealtad = tmsVtaFacade.buscaTiposPagoLealtad();
        String res = tmsVtaFacade.obtener_valor_parametro_misc("P_VTA_CP_CTE", -1, -1, this.userCon.getTerminalId(), -1, -1);
        setAgenciasViaje(tmsVtaFacade.getAgenciasViaje());
        setClienteSEDENAId(tmsVtaFacade.getClienteSEDENA());
        setVentaJuconi(tmsVtaFacade.ventaJuconiActiva());
        String resp = tmsVtaFacade.getNombreServicios();
        this.setvEstados(tmsVtaFacade.getEstados());
        String[] sps = resp.split("\\|");
        System.out.println("Nombres Servicos: "+sps[0]);
        System.out.println("Impresion Nombre Servicos: "+sps[1]);
        String[] array = sps[0].split(",");
        Vector vec = new Vector();
        //Se agregan los nombres d elos servicios
        for(int i=0;i<array.length; i++)
            vec.add(array[i]);
        setNombresServicios(vec);
        //Se agregan los nombres impresos
        array = sps[1].split(",");
        vec = new Vector();
        for(int i=0;i<array.length; i++)
            vec.add(array[i]);
        setImpresionNombreBoleto(vec);
        this.solicitaCP = (res.equals("") || res.equals("N"))?false:true;
        System.out.println("empresaLealtad: "+empresaLealtad);
        System.out.println("empresaRedencion: "+empresaRedencion);
        System.out.println("tiposPagoLealtad: "+tiposPagoLealtad);
        System.out.println("Permite Moto en Lealtad: "+ this.isPermiteMotoLealtad());
        System.out.println("Rutas con Promocion Vigente: "+ this.getRutasConPromocionVigente());
        System.out.println("solicitaCP: "+solicitaCP);
        System.out.println("Agencias de Viaje: "+getAgenciasViaje());
        System.out.println("cliente SEDENA: "+this.clienteSEDENAId);
        System.out.println("Venta JUCONI Activa: "+this.ventaJuconi);
        Vector vecp =  tmsVtaFacade.getProductoCargo("CARGOHO");
        if(vecp!=null)
        {
            productoCARGOHOId=Long.valueOf(vecp.get(0).toString());
            productoCARGOHONombre=vecp.get(1).toString();
            productoCARGOHOEmpresa=vecp.get(2).toString();
        }
        vecp =  tmsVtaFacade.getProductoCargo("CARGOCN");
        if(vecp!=null)
        {
            productoCARGOCNId=Long.valueOf(vecp.get(0).toString());
            productoCARGOCNNombre=vecp.get(1).toString();
            productoCARGOCNEmpresa=vecp.get(2).toString();
        }
        System.out.println("productoCARGOHOId: "+this.productoCARGOHOId);
        System.out.println("productoCARGOHONombre: "+this.productoCARGOHONombre);
        System.out.println("productoCARGOHOEmpresa: "+this.productoCARGOHOEmpresa);
        System.out.println("productoCARGOCNId: "+this.productoCARGOCNId);
        System.out.println("productoCARGOCNNombre: "+this.productoCARGOCNNombre);
        System.out.println("productoCARGOCNEmpresa: "+this.productoCARGOCNEmpresa);

        if(this.clienteSEDENAId==null)
            DialogoAceptar.mostrarDialogo("¡Falta configuracion de Clientes!","El cliente SEDENA no esta dado de Alta. \nContacte al administrador del sistema",Color.RED);
        if(getAgenciasViaje().size()==1)
            DialogoAceptar.mostrarDialogo("¡Falta configuracion de Clientes!","Las Agencias de Viaje no estan dadas de Alta. \nContacte al administrador del sistema",Color.RED);

        return 0;
    }
    
    private int ConfigBD(){
        if(!abreSocketAS()) return -21;
        try{
            tmsBaseDatosConfigTbl = new ArrayList<TmsBDConfigTbl>(getTmsVtaFacade().queryTmsBaseDatosConfigTblAll());
        }catch(javax.ejb.EJBException ejbex){
            return 10;
        }
        if(tmsBaseDatosConfigTbl.size()==0) return 10;
        int contador=0, i;
        for(i=0; i<tmsBaseDatosConfigTbl.size(); i++)
            if(!tmsBaseDatosConfigTbl.get(i).getNombreTerminal().equals("CENTRAL")) contador++;
        
        OrigenesDBLink=new Object[contador];
        
        contador=0;
        for(i=0; i<tmsBaseDatosConfigTbl.size(); i++){
            if(!tmsBaseDatosConfigTbl.get(i).getNombreTerminal().equals("CENTRAL")){
                OrigenesDBLink[contador] =tmsBaseDatosConfigTbl.get(i).getNombreTerminal();
                contador++;
            }
                
            if(tmsBaseDatosConfigTbl.get(i).getEsquemaPropio().equals("S")){
                getUserCon().setNombreEsquemaPropio(tmsBaseDatosConfigTbl.get(i).getNombreEsquema());
                getUserCon().setTerminalNombre(tmsBaseDatosConfigTbl.get(i).getNombreTerminal());
                getUserCon().setTerminalId(tmsBaseDatosConfigTbl.get(i).getTerminalId());
                String prefijoTerminalId=String.valueOf(tmsBaseDatosConfigTbl.get(i).getTerminalId());
                String prefijoTerminal="";
                if(prefijoTerminalId.length()>3)  prefijoTerminal=prefijoTerminalId.substring(0,3);
                if(prefijoTerminalId.length()==3) prefijoTerminal=prefijoTerminalId;
                if(prefijoTerminalId.length()==2) prefijoTerminal=prefijoTerminalId+"0";
                if(prefijoTerminalId.length()==1) prefijoTerminal=prefijoTerminalId+"00";
                getUserCon().setTerminalPrefijoId(prefijoTerminal);
            }
        }
        return 0;
    }
    
    public String buscarDBlink(String terminal){
        String db = "";
        for(int i=0; i<tmsBaseDatosConfigTbl.size(); i++)
            if(terminal.equals(tmsBaseDatosConfigTbl.get(i).getNombreTerminal())){
                {
                    db = tmsBaseDatosConfigTbl.get(i).getNombreDblink();
                    break;
                }
            }
        return db;
    }
    
    public String buscarTerminalId(String terminal){
        String db = "";
        for(int i=0; i<tmsBaseDatosConfigTbl.size(); i++)
            if(terminal.equals(tmsBaseDatosConfigTbl.get(i).getNombreTerminal())){
                {
                    db =""+ tmsBaseDatosConfigTbl.get(i).getTerminalId();
                    break;
                }
            }
        return db;
    }
    
    public String getFormatoFolioSistema(long pFolSys){
        String strFolSys = String.valueOf(pFolSys);
        if(strFolSys.length()==7) return strFolSys;
        if(strFolSys.length()==6) return "0"+strFolSys;
        if(strFolSys.length()==5) return "00"+strFolSys;
        if(strFolSys.length()==4) return "000"+strFolSys;
        if(strFolSys.length()==3) return "0000"+strFolSys;
        if(strFolSys.length()==2) return "00000"+strFolSys;
        if(strFolSys.length()==1) return "000000"+strFolSys;
        return strFolSys;
    }
    
    public String getDBLink(){ return this.DBLink; }
    
    public void setDBLink(String dbl){ this.DBLink=dbl; }
    
    public String getDBLinkClaveOrigen(String Origen){
        for(int i=0; i<tmsBaseDatosConfigTbl.size(); i++)
            if(Origen.equals(tmsBaseDatosConfigTbl.get(i).getNombreTerminal())){
                this.DBLink=tmsBaseDatosConfigTbl.get(i).getNombreDblink();
                if(this.DBLink==null || tmsBaseDatosConfigTbl.get(i).getEsquemaPropio().equals("S")) {
                    this.DBLink=""; this.esquema = tmsBaseDatosConfigTbl.get(i).getNombreEsquema();
                }
                String terminalIdRemoto=String.valueOf(tmsBaseDatosConfigTbl.get(i).getTerminalId());
                if(terminalIdRemoto.length()>3) getUserCon().setTerminalPrefijoIdRem(terminalIdRemoto.substring(0, 3));
                if(terminalIdRemoto.length()==3) getUserCon().setTerminalPrefijoIdRem(terminalIdRemoto);
                if(terminalIdRemoto.length()==2) getUserCon().setTerminalPrefijoIdRem(terminalIdRemoto+"0");
                if(terminalIdRemoto.length()==1) getUserCon().setTerminalPrefijoIdRem(terminalIdRemoto+"00");
                return this.DBLink;
            }
        return null;
    }
    
    public String getDBLinkAuxiliar(){ return this.DBLinkAuxiliar; }
    
    public void setDBLinkAuxiliar(String dbl){ this.DBLinkAuxiliar=dbl; }
    
    public String getLigaRemota(String Origen){
        String ligaRem;
        for(int i=0; i<tmsBaseDatosConfigTbl.size(); i++)
            if(Origen.equals(tmsBaseDatosConfigTbl.get(i).getNombreTerminal())){
                ligaRem=tmsBaseDatosConfigTbl.get(i).getNombreDblink();
                if(this.DBLink==null){
                    if(tmsBaseDatosConfigTbl.get(i).getEsquemaPropio().equals("S")) return "";
                }
                prefijoConexionReal=String.valueOf(tmsBaseDatosConfigTbl.get(i).getTerminalId());
                if(prefijoConexionReal.length()>3) prefijoConexionReal=prefijoConexionReal.substring(0, 3);
                if(prefijoConexionReal.length()==3) prefijoConexionReal=prefijoConexionReal;
                if(prefijoConexionReal.length()==2) prefijoConexionReal=prefijoConexionReal+"0";
                if(prefijoConexionReal.length()==1) prefijoConexionReal=prefijoConexionReal+"00";
                return ligaRem;
            }
        return "";
    }
    
    private int FuncionesDeUsuario(long MENU_ID){
        if(!abreSocketAS()) return -21;
        Vector x = null;
        try{
            x = getTmsVtaFacade().FuncionesDeUsuario(MENU_ID);
        }catch(javax.ejb.EJBException ejbex){
            return 1;
        }
        if(x==null) return 1;
        Vector z;
        FuncionesUsuario = new String[x.size()][3];
        for(int i=0; i<FuncionesUsuario.length; i++){
            z=(Vector) x.get(i);
            FuncionesUsuario[i][0] = z.get(0).toString();
            FuncionesUsuario[i][1] = z.get(1).toString();
            FuncionesUsuario[i][2] = z.get(2).toString();
        }
        return 0;
    }
   
    public boolean ValidaFuncionUsuario(String Funcion){
        Timestamp dtFecha = new Timestamp(new Date().getTime());
        for(int f=0; f<FuncionesUsuario.length; f++){
            if(FuncionesUsuario[f][0].equals(Funcion)){
                if(FuncionesUsuario[f][2].equals("S")){
                    aplicaDatosAuditoria = new TmsAuditoriaTbl();
                    aplicaDatosAuditoria.setDescripcionProceso(FuncionesUsuario[f][1]);
                    aplicaDatosAuditoria.setEstadoProceso("EXITO");
                    aplicaDatosAuditoria.setFecha(dtFecha);
                    aplicaDatosAuditoria.setFuncionNumero(Long.valueOf(Funcion));
                    aplicaDatosAuditoria.setNombreEquipo(getUserCon().getCaja());
                    aplicaDatosAuditoria.setUsuarioId(getUserCon().getUsuarioId());
                    //System.out.println("USUARIO CON DERECHO Y AUDITA");
                }else aplicaDatosAuditoria=null;
                //System.out.println("USUARIO MORTAL OBV NO AUDITA");
                getUserCon().setAutorizadoPorIdUsuario(getUserCon().getUsuarioId());
                getUserCon().setAutorizadoPorNumeroUsuario(getUserCon().getUsuarioNum());
                return true;
            }
        }
        aplicaDatosAuditoria = null;
        return false;
    }
    
    public boolean AuditarFuncion(){
        if(!abreSocketAS()) return false; // MOD:: NIVEL -21
        if(aplicaDatosAuditoria==null) return true;
        try{
            String fechaHoy=formatoFechaHora.format(new Date(this.getAplicaDatosAuditoria().getFecha().getTime()));
            String valores="TO_NUMBER('"+getUserCon().getTerminalPrefijoId()+"' || TO_CHAR(TMS_AUDITORIA_SEQ.NEXTVAL)),"+this.getAplicaDatosAuditoria().getUsuarioId()+","
                    +"TO_DATE('"+fechaHoy+"','DD/MM/YYYY HH24:MI'),"+this.getAplicaDatosAuditoria().getFuncionNumero()+",'"+this.getAplicaDatosAuditoria().getDescripcionProceso()+","+
                    this.getAplicaDatosAuditoria().getEstadoProceso()+","+this.getAplicaDatosAuditoria().getNombreEquipo()+",NULL,NULL,NULL";
            System.out.println("ValAudita "+valores);
            return getTmsVtaFacade().FuncionAuditable(valores);
        }catch(javax.ejb.EJBException ejbex){
            return true;
        }
    }
    
    public boolean insertarFuncionAuditable(TmsAuditoriaTbl funcionAuditable){
        if(!abreSocketAS()) return false; // MOD:: NIVEL -21
        if(funcionAuditable==null) return true;
        try{
            String fechaHoy=formatoFechaHora.format(new Date(this.getAplicaDatosAuditoria().getFecha().getTime()));
            String valores="TO_NUMBER('"+getUserCon().getTerminalPrefijoId()+"' || TO_CHAR(TMS_AUDITORIA_SEQ.NEXTVAL)),"+this.getAplicaDatosAuditoria().getUsuarioId()+","
                    +"TO_DATE('"+fechaHoy+"','DD/MM/YYYY HH24:MI'),"+this.getAplicaDatosAuditoria().getFuncionNumero()+",'"+this.getAplicaDatosAuditoria().getDescripcionProceso()+","+
                    this.getAplicaDatosAuditoria().getEstadoProceso()+","+this.getAplicaDatosAuditoria().getNombreEquipo()+",NULL,NULL,NULL";
            System.out.println("ValAudita "+valores);
            return getTmsVtaFacade().FuncionAuditable(valores);
        }catch(javax.ejb.EJBException ejbex){
            return true;
        }
    }
    
    public boolean SupervisorAutorizaFuncion(String UsuarioNumero, String FuncionNumero){
        if(!abreSocketAS()) return false; // MOD:: NIVEL -21
        String[] funcionSupervisor = null;
        try{
            funcionSupervisor = getTmsVtaFacade().FuncionAutorizadaPorSupervisor(UsuarioNumero, FuncionNumero);
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
        if(funcionSupervisor==null){
            aplicaDatosAuditoria = null;
            System.out.println("SUPERVISOR NULO");
            return false;
        }
        if(funcionSupervisor[3].equals("S")){
            System.out.println("SUPERVISOR PODEROSO Y AUDITA");
            Timestamp dtFecha = new Timestamp(new Date().getTime());
            aplicaDatosAuditoria = new TmsAuditoriaTbl();
            aplicaDatosAuditoria.setDescripcionProceso(funcionSupervisor[2]);
            aplicaDatosAuditoria.setEstadoProceso("EXITO");
            aplicaDatosAuditoria.setFecha(dtFecha);
            aplicaDatosAuditoria.setFuncionNumero(Long.valueOf(FuncionNumero));
            aplicaDatosAuditoria.setNombreEquipo(getUserCon().getCaja());
            aplicaDatosAuditoria.setUsuarioId(Long.valueOf(funcionSupervisor[0]));
        }else aplicaDatosAuditoria = null;
        System.out.println("SUPERVISOR PODEROSO NO AUDITA");
        return true;
    }
    
    public TmsAuditoriaTbl getAplicaDatosAuditoria(){ return aplicaDatosAuditoria; }
    
/************************************************************** PARAM GLOBALES *******************************************/   
    public JCls_Sesion_Usuario getUserCon(){ return userCon; }
    
    public void setTipoTransaccion(String iv){ this.tipoTransaccion = iv; }
    public String getTipoTransaccion(){ return this.tipoTransaccion; }
    
    public void setTipoTransaccionAuxiliar(String iv){ this.tipoTransaccionAuxiliar = iv; }
    public String getTipoTransaccionAuxiliar(){ return this.tipoTransaccionAuxiliar; }
    
    public void setImporteVenta(double iv){ this.ImporteVtaActual = iv; }
    public double getImporteVenta(){ return this.ImporteVtaActual; }
    
    private void setReservacionId(long iv){ this.ReservacionId = iv; }
    public long getReservacionId(){ return this.ReservacionId; }
    
    private void setBoletoRelacionadoId(long iv){ this.BoletoRelacionadoId = iv; }
    public long getBoletoRelacionadoId(){ return this.BoletoRelacionadoId; }
    
    private void setBoletoIdCancelado(long iv){ this.boletoIdCancelado = iv; }
    public long getBoletoIdCancelado(){ return this.boletoIdCancelado; }
    
    private void setBoletoIdCanceladoRem(long iv){ this.boletoIdCanceladoRem = iv; }
    public long getBoletoIdCanceladoRem(){ return this.boletoIdCanceladoRem; }
    
    private void setVariosTmsBoletosVentaTbl(List<TmsBoletosVentaTbl> iv){ this.variosTmsBoletosVentaTbl = iv; }
    
    public List<TmsBoletosVentaTbl> getVariosTmsBoletosVentaTbl(){ return this.variosTmsBoletosVentaTbl; }
    
    private void setTmsBoletosVentaTbl(TmsBoletosVentaTbl iv){ this.tmsBoletosVentaTbl = iv; }
    
    public TmsBoletosVentaTbl getTmsBoletosVentaTbl(){ return this.tmsBoletosVentaTbl; }
    
    private void setUserCon(JCls_Sesion_Usuario userCon) {
        this.userCon = userCon;
    }    
    
    private void incrementafolio(String empresa){
        int indice;
        if(getUserCon().getFolioUnico()) indice = 0;
        else indice=this.indiceEmpresa(empresa);
        folioActual[indice]++;
    }  
    
    private void incrementaVariosFolios(String empresa, long foliosPreimpresos){
        int indice;
        if(getUserCon().getFolioUnico()) indice = 0;
        else indice=this.indiceEmpresa(empresa);
        folioActual[indice]=foliosPreimpresos;
        folioActual[indice]++;
    }  
    
    public String obtenerFolioActual(String empresa){
            int indice;
            if(getUserCon().getFolioUnico()) indice = 0;
            else indice=this.indiceEmpresa(empresa);
           return String.valueOf(folioActual[indice]);
    }

    public String obtenerFolioGneradoSistema(){
           return getTmsVtaFacade().getGeneraFolioPreimpreso(getUserCon().getTerminalId());
    }

    public long obtenerFolioFinal(String empresa){
        int indice;
        if(getUserCon().getFolioUnico()) indice = 0;
        else indice=this.indiceEmpresa(empresa);
        return Long.valueOf(getUserCon().getFolioPreimpreso()[indice][1]);
    }

    public void FoliosActuales(){
        folioActual=new long[this.getUserCon().getFolioPreimpreso().length];
        for(int i=0; i<this.getUserCon().getFolioPreimpreso().length; i++){
            try{
                folioActual[i]=Long.valueOf(getUserCon().getFolioPreimpreso()[i][0]);
            }
            catch(NumberFormatException nfe){
                if(i>1)
                    folioActual[i]=folioActual[0];
                else
                    folioActual[i]=0;
            }
        }
    }
    
    public int indiceEmpresa(String empresa){
        int i;
        String[][] folios=this.getUserCon().getFolioPreimpreso();
        for(i=0; i<folios.length; i++)
            if(folios[i][2].equals(empresa)) return i;
        
        return getUserCon().getIndiceEmpresaPrincipal();
    }
/******************************************************************************/
    private static Context getInitialContext() throws NamingException{
        return new InitialContext();
    }
/******************************************************************************/
    private int Plantillas(){
        if(!abreSocketAS()) return -21;
        try{
            componentes = new ArrayList<TmsComponenteBusTbl>(getTmsVtaFacade().queryTmsComponenteBusTblFindAll());
            encabezados = new ArrayList<TmsAutobusPlantillasEncTbl>(getTmsVtaFacade().queryTmsAutobusPlantillasEncTblFindAll());
            lineas =  new ArrayList<TmsAutobusPlantLineasTbl>(getTmsVtaFacade().queryTmsAutobusPlantLineasTblFindAll());
        }catch(javax.ejb.EJBException ejbex){
            return 20;
        }
        
        if(componentes==null || encabezados==null || lineas==null ||
           componentes.size()==0 || encabezados.size()==0 || lineas.size()==0)
            return 20;
        PLANTILLA_DEFAULT=encabezados.get(0).getPlantillaEncId();
        return 0;
    }
    
    public long getPLANTILLA_DEFAULT(){ return this.PLANTILLA_DEFAULT; }
    
    public List<TmsComponenteBusTbl> getComponentes(){
        return componentes;
    }
    
    public TmsAutobusPlantillasEncTbl getEncabezado(long PlantillaId){
        for(int i=0; i<encabezados.size(); i++)
            if(encabezados.get(i).getPlantillaEncId()==PlantillaId) return encabezados.get(i);
        return null;
    }
    
    public List<TmsAutobusPlantLineasTbl> getLineas(long PlantillaId){
        Vector vLineas = new Vector();
        
        for(int i=0; i<lineas.size(); i++)
            if(lineas.get(i).getPlantillaEncId()==PlantillaId)
                vLineas.addElement(lineas.get(i));
        
        if(vLineas.size()==0) return null;
        return new ArrayList<TmsAutobusPlantLineasTbl>(vLineas);
    }
    
    public int getCapacidadPlantilla(long PlantillaId){
        for(int i=0; i<encabezados.size(); i++)
            if(encabezados.get(i).getPlantillaEncId()==PlantillaId) return encabezados.get(i).getCapacidadAsientos().intValue();
        return 0;
    }
    
    private int queryTmsTiposPasajeroTblFindAll() {
        if(!abreSocketAS()) return -21;
        try{
            //tmsTiposPasajeroTbl = tmsVtaFacade.queryTmsTiposPasajeroTblFindAble();
            tmsTiposPasajeroTbl = getTmsVtaFacade().queryTmsTiposPasajeroTblFindAble2();
            //for(int i=0; i<tmsTiposPasajeroTbl.size(); i++)
                //System.out.println("tmsTiposPasajeroTbl("+i+") "+ tmsTiposPasajeroTbl.get(i).getTipoPasajeroId()+" "+tmsTiposPasajeroTbl.get(i).getNombreTipo()+" "+tmsTiposPasajeroTbl.get(i).getLetraTipo()+" "+tmsTiposPasajeroTbl.get(i).getPctDescuento()+" "+tmsTiposPasajeroTbl.get(i).getRutaId());
            
        }catch(javax.ejb.EJBException ejbex){
            return 22;
        }
        if(tmsTiposPasajeroTbl.size()==0) return 22;
        if(tmsTiposPasajeroTbl.size()==2
                && tmsTiposPasajeroTbl.get(0).getNombreTipo().equals("ESTUDIANTE")
                && tmsTiposPasajeroTbl.get(1).getNombreTipo().equals("PROFESOR")) return 22;
        return 0;
    }
    
    public String getTmsTiposPasajeroLetra(String nombre) {
        for(int i=0; i<tmsTiposPasajeroTbl.size(); i++)
        {
            //System.out.println("Tipo Pas: "+tmsTiposPasajeroTbl.get(i).getNombreTipo()+"=> tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("+nombre+")");
            if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals(nombre)) return tmsTiposPasajeroTbl.get(i).getLetraTipo();
        }
        return null;
    }
    
    public String getTmsTiposPasajeroNombre(String letra) {
        for(int i=0; i<tmsTiposPasajeroTbl.size(); i++)
        {
            //System.out.println("Tipo Pas: "+tmsTiposPasajeroTbl.get(i).getNombreTipo()+"=> tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("+nombre+")");
            if(tmsTiposPasajeroTbl.get(i).getLetraTipo().equals(letra)) return tmsTiposPasajeroTbl.get(i).getNombreTipo();
        }
        return null;
    }

    public String getTmsTiposPasajeroId(String letra) {
        for(int i=0; i<tmsTiposPasajeroTbl.size(); i++)
        {
            String tipoId="";
            //System.out.println("Tipo Pas: "+tmsTiposPasajeroTbl.get(i).getNombreTipo());
           // System.out.println("Tipo Pas: "+tmsTiposPasajeroTbl.get(i).getLetraTipo());
            if(tmsTiposPasajeroTbl.get(i).getLetraTipo().equals(letra))
            {
               tipoId= ""+tmsTiposPasajeroTbl.get(i).getTipoPasajeroId();
                for(int j=tipoId.length(); j<2; j++)
                    tipoId = "0"+tipoId;
               return tipoId;
            }
        }
        return null;
    }

    public String getEstadoId(String estadoNombre)
    {
        for(int i=0; i<getvEstados().size();i++)
        {
            Vector v = (Vector) getvEstados().get(i);
            if(v.get(0).toString().equals(estadoNombre))
                return v.get(1).toString();
        }
        return "000";
    }

    public String getEstadoDescripcion(String estadoNombre)
    {
        for(int i=0; i<getvEstados().size();i++)
        {
            Vector v = (Vector) getvEstados().get(i);
            if(v.get(0).toString().equals(estadoNombre))
                return v.get(2).toString();
        }
        return estadoNombre;
    }

    private int Rutas(){
        if(!abreSocketAS()) return -21;
        xxx = new Vector();
        try{
            xxx=getTmsVtaFacade().queryTmsServicioOrigsDestsV_vista(getUserCon().getListaRutasNoVenta());
        }catch(javax.ejb.EJBException ejbex){
            return 19;
        }
        if(xxx==null || xxx.size()==0) return 19;
        Vector tmsServicioOrigsDestsV = new Vector();
        int i, j;
        for(i=1; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            try{
                
                j=Integer.valueOf(tmsServicioOrigsDestsV.get(16).toString());
            }catch(NullPointerException npex){
                return 19;
            }catch(NumberFormatException nfex){
                return 19;
            }
        }
        
        tmsServicioOrigsDestsV=(Vector) xxx.get(0);
//0RUTA_ID, 1RUTA_NUMERO, 2RUTA_NOMBRE, 3ORIGEN_ID, 4ORIGEN, 5SERVICIO_ID, 6SERVICIO_CLAVE, 7SERVICIO, 8LETRA,
//9DESTINO_ID, 10DESTINO, 11TRAMO_VTA_REGRESO, 12 rutaorigenid, 13 rutaorigen, 14 rutadestinoid, 15 rutadestino, 16 adicional1
        Object objeto;
        VectorServicios = new Vector();
        VectorServicios.add("TODOS");
        VectorServicios.add(tmsServicioOrigsDestsV.get(7));
        boolean igual=false;
        Object[][] xDB = new Object[OrigenesDBLink.length][2];
        
        for(i=0; i<xDB.length; i++){
            xDB[i][0] = OrigenesDBLink[i].toString();
            xDB[i][1] = "S";
        }
        
        for(i=1; i<xxx.size(); i++){
            igual=false;
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            for(j=0; j<VectorServicios.size(); j++){
                if(tmsServicioOrigsDestsV.get(7).toString().equals(VectorServicios.get(j).toString())){
                    igual=true;
                    break;
                }
            }
            if(!igual) VectorServicios.add(tmsServicioOrigsDestsV.get(7));
        }
        
        for(i=0; i<xDB.length; i++){
            igual=false;
            for(j=0; j<xxx.size(); j++){
                tmsServicioOrigsDestsV=(Vector) xxx.get(j);
                if(xDB[i][0].toString().equals(tmsServicioOrigsDestsV.get(4).toString())){
                    igual=true;
                    break;
                }
            }
            if(!igual)
                xDB[i][1]="N";
        }
        
        for(j=0; j<xDB.length; j++){
            if(xDB[j][1].toString().equals("S")){
                for(i=j+1; i<xDB.length; i++){
                    if(xDB[i][1].toString().equals("S")){
                        if(xDB[i][0].toString().equals(xDB[j][0].toString())){
                            xDB[j][1] = "N";
                        }
                    }
                }
            }
        }
        
        j=0;
        for(i=0; i<xDB.length; i++){
            if(xDB[i][1].toString().equals("S")){
                j++;
            }
        }
        //System.out.println(j);
        OrigenDBLink = new Vector();
        for(i=0; i<xDB.length; i++){
            if(xDB[i][1].toString().equals("S")){
                OrigenDBLink.add(xDB[i][0].toString());
            }
        }
        
        return 0;
    }

    private String obtienePrimerOrigenDestino(String ciudadOrigen, boolean tipoBusqueda){
        Vector tmsServicioOrigsDestsV;
        for(int i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(ciudadOrigen.equals(tmsServicioOrigsDestsV.get(13).toString())) return tipoBusqueda ? tmsServicioOrigsDestsV.get(4).toString() : tmsServicioOrigsDestsV.get(10).toString();
        }
        return null;
    }
    
    private String obtienePrimerServicio(String ciudadOrigen){
        Vector tmsServicioOrigsDestsV;
        for(int i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(ciudadOrigen.equals(tmsServicioOrigsDestsV.get(13).toString())) return tmsServicioOrigsDestsV.get(8).toString();
        }
        return null;
    }
    
    private String obtienePrimerServicioSinLetra(String ciudadOrigen){
        Vector tmsServicioOrigsDestsV;
        for(int i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(ciudadOrigen.equals(tmsServicioOrigsDestsV.get(13).toString())) return tmsServicioOrigsDestsV.get(7).toString();
        }
        return null;
    }
    
    public void cargarOrigenesDestinosServicios(String ciudadOrigen, boolean conSinonimo){
        int i=0, j, k;
        //0RUTA_ID, 1RUTA_NUMERO, 2RUTA_NOMBRE, 3ORIGEN_ID, 4ORIGEN, 5SERVICIO_ID, 6SERVICIO_CLAVE, 7SERVICIO, 8LETRA,
        //9DESTINO_ID, 10DESTINO, 11TRAMO_VTA_REGRESO, 12 RUTAORIGENID 13 RUTAORIGEN 14 RUTADESTINOID 15 RUTADESTINO    
        boolean seRepite;
        Object objeto;

        k=1;
        vDestinos = new Vector();
        vDestinos.add(0,"TODOS");
        vDestinos.add(k,obtienePrimerOrigenDestino(ciudadOrigen, false));
        k++;
        Vector tmsServicioOrigsDestsV;
        for(i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(ciudadOrigen.equals(tmsServicioOrigsDestsV.get(13).toString())){
                objeto = tmsServicioOrigsDestsV.get(10);
                seRepite=false;
                for(j=0; j<vDestinos.size(); j++){
                    if(objeto.toString().equals(vDestinos.get(j).toString())){
                        seRepite=true; break;
                    }
                }
                if(!seRepite){
                    vDestinos.add(k,objeto);
                    k++;
                }
            }
        }
        
        //Origen
        k=0;
        vOrigenes = new Vector();
        vOrigenes.add(k,obtienePrimerOrigenDestino(ciudadOrigen, true));
        k++;
        
        for(i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(ciudadOrigen.equals(tmsServicioOrigsDestsV.get(13).toString())){
                objeto = tmsServicioOrigsDestsV.get(4);
                seRepite=false;
                for(j=0; j<vOrigenes.size(); j++){
                    if(objeto.toString().equals(vOrigenes.get(j).toString())){
                        seRepite=true; break;
                    }
                }
                if(!seRepite){
                    vOrigenes.add(k,objeto);
                    k++;
                }
            }
        }
        
        //Servicios
        k=1;
        vServicios = new Vector();
        vSReal = new Vector();
        vServicios.add(0,"TODOS");
        vServicios.add(k,obtienePrimerServicio(ciudadOrigen));
        vSReal.add(obtienePrimerServicioSinLetra(ciudadOrigen));
        k++;
        //System.out.print("primer servicio "+vServicios.get(1).toString());
        for(i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(ciudadOrigen.equals(tmsServicioOrigsDestsV.get(13).toString())){
                objeto = tmsServicioOrigsDestsV.get(8);
                seRepite=false;
                for(j=0; j<vServicios.size(); j++){
                    if(objeto.toString().equals(vServicios.get(j).toString())){
                        seRepite=true; break;
                    }
                }
                if(!seRepite){
                    vServicios.add(k,objeto);
                    vSReal.add(tmsServicioOrigsDestsV.get(7).toString());
                    k++;
                }
            }
        }
    }
    
    public void cargarODServicios(String ciudadOrigen){
        int i=0, j, k;
        //0RUTA_ID, 1RUTA_NUMERO, 2RUTA_NOMBRE, 3ORIGEN_ID, 4ORIGEN, 5SERVICIO_ID, 6SERVICIO_CLAVE, 7SERVICIO, 8LETRA,
        //9DESTINO_ID, 10DESTINO, 11TRAMO_VTA_REGRESO, 12 RUTAORIGENID 13 RUTAORIGEN 14 RUTADESTINOID 15 RUTADESTINO
        
            boolean seRepite;
            Object objeto;
            
        //Servicios
        Vector tmsServicioV;
        k=0;
        vS = new Vector();
        vS.add(k,obtienePrimerServicio(ciudadOrigen));
        k++;
        for(i=0; i<xxx.size(); i++){
            tmsServicioV=(Vector) xxx.get(i);
            if(ciudadOrigen.equals(tmsServicioV.get(13).toString())){
                objeto = tmsServicioV.get(8);
                seRepite=false;
                for(j=0; j<vS.size(); j++){
                    if(objeto.toString().equals(vS.get(j).toString())){
                        seRepite=true; break;
                    }
                }
                if(!seRepite){
                    vS.add(k,objeto);
                    k++;
                }
            }
        }
    }
    
    private String obtienePrimerDestino(String strDestino){
        Vector tmsServicioOrigsDestsV;
        for(int i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(strDestino.equals(tmsServicioOrigsDestsV.get(10).toString())) return tmsServicioOrigsDestsV.get(15).toString();
        }
        return null;
    }
    
    public Vector obtieneRutaDestino(String strDestino){
        Vector vRutaDestinos=new Vector();
        Vector tmsServicioOrigsDestsV;
        //0RUTA_ID, 1RUTA_NUMERO, 2RUTA_NOMBRE, 3ORIGEN_ID, 4ORIGEN, 5SERVICIO_ID, 6SERVICIO_CLAVE, 7SERVICIO, 8LETRA,
        //9DESTINO_ID, 10DESTINO, 11TRAMO_VTA_REGRESO, 12 RUTAORIGENID 13 RUTAORIGEN 14 RUTADESTINOID 15 RUTADESTINO
        boolean seRepite;
        Object objeto;
        int i, j;
        String pD = obtienePrimerDestino(strDestino);
        if(pD==null) return null;
        vRutaDestinos.add(pD);
        for(i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(strDestino.equals(tmsServicioOrigsDestsV.get(10).toString())){
                objeto = tmsServicioOrigsDestsV.get(15);
                seRepite=false;
                for(j=0; j<vRutaDestinos.size(); j++){
                    if(objeto.toString().equals(vRutaDestinos.get(j).toString())){
                        seRepite=true; break;
                    }
                }
                if(!seRepite){
                    vRutaDestinos.add(objeto);
                }
            }
        }
        return vRutaDestinos;
    }
     
    private String obtienePrimerRutaServicio(String strDestino){
        Vector tmsServicioOrigsDestsV;
        for(int i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(strDestino.equals(tmsServicioOrigsDestsV.get(10).toString())) return tmsServicioOrigsDestsV.get(7).toString();
        }
        return null;
    }
    
    public Vector obtieneRutaServicio(String strDestino){
        Vector vRutaServicios=new Vector();
        Vector tmsServicioOrigsDestsV;
        //0RUTA_ID, 1RUTA_NUMERO, 2RUTA_NOMBRE, 3ORIGEN_ID, 4ORIGEN, 5SERVICIO_ID, 6SERVICIO_CLAVE, 7SERVICIO, 8LETRA,
        //9DESTINO_ID, 10DESTINO, 11TRAMO_VTA_REGRESO, 12 RUTAORIGENID 13 RUTAORIGEN 14 RUTADESTINOID 15 RUTADESTINO
        boolean seRepite;
        Object objeto;
        int i, j;
        String pD = obtienePrimerRutaServicio(strDestino);
        if(pD==null) return null;
        vRutaServicios.add(pD);
        for(i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(strDestino.equals(tmsServicioOrigsDestsV.get(10).toString())){
                objeto = tmsServicioOrigsDestsV.get(7);
                seRepite=false;
                for(j=0; j<vRutaServicios.size(); j++){
                    if(objeto.toString().equals(vRutaServicios.get(j).toString())){
                        seRepite=true; break;
                    }
                }
                if(!seRepite){
                    vRutaServicios.add(objeto);
                }
            }
        }
        return vRutaServicios;
    }
    /*************************************************************************/
    private int TiposPagos(){
        if(!abreSocketAS()) return -21;
        try{
            //if(getUserCon().getAplicacionVenta())
                //formasPago = new ArrayList<TmsTipopagosV>(getTmsVtaFacade().queryTmsTipopagosVFindAll());
                formasPago = getTmsVtaFacade().queryTmsTipopagosVFindAll();
            //else
              //  formasPago = new ArrayList<TmsTipopagosV>(getTmsVtaFacade().queryTmsTipopagosCallVFindAll());
        }catch(javax.ejb.EJBException ejbex){
            ejbex.printStackTrace();
            return 21;
        }
        if(formasPago==null || formasPago.size()==0) return 21;
        if(getUserCon().getAplicacionVenta())    
            objFormasPago_BA = new Object[formasPago.size()-1][8];
        else
            objFormasPago_BA = new Object[formasPago.size()][8];
        objFormasPagoBA = new Object[1][8];
        TmsTipopagosV formaPago = new TmsTipopagosV();
        int j=0;
        System.out.println("Formas de pago: "+formasPago.size());
        for (int i = 0; i < formasPago.size(); i++) {
            formaPago=formasPago.get(i);
            if(!formaPago.getIdentificador().equals("BAB")){
                objFormasPago_BA[j][0] = formaPago.getNombre();
                objFormasPago_BA[j][1] = formaPago.getIdentificador();
                objFormasPago_BA[j][2] = "";
                objFormasPago_BA[j][3] = "";
                objFormasPago_BA[j][4] = "";
                objFormasPago_BA[j][5] = "";
                objFormasPago_BA[j][6] = (formaPago.getSocio_intimo() == null ?"N" :formaPago.getSocio_intimo());
                objFormasPago_BA[j][7] = (formaPago.getContado() == null ?"N" :formaPago.getContado());
                j++;
            }
            else{
                objFormasPagoBA[0][0] = formaPago.getNombre();
                objFormasPagoBA[0][1] = formaPago.getIdentificador();
                objFormasPagoBA[0][2] = "";
                objFormasPagoBA[0][3] = "";
                objFormasPagoBA[0][4] = "";
                objFormasPagoBA[0][5] = "";
                objFormasPagoBA[0][6] = (formaPago.getSocio_intimo() == null ?"N" :formaPago.getSocio_intimo());
                objFormasPagoBA[0][7] = (formaPago.getContado() == null ?"N" :formaPago.getContado());
            }
        }
        return 0;
    }
    
    public Object[][] getFormasPago_BA(){ return objFormasPago_BA; }
    public Object[][] getFormaPagoBA(){ return objFormasPagoBA; }

    public int liberaAsientoCancelado(String ClaveCorrida, int noAsiento, String tipoPasaje, String ctd){
        System.out.println("Estoy cancelando a: "+ClaveCorrida + " " + noAsiento + " " + tipoPasaje + " " + ctd);
        if(!abreSocketAS()) return 1; // MOD:: NIVEL -21
        String origenCorrida = ClaveCorrida.substring(0,4);
        if(origenCorrida.contains("_")) origenCorrida = origenCorrida.substring(0,3);
        try{
            System.out.println("origenCorrida: "+origenCorrida+" origen: "+getUserCon().getTerminalNombre());
            origenCorrida = origenCorridaReal(origenCorrida, getUserCon().getTerminalNombre());
            System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+getUserCon().getTerminalNombre());
            if(origenCorrida.equals(getUserCon().getTerminalNombre()))
                return getTmsVtaFacade().liberaAsientoCancelado("", "", ClaveCorrida, noAsiento, tipoPasaje, ctd);
            else
                return getTmsVtaFacade().liberaAsientoCancelado("", getDBLinkClaveOrigen(origenCorrida),
                        ClaveCorrida, noAsiento, tipoPasaje, ctd);
        }catch(javax.ejb.EJBException ejbex){
            return 1;
        }
    }
    
    public int _liberaAsientoCancelado(String ClaveCorrida){
        if(!abreSocketAS()) return 1; // MOD:: NIVEL -21
        String origenCorrida = ClaveCorrida.substring(0,4);
        if(origenCorrida.contains("_")) origenCorrida = origenCorrida.substring(0,3);
        try{
            origenCorrida = origenCorridaReal(origenCorrida, getUserCon().getTerminalNombre());
            System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+getUserCon().getTerminalNombre());
            int i;
            //armo cadena tipos
            if(!cadTipoPasaje.equals("")){
                cadTipoPasaje="";
                for(i=0; i<vRTP.size(); i++){
                    if(vRTP.get(i).toString().equals("M")) cadTipoPasaje+="MENORES_CORRIDA=MENORES_CORRIDA+"+(Integer.valueOf(vCRTP.get(i).toString())*-1)+",";
                    else if(vRTP.get(i).toString().equals("S")) cadTipoPasaje+="SENECTUD_CORRIDA=SENECTUD_CORRIDA+"+(Integer.valueOf(vCRTP.get(i).toString())*-1)+",";
                    else if(vRTP.get(i).toString().equals("E")) cadTipoPasaje+="ESTUDIANTES_CORRIDA=ESTUDIANTES_CORRIDA+"+(Integer.valueOf(vCRTP.get(i).toString())*-1)+",";
                    else if(vRTP.get(i).toString().equals("P")) cadTipoPasaje+="PROFESORES_CORRIDA=PROFESORES_CORRIDA+"+(Integer.valueOf(vCRTP.get(i).toString())*-1)+",";
                    else if(vRTP.get(i).toString().equals("C")) cadTipoPasaje+="CORTESIAS_CORRIDA=CORTESIAS_CORRIDA+"+(Integer.valueOf(vCRTP.get(i).toString())*-1)+",";
                }
                cadTipoPasaje=cadTipoPasaje.substring(0,cadTipoPasaje.length()-1);
            }
            
            //armo cadena asientos para update
            String cadCampoAsientos="";
            String noAsientos="";
            for(i=0; i<getVariosTmsBoletosVentaTbl().size(); i++){
                cadCampoAsientos+="ASIENTO"+getVariosTmsBoletosVentaTbl().get(i).getNoAsiento()+"='D',";
                noAsientos+=getVariosTmsBoletosVentaTbl().get(i).getNoAsiento()+",";
            }
            cadCampoAsientos=cadCampoAsientos.substring(0,cadCampoAsientos.length()-1);
            noAsientos=noAsientos.substring(0,noAsientos.length()-1);
            //          
            System.out.println("Estoy cancelando a: "+ClaveCorrida+" - "+cadCampoAsientos+" - "+cadTipoPasaje+" - "+noAsientos);
            if(origenCorrida.equals(getUserCon().getTerminalNombre()))
                return getTmsVtaFacade()._liberaAsientoCancelado("", ClaveCorrida, cadCampoAsientos, cadTipoPasaje, noAsientos);
            else
                return getTmsVtaFacade()._liberaAsientoCancelado(getDBLinkClaveOrigen(origenCorrida), ClaveCorrida, cadCampoAsientos, cadTipoPasaje, noAsientos);
        }catch(javax.ejb.EJBException ejbex){
            return 1;
        }
    }
    
    public boolean actualizaTipoAsiento(long corridaId, String tipoPasaje, int noAsiento){
        if(!abreSocketAS()) return false; // MOD:: NIVEL -21
        try{
            if(this.getTipoTransaccion().equals("L")){
                if(!getTmsVtaFacade().actualizaTipoAsiento("", "", corridaId, noAsiento, tipoPasaje)) return false;
            }
            if(!getTmsVtaFacade().actualizaTipoAsiento("", this.DBLink, corridaId, noAsiento, tipoPasaje)) return false;
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
        return true;
    }
    
    public int incTipoPasaje(long corridaId, String tipoPasaje){
        try{
            if(!tipoPasaje.equals("M") && !tipoPasaje.equals("E") && !tipoPasaje.equals("P") && !tipoPasaje.equals("S") && !tipoPasaje.equals("C")
            && !tipoPasaje.equals("O")) return 0;
            
            int ctd=-1; // quien incrementa
            
            if(tipoPasaje.equals("S")) ctd=1;
            else if(tipoPasaje.equals("C")) ctd=1;
            else if(tipoPasaje.equals("E")) ctd=1;
            else if(tipoPasaje.equals("P")) ctd=1;
            
            if(!abreSocketAS()) return 1; // MOD:: NIVEL -21
            if(this.getTipoTransaccion().equals("L")) return getTmsVtaFacade()._ModificaCtdTipoPasajeRem("", corridaId, tipoPasaje, "("+String.valueOf(ctd)+")");
            return getTmsVtaFacade()._ModificaCtdTipoPasajeRem(this.DBLink, corridaId, tipoPasaje, "("+String.valueOf(ctd)+")");
        }catch(javax.ejb.EJBException ejbex){
            return 1; // no esta bloqueado el registro, pero es problema de cnx o bd
        }
    }
    
   public Vector CastTiposPasaje(){
        tps = new Vector();
        Vector t;
        for(int i=0; i<tmsTiposPasajeroTbl.size(); i++){
//            if(tmsTiposPasajeroTbl.get(i).getRutaId()==1)System.out.println("Lealtad("+tmsTiposPasajeroTbl.get(i).getRutaId()+")"+tmsTiposPasajeroTbl.get(i).getLealtad() +" Lunes: "+tmsTiposPasajeroTbl.get(i).getL_LUNES() +" Tipo: "+tmsTiposPasajeroTbl.get(i).getAplicaTipoLealtad()+" Aplica ("+tmsTiposPasajeroTbl.get(i).getLetraTipo()+") = "+tmsTiposPasajeroTbl.get(i).getAplicaLealtad());
            t = new Vector();
            t.addElement(tmsTiposPasajeroTbl.get(i).getDescuentRutaId() );//getTipoPasajeroId());
            t.addElement(tmsTiposPasajeroTbl.get(i).getNombreTipo());
            t.addElement(tmsTiposPasajeroTbl.get(i).getLetraTipo());
            t.addElement(tmsTiposPasajeroTbl.get(i).getPctDescuento());
            t.addElement(tmsTiposPasajeroTbl.get(i).getRutaId());
            t.addElement(tmsTiposPasajeroTbl.get(i).getRedondeo());
            tps.addElement(t);
        }
        return tps;
    }
   
   public void setTiposPasajeLealtad(int rutaId,String origen)
   {
       terminalLealtadIdO = -1;
       terminalLealtadIdD = -1;
        Vector tmsServicioOrigsDestsV;
        for(int i=0; i<xxx.size(); i++){
            //System.out.println("");
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            //System.out.println("tmsServicioOrigsDestsV.get(0.toString() : "+tmsServicioOrigsDestsV.get(0).toString()+" => "+rutaId);
            if(Long.valueOf(tmsServicioOrigsDestsV.get(0).toString() )==rutaId )
            {
               this.setTerminalLealtadNombreO(tmsServicioOrigsDestsV.get(13).toString());
               if(tmsServicioOrigsDestsV.get(4).toString().equals(origen))
               { 
                   this.terminalLealtadIdO = Long.valueOf(tmsServicioOrigsDestsV.get(12).toString());
                   this.terminalLealtadIdD = Long.valueOf(tmsServicioOrigsDestsV.get(14).toString());
               }
               
            }
        }
       if(rutaId == -1)
           tiposPasajeLealtad = null;
       else
       {
           tiposPasajeLealtad = new ArrayList<TmsTiposPasajeroTbl>() ;
           for(int i=0; i<tmsTiposPasajeroTbl.size(); i++)
               {
                  if(tmsTiposPasajeroTbl.get(i).getRutaId()==rutaId)
                  {
                      if(tmsTiposPasajeroTbl.get(i).getLealtad()==null)
                          tmsTiposPasajeroTbl.get(i).setLealtad("");
                      if(tmsTiposPasajeroTbl.get(i).getAplicaTipoLealtad()==null)
                          tmsTiposPasajeroTbl.get(i).setAplicaTipoLealtad("");
                      if(tmsTiposPasajeroTbl.get(i).getAplicaLealtad()==null)
                          tmsTiposPasajeroTbl.get(i).setAplicaLealtad("");
                      if(tmsTiposPasajeroTbl.get(i).getL_LUNES()==null)
                          tmsTiposPasajeroTbl.get(i).setL_LUNES("");
                      if(tmsTiposPasajeroTbl.get(i).getL_MARTES()==null)
                          tmsTiposPasajeroTbl.get(i).setL_MARTES("");
                      if(tmsTiposPasajeroTbl.get(i).getL_MIERCOLES()==null)
                          tmsTiposPasajeroTbl.get(i).setL_MIERCOLES("");
                      if(tmsTiposPasajeroTbl.get(i).getL_JUEVES()==null)
                          tmsTiposPasajeroTbl.get(i).setL_JUEVES("");
                      if(tmsTiposPasajeroTbl.get(i).getL_VIERNES()==null)
                          tmsTiposPasajeroTbl.get(i).setL_VIERNES("");
                      if(tmsTiposPasajeroTbl.get(i).getL_SABADO()==null)
                          tmsTiposPasajeroTbl.get(i).setL_SABADO("");
                      if(tmsTiposPasajeroTbl.get(i).getL_DOMINGO()==null)
                          tmsTiposPasajeroTbl.get(i).setL_DOMINGO("");
                        tiposPasajeLealtad.add(tmsTiposPasajeroTbl.get(i));
                  } 
               }
                  
       }
   }
   
   
   public List<TmsTiposPasajeroTbl> getTiposPasajeLealtad(){
       return this.tiposPasajeLealtad;
   }
   
   public Vector CastTiposPasajeBA(){
        tps = new Vector();
        Vector t;
        for(int i=0; i<tmsTiposPasajeroTbl.size(); i++){
            if(!tmsTiposPasajeroTbl.get(i).getNombreTipo().contains("VOLARIS") &&
                    !tmsTiposPasajeroTbl.get(i).getNombreTipo().contains("MENOR") &&
                    !tmsTiposPasajeroTbl.get(i).getNombreTipo().contains("ADULTO")) continue;
            t = new Vector();
            t.addElement(tmsTiposPasajeroTbl.get(i).getDescuentRutaId());// getTipoPasajeroId());
            t.addElement(tmsTiposPasajeroTbl.get(i).getNombreTipo());
            t.addElement(tmsTiposPasajeroTbl.get(i).getLetraTipo());
            t.addElement(tmsTiposPasajeroTbl.get(i).getPctDescuento());
            t.addElement(tmsTiposPasajeroTbl.get(i).getRutaId());
            t.addElement(tmsTiposPasajeroTbl.get(i).getRedondeo());
            tps.addElement(t);
        }
        return tps;
    }
   
   public void TiposPasajeParaVenta(){
        tps = new Vector();
        Vector t;
        System.out.println("strDestinoX::: "+strDestinoX);
//        if(this.strDestinoX.equals("AFRPA")||this.strDestinoX.equals("AFRPM")
//          ||this.strDestinoX.equals("AFRTA")||this.strDestinoX.equals("AFRTM")
//          ||this.strDestinoX.equals("SIXPA")||this.strDestinoX.equals("SIXPM")
//          ||this.strDestinoX.equals("SIXTA")||this.strDestinoX.equals("SIXTM")
//          ||this.strDestinoX.equals("CIUPA")||this.strDestinoX.equals("CIUPM")
//          ||this.strDestinoX.equals("GRAPA")||this.strDestinoX.equals("GRAPM")
//          ||this.strDestinoX.equals("TEOPA")||this.strDestinoX.equals("TEOPM")){
//          for(int i=0; i<tmsTiposPasajeroTbl.size(); i++){
//            if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("ADULTO")){
//                System.out.println("entre adulto");
//                t = new Vector();
//                t.addElement(tmsTiposPasajeroTbl.get(i).getTipoPasajeroId());
//                t.addElement(tmsTiposPasajeroTbl.get(i).getNombreTipo());
//                t.addElement(tmsTiposPasajeroTbl.get(i).getLetraTipo());
//                t.addElement(tmsTiposPasajeroTbl.get(i).getPctDescuento());
//                t.addElement(tmsTiposPasajeroTbl.get(i).getRutaId());
//                tps.addElement(t);
//            }
//           }
//        }
//        else{
        if(true){
            System.out.println("Entro a TiposPasajeParaVenta(else)..."+tmsTiposPasajeroTbl.size());
            for(int i=0; i<tmsTiposPasajeroTbl.size(); i++){
                if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("SENECTUD") && getUserCon().getDispSenCor()==0) continue;
                if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("ESTUDIANTE") && getUserCon().getDispEstCor()==0) continue;
                if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("PROFESOR") && getUserCon().getDispProCor()==0) continue;
                if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("ESPECIAL") && getUserCon().getDispCorCor()==0) continue;
                t = new Vector();
                t.addElement(tmsTiposPasajeroTbl.get(i).getDescuentRutaId());// getTipoPasajeroId());
                t.addElement(tmsTiposPasajeroTbl.get(i).getNombreTipo());
                t.addElement(tmsTiposPasajeroTbl.get(i).getLetraTipo());
                t.addElement(tmsTiposPasajeroTbl.get(i).getPctDescuento());
                t.addElement(tmsTiposPasajeroTbl.get(i).getRutaId());
                t.addElement(tmsTiposPasajeroTbl.get(i).getRedondeo());
                //System.out.println("tmsTiposPasajeroTbl("+i+") "+ tmsTiposPasajeroTbl.get(i).getTipoPasajeroId()+" "+tmsTiposPasajeroTbl.get(i).getNombreTipo()+" "+tmsTiposPasajeroTbl.get(i).getLetraTipo()+" "+tmsTiposPasajeroTbl.get(i).getPctDescuento()+" "+tmsTiposPasajeroTbl.get(i).getRutaId());
                tps.addElement(t);
            }
        }
    }
   
    public Vector getCastTiposPasaje(){ return this.tps; }
    
    /**************************** TRANSACCIONES ******************************/
    
    public String FechaHoraCorrida(){
        String origenCorrida = getTmsBoletosVentaTbl().getClaveCorrida().substring(0,4);
        if(origenCorrida.contains("_")) origenCorrida = origenCorrida.substring(0,3);
        if(!abreSocketAS()) return null; // MOD:: NIVEL -21
        try{
            System.out.println("origenCorrida: "+origenCorrida+" origen: "+getUserCon().getTerminalNombre());
            origenCorrida = origenCorridaReal(origenCorrida, getUserCon().getTerminalNombre());
            System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+getUserCon().getTerminalNombre());
            if(origenCorrida.equals(getUserCon().getTerminalNombre()))
                return getTmsVtaFacade().FechaHoraCorridaParaBoleto("", "", getTmsBoletosVentaTbl().getClaveCorrida().trim());
            else
                return getTmsVtaFacade().FechaHoraCorridaParaBoleto("", getDBLinkClaveOrigen(origenCorrida), getTmsBoletosVentaTbl().getClaveCorrida().trim());
        }catch(javax.ejb.EJBException ejbex){
            return null;
        }
    }
    
    public Object[][] buscaBoletoVendido(String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa){
        getDBLinkClaveOrigen(Origen);
        List<TmsBoletosVentaTbl> boletoACancelar = null; String[] estadoTViaje; String origenCorrida;
        try{
            if(!abreSocketAS()) return null; // MOD:: NIVEL -21
            if(this.getTipoTransaccion().equals("L")){ // TRANSACCION LOCAL
                boletoACancelar = getTmsVtaFacade().BuscaBoletoVendido("", "", Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                if(boletoACancelar == null || boletoACancelar.size()==0) return null;
                origenCorrida=boletoACancelar.get(0).getClaveCorrida().substring(0,4).replace("_","");
                System.out.println("origenCorrida: "+origenCorrida+" origen: "+Origen);
                origenCorrida = origenCorridaReal(origenCorrida, Origen);
                System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+Origen);
                if(origenCorrida.equals(Origen)){ // realmente es transaccion local
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", "", boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
                else{ // transaccion realmente remota
                    Origen=origenCorrida;
                    setTipoTransaccion("R");
                    getDBLinkClaveOrigen(Origen);
                    boletoACancelar = getTmsVtaFacade().BuscaBoletoVendido("", this.DBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                    if(boletoACancelar == null || boletoACancelar.size()==0) return null;
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
            }
            else{ // TRANSACCION REMOTA
                boletoACancelar = getTmsVtaFacade().BuscaBoletoVendido("", this.DBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                if(boletoACancelar == null || boletoACancelar.size()==0) return null;
                origenCorrida=boletoACancelar.get(0).getClaveCorrida().substring(0,4).replace("_","");
                System.out.println("origenCorrida: "+origenCorrida+" origen: "+Origen);
                origenCorrida = origenCorridaReal(origenCorrida, Origen);
                System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+Origen);
                if(origenCorrida.equals(Origen)){ // realmente es transaccion remota
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                     if(!estadoTViaje[0].equals("ABIERTA")){
                        if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                        return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
                else{ // transaccion realmente es extraremota
                    Origen=origenCorrida;
                    getDBLinkClaveOrigen(Origen);
                    boletoACancelar = getTmsVtaFacade().BuscaBoletoVendido("", this.DBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                    if(boletoACancelar == null || boletoACancelar.size()==0) return null;
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
            }
        }catch(javax.ejb.EJBException ejbex){
            return null;
        }
        setTmsBoletosVentaTbl(boletoACancelar.get(0));
        Object[][] boleto = new Object[1][11];
        boleto[0][0] = getTmsBoletosVentaTbl().getClaveCorrida().substring(0,10);
        boleto[0][1] = formatoFechaHora.format(getTmsBoletosVentaTbl().getFechaHoraVenta());
        boleto[0][2] = getTmsBoletosVentaTbl().getNoAsiento();
        boleto[0][3] = getTmsBoletosVentaTbl().getTipoPasajero();
        boleto[0][4] = getTmsBoletosVentaTbl().getNombrePasajero()==null ? " " : getTmsBoletosVentaTbl().getNombrePasajero();
        boleto[0][5] = getTmsBoletosVentaTbl().getOrigen();
        boleto[0][6] = getTmsBoletosVentaTbl().getDestino();
        boleto[0][7] = getTmsBoletosVentaTbl().getImporteBoleto();
        boleto[0][8] = getTmsBoletosVentaTbl().getFolioPreimpreso();
        boleto[0][9] = getTmsBoletosVentaTbl().getTipoPago();
        boleto[0][10] = getTmsBoletosVentaTbl().getEmpresa();
        return boleto;
    }
    
    public Object[][] buscaBoletoVendidoParaHO(String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa){
        getDBLinkClaveOrigen(Origen);
        List<TmsBoletosVentaTbl> boletoACancelar = null; String[] estadoTViaje; String origenCorrida;
        try{
            if(!abreSocketAS()) return null; // MOD:: NIVEL -21
            if(this.getTipoTransaccion().equals("L")){ // TRANSACCION LOCAL
                boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaHO("", "", Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                if(boletoACancelar == null || boletoACancelar.size()==0) return null;
                origenCorrida=boletoACancelar.get(0).getClaveCorrida().substring(0,4).replace("_","");
                System.out.println("origenCorrida: "+origenCorrida+" origen: "+Origen);
                origenCorrida = origenCorridaReal(origenCorrida, Origen);
                System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+Origen);
                if(origenCorrida.equals(Origen)){ // realmente es transaccion local
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", "", boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
                else{ // transaccion realmente remota
                    Origen=origenCorrida;
                    setTipoTransaccion("R");
                    getDBLinkClaveOrigen(Origen);
                    boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaHO("", this.DBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                    if(boletoACancelar == null || boletoACancelar.size()==0) return null;
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
            }
            else{ // TRANSACCION REMOTA
                boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaHO("", this.DBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                if(boletoACancelar == null || boletoACancelar.size()==0) return null;
                origenCorrida=boletoACancelar.get(0).getClaveCorrida().substring(0,4).replace("_","");
                System.out.println("origenCorrida: "+origenCorrida+" origen: "+Origen);
                origenCorrida = origenCorridaReal(origenCorrida, Origen);
                System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+Origen);
                if(origenCorrida.equals(Origen)){ // realmente es transaccion remota
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                     if(!estadoTViaje[0].equals("ABIERTA")){
                        if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                        return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
                else{ // transaccion realmente es extraremota
                    Origen=origenCorrida;
                    getDBLinkClaveOrigen(Origen);
                    boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaHO("", this.DBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                    if(boletoACancelar == null || boletoACancelar.size()==0) return null;
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
            }
        }catch(javax.ejb.EJBException ejbex){
            return null;
        }
        setTmsBoletosVentaTbl(boletoACancelar.get(0));
        Object[][] boleto = new Object[1][11];
        boleto[0][0] = getTmsBoletosVentaTbl().getClaveCorrida().substring(0,10);
        boleto[0][1] = formatoFechaHora.format(getTmsBoletosVentaTbl().getFechaHoraVenta());
        boleto[0][2] = getTmsBoletosVentaTbl().getNoAsiento();
        boleto[0][3] = getTmsBoletosVentaTbl().getTipoPasajero();
        boleto[0][4] = getTmsBoletosVentaTbl().getNombrePasajero()==null ? " " : getTmsBoletosVentaTbl().getNombrePasajero();
        boleto[0][5] = getTmsBoletosVentaTbl().getOrigen();
        boleto[0][6] = getTmsBoletosVentaTbl().getDestino();
        boleto[0][7] = getTmsBoletosVentaTbl().getImporteBoleto();
        boleto[0][8] = getTmsBoletosVentaTbl().getFolioPreimpreso();
        boleto[0][9] = getTmsBoletosVentaTbl().getTipoPago();
        boleto[0][10] = getTmsBoletosVentaTbl().getEmpresa();
        return boleto;
    }
    
    public Object[][] buscaBoletoVendidoParaCancelar(String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa
            , int ctdBoletosSolicitados){
        getDBLinkClaveOrigen(Origen);
        List<TmsBoletosVentaTbl> boletoACancelar = null; String[] estadoTViaje; String origenCorrida;
        try{
            System.out.println("Busca para cancelar a: "+Origen+" - "+FolioPreimpreso+" - "+noAsiento+" - "+nombreEmpresa+" - DBLink: "+this.DBLink);
            if(!abreSocketAS()) return null; // MOD:: NIVEL -21
            if(this.getTipoTransaccion().equals("L")){ // TRANSACCION LOCAL
                boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaCancelar("", "", Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                if(boletoACancelar == null || boletoACancelar.size()==0 || boletoACancelar.size()!=ctdBoletosSolicitados){
                    if(boletoACancelar!=null && boletoACancelar.size()>0) DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>Algun folio no pertene a la misma<br>corrida que los demas.</html>", Color.RED);
                    return null;
                }
                origenCorrida=boletoACancelar.get(0).getClaveCorrida().substring(0,4).replace("_","");
                System.out.println("origenCorrida: "+origenCorrida+" origen: "+Origen);
                origenCorrida = origenCorridaReal(origenCorrida, Origen);
                System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+Origen);
                if(origenCorrida.equals(Origen)){ // realmente es transaccion local
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", "", boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
                else{ // transaccion realmente remota
                    Origen=origenCorrida;
                    setTipoTransaccion("R");
                    getDBLinkClaveOrigen(Origen);
                    boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaCancelar("", this.DBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                    if(boletoACancelar == null || boletoACancelar.size()==0) return null;
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
            }
            else{ // TRANSACCION REMOTA
                boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaCancelar("", this.DBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                if(boletoACancelar == null || boletoACancelar.size()==0) return null;
                origenCorrida=boletoACancelar.get(0).getClaveCorrida().substring(0,4).replace("_","");
                System.out.println("origenCorrida: "+origenCorrida+" origen: "+Origen);
                origenCorrida = origenCorridaReal(origenCorrida, Origen);
                System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+Origen);
                if(origenCorrida.equals(Origen)){ // realmente es transaccion remota
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                     if(!estadoTViaje[0].equals("ABIERTA")){
                        if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                        return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
                else{ // transaccion realmente es extraremota
                    Origen=origenCorrida;
                    getDBLinkClaveOrigen(Origen);
                    boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaCancelar("", this.DBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                    if(boletoACancelar == null || boletoACancelar.size()==0) return null;
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
            }
        }catch(javax.ejb.EJBException ejbex){
            return null;
        }
        setTmsBoletosVentaTbl(boletoACancelar.get(0));
        setVariosTmsBoletosVentaTbl(boletoACancelar);
        Object[][] boleto = new Object[boletoACancelar.size()][13];
        for(int i=0; i<boletoACancelar.size(); i++){
            System.out.println("(CN)Folio = "+getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso());
            System.out.println("Adicional8 = "+getVariosTmsBoletosVentaTbl().get(i).getAdicional8());
            System.out.println("Adicional9 = "+getVariosTmsBoletosVentaTbl().get(i).getAdicional9());
            System.out.println("Adicional15 = "+getVariosTmsBoletosVentaTbl().get(i).getAdicional15());
            System.out.println("ImporteBoleto = "+getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto());
            System.out.println("Viajado = "+getVariosTmsBoletosVentaTbl().get(i).getViajado());
            boleto[i][0] = getVariosTmsBoletosVentaTbl().get(i).getClaveCorrida().substring(0,10);
            boleto[i][1] = formatoFechaHora.format(getVariosTmsBoletosVentaTbl().get(i).getFechaHoraVenta());
            boleto[i][2] = getVariosTmsBoletosVentaTbl().get(i).getNoAsiento();
            boleto[i][3] = getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero();
            boleto[i][4] = getVariosTmsBoletosVentaTbl().get(i).getNombrePasajero()==null ? " " : getVariosTmsBoletosVentaTbl().get(i).getNombrePasajero();
            boleto[i][5] = getVariosTmsBoletosVentaTbl().get(i).getOrigen();
            boleto[i][6] = getVariosTmsBoletosVentaTbl().get(i).getDestino();
            boleto[i][7] = getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto();
            //boleto[i][7] = getVariosTmsBoletosVentaTbl().get(i).getAdicional15();
            boleto[i][8] = getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso();
            boleto[i][9] = getVariosTmsBoletosVentaTbl().get(i).getTipoPago();
            boleto[i][10] = getVariosTmsBoletosVentaTbl().get(i).getEmpresa();
            boleto[i][11] = Double.valueOf(getVariosTmsBoletosVentaTbl().get(i).getAdicional15());//CARGO -getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto();
            //boleto[i][11] = getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto()-Double.valueOf(getVariosTmsBoletosVentaTbl().get(i).getAdicional15());
            boleto[i][12] = getVariosTmsBoletosVentaTbl().get(i).getViajado();
        }
        return boleto;
    }
    
    public Object[][] buscaBoletoAbierto(String Origen, String FolioPreimpreso, String Servicio, String empresa){
        getDBLinkClaveOrigen(Origen);
        List<TmsBoletosVentaTbl> boletoACancelar = null;
        try{
            System.out.println("Busca BA: "+Origen+" - "+FolioPreimpreso+" - "+Servicio+" - "+empresa+" --- "+this.DBLink);
            if(!abreSocketAS()) return null; // MOD:: NIVEL -21
            if(this.getTipoTransaccion().equals("L"))
                boletoACancelar=getTmsVtaFacade().BuscaBoletoAbierto("","",Origen, FolioPreimpreso, Servicio, empresa);
            else
                boletoACancelar=getTmsVtaFacade().BuscaBoletoAbierto("", this.DBLink, Origen, FolioPreimpreso, Servicio, empresa);
        }catch(javax.ejb.EJBException ejbex){
            return null;
        }
        if(boletoACancelar == null || boletoACancelar.size()==0) return null;
        setTmsBoletosVentaTbl(boletoACancelar.get(0));
        Object[][] boleto = new Object[1][10];
        boleto[0][0] = null;
        boleto[0][1] = formatoFechaHora.format(getTmsBoletosVentaTbl().getFechaHoraVenta());
        boleto[0][2] = "";//getTmsBoletosVentaTbl().getNoAsiento();
        boleto[0][3] = getTmsBoletosVentaTbl().getTipoPasajero();
        boleto[0][4] = getTmsBoletosVentaTbl().getNombrePasajero();
        boleto[0][5] = getTmsBoletosVentaTbl().getOrigen();
        boleto[0][6] = getTmsBoletosVentaTbl().getDestino();
        boleto[0][7] = getTmsBoletosVentaTbl().getImporteBoleto();
        boleto[0][8] = getTmsBoletosVentaTbl().getFolioPreimpreso();
        boleto[0][9] = getTmsBoletosVentaTbl().getTipoPago();
        return boleto;
    }
    
    public boolean CancelaBoletoMultiple(String Motivo, String Aprobacion){
        //if(!abreSocketAS()) return false; // MOD:: NIVEL -21
        if(this.getTipoTransaccion().equals("R")) return CancelaBoletoRemotoMultiple(Motivo, Aprobacion);
        TmsBoletosVentaTbl BoletoCancelado=null;
        Timestamp hoy = new Timestamp(thX.getFecha().getTime());
        String[] strCadenas=null;
        boolean cobroCargoCN = false;
        float montoTotalTicket =0;
        String folioProductos="";
        String empresaProductos="";
        String sCad="\n";
        sCad = sCad+"      AUTOBUSES MEXICO PUEBLA";
        sCad = sCad+"\n";
        sCad = sCad+"     ESTRELLA ROJA S.A. DE C.V.";
        sCad = sCad+"\n";
        sCad = sCad+"        Comprobante de Pago ";
        sCad = sCad+"\n";
        SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
        SimpleDateFormat formath = new SimpleDateFormat ("H:mm");
        sCad = sCad+"  FECHA: "+formatf.format(new Date()) +" HORA: "+ formath.format(new Date());
        sCad = sCad+"\n\n";

        for(int i=0; i<getVariosTmsBoletosVentaTbl().size(); i++){
            BoletoCancelado=new TmsBoletosVentaTbl();
            BoletoCancelado.setAdicional1(Motivo);
            BoletoCancelado.setEmpresa(getVariosTmsBoletosVentaTbl().get(i).getEmpresa());
            BoletoCancelado.setServicio(getVariosTmsBoletosVentaTbl().get(i).getServicio());
            BoletoCancelado.setCaja(getUserCon().getCaja());
            BoletoCancelado.setCorteId(getUserCon().getCorteId());
            BoletoCancelado.setClaveCorrida(getVariosTmsBoletosVentaTbl().get(i).getClaveCorrida());
            BoletoCancelado.setClienteId(getVariosTmsBoletosVentaTbl().get(i).getClienteId());
            BoletoCancelado.setNoAsiento(getVariosTmsBoletosVentaTbl().get(i).getNoAsiento());
            BoletoCancelado.setNombrePasajero(getVariosTmsBoletosVentaTbl().get(i).getNombrePasajero());
            BoletoCancelado.setTipoPasajero(getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero());
            BoletoCancelado.setTipoPago(getVariosTmsBoletosVentaTbl().get(i).getTipoPago());
            BoletoCancelado.setReferenciaPago(Aprobacion.length()>25?Aprobacion.substring(0,25):Aprobacion);
            BoletoCancelado.setImporteBoleto(getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto());
            BoletoCancelado.setTipoOperacion("CN");
            BoletoCancelado.setReservacionId(getVariosTmsBoletosVentaTbl().get(i).getReservacionId());
            BoletoCancelado.setBoletoRelacionadoId(getVariosTmsBoletosVentaTbl().get(i).getBoletoId());
            BoletoCancelado.setDiasValidezBoletoAbierto(getVariosTmsBoletosVentaTbl().get(i).getDiasValidezBoletoAbierto());
            BoletoCancelado.setFolioPreimpreso(getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso());
            BoletoCancelado.setFolioBoleto(getVariosTmsBoletosVentaTbl().get(i).getFolioBoleto());
            BoletoCancelado.setCiudadVenta(getUserCon().getTerminalNombre());
            BoletoCancelado.setOrigen(getVariosTmsBoletosVentaTbl().get(i).getOrigen());
            BoletoCancelado.setDestino(getVariosTmsBoletosVentaTbl().get(i).getDestino());
            BoletoCancelado.setTipoTransaccion("L");
            BoletoCancelado.setClaveCajero(getUserCon().getUsuarioNum());
            BoletoCancelado.setFechaHoraVenta(hoy);
            BoletoCancelado.setAutorizadoPor(getUserCon().getAutorizadoPorIdUsuario());
            BoletoCancelado.setFechaHoraAutorizacion(hoy);
            BoletoCancelado.setCreadoPor(getUserCon().getUsuarioId());
            BoletoCancelado.setFechaCreacion(hoy);
            BoletoCancelado.setUltimaActualizacionPor(getUserCon().getUsuarioId());
            BoletoCancelado.setUltimaFechaActualizacion(hoy);
            BoletoCancelado.setAdicional2((getVariosTmsBoletosVentaTbl().get(i).getAdicional2()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional2()) );
            BoletoCancelado.setAdicional3((getVariosTmsBoletosVentaTbl().get(i).getAdicional3()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional3()) );
            BoletoCancelado.setAdicional4((getVariosTmsBoletosVentaTbl().get(i).getAdicional4()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional4()) );
            BoletoCancelado.setAdicional5((getVariosTmsBoletosVentaTbl().get(i).getAdicional5()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional5()) );
            BoletoCancelado.setAdicional6((getVariosTmsBoletosVentaTbl().get(i).getAdicional6()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional6()) );
            BoletoCancelado.setAdicional7((getVariosTmsBoletosVentaTbl().get(i).getAdicional7()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional7()) );
            BoletoCancelado.setAdicional8((getVariosTmsBoletosVentaTbl().get(i).getAdicional8()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional8()) );
            BoletoCancelado.setAdicional9((getVariosTmsBoletosVentaTbl().get(i).getAdicional9()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional9()) );
            BoletoCancelado.setAdicional10((getUserCon().getAplicacionVenta() ?"TAQUILLA" :"CALL_CENTER") );
            BoletoCancelado.setAdicional12((getVariosTmsBoletosVentaTbl().get(i).getAdicional12()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional12()) );
            BoletoCancelado.setAdicional13((getVariosTmsBoletosVentaTbl().get(i).getAdicional13()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional13()) );
            BoletoCancelado.setAdicional14((getVariosTmsBoletosVentaTbl().get(i).getAdicional14()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional14()) );
            BoletoCancelado.setAdicional15((getVariosTmsBoletosVentaTbl().get(i).getAdicional15()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional15()) );
            System.out.println("llama aunExisteBoletoVendido en Cancelacion...");
            if(!aunExisteBoletoVendido(getVariosTmsBoletosVentaTbl().get(i).getOrigen(), getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso(), getVariosTmsBoletosVentaTbl().get(i).getNoAsiento().toString(), getVariosTmsBoletosVentaTbl().get(i).getEmpresa(),getVariosTmsBoletosVentaTbl().get(i).getOrigenCorrida())){
                DialogoAceptar.mostrarDialogo("Cancelación de boleto.","No es posible realizar la cancelación de este boleto.",Color.RED);
                return false;
            }
            try{
                if(!abreSocketAS()) return false; // MOD:: NIVEL -21
                strCadenas=new String[2];
                strCadenas = armaCadenaBolCn(BoletoCancelado, "L", "", getUserCon().getTerminalPrefijoId());
                //System.out.println("Consulta: "+strCadenas[0]+"\n"+strCadenas[1]);
                setBoletoIdCancelado(getTmsVtaFacade().CancelaBoletoLocal(strCadenas[0], strCadenas[1], BoletoCancelado.getClaveCorrida(), BoletoCancelado.getNoAsiento(), getVariosTmsBoletosVentaTbl().get(i).getBoletoId()));
                    cobroCargoCN = false;
                    if(BoletoCancelado.getAdicional15() !=null  && !BoletoCancelado.getAdicional15().equals("") && Double.valueOf(BoletoCancelado.getAdicional15())>0 )
                        cobroCargoCN = true;
                    System.out.println("Guarda los cargos CN como Productos(Local): "+isCobroCargo());
                    System.out.println("Guarda los cargos CN como Productos(Local): "+cobroCargoCN);
                    if(isCobroCargo() || cobroCargoCN)
                    {
                        System.out.println("Producto("+i+"): "+BoletoCancelado.getFolioPreimpreso());
                        System.out.println("Producto("+i+"): "+BoletoCancelado.getAdicional15());
                        System.out.println("Producto("+i+"): "+BoletoCancelado.getTipoOperacion());
                        System.out.println("(CN0)sesionVenta.getTipoPagoCargo(): "+this.getTipoPagoCargo());
                        System.out.println("(CN0)sesionVenta.getAprobacionCargo(): "+this.getAprobacionCargo());
                        System.out.println("(CN0)sesionVenta.getAprobacionCargo(): "+this.getAprobacionCargo());

                        Float preciop =  Float.valueOf(BoletoCancelado.getAdicional15());
                        ProductoCarrito p= new ProductoCarrito(productoCARGOCNId,productoCARGOHOEmpresa,productoCARGOCNNombre,1,BoletoCancelado.getFolioPreimpreso(),productoCARGOCNClave,preciop,preciop,0, preciop,0,this.getUserCon().getCorteId(), this.getUserCon().getCaja(),Long.valueOf(this.getUserCon().getUsuarioNum()),this.getUserCon().getTerminalNombre(),Long.valueOf(this.getUserCon().getTerminalPrefijoId()),this.getUserCon().getUsuarioId(),this.getTipoPagoCargo(),true,"Terminal Las Torres Puebla 1 S.A de C.V.");
                        getTmsVtaFacade().registrarVentaProducto(p.getEmpresa(), p.getProducto(), p.getCantidad(), p.getFolio(), p.getClave_producto(), p.getPrecio_unitario(), p.getSubtotal(), p.getIva(), p.getTotal(), p.getRetencion(), p.getCorteId(), p.getCaja(), p.getCajero(), p.getTerminal(), p.getPrefijo(), p.getUsuarioId(),((this.getTipoPagoCargo().equals(""))?"EFE":this.getTipoPagoCargo()),this.getAprobacionCargo());
                        folioProductos=p.getFolio();
                        empresaProductos=p.getEmpresa();
                        //imprime Ticket de comprobante de pago de cargo
                        sCad = sCad+"  No.Folio: "+p.getFolio();
                        sCad = sCad+"\n";
                        sCad = sCad+"_____________________________________";
                        sCad = sCad+"\n";
                        sCad = sCad+"Cant.\tNombre\t\tPrecio\tImporte";
                        sCad = sCad+"\n";
                        sCad = sCad+"_____________________________________";
                        sCad = sCad+"\n";
                        montoTotalTicket=montoTotalTicket+p.getTotal();
                        sCad = sCad+" "+p.getCantidad()+(((""+p.getCantidad()).length()>2)?" ":"  ")+((p.getProducto().length()<=15)?p.getProducto():p.getProducto().substring(0,15))+(((""+p.getProducto()).length()>7)?"\t":"\t\t")+p.getPrecio_unitario()+(((""+p.getPrecio_unitario()).length()>3)?"\t":"\t\t")+p.getTotal();
                        sCad = sCad+"\n\n";

                    }
            }catch(javax.ejb.EJBException ejbex){
                return false;
            } 

            if(getBoletoIdCancelado()<0) return false;
        }

        if(isCobroCargo() || cobroCargoCN)
        {
            sCad = sCad+"\t\t\t\t\tTotal: $"+montoTotalTicket;
            sCad = sCad+"\n\n";
            String letras= "( "+new cantidad_a_letras().toLetras((long)((new Double(montoTotalTicket)).longValue()))  + "Pesos 00/M.N. )";
            sCad = sCad+letras;
            sCad = sCad+"\n\n";
            sCad = sCad+"\n";
            sCad = sCad+"Cajero: "+((this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()).length()<25?(this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()):(this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()).substring(0,25));//+ClaveCajero+"-"+(NombreCajero.length()<21?NombreCajero:NombreCajero.substring(0,20));
            sCad = sCad+"\n";
            sCad = sCad+" #Caja: "+this.getUserCon().getCaja();
            sCad = sCad+"\n\n   Este comprobante de pago";
            sCad = sCad+"\n"+"      No es Valido";
            sCad = sCad+"\n"+" Como cupon de viaje, como";
            sCad = sCad+"\n"+"boleto de transporte ni como ";
            sCad = sCad+"\n"+"     comprobante Fiscal";
            sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";
            sCad = sCad+"         .";
            imprimeTicketCargo(empresaProductos,sCad,folioProductos);
        }

        return true;
    }
    
    public List<TmsVtaRvnV> obtenerRvnesEnc(String cRvn, String nomResp){
        if(!abreSocketAS()) return null; // MOD:: NIVEL -21
        List<TmsVtaRvnV> RvnesEnc=new ArrayList<TmsVtaRvnV>();
        String string_RvnesEnc = "";
        try {
            if(this.getTipoTransaccion().equals("L")){
                //RvnesEnc=getTmsVtaFacade().queryTmsVtaRvnVFindByCRvnRem("","",cRvn, nomResp);
                string_RvnesEnc=getTmsVtaFacade().queryTmsVtaRvnVFindByCRvnRem2("","",cRvn, nomResp);
            }
            else //RvnesEnc=getTmsVtaFacade().queryTmsVtaRvnVFindByCRvnRem("",this.DBLink,cRvn, nomResp);
                string_RvnesEnc=getTmsVtaFacade().queryTmsVtaRvnVFindByCRvnRem2("",this.DBLink,cRvn, nomResp);
            System.out.println("regresa la cadena: "+string_RvnesEnc );
            if(string_RvnesEnc == null)
                return null;
                    int i;
                    TmsVtaRvnV tmsVtaRvnV;
                    //java.util.List<TmsVtaRvnV> x = new ArrayList<TmsVtaRvnV>();
                    
                    Vector z = new Vector();
                    Vector zx= new Vector();
                    StringTokenizer st = new StringTokenizer(string_RvnesEnc,"|");  
                    System.out.println("string_RvnesEnc.indexOf(|) = "+string_RvnesEnc.indexOf("|"));
                    if(string_RvnesEnc.indexOf("|") == -1)
                    {
                           StringTokenizer t = new StringTokenizer(string_RvnesEnc,",");  
                            Vector x = new Vector();
                            while(t.hasMoreTokens()) 
                            {
                                String d = t.nextToken();
                                d = d.trim();
                                if(d.equals("null") || d.equals("NULL"))
                                    x.add(null);
                                else
                                    x.add(d);
                            }
                        z.add(x);
                    }
                    else
                    {
                       while(st.hasMoreTokens()) 
                       {
                           StringTokenizer t = new StringTokenizer(st.nextToken(),",");  
                           Vector x = new Vector();
                            while(t.hasMoreTokens()) 
                            {
                                String d = t.nextToken();
                                d = d.trim();
                                if(d.equals("null") || d.equals("NULL"))
                                    x.add(null);
                                else
                                    x.add(d);
                            }                           
                           z.add(x);
                       } 
                    }
                    System.out.println("el vector para llenar reservaciones es: "+z);
                        for(i=0; i<z.size(); i++){
                        zx = (Vector) z.get(i);
                        tmsVtaRvnV = new TmsVtaRvnV();
                        tmsVtaRvnV.setReservacionId(Long.valueOf(zx.get(0).toString()));
                        tmsVtaRvnV.setClaveReservacion(zx.get(1).toString());
                        if(zx.get(2)!=null) tmsVtaRvnV.setClienteId(Long.valueOf(zx.get(2).toString()));
                        tmsVtaRvnV.setResponsableReservacion(zx.get(3).toString());
                        tmsVtaRvnV.setCancelable(zx.get(4).toString());
                        tmsVtaRvnV.setTotalRvdos(Long.valueOf(zx.get(5).toString()));
                        tmsVtaRvnV.setCorridaId(Long.valueOf(zx.get(6).toString()));
                        tmsVtaRvnV.setClaveCorrida(zx.get(7).toString());
                        tmsVtaRvnV.setNoAsiento(Long.valueOf(zx.get(8).toString()));
                        tmsVtaRvnV.setTipoPasajero(zx.get(9).toString());
                        try {
                            Timestamp tim = null;
                            tim = tim.valueOf(zx.get(10).toString());
                            //tmsVtaRvnV.setFechaCreacion(new Timestamp(formatoFecha.parse(zx.get(10).toString()).getTime()));
                            tmsVtaRvnV.setFechaCreacion(tim);
                            
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            return null;
                        }
                        tmsVtaRvnV.setEstadoReservacion(zx.get(11).toString());
                        try {
                            Timestamp tim = null;
                            tim = tim.valueOf(zx.get(10).toString());
                            //tmsVtaRvnV.setFechaHoraCorrida(new Timestamp(formatoFecha.parse(zx.get(12).toString()).getTime()));
                            tmsVtaRvnV.setFechaHoraCorrida(tim);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            return null;
                        }
                        tmsVtaRvnV.setOrigen(zx.get(13).toString());
                        tmsVtaRvnV.setDestino(zx.get(14).toString());
                        tmsVtaRvnV.setServicio(zx.get(15).toString());
                        tmsVtaRvnV.setEmpresa(zx.get(16).toString());
                        tmsVtaRvnV.setTotalCndos(Long.valueOf(zx.get(17).toString()));
                        tmsVtaRvnV.setTotalVta(Long.valueOf(zx.get(18).toString()));
                        tmsVtaRvnV.setRutaId(Long.valueOf(zx.get(19).toString()));

                        tmsVtaRvnV.setfCreacion(zx.get(20).toString());
                        tmsVtaRvnV.sethCreacion(zx.get(21).toString());
                        tmsVtaRvnV.setFCorrida(zx.get(22).toString());
                        tmsVtaRvnV.setHCorrida(zx.get(23).toString());
                        RvnesEnc.add(tmsVtaRvnV);
                        System.out.println("agrega un registro a RvnesEnc...");
                    }
            
        }catch(javax.ejb.EJBException ejbex){
            System.out.println("excepcion en el catxh1...");
            ejbex.printStackTrace();
            return null;
        }catch (Exception ex){
            System.out.println("excepcion en el catxh1...");
            ex.printStackTrace();
            return null;
        }
        return RvnesEnc;
    }
    
    private String ObtenerClaveReservacion(long IdEncRvn){
        SimpleDateFormat formatoClaveRvn = new SimpleDateFormat("yyyyMMdd");
        String cRvn=formatoClaveRvn.format(new Date().getTime())+"-"+IdEncRvn;
        return cRvn;
    }
    
    public boolean cancelarReservacionTotal(Object[][] z, long thFechaHoraSistema){
        TmsReservacionesTbl NewLineaReservacion;
        long lineaId;
        long corridaId;
        String ctd;
        Timestamp hoy = new Timestamp(thFechaHoraSistema);
        int asiento=0;
        
        String tipoPasaje="";
        try{
            for (int i = 0; i < z.length; i++) {
                corridaId=Long.valueOf(z[i][11].toString());
                lineaId=Long.valueOf(z[i][10].toString());
                ctd=PeriodoVacacional(z[i]);
                asiento = Integer.valueOf(z[i][2].toString());
                tipoPasaje=z[i][3].toString();
                if(!aunExisteReservacion(z[i][10].toString())){
                    DialogoAceptar.mostrarDialogo("¡Cancelación de reservacion!","Algunas líneas no fueron canceladas.",Color.RED);
                    return false;
                }
                if(!abreSocketAS()) return false; // MOD:: NIVEL -21
                if(this.getTipoTransaccion().equals("L")){
                    if(!getTmsVtaFacade().actualizaEdoReservacionRem("","",lineaId,"CANCELADA", getUserCon().getUsuarioId())) return false;
                    if(!getTmsVtaFacade().LiberaCtdTipoPasajeRvn("", "", corridaId, tipoPasaje, ctd, asiento, "D")) return false;
                }
                else{
                    if(!getTmsVtaFacade().actualizaEdoReservacionRem("", this.DBLink, lineaId,"CANCELADA", getUserCon().getUsuarioId())) return false;
                    if(!getTmsVtaFacade().LiberaCtdTipoPasajeRvn("", this.DBLink, corridaId, tipoPasaje, ctd, asiento, "D")) return false;
                }
                if(!this.AuditarFuncion()){
                    DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Cancelar Reservacion!", "<HTML>La aplicacion será cerrada.<BR>Contacte al administrador del sistema.</HTML>",Color.RED);
                    return false;
                }
            }
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
        return true;
    }
    
    public int cancelarReservacionParcial(Object[][] z, long thFechaHoraSistema){
        TmsReservacionesTbl NewLineaReservacion;
        int lineasCanceladas=0;
        long lineaId;
        long corridaId;
        Timestamp hoy = new Timestamp(thFechaHoraSistema);
        String ctd="";
        int asiento=0;
        
        String tipoPasaje="";
        String bSi;
        try{
            for (int i = 0; i < z.length; i++) {
                bSi=z[i][8].toString();
                corridaId=Long.valueOf(z[i][11].toString());
                ctd=PeriodoVacacional(z[i]);
                lineaId=Long.valueOf(z[i][10].toString());
                asiento = Integer.valueOf(z[i][2].toString());
                tipoPasaje=z[i][3].toString();
                if(bSi.equals("CN")){
                    if(!aunExisteReservacion(z[i][10].toString())){
                        DialogoAceptar.mostrarDialogo("¡Cancelación de reservacion!","Algunas líneas no fueron canceladas.",Color.RED);
                        return -1;
                    }
                    if(!abreSocketAS()) return -1; // MOD:: NIVEL -21
                    if(this.getTipoTransaccion().equals("L")){
                        if(!getTmsVtaFacade().actualizaEdoReservacionRem("","", lineaId,"CANCELADA", getUserCon().getUsuarioId())) return -1;
                        else{
                            lineasCanceladas++;
                            if(!getTmsVtaFacade().LiberaCtdTipoPasajeRvn("", "", corridaId, tipoPasaje, ctd, asiento, "D")) return -1;
                        }
                    }
                    else{
                        if(!getTmsVtaFacade().actualizaEdoReservacionRem("", this.DBLink, lineaId,"CANCELADA", getUserCon().getUsuarioId())) return -1;
                        else{
                            lineasCanceladas++;
                            if(!getTmsVtaFacade().LiberaCtdTipoPasajeRvn("", this.DBLink, corridaId, tipoPasaje, ctd, asiento, "D")) return -1;
                        }
                    }
                    if(!this.AuditarFuncion()){
                        DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Cancelar Reservacion!", "<HTML>La aplicacion será cerrada.<BR>Contacte al administrador del sistema.</HTML>",Color.RED);
                        return -1;
                    }
                }
            }
        }catch(javax.ejb.EJBException ejbex){
            return -1;
        }
        return lineasCanceladas;
    }
    
    public boolean venderReservacionTotal(Object[][] z, long thFechaHoraSistema){        
        TmsReservacionesTbl NewLineaReservacion;
        long lineaId, corridaId;
        int noAsiento;
        Timestamp hoy = new Timestamp(thFechaHoraSistema);
        try{
            for (int i = 0; i < z.length; i++) {
                corridaId=Long.valueOf(z[i][11].toString());
                lineaId=Long.valueOf(z[i][10].toString());
                noAsiento=Integer.valueOf(z[i][2].toString());
                if(!abreSocketAS()) return false; // MOD:: NIVEL -21
                if(this.getTipoTransaccion().equals("L")){
                    if(!getTmsVtaFacade().actualizaEdoReservacionRem("", "", lineaId,"VENDIDA", getUserCon().getUsuarioId())) return false;
                    if(getTmsVtaFacade()._ModificaEstadoAsientoCorrida("",corridaId, noAsiento, "V")!=0) return false;
                }
                else{
                    if(!getTmsVtaFacade().actualizaEdoReservacionRem("", this.DBLink, lineaId,"VENDIDA", getUserCon().getUsuarioId())) return false;
                    if(getTmsVtaFacade()._ModificaEstadoAsientoCorrida(this.DBLink, corridaId, noAsiento, "V")!=0) return false;
                }
                if(!this.AuditarFuncion()){
                    DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Venta Reservacion!", "La aplicacion será cerrada.\nContacte al administrador del sistema.",Color.RED);
                    return false;
                }
            }
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
        return true;
    }
    
    public int venderReservacionParcial(Object[][] z, long thFechaHoraSistema){
        TmsReservacionesTbl NewLineaReservacion;
        int lineasVendidas=0;
        long lineaId;
        long corridaId;
        Timestamp hoy = new Timestamp(thFechaHoraSistema);
        String ctd="";
        int asiento=0;
        
        String tipoPasaje="";
        String bSi;
        try{
            for (int i = 0; i < z.length; i++) {
                bSi=z[i][8].toString();
                corridaId=Long.valueOf(z[i][11].toString());
                lineaId=Long.valueOf(z[i][10].toString());
                ctd=PeriodoVacacional(z[i]);
                asiento = Integer.valueOf(z[i][2].toString());
                tipoPasaje=z[i][3].toString();
                if(!abreSocketAS()) return -1; // MOD:: NIVEL -21
                if(bSi.equals("CN")){
                    if(this.getTipoTransaccion().equals("L")){
                        if(!getTmsVtaFacade().actualizaEdoReservacionRem("", "", lineaId,"CANCELADA", getUserCon().getUsuarioId())) return -1;
                        else{
                            if(!getTmsVtaFacade().LiberaCtdTipoPasaje("", "", corridaId, tipoPasaje, ctd, asiento, "D")) return -1;
                        }
                    }
                    else{
                        if(!getTmsVtaFacade().actualizaEdoReservacionRem("", this.DBLink, lineaId,"CANCELADA", getUserCon().getUsuarioId())) return -1;
                        else{
                            if(!getTmsVtaFacade().LiberaCtdTipoPasaje("", this.DBLink, corridaId, tipoPasaje, ctd, asiento, "D")) return -1;
                        }
                    }
                }
                else{
                    if(this.getTipoTransaccion().equals("L")){
                        if(!getTmsVtaFacade().actualizaEdoReservacionRem("", "", lineaId,"VENDIDA", getUserCon().getUsuarioId())) return -1;
                        else{
                            lineasVendidas++;
                            if(getTmsVtaFacade()._ModificaEstadoAsientoCorrida("",corridaId,  Integer.valueOf(z[i][2].toString()), "V")!=0) return -1;
                        }
                    }
                    else{
                        if(!getTmsVtaFacade().actualizaEdoReservacionRem("", this.DBLink, lineaId,"VENDIDA", getUserCon().getUsuarioId())) return -1;
                        else{
                            lineasVendidas++;
                            if(getTmsVtaFacade()._ModificaEstadoAsientoCorrida(this.DBLink, corridaId,  Integer.valueOf(z[i][2].toString()), "V")!=0) return -1;
                        }
                    }
                }
                if(!this.AuditarFuncion()){
                    DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Venta Reservacion!", "La aplicacion será cerrada.\nContacte al administrador del sistema.",Color.RED);
                    return -1;
                }
            }
        }catch(javax.ejb.EJBException ejbex){
            return -1;
        }
        return lineasVendidas;
    }

    private String PeriodoVacacional(Object[] z){
        if(!z[3].toString().equals("S") && !z[3].toString().equals("E") && !z[3].toString().equals("P")
            && !z[3].toString().equals("C") && !z[3].toString().equals("M")) return "";
        /*if(z[3].toString().equals("S")) return "1";
        if(z[3].toString().equals("C")) return "1";*/
        if(z[3].toString().equals("M")) return "(-1)";
        return "1";
    }
    
    /************************** OPERACIONES REMOTAS **************************/
    public boolean busqCorridaVentaBA(String strRutaOrigen, String strOrigen, String strDestino,
                              Timestamp dtHoy, Timestamp dtFecha, String strServicio, String strEmpresa){
        String conjuntoServicios="'"+strServicio+"'";
        if(strServicio.equals("%")){
            conjuntoServicios="'"+VectorServicios.get(1).toString()+"'";
            for(int i=2; i<VectorServicios.size(); i++)
                conjuntoServicios=conjuntoServicios+",'"+VectorServicios.get(i).toString()+"'";
        }
        
        try{
            if(!abreSocketAS()) return false; // MOD:: NIVEL -21
            boolean mas;
            if(getTipoTransaccion().equals("L"))
                mas = getTmsVtaFacade().queryTmsCorridasVentaBA("", "", strRutaOrigen, strOrigen, formatoFechaHora.format(dtHoy), formatoFechaHora.format(dtFecha), conjuntoServicios, strDestino, strEmpresa);
            else
                mas = getTmsVtaFacade().queryTmsCorridasVentaBA("", this.DBLink, strRutaOrigen, strOrigen, formatoFechaHora.format(dtHoy), formatoFechaHora.format(dtFecha), conjuntoServicios, strDestino, strEmpresa);
            return mas;
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
    }
    
    public boolean CancelaBoletoRemotoMultiple(String Motivo, String Aprobacion){
        String[] strCadenas=null;
        String Campos1="", Campos2="";
        String Valores1="", Valores2="";
        TmsBoletosVentaTbl BoletoCancelado=null;
        Timestamp hoy = new Timestamp(thX.getFecha().getTime());
        boolean cobroCargoCN = false;
        float montoTotalTicket =0;
        String folioProductos="";
        String empresaProductos="";
        String sCad="\n";
        sCad = sCad+"      AUTOBUSES MEXICO PUEBLA";
        sCad = sCad+"\n";
        sCad = sCad+"     ESTRELLA ROJA S.A. DE C.V.";
        sCad = sCad+"\n";
        sCad = sCad+"        Comprobante de Pago ";
        sCad = sCad+"\n";
        SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
        SimpleDateFormat formath = new SimpleDateFormat ("H:mm");
        sCad = sCad+"  FECHA: "+formatf.format(new Date()) +" HORA: "+ formath.format(new Date());
        sCad = sCad+"\n\n";
     for(int i=0; i<getVariosTmsBoletosVentaTbl().size(); i++){
        
        BoletoCancelado= new TmsBoletosVentaTbl();
        
        BoletoCancelado.setEmpresa(getVariosTmsBoletosVentaTbl().get(i).getEmpresa());
        BoletoCancelado.setServicio(getVariosTmsBoletosVentaTbl().get(i).getServicio());
        BoletoCancelado.setCaja(getUserCon().getCaja());
        BoletoCancelado.setCorteId(getUserCon().getCorteId());//getVariosTmsBoletosVentaTbl().get(i).getCorteId());
        BoletoCancelado.setClaveCorrida(getVariosTmsBoletosVentaTbl().get(i).getClaveCorrida());
        BoletoCancelado.setClienteId(getVariosTmsBoletosVentaTbl().get(i).getClienteId());
        BoletoCancelado.setNoAsiento(getVariosTmsBoletosVentaTbl().get(i).getNoAsiento());
        BoletoCancelado.setNombrePasajero(getVariosTmsBoletosVentaTbl().get(i).getNombrePasajero());
        BoletoCancelado.setTipoPasajero(getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero());
        BoletoCancelado.setTipoPago(getVariosTmsBoletosVentaTbl().get(i).getTipoPago());
        BoletoCancelado.setReferenciaPago(Aprobacion.length()>25?Aprobacion.substring(0,25):Aprobacion);
        BoletoCancelado.setImporteBoleto(getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto());
        BoletoCancelado.setTipoOperacion("CN");
        BoletoCancelado.setReservacionId(getVariosTmsBoletosVentaTbl().get(i).getReservacionId());
        BoletoCancelado.setBoletoRelacionadoId(getVariosTmsBoletosVentaTbl().get(i).getBoletoId());
        BoletoCancelado.setDiasValidezBoletoAbierto(getVariosTmsBoletosVentaTbl().get(i).getDiasValidezBoletoAbierto());
        BoletoCancelado.setFolioPreimpreso(getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso());
        BoletoCancelado.setFolioBoleto(getVariosTmsBoletosVentaTbl().get(i).getFolioBoleto());
        BoletoCancelado.setCiudadVenta(getUserCon().getTerminalNombre());
        BoletoCancelado.setOrigen(getVariosTmsBoletosVentaTbl().get(i).getOrigen());
        BoletoCancelado.setDestino(getVariosTmsBoletosVentaTbl().get(i).getDestino());
        BoletoCancelado.setTipoTransaccion("L");
        BoletoCancelado.setClaveCajero(getUserCon().getUsuarioNum());
        BoletoCancelado.setFechaHoraVenta(hoy);
        BoletoCancelado.setAutorizadoPor(getUserCon().getAutorizadoPorIdUsuario());
        BoletoCancelado.setFechaHoraAutorizacion(hoy);
        BoletoCancelado.setCreadoPor(getUserCon().getUsuarioId());
        BoletoCancelado.setFechaCreacion(hoy);
        BoletoCancelado.setUltimaActualizacionPor(getUserCon().getUsuarioId());
        BoletoCancelado.setUltimaFechaActualizacion(hoy);
        BoletoCancelado.setAdicional1(Motivo);
        BoletoCancelado.setAdicional2((getVariosTmsBoletosVentaTbl().get(i).getAdicional2()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional2()) );
        BoletoCancelado.setAdicional3((getVariosTmsBoletosVentaTbl().get(i).getAdicional3()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional3()) );
        BoletoCancelado.setAdicional4((getVariosTmsBoletosVentaTbl().get(i).getAdicional4()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional4()) );
        BoletoCancelado.setAdicional5((getVariosTmsBoletosVentaTbl().get(i).getAdicional5()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional5()) );
        BoletoCancelado.setAdicional6((getVariosTmsBoletosVentaTbl().get(i).getAdicional6()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional6()) );
        BoletoCancelado.setAdicional7((getVariosTmsBoletosVentaTbl().get(i).getAdicional7()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional7()) );
        BoletoCancelado.setAdicional8((getVariosTmsBoletosVentaTbl().get(i).getAdicional8()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional8()) );
        BoletoCancelado.setAdicional9((getVariosTmsBoletosVentaTbl().get(i).getAdicional9()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional9()) );
        BoletoCancelado.setAdicional10((getUserCon().getAplicacionVenta() ?"TAQUILLA" :"CALL_CENTER") );
        BoletoCancelado.setAdicional12((getVariosTmsBoletosVentaTbl().get(i).getAdicional12()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional12()) );
        BoletoCancelado.setAdicional13((getVariosTmsBoletosVentaTbl().get(i).getAdicional13()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional13()) );
        BoletoCancelado.setAdicional14((getVariosTmsBoletosVentaTbl().get(i).getAdicional14()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional14()) );
        BoletoCancelado.setAdicional15((getVariosTmsBoletosVentaTbl().get(i).getAdicional15()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getAdicional15()) );
        
        if(!aunExisteBoletoVendido(getVariosTmsBoletosVentaTbl().get(i).getOrigen(), getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso(), getVariosTmsBoletosVentaTbl().get(i).getNoAsiento().toString(), getTmsBoletosVentaTbl().getEmpresa(),getVariosTmsBoletosVentaTbl().get(i).getOrigenCorrida())){
            DialogoAceptar.mostrarDialogo("Cancelación de boleto.","No es posible realizar la cancelación de este boleto.",Color.RED);
            return false;
        }
        long[] boletoIds;
        try{
            if(!abreSocketAS()) return false; // MOD:: NIVEL -21
            strCadenas=new String[2];
            strCadenas = armaCadenaBolCn(BoletoCancelado, "L", "", getUserCon().getTerminalPrefijoId());
            Campos1=strCadenas[0];
            Valores1=strCadenas[1];
            strCadenas=new String[2];
            strCadenas = armaCadenaBolCn(BoletoCancelado, "R", this.DBLink, getUserCon().getTerminalPrefijoIdRem());
            Campos2=strCadenas[0];
            Valores2=strCadenas[1];
            boletoIds = getTmsVtaFacade().CancelaBoletoRemoto(this.DBLink, Campos1, Valores1, Campos2, Valores2,
                    getVariosTmsBoletosVentaTbl().get(i).getClaveCorrida(), getVariosTmsBoletosVentaTbl().get(i).getNoAsiento(), getVariosTmsBoletosVentaTbl().get(i).getBoletoId());
            cobroCargoCN = false;
            System.out.println("BoletoCancelado.getAdicional15(): "+BoletoCancelado.getAdicional15());
            if(BoletoCancelado.getAdicional15() !=null  && !BoletoCancelado.getAdicional15().equals("") && Double.valueOf(BoletoCancelado.getAdicional15())>0)
                cobroCargoCN = true;
            System.out.println("Guarda los cargos CN como Productos(Remoto): "+isCobroCargo());
            System.out.println("Guarda los cargos CN como Productos(Remoto): "+cobroCargoCN);
            if(isCobroCargo() || cobroCargoCN)
            {
                System.out.println("Producto("+i+"): "+BoletoCancelado.getFolioPreimpreso());
                System.out.println("Producto("+i+"): "+BoletoCancelado.getAdicional15());
                System.out.println("Producto("+i+"): "+BoletoCancelado.getTipoOperacion());
                System.out.println("(CN00)sesionVenta.getTipoPagoCargo(): "+this.getTipoPagoCargo());
                System.out.println("(CN00)sesionVenta.getAprobacionCargo(): "+this.getAprobacionCargo());
                Float preciop =  Float.valueOf(BoletoCancelado.getAdicional15());
                ProductoCarrito p= new ProductoCarrito(productoCARGOCNId,productoCARGOCNEmpresa,productoCARGOCNNombre,1,BoletoCancelado.getFolioPreimpreso(),productoCARGOCNClave,preciop,preciop,0, preciop,0,this.getUserCon().getCorteId(), this.getUserCon().getCaja(),Long.valueOf(this.getUserCon().getUsuarioNum()),this.getUserCon().getTerminalNombre(),Long.valueOf(this.getUserCon().getTerminalPrefijoId()),this.getUserCon().getUsuarioId(),this.getTipoPagoCargo(),true,"Terminal Las Torres Puebla 1 S.A de C.V.");
                getTmsVtaFacade().registrarVentaProducto(p.getEmpresa(), p.getProducto(), p.getCantidad(), p.getFolio(), p.getClave_producto(), p.getPrecio_unitario(), p.getSubtotal(), p.getIva(), p.getTotal(), p.getRetencion(), p.getCorteId(), p.getCaja(), p.getCajero(), p.getTerminal(), p.getPrefijo(), p.getUsuarioId(),((this.getTipoPagoCargo().equals(""))?"EFE":this.getTipoPagoCargo()),this.getAprobacionCargo());
                folioProductos=p.getFolio();
                empresaProductos=p.getEmpresa();
                //imprime Ticket de comprobante de pago de cargo
                sCad = sCad+"  No.Folio: "+p.getFolio();
                sCad = sCad+"\n";
                sCad = sCad+"_____________________________________";
                sCad = sCad+"\n";
                sCad = sCad+"Cant.\tNombre\t\tPrecio\tImporte";
                sCad = sCad+"\n";
                sCad = sCad+"_____________________________________";
                sCad = sCad+"\n";
                montoTotalTicket=montoTotalTicket+p.getTotal();
                sCad = sCad+" "+p.getCantidad()+(((""+p.getCantidad()).length()>2)?" ":"  ")+((p.getProducto().length()<=15)?p.getProducto():p.getProducto().substring(0,15))+(((""+p.getProducto()).length()>7)?"\t":"\t\t")+p.getPrecio_unitario()+(((""+p.getPrecio_unitario()).length()>3)?"\t":"\t\t")+p.getTotal();
                sCad = sCad+"\n\n";            }
        }catch(javax.ejb.EJBException ejbex){
            return false;    
        }
        if(boletoIds[0] < 0 || boletoIds[1] < 0) return false;
        setBoletoIdCancelado(boletoIds[0]);
        setBoletoIdCanceladoRem(boletoIds[1]);
     }
        if(isCobroCargo() || cobroCargoCN)
        {
            sCad = sCad+"\t\t\t\t\tTotal: $"+montoTotalTicket;
            sCad = sCad+"\n\n";
            String letras= "( "+new cantidad_a_letras().toLetras((long)((new Double(montoTotalTicket)).longValue()))  + "Pesos 00/M.N. )";
            sCad = sCad+letras;
            sCad = sCad+"\n\n";
            sCad = sCad+"\n";
            sCad = sCad+"Cajero: "+((this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()).length()<25?(this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()):(this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()).substring(0,25));//+ClaveCajero+"-"+(NombreCajero.length()<21?NombreCajero:NombreCajero.substring(0,20));
            sCad = sCad+"\n";
            sCad = sCad+" #Caja: "+this.getUserCon().getCaja();
            sCad = sCad+"\n\n   Este comprobante de pago";
            sCad = sCad+"\n"+"      No es Valido";
            sCad = sCad+"\n"+" Como cupon de viaje, como";
            sCad = sCad+"\n"+"boleto de transporte ni como ";
            sCad = sCad+"\n"+"     comprobante Fiscal";
            sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";
            sCad = sCad+"         .";
            imprimeTicketCargo(empresaProductos,sCad,folioProductos);
        }

     return true;
    }
    
    /************************************************************************/   
    public boolean insertarDatosRecoleccion(Object[][] datosReco, int rowNum, long numeroSupId){
        if(!abreSocketAS()) return false; // MOD:: NIVEL -21
        try{
            String valores = ","+getUserCon().getCorteId()+","+idRecol+","+numeroSupId+","+getUserCon().getUsuarioId()+","+getEmpresaId(getUserCon().getEmpresaPrincipal())+","+
                      getUserCon().getCajaId()+","+datosReco[0][0].toString()+","+datosReco[0][1].toString()+","+datosReco[0][2].toString()+","+getUserCon().getTerminalPrefijoId();
            System.out.println(valores);
            int iVar = getTmsVtaFacade()._RegistraRecoleccion(valores);
            if(iVar!=0) return false;
        }catch(javax.ejb.EJBException ejbex){
            return false;    
        }
        return true;
    }
        
    public boolean esPrimeraRecoleccion() {
        //if(!abreSocketAS()) return false; // MOD:: NIVEL -21
        boolean r;
        try{
            r=!getTmsVtaFacade().existeRecoleccionEnSesion(String.valueOf(getUserCon().getCorteId()));
        }catch(javax.ejb.EJBException ejbex){
            return false;    
        }
        return r;
    }
    
    /************************************************************************/
    public String getValorParametro(String ParamComponente, int NivelError){
        int indice = 0;
        indice = codigos.indexOf(ParamComponente);
        //System.out.println("valor "+indice);
        if(indice<0){
            if(NivelError==-1) return null;
            if(NivelError==-2) return "THIS.DISPOSE()";
            Toolkit.getDefaultToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡Parametro "+ParamComponente+" incorrecto!", "Contacte al administrador del sistema.", Color.RED);
            return "THIS.DISPOSE()";
        }
        else return valores.get(indice).toString();
    }
    
    public double getTarifaTramo(String ParamComponente, int opcion){
        int indice = 0;
        indice = tramos.indexOf(ParamComponente+getUserCon().getMonedaTX());
        if(indice<0){
            if(opcion==-1) return 0;
            Toolkit.getDefaultToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡Tarifa no configurada!", "<html>Codigo de busqueda: "+ParamComponente+".<br>Contacte al administrador del sistema.</html>", Color.RED);
            return 0;
        }
        //else return Math.ceil(Double.valueOf(tarifas.get(indice).toString()));
        else return Double.valueOf(tarifas.get(indice).toString());
            
    }
    
    
     public double getTarifaTramo1(String ParamComponente, int opcion){
        int indice = 0;
        int primeravez = 0;
        Vector tars = new Vector();
        double tarifaCorrecta = 0;
        ///System.out.println("vector tramos1 :  "+ tramos1);
        do{
            //System.out.println("indice en inicio: "+ indice);
            if (primeravez == 0){
            indice = tramos1.indexOf(ParamComponente+getUserCon().getMonedaTX(),indice);
            //System.out.println("indice despues de primera vez: "+ indice);
            primeravez = 1;
            }
            else{
                indice = tramos1.indexOf(ParamComponente+getUserCon().getMonedaTX(),indice+1);
                //System.out.println("indice despues de segunda vez: "+ indice);
            }
            //System.out.println("indice antes de agregar en vector: "+ indice);
            if(indice>=0)
                tars.add(indice);
        }while(indice>=0);
        
        //System.out.println("vector size: "+ tars.size());
        //System.out.println("vector tars: "+ tars);
        if(tars.size()<=0)
        {
            Toolkit.getDefaultToolkit().beep();
            DialogoAceptar.mostrarDialogo("¡Tarifa no configurada!", "<html>Codigo de busqueda(2): "+ParamComponente+".<br>Contacte al administrador del sistema.</html>", Color.RED);
            return 0;
        }
        else
        {
          for(int i=0; i<tars.size();i++) 
          {
//              if(Math.ceil(Double.valueOf(tarifas1.get(Integer.valueOf(tars.get(i).toString())).toString()))>tarifaCorrecta)
//                tarifaCorrecta = Math.ceil(Double.valueOf(tarifas1.get(Integer.valueOf(tars.get(i).toString())).toString()));
              if(Double.valueOf(tarifas1.get(Integer.valueOf(tars.get(i).toString())).toString())>tarifaCorrecta)
                tarifaCorrecta = Double.valueOf(tarifas1.get(Integer.valueOf(tars.get(i).toString())).toString());
          }
        }
       return tarifaCorrecta;
                
//        if(indice<0){
//            if(opcion==-1) return 0;
//            Toolkit.getDefaultToolkit().beep();
//            DialogoAceptar.mostrarDialogo("¡Tarifa no configurada!", "<html>Codigo de busqueda(2): "+ParamComponente+".<br>Contacte al administrador del sistema.</html>", Color.RED);
//            return 0;
//        }
//        else return Math.ceil(Double.valueOf(tarifas1.get(indice).toString()));
    }
    
    private int InicializarSesionVenta(){
        int error=0;
        String vParam;
        
        vParam=getValorParametro("P_VLREMPPRI", 0);
        if(vParam.equals("THIS.DISPOSE()")) return -69;
        getUserCon().setEmpresaPrincipal(vParam);
        
        // NUEVOS PARAMETROS CONFIDENCIALES
        vParam=getValorParametro("P_SYSCBOSTANDR", -2);
        getUserCon().setSysCobroBancario(vParam.equals("THIS.DISPOSE()")?false:(vParam.equals("N")?false:true));
        System.out.println("        +++++++++ Inicia"+new Date() +" getSysCobroBancario +++++++++");
        if(getUserCon().getSysCobroBancario()){
            vParam=getValorParametro("P_EMV_URL", 0);
            if(vParam.equals("THIS.DISPOSE()")) return -69;
            getUserCon().setDbgSetUrl(vParam);
            
            vParam=getValorParametro("P_EMV_BRANCH", 0);
            if(vParam.equals("THIS.DISPOSE()")) return -69;
            getUserCon().setBs_Branch(vParam);

            vParam=getValorParametro("P_EMV_USER", 0);
            if(vParam.equals("THIS.DISPOSE()")) return -69;
            getUserCon().setBs_User(vParam);

            vParam=getValorParametro("P_EMV_PASSWORD", 0);
            if(vParam.equals("THIS.DISPOSE()")) return -69;
            getUserCon().setBs_Pwd(vParam);

            vParam=getValorParametro("P_EMV_MERCHANT", 0);
            if(vParam.equals("THIS.DISPOSE()")) return -69;
            getUserCon().setTx_MerchantP(vParam);

            vParam=getValorParametro("P_EMV_MERCHANT_MOTO", 0);
            if(vParam.equals("THIS.DISPOSE()")) return -69;
            getUserCon().setTx_MerchantS(vParam);
            System.out.println("P_EMV_MERCHANT_MOTO = "+vParam);

            vParam=getValorParametro("P_EMV_MERCHANT_AMEX", 0);
            if(vParam.equals("THIS.DISPOSE()")) return -69;
            getUserCon().setTx_MerchantAMEX(vParam);

            vParam=getValorParametro("P_EMV_MERCHANT_AMEX_MOTO", 0);
            if(vParam.equals("THIS.DISPOSE()")) return -69;
            getUserCon().setTx_MerchantAMEXMOTO(vParam);

        }
        System.out.println("        --------- Termina"+new Date() +" getSysCobroBancario ---------");
        System.out.println(new Date() +" branch user password merchant merchantAMEX "+getUserCon().getBs_Branch()+" - "+getUserCon().getBs_User()+" - "+getUserCon().getBs_Pwd()+" - "+getUserCon().getTx_MerchantP()+" - "+getUserCon().getTx_MerchantS()+" - "+getUserCon().getTx_MerchantAMEX()+" - "+(getUserCon().getSysCobroBancario()?"SANTANDER "+getUserCon().getDbgSetUrl()+" "+getUserCon().getBs_Company():"BANCOMER"));
        //
        
        vParam=getValorParametro("P_LIMFONMAX", 0);
        if(vParam.equals("THIS.DISPOSE()")) return -69;
        {
            System.out.println(new Date() +" Verifica el Fondo maximo: "+getUserCon().getAplicacionVenta()+" "+vParam);
            if(getUserCon().getAplicacionVenta())
                getUserCon().setFondoMax(Double.valueOf(vParam));
            else
                getUserCon().setFondoMax(Double.valueOf("0.0")); 
        }
        System.out.println(new Date() +" despues de Verificar el Fondo maximo: "+getUserCon().getFondoMax());
//        if(getUserCon().getAplicacionVenta()){
        if(true){//Modificacion
            error = 0;

            if (!cargarSinDatoInicial){
                error=mostrarDlgDatosIni(nuevaSesionSinFondoInicial, false);
                if(error!=0) return error;
            }
            else{
                error=mostrarDlgDatosIni(nuevaSesionSinFondoInicial, true);
                if(error!=0) return error;
            }
        }
        
        vParam=this.getValorParametro("P_TMPDIABAB", 0);
        if(vParam.equals("THIS.DISPOSE()")) return -69;
        getUserCon().setDiasValBab(Integer.valueOf(vParam));
        
        Date auxFecha = null;
        String strFecha;
        try {
            strFecha=getValorParametro("P_TMPINIVAC", 0);
            if(strFecha.equals("THIS.DISPOSE()")) return -69;
            auxFecha = formatoFechaHora.parse(strFecha);
        } catch (ParseException ex) {
            DialogoAceptar.mostrarDialogo("¡Parametro P_TMPINIVAC incorrecto!", "Contacte al administrador del sistema.", Color.RED);
            return -69;
        }
        getUserCon().setIniVac(auxFecha);
        
        try {
            strFecha=getValorParametro("P_TMPFINVAC", 0);
            if(strFecha.equals("THIS.DISPOSE()")) return -69;
            auxFecha=formatoFechaHora.parse(strFecha);
        } catch (ParseException ex) {
            //System.out.println("FECHA FINAL "+strFecha);
            DialogoAceptar.mostrarDialogo("¡Parametro P_TMPFINVAC incorrecto!", "Contacte al administrador del sistema.", Color.RED);
            return -69;
        }
        getUserCon().setFinVac(auxFecha);
        
        vParam=getValorParametro("P_LIMMINBOL", 0);
        if(vParam.equals("THIS.DISPOSE()")) return -69;
        getUserCon().setBolNvtImp(Integer.valueOf(vParam));
        
        vParam=getValorParametro("P_LIMEFEREC", 0);
        if(vParam.equals("THIS.DISPOSE()")) return -69;
        getUserCon().setAvisoRecoleccion(Double.valueOf(vParam));
        
        vParam = getValorParametro("P_LIMEFENVT", 0);
        if(vParam.equals("THIS.DISPOSE()")) return -69;
        getUserCon().setLimVenta(Double.valueOf(vParam));
        
        vParam = getValorParametro("P_TMPDESCAN", 0);
        if(vParam.equals("THIS.DISPOSE()")) return -69;
        getUserCon().setMinDesCan(Integer.valueOf(vParam));
        
        vParam = getValorParametro("P_TMPCANRES", 0);
        if(vParam.equals("THIS.DISPOSE()")) return -69;
        getUserCon().setMinAntRv(Integer.valueOf(vParam));
        
        vParam = getValorParametro("P_MTOMINTJT", 0);
        if(vParam.equals("THIS.DISPOSE()")) return -69;
        getUserCon().setMtoMinTjt(Double.valueOf(vParam));
        
        vParam = getValorParametro("P_DIGITARBANC", 0);
        getUserCon().setDigitaTB(vParam.equals("THIS.DISPOSE()")?false:(vParam.equals("S")?true:false));
        
        getUserCon().setListaRutasNoVenta(getValorParametro("P_LSTRUTASEXC"+getUserCon().getCaja(),-1));
        return 0;
    }
        
    private int existeTerminal(){
        if(!abreSocketAS()) return -21;
        System.out.println(getUserCon().getCajaUbicacionId()+" --ubicaciones --"+getUserCon().getTerminalId());
        if(getUserCon().getCajaUbicacionId() != getUserCon().getTerminalId()) return 11;
        buscarDireccionMAC bMac = null;
        try {
            bMac = new buscarDireccionMAC();
        } catch (IOException ex) {
            return 12;
        }
        if(!bMac.buscarMac(getUserCon().getCajaMAC())){
            return 12;
        }
        return 0;
    }
    
    private int obtieneTmsEstadosV(){
        if(!abreSocketAS()) return -21;
        try{
            tmsEstadosV = getTmsVtaFacade().queryTmsEstadosVAll();
        }catch(javax.ejb.EJBException ejbex){
            return 18;
        }
        if(tmsEstadosV == null) return 18;
        return 0;
    }
    
    private String getTerminalNombre(long terminalId){
        for(int i=0; i<tmsEstadosV.size(); i++)
            if(tmsEstadosV.get(i).getEstadoId()==terminalId) return tmsEstadosV.get(i).getEstadoNombre();
        return null;
    }
       
    public long getTerminalLealtadId(String terminal){
        for(int i=0; i<tmsEstadosV.size(); i++)
            if(tmsEstadosV.get(i).getEstadoNombre().equals(terminal)) return tmsEstadosV.get(i).getEstadoId();
        return -1;
    }
    
    
    public String getEstadoCorte() {
        return estadoCorte;
    }

    public void setEstadoCorte(String estadoCorte) {
        this.estadoCorte = estadoCorte;
    }
    
    public String[][] getFoliosSesion() {
        return foliosSesion;
    }

    public void setFoliosSesion(String[][] foliosSesion) {
        getUserCon().setFolioPreimpreso(foliosSesion);
        this.foliosSesion = foliosSesion;
    }
    
    public long getEmpresaId(String nce){
        for(int i=0; i<getUserCon().getEmpresasOfertantes().length; i++){
            //System.out.println(nce+" "+getUserCon().getEmpresasOfertantes()[i][0]);
            if(getUserCon().getEmpresasOfertantes()[i][1].equals(nce)) return Long.valueOf(getUserCon().getEmpresasOfertantes()[i][0]);
        }
        return -1;
    }
    
    public String getEmpresasId(String nce){
        for(int i=0; i<getUserCon().getEmpresasOfertantes().length; i++)
            if(getUserCon().getEmpresasOfertantes()[i][1].equals(nce)) return getUserCon().getEmpresasOfertantes()[i][0];
        return "";
    }
    
    public long getEmpresaIdEspecial(String nce){
        for(int i=0; i<getUserCon().getEmpresasOfertantes().length; i++)
            if(getUserCon().getEmpresasOfertantes()[i][1].equals(nce)) return Long.valueOf(getUserCon().getEmpresasOfertantes()[i][0]);
        return -1;
    }
    
    public String getEmpresaNombreCorto(String nce){
        for(int i=0; i<getUserCon().getEmpresasOfertantes().length; i++){
            if(getUserCon().getEmpresasOfertantes()[i][1].equals(nce)) return getUserCon().getEmpresasOfertantes()[i][2];
        }
        return null;
    }
    
    public boolean getEmpresaCtrlFolio(String nce){
        for(int i=0; i<getUserCon().getEmpresasConFolios().length; i++)
            if(getUserCon().getEmpresasConFolios()[i][1].equals(nce))
                 return true;
        return false;
    }
    
    public boolean validarClaveSupervisorConFuncion(String funcionNumero, String usuarioNumero,String usuarioPwd) {
        if(!abreSocketAS()) return false;
        EncriptarMD5 pwdEnc = new EncriptarMD5();
        long valido = -1;
        try {
            valido = getTmsVtaFacade().esUsuarioSupervisorConFuncion(funcionNumero, usuarioNumero, pwdEnc.encriptar(usuarioPwd));
        } catch (EJBException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        if(valido==-1) return false;
        getUserCon().setAutorizadoPorIdUsuario(valido);
        getUserCon().setAutorizadoPorNumeroUsuario(usuarioNumero);
        return true;
    }
    
    public int insertarDatosSesionInicio(String[][] empresasNombre, double FondoInicial,long[] empresaFolioInicial,
            long[] empresaFolioFinal, String[] nombres){
        String valores="", parametros="";
        try{
            // parametros generales (corte nuevo inicio de sesion y fondo inicial)
            parametros=",S,S,"+idFondini+","+FondoInicial+",0,"+idInises+","+getUserCon().getEmpresaPrincipal()+","+getEmpresaId(getUserCon().getEmpresaPrincipal())+","+
                        getUserCon().getUsuarioNom()+"-"+getUserCon().getTerminalNombre()+","+getUserCon().getAutorizadoPorIdUsuario()+
                        ","+getUserCon().getUsuarioId()+","+getUserCon().getTerminalPrefijoId()+","+getUserCon().getCajaId();
            // foliado por empresa
            valores="";
            int i;
            for(i=0;i<empresaFolioInicial.length;i++)
                valores=valores+"|,"+idFolini+","+idFolfin+","+getEmpresaId(nombres[i])+","+empresaFolioInicial[i]+","+empresaFolioFinal[i];
            
            System.out.println("Envio de SP Folios: "+valores+" - Empresas: "+empresaFolioInicial.length+" PARAMETROS: "+parametros);
            long[] r=new long[2];
            r=getTmsVtaFacade()._RegistraFolios(parametros, valores, empresaFolioInicial.length);
            if(r[0]!=0) return 21;
            getUserCon().setCorteId(r[1]);
            System.out.println("Corte nuevo: "+getUserCon().getCorteId());
        }catch(javax.ejb.EJBException ejbex){
            return 21;
        }
        return 0;
    }
    
    public int insertarDatosRefoliado(boolean esNuevaSesion,String[][] empresasNombre, 
            long[] empresaFolioInicial, long[] empresaFolioFinal, String[] nombres) {
        String valores="", parametros="";
        try{
            // parametros generales (reinicio de sesion)
            parametros=",N,"+(esNuevaSesion ? "S" : "N")+",0,0,"+getUserCon().getCorteId()+","+idInises+","+getUserCon().getEmpresaPrincipal()+","+getEmpresaId(getUserCon().getEmpresaPrincipal())+","+
                        getUserCon().getUsuarioNom()+"-"+getUserCon().getTerminalNombre()+","+getUserCon().getAutorizadoPorIdUsuario()+
                        ","+getUserCon().getUsuarioId()+","+getUserCon().getTerminalPrefijoId()+","+getUserCon().getCajaId();
            if(esNuevaSesion){
                System.out.println("INICIO SESION:"+getUserCon().getUsuarioNom()+"-"+getUserCon().getTerminalNombre());
                idFolini = idRefolini;
                idFolfin = idRefolfin;
            }else{
                System.out.println("ReFoliaje en refoliaje ========= "+getUserCon().getCorteId());
                idFolini = idRefolini;
                idFolfin = idRefolfin;
            }
            
            valores="";
            int i;
            for(i=0;i<empresaFolioInicial.length;i++)
                valores=valores+"|,"+idFolini+","+idFolfin+","+getEmpresaId(nombres[i])+","+empresaFolioInicial[i]+","+empresaFolioFinal[i];
            
            System.out.println("Envio de SP Folios: "+valores+" - Empresas: "+empresaFolioInicial.length+" PARAMETROS: "+parametros);
            long[] r = new long[2];
            r=getTmsVtaFacade()._RegistraFolios(parametros, valores, empresaFolioInicial.length);
            if(r[0]!=0) return 22;
            System.out.println("Sigue Corte: "+getUserCon().getCorteId());
        }catch(javax.ejb.EJBException ejbex){
            return 22;
        }catch(Exception ex){
            ex.printStackTrace();
            return 22;
        }
        return 0;
    }
    
    private int mostrarDlgDatosIni(boolean datosSinFondoIni, boolean mismaSesion) {
        System.out.println("mostrarDlgDatosIni (mismaSesion) = "+mismaSesion);
        Object[] registro = new Object[5];
        int error = 0;
        System.out.println("fondo antes "+getUserCon().getFondoMax());
        if(datosSinFondoIni){ // refolio
            // nuevo codigo de fondo inicial
            double xFondoInicial = obtieneFondoInicialOriginal();
            if(xFondoInicial==-1){
                DialogoAceptar.mostrarDialogo("¡Fondo Inicial!","Carga de Fondo inicial invalido.", Color.RED);
                return 666;
            }
            System.out.println("fondo despues (solo en reinicio) "+xFondoInicial);
            getUserCon().setFondoMax(xFondoInicial);
             
            System.out.println("REFOLIO - DOS OPCIONES "+mismaSesion);
            System.out.println("+++++++++ Inicia"+new Date() +" jDlgDatosIniciales(REFOLIO) +++++++++");
            dlgDatosIni = new jDlgDatosIniciales(false,esNuevaSesion, getUserCon().getFondoMax(), this, mismaSesion);
            dlgDatosIni.centrarDialogo();
            dlgDatosIni.inicializarEmpresasNombre(getUserCon().getFolioUnico());
            Vector xer = cargarFoliosSesionEspecial();
            System.out.println("--------- Termina"+new Date() +" jDlgDatosIniciales(REFOLIO) ---------");
            if(this.SISTEMA_SALIDA_INMEDIATA()) return 666;
            if(xer.size()!=0){
                System.out.println("OPCION 2: REFOLIADO NUEVO");
                dlgDatosIni = null;
                return refoliado(xer);
            }
            dlgDatosIni.mostrarFoliosSesion();
            // PERMISO MOSTRAR REFOLIOS
            if(!ValidaFuncionUsuario("5003")){
                jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(this,"5003", "Reinicio de Sesion");
                dlg.setVisible(true);
                if(!dlg.getRespuesta()) return 666;
            }
            dlgDatosIni.setTipoMsg(69);
            dlgDatosIni.setVisible(true);
            error=dlgDatosIni.getAccesoVenta();
            if(error==0) return 0;
            else return 666;
        }else{ // folio
            System.out.println("fondo en inicio "+getUserCon().getFondoMax());
            System.out.println("FOLIO");
            System.out.println("+++++++++ Inicia"+new Date() +" jDlgDatosIniciales(FOLIO) +++++++++");
            dlgDatosIni = new jDlgDatosIniciales(getUserCon().getFondoMax(), this);
            //dlgDatosIni.setDirecto(false);
            dlgDatosIni.centrarDialogo();
            dlgDatosIni.inicializarEmpresasNombre(getUserCon().getFolioUnico());
            System.out.println("--------- Termina"+new Date() +" jDlgDatosIniciales(FOLIO ---------");
            // PERMISO MOSTRAR REFOLIOS
            if(!ValidaFuncionUsuario("5003")){
                jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(this,"5003", "Folios Iniciales");
                dlg.setVisible(true);
                if(!dlg.getRespuesta()) return 666;
            }
            dlgDatosIni.setVisible(true);
            error=dlgDatosIni.getAccesoVenta();
            if(error==0){
                String[][] foliosVal= new String[dlgDatosIni.getEmpresaFolioInicial().length][3];
                for(int i=0; i<dlgDatosIni.getEmpresaFolioInicial().length; i++){
                    foliosVal[i][0] = String.valueOf(dlgDatosIni.getEmpresaFolioInicial()[i]);
                    foliosVal[i][1] = String.valueOf(dlgDatosIni.getEmpresaFolioFinal()[i]);
                    foliosVal[i][2] = dlgDatosIni.getNombreEmpresaFinal()[i];
                    setFoliosSesion(foliosVal);
                }
                return 0;
            }
            else return 666;
        }
    }
    
    public void cargarFoliosSesion(){
        Vector vEmpresas=new Vector();
        int empresasNum=1;
        if(!this.getUserCon().getFolioUnico()){
            empresasNum=0;
            for(int i=0; i<getUserCon().getEmpresasConFolios().length; i++){
                    vEmpresas.add(getUserCon().getEmpresasConFolios()[i][1]);
                    empresasNum++;
            }
        }
        String[][] foliosValor = new String[empresasNum][3];
        Object[] z;
        Vector empresasRefoliar = new Vector();
        int i=0, ix;
        long diferencia;
        try{
            for(ix=0; ix<getUserCon().getEmpresasConFolios().length; ix++){
                System.out.println("Obtener Folios para: "+getUserCon().getEmpresasConFolios()[ix][1]);
                if(this.isVtaBolBco(""))
                {
                    z = new Object[3];
                    z[0] = 0; z[1] = 0; z[2] =0;
                }
                else
                    z=getTmsVtaFacade().obtieneFolios(getUserCon().getCorteId(), Long.valueOf(getUserCon().getEmpresasConFolios()[ix][0]));

                if(z==null){
                    DialogoAceptar.mostrarDialogo("¡No es posible cargar folios!","Contacte al administrador del sistema.",Color.RED);
                    this._SetEstadoSistema(es_SALIDA_INMEDIATA);
                    return;
                }
                if(z[2]!=null){
                    if(Long.valueOf(z[0].toString())>Long.valueOf(z[2].toString())) foliosValor[i][0]=z[0].toString();
                    else{
                        if(Long.valueOf(z[1].toString())<Long.valueOf(z[2].toString())){ // refoliar al inicio
                            diferencia = Math.abs(Long.valueOf(z[2].toString())-Long.valueOf(z[1].toString()));
                            if(diferencia==1){
                                foliosValor[i][0]="-";
                                empresasRefoliar.add(getUserCon().getEmpresasConFolios()[ix][1]);
                            }
                            else foliosValor[i][0]=z[0].toString();
                        }
                        else foliosValor[i][0]=z[2].toString();
                    }
                }
                else foliosValor[i][0]=z[0].toString();

                foliosValor[i][1]=z[1].toString();
                if(vEmpresas.size()==0)
                    foliosValor[i][2]=getUserCon().getEmpresaPrincipal();
                else
                    foliosValor[i][2]=vEmpresas.get(i).toString();
                i++;
            }
        }catch(javax.ejb.EJBException ejbex){
            this._SetEstadoSistema(es_SALIDA_INMEDIATA);
            return;
        }
        setFoliosSesion(foliosValor);
    }
    
    public Vector cargarFoliosSesionEspecial(){
        Vector vEmpresas=new Vector();
        int empresasNum=1;
        if(!this.getUserCon().getFolioUnico()){
            empresasNum=0;
            for(int i=0; i<getUserCon().getEmpresasConFolios().length; i++){
                    vEmpresas.add(getUserCon().getEmpresasConFolios()[i][1]);
                    empresasNum++;
                }
        }
        int i=0, ix;
        long diferencia;
        String[][] foliosValor = new String[empresasNum][3];
        Object[] z;
        Vector empresasRefoliar = new Vector();
        try{
            for(ix=0;ix<getUserCon().getEmpresasConFolios().length;ix++){
                System.out.println("Obtener Folios para 1: "+getUserCon().getEmpresasConFolios()[ix][1]);
                System.out.println("    +++++++++ Inicia"+new Date() +" cargarFoliosSesionEspecial +++++++++");
                if(this.isVtaBolBco(""))
                {
                    z = new Object[3];
                    z[0] = 0; z[1] = 0; z[2] =0;
                }
                else
                    z=getTmsVtaFacade().obtieneFolios(getUserCon().getCorteId(), Long.valueOf(getUserCon().getEmpresasConFolios()[ix][0]));
                System.out.println("    --------- Termina"+new Date() +" cargarFoliosSesionEspecial ---------");
                if(z==null){
                    DialogoAceptar.mostrarDialogo("¡No es posible cargar folios!","Contacte al administrador del sistema.",Color.RED);
                    //System.exit(0);
                    this._SetEstadoSistema(es_SALIDA_INMEDIATA);
                    return null;
                }
                if(z[2]!=null){
                    if(Long.valueOf(z[0].toString())>Long.valueOf(z[2].toString())) foliosValor[i][0]=z[0].toString();
                    else{
                        if(Long.valueOf(z[1].toString())<Long.valueOf(z[2].toString())){ // refoliar al inicio
                            diferencia=Math.abs(Long.valueOf(z[2].toString())-Long.valueOf(z[1].toString()));
                            if(diferencia==1){
                                foliosValor[i][0]="-";
                                empresasRefoliar.add(getUserCon().getEmpresasConFolios()[ix][1]);//foliosValor[i][0]=z[0].toString();
                            }
                            else foliosValor[i][0]=z[0].toString();
                        }
                        else foliosValor[i][0]=z[2].toString();
                    }
                }
                else foliosValor[i][0]=z[0].toString();
                foliosValor[i][1]=z[1].toString();
                if(vEmpresas.size()==0)
                    foliosValor[i][2]=getUserCon().getEmpresaPrincipal();
                else
                    foliosValor[i][2]=vEmpresas.get(i).toString();
                i++;
            }
        }catch(javax.ejb.EJBException ejbex){
            this._SetEstadoSistema(es_SALIDA_INMEDIATA);
            return null;
        }
        setFoliosSesion(foliosValor);
        return empresasRefoliar;
    }

    public boolean finalizarVenta() {
        //Finaliza la conexion con el WS
            //service = null;
            //port = null;
        
        // if(!getUserCon().getAplicacionVenta()) return true;
        /***** FIN DE SESION ****/
        try{
            String parametros=","+getUserCon().getCorteId()+","+idFinses+","+getEmpresaId(getUserCon().getEmpresaPrincipal())+","+
                        getUserCon().getUsuarioNom()+"-"+getUserCon().getTerminalNombre()+","+getUserCon().getAutorizadoPorIdUsuario()+
                        ","+getUserCon().getUsuarioId()+","+getUserCon().getTerminalPrefijoId()+","+getUserCon().getCajaId();
            if(getTmsVtaFacade()._FinalizarVenta(parametros)!=0) return false;
            return true;
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
    }
    
    public int getRutaIdVA(String s,String o,String d){
        Vector tmsServicioOrigsDestsV;
        for(int i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(tmsServicioOrigsDestsV.get(8).toString().equals(s) && tmsServicioOrigsDestsV.get(4).toString().equals(o) && tmsServicioOrigsDestsV.get(10).toString().equals(d) ) return Integer.valueOf(tmsServicioOrigsDestsV.get(0).toString());
        }
        return -1;
    }    
    
    public long getServicioId(String ns){
        Vector tmsServicioOrigsDestsV;
        for(int i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(tmsServicioOrigsDestsV.get(7).toString().equals(ns)) return Long.valueOf(tmsServicioOrigsDestsV.get(5).toString());
        }
        return -1;
    }
    
    public String getServicioLetra(String ns){
        Vector tmsServicioOrigsDestsV;
        for(int i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(tmsServicioOrigsDestsV.get(7).toString().equals(ns)) return tmsServicioOrigsDestsV.get(8).toString();
        }
        return "";
    }
    
    public String getServicioNombre(String ns){
        Vector tmsServicioOrigsDestsV;
        for(int i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(tmsServicioOrigsDestsV.get(8).toString().equals(ns))  {
                System.out.println("tmsServicioOrigsDestsV= "+tmsServicioOrigsDestsV);

                return tmsServicioOrigsDestsV.get(7).toString();
            }
        }
        return "";
    }
    
    public Vector getOrigenesDBLink() {
        return OrigenDBLink;
    }
    
    public Vector getOrigenes() {
        return vOrigenes;
    }
    
    public Vector getVectorServicios() {
        return VectorServicios;
    }
    
    public String getVectorServiciosReal(String serv) {
        if(serv.equals("TODOS")) return serv;
        String strServicio=null;
        Vector tmsServicioOrigsDestsV=null;
        for(int i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(serv.equals(tmsServicioOrigsDestsV.get(8).toString())) return tmsServicioOrigsDestsV.get(7).toString();
        }
        return strServicio;
    }
    
    public Vector getServicios() {
        return vServicios;
    }
    
    public Vector getSs() {
        return vS;
    }
    
    public Vector getSsReal() {
        return vSReal;
    }
    
    public Vector getDestinos() {
        return vDestinos;
    }
    
    public void setFechaHoraSistemaVenta(Date fechaHoraSistemaVenta) {
        this.fechaHoraSistemaVenta = fechaHoraSistemaVenta;
    }
    
    public Date getFechaHoraSistemaVenta(){ return this.fechaHoraSistemaVenta; }
    
    public String getFechaHoraSistemaVentaLealtad(){ return this.getTmsVtaFacade()._ObtieneFechaHoraBDLealtad(); }
    
    public String getDiaSistemaVentaLealtad(){  
        String fech = this.getTmsVtaFacade()._ObtieneDiaVenta(); 
        fech = fech.replace('[',' ');
        fech = fech.replace(']',' ');
        fech = fech.trim();
        Timestamp f = null;
        f = Timestamp.valueOf(fech+" 00:00:00");
        return   new SimpleDateFormat("EEEE").format(f.getTime()).toUpperCase();
    }    
    
    private void ConexionBA(String strOrigen, String strDestino, String strServicio){
        Vector tmsServicioOrigsDestsV = new Vector();
        //0RUTA_ID, 1RUTA_NUMERO, 2RUTA_NOMBRE, 3ORIGEN_ID, 4ORIGEN, 5SERVICIO_ID, 6SERVICIO_CLAVE, 7SERVICIO, 8LETRA,
        //9DESTINO_ID, 10DESTINO, 11TRAMO_VTA_REGRESO
        int i;
        for(i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(tmsServicioOrigsDestsV.get(7).toString().equals(strServicio) &&
               tmsServicioOrigsDestsV.get(10).toString().equals(strDestino) &&
               tmsServicioOrigsDestsV.get(4).toString().equals(strOrigen)){
                OrigenConexionReal=tmsServicioOrigsDestsV.get(13).toString();
                if(OrigenConexionReal.equals(getUserCon().getTerminalNombre()))
                    OrigenConexionReal=tmsServicioOrigsDestsV.get(15).toString();
                System.out.println("ORIGEN CONEXION REAL "+OrigenConexionReal);
                return;
            }
        }
    }

    public String customFormat(String pattern, double value ) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);
    }
    
    private int refoliado(Vector refoliarEmpresas){
        if(!ValidaFuncionUsuario("5003")){
            jDlgAutorizaSupervisor dlg = new jDlgAutorizaSupervisor(this, "5003", "Refoliar");
            dlg.setVisible(true);
            if(!dlg.getRespuesta()) return 666;
        }
        jDlgRefoliar dlgDatosIni = new jDlgRefoliar(false, false, getUserCon().getFondoMax(), this, refoliarEmpresas, true);
        dlgDatosIni.centrarDialogo();
        dlgDatosIni.inicializarEmpresasNombre(getUserCon().getFolioUnico());
        if(dlgDatosIni.mostrarFoliosSesion()==-2) return 16;
        dlgDatosIni.setVisible(true);
        if(dlgDatosIni.getAccesoVenta()==0){
            cargarFoliosSesion(); // MODIFICADO PARA REFOLIAJE
            if(this.SISTEMA_SALIDA_INMEDIATA()) return 666;
            getUserCon().setFolioPreimpreso(getFoliosSesion());
            FoliosActuales();
            if(!AuditarFuncion()){
                DialogoAceptar.mostrarDialogo("¡Error al tratar de auditar la funcion Refoliar!", "La aplicacion será cerrada.\nContacte al administrador del sistema.",Color.RED);
                return 666;
            }
            DialogoAceptar.mostrarDialogo("¡Refoliado correcto!", "Puede continuar la venta...",Color.RED);
            return 0;
        }
        return 16;
    }
    
    public void setUltimoBoletoId(long b){ this.ultimoBoletoId = b; }
    
    public long getUltimoBoletoId(){ return this.ultimoBoletoId; }
    
    public void setFolioSistema(long pFolioSistema){ this.folioSistema = pFolioSistema; }
    
    public long getFolioSistema(){ return this.folioSistema; }
    
    public long siguienteFolioSistema(){ return (++this.folioSistema); }

    public boolean existeCorrida(String claveCorrida, String strLiga){
        if(!abreSocketAS()) return false; // MOD:: NIVEL -21
        try{
            return getTmsVtaFacade().existeCorrida(claveCorrida, strLiga);
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
    }
    
    public int estamosEnVacaciones(){
        if(!abreSocketAS()) return -1; // MOD:: NIVEL -21
        try{
            return getTmsVtaFacade().estamosEnVacaciones();
        }catch(javax.ejb.EJBException ejbex){
            return -1;
        }
    }
    
    public boolean sonVacaciones(){
        if(!abreSocketAS()) return false; // MOD:: NIVEL -21
        try{
            switch(getTmsVtaFacade().estamosEnVacaciones()){
                case -1: return false;
                case  0: return false;
                case  1: return true;
            }
            return false;
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
    }
    
    public boolean queTiposPasajeVendo(String letraServicio){
        tps = new Vector();
        Vector t;
        System.out.println("strDestinoX en VtaBA::: "+strDestinoX);
//        if(this.strDestinoX.equals("AFRPA")||this.strDestinoX.equals("AFRPM")
//          ||this.strDestinoX.equals("AFRTA")||this.strDestinoX.equals("AFRTM")
//          ||this.strDestinoX.equals("SIXPA")||this.strDestinoX.equals("SIXPM")
//          ||this.strDestinoX.equals("SIXTA")||this.strDestinoX.equals("SIXTM")
//          ||this.strDestinoX.equals("CIUPA")||this.strDestinoX.equals("CIUPM")
//          ||this.strDestinoX.equals("GRAPA")||this.strDestinoX.equals("GRAPM")
//          ||this.strDestinoX.equals("TEOPA")||this.strDestinoX.equals("TEOPM")){
//          for(int i=0; i<tmsTiposPasajeroTbl.size(); i++){
//            if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("ADULTO")){
//                System.out.println("entre adulto");
//                t = new Vector();
//                t.addElement(tmsTiposPasajeroTbl.get(i).getTipoPasajeroId());
//                t.addElement(tmsTiposPasajeroTbl.get(i).getNombreTipo());
//                t.addElement(tmsTiposPasajeroTbl.get(i).getLetraTipo());
//                t.addElement(tmsTiposPasajeroTbl.get(i).getPctDescuento());
//                t.addElement(tmsTiposPasajeroTbl.get(i).getRutaId());
//                tps.addElement(t);
//            }
//           }
//           return true;
//        }
        
        int sinoVac = estamosEnVacaciones();
        if(sinoVac==-1) return false;
        
        int i, h;
        // tratamiento a tipos especiales
        Object[][] tiposPasajeVta;
        try{
            if(!abreSocketAS()) return false; // MOD:: NIVEL -21
            tiposPasajeVta = getTmsVtaFacade().queTiposPasajeVendo(letraServicio);
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
        if(tiposPasajeVta==null) return false;
        
        String letraTipo="";
        int disponibles=0;
        
        if(sinoVac==0){ // excluye tipos especiales
            boolean continuarFor = true;
            for(i=0; i<tmsTiposPasajeroTbl.size(); i++){
                if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("ESTUDIANTE")) continue;
                if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("PROFESOR")) continue;
                
                if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("SENECTUD") || tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("ESPECIAL")){
                    
                    for(h=0; h<tiposPasajeVta.length; h++){
                        if(tmsTiposPasajeroTbl.get(i).getLetraTipo().equals(tiposPasajeVta[h][0].toString())){
                            disponibles=Integer.valueOf(tiposPasajeVta[h][1].toString());
                            if(disponibles==0){
                                continuarFor=false;
                                break;
                            }
                        }
                    }
                    if(!continuarFor) continue;
                }
                
                t = new Vector();
                t.addElement(tmsTiposPasajeroTbl.get(i).getDescuentRutaId());// TipoPasajeroId());
                t.addElement(tmsTiposPasajeroTbl.get(i).getNombreTipo());
                t.addElement(tmsTiposPasajeroTbl.get(i).getLetraTipo());
                t.addElement(tmsTiposPasajeroTbl.get(i).getPctDescuento());
                t.addElement(tmsTiposPasajeroTbl.get(i).getRutaId());
                t.addElement(tmsTiposPasajeroTbl.get(i).getRedondeo());
                tps.addElement(t);
            }
            return true;
        }
        
        // vacaciones
        for(i=0; i<tmsTiposPasajeroTbl.size(); i++){ // tipos normales
            if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("ESTUDIANTE")) continue;
            if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("PROFESOR")) continue;
            if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("SENECTUD")) continue;
            if(tmsTiposPasajeroTbl.get(i).getNombreTipo().equals("ESPECIAL")) continue;

            t = new Vector();
            t.addElement(tmsTiposPasajeroTbl.get(i).getDescuentRutaId());//getTipoPasajeroId());
            t.addElement(tmsTiposPasajeroTbl.get(i).getNombreTipo());
            t.addElement(tmsTiposPasajeroTbl.get(i).getLetraTipo());
            t.addElement(tmsTiposPasajeroTbl.get(i).getPctDescuento());
            t.addElement(tmsTiposPasajeroTbl.get(i).getRutaId());
            t.addElement(tmsTiposPasajeroTbl.get(i).getRedondeo());
            tps.addElement(t);
        }
        
        for(h=0; h<tiposPasajeVta.length; h++){ // tipos especiales
            letraTipo=tiposPasajeVta[h][0].toString();
            disponibles=Integer.valueOf(tiposPasajeVta[h][1].toString());
            if(letraTipo.equals("S") && disponibles==0) continue;
            if(letraTipo.equals("E") && disponibles==0) continue;
            if(letraTipo.equals("P") && disponibles==0) continue;
            if(letraTipo.equals("C") && disponibles==0) continue;
            
            for(i=0; i<tmsTiposPasajeroTbl.size(); i++){
                if(tmsTiposPasajeroTbl.get(i).getLetraTipo().equals(letraTipo)){
                    t = new Vector();
                    t.addElement(tmsTiposPasajeroTbl.get(i).getDescuentRutaId());// getTipoPasajeroId());
                    t.addElement(tmsTiposPasajeroTbl.get(i).getNombreTipo());
                    t.addElement(tmsTiposPasajeroTbl.get(i).getLetraTipo());
                    t.addElement(tmsTiposPasajeroTbl.get(i).getPctDescuento());
                    t.addElement(tmsTiposPasajeroTbl.get(i).getRutaId());
                    t.addElement(tmsTiposPasajeroTbl.get(i).getRedondeo());
                    tps.addElement(t);
                }
            }
        }
        
        return true;
    }

    public int procesaTipoPasaje(long corridaId, Vector vTP, int signo){ // signo dec=1 inc=-1
        String tipoPasaje;
        vRTP=new Vector();
        vCRTP=new Vector();
        try{
            int ctd, i;
            for(i=0; i<vTP.size(); i++){ // que y cuantos tipos de pasaje diferentes
                tipoPasaje=vTP.get(i).toString();
                if(!tipoPasaje.equals("M") && !tipoPasaje.equals("E") && !tipoPasaje.equals("P") && !tipoPasaje.equals("S") && !tipoPasaje.equals("C")
                && !tipoPasaje.equals("O")) continue;
                
                ctd=1*signo; // quien decrementa

                if(tipoPasaje.equals("S")) ctd=-1*signo;
                else if(tipoPasaje.equals("C")) ctd=-1*signo;
                else if(tipoPasaje.equals("E")) ctd=-1*signo;
                else if(tipoPasaje.equals("P")) ctd=-1*signo;
                insertoTipoCtd(tipoPasaje, ctd);
            }
            cadTipoPasaje="";
            if(vRTP.size()==0) return 0;
            //armo cadena para update
            for(i=0; i<vRTP.size(); i++){
                if(vRTP.get(i).toString().equals("M")) cadTipoPasaje+="MENORES_CORRIDA=MENORES_CORRIDA+"+vCRTP.get(i).toString()+",";
                else if(vRTP.get(i).toString().equals("S")) cadTipoPasaje+="SENECTUD_CORRIDA=SENECTUD_CORRIDA+"+vCRTP.get(i).toString()+",";
                else if(vRTP.get(i).toString().equals("E")) cadTipoPasaje+="ESTUDIANTES_CORRIDA=ESTUDIANTES_CORRIDA+"+vCRTP.get(i).toString()+",";
                else if(vRTP.get(i).toString().equals("P")) cadTipoPasaje+="PROFESORES_CORRIDA=PROFESORES_CORRIDA+"+vCRTP.get(i).toString()+",";
                else if(vRTP.get(i).toString().equals("C")) cadTipoPasaje+="CORTESIAS_CORRIDA=CORTESIAS_CORRIDA+"+vCRTP.get(i).toString()+",";
            }
            //
            cadTipoPasaje=cadTipoPasaje.substring(0,cadTipoPasaje.length()-1);
            //
            if(!abreSocketAS()) return -1; // MOD:: NIVEL -21
            System.out.println("_ModificaTipoPasajeVarios: "+corridaId+" - "+cadTipoPasaje);
            int r;
            if(this.getTipoTransaccion().equals("L")){
                r=getTmsVtaFacade()._ModificaTipoPasajeVarios("", corridaId, cadTipoPasaje);
                return r;
            }
            r=getTmsVtaFacade()._ModificaTipoPasajeVarios(this.DBLink, corridaId, cadTipoPasaje);
            return r;
        }catch(javax.ejb.EJBException ejbex){
            return -1;
        }
    }
    
    private void insertoTipoCtd(String tipoPasajex, int signo) {
        if(vRTP.size()==0){
            vRTP.add(tipoPasajex);
            vCRTP.add(1*signo);
        }
        else{
            int pos=yaExisteTipo(tipoPasajex);
            if(pos!=-1){
                vCRTP.set(pos,(Integer.valueOf(vCRTP.get(pos).toString())+(1*signo)));
            }
            else{
                vRTP.add(tipoPasajex);
                vCRTP.add(1*signo);
            }
        }
    }
    
    private int yaExisteTipo(String tipoPasajeX) {
        for(int i=0; i<vRTP.size(); i++)
            if(vRTP.get(i).toString().equals(tipoPasajeX)) return i;
        return -1;
    }
    
    public boolean aunExisteBoletoVendido(String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa, String origenCorrida){
        try{
            List<TmsBoletosVentaTbl> boletoACancelar = null; String[] estadoTViaje;
            if(!abreSocketAS()) return false; // MOD:: NIVEL -21
            if(this.getTipoTransaccionAuxiliar().equals("L")){
                System.out.println("Busca boleto vendido para cancelar (L): DBLinkAuxiliar: "+""+"  Origen: "+Origen + " OrigenCorrida: "+origenCorrida);
                //23/04/2014 VAGL
                //boletoACancelar = getTmsVtaFacade().BuscaBoletoVendido("", "", Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                boletoACancelar = getTmsVtaFacade().BuscaBoletoVendido("", "", origenCorrida, FolioPreimpreso, noAsiento, nombreEmpresa);
                if(boletoACancelar == null || boletoACancelar.size()==0) return false;
                estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", "", boletoACancelar.get(0).getClaveCorrida());
                if(estadoTViaje!=null && estadoTViaje[0].equals("RECAUDADA")) return false;
            }
            else{
                System.out.println("Busca boleto vendido para cancelar (R): DBLinkAuxiliar: "+getDBLinkAuxiliar()+"  Origen: "+Origen);
                //boletoACancelar = getTmsVtaFacade().BuscaBoletoVendido("", getDBLinkAuxiliar(), Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                boletoACancelar = getTmsVtaFacade().BuscaBoletoVendido("", getDBLinkAuxiliar(), origenCorrida, FolioPreimpreso, noAsiento, nombreEmpresa);
                if(boletoACancelar == null || boletoACancelar.size()==0) return false;
                estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", getDBLinkAuxiliar(), boletoACancelar.get(0).getClaveCorrida());
                if(estadoTViaje!=null && estadoTViaje[0].equals("RECAUDADA")) return false;
            }
            return true;
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
    }
        
    public boolean aunExisteBoletoVendidoParaHO(String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa,String ptipo, String folio,String OrigenCorrida){
        try{
            System.out.println("Busca HO: "+Origen+" - "+FolioPreimpreso+" - "+noAsiento+" - "+nombreEmpresa+" ---- "+pDBLink+"  Transaccion: "+this.tipoTransaccionAuxiliar+ "    OrigenCorrida: "+OrigenCorrida);
            List<TmsBoletosVentaTbl> boletoACancelar = null; String[] estadoTViaje;
            if(!abreSocketAS()) return false; // MOD:: NIVEL -21
            //System.out.println("Transaccion a buscar: "+ptipo);   
            /*if(ptipo.equals("FO") || ptipo.equals("FT") )
            {
               if(ptipo.equals("FO"))
               {
                System.out.println("****** Antes de llamar BuscaBoletoValidoparaFO ...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
                if(!getTmsVtaFacade().BuscaBoletoValidoparaFO("", pDBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa, folio))
                   return false;
                System.out.println("****** Despues de llamar BuscaBoletoValidoparaFO ...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());               
               }
               else
               {dd
                 String pDBLink2 = pDBLink;
                 if(!pDBLink2.equals("")) pDBLink2 = "@" + pDBLink2;

                  qry = "SELECT BOLETO_ID FROM TMS_BOLETOS_VENTA_TBL"+pDBLink2+" WHERE TIPO_OPERACION = 'FT' AND ADICIONAL4 = '"+folio+"' ";
//                 qry = "SELECT * FROM " +
//                 "TMS_BOLETOS_VENTA_TBL"+pDBLink2+" BVT  " +
//                 "WHERE "+
//                 "BVT.ORIGEN IN (SELECT TMSV.ORIGEN FROM TMS_SERVICIO_ORIGS_DESTS_V"+pDBLink2+" TMSV WHERE (TMSV.RUTA_ORIGEN = '"+Origen+"')) "+
//                    "AND	 BVT.FOLIO_PREIMPRESO = '"+FolioPreimpreso+"' "+
//                    "AND	 BVT.NO_ASIENTO = '"+noAsiento+"' "+
//                    "AND	 BVT.EMPRESA = '"+nombreEmpresa+"' " +
//                    "AND         BVT.BOLETO_ID IN (SELECT BVT2.BOLETO_RELACIONADO_ID FROM TMS_BOLETOS_VENTA_TBL BVT2 where BVT2.TIPO_OPERACION = 'FT')";
//                qry = "SELECT BF.BOLETO_REFERENCIADO_ID  from " +
//                "TMS_BOLETOS_REFERENCIADOS_TBL"+pDBLink2+" BF where BF.FOLIO_PREIMPRESO = '"+FolioPreimpreso+"' and  BF.ESTADO_REFERENCIA = 'P'";
                System.out.println("****** Antes de llamar BuscaBoletoValidoparaFT ...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
                   if(!getTmsVtaFacade().BuscaBoletoValidoparaFT("", pDBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa,folio))
                      return false;
                System.out.println("****** Despues de llamar BuscaBoletoValidoparaFT ...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());               
                   
               }
            }*/
            System.out.println("****** Antes de llamar BuscaBoletoVendidoParaHO ...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
            boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaHO("", pDBLink, OrigenCorrida, FolioPreimpreso, noAsiento, nombreEmpresa);
            System.out.println("****** Despues de llamar BuscaBoletoVendidoParaHO ...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
            if(boletoACancelar == null || boletoACancelar.size()==0) return false;
            System.out.println("****** Antes de llamar queryBoletoConTJAbierta ...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
            estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", pDBLink, boletoACancelar.get(0).getClaveCorrida());
            System.out.println("****** Despues de llamar queryBoletoConTJAbierta ...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
            if(estadoTViaje!=null && estadoTViaje[0].equals("RECAUDADA")) return false;
            
            return true;
        }catch(javax.ejb.EJBException ejbex){
            ejbex.printStackTrace();
            return false;
        }
    }
    
    public boolean aunExisteBoletoAbierto(String pDBLink, String Origen, String FolioPreimpreso, String Servicio, String empresa){
        try{
            List<TmsBoletosVentaTbl> boletoACancelar = null;
            if(!abreSocketAS()) return false; // MOD:: NIVEL -21
            boletoACancelar=getTmsVtaFacade().BuscaBoletoAbierto("",pDBLink,Origen, FolioPreimpreso, Servicio, empresa);
            if(boletoACancelar == null || boletoACancelar.size()==0) return false;
            return true;
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
    }
    
    public boolean aunExisteReservacion(String reservacionId){
        if(!abreSocketAS()) return false; // MOD:: NIVEL -21
        try{
            if(this.getTipoTransaccionAuxiliar().equals("L")) return getTmsVtaFacade().existeReservacion(reservacionId, "");
            return getTmsVtaFacade().existeReservacion(reservacionId, getDBLinkAuxiliar());
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
    }

    public boolean bloqueaPermisoFolios(String valor){
        if(!abreSocketAS()) return false;
        try{
            return getTmsVtaFacade().bloqueaPermisoFolios(valor);
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }catch(NoBloqueoFoliosException nbfex){
            System.out.println(nbfex.getMessage());
            return false;
        }
    }

    public boolean abreSocketAS(){
        return true;
       /* Socket s = null;
        try {
            s = new Socket(getUserCon().getIpAS(), getUserCon().getPortAS());
            //s.setSoTimeout(15000);
            if(etiqueta==null) return true;
            if(etiqueta.getBackground().equals(Color.RED)) etiqueta.setBackground(Color.GREEN);
            return true;
        }catch( IOException e ) {
            if(etiqueta==null) return false;
            if(etiqueta.getBackground().equals(Color.GREEN)) etiqueta.setBackground(Color.RED);
            return false;
        }catch(Exception err){
            if(etiqueta==null) return false;
            if(etiqueta.getBackground().equals(Color.GREEN)) etiqueta.setBackground(Color.RED);
            return false;
        }*/
    }
 
    /******************** consultas para optimizar ***************************/
    public int getParametrosIniciales(long usuarioId, String caja_nombre, String terminal_nombre){
        if(!abreSocketAS()) return -21;
        String cadena = getTmsVtaFacade().getParametrosIniciales(usuarioId, caja_nombre);
        if(cadena==null) return 2;
        System.out.println(" ***  Parametros Iniciales  --->  "+cadena);
        String valor;
        StringTokenizer srtToken = new StringTokenizer(cadena,",");
        valor=srtToken.nextToken();
        boolean huboCortesdeUsuario = true;
        if(valor.equals("-1") && getUserCon().getAplicacionVenta()){
            huboCortesdeUsuario = false;
            //return 3; // sesion invalida
        }
        if(valor.equals("-68") && getUserCon().getAplicacionVenta()) return 4; // corte E
        //if(getUserCon().getAplicacionVenta()){
        if(true){//Modificacion
            if(!huboCortesdeUsuario) getUserCon().setCorteId(0);
            else getUserCon().setCorteId(Long.valueOf(valor));
        }
        else getUserCon().setCorteId(0);
        valor=srtToken.nextToken();
        if(valor.equals("-1") && getUserCon().getAplicacionVenta()){
            if(huboCortesdeUsuario) return -1;
        }
        if(huboCortesdeUsuario)
            edoCorte = (valor.equals("1")?"P":(valor.equals("2")?"R":"E"));
        else edoCorte = "-1";
        if(edoCorte.equals("E") && getUserCon().getAplicacionVenta()) return 4; // corte E
        valor=srtToken.nextToken();
        if(valor.equals("-1") && getUserCon().getAplicacionVenta()){
            if(huboCortesdeUsuario) return -1;
        }
        modoCorte = valor.equals("1") ? true : false;
        cargarSinDatoInicial = modoCorte;
        nuevaSesionSinFondoInicial = modoCorte;
        esNuevaSesion = modoCorte;
        System.out.println("val_is: corte, edoCorte, modo "+getUserCon().getCorteId()+" "+edoCorte+" "+modoCorte);
        valor=srtToken.nextToken();
        if(valor.equals("-1") && getUserCon().getAplicacionVenta()){
            if(!huboCortesdeUsuario) return 3; // sesion invalida
            return 6;
        }
        idUltFolVend=Long.valueOf(valor);
        valor=srtToken.nextToken();
        idRecol=Long.valueOf(valor);
        valor=srtToken.nextToken();
        idInises=Long.valueOf(valor);
        valor=srtToken.nextToken();
        idFondini=Long.valueOf(valor);
        valor=srtToken.nextToken();
        idFolini=Long.valueOf(valor);
        valor=srtToken.nextToken();
        idFolfin=Long.valueOf(valor);
        valor=srtToken.nextToken();
        idRefolini=Long.valueOf(valor);
        valor=srtToken.nextToken();
        idRefolfin=Long.valueOf(valor);
        valor=srtToken.nextToken();
        idFinses=Long.valueOf(valor);
        valor=srtToken.nextToken();
        if(valor.equals("-1")) return 7;
        getUserCon().setCajaUbicacionId(Long.valueOf(valor));
        valor=srtToken.nextToken();
        getUserCon().setCajaMAC(valor);
        valor=srtToken.nextToken();
        getUserCon().setCajaNumero(valor);
        valor=srtToken.nextToken();
        getUserCon().setCajaId(Long.valueOf(valor));
        valor=srtToken.nextToken();
        System.out.println("setCaja: "+valor);
        getUserCon().setCaja(valor);
        tmsVtaFacade.setCaja(this.userCon.getCaja());
        valor=srtToken.nextToken();
        getUserCon().setPrefijo(valor);
        valor=srtToken.nextToken();
        //if(valor.equals("-1") && getUserCon().getAplicacionVenta()) return 8;
        System.out.println("Folio de sistena = "+valor);
        //if(getUserCon().getAplicacionVenta()) setFolioSistema(Long.valueOf(valor));
        //else setFolioSistema(0);
        if(valor.equals("-1")) return 8;
        setFolioSistema(Long.valueOf(valor));
        ////////////////////
        valor=srtToken.nextToken();
        if(getUserCon().getAplicacionVenta())
            EFECTIVO_CAJA=Double.valueOf(valor);
        valor=srtToken.nextToken();
        if(valor.equals("-1")) return 9;
        Date fHSistemaVenta = null;
        try {
            fHSistemaVenta = new Date(formatoFechaHora.parse(valor).getTime()+60000);
        } catch (ParseException ex) {
            return -1;
        }
        setFechaHoraSistemaVenta(fHSistemaVenta);
        return 0; // carga completa y exitosa
    }
    
    public int getParametrosIniciales2(String caja_nombre){
        if(!abreSocketAS()) return -21;
        String cadena = getTmsVtaFacade().getParametrosIniciales2(caja_nombre);
        if(cadena==null) return 13;
        codigos = new Vector();
        valores = new Vector();
        String valor;
        StringTokenizer strToken = new StringTokenizer(cadena,"|");
        StringTokenizer strTokenCampos;
        int i,j=strToken.countTokens();
        for(i=0; i<j; i++){
            valor=strToken.nextToken();
            strTokenCampos = new StringTokenizer(valor,",");
            codigos.add(strTokenCampos.nextToken());
            valores.add(strTokenCampos.nextToken());
        }
        
        return 0; // carga completa y exitosa
    }
    
    public int getParametrosIniciales3(long caja_id){
        if(!abreSocketAS()) return -21;
        System.out.println("caja_id "+caja_id);
        String cadena = getTmsVtaFacade().getParametrosIniciales3(caja_id);
        if(cadena==null) return 14;
        
        String bloque, registro, campo;
        StringTokenizer strToken = new StringTokenizer(cadena,";");
        int i, j, k, iCampos, iRegistros, iBloques=strToken.countTokens();
        
        StringTokenizer strTokenBloque;
        StringTokenizer strTokenRegistro;
        
        Object[][] x = null;
        String[][] z = null;
        if(iBloques<4) return 14;
        System.out.println("Param Empresas (Impresoras): "+cadena);
        for(i=0; i<iBloques; i++){
            bloque=strToken.nextToken();
            if(i==2){
                getUserCon().setFolioUnico(bloque.equals("S") ? true : false);
                continue;
            }
            strTokenBloque = new StringTokenizer(bloque,"|");
            iRegistros=strTokenBloque.countTokens();
            if(i<2)
                z=new String[iRegistros][3];
            else{
                if(iRegistros==0) return 14;
                x=new Object[iRegistros][8];
            }
            for(j=0; j<iRegistros; j++){
                registro=strTokenBloque.nextToken();
                strTokenRegistro = new StringTokenizer(registro,",");
                iCampos = strTokenRegistro.countTokens();
                if(i<2)
                    for(k=0; k<iCampos; k++){
                        campo = strTokenRegistro.nextToken();
                        z[j][k] = campo;
                    }
                else
                    for(k=0; k<iCampos; k++){
                        campo = strTokenRegistro.nextToken();
                        x[j][k] = campo;
                    }
            }
            switch(i){
                case 0: getUserCon().setEmpresasOfertantes(z); break;
                case 1: getUserCon().setEmpresasConFolios(z); break;
                case 3:
                    if(x!=null){
                        if(x.length==1 && x[0][3].toString().equals("VOUCHERS")) return 14;
                        getUserCon().setEmpresasImpresora(x);
                    }
                    else
                        if(getUserCon().getAplicacionVenta()) return 14;
                    break;
            }
        }
        //if(getUserCon().getAplicacionVenta())
            if(getUserCon().getMsgImpVOU()!=null && !getUserCon().getMsgImpVOU().equals(""))
                DialogoAceptar.mostrarDialogo("TMS Venta.", "Impresoras para voucher no configuradas.", Color.RED);
        return 0; // carga completa y exitosa
    }
    
    public int getTramosTarifas(){
        if(!abreSocketAS()) return -21;
        String cadena = getTmsVtaFacade().getTramosTarifas();
        String cadena1 = getTmsVtaFacade().getTramosTarifas1();
        if(cadena==null) return 17;
        if(cadena1==null) return 17;
        tramos = new Vector();
        tramos1 = new Vector();
        tarifas = new Vector();
        tarifas1 = new Vector();
        String valor;
        StringTokenizer strToken = new StringTokenizer(cadena,"|");
        StringTokenizer strTokenCampos;
        int i,j=strToken.countTokens();
        for(i=0; i<j; i++){
            valor=strToken.nextToken();
            strTokenCampos = new StringTokenizer(valor,",");
            tramos.add(strTokenCampos.nextToken());
            tarifas.add(strTokenCampos.nextToken());
        }
        StringTokenizer strToken1 = new StringTokenizer(cadena1,"|");
        StringTokenizer strTokenCampos1;
        String valor1;
        int i1,j1=strToken1.countTokens();
        for(i1=0; i1<j1; i1++){
            valor1=strToken1.nextToken();
            strTokenCampos1 = new StringTokenizer(valor1,",");
            tramos1.add(strTokenCampos1.nextToken());
            tarifas1.add(strTokenCampos1.nextToken());
        }
        
        return 0; // carga completa y exitosa
    }
    
    public Object[][] obtenerCorridasVenta(String strRutaOrigen, String strOrigen, String strDestino,
                              String dtHoy, String strServicio, String strEmpresa){
        int i=0;
        String conjuntoServicios=strServicio;
        if(strServicio.equals("%")){
            conjuntoServicios=""+VectorServicios.get(1).toString()+"";
            for(i=2; i<VectorServicios.size(); i++)
                conjuntoServicios=conjuntoServicios+","+VectorServicios.get(i).toString()+"";
        }
        //nuevo
        String conjuntoEmpresas=strEmpresa;
        if(strEmpresa.equals("%")){
            conjuntoEmpresas=""+getUserCon().getEmpresasOfertantes()[0][1]+"";
            for(i=1; i<getUserCon().getEmpresasOfertantes().length; i++)
                conjuntoEmpresas=conjuntoEmpresas+","+getUserCon().getEmpresasOfertantes()[i][1]+"";
        }
        //
        String strCorridas;
        if(!abreSocketAS()) return null; // MOD:: NIVEL -21
        strCorridas = getTmsVtaFacade().obtenerCorridasVenta("", this.DBLink, strRutaOrigen, strOrigen, dtHoy, conjuntoServicios, strDestino, conjuntoEmpresas);
        if(strCorridas==null) return null;
        return SISTEMA_DECODE(strCorridas);
    }
    
    private Object[][] SISTEMA_DECODE(String strCorridas) {
        StringTokenizer strToken = new StringTokenizer(strCorridas,"|");
        StringTokenizer strTokenCampos = null;
        String valor = null;
        int i, j, fin;
        Object[][] oCorridas = new Object[strToken.countTokens()][9];
        for(i=0; i<oCorridas.length; i++){
            valor = strToken.nextToken();
            strTokenCampos = new StringTokenizer(valor,",");
            fin = 9;
            for(j=0; j<fin; j++){
                if(j==1){
                    valor=strTokenCampos.nextToken();
                    oCorridas[i][j] = valor.substring(0,10);
                    j++;
                    oCorridas[i][j] = valor.substring(11);
                }
                else oCorridas[i][j] = strTokenCampos.nextToken();
            }
        }
        return oCorridas;
    }
    
    public Object[] obtenerUnaCorridaVenta(String corridaId){
        String strCorridas;
        if(!abreSocketAS()) return null; // MOD:: NIVEL -21
        CorridaCerrada = false;
        strCorridas = getTmsVtaFacade().obtenerUnaCorridaVenta("", this.DBLink, corridaId);
        //System.out.println("strCorridas: "+strCorridas);
        if(strCorridas==null){
            if(isBolRedCer())
                strCorridas = getTmsVtaFacade().obtenerUnaCorridaVenta("", this.dblinkRedIda, corridaId);
            //System.out.println("strCorridas: "+strCorridas);
            if(strCorridas==null)
                CorridaCerrada = true;
            else
            {
                CorridaCerrada = false;
                return SISTEMA_DECODE_2(strCorridas);
            }
            return null;
        }
        return SISTEMA_DECODE_2(strCorridas);
    }
    
    private Object[] SISTEMA_DECODE_2(String strCorridas) {
        while(b_decode_data);
        String valor = null;
        StringTokenizer strToken = new StringTokenizer(strCorridas,",");
        int i;
        Object[] oCorridas = new Object[strToken.countTokens()];
        for(i=0; i<oCorridas.length; i++){
            valor = strToken.nextToken();
            oCorridas[i] = valor;
        }
        return oCorridas;
    }
    
    public String getDBLinkClaveOrigenX(String Origen){
        for(int i=0; i<tmsBaseDatosConfigTbl.size(); i++)
            if(Origen.equals(tmsBaseDatosConfigTbl.get(i).getNombreTerminal())){
                this.DBLinkX=tmsBaseDatosConfigTbl.get(i).getNombreDblink();
                if(this.DBLinkX==null){
                    this.DBLinkX="";
                }
                return this.DBLinkX;
            }
        return "";
    }
    
    public int busqExisteCorrida(String strRutaOrigen, String strOrigen, String strDestino,
                              Timestamp dtHoy, String strServicio, String strEmpresa){
        try{
            if(this.DBLinkX==null) return -1;
            System.out.println("dblink ---> "+this.DBLinkX);
            String conjuntoServicios="'"+strServicio+"'";
            if(!abreSocketAS()) return -1; // MOD:: NIVEL -21
            int q = getTmsVtaFacade().queryExisteCorrida("", this.DBLinkX, strRutaOrigen, strOrigen, formatoFechaHora.format(dtHoy), null, conjuntoServicios, strDestino, strEmpresa);
            System.out.println("Existe corrida regreso? "+q);
            return q;
        }catch(javax.ejb.EJBException ejbex){
            ejbex.printStackTrace();
            return -1;
        } catch(NullPointerException npex){
            npex.printStackTrace();
            return -1;
        } catch(Exception ex){
            ex.printStackTrace();
            return -1;
        }
    }   
    
    public int getValidaSesionVenta(){
        String tipoVal = (getUserCon().getAplicacionVenta()?"S":"N");
        //String tipoVal = "S";
        if(!abreSocketAS()) return -21;
           ocurrioError.add("Error");
        //int estado = -21;
        //try {
           int  estado = getTmsVtaFacade().getValidaSesionVenta(getUserCon().getCorteId(), getUserCon().getSesionId(), tipoVal);
//        } catch(Exception e) {
//            System.out.println("Excepcion cachada...");
//            e.printStackTrace();
//            System.out.println("------------------------------ se termina la excepcion cachada --------------------");
//            return -21;
//            
//        }
           ocurrioError.remove(0);
//            System.out.println("velor de la variable estado: "+estado);
        switch(estado){
            case -1: DialogoAceptar.mostrarDialogo("¡Sesion invalida!","<html>No existe conexion a la base de datos.<br>Contacte al administrador del sistema.</html>", Color.RED); return -21;
            case 0: return 0;
            case 1: DialogoAceptar.mostrarDialogo("¡Sesion invalida!","<html>Corte en proceso.<br>TMS Venta se cerrara.</html>", Color.RED); break;
            case 2: DialogoAceptar.mostrarDialogo("¡Sesion invalida!","<html>Corte realizado.<br>TMS Venta se cerrara.</html>", Color.RED); break;
            case 3: DialogoAceptar.mostrarDialogo("¡Sesion invalida!","<html>La sesion de venta ya no es vigente.<br>TMS Venta se cerrara.</html>", Color.RED); break;
            case 4: DialogoAceptar.mostrarDialogo("¡Sesion invalida!","<html>La sesion de venta ya no es vigente.<br>TMS Venta se cerrara.</html>", Color.RED); break;
        }
        return -1;
    }
    /*******/
    public void _SetEstadoSistema(int sysedo){ this.ESTADO_DEL_SISTEMA=sysedo; }
    public int  _GetEstadoSistema(){ return this.ESTADO_DEL_SISTEMA; }
    
    public boolean SISTEMA_EN_ESPERA(){ return (ESTADO_DEL_SISTEMA==es_ESPERA?true:false); }
    public boolean SISTEMA_SALIDA_INMEDIATA(){ return (ESTADO_DEL_SISTEMA==es_SALIDA_INMEDIATA?true:false); }
    public boolean SISTEMA_NORMAL(){ return (ESTADO_DEL_SISTEMA==es_NORMAL?true:false); }

    public void setEtiqueta(JLabel pEtiqueta) {
        etiqueta = pEtiqueta;
    }

    public boolean getExisteImpresoraVoucher(String empresa) {
        for(int i=0; i<getUserCon().getEmpresasImpresora().length; i++)
            if(getUserCon().getEmpresasImpresora()[i][3].toString().equals("VOUCHERS")
               && getUserCon().getEmpresasImpresora()[i][7].toString().equals("S"))
               return true;
        return false;
    }
    
    public String getImpresoraVoucher(String empresa) {
        for(int i=0; i<getUserCon().getEmpresasImpresora().length; i++)
        {
            System.out.println("Empresa a comparar: "+empresa+" ("+empresa.length()+")");
            System.out.println("getEmpresasImpresora()["+i+"][0]= "+getUserCon().getEmpresasImpresora()[i][0].toString()+"  ("+getUserCon().getEmpresasImpresora()[i][0].toString().length()+")");
            System.out.println("getEmpresasImpresora()["+i+"][3]= "+getUserCon().getEmpresasImpresora()[i][3].toString()+"  ("+getUserCon().getEmpresasImpresora()[i][3].toString().length()+")"); 
            //System.out.println("getEmpresasImpresora()[i][6]= "+getUserCon().getEmpresasImpresora()[i][6].toString()); 
            if(getUserCon().getEmpresasImpresora()[i][0].toString().equals(empresa)
               && getUserCon().getEmpresasImpresora()[i][3].toString().equals("VOUCHERS")){
               System.out.println("entra a traer el nombre de la impresora..");
               String ImpresoraVoucher=getUserCon().getEmpresasImpresora()[i][6].toString();
               System.out.println("ImpresoraVoucher "+ImpresoraVoucher);
               return ImpresoraVoucher;
            }
        System.out.println("ImpresoraVoucher de EP "+getUserCon().getImpresoraEP());
        }
        return getUserCon().getImpresoraEP();
    }

    public boolean isImpresoraVoucherBlanco(String empresa) {
        for(int i=0; i<getUserCon().getEmpresasImpresora().length; i++)
        {
            System.out.println("(isImpresoraVoucherBlanco)Empresa a comparar: "+empresa+" ("+empresa.length()+")");
            System.out.println("(isImpresoraVoucherBlanco)getEmpresasImpresora()["+i+"][0]= "+getUserCon().getEmpresasImpresora()[i][0].toString()+"  ("+getUserCon().getEmpresasImpresora()[i][0].toString().length()+")");
            System.out.println("(isImpresoraVoucherBlanco)getEmpresasImpresora()["+i+"][3]= "+getUserCon().getEmpresasImpresora()[i][3].toString()+"  ("+getUserCon().getEmpresasImpresora()[i][3].toString().length()+")");
            System.out.println("(isImpresoraVoucherBlanco)getEmpresasImpresora()["+i+"][5]= "+getUserCon().getEmpresasImpresora()[i][5].toString());
            //System.out.println("getEmpresasImpresora()[i][6]= "+getUserCon().getEmpresasImpresora()[i][6].toString());
            if(getUserCon().getEmpresasImpresora()[i][0].toString().equals(empresa)
               && getUserCon().getEmpresasImpresora()[i][3].toString().equals("VOUCHERS")
               && getUserCon().getEmpresasImpresora()[i][5].toString().equals("VOUCHERS_TERMICO")){
               System.out.println("Si es impresora de Voucher Termico...");
               return true;
            }

        }
        return false;
    }

    public String getMarcaImpresoraVoucherBlanco(String empresa) {
        for(int i=0; i<getUserCon().getEmpresasImpresora().length; i++)
        {
            System.out.println("(getMarcaImpresoraVoucherBlanco)Empresa a comparar: "+empresa+" ("+empresa.length()+")");
            System.out.println("(getMarcaImpresoraVoucherBlanco)getEmpresasImpresora()["+i+"][0]= "+getUserCon().getEmpresasImpresora()[i][0].toString()+"  ("+getUserCon().getEmpresasImpresora()[i][0].toString().length()+")");
            System.out.println("(getMarcaImpresoraVoucherBlanco)getEmpresasImpresora()["+i+"][3]= "+getUserCon().getEmpresasImpresora()[i][3].toString()+"  ("+getUserCon().getEmpresasImpresora()[i][3].toString().length()+")");
            System.out.println("(getMarcaImpresoraVoucherBlanco)getEmpresasImpresora()["+i+"][4]= "+getUserCon().getEmpresasImpresora()[i][4].toString());
            //System.out.println("getEmpresasImpresora()[i][6]= "+getUserCon().getEmpresasImpresora()[i][6].toString());
            if(getUserCon().getEmpresasImpresora()[i][0].toString().equals(empresa)
               && getUserCon().getEmpresasImpresora()[i][3].toString().equals("VOUCHERS")
               && getUserCon().getEmpresasImpresora()[i][5].toString().equals("VOUCHERS_TERMICO")){
               System.out.println("entra a traer la marca de la impresora:"+getUserCon().getEmpresasImpresora()[i][4].toString());
               return getUserCon().getEmpresasImpresora()[i][4].toString();
            }

        }
        return "";
    }

    public String getNombreImpresoraVoucherBlanco(String empresa) {
        for(int i=0; i<getUserCon().getEmpresasImpresora().length; i++)
        {
            System.out.println("(getNombreImpresoraVoucherBlanco)Empresa a comparar: "+empresa+" ("+empresa.length()+")");
            System.out.println("(getNombreImpresoraVoucherBlanco)getEmpresasImpresora()["+i+"][0]= "+getUserCon().getEmpresasImpresora()[i][0].toString()+"  ("+getUserCon().getEmpresasImpresora()[i][0].toString().length()+")");
            System.out.println("(getNombreImpresoraVoucherBlanco)getEmpresasImpresora()["+i+"][3]= "+getUserCon().getEmpresasImpresora()[i][3].toString()+"  ("+getUserCon().getEmpresasImpresora()[i][3].toString().length()+")");
            System.out.println("(getNombreImpresoraVoucherBlanco)getEmpresasImpresora()["+i+"][4]= "+getUserCon().getEmpresasImpresora()[i][4].toString());
            //System.out.println("getEmpresasImpresora()[i][6]= "+getUserCon().getEmpresasImpresora()[i][6].toString());
            if(getUserCon().getEmpresasImpresora()[i][0].toString().equals(empresa)
               && getUserCon().getEmpresasImpresora()[i][3].toString().equals("VOUCHERS")
               //&& getUserCon().getEmpresasImpresora()[i][5].toString().equals("VOUCHERS_TERMICO")
               ){
               System.out.println("entra a traer el nombre de la impresora:"+getUserCon().getEmpresasImpresora()[i][1].toString());
               return getUserCon().getEmpresasImpresora()[i][1].toString();
            }

        }
        return "";
    }

    public String[] getImpresoraBoletos(String empresa) {
        //System.out.println(empresa);
        for(int i=0; i<getUserCon().getEmpresasImpresora().length; i++)
            if(getUserCon().getEmpresasImpresora()[i][0].toString().equals(empresa)
               && getUserCon().getEmpresasImpresora()[i][3].toString().equals("BOLETOS")){
               String[] x = new String[4];
               x[0] = getUserCon().getEmpresasImpresora()[i][2].toString();
               x[1] = getUserCon().getEmpresasImpresora()[i][4].toString();
               x[2] = getUserCon().getEmpresasImpresora()[i][5].toString();
               x[3] = getUserCon().getEmpresasImpresora()[i][1].toString();
               return x;
            }
        return null;
    }
    
    public boolean bloqueaRvn(String claveRvn, String valor){
        if(!abreSocketAS()) return false;
        try{
            return getTmsVtaFacade().bloqueaRvn(claveRvn, valor+getUserCon().getUsuarioNum(),"", this.DBLink);
        }catch(javax.ejb.EJBException ejbex){
            ejbex.printStackTrace();
            return false;
        }
    }
    
    public String quienOcupaRvn(String claveRvn){
        if(!abreSocketAS()) return null;
        try{
            return getTmsVtaFacade().quienOcupaRvn(claveRvn,"", this.DBLink);
        }catch(javax.ejb.EJBException ejbex){
            return null;
        }
    }
    
    public boolean isCorridaCerrada() {
        return CorridaCerrada;
    }

    public Date getDtFechaDespachoTarjeta() {
        return dtFechaDespachoTarjeta;
    }
    
    public int validarFolios(long P_TERMINAL_ID, long P_EMPRESA_ID, long P_FOLIO_INICIAL, long P_FOLIO_FINAL, long P_USUARIO_ID){
        System.out.println("Valores: "+P_TERMINAL_ID+" "+P_EMPRESA_ID+" "+P_FOLIO_INICIAL+" "+P_FOLIO_FINAL+" "+P_USUARIO_ID);
        if(!abreSocketAS()) return -1;
        try{
            return getTmsVtaFacade().validarFolios(P_TERMINAL_ID, P_EMPRESA_ID, P_FOLIO_INICIAL, P_FOLIO_FINAL, P_USUARIO_ID);
        }catch(javax.ejb.EJBException ejbex){
            ejbex.printStackTrace();
            return -1;
        }
    }
    
    public int _OcuparAsientosSP(long corridaId, String asientos, String tiposPasajero, String modo){
        if(!abreSocketAS()) return -21; // MOD:: NIVEL -21
        String pDBLink="";
        if(!this.DBLink.equals("")) pDBLink = "@" + this.DBLink;
        int retVal = getTmsVtaFacade()._OcuparAsientosSP(pDBLink, corridaId, asientos, tiposPasajero, modo, getUserCon().getUsuarioId());
        System.out.println("Valor "+retVal);
        return retVal;
    }

        public int _OcuparAsientosSPReCer(long corridaId, String asientos, String tiposPasajero, String modo,String p_dblink){
        if(!abreSocketAS()) return -21; // MOD:: NIVEL -21
        System.out.println("------------------------------");
        System.out.println("corridaId: "+corridaId);
        System.out.println("asientos: "+asientos);
        System.out.println("tiposPasajero: "+tiposPasajero);
        System.out.println("modo: "+modo);
        System.out.println("DBLink: "+p_dblink);
        String pDBLink="";
        if(!p_dblink.equals("")) pDBLink = "@" + p_dblink;
        int retVal = getTmsVtaFacade()._OcuparAsientosSP(pDBLink, corridaId, asientos, tiposPasajero, modo, getUserCon().getUsuarioId());
        System.out.println("Valor "+retVal);
        return retVal;
    }

    public String getValorTipo(){ return valorTipo; }
    
    public boolean _ArrayBoletosSP(Object[][] Boletos){
        _strBoletos = new String[Boletos.length];
        TmsBoletosVentaTbl boleto;
        _BoletosVenta = new ArrayList<TmsBoletosVentaTbl>();
        String[] strSplit;
        boolean aunExiste;
        _empresasRegistranVenta = null;
        _folioBol = null;
        int nbolact =  0;
        for(int i = 0; i<Boletos.length; i++){
            this.TipoBoleto = Boletos[i][6].toString();
            _strBoleto = "";
            boleto = new TmsBoletosVentaTbl();
            boleto.setEmpresa(Boletos[i][7].toString());
            boleto.setServicio(Boletos[i][8].toString());
            boleto.setCaja(getUserCon().getCaja());//Boletos[i][9].toString());
            boleto.setCorteId(getUserCon().getCorteId());//Long.valueOf(Boletos[i][10].toString()));
            if(Boletos[i][11]!=null) boleto.setClaveCorrida(Boletos[i][11].toString());
            if(Boletos[i][12]!=null) boleto.setClienteId(Long.valueOf(Boletos[i][12].toString()));
            if(Boletos[i][1]!=null) boleto.setNoAsiento(Long.valueOf(Boletos[i][1].toString()));
            if(Boletos[i][3]!=null) boleto.setNombrePasajero(Boletos[i][3].toString());
            boleto.setTipoPasajero(Boletos[i][2].toString());
            boleto.setTipoPago(Boletos[i][13].toString());
            boleto.setTipoOperacion(Boletos[i][15].toString());
            if(Boletos[i][14] != null){
                if(Boletos[i][13].toString().equals("AMX")){
                    strSplit = Boletos[i][14].toString().split(";");
                    if(strSplit.length==2){
                        if(strSplit[0].length()>25) strSplit[0] = strSplit[0].substring(0,25);
                        boleto.setReferenciaPago(strSplit[0]);
                        if(strSplit[1].length()>25) strSplit[1] = strSplit[1].substring(0,25);
                       //boleto.setAdicional1(strSplit[1]);//numero de operacion con sistema de cobro con tarjeta bancario
                       //boleto.setAdicional1(strSplit[3]);//Tipo de TDC y ultimos 4 Digitos de la tarjeta
                    }
                    else{
                        if(Boletos[i][14].toString().length()>25) Boletos[i][14] = Boletos[i][14].toString().substring(0,25);
                        boleto.setReferenciaPago(Boletos[i][14].toString());
                    }
                }
                else{
//                    if(!this.getUserCon().getSysCobroBancario() || (boleto.getTipoOperacion().equals("HO")||Boletos[i][15].toString().equals("AC"))){
//                        if(Boletos[i][14].toString().length()>25) Boletos[i][14] = Boletos[i][14].toString().substring(0,25);
//                        boleto.setReferenciaPago(Boletos[i][14].toString());
//                    }
//                    else{
//                        if(Boletos[i][13].toString().equals("AME")){
//                            if(Boletos[i][14].toString().length()>25) Boletos[i][14] = Boletos[i][14].toString().substring(0,25);
//                            boleto.setReferenciaPago(Boletos[i][14].toString());
//                        }
//                        else{
//                            strSplit = Boletos[i][14].toString().split(";");
//                            boleto.setReferenciaPago(strSplit[0]);
//                            boleto.setAdicional2(strSplit[1]); // numero de operacion con sistema de cobro con tarjeta bancario
//                            boleto.setAdicional3(strSplit[2]); // numero de referencia
//                        }
//                    }
                   ///*09102008
                   if(Boletos[i][13].toString().equals("BBV"))
                   {
                        if(!this.getUserCon().getSysCobroBancario() || (boleto.getTipoOperacion().equals("HO") ||Boletos[i][15].toString().equals("AC") || boleto.getTipoOperacion().equals("FO")))
                        {
                            if(Boletos[i][14].toString().length()>25) Boletos[i][14] = Boletos[i][14].toString().substring(0,25);
                            boleto.setReferenciaPago(Boletos[i][14].toString());
                        }
                        else
                        {
                            strSplit = Boletos[i][14].toString().split(";");
                            boleto.setReferenciaPago(strSplit[0]);
                            boleto.setAdicional2(strSplit[1]); // numero de operacion con sistema de cobro con tarjeta bancario
                            boleto.setAdicional3(strSplit[2]); // numero de referencia
                            //boleto.setAdicional1(strSplit[3]); //Tipo de TDC y ultimos 4 Digitos de la tarjeta
                        }
                    
                   }
                   else
                      {
                            if(Boletos[i][14].toString().length()>25) Boletos[i][14] = Boletos[i][14].toString().substring(0,25);
                            boleto.setReferenciaPago(Boletos[i][14].toString());
                          
                      }//*/09102008
                }
            }
            boleto.setAdicional1(tipoTDC);
            boleto.setAdicional13(getCP());
            if(Boletos[i][16] != null) boleto.setReservacionId(Long.valueOf(Boletos[i][16].toString()));
            System.out.println("Boletos["+i+"][5]= "+ Boletos[i][5].toString());
            System.out.println("Boletos["+i+"][15]= "+ Boletos[i][15].toString());
            boleto.setAdicional15("0");
            if(boleto.getTipoOperacion().equals("HO")||boleto.getTipoOperacion().equals("CN"))
                  boleto.setAdicional15(this.getVariosTmsBoletosVentaTbl().get(i).getAdicional15());

            if(boleto.getTipoOperacion().equals("HO")||Boletos[i][15].toString().equals("AC")||Boletos[i][15].toString().equals("FC") ||Boletos[i][15].toString().equals("FT") ||Boletos[i][15].toString().equals("FO")){
                boleto.setImporteBoleto(Double.valueOf(Boletos[i][5].toString()));
                boleto.setBoletoRelacionadoId(this.getVariosTmsBoletosVentaTbl().get(i).getBoletoId());
                boleto.setAdicional4(this.getVariosTmsBoletosVentaTbl().get(i).getFolioBoleto());
                boleto.setAdicional1(this.getVariosTmsBoletosVentaTbl().get(i).getAdicional1());
                boleto.setAdicional2(this.getVariosTmsBoletosVentaTbl().get(i).getAdicional2());
                boleto.setAdicional3(this.getVariosTmsBoletosVentaTbl().get(i).getAdicional3());
            }
            else{
                boleto.setImporteBoleto(Double.valueOf(Boletos[i][5].toString()));
                if(Boletos[i][17] != null) boleto.setBoletoRelacionadoId(Long.valueOf(Boletos[i][17].toString()));
            }
            if(boleto.getTipoOperacion().equals("VA") && Boletos[i][13].toString().equals("BRF"))
                boleto.setAdicional4(this.getVariosTmsBoletosVentaTbl().get(i).getFolioBoleto());
            if(Boletos[i][18] != null) boleto.setDiasValidezBoletoAbierto(Long.valueOf(Boletos[i][18].toString()));
            
            //boleto.setFolioPreimpreso(String.valueOf(Long.valueOf(obtenerFolioActual(boleto.getEmpresa()))+i));
            if(Boletos[i][29].toString().equals("N"))
            {
                if(this.isVtaBolBco(boleto.getEmpresa()))
                    boleto.setFolioPreimpreso(String.valueOf(Long.valueOf(this.obtenerFolioGneradoSistema())));
                else
                {
                    boleto.setFolioPreimpreso(String.valueOf(Long.valueOf(obtenerFolioActual(boleto.getEmpresa()))+nbolact));
                    nbolact++;
                }
            }
            else
                boleto.setFolioPreimpreso("000000");
            //System.out.println("Folio del sistema al ponerlo en el boleto: "+this.folioSistema);
            boleto.setFolioBoleto(getUserCon().getPrefijo()+""+this.getEmpresaId(boleto.getEmpresa())+""+this.getFormatoFolioSistema(this.siguienteFolioSistema()));
            Boletos[i][19] = boleto.getFolioPreimpreso();
            boleto.setCiudadVenta(getUserCon().getTerminalNombre());
            boleto.setOrigen(Boletos[i][21].toString());
            boleto.setDestino(Boletos[i][22].toString());
            //ConexionReal(boleto.getOrigen(), boleto.getDestino(), boleto.getServicio());
            System.out.println("Origen Boleto: "+boleto.getOrigen());
            System.out.println("OrigenCorrida: "+boleto.getOrigenCorrida());
            if(this.isBolRedCer())
            {
                System.out.println("origenCorridaRedIda: "+origenCorridaRedIda);
                System.out.println("origenCorridaRedReg: "+origenCorridaRedReg);
                System.out.println("origenRedIda: "+origenRedIda);
                System.out.println("origenRedReg: "+origenRedReg);
                System.out.println("isBolRedCer: "+this.isBolRedCer());
                if(boleto.getOrigen().equals(origenRedIda))
                    this.strCiudadOrigen = origenCorridaRedIda;
                if(boleto.getOrigen().equals(origenRedReg))
                    this.strCiudadOrigen = origenCorridaRedReg;
            }
            ConexionReal(((boleto.getOrigenCorrida()==null)?boleto.getOrigen():boleto.getOrigenCorrida()), boleto.getDestino(), boleto.getServicio());
            boleto.setTipoTransaccion(strCnxReal[1]);
            boleto.setClaveCajero(Boletos[i][24].toString());
            boleto.setAutorizadoPor(getUserCon().getAutorizadoPorIdUsuario());
            boleto.setCreadoPor(getUserCon().getUsuarioId());
            boleto.setUltimaActualizacionPor(getUserCon().getUsuarioId());
            
            System.out.println("que dato trae??(Boletos[i][11]): "+Boletos[i][11]);
            // VALIDACIONES SOBRE AC Y HO
            if(Boletos[i][11]!=null){ // existe corrida?dsafs
                qry = "";
               // System.out.println("que dato trae??: "+boleto.getTipoOperacion());
                if(boleto.getTipoOperacion().equals("HO") || boleto.getTipoOperacion().equals("AC") || boleto.getTipoOperacion().equals("FO") || boleto.getTipoOperacion().equals("FT")){ // aun existe boleto?
                    if(boleto.getTipoOperacion().equals("AC")) aunExiste=aunExisteBoletoAbierto(strCnxReal[2], getVariosTmsBoletosVentaTbl().get(i).getOrigen(), getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso(), getVariosTmsBoletosVentaTbl().get(i).getServicio(), getVariosTmsBoletosVentaTbl().get(i).getEmpresa());
                    else {
                        System.out.println("****** Antes de llamar aunExisteBoletoVendidoParaHO ...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
                        aunExiste=aunExisteBoletoVendidoParaHO(strCnxReal[2], getVariosTmsBoletosVentaTbl().get(i).getOrigen(), getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso(), getVariosTmsBoletosVentaTbl().get(i).getNoAsiento().toString(), getVariosTmsBoletosVentaTbl().get(i).getEmpresa(),boleto.getTipoOperacion(),getVariosTmsBoletosVentaTbl().get(i).getFolioBoleto(),getVariosTmsBoletosVentaTbl().get(i).getOrigenCorrida());
                        System.out.println("****** Despues de llamar aunExisteBoletoVendidoParaHO ...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
                    }
                    if(!aunExiste){
                        if(boleto.getTipoOperacion().equals("AC")) DialogoAceptar.mostrarDialogo("Canje de boleto abierto.","No es posible canjear este boleto.",Color.RED);
                        else 
                        {
                            if(boleto.getTipoOperacion().equals("FT"))
                                DialogoAceptar.mostrarDialogo("Canje de boleto.","No es posible realizar el canje del boleto.",Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Cambio de horario.","No es posible realizar cambio de horario de este boleto.",Color.RED);
                        }
                        return false;
                    }
                }
            }
            // SE CREA ESTRUCTURA DE BOLETOS PARA SP
            _strBoleto=","+boleto.getEmpresa()+","+boleto.getServicio()+","+boleto.getCaja()+","+boleto.getCorteId();
            _strBoleto=_strBoleto+","+(boleto.getClaveCorrida()==null?"":boleto.getClaveCorrida());
            _strBoleto=_strBoleto+","+(boleto.getClienteId()==null?"":boleto.getClienteId());
            _strBoleto=_strBoleto+","+(boleto.getNoAsiento()==null?"":boleto.getNoAsiento());
            _strBoleto=_strBoleto+","+(boleto.getNombrePasajero()==null?"":boleto.getNombrePasajero());
            _strBoleto=_strBoleto+","+boleto.getTipoPasajero()+","+boleto.getTipoPago();
            _strBoleto=_strBoleto+","+(boleto.getReferenciaPago()==null?"":boleto.getReferenciaPago());
            _strBoleto=_strBoleto+","+boleto.getImporteBoleto()+","+boleto.getTipoOperacion();
            _strBoleto=_strBoleto+","+(boleto.getReservacionId()==null?"":boleto.getReservacionId());
            _strBoleto=_strBoleto+","+(boleto.getBoletoRelacionadoId()==null?"":boleto.getBoletoRelacionadoId());
            _strBoleto=_strBoleto+","+(boleto.getDiasValidezBoletoAbierto()==null?"":boleto.getDiasValidezBoletoAbierto());
            _strBoleto=_strBoleto+","+boleto.getFolioPreimpreso()+","+boleto.getFolioBoleto()+","+boleto.getCiudadVenta();
            _strBoleto=_strBoleto+","+boleto.getOrigen()+","+boleto.getDestino()+","+boleto.getTipoTransaccion();
            _strBoleto=_strBoleto+","+boleto.getClaveCajero()+","+boleto.getAutorizadoPor()+","+boleto.getCreadoPor()+","+boleto.getUltimaActualizacionPor();
            _strBoleto=_strBoleto+","+(boleto.getAdicional1()==null?"":boleto.getAdicional1());//VAGL 15082013 Tipo de Tarjeta de Credito AMEX=American Express, SANTANDER=Otras
            _strBoleto=_strBoleto+","+(boleto.getAdicional2()==null?"":boleto.getAdicional2());
            _strBoleto=_strBoleto+","+(boleto.getAdicional3()==null?"":boleto.getAdicional3());
            _strBoleto=_strBoleto+","+(boleto.getAdicional4()==null?"":boleto.getAdicional4());
            _strBoleto=_strBoleto+","+(boleto.getAdicional5()==null?"":boleto.getAdicional5());
            _strBoleto=_strBoleto+","+(boleto.getAdicional6()==null?"":boleto.getAdicional6());
//            _strBoleto=_strBoleto+","+(boleto.getAdicional7()==null?"":boleto.getAdicional3());
//            _strBoleto=_strBoleto+","+(boleto.getAdicional8()==null?"":boleto.getAdicional4());
//            _strBoleto=_strBoleto+","+(boleto.getAdicional9()==null?"":boleto.getAdicional3());
            _strBoleto=_strBoleto+","+(Boletos[i][31]==null?"":Boletos[i][31].toString());
            _strBoleto=_strBoleto+","+(Boletos[i][33]==null?"":Boletos[i][33].toString());
            _strBoleto=_strBoleto+","+(Boletos[i][34]==null?"":Boletos[i][34].toString().trim());
            _strBoleto=_strBoleto+","+(getUserCon().getAplicacionVenta() ?"TAQUILLA" :"CALL_CENTER") ;//(boleto.getAdicional10()==null?"":boleto.getAdicional10());
            _strBoleto=_strBoleto+","+(Boletos[i][35]==null?"MOS":Boletos[i][35].toString().trim());//VAGL 02082013 Adicional12 Tipo de Venta: CRE,MOS
            _strBoleto=_strBoleto+","+(boleto.getAdicional13()==null?"":boleto.getAdicional13());//VAGL 15082013 Adicional13 CP
            if(this.isCobroCargo())
                _strBoleto=_strBoleto+","+(boleto.getAdicional15()==null?"":boleto.getAdicional15());//VAGL 14082014 //importe boleto sin cargo
            else
                _strBoleto=_strBoleto+",0";//VAGL 14082014 //importe boleto sin carg
            _strBoleto=_strBoleto+","+strCnxReal[0];
            _strBoleto=_strBoleto+","+Boletos[i][29].toString();
            _strBoleto=_strBoleto+","+Boletos[i][30].toString();
            System.out.println("getReferenciaOriginal = "+getReferenciaOriginal());
            if(isCambHorRer())
                _strBoleto=_strBoleto+","+getReferenciaOriginal();
            else
                _strBoleto=_strBoleto+","+"";
           _strBoleto=_strBoleto+","+(this.getReferenciadoId().equals("")?"":Long.valueOf(this.getReferenciadoId()));
            _strBoletos[i]=_strBoleto;
            System.out.println(_strBoleto+" --- Origen:(L/R):DBlink - "+strCnxReal[0]+":"+strCnxReal[1]+":"+strCnxReal[2]);
            _BoletosVenta.add(boleto);
            
            // control interno de posibles boletos por registrar
            System.out.println("(Boletos["+i+"][29] = "+Boletos[i][29]+" Folio: "+Boletos[i][19]);
            if(Boletos[i][29].toString().equals("N"))
                _empresaFolioBoletos(boleto.getEmpresa(), boleto.getFolioPreimpreso());
        }
        this.BoletosLealtad = Boletos;
        return true;
    }
    
    public Object[][] getBoletosLealtad(){
        return this.BoletosLealtad;
    }
    
    public void setBoletosLealtad(Object[][]  b){
        this.BoletosLealtad = b; 
    }
    
    public Vector buscaTerminalRef(int terminal)
    {
        return getTmsVtaFacade().buscaTerminalRef(terminal);
    }
 
    public boolean getPromocionVigente(){
        return getTmsVtaFacade().getPromocionVigente();
    }

    public boolean getAplicaPromocion(double montoVa, double tarifaRedondo, double descuento, double tarifaSencillo, String dia){
        return getTmsVtaFacade().getAplicaPromocion( montoVa,  tarifaRedondo,  descuento,tarifaSencillo, dia );
    }
    
    
    public int _solicitudRegistroVentaSP(){
        Object[] res =null;
        String respuesta = "";
        if(!abreSocketAS()) return -21; // MOD:: NIVEL -21
        //int retVal = tmsVtaFacade._solicitudRegistroVentaSP(_strBoletos, this.getTipoVenta(), this.getNombreCliente());
        System.out.println("****** Antes de Escribir en el archivo...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
        int retVal=0;
        try {TextOut = new FileWriter(TextFile, true);} catch (IOException ex) {ex.printStackTrace();}
        String tip="EFE";
        for(int i=0; i<_strBoletos.length; i++)
        {
                System.out.println("_strBoletos["+  i+"]="+_strBoletos[i]);
                 try{TextOut.write(formatoFechaHoraBD.format(new Date())+" _strBoletos["+i+"]="+_strBoletos[i]+"\n");} catch (IOException ex) { System.out.println("Error al mandar a escribi al archivo _strBoletos ");ex.printStackTrace();}
            if(_strBoletos[i].toString().indexOf(",BBV,")>=0)
                tip = "BBV";

        }
        System.out.println("****** Despues de Escribir en el archivo...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
        //System.out.println("manda a llamar obtenerCorridasVenta...");
        //String strCorridas = getTmsVtaFacade().obtenerCorridasVenta("", this.DBLink, "CAPU", "CAPU", "10/07/2009 19:55", "SERVICIO DE TURISMO,AEROPUERTO,PULLMAN PRIMERA CLASE,SUPER RAPIDOS FEDERAL,DIRECTO ECONOMICO,INTERMEDIO,DIRECTO TTP,SERVICIO TIJ-SDIEGO-TIJ", "%", "Estrella Roja");
        //System.out.println("manda a llamar _ObtieneFechaHoraBD...");
        //String f = getTmsVtaFacade(). _ObtieneFechaHoraBD();
        //System.out.println("la fecha es: "+f);
        ///System.out.println("manda a llamar _solicitudRegistroVentaSP3...");
        //Vector ve= new Vector();
        String nuevo_strBoletos = "";
        for(int i=0; i<_strBoletos.length; i++)
        {
          //ve.add(_strBoletos[i]);
            if(i==0)
                nuevo_strBoletos = _strBoletos[i];
            else
                nuevo_strBoletos = nuevo_strBoletos + "|"+ _strBoletos[i];
            //System.out.println("_strBoletos["+  i+"]="+_strBoletos[i]);
        }
//        System.out.println("qry = "+qry);
//        System.out.println("manda a llamar _sRVSP4...");
//        String vare0 = getTmsVtaFacade()._sRVSP4(qry);
//        System.out.println("manda a llamar _sRVSP3...");
//        String var0 = getTmsVtaFacade()._sRVSP3(qry,ve);
//        System.out.println("manda a llamar _sRVSP2...");
//        String var = getTmsVtaFacade()._sRVSP2(_strBoletos,qry);
//        System.out.println("manda a llamar _sRVSP...");
//        Object[] var2 = getTmsVtaFacade()._sRVSP(_strBoletos,qry);
//        res = getTmsVtaFacade()._solicitudRegistroVentaSP2(nuevo_strBoletos,qry,_strBoletos.length);
          System.out.println("****** Antes de llamar a _solicitudRegistroVentaSP3...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
          respuesta = getTmsVtaFacade()._solicitudRegistroVentaSP3(nuevo_strBoletos,qry,_strBoletos.length);
          //respuesta = getTmsVtaFacade()._solicitudRegistroVentaSP4(nuevo_strBoletos,qry,_strBoletos.length, getUserCon().getCorteId());
         System.out.println("****** Despues de llamar a _solicitudRegistroVentaSP3...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());            
          ///*********** Guarda los Productos **********/
          if(this.productosCarrito.size()>0)
          {

                float montoTotalTicket =0;
                String sCad="\n";
                sCad = sCad+"      AUTOBUSES MEXICO PUEBLA";
                sCad = sCad+"\n";
                sCad = sCad+"     ESTRELLA ROJA S.A. DE C.V.";
                sCad = sCad+"\n";
                sCad = sCad+"        Comprobante de Pago ";
                sCad = sCad+"\n";
                SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
                SimpleDateFormat formath = new SimpleDateFormat ("H:mm");
                sCad = sCad+"  FECHA: "+formatf.format(new Date()) +" HORA: "+ formath.format(new Date());
                sCad = sCad+"\n\n";
             String foliop =""; 
              //registrarVentaProducto(empresa, producto, cantidad, folio, clave_producto, precio_unitario, subtotal, iva, total, retencion, corteId, caja, cajero, terminal, getPrefijo(), usuarioId);
             for( ProductoCarrito p:productosCarrito)
             {
                if(p.getFolio() == null || p.getFolio().equals("") || p.getFolio().equals("0"))
                {
                        if(foliop.equals(""))
                                foliop=getTmsVtaFacade().getTicketCompra(Long.valueOf(this.getUserCon().getTerminalPrefijoId()));//PrefijoTerminal
                      p.setFolio(foliop);
                }
                getTmsVtaFacade().registrarVentaProducto(p.getEmpresa(), p.getProducto(), p.getCantidad(), p.getFolio(), p.getClave_producto(), p.getPrecio_unitario(), p.getSubtotal(), p.getIva(), p.getTotal(), p.getRetencion(), p.getCorteId(), p.getCaja(), p.getCajero(), p.getTerminal(), p.getPrefijo(), p.getUsuarioId(),tip,"");
                if(isCobroCargo())//imprime Ticket de comprobante de pago de cargo
                {
                    sCad = sCad+"  No.Folio: "+p.getFolio();
                    sCad = sCad+"\n";
                    sCad = sCad+"_____________________________________";
                    sCad = sCad+"\n";
                    sCad = sCad+"Cant.\tNombre\t\tPrecio\tImporte";
                    sCad = sCad+"\n";
                    sCad = sCad+"_____________________________________";
                    sCad = sCad+"\n";
                    montoTotalTicket=montoTotalTicket+p.getTotal();
                    sCad = sCad+" "+p.getCantidad()+(((""+p.getCantidad()).length()>2)?" ":"  ")+((p.getProducto().length()<=15)?p.getProducto():p.getProducto().substring(0,15))+(((""+p.getProducto()).length()>7)?"\t":"\t\t")+p.getPrecio_unitario()+(((""+p.getPrecio_unitario()).length()>3)?"\t":"\t\t")+p.getTotal();
                    sCad = sCad+"\n\n";
                }
             }
             if(isCobroCargo())
             {
                    sCad = sCad+"\t\t\t\t\tTotal: $"+montoTotalTicket;
                    sCad = sCad+"\n\n";
                    String letras= "( "+new cantidad_a_letras().toLetras((long)((new Double(montoTotalTicket)).longValue()))  + "Pesos 00/M.N. )";
                    sCad = sCad+letras;
                    sCad = sCad+"\n\n";
                    sCad = sCad+"\n"; 
                    sCad = sCad+"Cajero: "+((this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()).length()<25?(this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()):(this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()).substring(0,25));//+ClaveCajero+"-"+(NombreCajero.length()<21?NombreCajero:NombreCajero.substring(0,20));
                    sCad = sCad+"\n";
                    sCad = sCad+" #Caja: "+this.getUserCon().getCaja();
                    sCad = sCad+"\n\n   Este comprobante de pago";
                    sCad = sCad+"\n"+"      No es Valido";
                    sCad = sCad+"\n"+" Como cupon de viaje, como";
                    sCad = sCad+"\n"+"boleto de transporte ni como ";
                    sCad = sCad+"\n"+"     comprobante Fiscal";   
                    sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";
                    sCad = sCad+"         .";
                    imprimeTicketCargo(productosCarrito.get(0).getEmpresa(),sCad,productosCarrito.get(0).getFolio());
             }
          }

         //Manda a imprimior los Tickets de los productos
         boolean imprime = false;
         List<ProductoCarrito> pproductosImp = new ArrayList<ProductoCarrito>() ;
         for( ProductoCarrito p:productosCarrito)
         {
             if(p.isImprimeTicket())
            {
                imprime = true;
                pproductosImp.add(p);
            }
         }
        if(imprime)
            imprimeTicketProductos(pproductosImp);
          ///Limpia las variables del los productos
                List<ProductoCarrito>productos = new ArrayList<ProductoCarrito>();
                this.setProductosCarrito(productos);

         System.out.println("Regreso la cadena: "+respuesta);
          res =  respuesta.split(",");
        //res = getTmsVtaFacade()._solicitudRegistroVentaSP(_strBoletos,qry);
//        System.out.println("termina de llamar _solicitudRegistroVentaSP...");
//        System.out.println("regreso res[0] = "+res[0]);
        qry = "";
        retVal =  Integer.valueOf(res[0].toString());                       
        try{TextOut.write(formatoFechaHoraBD.format(new Date())+" Repuesta: "+retVal+"\n");} catch (IOException ex) {ex.printStackTrace();}
        this.vtaRef = res[1].toString();
        foliosBoletos = new Vector();
        for(int i=2; i<res.length; i++)
        {
            Vector v = new Vector();
            String[] s =  res[i].toString().split("-");
            v.add(s[0].toString());
            v.add(s[1].toString());
            foliosBoletos.add(v);
        }
        try{TextOut.write(formatoFechaHoraBD.format(new Date())+" Referencia: "+vtaRef+"\n");} catch (IOException ex) {ex.printStackTrace();}
        System.out.println("Valor "+retVal);
        try{TextOut.close();} catch (IOException ex) {ex.printStackTrace();}
        System.out.println("regresaria el valor: "+retVal);
        return retVal;
    }

    public int _RegistroVentaSP(Object[][] Boletos){
        System.out.println("****** Antes de llamar _ArrayBoletosSP ...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
        if(!_ArrayBoletosSP(Boletos)) return -3;
        // ENVIO SOLICITUD
        //System.out.println("verifica si ocurrio un error: "+ocurrioError);
          ///*********** Genera los cargos CH/HO como Productos **********/
         System.out.println("Guarda los cargos HO como Productos(): "+isCobroCargo());
         if(isCobroCargo())
         {
             System.out.println("tipoPagoCargo: "+this.tipoPagoCargo);
             System.out.println("aprobacionCargo: "+this.aprobacionCargo);
             if(this.getProductosCarrito() == null)
             {
                List<ProductoCarrito>productos = new ArrayList<ProductoCarrito>();
                this.setProductosCarrito(productos);
             }
             System.out.println("Guardaria "+this.getVariosTmsBoletosVentaTbl().size()+" Productos");
             for(int j=0; j<this.getVariosTmsBoletosVentaTbl().size(); j++)
             {
                System.out.println("Producto("+j+"): "+this.getVariosTmsBoletosVentaTbl().get(j).getFolioPreimpreso());
                System.out.println("Producto("+j+"): "+this.getVariosTmsBoletosVentaTbl().get(j).getAdicional15());
                System.out.println("Producto("+j+"): "+this.getVariosTmsBoletosVentaTbl().get(j).getTipoOperacion());
                //ProductoCarrito(long productoId, String empresa, String producto, long cantidad,
                                //String folio, String clave_producto, float precio_unitario, float subtotal
                                //, float iva, float total, float retencion, long corteId, String caja, long cajero
                                //, String terminal, long prefijo, long usuarioId, String tiposPago)
                //ProductoCarrito p= new ProductoCarrito(1,"","",1,"","",0,0,0,0,0,1,"",1,"",0,0,"");
                Float preciop =  Float.valueOf(this.getVariosTmsBoletosVentaTbl().get(j).getAdicional15());
                ProductoCarrito p= new ProductoCarrito(productoCARGOHOId,productoCARGOHOEmpresa,productoCARGOHONombre,1,this.getVariosTmsBoletosVentaTbl().get(j).getFolioPreimpreso(),productoCARGOHOClave,preciop,preciop,0, preciop,0,this.getUserCon().getCorteId(), this.getUserCon().getCaja(),Long.valueOf(this.getUserCon().getUsuarioNum()),this.getUserCon().getTerminalNombre(),Long.valueOf(this.getUserCon().getTerminalPrefijoId()),this.getUserCon().getUsuarioId(),this.getVariosTmsBoletosVentaTbl().get(j).getTipoPago(),true,"Terminal Las Torres Puebla 1 S.A de C.V.");
                this.getProductosCarrito().add(p);
             }
        }
        System.out.println("****** Antes de llamar  _solicitudRegistroVentaSP...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());

        int edo = _solicitudRegistroVentaSP();
        System.out.println("****** Despues de llamar  _solicitudRegistroVentaSP...."+getTmsVtaFacade()._ObtieneFechaHoraBD2());
        
        if(edo!=0){ // MANEJO DE ERRORES
            return edo;
        }
        // SI TODO SALIO BIEN ACTUALIZO ULTIMO BOLETO
        System.out.println("Pregnta si es VtaBolBco con la empresa: "+Boletos[0][7].toString());
        if(!this.isVtaBolBco( Boletos[0][7].toString())) //this.getVariosTmsBoletosVentaTbl().get(0).getEmpresa()))
        {
            System.out.println(formatoDebugFecha.format(new Date())+":"+"19.- Antes FOR actualizaUltimoFolioVendido");
            if(_empresasRegistranVenta!=null)
            for(int i=0; i<_empresasRegistranVenta.size(); i++){
                System.out.println(formatoDebugFecha.format(new Date())+":"+"fol, emp::: "+_folioBol.get(i).toString()+" - "+ _empresasRegistranVenta.get(i).toString());
                //actualizaUltimoFolioVendido(_folioBol.get(i).toString(), _empresasRegistranVenta.get(i).toString(), new Timestamp(lFecha));
                System.out.println(formatoDebugFecha.format(new Date())+":"+"21.- despues de actualizaUltimoFolioVendido i:"+i);

                incrementaVariosFolios(_empresasRegistranVenta.get(i).toString(), Long.valueOf(_folioBol.get(i).toString()));
                System.out.println(formatoDebugFecha.format(new Date())+":"+"22.- despues de incrementaVariosFolios i:"+i);
            }
            System.out.println(formatoDebugFecha.format(new Date())+":"+"23.- Despues FOR actualizaUltimoFolioVendido");
        }
        else
            System.out.println(formatoDebugFecha.format(new Date())+":"+"19.1.- No actualizaUltimoFolioVendido por que Vende Boleto Blanco");
        return 0;
    }
    
    public boolean liberaReferenciado(Vector ids)
    {
        int res = 0;
            if(this.getTipoTransaccion().equals("L"))
               res = getTmsVtaFacade().liberarReferenciados("",ids,getUserCon().getUsuarioId(), this.esquema);
            else
               res = getTmsVtaFacade().liberarReferenciados(this.DBLink,ids,getUserCon().getUsuarioId(), this.esquema); 
        
        if(res==0)
            return true;
        else
            return false;
    }
    
    private void ConexionReal(String strOrigen, String strDestino, String strServicio){
        Vector tmsServicioOrigsDestsV = new Vector();
        strCnxReal = new String[3];
        int i;
        for(i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(tmsServicioOrigsDestsV.get(7).toString().equals(strServicio) &&
               tmsServicioOrigsDestsV.get(10).toString().equals(strDestino) &&
               tmsServicioOrigsDestsV.get(4).toString().equals(strOrigen)){
                strCnxReal[0]=tmsServicioOrigsDestsV.get(13).toString();
                System.out.println("Ruta nombre encontrada: "+strCnxReal[0]+" Ciudad Origen: "+strCiudadOrigen+" - Tipo Boleto: "+this.TipoBoleto);
                if( (this.isBolRedCer() || !this.TipoBoleto.equals("R")) && !strCnxReal[0].equals(strCiudadOrigen)) continue;
                if(strCnxReal[0].equals(getUserCon().getTerminalNombre())) strCnxReal[1]="L";
                else strCnxReal[1]="R";
                strCnxReal[2]=getDBLinkReal(strCnxReal[0]);
                return;
            }
        }
        
        if(strCnxReal[1]==null){
            strCnxReal[0]=strOrigen;
            if(strCnxReal[0].equals(getUserCon().getTerminalNombre())) strCnxReal[1]="L";
            else strCnxReal[1]="R";
            strCnxReal[2]=getDBLinkReal(strCnxReal[0]);
            System.out.println("Datos finales: Origen "+strCnxReal[0]+" Ciudad Origen: "+strCiudadOrigen+" - Tipo Boleto: "+this.TipoBoleto+" (L:R) "+strCnxReal[1]+" LIGA: "+strCnxReal[2]);
        }
    }
    
    public String getOrigenCorrida(String strOrigen, String strDestino, String strServicio){
        Vector tmsServicioOrigsDestsV = new Vector();
        String strOrigenCorrida=strOrigen;
        int i;
        System.out.println("getOrigenCorrida: "+strOrigen);
        for(i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(tmsServicioOrigsDestsV.get(7).toString().equals(strServicio) &&
               tmsServicioOrigsDestsV.get(10).toString().equals(strDestino) &&
               tmsServicioOrigsDestsV.get(4).toString().equals(strOrigen)){
                strOrigenCorrida=tmsServicioOrigsDestsV.get(13).toString();
                System.out.println("Ruta nombre encontrada("+strOrigen+"): "+strOrigenCorrida+" Ciudad Origen: "+strCiudadOrigen+" - Tipo Boleto: "+this.TipoBoleto);
                if(!strOrigenCorrida.equals(strCiudadOrigen)) continue;
                return strOrigenCorrida;
            }
        }
        
        if(strCnxReal[1]==null){
            strOrigenCorrida=strOrigen;
            System.out.println("Datos finales: Origen("+strOrigen+") "+strOrigenCorrida+" Ciudad Origen: "+strCiudadOrigen+" - Tipo Boleto: "+this.TipoBoleto);
            return strOrigenCorrida;
        }
        return strOrigenCorrida;
    }    
    
    public String getDBLinkReal(String Origen){
        String strDBLink;
        for(int i=0; i<tmsBaseDatosConfigTbl.size(); i++)
            if(Origen.equals(tmsBaseDatosConfigTbl.get(i).getNombreTerminal())){
                strDBLink=tmsBaseDatosConfigTbl.get(i).getNombreDblink();
                if(strDBLink==null || strDBLink.equals("")){
                    if(tmsBaseDatosConfigTbl.get(i).getEsquemaPropio().equals("S")) return "";
                    else return null;
                }
                else{
                    if(tmsBaseDatosConfigTbl.get(i).getEsquemaPropio().equals("S")) return "";
                    return strDBLink;
                }
            }
        return null;
    }

    private void _empresaFolioBoletos(String empresa, String folioPreimpreso) {
        if(_empresasRegistranVenta==null){
            _empresasRegistranVenta = new Vector();
            _folioBol = new Vector();
            _empresasRegistranVenta.add(empresa);
            _folioBol.add(folioPreimpreso);
        }
        else{
            int pos=yaExisteEmpresa(empresa);
            if(pos!=-1){
                _folioBol.set(pos, folioPreimpreso);
            }
            else{
                _empresasRegistranVenta.add(empresa);
                _folioBol.add(folioPreimpreso);
            }
        }
    }

    private int yaExisteEmpresa(String empresa) {
        for(int i=0; i<_empresasRegistranVenta.size(); i++)
            if(_empresasRegistranVenta.get(i).toString().equals(empresa)) return i;
        return -1;
    }
    
    public String ConexionRealBAX(String strOrigen, String strDestino, String strServicio){
        Vector tmsServicioOrigsDestsV = new Vector();
        String xa;
        int i;
        for(i=0; i<xxx.size(); i++){
            tmsServicioOrigsDestsV=(Vector) xxx.get(i);
            if(tmsServicioOrigsDestsV.get(7).toString().equals(strServicio) &&
               tmsServicioOrigsDestsV.get(10).toString().equals(strDestino) &&
               tmsServicioOrigsDestsV.get(4).toString().equals(strOrigen)){
                xa=tmsServicioOrigsDestsV.get(13).toString();
                return xa;
            }
        }
        return null;
    }
    
    public String getDBLinkRealBAX(String Origen){
        String strDBLink;
        for(int i=0; i<tmsBaseDatosConfigTbl.size(); i++)
            if(Origen.equals(tmsBaseDatosConfigTbl.get(i).getNombreTerminal())){
                strDBLink=tmsBaseDatosConfigTbl.get(i).getNombreDblink();
                if(strDBLink==null || strDBLink.equals("")){
                    if(tmsBaseDatosConfigTbl.get(i).getEsquemaPropio().equals("S")) return "";
                    else return null;
                }
                else{
                    if(tmsBaseDatosConfigTbl.get(i).getEsquemaPropio().equals("S")) return "";
                    return strDBLink;
                }
            }
        return null;
    }   
    
    public double obtieneFondoInicialOriginal(){
        String xfi=getTmsVtaFacade().obtieneFondoInicial(getUserCon().getCorteId(), 0);
        if(xfi==null) return -1;
        double dfi;
        try{
           if(getUserCon().getAplicacionVenta())
            dfi=Double.valueOf(xfi);
           else
              dfi = 0; 
        }catch(NumberFormatException nfex){
            return -1;
        }
        
        return dfi;
    }
    
    public boolean fechaHoraBD(){ // obtiene la fecha hora de la base de datos
        String valor = getTmsVtaFacade()._ObtieneFechaHoraBD();
        System.out.println("SP FechaHora_cliente "+(valor==null?"null":valor));
        if(valor==null) return false;
        Date fHSistemaVenta = null;
        try {
            fHSistemaVenta = new Date(formatoFechaHoraBD.parse(valor).getTime());
        } catch (ParseException ex) {
            return false;
        }
        setFechaHoraSistemaVenta(fHSistemaVenta);
        return true;
    }
    
    public String[] armaCadenaBolCn(TmsBoletosVentaTbl BCn, String tx, String _DBLink, String valPrefijo){
        String Campos;
        String Valores;
        String strAux;
        if(!_DBLink.equals("")) _DBLink="@"+_DBLink;
        Campos="(BOLETO_ID, ";
        Valores="('"+valPrefijo+"' || TMS_BOLETOS_VENTA_SEQ.NEXTVAL"+_DBLink+", ";
        strAux="";
        
        Campos=Campos+"EMPRESA, ";
        Valores=Valores+"'"+BCn.getEmpresa()+"', ";
        
        Campos=Campos+"SERVICIO, ";
        Valores=Valores+"'"+BCn.getServicio()+"', ";
        
        Campos=Campos+"CAJA, ";
        Valores=Valores+"'"+getUserCon().getCaja()+"', ";
        
        Campos=Campos+"CORTE_ID, ";
        Valores=Valores+getUserCon().getCorteId()+", ";
        
        Campos=Campos+"CLAVE_CORRIDA, ";
        Valores=Valores+"'"+BCn.getClaveCorrida()+"', ";
        
        Campos=Campos+"CLIENTE_ID, ";
        if(BCn.getClienteId()==null) strAux="NULL"; else strAux=BCn.getClienteId().toString();
        Valores=Valores+strAux+", ";
        
        Campos=Campos+"NO_ASIENTO, ";
        Valores=Valores+"'"+BCn.getNoAsiento()+"', ";
        
        Campos=Campos+"NOMBRE_PASAJERO, ";
        if(BCn.getNombrePasajero()==null) strAux="NULL"; else strAux="'"+BCn.getNombrePasajero()+"'";
        Valores=Valores+strAux+", ";
        
        Campos=Campos+"TIPO_PASAJERO, ";
        Valores=Valores+"'"+BCn.getTipoPasajero()+"', ";
        
        Campos=Campos+"TIPO_PAGO, ";
        Valores=Valores+"'"+BCn.getTipoPago()+"', ";
        
        Campos=Campos+"REFERENCIA_PAGO, ";
        if(BCn.getReferenciaPago()==null) strAux="NULL"; else strAux="'"+BCn.getReferenciaPago()+"'";
        Valores=Valores+strAux+", ";
        
        
        Campos=Campos+"IMPORTE_BOLETO, ";
        Valores=Valores+(BCn.getImporteBoleto()*-1)+", ";
        
        Campos=Campos+"TIPO_OPERACION, ";
        Valores=Valores+"'CN', ";
        
        Campos=Campos+"RESERVACION_ID, ";
        Valores=Valores+BCn.getReservacionId()+", ";
        
        Campos=Campos+"BOLETO_RELACIONADO_ID, ";
        Valores=Valores+BCn.getBoletoRelacionadoId()+", ";
        
        Campos=Campos+"DIAS_VALIDEZ_BOLETO_ABIERTO, ";
        if(BCn.getDiasValidezBoletoAbierto()==null) strAux="NULL"; else strAux=BCn.getDiasValidezBoletoAbierto().toString();
        Valores=Valores+strAux+", ";
        
        Campos=Campos+"FOLIO_PREIMPRESO, ";
        Valores=Valores+"'"+BCn.getFolioPreimpreso()+"', ";
        
        Campos=Campos+"FOLIO_BOLETO, ";
        Valores=Valores+"'"+BCn.getFolioBoleto()+"', ";
        
        Campos=Campos+"CIUDAD_VENTA, ";
        Valores=Valores+"'"+BCn.getCiudadVenta()+"', ";
        
        Campos=Campos+"ORIGEN, ";
        Valores=Valores+"'"+BCn.getOrigen()+"', ";
        
        Campos=Campos+"DESTINO, ";
        Valores=Valores+"'"+BCn.getDestino()+"', ";
        
        Campos=Campos+"TIPO_TRANSACCION, ";
        Valores=Valores+"'"+tx+"', ";
        
        Campos=Campos+"CLAVE_CAJERO, ";
        Valores=Valores+"'"+BCn.getClaveCajero()+"', ";
        
        Campos=Campos+"FECHA_HORA_VENTA, ";
        Valores=Valores+"SYSDATE, ";
        
        Campos=Campos+"AUTORIZADO_POR, ";
        Valores=Valores+"'"+BCn.getAutorizadoPor()+"', ";
        
        Campos=Campos+"FECHA_HORA_AUTORIZACION, ";
        Valores=Valores+"SYSDATE, ";

        Campos=Campos+"CREADO_POR, ";
        Valores=Valores+BCn.getCreadoPor()+", ";
        
        Campos=Campos+"FECHA_CREACION, ";
        Valores=Valores+"SYSDATE, ";

        Campos=Campos+"ULTIMA_ACTUALIZACION_POR, ";
        Valores=Valores+BCn.getUltimaActualizacionPor()+", ";

        Campos=Campos+"ULTIMA_FECHA_ACTUALIZACION, ";
        Valores=Valores+"SYSDATE, ";
        
        Campos=Campos+"ADICIONAL1, ";
        Valores=Valores+"'"+BCn.getAdicional1()+"', ";
        
        Campos=Campos+"ADICIONAL2, ";
        Valores=Valores+"'"+BCn.getAdicional2()+"', ";
        Campos=Campos+"ADICIONAL3, ";
        Valores=Valores+"'"+BCn.getAdicional3()+"', ";
        Campos=Campos+"ADICIONAL4, ";
        Valores=Valores+"'"+BCn.getAdicional4()+"', ";
        Campos=Campos+"ADICIONAL5, ";
        Valores=Valores+"'"+BCn.getAdicional5()+"', ";
        Campos=Campos+"ADICIONAL6, ";
        Valores=Valores+"'"+BCn.getAdicional6()+"', ";
        Campos=Campos+"ADICIONAL7, ";
        Valores=Valores+"'"+BCn.getAdicional7()+"', ";
        Campos=Campos+"ADICIONAL8, ";
        //Valores=Valores+"'"+BCn.getAdicional8()+"', ";
        Valores=Valores+"NULL, ";
        Campos=Campos+"ADICIONAL9, ";
        Valores=Valores+"'"+BCn.getAdicional9()+"', ";
        Campos=Campos+"ADICIONAL10,";
        Valores=Valores+"'"+BCn.getAdicional10()+"', ";
        Campos=Campos+"ADICIONAL12,";
        Valores=Valores+"'"+BCn.getAdicional12()+"', ";
        Campos=Campos+"ADICIONAL13,";
        Valores=Valores+"'"+BCn.getAdicional13()+"', ";
        Campos=Campos+"ADICIONAL14,";
        Valores=Valores+"'"+BCn.getAdicional14()+"', ";
        Campos=Campos+"ADICIONAL15)";
        Valores=Valores+"'"+BCn.getAdicional15()+"') ";
        //Valores=Valores+"NULL)";
        
        String[] strBCn=new String[2];
        strBCn[0] = Campos;
        strBCn[1] = Valores;
        return strBCn;
    }
    
    public String TerminarReservacionLocal(Object[][] Boletos, long corridaId, String cancelable, String asientos, String tiposPasajero){
        String pDBLink="";
        if(!this.DBLink.equals("")) pDBLink = "@" + this.DBLink;
        
        String claveRvn="-1";
        iTempos=0;
        
        while(claveRvn.equals("-1")){
            System.out.println(pDBLink+" - "+corridaId+" - "+asientos+" - "+tiposPasajero+" - "+(Boletos[0][12]==null?"":Boletos[0][12].toString())+" - "+
                    Boletos[0][3].toString()+" - "+cancelable+" - "+"L"+" - "+getUserCon().getUsuarioId());
            claveRvn = getTmsVtaFacade()._ReservarAsientosSP(pDBLink, corridaId, asientos, tiposPasajero, (Boletos[0][12]==null?"":Boletos[0][12].toString()),
                                                         Boletos[0][3].toString(), cancelable, "L", getUserCon().getUsuarioId());
            if(claveRvn.equals("-1")){
                iTempos++;
                if(iTempos>5) break;
            }
        }
        
        System.out.println("Valor Reservacion SP: "+claveRvn);
        if(claveRvn.contains("e_")){
            System.out.println("Registro de reservacion sin registro en asiento_no_disponible: "+claveRvn);
            claveRvn=null;
        }
        
        return claveRvn;
    }

    public long getCorridaId(String cCorrida) {
        System.out.println("getCorridaId::: "+this.DBLink+" - "+cCorrida);
        return getTmsVtaFacade().getCorridaId(this.DBLink, cCorrida);
    }

    private String origenCorridaReal(String valorOrig, String o1) {
        String valor="";
        int j=0;
        for(int i=0; i<tmsEstadosV.size(); i++){
            j=tmsEstadosV.get(i).getEstadoNombre().length();
            j=(j>4?4:j);
            valor=tmsEstadosV.get(i).getEstadoNombre().substring(0,j);
            if(valorOrig.equals(valor)){
                System.out.println("Retorno origen real: "+tmsEstadosV.get(i).getEstadoNombre());
                return tmsEstadosV.get(i).getEstadoNombre();
            }
        }
        System.out.println("Mala configuracion de origen");
        return o1;
    }
    
   public Object[][] buscaBoletoVendidoParaHOMult(String Origen, String FolioPreimpreso,
            String noAsiento, String nombreEmpresa, int ctdBoletosSolicitados){
        getDBLinkClaveOrigen(Origen);
        List<TmsBoletosVentaTbl> boletoACancelar = null; String[] estadoTViaje; String origenCorrida;
        try{
            if(!abreSocketAS()) return null; // MOD:: NIVEL -21
            if(this.getTipoTransaccion().equals("L")){ // TRANSACCION LOCAL
                boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaHOMult("", "", Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                if(boletoACancelar == null || boletoACancelar.size()==0 || boletoACancelar.size()!=ctdBoletosSolicitados) return null;
                origenCorrida=boletoACancelar.get(0).getClaveCorrida().substring(0,4).replace("_","");
                System.out.println("origenCorrida: "+origenCorrida+" origen: "+Origen);
                origenCorrida = origenCorridaReal(origenCorrida, Origen);
                System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+Origen);
                if(origenCorrida.equals(Origen)){ // realmente es transaccion local
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", "", boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
                else{ // transaccion realmente remota
                    Origen=origenCorrida;
                    setTipoTransaccion("R");
                    getDBLinkClaveOrigen(Origen);
                    boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaHOMult("", this.DBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                    if(boletoACancelar == null || boletoACancelar.size()==0 || boletoACancelar.size()!=ctdBoletosSolicitados) return null;
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
            }
            else{ // TRANSACCION REMOTA
                boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaHOMult("", this.DBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                if(boletoACancelar == null || boletoACancelar.size()==0 || boletoACancelar.size()!=ctdBoletosSolicitados) return null;
                origenCorrida=boletoACancelar.get(0).getClaveCorrida().substring(0,4).replace("_","");
                System.out.println("origenCorrida: "+origenCorrida+" origen: "+Origen);
                origenCorrida = origenCorridaReal(origenCorrida, Origen);
                System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+Origen);
                if(origenCorrida.equals(Origen)){ // realmente es transaccion remota
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                     if(!estadoTViaje[0].equals("ABIERTA")){
                        if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                        return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
                else{ // transaccion realmente es extraremota
                    Origen=origenCorrida;
                    getDBLinkClaveOrigen(Origen);
                    boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaHOMult("", this.DBLink, Origen, FolioPreimpreso, noAsiento, nombreEmpresa);
                    if(boletoACancelar == null || boletoACancelar.size()==0 || boletoACancelar.size()!=ctdBoletosSolicitados) return null;
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }
            }
        }catch(javax.ejb.EJBException ejbex){
            return null;
        }
        setTmsBoletosVentaTbl(boletoACancelar.get(0));
        setVariosTmsBoletosVentaTbl(boletoACancelar);
        Object[][] boletos = new Object[getVariosTmsBoletosVentaTbl().size()][13];
        for(int i=0; i<boletos.length; i++){
            System.out.println("(HO)Folio = "+getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso());
            System.out.println("Adicional8 = "+getVariosTmsBoletosVentaTbl().get(i).getAdicional8());
            System.out.println("Adicional9 = "+getVariosTmsBoletosVentaTbl().get(i).getAdicional9());
            System.out.println("Adicional15 = "+getVariosTmsBoletosVentaTbl().get(i).getAdicional15());
            System.out.println("ImporteBoleto = "+getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto());
            System.out.println("Viajado = "+getVariosTmsBoletosVentaTbl().get(i).getViajado());
            boletos[i][0] = getVariosTmsBoletosVentaTbl().get(i).getClaveCorrida().substring(0,10);
            boletos[i][1] = formatoFechaHora.format(getVariosTmsBoletosVentaTbl().get(i).getFechaHoraVenta());
            boletos[i][2] = getVariosTmsBoletosVentaTbl().get(i).getNoAsiento();
            boletos[i][3] = getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero();
            boletos[i][4] = getVariosTmsBoletosVentaTbl().get(i).getNombrePasajero()==null ? " " : getVariosTmsBoletosVentaTbl().get(i).getNombrePasajero();
            boletos[i][5] = getVariosTmsBoletosVentaTbl().get(i).getOrigen();
            boletos[i][6] = getVariosTmsBoletosVentaTbl().get(i).getDestino();
            boletos[i][7] = getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto();
            //boletos[i][7] = getVariosTmsBoletosVentaTbl().get(i).getAdicional15();
            boletos[i][8] = getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso();
            boletos[i][9] = getVariosTmsBoletosVentaTbl().get(i).getTipoPago();
            boletos[i][10] = getVariosTmsBoletosVentaTbl().get(i).getEmpresa();
            //boletos[i][11] = Double.valueOf(getVariosTmsBoletosVentaTbl().get(i).getAdicional15())-getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto();
            boletos[i][11] = Double.valueOf(getVariosTmsBoletosVentaTbl().get(i).getAdicional15());//CARGO
            boletos[i][12] = getVariosTmsBoletosVentaTbl().get(i).getViajado();
        }
        return boletos;
    }
        
  public Object[][] buscaBoletoVendidoParaHOMultRef(String Origen, String FolioPreimpreso){
        getDBLinkClaveOrigen(Origen);
        List<TmsBoletosVentaTbl> boletoACancelar = null; String[] estadoTViaje; String origenCorrida;
        try{
            if(!abreSocketAS()) return null; // MOD:: NIVEL -21
            if(this.getTipoTransaccion().equals("L")){ // TRANSACCION LOCAL
                boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaHORef("", "", FolioPreimpreso);
                if(boletoACancelar == null || boletoACancelar.size()==0 ) return null;
                origenCorrida=boletoACancelar.get(0).getClaveCorrida().substring(0,4).replace("_","");
                System.out.println("origenCorrida: "+origenCorrida+" origen: "+Origen);
                origenCorrida = origenCorridaReal(origenCorrida, Origen);
                System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+Origen);
                if(origenCorrida.equals(Origen)){ // realmente es transaccion local
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", "", boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                        if(!estadoTViaje[0].equals("ABIERTA")){
                            if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                            return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }

            }
            else{ // TRANSACCION REMOTA
                boletoACancelar = getTmsVtaFacade().BuscaBoletoVendidoParaHORef("", this.DBLink, FolioPreimpreso);
                if(boletoACancelar == null || boletoACancelar.size()==0) return null;
                origenCorrida=boletoACancelar.get(0).getClaveCorrida().substring(0,4).replace("_","");
                System.out.println("origenCorrida: "+origenCorrida+" origen: "+Origen);
                origenCorrida = origenCorridaReal(origenCorrida, Origen);
                System.out.println("origenCorrida Real: "+origenCorrida+" origen: "+Origen);
                if(origenCorrida.equals(Origen)){ // realmente es transaccion remota
                    estadoTViaje = getTmsVtaFacade().queryBoletoConTJAbierta("", this.DBLink, boletoACancelar.get(0).getClaveCorrida());
                    if(estadoTViaje!=null){
                     if(!estadoTViaje[0].equals("ABIERTA")){
                        if(estadoTViaje[0].equals("RECAUDADA"))
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la corrida ya ha sido recaudada.</html>", Color.RED);
                            else
                                DialogoAceptar.mostrarDialogo("Transaccion de boletos.","<html>La transaccion no puede realizarse, la tarjeta de la corrida ya ha sido cancelada.</html>", Color.RED);
                        return null;
                        }
                        try {
                            dtFechaDespachoTarjeta=new Date(formatoDateServer.parse(estadoTViaje[1]).getTime());
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                            return null;
                        }
                    }
                }

            }
        }catch(javax.ejb.EJBException ejbex){
            return null;
        }
        setTmsBoletosVentaTbl(boletoACancelar.get(0));
        setVariosTmsBoletosVentaTbl(boletoACancelar);
//        Object[][] boletos = new Object[getVariosTmsBoletosVentaTbl().size()][11];
//        for(int i=0; i<boletos.length; i++){
//            boletos[i][0] = getVariosTmsBoletosVentaTbl().get(i).getClaveCorrida().substring(0,10);
//            boletos[i][1] = formatoFechaHora.format(getVariosTmsBoletosVentaTbl().get(i).getFechaHoraVenta());
//            boletos[i][2] = getVariosTmsBoletosVentaTbl().get(i).getNoAsiento();
//            boletos[i][3] = getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero();
//            boletos[i][4] = getVariosTmsBoletosVentaTbl().get(i).getNombrePasajero()==null ? " " : getVariosTmsBoletosVentaTbl().get(i).getNombrePasajero();
//            boletos[i][5] = getVariosTmsBoletosVentaTbl().get(i).getOrigen();
//            boletos[i][6] = getVariosTmsBoletosVentaTbl().get(i).getDestino();
//            boletos[i][7] = getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto();
//            boletos[i][8] = getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso();
//            boletos[i][9] = getVariosTmsBoletosVentaTbl().get(i).getTipoPago();
//            boletos[i][10] = getVariosTmsBoletosVentaTbl().get(i).getEmpresa();
//        }

        Object[][] boletos = new Object[getVariosTmsBoletosVentaTbl().size()][14];
        
        for(int i=0; i<getVariosTmsBoletosVentaTbl().size(); i++){
            boletos[i][0] = getVariosTmsBoletosVentaTbl().get(i).getClaveCorrida();
            //System.out.println("fecha("+i+"): "+getVariosTmsBoletosVentaTbl().get(i).getFecha()+" -> "+ formatoFechaHora.format(getVariosTmsBoletosVentaTbl().get(i).getFecha()));
            boletos[i][1] = formatoFechaHora.format(getVariosTmsBoletosVentaTbl().get(i).getFecha());
            //boletos[i][1] = getVariosTmsBoletosVentaTbl().get(i).getFcorrida() + " "+ getVariosTmsBoletosVentaTbl().get(i).getHora();
            boletos[i][2] = getVariosTmsBoletosVentaTbl().get(i).getNoAsiento();
            boletos[i][3] = getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero();
            boletos[i][4] = getVariosTmsBoletosVentaTbl().get(i).getOrigen();
            boletos[i][5] = getVariosTmsBoletosVentaTbl().get(i).getDestino();
            boletos[i][6] = getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto();
            boletos[i][7] = getVariosTmsBoletosVentaTbl().get(i).getNombrePasajero();
            boletos[i][8] = getVariosTmsBoletosVentaTbl().get(i).getNombreAutorizado();
            boletos[i][9] = "N";
            boletos[i][10] = "";
            boletos[i][11] = getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso();
            boletos[i][12] = getVariosTmsBoletosVentaTbl().get(i).getTipoPago();
            boletos[i][13] = getVariosTmsBoletosVentaTbl().get(i).getEmpresa();
        }
        
        
        return boletos;
    }
        
    public Object[][] buscaBoletoAbiertoMult(String Origen, String FolioPreimpreso, String Servicio, String empresa, int ctdBoletosSolicitados){
        getDBLinkClaveOrigen(Origen);
        List<TmsBoletosVentaTbl> boletoACancelar = null;
        try{
            System.out.println("Busca BA Mult: "+Origen+" - "+FolioPreimpreso+" - "+Servicio+" - "+empresa+" --- "+this.DBLink);
            if(!abreSocketAS()) return null; // MOD:: NIVEL -21
            if(this.getTipoTransaccion().equals("L"))
                boletoACancelar=getTmsVtaFacade().BuscaBoletoAbiertoMult("","",Origen, FolioPreimpreso, Servicio, empresa);
            else
                boletoACancelar=getTmsVtaFacade().BuscaBoletoAbiertoMult("", this.DBLink, Origen, FolioPreimpreso, Servicio, empresa);
        }catch(javax.ejb.EJBException ejbex){
            return null;
        }
        if(boletoACancelar == null || boletoACancelar.size()==0 || boletoACancelar.size()!=ctdBoletosSolicitados) return null;
        
        setTmsBoletosVentaTbl(boletoACancelar.get(0));
        setVariosTmsBoletosVentaTbl(boletoACancelar);
        Object[][] boletos = new Object[getVariosTmsBoletosVentaTbl().size()][11];
        
        for(int i=0; i<boletos.length; i++){
            boletos[i][0] = null;
            //boletos[i][1] = formatoFechaHora.format(getVariosTmsBoletosVentaTbl().get(i).getFechaHoraVenta());
            boletos[i][1] =  (getVariosTmsBoletosVentaTbl().get(i).getFcorrida()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getFcorrida())+" "+(getVariosTmsBoletosVentaTbl().get(i).getHora()==null ?"" :getVariosTmsBoletosVentaTbl().get(i).getHora() ) ;
            boletos[i][2] = ""; // asiento
            boletos[i][3] = getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero();
            boletos[i][4] = getVariosTmsBoletosVentaTbl().get(i).getNombrePasajero();
            boletos[i][5] = getVariosTmsBoletosVentaTbl().get(i).getOrigen();
            boletos[i][6] = getVariosTmsBoletosVentaTbl().get(i).getDestino();
            boletos[i][7] = getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto();
            boletos[i][8] = getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso();
            boletos[i][9] = getVariosTmsBoletosVentaTbl().get(i).getTipoPago();
        }
        return boletos;
    }

    public Object[][] buscaBoletoAbiertoMultRef(String Origen, String FolioPreimpreso){
        getDBLinkClaveOrigen(Origen);
        List<TmsBoletosVentaTbl> boletoACancelar = null;
        try{
            System.out.println("Busca BA MultRef: "+Origen+" - "+FolioPreimpreso+" --- "+this.DBLink);
            if(!abreSocketAS()) return null; // MOD:: NIVEL -21
            if(this.getTipoTransaccion().equals("L"))
                boletoACancelar=getTmsVtaFacade().BuscaBoletoAbiertoRef("","", FolioPreimpreso);
            else
                boletoACancelar=getTmsVtaFacade().BuscaBoletoAbiertoRef("", this.DBLink, FolioPreimpreso);
        }catch(javax.ejb.EJBException ejbex){
            return null;
        }
        if(boletoACancelar == null || boletoACancelar.size()==0 ) return null;
        
        setTmsBoletosVentaTbl(boletoACancelar.get(0));
        setVariosTmsBoletosVentaTbl(boletoACancelar);
        Object[][] boletos = new Object[getVariosTmsBoletosVentaTbl().size()][11];
        
        for(int i=0; i<boletos.length; i++){
            boletos[i][0] = null;
            //boletos[i][1] = formatoFechaHora.format(getVariosTmsBoletosVentaTbl().get(i).getFechaHoraVenta());
            boletos[i][1] = (getVariosTmsBoletosVentaTbl().get(i).getFcorrida()== null ?"" :getVariosTmsBoletosVentaTbl().get(i).getFcorrida())+" "+(getVariosTmsBoletosVentaTbl().get(i).getHora() == null ?"" :getVariosTmsBoletosVentaTbl().get(i).getHora());
            boletos[i][2] = ""; // asiento
            boletos[i][3] = getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero();
            boletos[i][4] = getVariosTmsBoletosVentaTbl().get(i).getOrigen();
            boletos[i][5] = getVariosTmsBoletosVentaTbl().get(i).getDestino();
            boletos[i][6] = getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto();
            boletos[i][7] = getVariosTmsBoletosVentaTbl().get(i).getNombrePasajero();
            boletos[i][8] = getVariosTmsBoletosVentaTbl().get(i).getNombreAutorizado();
            boletos[i][9] = "N";
            boletos[i][10] = "";
        }
        return boletos;
    }

    
    public Object[][] buscaBoletosReferencia(String Origen, String referencia){
        getDBLinkClaveOrigen(Origen);
        try{
            System.out.println("Busca BF: "+Origen+" - "+referencia+" --- "+this.DBLink);
            if(!abreSocketAS()) return null; // MOD:: NIVEL -21
            if(this.getTipoTransaccion().equals("L"))
                boletoACanjear=getTmsVtaFacade().buscaBoletosReferencia("","", referencia);
            else
                boletoACanjear=getTmsVtaFacade().buscaBoletosReferencia("", this.DBLink, referencia);
        }catch(javax.ejb.EJBException ejbex){
            return null;
        }
        if(boletoACanjear == null || boletoACanjear.size()==0) return null;
        
        setTmsBoletosVentaTbl(boletoACanjear.get(0));
        setVariosTmsBoletosVentaTbl(boletoACanjear);
        
        Object[][] boletos = new Object[getVariosTmsBoletosVentaTbl().size()][11];
        
        for(int i=0; i<boletos.length; i++){
            boletos[i][0] = getVariosTmsBoletosVentaTbl().get(i).getClaveCorrida();
            if(getVariosTmsBoletosVentaTbl().get(i).getFecha()!=null)
                //boletos[i][1] = formatoFechaHora.format(getVariosTmsBoletosVentaTbl().get(i).getFecha());
                boletos[i][1] = getVariosTmsBoletosVentaTbl().get(i).getFcorrida()+" "+getVariosTmsBoletosVentaTbl().get(i).getHora();
            else
                boletos[i][1] = "";
            boletos[i][2] = getVariosTmsBoletosVentaTbl().get(i).getNoAsiento();
            boletos[i][3] = getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero();
            boletos[i][4] = getVariosTmsBoletosVentaTbl().get(i).getOrigen();
            boletos[i][5] = getVariosTmsBoletosVentaTbl().get(i).getDestino();
            boletos[i][6] = getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto();
            boletos[i][7] = getVariosTmsBoletosVentaTbl().get(i).getNombrePasajero();
            boletos[i][8] = getVariosTmsBoletosVentaTbl().get(i).getNombreAutorizado();
            boletos[i][9] = "N";
            boletos[i][10] = "";
        }
        return boletos;
    }

    public boolean liberarSelectBoletosRef(){
        String liberar = "";
        for(int i = 0 ; i < boletoACanjear.size(); i++)
        {
            if((i + 1) < boletoACanjear.size())
                liberar = liberar + boletoACanjear.get(i).getBolReferenciadoId()+",";
            else
                liberar = liberar +boletoACanjear.get(i).getBolReferenciadoId();
        }   
        boolean regresa = true;
        try{
            if(!abreSocketAS()) return false; 
            if(this.getTipoTransaccion().equals("L"))
                regresa=getTmsVtaFacade().liberarSelectBoletosRef("","", liberar);
            else
                regresa=getTmsVtaFacade().liberarSelectBoletosRef("", this.DBLink, liberar);
        }catch(javax.ejb.EJBException ejbex){
            return false;
        }
        return regresa;
    }
    
  public Vector buscaRegistroLealtadAcumHO(){
        boolean local = true;
        String dbl = "";
        Vector vvec = null;
        for(int i=0; i<getVariosTmsBoletosVentaTbl().size(); i++){  
                    String cventa  = getVariosTmsBoletosVentaTbl().get(i).getCiudadVenta();
                    System.out.println("cventa = "+cventa);
                    System.out.println("terminal = "+userCon.getTerminalNombre());
                    if(cventa.equals(userCon.getTerminalNombre()))
                        local = true;
                    else { local = false; dbl = buscarDBlink(cventa); }
                    System.out.println("es local? "+local);
                    System.out.println("DBLink(Cancelar Lealtad) = "+dbl);
                     if(local) vvec = getTmsVtaFacade().buscaRegistroLealtadAcumulaHO(getVariosTmsBoletosVentaTbl().get(i).getAdicional8().toString(), getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso(),"","");
                     else      vvec = getTmsVtaFacade().buscaRegistroLealtadAcumulaHO(getVariosTmsBoletosVentaTbl().get(i).getAdicional8().toString(), getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso(),"",dbl);
        }
        return vvec;                     
  }   
    
    
    public void insertaRegistroLealtad(int index, String producto, String num_Tarjeta, String referencia, String unidad_negocio,String tipo){
    //String boleto_id,String folio_preimpreso, String preoducto, String ciudad_venta,String tipo_operacion,String num_tarjeta,String numero_operacion, String usuario, String contraseña, String importe, String tipo_pasajero, String caja, String unidad_negocio, String descuento
    //    sesionVenta.getBoletosLealtad();
        Object[][] lb = this.getBoletosLealtad();
//        System.out.println("tipo: "+tipo);
//        System.out.println("lb[index][19]: "+lb[index][19]);
//        System.out.println("producto: "+producto);
//        System.out.println("this.getTerminalLealtad(): "+this.getTerminalLealtad());
//        System.out.println("num_Tarjeta: "+num_Tarjeta);
//        System.out.println("referencia: "+referencia);
//        System.out.println("this.getUsuarioLealtad():"+ this.getUsuarioLealtad());
//        System.out.println("this.getPasswordLealtad(): "+this.getPasswordLealtad());
//        System.out.println("lb[index][5]: "+lb[index][5]);
//        System.out.println("lb[index][2]: "+lb[index][2]);
//        System.out.println("this.getUserCon().getCaja(): "+this.getUserCon().getCaja());
//        System.out.println("unidad_negocio: "+unidad_negocio);
//        System.out.println("this.getUserCon().getUsuarioId(): "+this.getUserCon().getUsuarioId());
//        System.out.println("Folio Boleto(insertaRegistroLealtad) :"+lb[index][19].toString());
        this.getTmsVtaFacade().insertaRegistroLealtad("-1",((tipo.equals("R"))?"000000" :lb[index][19].toString() ),producto,this.getTerminalLealtad(), tipo,num_Tarjeta,referencia,this.getUsuarioLealtad(), this.getPasswordLealtad(), lb[index][5].toString(), lb[index][2].toString(),this.getUserCon().getCaja(),unidad_negocio,"0", this.getUserCon().getUsuarioId(),"","",""+ this.userCon.getTerminalId());
    }
    
    public boolean cancelaLealtad(){
        boolean respuesta= false;
        String referencia = ""+this.getFechaHoraSistemaVentaLealtad();
        referencia = referencia.replace('[',' ');
        referencia = referencia.replace(']',' ');
        referencia = referencia.trim();
        referencia = this.getUserCon().getTerminalId()+""+this.getUserCon().getCajaId()+""+referencia+"1";
        System.out.println("Cancelar boleto lealtad: referencia = "+referencia);
        boolean encontrados = true;
        boolean lelatad = false;
        boolean local = true;
        String dbl = "";
        String terminalId = "";
        System.out.println(" getVariosTmsBoletosVentaTbl().size() "+getVariosTmsBoletosVentaTbl().size());
        for(int i=0; i<getVariosTmsBoletosVentaTbl().size(); i++){                    
            //if(getVariosTmsBoletosVentaTbl().get(i).getAdicional8()!=null){
                //if(!getVariosTmsBoletosVentaTbl().get(i).getAdicional8().equals(""))
                //{   
                    String cventa  = getVariosTmsBoletosVentaTbl().get(i).getCiudadVenta();
                    System.out.println("cventa = "+cventa);
                    System.out.println("terminal = "+userCon.getTerminalNombre());
                    if(cventa.equals(userCon.getTerminalNombre()))
                        local = true;
                    else { 
                        local = false; 
                        dbl = buscarDBlink(cventa); 
                        terminalId = buscarTerminalId(cventa); 
                        }
                    
                    System.out.println("es local? "+local);
                    System.out.println("DBLink(Cancelar Lealtad) = "+dbl);
                    Vector vvec = null;
                    
                     if(local) vvec = getTmsVtaFacade().buscaRegistroLealtad(getVariosTmsBoletosVentaTbl().get(i).getAdicional8().toString(), getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso(),"","");
                     else      vvec = getTmsVtaFacade().buscaRegistroLealtad(getVariosTmsBoletosVentaTbl().get(i).getAdicional8().toString(), getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso(),"",dbl);
                    System.out.println("vvec "+vvec);
                    if(vvec.size()==0)
                    {
                        respuesta = true;
                        encontrados = false;
                        System.out.println("El Folio ya esta cancelado(SOC): "+getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso());
                        System.out.println("estaba registrado con la operacion: "+getVariosTmsBoletosVentaTbl().get(i).getAdicional8().toString());
                        
                    }
                    else 
                    {   
                        if(vvec.size()>1)
                        {
                            System.out.println("Se encontro dos veces el folio: "+getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso()+" ==> "+vvec);
                            System.out.println(" con la operacion: "+getVariosTmsBoletosVentaTbl().get(i).getAdicional8().toString());
                        }
                        Vector v = (Vector)vvec.get(0);
                        //String boleto_id,String folio_preimpreso, String preoducto, String ciudad_venta,String tipo_operacion,String num_tarjeta,String numero_operacion, String usuario, String contraseña, String importe, String tipo_pasajero, String caja, String unidad_negocio, String descuento,long user
                        System.out.println("getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso() "+getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso());                        
                        System.out.println("v.get(0).toString() "+v.get(0).toString());
                        System.out.println("v.get(1).toString() "+v.get(1).toString());
                        System.out.println("v.get(2).toString() "+v.get(2).toString());
                        System.out.println("getVariosTmsBoletosVentaTbl().get("+i+").getAdicional9() "+getVariosTmsBoletosVentaTbl().get(i).getAdicional9());
                                                                 //String boleto_id,String folio_preimpreso                                  ,String preoducto                    ,String ciudad_venta      ,String tipo_operacion,String num_tarjeta,String numero_operacion, String usuario, String contraseña, String importe, String tipo_pasajero, String caja, String unidad_negocio, String descuento,long user, String pDBSchema, String pDBLink){  
                        if(local)this.getTmsVtaFacade().insertaRegistroLealtad("-1",getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso(),v.get(2).toString(),this.getTerminalLealtad(), "C",v.get(0).toString(),referencia,this.getUsuarioLealtad(), this.getPasswordLealtad(), ""+getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto(),getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero(),this.getUserCon().getCaja(),v.get(1).toString(),"0", this.getUserCon().getUsuarioId(),"","",""+this.userCon.getTerminalId());
                        else     this.getTmsVtaFacade().insertaRegistroLealtad("-1",getVariosTmsBoletosVentaTbl().get(i).getFolioPreimpreso(),v.get(2).toString(),this.getTerminalLealtad(), "C",v.get(0).toString(),referencia,this.getUsuarioLealtad(), this.getPasswordLealtad(), ""+getVariosTmsBoletosVentaTbl().get(i).getImporteBoleto(),getVariosTmsBoletosVentaTbl().get(i).getTipoPasajero(),this.getUserCon().getCaja(),v.get(1).toString(),"0", this.getUserCon().getUsuarioId(),"",dbl,terminalId);
                        System.out.println("Despues de insertar el registro para cancelar...");
                        lelatad = true;
                    }
                //}
            //}
        }//for
        if(lelatad)// && local)
        {
                System.out.println("Cancelar Estrellas...");
            if(!this.isUsarWSLelatad())                
            {
                try{
                   System.out.println("Registra_Transaccion_Lealtad ("+referencia+")...");
                   String respuestaLealtad = this.getTmsVtaFacade().Registra_Transaccion_Lealtad(referencia,dbl);
                   String[] arrayrsp = respuestaLealtad.split(",");
                   respuesta = Boolean.parseBoolean(arrayrsp[0]);//(arrayrsp[0).toString().equals("0") ?true :false);
                   System.out.println("Respueesta(C0): "+arrayrsp[0]);
                    //if(Boolean.parseBoolean(arrayrsp[0)))
                        System.out.println("Respueesta(C1): "+arrayrsp[1]);
                        System.out.println("Respueesta(C2): "+arrayrsp[2]);
                        if(Boolean.parseBoolean(arrayrsp[0]) && Boolean.parseBoolean(arrayrsp[2]))
                            DialogoAceptar.mostrarDialogo("¡Estrellas de Tarjeta!","<html><Font size=6> ¡ Sus Estrellas estan proximas a vencer!</Font></html>", Color.BLUE);              
                        
                        //DialogoAceptar.mostrarDialogo("¡Acumulacion de Estrellas!","<html><Font size=9> "+respuestaLealtad.get(1).replaceAll("\\\n\\\r","   <br>  ")+"</Font></html>", colorDialogoActivo);              
                } catch (Exception ex) {
                    System.out.println("Excepcion al llamar getOperacion(C)");
                    ex.printStackTrace();
                    //DialogoAceptar.mostrarDialogo("Acumulacion de Estrellas.","<html>En este momento no se puede mostrar el saldo</html>", Color.RED);
                    //DialogoAceptar.mostrarDialogo("Acumulación de Estrellas.","<html>Las Estrellas estan pendientes por acumular.<br>En un lapso no mayor a 24 Hrs. seran acumuladas</html>", Color.RED);
                }
            
            }
            else
            {
                try { // Call Web Service Operation
                    System.out.println("Result(getOperacion(C)("+referencia+"))");
                    wsLealtad.OperacionesResponse result = this.getWSPort().getOperacion(referencia);
                    //result.getStatus().isSuccess();
                    System.out.println("Result(getOperacion(C)("+referencia+")) = "+result.getStatus().isSuccess());
                    System.out.println("Mensaje Error(C)("+referencia+")) = "+result.getStatus().getMessage());
                } catch (Exception ex) {
                    System.out.println("Excepcion al llamar getOperacion(C) del WS");
                    ex.printStackTrace();
                }
            }
        }      
        this.detenerWS();
        return respuesta;
    }
    
    public String getVtaRef() {
        return vtaRef;
    }

    public String getNombreAutorizado() {
        return nombreAutorizado;
    }

    public void setNombreAutorizado(String nombreAutorizado) {
        this.nombreAutorizado = nombreAutorizado;
    }

    public String getReferenciaOriginal() {
        return referenciaOriginal;
    }

    public void setReferenciaOriginal(String referenciaOriginal) {
        this.referenciaOriginal = referenciaOriginal;
    }

    public boolean isCambHorRer() {
        return CambHorRer;
    }

    public void setCambHorRer(boolean CambHorRer) {
        this.CambHorRer = CambHorRer;
    }

    public TmsVtaFacadeRemote getTmsVtaFacade() {
        return tmsVtaFacade;
    }

    public String getReferenciaCanjeBA() {
        return referenciaCanjeBA;
    }

    public void setReferenciaCanjeBA(String referenciaCanjeBA) {
        this.referenciaCanjeBA = referenciaCanjeBA;
    }

    public String getEsquema() {
        return esquema;
    }

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }
    
   public void setReferenciadoId(String ref) {
        this.referenciadoId = ref;
    }

   public String getReferenciadoId() {
        return this.referenciadoId ;
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
   
    public boolean isEmpresaLealtad(String empresa){
        if(this.empresaLealtad.indexOf((empresa+"-S"))==-1)
           return false;
        else
            return true;
    }
    
    public boolean isEmpresaRedencion(String empresa){
        if(this.empresaRedencion.indexOf((empresa+"-S"))==-1)
           return false;
        else
            return true;
    }    

    public boolean isPermiteMotoLealtad() {
        return permiteMotoLealtad;
    }

    public void setPermiteMotoLealtad(boolean permiteMotoLealtad) {
        this.permiteMotoLealtad = permiteMotoLealtad;
    }

    public Vector getFoliosBoletos() {
        return foliosBoletos;
    }

    public String getNumeroTarjetaSocio() {
        return numeroTarjetaSocio;
    }

    public void setNumeroTarjetaSocio(String numeroTarjetaSocio) {
        this.numeroTarjetaSocio = numeroTarjetaSocio;
    }

    public String getTiposPagoLealtad() {
        return tiposPagoLealtad;
    }

    public void setTiposPagoLealtad(String tiposPagoLealtad) {
        this.tiposPagoLealtad = tiposPagoLealtad;
    }

    public long getTerminalLealtadIdO() {
        return terminalLealtadIdO;
    }

    public void setTerminalLealtadIdO(long terminalLealtadIdO) {
        this.terminalLealtadIdO = terminalLealtadIdO;
    }

    public long getTerminalLealtadIdD() {
        return terminalLealtadIdD;
    }

    public void setTerminalLealtadIdD(long terminalLealtadIdD) {
        this.terminalLealtadIdD = terminalLealtadIdD;
    }

    public String getTerminalLealtadNombreO() {
        return terminalLealtadNombreO;
    }

    public void setTerminalLealtadNombreO(String terminalLealtadNombreO) {
        this.terminalLealtadNombreO = terminalLealtadNombreO;
    }
    
  /*
    public Vector GetPar_TAE(String CajaId, String Terminal)
    {
     return tmsVtaFacade.GetParametrosTAE(CajaId, Terminal);
    }
    
*/   
    
    public Vector GetPar_TAE(long  EMPRESA_ID, long SERVICIO_ID, long TERMINAL_ID, long RUTA_ID, long CAJA_ID)
    {
        String resp = tmsVtaFacade.GetParametrosTAE(EMPRESA_ID,SERVICIO_ID,TERMINAL_ID,RUTA_ID,CAJA_ID);
        if(resp.equals(context))
            return null;
        else
        {
            Vector v = new Vector(); 
            resp=resp.replace("[","");
            resp=resp.replace("]","");
            String[] s = resp.split(",");
            for(int i=0;i<s.length;i++)
                v.add(s[i].equals("-1")?null:s[i]);
            return v;
        }
    }
    

    public boolean isUsarWSLelatad() {
        return usarWSLelatad;
    }

    public void setUsarWSLelatad(boolean usarWSLelatad) {
        this.usarWSLelatad = usarWSLelatad;
    }

    /**
     * @return the impVouComercio
     */
    public boolean isImpVouComercio() {
        return impVouComercio;
    }

    /**
     * @param impVouComercio the impVouComercio to set
     */
    public void setImpVouComercio(boolean impVouComercio) {
        this.impVouComercio = impVouComercio;
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

    /**
     * @return the enc
     */
    public rc4 getEnc() {
        return enc;
    }

    /**
     * @param enc the enc to set
     */
    public void setEnc(rc4 enc) {
        this.enc = enc;
    }

    /**
     * @return the rutasConPromocionVigente
     */
    public List<Promocion> getRutasConPromocionVigente() {
        return rutasConPromocionVigente;
    }

    /**
     * @param rutasConPromocionVigente the rutasConPromocionVigente to set
     */
    public void setRutasConPromocionVigente(List<Promocion> rutasConPromocionVigente) {
        this.rutasConPromocionVigente = rutasConPromocionVigente;
    }

    /**
     * @return the rutaConPromocion
     */
    public boolean isRutaConPromocion() {
        return rutaConPromocion;
    }

    /**
     * @param rutaConPromocion the rutaConPromocion to set
     */
    public void setRutaConPromocion(boolean rutaConPromocion) {
        this.rutaConPromocion = rutaConPromocion;
    }

    /**
     * @return the solicitaCP
     */
    public boolean isSolicitaCP() {
        return solicitaCP;
    }

    /**
     * @param solicitaCP the solicitaCP to set
     */
    public void setSolicitaCP(boolean solicitaCP) {
        this.solicitaCP = solicitaCP;
    }

    /**
     * @return the CP
     */
    public String getCP() {
        return CP;
    }

    /**
     * @param CP the CP to set
     */
    public void setCP(String CP) {
        this.CP = CP;
    }

    /**
     * @return the agenciasViaje
     */
    public Vector getAgenciasViaje() {
        return agenciasViaje;
    }

    /**
     * @param agenciasViaje the agenciasViaje to set
     */
    public void setAgenciasViaje(Vector agenciasViaje) {
        this.agenciasViaje = agenciasViaje;
    }

    /**
     * @return the clienteCreId
     */
    public BigDecimal getClienteCreId() {
        return clienteCreId;
    }

    /**
     * @param clienteCreId the clienteCreId to set
     */
    public void setClienteCreId(BigDecimal clienteCreId) {
        this.clienteCreId = clienteCreId;
    }

    /**
     * @return the clienteSEDENAId
     */
    public BigDecimal getClienteSEDENAId() {
        return clienteSEDENAId;
    }

    /**
     * @param clienteSEDENAId the clienteSEDENAId to set
     */
    public void setClienteSEDENAId(BigDecimal clienteSEDENAId) {
        this.clienteSEDENAId = clienteSEDENAId;
    }

    /**
     * @return the tipoTDC
     */
    public String getTipoTDC() {
        return tipoTDC;
    }

    /**
     * @param tipoTDC the tipoTDC to set
     */
    public void setTipoTDC(String tipoTDC) {
        this.tipoTDC = tipoTDC;
    }

    /**
     * @return the DatosInicales
     */
    public Vector getDatosInicales() {
        return DatosInicales;
    }

    /**
     * @param DatosInicales the DatosInicales to set
     */
    public void setDatosInicales(Vector DatosInicales) {
        this.DatosInicales = DatosInicales;
    }

    /**
     * @return the productosCarrito
     */
    public List<ProductoCarrito> getProductosCarrito() {
        return productosCarrito;
    }

    /**
     * @param productosCarrito the productosCarrito to set
     */
    public void setProductosCarrito(List<ProductoCarrito> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }

    /**
     * @return the ventaJuconi
     */
    public boolean isVentaJuconi() {
        return ventaJuconi;
    }

    /**
     * @param ventaJuconi the ventaJuconi to set
     */
    public void setVentaJuconi(boolean ventaJuconi) {
        this.ventaJuconi = ventaJuconi;
    }

    /**
     * @return the nombresServicios
     */
    public Vector getNombresServicios() {
        return nombresServicios;
    }

    /**
     * @param nombresServicios the nombresServicios to set
     */
    public void setNombresServicios(Vector nombresServicios) {
        this.nombresServicios = nombresServicios;
    }

    /**
     * @return the impresionNombreBoleto
     */
    public Vector getImpresionNombreBoleto() {
        return impresionNombreBoleto;
    }

    /**
     * @param impresionNombreBoleto the impresionNombreBoleto to set
     */
    public void setImpresionNombreBoleto(Vector impresionNombreBoleto) {
        this.impresionNombreBoleto = impresionNombreBoleto;
    }

    /**
     * @return the bolRedCer
     */
    public boolean isBolRedCer() {
        return bolRedCer;
    }

    /**
     * @param bolRedCer the bolRedCer to set
     */
    public void setBolRedCer(boolean bolRedCer) {
        this.bolRedCer = bolRedCer;
    }

    /**
     * @return the dblinkRedIda
     */
    public String getDblinkRedIda() {
        return dblinkRedIda;
    }

    /**
     * @param dblinkRedIda the dblinkRedIda to set
     */
    public void setDblinkRedIda(String dblinkRedIda) {
        this.dblinkRedIda = dblinkRedIda;
    }

    /**
     * @return the vtaBolBco
     */
    public boolean isVtaBolBco(String empresa)
    {
        if(vtaBolBco)
            return vtaBolBco;
        else
        {
            String[] xB = this.getImpresoraBoletos(empresa);
            if(xB==null) return false;
            //SalidaImpresion = xB[0];
            //marcaImpresora = xB[1];
            String FormatoBoleto = xB[2];
            //nombreImpresora = xB[3];
            if(FormatoBoleto.equals("BOLETO_ESTANDAR_BLANCO"))
                return  true;
            else
                return false;
        }
    }
    /**
     * @return the vtaBolBco
     */

    public boolean isVtaBolBcoActualizaUltimoFolioVendido(String empresa)
    {
        if(vtaBolBco)
            return vtaBolBco;
        else
        {
            String[] xB = this.getImpresoraBoletos(empresa);
            if(xB==null) return false;
            //SalidaImpresion = xB[0];
            //marcaImpresora = xB[1];
            String FormatoBoleto = xB[2];
            //nombreImpresora = xB[3];
            if(FormatoBoleto.equals("BOLETO_ESTANDAR_BLANCO"))
                return  true;
            else
                return false;
        }
    }

    /**
     * @param vtaBolBco the vtaBolBco to set
     */
    public void setVtaBolBco(boolean vtaBolBco) {
        this.vtaBolBco = vtaBolBco;
    }

    /**
     * @return the vEstado
     */
    public Vector getvEstados() {
        return vEstados;
    }

    /**
     * @param vEstado the vEstado to set
     */
    public void setvEstados(Vector vEstado) {
        this.vEstados = vEstado;
    }

    /**
     * @return the cobroCargo
     */
    public boolean isCobroCargo() {
        return cobroCargo;
    }

    /**
     * @param cobroCargo the cobroCargo to set
     */
    public void setCobroCargo(boolean cobroCargo) {
        this.cobroCargo = cobroCargo;
    }

    /**
     * @return the tipoPagoCargo
     */
    public String getTipoPagoCargo() {
        return tipoPagoCargo;
    }

    /**
     * @param tipoPagoCargo the tipoPagoCargo to set
     */
    public void setTipoPagoCargo(String tipoPagoCargo) {
        this.tipoPagoCargo = tipoPagoCargo;
    }

    /**
     * @return the aprobacionCargo
     */
    public String getAprobacionCargo() {
        return aprobacionCargo;
    }

    /**
     * @param aprobacionCargo the aprobacionCargo to set
     */
    public void setAprobacionCargo(String aprobacionCargo) {
        this.aprobacionCargo = aprobacionCargo;
    }

    /**
     * @return the origenCorridaRedIda
     */
    public String getOrigenCorridaRedIda() {
        return origenCorridaRedIda;
    }

    /**
     * @param origenCorridaRedIda the origenCorridaRedIda to set
     */
    public void setOrigenCorridaRedIda(String origenCorridaRedIda) {
        this.origenCorridaRedIda = origenCorridaRedIda;
    }

    /**
     * @return the origenCorridaRedReg
     */
    public String getOrigenCorridaRedReg() {
        return origenCorridaRedReg;
    }

    /**
     * @param origenCorridaRedReg the origenCorridaRedReg to set
     */
    public void setOrigenCorridaRedReg(String origenCorridaRedReg) {
        this.origenCorridaRedReg = origenCorridaRedReg;
    }

    /**
     * @return the origenRedIda
     */
    public String getOrigenRedIda() {
        return origenRedIda;
    }

    /**
     * @param origenRedIda the origenRedIda to set
     */
    public void setOrigenRedIda(String origenRedIda) {
        this.origenRedIda = origenRedIda;
    }

    /**
     * @return the origenRedReg
     */
    public String getOrigenRedReg() {
        return origenRedReg;
    }

    /**
     * @param origenRedReg the origenRedReg to set
     */
    public void setOrigenRedReg(String origenRedReg) {
        this.origenRedReg = origenRedReg;
    }


    public void imprimeTicketCargo(String empresa, String sCad, String folioArchivo)
    {
        String SalidaImpresion = this.getImpresoraVoucher(empresa);
        if(SalidaImpresion==null){
            System.out.println("No encontro impresora de voucher para imprimir el comprobante de cargo");
            DialogoAceptar.mostrarDialogo("¡No encontro impresora de voucher para imprimir el comprobante de Cargo!","Contacte al administrador del sistema.",Color.RED);
        }
        else
        {
            try{
                String UserHome = System.getProperty("user.home");
                if(SalidaImpresion.equals("ARCHIVO"))
                    SalidaImpresion = UserHome+"\\Comprobante_Cargo_"+folioArchivo+".TXT";

                //FileDescriptor fd = new FileDescriptor();
                FileOutputStream os = new FileOutputStream(SalidaImpresion); // LPT / C:\\ARCHIVO.TXT / COM \\CAPUSIS53\HPLaserJ
                PrintStream ps = new PrintStream(os);
                ps.print(sCad); // CADENA A IMPRIMIR
               // ps.print(sCad); // CADENA A IMPRIMIR(Copia)
                ps.flush();
                os.close();
            }catch(java.io.FileNotFoundException fsctex){
                String UserHome = System.getProperty("user.home");
                fsctex.printStackTrace();
                SalidaImpresion = UserHome+"\\\\Comprobante_Cargo_"+folioArchivo+".TXT";
                FileOutputStream os;
                    try {
                        os = new FileOutputStream(SalidaImpresion);
                        PrintStream ps = new PrintStream(os);
                        ps.print(sCad); // CADENA A IMPRIMIR
                        ps.flush();
                        try {
                            os.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } // LPT / C:\\ARCHIVO.TXT / COM
            }catch(Exception sctex){
                sctex.printStackTrace();
            }
        }
    }

    /**
     * @return the rutasConPromocionRedondoTTP
     */
    public Vector getRutasConPromocionRedondoTTP() {
        return rutasConPromocionRedondoTTP;
    }

    /**
     * @param rutasConPromocionRedondoTTP the rutasConPromocionRedondoTTP to set
     */
    public void setRutasConPromocionRedondoTTP(Vector rutasConPromocionRedondoTTP) {
        this.rutasConPromocionRedondoTTP = rutasConPromocionRedondoTTP;
    }

    public boolean isRutaPromocionRedondoTTP(String rutaId)
    {
        for(int i=0; i<this.getRutasConPromocionRedondoTTP().size(); i++)
        {
            Vector v =(Vector)this.getRutasConPromocionRedondoTTP().get(i);
            if(v.get(0).toString().equals(rutaId))
                return true;
        }
        return false;
    }

    /**
     * @return the voucherBlanco
     */
    public boolean isVoucherBlanco() {
        return voucherBlanco;
    }

    /**
     * @param voucherBlanco the voucherBlanco to set
     */
    public void setVoucherBlanco(boolean voucherBlanco) {
        this.voucherBlanco = voucherBlanco;
    }

    /**
     * @return the impresoraNombreVoucherBlanco
     */
    public String getImpresoraNombreVoucherBlanco() {
        return impresoraNombreVoucherBlanco;
    }

    /**
     * @param impresoraNombreVoucherBlanco the impresoraNombreVoucherBlanco to set
     */
    public void setImpresoraNombreVoucherBlanco(String impresoraNombreVoucherBlanco) {
        this.impresoraNombreVoucherBlanco = impresoraNombreVoucherBlanco;
    }

/*getGeneraFolioPreimpreso
    public boolean isCobroMultiple() {
        return cobroMultiple;
    }

    public void setCobroMultiple(boolean cobroMultiple) {
        this.cobroMultiple = cobroMultiple;
    }
*/    

  public static double roundNum(double num, int ndec)
    throws Exception
  {
    double valor = 0.0D;

    valor = num;

    valor *= ndec;
    valor = Math.round(valor);
    valor /= ndec;

    return valor;
  }

    private String getLPAD(String texto, int lenght)
    {
        String lpad =texto;
        for(int i=texto.length(); i<lenght;i++ )
            lpad = " " + lpad;
        return lpad;
    }

 private void imprimeTicketProductos(List<ProductoCarrito> productosImp)
 {
                         String SalidaImpresiont="ARCHIVO";
                         String puerto = "";
                         String nombreFormato = "";
                         String nombreImpresora = "";
                        PcInfo pc = new PcInfo();
                         System.out.println("Host Name: "+pc.getHostName());
                       Vector resps = getTmsVtaFacade().getImpresora(pc.getHostName());
                       if(resps.size()==0)
                           DialogoAceptar.mostrarDialogo("¡Configuracion Impresoras!", "¡No existen impresoras configuradas para Tickets en esta caja. \nFavor de contactar al Administrador del Sistema", Color.RED);
                       else
                       {
                           Vector v = (Vector)resps.get(0);
                           String nombreImpresorat= (v.get(0).toString());
                           String puertot= (v.get(1).toString());
                           String nombreFormatot=(v.get(2).toString());
                           System.out.println("NombreImpresora: "+nombreImpresorat);
                           System.out.println("puerto: "+puertot);
                           System.out.println("NombreFormato: "+nombreFormatot);

                           String empresa="";
                            String producto="";
                            long cantidad=0;
                            String folio="";
                            System.out.println("productosImp.get(0).getFolio(): "+productosImp.get(0).getFolio());
                            if(productosImp.get(0).getFolio() == null || productosImp.get(0).getFolio().equals("") || productosImp.get(0).getFolio().equals("0"))
                                folio=getTmsVtaFacade().getTicketCompra(Long.valueOf(this.getUserCon().getTerminalPrefijoId()));//PrefijoTerminal
                            else
                                folio=productosImp.get(0).getFolio();//PrefijoTerminal
                            System.out.println("folio: "+folio);
                            String clave_producto="";
                            float precio_unitario=0;
                            float subtotal=0;
                            float iva=0;
                            float total=0;
                            float retencion=0;
                            float montoTotalTicket =0;
                            String emp = productosImp.get(0).getEmpresaDescipcion().toUpperCase();
                            if(emp.length()<50)
                                    emp = "    "+emp;
                            String sCad="\n";
                            //sCad = sCad+"      AUTOBUSES MEXICO PUEBLA";
                            System.out.println("Empresa Nombre: "+emp);
                            if (emp.length()>23)
                                sCad = sCad+"      "+emp.substring(0,23).trim();
                            sCad = sCad+"\n";
                            //sCad = sCad+"     ESTRELLA ROJA S.A. DE C.V.";
                            if (emp.length()>23)
                                sCad = sCad+"      "+emp.substring(24,emp.length()).trim();
                            sCad = sCad+"\n";
                            sCad = sCad+"        Comprobante de Pago ";
                            sCad = sCad+"\n";
                            SimpleDateFormat formatf = new SimpleDateFormat ("dd/MM/yyyy");
                            SimpleDateFormat formath = new SimpleDateFormat ("H:mm");
                            sCad = sCad+"  FECHA: "+formatf.format(new Date()) +" HORA: "+ formath.format(new Date());
                            sCad = sCad+"\n";
                            sCad = sCad+"  No.Ticket: "+folio;
                            sCad = sCad+"\n\n";
                            sCad = sCad+"            Productos      "; //750
                            sCad = sCad+"\n";
                            sCad = sCad+"_____________________________________";
                            sCad = sCad+"\n";
                            sCad = sCad+"Cant.\tNombre\t\tPrecio\tImporte";
                            sCad = sCad+"\n";
                            sCad = sCad+"_____________________________________";
                            sCad = sCad+"\n";

                            for(ProductoCarrito product:productosImp)
                            {
                                    System.out.println("*****************************************");
                                    System.out.println(" ProductoId: "+product.getProductoId());
                                    //System.out.println(" Clave: "+product.getProductoClave());
                                    System.out.println(" Nombre: "+product.getProducto());
                                    System.out.println(" TarifaMostrador: "+product.getPrecio_unitario());
                                    System.out.println(" EmpresaNombre: "+product.getEmpresa());
                                    System.out.println(" Impresion: "+product.isImprimeTicket());
                                    empresa=product.getEmpresa();
                                    producto=product.getProducto();
                                    cantidad=product.getCantidad();
                                    //clave_producto=product.getProductoClave();
                                    precio_unitario=product.getPrecio_unitario();
                                    subtotal=product.getSubtotal();
                                    iva=0;
                                    total=product.getTotal();
                                    retencion=0;

                                        //sCad = sCad+" Catidad   Nombre  Precio  Importe";
                                        montoTotalTicket=montoTotalTicket+total;
                                        sCad = sCad+" "+cantidad+(((""+cantidad).length()>2)?" ":"  ")+((producto.length()<=11)?producto:producto.substring(0,11))+(((""+producto).length()>7)?"\t":"\t\t")+precio_unitario+(((""+precio_unitario).length()>3)?"\t":"\t\t")+total;
                                        sCad = sCad+"\n";
                            }


                               sCad = sCad+"\n";
                                sCad = sCad+"\t\t\t\t\tTotal: $"+montoTotalTicket;
                                sCad = sCad+"\n\n";
                                String letras= "( "+new cantidad_a_letras().toLetras((long)((new Double(montoTotalTicket)).longValue()))  + "Pesos 00/M.N. )";
                                sCad = sCad+letras;
                                sCad = sCad+"\n\n";
                                sCad = sCad+"\n";
                                sCad = sCad+"Cajero: "+((this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()).length()<25?(this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()):(this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()).substring(0,25));//+ClaveCajero+"-"+(NombreCajero.length()<21?NombreCajero:NombreCajero.substring(0,20));
                                sCad = sCad+"\n";
                                sCad = sCad+" #Caja: "+this.getUserCon().getCaja();
                                sCad = sCad+"\n\n   Este comprobante de pago";
                                sCad = sCad+"\n"+"      No es Valido";
                                sCad = sCad+"\n"+" Como cupon de viaje, como";
                                sCad = sCad+"\n"+"boleto de transporte ni como ";
                                sCad = sCad+"\n"+"     comprobante Fiscal";
                                sCad = sCad+"\n\n\n\n\n\n\n\n\n\n";
                                sCad = sCad+"         .";
                                String sCadTermico = sCad;
                             if(nombreFormatot.equals("TICKET_TERMICO") ) //|| nombreFormatot.equals("TICKET"))
                             {
                                 sCadTermico = "";
                                 float impTotalTick = 0;
                                 float subTotalTick = 0;
                                 float ivaTotalTick = 0;
                                 System.out.println(" Entra a generar Ticket Termico...");
                                 for(int j=0; j<productosImp.size();j++)
                                 {
                                     //float sub = productosImp.get(j).getSubtotal();
                                     float sub = productosImp.get(j).getPrecio_unitario();
                                    try {
                                         productosImp.get(j).setSubtotal(Float.valueOf("" + roundNum(Double.valueOf(sub / 1.16), 2)));
                                         productosImp.get(j).setIva(sub - (Float.valueOf("" + roundNum(Double.valueOf(sub / 1.16), 2))));
                                    } catch (Exception ex) {
                                       ex.printStackTrace();
                                    }

                                     ProductoCarrito prod= productosImp.get(j);
                                     impTotalTick  = impTotalTick + prod.getTotal();
                                     subTotalTick  = subTotalTick + (prod.getSubtotal() * prod.getCantidad() );
                                     ivaTotalTick  = ivaTotalTick + (prod.getIva() * prod.getCantidad() );
                                 }
                                     sCadTermico = "^XA\n";
                                    sCadTermico = sCadTermico + "^MMT\n";
                                    sCadTermico = sCadTermico + "^PW609\n";
                                    sCadTermico = sCadTermico + "^LL0972\n";
                                    sCadTermico = sCadTermico + "^LS0\n";
                                    if(productosImp.get(0).getEmpresa().equals("Terminal Las Torres Puebla 1"))
                                        sCadTermico = sCadTermico + "^FT35,110^XGE:cmpEbus2.GRF,1,1'+'^FS'\n";
                                    else
                                        sCadTermico = sCadTermico + "^FT35,110^XGE:cmpER.GRF,1,1'+'^FS'\n";
                                    sCadTermico = sCadTermico + "^FO410,30\n";
                                    sCadTermico = sCadTermico + "^GS^FDA^FS\n";
                                    sCadTermico = sCadTermico + "^FT512,191^A0N,28,28^FH\\^FD"+formath.format(new Date()) +"^FS\n";
                                    sCadTermico = sCadTermico + "^FT24,738^A0N,28,28^FH\\^FD#Caja:^FS\n";
                                    sCadTermico = sCadTermico + "^FT24,230^A0N,28,28^FH\\^FDNo. Ticket:^FS\n";
                                    sCadTermico = sCadTermico + "^FT110,742^A0N,28,28^FH\\^FD"+this.getUserCon().getCaja()+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT29,628^A0N,28,28^FH\\^FD"+"( "+new cantidad_a_letras().toLetras((long)((new Double(impTotalTick)).longValue()))  + "Pesos 00/M.N. )"+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT170,232^A0N,28,28^FH\\^FD"+folio+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT434,191^A0N,28,28^FH\\^FDHora:^FS\n";
                                    sCadTermico = sCadTermico + "^FT113,697^A0N,28,28^FH\\^FD"+((this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()).length()<25?(this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()):(this.getUserCon().getUsuarioNum()+"-"+this.getUserCon().getUsuarioNom()).substring(0,25))+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT171,191^A0N,28,28^FH\\^FD"+formatf.format(new Date()) +"^FS\n";
                                    sCadTermico = sCadTermico + "^FT126,329^A0N,23,24^FH\\^FDNombre^FS\n";
                                    sCadTermico = sCadTermico + "^FT480,330^A0N,23,24^FH\\^FDImporte^FS\n";
                                    sCadTermico = sCadTermico + "^FT351,573^A0N,28,28^FH\\^FDTotal:^FS\n";
                                    sCadTermico = sCadTermico + "^FT280,330^A0N,23,24^FH\\^FDPrecio Unitario^FS\n";
                                    sCadTermico = sCadTermico + "^FT440,570^A0N,28,28^FH\\^FD"+getLPAD("$"+impTotalTick,10)+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT24,329^A0N,23,24^FH\\^FDCant.^FS\n";
                                    sCadTermico = sCadTermico + "^FT452,497^A0N,23,24^FH\\^FD"+getLPAD("$"+subTotalTick,10)+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT452,529^A0N,23,24^FH\\^FD"+getLPAD("$"+ivaTotalTick,10)+"^FS\n";
                                    if(productosImp.size()>=3)
                                        sCadTermico = sCadTermico + "^FT468,450^A0N,23,24^FH\\^FD"+getLPAD("$"+((productosImp.get(2).getSubtotal())*productosImp.get(2).getCantidad()), 8)+"^FS\n";
                                    if(productosImp.size()>=2)
                                    sCadTermico = sCadTermico + "^FT468,413^A0N,23,24^FH\\^FD"+getLPAD("$"+((productosImp.get(1).getSubtotal())*productosImp.get(1).getCantidad()), 8)+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT333,499^A0N,23,24^FH\\^FDSubtotal:^FS\n";
                                    if(productosImp.size()>=3)
                                        sCadTermico = sCadTermico + "^FT121,455^A0N,23,24^FH\\^FD"+productosImp.get(2).getProducto()+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT358,534^A0N,23,24^FH\\^FDI.V.A.:^FS\n";
                                    if(productosImp.size()>=3)
                                        sCadTermico = sCadTermico + "^FT328,453^A0N,23,24^FH\\^FD"+getLPAD("$"+productosImp.get(2).getSubtotal(), 8)+"^FS\n";
                                    if(productosImp.size()>=1)
                                        sCadTermico = sCadTermico + "^FT467,376^A0N,23,24^FH\\^FD"+getLPAD("$"+((productosImp.get(0).getSubtotal())*productosImp.get(0).getCantidad()), 8)+"^FS\n";
                                    if(productosImp.size()>=2)
                                    sCadTermico = sCadTermico + "^FT121,419^A0N,23,24^FH\\^FD"+productosImp.get(1).getProducto()+"^FS\n";
                                    if(productosImp.size()>=3)
                                        sCadTermico = sCadTermico + "^FT35,455^A0N,23,24^FH\\^FD"+getLPAD(""+productosImp.get(2).getCantidad(), 2)+"^FS\n";
                                    if(productosImp.size()>=2)
                                        sCadTermico = sCadTermico + "^FT328,417^A0N,23,24^FH\\^FD"+getLPAD("$"+productosImp.get(1).getSubtotal(), 8)+"^FS\n";
                                    if(productosImp.size()>=1)
                                        sCadTermico = sCadTermico + "^FT328,380^A0N,23,24^FH\\^FD"+getLPAD("$"+productosImp.get(0).getSubtotal(), 8)+"^FS\n";
                                    if(productosImp.size()>=1)
                                        sCadTermico = sCadTermico + "^FT121,382^A0N,23,24^FH\\^FD"+productosImp.get(0).getProducto()+"^FS\n";
                                    if(productosImp.size()>=2)
                                        sCadTermico = sCadTermico + "^FT35,418^A0N,23,24^FH\\^FD"+getLPAD(""+productosImp.get(1).getCantidad(), 2)+"^FS\n";
                                    if(productosImp.size()>=1)
                                        sCadTermico = sCadTermico + "^FT35,381^A0N,23,24^FH\\^FD"+getLPAD(""+productosImp.get(0).getCantidad(), 2)+"^FS\n";
                                    sCadTermico = sCadTermico + "^FT17,693^A0N,28,28^FH\\^FDCajero:^FS\n";
                                    sCadTermico = sCadTermico + "^FT76,190^A0N,28,28^FH\\^FDFecha:^FS\n";
                                    sCadTermico = sCadTermico + "^FT6,339^A0N,25,24^FH\\^FD__________________________________________________^FS\n";
                                    sCadTermico = sCadTermico + "^FT251,286^A0N,34,33^FH\\^FDProductos^FS\n";
                                    sCadTermico = sCadTermico + "^FT8,293^A0N,25,24^FH\\^FD__________________________________________________^FS\n";
                                    sCadTermico = sCadTermico + "^FT25,827^A0N,23,24^FH\\^FDcomo boleto de transporte ni como comprobante fiscal.^FS\n";
                                    sCadTermico = sCadTermico + "^FT2,797^A0N,23,24^FH\\^FDEste comprobante de pago no es valido como cupon de viaje,^FS\n";
                                    if(productosImp.get(0).getEmpresa().equals("Terminal Las Torres Puebla 1"))
                                        sCadTermico = sCadTermico + "^FT40,965^XGE:Ebusred.GRF,1,1'+'^FS'\n";
                                    else
                                        sCadTermico = sCadTermico + "^FT40,965^XGE:ER redes.GRF,1,1'+'^FS'\n";
                                    sCadTermico = sCadTermico + "^PQ1,0,1,Y^XZ\n";
                                    sCadTermico = sCadTermico + "C\n";
                                    sCadTermico= sCadTermico.replace("Ñ","N");

                              }
                            sCad = sCadTermico;
                           if(puertot.equals("LPT1") ||puertot.equals("LPT2") ||puertot.equals("LPT3") || puertot.equals("LPT4") ||  puertot.equals("COM1") || puertot.equals("COM2")  || puertot.equals("ARCHIVO") )
                                SalidaImpresiont = puertot;
                           if(puertot.equals("RED") )
                               SalidaImpresiont = nombreImpresorat;
                            if(puertot.equals("USB"))
                                SalidaImpresiont = "\\\\127.0.0.1\\"+ nombreImpresorat;

                            System.out.println(" SalidaImpresiont: "+SalidaImpresiont);
                            try{
                                String UserHome = System.getProperty("user.home");
                                if(SalidaImpresiont.equals("ARCHIVO"))
                                    SalidaImpresiont = UserHome+"\\TICKET_COMPRA_"+folio+".TXT";

                                //FileDescriptor fd = new FileDescriptor();
                                FileOutputStream os = new FileOutputStream(SalidaImpresiont); // LPT / C:\\ARCHIVO.TXT / COM
                                PrintStream ps = new PrintStream(os);
                                ps.print(sCad); // CADENA A IMPRIMIR
                               // ps.print(sCad); // CADENA A IMPRIMIR(Copia)
                                ps.flush();
                                os.close();
                            }catch(java.io.FileNotFoundException fsctex){
                                fsctex.printStackTrace();
                                String UserHome = System.getProperty("user.home");
                                SalidaImpresiont = UserHome+"\\TICKET_COMPRA_"+folio+".TXT";
                                FileOutputStream os;
                                    try {
                                        os = new FileOutputStream(SalidaImpresiont);
                                        PrintStream ps = new PrintStream(os);
                                        ps.print(sCad); // CADENA A IMPRIMIR
                                        ps.flush();
                                        try {
                                            os.close();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                    } catch (FileNotFoundException ex) {
                                    ex.printStackTrace();
                                } // LPT / C:\\ARCHIVO.TXT / COM
                            }catch(Exception sctex){
                                sctex.printStackTrace();
                            }

                           }
 }
}