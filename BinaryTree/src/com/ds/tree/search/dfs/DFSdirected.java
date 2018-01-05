package com.ds.tree.search.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Depth First Search with edge classification for Diredcted graphs
 * @author harsh
 *
 */
public class DFSdirected {
	
	final static int WHITE = 0;
	final static int GRAY = 1;
	final static int BLACK = 2;
	
	static int time = 1;
	static final int NUM_NODES = 100;
	static int color[] = new int[NUM_NODES];
	static int parents[] = new int[NUM_NODES]; //This records tree edges
	static int discoveryTime[] = new int[NUM_NODES];
	static List<String> backEdges = new ArrayList<>(); 
	static List<String> forwardEdges = new ArrayList<>();
	static List<String> crossEdges = new ArrayList<>();
	
	public void DFSmain(HashMap<Integer,List<Integer>> adjlist, List<Integer> vertices)
	{
		/*
		 * Here color 0 = white, 1 = gray and 2 = black
		 * This is non integral - done only for edge classification
		 */
		for(int vertex : vertices)
		{
			color[vertex] = WHITE;
			parents[vertex] = -1;
		}
		
		/*
		 * Iterating over all the nodes
		 */
		for(int vertex : vertices)
		{
			if(color[vertex] == WHITE)
			{
				DFSVisit(vertex, adjlist);
			}
		}
	}
	
	/**
	 * The recursive module
	 * @param vertex
	 * @param adjlist
	 */
	public void DFSVisit(int vertex, HashMap<Integer,List<Integer>> adjlist)
	{
		color[vertex] = GRAY; // coloring the vertex gray here
		for(int i = 0; adjlist.get(vertex)!= null && i < adjlist.get(vertex).size(); i++)
		{
			int child = adjlist.get(vertex).get(i);
			if(color[child] == WHITE)
			{
				parents[child] = vertex;
				discoveryTime[child] = ++time;
				DFSVisit(child, adjlist);
			}
			//Part below is done only for edge classification
			else if(color[child] == GRAY) 
			{
				backEdges.add(vertex+","+child);
			}
			else if(color[child] == BLACK)
			{
				if(discoveryTime[vertex] < discoveryTime[child])
				{
					forwardEdges.add(vertex+","+child);
				}
				else if(discoveryTime[child] < discoveryTime[vertex])
				{
					crossEdges.add(vertex+","+child);
				}
			}
		}
		color[vertex] = BLACK; //finishing exploring a vertex
	}
	
	public static void main(String[] args) {
		
		int[] vertices = {0,1,2,3,4,5,6}; 
		int[][] edgeList = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{4,3},{3,0},{0,6}};
		List<Integer> vertexList = new ArrayList<>();
		for(int i=0; i<vertices.length; i++)
		{
			vertexList.add(vertices[i]);
		}
		HashMap<Integer,List<Integer>> adjlist = new HashMap<>();
		List<Integer> tmpList = null;
		for(int i=0; i<edgeList.length; i++)
		{
			tmpList = adjlist.get(edgeList[i][0]); 
			if(tmpList == null)
			{
				tmpList = new ArrayList<>();
			}
			tmpList.add(edgeList[i][1]);
			adjlist.put(edgeList[i][0], tmpList);
		}
		
		DFSdirected dfs = new DFSdirected();
		dfs.DFSmain(adjlist, vertexList);
		printArray(color, 7);
		printArray(parents, 7);
		System.out.println("Back Edges: "+backEdges);
		System.out.println("Forward Edges: "+forwardEdges);
		System.out.println("Cross Edges: "+crossEdges);
		System.out.println("Tree Edge: "+dfs.getTreeEdges(parents, 7));
	}
	
	List<String> getTreeEdges(int[] arr, int length)
	{
		if(length == 0)
			length = arr.length;
		List<String> treeEdgeList = new ArrayList<>();
		for(int i=0; i < length; i++)
		{
			if(arr[i] != -1)
				treeEdgeList.add(arr[i]+","+i);
		}
		return treeEdgeList;
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
