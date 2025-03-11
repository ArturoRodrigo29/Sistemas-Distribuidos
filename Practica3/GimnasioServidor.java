
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
                Socket socket = servidor.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());
                new Thread(new ClienteHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClienteHandler implements Runnable {
        private Socket socket;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {
                
                while (true) {
                    String entrenamiento = entrada.readLine();
                    if (entrenamiento == null || entrenamiento.equalsIgnoreCase("salir")) {
                        System.out.println("Cliente desconectado: " + socket.getInetAddress());
                        break;
                    }
                    synchronized (registrosEntrenamiento) {
                        registrosEntrenamiento.add(entrenamiento);
                    }
                    System.out.println("Entrenamiento registrado: " + entrenamiento);
                    salida.println("Rutina registrada con éxito.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


