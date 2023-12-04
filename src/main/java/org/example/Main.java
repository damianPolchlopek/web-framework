package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");


        BullBootServer bullBootServer = new BullBootServer();





//        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
//        httpServer.createContext("/hello", new HttpHandler() {
//            @Override
//            public void handle(HttpExchange exchange) throws IOException {
//                exchange.sendResponseHeaders(200, 0);
//                OutputStream outputStream = exchange.getResponseBody();
//                outputStream.write("Hello! ".getBytes());
//                outputStream.close();
//
//
//            }
//        });
//
//        httpServer.start();



    }
}