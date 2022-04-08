package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import beandto.BeanDtoGraficoSalarioUser;
import dao.UsuarioDaoRepository;
import model.Usuarios;
import util.ReportUtil;

@MultipartConfig // pepara para o updload
@WebServlet(urlPatterns = { "/ServletUsuarioController" })
public class ServletUsuarioController extends ServletGenerecicUtil {
	private static final long serialVersionUID = 1L;
	private UsuarioDaoRepository usuariDao = new UsuarioDaoRepository();

	public ServletUsuarioController() {
		super();
	}

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			if (!acao.isEmpty() && acao != null && acao.equals("listarusuarios")) {

				List<Usuarios> listaUsuarios = usuariDao.listarUsuarios(super.getUsuarioLogado(request));
				request.setAttribute("msg", "Lista de Usuários carregados");
				request.setAttribute("lista", listaUsuarios);
				request.setAttribute("totalPagina", usuariDao.totalPagina(this.getUsuarioLogado(request)));
				request.getRequestDispatcher("principal/cadastrousuarios.jsp").forward(request, response);

			} else if (!acao.isEmpty() && acao != null && acao.equals("editar")) {
				String id = request.getParameter("id");
				Usuarios usuarios = usuariDao.buscarUsuarioEdicao(id, super.getUsuarioLogado(request));
				request.setAttribute("msg", "Usuário em edição");
				request.setAttribute("user", usuarios);
				List<Usuarios> listaUsuarios = usuariDao.listarUsuarios(super.getUsuarioLogado(request));
				request.setAttribute("lista", listaUsuarios);
				request.setAttribute("totalPagina", usuariDao.totalPagina(this.getUsuarioLogado(request)));
				request.getRequestDispatcher("principal/cadastrousuarios.jsp").forward(request, response);

			} else if (!acao.isEmpty() && acao != null && acao.equals("deletar")) {

				String id = request.getParameter("id");
				usuariDao.Delete(id);
				request.setAttribute("msg", "Usuário excluído com Sucesso!");
				request.setAttribute("totalPagina", usuariDao.totalPagina(this.getUsuarioLogado(request)));
				request.getRequestDispatcher("principal/cadastrousuarios.jsp").forward(request, response);

			} else if (!acao.isEmpty() && acao != null && acao.equals("filtroAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");
				List<Usuarios> dadosJson = usuariDao.filtroPorNome(nomeBusca, super.getUsuarioLogado(request));
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJson);

