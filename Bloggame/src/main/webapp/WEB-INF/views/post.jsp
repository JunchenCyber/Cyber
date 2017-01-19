<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Bloggame</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="postView">
      <div class="postMessage"><c:out value="${post.message}" /></div>
      <div>
        <span class="postTime"><c:out value="${post.time}" /></span>
      </div>
    </div>
  </body>
</html>