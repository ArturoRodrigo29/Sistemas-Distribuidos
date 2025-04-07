import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    String sendMessage(int clientId, String clientMessage) throws RemoteException;
}
