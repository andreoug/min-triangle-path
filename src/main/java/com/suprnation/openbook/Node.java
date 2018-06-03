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
    private int value;
    private Node parent;
    private List<Node> children;

    public Node(Node parent, int value) {
        this.parent = parent;
        this.value = value;
    }

    public final int getTrianglePathValue(){
        int restTrianglePathValue = (parent != null) ? parent.getTrianglePathValue()  : 0;
        return restTrianglePathValue + value;
    }
    public final String getTrianglePath(){
        String restTrianglePath = (parent != null) ? parent.getTrianglePath() + " + " : "";
        return restTrianglePath + value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Node{");
        sb.append("value=").append(value);
        sb.append('}');
        return sb.toString();
    }

    public Stream<Node> stream() {
        return (children != null) ? Stream.concat(Stream.of(this), children.stream().flatMap(Node::stream))
            : Stream.of(this);
    }
}
