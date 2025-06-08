package org.example.grafos.ejercicio1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Grafo <T, N extends Nodo<T, N>> {
    protected List<N> nodos = new ArrayList<>();

    public void addNodo(N nodo) {
        if (!nodos.contains(nodo)) {
            nodos.add(nodo);
        }
    }

    public abstract void agregarArista(N origen, N destino);

    public N getNodo(T valor) {
        for (N nodo : this.getNodos()) {
            if (nodo.getValor().equals(valor)) {
                return nodo;
            }
        }
        throw new NoSuchElementException("Nodo con valor " + valor + " no encontrado.");
    }


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
