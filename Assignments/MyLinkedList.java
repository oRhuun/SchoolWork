

public class MyLinkedList
{

	private Node start;
	
	public MyLinkedList()
	{
		start = null;
	}
	
	public void insert(Node n, int i)
	{
		int count = 0;
		Node tracker;
		tracker = start;
		Node prevNode;
		
		if(start == null)   					// no Nodes in the list 
			append(n);
		else
			if(i == 0)
				n.setNext(start);
			else
			{
				while(tracker.getNext() != null){ // there is more than one Node in the list
					prevNode = tracker;
					tracker = tracker.getNext();
					count++;
					if(count == i){
						n.setNext(tracker);
						prevNode.setNext(n);
					}
				}
			}
	
	}
	
	public void append(Node n)
	{
		Node tracker;
		tracker = start;
		
		if(start == null)   					// no Nodes in the list 
			start = n;
		else
			if(start.getNext() == null)		// there is one Node in the list
				start.setNext(n);
			else
			{
				while(tracker.getNext() != null) // there is more than one Node in the list
					tracker = tracker.getNext();
				tracker.setNext(n);
			}
			
	}
		
	
	public void delete(String s)
	{
		Node tracker;
		tracker = start;
		Node prevNode;
		
		if(start != null){					// no Nodes in the list 
			while(tracker.getNext() != null){
				prevNode = tracker;
				tracker = tracker.getNext();
				if(s.equals(tracker.getInformation()))
					prevNode.setNext(tracker.getNext());
			}
		}
	}
	
					
					
	public void delete(int i)
	{
		int count = 0;
		Node tracker;
		tracker = start;
		Node prevNode;
		
		if(start != null){					// no Nodes in the list 
			while(tracker.getNext() != null){ // there is more than one Node in the list
				prevNode = tracker;
				tracker = tracker.getNext();
				count++;
				if(count == i)
					prevNode.setNext(tracker.getNext());
			}
		}
	}
	
	public Node getNode(String s)
	{
		Node tracker;
		tracker = start;
		if(start == null)   					// no Nodes in the list 
			return null;
		else{
			while(tracker.getNext() != null){
				if(s.equals(tracker.getInformation()))
					return tracker;
				else
					tracker = tracker.getNext();
			}
		}
		return null;
	}
	
	public String toString() {
		Node tracker;
		tracker = start;
		String output = "";
		while(tracker.getNext() != null){
			output += tracker.getInformation() + "\n";
			tracker = tracker.getNext();
		}
		output += tracker.getInformation() + "\n";
		return output;
	}

}