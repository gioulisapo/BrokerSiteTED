<%-- 
    Document   : responseSignin
    Created on : Aug 26, 2014, 9:07:36 PM
    Author     : Apostolis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/Response.css">
        <%if (request.getAttribute("close") != null)
             {%>
        <script type="text/javascript">
            setTimeout(
                    function( )
                    {
                        self.close();
                    }, 2500);
        </script>
        <%}
        else
            response.setHeader("Refresh", "3; URL=" + request.getAttribute("url") + "");%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
    </head>
    <body>
        <h1 id="message"><q>${message}</q></h1>
    </body>
</html>
