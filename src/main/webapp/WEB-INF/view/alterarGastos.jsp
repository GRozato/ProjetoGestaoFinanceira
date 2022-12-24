 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <c:url value="/Sunna?do=AlterarFinanca&id=${financa.idFinanca}&type=Gastos" var="linkServletNovaEmpresa"/>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alterando gasto</title>
<style>
	body{
		font-family:arial;
	}
</style>
</head>
<body>
	<br>
	<h2>Alterar gasto</h2>
	<br><hr><br>
	
	<form action="${linkServletNovaEmpresa}" method="post">
	
		Nome do gasto<input type="text" required="required" name="nome" value="${financa.nomeFinanca} ">
		Data do gasto <input type="text" name="data" value="<fmt:formatDate value="${financa.dataFinanca}" pattern="dd/MM/yyyy"/>">
		Valor do gasto <input type="text" required="required" name="valor" value="${financa.valorFinanca}">
		
		<br><br>Confirmar gasto <input type="submit">
			
	</form>
</body>
</html>