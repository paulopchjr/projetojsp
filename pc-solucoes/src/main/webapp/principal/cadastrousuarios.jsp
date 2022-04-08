<%@page import="model.Usuarios"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/layouts/head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->
	<jsp:include page="/layouts/theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<jsp:include page="/layouts/navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<jsp:include page="/layouts/navbar-menu.jsp"></jsp:include>
					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="/layouts/page-header.jsp"></jsp:include>
						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<!-- task, page, download counter  end -->
											<!--  sale analytics start -->
											<div class="col-xl-8 col-md-12">
												<div class="card">
													<div class="card-header">
														<h5>Cadastro de Usuários</h5>
														<span id="msg">${msg}</span>
													</div>
													<div class="card-block">
														<form
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="POST" class="form-material" id="formUsuario"
															enctype="multipart/form-data">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${user.id}"> <span class="form-bar"></span>
																<label class="float-label">ID</label>
															</div>
															<div class="form-group form-default input-group mb-4">
																<div class="input-group-prepend">
																	<c:if
																		test="${user.fotouser !='' && user.fotouser != null}">
																		<a
																			href="<%=request.getContextPath()%>/ServletUsuarioController?acao=downloadFoto&id=${user.id}">
																			<img alt="Imagem User" src="${user.fotouser}"
																			width="100px" id="fotoembase64">
																		</a>
																	</c:if>
																	<c:if
																		test="${user.fotouser =='' || user.fotouser == null}">
																		<img alt="Imagem User"
																			src="assets/images/avatar-blank.jpg" width="100px"
																			id="fotoembase64">
																	</c:if>
																</div>
																<input type="file" accept="image/*"
																	onchange="visualizarImg('fotoembase64','filefoto');"
																	class="form-control-file" id="filefoto" name="filefoto"
																	style="margin-top: 15px; margin-left: 5px;">

															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" value="${user.nome}" required>
																<span class="form-bar"></span> <label
																	class="float-label">Nome:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="dataNascimento"
																	id="dataNascimento" class="form-control"
																	value="${user.dataNascimento}" required> <span
																	class="form-bar"></span> <label class="float-label">Data
																	Nascimento:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="salario" id="salario"
																	class="form-control" value="${user.salario}" required>
																<span class="form-bar"></span> <label
																	class="float-label">Sálario:</label>
															</div>



															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	class="form-control" value="${user.email}" required>
																<span class="form-bar"></span> <label
																	class="float-label">Email:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<label class="col-sm-2 col-form-label">Perfil:</label>
																<div class="col-sm-10">
																	<select name="perfil" id="p" class="form-control">
																		<option disabled="disabled">[Selecione o
																			Perfil]</option>
																		<option value="ADMIN"
																			<%Usuarios usuarios = (Usuarios) request.getAttribute("user");
if (usuarios != null && usuarios.getPerfil().equalsIgnoreCase("ADMIN")) {
	out.print("");
	out.print("selected=\"selected\"");
	out.print("");
}%>>ADMIN</option>

																		<option value="GERENTE"
																			<%usuarios = (Usuarios) request.getAttribute("user");
if (usuarios != null && usuarios.getPerfil().equalsIgnoreCase("GERENTE")) {
	out.print("");
	out.print("selected=\"selected\"");
	out.print("");
}%>>GERENTE</option>
																		<option value="DESENVOLVEDOR"
																			<%usuarios = (Usuarios) request.getAttribute("user");
if (usuarios != null && usuarios.getPerfil().equalsIgnoreCase("DESENVOLVEDOR")) {
	out.print("");
	out.print("selected=\"selected\"");
	out.print("");
}%>>DESENVOLVEDOR</option>
																		<option value="ESTAGIARIO"
																			<%usuarios = (Usuarios) request.getAttribute("user");
if (usuarios != null && usuarios.getPerfil().equalsIgnoreCase("ESTAGIARIO")) {
	out.print("");
	out.print("selected=\"selected\"");
	out.print("");
}%>>ESTAGIÁRIO</option>
																	</select>
																</div>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="cep" id="cep"
																	class="form-control" value="${user.cep}"
																	onblur="pesquisaCep();" required> <span
																	class="form-bar"></span> <label class="float-label">CEP:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="endereco" id="endereco"
																	class="form-control" value="${user.endereco}">
																<span class="form-bar"></span> <label
																	class="float-label">Endereço:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="numero" id="numero"
																	class="form-control" value="${user.numero}"> <span
																	class="form-bar"></span> <label class="float-label">Número:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="bairro" id="bairro"
																	class="form-control" value="${user.bairro}"> <span
																	class="form-bar"></span> <label class="float-label">Bairro:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="cidade" id="cidade"
																	class="form-control" value="${user.cidade}"> <span
																	class="form-bar"></span> <label class="float-label">Cidade:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="estado" id="estado"
																	class="form-control" value="${user.estado}"> <span
																	class="form-bar"></span> <label class="float-label">Estado:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login"
																	class="form-control" value="${user.login}"> <span
																	class="form-bar"></span> <label class="float-label">Login:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha"
																	class="form-control" value="${user.senha}"> <span
																	class="form-bar"></span> <label class="float-label">Senha:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<div class="form-check form-check-inline">
																	<input type="radio" name="sexo" checked="checked"
																		value="MASCULINO"
																		<%usuarios = (Usuarios) request.getAttribute("user");
