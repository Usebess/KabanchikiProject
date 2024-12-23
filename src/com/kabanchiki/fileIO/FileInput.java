package com.kabanchiki.fileIO;

import com.kabanchiki.core.models.*;
import com.kabanchiki.fileIO.FileInputStrategies.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInput {

    public <T> List<T> readDataFromFile(Class<T> type, int maxSize) {
        String data = "";
        Path file = Paths.get("src/com/kabanchiki/fileIO/" + type.getSimpleName() + ".txt");

        try {
            if (Files.exists(file)) data = Files.readString(file);
            else {
                return new ArrayList<>();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        InputStrategy inputStrategy;
        if (type == Book.class) inputStrategy = new ReadBookStrategy();
        else if (type == Car.class) inputStrategy = new ReadCarStrategy();
        else if (type == Root.class) inputStrategy = new ReadRootStrategy();
        else return new ArrayList<>();

        return inputStrategy.readData(data, maxSize);
    }
}
