package br.com.battlemonsters.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.battlemonsters.entidades.JogadorMonstro;

public interface JogadorMonstroDAO {
	
	void salva(JogadorMonstro jm) throws SQLException;

	List<JogadorMonstro> listarMonstros(int idJogador, String reserva) throws SQLException;

	void modificarTimePrincipal(int id, String flagReserva);

	void marcarPrincipal(int id, int idJogador);

	JogadorMonstro carregarPrincipal(int idJogador) throws SQLException;

	JogadorMonstro getJogadorMonstroById(int idJogadorMonstro) throws SQLException;

	int ganhaExp(JogadorMonstro jm, int expGanha);

}
