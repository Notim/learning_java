package br.edu.tpzlms.APP.Teacher.view;
import br.edu.tpzlms.APP.Teacher.models.TeacherVW;
import br.edu.tpzlms.DAL.Teacher.entity.DALTeacher;

public class listConsole {
    public static void listConsole(TeacherVW ViewModel){
        var out = System.out;
        out.println("ID\tNome");
        for( DALTeacher teacher : ViewModel.getListTeachers()){
            out.println(teacher.getId()+"\t"+teacher.getName());
        }
    }
}