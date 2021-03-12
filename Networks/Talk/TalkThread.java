/*
    THIS IMPLEMENTATION DOES NOT WORK.
*/
import java.io.*;
import java.net.*;
import java.lang.Exception;
import java.lang.Runnable;
import java.lang.Thread;

class Receiver implements Runnable {
	private BufferedReader instream;

	public Receiver(BufferedReader instream) {
		this.instream = instream;
	}

	public void run() {
		try{
			while(true){
				System.out.println("[REMOTE] " + instream.readLine());
			}
		}catch(Exception e){

		}
	}
}

class Sender implements Runnable {
	private PrintWriter outstream;
	private Socket socket;
	BufferedReader input;
	String message;

	public Sender(PrintWriter outstream, Socket socket) {
		this.outstream = outstream;
		this.socket = socket;
		
	}

	public void run() {
		try{
			while(true){
				message = input.readLine();
				if(message.equals("STATUS")){
					System.out.println("Local IP:" + socket.getLocalAddress().getHostAddress() + " Local port:" + socket.getLocalPort());
					System.out.println("Remote IP: " + socket.getInetAddress().getHostAddress() + "  Remote port:" + socket.getPort());
				}
				else{
					outstream.println(message);
				}
			}
		}catch(Exception e){

		}
	}
}

public class TalkThread {
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

		}
	}

	public static void talkClient(final String[] args) throws Exception {
		System.out.println("Running talkClient");
		// Create socket connection
		int serverPortNumber = 12987;
		String serverName = "localhost";
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

		instream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		talk(instream, outstream, socket);

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
			instream = new BufferedReader(new InputStreamReader(client.getInputStream()));
			talk(instream, outstream, client);
		} catch (final IOException e) {
			System.out.println("Couldn't get an inputStream from the client");
			System.exit(-1);
		}
	}

	public static void talk(final BufferedReader instream, final PrintWriter outstream, Socket socket) throws IOException {
		new Thread(new Receiver(instream)).start();
		new Thread(new Sender(outstream,socket)).start();
	}
		
	public static void talkAuto(String[] args) throws Exception {
		try{
			talkClient(args);
		}catch(Exception e){
			System.out.println("Switching to talkServer");
			talkServer(args);
		}
	}

	public static void  talkHelp(){
		System.out.println("talkHelp");
		System.out.println("Christopher Schaefer");
		System.out.println("java Talk –h [hostname | IPaddress] [–p portnumber]");
		System.out.println("\tThe program behaves as a client connecting to [hostname | IPaddress] on port portnumber.\n\tIf a server is not available the program should exit with the message \"Client unable to communicate with server\".\n\tNote: portnumber in this case refers to the server and not to the client.");
		System.out.println("java Talk –s [–p portnumber]");
		System.out.println("\tThe program behaves as a server listening for connections on port portnumber.\n\tIf the port is not available for use, the program should exit with the message \"Server unable to listen on specified port.\"");
		System.out.println("java Talk –a [hostname|IPaddress] [–p portnumber]");
		System.out.println("\tThe program enters \"auto\" mode. When in auto mode, the program should start as a client attempting to communicate with hostname|IPaddress on port portnumber.\n\tIf a server is not found, the program should detect this condition and start behaving as a server listening for connections on port portnumber.");
		System.out.println("java Talk –help");
		System.out.println("\tThe program prints the creator's name and instructions on how to use the program");
	}
}
