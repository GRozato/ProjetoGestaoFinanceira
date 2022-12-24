package br.com.sunna.gerenciador.acoes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sunna.gerenciador.banco.ConnectionFactory;
import br.com.sunna.gerenciador.banco.FinancaDAO;
import br.com.sunna.gerenciador.modelo.Gastos;
import br.com.sunna.gerenciador.modelo.Receitas;

public class AlterarFinanca implements ExecucaoAcoes {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			
		HttpSession sessao = request.getSession();
		int idUsuario = (int) sessao.getAttribute("id");
		
		String a = "Receitas";
		String nome = request.getParameter("nome");
		String ParamData = request.getParameter("data");
		String ParamValor = request.getParameter("valor");
		String idFinanca = request.getParameter("id");
		String tipo = request.getParameter("type");
				
		Double valor = Double.parseDouble(ParamValor);
		Date data = null;
		if (ParamData != "") {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				data = sdf.parse(ParamData);
			} catch (ParseException e) {
				throw new ServletException(e);
			}
		}
		
		FinancaDAO dao = new FinancaDAO(connection);
		
		if(tipo.equals(a)) {
			
			Receitas receita = new Receitas();
			receita.setDataFinanca(data);
			receita.setNomeFinanca(nome);
			receita.setValorFinanca(valor);
			receita.setIdUsuarioFinanca(idUsuario);
			dao.alterarFinanca(receita, idFinanca, tipo);
			
		}else {
			Gastos gasto = new Gastos();
			gasto.setDataFinanca(data);
			gasto.setNomeFinanca(nome);
			gasto.setValorFinanca(valor);
			gasto.setIdUsuarioFinanca(idUsuario);
			dao.alterarFinanca(gasto, idFinanca, tipo);
		}
		
		return "redirect:Sunna?do=ListaUsuario";
		}catch(SQLException el) {
			return "redirect:Sunna?do=ListaUsuario";
		}
	}

}
