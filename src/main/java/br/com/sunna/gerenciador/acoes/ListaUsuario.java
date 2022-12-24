package br.com.sunna.gerenciador.acoes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sunna.gerenciador.banco.ConnectionFactory;
import br.com.sunna.gerenciador.banco.FinancaDAO;
import br.com.sunna.gerenciador.modelo.Gastos;
import br.com.sunna.gerenciador.modelo.Receitas;

public class ListaUsuario implements ExecucaoAcoes {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			
			FinancaDAO dao = new FinancaDAO(connection);
			ValoresTotais vt = new ValoresTotais();

			HttpSession sessao = request.getSession();
			int idUsuario = (int) sessao.getAttribute("id");
			
			List<Receitas> receitasUsuario = dao.pegarReceitasDoUsuario(idUsuario);
			
			List<Gastos> gastosUsuario = dao.pegarGastosDoUsuario(idUsuario);

			if (receitasUsuario != null || gastosUsuario != null) {

				Double receitaTotal = 0.0, gastoTotal = 0.0;
				System.out.println("hm");
				
				for (Receitas receita : receitasUsuario) {
					receitaTotal += receita.getValorFinanca();
				}
				System.out.println("ha");
				for (Gastos gasto : gastosUsuario) {
					gastoTotal += gasto.getValorFinanca();
				}
				
				vt.setReceitaTotal(receitaTotal);
				vt.setGastoTotal(gastoTotal);
				vt.setValorTotal();

				request.setAttribute("receitas", receitasUsuario);
				request.setAttribute("gastos", gastosUsuario);
				request.setAttribute("total", vt);
			}
			return "forward:VisaoGeral.jsp";
		}

	}
	public class ValoresTotais{
		
		private Double receitaTotal;
		private Double gastoTotal;
		private Double valorTotal;
		public Double 
			
		getReceitaTotal() {
			return receitaTotal;
		}
		public void setReceitaTotal(Double receitaTotal) {
			this.receitaTotal = receitaTotal;
		}
		public Double getGastoTotal() {
			return gastoTotal;
		}
		public void setGastoTotal(Double gastoTotal) {
			this.gastoTotal = gastoTotal;
		}
		public void setValorTotal() {
			this.valorTotal = this.receitaTotal - this.gastoTotal;
		}
		public Double getValorTotal() {
			return valorTotal;
		}
		
	}
}


