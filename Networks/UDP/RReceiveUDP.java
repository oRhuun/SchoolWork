import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import edu.utulsa.unet.RReceiveUDPI;
import edu.utulsa.unet.UDPSocket;

public class RReceiveUDP implements RReceiveUDPI{
	
    public int mode; // 0 for stop and wait, 1 for sliding window
	public long swConst;
	public String name;
	public int localPort = 12345;
	public List<byte[]> packs = new ArrayList<byte[]>();
	public InetAddress senderIP;
	public int senderPort;
    public int bufferSize = 0;
    
	public static void main(String[] args)
	{
		RReceiveUDP rRec = new RReceiveUDP();
		rRec.setFilename("newtext2.txt");
		rRec.setMode(1);
		rRec.setLocalPort(12345);
		rRec.receiveFile();
	}

	 
	public boolean setMode(int m) {
		if (m > 1 || m < 0)
		{
			System.out.println("Error, Mode values range from 0 (For Stop and Wait) to 1 (For Sliding Window)");
			System.exit(-1);
		}
        mode = m;
		return false;
	}

	 
	public int getMode() {
		return mode;
	}

	 
	public boolean setModeParameter(long n) {
		if(mode == 1)
		{
			swConst = n;
		}
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

	 
	public boolean setLocalPort(int port) {
		localPort = port;
		return false;
	}

	 
	public int getLocalPort() {
		return localPort;
	}

	 
	public boolean receiveFile() {
		if(localPort == 0)
		{
			localPort = 12345;
		}
		try {
            String gModeName = "";
            if(mode==0){
                gModeName = "Stop and Wait";
            }else if(mode==1){
                gModeName = "Sliding Window";
            }
			System.out.println("Expecting connection with host IP " + InetAddress.getLocalHost() + 
					" on port " + localPort + " using " + gModeName + " file transfer method.");
		} catch (UnknownHostException e1) {
			System.out.println("Issue receiving local IP");
		}
		long startTime = System.currentTimeMillis();
		try {
			UDPSocket socket = new UDPSocket(localPort);
			bufferSize = socket.getSendBufferSize();
			byte[] rBuffer = new byte[socket.getSendBufferSize()];
            DatagramPacket rec = new DatagramPacket(rBuffer, rBuffer.length);
            
			List<Integer> collectedPacks = new ArrayList<Integer>(0);
			try {
				boolean hasNext = true;
				int packNum = 0;
				while(hasNext) {
					startTime = System.currentTimeMillis();
                    System.out.println("Processed " + collectedPacks.size() + " out of " + packNum + " packets");
                    
					socket.receive(rec);
					System.out.println("Connected to IP " + rec.getAddress() + " and port " + rec.getPort());
					senderIP = rec.getAddress();
					senderPort = rec.getPort();
					ByteBuffer rtemp = ByteBuffer.allocate(socket.getSendBufferSize());
					rtemp.put(rec.getData());
					int sequenceNumber = (int) ((rtemp.array()[0] & 0xFF) * Math.pow(2, 24) + (rtemp.array()[1] & 0xFF) * Math.pow(2, 16) + (rtemp.array()[2] & 0xFF) * Math.pow(2, 8) + (rtemp.array()[3] & 0xFF));
					int dataLength = (int) ((rtemp.array()[4] & 0xFF) * Math.pow(2, 24) + (rtemp.array()[5] & 0xFF) * Math.pow(2, 16) + (rtemp.array()[6] & 0xFF) * Math.pow(2, 8) + (rtemp.array()[7] & 0xFF));
					packNum = (int) ((rtemp.array()[8] & 0xFF) * Math.pow(2, 24) + (rtemp.array()[9] & 0xFF) * Math.pow(2, 16) + (rtemp.array()[10] & 0xFF) * Math.pow(2, 8) + (rtemp.array()[11] & 0xFF));
					System.out.println("Received message with sequence number " + sequenceNumber + " with " + dataLength + " bytes of data");
					if(collectedPacks.isEmpty() || !collectedPacks.contains(sequenceNumber))
					{
						collectedPacks.add(sequenceNumber);
						packs.add(rtemp.array());
					}
					if(packs.size() >= packNum)
						hasNext = false;
					byte[] ACK = new byte[4];
					ACK[0] = (byte) (sequenceNumber >> 24); //place header into buffer
					ACK[1] = (byte) (sequenceNumber >> 16);
					ACK[2] = (byte) ((sequenceNumber >> 8));
					ACK[3] = (byte) (sequenceNumber);
                    DatagramPacket send;
                    send = new DatagramPacket(ACK, ACK.length, senderIP, senderPort);
					socket.send(send);
				}
			} catch(IOException e) {
				System.out.println("Connection to host was unsuccessful\n" + e.getMessage());
			}
			System.out.println("Packets received, beginning file creation");
			List<ByteBuffer> temp = new ArrayList<ByteBuffer>(packs.size());
			for(int i = 0; i < packs.size(); i++)
			{
				temp.add(ByteBuffer.allocate(0));
			}
			for(int i = 0; i < packs.size(); i++)
			{
				int sequence = (int)((packs.get(i)[0] & 0xFF) * Math.pow(2, 24) + (packs.get(i)[1] & 0xFF) * Math.pow(2, 16) +  (packs.get(i)[2] & 0xFF) * Math.pow(2, 8) + (packs.get(i)[3] & 0xFF));
				int pSize = (int)((packs.get(i)[4] & 0xFF) * Math.pow(2, 24) + (packs.get(i)[5] & 0xFF) * Math.pow(2, 16) + (packs.get(i)[6] & 0xFF) * Math.pow(2, 8) + (packs.get(i)[7] & 0xFF));
				System.out.println("Packet size " + pSize + " and sequence number " + sequence);
				temp.set(sequence, ByteBuffer.allocate(pSize));
				temp.set(sequence, temp.get(sequence).put(packs.get(i), 12, pSize));
			}
			FileOutputStream newFile = new FileOutputStream(name);
			for (ByteBuffer buf : temp)
			{
				newFile.write(buf.array());
			}
			newFile.close();
			long finishTime = System.currentTimeMillis();
			long totalTime = finishTime - startTime;
			System.out.println("File transfer complete, system spent " + totalTime + " milliseconds on transfer");
			socket.close();
		} catch (IOException e) {
			System.out.println("Error connecting with socket\n" + e.getMessage());
		}
		return false;
	}
}