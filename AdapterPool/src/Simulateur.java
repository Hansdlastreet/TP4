import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulateur implements Runnable
{
    private final int PORT=5050;

    private final int periode=3000;

    private final String DIR=System.getProperty("user.dir")+"\\capteurs\\";
    private final List<String> readFiles;

    List<Capteur> capteurs;
    List<Adaptateur> adaptateurs;
    IFilterRemote stub;

    public Simulateur()
    {
        capteurs=new ArrayList<Capteur>();
        adaptateurs=new ArrayList<Adaptateur>();
        readFiles=new ArrayList<String>();
        getStub();
    }

    private void getStub()
    {
        IFilterRemote stub=null;
        try {
            Registry reg = LocateRegistry.getRegistry(5050);
            stub = (IFilterRemote) reg.lookup("Filters");
            stub.f1(new Lecture("x", 100));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(stub!=null)
        {
            this.stub=stub;
        }
    }

    public void initAdapters()
    {
        for (int i=0;i<capteurs.size();i++)
        {
            Capteur c=capteurs.get(i);
            Adaptateur adaptateur=new Adaptateur(c);
            adaptateur.setStub(stub);
            adaptateurs.add(adaptateur);
            Thread t=new Thread(adaptateur);
            t.start();
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
                if(!alreadyRead)
                {
                    lecture(file);
                    readFiles.add(name);
                }
            }
        }
        initAdapters();
    }

    private void lecture(File file)
    {
        Scanner sc=null;
        try {
            sc=new Scanner(file);
            int a =0;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(sc!=null) {
            String[] description = new String[3];
            for (int i = 0; i < 3; i++) {
                String line = sc.nextLine();
                String[] split = line.split(" ");
                description[i] = split[1];
            }
            String SID=description[0];
            int FREQ=Integer.parseInt(description[1]);
            String LNK=description[2];
            Capteur capteur=new Capteur(SID,FREQ,LNK);
            capteurs.add(capteur);
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
