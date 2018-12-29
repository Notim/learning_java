package br.edu.tpzlms.APP.Teacher.view;

import br.edu.tpzlms.APP.Teacher.models.TeacherVW;
import br.edu.tpzlms.DAL.Teacher.entity.DALTeacher;

public class chargeConsole {
    public static void chargeConsole(TeacherVW ViewModel){
        var out = System.out;
        out.println("ID\t Nome \t Cpf\t   RA");
        out.println(ViewModel.getTeacher().getId()+
            "\t" + ViewModel.getTeacher().getName()+
            "\t" + ViewModel.getTeacher().getCpf() +
            "\t" + ViewModel.getTeacher().getRa()
        );
    }
}