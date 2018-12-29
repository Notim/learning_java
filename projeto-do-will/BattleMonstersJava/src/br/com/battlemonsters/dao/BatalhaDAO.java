package br.com.battlemonsters.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.battlemonsters.dto.MonstroDTO;
import br.com.battlemonsters.entidades.Jogador;
import br.com.battlemonsters.entidades.JogadorMonstro;
import br.com.battlemonsters.entidades.Monstro;

public interface BatalhaDAO {

	List<Integer> carregarIdMonstros() throws SQLException;
	MonstroDTO sortearMonstro();
	MonstroDTO sortearMonstroPorLvl(int lvl);
	MonstroDTO carregarMonstroJogador(int idJogador);
	List<JogadorMonstro> listarEsquadrao(int idJogador);
	List<Integer> carregarIdMonstrosPorLvl(int lvl) throws SQLException;
	
}
