package br.com.battlemonsters.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.battlemonsters.controllers.Util;
import br.com.battlemonsters.dto.MonstroDTO;
import br.com.battlemonsters.entidades.Jogador;
import br.com.battlemonsters.entidades.JogadorMonstro;
import br.com.battlemonsters.entidades.Monstro;

public class BatalhaDAOImpl implements BatalhaDAO{
	
	private static EntityManagerFactory emf;
	
	public BatalhaDAOImpl() {
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("battlemonsters");
		}
	}

	@Override
	public List<Integer> carregarIdMonstros() throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Integer> qry = em.createQuery("SELECT m.id FROM Monstro m", Integer.class);
		List<Integer> idsMonstros = qry.getResultList();
		
		em.close();
		return idsMonstros;
	}

	@Override
	public List<Integer> carregarIdMonstrosPorLvl(int lvl) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Integer> qry = em.createQuery("SELECT m.id FROM Monstro m WHERE "+lvl+" BETWEEN m.baseLvlMin AND m.baseLvlMax", Integer.class);
		List<Integer> idsMonstros = qry.getResultList();
		
		em.close();
		return idsMonstros;
	}
	
	@Override
	public MonstroDTO sortearMonstro(){
		List<Integer> idsMonstros = new ArrayList<Integer>();
		try {
			idsMonstros = carregarIdMonstros();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int idMonstroSorteado = Util.randInt(idsMonstros);
		Monstro MonstroSorteado = new Monstro();
		try {
			MonstroSorteado = new MonstroDAOImpl().getMonstroById(idMonstroSorteado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (MonstroSorteado == null){
			sortearMonstro();
		}
		
		MonstroDTO MonstroSorteadoDTO = new MonstroDTO();
		MonstroSorteadoDTO.setId(MonstroSorteado.getId());
		MonstroSorteadoDTO.setNome(MonstroSorteado.getNome());
		
		int lvl = Util.randInt(MonstroSorteado.getBaseLvlMin(), MonstroSorteado.getBaseLvlMax());
		MonstroSorteadoDTO.setLvl(lvl);
		
		MonstroSorteadoDTO.setHp(Util.randInt(MonstroSorteado.getMinHpPorLvl(), MonstroSorteado.getMaxHpPorLvl()) * lvl);
		MonstroSorteadoDTO.setMaxHp(MonstroSorteadoDTO.getHp());
		MonstroSorteadoDTO.setAtk(Util.randInt(MonstroSorteado.getMinAtkPorLvl(), MonstroSorteado.getMaxAtkPorLvl()) * lvl);
		MonstroSorteadoDTO.setDef(Util.randInt(MonstroSorteado.getMinDefPorLvl(), MonstroSorteado.getMaxDefPorLvl()) * lvl);
		
		return MonstroSorteadoDTO;
	}
	
	@Override
	public MonstroDTO sortearMonstroPorLvl(int lvl){
		List<Integer> idsMonstros = new ArrayList<Integer>();
		try {
			idsMonstros = carregarIdMonstrosPorLvl(lvl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int idMonstroSorteado = Util.randInt(idsMonstros);
		Monstro MonstroSorteado = new Monstro();
		try {
			MonstroSorteado = new MonstroDAOImpl().getMonstroById(idMonstroSorteado);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (MonstroSorteado == null){
			sortearMonstro();
		}
		
		MonstroDTO MonstroSorteadoDTO = new MonstroDTO();
		MonstroSorteadoDTO.setId(MonstroSorteado.getId());
		MonstroSorteadoDTO.setNome(MonstroSorteado.getNome());

		/* 
		 * Level
		*/
		int porcLvlEnemy = Util.randInt(0, 100);
		int lvlEnemy = 1;
		if(porcLvlEnemy < 10) {
			// 2lvl -
			lvlEnemy = lvl - 2;
		} else if(porcLvlEnemy < 30) {
			// 1lvl -
			lvlEnemy = lvl - 1;
		} else if(porcLvlEnemy < 60) {
			// lvl =
			lvlEnemy = lvl;
		} else if(porcLvlEnemy < 80) {
			// 1lvl +
			lvlEnemy = lvl + 1;
		} else {
			// 2lvl +
			lvlEnemy = lvl + 2;
		}
		if(lvlEnemy < 1) {
			lvlEnemy = 1;
		}
		
		/* 
		 * HP
		*/
		int hpEnemy = 20;
		for (int i = 0; i < lvlEnemy; i++) {
			hpEnemy = hpEnemy + Util.randInt(MonstroSorteado.getMinHpPorLvl(), MonstroSorteado.getMaxHpPorLvl());
		}
		
		/* 
		 * Atk
		*/
		int atkEnemy = 15;
		for (int i = 0; i < lvlEnemy; i++) {
			atkEnemy = atkEnemy + Util.randInt(MonstroSorteado.getMinAtkPorLvl(), MonstroSorteado.getMaxAtkPorLvl());
		}
		
		/* 
		 * Def
		*/
		int defEnemy = 15;
		for (int i = 0; i < lvlEnemy; i++) {
			defEnemy = defEnemy + Util.randInt(MonstroSorteado.getMinDefPorLvl(), MonstroSorteado.getMaxDefPorLvl());
		}
		
		MonstroSorteadoDTO.setLvl(lvlEnemy);
		MonstroSorteadoDTO.setMaxHp(hpEnemy);
		MonstroSorteadoDTO.setHp(hpEnemy);
		MonstroSorteadoDTO.setAtk(atkEnemy);
		MonstroSorteadoDTO.setDef(defEnemy);
		
		return MonstroSorteadoDTO;
	}
	
	@Override
	public MonstroDTO carregarMonstroJogador(int idJogador){
		JogadorMonstroDAO OJogadorMonstroDAO = new JogadorMonstroDAOImpl();
		JogadorMonstro MonstroPrincipal = new JogadorMonstro();
		try {
			MonstroPrincipal = OJogadorMonstroDAO.carregarPrincipal(idJogador);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		MonstroDTO MonstroPrincipalDTO = new MonstroDTO();
		MonstroPrincipalDTO.setId(MonstroPrincipal.getMonstro().getId());
		MonstroPrincipalDTO.setIdJogadorMonstro(MonstroPrincipal.getId());
		MonstroPrincipalDTO.setNome(MonstroPrincipal.getNome());
		MonstroPrincipalDTO.setLvl(MonstroPrincipal.getLvl());
		MonstroPrincipalDTO.setMaxHp(MonstroPrincipal.getHp());
		MonstroPrincipalDTO.setHp(MonstroPrincipal.getHp());
		MonstroPrincipalDTO.setAtk(MonstroPrincipal.getAtk());
		MonstroPrincipalDTO.setDef(MonstroPrincipal.getDef());
		
		return MonstroPrincipalDTO;
		
	}
	
	@Override
	public List<JogadorMonstro> listarEsquadrao(int idJogador){
		JogadorMonstroDAO OJogadorMonstroDAO = new JogadorMonstroDAOImpl();
		List<JogadorMonstro> esquadrao = new ArrayList<JogadorMonstro>(); 
		
		try {
			esquadrao = OJogadorMonstroDAO.listarMonstros(idJogador, "N");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return esquadrao;
		
	}
}
