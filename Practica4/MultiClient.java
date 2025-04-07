import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MultiClient {
    private String serverAddress;
    private int serverPort;
    private int clientId;

    public MultiClient(String serverAddress, int serverPort, int clientId) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.clientId = clientId;
    }

    public void start() {
        try (
            Socket socket = new Socket(serverAddress, serverPort);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            // Enviar el ID del cliente al servidor
            out.println(clientId);
            System.out.println("[Cliente " + clientId + "] Conectado al servidor en el puerto " + serverPort);

            String message;
            while (true) {
                System.out.print("[Cliente " + clientId + "] Escribe un mensaje (o 'salir' para desconectar): ");
                message = userInput.readLine();
                if (message.equalsIgnoreCase("salir")) break;
                out.println(message);
                System.out.println("[Cliente " + clientId + "] Respuesta del servidor: " + in.readLine());
            }
        } catch (IOException e) {
            System.out.println("[Cliente " + clientId + "] Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Solicitar al usuario el puerto del servidor al que quiere conectarse
            System.out.print("Ingrese la dirección del servidor (localhost si es local): ");
            String serverAddress = scanner.nextLine();

            System.out.print("Ingrese el puerto del servidor: ");
            int serverPort = scanner.nextInt();

            int clientId = (int) (Math.random() * 1000);  // Generar un ID único para cada cliente

            new MultiClient(serverAddress, serverPort, clientId).start();
        }
    }
}
