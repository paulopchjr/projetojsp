<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<title>Área de Login</title>

<style type="text/css">
@charset "ISO-8859-1";

* {
	padding: 0;
	margin: 0;
	box-sizing: border-box;
	/*O tamnho das caixas são todas iguais, inputs etc*/
	font-family: Helveetica; /* Padrão de todas as fontes*/
	color: #323232;
	border: none;
}

html, body {
	background: #363636;
}

/*Tirando todos os focus dos inputs*/
input:focus {
	outline: none;
}

a {
	font-size: .8rem;
} /*16 .8 = valor pixel*/

/*passa com mouse*/
a:hover {
	color: #08558B;
}

/*login-container*/
#login-container {
	background: #fff;
	width: 400px;
	margin-right: auto;
	margin-left: auto;
	padding: 20px 30px;
	margin-top: 10vh; /* Mesma coisa que porcentagel*/
	border-radius: 10px;
	text-align: center;
}

form {
	margin-top: 30px;
	margin-bottom: 30px;
}

/*Separando os labels e inputs*/
label, input {
	display: block;
	width: 100%;
	text-align: left;
}

label {
	font-weight: bold;
	font-size: .8rem;
}

input {
	border-bottom: 2px solid #323232;
	padding: 10px;
	font-size: 1rem;
}

input:focus {
	border-bottom: 2px solid #08558B
}

#forgot-pass {
	text-align: right;
	display: block;
}

#register {
	text-align: left;
	display: block;
}

/*Submit*/
input[type="submit"] {
	text-align: center;
	text-transform: uppercase;
	font-weight: bold;
	border: none;
	height: 40px;
	border-radius: 20px;
	margin-top: 30px;
	color: #fff;
	background: #08558B;
	cursor: pointer;
}

input[type="submit"]:hover {
	background: #1B223C;
	transition: .5s;
}

</style>
</head>
<body>





	<div id="login-container">

		<h4>Área de Login</h4>
		<form  action="<%=request.getContextPath() %>/ServletLogin" method="POST" onsubmit="validarCampos() ? true : false">
		<input type="hidden" value="<%=request.getParameter("url")%>" name="url">
			<label for="login">Login</label> 
			<input	type="text" name="login" id="login" placeholder="Informe o Login"	autocapitalize="off"> 
				<label for="senha">Senha</label> 
			<input type="password" name="senha" id="senha" placeholder="Informe a senha"> 
			<input type="submit"  value="Logar"> 
			
			<br><br>
			<span>${msg}</span>
		</form>
	</div>









	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

	<script type="text/javascript">
		function validarCampos(){
			var login = $('#login').val();
			var senha = $('#senha').val();
			
			if(login == null || login.trim() == ''){
				alert("Informe o Login");
				return false;
			}else if(senha ==null || senha.trim()== ''){
				alert("Informa a senha");
				return false;
			}
			
			
			
			
		}
	
	
	</script>

</body>
</html>