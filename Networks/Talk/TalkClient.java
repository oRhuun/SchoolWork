import java.io.*;
import java.net.*;
public class TalkClient{
	public static void main(String[] args)	{
	//Create socket connection
	//System.out.println("Starting TalkClient");
	String serverName=args[0]; //First command line argument
	int serverPortNumber=Integer.parseInt(args[1]);
	String message=null;
	Socket socket = null;
	try{
		socket = new Socket(serverName, serverPortNumber);
		//Print local IP address and port number
		System.out.println("Local IP:"+socket.getLocalAddress().getHostAddress()+"  Local Port:"+socket.getLocalPort());
		//Print remote IP and port number
		System.out.println("Remote IP:"+socket.getInetAddress().getHostAddress()+"  Remote port:"+socket.getPort());
       		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
       		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	   while(true)
	   {
	   	message = in.readLine();
		out.println(message);	   }
     } catch (UnknownHostException e) {
       System.out.println("Unknown host:"+serverName);
       System.exit(1);
     } catch  (IOException e) {
       System.out.println("No I/O");
       System.exit(1);
     }
	finally {
		try {
			socket.close();
		}
		catch(Exception e) {
			
		}
	}
  }
}