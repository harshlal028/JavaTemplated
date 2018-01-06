package com.ds.string.matching;

import java.util.ArrayList;
import java.util.List;

public class KMPalgo {
	
	/* An algo for building longest prefix list */
	public static void buildLPS(int[] lps, String pattern)
	{
		int length = 0;
		lps[length] = 0;
		for(int i=1; i < lps.length;)
		{
			if(pattern.charAt(i) == pattern.charAt(length))
			{
				length++;
				lps[i] = length;
				i++;
			}
			else
			{
				if(length != 0) // here we backtrack to find the max len prefix
				{
					length = lps[length-1]; // For repeating case AAAAAABAAAAAAAAA
				}
				else
				{
					lps[i] = length;
					i++;
				}
			}
		}
	}
	
	/* Algo for KMP search */
	public static int KMPsearch(String text, String pattern)
	{
		int[] lps = new int[pattern.length()];
		buildLPS(lps, pattern);
		int i = 0;
		int j = 0;
		
		List<Integer> match = new ArrayList<>();
		int countMatch = 0;
		while(i < text.length())
		{
			if(text.charAt(i) == pattern.charAt(j))
			{
				i++;
				j++;
				
				if(j == pattern.length())
				{
					match.add(i-j);
					countMatch++;
					j = lps[j-1];
				}
			}
			else
			{
				if(j != 0)
				{
					j = lps[j-1];
				}
				else
				{
					i = i + 1;
				}
			}
		}
		System.out.println(match);
		return countMatch;
	}
	
	public static void main(String[] args) {
		String text = "abxabcabcaby";
		String pattern = "abcaby";
		//System.out.println(KMPsearch(text, pattern));
		String pat = "abcabb";
		int[] lps = new int[pat.length()]; 
		buildLPS(lps, pat);
		System.out.println(lps);
	}

}
