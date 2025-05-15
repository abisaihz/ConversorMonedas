package com.alura.conversor.principal;

import com.alura.conversor.metodos.ListaConversion;
import com.alura.conversor.metodos.MonedaConversion;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
            claveApi + "/pair/";


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


    // Este Espacio es para pruebas
    public ConversorAPI(){
        System.out.println(claveApi);
        ListaConversion v1 = new ListaConversion();
        String seleccion = v1.getMonedas(1).getSimbolo();
        System.out.println(seleccion);
        System.out.println(direccion);

    }

    public ConversorAPI(int origen, int destino){

        // Aqui se buscan y asignan los valores a convertir
        ListaConversion v1 = new ListaConversion();
        String monedaOrigen = v1.getMonedas(origen).getSimbolo();
        String monedaDestino = v1.getMonedas(destino).getSimbolo();

        String direccionFinal = direccion + monedaOrigen + "/" + monedaDestino;

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

            String json = response.body();
            System.out.println(json);

            MonedaConversion miConversion = gson.fromJson(json, MonedaConversion.class);


        } catch (Exception e){
            System.out.println(e.getMessage());
        }



    }



}
