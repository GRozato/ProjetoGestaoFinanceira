package br.com.sunna.gerenciador.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sunna.gerenciador.acoes.ExecucaoAcoes;

public class ControladorServ extends HttpServlet implements Filter{
	private static final long serialVersionUID = 1L;
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		String acao = request.getParameter("do");
		String nomeDaClasse = "br.com.sunna.gerenciador.acoes." + acao;
		String nome = null;
		
		try {	
			Class classe = Class.forName(nomeDaClasse);
			ExecucaoAcoes ExAcao = (ExecucaoAcoes) classe.newInstance(); 
			nome = ExAcao.executa(request,response);
			
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ServletException | SQLException e) {
			throw new ServletException(e);
		}

		String TipoEEndereco[] = nome.split(":");
		if (TipoEEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + TipoEEndereco[1]);
			rd.forward(request, response);  
		}else {
			
			response.sendRedirect(TipoEEndereco[1]);
		
		}
		
	}

}
