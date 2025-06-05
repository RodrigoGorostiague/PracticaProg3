package org.example.grafos.ejercicio1;

public class GrafoDirigido<T, N extends Nodo<T, N>> extends Grafo<T, N> {


    @Override
    public void agregarArista(N origen, N destino) {
        origen.addAdyacente(destino);
    }
}