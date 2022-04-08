package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoTelefoneRepository;
import dao.UsuarioDaoRepository;
import model.Telefone;
import model.Usuarios;

@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenerecicUtil {
	private static final long serialVersionUID = 1L;
	private UsuarioDaoRepository usuarioDaoRepository = new UsuarioDaoRepository();
	private DaoTelefoneRepository daoTelefoneRepository = new DaoTelefoneRepository();

	public ServletTelefone() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				String idfone = request.getParameter("id");
				String usuario_id = request.getParameter("usuario_id");
				daoTelefoneRepository.deleteFone(Long.parseLong(idfone));

				Usuarios usuarios = usuarioDaoRepository.buscarUsuarioEdicao(usuario_id,
						super.getUsuarioLogado(request));
				List<Telefone> listaTelefones = daoTelefoneRepository.listaTelefones(usuarios.getId());
				request.setAttribute("telefones", listaTelefones);
				request.setAttribute("mensagem", "Telefone excluído com Sucesso");
				request.setAttribute("us", usuarios);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

				return;
			}

			String iduser = request.getParameter("iduser");

			if (iduser != null && !iduser.isEmpty()) {

				Usuarios usuarios = usuarioDaoRepository.buscarUsuarioEdicao(iduser, super.getUsuarioLogado(request));

				List<Telefone> listaTelefones = daoTelefoneRepository.listaTelefones(usuarios.getId());

				request.setAttribute("telefones", listaTelefones);
				request.setAttribute("us", usuarios);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			} else {
				List<Usuarios> listaUsuarios = usuarioDaoRepository.listarUsuarios(super.getUsuarioLogado(request));
				request.setAttribute("lista", listaUsuarios);
				request.setAttribute("totalPagina", usuarioDaoRepository.totalPagina(this.getUsuarioLogado(request)));
				request.getRequestDispatcher("principal/cadastrousuarios.jsp").forward(request, response);

			}

		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String usuario_id = request.getParameter("id");
			String numerofone = request.getParameter("numerotelefone");

			if (!daoTelefoneRepository.existeTelefone(numerofone, Long.parseLong(usuario_id))) {

				Telefone telefone = new Telefone();
				telefone.setNumero(numerofone);
				telefone.setUsuario_id(
						usuarioDaoRepository.buscarUsuarioEdicao(usuario_id, super.getUsuarioLogado(request)));
				telefone.setUsuario_cad_id(super.getUsuarioLogadoObject(request)); // usuario que cadastrou

				daoTelefoneRepository.createFone(telefone);
				request.setAttribute("mensagem", "Telefone cadastro com Sucesso");

			} else {
				request.setAttribute("mensagem", "Telefone já existe");
			}

			Usuarios usuarios = usuarioDaoRepository.buscarUsuarioEdicao(usuario_id, super.getUsuarioLogado(request));
			List<Telefone> listaTelefones = daoTelefoneRepository.listaTelefones(usuarios.getId());
			request.setAttribute("telefones", listaTelefones);
			request.setAttribute("us", usuarios);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}

	}

}
