import com.example.onlinecasinobackend.gameLogic.*;
import com.example.onlinecasinobackend.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SlotMachineTest {

    @Test
    public void testSpinWithInsufficientFunds() {
        User user = new User("TestUser", 0.5);
        List<Reel> reels = createMockReels();
        List<Payline> paylines = createMockPaylines();
        SlotMachine slotMachine = new SlotMachine(reels, paylines);


        slotMachine.spin(user);


        assertEquals(0.5, user.getBalance(), 0.001);
        assertEquals(0.0, slotMachine.getJackpotPool(), 0.001);
    }

    @Test
    public void testSpinWithWinningCombination() {

        User user = new User("TestUser", 10.0);
        List<Reel> reels = createMockReels();
        List<Payline> paylines = createMockPaylines();
        SlotMachine slotMachine = new SlotMachine(reels, paylines);

        for (Reel reel : reels) {
            when(reel.spin()).thenReturn(new Symbol("Seven", 5));
        }

        slotMachine.spin(user);

        // Assert
        assertEquals(15.0, user.getBalance(), 0.001); // User wins the jackpot
        assertEquals(0.0, slotMachine.getJackpotPool(), 0.001);
    }

    @Test
    public void testSpinWithoutWinningCombination() {
        // Arrange
        User user = new User("TestUser", 10.0);
        List<Reel> reels = createMockReels();
        List<Payline> paylines = createMockPaylines();
        SlotMachine slotMachine = new SlotMachine(reels, paylines);

        // Mock the spin result to create a non-winning combination
        for (Reel reel : reels) {
            when(reel.spin()).thenReturn(new Symbol("Cherry", 1));
        }

        // Act
        slotMachine.spin(user);

        // Assert
        assertEquals(9.0, user.getBalance(), 0.001); // User loses the bet amount
        assertEquals(1.0, slotMachine.getJackpotPool(), 0.001);
    }

    private List<Reel> createMockReels() {
        // Create mock reels with a single symbol for simplicity
        List<Reel> reels = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Reel reel = Mockito.mock(Reel.class);
            when(reel.spin()).thenReturn(new Symbol("TestSymbol", 1));
            reels.add(reel);
        }
        return reels;
    }

    private List<Payline> createMockPaylines() {
        List<Payline> paylines = new ArrayList<>();
        Payline payline = Mockito.mock(Payline.class);
        when(payline.getWinningCountAndSymbol(Mockito.any())).thenReturn(Map.entry(3, new Symbol("TestSymbol", 1)));
        paylines.add(payline);
        return paylines;
    }
}
