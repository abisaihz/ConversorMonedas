package com.alura.conversor.principal;

import com.alura.conversor.metodos.Conversion;
import com.alura.conversor.metodos.ListaConversion;
import com.google.gson.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ConversorAPI {


    private String claveApi = connectionFile();
    private String direccion = "https://v6.exchangerate-api.com/v6/" +
            claveApi + "/latest/";
    private double factorConversion;

    public double getFactorConversion() {
        return factorConversion;
    }



    // Aqui se asigna el APIKEY, este se excluye a traves del ignore
    // Se puede asignar uno diferente manualmente

    public String connectionFile() {
        Properties properties = new Properties();

        try {
            FileInputStream fileConfig = new FileInputStream("config.properties");
            properties.load(fileConfig);
            return properties.getProperty("API_KEY");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Capturar una nueva API");
            return "Cambiar API Nueva Aqui";

        }
    }


    public ConversorAPI(Conversion operacionUsuario){

        // Aqui se buscan y asignan los valores a convertir

        String monedaOrigen = operacionUsuario.getOrigenMoneda().getSimbolo();
        String monedaDestino = operacionUsuario.getDestinoMoneda().getSimbolo();

        String direccionFinal = direccion + monedaOrigen;

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccionFinal))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonElement elemento = JsonParser.parseString(response.body());
            JsonObject objectRoot = elemento.getAsJsonObject();

            JsonObject rates = objectRoot.getAsJsonObject("conversion_rates");

            // Aqui se asigna el valor obtenido a la variable
            this.factorConversion = rates.get(monedaDestino).getAsDouble();




        } catch (Exception e){
            System.out.println(e.getMessage());
            this.factorConversion = 0;

        }



    }



}
