package com.kabanchiki.core;

public class Root {
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
