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

import br.com.battlemonsters.dao.JogadorMonstroDAOImpl;
import br.com.battlemonsters.dao.MonstroDAOImpl;
import br.com.battlemonsters.entidades.Jogador;
import br.com.battlemonsters.entidades.JogadorMonstro;
import br.com.battlemonsters.entidades.Monstro;

@SuppressWarnings("serial")
@WebServlet("/EscolheInicial")
public class EscolheInicialController extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		MonstroDAOImpl md = new MonstroDAOImpl();
		JogadorMonstroDAOImpl jmi = new JogadorMonstroDAOImpl();
		
		String escolhido = request.getParameter("inicialEscolhido");
		String nome = request.getParameter("nomeEscolhido");
		
		JogadorMonstro jogadorMonstro = new JogadorMonstro();
		Monstro monstro = new Monstro();

		HttpSession session = request.getSession();
		Jogador jogador = (Jogador) session.getAttribute("LOGADO"); 

		try {
			switch (escolhido) {
				case "Sapograma":
					monstro = md.getMonstroById(1);
					break;
		
				case "Lagartochama":
					monstro = md.getMonstroById(4);
					break;
					
				case "Tartaragua":
					monstro = md.getMonstroById(7);
					break;
	
				default:
					break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int atk = 15 + Util.randInt(monstro.getMinAtkPorLvl(), monstro.getMaxAtkPorLvl());
		int def = 15 + Util.randInt(monstro.getMinDefPorLvl(), monstro.getMaxDefPorLvl());
		int hp = 20 + Util.randInt(monstro.getMinHpPorLvl(), monstro.getMaxHpPorLvl());
		
		jogadorMonstro.setJogador(jogador);
		jogadorMonstro.setMonstro(monstro);
		jogadorMonstro.setFlagPrincipal("S");
		jogadorMonstro.setFlagReserva("N");
		jogadorMonstro.setNome(nome);
		jogadorMonstro.setLvl(1);
		jogadorMonstro.setExp(0);
		jogadorMonstro.setAtk(atk);
		jogadorMonstro.setDef(def);
		jogadorMonstro.setHp(hp);
		
		try {
			jmi.salva(jogadorMonstro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("MENSAGEM", "Bem vindo ao BattleMonsters!");
		RequestDispatcher rd = request.getRequestDispatcher("/menu.jsp");
		rd.forward(request, response);
	}
	
}
