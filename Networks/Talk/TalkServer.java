import java.io.*;
import java.net.*;
public class TalkServer
{
	public static void main(String [] args)
	{
	System.out.println("Starting TalkServer");
	BufferedReader inS=null;
	BufferedReader outS=null;
	int serverPortNumber=Integer.parseInt(args[0]);//First command-line argument
	String message=null;
	Socket client=null;
	ServerSocket server=null;
	try{
		server= new ServerSocket(serverPortNumber);
		//Print server IP and port it is listening to
		System.out.println("ServerSocket \nLocal IP:"+server.getInetAddress().getHostAddress()+"  Local Port:"+server.getLocalPort());
	}
	catch (IOException e)
	{
		System.out.println("Could not listen on port "+serverPortNumber);
		System.exit(-1);
	}
	try{
		client=server.accept();
		//Print client IP and port number
		System.out.println("Server accepted connection");
		System.out.println("Local IP:"+client.getLocalAddress().getHostAddress()+" Local port:"+client.getLocalPort());
		System.out.println("Remote IP: "+client.getInetAddress().getHostAddress()+"  Remote port:"+client.getPort());
		}
	catch (IOException e)
	{
		System.out.println("Accept failed on port "+ serverPortNumber);
		System.exit(-1);
	}
	try{
		inS = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}
	catch (IOException e)
	{
		System.out.println("Couldn't get an inputStream from the client");
		System.exit(-1);
	}
	try{
		while(true){
		 if (inS.ready()) {
			message=inS.readLine();
			System.out.println(message);}
		}
	}
	catch (IOException e) {
		System.out.println("Read failed");
		System.exit(-1);
	}
}
}