package AdapterExecutionPool;

import java.util.Random;

public class Capteur
{
    private String SID;
    private int  FRQ;
    private String  LNK;
    private int presence;
    private int serviceId;

    public int getServiceId() {
		return serviceId;
	}


	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}


	public Capteur(String SID, int FRQ, String LNK,int presence,int serviceId) {
        this.SID = SID;
        this.FRQ = FRQ;
        this.LNK = LNK;
        this.presence=presence;
        this.serviceId=serviceId;
    }


    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public int getFRQ() {
        return FRQ;
    }

    public void setFRQ(int FRQ) {
        this.FRQ = FRQ;
    }

    public String getLNK() {
        return LNK;
    }

    public void setLNK(String LNK) {
        this.LNK = LNK;
    }

    public int getPresence() {
        return presence;
    }

    /*public void updatePresence()
    {
        generateValue();
    }*/

    public void setPresence(int presence) {
        this.presence = presence;
    }

    /*private void generateValue()
    {
        Random r=new Random();
        int value=r.nextInt(2);
        this.presence=value;
    //    System.out.println("Nouvelle valeur "+ value+ " assignee au capteur"+SID);
    }*/
}