if (usuarios != null && usuarios.getSexo().equalsIgnoreCase("MASCULINO")) {
	out.print("");
	out.print("checked=\"checked\"");
	out.print("");
}%>>Masculino</>
																	<input type="radio" name="sexo" value="FEMININO"
																		<%usuarios = (Usuarios) request.getAttribute("user");
if (usuarios != null && usuarios.getSexo().equalsIgnoreCase("FEMININO")) {
	out.print("");
	out.print("checked=\"checked\"");
	out.print("");
}%>>Feminino</>
																	<span class="form-bar"> </span><label
																		class="float-label">Sexo:</label>
																</div>
															</div>
															<button type="button"
																class="btn btn-default btn-round waves-effect waves-light"
																style="background-color: #35444A; color: #fafafa;"
																onclick="limparForm();">Novo</button>
															<button type="submit"
																class="btn btn-success btn-round waves-effect waves-light">Salvar</button>
															<button type="button"
																class="btn btn-info btn-round waves-effect waves-light"
																data-toggle="modal" data-target="#exampleModal">
																Pesquisar</button>
															<button type="button"
																class="btn btn-default btn-round waves-effect waves-light"
																style="background-color: #E40505; color: #fafafa"
																onclick="excluirAjax();">Excluir</button>

															<c:if
																test="${user.id !=null && user.id != '' && user.id > 0}">
																<a
																	href="<%=request.getContextPath()%>/ServletTelefone?iduser=${user.id}"
																	class="btn btn-primary btn-round waves-effect waves-light">Telefone</a>
															</c:if>
														</form>
													</div>
												</div>
											</div>
											<!--  sale analytics end -->
											<!--  project and team member start -->

											<div class="col-xl-8 col-md-12">
												<div class="card table-card">
													<div class="card-header">
														<h5>Tabela de Usuários</h5>
													</div>
													<div style="height: 300px; overflow: scroll;">
														<table class="table table-striped " id="tabelaUsuario">
															<thead
																style="background-color: black; color: #fff; text-decoration: none;">
																<tr>
																	<th scope="col">ID</th>
																	<th scope="col">Nome</th>
																	<th scope="col">Email</th>
																	<th scope="col">Login</th>
																	<th scope="col">Ações</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items='${lista}' var='l'>
																	<tr>
																		<td><c:out value="${l.id}"></c:out></td>
																		<td><c:out value="${l.nome}"></c:out></td>
																		<td><c:out value="${l.email}"></c:out></td>
																		<td><c:out value="${l.login}"></c:out></td>
																		<td><a class="btn btn-primary"
																			href="<%=request.getContextPath()%>/ServletUsuarioController?acao=editar&id=${l.id}">
																				Editar</a> <a class="btn btn-danger"
																			href="<%=request.getContextPath()%>/ServletUsuarioController?acao=deletar&id=${l.id}"
																			onclick="deletar();"> Excluir</a></td>

																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
													<nav aria-label="Page navigation example">
														<ul class="pagination justify-content-center">

															<%
															int totalPagina = (int) request.getAttribute("totalPagina");

															for (int pos = 0; pos < totalPagina; pos++) {
																String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&pagina=" + (pos * 5);
																out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"" + url + "\">" + (pos + 1) + "</a></li>");
															}
															%>

														</ul>
													</nav>
												</div>

												<!-- final 	<div class="col-xl-8 col-md-12"> -->
											</div>


											<!--  project and team member end -->
										</div>
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- MODAL -->

	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Filtro Pesquisa
						Usuário</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<div class="input-group mb-3">
							<input type="text" class="form-control" id="nomeBusca"
								placeholder="Pesquisar por nome"
								aria-label="Recipient's username"
								aria-describedby="button-addon2">
							<div class="input-group-append">
								<button class="btn btn-outline-secondary" type="button"
									id="button-addon2" onclick="filtrar();">Buscar</button>
							</div>
						</div>
					</div>
					<div style="height: 300px; overflow: scroll;">
						<table class="table" id="tabelamodal">
							<thead>
								<tr>
									<th>ID</th>
									<th>Nome</th>
									<th>Ver</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>

					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center"
							id="ulPaginacaoUserAjax">
						</ul>
					</nav>
					<span id="totalUsers"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="/js/rodape_js.jsp"></jsp:include>


