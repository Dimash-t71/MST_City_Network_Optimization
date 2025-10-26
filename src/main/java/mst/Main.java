package mst;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            // 1 Читаем входной JSON
            String inputData = Files.readString(Path.of("src/main/resources/ass_3_input.json"));
            JsonObject jsonObject = JsonParser.parseString(inputData).getAsJsonObject();
            JsonArray graphs = jsonObject.getAsJsonArray("graphs");

            JsonArray results = new JsonArray();

            //  2 Перебираем каждый граф
            for (JsonElement gElem : graphs) {
                JsonObject gObj = gElem.getAsJsonObject();
                Graph graph = new Graph();

                JsonArray edges = gObj.getAsJsonArray("edges");
                for (JsonElement e : edges) {
                    JsonObject eo = e.getAsJsonObject();
                    graph.addEdge(
                            eo.get("from").getAsString(),
                            eo.get("to").getAsString(),
                            eo.get("weight").getAsInt()
                    );
                }

                // 3 Запускаем алгоритмы
                MSTResult prim = PrimAlgorithm.run(graph);
                MSTResult kruskal = KruskalAlgorithm.run(graph);

                // 4 Формируем результат для JSON
                JsonObject output = new JsonObject();
                output.addProperty("graph_id", gObj.get("id").getAsInt());
                output.add("prim", gson.toJsonTree(prim));
                output.add("kruskal", gson.toJsonTree(kruskal));
                results.add(output);
            }

            // 5Записываем результаты в файл
            JsonObject finalOutput = new JsonObject();
            finalOutput.add("results", results);

            Files.writeString(
                    Path.of("src/main/resources/ass_3_output.json"),
                    gson.toJson(finalOutput)
            );

            System.out.println("✅ MST results written to src/main/resources/ass_3_output.json");

        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}