<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html lang="es">
<head>
    <title>Prueba Transbank Webpay</title>
</head>
<body>
<p>Intermedio :)</p>


<form id="transbankform" method="post" target="_blank" action="${redirectURL}">
    <input type="hidden" id="token_ws" name="token_ws" value="${token_ws}"/>
</form>
</body>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>
    $(document).ready(function(){
        console.log("READY GO");
        $("#transbankform").submit();
        setTimeout(function(){
            window.top.close();
        },1000);
    });

</script>


</html>
