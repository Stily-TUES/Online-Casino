package com.example.onlinecasinobackend.tests;

import com.example.onlinecasinobackend.gameLogic.Reel;
import com.example.onlinecasinobackend.gameLogic.Symbol;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReelTest {

    @Test
    public void testSpin() {

        List<Symbol> testSymbols = Arrays.asList(
                new Symbol("Symbol1", 1),
                new Symbol("Symbol2", 2),
                new Symbol("Symbol3", 3));

        Reel reel = new Reel(testSymbols);

        Random mockRandom = Mockito.mock(Random.class);
        Mockito.when(mockRandom.nextInt(Mockito.anyInt())).thenReturn(0);


        reel.setRandomGenerator(mockRandom);

        Symbol spunSymbol = reel.spin();

        assertEquals(testSymbols.get(0), spunSymbol);
    }
}
