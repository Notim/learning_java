package br.edu.tpzlms.APP.Teacher.controller;

import br.edu.tpzlms.APP.Teacher.models.TeacherVW;
import br.edu.tpzlms.BLL.services.BLTeacherConsulta;

import static br.edu.tpzlms.APP.Teacher.view.chargeConsole.chargeConsole;
import static br.edu.tpzlms.APP.Teacher.view.listConsole.listConsole;

public class TeacherController {

    public BLTeacherConsulta consulta;

    public TeacherVW ViewModel;

    public TeacherController(){

        this.consulta = new BLTeacherConsulta();
        this.ViewModel = new TeacherVW();

        this.ViewModel.setListTeachers(consulta.list());
        this.ViewModel.setTeacher(consulta.charge(0));
    }

    public void showList() {

        listConsole(this.ViewModel);
    }

    public void showTeacher(int id) {

        this.ViewModel.setTeacher(consulta.charge(id));
        chargeConsole(this.ViewModel);
    }
}
