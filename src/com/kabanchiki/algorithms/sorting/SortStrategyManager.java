package com.kabanchiki.algorithms.sorting;

import java.util.List;

/**
 * Реализует интерфейс {@link SortStrategy}
 * Обязательный параметр передать в конструктор для сортировки по умолчанию
 * Принимает другие сортировщики, реализующие интерфейс {@link SortStrategy}
 * @param <T>
 */
public final class SortStrategyManager<T> implements SortStrategy<T> {
    private SortStrategy<T> sortStrategy;

    public SortStrategyManager(SortStrategy<T> sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void setSortStrategy(SortStrategy<T> sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    @Override
    public List<T> sort(List<T> list) {
        return sortStrategy.sort(list);
    }
}
