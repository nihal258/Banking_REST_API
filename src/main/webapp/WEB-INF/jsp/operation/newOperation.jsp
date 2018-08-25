<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
    <h1>Add new Operation</h1>
        
    <form:form modelAttribute="form">
        <form:errors path="" element="div" />
        <div>
            <form:label path="fromID">ID Client From</form:label>
            <form:input path="fromID" />
            <form:errors path="fromID" />
        </div>
        <div>
            <form:label path="toID">Id Client TO</form:label>
            <form:input path="toID" />
            <form:errors path="toID" />
        </div>
        <div>
            <form:label path="type">Type : D = deposit/W = withdraw</form:label>
            <form:input path="type" />
            <form:errors path="type" />
        </div>        
       <div>
            <form:label path="amount">Amount</form:label>
            <form:input path="amount" />
            <form:errors path="amount" />
        </div>
        <div>
            <form:label path="currency">Currency</form:label>
            <form:input path="currency" />
            <form:errors path="currency" />
        </div>
        <div>
            <input type="submit" />
        </div>
    </form:form>
</body>
</html>