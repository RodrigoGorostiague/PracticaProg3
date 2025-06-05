package org.example.grafos.ejercicio1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodoColor<T> extends Nodo<T, NodoColor<T>>{
    private String color = "Blanco";
    public NodoColor(T valor) {
        super(valor);
        this.color = "Blanco";
    }
    @Override
    public String toString() {
        return "NodoColor{" +
                "valor=" + super.getValor() +
                "color='" + color + '\'' +
                '}';
    }

}
