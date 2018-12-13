package Filters;
import AdapterExecutionPool.Lecture;
import Server.Service1;
import Server.Service2;
import Server.Service3;

public class Filters{
	
	private static Lecture[] lastLecture={null,null,null};
	
	public synchronized static void forwarding(Lecture l)
	{
		switch(l.getServiceId())
		{
		case 1:
		{
			toService1(l);
			break;
		}
		case 2:
		{
			toService2(l);
			break;
		}
		case 3:
		{
			toService3(l);
			break;
		}
		}
	}
	
	private static void toService1(Lecture l)
	{
		Service1.setData(l);
	}
	
	private static void toService2(Lecture l)
	{
		l=f2(f1(l));
		Service2.setData(l);
	}
	
	private static void toService3(Lecture l)
	{	
		l=f1(l);
		Service3.setData(l);
	}
	
    public static Lecture f1(Lecture l)
    {
    	int v=Integer.parseInt(l.getValue());
    	String data="";
    	if(v==1)
    	{
    		data="D";
    	}
    	else if(v==0)
    	{
    		data="N";
    	}
    	l.setValue(data);
    	return l;
    }

    public static Lecture f2(Lecture l) {
    	int i=l.getServiceId()-1;
    	if(lastLecture[i]!=null)
    	{
    		Lecture lastL=lastLecture[i];
    		if(lastL.getValue().equals(l.getValue()))
    		{
    			return lastL;
    		}
    		else
    		{
    			lastLecture[i]=l;
    			return l;
    		}
    	}
    	else
    	{
    		lastLecture[i]=l;
    		return l;
    	}
    }
}

