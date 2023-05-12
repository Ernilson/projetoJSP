<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Controle de Horário</title>
    <link rel="stylesheet" href="style.css">    
</head>
<body>

<h2 class="semMargem">Horário de Trabalho</h2>
<form method="POST">
    Entrada: <input type="text" name="entrada" pattern="^([0-1][0-9]|2[0-3]):[0-5][0-9]$" placeholder="HH:MM" maxlength="5" required>
    Saída: <input type="text" name="saida" pattern="^([0-1][0-9]|2[0-3]):[0-5][0-9]$" placeholder="HH:MM" maxlength="5" required>
    <div>
    <br>
    <input type="submit" value="Cadastrar">
    <input type="button" value="Excluir todos" onclick="if(confirm('Tem certeza que deseja excluir todos?')) { document.forms[0].reset(); }">   
    </div>
    <div class="clear"></div>
</form>

<!-- Lista os horários de trabalho cadastrados -->
<table class="horarios">
  <thead>
    <tr>
      <th>Entrada</th>
      <th>Saída</th>
      <th>Opções</th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="horario" items="${horarios}">
      <tr>
        <td>${horario.entrada}</td>
        <td>${horario.saida}</td>
        <td>
        <button class="editar" onclick="editarHorario(${horario.id})">Editar</button>
        <button class="excluir" onclick="excluirHorario(${horario.id})" onclick="if(confirm('Tem certeza que deseja excluir esse(s) horários?')) { document.forms[0].reset(); }">Excluir</button>
        </td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<div class="clear"></div>
<h2 class="comMargem">Marcações Feitas</h2>
<form method="POST">
    Entrada: <input type="text" name="entradaMarcacao" pattern="^([0-1][0-9]|2[0-3]):[0-5][0-9]$" placeholder="HH:MM" maxlength="5" required> 
    Saída: <input type="text" name="saidaMarcacao" pattern="^([0-1][0-9]|2[0-3]):[0-5][0-9]$" placeholder="HH:MM" maxlength="5" required> 
    <div>
    <br>
    <input type="submit" value="Cadastrar">
    </div>
</form>

<!-- Lista das marcações feitas -->
<table class="horarios">
  <thead>
    <tr>     
      <th>Entrada</th>
      <th>Saída</th>
    </tr>
  </thead>
  <tbody>
    <tr>      
      <td>07:00</td>
      <td>12:30</td>
    </tr>
    <tr>      
      <td>14:00</td>
      <td>17:30</td>
    </tr>   
  </tbody>
</table>
<div class="clear"></div>

<h2 class="comMargem">Atrasos</h2>
<!-- Lista dos atrasos -->
<table class="horarios">
  <thead>
    <tr>     
      <th>Entrada</th>
      <th>Saída</th>
    </tr>
  </thead>
  <tbody>
    <tr>      
      <td>13:30</td>
      <td>14:00</td>
    </tr>
    <tr>      
      <td>17:00</td>
      <td>17:30</td>
    </tr>    
  </tbody>
</table>
<div class="clear"></div>

<h2 class="comMargem">Hora Extra</h2>
<!-- Lista das horas extras -->
<table class="horarios">
  <thead>
    <tr>     
      <th>Entrada</th>
      <th>Saída</th>
    </tr>
  </thead>
  <tbody>
    <tr>      
      <td>07:00</td>
      <td>18:00</td>
    </tr>
    <tr>      
      <td>12:00</td>
      <td>12:30</td>
    </tr>   
  </tbody>
</table>
<div class="clear"></div>
<script>
  function excluirHorario(id) {
    if (confirm("Tem certeza que deseja excluir esse horário?")) {
      // enviar uma requisição AJAX para excluir o horário com o ID especificado
      $.ajax({
        url: "excluirHorario.jsp",
        type: "POST",
        data: { id: id },
        success: function(result) {
          alert("Horário excluído com sucesso!");
          location.reload(); // recarrega a página para atualizar a tabela
        },
        error: function(xhr, status, error) {
          alert("Ocorreu um erro ao excluir o horário: " + error);
        }
      });
    }
  }

  function editarHorario(id) {
    // redirecionar para uma página de edição de horário com o ID especificado
    window.location.href = "editarHorario.jsp?id=" + id;
  }
</script>
</body>
</html>