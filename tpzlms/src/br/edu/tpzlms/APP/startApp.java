package br.edu.tpzlms.APP;

import br.edu.tpzlms.APP.Teacher.controller.TeacherController;
import br.edu.tpzlms.BLL.services.BLTeacherConsulta;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class startApp {
    public static void startApp()  throws IOException {

        var Control = new TeacherController();

        var in = new BufferedReader(new InputStreamReader(System.in));
        var out = System.out;

        while (true) {
            out.println("--------------------------------");
            out.println("Digite a opção por favor!");
            out.println("1 para listar os professores");
            out.println("2 para carregar o professor pelo id");
            out.println("qualquer outra tecla para sair!");

            var opt = in.readLine();
            if (opt.equals("1")) {

                Control.showList();
            } else if (opt.equals("2")) {
                out.println("Digite o id");
                var id = Integer.parseInt(in.readLine());
                Control.showTeacher(id);
            }else{
                out.println("saindo, obrigado");
                System.exit(0);
            }
        }
    }
}