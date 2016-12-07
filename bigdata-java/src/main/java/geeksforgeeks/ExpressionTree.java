package geeksforgeeks;

import java.util.Scanner;

public class ExpressionTree {

    private ExpressionTree() {
    }

    public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	int n = scan.nextInt();
	for (int i = 0; i < n; i++) {
	    String[] arr = new String[scan.nextInt()];
	    for (int j = 0; j < arr.length; j++) {
		arr[j] = scan.next();
	    }
	    eval(parseRoot(arr));
	}
	scan.close();
    }

    private static Tree process(String[] arr, int index) {
	if (index <= arr.length) {
	    String val = arr[index - 1];
	    if (val != null) {
		Tree t = new Tree(val);
		t.left = process(arr, index * 2);
		t.right = process(arr, index * 2 + 1);
		return t;
	    }
	}
	return null;
    }

    private static Tree parseRoot(String[] arr) {
	return process(arr, 1);
    }

    private static Long eval(Tree root) {
	if (root.left.left == null) {
	    return Long.valueOf(root.data);
	}
	return evaluate(root.data, root.left.data, root.right.data);
    }

    private static void printTree(Tree root) {
	if (root != null) {
	    printTree(root.left);
	    System.out.println(root.data + " ");
	    printTree(root.right);
	}
    }

    private static Long evaluate(String exprn, String leftval, String rightval) {
	if ("+".equals(exprn)) {
	    return Long.valueOf(leftval) + Long.valueOf(rightval);
	} else if ("-".equals(exprn)) {
	    return Long.valueOf(leftval) - Long.valueOf(rightval);
	} else if ("*".equals(exprn)) {
	    return Long.valueOf(leftval) * Long.valueOf(rightval);
	} else if ("/".equals(exprn)) {
	    return Long.valueOf(leftval) / Long.valueOf(rightval);
	} else {
	    throw new UnsupportedOperationException("Unsupported operation");
	}
    }
}

class Tree {
    String data;
    Tree left, right;

    Tree(String d) {
	data = d;
	left = null;
	right = null;
    }
}