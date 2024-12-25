package com.kabanchiki.fileIO.fileInputStrategies;

import java.util.List;

// Интерфейс стратегии считывания.
public interface InputStrategy {
    public <T> List<T> readData(String data, int maxSize);
}
