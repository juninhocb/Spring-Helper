import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(1225);
        Socket listener = socket.accept();
        System.out.println("bloqueante...");
        PrintWriter writer = new PrintWriter(listener.getOutputStream(), true);
        String serverResponse = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nHello, client!";
        writer.println(serverResponse);


        writer.close();
        listener.close();
        socket.close();
    }

}
