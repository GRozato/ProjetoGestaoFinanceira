 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <c:url value="/Sunna?do=CadastraFinanca&type=Receitas" var="linkRedirecionamento"/>
 
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
		<h1>Cadastro de receitas</h1>
		<br><hr>
		
		<form action="${linkRedirecionamento}" method="post">
		
			Nome da receita <input type="text" name="nome" required="required">
			Data da receita <input type="text" name="data">
			Valor da receita <input type="text" name="valor" required="required">
			
			<br><br>Confirmar receita <input type="submit">
			
		</form>
</body>
</html>