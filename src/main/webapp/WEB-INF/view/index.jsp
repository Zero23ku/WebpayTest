<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html lang="es">
<head>
    <title>Prueba Transbank Webpay</title>
</head>
<body>
    <p>Hola Mundo</p>
    <button type="button" onclick="initTransaction();">Click Me!</button>

    <form id="transbankform" method="post" target="_blank">
        <input type="hidden" id="token_ws" name="token_ws"/>
    </form>
</body>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
<script>
    $(document).ready(function(){
        console.log("JQUERY LOADED");
    });

    function initTransaction(){
        $.post("/transactionTest",function(json){
            console.log("test init");
            console.log(json);
            $("#transbankform").attr("action",json.formAction);
            $("#token_ws").val(json.tokenWS);
            $("#transbankform").submit();
        });
    }

</script>


</html>
