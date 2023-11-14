import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class QTree
{
	private class Node
	{
		Node left;
		Node right;
		String val;
		Boolean is_Question;
		
		public Node(QTree.Node left, QTree.Node right, String val, Boolean is_Question) {
			super();
			this.left = left;
			this.right = right;
			this.val = val;
			this.is_Question = is_Question;
		}
		
		
	}
	
	Scanner in;
	PrintStream out;
	Node root;
	
    //initializes the game
	public QTree(InputStream in,PrintStream out)
	{
		this.out=out;
		this.in=new Scanner(in);
		Node rock = new Node(null, null, Strings.ROCK, false);
		Node duck = new Node(null, null, Strings.DUCK, false);
		
		root = new Node(rock, duck, Strings.IS_IT_ALIVE, true);
	}
	
    
    //plays the game, be sure to grab input from the Scanner "in", and send your output to "out".
	public void playGame()
	{
		String userInput;
		
		do 
		{
			doRound();
			out.println(Strings.PLAY_AGAIN);
			userInput = in.nextLine().toLowerCase();
		}
		while(userInput.equals("y") || userInput.equals("yes"));
			
	}
	
	
	private void doRound()
	{
		Node curr = root;
		String userInput;
		
		while(curr.is_Question)
		{
			out.println(curr.val);
			userInput = in.nextLine().toLowerCase();
			
			if(userInput.equals("y"))
			{
				curr = curr.right;
			}
			else
			{
				curr = curr.left;
			}
		}
		
		out.println("Is it a " + curr.val + "?");
		userInput = in.nextLine().toLowerCase();
		
		if(userInput.equals("y"))
		{
			out.println(Strings.I_WIN);
		}
		else
		{
			out.println(Strings.WHAT_IS_THE_ANSWER);
			String answer = in.nextLine();
			
			out.println(Strings.NEW_QUESTION + curr.val + " and a " + answer);
			String question = in.nextLine();
			
			out.println("Answering yes to " + question + " means " + answer + "?");
			String spec = in.nextLine().toLowerCase();
			
			if(spec.equals("y"))
			{
				updateTree(curr, question, answer, true);
			}
			else
			{
				updateTree(curr, question, answer, false);
			}
			
			out.println(Strings.THANKS);
		}
		
	}
	
	private void updateTree(Node node, String newQ, String newA, Boolean spot)
	{
		Node leftA;
		Node rightA;
		
		if(spot)
		{
			leftA = new Node(null, null, node.val, false);
			rightA = new Node(null, null, newA, false);
		}
		else
		{
			leftA = new Node(null, null, newA, false);
			rightA = new Node(null, null, node.val, false);
		}
		
		node.val = newQ;
		node.is_Question = true;
		node.left = leftA;
		node.right = rightA;		
	}
	
	
	public static void main(String[] args)
	{
		QTree t = new QTree(System.in, System.out);
		t.playGame();
	}
	
	
}
