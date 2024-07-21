package com.periferiaitgroup.utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivosJSON {

    private GestorArchivosJSON() {
        throw new IllegalStateException("Utility class");
    }

    public interface JsonElementProcessor {
        Object[] processElement(JsonElement element);
    }

    public static Object[][] leerArchivoJSON(String jsonFilePath, JsonElementProcessor processor) {
        try {
            // Lee el archivo JSON
            JsonElement jsonData = JsonParser.parseReader(new FileReader(jsonFilePath));
            JsonObject jsonObject = jsonData.getAsJsonObject();
            JsonArray dataArray = jsonObject.getAsJsonArray("data");

            // Inicializa una lista para almacenar los datos
            List<Object[]> dataList = new ArrayList<>();

            // Itera sobre los elementos del array y usa el processor para agregar los datos a la lista
            for (JsonElement element : dataArray) {
                dataList.add(processor.processElement(element));
            }

            // Convierte la lista en un array de arrays de objetos y devuélvelo
            return dataList.toArray(new Object[0][]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Devuelve un array vacío en lugar de null
        return new Object[0][];
    }

}
