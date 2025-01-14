/*
 *  Licensed to GraphHopper GmbH under one or more contributor
 *  license agreements. See the NOTICE file distributed with this work for
 *  additional information regarding copyright ownership.
 *
 *  GraphHopper GmbH licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except in
 *  compliance with the License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.graphhopper.routing.util;

import com.graphhopper.util.PMap;

import static com.graphhopper.routing.ev.Smoothness.*;

/**
 * Specifies the settings for cycletouring/trekking
 *
 * @author ratrun
 * @author Peter Karich
 */
public class BikeTagParser extends BikeCommonTagParser {
    public BikeTagParser() {
        this("bike");
    }

    public BikeTagParser(String name) {
        this(name, 4, 2, 0, false);
    }

    public BikeTagParser(PMap properties) {
        this(properties.getString("name", "bike"),
                properties.getInt("speed_bits", 4),
                properties.getInt("speed_factor", 2),
                properties.getInt("max_turn_costs", properties.getBool("turn_costs", false) ? 1 : 0),
                properties.getBool("speed_two_directions", false));

        blockPrivate(properties.getBool("block_private", true));
        blockFords(properties.getBool("block_fords", false));
    }

    public BikeTagParser(int speedBits, double speedFactor, int maxTurnCosts, boolean speedTwoDirections) {
        this("bike", speedBits, speedFactor, maxTurnCosts, speedTwoDirections);
    }

    public BikeTagParser(String name, int speedBits, double speedFactor, int maxTurnCosts, boolean speedTwoDirections) {
        super(name, speedBits, speedFactor, maxTurnCosts, speedTwoDirections);
        addPushingSection("path");
        addPushingSection("footway");
        addPushingSection("pedestrian");
        addPushingSection("steps");
        addPushingSection("platform");

        avoidHighwayTags.add("trunk");
        avoidHighwayTags.add("trunk_link");
        avoidHighwayTags.add("primary");
        avoidHighwayTags.add("primary_link");
        avoidHighwayTags.add("secondary");
        avoidHighwayTags.add("secondary_link");

        // preferHighwayTags.add("road");
        preferHighwayTags.add("service");
        preferHighwayTags.add("tertiary");
        preferHighwayTags.add("tertiary_link");
        preferHighwayTags.add("residential");
        preferHighwayTags.add("unclassified");

        setSmoothnessSpeedFactor(EXCELLENT, 1.1d);
        setSmoothnessSpeedFactor(GOOD, 1.0d);
        setSmoothnessSpeedFactor(INTERMEDIATE, 0.9d);
        setSmoothnessSpeedFactor(BAD, 0.7d);
        setSmoothnessSpeedFactor(VERY_BAD, 0.6d);
        setSmoothnessSpeedFactor(HORRIBLE, 0.5d);
        setSmoothnessSpeedFactor(VERY_HORRIBLE, 0.4d);
        // SmoothnessSpeed <= smoothnessFactorPushingSectionThreshold gets mapped to speed PUSHING_SECTION_SPEED
        setSmoothnessSpeedFactor(IMPASSABLE, smoothnessFactorPushingSectionThreshold);

        barriers.add("kissing_gate");
        barriers.add("stile");
        barriers.add("turnstile");

        setSpecificClassBicycle("touring");
    }
}
