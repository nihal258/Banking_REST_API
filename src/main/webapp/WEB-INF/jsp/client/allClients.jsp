<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Clients page</h1>

<ul>
<c:forEach var="client" items="${clients}">
   <li>${client}</li>
</c:forEach>
</ul>