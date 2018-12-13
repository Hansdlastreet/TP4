package client;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.ClientEndpointConfig.Configurator;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

public class main {

	public static void main(String[] args) throws DeploymentException, IOException, URISyntaxException {
		System.out.println("quel service voulez vous appeller?");
		Scanner sc=new Scanner(System.in);
		String entree=sc.nextLine();
		int idService=Integer.parseInt(entree);
		Client c=new Client(idService);
		c.connect();
		while(true)
		{
			c.callService();
		}
	}

}
