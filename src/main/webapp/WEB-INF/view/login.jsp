 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <c:url value="/Sunna?do=Login" var="linkRedirecionamento"/>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>

	body{
		font-family:arial;
	}

</style>
</head>
<body>
	<h2>Realize seu login</h2>|<a href="/ProjetoGestaoFinanceira/Sunna?do=RedirectCriarUsuario">Criar novo usuário</a>|
	<br><hr><br>
</body>
	<form action="${linkRedirecionamento}" method="post">
		E-mail:<input type="text" name="email">
		Senha:<input type="password" name="senha">
		<br><br>		
		Realizar Login<input type="submit">
	
	</form>
</html>