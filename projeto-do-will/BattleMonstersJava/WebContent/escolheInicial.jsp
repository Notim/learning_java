<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>BattleMonsters</title>
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="css/bootstrap-paper.min.css" />
  <link rel="stylesheet" type="text/css" href="css/common-classes.css" />
  <link rel="stylesheet" type="text/css" href="css/main.css" />
  <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
  <script type="text/javascript" src="js/main.js"></script>
</head>
<body>
  <div class="container" id="content">
    <div id="holder" class="row">
      <div class="col-xs-12 text-center home-logo">
        <img src="img/logo.png" />
      </div>
      
      <form method="post" class="escolhe-inicial" action="/prjBattleMonstersWeb/EscolheInicial">
        <div class="col-xs-8 col-xs-offset-2 mb-20">
        	Seja bem vindo ao mundo de <strong>Battlemon</strong>! Aqui você pode batalhar contra
        	diversas criaturas e se tornar o melhor treinador, com um jeito de viver que ninguém
        	nunca foi igual!
        </div>
        <div class="col-xs-8 col-xs-offset-2 mb-20">
        	Mas primeiro, você precisa escolher quem vai te ajudar a começar essa jornada.
        	Escolha um dos battlemons abaixo.
        </div>
        <div class="col-xs-8 col-xs-offset-2">
          <a href="#Sapograma" class="col-xs-4">
          	<div class="col-xs-10 col-xs-offset-1 each-escolhe-inicial">
	            <div class="image" style="background-image: url('img/monster/enemy/1.png')"></div>
	            <div class="nome">Sapograma</div>
            </div>
          </a>
          <a href="#Lagartochama" class="col-xs-4">
          	<div class="col-xs-10 col-xs-offset-1 each-escolhe-inicial">
	            <div class="image" style="background-image: url('img/monster/enemy/4.png')"></div>
	            <div class="nome">Lagartochama</div>
            </div>
          </a>
          <a href="#Tartaragua" class="col-xs-4">
          	<div class="col-xs-10 col-xs-offset-1 each-escolhe-inicial">
	            <div class="image" style="background-image: url('img/monster/enemy/7.png')"></div>
	            <div class="nome">Tartaragua</div>
            </div>
          </a>
        </div>
        <div class="col-xs-8 col-xs-offset-2 mb-20">
	        <input type="hidden" name="inicialEscolhido" id="inicialEscolhido" />
	        <div class="col-xs-8 col-xs-offset-2">
		        <input type="text" id="nomeEscolhido" name="nomeEscolhido" class="form-control" disabled placeholder="Digite o nome do seu novo Battlemon" />
	        </div>
        </div>
        <div class="col-xs-8 col-xs-offset-2">
        	<button type="submit" id="chooseEscolhido" class="btn btn-default col-xs-4 col-xs-offset-4 mt20 disabled" >Escolher</button>
        </div>
      </form>
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