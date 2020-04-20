package com.sc.util;

import java.util.HashMap;

//Trie Node, which stores a character and the children in a HashMap 
public class TrieNode { 
	int size = 0;
	private char value; 
	private HashMap<Character,TrieNode> children; 
	private boolean bIsEnd; 
	public TrieNode(char ch) 
	{ 
		value = ch; 
		children = new HashMap<>(); 
		bIsEnd = false; 
	} 
	public HashMap<Character,TrieNode> getChildren()
	{
		return children; 
	} 
	public char getValue()
	{  
		return value;     
	} 
	public void setIsEnd(boolean val)  
	{ 
		bIsEnd = val;    
	} 
	public boolean isEnd()        
	{
		return bIsEnd;    
	} 

	
} 
