 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <c:url value="/Sunna?do=CadastraFinanca&type=Gastos" var="linkRedirecionamento"/>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de receitas</title>
<style>

	body{
		font-family:arial;
	}

</style>
</head>
<body>
		<h1>Cadastro de gastos</h1>
		<br><hr>
		
		<form action="${linkRedirecionamento}" method="post">
		
			Nome do gasto <input type="text" name="nome" required="required">
			Data do gasto <input type="text" name="data">
			Valor do gasto <input type="text" name="valor" required="required">
			
			<br><br>Confirmar gasto <input type="submit">
			
		</form>
</body>
</html>