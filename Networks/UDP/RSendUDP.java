import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import edu.utulsa.unet.UDPSocket;
import edu.utulsa.unet.RSendUDPI;

class RSendUDP implements RSendUDPI{

    private int mode;
    private long swConst = 256;
    private long fSize = 0;
    private long time = 100;
    private String name;
    private int localPort = 54321;
    private final int recPort = 12345;
	private List<DatagramPacket> packs = new ArrayList<DatagramPacket>();
	private InetSocketAddress receiverIP;
    private final int headerSize = 12;

    public static void main(String[] args)
    {
		RSendUDP RSend = new RSendUDP();
		RSend.setFilename("text.txt");
		RSend.setMode(1);
		RSend.setLocalPort(54321);
		RSend.setTimeout(1000);
        RSend.setModeParameter(1000);
        InetSocketAddress r = new InetSocketAddress("localhost", 12345);
		RSend.setReceiver(r);
		RSend.sendFile();
    }

     
	public boolean setMode(int m) {
		if (m > 1 || m < 0)
		{
			System.out.println("Error, Mode values range from 0 (For Stop and Wait) to 1 (For Sliding Window)");
		}
		mode = m;
		return false;
	}

	 
	public int getMode() {
		return mode;
	}

	 
	public boolean setModeParameter(long n) { // Size of the window, only applicable for mode 1
		swConst = n;
		return false;
	}

	 
	public long getModeParameter() {
		return swConst;
	}

	 
	public void setFilename(String fname) {
		name = fname;
	}

	 
	public String getFilename() {
		return name;
	}

	 
	public boolean setTimeout(long timeout) { // in milliseconds
		time = timeout;
		return false;
	}

	 
	public long getTimeout() {
		return time;
	}

	 
	public boolean setLocalPort(int port) {
		localPort = port;
		System.out.println("Local port is " + localPort);
		return false;
	}

	 
	public int getLocalPort() {
		return localPort;
	}

	 
	public boolean setReceiver(InetSocketAddress receiver) {
		receiverIP = receiver;
		System.out.println(receiverIP);
		return false;
	}

	 
	public InetSocketAddress getReceiver() {
		return receiverIP;
	}

	 
	public boolean sendFile() {
		// take size of the elements in buffer and add them together, divide them by size to get the number of packets.
		if(localPort == 0)
		{
			localPort = recPort;
		}
		try {
            String modeName = "";
            if(mode==0){
                modeName = "Stop and Wait";
            }else if(mode==1){
                modeName = "Sliding Window";
            }
			System.out.println("Expecting connection with host from an IP of " + InetAddress.getLocalHost() + 
					" on port number " + localPort + " using the " + modeName + " file transfer method.");
		} catch (UnknownHostException e) {
			System.out.println("Issue receiving local IP");
		}
		try
		{
			UDPSocket socket = new UDPSocket(localPort);
			System.out.println("Socket established with connection to port " + socket.getPort());
			int size = socket.getSendBufferSize();
			int maxFrames = (int) Math.ceil(swConst/size);
			byte[] rBuffer = new byte[size];
			byte[] sBuffer;
			DatagramPacket send;
			DatagramPacket ack = new DatagramPacket(rBuffer, rBuffer.length);
			System.out.println("Attempting to get file with file name: " + name);
			byte[] file = Files.readAllBytes(Paths.get(name));
			System.out.println("File received");
			fSize = file.length;
			int packNum = (int) Math.ceil((double) fSize/((double) size - (double) headerSize));
			int Iter = 0;
			for (int i = 0; i < packNum; i++)
			{
				sBuffer = new byte[size];
				int datalen = 0;
				sBuffer[0] = (byte) ((i >> 24)); //place header into buffer
				sBuffer[1] = (byte) ((i >> 16));
				sBuffer[2] = (byte) ((i >> 8));
				sBuffer[3] = (byte) i;
				if (i + 1 < packNum)
				{
					datalen = size - 12;
				}
				else
				{
					datalen = (int) (file.length - ((packNum - 1)*(size - 12)));
				}
				sBuffer[4] = (byte) ((datalen >> 24)); //place header into buffer
				sBuffer[5] = (byte) ((datalen >> 16));
				sBuffer[6] = (byte) ((datalen >> 8));
				sBuffer[7] = (byte) ((datalen));
				sBuffer[8] = (byte) (packNum >> 24);
				sBuffer[9] = (byte) (packNum >> 16);
				sBuffer[10] = (byte) ((packNum >> 8));
				sBuffer[11] = (byte) ((packNum));
				for(int j = 12; j < datalen + 12; j++, Iter++)
				{
					sBuffer[j] = file[Iter];
				}
				send = new DatagramPacket(sBuffer, sBuffer.length, getReceiver());
				System.out.println("Attempting to add datagram packet");
				packs.add(send);
			}
			int packIterator = 0; // Where you are in the packet list
			int i = 0;
			if(mode == 0) // Stop and wait (same as Sliding window when sws=1)
			{
				maxFrames = 1;
			}
			else if (mode == 1) // Sliding window
			{
				maxFrames = (int) swConst/size;
				if(swConst%size != 0)
					maxFrames++;
			}
			else
			{
				System.out.println("Error, unrecognized mode: " + mode);
				socket.close();
				System.exit(-1);
			}
			long[][] timeOut = new long[(int) maxFrames][2]; // First value is time, second is packet position
			int j = 0;
			
			System.out.println("Attempting to connect");
			socket.connect(getReceiver());
			boolean finished = false;
			List<Integer> complete = new ArrayList<Integer>(0);
			while(i <= maxFrames && !finished) // While more frames can be sent
			{
				if (complete.size() == packs.size())
				{
					System.out.println("Receiver has received all messages. Program exiting.");
					finished = true;
					break;
				}
				else if(timeOut[(int) (j%maxFrames)][0] <= System.currentTimeMillis()
					 && timeOut[(int) (j%maxFrames)][0] != -1)
				{
					try {
						socket.setSoTimeout((int)time);
						socket.receive(ack);
						rBuffer = ack.getData();
						int rSeqNum = (int) ((rBuffer[0] & 0xFF) * Math.pow(2, 24) + (rBuffer[1] & 0xFF) * Math.pow(2, 16)
								+ (rBuffer[2] & 0xFF) * Math.pow(2, 8) + (rBuffer[3] & 0xFF));
						for(int k = 0; k < timeOut.length; k++)
						{
							if (rSeqNum == (int)timeOut[k][1])
							{
								timeOut[k][0] = -1;
							}
						}
						j++;
						if (!complete.contains(rSeqNum))
							complete.add(rSeqNum);
						i--;
						System.out.println("Receiver acknowledges package of sequence number: " + rSeqNum);
					} catch (IOException e) {
						if(!complete.contains((int) timeOut[j%maxFrames][1]))
						{
							System.out.println("Recipient took too long to respond, resending.");
							System.out.println("Sender sent message with sequence number " + timeOut[j%maxFrames][1] + " and a number of bytes of " + packs.get((int)timeOut[j%maxFrames][1]).getLength());
							socket.send(packs.get((int) timeOut[(int) (j%maxFrames)][1]));
							timeOut[j%maxFrames][0] = System.currentTimeMillis() + time;
						}
						else
						{
							timeOut[j%maxFrames][0] = -1;
						}
					}
					j++;
				}
				else if (packIterator < packs.size()) // As long as there are more packets
				{
					if (i < maxFrames) 
					{
						System.out.println("Sender attempting to send");
						socket.send(packs.get(packIterator));
						System.out.println("Sender sent message with sequence number " + packIterator + " and a number of bytes of " + packs.get(packIterator).getLength());
						timeOut[(int) (++j%maxFrames)][0] = System.currentTimeMillis() + time;
						timeOut[(int) (j%maxFrames)][1] = packIterator;
						packIterator++;
						i++;
					}
				}
				if(timeOut[j%maxFrames][0] == -1)
				{
					j++;
				}
			}
		} catch(IOException e) {
			System.out.println("Error connecting to socket");
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		return false;
	}
}