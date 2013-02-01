<%-- 
    Document   : fb
    Created on : 2012-12-12, 21:28:37
    Author     : kostrows
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello Facebook :)</h1>
        <div>
            <spring:eval expression="facebookProfile" />
        </div>
            
    </body>
</html>
