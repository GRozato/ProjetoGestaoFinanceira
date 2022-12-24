package br.com.sunna.gerenciador.modelo;

import java.util.Date;
import java.util.Random;

public class Financa {
	
	private String idFinanca;
	private String nomeFinanca;
	private Date dataFinanca;
	private double valorFinanca;
	private int IdUsuarioFinanca;
	

	public String getNomeFinanca() {
		return nomeFinanca;
	}


	public void setNomeFinanca(String nomeFinanca) {
		this.nomeFinanca = nomeFinanca;
	}


	public Date getDataFinanca() {
		return dataFinanca;
	}


	public void setDataFinanca(Date dataFinanca) {
		this.dataFinanca = dataFinanca;
	}


	public double getValorFinanca() {
		return valorFinanca;
	}


	public void setValorFinanca(double valorFinanca) {
		this.valorFinanca = valorFinanca;
	}


	public int getIdUsuarioFinanca() {
		return IdUsuarioFinanca;
	}


	public void setIdUsuarioFinanca(int idUsuarioFinanca) {
		IdUsuarioFinanca = idUsuarioFinanca;
	}


	public String getIdFinanca() {
		return idFinanca;
	}


	public void setIdFinanca() {

		String codigo = "";
		String alfabeto = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random r = new Random();
		while (codigo.length() < 10) {
			char c = alfabeto.charAt(r.nextInt(alfabeto.length()));
			codigo = codigo + c;
		}

		this.idFinanca = codigo;

	}

	public void criaFinanca(String nomeFinanca, Date data, double valor, int idUsuario) {
		setNomeFinanca(nomeFinanca);
		setDataFinanca(data);
		setValorFinanca(valor);
		setIdUsuarioFinanca(idUsuario);
		setIdFinanca();
	}

	public void gerarFinancaSemIdUser(String idFinanca, String nomeFinanca, double valor, Date data) {
		setNomeFinanca(nomeFinanca);
		setDataFinanca(data);
		setValorFinanca(valor);
		this.idFinanca = idFinanca;
	}

	public void criaFinancaComTodosParametros(String nomeFinanca, Date data, 
													double valor, int idUsuario, String idFinanca) {
		setNomeFinanca(nomeFinanca);
		setDataFinanca(data);
		setValorFinanca(valor);
		setIdUsuarioFinanca(idUsuario);
		this.idFinanca = idFinanca;
	}
	
	@Override
	public String toString() {
		return String.format("Financa com nome %s, " + "com o valor de %f, do usuário %d e com ID da financa %s",
				nomeFinanca, valorFinanca, IdUsuarioFinanca, idFinanca);
	}

}
