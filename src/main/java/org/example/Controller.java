package org.example;

public class Controller {
    @GET(path = "/hello")
    public String get() {
        return "Hello world!";
    }
}
