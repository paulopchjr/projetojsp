package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import beandto.BeanDtoGraficoSalarioUser;
import connection.Conexao;
import model.Telefone;
import model.Usuarios;

public class UsuarioDaoRepository {

	private Connection connection;
	

	public UsuarioDaoRepository() {
		connection = Conexao.getConnection();
	}

	/* Metódo responsável por cadastrar usuarios */
	public void createUsuario(Usuarios usuarios, Long usuarioLogado) {
		try {

			String sql = "INSERT INTO usuario (nome, email,login,senha,usuario_id, perfil,sexo,cep,endereco, "
					+ "numero,bairro,cidade,estado,dataNascimento,salario) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuarios.getNome());
			statement.setString(2, usuarios.getEmail());
			statement.setString(3, usuarios.getLogin());
			statement.setString(4, usuarios.getSenha());
			statement.setLong(5, usuarioLogado);
			statement.setString(6, usuarios.getPerfil());
			statement.setString(7, usuarios.getSexo());
			statement.setString(8, usuarios.getCep());
			statement.setString(9, usuarios.getEndereco());
			statement.setString(10, usuarios.getNumero());
			statement.setString(11, usuarios.getBairro());
			statement.setString(12, usuarios.getCidade());
			statement.setString(13, usuarios.getEstado());
			statement.setDate(14, usuarios.getDataNascimento());
			statement.setDouble(15, usuarios.getSalario());
			statement.execute();

			if (usuarios.getFotouser() != null && !usuarios.getFotouser().isEmpty()) {
				sql = "update usuario set fotouser = ?, extensaofotouser = ? where login = ?";
				statement = connection.prepareStatement(sql);
				statement.setString(1, usuarios.getFotouser());
				statement.setString(2, usuarios.getExtensaofotouser());
				statement.setString(3, usuarios.getLogin());
				statement.execute();
			}
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * metodo responsável por listar usuários que foram cadastro por outros usuários
	 * que não sejam admin
	 */
	public List<Usuarios> listarUsuarios(Long usuarioLoggado) throws Exception {
		List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();
		String sql = "SELECT * FROM usuario WHERE useadmin is false and usuario_id= " + usuarioLoggado;
			   sql+=" order by nome LIMIT 5";
				
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet res = statement.executeQuery();
		while (res.next()) {
			Usuarios usuarios = new Usuarios();
			usuarios.setId(res.getLong("id"));
			usuarios.setNome(res.getString("nome"));
			usuarios.setEmail(res.getString("email"));
			usuarios.setLogin(res.getString("login"));
			usuarios.setPerfil(res.getString("perfil"));
			usuarios.setSexo(res.getString("sexo"));
			usuarios.setCep(res.getString("cep"));
			usuarios.setEndereco(res.getString("endereco"));
			usuarios.setNumero(res.getString("numero"));
			usuarios.setBairro(res.getString("bairro"));
			usuarios.setCidade(res.getString("cidade"));
			usuarios.setEstado(res.getString("estado"));
			usuarios.setSalario(res.getDouble("salario"));
			
			listaUsuarios.add(usuarios);
		}
		return listaUsuarios;
	}

	/* Metódo responsávl por atualizar o usuario */
	public void update(Usuarios usuarios) throws SQLException {
		try {
			String sql = "UPDATE usuario SET nome =?, email =?, login =?, senha = ?, perfil =?, sexo=?, cep=?, endereco=?, numero=?,"
					+ " bairro=?, cidade=?, estado=?, dataNascimento = ?, salario = ? where id = " + usuarios.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuarios.getNome());
			statement.setString(2, usuarios.getEmail());
			statement.setString(3, usuarios.getLogin());
			statement.setString(4, usuarios.getSenha());
			statement.setString(5, usuarios.getPerfil());
			statement.setString(6, usuarios.getSexo());
			statement.setString(7, usuarios.getCep());
			statement.setString(8, usuarios.getEndereco());
			statement.setString(9, usuarios.getNumero());
			statement.setString(10, usuarios.getBairro());
			statement.setString(11, usuarios.getCidade());
			statement.setString(12, usuarios.getEstado());
			statement.setDate(13, usuarios.getDataNascimento());
			statement.setDouble(14, usuarios.getSalario());

			statement.executeUpdate();

			if (usuarios.getFotouser() != null && !usuarios.getFotouser().isEmpty()) {
				sql = "update usuario set fotouser = ?, extensaofotouser = ? where id = ?";
				statement = connection.prepareStatement(sql);
				statement.setString(1, usuarios.getFotouser());
				statement.setString(2, usuarios.getExtensaofotouser());
				statement.setLong(3, usuarios.getId());
				statement.execute();
			}
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		connection.rollback();
	}

	/*
	 * Método responsável para verificar se há um usuário identico ao que está sendo
	 * registrado, pelo login
	 */
	public Boolean verificaUsuario(String login) throws Exception {

		String sql = "SELECT count(1) as existe from usuario where upper(login) = upper('" + login + "') ";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet res = statement.executeQuery();
		res.next();
		return res.getBoolean("existe");

	}

	/* Método responsável para buscar Usuário para edição */
	public Usuarios buscarUsuarioEdicao(String id, Long usuarioLogado) throws Exception {

		Usuarios usuario = new Usuarios();
		String sql = "SELECT * FROM usuario WHERE id = ? and useadmin is false and usuario_id =?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, usuarioLogado);
		ResultSet res = statement.executeQuery();
		while (res.next()) {
			usuario.setId(res.getLong("id"));
			usuario.setNome(res.getString("nome"));
			usuario.setEmail(res.getString("email"));
			usuario.setLogin(res.getString("login"));
			usuario.setSenha(res.getString("senha"));
			usuario.setPerfil(res.getString("perfil"));
			usuario.setSexo(res.getString("sexo"));
			usuario.setFotouser(res.getString("fotouser"));
			usuario.setExtensaofotouser(res.getString("extensaofotouser"));
			usuario.setCep(res.getString("cep"));
			usuario.setEndereco(res.getString("endereco"));
			usuario.setNumero(res.getString("numero"));
			usuario.setBairro(res.getString("bairro"));
			usuario.setCidade(res.getString("cidade"));
			usuario.setEstado(res.getString("estado"));
			usuario.setSalario(res.getDouble("salario"));
			usuario.setDataNascimento(res.getDate("dataNascimento"));
		}

		return usuario;

	}

	/* Método responsável para excluir um usuario */
	public void Delete(String id) throws SQLException {
		try {
			String sql = "DELETE FROM usuario where id = ? and useadmin is false";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(id));
			statement.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		connection.rollback();
	}

	/* Método responsável para buscar o usuário por nome */
	public List<Usuarios> filtroPorNome(String nome, Long usuarioLogado) throws Exception {

		List<Usuarios> listaUsuario = new ArrayList<Usuarios>();
		String sql = "SELECT * FROM usuario where upper(nome) like upper(?) and useadmin is false and usuario_id = ? limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, usuarioLogado);
		ResultSet res = statement.executeQuery();
		while (res.next()) {

			Usuarios usuarios = new Usuarios();
			usuarios.setId(res.getLong("id"));
			usuarios.setNome(res.getString("nome"));
			usuarios.setEmail(res.getString("email"));
			usuarios.setLogin(res.getString("login"));
			usuarios.setSenha(res.getString("senha"));
			usuarios.setPerfil(res.getString("perfil"));
			usuarios.setSexo(res.getString("sexo"));
			listaUsuario.add(usuarios);
		}
		return listaUsuario;

	}

	/* método responsável por saber qual perfil está sendo logado com o login */
	public Usuarios consultarUsuario(String login) throws Exception {

		Usuarios usuarios = new Usuarios();
		String sql = "SELECT * FROM usuario WHERE upper(login) = upper('" + login + "')";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet res = statement.executeQuery();
		while (res.next()) {
			usuarios.setId(res.getLong("id"));
			usuarios.setNome(res.getString("nome"));
			usuarios.setEmail(res.getString("email"));
			usuarios.setLogin(res.getString("login"));
			usuarios.setSenha(res.getString("senha"));
			usuarios.setUseadmin(res.getBoolean("useadmin"));
			usuarios.setPerfil(res.getString("perfil"));
			usuarios.setFotouser(res.getString("fotouser"));
		}

		return usuarios;
	}
	
	
	/* ####### PAGINAÇÃO #######*/

	
	public List<Usuarios> listarUsuariosporPagina(Long usuarioLoggado, Integer offset) throws Exception {
		List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();
		String sql = "SELECT * FROM usuario where usuario_id ="+usuarioLoggado;
				sql+=" and useadmin is false order by nome limit 5  offset " + offset;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet res = statement.executeQuery();
		while (res.next()) {
			Usuarios usuarios = new Usuarios();
			usuarios.setId(res.getLong("id"));
			usuarios.setNome(res.getString("nome"));
			usuarios.setEmail(res.getString("email"));
			usuarios.setLogin(res.getString("login"));
			usuarios.setPerfil(res.getString("perfil"));
			usuarios.setSexo(res.getString("sexo"));
			usuarios.setCep(res.getString("cep"));
			usuarios.setEndereco(res.getString("endereco"));
			usuarios.setNumero(res.getString("numero"));
			usuarios.setBairro(res.getString("bairro"));
			usuarios.setCidade(res.getString("cidade"));
			usuarios.setEstado(res.getString("estado"));
			listaUsuarios.add(usuarios);
		}
		return listaUsuarios;
	}
	
	
	
	
	/* metodo responsável por calcular o total de usuarios e montar sql de 5 em 5 usuario cadastro */
	public int totalPagina(Long usuarioLogado) throws Exception {
		
		String sql ="SELECT COUNT(1) as totalusuarios FROM usuario where useadmin is false and usuario_id =" + usuarioLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet res = statement.executeQuery();
		
		res.next();
		Double cadastros = res.getDouble("totalusuarios");
		
		Double porpagina = 5.0;
		
		Double pagina = cadastros / porpagina;
		
		Double resto = pagina % 2;
		
		if(resto > 1.0) {
			pagina ++;
		}
		
		return pagina.intValue();
		
		
	}
	
	
	
	/*Método responsável por somar todos os usuários que foram filtrados pelo nome 
	 * e somar a quantidade de pagina sobre eles*/
	public int paginacaofiltroPorNome(String nome, Long usuarioLogado) throws Exception {
		String sql = "SELECT count(1) as userfiltrado  FROM usuario where upper(nome) like upper(?)";
			   sql+= " and useadmin is false and usuario_id = ? limit 5 ";
			   PreparedStatement statement = connection.prepareStatement(sql);
			   statement.setString(1, "%" + nome + "%");
				statement.setLong(2, usuarioLogado);
				ResultSet res = statement.executeQuery();
				
				res.next();
				Double cadastros = res.getDouble("userfiltrado");
				
				Double porpagina = 5.0;
				
				Double pagina = cadastros / porpagina;
				
				Double resto = pagina % 2;
				
				if(resto > 0) {
					pagina ++;
				}
				
				return pagina.intValue();
				
	
	}
	
	
	
	
	/* Método responsável para buscar o usuário por nome e retornar os registros paginados no modal  */
	public List<Usuarios> paginacaofiltroPorNomeOffset(String nome, Long usuarioLogado, String offset) throws Exception {

		List<Usuarios> listaUsuario = new ArrayList<Usuarios>();
		String sql = "SELECT * FROM usuario where upper(nome) like upper(?) and useadmin is false and ";
			   sql+= "usuario_id = ?  order by nome limit 5 offset "+ offset;
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, usuarioLogado);
		ResultSet res = statement.executeQuery();
		while (res.next()) {

			Usuarios usuarios = new Usuarios();
			usuarios.setId(res.getLong("id"));
			usuarios.setNome(res.getString("nome"));
			usuarios.setEmail(res.getString("email"));
			usuarios.setLogin(res.getString("login"));
			usuarios.setSenha(res.getString("senha"));
			usuarios.setPerfil(res.getString("perfil"));
			usuarios.setSexo(res.getString("sexo"));
			listaUsuario.add(usuarios);
		}
		return listaUsuario;

	}
	
	
	/*METODOS PARA O RELACIONAMENTO COM TELEFONE*/
	
	/*metodo responsável por buscar um usuario para o telefone*/
	public Usuarios buscarUsuarioTelefone(Long id) throws Exception {

		Usuarios usuario = new Usuarios();
		String sql = "SELECT * FROM usuario WHERE id = ? and useadmin is false";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		ResultSet res = statement.executeQuery();
		while (res.next()) {
			usuario.setId(res.getLong("id"));
			usuario.setNome(res.getString("nome"));
			usuario.setEmail(res.getString("email"));
			usuario.setLogin(res.getString("login"));
			usuario.setSenha(res.getString("senha"));
			usuario.setPerfil(res.getString("perfil"));
			usuario.setSexo(res.getString("sexo"));
			usuario.setFotouser(res.getString("fotouser"));
			usuario.setExtensaofotouser(res.getString("extensaofotouser"));
			usuario.setCep(res.getString("cep"));
			usuario.setEndereco(res.getString("endereco"));
			usuario.setNumero(res.getString("numero"));
			usuario.setBairro(res.getString("bairro"));
			usuario.setCidade(res.getString("cidade"));
			usuario.setEstado(res.getString("estado"));
		}

		return usuario;

	}
		
	
		/*METODOS PARA RELATÓRIOS*/
	
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
				telefone.setUsuario_id(this.buscarUsuarioTelefone(res.getLong("usuario_id")));
				telefone.setUsuario_cad_id(this.buscarUsuarioTelefone(res.getLong("usuario_cad_id")));
				
				listTelefone.add(telefone);
			
		}
		
