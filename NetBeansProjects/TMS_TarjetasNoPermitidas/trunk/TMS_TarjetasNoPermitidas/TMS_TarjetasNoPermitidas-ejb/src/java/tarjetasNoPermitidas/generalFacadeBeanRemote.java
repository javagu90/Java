/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tarjetasNoPermitidas;

import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface generalFacadeBeanRemote {

    public java.util.Vector buscarTarjetas();

    public boolean agregarTarjeta(java.lang.String NoTarjeta, long usuarioId);

    public boolean removerTarjeta(long idTarjeta, long usuarioId);

}
