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
 <h1>Horários de Trabalho</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Entrada</th>
                <th>Intervalo (Início)</th>
                <th>Intervalo (Fim)</th>
                <th>Saída</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="horario" items="${horarios}">
                <tr>
                    <td>${horario.entrada}</td>
                    <td>${horario.intervaloInicio}</td>
                    <td>${horario.intervaloFim}</td>
                    <td>${horario.saida}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
