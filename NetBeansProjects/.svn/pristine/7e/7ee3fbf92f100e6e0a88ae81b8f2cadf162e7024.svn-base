/*
 * TmsRolesBaseLineasTbl.java
 *
 * Created on 26 de diciembre de 2007, 12:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsRolesBaseLineasTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_ROLES_BASE_LINEAS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRolBaseLineaId", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rolBaseLineaId = :rolBaseLineaId"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByNumeroCuadro", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.numeroCuadro = :numeroCuadro"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByCantidadSalidas", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.cantidadSalidas = :cantidadSalidas"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByKmsEstimadosCuadro", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.kmsEstimadosCuadro = :kmsEstimadosCuadro"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByCuadroGuardia", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.cuadroGuardia = :cuadroGuardia"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByCuadroQuedada", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.cuadroQuedada = :cuadroQuedada"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByCuadroCondicionExtra", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.cuadroCondicionExtra = :cuadroCondicionExtra"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida1Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida1Id = :ofertaCorrida1Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida1Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida1Id = :empresaCorrida1Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida1Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida1Id = :rutaCorrida1Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida1Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida1Id = :origenCorrida1Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida1", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida1 = :horarioCorrida1"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida2Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida2Id = :ofertaCorrida2Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida2Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida2Id = :empresaCorrida2Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida2Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida2Id = :rutaCorrida2Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida2Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida2Id = :origenCorrida2Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida2", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida2 = :horarioCorrida2"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida3Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida3Id = :ofertaCorrida3Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida3Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida3Id = :empresaCorrida3Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida3Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida3Id = :rutaCorrida3Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida3Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida3Id = :origenCorrida3Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida3", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida3 = :horarioCorrida3"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida4Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida4Id = :ofertaCorrida4Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida4Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida4Id = :empresaCorrida4Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida4Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida4Id = :rutaCorrida4Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida4Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida4Id = :origenCorrida4Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida4", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida4 = :horarioCorrida4"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida5Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida5Id = :ofertaCorrida5Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida5Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida5Id = :empresaCorrida5Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida5Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida5Id = :rutaCorrida5Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida5Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida5Id = :origenCorrida5Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida5", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida5 = :horarioCorrida5"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida6Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida6Id = :ofertaCorrida6Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida6Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida6Id = :empresaCorrida6Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida6Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida6Id = :rutaCorrida6Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida6Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida6Id = :origenCorrida6Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida6", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida6 = :horarioCorrida6"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida7Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida7Id = :ofertaCorrida7Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida7Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida7Id = :empresaCorrida7Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida7Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida7Id = :rutaCorrida7Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida7Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida7Id = :origenCorrida7Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida7", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida7 = :horarioCorrida7"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida8Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida8Id = :ofertaCorrida8Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida8Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida8Id = :empresaCorrida8Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida8Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida8Id = :rutaCorrida8Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida8Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida8Id = :origenCorrida8Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida8", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida8 = :horarioCorrida8"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida9Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida9Id = :ofertaCorrida9Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida9Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida9Id = :empresaCorrida9Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida9Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida9Id = :rutaCorrida9Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida9Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida9Id = :origenCorrida9Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida9", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida9 = :horarioCorrida9"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida10Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida10Id = :ofertaCorrida10Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida10Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida10Id = :empresaCorrida10Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida10Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida10Id = :rutaCorrida10Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida10Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida10Id = :origenCorrida10Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida10", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida10 = :horarioCorrida10"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida11Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida11Id = :ofertaCorrida11Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida11Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida11Id = :empresaCorrida11Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida11Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida11Id = :rutaCorrida11Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida11Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida11Id = :origenCorrida11Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida11", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida11 = :horarioCorrida11"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida12Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida12Id = :ofertaCorrida12Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida12Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida12Id = :empresaCorrida12Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida12Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida12Id = :rutaCorrida12Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida12Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida12Id = :origenCorrida12Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida12", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida12 = :horarioCorrida12"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida13Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida13Id = :ofertaCorrida13Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida13Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida13Id = :empresaCorrida13Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida13Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida13Id = :rutaCorrida13Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida13Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida13Id = :origenCorrida13Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida13", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida13 = :horarioCorrida13"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida14Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida14Id = :ofertaCorrida14Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida14Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida14Id = :empresaCorrida14Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida14Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida14Id = :rutaCorrida14Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida14Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida14Id = :origenCorrida14Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida14", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida14 = :horarioCorrida14"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida15Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida15Id = :ofertaCorrida15Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida15Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida15Id = :empresaCorrida15Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida15Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida15Id = :rutaCorrida15Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida15Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida15Id = :origenCorrida15Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida15", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida15 = :horarioCorrida15"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida16Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida16Id = :ofertaCorrida16Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida16Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida16Id = :empresaCorrida16Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida16Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida16Id = :rutaCorrida16Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida16Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida16Id = :origenCorrida16Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida16", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida16 = :horarioCorrida16"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida17Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida17Id = :ofertaCorrida17Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida17Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida17Id = :empresaCorrida17Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida17Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida17Id = :rutaCorrida17Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida17Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida17Id = :origenCorrida17Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida17", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida17 = :horarioCorrida17"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida18Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida18Id = :ofertaCorrida18Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida18Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida18Id = :empresaCorrida18Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida18Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida18Id = :rutaCorrida18Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida18Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida18Id = :origenCorrida18Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida18", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida18 = :horarioCorrida18"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida19Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida19Id = :ofertaCorrida19Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida19Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida19Id = :empresaCorrida19Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida19Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida19Id = :rutaCorrida19Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida19Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida19Id = :origenCorrida19Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida19", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida19 = :horarioCorrida19"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida20Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida20Id = :ofertaCorrida20Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida20Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida20Id = :empresaCorrida20Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida20Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida20Id = :rutaCorrida20Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida20Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida20Id = :origenCorrida20Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida20", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida20 = :horarioCorrida20"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida21Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida21Id = :ofertaCorrida21Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida21Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida21Id = :empresaCorrida21Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida21Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida21Id = :rutaCorrida21Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida21Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida21Id = :origenCorrida21Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida21", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida21 = :horarioCorrida21"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida22Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida22Id = :ofertaCorrida22Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida22Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida22Id = :empresaCorrida22Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida22Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida22Id = :rutaCorrida22Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida22Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida22Id = :origenCorrida22Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida22", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida22 = :horarioCorrida22"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida23Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida23Id = :ofertaCorrida23Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida23Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida23Id = :empresaCorrida23Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida23Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida23Id = :rutaCorrida23Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida23Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida23Id = :origenCorrida23Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida23", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida23 = :horarioCorrida23"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida24Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida24Id = :ofertaCorrida24Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida24Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida24Id = :empresaCorrida24Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida24Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida24Id = :rutaCorrida24Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida24Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida24Id = :origenCorrida24Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida24", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida24 = :horarioCorrida24"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOfertaCorrida25Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ofertaCorrida25Id = :ofertaCorrida25Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByEmpresaCorrida25Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.empresaCorrida25Id = :empresaCorrida25Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByRutaCorrida25Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.rutaCorrida25Id = :rutaCorrida25Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByOrigenCorrida25Id", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.origenCorrida25Id = :origenCorrida25Id"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByHorarioCorrida25", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.horarioCorrida25 = :horarioCorrida25"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByCreadoPor", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByFechaCreacion", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByReplicacionEstado", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsRolesBaseLineasTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsRolesBaseLineasTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsRolesBaseLineasTbl implements Serializable {

    
    @SequenceGenerator(name="TMS_RBL_SEQ",sequenceName="TMS_ROL_BASE_LINEAS_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMS_RBL_SEQ")
    @Id
    @Column(name = "ROL_BASE_LINEA_ID", nullable = false)
    private BigDecimal rolBaseLineaId;

    @Column(name = "NUMERO_CUADRO", nullable = false)
    private BigInteger numeroCuadro;

    @Column(name = "CANTIDAD_SALIDAS")
    private BigInteger cantidadSalidas;

    @Column(name = "KMS_ESTIMADOS_CUADRO")
    private BigInteger kmsEstimadosCuadro;

    @Column(name = "CUADRO_GUARDIA", nullable = false)
    private String cuadroGuardia;

    @Column(name = "CUADRO_QUEDADA", nullable = false)
    private String cuadroQuedada;

    @Column(name = "CUADRO_CONDICION_EXTRA", nullable = false)
    private String cuadroCondicionExtra;

    @Column(name = "OFERTA_CORRIDA_1_ID")
    private BigInteger ofertaCorrida1Id;

    @Column(name = "EMPRESA_CORRIDA_1_ID")
    private BigInteger empresaCorrida1Id;

    @Column(name = "RUTA_CORRIDA_1_ID")
    private BigInteger rutaCorrida1Id;

    @Column(name = "ORIGEN_CORRIDA_1_ID")
    private BigInteger origenCorrida1Id;

    @Column(name = "HORARIO_CORRIDA_1")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida1;

    @Column(name = "OFERTA_CORRIDA_2_ID")
    private BigInteger ofertaCorrida2Id;

    @Column(name = "EMPRESA_CORRIDA_2_ID")
    private BigInteger empresaCorrida2Id;

    @Column(name = "RUTA_CORRIDA_2_ID")
    private BigInteger rutaCorrida2Id;

    @Column(name = "ORIGEN_CORRIDA_2_ID")
    private BigInteger origenCorrida2Id;

    @Column(name = "HORARIO_CORRIDA_2")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida2;

    @Column(name = "OFERTA_CORRIDA_3_ID")
    private BigInteger ofertaCorrida3Id;

    @Column(name = "EMPRESA_CORRIDA_3_ID")
    private BigInteger empresaCorrida3Id;

    @Column(name = "RUTA_CORRIDA_3_ID")
    private BigInteger rutaCorrida3Id;

    @Column(name = "ORIGEN_CORRIDA_3_ID")
    private BigInteger origenCorrida3Id;

    @Column(name = "HORARIO_CORRIDA_3")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida3;

    @Column(name = "OFERTA_CORRIDA_4_ID")
    private BigInteger ofertaCorrida4Id;

    @Column(name = "EMPRESA_CORRIDA_4_ID")
    private BigInteger empresaCorrida4Id;

    @Column(name = "RUTA_CORRIDA_4_ID")
    private BigInteger rutaCorrida4Id;

    @Column(name = "ORIGEN_CORRIDA_4_ID")
    private BigInteger origenCorrida4Id;

    @Column(name = "HORARIO_CORRIDA_4")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida4;

    @Column(name = "OFERTA_CORRIDA_5_ID")
    private BigInteger ofertaCorrida5Id;

    @Column(name = "EMPRESA_CORRIDA_5_ID")
    private BigInteger empresaCorrida5Id;

    @Column(name = "RUTA_CORRIDA_5_ID")
    private BigInteger rutaCorrida5Id;

    @Column(name = "ORIGEN_CORRIDA_5_ID")
    private BigInteger origenCorrida5Id;

    @Column(name = "HORARIO_CORRIDA_5")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida5;

    @Column(name = "OFERTA_CORRIDA_6_ID")
    private BigInteger ofertaCorrida6Id;

    @Column(name = "EMPRESA_CORRIDA_6_ID")
    private BigInteger empresaCorrida6Id;

    @Column(name = "RUTA_CORRIDA_6_ID")
    private BigInteger rutaCorrida6Id;

    @Column(name = "ORIGEN_CORRIDA_6_ID")
    private BigInteger origenCorrida6Id;

    @Column(name = "HORARIO_CORRIDA_6")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida6;

    @Column(name = "OFERTA_CORRIDA_7_ID")
    private BigInteger ofertaCorrida7Id;

    @Column(name = "EMPRESA_CORRIDA_7_ID")
    private BigInteger empresaCorrida7Id;

    @Column(name = "RUTA_CORRIDA_7_ID")
    private BigInteger rutaCorrida7Id;

    @Column(name = "ORIGEN_CORRIDA_7_ID")
    private BigInteger origenCorrida7Id;

    @Column(name = "HORARIO_CORRIDA_7")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida7;

    @Column(name = "OFERTA_CORRIDA_8_ID")
    private BigInteger ofertaCorrida8Id;

    @Column(name = "EMPRESA_CORRIDA_8_ID")
    private BigInteger empresaCorrida8Id;

    @Column(name = "RUTA_CORRIDA_8_ID")
    private BigInteger rutaCorrida8Id;

    @Column(name = "ORIGEN_CORRIDA_8_ID")
    private BigInteger origenCorrida8Id;

    @Column(name = "HORARIO_CORRIDA_8")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida8;

    @Column(name = "OFERTA_CORRIDA_9_ID")
    private BigInteger ofertaCorrida9Id;

    @Column(name = "EMPRESA_CORRIDA_9_ID")
    private BigInteger empresaCorrida9Id;

    @Column(name = "RUTA_CORRIDA_9_ID")
    private BigInteger rutaCorrida9Id;

    @Column(name = "ORIGEN_CORRIDA_9_ID")
    private BigInteger origenCorrida9Id;

    @Column(name = "HORARIO_CORRIDA_9")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida9;

    @Column(name = "OFERTA_CORRIDA_10_ID")
    private BigInteger ofertaCorrida10Id;

    @Column(name = "EMPRESA_CORRIDA_10_ID")
    private BigInteger empresaCorrida10Id;

    @Column(name = "RUTA_CORRIDA_10_ID")
    private BigInteger rutaCorrida10Id;

    @Column(name = "ORIGEN_CORRIDA_10_ID")
    private BigInteger origenCorrida10Id;

    @Column(name = "HORARIO_CORRIDA_10")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida10;

    @Column(name = "OFERTA_CORRIDA_11_ID")
    private BigInteger ofertaCorrida11Id;

    @Column(name = "EMPRESA_CORRIDA_11_ID")
    private BigInteger empresaCorrida11Id;

    @Column(name = "RUTA_CORRIDA_11_ID")
    private BigInteger rutaCorrida11Id;

    @Column(name = "ORIGEN_CORRIDA_11_ID")
    private BigInteger origenCorrida11Id;

    @Column(name = "HORARIO_CORRIDA_11")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida11;

    @Column(name = "OFERTA_CORRIDA_12_ID")
    private BigInteger ofertaCorrida12Id;

    @Column(name = "EMPRESA_CORRIDA_12_ID")
    private BigInteger empresaCorrida12Id;

    @Column(name = "RUTA_CORRIDA_12_ID")
    private BigInteger rutaCorrida12Id;

    @Column(name = "ORIGEN_CORRIDA_12_ID")
    private BigInteger origenCorrida12Id;

    @Column(name = "HORARIO_CORRIDA_12")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida12;

    @Column(name = "OFERTA_CORRIDA_13_ID")
    private BigInteger ofertaCorrida13Id;

    @Column(name = "EMPRESA_CORRIDA_13_ID")
    private BigInteger empresaCorrida13Id;

    @Column(name = "RUTA_CORRIDA_13_ID")
    private BigInteger rutaCorrida13Id;

    @Column(name = "ORIGEN_CORRIDA_13_ID")
    private BigInteger origenCorrida13Id;

    @Column(name = "HORARIO_CORRIDA_13")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida13;

    @Column(name = "OFERTA_CORRIDA_14_ID")
    private BigInteger ofertaCorrida14Id;

    @Column(name = "EMPRESA_CORRIDA_14_ID")
    private BigInteger empresaCorrida14Id;

    @Column(name = "RUTA_CORRIDA_14_ID")
    private BigInteger rutaCorrida14Id;

    @Column(name = "ORIGEN_CORRIDA_14_ID")
    private BigInteger origenCorrida14Id;

    @Column(name = "HORARIO_CORRIDA_14")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida14;

    @Column(name = "OFERTA_CORRIDA_15_ID")
    private BigInteger ofertaCorrida15Id;

    @Column(name = "EMPRESA_CORRIDA_15_ID")
    private BigInteger empresaCorrida15Id;

    @Column(name = "RUTA_CORRIDA_15_ID")
    private BigInteger rutaCorrida15Id;

    @Column(name = "ORIGEN_CORRIDA_15_ID")
    private BigInteger origenCorrida15Id;

    @Column(name = "HORARIO_CORRIDA_15")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida15;

    @Column(name = "OFERTA_CORRIDA_16_ID")
    private BigInteger ofertaCorrida16Id;

    @Column(name = "EMPRESA_CORRIDA_16_ID")
    private BigInteger empresaCorrida16Id;

    @Column(name = "RUTA_CORRIDA_16_ID")
    private BigInteger rutaCorrida16Id;

    @Column(name = "ORIGEN_CORRIDA_16_ID")
    private BigInteger origenCorrida16Id;

    @Column(name = "HORARIO_CORRIDA_16")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida16;

    @Column(name = "OFERTA_CORRIDA_17_ID")
    private BigInteger ofertaCorrida17Id;

    @Column(name = "EMPRESA_CORRIDA_17_ID")
    private BigInteger empresaCorrida17Id;

    @Column(name = "RUTA_CORRIDA_17_ID")
    private BigInteger rutaCorrida17Id;

    @Column(name = "ORIGEN_CORRIDA_17_ID")
    private BigInteger origenCorrida17Id;

    @Column(name = "HORARIO_CORRIDA_17")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida17;

    @Column(name = "OFERTA_CORRIDA_18_ID")
    private BigInteger ofertaCorrida18Id;

    @Column(name = "EMPRESA_CORRIDA_18_ID")
    private BigInteger empresaCorrida18Id;

    @Column(name = "RUTA_CORRIDA_18_ID")
    private BigInteger rutaCorrida18Id;

    @Column(name = "ORIGEN_CORRIDA_18_ID")
    private BigInteger origenCorrida18Id;

    @Column(name = "HORARIO_CORRIDA_18")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida18;

    @Column(name = "OFERTA_CORRIDA_19_ID")
    private BigInteger ofertaCorrida19Id;

    @Column(name = "EMPRESA_CORRIDA_19_ID")
    private BigInteger empresaCorrida19Id;

    @Column(name = "RUTA_CORRIDA_19_ID")
    private BigInteger rutaCorrida19Id;

    @Column(name = "ORIGEN_CORRIDA_19_ID")
    private BigInteger origenCorrida19Id;

    @Column(name = "HORARIO_CORRIDA_19")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida19;

    @Column(name = "OFERTA_CORRIDA_20_ID")
    private BigInteger ofertaCorrida20Id;

    @Column(name = "EMPRESA_CORRIDA_20_ID")
    private BigInteger empresaCorrida20Id;

    @Column(name = "RUTA_CORRIDA_20_ID")
    private BigInteger rutaCorrida20Id;

    @Column(name = "ORIGEN_CORRIDA_20_ID")
    private BigInteger origenCorrida20Id;

    @Column(name = "HORARIO_CORRIDA_20")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida20;

    @Column(name = "OFERTA_CORRIDA_21_ID")
    private BigInteger ofertaCorrida21Id;

    @Column(name = "EMPRESA_CORRIDA_21_ID")
    private BigInteger empresaCorrida21Id;

    @Column(name = "RUTA_CORRIDA_21_ID")
    private BigInteger rutaCorrida21Id;

    @Column(name = "ORIGEN_CORRIDA_21_ID")
    private BigInteger origenCorrida21Id;

    @Column(name = "HORARIO_CORRIDA_21")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida21;

    @Column(name = "OFERTA_CORRIDA_22_ID")
    private BigInteger ofertaCorrida22Id;

    @Column(name = "EMPRESA_CORRIDA_22_ID")
    private BigInteger empresaCorrida22Id;

    @Column(name = "RUTA_CORRIDA_22_ID")
    private BigInteger rutaCorrida22Id;

    @Column(name = "ORIGEN_CORRIDA_22_ID")
    private BigInteger origenCorrida22Id;

    @Column(name = "HORARIO_CORRIDA_22")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida22;

    @Column(name = "OFERTA_CORRIDA_23_ID")
    private BigInteger ofertaCorrida23Id;

    @Column(name = "EMPRESA_CORRIDA_23_ID")
    private BigInteger empresaCorrida23Id;

    @Column(name = "RUTA_CORRIDA_23_ID")
    private BigInteger rutaCorrida23Id;

    @Column(name = "ORIGEN_CORRIDA_23_ID")
    private BigInteger origenCorrida23Id;

    @Column(name = "HORARIO_CORRIDA_23")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida23;

    @Column(name = "OFERTA_CORRIDA_24_ID")
    private BigInteger ofertaCorrida24Id;

    @Column(name = "EMPRESA_CORRIDA_24_ID")
    private BigInteger empresaCorrida24Id;

    @Column(name = "RUTA_CORRIDA_24_ID")
    private BigInteger rutaCorrida24Id;

    @Column(name = "ORIGEN_CORRIDA_24_ID")
    private BigInteger origenCorrida24Id;

    @Column(name = "HORARIO_CORRIDA_24")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida24;

    @Column(name = "OFERTA_CORRIDA_25_ID")
    private BigInteger ofertaCorrida25Id;

    @Column(name = "EMPRESA_CORRIDA_25_ID")
    private BigInteger empresaCorrida25Id;

    @Column(name = "RUTA_CORRIDA_25_ID")
    private BigInteger rutaCorrida25Id;

    @Column(name = "ORIGEN_CORRIDA_25_ID")
    private BigInteger origenCorrida25Id;

    @Column(name = "HORARIO_CORRIDA_25")
    @Temporal(TemporalType.TIME)
    private Date horarioCorrida25;

    @Column(name = "CREADO_POR", nullable = false)
    private BigInteger creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;

    @Column(name = "REPLICACION_ESTADO")
    private String replicacionEstado;

    @Column(name = "REPLICACION_INTENTOS")
    private BigInteger replicacionIntentos;

    @Column(name = "REPLICACION_ORIGEN")
    private String replicacionOrigen;

    @JoinColumn(name = "AUTOBUS_ID", referencedColumnName = "AUTOBUS_ID")
    @ManyToOne
    private TmsAutobusesTbl autobusId;

    @JoinColumn(name = "ROL_BASE_ID", referencedColumnName = "ROL_BASE_ID")
    @ManyToOne
    private TmsRolesBaseTbl rolBaseId;
    
    /** Creates a new instance of TmsRolesBaseLineasTbl */
    public TmsRolesBaseLineasTbl() {
    }

    /**
     * Creates a new instance of TmsRolesBaseLineasTbl with the specified values.
     * @param rolBaseLineaId the rolBaseLineaId of the TmsRolesBaseLineasTbl
     */
    public TmsRolesBaseLineasTbl(BigDecimal rolBaseLineaId) {
        this.rolBaseLineaId = rolBaseLineaId;
    }

    /**
     * Creates a new instance of TmsRolesBaseLineasTbl with the specified values.
     * @param rolBaseLineaId the rolBaseLineaId of the TmsRolesBaseLineasTbl
     * @param numeroCuadro the numeroCuadro of the TmsRolesBaseLineasTbl
     * @param cuadroGuardia the cuadroGuardia of the TmsRolesBaseLineasTbl
     * @param cuadroQuedada the cuadroQuedada of the TmsRolesBaseLineasTbl
     * @param cuadroCondicionExtra the cuadroCondicionExtra of the TmsRolesBaseLineasTbl
     * @param creadoPor the creadoPor of the TmsRolesBaseLineasTbl
     * @param fechaCreacion the fechaCreacion of the TmsRolesBaseLineasTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsRolesBaseLineasTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsRolesBaseLineasTbl
     */
    public TmsRolesBaseLineasTbl(BigDecimal rolBaseLineaId, BigInteger numeroCuadro, String cuadroGuardia, String cuadroQuedada, String cuadroCondicionExtra, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.rolBaseLineaId = rolBaseLineaId;
        this.numeroCuadro = numeroCuadro;
        this.cuadroGuardia = cuadroGuardia;
        this.cuadroQuedada = cuadroQuedada;
        this.cuadroCondicionExtra = cuadroCondicionExtra;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the rolBaseLineaId of this TmsRolesBaseLineasTbl.
     * @return the rolBaseLineaId
     */
    public BigDecimal getRolBaseLineaId() {
        return this.rolBaseLineaId;
    }

    /**
     * Sets the rolBaseLineaId of this TmsRolesBaseLineasTbl to the specified value.
     * @param rolBaseLineaId the new rolBaseLineaId
     */
    public void setRolBaseLineaId(BigDecimal rolBaseLineaId) {
        this.rolBaseLineaId = rolBaseLineaId;
    }

    /**
     * Gets the numeroCuadro of this TmsRolesBaseLineasTbl.
     * @return the numeroCuadro
     */
    public BigInteger getNumeroCuadro() {
        return this.numeroCuadro;
    }

    /**
     * Sets the numeroCuadro of this TmsRolesBaseLineasTbl to the specified value.
     * @param numeroCuadro the new numeroCuadro
     */
    public void setNumeroCuadro(BigInteger numeroCuadro) {
        this.numeroCuadro = numeroCuadro;
    }

    /**
     * Gets the cantidadSalidas of this TmsRolesBaseLineasTbl.
     * @return the cantidadSalidas
     */
    public BigInteger getCantidadSalidas() {
        return this.cantidadSalidas;
    }

    /**
     * Sets the cantidadSalidas of this TmsRolesBaseLineasTbl to the specified value.
     * @param cantidadSalidas the new cantidadSalidas
     */
    public void setCantidadSalidas(BigInteger cantidadSalidas) {
        this.cantidadSalidas = cantidadSalidas;
    }

    /**
     * Gets the kmsEstimadosCuadro of this TmsRolesBaseLineasTbl.
     * @return the kmsEstimadosCuadro
     */
    public BigInteger getKmsEstimadosCuadro() {
        return this.kmsEstimadosCuadro;
    }

    /**
     * Sets the kmsEstimadosCuadro of this TmsRolesBaseLineasTbl to the specified value.
     * @param kmsEstimadosCuadro the new kmsEstimadosCuadro
     */
    public void setKmsEstimadosCuadro(BigInteger kmsEstimadosCuadro) {
        this.kmsEstimadosCuadro = kmsEstimadosCuadro;
    }

    /**
     * Gets the cuadroGuardia of this TmsRolesBaseLineasTbl.
     * @return the cuadroGuardia
     */
    public String getCuadroGuardia() {
        return this.cuadroGuardia;
    }

    /**
     * Sets the cuadroGuardia of this TmsRolesBaseLineasTbl to the specified value.
     * @param cuadroGuardia the new cuadroGuardia
     */
    public void setCuadroGuardia(String cuadroGuardia) {
        this.cuadroGuardia = cuadroGuardia;
    }

    /**
     * Gets the cuadroQuedada of this TmsRolesBaseLineasTbl.
     * @return the cuadroQuedada
     */
    public String getCuadroQuedada() {
        return this.cuadroQuedada;
    }

    /**
     * Sets the cuadroQuedada of this TmsRolesBaseLineasTbl to the specified value.
     * @param cuadroQuedada the new cuadroQuedada
     */
    public void setCuadroQuedada(String cuadroQuedada) {
        this.cuadroQuedada = cuadroQuedada;
    }

    /**
     * Gets the cuadroCondicionExtra of this TmsRolesBaseLineasTbl.
     * @return the cuadroCondicionExtra
     */
    public String getCuadroCondicionExtra() {
        return this.cuadroCondicionExtra;
    }

    /**
     * Sets the cuadroCondicionExtra of this TmsRolesBaseLineasTbl to the specified value.
     * @param cuadroCondicionExtra the new cuadroCondicionExtra
     */
    public void setCuadroCondicionExtra(String cuadroCondicionExtra) {
        this.cuadroCondicionExtra = cuadroCondicionExtra;
    }

    /**
     * Gets the ofertaCorrida1Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida1Id
     */
    public BigInteger getOfertaCorrida1Id() {
        return this.ofertaCorrida1Id;
    }

    /**
     * Sets the ofertaCorrida1Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida1Id the new ofertaCorrida1Id
     */
    public void setOfertaCorrida1Id(BigInteger ofertaCorrida1Id) {
        this.ofertaCorrida1Id = ofertaCorrida1Id;
    }

    /**
     * Gets the empresaCorrida1Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida1Id
     */
    public BigInteger getEmpresaCorrida1Id() {
        return this.empresaCorrida1Id;
    }

    /**
     * Sets the empresaCorrida1Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida1Id the new empresaCorrida1Id
     */
    public void setEmpresaCorrida1Id(BigInteger empresaCorrida1Id) {
        this.empresaCorrida1Id = empresaCorrida1Id;
    }

    /**
     * Gets the rutaCorrida1Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida1Id
     */
    public BigInteger getRutaCorrida1Id() {
        return this.rutaCorrida1Id;
    }

    /**
     * Sets the rutaCorrida1Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida1Id the new rutaCorrida1Id
     */
    public void setRutaCorrida1Id(BigInteger rutaCorrida1Id) {
        this.rutaCorrida1Id = rutaCorrida1Id;
    }

    /**
     * Gets the origenCorrida1Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida1Id
     */
    public BigInteger getOrigenCorrida1Id() {
        return this.origenCorrida1Id;
    }

    /**
     * Sets the origenCorrida1Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida1Id the new origenCorrida1Id
     */
    public void setOrigenCorrida1Id(BigInteger origenCorrida1Id) {
        this.origenCorrida1Id = origenCorrida1Id;
    }

    /**
     * Gets the horarioCorrida1 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida1
     */
    public Date getHorarioCorrida1() {
        return this.horarioCorrida1;
    }

    /**
     * Sets the horarioCorrida1 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida1 the new horarioCorrida1
     */
    public void setHorarioCorrida1(Date horarioCorrida1) {
        this.horarioCorrida1 = horarioCorrida1;
    }

    /**
     * Gets the ofertaCorrida2Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida2Id
     */
    public BigInteger getOfertaCorrida2Id() {
        return this.ofertaCorrida2Id;
    }

    /**
     * Sets the ofertaCorrida2Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida2Id the new ofertaCorrida2Id
     */
    public void setOfertaCorrida2Id(BigInteger ofertaCorrida2Id) {
        this.ofertaCorrida2Id = ofertaCorrida2Id;
    }

    /**
     * Gets the empresaCorrida2Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida2Id
     */
    public BigInteger getEmpresaCorrida2Id() {
        return this.empresaCorrida2Id;
    }

    /**
     * Sets the empresaCorrida2Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida2Id the new empresaCorrida2Id
     */
    public void setEmpresaCorrida2Id(BigInteger empresaCorrida2Id) {
        this.empresaCorrida2Id = empresaCorrida2Id;
    }

    /**
     * Gets the rutaCorrida2Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida2Id
     */
    public BigInteger getRutaCorrida2Id() {
        return this.rutaCorrida2Id;
    }

    /**
     * Sets the rutaCorrida2Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida2Id the new rutaCorrida2Id
     */
    public void setRutaCorrida2Id(BigInteger rutaCorrida2Id) {
        this.rutaCorrida2Id = rutaCorrida2Id;
    }

    /**
     * Gets the origenCorrida2Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida2Id
     */
    public BigInteger getOrigenCorrida2Id() {
        return this.origenCorrida2Id;
    }

    /**
     * Sets the origenCorrida2Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida2Id the new origenCorrida2Id
     */
    public void setOrigenCorrida2Id(BigInteger origenCorrida2Id) {
        this.origenCorrida2Id = origenCorrida2Id;
    }

    /**
     * Gets the horarioCorrida2 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida2
     */
    public Date getHorarioCorrida2() {
        return this.horarioCorrida2;
    }

    /**
     * Sets the horarioCorrida2 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida2 the new horarioCorrida2
     */
    public void setHorarioCorrida2(Date horarioCorrida2) {
        this.horarioCorrida2 = horarioCorrida2;
    }

    /**
     * Gets the ofertaCorrida3Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida3Id
     */
    public BigInteger getOfertaCorrida3Id() {
        return this.ofertaCorrida3Id;
    }

    /**
     * Sets the ofertaCorrida3Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida3Id the new ofertaCorrida3Id
     */
    public void setOfertaCorrida3Id(BigInteger ofertaCorrida3Id) {
        this.ofertaCorrida3Id = ofertaCorrida3Id;
    }

    /**
     * Gets the empresaCorrida3Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida3Id
     */
    public BigInteger getEmpresaCorrida3Id() {
        return this.empresaCorrida3Id;
    }

    /**
     * Sets the empresaCorrida3Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida3Id the new empresaCorrida3Id
     */
    public void setEmpresaCorrida3Id(BigInteger empresaCorrida3Id) {
        this.empresaCorrida3Id = empresaCorrida3Id;
    }

    /**
     * Gets the rutaCorrida3Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida3Id
     */
    public BigInteger getRutaCorrida3Id() {
        return this.rutaCorrida3Id;
    }

    /**
     * Sets the rutaCorrida3Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida3Id the new rutaCorrida3Id
     */
    public void setRutaCorrida3Id(BigInteger rutaCorrida3Id) {
        this.rutaCorrida3Id = rutaCorrida3Id;
    }

    /**
     * Gets the origenCorrida3Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida3Id
     */
    public BigInteger getOrigenCorrida3Id() {
        return this.origenCorrida3Id;
    }

    /**
     * Sets the origenCorrida3Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida3Id the new origenCorrida3Id
     */
    public void setOrigenCorrida3Id(BigInteger origenCorrida3Id) {
        this.origenCorrida3Id = origenCorrida3Id;
    }

    /**
     * Gets the horarioCorrida3 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida3
     */
    public Date getHorarioCorrida3() {
        return this.horarioCorrida3;
    }

    /**
     * Sets the horarioCorrida3 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida3 the new horarioCorrida3
     */
    public void setHorarioCorrida3(Date horarioCorrida3) {
        this.horarioCorrida3 = horarioCorrida3;
    }

    /**
     * Gets the ofertaCorrida4Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida4Id
     */
    public BigInteger getOfertaCorrida4Id() {
        return this.ofertaCorrida4Id;
    }

    /**
     * Sets the ofertaCorrida4Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida4Id the new ofertaCorrida4Id
     */
    public void setOfertaCorrida4Id(BigInteger ofertaCorrida4Id) {
        this.ofertaCorrida4Id = ofertaCorrida4Id;
    }

    /**
     * Gets the empresaCorrida4Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida4Id
     */
    public BigInteger getEmpresaCorrida4Id() {
        return this.empresaCorrida4Id;
    }

    /**
     * Sets the empresaCorrida4Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida4Id the new empresaCorrida4Id
     */
    public void setEmpresaCorrida4Id(BigInteger empresaCorrida4Id) {
        this.empresaCorrida4Id = empresaCorrida4Id;
    }

    /**
     * Gets the rutaCorrida4Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida4Id
     */
    public BigInteger getRutaCorrida4Id() {
        return this.rutaCorrida4Id;
    }

    /**
     * Sets the rutaCorrida4Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida4Id the new rutaCorrida4Id
     */
    public void setRutaCorrida4Id(BigInteger rutaCorrida4Id) {
        this.rutaCorrida4Id = rutaCorrida4Id;
    }

    /**
     * Gets the origenCorrida4Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida4Id
     */
    public BigInteger getOrigenCorrida4Id() {
        return this.origenCorrida4Id;
    }

    /**
     * Sets the origenCorrida4Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida4Id the new origenCorrida4Id
     */
    public void setOrigenCorrida4Id(BigInteger origenCorrida4Id) {
        this.origenCorrida4Id = origenCorrida4Id;
    }

    /**
     * Gets the horarioCorrida4 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida4
     */
    public Date getHorarioCorrida4() {
        return this.horarioCorrida4;
    }

    /**
     * Sets the horarioCorrida4 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida4 the new horarioCorrida4
     */
    public void setHorarioCorrida4(Date horarioCorrida4) {
        this.horarioCorrida4 = horarioCorrida4;
    }

    /**
     * Gets the ofertaCorrida5Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida5Id
     */
    public BigInteger getOfertaCorrida5Id() {
        return this.ofertaCorrida5Id;
    }

    /**
     * Sets the ofertaCorrida5Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida5Id the new ofertaCorrida5Id
     */
    public void setOfertaCorrida5Id(BigInteger ofertaCorrida5Id) {
        this.ofertaCorrida5Id = ofertaCorrida5Id;
    }

    /**
     * Gets the empresaCorrida5Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida5Id
     */
    public BigInteger getEmpresaCorrida5Id() {
        return this.empresaCorrida5Id;
    }

    /**
     * Sets the empresaCorrida5Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida5Id the new empresaCorrida5Id
     */
    public void setEmpresaCorrida5Id(BigInteger empresaCorrida5Id) {
        this.empresaCorrida5Id = empresaCorrida5Id;
    }

    /**
     * Gets the rutaCorrida5Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida5Id
     */
    public BigInteger getRutaCorrida5Id() {
        return this.rutaCorrida5Id;
    }

    /**
     * Sets the rutaCorrida5Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida5Id the new rutaCorrida5Id
     */
    public void setRutaCorrida5Id(BigInteger rutaCorrida5Id) {
        this.rutaCorrida5Id = rutaCorrida5Id;
    }

    /**
     * Gets the origenCorrida5Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida5Id
     */
    public BigInteger getOrigenCorrida5Id() {
        return this.origenCorrida5Id;
    }

    /**
     * Sets the origenCorrida5Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida5Id the new origenCorrida5Id
     */
    public void setOrigenCorrida5Id(BigInteger origenCorrida5Id) {
        this.origenCorrida5Id = origenCorrida5Id;
    }

    /**
     * Gets the horarioCorrida5 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida5
     */
    public Date getHorarioCorrida5() {
        return this.horarioCorrida5;
    }

    /**
     * Sets the horarioCorrida5 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida5 the new horarioCorrida5
     */
    public void setHorarioCorrida5(Date horarioCorrida5) {
        this.horarioCorrida5 = horarioCorrida5;
    }

    /**
     * Gets the ofertaCorrida6Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida6Id
     */
    public BigInteger getOfertaCorrida6Id() {
        return this.ofertaCorrida6Id;
    }

    /**
     * Sets the ofertaCorrida6Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida6Id the new ofertaCorrida6Id
     */
    public void setOfertaCorrida6Id(BigInteger ofertaCorrida6Id) {
        this.ofertaCorrida6Id = ofertaCorrida6Id;
    }

    /**
     * Gets the empresaCorrida6Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida6Id
     */
    public BigInteger getEmpresaCorrida6Id() {
        return this.empresaCorrida6Id;
    }

    /**
     * Sets the empresaCorrida6Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida6Id the new empresaCorrida6Id
     */
    public void setEmpresaCorrida6Id(BigInteger empresaCorrida6Id) {
        this.empresaCorrida6Id = empresaCorrida6Id;
    }

    /**
     * Gets the rutaCorrida6Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida6Id
     */
    public BigInteger getRutaCorrida6Id() {
        return this.rutaCorrida6Id;
    }

    /**
     * Sets the rutaCorrida6Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida6Id the new rutaCorrida6Id
     */
    public void setRutaCorrida6Id(BigInteger rutaCorrida6Id) {
        this.rutaCorrida6Id = rutaCorrida6Id;
    }

    /**
     * Gets the origenCorrida6Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida6Id
     */
    public BigInteger getOrigenCorrida6Id() {
        return this.origenCorrida6Id;
    }

    /**
     * Sets the origenCorrida6Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida6Id the new origenCorrida6Id
     */
    public void setOrigenCorrida6Id(BigInteger origenCorrida6Id) {
        this.origenCorrida6Id = origenCorrida6Id;
    }

    /**
     * Gets the horarioCorrida6 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida6
     */
    public Date getHorarioCorrida6() {
        return this.horarioCorrida6;
    }

    /**
     * Sets the horarioCorrida6 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida6 the new horarioCorrida6
     */
    public void setHorarioCorrida6(Date horarioCorrida6) {
        this.horarioCorrida6 = horarioCorrida6;
    }

    /**
     * Gets the ofertaCorrida7Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida7Id
     */
    public BigInteger getOfertaCorrida7Id() {
        return this.ofertaCorrida7Id;
    }

    /**
     * Sets the ofertaCorrida7Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida7Id the new ofertaCorrida7Id
     */
    public void setOfertaCorrida7Id(BigInteger ofertaCorrida7Id) {
        this.ofertaCorrida7Id = ofertaCorrida7Id;
    }

    /**
     * Gets the empresaCorrida7Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida7Id
     */
    public BigInteger getEmpresaCorrida7Id() {
        return this.empresaCorrida7Id;
    }

    /**
     * Sets the empresaCorrida7Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida7Id the new empresaCorrida7Id
     */
    public void setEmpresaCorrida7Id(BigInteger empresaCorrida7Id) {
        this.empresaCorrida7Id = empresaCorrida7Id;
    }

    /**
     * Gets the rutaCorrida7Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida7Id
     */
    public BigInteger getRutaCorrida7Id() {
        return this.rutaCorrida7Id;
    }

    /**
     * Sets the rutaCorrida7Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida7Id the new rutaCorrida7Id
     */
    public void setRutaCorrida7Id(BigInteger rutaCorrida7Id) {
        this.rutaCorrida7Id = rutaCorrida7Id;
    }

    /**
     * Gets the origenCorrida7Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida7Id
     */
    public BigInteger getOrigenCorrida7Id() {
        return this.origenCorrida7Id;
    }

    /**
     * Sets the origenCorrida7Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida7Id the new origenCorrida7Id
     */
    public void setOrigenCorrida7Id(BigInteger origenCorrida7Id) {
        this.origenCorrida7Id = origenCorrida7Id;
    }

    /**
     * Gets the horarioCorrida7 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida7
     */
    public Date getHorarioCorrida7() {
        return this.horarioCorrida7;
    }

    /**
     * Sets the horarioCorrida7 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida7 the new horarioCorrida7
     */
    public void setHorarioCorrida7(Date horarioCorrida7) {
        this.horarioCorrida7 = horarioCorrida7;
    }

    /**
     * Gets the ofertaCorrida8Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida8Id
     */
    public BigInteger getOfertaCorrida8Id() {
        return this.ofertaCorrida8Id;
    }

    /**
     * Sets the ofertaCorrida8Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida8Id the new ofertaCorrida8Id
     */
    public void setOfertaCorrida8Id(BigInteger ofertaCorrida8Id) {
        this.ofertaCorrida8Id = ofertaCorrida8Id;
    }

    /**
     * Gets the empresaCorrida8Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida8Id
     */
    public BigInteger getEmpresaCorrida8Id() {
        return this.empresaCorrida8Id;
    }

    /**
     * Sets the empresaCorrida8Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida8Id the new empresaCorrida8Id
     */
    public void setEmpresaCorrida8Id(BigInteger empresaCorrida8Id) {
        this.empresaCorrida8Id = empresaCorrida8Id;
    }

    /**
     * Gets the rutaCorrida8Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida8Id
     */
    public BigInteger getRutaCorrida8Id() {
        return this.rutaCorrida8Id;
    }

    /**
     * Sets the rutaCorrida8Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida8Id the new rutaCorrida8Id
     */
    public void setRutaCorrida8Id(BigInteger rutaCorrida8Id) {
        this.rutaCorrida8Id = rutaCorrida8Id;
    }

    /**
     * Gets the origenCorrida8Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida8Id
     */
    public BigInteger getOrigenCorrida8Id() {
        return this.origenCorrida8Id;
    }

    /**
     * Sets the origenCorrida8Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida8Id the new origenCorrida8Id
     */
    public void setOrigenCorrida8Id(BigInteger origenCorrida8Id) {
        this.origenCorrida8Id = origenCorrida8Id;
    }

    /**
     * Gets the horarioCorrida8 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida8
     */
    public Date getHorarioCorrida8() {
        return this.horarioCorrida8;
    }

    /**
     * Sets the horarioCorrida8 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida8 the new horarioCorrida8
     */
    public void setHorarioCorrida8(Date horarioCorrida8) {
        this.horarioCorrida8 = horarioCorrida8;
    }

    /**
     * Gets the ofertaCorrida9Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida9Id
     */
    public BigInteger getOfertaCorrida9Id() {
        return this.ofertaCorrida9Id;
    }

    /**
     * Sets the ofertaCorrida9Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida9Id the new ofertaCorrida9Id
     */
    public void setOfertaCorrida9Id(BigInteger ofertaCorrida9Id) {
        this.ofertaCorrida9Id = ofertaCorrida9Id;
    }

    /**
     * Gets the empresaCorrida9Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida9Id
     */
    public BigInteger getEmpresaCorrida9Id() {
        return this.empresaCorrida9Id;
    }

    /**
     * Sets the empresaCorrida9Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida9Id the new empresaCorrida9Id
     */
    public void setEmpresaCorrida9Id(BigInteger empresaCorrida9Id) {
        this.empresaCorrida9Id = empresaCorrida9Id;
    }

    /**
     * Gets the rutaCorrida9Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida9Id
     */
    public BigInteger getRutaCorrida9Id() {
        return this.rutaCorrida9Id;
    }

    /**
     * Sets the rutaCorrida9Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida9Id the new rutaCorrida9Id
     */
    public void setRutaCorrida9Id(BigInteger rutaCorrida9Id) {
        this.rutaCorrida9Id = rutaCorrida9Id;
    }

    /**
     * Gets the origenCorrida9Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida9Id
     */
    public BigInteger getOrigenCorrida9Id() {
        return this.origenCorrida9Id;
    }

    /**
     * Sets the origenCorrida9Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida9Id the new origenCorrida9Id
     */
    public void setOrigenCorrida9Id(BigInteger origenCorrida9Id) {
        this.origenCorrida9Id = origenCorrida9Id;
    }

    /**
     * Gets the horarioCorrida9 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida9
     */
    public Date getHorarioCorrida9() {
        return this.horarioCorrida9;
    }

    /**
     * Sets the horarioCorrida9 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida9 the new horarioCorrida9
     */
    public void setHorarioCorrida9(Date horarioCorrida9) {
        this.horarioCorrida9 = horarioCorrida9;
    }

    /**
     * Gets the ofertaCorrida10Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida10Id
     */
    public BigInteger getOfertaCorrida10Id() {
        return this.ofertaCorrida10Id;
    }

    /**
     * Sets the ofertaCorrida10Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida10Id the new ofertaCorrida10Id
     */
    public void setOfertaCorrida10Id(BigInteger ofertaCorrida10Id) {
        this.ofertaCorrida10Id = ofertaCorrida10Id;
    }

    /**
     * Gets the empresaCorrida10Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida10Id
     */
    public BigInteger getEmpresaCorrida10Id() {
        return this.empresaCorrida10Id;
    }

    /**
     * Sets the empresaCorrida10Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida10Id the new empresaCorrida10Id
     */
    public void setEmpresaCorrida10Id(BigInteger empresaCorrida10Id) {
        this.empresaCorrida10Id = empresaCorrida10Id;
    }

    /**
     * Gets the rutaCorrida10Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida10Id
     */
    public BigInteger getRutaCorrida10Id() {
        return this.rutaCorrida10Id;
    }

    /**
     * Sets the rutaCorrida10Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida10Id the new rutaCorrida10Id
     */
    public void setRutaCorrida10Id(BigInteger rutaCorrida10Id) {
        this.rutaCorrida10Id = rutaCorrida10Id;
    }

    /**
     * Gets the origenCorrida10Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida10Id
     */
    public BigInteger getOrigenCorrida10Id() {
        return this.origenCorrida10Id;
    }

    /**
     * Sets the origenCorrida10Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida10Id the new origenCorrida10Id
     */
    public void setOrigenCorrida10Id(BigInteger origenCorrida10Id) {
        this.origenCorrida10Id = origenCorrida10Id;
    }

    /**
     * Gets the horarioCorrida10 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida10
     */
    public Date getHorarioCorrida10() {
        return this.horarioCorrida10;
    }

    /**
     * Sets the horarioCorrida10 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida10 the new horarioCorrida10
     */
    public void setHorarioCorrida10(Date horarioCorrida10) {
        this.horarioCorrida10 = horarioCorrida10;
    }

    /**
     * Gets the ofertaCorrida11Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida11Id
     */
    public BigInteger getOfertaCorrida11Id() {
        return this.ofertaCorrida11Id;
    }

    /**
     * Sets the ofertaCorrida11Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida11Id the new ofertaCorrida11Id
     */
    public void setOfertaCorrida11Id(BigInteger ofertaCorrida11Id) {
        this.ofertaCorrida11Id = ofertaCorrida11Id;
    }

    /**
     * Gets the empresaCorrida11Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida11Id
     */
    public BigInteger getEmpresaCorrida11Id() {
        return this.empresaCorrida11Id;
    }

    /**
     * Sets the empresaCorrida11Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida11Id the new empresaCorrida11Id
     */
    public void setEmpresaCorrida11Id(BigInteger empresaCorrida11Id) {
        this.empresaCorrida11Id = empresaCorrida11Id;
    }

    /**
     * Gets the rutaCorrida11Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida11Id
     */
    public BigInteger getRutaCorrida11Id() {
        return this.rutaCorrida11Id;
    }

    /**
     * Sets the rutaCorrida11Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida11Id the new rutaCorrida11Id
     */
    public void setRutaCorrida11Id(BigInteger rutaCorrida11Id) {
        this.rutaCorrida11Id = rutaCorrida11Id;
    }

    /**
     * Gets the origenCorrida11Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida11Id
     */
    public BigInteger getOrigenCorrida11Id() {
        return this.origenCorrida11Id;
    }

    /**
     * Sets the origenCorrida11Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida11Id the new origenCorrida11Id
     */
    public void setOrigenCorrida11Id(BigInteger origenCorrida11Id) {
        this.origenCorrida11Id = origenCorrida11Id;
    }

    /**
     * Gets the horarioCorrida11 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida11
     */
    public Date getHorarioCorrida11() {
        return this.horarioCorrida11;
    }

    /**
     * Sets the horarioCorrida11 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida11 the new horarioCorrida11
     */
    public void setHorarioCorrida11(Date horarioCorrida11) {
        this.horarioCorrida11 = horarioCorrida11;
    }

    /**
     * Gets the ofertaCorrida12Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida12Id
     */
    public BigInteger getOfertaCorrida12Id() {
        return this.ofertaCorrida12Id;
    }

    /**
     * Sets the ofertaCorrida12Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida12Id the new ofertaCorrida12Id
     */
    public void setOfertaCorrida12Id(BigInteger ofertaCorrida12Id) {
        this.ofertaCorrida12Id = ofertaCorrida12Id;
    }

    /**
     * Gets the empresaCorrida12Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida12Id
     */
    public BigInteger getEmpresaCorrida12Id() {
        return this.empresaCorrida12Id;
    }

    /**
     * Sets the empresaCorrida12Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida12Id the new empresaCorrida12Id
     */
    public void setEmpresaCorrida12Id(BigInteger empresaCorrida12Id) {
        this.empresaCorrida12Id = empresaCorrida12Id;
    }

    /**
     * Gets the rutaCorrida12Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida12Id
     */
    public BigInteger getRutaCorrida12Id() {
        return this.rutaCorrida12Id;
    }

    /**
     * Sets the rutaCorrida12Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida12Id the new rutaCorrida12Id
     */
    public void setRutaCorrida12Id(BigInteger rutaCorrida12Id) {
        this.rutaCorrida12Id = rutaCorrida12Id;
    }

    /**
     * Gets the origenCorrida12Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida12Id
     */
    public BigInteger getOrigenCorrida12Id() {
        return this.origenCorrida12Id;
    }

    /**
     * Sets the origenCorrida12Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida12Id the new origenCorrida12Id
     */
    public void setOrigenCorrida12Id(BigInteger origenCorrida12Id) {
        this.origenCorrida12Id = origenCorrida12Id;
    }

    /**
     * Gets the horarioCorrida12 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida12
     */
    public Date getHorarioCorrida12() {
        return this.horarioCorrida12;
    }

    /**
     * Sets the horarioCorrida12 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida12 the new horarioCorrida12
     */
    public void setHorarioCorrida12(Date horarioCorrida12) {
        this.horarioCorrida12 = horarioCorrida12;
    }

    /**
     * Gets the ofertaCorrida13Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida13Id
     */
    public BigInteger getOfertaCorrida13Id() {
        return this.ofertaCorrida13Id;
    }

    /**
     * Sets the ofertaCorrida13Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida13Id the new ofertaCorrida13Id
     */
    public void setOfertaCorrida13Id(BigInteger ofertaCorrida13Id) {
        this.ofertaCorrida13Id = ofertaCorrida13Id;
    }

    /**
     * Gets the empresaCorrida13Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida13Id
     */
    public BigInteger getEmpresaCorrida13Id() {
        return this.empresaCorrida13Id;
    }

    /**
     * Sets the empresaCorrida13Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida13Id the new empresaCorrida13Id
     */
    public void setEmpresaCorrida13Id(BigInteger empresaCorrida13Id) {
        this.empresaCorrida13Id = empresaCorrida13Id;
    }

    /**
     * Gets the rutaCorrida13Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida13Id
     */
    public BigInteger getRutaCorrida13Id() {
        return this.rutaCorrida13Id;
    }

    /**
     * Sets the rutaCorrida13Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida13Id the new rutaCorrida13Id
     */
    public void setRutaCorrida13Id(BigInteger rutaCorrida13Id) {
        this.rutaCorrida13Id = rutaCorrida13Id;
    }

    /**
     * Gets the origenCorrida13Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida13Id
     */
    public BigInteger getOrigenCorrida13Id() {
        return this.origenCorrida13Id;
    }

    /**
     * Sets the origenCorrida13Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida13Id the new origenCorrida13Id
     */
    public void setOrigenCorrida13Id(BigInteger origenCorrida13Id) {
        this.origenCorrida13Id = origenCorrida13Id;
    }

    /**
     * Gets the horarioCorrida13 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida13
     */
    public Date getHorarioCorrida13() {
        return this.horarioCorrida13;
    }

    /**
     * Sets the horarioCorrida13 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida13 the new horarioCorrida13
     */
    public void setHorarioCorrida13(Date horarioCorrida13) {
        this.horarioCorrida13 = horarioCorrida13;
    }

    /**
     * Gets the ofertaCorrida14Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida14Id
     */
    public BigInteger getOfertaCorrida14Id() {
        return this.ofertaCorrida14Id;
    }

    /**
     * Sets the ofertaCorrida14Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida14Id the new ofertaCorrida14Id
     */
    public void setOfertaCorrida14Id(BigInteger ofertaCorrida14Id) {
        this.ofertaCorrida14Id = ofertaCorrida14Id;
    }

    /**
     * Gets the empresaCorrida14Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida14Id
     */
    public BigInteger getEmpresaCorrida14Id() {
        return this.empresaCorrida14Id;
    }

    /**
     * Sets the empresaCorrida14Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida14Id the new empresaCorrida14Id
     */
    public void setEmpresaCorrida14Id(BigInteger empresaCorrida14Id) {
        this.empresaCorrida14Id = empresaCorrida14Id;
    }

    /**
     * Gets the rutaCorrida14Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida14Id
     */
    public BigInteger getRutaCorrida14Id() {
        return this.rutaCorrida14Id;
    }

    /**
     * Sets the rutaCorrida14Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida14Id the new rutaCorrida14Id
     */
    public void setRutaCorrida14Id(BigInteger rutaCorrida14Id) {
        this.rutaCorrida14Id = rutaCorrida14Id;
    }

    /**
     * Gets the origenCorrida14Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida14Id
     */
    public BigInteger getOrigenCorrida14Id() {
        return this.origenCorrida14Id;
    }

    /**
     * Sets the origenCorrida14Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida14Id the new origenCorrida14Id
     */
    public void setOrigenCorrida14Id(BigInteger origenCorrida14Id) {
        this.origenCorrida14Id = origenCorrida14Id;
    }

    /**
     * Gets the horarioCorrida14 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida14
     */
    public Date getHorarioCorrida14() {
        return this.horarioCorrida14;
    }

    /**
     * Sets the horarioCorrida14 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida14 the new horarioCorrida14
     */
    public void setHorarioCorrida14(Date horarioCorrida14) {
        this.horarioCorrida14 = horarioCorrida14;
    }

    /**
     * Gets the ofertaCorrida15Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida15Id
     */
    public BigInteger getOfertaCorrida15Id() {
        return this.ofertaCorrida15Id;
    }

    /**
     * Sets the ofertaCorrida15Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida15Id the new ofertaCorrida15Id
     */
    public void setOfertaCorrida15Id(BigInteger ofertaCorrida15Id) {
        this.ofertaCorrida15Id = ofertaCorrida15Id;
    }

    /**
     * Gets the empresaCorrida15Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida15Id
     */
    public BigInteger getEmpresaCorrida15Id() {
        return this.empresaCorrida15Id;
    }

    /**
     * Sets the empresaCorrida15Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida15Id the new empresaCorrida15Id
     */
    public void setEmpresaCorrida15Id(BigInteger empresaCorrida15Id) {
        this.empresaCorrida15Id = empresaCorrida15Id;
    }

    /**
     * Gets the rutaCorrida15Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida15Id
     */
    public BigInteger getRutaCorrida15Id() {
        return this.rutaCorrida15Id;
    }

    /**
     * Sets the rutaCorrida15Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida15Id the new rutaCorrida15Id
     */
    public void setRutaCorrida15Id(BigInteger rutaCorrida15Id) {
        this.rutaCorrida15Id = rutaCorrida15Id;
    }

    /**
     * Gets the origenCorrida15Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida15Id
     */
    public BigInteger getOrigenCorrida15Id() {
        return this.origenCorrida15Id;
    }

    /**
     * Sets the origenCorrida15Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida15Id the new origenCorrida15Id
     */
    public void setOrigenCorrida15Id(BigInteger origenCorrida15Id) {
        this.origenCorrida15Id = origenCorrida15Id;
    }

    /**
     * Gets the horarioCorrida15 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida15
     */
    public Date getHorarioCorrida15() {
        return this.horarioCorrida15;
    }

    /**
     * Sets the horarioCorrida15 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida15 the new horarioCorrida15
     */
    public void setHorarioCorrida15(Date horarioCorrida15) {
        this.horarioCorrida15 = horarioCorrida15;
    }

    /**
     * Gets the ofertaCorrida16Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida16Id
     */
    public BigInteger getOfertaCorrida16Id() {
        return this.ofertaCorrida16Id;
    }

    /**
     * Sets the ofertaCorrida16Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida16Id the new ofertaCorrida16Id
     */
    public void setOfertaCorrida16Id(BigInteger ofertaCorrida16Id) {
        this.ofertaCorrida16Id = ofertaCorrida16Id;
    }

    /**
     * Gets the empresaCorrida16Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida16Id
     */
    public BigInteger getEmpresaCorrida16Id() {
        return this.empresaCorrida16Id;
    }

    /**
     * Sets the empresaCorrida16Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida16Id the new empresaCorrida16Id
     */
    public void setEmpresaCorrida16Id(BigInteger empresaCorrida16Id) {
        this.empresaCorrida16Id = empresaCorrida16Id;
    }

    /**
     * Gets the rutaCorrida16Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida16Id
     */
    public BigInteger getRutaCorrida16Id() {
        return this.rutaCorrida16Id;
    }

    /**
     * Sets the rutaCorrida16Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida16Id the new rutaCorrida16Id
     */
    public void setRutaCorrida16Id(BigInteger rutaCorrida16Id) {
        this.rutaCorrida16Id = rutaCorrida16Id;
    }

    /**
     * Gets the origenCorrida16Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida16Id
     */
    public BigInteger getOrigenCorrida16Id() {
        return this.origenCorrida16Id;
    }

    /**
     * Sets the origenCorrida16Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida16Id the new origenCorrida16Id
     */
    public void setOrigenCorrida16Id(BigInteger origenCorrida16Id) {
        this.origenCorrida16Id = origenCorrida16Id;
    }

    /**
     * Gets the horarioCorrida16 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida16
     */
    public Date getHorarioCorrida16() {
        return this.horarioCorrida16;
    }

    /**
     * Sets the horarioCorrida16 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida16 the new horarioCorrida16
     */
    public void setHorarioCorrida16(Date horarioCorrida16) {
        this.horarioCorrida16 = horarioCorrida16;
    }

    /**
     * Gets the ofertaCorrida17Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida17Id
     */
    public BigInteger getOfertaCorrida17Id() {
        return this.ofertaCorrida17Id;
    }

    /**
     * Sets the ofertaCorrida17Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida17Id the new ofertaCorrida17Id
     */
    public void setOfertaCorrida17Id(BigInteger ofertaCorrida17Id) {
        this.ofertaCorrida17Id = ofertaCorrida17Id;
    }

    /**
     * Gets the empresaCorrida17Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida17Id
     */
    public BigInteger getEmpresaCorrida17Id() {
        return this.empresaCorrida17Id;
    }

    /**
     * Sets the empresaCorrida17Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida17Id the new empresaCorrida17Id
     */
    public void setEmpresaCorrida17Id(BigInteger empresaCorrida17Id) {
        this.empresaCorrida17Id = empresaCorrida17Id;
    }

    /**
     * Gets the rutaCorrida17Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida17Id
     */
    public BigInteger getRutaCorrida17Id() {
        return this.rutaCorrida17Id;
    }

    /**
     * Sets the rutaCorrida17Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida17Id the new rutaCorrida17Id
     */
    public void setRutaCorrida17Id(BigInteger rutaCorrida17Id) {
        this.rutaCorrida17Id = rutaCorrida17Id;
    }

    /**
     * Gets the origenCorrida17Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida17Id
     */
    public BigInteger getOrigenCorrida17Id() {
        return this.origenCorrida17Id;
    }

    /**
     * Sets the origenCorrida17Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida17Id the new origenCorrida17Id
     */
    public void setOrigenCorrida17Id(BigInteger origenCorrida17Id) {
        this.origenCorrida17Id = origenCorrida17Id;
    }

    /**
     * Gets the horarioCorrida17 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida17
     */
    public Date getHorarioCorrida17() {
        return this.horarioCorrida17;
    }

    /**
     * Sets the horarioCorrida17 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida17 the new horarioCorrida17
     */
    public void setHorarioCorrida17(Date horarioCorrida17) {
        this.horarioCorrida17 = horarioCorrida17;
    }

    /**
     * Gets the ofertaCorrida18Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida18Id
     */
    public BigInteger getOfertaCorrida18Id() {
        return this.ofertaCorrida18Id;
    }

    /**
     * Sets the ofertaCorrida18Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida18Id the new ofertaCorrida18Id
     */
    public void setOfertaCorrida18Id(BigInteger ofertaCorrida18Id) {
        this.ofertaCorrida18Id = ofertaCorrida18Id;
    }

    /**
     * Gets the empresaCorrida18Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida18Id
     */
    public BigInteger getEmpresaCorrida18Id() {
        return this.empresaCorrida18Id;
    }

    /**
     * Sets the empresaCorrida18Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida18Id the new empresaCorrida18Id
     */
    public void setEmpresaCorrida18Id(BigInteger empresaCorrida18Id) {
        this.empresaCorrida18Id = empresaCorrida18Id;
    }

    /**
     * Gets the rutaCorrida18Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida18Id
     */
    public BigInteger getRutaCorrida18Id() {
        return this.rutaCorrida18Id;
    }

    /**
     * Sets the rutaCorrida18Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida18Id the new rutaCorrida18Id
     */
    public void setRutaCorrida18Id(BigInteger rutaCorrida18Id) {
        this.rutaCorrida18Id = rutaCorrida18Id;
    }

    /**
     * Gets the origenCorrida18Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida18Id
     */
    public BigInteger getOrigenCorrida18Id() {
        return this.origenCorrida18Id;
    }

    /**
     * Sets the origenCorrida18Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida18Id the new origenCorrida18Id
     */
    public void setOrigenCorrida18Id(BigInteger origenCorrida18Id) {
        this.origenCorrida18Id = origenCorrida18Id;
    }

    /**
     * Gets the horarioCorrida18 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida18
     */
    public Date getHorarioCorrida18() {
        return this.horarioCorrida18;
    }

    /**
     * Sets the horarioCorrida18 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida18 the new horarioCorrida18
     */
    public void setHorarioCorrida18(Date horarioCorrida18) {
        this.horarioCorrida18 = horarioCorrida18;
    }

    /**
     * Gets the ofertaCorrida19Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida19Id
     */
    public BigInteger getOfertaCorrida19Id() {
        return this.ofertaCorrida19Id;
    }

    /**
     * Sets the ofertaCorrida19Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida19Id the new ofertaCorrida19Id
     */
    public void setOfertaCorrida19Id(BigInteger ofertaCorrida19Id) {
        this.ofertaCorrida19Id = ofertaCorrida19Id;
    }

    /**
     * Gets the empresaCorrida19Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida19Id
     */
    public BigInteger getEmpresaCorrida19Id() {
        return this.empresaCorrida19Id;
    }

    /**
     * Sets the empresaCorrida19Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida19Id the new empresaCorrida19Id
     */
    public void setEmpresaCorrida19Id(BigInteger empresaCorrida19Id) {
        this.empresaCorrida19Id = empresaCorrida19Id;
    }

    /**
     * Gets the rutaCorrida19Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida19Id
     */
    public BigInteger getRutaCorrida19Id() {
        return this.rutaCorrida19Id;
    }

    /**
     * Sets the rutaCorrida19Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida19Id the new rutaCorrida19Id
     */
    public void setRutaCorrida19Id(BigInteger rutaCorrida19Id) {
        this.rutaCorrida19Id = rutaCorrida19Id;
    }

    /**
     * Gets the origenCorrida19Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida19Id
     */
    public BigInteger getOrigenCorrida19Id() {
        return this.origenCorrida19Id;
    }

    /**
     * Sets the origenCorrida19Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida19Id the new origenCorrida19Id
     */
    public void setOrigenCorrida19Id(BigInteger origenCorrida19Id) {
        this.origenCorrida19Id = origenCorrida19Id;
    }

    /**
     * Gets the horarioCorrida19 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida19
     */
    public Date getHorarioCorrida19() {
        return this.horarioCorrida19;
    }

    /**
     * Sets the horarioCorrida19 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida19 the new horarioCorrida19
     */
    public void setHorarioCorrida19(Date horarioCorrida19) {
        this.horarioCorrida19 = horarioCorrida19;
    }

    /**
     * Gets the ofertaCorrida20Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida20Id
     */
    public BigInteger getOfertaCorrida20Id() {
        return this.ofertaCorrida20Id;
    }

    /**
     * Sets the ofertaCorrida20Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida20Id the new ofertaCorrida20Id
     */
    public void setOfertaCorrida20Id(BigInteger ofertaCorrida20Id) {
        this.ofertaCorrida20Id = ofertaCorrida20Id;
    }

    /**
     * Gets the empresaCorrida20Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida20Id
     */
    public BigInteger getEmpresaCorrida20Id() {
        return this.empresaCorrida20Id;
    }

    /**
     * Sets the empresaCorrida20Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida20Id the new empresaCorrida20Id
     */
    public void setEmpresaCorrida20Id(BigInteger empresaCorrida20Id) {
        this.empresaCorrida20Id = empresaCorrida20Id;
    }

    /**
     * Gets the rutaCorrida20Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida20Id
     */
    public BigInteger getRutaCorrida20Id() {
        return this.rutaCorrida20Id;
    }

    /**
     * Sets the rutaCorrida20Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida20Id the new rutaCorrida20Id
     */
    public void setRutaCorrida20Id(BigInteger rutaCorrida20Id) {
        this.rutaCorrida20Id = rutaCorrida20Id;
    }

    /**
     * Gets the origenCorrida20Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida20Id
     */
    public BigInteger getOrigenCorrida20Id() {
        return this.origenCorrida20Id;
    }

    /**
     * Sets the origenCorrida20Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida20Id the new origenCorrida20Id
     */
    public void setOrigenCorrida20Id(BigInteger origenCorrida20Id) {
        this.origenCorrida20Id = origenCorrida20Id;
    }

    /**
     * Gets the horarioCorrida20 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida20
     */
    public Date getHorarioCorrida20() {
        return this.horarioCorrida20;
    }

    /**
     * Sets the horarioCorrida20 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida20 the new horarioCorrida20
     */
    public void setHorarioCorrida20(Date horarioCorrida20) {
        this.horarioCorrida20 = horarioCorrida20;
    }

    /**
     * Gets the ofertaCorrida21Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida21Id
     */
    public BigInteger getOfertaCorrida21Id() {
        return this.ofertaCorrida21Id;
    }

    /**
     * Sets the ofertaCorrida21Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida21Id the new ofertaCorrida21Id
     */
    public void setOfertaCorrida21Id(BigInteger ofertaCorrida21Id) {
        this.ofertaCorrida21Id = ofertaCorrida21Id;
    }

    /**
     * Gets the empresaCorrida21Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida21Id
     */
    public BigInteger getEmpresaCorrida21Id() {
        return this.empresaCorrida21Id;
    }

    /**
     * Sets the empresaCorrida21Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida21Id the new empresaCorrida21Id
     */
    public void setEmpresaCorrida21Id(BigInteger empresaCorrida21Id) {
        this.empresaCorrida21Id = empresaCorrida21Id;
    }

    /**
     * Gets the rutaCorrida21Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida21Id
     */
    public BigInteger getRutaCorrida21Id() {
        return this.rutaCorrida21Id;
    }

    /**
     * Sets the rutaCorrida21Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida21Id the new rutaCorrida21Id
     */
    public void setRutaCorrida21Id(BigInteger rutaCorrida21Id) {
        this.rutaCorrida21Id = rutaCorrida21Id;
    }

    /**
     * Gets the origenCorrida21Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida21Id
     */
    public BigInteger getOrigenCorrida21Id() {
        return this.origenCorrida21Id;
    }

    /**
     * Sets the origenCorrida21Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida21Id the new origenCorrida21Id
     */
    public void setOrigenCorrida21Id(BigInteger origenCorrida21Id) {
        this.origenCorrida21Id = origenCorrida21Id;
    }

    /**
     * Gets the horarioCorrida21 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida21
     */
    public Date getHorarioCorrida21() {
        return this.horarioCorrida21;
    }

    /**
     * Sets the horarioCorrida21 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida21 the new horarioCorrida21
     */
    public void setHorarioCorrida21(Date horarioCorrida21) {
        this.horarioCorrida21 = horarioCorrida21;
    }

    /**
     * Gets the ofertaCorrida22Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida22Id
     */
    public BigInteger getOfertaCorrida22Id() {
        return this.ofertaCorrida22Id;
    }

    /**
     * Sets the ofertaCorrida22Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida22Id the new ofertaCorrida22Id
     */
    public void setOfertaCorrida22Id(BigInteger ofertaCorrida22Id) {
        this.ofertaCorrida22Id = ofertaCorrida22Id;
    }

    /**
     * Gets the empresaCorrida22Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida22Id
     */
    public BigInteger getEmpresaCorrida22Id() {
        return this.empresaCorrida22Id;
    }

    /**
     * Sets the empresaCorrida22Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida22Id the new empresaCorrida22Id
     */
    public void setEmpresaCorrida22Id(BigInteger empresaCorrida22Id) {
        this.empresaCorrida22Id = empresaCorrida22Id;
    }

    /**
     * Gets the rutaCorrida22Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida22Id
     */
    public BigInteger getRutaCorrida22Id() {
        return this.rutaCorrida22Id;
    }

    /**
     * Sets the rutaCorrida22Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida22Id the new rutaCorrida22Id
     */
    public void setRutaCorrida22Id(BigInteger rutaCorrida22Id) {
        this.rutaCorrida22Id = rutaCorrida22Id;
    }

    /**
     * Gets the origenCorrida22Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida22Id
     */
    public BigInteger getOrigenCorrida22Id() {
        return this.origenCorrida22Id;
    }

    /**
     * Sets the origenCorrida22Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida22Id the new origenCorrida22Id
     */
    public void setOrigenCorrida22Id(BigInteger origenCorrida22Id) {
        this.origenCorrida22Id = origenCorrida22Id;
    }

    /**
     * Gets the horarioCorrida22 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida22
     */
    public Date getHorarioCorrida22() {
        return this.horarioCorrida22;
    }

    /**
     * Sets the horarioCorrida22 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida22 the new horarioCorrida22
     */
    public void setHorarioCorrida22(Date horarioCorrida22) {
        this.horarioCorrida22 = horarioCorrida22;
    }

    /**
     * Gets the ofertaCorrida23Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida23Id
     */
    public BigInteger getOfertaCorrida23Id() {
        return this.ofertaCorrida23Id;
    }

    /**
     * Sets the ofertaCorrida23Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida23Id the new ofertaCorrida23Id
     */
    public void setOfertaCorrida23Id(BigInteger ofertaCorrida23Id) {
        this.ofertaCorrida23Id = ofertaCorrida23Id;
    }

    /**
     * Gets the empresaCorrida23Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida23Id
     */
    public BigInteger getEmpresaCorrida23Id() {
        return this.empresaCorrida23Id;
    }

    /**
     * Sets the empresaCorrida23Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida23Id the new empresaCorrida23Id
     */
    public void setEmpresaCorrida23Id(BigInteger empresaCorrida23Id) {
        this.empresaCorrida23Id = empresaCorrida23Id;
    }

    /**
     * Gets the rutaCorrida23Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida23Id
     */
    public BigInteger getRutaCorrida23Id() {
        return this.rutaCorrida23Id;
    }

    /**
     * Sets the rutaCorrida23Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida23Id the new rutaCorrida23Id
     */
    public void setRutaCorrida23Id(BigInteger rutaCorrida23Id) {
        this.rutaCorrida23Id = rutaCorrida23Id;
    }

    /**
     * Gets the origenCorrida23Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida23Id
     */
    public BigInteger getOrigenCorrida23Id() {
        return this.origenCorrida23Id;
    }

    /**
     * Sets the origenCorrida23Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida23Id the new origenCorrida23Id
     */
    public void setOrigenCorrida23Id(BigInteger origenCorrida23Id) {
        this.origenCorrida23Id = origenCorrida23Id;
    }

    /**
     * Gets the horarioCorrida23 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida23
     */
    public Date getHorarioCorrida23() {
        return this.horarioCorrida23;
    }

    /**
     * Sets the horarioCorrida23 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida23 the new horarioCorrida23
     */
    public void setHorarioCorrida23(Date horarioCorrida23) {
        this.horarioCorrida23 = horarioCorrida23;
    }

    /**
     * Gets the ofertaCorrida24Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida24Id
     */
    public BigInteger getOfertaCorrida24Id() {
        return this.ofertaCorrida24Id;
    }

    /**
     * Sets the ofertaCorrida24Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida24Id the new ofertaCorrida24Id
     */
    public void setOfertaCorrida24Id(BigInteger ofertaCorrida24Id) {
        this.ofertaCorrida24Id = ofertaCorrida24Id;
    }

    /**
     * Gets the empresaCorrida24Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida24Id
     */
    public BigInteger getEmpresaCorrida24Id() {
        return this.empresaCorrida24Id;
    }

    /**
     * Sets the empresaCorrida24Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida24Id the new empresaCorrida24Id
     */
    public void setEmpresaCorrida24Id(BigInteger empresaCorrida24Id) {
        this.empresaCorrida24Id = empresaCorrida24Id;
    }

    /**
     * Gets the rutaCorrida24Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida24Id
     */
    public BigInteger getRutaCorrida24Id() {
        return this.rutaCorrida24Id;
    }

    /**
     * Sets the rutaCorrida24Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida24Id the new rutaCorrida24Id
     */
    public void setRutaCorrida24Id(BigInteger rutaCorrida24Id) {
        this.rutaCorrida24Id = rutaCorrida24Id;
    }

    /**
     * Gets the origenCorrida24Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida24Id
     */
    public BigInteger getOrigenCorrida24Id() {
        return this.origenCorrida24Id;
    }

    /**
     * Sets the origenCorrida24Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida24Id the new origenCorrida24Id
     */
    public void setOrigenCorrida24Id(BigInteger origenCorrida24Id) {
        this.origenCorrida24Id = origenCorrida24Id;
    }

    /**
     * Gets the horarioCorrida24 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida24
     */
    public Date getHorarioCorrida24() {
        return this.horarioCorrida24;
    }

    /**
     * Sets the horarioCorrida24 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida24 the new horarioCorrida24
     */
    public void setHorarioCorrida24(Date horarioCorrida24) {
        this.horarioCorrida24 = horarioCorrida24;
    }

    /**
     * Gets the ofertaCorrida25Id of this TmsRolesBaseLineasTbl.
     * @return the ofertaCorrida25Id
     */
    public BigInteger getOfertaCorrida25Id() {
        return this.ofertaCorrida25Id;
    }

    /**
     * Sets the ofertaCorrida25Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param ofertaCorrida25Id the new ofertaCorrida25Id
     */
    public void setOfertaCorrida25Id(BigInteger ofertaCorrida25Id) {
        this.ofertaCorrida25Id = ofertaCorrida25Id;
    }

    /**
     * Gets the empresaCorrida25Id of this TmsRolesBaseLineasTbl.
     * @return the empresaCorrida25Id
     */
    public BigInteger getEmpresaCorrida25Id() {
        return this.empresaCorrida25Id;
    }

    /**
     * Sets the empresaCorrida25Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param empresaCorrida25Id the new empresaCorrida25Id
     */
    public void setEmpresaCorrida25Id(BigInteger empresaCorrida25Id) {
        this.empresaCorrida25Id = empresaCorrida25Id;
    }

    /**
     * Gets the rutaCorrida25Id of this TmsRolesBaseLineasTbl.
     * @return the rutaCorrida25Id
     */
    public BigInteger getRutaCorrida25Id() {
        return this.rutaCorrida25Id;
    }

    /**
     * Sets the rutaCorrida25Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param rutaCorrida25Id the new rutaCorrida25Id
     */
    public void setRutaCorrida25Id(BigInteger rutaCorrida25Id) {
        this.rutaCorrida25Id = rutaCorrida25Id;
    }

    /**
     * Gets the origenCorrida25Id of this TmsRolesBaseLineasTbl.
     * @return the origenCorrida25Id
     */
    public BigInteger getOrigenCorrida25Id() {
        return this.origenCorrida25Id;
    }

    /**
     * Sets the origenCorrida25Id of this TmsRolesBaseLineasTbl to the specified value.
     * @param origenCorrida25Id the new origenCorrida25Id
     */
    public void setOrigenCorrida25Id(BigInteger origenCorrida25Id) {
        this.origenCorrida25Id = origenCorrida25Id;
    }

    /**
     * Gets the horarioCorrida25 of this TmsRolesBaseLineasTbl.
     * @return the horarioCorrida25
     */
    public Date getHorarioCorrida25() {
        return this.horarioCorrida25;
    }

    /**
     * Sets the horarioCorrida25 of this TmsRolesBaseLineasTbl to the specified value.
     * @param horarioCorrida25 the new horarioCorrida25
     */
    public void setHorarioCorrida25(Date horarioCorrida25) {
        this.horarioCorrida25 = horarioCorrida25;
    }

    /**
     * Gets the creadoPor of this TmsRolesBaseLineasTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsRolesBaseLineasTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsRolesBaseLineasTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsRolesBaseLineasTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsRolesBaseLineasTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsRolesBaseLineasTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsRolesBaseLineasTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsRolesBaseLineasTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsRolesBaseLineasTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsRolesBaseLineasTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsRolesBaseLineasTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsRolesBaseLineasTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsRolesBaseLineasTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsRolesBaseLineasTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Gets the autobusId of this TmsRolesBaseLineasTbl.
     * @return the autobusId
     */
    public TmsAutobusesTbl getAutobusId() {
        return this.autobusId;
    }

    /**
     * Sets the autobusId of this TmsRolesBaseLineasTbl to the specified value.
     * @param autobusId the new autobusId
     */
    public void setAutobusId(TmsAutobusesTbl autobusId) {
        this.autobusId = autobusId;
    }

    /**
     * Gets the rolBaseId of this TmsRolesBaseLineasTbl.
     * @return the rolBaseId
     */
    public TmsRolesBaseTbl getRolBaseId() {
        return this.rolBaseId;
    }

    /**
     * Sets the rolBaseId of this TmsRolesBaseLineasTbl to the specified value.
     * @param rolBaseId the new rolBaseId
     */
    public void setRolBaseId(TmsRolesBaseTbl rolBaseId) {
        this.rolBaseId = rolBaseId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.rolBaseLineaId != null ? this.rolBaseLineaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsRolesBaseLineasTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsRolesBaseLineasTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsRolesBaseLineasTbl)) {
            return false;
        }
        TmsRolesBaseLineasTbl other = (TmsRolesBaseLineasTbl)object;
        if (this.rolBaseLineaId != other.rolBaseLineaId && (this.rolBaseLineaId == null || !this.rolBaseLineaId.equals(other.rolBaseLineaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsroles.entidad.TmsRolesBaseLineasTbl[rolBaseLineaId=" + rolBaseLineaId + "]";
    }
    
}
