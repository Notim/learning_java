package br.edu.tpzlms
import java.io.IOException

import br.edu.tpzlms.APP.startApp.startApp

object Main {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val out = System.out

        out.println("Java version: " + System.getProperty("java.version"))
        out.println("Simple MVC application, without database, but in this architeture mode is so simple to put!!")
        out.println("- Writed with S2 by NOTIM")

        startApp()
    }
}
