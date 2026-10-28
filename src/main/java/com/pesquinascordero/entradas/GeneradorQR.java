package com.pesquinascordero.entradas;

import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class GeneradorQR {

    private final String id = UUID.randomUUID().toString().substring(0, 5);

    public GeneradorQR() {
        System.out.println(">>> Ha nacido un QR: " + id);
    }

    public String getId() {
        return id;
    }
}