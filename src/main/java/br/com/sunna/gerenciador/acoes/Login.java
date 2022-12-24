package br.com.sunna.gerenciador.acoes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sunna.gerenciador.banco.ConnectionFactory;
import br.com.sunna.gerenciador.banco.UsuarioDAO;
import br.com.sunna.gerenciador.modelo.Usuario;

public class Login implements ExecucaoAcoes {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {

			String email = request.getParameter("email");
			String senha = request.getParameter("senha");

			UsuarioDAO dao = new UsuarioDAO(connection);

			Usuario usuario = dao.verificarUsuario(email, senha);
			if (!(usuario == null)) {

				HttpSession sessao = request.getSession();

				sessao.setAttribute("usuarlogado", usuario);
				sessao.setAttribute("id", (int) usuario.getID());

				return "redirect:Sunna?do=ListaUsuario";
			} else {
				return "redirect:Sunna?do=RealizarLogin";
			}
		}
	}
}
