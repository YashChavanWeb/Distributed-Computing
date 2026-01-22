import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    public PrintWriter out;
    private BufferedReader in;
    public String clientName;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            this.clientName = in.readLine();
            Server.broadcast(clientName + " joined!", this, false);

            String message;
            while ((message = in.readLine()) != null) {
                if (message.startsWith("@")) {
                    // Private Message Logic: @Anjali Hello
                    int firstSpace = message.indexOf(" ");
                    if (firstSpace != -1) {
                        String targetName = message.substring(1, firstSpace);
                        String privateMsg = message.substring(firstSpace + 1);
                        Server.sendPrivate(targetName, "[Private] " + clientName + ": " + privateMsg, this);
                    }
                } else {
                    // Group Message Logic
                    Server.broadcast(clientName + ": " + message, this, true);
                }
            }
        } catch (IOException e) {
            System.out.println(clientName + " disconnected.");
        } finally {
            Server.clients.remove(this);
            Server.broadcast(clientName + " left.", this, false);
        }
    }
}
