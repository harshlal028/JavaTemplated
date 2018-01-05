package com.ds.tree.search.bfs;

import java.util.HashMap;
import java.util.HashSet;

import sun.misc.Queue;

/**
 * Breadth first search with levels.
 * The algorithm can find the shortest path from a source node to all other nodes 
 * and works for both directed and undirected graphs.
 * @author harsh
 *
 */
public class BFS {
	
	final static int WHITE = 0;
	final static int GRAY = 1;
	final static int BLACK = 2;
	
	static final int NUM_NODES = 100;
	static int[] parent = new int[NUM_NODES];
	static int[] color = new int[NUM_NODES];
	static int[] level = new int[NUM_NODES];
	
	public void BFSmain(HashMap<Integer, HashSet<Integer>> adjlist, int source) throws InterruptedException
	{
		for(int i = 0; i < NUM_NODES; i++)
		{
			parent[i] = -1;
			level[i] = -1;
		}
		color[source] = GRAY; //coloring gray
		level[source] = 0;
		parent[source] = -1;
		
		Queue<Integer> frontier = new Queue<>();
		frontier.enqueue(source);
		while(!frontier.isEmpty())
		{
			int u = frontier.dequeue();
			if(adjlist.get(u) != null)
			{
				for(int v : adjlist.get(u))
				{
					if(color[v] == WHITE)
					{
						color[v] = GRAY;
						level[v] = level[u]+1;
						parent[v] = u;
						frontier.enqueue(v);
					}
				}
			}
			color[u] = BLACK;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		int[] vertices = {0,1,2,3,4,5,6}; 
		int[][] edgeList = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{4,3},{3,0},{0,6}};
		
		HashMap<Integer,HashSet<Integer>> adjlist = new HashMap<>();
		HashSet<Integer> tmpList1 = null;
		HashSet<Integer> tmpList2 = null;
		for(int i=0; i<edgeList.length; i++)
		{
			tmpList1 = adjlist.get(edgeList[i][0]);
			tmpList2 = adjlist.get(edgeList[i][1]);
			if(tmpList1 == null)
			{
				tmpList1 = new HashSet<>();
			}
			if(tmpList2 == null)
			{
				tmpList2 = new HashSet<>();
			}
			tmpList1.add(edgeList[i][1]);
			adjlist.put(edgeList[i][0], tmpList1);
			tmpList2.add(edgeList[i][0]);
			adjlist.put(edgeList[i][1], tmpList2);
		}
		
		BFS bfs = new BFS();
		bfs.BFSmain(adjlist, 0);
		System.out.println("Color");
		printArray(color, 7);
		System.out.println("Parent");
		printArray(parent, 7);
		System.out.println("Level");
		printArray(level, 7);
	}
	
	static void printArray(int[] arr, int length)
	{
		if(length == 0)
			length = arr.length;
		for(int i=0; i<length; i++)
		{
			System.out.print(arr[i]+" , ");
		}
		System.out.println();
	}
}
