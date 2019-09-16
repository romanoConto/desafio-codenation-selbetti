package com.romano;

import com.romano.work.solveText.SolveText;

/**
 * Hello world!
 */
public class App {

    private static String token = "5169c0fabedc59f61ba0d171695c789aa530b2d1";

    public static void main(String[] args) {

        System.out.println("Hello World!");

        SolveText work = new SolveText(token);

        work.solve();
    }
}
