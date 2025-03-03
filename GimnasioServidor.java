import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class GimnasioServidor {
    private static final int PUERTO = 5000;
    private static List<String> registrosEntrenamiento = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor de Gimnasio esperando clientes en el puerto " + PUERTO);
            
            while (true) {
                try (Socket socket = servidor.accept();
                     BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {
                    
                    System.out.println("Cliente conectado: " + socket.getInetAddress());
                    
                    // Leer el mensaje del cliente
                    String entrenamiento = entrada.readLine();
                    if (entrenamiento != null) {
                        registrosEntrenamiento.add(entrenamiento);
                        System.out.println("Entrenamiento registrado: " + entrenamiento);
                        salida.println("Rutina registrada con éxito.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}