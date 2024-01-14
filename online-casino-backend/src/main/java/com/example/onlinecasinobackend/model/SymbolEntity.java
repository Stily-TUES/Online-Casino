import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SymbolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int payout;

    public SymbolEntity(String name, int payout) {
        this.name = name;
        this.payout = payout;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPayout() {
        return payout;
    }
}
