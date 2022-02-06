/*
 * TmsPagosActAdicionalesTblFacadeRemote.java
 *
 * Created on 2 de octubre de 2007, 12:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.solicitud;

import java.math.BigInteger;
import java.util.List;
import javax.ejb.Remote;
import tmsactividadesadicionales.entidad.TmsPagosActAdicionalesTbl;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface TmsPagosActAdicionalesTblFacadeRemote {

    void edit(TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl);

    void destroy(TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl);

    TmsPagosActAdicionalesTbl find(Object pk);

    List findAll();

    List<tmsactividadesadicionales.entidad.TmsPagosActAdicionalesTbl>  buscaActividaesPreingresadas(BigInteger Idoperador);


    java.util.List<tmsactividadesadicionales.entidad.TmsPagosActAdicionalesTbl> buscaActividaesPorReferencia(BigInteger ref);

    java.math.BigDecimal create(TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl, String ter);
    
}
