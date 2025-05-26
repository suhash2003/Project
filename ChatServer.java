ChatServer.java

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<ClientHandler> clientHandlers = ConcurrentHashMap.newKeySet();

    public static void main(String[] args) {
        System.out.println("Chat server started...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandlers.add(clientHandler);

                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clientHandlers) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    static void removeClient(ClientHandler client) {
        clientHandlers.remove(client);
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("Enter your name: ");
                clientName = in.readLine();
                broadcast(clientName + " joined the chat.", this);
                System.out.println(clientName + " connected.");

                String message;
                while ((message = in.readLine()) != null) {
                    broadcast(clientName + ": " + message, this);
                }
            } catch (IOException e) {
                System.out.println("Client error: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException ignored) {}
                removeClient(this);
                broadcast(clientName + " left the chat.", this);
                System.out.println(clientName + " disconnected.");
            }
        }

        void sendMessage(String message) {
            out.println(message);
        }
    }
} 

