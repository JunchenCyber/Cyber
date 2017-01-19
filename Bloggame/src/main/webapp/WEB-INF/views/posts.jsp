<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head>
    <title>Bloggame</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <div class="postForm">
      <h1>Post it out...</h1>
      <form method="POST" name="postForm">
        <input type="hidden" name="latitude">
        <input type="hidden" name="longitude">
        <textarea name="message" cols="80" rows="5"></textarea><br/>
        <input type="submit" value="Add" />
      </form>
    </div>
    <div class="listTitle">
      <h1>Recent Posts</h1>
      <ul class="postList">
        <c:forEach items="${postList}" var="post" >
          <li id="post_<c:out value="post.id"/>">
            <div class="postMessage"><c:out value="${post.message}" /></div>
            <div>
              <span class="postTime"><c:out value="${post.time}" /></span>
              <span class="postLocation">(<c:out value="${post.latitude}" />, <c:out value="${post.longitude}" />)</span>
            </div>
          </li>
        </c:forEach>
      </ul>
      <c:if test="${fn:length(postList) gt 20}">
        <hr />
        <s:url value="/posts?count=${nextCount}" var="more_url" />
        <a href="${more_url}">Show more</a>
      </c:if>
    </div>
  </body>
</html>