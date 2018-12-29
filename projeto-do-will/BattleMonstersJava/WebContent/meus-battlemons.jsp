<%
	List<JogadorMonstro> listaMonstros = (List<JogadorMonstro>) request.getAttribute("LISTA_MONSTROS");
	List<JogadorMonstro> listaMonstrosReservas = (List<JogadorMonstro>) request.getAttribute("LISTA_MONSTROS_RESERVAS");
%>

<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="br.com.battlemonsters.entidades.JogadorMonstro"
    import="java.util.List"%>
    
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
      <div class="col-xs-12 lateral-menu">
      	<%
			String erro = (String) request.getAttribute("MENSAGEM");
			if (erro != null){
		%>
			<div class="alert alert-warning"><%=erro%></div>
		<% 
			}
		%>
      
        <div class="col-xs-12 title text-center padbot20">Meus Battlemons</div>
		<% for (JogadorMonstro JM : listaMonstros){ %>        
	        <!-- Para cada monstro no esquadrão -->
	        <div class="col-xs-4 each-meus-battlemons each-monster padtop20">
	          <div class="col-xs-3 photo">
	            <img src="img/monster/icons/<%=JM.getMonstro().getId()%>.png" alt="Icon" />
	          </div>
	          <div class="col-xs-9 stats">
	            <div class="text-uppercase"><strong><%=JM.getNome()%> (Nv. <%=JM.getLvl()%>)</strong></div>
	            <div class="clearfix"></div>
	            
	            <div class="text-uppercase col-xs-2 nopad">
	            	<strong>Exp. </strong>
	            </div>
	            <div class="col-xs-10 exp-block nopad margtop5">
	            	<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="<%=JM.getExp()%>" aria-valuemin="0" aria-valuemax="100" style="width: <%=JM.getExp()%>%">
						<span class="sr-only">100% Complete (success)</span>
					</div>
	            </div>
	            <div class="clearfix"></div>
	            
	            <div class="clearfix"></div>
	            
	            <div class="col-md-12 padbot10 padtop5 nopadlef nopadrig">
	            	<div class="col-xs-4 nopad"> <img src="img/hp.png" /> <%=JM.getHp()%> </div>
		            <div class="col-xs-4 nopad"> <img src="img/atk.png" /> <%=JM.getAtk()%> </div>
		            <div class="col-xs-4 nopad"> <img src="img/def.png" /> <%=JM.getDef()%> </div>
		            <div class="clearfix"></div>
	            </div>
	            <div class="clearfix"></div>
	          </div>
	          <div class="col-xs-12 actions">
	          	<% if ("S".equals(JM.getFlagPrincipal())){ %>
	          		<button class="btn btn-success btn-xs btn-block">
	          			<span class="glyphicon glyphicon-ok"></span> Principal
	          		</button>
	          	<% } else { %>
	          		<a href="/prjBattleMonstersWeb/MeusBattlemons?act=marcar_principal&idMonstro=<%=JM.getId()%>" class="btn btn-default btn-xs btn-block">Marcar como principal</button>
	          		<a href="/prjBattleMonstersWeb/MeusBattlemons?act=remover&idMonstro=<%=JM.getId()%>" class="btn btn-default btn-xs btn-block">Remover</a>
	          	<% } %>
	          </div>
	        </div>
		<% } %>
		<div class="clearfix"></div><br/>
		
		<div class="col-md-12 text-center">
			<div class="col-xs-offset-4 col-xs-4">
				<a href="menu.jsp" class="btn btn-default col-xs-12">Voltar</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div><hr />
		
		<div class="col-xs-12 title text-center padbot20">Battlemons Reservas</div>
		
		<% for (JogadorMonstro JM : listaMonstrosReservas){ %>        
	        <!-- Para cada monstro no esquadrão -->
	        <div class="col-xs-4 each-monster each-meus-battlemons padtop20">
	          <div class="col-xs-3 photo">
	            <img src="img/monster/icons/<%=JM.getMonstro().getId()%>.png" alt="Icon" />
	          </div>
	          <div class="col-xs-9 stats">
	            <div class="text-uppercase"><strong><%=JM.getNome()%> (Nv. <%=JM.getLvl()%>)</strong></div>
	            <div class="clearfix"></div>
	            
	            <div class="text-uppercase col-xs-2 nopad">
	            	<strong>Exp. </strong>
	            </div>
	            <div class="col-xs-10 exp-block nopad margtop5">
	            	<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="<%=JM.getExp()%>" aria-valuemin="0" aria-valuemax="100" style="width: <%=JM.getExp()%>%">
						<span class="sr-only">100% Complete (success)</span>
					</div>
	            </div>
	            <div class="clearfix"></div>
	            
	            <div class="col-md-12 padbot10 padtop5 nopadlef nopadrig">
	            	<div class="col-xs-4 nopad"> <img src="img/hp.png" /> <%=JM.getHp()%> </div>
		            <div class="col-xs-4 nopad"> <img src="img/atk.png" /> <%=JM.getAtk()%> </div>
		            <div class="col-xs-4 nopad"> <img src="img/def.png" /> <%=JM.getDef()%> </div>
		            <div class="clearfix"></div>
	            </div>
	            <div class="clearfix"></div>
	          </div>
	          <div class="col-xs-12 actions">
	          		<a href="/prjBattleMonstersWeb/MeusBattlemons?act=time_principal&idMonstro=<%=JM.getId()%>" class="btn btn-default btn-xs btn-block">Adicionar ao time principal</a>
	          </div>
	        </div>
		<% } %>
		
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