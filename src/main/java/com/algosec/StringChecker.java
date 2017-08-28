package com.algosec;

import java.util.*;
import java.util.stream.Collectors;

public class StringChecker {
    /**
     * The set of words which will be used as a dictionary
     */
//    private Set<String> dictionary;
    private Set<CharNode> charDictionary = new LinkedHashSet<>();

    public StringChecker(Collection<String> dictionary) {
        this.charDictionary = buildDictionary(dictionary);
    }

    public StringChecker() {
        this.charDictionary = new LinkedHashSet<>();
    }

    /**
     * Method for building a dictionary. It changes words for future comparison and changes the word order
     */
    private Set<CharNode> buildDictionary(Collection<String> dictionary) {
        LinkedHashSet<String> words = dictionary.stream()
                .sorted(Comparator.comparingInt(String::length))
                .map(StringChecker::rangeLetters)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        for (String word : words) {
            char[] chars = word.toCharArray();
            CharNode current = null;
            for (int i = 0; i < chars.length; i++) {
                int num = i;
                if (charDictionary.stream().anyMatch(cn -> cn.getCh() == chars[num])) {
                    current = charDictionary.stream().filter(cn -> cn.getCh() == chars[num]).findFirst().orElse(null);
                    continue;
                }
                if (current == null){
                    current = new CharNode(chars[i], i == chars.length - 1, current);
                    charDictionary.add(current);
                } else {
                    Optional<CharNode> first = current.getChildren().stream().filter(cn -> cn.getCh() == chars[num]).findFirst();
                    if (!first.isPresent()) {
                        CharNode charNode = new CharNode(chars[i], i == chars.length - 1, current);
                        current.getChildren().add(charNode);
                        current = charNode;
                    } else {
                        current = first.get();
                    }
                }
            }
        }

        return charDictionary;
    }

    /**
     * Check the string
     *
     * @param inputString string which will be checked
     * @return if {@code inpuctString} consists of the dictionary words
     */
    public boolean checkString(String inputString) {
        if (inputString.length() == 0) return true;
        for (String word : dictionary) {
            if (checkWord(word, inputString))
                return checkString(inputString.substring(word.length()));
        }
        return false;
    }

    /**
     * Take a word from the string and check it
     *
     * @param word        a word from the dictionary
     * @param inputString string which will be used for the word
     * @return if a string starts with the {@code word}
     */
    private boolean checkWord(String word, String inputString) {
        if (inputString.length() < word.length()) return false;
        String croppedString = inputString.substring(0, word.length());
        croppedString = rangeLetters(croppedString);
        if (croppedString.equals(word))
            return checkString(inputString.substring(word.length()));
        return false;
    }

    /**
     * Change a word letters order to alphabet one
     *
     * @param word input word
     * @return return a word whose letters are ranged
     */
    private static String rangeLetters(String word) {
        List<String> rangedList = new ArrayList<>();
        for (char ch : word.toCharArray())
            rangedList.add(String.valueOf(ch));
        return rangedList.stream()
                .sorted()
                .reduce((a, b) -> a + b)
                .orElse("");
    }

    public Set<String> getDictionary() {
        return new LinkedHashSet<>(dictionary);
    }
}

