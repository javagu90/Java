package com.alidasoftware.pos.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the configuracionfactura database table.
 * 
 */
@Entity
@NamedQuery(name="Configuracionfactura.findAll", query="SELECT c FROM Configuracionfactura c")
public class Configuracionfactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idconfiguracionfactura;

	private String calle;

	private byte[] cfdi;

	private String codigopostal;

	private String colonia;

	private String correoelectronico;

	@JoinColumn(name="idmunicipio")
	private Municipio municipio;

	private byte[] key;

	private byte[] logo;

	private String lugarexpedicion;

	private String numext;

	private String numint;

	private String razonsocial;

	private String rfc;

	private String telefono;
	
	private String regimenFiscal;
	
	private String passwordKey;

	public Configuracionfactura() {
	}

	public Integer getIdconfiguracionfactura() {
		return this.idconfiguracionfactura;
	}

	public void setIdconfiguracionfactura(Integer idconfiguracionfactura) {
		this.idconfiguracionfactura = idconfiguracionfactura;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public byte[] getCfdi() {
		return this.cfdi;
	}

	public void setCfdi(byte[] cfdi) {
		this.cfdi = cfdi;
	}

	public String getCodigopostal() {
		return this.codigopostal;
	}

	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}

	public String getColonia() {
		return this.colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCorreoelectronico() {
		return this.correoelectronico;
	}

	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public byte[] getKey() {
		return this.key;
	}

	public void setKey(byte[] key) {
		this.key = key;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getLugarexpedicion() {
		return this.lugarexpedicion;
	}

	public void setLugarexpedicion(String lugarexpedicion) {
		this.lugarexpedicion = lugarexpedicion;
	}

	public String getNumext() {
		return this.numext;
	}

	public void setNumext(String numext) {
		this.numext = numext;
	}

	public String getNumint() {
		return this.numint;
	}

	public void setNumint(String numint) {
		this.numint = numint;
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

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}

	public String getPasswordKey() {
		return passwordKey;
	}

	public void setPasswordKey(String passwordKey) {
		this.passwordKey = passwordKey;
	}

}