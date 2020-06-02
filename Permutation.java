import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;

public class Permutation {
	public static void main(String[] args){

        RandomizedQueue<String> q = new RandomizedQueue<String>();
        
		int out = Integer.parseInt(args[0]);
        int in = 0;
        
		while (!StdIn.isEmpty()){
			String item = StdIn.readString();
			if (item != null && item.length() > 0){
				q.enqueue(item);
				in++;
			}
        }
        
		if (in < out)
			throw new NoSuchElementException("incoming element < need to be print out");
        
		for (int i = 0; i < out; i++)
			System.out.println(q.dequeue());
	}	
}