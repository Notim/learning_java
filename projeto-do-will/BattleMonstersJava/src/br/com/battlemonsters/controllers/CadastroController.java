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
@WebServlet("/Cadastro")
public class CadastroController extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String nome = request.getParameter("txtNome");
		String login = request.getParameter("txtLogin");
		String senha = request.getParameter("txtSenha");
		String sexo = request.getParameter("txtSexo");
		boolean jaCadastrado = true;
		
		JogadorDAO OJogadorDAO = new JogadorDAOImpl();
		try {
			jaCadastrado = OJogadorDAO.cadastroExistente(login);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(jaCadastrado) {
			request.setAttribute("MENSAGEM", "Usuário já cadastrado.");
			RequestDispatcher rd = request.getRequestDispatcher("/cadastro.jsp");
			rd.forward(request, response);
		}
		
		Jogador jogadorCadastrado = new Jogador();
		try {
			OJogadorDAO.cadastraJogador(nome, login, senha, sexo);
			jogadorCadastrado = OJogadorDAO.fazerLogin(login, senha);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(jogadorCadastrado != null) {
			HttpSession session = request.getSession();
			session.setAttribute("LOGADO", jogadorCadastrado);
			
			request.setAttribute("MENSAGEM", "Bem vindo ao BattleMonsters!");
			RequestDispatcher rd = request.getRequestDispatcher("/escolheInicial.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("MENSAGEM", "Erro ao fazer cadastro, tente novamente.");
			RequestDispatcher rd = request.getRequestDispatcher("/cadastro.jsp");
			rd.forward(request, response);
		}
	}
	
}
