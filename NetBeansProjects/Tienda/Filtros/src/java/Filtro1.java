
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jav
 */
public class Filtro1 implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException 
    {
        long begin =System.currentTimeMillis();
        chain.doFilter(request, response);
        long end = System.currentTimeMillis();
        
        StringBuffer logMessage= new StringBuffer();
        if(request instanceof HttpServletRequest)
        {
            logMessage=((HttpServletRequest) request).getRequestURL();
        }
        logMessage.append(": ");
        logMessage.append(end-begin);
        logMessage.append("ms");
    }

    @Override
    public void destroy() {}
   
}
