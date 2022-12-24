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

public class CriarNovoUsuario implements ExecucaoAcoes {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {
			UsuarioDAO dao = new UsuarioDAO(connection);
			Erro erro = new Erro();
			HttpSession sessao = request.getSession();

			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String confirmaSenha = request.getParameter("confirmaSenha");


			if (!senha.equals(confirmaSenha)) {
				erro.setNome("As senhas precisam ser iguais.");
				request.setAttribute("erro", erro);
				return "forward:CriarUsuario.jsp";
			}
			
			boolean emailUserExiste = dao.verificacaoDeEmail(email);
			
			if(emailUserExiste) {
				erro.setNome("Esse e-mail já está atribuído a um usuário.");
				request.setAttribute("erro", erro);
				return "forward:CriarUsuario.jsp";
			}

			Usuario novoUsuario = new Usuario(nome, email, senha);
			dao.adicionarNovoUsuario(novoUsuario);

			sessao.setAttribute("usuarlogado", novoUsuario);
			sessao.setAttribute("id", (int) novoUsuario.getID());

			return "redirect:Sunna?do=ListaUsuario";

		}
	}

	public class Erro {
		String nome;

		public String getNome() {
			return nome;
		}

		public void setNome(String erro) {
			this.nome = erro;
		}

	}

}
