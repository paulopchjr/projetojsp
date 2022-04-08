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

													</div>

													<div class="card-block">
														
													
													
													</div>





												</div>
											</div>

											<!--  sale analytics end -->

											<!--  project and team member start -->
											<div class="col-xl-8 col-md-12">
												<div class="card table-card">
													<div class="card-header">
														<h5>Projects</h5>

													</div>
													<div class="card-block">
														<div class="table-responsive">
															<table class="table table-hover">
																<thead>
																	<tr>
																		<th></th>

																	</tr>
																</thead>
																<tbody>

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

</html>
