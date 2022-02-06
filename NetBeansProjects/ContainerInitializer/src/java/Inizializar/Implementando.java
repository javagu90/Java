/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inizializar;

import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 *
 * @author Jav
 */
public class Implementando implements ServletContainerInitializer 
{
    public ServletContext sc;
    public Set set;
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx)
    {       
        this.set=c;
        this.sc=ctx;
    }
    
}
