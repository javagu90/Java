/*
 * Cliente.java
 *
 * Created on 23 de septiembre de 2008, 05:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package wsc.entidades;

import java.io.Serializable;
import java.util.Vector;



public class Cliente implements Serializable {
    private long clienteId;
    private String nombre;
    private String apellidos;
    private String contacto;
    private String calle;
    private String numeroInt;
    private String numeroExt;
    private String colonia;
    private String codigoPostal;
    private String delegMun;
    private String ciudad;
    private String estado;
    private String telefonoCasa;
    private String telefonoOficina;
    private String fechaNac;
    private String sexo;
    private String estadoCivil;
    private String compania;
    private String puesto;
    private String email;
    private long saldoPuntos;
    private long tipoClienteId;
    private String fechaUltimaAcumulacion;
    private String fechaUltimoCanje;
    private String fechaDesde;
    private String fechaHasta;
    private long creadoPor;
    private String fechaCreacion;
    private long ultimaActualizacionPor;
    private String ultimaFechaActualizacion;
    private String replicacionEstado;
    private long  replicacionIntentos;
    private String replicacionOrigen;
    private String contrasenia;
    private String telefonoCelular;
    private String noPasaporte;
    private String pais;
    private String telefonoFax;
    private long clienteRelacionadoId;
    private String adicional1;
    private String adicional2;
    private String adicional3;
    private String adicional4;
    private String adicional5;

    /** Creates a new instance of Cliente */
    public Cliente() {
    }

     public Cliente(Vector vc) {
        System.out.println("Vector par aarmar cliente: "+vc);
         this.setClienteId(Long.valueOf(vc.get(0).toString()) );
        if(vc.get(1)!=null)
            this.setnombre(vc.get(1).toString());
        if(vc.get(2)!=null)
            this.setApellidos(vc.get(2).toString());
        if(vc.get(3)!=null)
            this.setContacto(vc.get(3).toString());
        if(vc.get(4)!=null)
            this.setCalle(vc.get(4).toString());
        if(vc.get(5)!=null)
            this.setNumeroInt(vc.get(5).toString());
        if(vc.get(6)!=null)
            this.setNumeroExt(vc.get(6).toString());
        if(vc.get(7)!=null)
            this.setColonia(vc.get(7).toString());
        if(vc.get(8)!=null)
            this.setCodigoPostal(vc.get(8).toString());
        if(vc.get(9)!=null)
            this.setDelegMun(vc.get(9).toString());
        if(vc.get(10)!=null)
            this.setCiudad(vc.get(10).toString());
        if(vc.get(11)!=null)
            this.setEstado(vc.get(11).toString());
        if(vc.get(12)!=null)
            this.setTelefonoCasa(vc.get(12).toString());
        if(vc.get(13)!=null)
            this.setTelefonoOficina(vc.get(13).toString());
        if(vc.get(14)!=null)
            this.setFechaNac(vc.get(14).toString());
        if(vc.get(15)!=null)
            this.setSexo(vc.get(15).toString());
        if(vc.get(16)!=null)
            this.setEstadoCivil(vc.get(16).toString());
        if(vc.get(17)!=null)
            this.setCompania(vc.get(17).toString());
        if(vc.get(18)!=null)
            this.setPuesto(vc.get(18).toString());
        if(vc.get(19)!=null)
            this.setEmail(vc.get(19).toString());
        if(vc.get(20)!=null)
            this.setSaldoPuntos(Long.valueOf(vc.get(20).toString()));
        if(vc.get(21)!=null)
            this.setFechaUltimaAcumulacion(vc.get(21).toString());
        if(vc.get(22)!=null)
            this.setFechaCreacion(vc.get(22).toString());
        if(vc.get(23)!=null)
            this.setTipoClienteId(Long.valueOf(vc.get(23).toString()));
        if(vc.get(24)!=null)
            this.setFechaDesde(vc.get(24).toString());
        if(vc.get(25)!=null)
            this.setFechaHasta(vc.get(25).toString());
        if(vc.get(26)!=null)
            this.setCreadoPor(Long.valueOf(vc.get(26).toString()));
        if(vc.get(27)!=null)
            this.setFechaCreacion(vc.get(27).toString());
        if(vc.get(28)!=null)
            this.setUltimaActualizacionPor(Long.valueOf(vc.get(28).toString()));
        if(vc.get(29)!=null)
            this.setUltimaFechaActualizacion(vc.get(29).toString());
        if(vc.get(30)!=null)
            this.setReplicacionEstado(vc.get(30).toString());
        if(vc.get(31)!=null)
            this.setReplicacionIntentos(Long.valueOf(vc.get(31).toString()));
        if(vc.get(32)!=null)
            this.setReplicacionOrigen(vc.get(32).toString());
        this.setContrasenia("");//vc.get(33).toString());
        if(vc.get(34)!=null)
            this.setTelefonoCelular(vc.get(34).toString());
        if(vc.get(35)!=null)
            this.setNoPasaporte(vc.get(35).toString());
        if(vc.get(36)!=null)
            this.setPais(vc.get(36).toString());
        if(vc.get(37)!=null)
            this.setTelefonoFax(vc.get(37).toString());
        if(vc.get(39)!=null)
            this.setClienteRelacionadoId(Long.valueOf(vc.get(39).toString()));
        if(vc.get(40)!=null)
            this.setAdicional1(vc.get(40).toString());
        if(vc.get(41)!=null)
            this.setAdicional2(vc.get(41).toString());
        if(vc.get(42)!=null)
            this.setAdicional3(vc.get(42).toString());
        if(vc.get(43)!=null)
            this.setAdicional4(vc.get(43).toString());
        if(vc.get(44)!=null)
            this.setAdicional5(vc.get(44).toString());
    }

    /**
     * Gets the clienteId of this Cliente.
     * @return the clienteId
     */
    public long getClienteId() {
        return this.clienteId;
    }

    /**
     * Sets the clienteId of this Cliente to the specified value.
     * @param clienteId the new clienteId
     */
    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Gets the nombre of this Cliente.
     * @return the nombre
     */
    public String getnombre() {
        return this.nombre;
    }

    /**
     * Sets the nombre of this Cliente to the specified value.
     * @param nombre the new nombre
     */
    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the apellidos of this Cliente.
     * @return the apellidos
     */
    public String getApellidos() {
        return this.apellidos;
    }

    /**
     * Sets the apellidos of this Cliente to the specified value.
     * @param apellidos the new apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Gets the contacto of this Cliente.
     * @return the contacto
     */
    public String getContacto() {
        return this.contacto;
    }

    /**
     * Sets the contacto of this Cliente to the specified value.
     * @param contacto the new contacto
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    /**
     * Gets the calle of this Cliente.
     * @return the calle
     */
    public String getCalle() {
        return this.calle;
    }

    /**
     * Sets the calle of this Cliente to the specified value.
     * @param calle the new calle
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Gets the numeroInt of this Cliente.
     * @return the numeroInt
     */
    public String getNumeroInt() {
        return this.numeroInt;
    }

    /**
     * Sets the numeroInt of this Cliente to the specified value.
     * @param numeroInt the new numeroInt
     */
    public void setNumeroInt(String numeroInt) {
        this.numeroInt = numeroInt;
    }

    /**
     * Gets the numeroExt of this Cliente.
     * @return the numeroExt
     */
    public String getNumeroExt() {
        return this.numeroExt;
    }

    /**
     * Sets the numeroExt of this Cliente to the specified value.
     * @param numeroExt the new numeroExt
     */
    public void setNumeroExt(String numeroExt) {
        this.numeroExt = numeroExt;
    }

    /**
     * Gets the colonia of this Cliente.
     * @return the colonia
     */
    public String getColonia() {
        return this.colonia;
    }

    /**
     * Sets the colonia of this Cliente to the specified value.
     * @param colonia the new colonia
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Gets the codigoPostal of this Cliente.
     * @return the codigoPostal
     */
    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    /**
     * Sets the codigoPostal of this Cliente to the specified value.
     * @param codigoPostal the new codigoPostal
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Gets the delegMun of this Cliente.
     * @return the delegMun
     */
    public String getDelegMun() {
        return this.delegMun;
    }

    /**
     * Sets the delegMun of this Cliente to the specified value.
     * @param delegMun the new delegMun
     */
    public void setDelegMun(String delegMun) {
        this.delegMun = delegMun;
    }

    /**
     * Gets the ciudad of this Cliente.
     * @return the ciudad
     */
    public String getCiudad() {
        return this.ciudad;
    }

    /**
     * Sets the ciudad of this Cliente to the specified value.
     * @param ciudad the new ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Gets the estado of this Cliente.
     * @return the estado
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     * Sets the estado of this Cliente to the specified value.
     * @param estado the new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets the telefonoCasa of this Cliente.
     * @return the telefonoCasa
     */
    public String getTelefonoCasa() {
        return this.telefonoCasa;
    }

    /**
     * Sets the telefonoCasa of this Cliente to the specified value.
     * @param telefonoCasa the new telefonoCasa
     */
    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    /**
     * Gets the telefonoOficina of this Cliente.
     * @return the telefonoOficina
     */
    public String getTelefonoOficina() {
        return this.telefonoOficina;
    }

    /**
     * Sets the telefonoOficina of this Cliente to the specified value.
     * @param telefonoOficina the new telefonoOficina
     */
    public void setTelefonoOficina(String telefonoOficina) {
        this.telefonoOficina = telefonoOficina;
    }

    /**
     * Gets the fechaNac of this Cliente.
     * @return the fechaNac
     */
    public String getFechaNac() {
        return this.fechaNac;
    }

    /**
     * Sets the fechaNac of this Cliente to the specified value.
     * @param fechaNac the new fechaNac
     */
    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    /**
     * Gets the sexo of this Cliente.
     * @return the sexo
     */
    public String getSexo() {
        return this.sexo;
    }

    /**
     * Sets the sexo of this Cliente to the specified value.
     * @param sexo the new sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Gets the estadoCivil of this Cliente.
     * @return the estadoCivil
     */
    public String getEstadoCivil() {
        return this.estadoCivil;
    }

    /**
     * Sets the estadoCivil of this Cliente to the specified value.
     * @param estadoCivil the new estadoCivil
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * Gets the compania of this Cliente.
     * @return the compania
     */
    public String getCompania() {
        return this.compania;
    }

    /**
     * Sets the compania of this Cliente to the specified value.
     * @param compania the new compania
     */
    public void setCompania(String compania) {
        this.compania = compania;
    }

    /**
     * Gets the puesto of this Cliente.
     * @return the puesto
     */
    public String getPuesto() {
        return this.puesto;
    }

    /**
     * Sets the puesto of this Cliente to the specified value.
     * @param puesto the new puesto
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * Gets the email of this Cliente.
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email of this Cliente to the specified value.
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the saldoPuntos of this Cliente.
     * @return the saldoPuntos
     */
    public long getSaldoPuntos() {
        return this.saldoPuntos;
    }

    /**
     * Sets the saldoPuntos of this Cliente to the specified value.
     * @param saldoPuntos the new saldoPuntos
     */
    public void setSaldoPuntos(long saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    /**
     * Gets the fechaUltimaAcumulacion of this Cliente.
     * @return the fechaUltimaAcumulacion
     */
    public String getFechaUltimaAcumulacion() {
        return this.fechaUltimaAcumulacion;
    }

    /**
     * Sets the fechaUltimaAcumulacion of this Cliente to the specified value.
     * @param fechaUltimaAcumulacion the new fechaUltimaAcumulacion
     */
    public void setFechaUltimaAcumulacion(String fechaUltimaAcumulacion) {
        this.fechaUltimaAcumulacion = fechaUltimaAcumulacion;
    }

    /**
     * Gets the fechaUltimoCanje of this Cliente.
     * @return the fechaUltimoCanje
     */
    public String getFechaUltimoCanje() {
        return this.fechaUltimoCanje;
    }

    /**
     * Sets the fechaUltimoCanje of this Cliente to the specified value.
     * @param fechaUltimoCanje the new fechaUltimoCanje
     */
    public void setFechaUltimoCanje(String fechaUltimoCanje) {
        this.fechaUltimoCanje = fechaUltimoCanje;
    }

    /**
     * Gets the fechaDesde of this Cliente.
     * @return the fechaDesde
     */
    public String getFechaDesde() {
        return this.fechaDesde;
    }

    /**
     * Sets the fechaDesde of this Cliente to the specified value.
     * @param fechaDesde the new fechaDesde
     */
    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Gets the fechaHasta of this Cliente.
     * @return the fechaHasta
     */
    public String getFechaHasta() {
        return this.fechaHasta;
    }

    /**
     * Sets the fechaHasta of this Cliente to the specified value.
     * @param fechaHasta the new fechaHasta
     */
    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Gets the creadoPor of this Cliente.
     * @return the creadoPor
     */
    public long getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this Cliente to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(long creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this Cliente.
     * @return the fechaCreacion
     */
    public String getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this Cliente to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this Cliente.
     * @return the ultimaActualizacionPor
     */
    public long getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this Cliente to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(long ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this Cliente.
     * @return the ultimaFechaActualizacion
     */
    public String getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this Cliente to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(String ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this Cliente.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this Cliente to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this Cliente.
     * @return the replicacionIntentos
     */
    public long getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this Cliente to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(long replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this Cliente.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this Cliente to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Gets the contrasenia of this Cliente.
     * @return the contrasenia
     */
    public String getContrasenia() {
        return this.contrasenia;
    }

    /**
     * Sets the contrasenia of this Cliente to the specified value.
     * @param contrasenia the new contrasenia
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Gets the telefonoCelular of this Cliente.
     * @return the telefonoCelular
     */
    public String getTelefonoCelular() {
        return this.telefonoCelular;
    }

    /**
     * Sets the telefonoCelular of this Cliente to the specified value.
     * @param telefonoCelular the new telefonoCelular
     */
    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    /**
     * Gets the noPasaporte of this Cliente.
     * @return the noPasaporte
     */
    public String getNoPasaporte() {
        return this.noPasaporte;
    }

    /**
     * Sets the noPasaporte of this Cliente to the specified value.
     * @param noPasaporte the new noPasaporte
     */
    public void setNoPasaporte(String noPasaporte) {
        this.noPasaporte = noPasaporte;
    }

    /**
     * Gets the pais of this Cliente.
     * @return the pais
     */
    public String getPais() {
        return this.pais;
    }

    /**
     * Sets the pais of this Cliente to the specified value.
     * @param pais the new pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Gets the telefonoFax of this Cliente.
     * @return the telefonoFax
     */
    public String getTelefonoFax() {
        return this.telefonoFax;
    }

    /**
     * Sets the telefonoFax of this Cliente to the specified value.
     * @param telefonoFax the new telefonoFax
     */
    public void setTelefonoFax(String telefonoFax) {
        this.telefonoFax = telefonoFax;
    }

    /**
     * Gets the replicacionOrigen of this Cliente.
     * @return the replicacionOrigen
     */    public long getTipoClienteId() {
        return tipoClienteId;
    }
    /**
     * Sets the pais of this Cliente to the specified value.
     * @param pais the new pais
     */
    public void setTipoClienteId(long tipoClienteId) {
        this.tipoClienteId = tipoClienteId;
    }

    
    /**
     * Gets the replicacionOrigen of this Cliente.
     * @return the replicacionOrigen
     */
    public long getClienteRelacionadoId() {
        return clienteRelacionadoId;
    }
    /**
     * Sets the pais of this Cliente to the specified value.
     * @param pais the new pais
     */
    public void setClienteRelacionadoId(long clienteRelacionadoId) {
        this.clienteRelacionadoId = clienteRelacionadoId;
    }
    /**
     * Gets the replicacionOrigen of this Cliente.
     * @return the replicacionOrigen
     */    
    public String getAdicional1() {
        return this.adicional1;
    }
    /**
     * Sets the pais of this Cliente to the specified value.
     * @param pais the new pais
     */
    public void setAdicional1(String adicional) {
        this.adicional1 = adicional;
    }
    
    /**
     * Gets the replicacionOrigen of this Cliente.
     * @return the replicacionOrigen
     */
    public String getAdicional2() {
        return this.adicional2;
    }
    /**
     * Sets the pais of this Cliente to the specified value.
     * @param pais the new pais
     */
    public void setAdicional2(String adicional) {
        this.adicional2 = adicional;
    }
    
      /**
     * Gets the replicacionOrigen of this Cliente.
     * @return the replicacionOrigen
     */
    public String getAdicional3() {
        return this.adicional3;
    }
    /**
     * Sets the pais of this Cliente to the specified value.
     * @param pais the new pais
     */
    public void setAdicional3(String adicional) {
        this.adicional3 = adicional;
    }
    
     /**
     * Gets the replicacionOrigen of this Cliente.
     * @return the replicacionOrigen
     */
    public String getAdicional4() {
        return this.adicional4;
    }
    /**
     * Sets the pais of this Cliente to the specified value.
     * @param pais the new pais
     */
    public void setAdicional4(String adicional) {
        this.adicional4 = adicional;
    }

    /**
     * Gets the replicacionOrigen of this Cliente.
     * @return the replicacionOrigen
     */    
    public String getAdicional5() {
        return this.adicional5;
    }
    /**
     * Sets the pais of this Cliente to the specified value.
     * @param pais the new pais
     */
    public void setAdicional5(String adicional) {
        this.adicional5 = adicional;
    }
    
    /**
     * Gets the replicacionOrigen of this Cliente.
     * @return the replicacionOrigen
     */    
    public String getInsertar(long userId, String psw){
        String inserta = "";
        try {
            inserta = "insert into tms_clientes_tbl values ( TMS_CLIENTES_SEQ.NEXTVAL,";
                    inserta = inserta + (this.getnombre()==null ?"null," :"'" + this.getnombre() + "'," );
                    inserta = inserta + (this.getApellidos()==null ?"null," :"'"+this.getApellidos() + "',");
                    inserta = inserta + (this.getContacto()==null ?"null," :"'" + this.getContacto() + "',");
                    inserta = inserta + (this.getCalle()==null ?"null," :"'" + this.getCalle() + "',");
                    inserta = inserta + (this.getNumeroInt()==null ?"null," :"'" +this.getNumeroInt() + "',");
                    inserta = inserta + (this.getNumeroExt()==null ?"null," :"'" + this.getNumeroExt() + "',");
                    inserta = inserta + (this.getColonia()==null ?"null," :"'" + this.getColonia() + "',");
                    inserta = inserta + (this.getCodigoPostal()==null ?"null," :"'" + this.getCodigoPostal() + "',");
                    inserta = inserta + (this.getDelegMun()==null ?"null," :"'" + this.getDelegMun() + "',");
                    inserta = inserta + (this.getCiudad()==null ?"null," :"'" + this.getCiudad() + "',");
                    inserta = inserta + (this.getEstado()==null ?"null," :"'" + this.getEstado() + "',");
                    inserta = inserta + (this.getTelefonoCasa()==null ?"null," :"'" + this.getTelefonoCasa() + "',");
                    inserta = inserta + (this.getTelefonoOficina()==null ?"null," :"'" + this.getTelefonoOficina() + "',");
                    inserta = inserta + (this.getFechaNac()==null ?"null," :"to_date('"+ this.getFechaNac() + "','DD/MM/RRRR'),");
                    inserta = inserta + (this.getSexo()==null ?"null," :"'" + this.getSexo() + "',");
                    inserta = inserta + (this.getEstadoCivil()==null ?"null," :"'" + this.getEstadoCivil() + "',");
                    inserta = inserta + (this.getCompania()==null ?"null," :"'" + this.getCompania() + "',");
                    inserta = inserta + (this.getPuesto()==null ?"null," :"'" + this.getPuesto() + "',");
                    inserta = inserta + (this.getEmail()==null ?"null," :"'" + this.getEmail() + "'")+",null,null,null,"+(this.getTipoClienteId() < 0 ?"null," :this.getTipoClienteId())+", sysdate,null," + userId + ",sysdate," + userId + ",sysdate,'P',0,'PCENTRAL','" + psw + "',";
                    inserta = inserta + (this.getTelefonoCelular()==null ?"null," :"'" + this.getTelefonoCelular() + "',");
                    inserta = inserta + (this.getNoPasaporte()==null ?"null," :"'" + this.getNoPasaporte() + "',");
                    inserta = inserta + (this.getPais()==null ?"null," :"'" + this.getPais() + "',");
                    inserta = inserta + (this.getTelefonoFax()==null ?"null)" :"'" + this.getTelefonoFax() + "'");
                    inserta = inserta +  ",'V',";
                    inserta = inserta + (this.getClienteRelacionadoId() < 0 ?"null," :"'" + this.getClienteRelacionadoId() + "',");
                    inserta = inserta + (this.getAdicional1() == null ?"null," :"'" + this.getAdicional1() + "',");
                    inserta = inserta + (this.getAdicional2() == null ?"null," :"'" + this.getAdicional2() + "',");
                    inserta = inserta + (this.getAdicional3() == null ?"null," :"'" + this.getAdicional3() + "',");
                    inserta = inserta + (this.getAdicional4() == null ?"null," :"'" + this.getAdicional4() + "',");
                    inserta = inserta + (this.getAdicional5() == null ?"null " :"'" + this.getAdicional5())+ " )";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return inserta;
    }
    
    public String getModificar(long userId, String psw){
        String actualiza = "";
        try {
            actualiza = "update tms_clientes_tbl set ";
                   actualiza = actualiza + "NOMBRE = " + (this.getnombre() == null ?"null," :"'"+this.getnombre() + "', " );
                   actualiza = actualiza + "APELLIDOS = "+(this.getApellidos() == null ?"null," :"'" + this.getApellidos() + "'," );
                   actualiza = actualiza + "CONTACTO= "+ (this.getContacto() == null ?"null," :"'" + this.getContacto() + "',");
                   actualiza = actualiza + "CALLE="+ (this.getCalle() == null ?"null," :"'" + this.getCalle() + "',");
                   actualiza = actualiza + "NUMERO_INT="+ (this.getNumeroInt() == null ?"null," :"'" + this.getNumeroInt() + "'," );
                   actualiza = actualiza + "NUMERO_EXT="+ (this.getNumeroExt() == null ?"null," :"'" + this.getNumeroExt() + "'," );
                   actualiza = actualiza + "COLONIA="+ (this.getColonia() == null ?"null," :"'" + this.getColonia() + "',");
                   actualiza = actualiza + "CODIGO_POSTAL="+ (this.getCodigoPostal() == null ?"null," :"'" + this.getCodigoPostal() + "',");
                   actualiza = actualiza + "DELEG_MUN="+ (this.getDelegMun() == null ?"null," :"'" + this.getDelegMun() + "',");
                   actualiza = actualiza + "CIUDAD="+ (this.getCiudad() == null ?"null," :"'" + this.getCiudad() + "',");
                   actualiza = actualiza + "ESTADO="+ (this.getEstado() == null ?"null," :"'" + this.getEstado() + "',");
                   actualiza = actualiza + "TELEFONO_CASA="+ (this.getTelefonoCasa() == null ?"null," :"'" + this.getTelefonoCasa() + "',");
                   actualiza = actualiza + "TELEFONO_OFICINA="+ (this.getTelefonoOficina() == null ?"null," :"'" + this.getTelefonoOficina() + "',");
                   actualiza = actualiza + "FECHA_NAC= "+ (this.getFechaNac() == null ?"null," :"to_date('" + this.getFechaNac() + "','DD/MM/RRRR'),");
                   actualiza = actualiza + "SEXO="+ (this.getSexo() == null ?"null," :"'" + this.getSexo() + "',");
                   actualiza = actualiza + "ESTADO_CIVIL= "+ (this.getEstadoCivil() == null ?"null," :"'" + this.getEstadoCivil() + "',");
                   actualiza = actualiza + "COMPANIA= "+ (this.getCompania() == null ?"null," :"'" + this.getCompania() + "',");
                   actualiza = actualiza + "PUESTO= "+ (this.getPuesto() == null ?"null," :"'" + this.getPuesto() + "',");
                   actualiza = actualiza + "EMAIL="+ (this.getEmail() == null ?"null," :"'" + this.getEmail() + "'")+",ULTIMA_ACTUALIZACION_POR =" + userId + "," + "ULTIMA_FECHA_ACTUALIZACION=sysdate," + "CONTRASENIA='" + psw + "'," ;
                   actualiza = actualiza + "TELEFONO_CELULAR="+ (this.getTelefonoCelular() == null ?"null," :"'" + this.getTelefonoCelular() + "',");
                   actualiza = actualiza + "NO_PASAPORTE="+ (this.getNoPasaporte() == null ?"null," :"'" + this.getNoPasaporte() + "',");
                   actualiza = actualiza + "PAIS="+ (this.getPais() == null ?"null," :"'" + this.getPais() + "',");
                   actualiza = actualiza + "TIPO_CLIENTE_ID="+ (this.getTipoClienteId() < 0 ?"null," :"'" + this.getTipoClienteId() + "',");
                   actualiza = actualiza + "FECHA_HASTA= "+ (this.getFechaHasta() == null ?"null," :"to_date('" + this.getFechaHasta() + "','DD/MM/RRRR'),");                   
                   actualiza = actualiza + "CLIENTE_RELACIONADO_ID="+ (this.getClienteRelacionadoId() < 0 ?"null," :"'" + this.getClienteRelacionadoId() + "',");
                   actualiza = actualiza + "ADICIONAL1="+ (this.getAdicional1() == null ?"null," :"'" + this.getAdicional1() + "',");
                   actualiza = actualiza + "ADICIONAL2="+ (this.getAdicional2() == null ?"null," :"'" + this.getAdicional2() + "',");
                   actualiza = actualiza + "ADICIONAL3="+ (this.getAdicional3() == null ?"null," :"'" + this.getAdicional3() + "',");
                   actualiza = actualiza + "ADICIONAL4="+ (this.getAdicional4() == null ?"null," :"'" + this.getAdicional4() + "',");
                   actualiza = actualiza + "ADICIONAL5="+ (this.getAdicional5() == null ?"null," :"'" + this.getAdicional5() + "',");
                   actualiza = actualiza + "TELEFONO_FAX="+ (this.getTelefonoFax() == null ?"null" :"'" + this.getTelefonoFax() + "' "); 
                   actualiza = actualiza + "  where CLIENTE_ID = "+this.getClienteId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return actualiza;
    }    
}
