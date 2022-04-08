package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Conexao;
import model.Usuarios;

public class DaoLoginRepository {
	
	private Connection connection;
	
	public DaoLoginRepository() {
		connection = Conexao.getConnection();
	}
	
	public boolean AutenticaLogineSenhaUsuario(Usuarios usuarios) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE upper(login) = upper(?) and upper(senha) = upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuarios.getLogin());
		statement.setString(2, usuarios.getSenha());
		
		ResultSet res = statement.executeQuery();
		
		if(res.next()) {
			return true;
		}
		
		return false; /*Usuário não autenticado*/
	}

}
