package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.CajaEfectivoFacade;
import com.alidasoftware.pos.facade.CajaFacade;
import com.alidasoftware.pos.facade.CorteCajaFacade;
import com.alidasoftware.pos.facade.DetalleCorteCajaFacade;
import com.alidasoftware.pos.facade.DetalleCorteParcialFacade;
import com.alidasoftware.pos.facade.FormaPagoFacade;
import com.alidasoftware.pos.facade.UsuarioFacade;
import com.alidasoftware.pos.model.Caja;
import com.alidasoftware.pos.model.Cajaefectivo;
import com.alidasoftware.pos.model.Cortecaja;
import com.alidasoftware.pos.model.Corteparcial;
import com.alidasoftware.pos.model.Detallecortecaja;
import com.alidasoftware.pos.model.Formapago;
import com.alidasoftware.pos.model.Usuario;
import com.alidasoftware.pos.util.Utils;


public class CorteCajaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -7511752856456833241L;
	
	private CajaFacade cajaFacade;
	private CajaEfectivoFacade cajaEfectivoFacade;
	private Caja caja;
	private String selectedCaja;
	private List<SelectItem> listaCaja;
	private float efectivoInicial;
	private String message;
	private Integer idTienda;
	private List<Cortecaja> listaCorteCaja;
	private UsuarioFacade usuarioFacade;
	private Usuario user;
	private Cajaefectivo cajaEfectivo;
	private DetalleCorteParcialFacade detalleCorteParcialFacade= new DetalleCorteParcialFacade();
	private DetalleCorteCajaFacade detalleCorteCajaFacade= new DetalleCorteCajaFacade();
	private Detallecortecaja detalleCorteCaja;
	
	
	private int idcortecaja;
	private float cantidadFisica;
	private float cantidadsistema;
	private String comentarios;
	private Date fecha;
	private Time horafin;
	private Time horainicio;
	private Usuario usuario1;
	private Usuario usuario2;
	
	private List<Corteparcial>corteparcials;
	private List<Detallecortecaja>detallecortecajas;
	private CorteCajaFacade corteCajaFacade= new CorteCajaFacade();
	private Cortecaja selected;
	private Date searchFecha;
	private String stringFecha;
	
	private float dineroEfectivo;
	private float dineroTarjeta;
	private float dineroCredito;
	private float dineroVales;
	private float total1;
	private float dineroInicial;
	private float dineroVentas;
	private float dineroPagos;
	private float dineroRetiro;
	private float total2;
	
	
	//private String stringHoraFin;
	
	public CorteCajaBean() {
		//System.out.println("Constructor");
		efectivoInicial = 0.0f;
		caja = null;
		selectedCaja = null;
		listaCaja =  new ArrayList<SelectItem>();
		prepareCreate();
		listar();
		//loadCajaSelectItems();
	}
	
	
	public Cortecaja prepareCreate()
	{
		selected= new Cortecaja();
		return selected;
	}
	
	public void prepareCreateNew()
	{
	
		this.dineroEfectivo=0;
		this.dineroCredito=0;
		this.dineroPagos=0;
		this.dineroRetiro=0;
		this.dineroTarjeta=0;
		this.dineroVales=0;
		this.dineroVentas=0;
		this.dineroInicial=0;
		this.total2=0;
		
		this.selected=getCorteParcialBean().getCortecaja();
		System.out.println("***********ID*************** "+ selected.getIdcortecaja());
		this.selected.setCaja(getCaja());
		this.selected.setUsuario1(getCorteParcialBean().getUsuario1());
		this.selected.setUsuario2(getCorteParcialBean().getUsuario2());
		this.selected.setFecha(getCorteParcialBean().getFecha());
		
		this.selected.setHorafin(getCorteParcialBean().getHora());
		

		System.out.println(this.selected.getCorteparcials().size());
		for(int i=0; i<this.selected.getCorteparcials().size(); i++)
		{
			
			System.out.println("ID CORTE PARCIAL "+this.selected.getCorteparcials().get(i).getIdcorteparcial());
			System.out.println("ID CORTE CAJA "+this.selected.getCorteparcials().get(i).getCortecaja().getIdcortecaja());
			System.out.println("FECHA "+this.selected.getCorteparcials().get(i).getFecha());
			System.out.println("HORA "+this.selected.getCorteparcials().get(i).getHora());
			System.out.println("CANTIDAD RETIRADA "+this.selected.getCorteparcials().get(i).getCantidadretirada());
			System.out.println("TOTAL CAJA "+this.selected.getCorteparcials().get(i).getTotalcaja());
			System.out.println("VENTAS "+this.selected.getCorteparcials().get(i).getVentas());
			System.out.println("PAGOS "+this.selected.getCorteparcials().get(i).getPagos());
			
			this.dineroRetiro+=this.selected.getCorteparcials().get(i).getCantidadretirada();
			this.dineroPagos+=this.selected.getCorteparcials().get(i).getPagos();
			this.dineroVentas+=this.selected.getCorteparcials().get(i).getVentas();
			this.total2+=this.selected.getCorteparcials().get(i).getTotalcaja();
			//this.dineroInicial+=this.selected.getCorteparcials().get(i).getTotalcaja();
			
			//System.out.println("SIZE OF DETALLES: "+this.selected.getCorteparcials().get(i).getDetallecorteparcial());
			for(int j=0; j< this.selected.getCorteparcials().get(i).getDetallecorteparcial().size(); j++)
			{
				/*System.out.println("ID FORMA PAGO "+this.selected.getCorteparcials().get(i).getDetallecorteparcial().get(j).getFormapago().getIdformapago());
				System.out.println("NOMBRE FORMA PAGO "+this.selected.getCorteparcials().get(i).getDetallecorteparcial().get(j).getFormapago().getNombre());
				System.out.println("PAGOS "+this.selected.getCorteparcials().get(i).getDetallecorteparcial().get(j).getCantidad());*/
				
				switch(this.selected.getCorteparcials().get(i).getDetallecorteparcial().get(j).getFormapago().getIdformapago())
				{
				//Efectivo
				case 1:
				{
					this.dineroEfectivo+=this.selected.getCorteparcials().get(i).getDetallecorteparcial().get(j).getCantidad();
					System.out.println("NOMBRE FORMA PAGO "+this.selected.getCorteparcials().get(i).getDetallecorteparcial().get(j).getFormapago().getNombre());
					break;
				}
				//Credito
				case 2:
				{
					this.dineroCredito+=this.selected.getCorteparcials().get(i).getDetallecorteparcial().get(j).getCantidad();
					break;
				}
				//Tarjeta
				case 3:
				{
					this.dineroTarjeta+=this.selected.getCorteparcials().get(i).getDetallecorteparcial().get(j).getCantidad();
					break;
				}
				//Vale
				case 4:
				{
					this.dineroVales+=this.selected.getCorteparcials().get(i).getDetallecorteparcial().get(j).getCantidad();
					break;
				}
			}
			
		  }
	  }
		this.dineroInicial=(this.total2-this.dineroPagos-this.dineroVentas)+this.dineroRetiro;
		this.total1=this.dineroEfectivo+this.dineroCredito+this.dineroVales+this.dineroTarjeta;
		//this.total2=this.dineroPagos+this.dineroVentas+this.dineroInicial-this.dineroRetiro;
				
		

		System.out.println("*****CANTIDAD FISICA********: "	+this.cantidadFisica);
		System.out.println("*****COMENTARIOS************: "	+this.comentarios);
		
		
		this.selected.setCantidadsistema(this.total2);
		this.selected.setTotalcaja(this.total2);
		this.selected.setPagos(this.dineroPagos);
		this.selected.setVentas(this.dineroVentas);
		
		System.out.println("*****INICIAL TOTAL******: "	+this.dineroInicial);
		System.out.println("*****PAGOS TOTALES******: "	+this.dineroPagos);
		System.out.println("*****VENTAS TOTALES*****: "	+this.dineroVentas);
		System.out.println("*****RETIROS TOTALES****: "	+this.dineroRetiro);
		System.out.println("*****EFECTIVO TOTAL*****: "	+this.dineroEfectivo);
		System.out.println("*****TARJETA TOTAL******: "	+this.dineroTarjeta);
		System.out.println("*****CREDITO TOTAL******: "	+this.dineroCredito);
		System.out.println("*****VALES TOTAL********: "	+this.dineroVales);
		
		

		System.out.println("*****CANTIDAD FISICA********: "	+this.cantidadFisica);
		System.out.println("*****COMENTARIOS************: "	+this.comentarios);
		
		System.out.println("*****CANTIDAD FISICA********: "	+this.selected.getCantidadfisica());
		System.out.println("*****COMENTARIOS************: "	+this.selected.getComentarios());
		
		
		//actualizarCorteCaja();
  }
	
	
	public String getStringFecha()
	{
		Date fecha= new Date();
		stringFecha=null;
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		stringFecha= format.format(fecha);
		this.fecha=fecha;
		return stringFecha;
	}
	

	
	public void actualizarCorteCaja()
	{
		this.selected=getCorteParcialBean().getCortecaja();
		
		this.selected.setCantidadfisica(cantidadFisica);
		
		this.selected.setComentarios(comentarios);
		
		System.out.println("*****CANTIDAD FISICA********: "	+cantidadFisica);
		System.out.println("*****COMENTARIOS************: "	+comentarios);
		
		System.out.println("*****CANTIDAD FISICA********: "	+this.cantidadFisica);
		System.out.println("*****COMENTARIOS************: "	+this.comentarios);
		
		System.out.println("*****CANTIDAD FISICA********: "	+this.selected.getCantidadfisica());
		System.out.println("*****COMENTARIOS************: "	+this.selected.getComentarios());
		
		
		try {
		corteCajaFacade.updateCortecaja(selected);
		//Crear detalle corte de caja
		creaDetalleCorteCaja();
	} catch (AlidaPosException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		try {
			cajaEfectivoFacade.deleteCajaEfectivo(cajaEfectivo);
			setCaja(null);
			
		} catch (AlidaPosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeDialog("VentanaCCCreado");
    	displayInfoMessageToUser("El registro se ha creado, Se ha cerrado la caja");
       	listar();
    	prepareCreate();
 }

	
	public void creaDetalleCorteCaja()
	{
		try
		{
			this.detalleCorteCaja= new Detallecortecaja();
			this.detalleCorteCaja.setCortecaja(selected);
			int i=0;
			FormaPagoFacade formaPagoFacade= new FormaPagoFacade();
			for(i=0; i<formaPagoFacade.listAllOrderByIdAsc().size(); i++)
			{
				Formapago forma= formaPagoFacade.listAllOrderByIdAsc().get(i);
				System.out.println("FORMA ID: "+forma.getIdformapago());
				
				this.detalleCorteCaja.setFormapago(forma);
				
				switch(this.detalleCorteCaja.getFormapago().getIdformapago())
				{
				case 1:
				{
					this.detalleCorteCaja.setCantidad(this.dineroEfectivo);
					break;
				}
				case 2:
				{
					this.detalleCorteCaja.setCantidad(this.dineroCredito);
					break;
				}
				case 3:
				{
					this.detalleCorteCaja.setCantidad(this.dineroTarjeta);
					break;
				}
				case 4:
				{
					this.detalleCorteCaja.setCantidad(this.dineroVales);
					break;
				}
			  }
				detalleCorteCajaFacade.createDetallecortecaja(detalleCorteCaja);
			}
		}
		catch(AlidaPosException ae)
		{
			ae.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		listar();
    	prepareCreate();
	}
	
	
	private CorteParcialBean getCorteParcialBean()
	{
		return (CorteParcialBean) Utils.getManagedBean("corteParcialBean");
	}
	
	
	public void onRowDblClckSelect(SelectEvent event) {  
		System.out.println("entro a esto");
		if (event != null && event.getObject() != null) {
			selected = (Cortecaja) event.getObject();
			
			System.out.println("size: "+ selected.getDetallecortecajas().size());
			System.out.println("id: "+ selected.getDetallecortecajas().get(0).getIddetallecortecaja());
			System.out.println("idCorte caja: "+ selected.getDetallecortecajas().get(0).getCortecaja().getIdcortecaja());
			System.out.println("forma pago: "+ selected.getDetallecortecajas().get(0).getFormapago().getNombre());
			System.out.println("cantidad: "+ selected.getDetallecortecajas().get(0).getCantidad());
			
			System.out.println("************************DETALLES CORTE CAJA************************************");
			for(int i=0; i<selected.getDetallecortecajas().size(); i++)
			{
				//System.out.println("ID FORMA DE PAGO: "+selected.getDetallecorteparcial().get(i).getFormapago().getIdformapago());
				switch(selected.getDetallecortecajas().get(i).getFormapago().getIdformapago())
				{
					case 1:
					{
						System.out.println("FORMA DE PAGO: "+selected.getDetallecortecajas().get(i).getFormapago().getNombre());
						System.out.println("ID FORMA DE PAGO: "+selected.getDetallecortecajas().get(i).getFormapago().getIdformapago());
						System.out.println("cantidad "+ selected.getDetallecortecajas().get(i).getCantidad());
						dineroEfectivo=selected.getDetallecortecajas().get(i).getCantidad();
						break;
					}
					case 2:
					{
						System.out.println("FORMA DE PAGO: "+selected.getDetallecortecajas().get(i).getFormapago().getNombre());
						System.out.println("ID FORMA DE PAGO: "+selected.getDetallecortecajas().get(i).getFormapago().getIdformapago());
						System.out.println("cantidad "+ selected.getDetallecortecajas().get(i).getCantidad());
						dineroCredito=selected.getDetallecortecajas().get(i).getCantidad();
						break;
					}
					case 3:
					{
						System.out.println("FORMA DE PAGO: "+selected.getDetallecortecajas().get(i).getFormapago().getNombre());
						System.out.println("ID FORMA DE PAGO: "+selected.getDetallecortecajas().get(i).getFormapago().getIdformapago());
						System.out.println("cantidad "+ selected.getDetallecortecajas().get(i).getCantidad());
						dineroTarjeta=selected.getDetallecortecajas().get(i).getCantidad();
						break;
					}
					case 4:
					{
						System.out.println("FORMA DE PAGO: "+selected.getDetallecortecajas().get(i).getFormapago().getNombre());
						System.out.println("ID FORMA DE PAGO: "+selected.getDetallecortecajas().get(i).getFormapago().getIdformapago());
						System.out.println("cantidad "+ selected.getDetallecortecajas().get(i).getCantidad());
						dineroVales=selected.getDetallecortecajas().get(i).getCantidad();
						break;
					}
				}
			}
			
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("CierreCajaViewDlg");
	}
	
	public void listar()
	{

		//System.out.println("Mensaje listar");
		try {
			listaCorteCaja=corteCajaFacade.listAll();
		} catch (AlidaPosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Cortecaja> getListaCorteCaja() {
		//System.out.println("obteniendo lista"+ listaCorteCaja);
		return listaCorteCaja;
	}

	public void setListaCorteCaja(List<Cortecaja> listaCorteCaja) {
		this.listaCorteCaja = listaCorteCaja;
	}
	
	
	public CorteCajaFacade getCorteCajaFacade() {
		return corteCajaFacade;
	}

	public void setCorteCajaFacade(CorteCajaFacade corteCajaFacade) {
		this.corteCajaFacade = corteCajaFacade;
	}
	
	public int getIdcortecaja() {
		return idcortecaja;
	}

	public void setIdcortecaja(int idcortecaja) {
		this.idcortecaja = idcortecaja;
	}

	
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHorafin() {
		Date horafinal= new Date();
		String stringHoraFin= horafinal.toString(); 
		SimpleDateFormat format= new SimpleDateFormat("HH:mm:ss");
		try
		{
			horafinal= format.parse(stringHoraFin);
		}
		catch(Exception e)
		{}
		
		horafin=(Time) horafinal;
		return horafin;
	}

	public void setHorafin(Time horafin) {
		this.horafin = horafin;
	}

	public Time getHorainicio() {
		return horainicio;
	}

	public void setHorainicio(Time horainicio) {
		this.horainicio = horainicio;
	}

	public Usuario getUsuario1() {
		return usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuario getUsuario2() {
		return usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public List<Corteparcial> getCorteparcials() {
		return corteparcials;
	}

	public void setCorteparcials(List<Corteparcial> corteparcials) {
		this.corteparcials = corteparcials;
	}

	public List<Detallecortecaja> getDetallecortecajas() {
		return detallecortecajas;
	}

	public void setDetallecortecajas(List<Detallecortecaja> detallecortecajas) {
		this.detallecortecajas = detallecortecajas;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}


	public UsuarioFacade getUsuarioFacade() {
		if (usuarioFacade == null) {
			usuarioFacade = new UsuarioFacade();
		}
		return usuarioFacade;
	}
	
	private LoginBean getLoginBean() {
		return (LoginBean) Utils.getManagedBean("loginBean");
	}
	
	private VentaBean getVentaBean() {
		return (VentaBean) Utils.getManagedBean("ventaBean");
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public float getEfectivoInicial() {
		return efectivoInicial;
	}

	public void setEfectivoInicial(float efectivoInicial) {
		this.efectivoInicial = efectivoInicial;
	}

	public String getSelectedCaja() {
		return selectedCaja;
	}

	public void setSelectedCaja(String selectedCaja) {
		this.selectedCaja = selectedCaja;
	}	

	public List<SelectItem> getListaCaja() {
		return listaCaja;
	}

	public void setListaCaja(List<SelectItem> listaCaja) {
		this.listaCaja = listaCaja;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}
	
	private CajaFacade getCajaFacade() {
		if (cajaFacade == null) {
			cajaFacade = new CajaFacade();
		}
		return cajaFacade;
	}
	
	private CajaEfectivoFacade getCajaEfectivoFacade() {
		if (cajaEfectivoFacade == null) {
			cajaEfectivoFacade = new CajaEfectivoFacade();
		}
		return cajaEfectivoFacade;
	}
	
	
	public void showListDialogListener() {
		
	}
	
	public String showListDialogAction() {
		RequestContext.getCurrentInstance().execute("PF('blockPage').show().show();");
		showListDialogListener();
		RequestContext.getCurrentInstance().execute("PF('blockPage').show().hide();");
		return "/caja/List.xhtml?faces-redirect=true";
	}

	//********************************************************************
	// Metodos para controlar que caja es la que utilizara en la sesion.
	//********************************************************************
	
	public void cajaListener(ActionEvent event) {
		initializeVars();
	}
	
	public void cleanUp() {
		initializeVars();
	}
	
	private void initializeVars() {
		message = "";
		efectivoInicial = 0.0f;
		caja = null;
		selectedCaja = "";
	}
	
	public void cajaChangeListener(ValueChangeEvent event) {
		if (event != null) {
			try {			
				//System.out.println("caja valueChange : " + event.getNewValue().getClass().toString());
				selectedCaja = event.getNewValue().toString();
				message = "";
			} catch (Exception ex) {
				System.out.println("ErrorValueChangeCaja : " + ex.getMessage());
				keepDialogOpen();
	            displayErrorMessageToUser("Error: " + ex.getMessage());
			}
		}
	}
	
	private void loadCajaSelectItems(Integer idTienda) {
		this.idTienda = idTienda;
		try {
			listaCaja = new ArrayList<SelectItem>();
			List<Caja> list = getCajaFacade().listByTienda(idTienda);
			if (list != null && list.size() > 0) {
				String label = "";
				for (int i = 0; i < list.size(); i++) {
					label = list.get(i).getClave();
					listaCaja.add(new SelectItem( label ));
				}
				selectedCaja = list.get(0).getClave();
			}			
		} catch (Exception ex) {
			System.out.println("ErrorCaja : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: No exiten cajas.. verifique cat?logo");
		}
	}
	
	private void loadCajaSelectItems() {
		try {
			List<Cajaefectivo> listaCajaEfectivo = getCajaEfectivoFacade().listAll();			
			listaCaja = new ArrayList<SelectItem>();
			List<Caja> list = getCajaFacade().listAll();
			if (list != null && list.size() > 0) {
				String label = "";
				for (int i = 0; i < list.size(); i++) {
					label = list.get(i).getNombre();
					//System.out.println("CajaName : " + label);
					if (listaCajaEfectivo != null && listaCajaEfectivo.size() > 0) {
						for (int j = 0; j < listaCajaEfectivo.size(); j++) {
							if (!listaCajaEfectivo.get(j).getCaja().getClave().equals(label)) {
								listaCaja.add(new SelectItem( label ));
							}
						}
					} else {
						listaCaja.add(new SelectItem( label ));
					}
				}
				selectedCaja = list.get(0).getClave();
				//System.out.println("Init Caja seleccionada : " + selectedCaja);				
			}
			
		} catch (Exception ex) {
			System.out.println("ErrorCaja : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: No exiten cajas.. verifique cat?logo");
		}
	}	
	
	private boolean checkCajainUse(Caja auxCaja) throws AlidaPosException {
		Cajaefectivo auxCajaEfectivo = getCajaEfectivoFacade().findByCaja(auxCaja);
		if (auxCajaEfectivo != null) {
			return true;
		}
		return false;
	}
	
	public String checkCashier() {
		try {
			if (Float.compare(efectivoInicial, (float) 0.0) > 0) {
				caja = getCajaFacade().findByClaveIdTienda(selectedCaja, idTienda);	
				if (caja != null && !checkCajainUse(caja)) {					
					//Cajaefectivo cajaEfectivo = new Cajaefectivo();
					cajaEfectivo = new Cajaefectivo();
					cajaEfectivo.setEfectivo(efectivoInicial);
					cajaEfectivo.setFecha(new Date());
					cajaEfectivo.setCaja(caja);
					cajaEfectivo.setUsuario(getLoginBean().getUser());
					getCajaEfectivoFacade().createCajaEfectivo(cajaEfectivo);
					RequestContext.getCurrentInstance().update("menuForm:mainMenuBar");
					message = "";
					RequestContext.getCurrentInstance().execute("PF('cashierDialog').hide();");
					getVentaBean().showCreateDialogListener();		
					String uri = "/ventas/venta/List.xhtml?faces-redirect=true";
					return uri;
				} else {
					message = "La caja ya esta utilizada, seleccione otra.";
					RequestContext.getCurrentInstance().update("abreCajaForm:panelOpenCaja");				
				}	
			} else {
				message = "La cantidad ingresada debe ser mayor a cero.";
				RequestContext.getCurrentInstance().update("abreCajaForm:panelOpenCaja");
			}
		} catch (Exception ex) {
			System.out.println("ErrorCerrarCaja : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		efectivoInicial = 0.0f;
		return null;
	}
	
	public String openCajaDialogAction(Integer idTienda) {
		loadCajaSelectItems(idTienda);		
		RequestContext.getCurrentInstance().update("abreCajaForm:panelOpenCaja");
		RequestContext.getCurrentInstance().execute("PF('cashierDialog').show();");
		return null;
	}
	
	public String openCajaDialogAction() {
		try {
			int idTienda = getLoginBean().getUser().getTienda().getIdtienda();
			loadCajaSelectItems(idTienda);
			RequestContext.getCurrentInstance().update("abreCajaForm:panelOpenCaja");
			RequestContext.getCurrentInstance().execute("PF('cashierDialog').show();");
		} catch (Exception ex) {
			
		}
		return null;
	}
	

	public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
		this.usuarioFacade = usuarioFacade;
	}


	public float getCantidadsistema() {
		return cantidadsistema;
	}

	public void setCantidadsistema(int cantidadsistema) {
		this.cantidadsistema = cantidadsistema;
	}

	
	
	public void doSearch() {
		System.out.println("Realizando busqueda por ... " + searchFecha);
    	selected = null;	
		try {
			System.out.println("Fecha: " + fecha);
			listaCorteCaja = getCorteCajaFacade().findByFecha(searchFecha);
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	//searchFecha;
		RequestContext.getCurrentInstance().update("CierreCajaListForm:CierreCajaListPanel");
	}

	public Date getSearchFecha() {
		return searchFecha;
	}

	public void setSearchFecha(Date searchFecha) {
		this.searchFecha = searchFecha;
	}
	
	
	public Cortecaja getSelected() {
		return selected;
	}

	public void setSelected(Cortecaja selected) {
		this.selected = selected;
	}


	public void setStringFecha(String stringFecha) {
		this.stringFecha = stringFecha;
	}
	
	public float getDineroEfectivo() {
		return dineroEfectivo;
	}


	public void setDineroEfectivo(float dineroEfectivo) {
		this.dineroEfectivo = dineroEfectivo;
	}


	public float getDineroTarjeta() {
		return dineroTarjeta;
	}


	public void setDineroTarjeta(float dineroTarjeta) {
		this.dineroTarjeta = dineroTarjeta;
	}


	public float getDineroCredito() {
		return dineroCredito;
	}


	public void setDineroCredito(float dineroCredito) {
		this.dineroCredito = dineroCredito;
	}


	public float getDineroVales() {
		return dineroVales;
	}


	public void setDineroVales(float dineroVales) {
		this.dineroVales = dineroVales;
	}


	public float getTotal1() {
		return total1;
	}


	public void setTotal1(float total1) {
		this.total1 = total1;
	}


	public float getDineroInicial() {
		return dineroInicial;
	}


	public void setDineroInicial(float dineroInicial) {
		this.dineroInicial = dineroInicial;
	}


	public float getDineroVentas() {
		return dineroVentas;
	}


	public void setDineroVentas(float dineroVentas) {
		this.dineroVentas = dineroVentas;
	}


	public float getDineroPagos() {
		return dineroPagos;
	}


	public void setDineroPagos(float dineroPagos) {
		this.dineroPagos = dineroPagos;
	}


	public float getDineroRetiro() {
		return dineroRetiro;
	}


	public void setDineroRetiro(float dineroRetiro) {
		this.dineroRetiro = dineroRetiro;
	}


	public float getTotal2() {
		return total2;
	}


	public void setTotal2(float total2) {
		this.total2 = total2;
	}


	public Detallecortecaja getDetalleCorteCaja() {
		return detalleCorteCaja;
	}


	public void setDetalleCorteCaja(Detallecortecaja detalleCorteCaja) {
		this.detalleCorteCaja = detalleCorteCaja;
	}


	public DetalleCorteCajaFacade getDetalleCorteCajaFacade() {
		return detalleCorteCajaFacade;
	}


	public void setDetalleCorteCajaFacade(DetalleCorteCajaFacade detalleCorteCajaFacade) {
		this.detalleCorteCajaFacade = detalleCorteCajaFacade;
	}


	public float getCantidadFisica() {
		return cantidadFisica;
	}


	public void setCantidadFisica(float cantidadFisica) {
		this.cantidadFisica = cantidadFisica;
	}



}

