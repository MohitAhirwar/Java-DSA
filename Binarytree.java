package BinaryTree;
import java.util.*;

//import linkedList.Node;

public class Binarytree {
	
	public static Balancedtreereturn isbal(BTNode root) {
		if(root==null) {
			Balancedtreereturn ans =  new Balancedtreereturn();
			ans.isbalanced=true;
			ans.height=0;
			return ans;
		}
		
		Balancedtreereturn leftans=isbal(root.left);
		Balancedtreereturn rightans=isbal(root.right);
		boolean isb=true;
		int h=1+ Math.max(leftans.height, rightans.height);

		
		if(Math.abs(leftans.height-rightans.height)>1) {
			isb=false;
		}
		
		if(leftans.isbalanced==false || rightans.isbalanced==false) {
			isb=false;
		}
		
		Balancedtreereturn ans =  new Balancedtreereturn();
		ans.isbalanced=isb;
		ans.height=h;
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int i;
		i=0;
		

	}

}
