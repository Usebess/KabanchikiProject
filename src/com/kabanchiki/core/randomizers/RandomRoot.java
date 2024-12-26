package com.kabanchiki.core.randomizers;

import com.kabanchiki.core.models.Root;

import java.util.Random;

public class RandomRoot {
    private String[] types = {"beet", "carrot", "kohlrabi", "potato", "parsnip", "rutabaga"};
    private String[] colors = {"red", "yellow", "green", "blue", "orange", "violet", "pink", "gray"};

    public Root getRandomRoot() {
        Random random = new Random();
        return new Root.RootBuilder(types[random.nextInt(6)])
                .setColor(colors[random.nextInt(8)])
                .setWeight(random.nextInt(5))
                .build();
    }
}
