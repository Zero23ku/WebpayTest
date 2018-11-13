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
    <input type="text" id="valor" name="valor"/>
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
        var valor = $("#valor").val();
        valor = parseFloat(valor);
        var data = {
            amount : valor
        }
        $.ajax({
            url: "/transactionTest",
            dataType: 'json',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(data){
                console.log(data);
                $("#transbankform").attr("action",data.formAction);
                $("#token_ws").val(data.tokenWS);
                $("#transbankform").submit();
            },
            error: function(){
                alert("Error");
            }
        });
    }

</script>


</html>
