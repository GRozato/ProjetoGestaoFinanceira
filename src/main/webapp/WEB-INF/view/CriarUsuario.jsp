 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <c:url value="/Sunna?do=CriarNovoUsuario" var="linkRedirecionamento"/>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Criar novo usuário</title>
<style>

	body{
		font-family:arial;
	}

</style>
</head>
<body>

	<h2>Crie sua conta na Sunna!</h2><br><hr><br>
	<p>A Sunna é uma plataforma de gestão financeira e seu <b>analista financeiro de bolso.</b> 
		Crie sua conta agora, e fique por dentro da nossa plataforma.</p><br>
		
		<form action="${linkRedirecionamento}" method="post">
		
			Nome:<input type="text" required="required" name="nome">
			E-mail<input type="email" required="required" name="email"><br><br>
			Senha:<input type="password" required="required" name="senha">
			Confirme sua senha:<input type="password" required="required" name="confirmaSenha"><br><br>
			Criar conta<input type="submit">
		
		</form><br>		
		
		<p>${erro.nome}</p>
	

</body>
</html>