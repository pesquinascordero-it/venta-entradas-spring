package com.pesquinascordero.entradas;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<RespuestaCompra> comprar() {
        boolean vendida =aforo.venderButaca();

        if (!vendida) {
            RespuestaCompra respuesta = new RespuestaCompra("Entradas agotadas", 0, null, null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }

        GeneradorQR qr = generadorQR.getObject();
        
        RespuestaCompra respuesta = new RespuestaCompra("Entrada comprada", aforo.getButacasLibres(), carrito.getId(), qr.getId()) ;
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