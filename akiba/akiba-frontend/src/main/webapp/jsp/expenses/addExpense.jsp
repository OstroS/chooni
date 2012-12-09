<%@page import="pl.akiba.frontend.expenses.controller.addExpenseController"%>
<%@page import="pl.akiba.model.entities.Profile"%>
<%@page import="java.util.List"%>
<%@page import="pl.akiba.model.entities.Kind"%>
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
        <p>${errMsg}</p>
        <form action="./add" method = "POST">
            <p>Amount: <input type="text" name="amount" /></p>
            <p>
                Kind: 
                <select name="kind">
                    <%
                        List<Kind> kinds = (List<Kind>) request.getAttribute(addExpenseController.KINDS_REF);
                        for (Kind kind : kinds) {
                            out.print("<option value=\"" + kind.getId() + "\">" + kind.getName() + "</option>");
                        }
                    %>

                </select>
            </p>
            <p>
                Profile: 
                <select name="profile">
                    <%
                        List<Profile> profiles = (List<Profile>) request.getAttribute(addExpenseController.PROFILES_REF);
                        for (Profile profile : profiles) {
                            out.print("<option value=\"" + profile.getId() + "\">" + profile.getName() + "</option>");
                        }
                    %>
                </select>
            </p>
            <p><input type="submit" value="Add expence" /></p>
        </form>
    </body>
</html>