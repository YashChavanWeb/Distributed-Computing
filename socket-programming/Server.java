import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    public static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server is running on port 5000...");

        while (true) {
            Socket socket = serverSocket.accept();
            ClientHandler handler = new ClientHandler(socket);
            clients.add(handler);
            new Thread(handler).start();
        }
    }

    public static void broadcast(String msg, ClientHandler sender, boolean showInConsole) {
        if(showInConsole) System.out.println(msg);
        for (ClientHandler client : clients) {
            if (client != sender) client.out.println(msg);
        }
    }

    public static void sendPrivate(String targetName, String msg, ClientHandler sender) {
        boolean found = false;
        for (ClientHandler client : clients) {
            if (client.clientName.equalsIgnoreCase(targetName)) {
                client.out.println(msg);
                found = true;
                break;
            }
        }
        if (!found) sender.out.println("System: User @" + targetName + " not found.");
    }
}
