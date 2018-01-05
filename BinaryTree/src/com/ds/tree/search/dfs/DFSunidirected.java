package com.ds.tree.search.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * An undirected graph can only have tree edges and back edges
 * Forward and cross edge cannot occur in an undirected graph
 * @author harsh
 *
 */
public class DFSunidirected {
	
	static final int NUM_NODES = 100;
	static int[] parent = new int[NUM_NODES];
	static int[] color = new int[NUM_NODES];
	static HashSet<Tuple> backEdges = new HashSet<>();
	
	public void DFSmain(HashMap<Integer,HashSet<Integer>> adjlist, List<Integer> vertices)
	{
		for(int vertex : vertices)
		{
			parent[vertex] = -1;
			color[vertex] = 0;
		}
		
		for(int vertex : vertices)
		{
			DFSvisit(vertex, adjlist);
		}
	}
	
	public void DFSvisit(int vertex, HashMap<Integer, HashSet<Integer>> adjlist)
	{
		color[vertex] = 1;
		if(adjlist.get(vertex) != null)
		{
			for(int child : adjlist.get(vertex))
			{	
				if(color[child] == 0)
				{
					parent[child] = vertex;
					DFSvisit(child, adjlist);
				}
				else if(color[child] == 1 && parent[vertex] != child && parent[child] != vertex)
				{
					backEdges.add(new Tuple(vertex, child));
				}
			}
		}
		color[vertex] = 2;
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
	
	public static void main(String[] args) {
		
		int[] vertices = {0,1,2,3,4,5,6}; 
		int[][] edgeList = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{4,3},{3,0},{0,6}};
		List<Integer> vertexList = new ArrayList<>();
		for(int i=0; i<vertices.length; i++)
		{
			vertexList.add(vertices[i]);
		}
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
		
		DFSunidirected dfs = new DFSunidirected();
		dfs.DFSmain(adjlist, vertexList);
		printArray(color, 7);
		printArray(parent, 7);
		System.out.println("Back Edges: "+backEdges);
		System.out.println("Tree Edge: "+dfs.getTreeEdges(parent, 7));
	}
}
class Tuple
{
	int parent;
	int child;
	
	public Tuple(int parent, int child) {
		this.parent = parent;
		this.child = child;
	}
	
	/* Equals and hashcode contract should always be held up */
	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		if(obj instanceof Tuple)
		{
			Tuple tempObj = (Tuple)obj;
			if(this.parent == tempObj.parent && this.child == tempObj.child)
			{
				flag = true;
			}
			else if(this.parent == tempObj.child && this.child == tempObj.parent)
			{
				flag = true;
			}
		}
		return flag;
	}
	
	@Override
	public int hashCode() {
		return parent+child;
	}
	
	@Override
	public String toString() {
		return(parent+","+child);
	}
}