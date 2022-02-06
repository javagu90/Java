/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Clase que define la entidad cliente
 * @author jildefonso
 */
public class Cliente {

    //<editor-fold defaultstate="collapsed" desc=" Atributos ">
    private String id = null;
    private String nombre = null;
    private String paterno = null;
    private String materno = null;
    private String email = null;
    private String contrasenia = null;
    private String fechaNacimiento = null;
    private String sexo = null;
    private String estadoCivil = null;
    private String ocupacion = null;//Adicional1
    private String calle = null;
    private String numeroExterior = null;
    private String numeroInterior = null;
    private String colonia = null;
    private String municipio = null;
    private String ciudad = null;
    private String pais = null;
    private String estado = null;
    private String cp = null;
    private String telefonoContacto = null;
    private String tipoIdentificacion = null;//Adicional2
    private String numeroIdentificacion = null;//Adicional3
    private String puesto = null;
    private String tipoCliente = null;//tipo_cliente_id
    private String clienteRelacionadoId = null;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Propiedades ">
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the paterno
     */
    public String getPaterno() {
        return paterno;
    }

    /**
     * @param paterno the paterno to set
     */
    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    /**
     * @return the materno
     */
    public String getMaterno() {
        return materno;
    }

    /**
     * @param materno the materno to set
     */
    public void setMaterno(String materno) {
        this.materno = materno;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * @return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the estadoCivil
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * @param estadoCivil the estadoCivil to set
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * @return the ocupacion
     */
    public String getOcupacion() {
        return ocupacion;
    }

    /**
     * @param ocupacion the ocupacion to set
     */
    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the numeroExterior
     */
    public String getNumeroExterior() {
        return numeroExterior;
    }

    /**
     * @param numeroExterior the numeroExterior to set
     */
    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    /**
     * @return the numeroInterior
     */
    public String getNumeroInterior() {
        return numeroInterior;
    }

    /**
     * @param numeroInterior the numeroInterior to set
     */
    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    /**
     * @return the colonia
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * @param colonia the colonia to set
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * @return the telefonoContacto
     */
    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    /**
     * @param telefonoContacto the telefonoContacto to set
     */
    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    /**
     * @return the tipoIdentificacion
     */
    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    /**
     * @param tipoIdentificacion the tipoIdentificacion to set
     */
    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    /**
     * @return the numeroidentificacion
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * @param numeroidentificacion the numeroidentificacion to set
     */
    public void setNumeroIdentificacion(String numeroidentificacion) {
        this.numeroIdentificacion = numeroidentificacion;
    }

    /**
     * @return the puesto
     */
    public String getPuesto() {
        return puesto;
    }

    /**
     * @param puesto the puesto to set
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * @return the tipoCliente
     */
    public String getTipoCliente() {
        return tipoCliente;
    }

    /**
     * @param tipoCliente the tipo_cliente to set
     */
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    /**
     * @return the clienteRelacionadoId
     */
    public String getClienteRelacionadoId() {
        return clienteRelacionadoId;
    }

    /**
     * @param clienteRelacionadoId the clienteRelacionadoId to set
     */
    public void setClienteRelacionadoId(String clienteRelacionadoId) {
        this.clienteRelacionadoId = clienteRelacionadoId;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Constructores ">
    /**
     * Crea una nueva instancia de la entidad Cliente
     */
    public Cliente() {

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Métodos ">
    /**
     * Valida que los campos requeridos no se encuentren vacios
     * @return El status que denota el resultado de la validación
     */
    public Status validaCliente() {
        Status status = new Status(Status.OK);
        StringBuilder detalles = new StringBuilder("Los campos siguientes deben contener un dato valido: ");
        //<editor-fold defaultstate="collapsed" desc=" Verificar todos los campos ">
        if(isEmpty(nombre)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[nombre]");
        }
        if(isEmpty(paterno)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[paterno]");
        }
        if(isEmpty(materno)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[materno]");
        }
        if(isEmpty(fechaNacimiento)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[fechaNacimiento]");
        }
        if(isEmpty(sexo)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[sexo]");
        }
        if(isEmpty(municipio)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[municipio]");
        }
        if(isEmpty(ciudad)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[ciudad]");
        }
        if(isEmpty(pais)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[pais]");
        }
        if(isEmpty(estado)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[estado]");
        }
        if(isEmpty(email)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[email]");
        }if(isEmpty(contrasenia)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[contrasenia]");
        }
        if(isEmpty(tipoIdentificacion)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[tipoIdentificacion]");
        }
        if(isEmpty(numeroIdentificacion)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[numeroIdentificacion]");
        }
        if(isEmpty(puesto)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[puesto]");
        }
        if(isEmpty(tipoCliente)) {
            status.setCodigo(Status.FALTAN_DATOS_REQUERIDOS);
            detalles.append("[tipoCliente]");
        }
        if(isEmpty(clienteRelacionadoId)) clienteRelacionadoId = "0";
        if(status.getCodigo() == Status.FALTAN_DATOS_REQUERIDOS) {
            status.setMensaje(Status.getMensajeFromCode(status.getCodigo()));
            status.setDetalles(detalles.toString());
        }
        //</editor-fold>
        return status;
    }

    /**
     * Verifica si una cadena es nula o so solo contiene caracteres de espacio ' '
     * @param value La cadena a verificar
     * @return True: Si la cadena es nula o vacia<br/>False: Si la cadena contiene un valor
     */
    private boolean isEmpty(String value) {
        return value == null ? true : value.trim().equals("");
    }

    /**
     * Generar la sentencia SQL para insertar un cliente
     * @param idUsuario El id del usuario que ejecuta la sentencia
     * @return La sentencia SQL para insertar un cliente
     */
    public String createSQLInsert(long idUsuario){
        StringBuilder inserta = new StringBuilder("insert into tms_clientes_tbl(");
        inserta.append("cliente_id, contrasenia, nombre, apellidos, telefono_casa, calle, numero_int, numero_ext, colonia, ");
        inserta.append("codigo_postal, deleg_mun, ciudad, estado, fecha_nac, sexo, estado_civil, email, adicional1, ");
        inserta.append("pais, adicional2, adicional3, creado_por, ultima_actualizacion_por, puesto, tipo_cliente_id, cliente_relacionado_id");
        inserta.append(") ");
        //<editor-fold defaultstate="collapsed" desc=" Concatena los valores del insert ">
        inserta.append("values ( TMS_CLIENTES_SEQ.NEXTVAL, ");//cliente_id
        inserta.append("'").append(contrasenia).append("', ");//contrasenia
        inserta.append("'").append(nombre).append("', ");//nombres
        inserta.append("'").append(paterno).append(" ").append(materno).append("', ");//apellidos = paterno materno
        inserta.append("'").append(telefonoContacto).append("', ");//contacto
        inserta.append("'").append(calle).append("', ");//calle
        inserta.append("'").append(numeroInterior).append("', ");//numero_int
        inserta.append("'").append(numeroExterior).append("', ");//numero_ext
        inserta.append("'").append(colonia).append("', ");//colonia
        inserta.append("'").append(cp).append("', ");//codigo_postal
        inserta.append("'").append(municipio).append("', ");//deleg_mun
        inserta.append("'").append(ciudad).append("', ");//ciudad
        inserta.append("'").append(estado).append("', ");//estado
        inserta.append("'").append(fechaNacimiento).append("', ");//fecha_nac
        inserta.append("'").append(sexo).append("', ");//sexo
        inserta.append("'").append(estadoCivil).append("', ");//estado_civil
        inserta.append("'").append(email).append("', ");//email
        inserta.append("'").append(ocupacion).append("', ");//ocupacion->adicional1
        inserta.append("'").append(pais).append("', ");//pais
        inserta.append("'").append(tipoIdentificacion).append("', ");//tipo identificacion->adicional2
        inserta.append("'").append(numeroIdentificacion).append("', ");//numero identificacion->adicional3
        inserta.append("'").append(idUsuario).append("', ");//creado_por
        inserta.append("'").append(idUsuario).append("', ");//ultima_actualizacion_por
        inserta.append("'").append(puesto).append("', ");
        inserta.append("'").append(tipoCliente).append("', ");
        inserta.append("'").append(clienteRelacionadoId).append("')");
        //</editor-fold>
        return inserta.toString();
    }

    /**
     * Generar la sentencia SQL para actualizar esta entidad
     * @param idusuario El id del usuario que ejecuta la sentencia
     * @return La sentencia SQL para actualizar la entidad
     */
    public String createSQLUpdate(long idusuario) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder update = new StringBuilder("update tms_clientes_tbl set ");
        //<editor-fold defaultstate="collapsed" desc=" Concatena los valores de los campos ">
        update.append("nombre = '").append(nombre).append("', ");
        update.append("apellidos = '").append(paterno).append(" ").append(materno).append("', ");
        update.append("telefono_casa = '").append(telefonoContacto).append("', ");
        update.append("calle = '").append(calle).append("', ");
        update.append("numero_int = '").append(numeroInterior).append("', ");
        update.append("numero_ext = '").append(numeroExterior).append("', ");
        update.append("colonia = '").append(colonia).append("', ");
        update.append("codigo_postal = '").append(cp).append("', ");
        update.append("deleg_mun = '").append(municipio).append("', ");
        update.append("ciudad = '").append(ciudad).append("', ");
        update.append("estado = '").append(estado).append("', ");
        update.append("fecha_nac = '").append(fechaNacimiento).append("', ");
        update.append("sexo = '").append(sexo).append("', ");
        update.append("estado_civil = '").append(estadoCivil).append("', ");
        update.append("email = '").append(email).append("', ");
        update.append("adicional1 = '").append(ocupacion).append("', ");
        update.append("pais = '").append(pais).append("', ");
        update.append("adicional2 = '").append(tipoIdentificacion).append("', ");
        update.append("adicional3 = '").append(numeroIdentificacion).append("', ");
        update.append("ultima_actualizacion_por = '").append(idusuario).append("', ");
        update.append("ultima_fecha_actualizacion = '").append(format.format(new Date())).append("', ");
        update.append("puesto = '").append(puesto).append("', ");
        update.append("tipo_cliente_id = '").append(tipoCliente).append("', ");
        update.append("cliente_relacionado_id = '").append(clienteRelacionadoId).append("' ");
        update.append("where cliente_id = '").append(id).append("'");
        //</editor-fold>
        return update.toString();
    }

    /**
     * Genera la sentencia SQL que obtiene el cliente al cual le pertenezca el email
     * @param email El email del cliente a consultar
     * @return La sentencia SQL para obtener la entidad
     */
    public static String createSQLgetByEmail(String email) {
        StringBuilder select = new StringBuilder("select ");
        select.append("cliente_id, nombre, apellidos, telefono_casa, calle, numero_int, numero_ext, ");
        select.append("colonia, codigo_postal, deleg_mun, ciudad, estado, to_date(fecha_nac, 'dd/mm/rrrr') fecha_nac, sexo, estado_civil, ");
        select.append("email, adicional1, pais, adicional2, adicional3, contrasenia, puesto, tipo_cliente_id, cliente_relacionado_id ");
        select.append(" from tms_clientes_tbl where email = '").append(email).append("'");
        return select.toString();
    }

    /**
     * Genera la sentencia SQL que obtiene los clientes administrados
     * @param id El identificador del administrador
     * @return La sentencia SQL para obtener la consulta
     */
    public static String createSQLgetByAdmin(Long id) {
        StringBuilder select = new StringBuilder("select ");
        select.append("cliente_id, nombre, apellidos, telefono_casa, calle, numero_int, numero_ext, ");
        select.append("colonia, codigo_postal, deleg_mun, ciudad, estado, to_date(fecha_nac, 'dd/mm/rrrr') fecha_nac, sexo, estado_civil, ");
        select.append("email, adicional1, pais, adicional2, adicional3, contrasenia, puesto, tipo_cliente_id, cliente_relacionado_id ");
        select.append(" from tms_clientes_tbl where cliente_relacionado_id = '").append(id).append("'");
        return select.toString();
    }

    /**
     * Crea una instancia de Cliente apartir de una lista de datos que fue con el resultado de createSQLgetByEmail
     * @param row La fila a cargar
     * @return Una nueva instancia de Cliente
     */
    public static Cliente createFromSQLRow(List row) {
        Cliente cliente = new Cliente();
        cliente.id = row.get(0) != null ? row.get(0).toString() : "0";
        cliente.nombre = row.get(1) != null ? row.get(1).toString() : "";
        if(row.get(2) != null) {
            String apellidos = row.get(2).toString();
            int index = apellidos.indexOf(' ');
            if(index > 0) {
                cliente.paterno = apellidos.substring(0, index).trim();
                cliente.materno = apellidos.substring(index).trim();
            }
            else
                cliente.paterno = apellidos;
        }
        cliente.telefonoContacto = row.get(3) != null ? row.get(3).toString() : "";
        cliente.calle = row.get(4) != null ? row.get(4).toString() : "";
        cliente.numeroInterior = row.get(5) != null ? row.get(5).toString() : "0";
        cliente.numeroExterior = row.get(6) != null ? row.get(6).toString() : "0";
        cliente.colonia = row.get(7) != null ? row.get(7).toString() : "";
        cliente.cp = row.get(8) != null ? row.get(8).toString() : "0";
        cliente.municipio = row.get(9) != null ? row.get(9).toString() : "";
        cliente.ciudad = row.get(10) != null ? row.get(10).toString() : "";
        cliente.estado = row.get(11) != null ? row.get(11).toString() : "";
        cliente.fechaNacimiento = row.get(12) != null ? row.get(12).toString() : "";
        cliente.sexo = row.get(13) != null ? row.get(13).toString() : "H";
        cliente.estadoCivil = row.get(14) != null ? row.get(14).toString() : "";
        cliente.email = row.get(15) != null ? row.get(15).toString() : "";
        cliente.ocupacion = row.get(16) != null ? row.get(16).toString() : "";
        cliente.pais = row.get(17) != null ? row.get(17).toString() : "";
        cliente.tipoIdentificacion = row.get(18) != null ? row.get(18).toString() : "";
        cliente.numeroIdentificacion = row.get(19) != null ? row.get(19).toString() : "";
        cliente.contrasenia = row.get(20) != null ? row.get(20).toString() : "";
        cliente.puesto = row.get(21) != null ? row.get(21).toString() : "";
        cliente.tipoCliente = row.get(22) != null ? row.get(22).toString() : "";
        cliente.clienteRelacionadoId = row.get(23) != null ? row.get(23).toString() : "";
        return cliente;
    }
    //</editor-fold>

}
