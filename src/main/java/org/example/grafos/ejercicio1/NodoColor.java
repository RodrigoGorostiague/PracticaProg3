package org.example.grafos.ejercicio1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodoColor<T> extends Nodo<T, NodoColor<T>>{
    private String color = "Blanco";
}