</body>


<script type="text/javascript">
	/* Usando a mascara de moeda do jquery*/
	$("#salario").maskMoney({
		showSymbol : true,
		symbol : "R$ ",
		decimal : ",",
		thousands : "."
	});

	/*Criando um tipo de formatação de moedas*/
	const formatter = new Intl.NumberFormat('pt-BR', {
		currency : 'BRL',
		minimumFractionDigits : 2
	});

	/* pegando o valor do salário que esta sendo inserido na tela, e formata valor real
	 * depois imprime na tela o valor formatado
	 */
	$("#salario").val(formatter.format($("#salario").val()));
	$("#salario").focus();

	var dataNascimento = $("#dataNascimento").val();

	if (dataNascimento != null && dataNascimento != '') {
		var dateFormat = new Date(dataNascimento);
		$("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR', {
			timeZone : 'UTC'
		}));
	}
	$("#nome").focus();

	$(function() {

		$("#dataNascimento").datepicker(
				{
					dateFormat : 'dd/mm/yy',
					dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta',
							'Quinta', 'Sexta', 'Sábado' ],
					dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D' ],
					dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex',
							'Sáb', 'Dom' ],
					monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril',
							'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro',
							'Outubro', 'Novembro', 'Dezembro' ],
					monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai',
							'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez' ],
					nextText : 'Próximo',
					prevText : 'Anterior'
				});
	});

	$("#numero").keypress(function(event) {
		return /\d/.test(String.fromCharCode(event.keyCode));
	});

	$("#cep").keypress(function(event) {
		return /\d/.test(String.fromCharCode(event.keyCode));
	});

	function pesquisaCep() {

		// Somente números na nova variável do cep
		var cep = $("#cep").val().replace(/\D/g, '');

		//verifica se o campo possui o valor informado
		if (cep != "") {

			//exmpressao regular para validar o cep.
			var validacep = /^[0-9]{8}$/;

			// valida o formato do cep.
			if (validacep.test(cep)) {
				// preenche os campos "..." enquanto consulta o webservice

				$("#endereco").val("...");
				$("#bairro").val("...");
				$("#bairro").val("...");
				$("#cidade").val("...");
				$("#estado").val("...");
				//Consulta o webservice viacep.com.br/
				$.getJSON("https://viacep.com.br/ws/" + cep
						+ "/json/?callback=?", function(dados) {

					if (!("erro" in dados)) {
						//Atualiza os campos com os valores da consulta.
						$("#endereco").val(dados.logradouro);
						$("#bairro").val(dados.bairro);
						$("#cidade").val(dados.localidade);
						$("#estado").val(dados.uf);
					} //end if.
					else {
						//CEP pesquisado não foi encontrado.
						limpa_formulário_cep();
						alert("CEP não encontrado.");
					}
				});
			}
			// endif
		} else {
			// cep invalido
			limpa_formulario_cep();
			alert("Formato de invalido");
		}
	}

	function limpa_formulario_cep() {
		$("#endereco").val("");
		$("#numero").val("");
		$("#bairro").val("");
		$("#cidade").val("");
		$("#estado").val("");
	}

	function visualizarImg(fotoembase64, filefoto) {

		var preview = document.getElementById(fotoembase64); // campo img html
		var fileUsuario = document.getElementById(filefoto).files[0];
		var reader = new FileReader();

		reader.onloadend = function() {
			preview.src = reader.result; /*Carrega a foto na tela*/

		};

		if (fileUsuario) {
			reader.readAsDataURL(fileUsuario); /* PREVIEW DA IMAGE*/
		} else {
			preview.src = '';
		}

	}

	function ver(id) {

		var UrlAction = document.getElementById('formUsuario').action;

		window.location.href = urlAction = '?acao=editar&id=' + id;
	}

	function excluirAjax() {

		if (confirm("Deseja realmente excluir?")) {
			var urlAction = document.getElementById('formUsuario').action;
			var id = document.getElementById('id').value;

			$.ajax({
				method : 'get',
				url : urlAction,
				data : 'id=' + id + '&acao=excluirUserAjax',
				success : function(response) {

					limparForm();
					document.getElementById('msg').textContent = response;

				}

			}).fail(function(xhr, status, errorThrown) {
				alert("Erro ao excluir o usuario : " + xhr.responseText);
			});
		}

	}

	function limparForm() {
		var campos = document.getElementById('formUsuario').elements;

		for (var c = 0; c < campos.length; c++) {
			campos[c].value = '';
		}

	}

	function filtrar() {
		var nomeBusca = document.getElementById('nomeBusca').value;
		if (nomeBusca != null && nomeBusca != '' && nomeBusca) {
			var urlAction = document.getElementById('formUsuario').action;

			$
					.ajax(
							{
								method : 'get',
								url : urlAction,
								data : 'nomeBusca=' + nomeBusca
										+ '&acao=filtroAjax',
								success : function(response, textStatus, xhr) {
									var json = JSON.parse(response); // convetendo a resposta em um objeto no json

									// caso houver alguma consulta, remove toda as linha da tabelamodal
									$('#tabelamodal >tbody >tr').remove();
									$("#ulPaginacaoUserAjax > li ").remove();

									for (var pos = 0; pos < json.length; pos++) {

										$('#tabelamodal > tbody')
												.append(
														'<tr><td>'
																+ json[pos].id
																+ '</td><td>'
																+ json[pos].nome
																+ '</td>'
																+ '<td><button type="button" class="btn btn-dark" onclick=ver('
																+ json[pos].id
																+ ');>Ver</button></td>'
																+ '</tr>');
									}
									document.getElementById('totalUsers').textContent = 'Resultados:'
											+ json.length;

									/*pegando o total de pagina*/
									var totalPagina = xhr
											.getResponseHeader("totalPagina");

									//Motando a paginação no ajax

									for (var pag = 0; pag < totalPagina; pag++) {

										var url = 'nomeBusca='
												+ nomeBusca
												+ '&acao=filtroAjaxPage&pagina='
												+ (pag * 5);

										$("#ulPaginacaoUserAjax ")
												.append(
														'<li class="page-item"><a class="page-link" href="#" onclick="buscarUsuarioPagAjax(\''
																+ url
																+ '\')">'
																+ (pag + 1)
																+ '</a></li>');

									}

								}

							}).fail(function(xhr, status, errorThrown) {
						alert("erro" + xhr.responseText);
					});

		}

	}

	function buscarUsuarioPagAjax(url) {

		alert(url);
		var nomeBusca = document.getElementById('nomeBusca').value;
		var urlAction = document.getElementById('formUsuario').action;

		$
				.ajax(
						{
							method : 'get',
							url : urlAction,
							data : url,
							success : function(response, textStatus, xhr) {
								var json = JSON.parse(response); // convetendo a resposta em um objeto no json

								// caso houver alguma consulta, remove toda as linha da tabelamodal
								$('#tabelamodal >tbody >tr').remove();
								$("#ulPaginacaoUserAjax > li").remove();

								for (var pos = 0; pos < json.length; pos++) {

									$('#tabelamodal > tbody')
											.append(
													'<tr><td>'
															+ json[pos].id
															+ '</td><td>'
															+ json[pos].nome
															+ '</td>'
															+ '<td><button type="button" class="btn btn-dark" onclick=ver('
															+ json[pos].id
															+ ');>Ver</button></td>'
															+ '</tr>');
								}
								document.getElementById('totalUsers').textContent = 'Resultados:'
										+ json.length;

								/*pegando o total de pagina*/
								var totalPagina = xhr
										.getResponseHeader("totalPagina");

								//Motando a paginação no ajax

								for (var pag = 0; pag < totalPagina; pag++) {

									var url = 'nomeBusca=' + nomeBusca
											+ '&acao=filtroAjaxPage&pagina='
											+ (pag * 5);

									// <li class="page-item"><a class="page-link" href="#">1</a></li>
									$("#ulPaginacaoUserAjax ")
											.append(
													'<li class="page-item"><a class="page-link" href="#" onclick="buscarUsuarioPagAjax(\''
															+ url
															+ '\')">'
															+ (pag + 1)
															+ '</a></li>');

								}

							}

						}).fail(function(xhr, status, errorThrown) {
					alert("erro" + xhr.responseText);
				});

	}

	function deletar() {
		if (confirm('Deseja realmente excluir os dados?')) {
			document.getElementById('formUsuario').method = 'get';
			document.getElementById('acao').value = 'deletar';
			document.getElementById('tabelaUsuario').submit();
		}
	}
</script>

</html>
