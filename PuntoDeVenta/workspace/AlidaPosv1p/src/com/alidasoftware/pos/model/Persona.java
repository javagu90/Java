package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@Table(name = "persona")
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdpersona", query = "SELECT p FROM Persona p WHERE p.idpersona = :idpersona"),
    @NamedQuery(name = "Persona.findByIdcontacto", query = "SELECT p FROM Persona p WHERE p.contacto = :idcontacto"),
    @NamedQuery(name = "Persona.findByNombre", query = "SELECT p FROM Persona p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Persona.findByApellidopaterno", query = "SELECT p FROM Persona p WHERE p.apellidopaterno = :apellidopaterno"),
    @NamedQuery(name = "Persona.findByApellidomaterno", query = "SELECT p FROM Persona p WHERE p.apellidomaterno = :apellidomaterno"),
    @NamedQuery(name = "Persona.findLikeInfoPersona", query = "SELECT p FROM Persona p WHERE p.nombre LIKE :info OR p.apellidopaterno LIKE :info OR p.apellidomaterno LIKE :info"),
    @NamedQuery(name = "Persona.findByFechanacimiento", query = "SELECT p FROM Persona p WHERE p.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "Persona.findByTipopersona", query = "SELECT p FROM Persona p WHERE p.tipopersona = :tipopersona"),
    @NamedQuery(name = "Persona.findByRfc", query = "SELECT p FROM Persona p WHERE p.rfc = :rfc"),
    @NamedQuery(name = "Persona.findByRazonsocial", query = "SELECT p FROM Persona p WHERE p.razonsocial = :razonsocial")})

public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String FIND_ALL                 = "Persona.findAll";
	public static String FIND_BY_ID               = "Persona.findByIdpersona";
	public static String FIND_BY_ID_CONTACTO      = "Persona.findByIdcontacto";
	public static String FIND_BY_NOMBRE           = "Persona.findByNombre";
	public static String FIND_BY_APELLIDO_PAT     = "Persona.findByApellidopaterno";
	public static String FIND_BY_APELLIDO_MAT     = "Persona.findByApellidomaterno";
	public static String FIND_BY_FECHA_NACIMIENTO = "Persona.findByFechanacimiento";
	public static String FIND_BY_TIPO_PERSONA     = "Persona.findByTipopersona";
	public static String FIND_BY_RFC              = "Persona.findByRfc";
	public static String FIND_BY_RAZON_SOCIAL     = "Persona.findByRazonsocial";
	public static String FIND_LIKE_INFO_PERSONA   = "Persona.findLikeInfoPersona";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idpersona;

	private String apellidomaterno;

	private String apellidopaterno;

	@Temporal(TemporalType.DATE)
	private Date fechanacimiento;

	private String nombre;

	private String razonsocial;

	private String rfc;

	private String tipopersona;
	
	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="persona")
	private List<Cliente> clientes;
	
	//bi-directional many-to-one association to Contacto
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="idcontacto")
	private Contacto contacto;
	
	//bi-directional many-to-one association to Proveedor
	@OneToMany(mappedBy="persona")
	private List<Proveedor> proveedores;

	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="persona")
	private List<Empleado> empleados;

	/*
	//bi-directional many-to-one association to Tienda
	@OneToMany(mappedBy="responsable")
	private List<Tienda> tiendas;
	*/

	public Persona() {
	}

	public Integer getIdpersona() {
		return this.idpersona;
	}

	public void setIdpersona(Integer idpersona) {
		this.idpersona = idpersona;
	}

	public String getApellidomaterno() {
		return this.apellidomaterno;
	}

	public void setApellidomaterno(String apellidomaterno) {
		this.apellidomaterno = apellidomaterno;
	}

	public String getApellidopaterno() {
		return this.apellidopaterno;
	}

	public void setApellidopaterno(String apellidopaterno) {
		this.apellidopaterno = apellidopaterno;
	}

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRazonsocial() {
		return this.razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public String getRfc() {
		return this.rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getTipopersona() {
		return this.tipopersona;
	}

	public void setTipopersona(String tipopersona) {
		this.tipopersona = tipopersona;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Empleado addEmpleado(Empleado empleado) {
		getEmpleados().add(empleado);
		empleado.setPersona(this);

		return empleado;
	}

	public Empleado removeEmpleado(Empleado empleado) {
		getEmpleados().remove(empleado);
		empleado.setPersona(null);

		return empleado;
	}

	/*
	public List<Tienda> getTiendas() {
		return this.tiendas;
	}

	public void setTiendas(List<Tienda> tiendas) {
		this.tiendas = tiendas;
	}

	public Tienda addTienda(Tienda tienda) {
		getTiendas().add(tienda);
		tienda.setResponsable(this);

		return tienda;
	}

	public Tienda removeTienda(Tienda tienda) {
		getTiendas().remove(tienda);
		tienda.setResponsable(null);

		return tienda;
	}
	*/
	
	public List<Cliente> getClientes() {
		if (this.clientes==null) {
			this.clientes =new ArrayList<Cliente>();
		}
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setPersona(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setPersona(null);
		return cliente;
	}

	public Contacto getContacto() {
		if (this.contacto==null) {
			this.contacto =new Contacto();
		}
		return this.contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public List<Proveedor> getProveedores() {
		if (this.proveedores==null) {
			this.proveedores =new ArrayList<Proveedor>();
		}
		return this.proveedores;
	}

	public void setProveedors(List<Proveedor> proveedors) {
		this.proveedores = proveedors;
	}

	public Proveedor addProveedor(Proveedor proveedor) {
		getProveedores().add(proveedor);
		proveedor.setPersona(this);

		return proveedor;
	}

	public Proveedor removeProveedor(Proveedor proveedor) {
		getProveedores().remove(proveedor);
		proveedor.setPersona(null);

		return proveedor;
	}

}