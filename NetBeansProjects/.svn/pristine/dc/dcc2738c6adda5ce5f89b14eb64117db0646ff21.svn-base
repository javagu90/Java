/*
 * TmsFechahoraV.java
 *
 * Created on 16 de agosto de 2007, 11:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertmsCorteReca.entidad;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "TmsFechahoraV.findAll", 
    query = "select o from TmsFechahoraV o")
@Table(name = "TMS_FECHAHORA_V")
public class TmsFechahoraV implements Serializable {
    @Id
    private Timestamp fechahorasys;

    public TmsFechahoraV() {
    }

    public Timestamp getFechahorasys() {
        return fechahorasys;
    }

    public void setFechahorasys(Timestamp fechahorasys) {
        this.fechahorasys = fechahorasys;
    }
}
