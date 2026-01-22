import java.io.*;
import java.net.*;
import java.util.Properties;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
            String ip = prop.getProperty("server_ip");
            int port = Integer.parseInt(prop.getProperty("server_port"));

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = sc.nextLine();

            Socket socket = new Socket(ip, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(name);

            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg;
                    while ((msg = in.readLine()) != null) System.out.println("\n" + msg + "\n> ");
                } catch (IOException e) { }
            }).start();

            while (true) {
                System.out.print("> ");
                out.println(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Could not connect to server at " + prop.getProperty("server_ip"));
        }
    }
}
