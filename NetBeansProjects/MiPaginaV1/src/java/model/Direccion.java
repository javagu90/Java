/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Jav
 */
public class Direccion 
{
    private String numero;
    private String calle;
    private String colonia;
    
    public Direccion()
    {}
    
    public Direccion(String numero, String calle, String colonia)
    {
        this.numero=numero;
        this.calle=calle;
        this.colonia=colonia;
    }
}
