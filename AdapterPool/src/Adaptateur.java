import java.lang.Runnable;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;

public class Adaptateur implements  Runnable
{
    private Capteur capteur;
    private long waitTime;

    public void setStub(IFilterRemote stub) {
        this.stub = stub;
    }

    private IFilterRemote stub;

    public Adaptateur(Capteur capteur)
    {
        this.capteur=capteur;
        double temp=(double)1/capteur.getFRQ();
        temp*=1000;
        this.waitTime=(long)temp;
    }

    @Override
    public void run() {
        while(true) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            Lecture lecture=read();
            System.out.println("lecture capteur " + capteur.getSID() + " presence:" +lecture.getValue()+" "+lecture.getTimestamp());
            capteur.updatePresence();
         /*   try {
                stub.f1(lecture);
            } catch (RemoteException e) {
                e.printStackTrace();
            }*/
            waiting();
        }
    }

    private synchronized void waiting()
    {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       /* try {

        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    private Lecture read()
    {
        long timestamp=Calendar.getInstance().getTime().getTime();
        return new Lecture(String.valueOf(capteur.getPresence()),timestamp);
    }
}
