package com.pesquinascordero.entradas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AforoController {

    private final AforoEstadio aforo;

    public AforoController(AforoEstadio aforo) {
        this.aforo = aforo;
    }

    @GetMapping("/aforo")
    public EstadioAforo consultar() {
        return new EstadioAforo(aforo.getId(), aforo.getButacasLibres());
    }
}