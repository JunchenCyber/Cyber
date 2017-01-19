<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Bloggame</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Welcome to Bloggame</h1>

    <a href="<c:url value="/posts" />">Posts</a> | 
    <a href="<c:url value="/blogger/register" />">Register</a>
  </body>
</html>
