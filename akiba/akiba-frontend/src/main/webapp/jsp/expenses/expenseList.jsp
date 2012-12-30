<%-- 
    Document   : expenseList.jsp
    Created on : 2012-12-20, 22:16:05
    Author     : OstroS
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello Expenses!</h1>
        <c:forEach items="${expenses}" var="expense">
            <p>amount = ${expense.amount} ; kind = ${expense.kind} ; profile = ${expense.profile}
            
        </c:forEach>
            
    </body>
</html>
