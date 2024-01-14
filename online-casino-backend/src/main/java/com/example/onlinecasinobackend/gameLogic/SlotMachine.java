public class SlotMachine {

    private List<Reel> reels;
    private List<Symbol> symbols;
    private List<Payline> paylines;

    // Assuming initial bet amount is $1
    private static final double BET_AMOUNT = 1.0;

    public SlotMachine(List<Reel> reels, List<Symbol> symbols, List<Payline> paylines) {
        this.reels = reels;
        this.symbols = symbols;
        this.paylines = paylines;
    }

    public void spin(User user) {
        if (user.getBalance() < BET_AMOUNT) {
            System.out.println("Insufficient funds. Please add more money to your balance.");
            return;
        }

        user.deductBalance(BET_AMOUNT);
    
        for (Reel reel : reels) {
            reel.spin();
        }

        for (Payline payline : paylines) {
            Map.Entry<Integer, Symbol> winningInfo = getWinningCountAndSymbol(payline);
            int winningCount = winningInfo.getKey();
            Symbol winningSymbol = winningInfo.getValue();
    
            if (winningCount >= 3) {
                double winnings = calculateWinnings(winningSymbol, winningCount);
                user.addBalance(winnings);
                System.out.println("You won " + winnings + "!");
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
