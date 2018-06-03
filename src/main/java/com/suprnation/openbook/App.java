package com.suprnation.openbook;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class App {

    private final static String SPLIT_REGEX = " ";
    private static TriangleGraph triangle = new TriangleGraph();

    public App() {}

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            triangle.addElements(s.nextLine().split(SPLIT_REGEX));
        }

        Node root = triangle.getRoot();

        List<Node> nodes = root.stream().collect(Collectors.toList());

        Optional<Node> minimunTrianglePathNode = nodes.stream()
            .peek(node -> {System.out.println("node: " + node.getValue() + " trianglePathValue: " + node.getTrianglePathValue() + " trianglePath: " + node.getTrianglePath());})
            .reduce(
                (n1, n2) -> {
                    return n1.getTrianglePathValue() > n2.getTrianglePathValue() ? n1 : n2;
                });

        System.out.print("Minimal path is: ");
        minimunTrianglePathNode
            .ifPresent((n) -> System.out.println(n.getTrianglePath() + " = " + n.getTrianglePathValue()));
    }


    public String getMinimumTrianglePath() {
        return triangle.getMinimumTrianglePath();
    }
}