				response.addHeader("totalPagina",
						"" + usuariDao.paginacaofiltroPorNome(nomeBusca, super.getUsuarioLogado(request)));
				response.getWriter().write(json);

			} else if (!acao.isEmpty() && acao != null && acao.equalsIgnoreCase("filtroAjaxPage")) {

				String nomeBusca = request.getParameter("nomeBusca");
				String pagina = request.getParameter("pagina");
				List<Usuarios> dadosJson = usuariDao.paginacaofiltroPorNomeOffset(nomeBusca,
						super.getUsuarioLogado(request), pagina);
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJson);

				response.addHeader("totalPagina",
						"" + usuariDao.paginacaofiltroPorNome(nomeBusca, super.getUsuarioLogado(request)));
				response.getWriter().write(json);
			}

			else if (!acao.isEmpty() && acao != null && acao.equals("excluirUserAjax")) {

				String id = request.getParameter("id");
				usuariDao.Delete(id);
				response.getWriter().write("Usuário excluído com Sucesso");

			} else if (!acao.isEmpty() && acao != null && acao.equals("downloadFoto")) {

				String idUser = request.getParameter("id");
				Usuarios usuarios = usuariDao.buscarUsuarioEdicao(idUser, super.getUsuarioLogado(request));
				if (usuarios.getFotouser() != null && !usuarios.getFotouser().isEmpty()) {
					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + usuarios.getExtensaofotouser());
					response.getOutputStream().write(new Base64().decodeBase64(usuarios.getFotouser().split("\\,")[1]));
				}
			} else if (!acao.isEmpty() && acao != null && acao.equals("paginar")) {
						
						Integer offset = Integer.parseInt(request.getParameter("pagina"));
		
						List<Usuarios> listausuarios = usuariDao.listarUsuariosporPagina(this.getUsuarioLogado(request),
								offset);
						request.setAttribute("lista", listausuarios);
						request.setAttribute("totalPagina", usuariDao.totalPagina(this.getUsuarioLogado(request)));
						request.getRequestDispatcher("principal/cadastrousuarios.jsp").forward(request, response);
		
			
			} else if (!acao.isEmpty() && acao != null && acao.equalsIgnoreCase("imprimirRelatorioUser")) {
		
						String dataInicial = request.getParameter("datainicial");
						String dataFinal = request.getParameter("datafinal");
						if (dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
							request.setAttribute("lista", usuariDao.listarRelatorioUsuario(super.getUsuarioLogado(request)));
						} else {
							request.setAttribute("lista", usuariDao
									.listarRelatorioUsuarioporData(super.getUsuarioLogado(request), dataInicial, dataFinal));
							request.setAttribute("datainicial", dataInicial);
							request.setAttribute("datafinal", dataFinal);
						}
						request.getRequestDispatcher("principal/relatoriousuario.jsp").forward(request, response);

			
			} else if (!acao.isEmpty() && acao != null && acao.equalsIgnoreCase("imprimirRelatorioPDF")) {

						String dataInicial = request.getParameter("datainicial");
						String dataFinal = request.getParameter("datafinal");
		
						List<Usuarios> listaUsuarios = null;
						if (dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
							listaUsuarios = usuariDao.listarRelatorioUsuario(super.getUsuarioLogado(request));
						} else {
		
							listaUsuarios = usuariDao.listarRelatorioUsuarioporData(super.getUsuarioLogado(request),
									dataInicial, dataFinal);
						}
		
						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put("PARAM_SUB_REPORT", request.getServletContext().getRealPath("relatorio") + File.separator);
						byte[] relatorio = new ReportUtil().geraRelatorioPDF(listaUsuarios, "relusuario", params,
								request.getServletContext());
						response.setHeader("Content-Disposition", "attachment;filename=arquivo.pdf");
						response.getOutputStream().write(relatorio);

			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("graficosalariousuario")) {
						
						String dataInicial = request.getParameter("datainicial");
						String dataFinal = request.getParameter("datafinal");
						if (dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
							
							BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = usuariDao.montarGraficoMediaSalarioUsuario(super.getUsuarioLogado(request));
							ObjectMapper mapper = new ObjectMapper();
							String json = mapper.writeValueAsString(beanDtoGraficoSalarioUser);
							
							response.getWriter().write(json);
								
						}else {

							BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = usuariDao.montarGraficoMediaSalarioUsuarioporData(super.getUsuarioLogado(request), dataInicial, dataFinal);
							ObjectMapper mapper = new ObjectMapper();
							String json = mapper.writeValueAsString(beanDtoGraficoSalarioUser);
							
							response.getWriter().write(json);
						}
			}

			else {

				List<Usuarios> listaUsuarios = usuariDao.listarUsuarios(super.getUsuarioLogado(request));
				request.setAttribute("lista", listaUsuarios);
				request.setAttribute("totalPagina", usuariDao.totalPagina(this.getUsuarioLogado(request)));
				request.getRequestDispatcher("principal/cadastrousuarios.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			String cep = request.getParameter("cep");
			String endereco = request.getParameter("endereco");
			String numero = request.getParameter("numero");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String dataNascimento = request.getParameter("dataNascimento");
			String salario = request.getParameter("salario");
			salario = salario.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");

			if (nome != null && !nome.isEmpty() && email != null && !email.isEmpty() && login != null
					&& !login.isEmpty() && senha != null && !senha.isEmpty() && perfil != null && !perfil.isEmpty()
					&& sexo != null && !sexo.isEmpty() && cep != null && !cep.isEmpty() && !endereco.isEmpty()
					&& endereco != null && !numero.isEmpty() && numero != null && !bairro.isEmpty() && bairro != null
					&& !cidade.isEmpty() && cidade != null && !estado.isEmpty() && estado != null) {

				Usuarios usuarios = new Usuarios();
				usuarios.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
				usuarios.setNome(nome);
				usuarios.setEmail(email);
				usuarios.setLogin(login);
				usuarios.setSenha(senha);
				usuarios.setPerfil(perfil);
				usuarios.setSexo(sexo);
				usuarios.setCep(cep);
				usuarios.setEndereco(endereco);
				usuarios.setNumero(numero);
				usuarios.setBairro(bairro);
				usuarios.setCidade(cidade);
				usuarios.setEstado(estado);
				usuarios.setDataNascimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd")
						.format(new SimpleDateFormat("dd/mm/yyy").parse(dataNascimento))));
				usuarios.setSalario(Double.valueOf(salario));
				String msg = "";

				if (ServletFileUpload.isMultipartContent(request)) {
					Part part = request.getPart("filefoto"); /* Pega a foto da tela */

					if (part.getSize() > 0) {

						// IOUtils.toByteArray(part.getInputStream()); /* CONVERTE IMAGEM PARA BTYE */
						byte[] foto = IOUtils.toByteArray(part.getInputStream());

						@SuppressWarnings("static-access")
						String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64,"
								+ new Base64().encodeBase64String(foto);

						/* Gravando a iamgem no banco */
						usuarios.setFotouser(imagemBase64);
						usuarios.setExtensaofotouser(part.getContentType().split("\\/")[1]); // pegando so a primeria
																								// extensao da imgem
					}
				}

				if (usuariDao.verificaUsuario(login) && usuarios.getId() == null) {
					msg = "Esse Usuário já exsite";
				} else if (usuarios.getId() != null && usuarios.getId() > 0) {
					// atualizar
					usuariDao.update(usuarios);
					msg = "Usuário Atualizado com Sucesso";
				} else {
					usuariDao.createUsuario(usuarios, super.getUsuarioLogado(request));
					msg = "Usuário Cadastro com Sucesso";
				}
				List<Usuarios> listaUsuarios = usuariDao.listarUsuarios(super.getUsuarioLogado(request));
				request.setAttribute("lista", listaUsuarios);
				request.setAttribute("msg", msg);
				request.setAttribute("totalPagina", usuariDao.totalPagina(this.getUsuarioLogado(request)));
				request.getRequestDispatcher("principal/cadastrousuarios.jsp").forward(request, response);

			} else {

				request.setAttribute("msg", "Campos vazios");

			}

		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
	}

}
