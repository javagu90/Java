<%-- 
    Document   : index
    Created on : 21/07/2014, 06:14:29 PM
    Author     : Jav
--%>

<html>
%-- hello.jsp --%>

  <body>

    <jsp:useBean id="person"  class="value.Person" scope="request" />

      <jsp:setProperty name="person" property="name" value="Niko" />


    Hello ${person.name}!!

  </body>

</html>
