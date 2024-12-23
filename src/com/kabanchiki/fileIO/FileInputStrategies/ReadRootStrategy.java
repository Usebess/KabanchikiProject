package com.kabanchiki.fileIO.FileInputStrategies;

import com.kabanchiki.core.models.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadRootStrategy implements InputStrategy {
    @Override
    public <T> List<T> readData(String data, int maxSize) {
        String regex = "Root\\[type='(.*?)', weight=(\\d+), color='(.*?)']";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);

        List<T> roots = new ArrayList<>();
        while (matcher.find() && roots.size() < maxSize) {
            roots.add((T) new Root.RootBuilder(matcher.group(1))
                    .setWeight(Integer.parseInt(matcher.group(2)))
                    .setColor(matcher.group(3))
                    .build());
        }

        return roots;
    }
}
