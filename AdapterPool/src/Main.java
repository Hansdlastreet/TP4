import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main
{
    public static void main(String[] args)
    {
        Simulateur sim=new Simulateur();
        Thread t=new Thread(sim);
        t.start();

    }

}
