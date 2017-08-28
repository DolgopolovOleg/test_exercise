package com.algosec;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 8/28/2017.
 */
public class Main {
    public static void main(String[] args) {
        Path p = Paths.get("src/main/resources/words.txt");
        List<String> words = new ArrayList<>();

        try(BufferedReader reader = Files.newBufferedReader(p, Charset.forName("UTF-8"))){


            String currentLine = null;
            while((currentLine = reader.readLine()) != null){//while there is content on the current line
                words.add(currentLine);
            }
        }catch(IOException ex){
            ex.printStackTrace(); //handle an exception here
        }

        StringChecker checker = new StringChecker(words);
        System.out.println(checker.getDictionary().size());
    }
}
