import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFilterRemote extends Remote
{
    public void f1(Lecture l) throws RemoteException;

    public void f2(Lecture l)throws RemoteException;

    public void noFilter(Lecture l)throws RemoteException;
}
