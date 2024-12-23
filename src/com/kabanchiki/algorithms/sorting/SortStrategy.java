package com.kabanchiki.algorithms.sorting;

import java.util.List;

/**
 * Интерфейс стратегии сортировки
 * @param <T>
 */
public interface SortStrategy<T> {
    List<T> sort(List<T> list);
}
