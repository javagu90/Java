package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the detalleinventariofisico database table.
 * 
 */
@Entity
@Table(name = "detalleinventariofisico")
@NamedQueries({
    @NamedQuery(name = "Detalleinventariofisico.findAll", query = "SELECT d FROM Detalleinventariofisico d"),
    @NamedQuery(name = "Detalleinventariofisico.findByIddetalleinventariofisico", query = "SELECT d FROM Detalleinventariofisico d WHERE d.iddetalleinventariofisico = :iddetalleinventariofisico"),
    @NamedQuery(name = "Detalleinventariofisico.findByIdinventariofisico", query = "SELECT d FROM Detalleinventariofisico d WHERE d.inventariofisico = :idinventariofisico"),
    @NamedQuery(name = "Detalleinventariofisico.findByIdproducto", query = "SELECT d FROM Detalleinventariofisico d WHERE d.producto = :idproducto"),
    @NamedQuery(name = "Detalleinventariofisico.findByExistenciafisica", query = "SELECT d FROM Detalleinventariofisico d WHERE d.existenciafisica = :existenciafisica"),
    @NamedQuery(name = "Detalleinventariofisico.findByExistenciasistema", query = "SELECT d FROM Detalleinventariofisico d WHERE d.existenciasistema = :existenciasistema")})
public class Detalleinventariofisico implements Serializable {
	private static final long serialVersionUID = 1L;
		
	public static String FIND_ALL                     = "Detalleinventariofisico.findAll";
	public static String FIND_BY_ID                   = "Detalleinventariofisico.findByIddetalleinventariofisico";
	public static String FIND_BY_ID_INVENTARIO_FISICO = "Detalleinventariofisico.findByIdinventariofisico";
	public static String FIND_BY_ID_PRODUCTO          = "Detalleinventariofisico.findByIdproducto";
	public static String FIND_BY_EXISTENCIA_FISICA    = "Detalleinventariofisico.findByExistenciafisica";
	public static String FIND_BY_EXISTENCIA_SISTEMA   = "Detalleinventariofisico.findByExistenciasistema";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iddetalleinventariofisico;

	private float existenciafisica;

	private float existenciasistema;

	//bi-directional many-to-one association to Inventariofisico
	@ManyToOne
	@JoinColumn(name="idinventariofisico")
	private Inventariofisico inventariofisico;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="idproducto")
	private Producto producto;

	public Detalleinventariofisico() {
	}

	public Integer getIddetalleinventariofisico() {
		return this.iddetalleinventariofisico;
	}

	public void setIddetalleinventariofisico(Integer iddetalleinventariofisico) {
		this.iddetalleinventariofisico = iddetalleinventariofisico;
	}

	public float getExistenciafisica() {
		return this.existenciafisica;
	}

	public void setExistenciafisica(float existenciafisica) {
		this.existenciafisica = existenciafisica;
	}

	public float getExistenciasistema() {
		return this.existenciasistema;
	}

	public void setExistenciasistema(float existenciasistema) {
		this.existenciasistema = existenciasistema;
	}

	public Inventariofisico getInventariofisico() {
		return this.inventariofisico;
	}

	public void setInventariofisico(Inventariofisico inventariofisico) {
		this.inventariofisico = inventariofisico;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}