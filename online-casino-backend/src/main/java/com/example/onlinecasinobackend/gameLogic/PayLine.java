
package com.example.onlinecasinobackend.gameLogic;

import java.util.List;
import java.util.AbstractMap;
import java.util.Map;

public class Payline {

    private List<Symbol> symbols;

    public Payline(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public Map.Entry<Integer, Symbol> getWinningCountAndSymbol() {
        int maxCount = 0;
        Symbol winningSymbol = null;
    
        // Check for vertical matches
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
    
        // Check for diagonal matches (top-left to bottom-right)
        for (int row = 0; row < symbols.size() - 2; row++) {
            for (int col = 0; col < symbols.get(row).size() - 2; col++) {
                Symbol symbol = symbols.get(row).get(col);
                if (symbol == symbols.get(row + 1).get(col + 1) && symbol == symbols.get(row + 2).get(col + 2)) {
                    return new AbstractMap.SimpleEntry<>(3, symbol); // Diagonal match of 3 symbols
                }
            }
        }
    
        // Check for diagonal matches (top-right to bottom-left)
        for (int row = 0; row < symbols.size() - 2; row++) {
            for (int col = symbols.get(row).size() - 1; col >= 2; col--) {
                Symbol symbol = symbols.get(row).get(col);
                if (symbol == symbols.get(row + 1).get(col - 1) && symbol == symbols.get(row + 2).get(col - 2)) {
                    return new AbstractMap.SimpleEntry<>(3, symbol); // Diagonal match of 3 symbols
                }
            }
        }
    
        return new AbstractMap.SimpleEntry<>(maxCount, winningSymbol); // Return the maximum count and winning symbol
    }
}
