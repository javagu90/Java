/*
 * TmsCorridasVentaTbl.java
 *
 * Created on 9 de octubre de 2007, 01:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspuertas.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Vector;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsCorridasVentaTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_CORRIDAS_VENTA_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsCorridasVentaTbl.findByCorridaId", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.corridaId = :corridaId"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByClaveCorrida", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.claveCorrida = :claveCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByEmpresa", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.empresa = :empresa"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByServicio", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.servicio = :servicio and t.fechaHoraCorrida between :fechaHoraCorrida1 and :fechaHoraCorrida2 and t.estadoCorrida = 'A' order by t.fechaHoraCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByFechaHoraCorrida", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.fechaHoraCorrida between :fechaHoraCorrida1 and :fechaHoraCorrida2 and t.estadoCorrida = 'A' order by t.fechaHoraCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByOrigen", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.origen = :origen"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByDestino", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.destino = :destino"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAutobus", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.autobus = :autobus and t.fechaHoraCorrida between :fechaHoraCorrida1 and :fechaHoraCorrida2 and t.estadoCorrida = 'A' order by t.fechaHoraCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAutobusServicio", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.autobus = :autobus and t.fechaHoraCorrida between :fechaHoraCorrida1 and :fechaHoraCorrida2 and t.servicio = :servicio and t.estadoCorrida = 'A' order by t.fechaHoraCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByOperador", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.operador = :operador and t.fechaHoraCorrida between :fechaHoraCorrida1 and :fechaHoraCorrida2 and t.estadoCorrida = 'A' order by t.fechaHoraCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByOperadorAutobus", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.operador = :operador and t.fechaHoraCorrida between :fechaHoraCorrida1 and :fechaHoraCorrida2 and t.autobus = :autobus and t.estadoCorrida = 'A' order by t.fechaHoraCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByOperadorServicio", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.operador = :operador and t.fechaHoraCorrida between :fechaHoraCorrida1 and :fechaHoraCorrida2 and t.servicio = :servicio and t.estadoCorrida = 'A' order by t.fechaHoraCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByOperadorAutobusServicio", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.operador = :operador and t.fechaHoraCorrida between :fechaHoraCorrida1 and :fechaHoraCorrida2 and t.servicio = :servicio and t.autobus = :autobus and t.estadoCorrida = 'A' order by t.fechaHoraCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByOperadorAdicional", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.operadorAdicional = :operadorAdicional"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByTipoCorrida", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.tipoCorrida = :tipoCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByEstadoCorrida", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.estadoCorrida = :estadoCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAutobusOriginal", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.autobusOriginal = :autobusOriginal"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByOperadorOriginal", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.operadorOriginal = :operadorOriginal"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByCorridaRelacionadaId", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.corridaRelacionadaId = :corridaRelacionadaId"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByMenoresCorrida", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.menoresCorrida = :menoresCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findBySenectudCorrida", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.senectudCorrida = :senectudCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByEstudiantesCorrida", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.estudiantesCorrida = :estudiantesCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByProfesoresCorrida", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.profesoresCorrida = :profesoresCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByCortesiasCorrida", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.cortesiasCorrida = :cortesiasCorrida"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByPlantillaId", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.plantillaId = :plantillaId"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento1", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento1 = :asiento1"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento2", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento2 = :asiento2"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento3", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento3 = :asiento3"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento4", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento4 = :asiento4"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento5", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento5 = :asiento5"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento6", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento6 = :asiento6"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento7", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento7 = :asiento7"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento8", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento8 = :asiento8"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento9", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento9 = :asiento9"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento10", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento10 = :asiento10"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento11", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento11 = :asiento11"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento12", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento12 = :asiento12"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento13", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento13 = :asiento13"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento14", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento14 = :asiento14"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento15", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento15 = :asiento15"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento16", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento16 = :asiento16"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento17", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento17 = :asiento17"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento18", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento18 = :asiento18"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento19", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento19 = :asiento19"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento20", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento20 = :asiento20"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento21", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento21 = :asiento21"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento22", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento22 = :asiento22"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento23", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento23 = :asiento23"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento24", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento24 = :asiento24"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento25", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento25 = :asiento25"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento26", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento26 = :asiento26"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento27", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento27 = :asiento27"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento28", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento28 = :asiento28"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento29", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento29 = :asiento29"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento30", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento30 = :asiento30"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento31", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento31 = :asiento31"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento32", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento32 = :asiento32"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento33", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento33 = :asiento33"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento34", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento34 = :asiento34"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento35", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento35 = :asiento35"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento36", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento36 = :asiento36"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento37", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento37 = :asiento37"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento38", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento38 = :asiento38"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento39", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento39 = :asiento39"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento40", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento40 = :asiento40"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento41", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento41 = :asiento41"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento42", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento42 = :asiento42"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento43", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento43 = :asiento43"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento44", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento44 = :asiento44"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento45", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento45 = :asiento45"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento46", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento46 = :asiento46"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento47", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento47 = :asiento47"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento48", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento48 = :asiento48"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento49", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento49 = :asiento49"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento50", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento50 = :asiento50"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento51", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento51 = :asiento51"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento52", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento52 = :asiento52"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento53", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento53 = :asiento53"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento54", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento54 = :asiento54"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento55", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento55 = :asiento55"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento56", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento56 = :asiento56"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento57", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento57 = :asiento57"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento58", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento58 = :asiento58"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento59", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento59 = :asiento59"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento60", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento60 = :asiento60"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento61", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento61 = :asiento61"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento62", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento62 = :asiento62"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento63", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento63 = :asiento63"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento64", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento64 = :asiento64"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento65", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento65 = :asiento65"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento66", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento66 = :asiento66"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento67", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento67 = :asiento67"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento68", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento68 = :asiento68"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento69", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento69 = :asiento69"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento70", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento70 = :asiento70"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento71", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento71 = :asiento71"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento72", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento72 = :asiento72"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento73", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento73 = :asiento73"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento74", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento74 = :asiento74"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento75", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento75 = :asiento75"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento76", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento76 = :asiento76"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento77", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento77 = :asiento77"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento78", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento78 = :asiento78"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento79", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento79 = :asiento79"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento80", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento80 = :asiento80"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento81", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento81 = :asiento81"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento82", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento82 = :asiento82"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento83", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento83 = :asiento83"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento84", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento84 = :asiento84"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAsiento85", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.asiento85 = :asiento85"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAdicional1", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAdicional2", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAdicional3", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAdicional4", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAdicional5", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAdicional6", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.adicional6 = :adicional6"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAdicional7", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.adicional7 = :adicional7"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAdicional8", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.adicional8 = :adicional8"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAdicional9", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.adicional9 = :adicional9"),
        @NamedQuery(name = "TmsCorridasVentaTbl.findByAdicional10", query = "SELECT t FROM TmsCorridasVentaTbl t WHERE t.adicional10 = :adicional10")
    })
public class TmsCorridasVentaTbl implements Serializable {

    @Id
    @Column(name = "CORRIDA_ID", nullable = false)
    private BigDecimal corridaId;

    @Column(name = "CLAVE_CORRIDA")
    private String claveCorrida;

    @Column(name = "EMPRESA")
    private String empresa;

    @Column(name = "SERVICIO")
    private String servicio;

    @Column(name = "FECHA_HORA_CORRIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCorrida;

    @Column(name = "ORIGEN")
    private String origen;

    @Column(name = "DESTINO")
    private String destino;

    @Column(name = "AUTOBUS")
    private String autobus;

    @Column(name = "OPERADOR")
    private String operador;

    @Column(name = "OPERADOR_ADICIONAL")
    private String operadorAdicional;

    @Column(name = "TIPO_CORRIDA")
    private String tipoCorrida;

    @Column(name = "ESTADO_CORRIDA")
    private String estadoCorrida;

    @Column(name = "AUTOBUS_ORIGINAL")
    private String autobusOriginal;

    @Column(name = "OPERADOR_ORIGINAL")
    private String operadorOriginal;

    @Column(name = "CORRIDA_RELACIONADA_ID")
    private BigInteger corridaRelacionadaId;

    @Column(name = "MENORES_CORRIDA")
    private BigInteger menoresCorrida;

    @Column(name = "SENECTUD_CORRIDA")
    private BigInteger senectudCorrida;

    @Column(name = "ESTUDIANTES_CORRIDA")
    private BigInteger estudiantesCorrida;

    @Column(name = "PROFESORES_CORRIDA")
    private BigInteger profesoresCorrida;

    @Column(name = "CORTESIAS_CORRIDA")
    private BigInteger cortesiasCorrida;

    @Column(name = "PLANTILLA_ID")
    private BigInteger plantillaId;

    @Column(name = "REPLICACION_ORIGEN")
    private String replicacionOrigen;
    
    @Column(name = "ASIENTO1")
    private String asiento1;

    @Column(name = "ASIENTO2")
    private String asiento2;

    @Column(name = "ASIENTO3")
    private String asiento3;

    @Column(name = "ASIENTO4")
    private String asiento4;

    @Column(name = "ASIENTO5")
    private String asiento5;

    @Column(name = "ASIENTO6")
    private String asiento6;

    @Column(name = "ASIENTO7")
    private String asiento7;

    @Column(name = "ASIENTO8")
    private String asiento8;

    @Column(name = "ASIENTO9")
    private String asiento9;

    @Column(name = "ASIENTO10")
    private String asiento10;

    @Column(name = "ASIENTO11")
    private String asiento11;

    @Column(name = "ASIENTO12")
    private String asiento12;

    @Column(name = "ASIENTO13")
    private String asiento13;

    @Column(name = "ASIENTO14")
    private String asiento14;

    @Column(name = "ASIENTO15")
    private String asiento15;

    @Column(name = "ASIENTO16")
    private String asiento16;

    @Column(name = "ASIENTO17")
    private String asiento17;

    @Column(name = "ASIENTO18")
    private String asiento18;

    @Column(name = "ASIENTO19")
    private String asiento19;

    @Column(name = "ASIENTO20")
    private String asiento20;

    @Column(name = "ASIENTO21")
    private String asiento21;

    @Column(name = "ASIENTO22")
    private String asiento22;

    @Column(name = "ASIENTO23")
    private String asiento23;

    @Column(name = "ASIENTO24")
    private String asiento24;

    @Column(name = "ASIENTO25")
    private String asiento25;

    @Column(name = "ASIENTO26")
    private String asiento26;

    @Column(name = "ASIENTO27")
    private String asiento27;

    @Column(name = "ASIENTO28")
    private String asiento28;

    @Column(name = "ASIENTO29")
    private String asiento29;

    @Column(name = "ASIENTO30")
    private String asiento30;

    @Column(name = "ASIENTO31")
    private String asiento31;

    @Column(name = "ASIENTO32")
    private String asiento32;

    @Column(name = "ASIENTO33")
    private String asiento33;

    @Column(name = "ASIENTO34")
    private String asiento34;

    @Column(name = "ASIENTO35")
    private String asiento35;

    @Column(name = "ASIENTO36")
    private String asiento36;

    @Column(name = "ASIENTO37")
    private String asiento37;

    @Column(name = "ASIENTO38")
    private String asiento38;

    @Column(name = "ASIENTO39")
    private String asiento39;

    @Column(name = "ASIENTO40")
    private String asiento40;

    @Column(name = "ASIENTO41")
    private String asiento41;

    @Column(name = "ASIENTO42")
    private String asiento42;

    @Column(name = "ASIENTO43")
    private String asiento43;

    @Column(name = "ASIENTO44")
    private String asiento44;

    @Column(name = "ASIENTO45")
    private String asiento45;

    @Column(name = "ASIENTO46")
    private String asiento46;

    @Column(name = "ASIENTO47")
    private String asiento47;

    @Column(name = "ASIENTO48")
    private String asiento48;

    @Column(name = "ASIENTO49")
    private String asiento49;

    @Column(name = "ASIENTO50")
    private String asiento50;

    @Column(name = "ASIENTO51")
    private String asiento51;

    @Column(name = "ASIENTO52")
    private String asiento52;

    @Column(name = "ASIENTO53")
    private String asiento53;

    @Column(name = "ASIENTO54")
    private String asiento54;

    @Column(name = "ASIENTO55")
    private String asiento55;

    @Column(name = "ASIENTO56")
    private String asiento56;

    @Column(name = "ASIENTO57")
    private String asiento57;

    @Column(name = "ASIENTO58")
    private String asiento58;

    @Column(name = "ASIENTO59")
    private String asiento59;

    @Column(name = "ASIENTO60")
    private String asiento60;

    @Column(name = "ASIENTO61")
    private String asiento61;

    @Column(name = "ASIENTO62")
    private String asiento62;

    @Column(name = "ASIENTO63")
    private String asiento63;

    @Column(name = "ASIENTO64")
    private String asiento64;

    @Column(name = "ASIENTO65")
    private String asiento65;

    @Column(name = "ASIENTO66")
    private String asiento66;

    @Column(name = "ASIENTO67")
    private String asiento67;

    @Column(name = "ASIENTO68")
    private String asiento68;

    @Column(name = "ASIENTO69")
    private String asiento69;

    @Column(name = "ASIENTO70")
    private String asiento70;

    @Column(name = "ASIENTO71")
    private String asiento71;

    @Column(name = "ASIENTO72")
    private String asiento72;

    @Column(name = "ASIENTO73")
    private String asiento73;

    @Column(name = "ASIENTO74")
    private String asiento74;

    @Column(name = "ASIENTO75")
    private String asiento75;

    @Column(name = "ASIENTO76")
    private String asiento76;

    @Column(name = "ASIENTO77")
    private String asiento77;

    @Column(name = "ASIENTO78")
    private String asiento78;

    @Column(name = "ASIENTO79")
    private String asiento79;

    @Column(name = "ASIENTO80")
    private String asiento80;

    @Column(name = "ASIENTO81")
    private String asiento81;

    @Column(name = "ASIENTO82")
    private String asiento82;

    @Column(name = "ASIENTO83")
    private String asiento83;

    @Column(name = "ASIENTO84")
    private String asiento84;

    @Column(name = "ASIENTO85")
    private String asiento85;

    @Column(name = "ADICIONAL1")
    private String adicional1;

    @Column(name = "ADICIONAL2")
    private String adicional2;

    @Column(name = "ADICIONAL3")
    private String adicional3;

    @Column(name = "ADICIONAL4")
    private String adicional4;

    @Column(name = "ADICIONAL5")
    private String adicional5;

    @Column(name = "ADICIONAL6")
    private String adicional6;

    @Column(name = "ADICIONAL7")
    private String adicional7;

    @Column(name = "ADICIONAL8")
    private String adicional8;

    @Column(name = "ADICIONAL9")
    private String adicional9;

    @Column(name = "ADICIONAL10")
    private String adicional10;
    
    /** Creates a new instance of TmsCorridasVentaTbl */
    public TmsCorridasVentaTbl() {
    }

    /**
     * Creates a new instance of TmsCorridasVentaTbl with the specified values.
     * @param corridaId the corridaId of the TmsCorridasVentaTbl
     */
    public TmsCorridasVentaTbl(BigDecimal corridaId) {
        this.corridaId = corridaId;
    }

    public void setValoresIniciales(Vector vector){
        this.setCorridaId((BigDecimal)vector.get(0));
        this.setClaveCorrida((String)vector.get(1));
        this.setEmpresa((String)vector.get(2));
        this.setServicio((String)vector.get(3));
        this.setFechaHoraCorrida((Date)vector.get(4));
        this.setOrigen((String)vector.get(5));
        this.setDestino((String)vector.get(6));
        if(vector.get(7)!=null)
         this.setAutobus((String)vector.get(7));
        if(vector.get(8)!=null)
         this.setOperador((String)vector.get(8));
        if(vector.get(9)!=null)
         this.setOperadorAdicional((String)vector.get(9));
        this.setTipoCorrida((String)vector.get(10));
        this.setEstadoCorrida((String)vector.get(11));
        if(vector.get(12)!=null)
         this.setAutobusOriginal((String)vector.get(12));
        if(vector.get(13)!=null)
         this.setOperadorOriginal((String)vector.get(13));
        if(vector.get(14)!=null)
         this.setCorridaRelacionadaId((BigInteger)vector.get(14));
        BigDecimal nmenores = (BigDecimal)vector.get(15);
        this.setMenoresCorrida(BigInteger.valueOf(nmenores.longValue()));
        BigDecimal nsenectud = (BigDecimal)vector.get(16);
        this.setSenectudCorrida(BigInteger.valueOf(nsenectud.longValue()));
        BigDecimal nest = (BigDecimal)vector.get(17);
        this.setEstudiantesCorrida(BigInteger.valueOf(nest.longValue()));
        BigDecimal nprof = (BigDecimal)vector.get(18);
        this.setProfesoresCorrida(BigInteger.valueOf(nprof.longValue()));
        BigDecimal ncort = (BigDecimal)vector.get(19);
        this.setCortesiasCorrida(BigInteger.valueOf(ncort.longValue()));
        if(vector.get(20)!=null)
        {
             BigDecimal npla = (BigDecimal)vector.get(20);
            this.setPlantillaId(BigInteger.valueOf(npla.longValue()));
        }
        this.setAsiento1((String)vector.get(21));this.setAsiento2((String)vector.get(22));this.setAsiento3((String)vector.get(23));this.setAsiento4((String)vector.get(24));this.setAsiento5((String)vector.get(25));
        this.setAsiento6((String)vector.get(26));this.setAsiento7((String)vector.get(27));this.setAsiento8((String)vector.get(28));this.setAsiento9((String)vector.get(29));this.setAsiento10((String)vector.get(30));
        this.setAsiento11((String)vector.get(31));this.setAsiento12((String)vector.get(32));this.setAsiento13((String)vector.get(33));this.setAsiento14((String)vector.get(34));this.setAsiento15((String)vector.get(35));
        this.setAsiento16((String)vector.get(36));this.setAsiento17((String)vector.get(37));this.setAsiento18((String)vector.get(38));this.setAsiento19((String)vector.get(39));this.setAsiento20((String)vector.get(40));
        this.setAsiento21((String)vector.get(41));this.setAsiento22((String)vector.get(42));this.setAsiento23((String)vector.get(43));this.setAsiento24((String)vector.get(44));this.setAsiento25((String)vector.get(45));
        this.setAsiento26((String)vector.get(46));this.setAsiento27((String)vector.get(47));this.setAsiento28((String)vector.get(48));this.setAsiento29((String)vector.get(49));this.setAsiento30((String)vector.get(50));
        this.setAsiento31((String)vector.get(51));this.setAsiento32((String)vector.get(52));this.setAsiento33((String)vector.get(53));this.setAsiento34((String)vector.get(54));this.setAsiento35((String)vector.get(55));
        this.setAsiento36((String)vector.get(56));this.setAsiento37((String)vector.get(57));this.setAsiento38((String)vector.get(58));this.setAsiento39((String)vector.get(59));this.setAsiento40((String)vector.get(60));
        this.setAsiento41((String)vector.get(61));this.setAsiento42((String)vector.get(62));this.setAsiento43((String)vector.get(63));this.setAsiento44((String)vector.get(64));this.setAsiento45((String)vector.get(65));
        this.setAsiento46((String)vector.get(66));this.setAsiento47((String)vector.get(67));this.setAsiento48((String)vector.get(68));this.setAsiento49((String)vector.get(69));this.setAsiento50((String)vector.get(70));
        this.setAsiento51((String)vector.get(71));this.setAsiento52((String)vector.get(72));this.setAsiento53((String)vector.get(73));this.setAsiento54((String)vector.get(74));this.setAsiento55((String)vector.get(75));
        this.setAsiento56((String)vector.get(76));this.setAsiento57((String)vector.get(77));this.setAsiento58((String)vector.get(78));this.setAsiento59((String)vector.get(79));this.setAsiento60((String)vector.get(80));
        this.setAsiento61((String)vector.get(81));this.setAsiento62((String)vector.get(82));this.setAsiento63((String)vector.get(83));this.setAsiento64((String)vector.get(84));this.setAsiento65((String)vector.get(85));
        this.setAsiento66((String)vector.get(86));this.setAsiento67((String)vector.get(87));this.setAsiento68((String)vector.get(88));this.setAsiento69((String)vector.get(89));this.setAsiento70((String)vector.get(90));
        this.setAsiento71((String)vector.get(91));this.setAsiento72((String)vector.get(92));this.setAsiento73((String)vector.get(93));this.setAsiento74((String)vector.get(94));this.setAsiento75((String)vector.get(95));
        this.setAsiento76((String)vector.get(96));this.setAsiento77((String)vector.get(97));this.setAsiento78((String)vector.get(98));this.setAsiento79((String)vector.get(99));this.setAsiento80((String)vector.get(100));
        this.setAsiento81((String)vector.get(101));this.setAsiento82((String)vector.get(102));this.setAsiento83((String)vector.get(103));this.setAsiento84((String)vector.get(104));this.setAsiento85((String)vector.get(105));
        if(vector.get(106)!=null)
            this.setAdicional1((String)vector.get(106));
        if(vector.get(107)!=null)
            this.setAdicional2((String)vector.get(107));
        if(vector.get(108)!=null)
            this.setAdicional3((String)vector.get(108));
        if(vector.get(109)!=null)
            this.setAdicional4((String)vector.get(109));
        if(vector.get(110)!=null)
            this.setAdicional5((String)vector.get(110));
        if(vector.get(111)!=null)
            this.setAdicional6((String)vector.get(111));
        if(vector.get(112)!=null)
            this.setAdicional7((String)vector.get(112));
        if(vector.get(113)!=null)
            this.setAdicional8((String)vector.get(113));
        if(vector.get(114)!=null)
            this.setAdicional9((String)vector.get(114));
        if(vector.get(115)!=null)
            this.setAdicional10((String)vector.get(115));
    }
    
    /**
     * Gets the corridaId of this TmsCorridasVentaTbl.
     * @return the corridaId
     */
    public BigDecimal getCorridaId() {
        return this.corridaId;
    }

    /**
     * Sets the corridaId of this TmsCorridasVentaTbl to the specified value.
     * @param corridaId the new corridaId
     */
    public void setCorridaId(BigDecimal corridaId) {
        this.corridaId = corridaId;
    }

    /**
     * Gets the claveCorrida of this TmsCorridasVentaTbl.
     * @return the claveCorrida
     */
    public String getClaveCorrida() {
        return this.claveCorrida;
    }

    /**
     * Sets the claveCorrida of this TmsCorridasVentaTbl to the specified value.
     * @param claveCorrida the new claveCorrida
     */
    public void setClaveCorrida(String claveCorrida) {
        this.claveCorrida = claveCorrida;
    }

    /**
     * Gets the empresa of this TmsCorridasVentaTbl.
     * @return the empresa
     */
    public String getEmpresa() {
        return this.empresa;
    }

    /**
     * Sets the empresa of this TmsCorridasVentaTbl to the specified value.
     * @param empresa the new empresa
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Gets the servicio of this TmsCorridasVentaTbl.
     * @return the servicio
     */
    public String getServicio() {
        return this.servicio;
    }

    /**
     * Sets the servicio of this TmsCorridasVentaTbl to the specified value.
     * @param servicio the new servicio
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * Gets the fechaHoraCorrida of this TmsCorridasVentaTbl.
     * @return the fechaHoraCorrida
     */
    public Date getFechaHoraCorrida() {
        return this.fechaHoraCorrida;
    }

    /**
     * Sets the fechaHoraCorrida of this TmsCorridasVentaTbl to the specified value.
     * @param fechaHoraCorrida the new fechaHoraCorrida
     */
    public void setFechaHoraCorrida(Date fechaHoraCorrida) {
        this.fechaHoraCorrida = fechaHoraCorrida;
    }

    /**
     * Gets the origen of this TmsCorridasVentaTbl.
     * @return the origen
     */
    public String getOrigen() {
        return this.origen;
    }

    /**
     * Sets the origen of this TmsCorridasVentaTbl to the specified value.
     * @param origen the new origen
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * Gets the destino of this TmsCorridasVentaTbl.
     * @return the destino
     */
    public String getDestino() {
        return this.destino;
    }

    /**
     * Sets the destino of this TmsCorridasVentaTbl to the specified value.
     * @param destino the new destino
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * Gets the autobus of this TmsCorridasVentaTbl.
     * @return the autobus
     */
    public String getAutobus() {
        return this.autobus;
    }

    /**
     * Sets the autobus of this TmsCorridasVentaTbl to the specified value.
     * @param autobus the new autobus
     */
    public void setAutobus(String autobus) {
        this.autobus = autobus;
    }

    /**
     * Gets the operador of this TmsCorridasVentaTbl.
     * @return the operador
     */
    public String getOperador() {
        return this.operador;
    }

    /**
     * Sets the operador of this TmsCorridasVentaTbl to the specified value.
     * @param operador the new operador
     */
    public void setOperador(String operador) {
        this.operador = operador;
    }

    /**
     * Gets the operadorAdicional of this TmsCorridasVentaTbl.
     * @return the operadorAdicional
     */
    public String getOperadorAdicional() {
        return this.operadorAdicional;
    }

    /**
     * Sets the operadorAdicional of this TmsCorridasVentaTbl to the specified value.
     * @param operadorAdicional the new operadorAdicional
     */
    public void setOperadorAdicional(String operadorAdicional) {
        this.operadorAdicional = operadorAdicional;
    }

    /**
     * Gets the tipoCorrida of this TmsCorridasVentaTbl.
     * @return the tipoCorrida
     */
    public String getTipoCorrida() {
        return this.tipoCorrida;
    }

    /**
     * Sets the tipoCorrida of this TmsCorridasVentaTbl to the specified value.
     * @param tipoCorrida the new tipoCorrida
     */
    public void setTipoCorrida(String tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
    }

    /**
     * Gets the estadoCorrida of this TmsCorridasVentaTbl.
     * @return the estadoCorrida
     */
    public String getEstadoCorrida() {
        return this.estadoCorrida;
    }

    /**
     * Sets the estadoCorrida of this TmsCorridasVentaTbl to the specified value.
     * @param estadoCorrida the new estadoCorrida
     */
    public void setEstadoCorrida(String estadoCorrida) {
        this.estadoCorrida = estadoCorrida;
    }

    /**
     * Gets the autobusOriginal of this TmsCorridasVentaTbl.
     * @return the autobusOriginal
     */
    public String getAutobusOriginal() {
        return this.autobusOriginal;
    }

    /**
     * Sets the autobusOriginal of this TmsCorridasVentaTbl to the specified value.
     * @param autobusOriginal the new autobusOriginal
     */
    public void setAutobusOriginal(String autobusOriginal) {
        this.autobusOriginal = autobusOriginal;
    }

    /**
     * Gets the operadorOriginal of this TmsCorridasVentaTbl.
     * @return the operadorOriginal
     */
    public String getOperadorOriginal() {
        return this.operadorOriginal;
    }

    /**
     * Sets the operadorOriginal of this TmsCorridasVentaTbl to the specified value.
     * @param operadorOriginal the new operadorOriginal
     */
    public void setOperadorOriginal(String operadorOriginal) {
        this.operadorOriginal = operadorOriginal;
    }

    /**
     * Gets the corridaRelacionadaId of this TmsCorridasVentaTbl.
     * @return the corridaRelacionadaId
     */
    public BigInteger getCorridaRelacionadaId() {
        return this.corridaRelacionadaId;
    }

    /**
     * Sets the corridaRelacionadaId of this TmsCorridasVentaTbl to the specified value.
     * @param corridaRelacionadaId the new corridaRelacionadaId
     */
    public void setCorridaRelacionadaId(BigInteger corridaRelacionadaId) {
        this.corridaRelacionadaId = corridaRelacionadaId;
    }

    /**
     * Gets the menoresCorrida of this TmsCorridasVentaTbl.
     * @return the menoresCorrida
     */
    public BigInteger getMenoresCorrida() {
        return this.menoresCorrida;
    }

    /**
     * Sets the menoresCorrida of this TmsCorridasVentaTbl to the specified value.
     * @param menoresCorrida the new menoresCorrida
     */
    public void setMenoresCorrida(BigInteger menoresCorrida) {
        this.menoresCorrida = menoresCorrida;
    }

    /**
     * Gets the senectudCorrida of this TmsCorridasVentaTbl.
     * @return the senectudCorrida
     */
    public BigInteger getSenectudCorrida() {
        return this.senectudCorrida;
    }

    /**
     * Sets the senectudCorrida of this TmsCorridasVentaTbl to the specified value.
     * @param senectudCorrida the new senectudCorrida
     */
    public void setSenectudCorrida(BigInteger senectudCorrida) {
        this.senectudCorrida = senectudCorrida;
    }

    /**
     * Gets the estudiantesCorrida of this TmsCorridasVentaTbl.
     * @return the estudiantesCorrida
     */
    public BigInteger getEstudiantesCorrida() {
        return this.estudiantesCorrida;
    }

    /**
     * Sets the estudiantesCorrida of this TmsCorridasVentaTbl to the specified value.
     * @param estudiantesCorrida the new estudiantesCorrida
     */
    public void setEstudiantesCorrida(BigInteger estudiantesCorrida) {
        this.estudiantesCorrida = estudiantesCorrida;
    }

    /**
     * Gets the profesoresCorrida of this TmsCorridasVentaTbl.
     * @return the profesoresCorrida
     */
    public BigInteger getProfesoresCorrida() {
        return this.profesoresCorrida;
    }

    /**
     * Sets the profesoresCorrida of this TmsCorridasVentaTbl to the specified value.
     * @param profesoresCorrida the new profesoresCorrida
     */
    public void setProfesoresCorrida(BigInteger profesoresCorrida) {
        this.profesoresCorrida = profesoresCorrida;
    }

    /**
     * Gets the cortesiasCorrida of this TmsCorridasVentaTbl.
     * @return the cortesiasCorrida
     */
    public BigInteger getCortesiasCorrida() {
        return this.cortesiasCorrida;
    }

    /**
     * Sets the cortesiasCorrida of this TmsCorridasVentaTbl to the specified value.
     * @param cortesiasCorrida the new cortesiasCorrida
     */
    public void setCortesiasCorrida(BigInteger cortesiasCorrida) {
        this.cortesiasCorrida = cortesiasCorrida;
    }

    /**
     * Gets the plantillaId of this TmsCorridasVentaTbl.
     * @return the plantillaId
     */
    public BigInteger getPlantillaId() {
        return this.plantillaId;
    }

    /**
     * Sets the plantillaId of this TmsCorridasVentaTbl to the specified value.
     * @param plantillaId the new plantillaId
     */
    public void setPlantillaId(BigInteger plantillaId) {
        this.plantillaId = plantillaId;
    }

    /**
     * Gets the asiento1 of this TmsCorridasVentaTbl.
     * @return the asiento1
     */
    public String getAsiento1() {
        return this.asiento1;
    }

    /**
     * Sets the asiento1 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento1 the new asiento1
     */
    public void setAsiento1(String asiento1) {
        this.asiento1 = asiento1;
    }

    /**
     * Gets the asiento2 of this TmsCorridasVentaTbl.
     * @return the asiento2
     */
    public String getAsiento2() {
        return this.asiento2;
    }

    /**
     * Sets the asiento2 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento2 the new asiento2
     */
    public void setAsiento2(String asiento2) {
        this.asiento2 = asiento2;
    }

    /**
     * Gets the asiento3 of this TmsCorridasVentaTbl.
     * @return the asiento3
     */
    public String getAsiento3() {
        return this.asiento3;
    }

    /**
     * Sets the asiento3 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento3 the new asiento3
     */
    public void setAsiento3(String asiento3) {
        this.asiento3 = asiento3;
    }

    /**
     * Gets the asiento4 of this TmsCorridasVentaTbl.
     * @return the asiento4
     */
    public String getAsiento4() {
        return this.asiento4;
    }

    /**
     * Sets the asiento4 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento4 the new asiento4
     */
    public void setAsiento4(String asiento4) {
        this.asiento4 = asiento4;
    }

    /**
     * Gets the asiento5 of this TmsCorridasVentaTbl.
     * @return the asiento5
     */
    public String getAsiento5() {
        return this.asiento5;
    }

    /**
     * Sets the asiento5 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento5 the new asiento5
     */
    public void setAsiento5(String asiento5) {
        this.asiento5 = asiento5;
    }

    /**
     * Gets the asiento6 of this TmsCorridasVentaTbl.
     * @return the asiento6
     */
    public String getAsiento6() {
        return this.asiento6;
    }

    /**
     * Sets the asiento6 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento6 the new asiento6
     */
    public void setAsiento6(String asiento6) {
        this.asiento6 = asiento6;
    }

    /**
     * Gets the asiento7 of this TmsCorridasVentaTbl.
     * @return the asiento7
     */
    public String getAsiento7() {
        return this.asiento7;
    }

    /**
     * Sets the asiento7 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento7 the new asiento7
     */
    public void setAsiento7(String asiento7) {
        this.asiento7 = asiento7;
    }

    /**
     * Gets the asiento8 of this TmsCorridasVentaTbl.
     * @return the asiento8
     */
    public String getAsiento8() {
        return this.asiento8;
    }

    /**
     * Sets the asiento8 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento8 the new asiento8
     */
    public void setAsiento8(String asiento8) {
        this.asiento8 = asiento8;
    }

    /**
     * Gets the asiento9 of this TmsCorridasVentaTbl.
     * @return the asiento9
     */
    public String getAsiento9() {
        return this.asiento9;
    }

    /**
     * Sets the asiento9 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento9 the new asiento9
     */
    public void setAsiento9(String asiento9) {
        this.asiento9 = asiento9;
    }

    /**
     * Gets the asiento10 of this TmsCorridasVentaTbl.
     * @return the asiento10
     */
    public String getAsiento10() {
        return this.asiento10;
    }

    /**
     * Sets the asiento10 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento10 the new asiento10
     */
    public void setAsiento10(String asiento10) {
        this.asiento10 = asiento10;
    }

    /**
     * Gets the asiento11 of this TmsCorridasVentaTbl.
     * @return the asiento11
     */
    public String getAsiento11() {
        return this.asiento11;
    }

    /**
     * Sets the asiento11 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento11 the new asiento11
     */
    public void setAsiento11(String asiento11) {
        this.asiento11 = asiento11;
    }

    /**
     * Gets the asiento12 of this TmsCorridasVentaTbl.
     * @return the asiento12
     */
    public String getAsiento12() {
        return this.asiento12;
    }

    /**
     * Sets the asiento12 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento12 the new asiento12
     */
    public void setAsiento12(String asiento12) {
        this.asiento12 = asiento12;
    }

    /**
     * Gets the asiento13 of this TmsCorridasVentaTbl.
     * @return the asiento13
     */
    public String getAsiento13() {
        return this.asiento13;
    }

    /**
     * Sets the asiento13 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento13 the new asiento13
     */
    public void setAsiento13(String asiento13) {
        this.asiento13 = asiento13;
    }

    /**
     * Gets the asiento14 of this TmsCorridasVentaTbl.
     * @return the asiento14
     */
    public String getAsiento14() {
        return this.asiento14;
    }

    /**
     * Sets the asiento14 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento14 the new asiento14
     */
    public void setAsiento14(String asiento14) {
        this.asiento14 = asiento14;
    }

    /**
     * Gets the asiento15 of this TmsCorridasVentaTbl.
     * @return the asiento15
     */
    public String getAsiento15() {
        return this.asiento15;
    }

    /**
     * Sets the asiento15 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento15 the new asiento15
     */
    public void setAsiento15(String asiento15) {
        this.asiento15 = asiento15;
    }

    /**
     * Gets the asiento16 of this TmsCorridasVentaTbl.
     * @return the asiento16
     */
    public String getAsiento16() {
        return this.asiento16;
    }

    /**
     * Sets the asiento16 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento16 the new asiento16
     */
    public void setAsiento16(String asiento16) {
        this.asiento16 = asiento16;
    }

    /**
     * Gets the asiento17 of this TmsCorridasVentaTbl.
     * @return the asiento17
     */
    public String getAsiento17() {
        return this.asiento17;
    }

    /**
     * Sets the asiento17 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento17 the new asiento17
     */
    public void setAsiento17(String asiento17) {
        this.asiento17 = asiento17;
    }

    /**
     * Gets the asiento18 of this TmsCorridasVentaTbl.
     * @return the asiento18
     */
    public String getAsiento18() {
        return this.asiento18;
    }

    /**
     * Sets the asiento18 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento18 the new asiento18
     */
    public void setAsiento18(String asiento18) {
        this.asiento18 = asiento18;
    }

    /**
     * Gets the asiento19 of this TmsCorridasVentaTbl.
     * @return the asiento19
     */
    public String getAsiento19() {
        return this.asiento19;
    }

    /**
     * Sets the asiento19 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento19 the new asiento19
     */
    public void setAsiento19(String asiento19) {
        this.asiento19 = asiento19;
    }

    /**
     * Gets the asiento20 of this TmsCorridasVentaTbl.
     * @return the asiento20
     */
    public String getAsiento20() {
        return this.asiento20;
    }

    /**
     * Sets the asiento20 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento20 the new asiento20
     */
    public void setAsiento20(String asiento20) {
        this.asiento20 = asiento20;
    }

    /**
     * Gets the asiento21 of this TmsCorridasVentaTbl.
     * @return the asiento21
     */
    public String getAsiento21() {
        return this.asiento21;
    }

    /**
     * Sets the asiento21 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento21 the new asiento21
     */
    public void setAsiento21(String asiento21) {
        this.asiento21 = asiento21;
    }

    /**
     * Gets the asiento22 of this TmsCorridasVentaTbl.
     * @return the asiento22
     */
    public String getAsiento22() {
        return this.asiento22;
    }

    /**
     * Sets the asiento22 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento22 the new asiento22
     */
    public void setAsiento22(String asiento22) {
        this.asiento22 = asiento22;
    }

    /**
     * Gets the asiento23 of this TmsCorridasVentaTbl.
     * @return the asiento23
     */
    public String getAsiento23() {
        return this.asiento23;
    }

    /**
     * Sets the asiento23 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento23 the new asiento23
     */
    public void setAsiento23(String asiento23) {
        this.asiento23 = asiento23;
    }

    /**
     * Gets the asiento24 of this TmsCorridasVentaTbl.
     * @return the asiento24
     */
    public String getAsiento24() {
        return this.asiento24;
    }

    /**
     * Sets the asiento24 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento24 the new asiento24
     */
    public void setAsiento24(String asiento24) {
        this.asiento24 = asiento24;
    }

    /**
     * Gets the asiento25 of this TmsCorridasVentaTbl.
     * @return the asiento25
     */
    public String getAsiento25() {
        return this.asiento25;
    }

    /**
     * Sets the asiento25 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento25 the new asiento25
     */
    public void setAsiento25(String asiento25) {
        this.asiento25 = asiento25;
    }

    /**
     * Gets the asiento26 of this TmsCorridasVentaTbl.
     * @return the asiento26
     */
    public String getAsiento26() {
        return this.asiento26;
    }

    /**
     * Sets the asiento26 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento26 the new asiento26
     */
    public void setAsiento26(String asiento26) {
        this.asiento26 = asiento26;
    }

    /**
     * Gets the asiento27 of this TmsCorridasVentaTbl.
     * @return the asiento27
     */
    public String getAsiento27() {
        return this.asiento27;
    }

    /**
     * Sets the asiento27 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento27 the new asiento27
     */
    public void setAsiento27(String asiento27) {
        this.asiento27 = asiento27;
    }

    /**
     * Gets the asiento28 of this TmsCorridasVentaTbl.
     * @return the asiento28
     */
    public String getAsiento28() {
        return this.asiento28;
    }

    /**
     * Sets the asiento28 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento28 the new asiento28
     */
    public void setAsiento28(String asiento28) {
        this.asiento28 = asiento28;
    }

    /**
     * Gets the asiento29 of this TmsCorridasVentaTbl.
     * @return the asiento29
     */
    public String getAsiento29() {
        return this.asiento29;
    }

    /**
     * Sets the asiento29 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento29 the new asiento29
     */
    public void setAsiento29(String asiento29) {
        this.asiento29 = asiento29;
    }

    /**
     * Gets the asiento30 of this TmsCorridasVentaTbl.
     * @return the asiento30
     */
    public String getAsiento30() {
        return this.asiento30;
    }

    /**
     * Sets the asiento30 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento30 the new asiento30
     */
    public void setAsiento30(String asiento30) {
        this.asiento30 = asiento30;
    }

    /**
     * Gets the asiento31 of this TmsCorridasVentaTbl.
     * @return the asiento31
     */
    public String getAsiento31() {
        return this.asiento31;
    }

    /**
     * Sets the asiento31 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento31 the new asiento31
     */
    public void setAsiento31(String asiento31) {
        this.asiento31 = asiento31;
    }

    /**
     * Gets the asiento32 of this TmsCorridasVentaTbl.
     * @return the asiento32
     */
    public String getAsiento32() {
        return this.asiento32;
    }

    /**
     * Sets the asiento32 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento32 the new asiento32
     */
    public void setAsiento32(String asiento32) {
        this.asiento32 = asiento32;
    }

    /**
     * Gets the asiento33 of this TmsCorridasVentaTbl.
     * @return the asiento33
     */
    public String getAsiento33() {
        return this.asiento33;
    }

    /**
     * Sets the asiento33 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento33 the new asiento33
     */
    public void setAsiento33(String asiento33) {
        this.asiento33 = asiento33;
    }

    /**
     * Gets the asiento34 of this TmsCorridasVentaTbl.
     * @return the asiento34
     */
    public String getAsiento34() {
        return this.asiento34;
    }

    /**
     * Sets the asiento34 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento34 the new asiento34
     */
    public void setAsiento34(String asiento34) {
        this.asiento34 = asiento34;
    }

    /**
     * Gets the asiento35 of this TmsCorridasVentaTbl.
     * @return the asiento35
     */
    public String getAsiento35() {
        return this.asiento35;
    }

    /**
     * Sets the asiento35 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento35 the new asiento35
     */
    public void setAsiento35(String asiento35) {
        this.asiento35 = asiento35;
    }

    /**
     * Gets the asiento36 of this TmsCorridasVentaTbl.
     * @return the asiento36
     */
    public String getAsiento36() {
        return this.asiento36;
    }

    /**
     * Sets the asiento36 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento36 the new asiento36
     */
    public void setAsiento36(String asiento36) {
        this.asiento36 = asiento36;
    }

    /**
     * Gets the asiento37 of this TmsCorridasVentaTbl.
     * @return the asiento37
     */
    public String getAsiento37() {
        return this.asiento37;
    }

    /**
     * Sets the asiento37 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento37 the new asiento37
     */
    public void setAsiento37(String asiento37) {
        this.asiento37 = asiento37;
    }

    /**
     * Gets the asiento38 of this TmsCorridasVentaTbl.
     * @return the asiento38
     */
    public String getAsiento38() {
        return this.asiento38;
    }

    /**
     * Sets the asiento38 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento38 the new asiento38
     */
    public void setAsiento38(String asiento38) {
        this.asiento38 = asiento38;
    }

    /**
     * Gets the asiento39 of this TmsCorridasVentaTbl.
     * @return the asiento39
     */
    public String getAsiento39() {
        return this.asiento39;
    }

    /**
     * Sets the asiento39 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento39 the new asiento39
     */
    public void setAsiento39(String asiento39) {
        this.asiento39 = asiento39;
    }

    /**
     * Gets the asiento40 of this TmsCorridasVentaTbl.
     * @return the asiento40
     */
    public String getAsiento40() {
        return this.asiento40;
    }

    /**
     * Sets the asiento40 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento40 the new asiento40
     */
    public void setAsiento40(String asiento40) {
        this.asiento40 = asiento40;
    }

    /**
     * Gets the asiento41 of this TmsCorridasVentaTbl.
     * @return the asiento41
     */
    public String getAsiento41() {
        return this.asiento41;
    }

    /**
     * Sets the asiento41 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento41 the new asiento41
     */
    public void setAsiento41(String asiento41) {
        this.asiento41 = asiento41;
    }

    /**
     * Gets the asiento42 of this TmsCorridasVentaTbl.
     * @return the asiento42
     */
    public String getAsiento42() {
        return this.asiento42;
    }

    /**
     * Sets the asiento42 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento42 the new asiento42
     */
    public void setAsiento42(String asiento42) {
        this.asiento42 = asiento42;
    }

    /**
     * Gets the asiento43 of this TmsCorridasVentaTbl.
     * @return the asiento43
     */
    public String getAsiento43() {
        return this.asiento43;
    }

    /**
     * Sets the asiento43 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento43 the new asiento43
     */
    public void setAsiento43(String asiento43) {
        this.asiento43 = asiento43;
    }

    /**
     * Gets the asiento44 of this TmsCorridasVentaTbl.
     * @return the asiento44
     */
    public String getAsiento44() {
        return this.asiento44;
    }

    /**
     * Sets the asiento44 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento44 the new asiento44
     */
    public void setAsiento44(String asiento44) {
        this.asiento44 = asiento44;
    }

    /**
     * Gets the asiento45 of this TmsCorridasVentaTbl.
     * @return the asiento45
     */
    public String getAsiento45() {
        return this.asiento45;
    }

    /**
     * Sets the asiento45 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento45 the new asiento45
     */
    public void setAsiento45(String asiento45) {
        this.asiento45 = asiento45;
    }

    /**
     * Gets the asiento46 of this TmsCorridasVentaTbl.
     * @return the asiento46
     */
    public String getAsiento46() {
        return this.asiento46;
    }

    /**
     * Sets the asiento46 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento46 the new asiento46
     */
    public void setAsiento46(String asiento46) {
        this.asiento46 = asiento46;
    }

    /**
     * Gets the asiento47 of this TmsCorridasVentaTbl.
     * @return the asiento47
     */
    public String getAsiento47() {
        return this.asiento47;
    }

    /**
     * Sets the asiento47 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento47 the new asiento47
     */
    public void setAsiento47(String asiento47) {
        this.asiento47 = asiento47;
    }

    /**
     * Gets the asiento48 of this TmsCorridasVentaTbl.
     * @return the asiento48
     */
    public String getAsiento48() {
        return this.asiento48;
    }

    /**
     * Sets the asiento48 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento48 the new asiento48
     */
    public void setAsiento48(String asiento48) {
        this.asiento48 = asiento48;
    }

    /**
     * Gets the asiento49 of this TmsCorridasVentaTbl.
     * @return the asiento49
     */
    public String getAsiento49() {
        return this.asiento49;
    }

    /**
     * Sets the asiento49 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento49 the new asiento49
     */
    public void setAsiento49(String asiento49) {
        this.asiento49 = asiento49;
    }

    /**
     * Gets the asiento50 of this TmsCorridasVentaTbl.
     * @return the asiento50
     */
    public String getAsiento50() {
        return this.asiento50;
    }

    /**
     * Sets the asiento50 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento50 the new asiento50
     */
    public void setAsiento50(String asiento50) {
        this.asiento50 = asiento50;
    }

    /**
     * Gets the asiento51 of this TmsCorridasVentaTbl.
     * @return the asiento51
     */
    public String getAsiento51() {
        return this.asiento51;
    }

    /**
     * Sets the asiento51 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento51 the new asiento51
     */
    public void setAsiento51(String asiento51) {
        this.asiento51 = asiento51;
    }

    /**
     * Gets the asiento52 of this TmsCorridasVentaTbl.
     * @return the asiento52
     */
    public String getAsiento52() {
        return this.asiento52;
    }

    /**
     * Sets the asiento52 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento52 the new asiento52
     */
    public void setAsiento52(String asiento52) {
        this.asiento52 = asiento52;
    }

    /**
     * Gets the asiento53 of this TmsCorridasVentaTbl.
     * @return the asiento53
     */
    public String getAsiento53() {
        return this.asiento53;
    }

    /**
     * Sets the asiento53 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento53 the new asiento53
     */
    public void setAsiento53(String asiento53) {
        this.asiento53 = asiento53;
    }

    /**
     * Gets the asiento54 of this TmsCorridasVentaTbl.
     * @return the asiento54
     */
    public String getAsiento54() {
        return this.asiento54;
    }

    /**
     * Sets the asiento54 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento54 the new asiento54
     */
    public void setAsiento54(String asiento54) {
        this.asiento54 = asiento54;
    }

    /**
     * Gets the asiento55 of this TmsCorridasVentaTbl.
     * @return the asiento55
     */
    public String getAsiento55() {
        return this.asiento55;
    }

    /**
     * Sets the asiento55 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento55 the new asiento55
     */
    public void setAsiento55(String asiento55) {
        this.asiento55 = asiento55;
    }

    /**
     * Gets the asiento56 of this TmsCorridasVentaTbl.
     * @return the asiento56
     */
    public String getAsiento56() {
        return this.asiento56;
    }

    /**
     * Sets the asiento56 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento56 the new asiento56
     */
    public void setAsiento56(String asiento56) {
        this.asiento56 = asiento56;
    }

    /**
     * Gets the asiento57 of this TmsCorridasVentaTbl.
     * @return the asiento57
     */
    public String getAsiento57() {
        return this.asiento57;
    }

    /**
     * Sets the asiento57 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento57 the new asiento57
     */
    public void setAsiento57(String asiento57) {
        this.asiento57 = asiento57;
    }

    /**
     * Gets the asiento58 of this TmsCorridasVentaTbl.
     * @return the asiento58
     */
    public String getAsiento58() {
        return this.asiento58;
    }

    /**
     * Sets the asiento58 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento58 the new asiento58
     */
    public void setAsiento58(String asiento58) {
        this.asiento58 = asiento58;
    }

    /**
     * Gets the asiento59 of this TmsCorridasVentaTbl.
     * @return the asiento59
     */
    public String getAsiento59() {
        return this.asiento59;
    }

    /**
     * Sets the asiento59 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento59 the new asiento59
     */
    public void setAsiento59(String asiento59) {
        this.asiento59 = asiento59;
    }

    /**
     * Gets the asiento60 of this TmsCorridasVentaTbl.
     * @return the asiento60
     */
    public String getAsiento60() {
        return this.asiento60;
    }

    /**
     * Sets the asiento60 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento60 the new asiento60
     */
    public void setAsiento60(String asiento60) {
        this.asiento60 = asiento60;
    }

    /**
     * Gets the asiento61 of this TmsCorridasVentaTbl.
     * @return the asiento61
     */
    public String getAsiento61() {
        return this.asiento61;
    }

    /**
     * Sets the asiento61 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento61 the new asiento61
     */
    public void setAsiento61(String asiento61) {
        this.asiento61 = asiento61;
    }

    /**
     * Gets the asiento62 of this TmsCorridasVentaTbl.
     * @return the asiento62
     */
    public String getAsiento62() {
        return this.asiento62;
    }

    /**
     * Sets the asiento62 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento62 the new asiento62
     */
    public void setAsiento62(String asiento62) {
        this.asiento62 = asiento62;
    }

    /**
     * Gets the asiento63 of this TmsCorridasVentaTbl.
     * @return the asiento63
     */
    public String getAsiento63() {
        return this.asiento63;
    }

    /**
     * Sets the asiento63 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento63 the new asiento63
     */
    public void setAsiento63(String asiento63) {
        this.asiento63 = asiento63;
    }

    /**
     * Gets the asiento64 of this TmsCorridasVentaTbl.
     * @return the asiento64
     */
    public String getAsiento64() {
        return this.asiento64;
    }

    /**
     * Sets the asiento64 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento64 the new asiento64
     */
    public void setAsiento64(String asiento64) {
        this.asiento64 = asiento64;
    }

    /**
     * Gets the asiento65 of this TmsCorridasVentaTbl.
     * @return the asiento65
     */
    public String getAsiento65() {
        return this.asiento65;
    }

    /**
     * Sets the asiento65 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento65 the new asiento65
     */
    public void setAsiento65(String asiento65) {
        this.asiento65 = asiento65;
    }

    /**
     * Gets the asiento66 of this TmsCorridasVentaTbl.
     * @return the asiento66
     */
    public String getAsiento66() {
        return this.asiento66;
    }

    /**
     * Sets the asiento66 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento66 the new asiento66
     */
    public void setAsiento66(String asiento66) {
        this.asiento66 = asiento66;
    }

    /**
     * Gets the asiento67 of this TmsCorridasVentaTbl.
     * @return the asiento67
     */
    public String getAsiento67() {
        return this.asiento67;
    }

    /**
     * Sets the asiento67 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento67 the new asiento67
     */
    public void setAsiento67(String asiento67) {
        this.asiento67 = asiento67;
    }

    /**
     * Gets the asiento68 of this TmsCorridasVentaTbl.
     * @return the asiento68
     */
    public String getAsiento68() {
        return this.asiento68;
    }

    /**
     * Sets the asiento68 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento68 the new asiento68
     */
    public void setAsiento68(String asiento68) {
        this.asiento68 = asiento68;
    }

    /**
     * Gets the asiento69 of this TmsCorridasVentaTbl.
     * @return the asiento69
     */
    public String getAsiento69() {
        return this.asiento69;
    }

    /**
     * Sets the asiento69 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento69 the new asiento69
     */
    public void setAsiento69(String asiento69) {
        this.asiento69 = asiento69;
    }

    /**
     * Gets the asiento70 of this TmsCorridasVentaTbl.
     * @return the asiento70
     */
    public String getAsiento70() {
        return this.asiento70;
    }

    /**
     * Sets the asiento70 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento70 the new asiento70
     */
    public void setAsiento70(String asiento70) {
        this.asiento70 = asiento70;
    }

    /**
     * Gets the asiento71 of this TmsCorridasVentaTbl.
     * @return the asiento71
     */
    public String getAsiento71() {
        return this.asiento71;
    }

    /**
     * Sets the asiento71 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento71 the new asiento71
     */
    public void setAsiento71(String asiento71) {
        this.asiento71 = asiento71;
    }

    /**
     * Gets the asiento72 of this TmsCorridasVentaTbl.
     * @return the asiento72
     */
    public String getAsiento72() {
        return this.asiento72;
    }

    /**
     * Sets the asiento72 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento72 the new asiento72
     */
    public void setAsiento72(String asiento72) {
        this.asiento72 = asiento72;
    }

    /**
     * Gets the asiento73 of this TmsCorridasVentaTbl.
     * @return the asiento73
     */
    public String getAsiento73() {
        return this.asiento73;
    }

    /**
     * Sets the asiento73 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento73 the new asiento73
     */
    public void setAsiento73(String asiento73) {
        this.asiento73 = asiento73;
    }

    /**
     * Gets the asiento74 of this TmsCorridasVentaTbl.
     * @return the asiento74
     */
    public String getAsiento74() {
        return this.asiento74;
    }

    /**
     * Sets the asiento74 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento74 the new asiento74
     */
    public void setAsiento74(String asiento74) {
        this.asiento74 = asiento74;
    }

    /**
     * Gets the asiento75 of this TmsCorridasVentaTbl.
     * @return the asiento75
     */
    public String getAsiento75() {
        return this.asiento75;
    }

    /**
     * Sets the asiento75 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento75 the new asiento75
     */
    public void setAsiento75(String asiento75) {
        this.asiento75 = asiento75;
    }

    /**
     * Gets the asiento76 of this TmsCorridasVentaTbl.
     * @return the asiento76
     */
    public String getAsiento76() {
        return this.asiento76;
    }

    /**
     * Sets the asiento76 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento76 the new asiento76
     */
    public void setAsiento76(String asiento76) {
        this.asiento76 = asiento76;
    }

    /**
     * Gets the asiento77 of this TmsCorridasVentaTbl.
     * @return the asiento77
     */
    public String getAsiento77() {
        return this.asiento77;
    }

    /**
     * Sets the asiento77 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento77 the new asiento77
     */
    public void setAsiento77(String asiento77) {
        this.asiento77 = asiento77;
    }

    /**
     * Gets the asiento78 of this TmsCorridasVentaTbl.
     * @return the asiento78
     */
    public String getAsiento78() {
        return this.asiento78;
    }

    /**
     * Sets the asiento78 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento78 the new asiento78
     */
    public void setAsiento78(String asiento78) {
        this.asiento78 = asiento78;
    }

    /**
     * Gets the asiento79 of this TmsCorridasVentaTbl.
     * @return the asiento79
     */
    public String getAsiento79() {
        return this.asiento79;
    }

    /**
     * Sets the asiento79 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento79 the new asiento79
     */
    public void setAsiento79(String asiento79) {
        this.asiento79 = asiento79;
    }

    /**
     * Gets the asiento80 of this TmsCorridasVentaTbl.
     * @return the asiento80
     */
    public String getAsiento80() {
        return this.asiento80;
    }

    /**
     * Sets the asiento80 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento80 the new asiento80
     */
    public void setAsiento80(String asiento80) {
        this.asiento80 = asiento80;
    }

    /**
     * Gets the asiento81 of this TmsCorridasVentaTbl.
     * @return the asiento81
     */
    public String getAsiento81() {
        return this.asiento81;
    }

    /**
     * Sets the asiento81 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento81 the new asiento81
     */
    public void setAsiento81(String asiento81) {
        this.asiento81 = asiento81;
    }

    /**
     * Gets the asiento82 of this TmsCorridasVentaTbl.
     * @return the asiento82
     */
    public String getAsiento82() {
        return this.asiento82;
    }

    /**
     * Sets the asiento82 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento82 the new asiento82
     */
    public void setAsiento82(String asiento82) {
        this.asiento82 = asiento82;
    }

    /**
     * Gets the asiento83 of this TmsCorridasVentaTbl.
     * @return the asiento83
     */
    public String getAsiento83() {
        return this.asiento83;
    }

    /**
     * Sets the asiento83 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento83 the new asiento83
     */
    public void setAsiento83(String asiento83) {
        this.asiento83 = asiento83;
    }

    /**
     * Gets the asiento84 of this TmsCorridasVentaTbl.
     * @return the asiento84
     */
    public String getAsiento84() {
        return this.asiento84;
    }

    /**
     * Sets the asiento84 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento84 the new asiento84
     */
    public void setAsiento84(String asiento84) {
        this.asiento84 = asiento84;
    }

    /**
     * Gets the asiento85 of this TmsCorridasVentaTbl.
     * @return the asiento85
     */
    public String getAsiento85() {
        return this.asiento85;
    }

    /**
     * Sets the asiento85 of this TmsCorridasVentaTbl to the specified value.
     * @param asiento85 the new asiento85
     */
    public void setAsiento85(String asiento85) {
        this.asiento85 = asiento85;
    }

    /**
     * Gets the adicional1 of this TmsCorridasVentaTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsCorridasVentaTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsCorridasVentaTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsCorridasVentaTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsCorridasVentaTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsCorridasVentaTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsCorridasVentaTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsCorridasVentaTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsCorridasVentaTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsCorridasVentaTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the adicional6 of this TmsCorridasVentaTbl.
     * @return the adicional6
     */
    public String getAdicional6() {
        return this.adicional6;
    }

    /**
     * Sets the adicional6 of this TmsCorridasVentaTbl to the specified value.
     * @param adicional6 the new adicional6
     */
    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    /**
     * Gets the adicional7 of this TmsCorridasVentaTbl.
     * @return the adicional7
     */
    public String getAdicional7() {
        return this.adicional7;
    }

    /**
     * Sets the adicional7 of this TmsCorridasVentaTbl to the specified value.
     * @param adicional7 the new adicional7
     */
    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    /**
     * Gets the adicional8 of this TmsCorridasVentaTbl.
     * @return the adicional8
     */
    public String getAdicional8() {
        return this.adicional8;
    }

    /**
     * Sets the adicional8 of this TmsCorridasVentaTbl to the specified value.
     * @param adicional8 the new adicional8
     */
    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    /**
     * Gets the adicional9 of this TmsCorridasVentaTbl.
     * @return the adicional9
     */
    public String getAdicional9() {
        return this.adicional9;
    }

    /**
     * Sets the adicional9 of this TmsCorridasVentaTbl to the specified value.
     * @param adicional9 the new adicional9
     */
    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    /**
     * Gets the adicional10 of this TmsCorridasVentaTbl.
     * @return the adicional10
     */
    public String getAdicional10() {
        return this.adicional10;
    }

    /**
     * Sets the adicional10 of this TmsCorridasVentaTbl to the specified value.
     * @param adicional10 the new adicional10
     */
    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.corridaId != null ? this.corridaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsCorridasVentaTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsCorridasVentaTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsCorridasVentaTbl)) {
            return false;
        }
        TmsCorridasVentaTbl other = (TmsCorridasVentaTbl)object;
        if (this.corridaId != other.corridaId && (this.corridaId == null || !this.corridaId.equals(other.corridaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmspuesrtas.entidad.TmsCorridasVentaTbl[corridaId=" + corridaId + "]";
    }
    
    public Vector getAsientos(){    
        Vector vAsientos = new Vector();
        vAsientos.add(this.getAsiento1());vAsientos.add(this.getAsiento2());vAsientos.add(this.getAsiento3());vAsientos.add(this.getAsiento4());vAsientos.add(this.getAsiento5());vAsientos.add(this.getAsiento6());vAsientos.add(this.getAsiento7());vAsientos.add(this.getAsiento8());vAsientos.add(this.getAsiento9());vAsientos.add(this.getAsiento10());
        vAsientos.add(this.getAsiento11());vAsientos.add(this.getAsiento12());vAsientos.add(this.getAsiento13());vAsientos.add(this.getAsiento14());vAsientos.add(this.getAsiento15());vAsientos.add(this.getAsiento16());vAsientos.add(this.getAsiento17());vAsientos.add(this.getAsiento18());vAsientos.add(this.getAsiento19());vAsientos.add(this.getAsiento20());
        vAsientos.add(this.getAsiento21());vAsientos.add(this.getAsiento22());vAsientos.add(this.getAsiento23());vAsientos.add(this.getAsiento24());vAsientos.add(this.getAsiento25());vAsientos.add(this.getAsiento26());vAsientos.add(this.getAsiento27());vAsientos.add(this.getAsiento28());vAsientos.add(this.getAsiento29());vAsientos.add(this.getAsiento30());
        vAsientos.add(this.getAsiento31());vAsientos.add(this.getAsiento32());vAsientos.add(this.getAsiento33());vAsientos.add(this.getAsiento34());vAsientos.add(this.getAsiento35());vAsientos.add(this.getAsiento36());vAsientos.add(this.getAsiento37());vAsientos.add(this.getAsiento38());vAsientos.add(this.getAsiento39());vAsientos.add(this.getAsiento40());
        vAsientos.add(this.getAsiento41());vAsientos.add(this.getAsiento42());vAsientos.add(this.getAsiento43());vAsientos.add(this.getAsiento44());vAsientos.add(this.getAsiento45());vAsientos.add(this.getAsiento46());vAsientos.add(this.getAsiento47());vAsientos.add(this.getAsiento48());vAsientos.add(this.getAsiento49());vAsientos.add(this.getAsiento50());
        vAsientos.add(this.getAsiento51());vAsientos.add(this.getAsiento52());vAsientos.add(this.getAsiento53());vAsientos.add(this.getAsiento54());vAsientos.add(this.getAsiento55());vAsientos.add(this.getAsiento56());vAsientos.add(this.getAsiento57());vAsientos.add(this.getAsiento58());vAsientos.add(this.getAsiento59());vAsientos.add(this.getAsiento60());
        vAsientos.add(this.getAsiento61());vAsientos.add(this.getAsiento62());vAsientos.add(this.getAsiento63());vAsientos.add(this.getAsiento64());vAsientos.add(this.getAsiento65());vAsientos.add(this.getAsiento66());vAsientos.add(this.getAsiento67());vAsientos.add(this.getAsiento68());vAsientos.add(this.getAsiento69());vAsientos.add(this.getAsiento70());
        vAsientos.add(this.getAsiento71());vAsientos.add(this.getAsiento72());vAsientos.add(this.getAsiento73());vAsientos.add(this.getAsiento74());vAsientos.add(this.getAsiento75());vAsientos.add(this.getAsiento76());vAsientos.add(this.getAsiento77());vAsientos.add(this.getAsiento78());vAsientos.add(this.getAsiento79());vAsientos.add(this.getAsiento80());
        vAsientos.add(this.getAsiento81());vAsientos.add(this.getAsiento82());vAsientos.add(this.getAsiento83());vAsientos.add(this.getAsiento84());vAsientos.add(this.getAsiento85());
        return vAsientos;
    }

    public String getReplicacionOrigen() {
        return replicacionOrigen;
    }

    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }
    
    
}
