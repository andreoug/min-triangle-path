package com.suprnation.openbook;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gandreou on 02/06/2018.
 */
@Data
public class Node {
    private Integer value;
    private Node parent;
    private List<Node> children;

    private Integer trianglePathValue;

    public Node(Node parent, int value) {
        this.parent = parent;
        this.value = value;
    }

    public final Integer getTrianglePathValue(){
        if(trianglePathValue == null) {
            Integer parental = (parent != null) ? parent.getTrianglePathValue() : 0;
            trianglePathValue = parental + value;
        }
            return trianglePathValue;
    }
    public final String getTrianglePath(){
        String parental = (parent != null) ? parent.getTrianglePath() + " + " : "";
        return parental + value;
    }

    public Stream<Node> stream() {
        return (children != null) ? Stream.concat(Stream.of(this), children.stream().flatMap(Node::stream))
            : Stream.of(this);
    }
}
