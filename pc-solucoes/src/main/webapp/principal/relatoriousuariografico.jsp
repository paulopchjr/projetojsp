
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>



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
															method="GET" class="form-material" id="formRelGraficoUser">
															
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
																	<button class="btn btn-default btn-round waves-effect waves-light" type="button" onclick="gerarGrafico();"
																style="background-color: #513631; color: #fafafa; font-size:16px; font-family:sans-serif; text-decoration: none;">Gerar Gráfico</button>
																</div>
																<div class="col-md-2">
																	<button type="button" class="btn btn-default btn-round waves-effect waves-light"
																	style="background-color: #35444A; color: #fafafa;"onclick="limparForm();">Novo</button>
																
																</div>
																														
															</div>
														</form>
														
														<div style="height: 500px; overflow: scroll; ">
															 <canvas id="myChart"></canvas>
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

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script type="text/javascript">

var myChart = new Chart(document.getElementById('myChart'));



function gerarGrafico(){

	var urlAction = document.getElementById('formRelGraficoUser').action; 
	var datainicial = document.getElementById('datainicial').value;
	var datafinal = document.getElementById('datafinal').value;
	$.ajax({
		
		method:'get',
		url: urlAction,
		data: 'datainicial='+ datainicial + '&datafinal=' + datafinal +'&acao=graficosalariousuario',
		success: function(response){
			
			var json = JSON.parse(response);
			
			myChart.destroy(); // limpa o gráfico
			 myChart = new Chart(
				    document.getElementById('myChart'),
				    {
						  type: 'line',
						  data: {
							  labels: json.perfils,
							  datasets: [{
							    label: 'Gráfico de média salarial por usuário',
							    backgroundColor: 'rgb(255, 99, 132)',
							    borderColor: 'rgb(255, 99, 132)',
							    data: json.salarios,
							  }]
							},
						  options: {}
						}		    
				    
				  );
			
		}
		
	}).fail(function(xhr, status, errorThrown){
		alert("Erro" + xhr.responseText);
	})
	
	
	
	
	
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


function limparForm() {
	var campos = document.getElementById('formRelGraficoUser').elements;

	for (var c = 0; c < campos.length; c++) {
		campos[c].value = '';
	}

}



</script>

</html>
