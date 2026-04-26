import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        RecommendService service = new RecommendService();

        server.createContext("/", (exchange) -> {
            String path = exchange.getRequestURI().getPath();

            if (!path.equals("/")) {
                exchange.sendResponseHeaders(404, -1);
                return;
            }

            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                try {
                    File file = new File("index.html");
                    byte[] bytes = java.nio.file.Files.readAllBytes(file.toPath());

                    exchange.getResponseHeaders().add("Content-Type", "text/html");
                    exchange.sendResponseHeaders(200, bytes.length);

                    OutputStream os = exchange.getResponseBody();
                    os.write(bytes);
                    os.close();

                } catch (Exception e) {
                    e.printStackTrace();
                    String error = "Error loading index.html";
                    exchange.sendResponseHeaders(500, error.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(error.getBytes());
                    os.close();
                }
            }
        });

        server.createContext("/recommend", (exchange) -> {

            String path = exchange.getRequestURI().getPath();
            if (!path.equals("/recommend")) {
                exchange.sendResponseHeaders(404, -1);
                return;
            }

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                try {
                    InputStream is = exchange.getRequestBody();
                    String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

                    int budget = extractInt(body, "budget");
                    String fuel = extractString(body, "fuel");
                    String priority = extractString(body, "priority");

                    boolean budgetIgnored = (budget == 0);

                    List<String> recommendations = service.recommend(budget, fuel, priority);

                    String meta = budgetIgnored ? "\"note\":\"Budget not provided, showing best matches overall\"," : "";

                    String response = "{"
                            + meta
                            + "\"cars\":[" + String.join(",", recommendations) + "]"
                            + "}";

                    exchange.getResponseHeaders().add("Content-Type", "application/json");
                    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();

                } catch (Exception e) {
                    e.printStackTrace();
                    String error = "Error processing request";
                    exchange.sendResponseHeaders(500, error.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(error.getBytes());
                    os.close();
                }
            } else {
                exchange.sendResponseHeaders(405, -1); // Method not allowed
            }
        });

        server.start();
        System.out.println("Server running at http://localhost:8080");
    }

    // quick parsing helpers
    static int extractInt(String json, String key) {
        String val = extractString(json, key);
        try {
            return Integer.parseInt(val);
        } catch (Exception e) {
            return 0;
        }
    }

    static String extractString(String json, String key) {
        String pattern = "\"" + key + "\":";
        int start = json.indexOf(pattern) + pattern.length();

        while (json.charAt(start) == ' ' || json.charAt(start) == '\"') start++;

        int end = start;
        while (end < json.length() && json.charAt(end) != ',' && json.charAt(end) != '\"' && json.charAt(end) != '}') {
            end++;
        }

        return json.substring(start, end).replace("\"", "");
    }
}