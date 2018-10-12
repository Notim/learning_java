package br.edu.tpzlms.BLL.services;

import br.edu.tpzlms.BLL.interfaces.IBLTeacherConsulta;
import br.edu.tpzlms.DAL.Teacher.entity.DALTeacher;
import br.edu.tpzlms.UTILS.String.RandomString;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BLTeacherConsulta implements IBLTeacherConsulta {
    private List<DALTeacher> listTeachers;
    private DALTeacher Teacher;

    public BLTeacherConsulta(){
        System.out.println("start BLTeacherConsulta");
    }

    public DALTeacher charge(int id){
        this.Teacher = new DALTeacher();

        Random genRan = new Random();
        RandomString genString = new RandomString(10, ThreadLocalRandom.current());


        this.Teacher.setId(id);
        this.Teacher.setCpf(genRan.nextInt());
        this.Teacher.setName(genString.nextString());
        this.Teacher.setRa(genRan.nextInt());

        return this.Teacher;
    }


    public List<DALTeacher> list() {
        this.listTeachers = new ArrayList<DALTeacher>();

        this.Teacher = this.charge(1);
        this.listTeachers.add(this.Teacher);

        this.Teacher = this.charge(2);
        this.listTeachers.add(this.Teacher);

        this.Teacher = this.charge(15);
        this.listTeachers.add(this.Teacher);

        this.Teacher = this.charge(14);
        this.listTeachers.add(this.Teacher);

        this.Teacher = this.charge(17);
        this.listTeachers.add(this.Teacher);

        this.Teacher = this.charge(12);
        this.listTeachers.add(this.Teacher);

        return listTeachers;
    }
}