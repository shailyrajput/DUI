package com.scms.dui.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SingletonClass {
	private static SingletonClass myObj;
	private ArrayList<SaveQuestionDataUtils> list;
	private static List<ArrayList<SaveQuestionDataUtils>> singltonList;
	private static Map<Object, HashMap<Object, Object>> mainmap;

	private static Set<String> indexlist;
	public boolean checked;
	public static HashMap<Object, Object> map;
	public static HashMap<Object, Object> map_23;
	private SingletonClass() {
		singltonList = new ArrayList<ArrayList<SaveQuestionDataUtils>>();
		indexlist = new HashSet<String>();
		mainmap = new HashMap<Object, HashMap<Object, Object>>();
		map = new HashMap<Object, Object>();
		map_23= new HashMap<Object, Object>();
	}

	public static SingletonClass getInstance() {
		if (myObj == null) {
			myObj = new SingletonClass();
		}
		return myObj;
	}

	public void clearAll()
	{
		
		mainmap.clear();
		map.clear();
	}
	public void addList(int position, ArrayList<SaveQuestionDataUtils> list) {
		this.list = list;
		singltonList.add(position, list);
	}

	
	public void addMap(String question, HashMap<Object, Object> map) {
	
		mainmap.put(question, map);
		
	}
	
	
	
	public HashMap<Object, HashMap<Object, Object>> getMap() {
		return (HashMap<Object, HashMap<Object, Object>>) mainmap;
	}
	
	
	
	
	
	public List<ArrayList<SaveQuestionDataUtils>> getList() {
		return singltonList;

	}

	public void setIndex(String object) {

		indexlist.add(object);

	}

	public void removeIndex(String object) {
		indexlist.remove(object);
	}

	public Set<String> getIndex() {

		return indexlist;

	}
	
	

}
