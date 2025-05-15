package com.alura.conversor.metodos;

import java.util.ArrayList;
import java.util.List;

public class ListaConversion {
    private List<Moneda> monedas;

    public  ListaConversion(){

        Moneda m1 = new Moneda("USD", "US Dollar");
        Moneda m2 = new Moneda("MXN", "Mexican Peso");
        Moneda m3 = new Moneda("EUR", "Euro");
        Moneda m4 = new Moneda("ARS", "Argentine Peso");
        Moneda m5 = new Moneda("COP", "Colombian Peso");
        Moneda m6 = new Moneda("VES", "Venezuelan Bolívar Soberano");
        Moneda m7 = new Moneda("CNY", "Chinese Renminbi");
        Moneda m8 = new Moneda("JPY", "Japanese Yen");



        monedas = new ArrayList<>();

        monedas.add(m1);
        monedas.add(m2);
        monedas.add(m3);
        monedas.add(m4);
        monedas.add(m5);
        monedas.add(m6);
        monedas.add(m7);
        monedas.add(m8);



    }

    public Moneda getMonedas(int valor) {
        return monedas.get(valor);
    }
}
