//Christopher Schaefer 1523947 echrisschaef57@gmail.com
import java.io.*;
import java.net.*;

public class Talk {
	public static void main(final String[] args) throws Exception {
		if (args.length > 4 || args.length == 0) {
			throw new Exception("Invalid use of arguments");
		}
		switch (args[0]) {
			case "-h":
				System.out.println("talkClient");
				try{
					talkClient(args);
				}catch(Exception e){
					System.out.println("Couldn't get an inputStream from the server");
					System.exit(-1);
				}
				break;
			case "-s":
				System.out.println("talkServer");
				if (args.length > 3) {
					throw new Exception("Invalid use of arguments");
				}
				talkServer(args);
				break;
			case "-a":
				System.out.println("talkAuto");
				if (args.length > 4) {
					throw new Exception("Invalid use of arguments");
				}
				talkAuto(args);
				break;
			case "-help":
				if (args.length >= 2) {
					throw new Exception("Invalid use of arguments");
				}
				talkHelp();
				break;
			default:
				System.out.println("Invalid Entry, type \"Talk -help\" for help");
				break;
			//Parse the command line input to make a decision or throw an error
		}
	}

	public static void talkClient(final String[] args) throws Exception {
		System.out.println("Running talkClient");
		// Create socket connection
		// Find serverName and PortNumber in the commandline entry, otherwise set it to default or throw an error
		int serverPortNumber;
		String serverName;
		if(args.length == 4){
			if(args[2].equals("-p")){
				serverName = args[1];
				serverPortNumber = Integer.parseInt(args[3]);
			}else{
				throw new Exception("Invalid use of arguments");	}
		}else if(args.length == 3){
			if(args[1].equals("-p")){
				serverName = "localhost";
				serverPortNumber = Integer.parseInt(args[2]);
			}else{
				throw new Exception("Invalid use of arguments");	}
		}else if(args.length == 2){
			serverName = args[1];
			serverPortNumber = 12987;
		}else if(args.length == 1){
			serverName = "localhost";
			serverPortNumber = 12987;
		}else{
			throw new Exception("Invalid use of arguments");
		}
		Socket socket = null;
		BufferedReader instream = null;
		socket = new Socket(serverName, serverPortNumber);
		// Print local IP address and port number
		System.out.println(
				"Local IP:" + socket.getLocalAddress().getHostAddress() + "  Local Port:" + socket.getLocalPort());
		// Print remote IP and port number
		System.out.println(
				"Remote IP:" + socket.getInetAddress().getHostAddress() + "  Remote port:" + socket.getPort());
		final PrintWriter outstream = new PrintWriter(socket.getOutputStream(), true);
		try {
			//Get the incoming message
			instream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//Method that merges our connection messages
			talk(instream, outstream, socket);
		} catch (final IOException e) {
			System.out.println("Couldn't get an inputStream from the server");
			System.exit(-1);
		}
		//Finally close the socket
		try {
			socket.close();
		} catch (final Exception e) {

		}
		}
	
	public static void talkServer(final String[] args) throws Exception{
		System.out.println("Running talkServer");
		int serverPortNumber;
		BufferedReader instream = null;
		PrintWriter outstream = null;
		Socket client = null;
		ServerSocket server = null;
		//Find the port number in the command line arguments
		if (args.length > 1) {
			if(args[1].equals("-p")){
			   serverPortNumber = Integer.parseInt(args[2]);// First command-line argument
			}else{
				throw new Exception("Invalid use of arguments");
			}
	   	}else if(args.length == 1){
		   serverPortNumber = 12987;
	   	}else{
			throw new Exception("Invalid use of arguments");
	   	}
		try {
			server = new ServerSocket(serverPortNumber);
			// Print server IP and port it is listening to
			System.out.println("ServerSocket \nLocal IP:" + server.getInetAddress().getHostAddress() + "  Local Port:"
					+ server.getLocalPort());
		} catch (final IOException e) {
			System.out.println("Could not listen on port " + serverPortNumber);
			System.exit(-1);
		}
		try {
			client = server.accept();
			outstream = new PrintWriter(client.getOutputStream(), true);
			// Print client IP and port number
			System.out.println("Server accepted connection");
			System.out.println(
					"Local IP:" + client.getLocalAddress().getHostAddress() + " Local port:" + client.getLocalPort());
			System.out.println(
					"Remote IP: " + client.getInetAddress().getHostAddress() + "  Remote port:" + client.getPort());
		} catch (final IOException e) {
			System.out.println("Accept failed on port " + serverPortNumber);
			System.exit(-1);
		}
		try {
			//Get the incoming message
			instream = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//Method that merges our connection messages
			talk(instream, outstream, client);
		} catch (final IOException e) {
			System.out.println("Couldn't get an inputStream from the client");
			System.exit(-1);
		}
	}

	public static void talk(final BufferedReader instream, final PrintWriter outstream, Socket socket) throws IOException {
		//get the keyboard input
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		//always true loop
		while(true){ 
			if(input.ready()){
				String message = input.readLine();
				//special case for the input "STATUS" that gives info and doesn't send a message
				if(message.equals("STATUS")){
					System.out.println("Local IP:" + socket.getLocalAddress().getHostAddress() + " Local port:" + socket.getLocalPort());
					System.out.println("Remote IP: " + socket.getInetAddress().getHostAddress() + "  Remote port:" + socket.getPort());
				}//otherwise send the message
				else{
					outstream.println(message);
				}
			}//print the message from the instream whenever its ready
			if (instream.ready()) {
				System.out.println("[REMOTE] " + instream.readLine());
			}
		}
	}
		
	public static void talkAuto(String[] args) throws Exception {
		//try to connect to a server as a client first
		try{
			talkClient(args);
		}catch(Exception e){ //if that fails create the server searching for a client
			System.out.println("Switching to talkServer");
			talkServer(args);
		}
	}

	//display helpful information and how to use the program
	public static void  talkHelp(){
		System.out.println("talkHelp");
		System.out.println("Christopher Schaefer");
		System.out.println("Talk –h [hostname | IPaddress] [–p portnumber]");
		System.out.println("\tThe program behaves as a client connecting to [hostname | IPaddress] on port portnumber.\n\tIf a server is not available the program should exit with the message \"Client unable to communicate with server\".\n\tNote: portnumber in this case refers to the server and not to the client.");
		System.out.println("Talk –s [–p portnumber]");
		System.out.println("\tThe program behaves as a server listening for connections on port portnumber.\n\tIf the port is not available for use, the program should exit with the message \"Server unable to listen on specified port.\"");
		System.out.println("Talk –a [hostname|IPaddress] [–p portnumber]");
		System.out.println("\tThe program enters \"auto\" mode. When in auto mode, the program should start as a client attempting to communicate with hostname|IPaddress on port portnumber.\n\tIf a server is not found, the program should detect this condition and start behaving as a server listening for connections on port portnumber.");
		System.out.println("Talk –help");
		System.out.println("\tThe program prints the creator's name and instructions on how to use the program");
	}
}