		return listTelefone;
		
	}
	
	/* Método responsável por imprimir um relatório de usuário html e PDF */
	public List<Usuarios> listarRelatorioUsuario(Long usuarioLoggado) throws Exception {
		List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();
		String sql = "SELECT * FROM usuario WHERE useadmin is false and usuario_id= ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, usuarioLoggado);
		ResultSet res = statement.executeQuery();
		while (res.next()) {
			Usuarios usuarios = new Usuarios();
			usuarios.setId(res.getLong("id"));
			usuarios.setNome(res.getString("nome"));
			usuarios.setTelefones(this.listaTelefones(usuarios.getId()));
			usuarios.setDataNascimento(res.getDate("dataNascimento"));
			
			listaUsuarios.add(usuarios);
		}
		return listaUsuarios;
	}
	
	
	/* Método responsável por imprimir um relatório de usuário html e pdf por data */
	public List<Usuarios> listarRelatorioUsuarioporData(Long usuarioLoggado, String dataInicial, String dataFinal) throws Exception {
		List<Usuarios> listaUsuarios = new ArrayList<Usuarios>();
		String sql = "SELECT * FROM usuario WHERE useadmin is false and usuario_id= " + usuarioLoggado + " and dataNascimento >= ?  and dataNascimento <= ?"; // intervalo de data
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setDate(1, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
		statement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));
		ResultSet res = statement.executeQuery();
		while (res.next()) {
			Usuarios usuarios = new Usuarios();
			usuarios.setId(res.getLong("id"));
			usuarios.setNome(res.getString("nome"));
			usuarios.setTelefones(this.listaTelefones(usuarios.getId()));
			usuarios.setDataNascimento(res.getDate("dataNascimento"));
			
			listaUsuarios.add(usuarios);
		}
		return listaUsuarios;
	}
	
	
	
	
	   /* ####### SQL PARA OS GRAFÍCOS ##########*/
	
		public BeanDtoGraficoSalarioUser montarGraficoMediaSalarioUsuario( Long usuarioLogado)  throws Exception{
			String sql = "select avg(salario) as media_salarial, perfil from usuario where useadmin is false  and usuario_id = ? group by perfil";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1,  usuarioLogado);
			
			List<String> perfils = new ArrayList<String>();
			List<Double> salarios = new ArrayList<Double>();
			
			BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = new BeanDtoGraficoSalarioUser();
			
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				
					Double media_salarial = res.getDouble("media_salarial");
					String perfil = res.getString("perfil") ;
					perfils.add(perfil);
					salarios.add(media_salarial);
				
			}
			beanDtoGraficoSalarioUser.setPerfils(perfils);
			beanDtoGraficoSalarioUser.setSalarios(salarios);
			return beanDtoGraficoSalarioUser;
			
		}
		
		
		public BeanDtoGraficoSalarioUser montarGraficoMediaSalarioUsuarioporData( Long usuarioLogado, String dataInicial, String dataFinal)  throws Exception{
			String sql = "select avg(salario) as media_salarial, perfil from usuario where useadmin is false  and usuario_id = ? ";
				   sql+="and dataNascimento >= ? and dataNascimento <= ? group by perfil";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1,  usuarioLogado);
			statement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
			statement.setDate(3, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));
			
			List<String> perfils = new ArrayList<String>();
			List<Double> salarios = new ArrayList<Double>();
			
			BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = new BeanDtoGraficoSalarioUser();
			
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				
					Double media_salarial = res.getDouble("media_salarial");
					String perfil = res.getString("perfil") ;
					
					perfils.add(perfil);
					salarios.add(media_salarial);
				
			}
			beanDtoGraficoSalarioUser.setPerfils(perfils);
			beanDtoGraficoSalarioUser.setSalarios(salarios);
			return beanDtoGraficoSalarioUser;
			
		}
	
}
