package com.suprnation.openbook;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.suprnation.openbook.ErrorMessage.NO_DATA_AVAILABLE_ERROR;

/**
 * Created by gandreou on 02/06/2018.
 */
@Getter @Setter
public class TriangleGraph {
    private Node root;
    private String minimumTrianglePath = "1 + 2 + 3 = 6";

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
}
