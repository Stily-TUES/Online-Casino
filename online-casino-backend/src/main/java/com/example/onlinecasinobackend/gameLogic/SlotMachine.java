package com.example.onlinecasinobackend.gameLogic;

import com.example.onlinecasinobackend.gameLogic.Payline;
import com.example.onlinecasinobackend.gameLogic.Symbol;
import com.example.onlinecasinobackend.model.User;
import com.example.onlinecasinobackend.gameLogic.Reel;
import com.example.onlinecasinobackend.gameLogic.Payline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SlotMachine {

    private final List<Reel> reels;
    private final List<Payline> paylines;
    private double jackpotPool = 0.0;
    private static final double BET_AMOUNT = 1.0;
    public static final Symbol SEVEN = new Symbol("Seven", 5);
    public static final Symbol CHERRY = new Symbol("Chery", 1);
    public static final Symbol MELON = new Symbol("Melon", 2);
    public static final Symbol ORANGE = new Symbol("Orange", 3);

    public SlotMachine(List<Reel> reels, List<Payline> paylines) {
        this.reels = reels;
        this.paylines = paylines;
    }

    public double getJackpotPool() {
        return jackpotPool;
    }

    public void spin(User user) {

        if (user.getBalance() < BET_AMOUNT) {
            System.out.println("Insufficient funds. Please add more money to your balance.");
            return;
        }

        user.deductBalance(BET_AMOUNT);

        jackpotPool += BET_AMOUNT;

        for (Payline payline : paylines) {
            List<List<Symbol>> reelSymbols = new ArrayList<>();
            for (Reel reel : reels) {
                List<Symbol> symbolsOnReel = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    symbolsOnReel.add(reel.spin());
                }
                reelSymbols.add(symbolsOnReel);
            }

            Map.Entry<Integer, Symbol> winningInfo = payline.getWinningCountAndSymbol(reelSymbols);
            int winningCount = winningInfo.getKey();
            Symbol winningSymbol = winningInfo.getValue();

            if (winningCount >= 3) {
                if (winningSymbol == SEVEN && winningCount == 5) {
                    double jackpotWinnings = jackpotPool;
                    user.addBalance(jackpotWinnings);
                    System.out.println("Jackpot! You won $" + jackpotWinnings + "!");
                    jackpotPool = 0.0;
                } else {
                    double regularWinnings = calculateWinnings(winningSymbol, winningCount);
                    user.addBalance(regularWinnings);
                    System.out.println("You won $" + regularWinnings + "!");
                }
            } else {
                System.out.println("Sorry, you didn't win.");
            }
        }
    }

    private double calculateWinnings(Symbol winningSymbol, int winningCount) {
        double winnings = BET_AMOUNT * winningSymbol.getPayout() * winningCount;
        return winnings;
    }
}
