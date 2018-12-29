package br.com.battlemonsters.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "tb_monstro")
public class Monstro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "idTipoMonstro")
	private TipoMonstro TipoMonstro;
	
	private String nome;
	private int baseLvlMin;
	private int baseLvlMax;
	private int minHpPorLvl;
	private int minAtkPorLvl;
	private int minDefPorLvl;
	private int maxHpPorLvl;
	private int maxAtkPorLvl;
	private int maxDefPorLvl;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoMonstro getTipoMonstro() {
		return TipoMonstro;
	}
	public void setTipoMonstro(TipoMonstro tipoMonstro) {
		TipoMonstro = tipoMonstro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getBaseLvlMin() {
		return baseLvlMin;
	}
	public void setBaseLvlMin(int baseLvlMin) {
		this.baseLvlMin = baseLvlMin;
	}
	public int getBaseLvlMax() {
		return baseLvlMax;
	}
	public void setBaseLvlMax(int baseLvlMax) {
		this.baseLvlMax = baseLvlMax;
	}
	public int getMinHpPorLvl() {
		return minHpPorLvl;
	}
	public void setMinHpPorLvl(int minHpPorLvl) {
		this.minHpPorLvl = minHpPorLvl;
	}
	public int getMinAtkPorLvl() {
		return minAtkPorLvl;
	}
	public void setMinAtkPorLvl(int minAtkPorLvl) {
		this.minAtkPorLvl = minAtkPorLvl;
	}
	public int getMinDefPorLvl() {
		return minDefPorLvl;
	}
	public void setMinDefPorLvl(int minDefPorLvl) {
		this.minDefPorLvl = minDefPorLvl;
	}
	public int getMaxHpPorLvl() {
		return maxHpPorLvl;
	}
	public void setMaxHpPorLvl(int maxHpPorLvl) {
		this.maxHpPorLvl = maxHpPorLvl;
	}
	public int getMaxAtkPorLvl() {
		return maxAtkPorLvl;
	}
	public void setMaxAtkPorLvl(int maxAtkPorLvl) {
		this.maxAtkPorLvl = maxAtkPorLvl;
	}
	public int getMaxDefPorLvl() {
		return maxDefPorLvl;
	}
	public void setMaxDefPorLvl(int maxDefPorLvl) {
		this.maxDefPorLvl = maxDefPorLvl;
	}
	
}
