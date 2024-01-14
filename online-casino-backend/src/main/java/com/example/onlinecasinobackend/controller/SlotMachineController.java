import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/slot-machine")
public class SlotMachineController {

    private final SlotMachineService slotMachineService;

    @Autowired
    public SlotMachineController(SlotMachineService slotMachineService) {
        this.slotMachineService = slotMachineService;
    }

    @GetMapping("/symbols")
    public ResponseEntity<List<SymbolEntity>> getAllSymbols() {
        List<SymbolEntity> symbols = slotMachineService.getAllSymbols();
        return ResponseEntity.ok(symbols);
    }

    @GetMapping("/reels")
    public ResponseEntity<List<ReelEntity>> getAllReels() {
        List<ReelEntity> reels = slotMachineService.getAllReels();
        return ResponseEntity.ok(reels);
    }

    @GetMapping("/paylines")
    public ResponseEntity<List<PaylineEntity>> getAllPaylines() {
        List<PaylineEntity> paylines = slotMachineService.getAllPaylines();
        return ResponseEntity.ok(paylines);
    }
}
