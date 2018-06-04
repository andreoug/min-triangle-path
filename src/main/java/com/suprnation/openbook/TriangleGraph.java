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
        Node left, right = null;
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

    private Integer findMinimalTriangleValue(){
        Optional<Node> node = root.stream()
            .parallel()
            .collect(Collectors.maxBy(Comparator.comparing(Node::getTrianglePathValue)));

        return (node.isPresent()) ? node.get().getTrianglePathValue() : null;
    }

    private List<Node> findMinimalPaths(Integer val) {
        List<Node> minimumTrianglePathNodeList = root.stream()
            .filter(node -> node.getTrianglePathValue() == val)
            .parallel()
            .collect(Collectors.toList());

        return minimumTrianglePathNodeList;
    }

    public String findMinimalPaths() {
        Integer minimum = findMinimalTriangleValue();
        String output = null;
        if(minimum != null) {
            List<Node> nodes = findMinimalPaths(minimum);
            output = "Minimal path is: ";
                for(Node d: nodes)
                    output += d.getTrianglePath() + " = ";
            output += nodes.get(0).getTrianglePathValue();
            System.out.println(output);
        } else {
            System.out.println(SYSTEM_ERROR.getMessage());
            System.exit(1);
        }

        return output;
    }

    public String findMinimalPath() {
        Optional<Node> node = root.stream()
            .parallel()
            .collect(Collectors.maxBy(Comparator.comparing(Node::getTrianglePathValue)));

        String output = null;
        if(node.isPresent()) {
            output = "Minimal path is: ";
            output += node.get().getTrianglePath() + " = ";
            output += node.get().getTrianglePathValue();
            System.out.println(output);
        } else {
            System.out.println(SYSTEM_ERROR.getMessage());
            System.exit(1);
        }

        return output;
    }
}
