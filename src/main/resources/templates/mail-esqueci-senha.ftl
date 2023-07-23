<!DOCTYPE html>
<html lang="pt-br">

<head>
	<title th:remove="all">Template Padrão</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<style type="text/css">
		p {
			color: #4F5E69 !important;
			font-size: 14px;
		}

		img {
			height: 30px !important;
			padding: 20px;
			margin: auto;
		}

		.mensagem-duvida {
			font-size: 13px;
			text-align: center;
		}

		.div-mensagem-duvida {
			width: 350px;
			margin: auto;
			color: #4F5E69;
		}

		a:hover {
			text-decoration: none;
			color: #fff;
			opacity: 0.7;
		}

		.body {
			color: white;
			width: 100% !important;
			align-items: center !important;
			margin: 0;
			background-color: #F7F7F7 !important;
			font-family: Tahoma, sans-serif;
		}

		h2 {
			color: #11C76F;
		}

		.div-externa {
			margin: auto;
			min-width: 580px;
		}

		.div-interna {
			justify-items: center !important;
			background-color: white;
			width: 580px;
			border: 1px solid #7777774a;
			border-radius: 2px;
			text-align: center;
		}

		.container {
			width: 640px;
			margin: auto;
		}

		.centraliza-texto {
			text-align: center;
			width: 100%;
			display: grid;
		}

		button {
			width: 280px;
			height: 41px;
			font-size: 16px;
			color: #fff !important;
			background-color: #11C76F;
			border: 0;
			border-radius: 0.2rem;
			margin: auto;
			line-height: 41px;
			text-decoration: none;
			cursor: pointer;
		}

		tbody {
			width: 640px;
			border: 1px solid #7777774a;
			border-radius: 2px;
			padding-top: 20px;
			padding-bottom: 20px;
		}

		table {
			width: 640px;
		}
	</style>
</head>

<body>
	<div class="body">
		<div class="container">
			<table>
				<thead class="div-externa">
					<tr>
						<td>
							<div class="centraliza-texto">
								<img src="cid:logo">
							</div>
						</td>
					</tr>
				</thead>
				<tbody class="centraliza-texto div-interna">
					<tr>
						<td class="centraliza-texto">
							<h2>
								Olá, <span th:text="${USUARIO}"></span>!
							</h2>
						</td>
					</tr>
					<tr>
						<td class="centraliza-texto">
							<p>
								Você realizou uma solicitação de nova senha.
							</p>
							<p>
								Sua nova senha é:
								<br> <strong><span th:text="${NOVA_SENHA}"> </span></strong>
							</p>

							<p>Para alterar sua senha,
								<br> Basta clicar no botão abaixo
							</p>
						</td>
					</tr>
					<tr>
						<td class="centraliza-texto">
							<a th:href=${URL}>
								<button type="button">Alterar senha</button>
							</a>
						</td>
					</tr>
					<tr>
						<td class="centraliza-texto">
							<p><i>Caso você não tenha realizado o procedimento, por favor, responda esse e-mail.</i></p>
						</td>
					</tr>
				</tbody>
				<tfoot class="div-externa">
					<tr>
						<td class="centraliza-texto">
							<div class="div-mensagem-duvida">
								<p class="mensagem-duvida">Em caso de qualquer dúvida, fique a vontade para responder esse
									email ou pode nos contatar no email <span th:text="${EMAIL_APLICACAO}"></p>
								<p class="centraliza-texto"><strong>DoctorPlus</strong></p>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>

</html>