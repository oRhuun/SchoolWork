import java.net.Socket;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.io.*;

public class HttpServer implements Runnable{
    // Use fileinputstreams
    public int port = 0;
    public static ServerSocket serv;
    public static Socket client;
    public static Boolean sMade = false;
    public PrintWriter out;

    public static void main(String[] args){
        HttpServer s = new HttpServer();
            if (args.length != 1 && !sMade)
            {
                System.out.println("Port set to default, 4433");
                System.out.println("Error: System expected port " +
                    "from command line, too many or few arguments");
                s.port = 4433;
            }
            else if (!sMade)
                s.port = Integer.parseInt(args[0]);
            try {
                if(!sMade)
                {
                    serv = new ServerSocket(s.port);
                    sMade = true;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        while(true)
        {
            try {
                client = serv.accept();
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
            s.run();
        }
    }

    public void run()
    {
        BufferedReader rec;
        try {
            rec = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            String comm = rec.readLine();
            String[] splitStr = comm.split(" ");
            if (splitStr[0].equals("PUT") || splitStr[0].equals("DELETE") || splitStr[0].equals("OPTIONS")
            || splitStr[0].equals("CONNECT") || splitStr[0].equals("TRACE") || splitStr[0].equals("POST") )
            {
                System.out.println("The command attempts to access an unsupported function");
                unsupported(splitStr);
                System.exit(-1);
            }
            else if(splitStr[0].equals("GET"))
            {
                System.out.println("Client asks for a file");
                get(splitStr);
            }
            else if (splitStr[0].equals("HEAD"))
            {
                System.out.println("Client asks for the head");
                head(splitStr);
            }
            else 
            {
                System.out.println("There was an issue understanding the command");
                badReq();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            out.close();
        }
        
    }

    public void get(String[] comm)
    {
        String fName = comm[1];
        File file = new File("public_html", fName); //assume directory is public_html
        Boolean held = locate(file, "public_html");
        if (held)
        {
            System.out.println("The working directory is " + System.getProperty("user.dir"));
            System.out.println("The path is: " + file.getAbsolutePath());
            try {
                String[] type = file.getName().split("\\.");
                byte[] cont = new byte[(int)file.length()];
                ByteBuffer  buf = ByteBuffer.allocate((int)file.length());
                BufferedOutputStream read = new BufferedOutputStream(client.getOutputStream());
                buf.put(cont);
                FileInputStream in = new FileInputStream(file);
                in.read(cont);
                in.close();
                String contType = "Unknown: " + type[type.length-1];
                if(type[type.length-1].equals("html") || type[type.length-1].equals("htm"))
                    contType = "text/html";
                if(type[type.length-1].equals("gif"))
                    contType = "image/gif";
                if(type[type.length-1].equals("jpeg") || type[type.length-1].equals("jpg"))
                    contType = "image/jpeg";
                if(type[type.length-1].equals("pdf"))
                    contType = "application/pdf";
                out.println();
                out.println("HTTP/1.1 200 OK \n");
                out.println("Server: ChrisServer/1.0 \n");
                out.println("Content-Length: " + file.length() + " \n");
                out.println("Content-Type: " + contType +  " \n");
                read.write(cont);
                read.close();
                System.out.print("Finished Writing");
                
            } catch (FileNotFoundException e)
            {
                notFound(comm);
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
                badReq();
            }
        }
        else
        {
            System.out.println("Access denied. Requested file not held within public directory.");
            notFound(comm);
        }
        out.flush();
        out.close();
    }

    public Boolean locate(File file, String dir)
    {
        File library = new File(dir);
        for (File f : library.listFiles())
        {
            System.out.println(f.getName());
            if(f.isDirectory())
                locate(file, f.getName());
            else   
                if (f.getName().equals(file.getName()));
                    return true;
        }
        return false;
    }

    // Called when the user calls an unsupported command. Anything not get or head.
    public void unsupported(String[] command)
    {
        System.out.println("System couldn't perform the unimplemented/unsupported request");
        out.print("HTTP/1.1 501 Not Implemented \n");
        out.print("Server: ChrisServer/1.0 \n");
        out.print("Content-Length: N/A \n");
        out.print("Content-Type: N/A \n");
        out.flush();
        out.close();
    }
    // Print out only the header, no message
    public void head(String[] command)
    {
        System.out.println("System returns header value");
        out.print("HTTP/1.1 200 OK \n");
        out.print("Server: ChrisServer/1.0 \n");
        out.print("Content-Length: N/A \n");
        out.print("Content-Type: N/A \n");
        out.flush();
        out.close();
    }
    //Called by the get method when a file isn't found
    public void notFound(String[] command)
    {
        System.out.println("System couldn't find file");
        out.print("HTTP/1.1 404 Not Found \n");
        out.print("Server: ChrisServer/1.0 \n");
        out.print("Content-Length: N/A \n");
        out.print("Content-Type: N/A \n");
        out.flush();
        out.close();
    }
    //The server can't understand the requested command
    public void badReq()
    {
        System.out.println("System couldn't process request");
        out.print("HTTP/1.1 400 Bad Request \n");
        out.print("Server: ChrisServer/1.0 \n");
        out.print("Content-Length: N/A \n");
        out.print("Content-Type: N/A \n");
        out.flush();
        out.close();
    }
}