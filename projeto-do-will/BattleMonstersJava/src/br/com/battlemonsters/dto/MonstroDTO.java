package br.com.battlemonsters.dto;

public class MonstroDTO {

	private int id;
	private int idJogadorMonstro;
	private String nome;
	private int maxHp;
	private int hp;
	private int atk;
	private int def;
	private int lvl;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdJogadorMonstro() {
		return idJogadorMonstro;
	}
	public void setIdJogadorMonstro(int idJogadorMonstro) {
		this.idJogadorMonstro = idJogadorMonstro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}	
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
}
