<%--
  Created by IntelliJ IDEA.
  User: shuhsun
  Date: 12/13/20
  Time: 1:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType='text/html;charset=UTF-8' language='java' %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form id='calc' action='${pageContext.request.contextPath}/calc' method='get' >
    <label for='add1'></label><input type='text' id='add1' name='add1'/>
    +
    <label for='add2'></label><input type='text' id='add2' name='add2' />
    =
    <label for='addsum'></label><input type='text' id='addsum' name='addsum' />
    <br/>
    <label for='multi1'></label><input type='text' id='multi1' name='multi1' />
    *
    <label for='multi2'></label><input type='text' id='multi2' name='multi2' />
    =
    <label for='multisum'></label><input type='text' id='multisum' name='multisum' />
    <input type='submit' value='Submit'/>
  </form>

  </body>
</html>
