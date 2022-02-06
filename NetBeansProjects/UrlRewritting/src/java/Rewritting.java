import java.io.IOException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
   
    public class Rewritting extends HttpServlet {
       
      public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
   
        //SESSION ATTRIBUTE.
        String user = (String) request.getSession().getAttribute("user");
        if    (user == null) { request.getSession().setAttribute("user","John");        }
        else                 { request.getSession().setAttribute("user",user+"_extra"); }                
   
        //URL ENCODE.
        String servletURL = response.encodeURL("index.html");
        
        //OUTPUT.
        response.getWriter().println("<a href="+servletURL+">MyServlet</a>");
        
      } 
    }