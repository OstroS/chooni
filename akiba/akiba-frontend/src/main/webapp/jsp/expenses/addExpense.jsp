<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add expense</title>
    </head>
    <body>
        <h1>Add expense</h1>
        <form:form action="./add" method = "POST">
            <p>Amount: <form:input path="amount" /></p>
            <p> Kind: 
                <form:select path="kind">
                    <form:option value="" label="---" />
                    <form:options items="${kinds}" itemValue="id" itemLabel="name" />
                </form:select>
            </p>
            <p>
                Profile: 
                <form:select path="profile">
                    <form:option value="" label="---" />
                    <form:options items="${profiles}" itemValue="id" itemLabel="name"/>
                </form:select>
            </p>-->
            <p><input type="submit" value="Add expence" /></p>
            </form:form>
    </body>
</html>