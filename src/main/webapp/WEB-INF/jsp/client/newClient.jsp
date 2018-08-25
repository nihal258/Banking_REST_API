<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
    <h1>Add new Client</h1>
        
    <form:form modelAttribute="form">
        <form:errors path="" element="div" />
        <div>
            <form:label path="firstName">First Name</form:label>
            <form:input path="firstName" />
            <form:errors path="firstName" />
        </div>
        <div>
            <form:label path="lastName">Last Name</form:label>
            <form:input path="lastName" />
            <form:errors path="lastName" />
        </div>        
        <div>
            <form:label path="bankId">Bank ID</form:label>
            <form:input path="bankId" />
            <form:errors path="bankId" />
        </div>
        <div>
            <form:label path="balance">Actual Balance</form:label>
            <form:input path="balance" />
            <form:errors path="balance" />
        </div>
        <div>
            <input type="submit" />
        </div>
    </form:form>
</body>
</html>