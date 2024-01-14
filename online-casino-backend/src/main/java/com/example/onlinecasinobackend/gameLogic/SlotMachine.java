public class SlotMachine {

    private List<Reel> reels;
    private List<Symbol> symbols;
    private List<Payline> paylines;
    private double jackpotPool = 0.0;
    private static final double BET_AMOUNT = 1.0;

    public SlotMachine(List<Reel> reels, List<Symbol> symbols, List<Payline> paylines) {
        this.reels = reels;
        this.symbols = symbols;
        this.paylines = paylines;
    }

    public void spin(User user) {
        // Check if the user has enough balance
        if (user.getBalance() < BET_AMOUNT) {
            System.out.println("Insufficient funds. Please add more money to your balance.");
            return;
        }
    
        // Deduct the bet amount from the user's balance
        user.deductBalance(BET_AMOUNT);
    
        // Add $1 to the jackpot pool for every spin
        jackpotPool += BET_AMOUNT;
    
        // Spin each reel
        for (Reel reel : reels) {
            reel.spin();
        }
    
        // Check combinations on paylines and calculate winnings
        for (Payline payline : paylines) {
            Map.Entry<Integer, Symbol> winningInfo = getWinningCountAndSymbol(payline);
            int winningCount = winningInfo.getKey();
            Symbol winningSymbol = winningInfo.getValue();
    
            if (winningCount >= 3) {
                if (winningSymbol == Symbol.SEVEN && winningCount == 5) {
                    double jackpotWinnings = jackpotPool;
                    user.addBalance(jackpotWinnings);
                    System.out.println("Jackpot! You won $" + jackpotWinnings + "!");
                    // Reset the jackpot pool after a jackpot win
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
