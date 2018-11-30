import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main
{
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        try {
            Filters filters = new Filters();
            System.out.println();
            IFilterRemote stub = (IFilterRemote) UnicastRemoteObject.exportObject(filters,0);
            Registry reg = LocateRegistry.getRegistry(5050);
            reg.bind("Filters", stub);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
