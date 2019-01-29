package co.edu.javeriana.logo;
import java.util.Map;

import edu.emory.mathcs.backport.java.util.Collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SymbolTable {
	private List<Map<String, Object>> stack;

	public SymbolTable() {
		super();
		this.stack = new ArrayList<Map<String, Object>>();
	}
	
	public void addTable(){
		Map<String, Object> table = new HashMap<String, Object>();
		this.stack.add(table);
	}
	
	public void popTable(){
		this.stack.remove(stack.size()-1);
	}
	
	public Object findSymbol(String name){
		Collections.reverse(stack);
		Object x=null;
		for(Map<String, Object> aux : stack){
			x= aux.get(name);
			if(x!=null){
				break;
			}
		}
		Collections.reverse(stack);
		return x;
	}
	
	public void putSymbol(String name, Object obj){
		stack.get(stack.size()-1).put(name, obj);
	}
}
