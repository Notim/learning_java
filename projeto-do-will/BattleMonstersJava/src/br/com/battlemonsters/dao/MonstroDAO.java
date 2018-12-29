package br.com.battlemonsters.dao;

import java.sql.SQLException;

import br.com.battlemonsters.entidades.Monstro;

public interface MonstroDAO {
	
	public Monstro getMonstroById(int id) throws SQLException;

}
