package client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpoint;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.websocket.ClientEndpointConfig.Configurator;

@ClientEndpoint
public class Client {
	private final String URL = "ws://localhost:8080/TP4Server";
	private final String S1 = "/service1";
	private final String S2 = "/service2";
	private final String S3 = "/service3";
	private Session session;
	private int idService;


	public Client(int idService)
	{
		this.idService=idService;
	}
	
	public void connect() throws DeploymentException, IOException, URISyntaxException {
		String url = URL;
		switch (idService) {
		case 1: {
			url += S1;
			break;
		}
		case 2: {
			url += S2;
			break;
		}
		case 3: {
			url += S3;
			break;
		}
		}
		ClientEndpointConfig conf = ClientEndpointConfig.Builder.create().configurator(new Configurator()).build();
		WebSocketContainer ws = ContainerProvider.getWebSocketContainer();
		ws.connectToServer(this, new URI(url));
	}

	public void callService() {
		try {
			session.getBasicRemote().sendText("getData");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig conf) {

		System.out.println("connected");
		this.session = session;
		session.addMessageHandler(new MessageHandler.Whole<String>() {

			@Override
			public void onMessage(String message) {
				System.out.println("message :" + message);
			}
		});
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {

	}

	@OnError
	public void onError(Session session, Throwable thr) {

	}

	public void closeConnexion() {
		try {
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
