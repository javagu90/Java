package com.alidasoftware.pos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "perfil")
public class Perfil implements Serializable{

	/**
	 * Primera Version.
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="idperfil")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPerfil;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	private boolean activo;
	
	//bi-directional many-to-many association to Direccion
	@ManyToMany
	@JoinTable(
		name="perfilmodulo"
		, joinColumns={
			@JoinColumn(name="idperfil", referencedColumnName="idperfil")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idmodulo", referencedColumnName="idmodulo")
			}
		)
	private List<Modulo> modulos;
	
	public Perfil(){
		
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public List<Modulo> getModulos() {
		if(this.modulos==null){
			this.modulos = new ArrayList<Modulo>();
			return modulos;
		}
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
	
}
