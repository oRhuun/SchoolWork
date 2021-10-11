

public class Node
{

	private String information;
	private Node next;
	
	public Node(String info)
	{
		information = info;
		next = null;
	}
	
	public String getInformation()
	{
		return information;
	}
	
	public Node getNext()
	{
		return next;
	}
	
	public Node setNext(Node n)
	{
		next = n;
		return next;
	}
	
	//uncommenting this will return the string with the information in it
	//public String toString()
	//{
	//	return "This node contains: " + information;
	//}
	
}