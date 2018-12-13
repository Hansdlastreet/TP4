package Server;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import AdapterExecutionPool.Lecture;

@ServerEndpoint("/service1")
public class Service1 {
	private static String response;

	public static void setData(Lecture l)
	{
		response="Service1 "+l.getValue()+" "+l.getTimestamp();
		System.out.println(response);
	}

	@OnOpen
	public void open(Session session) {

	}

	@OnClose
	public void close(Session session) {

	}

	@OnError
	public void error(Session session, Throwable t) {

	}

	@OnMessage
	public void handleMessage(String message, Session session) {

	}
}
