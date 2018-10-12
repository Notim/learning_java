package br.edu.tpzlms.APP.Teacher.models;

import br.edu.tpzlms.DAL.Teacher.entity.DALTeacher;

import java.util.List;

public class TeacherVW {

    private DALTeacher Teacher;
    private List<DALTeacher> listTeachers;


    public DALTeacher getTeacher() {
        return Teacher;
    }

    public void setTeacher(DALTeacher teacher) {
        Teacher = teacher;
    }

    public List<DALTeacher> getListTeachers() {
        return listTeachers;
    }
    public void setListTeachers(List<DALTeacher> listTeachers) {
        this.listTeachers = listTeachers;
    }
}
