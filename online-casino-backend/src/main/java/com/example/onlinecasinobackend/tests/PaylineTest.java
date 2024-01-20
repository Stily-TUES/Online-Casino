package com.example.onlinecasinobackend.tests;

import com.example.onlinecasinobackend.gameLogic.Payline;
import com.example.onlinecasinobackend.gameLogic.Symbol;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaylineTest {

        @Test
        public void testGetWinningCountAndSymbol() {
                Payline payline = new Payline();

                // Test case 1: No winning combination
                List<List<Symbol>> symbols1 = Arrays.asList(
                                Arrays.asList(new Symbol("A", 1), new Symbol("B", 2), new Symbol("C", 3)),
                                Arrays.asList(new Symbol("D", 4), new Symbol("E", 5), new Symbol("F", 6)),
                                Arrays.asList(new Symbol("G", 7), new Symbol("H", 8), new Symbol("I", 9)));
                Map.Entry<Integer, Symbol> result1 = payline.getWinningCountAndSymbol(symbols1);
                assertEquals(0, result1.getKey());
                assertNull(result1.getValue());

                // Test case 2: Winning combination in a column
                List<List<Symbol>> symbols2 = Arrays.asList(
                                Arrays.asList(new Symbol("A", 1), new Symbol("B", 2), new Symbol("C", 3)),
                                Arrays.asList(new Symbol("A", 1), new Symbol("E", 5), new Symbol("F", 6)),
                                Arrays.asList(new Symbol("A", 1), new Symbol("H", 8), new Symbol("I", 9)));
                Map.Entry<Integer, Symbol> result2 = payline.getWinningCountAndSymbol(symbols2);
                assertEquals(3, result2.getKey());
                assertEquals(new Symbol("A"), result2.getValue());

                // Test case 3: Winning combination in a diagonal
                List<List<Symbol>> symbols3 = Arrays.asList(
                                Arrays.asList(new Symbol("A", 1), new Symbol("B", 2), new Symbol("C", 3)),
                                Arrays.asList(new Symbol("D", 4), new Symbol("A", 1), new Symbol("F", 6)),
                                Arrays.asList(new Symbol("G", 7), new Symbol("H", 8), new Symbol("A", 1)));
                Map.Entry<Integer, Symbol> result3 = payline.getWinningCountAndSymbol(symbols3);
                assertEquals(3, result3.getKey());
                assertEquals(new Symbol("A"), result3.getValue());
        }
}
