package com.pesquinascordero.entradas;

import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component //Spring esta clase es una pieza de mi programa, los objetos los creas tu
@RequestScope //se hace un carrito nuevo por cada peticion y se tira cuando termines
public class CarritoCompra {

    private final String id = UUID.randomUUID().toString().substring(0, 5);

    public CarritoCompra() {
        System.out.println(">>> Ha nacido un CARRITO: " + id);
    }


    public String getId() {
        return id;
    }
}