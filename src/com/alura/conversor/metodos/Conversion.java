package com.alura.conversor.metodos;

public class Conversion {


    private Moneda origenMoneda;
    private Moneda destinoMoneda;

    public Moneda getOrigenMoneda() {
        return origenMoneda;
    }

    public Moneda getDestinoMoneda() {
        return destinoMoneda;
    }

    public Conversion(int origenMoneda, int destinoMoneda) {


        ListaConversion v1 = new ListaConversion();
        this.origenMoneda = v1.getMonedas(origenMoneda);
        this.destinoMoneda = v1.getMonedas(destinoMoneda);



    }
}
