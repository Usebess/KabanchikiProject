package com.kabanchiki.core.comparators;

import com.kabanchiki.core.models.Root;

import java.util.Comparator;

public final class RootComparator {
    private RootComparator() {}

    /**
     * Компаратор, сортирующий Корнеплоды по типу, затем по цвету и весу.
     */
    public static final Comparator<Root> ALL = Comparator.comparing(Root::getType)
            .thenComparing(Root::getColor)
            .thenComparingInt(Root::getWeight);
    public static final Comparator<Root> TYPE = Comparator.comparing(Root::getType);
    public static final Comparator<Root> COLOR = Comparator.comparing(Root::getColor);
    public static final Comparator<Root> WEIGHT = Comparator.comparingInt(Root::getWeight);
}
