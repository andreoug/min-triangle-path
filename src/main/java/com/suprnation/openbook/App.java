package com.suprnation.openbook;

import java.util.Scanner;

public final class App {

    private final static String SPLIT_REGEX = " ";

    public App() {}

    public static void main(String[] args) {

        TriangleGraph triangle = new TriangleGraph();

        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            triangle.addElements(s.nextLine().split(SPLIT_REGEX));
        }

        triangle.findMinimalPaths();
    }
}
