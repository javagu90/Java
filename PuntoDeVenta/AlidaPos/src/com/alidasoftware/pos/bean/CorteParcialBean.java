package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.CorteCajaFacade;
import com.alidasoftware.pos.facade.CorteParcialFacade;
import com.alidasoftware.pos.facade.DetalleCorteParcialFacade;
import com.alidasoftware.pos.facade.FormaPagoFacade;
import com.alidasoftware.pos.facade.PagoApartadoFacade;
import com.alidasoftware.pos.facade.PagoVentaFacade;
import com.alidasoftware.pos.helper.PagoApartadoHelper;
import com.alidasoftware.pos.helper.PagoVentaHelper;
import com.alidasoftware.pos.model.Cortecaja;
import com.alidasoftware.pos.model.Corteparcial;
import com.alidasoftware.pos.model.Detallecorteparcial;
import com.alidasoftware.pos.model.Formapago;
import com.alidasoftware.pos.model.Usuario;
import com.alidasoftware.pos.util.Utils;


public class CorteParcialBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -6011391281740178938L;
	
	/*Objetos del Modelo*/
	private Detallecorteparcial detalleCorteparcial;
	private Cortecaja cortecaja;
	private List<Corteparcial> listaCorteParcial;
	private List<Corteparcial> listaCorteParcialesCaja;
	private Corteparcial selected;
	private Usuario usuario1;
	private Usuario usuario2;
	
	/*Objetos del Facade*/
	private DetalleCorteParcialFacade detalleCorteParcialFacade = new DetalleCorteParcialFacade();
	private CorteParcialFacade corteParcialFacade=new CorteParcialFacade();
	private PagoVentaFacade pagoVentaFacade= new PagoVentaFacade();
	private PagoApartadoFacade pagoApartadoFacade= new PagoApartadoFacade();
	private CorteCajaFacade corteCajaFacade= new CorteCajaFacade();
	private FormaPagoFacade formaPagoFacade= new FormaPagoFacade();
	
	/*Objetos Helper*/
	private List<PagoVentaHelper> listaPagoVentaHelper;
	private List <PagoApartadoHelper> listaPagoApartadoHelper;
	
	/*Variables*/
	private int idcorteparcial;
	private float cantidadretirada;
	private Date fecha;
	private Time hora;
	private int ultimopv;
	private int ultimopa;
	private String nombreCaja;
	private String comentarios;
	private float dineroInicial;
	private float dineroVentas;
	private float dineroPagos;
	private Date searchFecha;
	@SuppressWarnings("unused")
	private String stringFecha;
	@SuppressWarnings("unused")
	private String stringHora;
	private float total2;
	private float dineroEfectivo;
	private float dineroTarjeta;
	private float dineroCredito;
	private float dineroVales;
	private float total1;
	private String ultimaventa;
	private String ultimopagoapartado;
	
	/*Constructor*/
	public CorteParcialBean()
	{
		System.out.println("Constructor");
		getFecha();
		getHora();
		prepareCreate();
		listar();
	}
	
	/*Funci?n para la preparaci?n de la creaci?n de on CorteParcial*/
	public Corteparcial prepareCreate() {
		selected = new Corteparcial();
        return selected;
    }
	
	/*Funci?n para crear un nuevo CorteParcial*/
	public void prepareCreateNew() throws AlidaPosException
	{
		System.out.println("prepareCreateNew CorteParcial");
		comentarios="";
		cantidadretirada=0;
		getHora();
		if(getCorteCajaBean().getCaja()!=null)
		{
			this.nombreCaja=getCorteCajaBean().getCaja().getNombre();
			System.out.println("Caja: "+ this.nombreCaja);
			if(corteParcialFacade.findByFecha(fecha).size()==0)// && corteParcialFacade.listAll().)
			{
				System.out.println("es el primer corte del dia");
			}
			else
			{
				this.selected.getUltimopagoapartado();
				this.selected.getUltimopagoventa();
			}
			sumarPagos();
			prepareCreate();
		}
		else{
			System.out.println("CAJA NULA");
		}
	}
	
	/*Funci?n mandada llamar desde el boton Guardar del archivo List.xhtml*/
	public void create() {
		System.out.println("ENTRE A FUNCI?N CREATE");
		try {	
				//getCortecaja();
			System.out.println("ENTRE A PRIMER TRY");
			if(getCorteCajaBean().getCaja()==null)
			{
				System.out.println("ENTRE A PRIMER IF");
				throw new NullPointerException();
			}
			else
			{
				System.out.println("ENTRE A PRIMER ELSE");
				List <Cortecaja> listaCorteCaja=corteCajaFacade.findByFecha(fecha);
				if(listaCorteCaja.size()==0)
				{
					System.out.println("ENTRE A SEGUNDO IF");
					crearCorteCaja();
					crearCorteParcial();
					System.out.println("SALI DEL SEGUNDO IF");
				}
				else
				{
					System.out.println("ENTRE A SEGUNDO ELSE");
					int diferentes=0;
					System.out.println( "SIZE DE LISTA CORTE CAJA: "+ listaCorteCaja.size());
					//System.out.println("Ya hay creado un Corte de caja con esta fecha");
				  for(int i=0; i<listaCorteCaja.size(); i++)
				  {
					if(listaCorteCaja.get(i).getCaja().getIdcaja()==getCorteCajaBean().getCaja().getIdcaja())
					{
						
						System.out.println("CAJA ENCONTRADA");
						diferentes=0;
						break;
					}
					else
						diferentes++;
					System.out.println("SALI DEL SEGUNDO ELSE");
				  }
				  if(diferentes>0)
				  {
					  System.out.println("ENTRE A TERCER IF");
					  crearCorteCaja();
					  crearCorteParcial();
					  System.out.println("SALI DEL TERCER IF");
				  }
				  else
				  {
					  System.out.println("ENTRE AL ULTIMO ELSE");
					  crearCorteParcial();
					  System.out.println("SALI DEL ULTIMO ELSE");
					//  System.out.println("Ya hay creado un Corte de caja con esta fecha o id de caja");
				  }
				  System.out.println("ELSE FINAL");
				}
				System.out.println("ELSE FINAL FINAL");
			}
		}catch (AlidaPosException ex) {
			System.out.println("PRIMER CATCH");
        		keepDialogOpen();
        		displayErrorMessageToUser("Error: 2" + ex.getMessage());
        		System.out.println("FIN PRIMER CATCH");
        }
		catch(NullPointerException npe)
		{
			System.out.println("Entre al catch de nullpointer");
			closeDialog("VentanaCPCreado");
    		keepDialogOpen();
    		displayErrorMessageToUser("Error de Apertura Caja: No ha abierto una caja, abra primero una caja para continuar \n");
    		System.out.println("YA SALI");
		}
		catch(Exception e)
		{
			System.out.println("entre al catch general "+e.getMessage().toString().toUpperCase());
			e.printStackTrace();
		}
		System.out.println("YA TERMINE CREATE");
     }
	
	/*Funci?n para crear un registro de CorteCaja*/
	public void crearCorteCaja() throws AlidaPosException
	{
			getCortecaja();
			System.out.println("No se ha creado un Corte de caja con esta fecha");
			cortecaja.setCaja(getCorteCajaBean().getCaja());
			cortecaja.setCantidadfisica(0);
			cortecaja.setCantidadsistema(0);
			cortecaja.setComentarios("");
			cortecaja.setFecha(fecha);
			cortecaja.setHorafin(hora);
			cortecaja.setHorainicio(hora);
			cortecaja.setUsuario1(getLoginBean().getUser());
			cortecaja.setUsuario2(getPermisoBean().getUser());
			corteCajaFacade.createCortecaja(cortecaja);
			System.out.println("creado ");
	}
	
	/**
	 * Funci?n para crear un registro de CorteParcial
	 */
	public void crearCorteParcial() throws AlidaPosException
	{
		getCortecaja();
		System.out.println("EMPEZANDO ULTIMOPV?: " +ultimopv);
		this.selected.setUltimopagoventa(ultimopv+"");
		this.selected.setUltimopagoapartado(ultimopa+"");
		this.selected.setUsuario1(getLoginBean().getUser());
		this.selected.setUsuario2(getPermisoBean().getUser());
		this.selected.setCortecaja(cortecaja);
		this.selected.setFecha(fecha);
		this.selected.setHora(hora);
		this.selected.setCantidadretirada(cantidadretirada);
		this.selected.setComentarios(comentarios);
		this.selected.setPagos(dineroPagos);
		this.selected.setVentas(dineroVentas);
		dineroInicial=getCorteCajaBean().getEfectivoInicial();
		this.selected.setTotalcaja(dineroPagos+dineroVentas+dineroInicial-this.selected.getCantidadretirada());
    	getCorteParcialFacade().createCorteparcial(selected);
    	getCorteCajaBean().setEfectivoInicial(this.selected.getTotalcaja());
    	creaDetalleCorteParcial();
    	guardarEnLista(selected);
    	closeDialog("VentanaCPCreado");
    	displayInfoMessageToUser("El registro se ha creado");
    	listar();
    	prepareCreate();
	}
	
	/**
	 * Crea un registro de Detalle Corte Parcial
	 */
	public void creaDetalleCorteParcial()
	{
		List<Detallecorteparcial> listaDeDetallesCP = new ArrayList<Detallecorteparcial>();
		try
		{
			this.detalleCorteparcial= new Detallecorteparcial();
			this.detalleCorteparcial.setCorteparcial(selected);
			int i=0;
			
			for(i=0; i<formaPagoFacade.listAllOrderByIdAsc().size(); i++)
			{
				Formapago forma= formaPagoFacade.listAllOrderByIdAsc().get(i);
				System.out.println("FORMA ID: "+forma.getIdformapago());
		
				this.detalleCorteparcial.setFormapago(forma);
				System.out.println("listaPagoVentaHelper "+ listaPagoVentaHelper.get(i).getCantidad());
				System.out.println("listaPagoVentaApartado "+ listaPagoApartadoHelper.get(i).getCantidad());
				this.detalleCorteparcial.setCantidad(listaPagoVentaHelper.get(i).getCantidad()+listaPagoApartadoHelper.get(i).getCantidad());
				//this.selected.getDetallecorteparcial().add(this.detalleCorteparcial);
				this.detalleCorteParcialFacade.createDetallecorteparcial(this.detalleCorteparcial);
				//listaDeDetallesCP.add(this.detalleCorteparcial);
			}
			listaDeDetallesCP=detalleCorteParcialFacade.listAllbyCP(selected);
			this.selected.setDetallecorteparcial(listaDeDetallesCP);
			for(i=0; i<this.selected.getDetallecorteparcial().size(); i++)
			{
				System.out.println("FORMA DE PAGO DE FUNCION LISTA: "+this.selected.getDetallecorteparcial().get(i).getFormapago().getNombre());
			}
			System.out.println("DETALLE CORTE PARCIAL CREADO");
		}
		catch(Exception e)
		{
			System.out.println("Exception: "+ e.getMessage().toString().toUpperCase());
		}
	}
	
	/**
	 * Funci?n para guardar los cortes parciales en una lista que maneja el corte de caja
	 */
	public void guardarEnLista(Corteparcial cp)
	{
	/*	cortecaja.addCorteparcial(selected);
		for(int i=0; i<cortecaja.getCorteparcials().size(); i++)
		{
			System.out.println("ID CORTE PARCIAL: "+ cortecaja.getCorteparcials().get(i).getIdcorteparcial());
			System.out.println("ID CORTE CAJA: "+ cortecaja.getCorteparcials().get(i).getCortecaja().getIdcortecaja());
		}*/
		getListaCorteParcialesCaja();
		listaCorteParcialesCaja.add(cp);
		for(int i=0; i<listaCorteParcialesCaja.size(); i++)
		{
			System.out.println("ID CORTE PARCIAL: "+ listaCorteParcialesCaja.get(i).getIdcorteparcial());
			System.out.println("ID CORTE CAJA: "+ listaCorteParcialesCaja.get(i).getCortecaja().getIdcortecaja());
		}
		cortecaja.setCorteparcials(listaCorteParcialesCaja);
	}
	
	/*Funci?n para obtener los pagos de un CorteParcial*/
	@SuppressWarnings("rawtypes")
	public void sumarPagos()
	{
		System.out.println("entre a metodo de sumatoria");
		try
		{
			
			List <Formapago> formas=getFormaPagoBean().getFormaPagos();//.size();
			int numeroFormas=formas.size();
			int idcaja=getCorteCajaBean().getCaja().getIdcaja();
			
			int maxpv=(int)pagoVentaFacade.getMaxId().get(0);
			System.out.println("Pago Venta: "+ maxpv);
			List listultimopv=null;
			System.out.println("VACIA: "+ corteParcialFacade.findByFecha(fecha).isEmpty());
			List<PagoVentaHelper> listaSumas;
			
			if(corteParcialFacade.findByFecha(fecha).size()==0 )
			{
				System.out.println("Primero: "+ maxpv);
				listaPagoVentaHelper=pagoVentaFacade.listByFirstCP(maxpv, fecha, idcaja);
				System.out.println("lista pago helper: "+listaPagoVentaHelper.size());
				List ultimoPagoVenta=pagoVentaFacade.getMaxIdUltimoPagoVentaOfFirstCP(fecha, idcaja);
				int temporal= Integer.parseInt(ultimoPagoVenta.get(0).toString())+1;
				ultimopv=temporal;
				this.selected.setUltimopagoventa(temporal+"");
				System.out.println("temporal " +temporal);
				System.out.println("this seleceted of if: " +this.selected.getUltimopagoventa());
			}
			else
			{
				int diferentes=0;
				for(int i=0; i<corteParcialFacade.findByFecha(fecha).size(); i++)
				{
					System.out.println("(fecha).get("+i+").getCortecaja().getCaja().getIdcaja() "+corteParcialFacade.findByFecha(fecha).get(i).getCortecaja().getCaja().getIdcaja());
					System.out.println("bean id caja "+ getCorteCajaBean().getCaja().getIdcaja());
					System.out.println(corteParcialFacade.findByFecha(fecha).get(i).getCortecaja().getCaja().getIdcaja() == getCorteCajaBean().getCaja().getIdcaja()); 
					if(corteParcialFacade.findByFecha(fecha).get(i).getCortecaja().getCaja().getIdcaja() == getCorteCajaBean().getCaja().getIdcaja() )
					{
						System.out.println("IGUALES");
						diferentes=0;
						break;
					}
					else
					{
						System.out.println("DIFERENTES");
						diferentes++;
					}
				}
				
				if(diferentes>0)
				{
					System.out.println("ENTRE A IF DE DIFERENTES");
					System.out.println("Primero: "+ maxpv);
					listaPagoVentaHelper=pagoVentaFacade.listByFirstCP(maxpv, fecha, idcaja);
					System.out.println("lista pago helper: "+listaPagoVentaHelper.size());
					List ultimoPagoVenta=pagoVentaFacade.getMaxIdUltimoPagoVentaOfFirstCP(fecha, idcaja);
					int temporal= Integer.parseInt(ultimoPagoVenta.get(0).toString())+1;
					ultimopv=temporal;
					this.selected.setUltimopagoventa(temporal+"");
					System.out.println("temporal " +temporal);
					System.out.println("this seleceted of if: " +this.selected.getUltimopagoventa());
				}
				else
				{	
					System.out.println("else");
					listultimopv=corteParcialFacade.getUltimoPagoVentaofCaja(getCorteCajaBean().getIdcortecaja());
					ultimopv=Integer.parseInt(listultimopv.get(0).toString());
					System.out.println("This selected: "+this.selected.getUltimopagoventa());
					System.out.println("ultimopv "+ultimopv);
					System.out.println("maxpv " +maxpv);
					System.out.println(fecha);
					System.out.println("idcaja " +idcaja);
					List ultimoPagoVenta=null;
					try
					{
						System.out.println("CREANDO LISTA PAGO VENTA HELPER");
						listaPagoVentaHelper=pagoVentaFacade.listBySumFormaPago(ultimopv,maxpv,fecha,idcaja); //try y catch
						System.out.println("CREADA LA LISTA PAGO VENTA HELPER");
					}
					catch(AlidaPosException ae)
					{
						System.out.println("*************ALIDA EXCEPTION ***************** "+ae.getMessage().toString().toUpperCase());
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage().toString().toUpperCase());
					}
					try
					{
						System.out.println("LLAMANDO A  PAGO VENTA FACADE GETMAXIDULTIMO PV");
						ultimoPagoVenta=pagoVentaFacade.getMaxIdUltimoPagoVenta(ultimopv, fecha, idcaja); //try catch
						System.out.println(" FIN DEL LLAMANDO A  PAGO VENTA FACADE GETMAXIDULTIMO PV");
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage().toString().toUpperCase());
					}
					int temporal= Integer.parseInt(ultimoPagoVenta.get(0).toString())+1;
					System.out.println("CASTEADO EL TEMPORAL");
					ultimopv=temporal;
					System.out.println("ASIGNADO EL TEMPORAL");
					this.selected.setUltimopagoventa(temporal+"");
					System.out.println("temporal " +temporal);
					System.out.println("this seleceted of elese: " +this.selected.getUltimopagoventa());
					System.out.println("FINALIZADO EL ELSE DE SUMATORIA PAGA VRNTA HELPER");
				}
			}
			
			System.out.println("Lista sumatoria vacia?: "+listaPagoVentaHelper.isEmpty());
			System.out.println("Sumas obtenidas : "+listaPagoVentaHelper.size());
			System.out.println("formas de pago:"+ numeroFormas);
			
			
			if(listaPagoVentaHelper.isEmpty())
			{
				System.out.println("esta vacia la lista de pago venta helper");
				for(int i=0; i<numeroFormas; i++)
				{
					listaPagoVentaHelper.add(new PagoVentaHelper());
					
				}
			}
			else
			{
				System.out.println("entre al else de si la pago helper estaba vacia: "+ listaPagoVentaHelper.size());
				listaSumas=new ArrayList<PagoVentaHelper>();
				for(int i=0; i<numeroFormas; i++)
				{
					listaSumas.add(new PagoVentaHelper());
				}
					
				for(int i=0; i<listaPagoVentaHelper.size(); i++)
				{
					listaSumas.set(listaPagoVentaHelper.get(i).getIdFormaPago()-1, listaPagoVentaHelper.get(i));
				}
				listaPagoVentaHelper=listaSumas;
				System.out.println("Sumas obtenidas : "+listaPagoVentaHelper.size());
				
			}
			
			System.out.println("*************************LISTA PAGO VENTA HELPER*******************");
			
			for(int i=0; i<listaPagoVentaHelper.size(); i++)
			{
				System.out.println("ID FP: "+listaPagoVentaHelper.get(i).getIdFormaPago()+" : "+listaPagoVentaHelper.get(i).getCantidad());
			}
			
			if(listaPagoVentaHelper.get(0)!=null)
			{
				dineroEfectivo=listaPagoVentaHelper.get(0).getCantidad();
			}
			if(listaPagoVentaHelper.get(1)!=null)
			{
				dineroCredito=listaPagoVentaHelper.get(1).getCantidad();
			}
			if(listaPagoVentaHelper.get(2)!=null)
			{
				dineroTarjeta=listaPagoVentaHelper.get(2).getCantidad();
			}
			if(listaPagoVentaHelper.get(3)!=null)
			{
				dineroVales=listaPagoVentaHelper.get(3).getCantidad();
			}
			
			
			int maxpa=(int)pagoApartadoFacade.getMaxId().get(0);
			System.out.println("Pago Apartado: "+ maxpa);
			System.out.println("VACIA: "+ corteParcialFacade.findByFecha(fecha).isEmpty());
			List<PagoApartadoHelper> listaSumasApartado;
			List listultimopa=null;
			
			if(corteParcialFacade.findByFecha(fecha).size()==0)
			{
				System.out.println("pa: "+ maxpa);
				listaPagoApartadoHelper=pagoApartadoFacade.listByFirstCP(maxpa, fecha, idcaja);
				System.out.println("lista pago apartado helper: "+listaPagoApartadoHelper.size());
				List ultimoPagoApartado=pagoApartadoFacade.getMaxIdUltimoPagoApartadoOfFirstCP(fecha, idcaja);
				int temporal= Integer.parseInt(ultimoPagoApartado.get(0).toString())+1;
				ultimopa=temporal;
				this.selected.setUltimopagoapartado(temporal+"");
				System.out.println("temporal " +temporal);
				System.out.println("this seleceted of if: " +this.selected.getUltimopagoapartado());
				
			}
			else
			{
				int diferentes=0;
				for(int i=0; i<corteParcialFacade.findByFecha(fecha).size(); i++)
				{
					System.out.println("(fecha).get("+i+").getCortecaja().getCaja().getIdcaja() "+corteParcialFacade.findByFecha(fecha).get(i).getCortecaja().getCaja().getIdcaja());
					System.out.println("bean id caja "+ getCorteCajaBean().getCaja().getIdcaja());
					if(corteParcialFacade.findByFecha(fecha).get(i).getCortecaja().getCaja().getIdcaja() == getCorteCajaBean().getCaja().getIdcaja() )
					{
						System.out.println("IGUALES f2");
						diferentes=0;
						break;
					}
					else
					{
						System.out.println("DIFERENTES f2");
						diferentes++;
					}
				}
				
				if(diferentes>0)
				{
					System.out.println("ENTRE A IF DE DIFERENTES");
					System.out.println("Primero: "+ maxpa);
					listaPagoApartadoHelper=pagoApartadoFacade.listByFirstCP(maxpa, fecha, idcaja);
					System.out.println("lista pago apartado helper: "+listaPagoApartadoHelper.size());
					List ultimoPagoApartado=pagoApartadoFacade.getMaxIdUltimoPagoApartadoOfFirstCP(fecha, idcaja);
					int temporal= Integer.parseInt(ultimoPagoApartado.get(0).toString())+1;
					ultimopa=temporal;
					this.selected.setUltimopagoapartado(temporal+"");
					System.out.println("temporal " +temporal);
					System.out.println("this seleceted of if: " +this.selected.getUltimopagoapartado());
				}
				else
				{	
					System.out.println("else");
					listultimopa=corteParcialFacade.getUltimoPagoApartadoofCaja(getCorteCajaBean().getIdcortecaja());
					ultimopa=Integer.parseInt(listultimopa.get(0).toString());
					System.out.println("This selected: "+this.selected.getUltimopagoapartado());
					System.out.println("ultimopa "+ultimopa);
					System.out.println("maxpa " +maxpa);
					System.out.println(fecha);
					System.out.println("idcaja " +idcaja);
					List ultimoPagoApartado=null;
					try
					{
						listaPagoApartadoHelper=pagoApartadoFacade.listBySumFormaPago(ultimopa,maxpa,fecha,idcaja); //try y catch
						System.out.println("CREADA LA LISTA PAGO Apartado HELPER");
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage().toString().toUpperCase());
					}
					try
					{
						ultimoPagoApartado=pagoApartadoFacade.getMaxIdUltimoPagoApartado(ultimopa, fecha, idcaja); //try catch
						System.out.println("CREADA LA LISTA ULTIMO PAGO apartado");
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage().toString().toUpperCase());
					}
					int temporal= Integer.parseInt(ultimoPagoApartado.get(0).toString())+1;
					System.out.println("CASTEADO EL TEMPORAL");
					ultimopa=temporal;
					System.out.println("ASIGNADO EL TEMPORAL");
					this.selected.setUltimopagoapartado(temporal+"");
					System.out.println("temporal " +temporal);
					System.out.println("this seleceted of elese: " +this.selected.getUltimopagoapartado());
					System.out.println("FINALIZADO EL ELSE DE SUMATORIA PAGA apartado HELPER");
				}
			}
			System.out.println("Lista pagos vacia?: "+listaPagoApartadoHelper.isEmpty());
			System.out.println("Sumas obtenidas : "+listaPagoApartadoHelper.size());
			
			if(listaPagoApartadoHelper.isEmpty())
			{
				for(int i=0; i<numeroFormas; i++)
				{
					listaPagoApartadoHelper.add(new PagoApartadoHelper());
					
				}
			}
			else
			{
				listaSumasApartado=new ArrayList<PagoApartadoHelper>();
				for(int i=0; i<numeroFormas; i++)
				{
					listaSumasApartado.add(new PagoApartadoHelper());
				}
					
				for(int i=0; i<listaPagoApartadoHelper.size(); i++)
				{
					listaSumasApartado.set(listaPagoApartadoHelper.get(i).getIdFormaPago()-1, listaPagoApartadoHelper.get(i));
				}
				listaPagoApartadoHelper=listaSumasApartado;
				System.out.println("Sumas obtenidas : "+listaPagoApartadoHelper.size());
				
			}		
			
			System.out.println("*************************LISTA PAGO APARTADO HELPER*******************");
			
			for(int i=0; i<listaPagoApartadoHelper.size(); i++)
			{
				System.out.println("ID FP: "+listaPagoApartadoHelper.get(i).getIdFormaPago()+" : "+listaPagoApartadoHelper.get(i).getCantidad());
			}
			
			if(listaPagoApartadoHelper.get(0)!=null)
			{
				dineroEfectivo+=listaPagoApartadoHelper.get(0).getCantidad();
			}
			
			if(listaPagoApartadoHelper.get(1)!=null)
			{
				dineroCredito+=listaPagoApartadoHelper.get(1).getCantidad();
			}
			
			if(listaPagoApartadoHelper.get(2)!=null)
			{
				dineroTarjeta+=listaPagoApartadoHelper.get(2).getCantidad();
			}
			
			if(listaPagoApartadoHelper.get(3)!=null)
			{
				dineroVales+=listaPagoApartadoHelper.get(3).getCantidad();
			}
			
			if(listaPagoVentaHelper.get(0)!=null)
			{
				dineroVentas=listaPagoVentaHelper.get(0).getCantidad();
			}
			
			if(listaPagoApartadoHelper.get(0)!=null)
			{
				
				dineroPagos=listaPagoApartadoHelper.get(0).getCantidad();
			}
	
			
			total1=dineroEfectivo+dineroCredito+dineroTarjeta+dineroVales;
		}
		catch(AlidaPosException e)
		{
			System.out.println("Error en Lista de sumas contenida en CorteParcialBean "+ e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Error en FUNCION Lista de sumas contenida en CorteParcialBean "+ e.getMessage().toString());
			e.printStackTrace();
		}
	}
	
	/*Funci?n para obtener el evento de doble click en la tabla*/
	public void onRowDblClckSelect(SelectEvent event) {  
		System.out.println("entro a esto");
		if (event != null && event.getObject() != null) {
			selected = (Corteparcial) event.getObject();
			
			System.out.println("size "+selected.getDetallecorteparcial().size());
			System.out.println("id "+selected.getDetallecorteparcial().get(0).getIddetallecorteparcial());
			System.out.println("id corteparcial "+selected.getDetallecorteparcial().get(0).getCorteparcial().getIdcorteparcial());
			System.out.println("id forma de pago "+selected.getDetallecorteparcial().get(0).getFormapago().getIdformapago());
			System.out.println("id forma de pago "+selected.getDetallecorteparcial().get(1).getFormapago().getIdformapago());
			System.out.println("id forma de pago "+selected.getDetallecorteparcial().get(2).getFormapago().getIdformapago());
			System.out.println("id forma de pago "+selected.getDetallecorteparcial().get(3).getFormapago().getIdformapago());
			//System.out.println("cantidad "+ selected.getDetallecorteparcial().get(0).getCantidad());
			
			System.out.println("************************DETALLES************************************");
			for(int i=0; i<4; i++)
			{
				//System.out.println("ID FORMA DE PAGO: "+selected.getDetallecorteparcial().get(i).getFormapago().getIdformapago());
				switch(selected.getDetallecorteparcial().get(i).getFormapago().getIdformapago())
				{
					case 1:
					{
						System.out.println("FORMA DE PAGO: "+selected.getDetallecorteparcial().get(i).getFormapago().getNombre());
						System.out.println("ID FORMA DE PAGO: "+selected.getDetallecorteparcial().get(i).getFormapago().getIdformapago());
						System.out.println("cantidad "+ selected.getDetallecorteparcial().get(i).getCantidad());
						dineroEfectivo=selected.getDetallecorteparcial().get(i).getCantidad();
						break;
					}
					case 2:
					{
						System.out.println("FORMA DE PAGO: "+selected.getDetallecorteparcial().get(i).getFormapago().getNombre());
						System.out.println("ID FORMA DE PAGO: "+selected.getDetallecorteparcial().get(i).getFormapago().getIdformapago());
						System.out.println("cantidad "+ selected.getDetallecorteparcial().get(i).getCantidad());
						dineroCredito=selected.getDetallecorteparcial().get(i).getCantidad();
						break;
					}
					case 3:
					{
						System.out.println("FORMA DE PAGO: "+selected.getDetallecorteparcial().get(i).getFormapago().getNombre());
						System.out.println("ID FORMA DE PAGO: "+selected.getDetallecorteparcial().get(i).getFormapago().getIdformapago());
						System.out.println("cantidad "+ selected.getDetallecorteparcial().get(i).getCantidad());
						dineroTarjeta=selected.getDetallecorteparcial().get(i).getCantidad();
						break;
					}
					case 4:
					{
						System.out.println("FORMA DE PAGO: "+selected.getDetallecorteparcial().get(i).getFormapago().getNombre());
						System.out.println("ID FORMA DE PAGO: "+selected.getDetallecorteparcial().get(i).getFormapago().getIdformapago());
						System.out.println("cantidad "+ selected.getDetallecorteparcial().get(i).getCantidad());
						dineroVales=selected.getDetallecorteparcial().get(i).getCantidad();
						break;
					}
				}
			}	
				
			viewRecord();	        
		}
    }
	/*Funci?n para ver los detalles del cortepaarcial seleccionado*/
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("CorteParcialViewDlg");
	}
	
	/*Funci?n que lista los CortesParciales contenidos en la BBDD*/
	public void listar()
	{
		try {
			//System.out.println("numero de elementos_: "+ listaCorteParcial.size());
			listaCorteParcial=corteParcialFacade.listAll();
			System.out.println("Objetos obtenidos : " + listaCorteParcial.size());
		} catch (AlidaPosException e) { 
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*Funci?n para buscar un CorteParcial*/
	public void doSearch() {
		System.out.println("Realizando busqueda por ... " + searchFecha);
    	selected = null;	
		try {
			listaCorteParcial = getCorteParcialFacade().findByFecha(searchFecha);
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	//searchFecha;
		RequestContext.getCurrentInstance().update("CorteParcialListForm:CorteParcialListPanel");
	}
	
	/*Handlers*/
	 public void handleKeyEvent() {
		 
	        this.total2 = -this.cantidadretirada;
	    }

	
	/*GetManageBeans*/
	private FormaPagoBean getFormaPagoBean() {
		  return (FormaPagoBean) Utils.getManagedBean("formaPagoBean");
		 }

	
	private LoginBean getLoginBean() {
		  return (LoginBean) Utils.getManagedBean("loginBean");
		 }
	
	
	private PermisoBean getPermisoBean() {
		  return (PermisoBean) Utils.getManagedBean("permisoBean");
		 }
	
	private CorteCajaBean getCorteCajaBean()
	{
		return (CorteCajaBean) Utils.getManagedBean("corteCajaBean");
	}
	

	/*Getters*/
	public Detallecorteparcial getDetalleCorteparcial() 
	{
		if(detalleCorteparcial== null)
		{
			detalleCorteparcial= new Detallecorteparcial();
		}
		return detalleCorteparcial;
	}
		
	public DetalleCorteParcialFacade getDetalleCorteParcialFacade() {
		return detalleCorteParcialFacade;
	}
	
	public List <PagoApartadoHelper> getlistaPagoApartadoHelper() {
		return listaPagoApartadoHelper;
	}
	

	public PagoVentaFacade getPagoVentaFacade() {
		return pagoVentaFacade;
	}
	
	public List<PagoVentaHelper> getlistaPagoVentaHelper() {
		return listaPagoVentaHelper;
	}
	
	public Corteparcial getSelected() {
		return selected;
	}

	public Cortecaja getCortecaja() {
		if(cortecaja==null)
		{
			cortecaja= new Cortecaja();
		}
		return cortecaja;
	}
	
	public float getCantidadretirada() {
		return cantidadretirada;
	}
	
	public String getComentarios() {
		return comentarios;
	}
	
	public Date getFecha() {
		if(fecha==null)
		{
			fecha= new Date();
		}
		return fecha;
	}
	
	public float getDineroInicial() {
		return dineroInicial;
	}
	
	public CorteParcialFacade getCorteParcialFacade() {
		return corteParcialFacade;
	}
	
	public int getIdCorteParcial() {
		return idcorteparcial;
	}
	
	public float getCantidadRetirada() {
		return cantidadretirada;
	}
	
	public Time getHora() {
		//if(hora==null)
		//{
		System.out.println("MANDE A LLAMAR LA HORA");
			hora= Utils.getHora();
		//}
		return hora;
	}
	
	public CorteParcialFacade getCorteParcial() {
		if(corteParcialFacade==null)
		{
			corteParcialFacade=new CorteParcialFacade();
		}
		return corteParcialFacade;
	}
	
	public Date getSearchFecha() {
		return searchFecha;
	}
	/*Funci?n para Formatear la fecha*/
	public String getStringFecha()
	{
	
		return Utils.DateToString("dd-MM-yyyy");
	}
	
	public List<Corteparcial> getListaCorteParcial() {
		return listaCorteParcial;
	}
	
	public float getTotal2() {
		return total2;
	}
	
	public PagoApartadoFacade getPagoApartadoFacade() {
		return pagoApartadoFacade;
	}
	
	public Usuario getUsuario1() {
		if(usuario1==null)
		{
			usuario1=getLoginBean().getUser();
		}
		return usuario1;
	}
	
	public Usuario getUsuario2() {
		if(usuario2==null)
		{
			usuario2=getPermisoBean().getUser();
		}
		return usuario2;
	}
	
	public float getDineroVentas() {
		return dineroVentas;
	}
	
	public float getDineroPagos() {
		return dineroPagos;
	}

	public float getDineroVales() {
		return dineroVales;
	}

	public float getDineroCredito() {
		return dineroCredito;
	}

	public float getTotal1() {
		total1=dineroCredito+dineroEfectivo+dineroTarjeta+dineroVales;
		return total1;
	}
	
	public float getDineroEfectivo() {
		return dineroEfectivo;
	}
	
	public String getUltimaventa() {
		return ultimaventa;
	}
	
	public String getUltimopagoapartado() {
		return ultimopagoapartado;
	}
	
	public float getDineroTarjeta() {
		return dineroTarjeta;
	}
	
	public String getNombreCaja() {
		return nombreCaja;
	}


	
	/*Setters*/
	public void setDetalleCorteparcial(Detallecorteparcial detalleCorteparcial) {
		this.detalleCorteparcial = detalleCorteparcial;
	}

	public void setDetalleCorteParcialFacade(DetalleCorteParcialFacade detalleCorteParcialFacade) {
		this.detalleCorteParcialFacade = detalleCorteParcialFacade;
	}

	public void setlistaPagoApartadoHelper(List <PagoApartadoHelper> listaPagoApartadoHelper) {
		this.listaPagoApartadoHelper = listaPagoApartadoHelper;
	}
	
	public void setPagoVentaFacade(PagoVentaFacade pagoVentaFacade) {
		this.pagoVentaFacade = pagoVentaFacade;
	}

	public void setlistaPagoVentaHelper(List<PagoVentaHelper> listaPagoVentaHelper) {
		this.listaPagoVentaHelper = listaPagoVentaHelper;
	}
	
	public void setSelected(Corteparcial selected) {
		this.selected = selected;
	}
	
	public void setCortecaja(Cortecaja cortecaja) {
		this.cortecaja = cortecaja;
	}
	
	public void setCantidadretirada(float cantidadretirada) {
		this.cantidadretirada = cantidadretirada;
	}
	
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public void setDineroInicial(float dineroInicial) {
		this.dineroInicial = dineroInicial;
	}
	
	public void setCorteParcialFacade(CorteParcialFacade corteParcialFacade) {
		this.corteParcialFacade = corteParcialFacade;
	}
	public void setIdCorteParcial(int idCorteParcial) {
		this.idcorteparcial = idCorteParcial;
	}
	
	public void setCantidadRetirada(float cantidadRetirada) {
		this.cantidadretirada = cantidadRetirada;
	}
	
	public void setHora(Time hora) {
		this.hora = hora;
	}
	
	public void setCorteParcial(CorteParcialFacade corteParcial) {
		this.corteParcialFacade = corteParcial;
	}
	
	public void setSearchFecha(Date searchFecha) {
		this.searchFecha = searchFecha;
	}
	
	public void setStringFecha(String stringFecha) {
		this.stringFecha = stringFecha;
	}

	public void setStringHora(String stringHora) {
		this.stringHora = stringHora;
	}
	
	public void setPagoApartadoFacade(PagoApartadoFacade pagoApartadoFacade) {
		this.pagoApartadoFacade = pagoApartadoFacade;
	}
	
	public void setListaCorteParcial(List<Corteparcial> listaCorteParcial) {
		this.listaCorteParcial = listaCorteParcial;
	}
	
	public void setTotal2(float total2) {
		this.total2 = total2;
	}

	public void setDineroVentas(float dineroVentas) {
		this.dineroVentas = dineroVentas;
	}
	
	public void setDineroPagos(float dineroPagos) {
		this.dineroPagos = dineroPagos;
	}
	
	public void setDineroCredito(float dineroCredito) {
		this.dineroCredito = dineroCredito;
	}

	public void setDineroTarjeta(float dineroTarjeta) {
		this.dineroTarjeta = dineroTarjeta;
	}
	
	public void setDineroVales(float dineroVales) {
		this.dineroVales = dineroVales;
	}
	
	public void setTotal1(float total1) {
		this.total1 = total1;
	}
	
	public void setDineroEfectivo(float dineroEfectivo) {
		this.dineroEfectivo= dineroEfectivo;
	}
	
	public void setDineroEfectivo(int dineroEfectivo) {
		this.dineroEfectivo = dineroEfectivo;
	}
		
	public void setVentas(float dineroVentas) {
		this.dineroVentas = dineroVentas;
	}

	public void setUltimaventa(String ultimaventa) {
		this.ultimaventa = ultimaventa;
	}
		
	public void setUltimopagoapartado(String ultimopagoapartado) {
		this.ultimopagoapartado = ultimopagoapartado;
	}
	
	public void setNombreCaja(String nombreCaja) {
		this.nombreCaja = nombreCaja;
	}

	public List<Corteparcial> getListaCorteParcialesCaja() {
		if(listaCorteParcialesCaja==null)
		{
			listaCorteParcialesCaja= new ArrayList<Corteparcial>();
		}
		return listaCorteParcialesCaja;
	}

	public void setListaCorteParcialesCaja(List<Corteparcial> listaCorteParcialesCaja) {
		this.listaCorteParcialesCaja = listaCorteParcialesCaja;
	}
	
	
}
