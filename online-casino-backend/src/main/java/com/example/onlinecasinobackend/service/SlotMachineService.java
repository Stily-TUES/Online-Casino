
package com.example.onlinecasinobackend.service;

import com.example.onlinecasinobackend.model.SymbolEntity;
import com.example.onlinecasinobackend.repository.SymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotMachineService {

    private final SymbolRepository symbolRepository;

    @Autowired
    public SlotMachineService(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;

    }

    public List<SymbolEntity> getAllSymbols() {
        return symbolRepository.findAll();
    }

}