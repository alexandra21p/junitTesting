<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/imports.jsp" %>
<html>
<head>
    <title>Store Inventory</title>
    <style>
        body, h2, h4 {
            font-family: "Century Gothic", sans-serif;
            color: #999;
            margin-bottom: 0;
            font-weight: lighter;
            border-bottom: 1px ridge #CCCCCC;
        }
        h2 {
            margin: 30px 50px;
        }
        h4 {
            margin: 10px 50px;
        }
        form {
            max-width: 300px;
            margin-top: 20px;
            margin-left:50px;
            border: 1px solid #CCCCCC;
            border-radius: 3px;
            text-align: center;
        }

        #message {
            color: ghostwhite;
            background-color: lightcoral;
            margin: 50px;
            border: 1px solid #AA0000;
            padding: 10px 15px;
            border-radius: 3px;
        }

        label {
            display: inline-block;
            max-width: 70px;
            margin: 15px 0;
            color: #607D8B;
            font-weight: bold;
        }
        input {
            border-radius: 5px;
            border-style: ridge;
            height: 28px;
            font-size: 12px;
            padding-left: 8px;
            margin: 0 10px;
            font-family: "Century Gothic", sans-serif;
        }

        .submit-button {
            margin: 15px 0;
            font-family: "Century Gothic", sans-serif;
            background-color: #eee;
            border: none;
            padding: 0 10px;
            border-radius: 4px;
            cursor: pointer;
        }
        .submit-button:hover {
            background-color: #cfd8dc;
        }
        .submit-button:disabled {
            background-color: #eee;
            color: #ccc;
            cursor: auto;
        }
    </style>
</head>
<body>
<h2 class="title">Store Inventory</h2>

<div id="message">
    ${message}
</div>

<h4>Add a new product</h4>
<form:form name="product" action="/add" method="post">
    <div>
        <label for="code">Code</label>
        <input type="text" name="code" id="code" placeholder="Enter code..."/>
    </div>

    <div>
        <label for="name">Name</label>
        <input type="text" name="name" id="name" placeholder="Enter name..."/>
    </div>

    <div>
        <label for="category">Category</label>
        <input type="text" name="category" id="category" placeholder="Enter category..."/>
    </div>

    <div>
        <label for="quantity">Quantity</label>
        <input type="number" name="quantity" id="quantity" placeholder="Enter quantity..."/>
    </div>

    <div>
        <input class="submit-button" type="submit" name="saveBtn" value="Save"/>
    </div>

</form:form>
</body>
</html>