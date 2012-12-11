<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add expense</title>
        <link href="/akiba-frontend/resources/css/bootstrap.css" rel="stylesheet">

        <style type="text/css">
            body {
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }

            .form-addexpense {
                max-width: 300px;
                padding: 19px 29px 29px;
                margin: 0 auto 20px;
                background-color: #fff;
                border: 1px solid #e5e5e5;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
            }
            .form-addexpense .form-signin-heading,
            .form-signin .checkbox {
                margin-bottom: 10px;
            }
 
           

        </style>
    </head>
    <body>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="/akiba-frontend/resources/js/bootstrap.min.js"></script>

        <div class="container">

            <form:form action="./add" method = "POST" class="form-addexpense">
                <div class="control-group">
                    <div class="controls">
                        <h2 class="form-signin-heading">Add expense</h2>
                    </div>
                </div> 
                <div class="input-prepend input-append control-group">
                    <div class="controls">
                        <span class="add-on">Amount:</span>
                        <form:input path="amount" class="span2" id="appendedPrependedInput"/>
                        <span class="add-on">zł</span>
                    </div>
                </div> 

                <div class="control-group">
                    <label class="control-label" for="kindSelect">Kind: </label>
                    <div class="controls">
                        <form:select path="kind" id="kindSelect" >
                            <form:option value="" label="---" />
                            <form:options items="${kinds}" itemValue="id" itemLabel="name" />
                        </form:select>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="profileSelect">Profile: </label> 
                    <div class="controls">
                        <form:select path="profile" id="profileSelect">
                            <form:option value="" label="---" />
                            <form:options items="${profiles}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button type="submit" value="Add expence" class="btn btn-large btn-primary">Add expense</button>
                    </div>
                </div>
            </form:form>
        </div>
    </body>
</html>