package com.pesquinascordero.entradas;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/compras")
    public RespuestaCompra comprar() {
        boolean vendida =aforo.venderButaca();

        if (!vendida) {
            return new RespuestaCompra("Entradas agotadas", 0, null, null);
        }

        GeneradorQR qr = generadorQR.getObject();
        
        return new RespuestaCompra("Entrada comprada", aforo.getButacasLibres(), carrito.getId(), qr.getId()) ;

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