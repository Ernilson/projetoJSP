<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Controle de Horário</title>
</head>
<body>

<h2>Horário de Trabalho</h2>
<form method="POST" action="HoraDeTrabalhoServlet">
    <input type="hidden" name="action" value="add">
    <label> CPF: <input type="text" name="cpf" required></label>
    Entrada: <input type="text" name="entrada" pattern="^([0-1][0-9]|2[0-3]):[0-5][0-9]$" placeholder="HH:MM" maxlength="5" required> 
    Inicio do Intervalo: <input type="text" name="intervaloInicio" pattern="^([0-1][0-9]|2[0-3]):[0-5][0-9]$" placeholder="HH:MM" maxlength="5" required>
    Fim do Intervalo: <input type="text" name="intervaloFim" pattern="^([0-1][0-9]|2[0-3]):[0-5][0-9]$" placeholder="HH:MM" maxlength="5" required>  
    Saída: <input type="text" name="saida" pattern="^([0-1][0-9]|2[0-3]):[0-5][0-9]$" placeholder="HH:MM" maxlength="5" required> 
    <input type="submit" value="Cadastrar">
</form>

<!-- Aqui você poderia listar os horários de trabalho cadastrados -->


</body>
</html>
