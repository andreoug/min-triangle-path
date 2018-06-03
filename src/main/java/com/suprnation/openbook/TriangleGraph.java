package com.suprnation.openbook;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

import static com.suprnation.openbook.ErrorMessage.NO_DATA_AVAILABLE_ERROR;
import static com.suprnation.openbook.ErrorMessage.SYSTEM_ERROR;

/**
 * Created by gandreou on 02/06/2018.
 */
@Getter @Setter
public class TriangleGraph {
    private Node root;

    private List<Node> latestParents = new ArrayList<>();

    public void addElements(String[] elements) {
        if(elements.length == 0) {
            System.out.println(NO_DATA_AVAILABLE_ERROR.getMessage());
            System.exit(1);
        } else if(root == null && elements.length == 1) {
            root = new Node(null, Integer.valueOf(elements[0]));
            latestParents.add(root);
        } else if (root != null && elements.length >= 2) {
            buildTreeLayer(elements);
        }
    }

    private void buildTreeLayer(String[] elements) {
        List<Node> newParents = new ArrayList<>();
        Node left;
        Node right = null;
        int i = 0;
        for(Node parent: latestParents) {
            left = new Node(parent, Integer.valueOf(elements[i]));
            right = new Node(parent, Integer.valueOf(elements[++i]));
            parent.setChildren(Arrays.asList(left, right));

            newParents.add(left);
        }
        newParents.add(right);
        latestParents = newParents;
    }

    private Integer findMinimumTriangleValue(){
        List<Node> nodes = root.stream().collect(Collectors.toList());

        Optional<Node> minimumTrianglePathNode = nodes.stream()
            .peek(this::log)
            .parallel()
            .reduce((n1, n2) -> n1.getTrianglePathValue() > n2.getTrianglePathValue() ? n1 : n2);

        return (minimumTrianglePathNode.isPresent()) ? minimumTrianglePathNode.get().getTrianglePathValue() : null;
    }

    private List<Node> findMinimumTrianglePaths(Integer val) {
        List<Node> nodes = root.stream().collect(Collectors.toList());
        List<Node> minimumTrianglePathNodeList = nodes
            .stream()
            .filter(node -> node.getTrianglePathValue() == val)
            .peek(this::log)
            .parallel()
            .collect(
                Collectors.toList()
            );
        return minimumTrianglePathNodeList;
    }

    public String findMinimumTrianglePaths() {
        Integer minimum = findMinimumTriangleValue();
        String output = null;
        if(minimum != null) {
            List<Node> nodes = findMinimumTrianglePaths(minimum);
            output = "Minimal path is: ";
                for(Node d: nodes)
                    output += d.getTrianglePath() + " = ";
            output += nodes.get(0).getTrianglePathValue();
        } else {
            System.out.println(SYSTEM_ERROR.getMessage());
            System.exit(1);
        }

        return output;
    }
    private void log(Node node) {
        System.out.println("node: " + node.getValue() + " trianglePathValue: " + node.getTrianglePathValue() + " trianglePath: " + node.getTrianglePath());
    }
}
