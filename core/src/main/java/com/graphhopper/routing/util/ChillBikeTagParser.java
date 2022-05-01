package com.graphhopper.routing.util;

import com.graphhopper.routing.util.BikeTagParser;
import com.graphhopper.util.PMap;

public class ChillBikeTagParser extends BikeTagParser {
    // TODO: Flesh this out with custom tags
    public ChillBikeTagParser() {
        this("chillbike");
    }

    public ChillBikeTagParser(String name) {
        super(name);
    }

    public ChillBikeTagParser(PMap properties) {
        super(properties);
    }
}
