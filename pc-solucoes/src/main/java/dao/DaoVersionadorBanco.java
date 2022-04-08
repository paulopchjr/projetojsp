package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.Conexao;

public class DaoVersionadorBanco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection connection;

	public DaoVersionadorBanco() {
		connection = Conexao.getConnection();

	}

	
	public void gravarArquivoSqlRodado(String nomeFile)  {
		try {
		String sql =" INSERT INTO versionadorbanco(arquivo_sql) values (?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, nomeFile);
		statement.execute();
		
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public boolean arquivoSqlRodado(String nome_arquivo) throws Exception {

		String sql = "SELECT count(1) > 0 as rodado FROM versionadorbanco where arquivo_sql = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, nome_arquivo);
		ResultSet res = statement.executeQuery();
		res.next();
		return res.getBoolean("rodado");

	}

}
