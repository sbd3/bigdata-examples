package org.coursera.algorithm.partone;

public class QuickUnionUF {
	
	private int[] id;

	public QuickUnionUF(int n) {
		id = new int[n];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}
	
	private int root(int i) {
		while(i != id[i]) {
			i = id[i];
		}
		return i;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void union(int p, int q) {
		int rootp = root(p);
		int rootq = root(q);
		id[rootp] = rootq;
	}

}
