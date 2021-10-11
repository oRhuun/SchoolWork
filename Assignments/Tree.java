

public class Tree
{

	private TreeNode start;
	
	public Tree()
	{
		start = null;
	}
	
	public void append(TreeNode n)
	{
		TreeNode tracker;
		tracker = start;
		boolean placed = false;
		
		if(start == null)   					// no Nodes in the list 
			start = n;
		else{
			while(!placed){
				if(n.getInformation() < tracker.getInformation()){
					if(tracker.getLeft() == null){
						tracker.setLeft(n);
						placed = true;
					}
					else
						tracker = tracker.getLeft();
				}
				if(n.getInformation() > tracker.getInformation()){
					if(tracker.getRight() == null){
						tracker.setRight(n);
						placed = true;
					}
					else
						tracker = tracker.getRight();
				}
			}
		
		}
			
	}

	/*public Node getNode(String s)
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
	*/
	
	public String printTree(TreeNode tracker) {
		String output = "";
		
		if(tracker.getLeft() != null)
			output += printTree(tracker.getLeft());
			
		output += tracker;
		
		if(tracker.getRight() != null)
			output += printTree(tracker.getRight());
		
		return output;
	}
	
	public String toString(){
		return printTree(start);
	}

}