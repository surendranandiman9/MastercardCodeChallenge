package com.mastercard.challenge.utils;

import java.io.*;
import java.util.*;
import java.util.LinkedList;


public class Graph
{
	private int V; // No. of vertices
	public LinkedList<Integer> adj[]; //Adjacency List

	//Constructor
	public Graph(int v)
	{
		V = v;
		adj = new LinkedList[v];
		for (int i=0; i<v; ++i)
			adj[i] = new LinkedList();
	}

	public void addEdge(int v,int w) { adj[v].add(w); }

	public Boolean isConnected(int s, int d)
	{	System.out.println("inside isconnected");
		LinkedList<Integer>temp;

		boolean visited[] = new boolean[V];

		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[s]=true;
		queue.add(s);

		Iterator<Integer> i;
		while (queue.size()!=0)
		{
			s = queue.poll();

			int n;
			i = adj[s].listIterator();

			while (i.hasNext())
			{
				n = i.next();

				if (n==d)
					return true;

				if (!visited[n])
				{
					visited[n] = true;
					queue.add(n);
				}
			}
		}

		return false;
	}
}
