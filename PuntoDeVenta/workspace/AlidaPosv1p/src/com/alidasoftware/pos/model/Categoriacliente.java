package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the categoriacliente database table.
 * 
 */
@Entity
@Table(name = "categoriacliente")
@NamedQueries({
    @NamedQuery(name = "Categoriacliente.findAll", query = "SELECT c FROM Categoriacliente c"),
    @NamedQuery(name = "Categoriacliente.findByIdcategoriacliente", query = "SELECT c FROM Categoriacliente c WHERE c.idcategoriacliente = :idcategoriacliente"),
    @NamedQuery(name = "Categoriacliente.findByNombre", query = "SELECT c FROM Categoriacliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Categoriacliente.listByNombre", query = "SELECT c FROM Categoriacliente c WHERE UPPER(c.nombre) LIKE UPPER(:nombre)"),
    @NamedQuery(name = "Categoriacliente.findByDescripcion", query = "SELECT c FROM Categoriacliente c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Categoriacliente.findByDescuento", query = "SELECT c FROM Categoriacliente c WHERE c.descuento = :descuento"),
    @NamedQuery(name = "Categoriacliente.findByComentarios", query = "SELECT c FROM Categoriacliente c WHERE c.comentarios = :comentarios")})
public class Categoriacliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static String FIND_ALL            = "Categoriacliente.findAll";
	public static String FIND_BY_ID          = "Categoriacliente.findByIdcategoriacliente";
	public static String FIND_BY_NOMBRE      = "Categoriacliente.findByNombre";
	public static String FIND_BY_DESCRIPCION = "Categoriacliente.findByDescripcion";
	public static String FIND_BY_DESCUENTO   = "Categoriacliente.findByDescuento";
	public static String LIST_BY_NOMBRE      = "Categoriacliente.listByNombre";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcategoriacliente;

	private String comentarios;

	private String descripcion;

	private float descuento;

	private String nombre;
	
	private boolean activo;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="categoriacliente")
	private List<Cliente> clientes;

	public Categoriacliente() {
	}

	public Integer getIdcategoriacliente() {
		return this.idcategoriacliente;
	}

	public void setIdcategoriacliente(Integer idcategoriacliente) {
		this.idcategoriacliente = idcategoriacliente;
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

	public float getDescuento() {
		return this.descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setCategoriacliente(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setCategoriacliente(null);

		return cliente;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}