package com.graphhopper.routing.weighting;

import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.weighting.ShortFastestWeighting;
import com.graphhopper.util.PMap;

import static com.graphhopper.routing.weighting.TurnCostProvider.NO_TURN_COST_PROVIDER;

public class ChillBikeWeighting extends ShortFastestWeighting {
    // TODO: Implement custom weighting

    public ChillBikeWeighting(FlagEncoder encoder, PMap map, TurnCostProvider turnCostProvider) {
        super(encoder, map, turnCostProvider);
    }

    public ChillBikeWeighting(FlagEncoder encoder, PMap map) {
        this(encoder, map, NO_TURN_COST_PROVIDER);
    }

    @Override
    public String getName() {
        return "chillbike";
    }
}
