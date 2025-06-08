package org.example.grafos.ejercicio1;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Nodo<T, N extends Nodo<T, N>> {
    private T valor;
    private List<N> adyacentes = new ArrayList<>();
    private int tiempoDescubrimiento;
    private int tiempoFinalizacion;

    public Nodo(T valor) {
        this.valor = valor;
        this.adyacentes = new ArrayList<>();
    }

    public Nodo() {
    }

    public void addAdyacente(N destino) {
        if (!adyacentes.contains(destino)) {
            adyacentes.add(destino);
        }
    }



    @Override
    public String toString() {
        return "Nodo{" +
                "valor=" + valor +
                '}';
    }
    public void imprimir(){
        System.out.println(this.toString());
    }
}