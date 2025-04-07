import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Conectarse al registro RMI en localhost y puerto 1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Buscar el objeto remoto por nombre
            RemoteInterface stub = (RemoteInterface) registry.lookup("RemoteServer");

            // Generar un ID único basado en el tiempo (simulación)
            int clientId = (int) (Math.random() * 1000); 

            // Enviar mensajes al servidor
            Scanner scanner = new Scanner(System.in);
            String message;

            System.out.println("[Cliente " + clientId + "] Conectado al servidor. Escribe un mensaje ('salir' para cerrar):");
            while (true) {
                System.out.print("Tú: ");
                message = scanner.nextLine();
                if (message.equalsIgnoreCase("salir")) break;

                // Llamar al método remoto con el ID del cliente
                String response = stub.sendMessage(clientId, message);
                System.out.println("[Servidor] " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
