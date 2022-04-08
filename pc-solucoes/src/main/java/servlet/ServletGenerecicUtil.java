package servlet;

import java.io.Serializable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.UsuarioDaoRepository;
import model.Usuarios;

public class ServletGenerecicUtil  extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UsuarioDaoRepository usuariodaoRepository = new UsuarioDaoRepository();
	
	public Long getUsuarioLogado(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String usuariologado = (String) session.getAttribute("usuario");
		
		return usuariodaoRepository.consultarUsuario(usuariologado).getId();
		
	}
	
	
public Usuarios getUsuarioLogadoObject(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		String usuariologado = (String) session.getAttribute("usuario");
		
		return usuariodaoRepository.consultarUsuario(usuariologado);
		
	}

}
