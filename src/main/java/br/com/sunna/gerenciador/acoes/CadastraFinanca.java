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
import br.com.sunna.gerenciador.modelo.Financa;

public class CadastraFinanca implements ExecucaoAcoes {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			
			HttpSession sessao = request.getSession();
			int ID = (int) sessao.getAttribute("id");
			
			String nome = request.getParameter("nome");
			String ParamData = request.getParameter("data");
			String ParamValor = request.getParameter("valor");
			String tipo = request.getParameter("type");
			Double valor = Double.parseDouble(ParamValor);
			
			Date data = null;
			if(ParamData!="") {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					data = sdf.parse(ParamData);
				} catch (ParseException e) {
					throw new ServletException(e);
				}
			}
			

			Financa fin = new Financa();
			fin.criaFinanca(nome, data, valor, ID);
			
			
			FinancaDAO dao = new FinancaDAO(connection);
			dao.cadastrarNovaFinanca(fin, tipo);
			
			return "redirect:Sunna?do=ListaUsuario";
		}catch(SQLException el) {
			return "redirect:Sunna?do=ListaUsuario";
		}
		
		
		
	}

}
