package org.example;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class BullBootServer {

    public BullBootServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            try (Socket client = serverSocket.accept()) {

                handleClient(client);
            }
        }
    }

    private void handleClient(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        String line = in.readLine();
        String[] header = line.split(" ");
        if (header.length > 2 && header[0].equals("GET")) {
            try {
                handleGetMethod(header[1], out);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }



        in.close();
        out.close();
    }

    private void handleGetMethod(String path, BufferedWriter out) throws InvocationTargetException, IllegalAccessException, IOException {
        Method methodForPath = findMethodForPath(path);
        String methodResponse =  (String) methodForPath.invoke(new Controller());

        createResponseHeader(out, methodResponse);
    }

    private void createResponseHeader(BufferedWriter out, String methodResponse) throws IOException {
        out.write("HTTP/1.1 " + 200 + "\r\n");
        out.write("Content-Length: " + methodResponse.length() + "\r\n");
        out.write("\r\n");
        out.write(methodResponse);
        out.flush();

    }

    private Method findMethodForPath(String path) {
        for (Method method : Controller.class.getMethods()) {
            if (method.isAnnotationPresent(GET.class)) {
                GET get = method.getAnnotation(GET.class);
                if (get.path().equals(path)){
                    return method;
                }
            }
        }

        return null;
    }



}
