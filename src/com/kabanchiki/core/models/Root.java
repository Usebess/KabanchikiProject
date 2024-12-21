package com.kabanchiki.core.models;

import com.kabanchiki.core.comparators.RootComparator;

import java.util.Objects;
import java.util.StringJoiner;

public class Root implements Comparable<Root> {
    private String type;
    private int weight;
    private String color;

    private Root(RootBuilder rootBuilder) {
        type = rootBuilder.type;
        weight = rootBuilder.weight;
        color = rootBuilder.color;
    }

    public String getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    @Override
    public int compareTo(Root o) {
        return RootComparator.ALL.compare(this, o);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Root root)) return false;
        return weight == root.weight && Objects.equals(type, root.type) && Objects.equals(color, root.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, weight, color);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Root.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .add("weight=" + weight)
                .add("color='" + color + "'")
                .toString();
    }

    public static class RootBuilder {
        private String type;
        private int weight;
        private String color;

        public RootBuilder(String type) {
            this.type = type;
        }

        public RootBuilder setWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public RootBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public Root build() {
            return new Root(this);
        }
    }
}
