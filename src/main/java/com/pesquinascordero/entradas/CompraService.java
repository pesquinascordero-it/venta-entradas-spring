package com.pesquinascordero.entradas;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    private final AforoEstadio aforo;
    private final CarritoCompra carrito;
    private final ObjectProvider<GeneradorQR> generadorQR;

    public CompraService(AforoEstadio aforo, CarritoCompra carrito, ObjectProvider<GeneradorQR> generadorQR) {
        this.aforo = aforo;
        this.carrito = carrito;
        this.generadorQR = generadorQR;
    }

    public RespuestaCompra comprar() {
        boolean vendida = aforo.venderButaca();

        if (!vendida) {
            return null;
        }

        GeneradorQR qr = generadorQR.getObject();

        return new RespuestaCompra("Entrada comprada", aforo.getButacasLibres(), carrito.getId(), qr.getId());
    }
}