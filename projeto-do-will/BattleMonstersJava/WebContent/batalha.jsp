<%
MonstroDTO MonstroAdv = (MonstroDTO) session.getAttribute("MONSTRO_ADVERSARIO");
MonstroDTO MonstroJogador = (MonstroDTO) session.getAttribute("MONSTRO_JOGADOR");
List<JogadorMonstro> esquadrao = (List<JogadorMonstro>) session.getAttribute("ESQUADRAO");
%>

<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
import="br.com.battlemonsters.dto.MonstroDTO"
import="br.com.battlemonsters.entidades.JogadorMonstro"
import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>BattleMonsters</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap-paper.min.css" />
	<link rel="stylesheet" type="text/css" href="css/common-classes.css" />
	<link rel="stylesheet" type="text/css" href="css/perfect-scrollbar.min.css" />
	<link rel="stylesheet" type="text/css" href="css/main.css" />
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/perfect-scrollbar.jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<div class="container" id="content">
		<div id="holder" class="row">
			<div class="col-xs-3 lateral-menu">
				<div class="col-xs-12 title">Em batalha!</div>
				<div class="clearfix"></div>
				
				<div class="col-xs-12 log-holder"></div>

				<div class="squad-holder" style="display: none;">
					<!-- Para cada monstro no esquadrão -->
					<% for (JogadorMonstro JM : esquadrao) { %>
						<% if (JM.getId()!= MonstroJogador.getIdJogadorMonstro()) { %>
						<div class="col-xs-12 each-monster">
							<div class="col-xs-3 photo">
								<img src="img/monster/icons/<%=JM.getMonstro().getId() %>.png" alt="Icon" />
							</div>
							<div class="col-xs-9 stats">
								<div class="text-center text-uppercase"><strong><%=JM.getMonstro().getNome() %></strong></div>
								<div class="progress">
									<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
										<span class="sr-only">100% Complete (success)</span>
									</div>
								</div>
								<div class="level">Nv.: <%=JM.getLvl() %></div>
								<div class="hp">HP: <%=JM.getHp() %>/<%=JM.getHp() %></div>
							</div>
							<div class="col-xs-12 actions">
								<button class="btn btn-default btn-xs btn-block">Trocar</button>
							</div>
						</div>
						<% } %>
					<% } %>
				</div>

		</div>
		<div class="col-xs-9 battle-screen">
			<div class="col-xs-6 stats enemy-stats">
				<div class="infos">
					<div class="col-xs-9 name"><%=MonstroAdv.getNome()%></div>
					<div class="col-xs-3 level">Nv. <%=MonstroAdv.getLvl()%></div>
					<div class="col-xs-12 hp-bar">
						<div class="progress">
							<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<%=MonstroAdv.getHp()%>" aria-valuemin="0" aria-valuemax="<%=MonstroAdv.getHp()%>" style="width: 100%">
								<span class="sr-only">100% Complete (success)</span>
							</div>
						</div>
					</div>
					<div class="col-xs-12 hp">
						<span class="currentHp"><%=MonstroAdv.getHp()%></span>
						<span>/</span>
						<span class="maxHp"><%=MonstroAdv.getHp()%></span>
					</div>
				</div>
			</div>
			<div class="col-xs-6 enemy">
				<div class="enemy-image" style="background-image: url('img/monster/enemy/<%=MonstroAdv.getId()%>.png')"></div>
			</div>
			<div class="col-xs-6 ally">
				<div class="ally-image" style="background-image: url('img/monster/ally/<%=MonstroJogador.getId() %>.png')"></div>
			</div>
			<div class="col-xs-6 stats ally-stats">
				<div class="infos">
					<div class="col-xs-9 name"><%=MonstroJogador.getNome() %></div>
					<div class="col-xs-3 level">Nv. <%=MonstroJogador.getLvl() %></div>
					<div class="col-xs-12 hp-bar">
						<div class="progress">
							<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
								<span class="sr-only">100% Complete (success)</span>
							</div>
						</div>
					</div>
					<div class="col-xs-12 hp">
						<span class="currentHp"><%=MonstroJogador.getHp()%></span>
						<span>/</span>
						<span class="maxHp"><%=MonstroJogador.getHp()%></span>
					</div>
				</div>
			</div>
			<div class="col-xs-12 actions">
				<div class="col-xs-6 each-button button-1">
					<button class="btn btn-default btn-block" id="attackButton">Atacar</button>
				</div>
				<div class="col-xs-6 each-button button-2">
					<button class="btn btn-default btn-block" id="openBagButton">Itens</button>
				</div>
				<div class="col-xs-6 each-button button-3">
					<button class="btn btn-default btn-block" id="captureButton">Capturar</button>
				</div>
				<div class="col-xs-6 each-button button-4">
					<button class="btn btn-default btn-block" id="runButton">Fugir</button>
				</div>
				<div class="col-xs-6 col-xs-offset-3 each-button button-5">
					<button class="btn btn-default btn-block" id="acceptButton">Voltar</button>
				</div>
			</div>
			<div class="col-xs-12 submit-name">
				<div class="col-xs-6 col-xs-offset-3 each-button">
					<input type="text" class="form-control" id="nameCapturado" placeholder="Digite o nome do seu novo Battlemon" />
				</div>
				<div class="col-xs-6 col-xs-offset-3 each-button">
					<button class="btn btn-default btn-block" id="submitButton">Enviar</button>
				</div>
			</div>
		</div>
	</div>
	<div class="row footer">
		Desenvolvido por
		<a href="mailto:sandovaldanilo.lm@gmail.com" target="_blank">Sandoval Matos</a> e
		<a href="mailto:willsilvacarvalho@gmail.com" target="_blank">Willian Carvalho</a>
		para a Disciplina de Lab. de Engenharia - FATEC ZL
	</div>
</div>
</body>
</html>