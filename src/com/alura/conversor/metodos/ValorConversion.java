package com.alura.conversor.metodos;

public class ValorConversion extends Conversion{

    private double monto;
    private double valor;

    public ValorConversion(int origenMoneda, int destinoMoneda, double factor, double monto) {
        super(origenMoneda, destinoMoneda);
        this.valor = factor * monto;
        this.monto = monto;
    }



    @Override
    public String toString() {
        return getOrigenMoneda().getDescripcion() + ": "+
                monto + " ---> " +
                getDestinoMoneda().getDescripcion() + ": " +
                valor;
    }

}
