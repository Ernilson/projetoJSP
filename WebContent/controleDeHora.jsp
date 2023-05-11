<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Controle de Horário</title>
</head>
<body>

<h2>Horário de Trabalho</h2>
<form method="POST">
    Entrada: <input type="text" name="entrada" pattern="^([0-1][0-9]|2[0-3]):[0-5][0-9]$" placeholder="HH:MM" maxlength="5" required> 
    Saída: <input type="text" name="saida" pattern="^([0-1][0-9]|2[0-3]):[0-5][0-9]$" placeholder="HH:MM" maxlength="5" required> 
    <input type="submit" value="Cadastrar">
</form>
<!-- Aqui você poderia listar os horários de trabalho cadastrados -->

<h2>Marcações Feitas</h2>
<form method="POST">
    Entrada: <input type="text" name="entradaMarcacao" pattern="^([0-1][0-9]|2[0-3]):[0-5][0-9]$" placeholder="HH:MM" maxlength="5" required> 
    Saída: <input type="text" name="saidaMarcacao" pattern="^([0-1][0-9]|2[0-3]):[0-5][0-9]$" placeholder="HH:MM" maxlength="5" required> 
    <input type="submit" value="Cadastrar">
</form>
<!-- Aqui você poderia listar as marcações feitas -->

<h2>Atrasos</h2>
<!-- Aqui você poderia listar os atrasos -->

<h2>Hora Extra</h2>
<!-- Aqui você poderia listar as horas extras -->

</body>
</html>
