package com.graphhopper.routing.util;

import com.graphhopper.reader.ReaderWay;
import com.graphhopper.routing.util.BikeTagParser;
import com.graphhopper.util.PMap;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import static com.graphhopper.routing.util.PriorityCode.VERY_NICE;

public class ChillBikeTagParser extends BikeTagParser {

    protected final Set<String> veryNiceHighwayTags = new HashSet<>();

    public ChillBikeTagParser() {
        this("chillbike");
    }

    public ChillBikeTagParser(String name) {
        this(name, 4, 2, 0, false);
    }

    public ChillBikeTagParser(PMap properties) {
        this(properties.getString("name", "chillbike"),
                properties.getInt("speed_bits", 4),
                properties.getInt("speed_factor", 2),
                properties.getInt("max_turn_costs", properties.getBool("turn_costs", false) ? 1 : 0),
                properties.getBool("speed_two_directions", false));

        blockPrivate(properties.getBool("block_private", true));
        blockFords(properties.getBool("block_fords", false));
    }

    public ChillBikeTagParser(int speedBits, double speedFactor, int maxTurnCosts, boolean speedTwoDirections) {
        this("chillbike", speedBits, speedFactor, maxTurnCosts, speedTwoDirections);
    }

    public ChillBikeTagParser(String name, int speedBits, double speedFactor, int maxTurnCosts, boolean speedTwoDirections) {
        super(name, speedBits, speedFactor, maxTurnCosts, speedTwoDirections);

        setAvoidSpeedLimit(30);

        veryNiceHighwayTags.add("living_street");
        veryNiceHighwayTags.add("residential");

        setSpecificClassBicycle("non_experienced");
    }

   @Override
   void collect(ReaderWay way, double wayTypeSpeed, TreeMap<Double, Integer> weightToPrioMap) {
        super.collect(way, wayTypeSpeed, weightToPrioMap);
        String highway = way.getTag("highway");
        if (veryNiceHighwayTags.contains(highway)) {
            weightToPrioMap.put(110d, VERY_NICE.getValue());
        }
   }
}