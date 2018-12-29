package br.com.battlemonsters.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.json.JSONObject;

import br.com.battlemonsters.dao.BatalhaDAO;
import br.com.battlemonsters.dao.BatalhaDAOImpl;
import br.com.battlemonsters.dao.JogadorMonstroDAOImpl;
import br.com.battlemonsters.dao.MonstroDAOImpl;
import br.com.battlemonsters.dto.MonstroDTO;
import br.com.battlemonsters.entidades.Jogador;
import br.com.battlemonsters.entidades.JogadorMonstro;

@SuppressWarnings("serial")
@WebServlet(
	urlPatterns = {
		"/Batalha", 
		"/BatalhaAtaca", 
		"/BatalhaCaptura", 
		"/BatalhaCapturou"
	},
	asyncSupported = true
)
public class BatalhaController extends HttpServlet {
	
	HttpSession session;
	boolean dead;
	boolean capturou;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		session = request.getSession();
		Jogador jogador = (Jogador) session.getAttribute("LOGADO"); 
		
		BatalhaDAO OBatalhaDAO = new BatalhaDAOImpl();
		MonstroDTO MonstroJogador = OBatalhaDAO.carregarMonstroJogador(jogador.getId());
		MonstroDTO MonstroAdversario = OBatalhaDAO.sortearMonstroPorLvl(MonstroJogador.getLvl());
		List<JogadorMonstro> esquadrao = OBatalhaDAO.listarEsquadrao(jogador.getId());
		
		session.setAttribute("MONSTRO_ADVERSARIO", MonstroAdversario);
		session.setAttribute("MONSTRO_JOGADOR", MonstroJogador);
		session.setAttribute("ESQUADRAO", esquadrao);
		
		RequestDispatcher rd = request.getRequestDispatcher("/batalha.jsp");
		rd.forward(request, response);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		session = request.getSession();
		Jogador jogador = (Jogador) session.getAttribute("LOGADO"); 
		
		String urlRequest = request.getRequestURI();
		HashMap<Object, Object> ret = new HashMap<Object, Object>();
		JSONObject retJson;
		
		MonstroDTO ally = (MonstroDTO) session.getAttribute("MONSTRO_JOGADOR");
		MonstroDTO enemy = (MonstroDTO) session.getAttribute("MONSTRO_ADVERSARIO");
		JogadorMonstroDAOImpl jmdi = new JogadorMonstroDAOImpl();
		MonstroDAOImpl mdi = new MonstroDAOImpl();
		JogadorMonstro jm = null;
		
		try {
			jm = jmdi.getJogadorMonstroById(ally.getIdJogadorMonstro());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (urlRequest) {
			case "/prjBattleMonstersWeb/BatalhaAtaca":
				int danoAoInimigo = atacar(ally, enemy);
				ret.put("danoAoInimigo", danoAoInimigo);
				if(dead) {
					int expGanha = 40 - enemy.getLvl();
					expGanha = Util.randInt(expGanha - 5, expGanha + 5);
					int upou = jmdi.ganhaExp(jm, expGanha);
					
					ret.put("matou", true);
					ret.put("morreu", false);
					ret.put("expGanha", expGanha);
					ret.put("upou", upou);
				} else {
					int danoAoAliado = atacar(enemy, ally);
					ret.put("danoAoAliado", danoAoAliado);
					if(dead) {
						ret.put("matou", false);
						ret.put("morreu", true);
					}
				}
				
				retJson = new JSONObject(ret);
				response.getWriter().write(retJson.toString());
				break;

			case "/prjBattleMonstersWeb/BatalhaCaptura":
				int chanceCaptura = (int) 100 - (20 + ((100 - (enemy.getHp() * 100) / enemy.getMaxHp()) / 2));
				
				if(Util.randInt(0, 100) > chanceCaptura) {
					capturou = true;
				} else {
					capturou = false;
				}
				
				ret.put("capturou", capturou);
				retJson = new JSONObject(ret);
				response.getWriter().write(retJson.toString());
				break;

			case "/prjBattleMonstersWeb/BatalhaCapturou":
				JogadorMonstro jmCapturado = new JogadorMonstro();
				try {
					jmCapturado.setJogador(jogador);
					jmCapturado.setMonstro(mdi.getMonstroById(enemy.getId()));
					jmCapturado.setFlagPrincipal("N");
					jmCapturado.setFlagReserva("S");
					
					String nomeMonstro = request.getParameter("nomeMonstro");
					nomeMonstro = "".equals(nomeMonstro) ? jmCapturado.getMonstro().getNome() : nomeMonstro;
					jmCapturado.setNome(nomeMonstro);
					
					jmCapturado.setLvl(enemy.getLvl());
					jmCapturado.setExp(0);
					jmCapturado.setAtk(enemy.getAtk());
					jmCapturado.setDef(enemy.getDef());
					jmCapturado.setHp(enemy.getMaxHp());
					
					jmdi.salva(jmCapturado);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ret.put("nomeCapturado", jmCapturado.getNome());
				retJson = new JSONObject(ret);
				response.getWriter().write(retJson.toString());
				break;
			
			default:
				break;
		}
	}

	private int atacar(MonstroDTO atacante, MonstroDTO atacado) {
		int dano = (int) Math.round((atacante.getAtk() - (atacante.getAtk() / atacado.getDef())) * 0.4);
		dead = false;
		
		if(dano < 1) {
			dano = 1;
		} else if (dano >= atacado.getHp()) {
			dano = atacado.getHp();
			dead = true;
		}
		
		atacado.setHp(atacado.getHp() - dano);
		
		return dano;
	}
		
}
