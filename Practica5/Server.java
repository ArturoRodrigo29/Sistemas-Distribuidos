import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements RemoteInterface {
    private AtomicInteger clientCounter = new AtomicInteger(0);  // Contador de clientes

    @Override
    public String sendMessage(int clientId, String clientMessage) throws RemoteException {
        System.out.println("[Servidor] Cliente " + clientId + " dice: " + clientMessage);
        return "Cliente " + clientId + " Respuesta del servidor: " + clientMessage.toUpperCase();
    }

    public static void main(String[] args) {
        try {
            // Crear instancia del servidor
            Server server = new Server();

            // Exportar el objeto remoto
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(server, 0);

            // Crear el registro RMI en el puerto 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Publicar el objeto remoto con el nombre "RemoteServer"
            registry.rebind("RemoteServer", stub);

            System.out.println("[Servidor] Servidor RMI listo en el puerto 1099.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
