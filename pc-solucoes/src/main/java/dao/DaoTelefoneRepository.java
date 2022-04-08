package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import model.Telefone;

public class DaoTelefoneRepository {

	private Connection connection;
	
	private UsuarioDaoRepository daoUsuarioRepository = new UsuarioDaoRepository();

	public DaoTelefoneRepository() {
		connection = Conexao.getConnection();
	}
	
	public void createFone(Telefone telefone) throws Exception {

		String sql = "INSERT INTO telefone (numero,usuario_id, usuario_cad_id) values(?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, telefone.getNumero());
		statement.setLong(2, telefone.getUsuario_id().getId());
		statement.setLong(3, telefone.getUsuario_cad_id().getId());
		statement.execute();
		connection.commit();

		connection.rollback();

	}
	
	
	public List<Telefone> listaTelefones(Long idUserPai) throws Exception{
			
		List<Telefone> listTelefone = new ArrayList<Telefone>();
		String sql= "SELECT * FROM telefone where usuario_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, idUserPai);
		ResultSet res = statement.executeQuery();
		while(res.next()) {
				
				Telefone telefone = new Telefone();
				telefone.setId(res.getLong("id"));
				telefone.setNumero(res.getString("numero"));
				telefone.setUsuario_id(daoUsuarioRepository.buscarUsuarioTelefone(res.getLong("usuario_id")));
				telefone.setUsuario_cad_id(daoUsuarioRepository.buscarUsuarioTelefone(res.getLong("usuario_cad_id")));
				
				listTelefone.add(telefone);
			
		}
		
		return listTelefone;
		
		
	}

	public void deleteFone(Long id) throws Exception {
		String sql = "DELETE FROM telefone where id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);

		statement.executeUpdate();
		connection.commit();
	}


	/* Método responsável por verificar se existe telefone*/
	public boolean existeTelefone(String fone, Long iduser) throws Exception {
		String sql = "SELECT count(1) > 0 as existe from telefone where usuario_id =? and numero =? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, iduser);
		statement.setString(2, fone);
		ResultSet res = statement.executeQuery();
		res.next();
		return res.getBoolean("existe");
	}
}
