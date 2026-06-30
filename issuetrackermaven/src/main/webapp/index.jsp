<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<html>

<style>
    body {
        margin: 0;
        background-color: #333;  /* Dark background for a more modern look */
        color: #fff;
        font-family: Arial, sans-serif;  /* Smooth, sans-serif font */
    }
</style>
<body>
<h2><%= "Hello World!" %></h2>
<%  Date date = new Date(System.currentTimeMillis()); %>
<p>Date is <%= date%></p>
</body>
</html>
