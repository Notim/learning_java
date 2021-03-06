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
      <div class="col-xs-offset-4 col-xs-4 home-login">
      	<%
			String erro = (String) request.getAttribute("MENSAGEM");
			if (erro != null){
		%>
			<div class="alert alert-warning"><%=erro%></div>
		<% 
			}
		%>

        <a href="/prjBattleMonstersWeb/Batalha" type="button" class="btn btn-default col-xs-12">Batalhar</a>
		<div class="clearfix"></div><br />
        <a href="/prjBattleMonstersWeb/MeusBattlemons" type="button" class="btn btn-default col-xs-12">Meus BattleMons</a>
        <div class="clearfix"></div><br />
        <form action="/prjBattleMonstersWeb/Logout" method="get">
        	<button type="submit" class="btn btn-default col-xs-12">Sair</button>
        </form>
        <div class="clearfix"></div><br />
        
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