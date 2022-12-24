package br.com.sunna.gerenciador.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sunna.gerenciador.modelo.Financa;
import br.com.sunna.gerenciador.modelo.Gastos;
import br.com.sunna.gerenciador.modelo.Receitas;

public class FinancaDAO {

	private Connection connection;

	public FinancaDAO(Connection connection) {
		this.connection = connection;
	}

	public void cadastrarNovaFinanca(Financa financa, String tipo) throws SQLException{
		System.out.println("cadastra");
		String SQL = "insert into "+tipo+""
				+ "(Id"+tipo+",Nome,Valor,Data"+tipo+",User_ID) VALUES (?,?,?,?,?);";
		try(PreparedStatement pstm = connection.prepareStatement(SQL)){
			java.sql.Date sqlDate = null;
			if(financa.getDataFinanca()!=null) {
				sqlDate = new java.sql.Date(financa.getDataFinanca().getTime() + 86400000);
			}
			pstm.setString(1, financa.getIdFinanca());
			pstm.setString(2, financa.getNomeFinanca());
			pstm.setDouble(3, financa.getValorFinanca());
			pstm.setDate(4,sqlDate);
			pstm.setInt(5, financa.getIdUsuarioFinanca());
			pstm.execute();
		
		}
	}


	public void alterarFinanca(Financa financa, String idReceitas, String tipo) throws SQLException {
		String SQL = "update "+tipo+" set"
				+ " Nome = ?,Valor = ?,Data"+tipo+" = ? where Id"+tipo+" = ? and User_ID = ?;";
		try(PreparedStatement pstm = connection.prepareStatement(SQL)){
			java.sql.Date sqlDate = null;			
			if(financa.getDataFinanca()!=null) {
				sqlDate = new java.sql.Date(financa.getDataFinanca().getTime() + 86400000);
			}
			pstm.setString(1, financa.getNomeFinanca());
			pstm.setDouble(2, financa.getValorFinanca());
			pstm.setDate(3, sqlDate);
			pstm.setString(4, idReceitas);
			pstm.setInt(5, financa.getIdUsuarioFinanca());
			pstm.execute();
		}
		
	}
	
	
	public void deletarFinanca(String idFinanca, int idUsuario, String tipo) throws SQLException{
		System.out.println("deleta");
		String SQL = "Delete from "+tipo+" where id"+tipo+" = ? and User_ID = ?";
		try(PreparedStatement pstm = connection.prepareStatement(SQL)){
			
			pstm.setString(1, idFinanca);
			pstm.setInt(2, idUsuario);
			pstm.execute();
		}
		
	} 
	
	
	public List<Receitas> pegarReceitasDoUsuario(int idUsuario) throws SQLException{
		List<Receitas> receitasUsuario = new ArrayList<Receitas>();
		String SQL = "select IdReceitas, Nome, Valor, DataReceitas from Receitas where User_ID = ?";
		try(PreparedStatement pstm = connection.prepareStatement(SQL)){
			pstm.setInt(1, idUsuario);
			pstm.execute();
			try(ResultSet rs = pstm.getResultSet()){
				while(rs.next()) {
					Receitas rc = new Receitas();
					rc.gerarFinancaSemIdUser(rs.getString(1), rs.getString(2)
							, rs.getDouble(3), rs.getDate(4));
					rc.setIdUsuarioFinanca(idUsuario);
					receitasUsuario.add(rc);
				}
				return receitasUsuario;
			}
			
		}
		
	}
	
	public List<Gastos> pegarGastosDoUsuario(int idUsuario) throws SQLException{
		List<Gastos> gastosUsuario = new ArrayList<Gastos>();
		String SQL = "select IdGastos, Nome, Valor, DataGastos from Gastos where User_ID = ?";
		try(PreparedStatement pstm = connection.prepareStatement(SQL)){
			pstm.setInt(1, idUsuario);
			pstm.execute();
			try(ResultSet rs = pstm.getResultSet()){
				while(rs.next()) {
					Gastos g = new Gastos();
					g.gerarFinancaSemIdUser(rs.getString(1), rs.getString(2)
							, rs.getDouble(3), rs.getDate(4));
					g.setIdUsuarioFinanca(idUsuario);
					gastosUsuario.add(g);
				}
				return gastosUsuario;
			}
			
		}
		
	}
	

	public Financa buscarFinancaPorID(String idFinanca, int idUsuario, String tipo) throws SQLException {
		System.out.println("buscaporid");
		String SQL = "select Nome, Data"+tipo+",Valor, User_ID, Id"+tipo+"  "
							+ "from "+tipo+" where Id"+tipo+" = ? and User_ID = ?";
		
		try(PreparedStatement pstm = connection.prepareStatement(SQL)){
			pstm.setString(1, idFinanca);
			pstm.setInt(2, idUsuario);
			pstm.execute();
			try(ResultSet rs = pstm.getResultSet()){
				Financa financa = new Financa();
				while(rs.next()) {
					financa.criaFinancaComTodosParametros(
										rs.getString(1), rs.getDate(2),
												rs.getDouble(3), rs.getInt(4),rs.getString(5));
				}
				return financa;
			}
		}
	}
}














