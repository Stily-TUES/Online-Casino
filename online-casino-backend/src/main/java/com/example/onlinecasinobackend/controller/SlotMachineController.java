package com.example.onlinecasinobackend.controller;

import com.example.onlinecasinobackend.model.SymbolEntity;
import com.example.onlinecasinobackend.service.SlotMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
