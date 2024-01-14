import java.util.List;
import java.util.Random;

public class Reel {
    private List<Symbol> stops; // List of symbols on the reel

    public Reel(List<Symbol> stops) {
        this.stops = stops;
    }

    public Symbol spin() {
        Random random = new Random();
        int randomIndex = random.nextInt(stops.size());
        return stops.get(randomIndex);
    }
}
