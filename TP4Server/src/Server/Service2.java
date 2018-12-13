package Server; 

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import AdapterExecutionPool.Lecture;

@ServerEndpoint("/service2")
public class Service2
{private static String response;

public static void setData(Lecture l)
{
	response="Service2 "+l.getValue()+" "+l.getTimestamp();
}

@OnOpen
public void open(Session session) {
	System.out.println("connected");
	
}

@OnClose
public void close(Session session) {

}

@OnError
public void error(Session session, Throwable t) {

}

@OnMessage
public void handleMessage(String message, Session session) {
	if(message.equals("getData"))
	{
		try {
			session.getBasicRemote().sendText(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

}