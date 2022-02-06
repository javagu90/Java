package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuario.findByIdpersona", query = "SELECT u FROM Usuario u WHERE u.empleado.idempleado = :idempleado"),
    @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave"),
    @NamedQuery(name = "Usuario.findByClaveacceso", query = "SELECT u FROM Usuario u WHERE u.claveacceso = :claveacceso"),
    @NamedQuery(name = "Usuario.findByNamePwd", query = "SELECT u FROM Usuario u WHERE u.clave = :clave AND u.claveacceso = :claveacceso"),
    @NamedQuery(name = "Usuario.findByComentarios", query = "SELECT u FROM Usuario u WHERE u.comentarios = :comentarios")})
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL            = "Usuario.findAll";
	public static String FIND_BY_ID          = "Usuario.findByIdusuario";
	public static String FIND_BY_ID_EMPLEADO = "Usuario.findByIdempleado";
	public static String FIND_BY_CLAVE       = "Usuario.findByClave";
	public static String FIND_BY_NAME_PWD    = "Usuario.findByNamePwd";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idusuario;
	
	private String clave;
	
	private String claveacceso;
	
	private String comentarios;
	
	private boolean activo;
	
	private String accesomenu;
	
	//bi-directional many-to-one association to Cortecaja
	@OneToMany(mappedBy="usuario1")
	private List<Cortecaja> cortecajas1;
	
	//bi-directional many-to-one association to Cortecaja
	@OneToMany(mappedBy="usuario2")
	private List<Cortecaja> cortecajas2;
	
	//bi-directional many-to-one association to Corteparcial
	@OneToMany(mappedBy="usuario1")
	private List<Corteparcial> cortesparciales1;
	
	//bi-directional many-to-one association to Corteparcial
	@OneToMany(mappedBy="usuario2")
	private List<Corteparcial> cortesparciales2;
	
	//bi-directional many-to-one association to Inventariofisico
	@OneToMany(mappedBy="usuario")
	private List<Inventariofisico> inventariosfisicos;
	
	//bi-directional many-to-one association to Empleado
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="idempleado")
	private Empleado empleado;
	
	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="usuario")
	private List<Venta> ventas;

	//bi-directional many-to-one association to Tienda
	@ManyToOne
	@JoinColumn(name="idtienda")
	private Tienda tienda;
	
	@JoinColumn(name="idperfil")
	private Perfil perfil;
		
	public Usuario() {
	}

	public Integer getIdusuario() {
		if (this.idusuario==null) {
			this.idusuario=0;
		}
		return this.idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getClave() {
		if (this.clave==null) {
			this.clave="";
		}
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getClaveacceso() {
		if (this.claveacceso==null) {
			this.claveacceso="";
		}
		return this.claveacceso;
	}

	public void setClaveacceso(String claveacceso) {
		this.claveacceso = claveacceso;
	}

	public String getComentarios() {
		if (this.comentarios==null) {
			this.comentarios="";
		}
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public List<Cortecaja> getCortecajas1() {
		if (this.cortecajas1==null) {
			this.cortecajas1 =new ArrayList<Cortecaja>();
		}
		return this.cortecajas1;
	}

	public void setCortecajas1(List<Cortecaja> cortecajas1) {
		this.cortecajas1 = cortecajas1;
	}

	public Cortecaja addCortecajas1(Cortecaja cortecajas1) {
		getCortecajas1().add(cortecajas1);
		cortecajas1.setUsuario1(this);

		return cortecajas1;
	}

	public Cortecaja removeCortecajas1(Cortecaja cortecajas1) {
		getCortecajas1().remove(cortecajas1);
		cortecajas1.setUsuario1(null);

		return cortecajas1;
	}

	public List<Cortecaja> getCortecajas2() {
		if (this.cortecajas2==null) {
			this.cortecajas2 =new ArrayList<Cortecaja>();
		}
		return this.cortecajas2;
	}

	public void setCortecajas2(List<Cortecaja> cortecajas2) {
		this.cortecajas2 = cortecajas2;
	}

	public Cortecaja addCortecajas2(Cortecaja cortecajas2) {
		getCortecajas2().add(cortecajas2);
		cortecajas2.setUsuario2(this);

		return cortecajas2;
	}

	public Cortecaja removeCortecajas2(Cortecaja cortecajas2) {
		getCortecajas2().remove(cortecajas2);
		cortecajas2.setUsuario2(null);

		return cortecajas2;
	}

	public List<Corteparcial> getCorteparcials1() {
		if (this.cortesparciales1==null) {
			this.cortesparciales1 =new ArrayList<Corteparcial>();
		}
		return this.cortesparciales1;
	}

	public void setCorteparcials1(List<Corteparcial> corteparcials1) {
		this.cortesparciales1 = corteparcials1;
	}

	public Corteparcial addCorteparcials1(Corteparcial corteparcials1) {
		getCorteparcials1().add(corteparcials1);
		corteparcials1.setUsuario1(this);

		return corteparcials1;
	}

	public Corteparcial removeCorteparcials1(Corteparcial corteparcials1) {
		getCorteparcials1().remove(corteparcials1);
		corteparcials1.setUsuario1(null);

		return corteparcials1;
	}

	public List<Corteparcial> getCorteparcials2() {
		if (this.cortesparciales2==null) {
			this.cortesparciales2 =new ArrayList<Corteparcial>();
		}
		return this.cortesparciales2;
	}

	public void setCorteparcials2(List<Corteparcial> corteparcials2) {
		this.cortesparciales2 = corteparcials2;
	}

	public Corteparcial addCorteparcials2(Corteparcial corteparcials2) {
		getCorteparcials2().add(corteparcials2);
		corteparcials2.setUsuario2(this);

		return corteparcials2;
	}

	public Corteparcial removeCorteparcials2(Corteparcial corteparcials2) {
		getCorteparcials2().remove(corteparcials2);
		corteparcials2.setUsuario2(null);

		return corteparcials2;
	}

	public List<Inventariofisico> getInventariofisicos() {
		if (this.inventariosfisicos==null) {
			this.inventariosfisicos =new ArrayList<Inventariofisico>();
		}
		return this.inventariosfisicos;
	}

	public void setInventariofisicos(List<Inventariofisico> inventariofisicos) {
		this.inventariosfisicos = inventariofisicos;
	}

	public Inventariofisico addInventariofisico(Inventariofisico inventariofisico) {
		getInventariofisicos().add(inventariofisico);
		inventariofisico.setUsuario(this);

		return inventariofisico;
	}

	public Inventariofisico removeInventariofisico(Inventariofisico inventariofisico) {
		getInventariofisicos().remove(inventariofisico);
		inventariofisico.setUsuario(null);

		return inventariofisico;
	}

	public Empleado getEmpleado() {
		if (this.empleado==null) {
			this.empleado =new Empleado();
		}
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public List<Venta> getVentas() {
		if (this.ventas==null) {
			this.ventas =new ArrayList<Venta>();
		}
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setUsuario(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setUsuario(null);

		return venta;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public Tienda getTienda() {
		return this.tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public String getAccesoMenu() {
		if (this.accesomenu==null) {
			this.accesomenu="";
		}
		return this.accesomenu;
	}

	public void setAccesoMenu(String accesomenu) {
		this.accesomenu = accesomenu;
	}

}