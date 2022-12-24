 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visão geral</title>
	<style>
		body{
			font-family:arial;
		}
		 .container{
		 	display: flex;
		 }
		 .tabela-Receita{
		 	margin-right: 20px;
		 }
		 
		 table{
		 	border-collapse: collapse;
		 	width: 50%;
		 }
		 
		 .alinha {
  			text-align: center;
		}
		
		th, td {
 		 border-bottom: 1px solid #ddd;
 		 padding: 10px;
		}
		
		
		tr:nth-child(even) {background-color: #f2f2f2;}
		
		.resultsReceitas{
			font-weight: bold;
			background-color:#E4F5E1;
			font-size: 20px;
		}
		.resultsGastos{
			font-weight: bold;
			background-color:#FAE4E4;
			font-size: 20px;
		}
		
		.ValorRestante{
			border: 1px;
			font-size: 30px;
			background-color: #E4F5E1;
		}
		
	</style>
</head>
<body>
	          <a href="/ProjetoGestaoFinanceira/Sunna?do=Logout">Logout</a>
	     |     Usuario logado:<b> ${usuarlogado.nome }</b>
	<br><br><hr><br>
	<h1>Visão Geral</h1>	  
	<br><br>
		
		<div class="container">
		
		<table border="0" class="tabela-Receita">	
				<tr>
					<th class="alinha resultsReceitas">Receitas</th>
					<th class="alinha resultsReceitas">Data</th>
					<th class="alinha resultsReceitas">Valor</th>
					<th colspan="2" class="alinha resultsReceitas"><a href="/ProjetoGestaoFinanceira/Sunna?do=CadastrarNovaFinanca&type=Receitas">Cadastrar nova receita</a><br></th>
					</tr>
					<c:forEach items="${receitas}" var="receitas">
						<tr>
							<td>${receitas.nomeFinanca}</td>
							<td class="alinha"><fmt:formatDate value ="${receitas.dataFinanca}" pattern="dd/MM/yyyy"/></td>
							<td class="alinha">${receitas.valorFinanca}</td>	
							<td class="alinha"><a href="/ProjetoGestaoFinanceira/Sunna?do=EditarFinanca&id=${receitas.idFinanca}&type=Receitas">Editar receita</a></td>
							<td class="alinha"><a href="/ProjetoGestaoFinanceira/Sunna?do=ExcluirFinanca&id=${receitas.idFinanca}&type=Receitas">Excluir receita</a></td>
						</tr>
					</c:forEach>
						<tr>
							<td class="resultsReceitas"  colspan="3">Receitas Totais:</td>
							<td class="resultsReceitas"  colspan="2" align="center">${total.receitaTotal}</td>
						</tr>
					
			
			</table>
			
		
		<table border="0">	
				<tr>
					<th class="alinha resultsGastos">Gastos</th>
					<th class="alinha resultsGastos">Data</th>
					<th class="alinha resultsGastos">Valor</th>
					<th colspan="2" class="alinha resultsGastos"><a href="/ProjetoGestaoFinanceira/Sunna?do=CadastrarNovaFinanca&type=Gastos">Cadastrar novo gasto</a><br></th>
					</tr>
					<c:forEach items="${gastos}" var="gastos">
						<tr>
							<td>${gastos.nomeFinanca}</td>
					        <td class="alinha"><fmt:formatDate value ="${gastos.dataFinanca}" pattern="dd/MM/yyyy"/></td>
					        <td class="alinha">${gastos.valorFinanca}</td>
							<td class="alinha"><a href="/ProjetoGestaoFinanceira/Sunna?do=EditarFinanca&id=${gastos.idFinanca}&type=Gastos">Editar gasto</a></td>
							<td class="alinha"><a href="/ProjetoGestaoFinanceira/Sunna?do=ExcluirFinanca&id=${gastos.idFinanca}&type=Gastos">Excluir gasto</a></td>
						</tr>
					</c:forEach>
					<tr>
							<td class="resultsGastos"  colspan="3">Gastos Totais:</td>
							<td class="resultsGastos"  colspan="2" align="center">${total.gastoTotal}</td>
						</tr>
			</table>
		</div>
		<br><br>
		<div class="ValorRestante">
			<b>Valor restante:</b> ${total.valorTotal}
		</div>
</body>
</html>