package br.com.battlemonsters.dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.battlemonsters.entidades.Monstro;

public class MonstroDAOImpl implements MonstroDAO {
	
	private static EntityManagerFactory emf;
	
	public MonstroDAOImpl() {
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("battlemonsters");
		}
	}

	@Override
	public Monstro getMonstroById(int id) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Monstro> qry = em.createQuery("SELECT m FROM Monstro m WHERE m.id = '"+ id +"'", Monstro.class);
		Monstro monstro = null;
		if (qry.getResultList().size() > 0){
			monstro = qry.getResultList().get(0);
		}
		em.close();
		return monstro;
	}

}
