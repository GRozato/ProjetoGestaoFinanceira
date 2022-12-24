package br.com.sunna.gerenciador.banco;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private DataSource dataSource;
		
	public ConnectionFactory(){
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		comboPooledDataSource.setJdbcUrl("************");
		comboPooledDataSource.setUser("************");
		comboPooledDataSource.setPassword("************");
		
		comboPooledDataSource.setMaxPoolSize(30);
		comboPooledDataSource.setMinPoolSize(1);
	
		this.dataSource = comboPooledDataSource;	
				
	}
	
	public Connection recuperarConexao() throws SQLException {
		
		return this.dataSource.getConnection(); 
		
	}
}
