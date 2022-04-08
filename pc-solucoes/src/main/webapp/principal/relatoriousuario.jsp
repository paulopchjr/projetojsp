
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
											<div class=" col-xl-12 col-md-12">
												<div class="card">
													<div class="card-header">
														<h5>Relatório de Usuários</h5>
													</div>
													<div class="card-block">
														<form
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="GET" class="form-material" id="formRelUser">
															
															<input type="hidden" name="acao" value="imprimirRelatorioUser" id="acaoRelatorioImprimirTipo">
															<div class="row">
																<div class="col-md-4">
																	<div class="form-group form-default form-static-label">
																		<input type="text" name="datainicial" id="datainicial" class="form-control" value="${datainicial}">
																		<span class="form-bar"></span> 
																		<label class="float-label">Data Inicial:</label>
																	</div>
																</div>
																<div class="col-md-4">
																	<div class="form-group form-default form-static-label">
																		<input type="text" name="datafinal" id="datafinal" class="form-control" value="${datafinal}">
																		<span class="form-bar"></span> 
																		<label class="float-label">Data Final:</label>
																	</div>
																
																</div>
																<div class="col-md-2">
																	<button class="btn btn-default btn-round waves-effect waves-light" type="button" onclick="impimirHtml();"
																style="background-color: #513631; color: #fafafa; font-size:16px; font-family:sans-serif; text-decoration: none;">Imprimir Relatório</button>
																</div>
																<div class="col-md-2">
																	<button class="btn btn-default btn-round waves-effect waves-light" type="button" onclick="impimirPDF();"style="background-color: #35444A; color: #fafafa; font-size:16px; font-family:sans-serif; text-decoration: none;">Imprimir PDF</button>
																</div>															
															</div>
														</form>
														
														<div style="height: 300px; overflow: scroll; ">
															 <table class="table table-striped " id="tabelaUsuario">
																<thead class="bg-inverse">
																	<tr>
																		<th scope="row">ID</th>
																		<th scope="row">Nome</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items='${lista}' var='l'>
																		<tr>
																			<td><c:out value="${l.id}"></c:out></td>
																			<td><c:out value="${l.nome}"></c:out></td>
																		</tr>
																		<tr>
																		<td/>
																				<td style="font-size:11px; color:black; font-family: sans-serif; font-weight:bolder; "><c:out value="${l.mostrarTelefonesRelatorio}"></c:out></td>
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
</body>


<script type="text/javascript">

function impimirHtml() {
	document.getElementById('acaoRelatorioImprimirTipo').value = 'imprimirRelatorioUser';
	$('#formRelUser').submit();
	
	
}

function impimirPDF() {
	document.getElementById('acaoRelatorioImprimirTipo').value = 'imprimirRelatorioPDF';
	$('#formRelUser').submit();
	
}





$( function() {
	  $("#datainicial").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
} );

$( function() {
	  
	  $("#datafinal").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
} );



</script>

</html>
