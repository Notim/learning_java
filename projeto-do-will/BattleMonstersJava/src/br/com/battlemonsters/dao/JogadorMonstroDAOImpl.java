package br.com.battlemonsters.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.battlemonsters.controllers.Util;
import br.com.battlemonsters.entidades.JogadorMonstro;
import br.com.battlemonsters.entidades.Monstro;

public class JogadorMonstroDAOImpl implements JogadorMonstroDAO{
	
	private static EntityManagerFactory emf;
	
	public JogadorMonstroDAOImpl() {
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("battlemonsters");
		}
	}
	
	@Override
	public void salva(JogadorMonstro jm) throws SQLException {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		if (em.contains(jm)) { 
			em.persist(jm);
		} else {
			em.merge(jm);
		}
		em.getTransaction().commit();
		em.close();
	}
	
	@Override
	public List<JogadorMonstro> listarMonstros(int idJogador, String reserva) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<JogadorMonstro> qry = em.createQuery("select a from JogadorMonstro a where idJogador = " + idJogador + " AND flagReserva = '" + reserva + "'", JogadorMonstro.class);
		List<JogadorMonstro> listaMonstros = qry.getResultList();
		em.close();
		return listaMonstros;
	}
	
	@Override
	public JogadorMonstro getJogadorMonstroById(int idJogadorMonstro) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<JogadorMonstro> qry = em.createQuery("select j from JogadorMonstro j where id = " + idJogadorMonstro, JogadorMonstro.class);
		JogadorMonstro jm = qry.getSingleResult();
		em.close();
		return jm;
	}
	
	@Override
	public JogadorMonstro carregarPrincipal(int idJogador) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<JogadorMonstro> qry = em.createQuery("select a from JogadorMonstro a where idJogador = " + idJogador + " AND flagPrincipal = 'S'", JogadorMonstro.class);
		JogadorMonstro monstroPrincipal = qry.getSingleResult();
		em.close();
		return monstroPrincipal;
	}
	
	@Override
	public void modificarTimePrincipal(int id, String flagReserva){
		EntityManager em = emf.createEntityManager();
		TypedQuery<JogadorMonstro> qry = em.createQuery("select a from JogadorMonstro a where id = " + id, JogadorMonstro.class);
		JogadorMonstro obj = qry.getSingleResult();
		obj.setFlagReserva(flagReserva);
		
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
	}
	
	@Override
	public void marcarPrincipal(int id, int idJogador){
		EntityManager em = emf.createEntityManager();
		TypedQuery<JogadorMonstro> qryList = em.createQuery("select a from JogadorMonstro a where idJogador = " + idJogador, JogadorMonstro.class);
		List<JogadorMonstro> listMonstros = qryList.getResultList();
		
		em.getTransaction().begin();
		for (JogadorMonstro JM : listMonstros){
			JM.setFlagPrincipal("N");
			em.merge(JM);
		}
		em.getTransaction().commit();
		
		TypedQuery<JogadorMonstro> qry = em.createQuery("select a from JogadorMonstro a where id = " + id, JogadorMonstro.class);
		JogadorMonstro obj = qry.getSingleResult();
		obj.setFlagPrincipal("S");
		
		em.getTransaction().begin();
		em.merge(obj);
		em.getTransaction().commit();
	}

	@Override
	public int ganhaExp (JogadorMonstro jm, int expGanha) {
		int expAnterior = jm.getExp();
		int expNova = expGanha + expAnterior;
		int ret = 0;
		if(expNova >= 100) {
			expNova -= 100;

			int newHp = jm.getHp() + Util.randInt(jm.getMonstro().getMinHpPorLvl(), jm.getMonstro().getMaxHpPorLvl());
			int newAtk = jm.getAtk() + Util.randInt(jm.getMonstro().getMinAtkPorLvl(), jm.getMonstro().getMaxAtkPorLvl());
			int newDef = jm.getDef() + Util.randInt(jm.getMonstro().getMinDefPorLvl(), jm.getMonstro().getMaxDefPorLvl());
			
			jm.setLvl(jm.getLvl() + 1);
			jm.setHp(newHp);
			jm.setAtk(newAtk);
			jm.setDef(newDef);
			
			ret = jm.getLvl();
		}
		jm.setExp(expNova);
		
		try {
			this.salva(jm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// retorna o novo nível se upou, ou 0 se não upou
		return ret;
	}

}
