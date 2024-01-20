
package com.example.onlinecasinobackend.gameLogic;

import java.util.List;
import java.util.AbstractMap;
import java.util.Map;

public class Payline {

    private List<List<Symbol>> symbols;

    // public Payline(List<String> symbols) {
    // this.symbols = symbols;
    // }

    public Map.Entry<Integer, Symbol> getWinningCountAndSymbol(List<List<Symbol>> symbols) {
        int maxCount = 0;
        Symbol winningSymbol = null;

        for (int col = 0; col < symbols.get(0).size(); col++) {
            int count = 1;
            Symbol prevSymbol = symbols.get(0).get(col);
            for (int row = 1; row < symbols.size(); row++) {
                Symbol currentSymbol = symbols.get(row).get(col);
                if (currentSymbol == prevSymbol) {
                    count++;
                    if (count > maxCount) {
                        maxCount = count;
                        winningSymbol = currentSymbol;
                    }
                } else {
                    count = 1;
                }
                prevSymbol = currentSymbol;
            }
        }

        for (int row = 0; row < symbols.size() - 2; row++) {
            for (int col = 0; col < symbols.get(row).size() - 2; col++) {
                Symbol symbol = symbols.get(row).get(col);
                if (symbol == symbols.get(row + 1).get(col + 1) && symbol == symbols.get(row + 2).get(col + 2)) {
                    return new AbstractMap.SimpleEntry<>(3, symbol);
                }
            }
        }

        for (int row = 0; row < symbols.size() - 2; row++) {
            for (int col = symbols.get(row).size() - 1; col >= 2; col--) {
                Symbol symbol = symbols.get(row).get(col);
                if (symbol == symbols.get(row + 1).get(col - 1) && symbol == symbols.get(row + 2).get(col - 2)) {
                    return new AbstractMap.SimpleEntry<>(3, symbol);
                }
            }
        }

        return new AbstractMap.SimpleEntry<>(maxCount, winningSymbol);
    }
}
