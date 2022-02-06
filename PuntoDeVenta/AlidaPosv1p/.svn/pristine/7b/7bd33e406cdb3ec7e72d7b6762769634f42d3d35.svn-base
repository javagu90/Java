package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the traspaso database table.
 * 
 */
@Entity
@Table(name = "traspaso")
@NamedQueries({
    @NamedQuery(name = "Traspaso.findAll", query = "SELECT t FROM Traspaso t"),
    @NamedQuery(name = "Traspaso.findByIdtraspaso", query = "SELECT t FROM Traspaso t WHERE t.idtraspaso = :idtraspaso"),
    @NamedQuery(name = "Traspaso.findByTiendaorigen", query = "SELECT t FROM Traspaso t WHERE t.tienda1 = :tiendaorigen"),
    @NamedQuery(name = "Traspaso.findByTiendadestino", query = "SELECT t FROM Traspaso t WHERE t.tienda2 = :tiendadestino"),
    @NamedQuery(name = "Traspaso.findByComentarios", query = "SELECT t FROM Traspaso t WHERE t.comentarios = :comentarios")})
public class Traspaso implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String FIND_ALL               = "Traspaso.findAll";
	public static String FIND_BY_ID             = "Traspaso.findByIdtraspaso";
	public static String FIND_BY_TIENDA_ORIGEN  = "Traspaso.findByTiendaorigen";
	public static String FIND_BY_TIENDA_DESTINO = "Traspaso.findByTiendadestino";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idtraspaso;

	private String comentarios;

	//bi-directional many-to-one association to Detalletraspaso
	@OneToMany(mappedBy="traspaso", cascade = CascadeType.PERSIST)
	private List<Detalletraspaso> detalletraspasos;

	//bi-directional many-to-one association to Tienda
	@ManyToOne
	@JoinColumn(name="tiendaorigen")
	private Tienda tienda1;

	//bi-directional many-to-one association to Tienda
	@ManyToOne
	@JoinColumn(name="tiendadestino")
	private Tienda tienda2;
	
	private boolean activo;

	public Traspaso() {
	}

	public Integer getIdtraspaso() {
		return this.idtraspaso;
	}

	public void setIdtraspaso(Integer idtraspaso) {
		this.idtraspaso = idtraspaso;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public List<Detalletraspaso> getDetalletraspasos() {
		return this.detalletraspasos;
	}

	public void setDetalletraspasos(List<Detalletraspaso> detalletraspasos) {
		this.detalletraspasos = detalletraspasos;
	}

	public Detalletraspaso addDetalletraspaso(Detalletraspaso detalletraspaso) {
		getDetalletraspasos().add(detalletraspaso);
		detalletraspaso.setTraspaso(this);

		return detalletraspaso;
	}

	public Detalletraspaso removeDetalletraspaso(Detalletraspaso detalletraspaso) {
		getDetalletraspasos().remove(detalletraspaso);
		detalletraspaso.setTraspaso(null);

		return detalletraspaso;
	}

	public Tienda getTienda1() {
		return this.tienda1;
	}

	public void setTienda1(Tienda tienda1) {
		this.tienda1 = tienda1;
	}

	public Tienda getTienda2() {
		return this.tienda2;
	}

	public void setTienda2(Tienda tienda2) {
		this.tienda2 = tienda2;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}