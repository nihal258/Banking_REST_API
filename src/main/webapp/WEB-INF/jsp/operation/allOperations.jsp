<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Operations page</h1>

<ul>
<c:forEach var="operation" items="${operations}">
   <li>${operation}</li>
</c:forEach>
</ul>