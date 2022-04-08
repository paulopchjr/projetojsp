package filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.Conexao;
import dao.DaoVersionadorBanco;



@WebFilter(urlPatterns = { "/principal/*" }) /* Intercepta todas as requições que vier do projeto o mapeamento */
public class FilterAutenticacao implements Filter {

	private static Connection connection;
	

	public FilterAutenticacao() {
	}

	/* encerra os processo quando o servidor é parado */
	// mataria os processo com conexao com banco
	public void destroy() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* Intercepta as requisições e da resposta no sistema */
	// tudo que fizer no sistema passa por aqui
	// validação de autentição
	// commit e rollback no banco
	// validar e fazer redirecionamento de paginas
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpServletRequest req = (HttpServletRequest) request; // pegando a requisicao que ver por paramentro
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario"); // pegando o usuario logado

			String urlParaAutenticar = req.getServletPath(); // url que esta sendo acessada

			/* validar se está logado, se não direnciona para a tela de login */

			if (usuarioLogado == null
					&& !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) { /* Usuario não está logado */

				/*
				 * faz a parada da execução o redirecionamento para o login, passando o
				 * paramentro da url e manda a mensagem para o usuario
				 */
				request.setAttribute("msg", "Por favor realize o login");
				request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar).forward(req, response);

				return; /* Para a execução e redireciona para o login */

			} else {
				// Continua o sistema
				chain.doFilter(request, response);
			}
			connection.commit();
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}

		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* inicia os processos ou recursos quando o servidor sobe o projeto */
	// iniciar a conexao com banco
	public void init(FilterConfig fConfig) throws ServletException {
		
			connection = Conexao.getConnection();
			DaoVersionadorBanco daoVersionadorBanco = new DaoVersionadorBanco();

			String caminho_pasta_sql = fConfig.getServletContext().getRealPath("versionadorbancosql") + File.separator;
			File[] filesSql = new File(caminho_pasta_sql).listFiles();
		
		try {
			


			for (File file : filesSql) {

				boolean arquivoJaRodado = daoVersionadorBanco.arquivoSqlRodado(file.getName());

				if (!arquivoJaRodado) { // se o arquivo for falso, vou rodar ele

					// ler o conteudo do arquivo

					FileInputStream entradaArquivo = new FileInputStream(file);

					Scanner lerArquivo = new Scanner(entradaArquivo, "UTF-8");

					// armazenando o sql
					StringBuilder sql = new StringBuilder();
					while (lerArquivo.hasNext()) {
						sql.append(lerArquivo.nextLine());
						sql.append("\n");

					}

					/* Executa o sql */
					connection.prepareStatement(sql.toString()).execute();
					daoVersionadorBanco.gravarArquivoSqlRodado(file.getName());
					connection.commit();
					lerArquivo.close();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
