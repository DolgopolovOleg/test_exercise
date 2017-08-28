package com.algosec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 8/28/2017.
 */
public class CharNode {
    char ch;
    boolean hasWord;
    CharNode root;
    List<CharNode> children;

    public CharNode(char ch, boolean hasWord) {
        this.ch = ch;
        this.hasWord = hasWord;
        this.children = new ArrayList<>();
    }

    public CharNode(char ch, boolean hasWord, CharNode root) {
        this(ch, hasWord);
        this.root = root;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public boolean isHasWord() {
        return hasWord;
    }

    public void setHasWord(boolean hasWord) {
        this.hasWord = hasWord;
    }

    public CharNode getRoot() {
        return root;
    }

    public void setRoot(CharNode root) {
        this.root = root;
    }

    public List<CharNode> getChildren() {
        return children;
    }
}
