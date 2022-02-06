package com.alidasoftware.pos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "modulo")
public class Modulo implements Serializable{

	/**
	 * Primera Version.
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="idmodulo")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idModulo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="categoria")
	private String categoria;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="path")
	private String path;
	
	private boolean activo;
	
	//bi-directional many-to-many association to Contacto
	@ManyToMany(mappedBy="modulos", fetch = FetchType.EAGER)
	List<Perfil> perfiles;
	
	public Modulo(){
		
	}
	
	public Modulo(Integer idModulo, String nombre, String descripcion, String categoria, String path, boolean activo){
		this.idModulo = idModulo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.path = path;
		this.activo = activo;
	}

	public Integer getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<Perfil> getPerfiles() {
		if(this.perfiles==null){
			perfiles = new ArrayList<Perfil>();
		}
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

}
