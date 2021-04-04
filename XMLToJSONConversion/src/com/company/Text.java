package com.company;

public class Text extends XML{

    private String str;

    public Text() {
        this("");
    }

    public Text(String s) {
        super("HASDATA");
        str = s;
    }

    public String getText() {
        return this.str;
    }
}
