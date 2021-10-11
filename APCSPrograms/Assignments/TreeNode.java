

public class TreeNode
{

	private int information;
	private TreeNode right;
	private TreeNode left;
	
	public TreeNode(int info)
	{
		information = info;
		right = null;
		left = null;
	}
	
	public int getInformation()
	{
		return information;
	}
	
	public TreeNode getRight()
	{
		return right;
	}
	
	public TreeNode getLeft()
	{
		return left;
	}
	
	public TreeNode setRight(TreeNode r)
	{
		right = r;
		return right;
	}
	
	public TreeNode setLeft(TreeNode l)
	{
		left = l;
		return left;
	}
	
	//uncommenting this will return the string with the information in it
	public String toString()
	{
		return information + " ";
	}
	
}