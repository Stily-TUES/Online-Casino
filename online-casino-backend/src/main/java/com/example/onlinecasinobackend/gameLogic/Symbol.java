package com.example.onlinecasinobackend.gameLogic;

public class Symbol {

    private final String name;
    private final int payout;

    public Symbol(String name, int payout) {
        this.name = name;
        this.payout = payout;
    }

    public String getName() {
        return name;
    }

    public int getPayout() {
        return payout;
    }

    public static final Symbol SEVEN = new Symbol("Seven", 5);
    public static final Symbol CHERRY = new Symbol("Cherry", 1);
    public static final Symbol MELON = new Symbol("Melon", 2);
    public static final Symbol ORANGE = new Symbol("Orange", 3);
    // public int size() {
    // return name.length();
    // }

}
