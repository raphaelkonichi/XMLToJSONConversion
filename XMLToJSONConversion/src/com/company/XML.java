package com.company;
import java.util.ArrayList;
import java.util.List;

public class XML {

    private String name = "";
    private List<XML> children;

    XML() {
        super();
        this.children = new ArrayList<XML>();
    }

    public XML(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<XML> getChildren() {
        return children;
    }

    public void setChildren(List<XML> children) {
        this.children = children;
    }

    public void addChild(XML e) {
        this.children.add(e);
    }
}
