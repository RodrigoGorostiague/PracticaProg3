package org.example;

import org.example.grafos.ejercicio1.GrafoDirigido;
import org.example.grafos.ejercicio1.NodoColor;
import org.example.grafos.ejercicio2.Busqueda;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GrafoDirigido<String, NodoColor<String>> grafo = new GrafoDirigido<>();

        // Crear nodos
        NodoColor<String> nodoA = new NodoColor<>("A");
        NodoColor<String> nodoB = new NodoColor<>("B");
        NodoColor<String> nodoC = new NodoColor<>("C");
        NodoColor<String> nodoD = new NodoColor<>("D");
        NodoColor<String> nodoE = new NodoColor<>("E");

        // Agregar nodos al grafo
        grafo.addNodo(nodoA);
        grafo.addNodo(nodoB);
        grafo.addNodo(nodoC);
        grafo.addNodo(nodoD);
        grafo.addNodo(nodoE);

        // Agregar aristas (dirigidas)
        grafo.agregarArista(nodoA, nodoB);
        grafo.agregarArista(nodoA, nodoC);
        grafo.agregarArista(nodoB, nodoD);
        grafo.agregarArista(nodoC, nodoD);
        grafo.agregarArista(nodoD, nodoE);

        //Forma final del grafo dirigido
        /**
         *     A
         *    / \
         *   B   C
         *    \ /
         *     D
         *     |
         *     E
         */


        // Ejecutar DFS
        //Busqueda.dfs(grafo);
        // Ejecutar BFS
        Busqueda.bfs(grafo);
    }
}