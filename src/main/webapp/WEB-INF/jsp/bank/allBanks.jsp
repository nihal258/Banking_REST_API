<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Banks page</h1>

<ul>
<c:forEach var="bank" items="${banks}">
   <li>${bank}</li>
</c:forEach>
</ul>