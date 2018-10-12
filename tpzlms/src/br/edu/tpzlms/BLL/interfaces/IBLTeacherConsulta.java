package br.edu.tpzlms.BLL.interfaces;

import br.edu.tpzlms.DAL.Teacher.entity.DALTeacher;

import java.util.List;

public interface IBLTeacherConsulta {
    DALTeacher charge(int id);
    List<DALTeacher> list();
}
