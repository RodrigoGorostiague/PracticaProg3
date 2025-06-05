package org.example.grafos.ejercicio1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Grafo <T, N extends Nodo<T, N>> {
    protected List<N> nodos = new ArrayList<>();

    public void addNodo(N nodo) {
        nodos.add(nodo);
    }

    public abstract void agregarArista(N origen, N destino);

    public void imprimirGrafo() {
        for (N nodo : nodos) {
            System.out.print(nodo + " -> ");
            for (N ady : nodo.getAdyacentes()) {
                System.out.print(ady + " ");
            }
            System.out.println();
        }
    }
}
