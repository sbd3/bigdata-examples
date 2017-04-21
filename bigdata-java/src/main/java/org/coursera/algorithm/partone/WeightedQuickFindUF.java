package org.coursera.algorithm.partone;

public class WeightedQuickFindUF {
	
	private int[] id;
	private int[] sz;

	public WeightedQuickFindUF(int n) {
		id = new int[n];
		sz = new int[n];
		for (int i = 0; i < n; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	public int root(int p) {
		while(p != id[p]) {
			// Optimization for flattening the tree further
			id[p] = id[id[p]];
			p = id[p];
		}
		return p;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if(i == j) {
			return;
		}
		if(sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}
	}

}
