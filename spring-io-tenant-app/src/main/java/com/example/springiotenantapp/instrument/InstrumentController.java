package com.example.springiotenantapp.instrument;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrument")
@RequiredArgsConstructor
public class InstrumentController {
    private final InstrumentRepository instrumentRepository;
    @GetMapping
    public ResponseEntity<List<Instrument>> getAll(){
        return ResponseEntity.ok().body(instrumentRepository.findAll());
    }
    @GetMapping("/{type}")
    public ResponseEntity<List<Instrument>> getByType(@PathVariable String type){
        return ResponseEntity.ok().body(instrumentRepository.findByType(type));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createInstrument(@RequestBody Instrument instrument){
        instrumentRepository.save(instrument);
    }
}
