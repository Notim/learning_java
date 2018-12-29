package br.com.battlemonsters.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.battlemonsters.entidades.Jogador;

public class JogadorDAOImpl implements JogadorDAO{
	
	private static EntityManagerFactory emf;
	
	public JogadorDAOImpl() {
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("battlemonsters");
		}
	}

	@Override
	public Jogador fazerLogin(String login, String senha) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Jogador> qry = em.createQuery("SELECT j FROM Jogador j WHERE j.login = '"+ login +"' AND j.senha = '"+ senha +"'", Jogador.class);
		Jogador jogador = null;
		if(qry.getResultList().size() > 0) {
			jogador = qry.getResultList().get(0);
		}
		em.close();
		return jogador;
	}

	@Override
	public boolean cadastroExistente(String login) throws SQLException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Jogador> qry = em.createQuery("SELECT j FROM Jogador j WHERE j.login = '"+ login +"'", Jogador.class);
		List<Jogador> jogadores = qry.getResultList();
		em.close();
		return jogadores.size() > 0;
	}

	@Override
	public void cadastraJogador(String nome, String login, String senha,
			String sexo) throws SQLException {
		Jogador j = new Jogador(nome, login, senha, sexo);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(j);
		em.getTransaction().commit();
		em.close();
	}

}
