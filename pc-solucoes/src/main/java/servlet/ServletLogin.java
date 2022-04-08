package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoLoginRepository;
import dao.UsuarioDaoRepository;
import model.Usuarios;

@WebServlet(urlPatterns = { "/principal/ServletLogin", "/ServletLogin" })
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoLoginRepository daoLoginRepository = new DaoLoginRepository();
	private UsuarioDaoRepository daoUsuarioDaoRepository = new UsuarioDaoRepository();

	public ServletLogin() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate(); // invalida a sessao
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			doPost(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String url = request.getParameter("url");

			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {

				Usuarios usuarios = new Usuarios();
				usuarios.setLogin(login);
				usuarios.setSenha(senha);

				if (daoLoginRepository.AutenticaLogineSenhaUsuario(usuarios)) {

					usuarios = daoUsuarioDaoRepository.consultarUsuario(login);
					
					request.getSession().setAttribute("usuario", usuarios.getLogin());
					request.getSession().setAttribute("perfil", usuarios.getPerfil());
					request.getSession().setAttribute("imgUser", usuarios.getFotouser()); // pegando a foto do usuario loagado

					/* Validando a url que está sendo passada, após o usuário estár logado */
					if (url == null || url.equals("null")) {

						url = "principal/home.jsp";
					}

					/* faznedo o direcionamento da url */
					request.getRequestDispatcher(url).forward(request, response);

				} else {
					request.setAttribute("msg", "Login e/ou senha Inválidos!");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}

			} else {
				request.setAttribute("msg", "Campos vazios!");
				request.getRequestDispatcher("/index.jsp").forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
