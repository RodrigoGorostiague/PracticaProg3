package org.example.grafos.ejercicio1;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

public class GrafoDirigido<T, N extends Nodo<T, N>> extends Grafo<T, N> {

    public GrafoDirigido(List<N> nodos) {
        super(nodos);
    }

    @Override
    public void agregarArista(N origen, N destino) {
        origen.addAdyacente(destino);
    }
}