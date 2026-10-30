package com.pesquinascordero.entradas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping("/compras")
    public ResponseEntity<RespuestaCompra> comprar() {
        RespuestaCompra respuesta = compraService.comprar();

        if (respuesta == null) {
            RespuestaCompra agotado = new RespuestaCompra("Entradas agotadas", 0, null, null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(agotado);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

    }

    @GetMapping("/sin-inyeccion")
    public String sinInyeccion() {
        AforoEstadio aforo = new AforoEstadio();
        CarritoCompra carrito = new CarritoCompra();
        GeneradorQR qr = new GeneradorQR();

        return "SIN Spring -> Aforo: " + aforo.getId()
             + " | Carrito: " + carrito.getId()
             + " | QR: " + qr.getId();
    }
}