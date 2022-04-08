
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
														<h5>Cadastro de Telefones</h5>
														<span id="mensagem">${mensagem}</span>
													</div>
													<div class="card-block">
														<form
															action="<%=request.getContextPath()%>/ServletTelefone"
															method="POST" id="formTelefone" class="form-material">


															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${us.id}"> <span class="form-bar"></span>
																<label class="float-label">ID</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" value="${us.nome}" readonly>
																<span class="form-bar"></span> <label
																	class="float-label">Nome:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="numerotelefone"
																	id="numerotelefone" class="form-control" value="">
																<span class="form-bar"></span> <label
																	class="float-label">Número:</label>
															</div>

															<button type="submit"
																class="btn btn-success btn-round waves-effect waves-light">Salvar</button>

														</form>

													</div>
												</div>
											</div>

											<!--  sale analytics end -->

											<!--  project and team member start -->
											<div class="col-xl-8 col-md-12">
												<div class="card table-card">
													<div class="card-header">
														<h5>Telefones</h5>

													</div>
													<div class="card-block">
														<div style="height: 300px; overflow: scroll;">
															<table class="table table-hover">
																<thead>
																	<tr>
																		<th>ID</th>
																		<th>Número</th>
																		<th>Ações</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${telefones}" var='fone'>
																		<tr>
																			<td><c:out value="${fone.id}"></c:out></td>
																			<td><c:out value="${fone.numero}"></c:out></td>
																			<td><a class="btn btn-danger"
																				href="<%=request.getContextPath()%>/ServletTelefone?acao=deletar&id=${fone.id}&usuario_id=${us.id}"
																				onclick="deletar();">Excluir</a></td>
																		</tr>
																	</c:forEach>

																</tbody>
															</table>
														</div>
													</div>
												</div>
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

	<jsp:include page="/js/rodape_js.jsp"></jsp:include>
	
	
	<script type="text/javascript">
		
	$("#numerotelefone").keypress(function(event){
		return /\d/.test(String.fromCharCode(event.keyCode));
	});
	
	
	
	
	
	
	
	
	</script>
</body>

</html>
