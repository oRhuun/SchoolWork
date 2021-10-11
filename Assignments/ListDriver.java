

public class ListDriver
{

	public static void main(String[] args)
	{
	
		MyLinkedList theList = new MyLinkedList();
		
		Node n1 = new Node("hello");
		theList.append(n1);
		
		System.out.println(theList);
		
		Node n2 = new Node("monday");
		theList.append(n2);
		Node n3 = new Node("tuesday");
		theList.append(n3);
		Node n4 = new Node("wednesday");
		theList.append(n4);
		Node n5 = new Node("thursday");
		theList.append(n5);
		Node n6 = new Node("friday");
		theList.append(n6);
		Node n7 = new Node("saturday");
		theList.append(n7);
		Node n8 = new Node("sunday");
		theList.append(n8);
		
		System.out.println(theList);
		
		Node n9 = new Node("this day is not real");
		theList.insert(n9, 4);
		System.out.println(theList);
		
		theList.delete(2);
		System.out.println(theList);
		
		theList.delete("saturday");
		System.out.println(theList);
		
		System.out.println(theList.getNode("wednesday"));
		System.out.println(theList);
	
	
	}
	
	
}