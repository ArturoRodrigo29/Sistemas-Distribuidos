import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MultiServer {
    private int port;
    private int serverId;
    private ExecutorService pool;

    public MultiServer(int port, int serverId) {
        this.port = port;
        this.serverId = serverId;
        this.pool = Executors.newFixedThreadPool(5); // Permite hasta 5 clientes simultáneos
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("[Servidor " + serverId + "] Iniciado en el puerto: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ClientHandler(clientSocket, serverId));
            }
        } catch (IOException e) {
            System.out.println("[Servidor " + serverId + "] Error: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private int serverId;

        public ClientHandler(Socket socket, int serverId) {
            this.clientSocket = socket;
            this.serverId = serverId;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String clientId = in.readLine();
                System.out.println("[Servidor " + serverId + "] Cliente " + clientId + " conectado");

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("[Servidor " + serverId + "] Cliente " + clientId + " dice: " + message);
                    out.println("[Servidor " + serverId + "] Mensaje recibido: " + message);
                }
            } catch (IOException e) {
                System.out.println("[Servidor " + serverId + "] Error con cliente: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java MultiServer <puerto> <id_servidor>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        int serverId = Integer.parseInt(args[1]);

        new MultiServer(port, serverId).start();
    }
}
