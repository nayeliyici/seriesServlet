<%@ page import="java.io.*, javax.xml.parsers.*, org.w3c.dom.*, javax.xml.transform.*, javax.xml.transform.dom.*, javax.xml.transform.stream.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            pre {
                font-family: monospace;
                background-color: #f4f4f4;
                padding: 10px;
                border-radius: 5px;
                white-space: pre-wrap;
                word-wrap: break-word;
            }
        </style>
    </head>
    <body>
        <h2>Contenido del archivo XML</h2>
        <pre>${xmlContent}</pre>
        <form action="${pageContext.request.contextPath}/pages/descargarXml.jsp" method="GET">
            <input type="hidden" name="filePath" value="${xmlFilePath}">
            <button type="submit" class="btn btn-outline-primary">Descargar XML</button>
        </form>
    </body>
</html>
