package org.example.grafos.ejercicio1;

import java.util.List;

public class GrafoNoDirigido<T, N extends Nodo<T, N>> extends Grafo<T, N> {
    public GrafoNoDirigido(List nodos) {
        super(nodos);
    }

    @Override
    public void agregarArista(N nodo1, N nodo2) {
        nodo1.addAdyacente(nodo2);
        nodo2.addAdyacente(nodo1);
    }
}
