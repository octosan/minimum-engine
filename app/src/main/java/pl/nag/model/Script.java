package pl.nag.model;

import java.util.List;

public class Script {
    private String name;
    private List<Node> nodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "Script{" +
                "name='" + name + '\'' +
                ", node=" + nodes +
                '}';
    }
}
