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
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String login = request.getParameter("txtLogin");
		String senha = request.getParameter("txtSenha");
		Jogador jogadorLogado = new Jogador();
		
		JogadorDAO OJogadorDAO = new JogadorDAOImpl();
		try {
			jogadorLogado = OJogadorDAO.fazerLogin(login, senha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (jogadorLogado != null) {
			HttpSession session = request.getSession();
			session.setAttribute("LOGADO", jogadorLogado);
			
			request.setAttribute("MENSAGEM", "Bem vindo ao BattleMon!");
			RequestDispatcher rd = request.getRequestDispatcher("/menu.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("MENSAGEM", "Usuário e/ou senha inválidos.");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
	}
	
}
