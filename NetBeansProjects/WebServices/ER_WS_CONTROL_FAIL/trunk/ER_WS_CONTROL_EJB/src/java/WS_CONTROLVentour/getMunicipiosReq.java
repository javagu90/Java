/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROLVentour;

import java.io.Serializable;

/**
 *
 * @author brojas
 */
public class getMunicipiosReq implements Serializable{
  private Long SesionId;
  private String Estado_ID;

    /**
     * @return the SesionId
     */
    public Long getSesionId() {
        return SesionId;
    }

    /**
     * @param SesionId the SesionId to set
     */
    public void setSesionId(Long SesionId) {
        this.SesionId = SesionId;
    }

    /**
     * @return the Estado_ID
     */
    public String getEstado_ID() {
        return Estado_ID;
    }

    /**
     * @param Estado_ID the Estado_ID to set
     */
    public void setEstado_ID(String Estado_ID) {
        this.Estado_ID = Estado_ID;
    }
}
