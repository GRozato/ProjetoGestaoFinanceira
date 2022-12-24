package br.com.sunna.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutenticacaoUser extends HttpFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {}

	public void destroy() {}

	
	
	public void doFilter(ServletRequest ServletRequest, ServletResponse Servletresponse, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) ServletRequest;
		HttpServletResponse response = (HttpServletResponse) Servletresponse;
		
		String acao = request.getParameter("do");
		
		HttpSession sessao = request.getSession();
		boolean usuarioNaoEstaLogado = sessao.getAttribute("usuarlogado") == null;
		boolean AcaoNaoProtegida = acao.equals("Login") || 
				acao.equals("RealizarLogin") || acao.equals("RedirectCriarUsuario") ||
				acao.equals("CriarNovoUsuario");
		
		if(usuarioNaoEstaLogado && !AcaoNaoProtegida) {
			response.sendRedirect("Sunna?do=RealizarLogin");
			return;
		}

		chain.doFilter(request, response);
	}

	

}
