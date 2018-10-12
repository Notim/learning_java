package br.edu.tpzlms;

import java.beans.JavaBean;
import java.io.IOException;

import static br.edu.tpzlms.APP.startApp.startApp;

public class Main {
    public static void main(String[] args) throws IOException {
        var out = System.out;

        out.println("Java version: " + System.getProperty("java.version"));
        out.println("Simple MVC application, without database, but in this architeture mode is so simple to put!!");
        out.println("- Writed with S2 by NOTIM");

        startApp();
    }
}
