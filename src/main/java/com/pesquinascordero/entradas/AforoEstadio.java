package com.pesquinascordero.entradas;

import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

@Component
@Scope("singleton") //esto es singleton, crea una sola pieza de mi programa y se reutiliza siempre
public class AforoEstadio {

    private final String id = UUID.randomUUID().toString().substring(0, 5);

    private int butacasLibres = 5;

    public AforoEstadio() {
        System.out.println(">>> Ha nacido un AFORO: " + id);
    }


    public String getId() {
        return id;
    }

    public synchronized int getButacasLibres() {
        return butacasLibres;
    }

    public synchronized boolean venderButaca() {
        if (butacasLibres > 0) {
            butacasLibres--;
            return true;
        }
        return false;
    }
}