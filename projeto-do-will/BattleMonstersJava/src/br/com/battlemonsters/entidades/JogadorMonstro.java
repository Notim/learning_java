package br.com.battlemonsters.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "tb_jogador_monstro")
public class JogadorMonstro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "idJogador")
	private Jogador Jogador;
	
	@ManyToOne
	@JoinColumn(name = "idMonstro")
	private Monstro Monstro;
	
	private String nome;
	private int lvl;
	private int hp;
	private int atk;
	private int def;
	private int exp;
	
	@Column(length = 1)
	private String flagPrincipal;
	
	@Column(length = 1)
	private String flagReserva;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Jogador getJogador() {
		return Jogador;
	}
	public void setJogador(Jogador jogador) {
		Jogador = jogador;
	}
	public Monstro getMonstro() {
		return Monstro;
	}
	public void setMonstro(Monstro monstro) {
		Monstro = monstro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
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
	public String getFlagPrincipal() {
		return flagPrincipal;
	}
	public void setFlagPrincipal(String flagPrincipal) {
		this.flagPrincipal = flagPrincipal;
	}
	public String getFlagReserva() {
		return flagReserva;
	}
	public void setFlagReserva(String flagReserva) {
		this.flagReserva = flagReserva;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	
}
