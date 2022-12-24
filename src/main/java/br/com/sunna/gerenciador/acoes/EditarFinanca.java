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
import br.com.sunna.gerenciador.modelo.Financa;

public class EditarFinanca implements ExecucaoAcoes {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
		
			FinancaDAO dao = new FinancaDAO(connection);
			String idFinanca = request.getParameter("id");
			String tipo = request.getParameter("type");
			
			HttpSession sessao = request.getSession();
			int idUsuario = (int) sessao.getAttribute("id");
			
			Financa financa = dao.buscarFinancaPorID(idFinanca, idUsuario, tipo);
			request.setAttribute("financa", financa);
			
			System.out.println("alterar"+tipo+".jsp");
			return "forward:alterar"+tipo+".jsp";
		}
	}

}
