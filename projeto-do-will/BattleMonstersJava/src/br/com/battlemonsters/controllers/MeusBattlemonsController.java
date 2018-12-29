package br.com.battlemonsters.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.battlemonsters.dao.JogadorMonstroDAO;
import br.com.battlemonsters.dao.JogadorMonstroDAOImpl;
import br.com.battlemonsters.entidades.Jogador;
import br.com.battlemonsters.entidades.JogadorMonstro;

@SuppressWarnings("serial")
@WebServlet("/MeusBattlemons")
public class MeusBattlemonsController extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Jogador jogador = (Jogador)session.getAttribute("LOGADO"); 
		
		JogadorMonstroDAO OJogadorMonstroDAO = new JogadorMonstroDAOImpl();
		List<JogadorMonstro> listaMonstros = new ArrayList<JogadorMonstro>();
		List<JogadorMonstro> listaMonstrosReservas = new ArrayList<JogadorMonstro>();
		try {
			listaMonstros = OJogadorMonstroDAO.listarMonstros(jogador.getId(), "N");
			listaMonstrosReservas = OJogadorMonstroDAO.listarMonstros(jogador.getId(), "S");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String act = (String)request.getParameter("act");
		if (act != null && !"".equals(act)){
			int idMonstro = Integer.parseInt(request.getParameter("idMonstro"));
			if ("marcar_principal".equals(act)){
				OJogadorMonstroDAO.marcarPrincipal(idMonstro, jogador.getId());
			} else if ("time_principal".equals(act)) {
				if (listaMonstros.size() == 6){
					request.setAttribute("MENSAGEM", "Seu time principal já possui 6 Battlemons.");
				} else {
					OJogadorMonstroDAO.modificarTimePrincipal(idMonstro, "N");
					
				}
			} else if ("remover".equals(act)){
				OJogadorMonstroDAO.modificarTimePrincipal(idMonstro, "S");
			} 
			
			try {
				listaMonstros = OJogadorMonstroDAO.listarMonstros(jogador.getId(), "N");
				listaMonstrosReservas = OJogadorMonstroDAO.listarMonstros(jogador.getId(), "S");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("LISTA_MONSTROS", listaMonstros);
		request.setAttribute("LISTA_MONSTROS_RESERVAS", listaMonstrosReservas);
		
		RequestDispatcher rd = request.getRequestDispatcher("/meus-battlemons.jsp");
		rd.forward(request, response);
	}
	
}
