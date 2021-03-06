package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileController {
    private FileReader fileReader;
    private BufferedReader reader;

    public FileController(File file) throws IOException {
        this.fileReader = new FileReader(file, StandardCharsets.UTF_16);
        this.reader = new BufferedReader(fileReader);
    }

    public String[] getStrings(){
        ArrayList<String> strings = new ArrayList<>();
        reader.lines().forEach(strings::add);
        return strings.toArray(String[]::new);
    }
}
