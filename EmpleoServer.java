import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class EmpleoServer {

    public static void main(String[] args)
            throws Exception {

        HttpServer server =
                HttpServer.create(
                        new InetSocketAddress(8080),
                        0
                );

        server.createContext(
                "/api/save",
                EmpleoServer::saveResume
        );

        server.start();

        System.out.println(
                "Empleo Java Server started at http://localhost:8080"
        );
    }

    private static void saveResume(
            HttpExchange exchange
    ) throws IOException {

        exchange.getResponseHeaders()
                .add("Access-Control-Allow-Origin", "*");

        if (!exchange.getRequestMethod()
                .equalsIgnoreCase("POST")) {

            sendResponse(
                    exchange,
                    "Only POST requests are allowed."
            );

            return;
        }

        InputStream input =
                exchange.getRequestBody();

        String body =
                new String(
                        input.readAllBytes(),
                        StandardCharsets.UTF_8
                );

        Map<String, String> data =
                parseFormData(body);

        try {

            Resume resume =
                    new Resume(
                            data.get("name"),
                            data.get("email"),
                            data.get("phone"),
                            data.get("location"),
                            data.get("linkedin"),
                            data.get("github"),
                            data.get("summary"),
                            data.get("education"),
                            data.get("skills"),
                            data.get("projects"),
                            data.get("experience"),
                            data.get("certifications"),
                            data.get("achievements")
                    );

            ResumeController.saveResume(resume);

            sendResponse(
                    exchange,
                    "Resume saved successfully to MySQL!"
            );

        } catch (Exception e) {

            e.printStackTrace();

            sendResponse(
                    exchange,
                    "Database error: " + e.getMessage()
            );
        }
    }

    private static Map<String, String>
    parseFormData(String body) {

        Map<String, String> data =
                new HashMap<>();

        String[] pairs =
                body.split("&");

        for (String pair : pairs) {

            String[] parts =
                    pair.split("=", 2);

            if (parts.length == 2) {

                String key =
                        URLDecoder.decode(
                                parts[0],
                                StandardCharsets.UTF_8
                        );

                String value =
                        URLDecoder.decode(
                                parts[1],
                                StandardCharsets.UTF_8
                        );

                data.put(key, value);
            }
        }

        return data;
    }

    private static void sendResponse(
            HttpExchange exchange,
            String response
    ) throws IOException {

        byte[] bytes =
                response.getBytes(
                        StandardCharsets.UTF_8
                );

        exchange.sendResponseHeaders(
                200,
                bytes.length
        );

        OutputStream output =
                exchange.getResponseBody();

        output.write(bytes);

        output.close();
    }
}