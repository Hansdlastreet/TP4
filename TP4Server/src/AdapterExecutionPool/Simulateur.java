package AdapterExecutionPool;

import AdapterExecutionPool.Adaptateur;
import AdapterExecutionPool.Capteur;
import AdapterExecutionPool.Lecture;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulateur implements Runnable
{
    private final int periode=3000;

    private final String DIR=System.getProperty("user.dir")+"\\capteurs\\";
    private final List<String> readFiles;

    List<Capteur> capteurs;
    List<Adaptateur> adaptateurs;

    public Simulateur()
    {
    	System.out.println("simulateur constructor");
        capteurs=new ArrayList<Capteur>();
        adaptateurs=new ArrayList<Adaptateur>();
        readFiles=new ArrayList<String>();
    }


    private void initAdapter(Capteur c)
    {
            Adaptateur adaptateur=new Adaptateur(c);
            adaptateurs.add(adaptateur);
            Thread t=new Thread(adaptateur);
            t.start();
    }
    
    private void updateCapteur(Capteur c)
    {
    	for(int i=0;i<capteurs.size();i++)
    	{
    		Capteur curr=capteurs.get(i);
    		if(curr.getSID().equals(c.getSID()))
    		{
    			curr.setPresence(c.getPresence());
    		}
    	}
    }

    private void lecturePeriodique()
    {
        File curDir=new File(DIR);
        File[] filesList=curDir.listFiles();
        for (int i=0;i<filesList.length;i++)
        {
            File file=filesList[i];
            if(file.isFile())
            {
                String name=file.getName();
                Boolean alreadyRead=false;
                for(int j=0;j<readFiles.size();j++)
                {

                    if(readFiles.get(j).equals(name))
                    {
                       alreadyRead=true;
                       
                    }
                }
                Capteur c=lecture(file);
                if(!alreadyRead)
                {
                    capteurs.add(c);
                    initAdapter(c);
                    readFiles.add(name);
                }
                else
                {
                	updateCapteur(c);
                }
            }
        }
        @SuppressWarnings("unused")
		int a =0;
    }

    private Capteur lecture(File file)
    {
        Scanner sc=null;
        try {
            sc=new Scanner(file);
            int a =0;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(sc!=null) {
            String[] description = new String[5];
            for (int i = 0; i < 5; i++) {
                String line = sc.nextLine();
                String[] split = line.split(" ");
                description[i] = split[1];
            }
            String SID=description[0];
            int FREQ=Integer.parseInt(description[1]);
            String LNK=description[2];
            int value=Integer.parseInt(description[3]);
            int serviceId=Integer.parseInt(description[4]);
            Capteur capteur=new Capteur(SID,FREQ,LNK,value,serviceId);
            return capteur;
        }
        else
        {
        	return null;
        }
    }

    @Override
    public void run() {
        while (true)
        {
            lecturePeriodique();
            try {
                Thread.sleep(periode);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
