package com.kabanchiki.fileIO.FileInputStrategies;

import java.util.List;

public interface InputStrategy {
    public <T> List<T> readData(String data, int maxSize);
}
