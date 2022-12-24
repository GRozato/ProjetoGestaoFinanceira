package br.com.sunna.gerenciador.acoes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sunna.gerenciador.banco.ConnectionFactory;
import br.com.sunna.gerenciador.banco.FinancaDAO;

public class ExcluirFinanca implements ExecucaoAcoes {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
		
			String idReceita = request.getParameter("id");
			String tipo = request.getParameter("type");
			
			HttpSession sessao = request.getSession();
			int idUsuario = (int) sessao.getAttribute("id");
			
			FinancaDAO dao = new FinancaDAO(connection);
			dao.deletarFinanca(idReceita, idUsuario, tipo);
			
			return "redirect:Sunna?do=ListaUsuario";
		}catch(SQLException el) {
			return "redirect:Sunna?do=ListaUsuario";
		}
	}
}
