package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdproducto", query = "SELECT p FROM Producto p WHERE p.idproducto = :idproducto"),
    @NamedQuery(name = "Producto.findByIdunidad", query = "SELECT p FROM Producto p WHERE p.unidad = :idunidad"),
    @NamedQuery(name = "Producto.findByClave", query = "SELECT p FROM Producto p WHERE p.clave = :clave"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findLikeNombre", query = "SELECT p FROM Producto p WHERE UPPER(p.nombre) LIKE UPPER(:nombre) OR UPPER(p.clave) LIKE UPPER(:nombre)"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Producto.findByFechaingreso", query = "SELECT p FROM Producto p WHERE p.fechaingreso = :fechaingreso"),
    @NamedQuery(name = "Producto.findByIva", query = "SELECT p FROM Producto p WHERE p.iva = :iva"),
    @NamedQuery(name = "Producto.findByComentarios", query = "SELECT p FROM Producto p WHERE p.comentarios = :comentarios")})
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL                      = "Producto.findAll";
	public static String FIND_BY_ID                    = "Producto.findByIdproducto";
	public static String FIND_BY_ID_UNIDAD             = "Producto.findByIdunidad";
	public static String FIND_BY_ID_CATEGORIA_PRODUCTO = "Producto.findByIdcategoriaproducto";
	public static String FIND_BY_CLAVE                 = "Producto.findByClave";
	public static String FIND_BY_NOMBRE                = "Producto.findByNombre";
	public static String FIND_LIKE_NOMBRE              = "Producto.findLikeNombre";
	public static String FIND_BY_DESCRIPCION           = "Producto.findByDescripcion";
	public static String FIND_BY_FECHA_INGRESO         = "Producto.findByFechaingreso";
	public static String FIND_BY_IVA                   = "Producto.findByIva";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproducto;

	private String clave;

	private String codigo;

	private String comentarios;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fechaingreso;

	private float iva;

	private String nombre;

	private String pathimagen;
	
	private boolean activo;
	
	private float precio;
	
	@JoinColumn(name="idproveedor")
	private Proveedor proveedor;

	//bi-directional many-to-one association to Detalleapartado
	@OneToMany(mappedBy="producto")
	private List<Detalleapartado> detalleapartados;

	//bi-directional many-to-one association to Detalledevolucion
	@OneToMany(mappedBy="producto")
	private List<Detalledevolucion> detalledevolucions;

	//bi-directional many-to-one association to Detalleinventario
	@OneToMany(mappedBy="producto")
	private List<Detalleinventario> detalleinventarios;

	//bi-directional many-to-one association to Detalleinventariofisico
	@OneToMany(mappedBy="producto")
	private List<Detalleinventariofisico> detalleinventariofisicos;

	//bi-directional many-to-one association to Detalletraspaso
	@OneToMany(mappedBy="producto")
	private List<Detalletraspaso> detalletraspasos;

	//bi-directional many-to-one association to Detalleventa
	@OneToMany(mappedBy="producto")
	private List<Detalleventa> detalleventas;

	//bi-directional many-to-one association to Detallevista
	@OneToMany(mappedBy="producto")
	private List<Detallevista> detallevistas;

	//bi-directional many-to-one association to Entradainventario
	@OneToMany(mappedBy="producto")
	private List<Entradainventario> entradainventarios;

	//bi-directional many-to-one association to Precioventa
	@OneToMany(mappedBy="producto")
	private List<Precioventa> precioventas;

	//bi-directional many-to-one association to Unidad
	@ManyToOne
	@JoinColumn(name="idunidad")
	private Unidad unidad;

	//bi-directional many-to-many association to Categoriaproducto
	@ManyToMany
	@JoinTable(
		name="categoriasdeproducto"
		, joinColumns={
			@JoinColumn(name="idproducto")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idcategoriaproducto")
			}
		)
	private List<Categoriaproducto> categoriaproductos;

	public Producto() {
	}

	public Integer getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaingreso() {
		return this.fechaingreso;
	}

	public void setFechaingreso(Date fechaingreso) {
		this.fechaingreso = fechaingreso;
	}

	public float getIva() {
		return this.iva;
	}

	public void setIva(float iva) {
		this.iva = iva;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPathimagen() {
		return this.pathimagen;
	}

	public void setPathimagen(String pathimagen) {
		this.pathimagen = pathimagen;
	}

	public List<Detalleapartado> getDetalleapartados() {
		return this.detalleapartados;
	}

	public void setDetalleapartados(List<Detalleapartado> detalleapartados) {
		this.detalleapartados = detalleapartados;
	}

	public Detalleapartado addDetalleapartado(Detalleapartado detalleapartado) {
		getDetalleapartados().add(detalleapartado);
		detalleapartado.setProducto(this);

		return detalleapartado;
	}

	public Detalleapartado removeDetalleapartado(Detalleapartado detalleapartado) {
		getDetalleapartados().remove(detalleapartado);
		detalleapartado.setProducto(null);

		return detalleapartado;
	}

	public List<Detalledevolucion> getDetalledevolucions() {
		return this.detalledevolucions;
	}

	public void setDetalledevolucions(List<Detalledevolucion> detalledevolucions) {
		this.detalledevolucions = detalledevolucions;
	}

	public Detalledevolucion addDetalledevolucion(Detalledevolucion detalledevolucion) {
		getDetalledevolucions().add(detalledevolucion);
		detalledevolucion.setProducto(this);

		return detalledevolucion;
	}

	public Detalledevolucion removeDetalledevolucion(Detalledevolucion detalledevolucion) {
		getDetalledevolucions().remove(detalledevolucion);
		detalledevolucion.setProducto(null);

		return detalledevolucion;
	}

	public List<Detalleinventario> getDetalleinventarios() {
		return this.detalleinventarios;
	}

	public void setDetalleinventarios(List<Detalleinventario> detalleinventarios) {
		this.detalleinventarios = detalleinventarios;
	}

	public Detalleinventario addDetalleinventario(Detalleinventario detalleinventario) {
		getDetalleinventarios().add(detalleinventario);
		detalleinventario.setProducto(this);

		return detalleinventario;
	}

	public Detalleinventario removeDetalleinventario(Detalleinventario detalleinventario) {
		getDetalleinventarios().remove(detalleinventario);
		detalleinventario.setProducto(null);

		return detalleinventario;
	}

	public List<Detalleinventariofisico> getDetalleinventariofisicos() {
		return this.detalleinventariofisicos;
	}

	public void setDetalleinventariofisicos(List<Detalleinventariofisico> detalleinventariofisicos) {
		this.detalleinventariofisicos = detalleinventariofisicos;
	}

	public Detalleinventariofisico addDetalleinventariofisico(Detalleinventariofisico detalleinventariofisico) {
		getDetalleinventariofisicos().add(detalleinventariofisico);
		detalleinventariofisico.setProducto(this);

		return detalleinventariofisico;
	}

	public Detalleinventariofisico removeDetalleinventariofisico(Detalleinventariofisico detalleinventariofisico) {
		getDetalleinventariofisicos().remove(detalleinventariofisico);
		detalleinventariofisico.setProducto(null);

		return detalleinventariofisico;
	}

	public List<Detalletraspaso> getDetalletraspasos() {
		return this.detalletraspasos;
	}

	public void setDetalletraspasos(List<Detalletraspaso> detalletraspasos) {
		this.detalletraspasos = detalletraspasos;
	}

	public Detalletraspaso addDetalletraspaso(Detalletraspaso detalletraspaso) {
		getDetalletraspasos().add(detalletraspaso);
		detalletraspaso.setProducto(this);

		return detalletraspaso;
	}

	public Detalletraspaso removeDetalletraspaso(Detalletraspaso detalletraspaso) {
		getDetalletraspasos().remove(detalletraspaso);
		detalletraspaso.setProducto(null);

		return detalletraspaso;
	}

	public List<Detalleventa> getDetalleventas() {
		return this.detalleventas;
	}

	public void setDetalleventas(List<Detalleventa> detalleventas) {
		this.detalleventas = detalleventas;
	}

	public Detalleventa addDetalleventa(Detalleventa detalleventa) {
		getDetalleventas().add(detalleventa);
		detalleventa.setProducto(this);

		return detalleventa;
	}

	public Detalleventa removeDetalleventa(Detalleventa detalleventa) {
		getDetalleventas().remove(detalleventa);
		detalleventa.setProducto(null);

		return detalleventa;
	}

	public List<Detallevista> getDetallevistas() {
		return this.detallevistas;
	}

	public void setDetallevistas(List<Detallevista> detallevistas) {
		this.detallevistas = detallevistas;
	}

	public Detallevista addDetallevista(Detallevista detallevista) {
		getDetallevistas().add(detallevista);
		detallevista.setProducto(this);

		return detallevista;
	}

	public Detallevista removeDetallevista(Detallevista detallevista) {
		getDetallevistas().remove(detallevista);
		detallevista.setProducto(null);

		return detallevista;
	}

	public List<Entradainventario> getEntradainventarios() {
		return this.entradainventarios;
	}

	public void setEntradainventarios(List<Entradainventario> entradainventarios) {
		this.entradainventarios = entradainventarios;
	}

	public Entradainventario addEntradainventario(Entradainventario entradainventario) {
		getEntradainventarios().add(entradainventario);
		entradainventario.setProducto(this);

		return entradainventario;
	}

	public Entradainventario removeEntradainventario(Entradainventario entradainventario) {
		getEntradainventarios().remove(entradainventario);
		entradainventario.setProducto(null);

		return entradainventario;
	}

	public List<Precioventa> getPrecioventas() {
		return this.precioventas;
	}

	public void setPrecioventas(List<Precioventa> precioventas) {
		this.precioventas = precioventas;
	}

	public Precioventa addPrecioventa(Precioventa precioventa) {
		getPrecioventas().add(precioventa);
		precioventa.setProducto(this);

		return precioventa;
	}

	public Precioventa removePrecioventa(Precioventa precioventa) {
		getPrecioventas().remove(precioventa);
		precioventa.setProducto(null);

		return precioventa;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public List<Categoriaproducto> getCategoriaproductos() {
		return this.categoriaproductos;
	}

	public void setCategoriaproductos(List<Categoriaproducto> categoriaproductos) {
		this.categoriaproductos = categoriaproductos;
	}	
	
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public boolean equals(Object obj) {
		if(this.idproducto == ((Producto) obj).idproducto) {
	        return true;
	    }else {
	        return false;
	    }
	}

	public String getCategoriasString(){
		String ret = "";
		for (Categoriaproducto item : getCategoriaproductos()) {
			if (!ret.equals("")) {
				ret = ret + ", ";
			}
			ret = ret + item.getNombre();
		}
		return ret;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	
}