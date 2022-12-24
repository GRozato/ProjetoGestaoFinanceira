package br.com.sunna.gerenciador.acoes;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExecucaoAcoes {
		
	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException;
	
}
