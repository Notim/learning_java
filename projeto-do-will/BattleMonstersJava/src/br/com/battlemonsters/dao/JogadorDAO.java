package br.com.battlemonsters.dao;

import java.sql.SQLException;

import br.com.battlemonsters.entidades.Jogador;

public interface JogadorDAO {

	public Jogador fazerLogin(String login, String senha) throws SQLException;
	public boolean cadastroExistente(String login) throws SQLException;
	public void cadastraJogador(String nome, String login, String senha, String sexo) throws SQLException;
	
}
