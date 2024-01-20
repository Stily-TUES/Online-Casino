package com.example.onlinecasinobackend.gameLogic;

import java.util.List;
import java.util.Random;

public class Reel {
    private final List<Symbol> stops;

    public Reel(List<Symbol> stops) {
        this.stops = stops;
    }

    public Symbol spin() {
        Random random = new Random();
        int randomIndex = random.nextInt(stops.size());
        return stops.get(randomIndex);
    }
}
