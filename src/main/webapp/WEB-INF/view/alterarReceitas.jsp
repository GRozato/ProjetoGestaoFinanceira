 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <c:url value="/Sunna?do=AlterarFinanca&id=${financa.idFinanca}&type=Receitas" var="linkServletNovaEmpresa"/>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alterando receita</title>
<style>
	body{
		font-family:arial;
	}
</style>
</head>
<body>
	<br>
	<h2>Alterar receita</h2>
	<br><hr><br>
	
	<form action="${linkServletNovaEmpresa}" method="post">
	
		Nome da receita <input type="text" required="required" name="nome" value="${financa.nomeFinanca} ">
		Data da receita <input type="text" name="data" value="<fmt:formatDate value="${financa.dataFinanca}" pattern="dd/MM/yyyy"/>">
		Valor da receita <input type="text" required="required" name="valor" value="${financa.valorFinanca}">
		
		<br><br>Confirmar receita <input type="submit">
			
	</form>
</body>
</html>