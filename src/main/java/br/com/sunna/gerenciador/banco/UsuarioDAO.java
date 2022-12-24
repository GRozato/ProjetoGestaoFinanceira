package br.com.sunna.gerenciador.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.sunna.gerenciador.modelo.Usuario;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}

	public void adicionarNovoUsuario(Usuario novoUsuario) throws SQLException {

		String SQL = "insert into Usuario(Nome,Email,Senha) " + "values (?, ?,?);";
		try (PreparedStatement pstm = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, novoUsuario.getNome());
			pstm.setString(2, novoUsuario.getEmail());
			pstm.setString(3, novoUsuario.getSenha());
			pstm.execute();
			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					novoUsuario.setID(rst.getInt(1));
				}
			}
		}
	}

	public boolean verificacaoDeEmail(String email) throws SQLException {

		String SQL = "select Email from Usuario where email = ?;";

		try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
			pstm.setString(1, email);
			pstm.execute();
			try (ResultSet rs = pstm.getResultSet()) {

				if (rs.next()) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	public Usuario verificarUsuario(String email, String senha) throws SQLException {

		String SQL = "select nome, email, senha, ID_USER from Usuario where email = ? and senha =?;";

		try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
			pstm.setString(1, email);
			pstm.setString(2, senha);
			pstm.execute();
			try (ResultSet rs = pstm.getResultSet()) {

				while (rs.next()) {
					Usuario user = new Usuario(rs.getString(1), rs.getString(2), rs.getString(3));
					user.setID(rs.getInt(4));
					return user;

				}
			}
		}
		return null;
	}

	
}
