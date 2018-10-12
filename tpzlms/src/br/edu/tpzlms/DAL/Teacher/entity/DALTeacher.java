package br.edu.tpzlms.DAL.Teacher.entity;

//entity
public class DALTeacher {
    private int id;
    private int ra;
    private int cpf;
    private String name;

    public int getId() { return id; }
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getRa() {return ra;}
    public void setRa(int ra) {this.ra = ra;}
    public int getCpf() {return cpf;}
    public void setCpf(int cpf) {this.cpf = cpf; }
}
