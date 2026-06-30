<%@ page session="false" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Request object</h1>
    <p><%= request %></p>
    <pre>
        <%-- you can also inspect request details --%>
        Method: <%= request.getMethod() %>
        URI: <%= request.getRequestURI() %>
        Remote Addr: <%= request.getRemoteAddr() %>
        
        <%
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
            %>
                    <p>Cookie: <%= cookie.getName() %> = <%= cookie.getValue() %></p>
            <%
                }
            }
        %>
        
    </pre>
</body>
</html>