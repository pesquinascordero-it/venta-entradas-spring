package com.pesquinascordero.entradas;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraController {

    private final AforoEstadio aforo;
    private final CarritoCompra carrito;
    private final ObjectProvider<GeneradorQR> generadorQR;

    public CompraController(AforoEstadio aforo, CarritoCompra carrito, ObjectProvider<GeneradorQR> generadorQR) {
        this.aforo = aforo;
        this.carrito = carrito;
        this.generadorQR = generadorQR;
    }

    @GetMapping("/comprar")
    public String comprar() {
        GeneradorQR qr1 = generadorQR.getObject();
        GeneradorQR qr2 = generadorQR.getObject();

        return "Aforo: " + aforo.getId()
             + " | Carrito: " + carrito.getId()
             + " | QR1: " + qr1.getId()
             + " | QR2: " + qr2.getId();
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