package AdapterExecutionPool;

import java.lang.Runnable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Filters.Filters;

public class Adaptateur implements  Runnable
{
    private Capteur capteur;
    private long waitTime;
    int serviceId;


    public Adaptateur(Capteur capteur)
    {
        this.capteur=capteur;
        this.serviceId=capteur.getServiceId();
        double temp=(double)1/capteur.getFRQ();
        temp*=1000;
        this.waitTime=(long)temp;
    }

    @Override
    public void run() {
        while(true) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            Lecture lecture=read();
            //System.out.println("lecture capteur " + capteur.getSID() + " presence:" +lecture.getValue()+" "+lecture.getTimestamp());
            Filters.forwarding(lecture);
            waiting();
        }
    }

    public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
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
        return new Lecture(String.valueOf(capteur.getPresence()),timestamp,this.serviceId);
    }
}
