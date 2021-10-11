

public class TreeDriver
{

	public static void main(String[] args)
	{
	
		Tree myTree = new Tree();
		
		TreeNode n1 = new TreeNode(5);
		myTree.append(n1);
		System.out.println(myTree);
		
		TreeNode n2 = new TreeNode(3);
		myTree.append(n2);
		System.out.println(myTree);
		
		TreeNode n3 = new TreeNode(1);
		myTree.append(n3);
		System.out.println(myTree);
		
		TreeNode n4 = new TreeNode(7);
		myTree.append(n4);
		System.out.println(myTree);
		
		TreeNode n5 = new TreeNode(10);
		myTree.append(n5);
		System.out.println(myTree);
		
		TreeNode n6 = new TreeNode(6);
		myTree.append(n6);
		System.out.println(myTree);
		
		TreeNode n7 = new TreeNode(2);
		myTree.append(n7);
		System.out.println(myTree);
		
		TreeNode n8 = new TreeNode(13);
		myTree.append(n8);
		System.out.println(myTree);
		
		//System.out.println(myTree.printTree(n1));
	}
	
	
}