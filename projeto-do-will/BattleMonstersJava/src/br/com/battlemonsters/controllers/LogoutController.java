package br.com.battlemonsters.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.battlemonsters.dao.JogadorDAO;
import br.com.battlemonsters.dao.JogadorDAOImpl;
import br.com.battlemonsters.entidades.Jogador;

@SuppressWarnings("serial")
@WebServlet("/Logout")
public class LogoutController extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.setAttribute("LOGADO", null);
		
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
	
}
