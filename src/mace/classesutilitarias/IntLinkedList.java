package mace.classesutilitarias;

import java.util.LinkedList;

public class IntLinkedList extends LinkedList<Object> {
	
	private static final long serialVersionUID = 1L;
	
	public void adicionar(int i) {
		super.add(new Integer(i));
	}
	
	public boolean contem(int i) {
		return super.contains(new Integer(i));
	}
	
	public int removerPrimeiro() {
		
		Integer j = (Integer) super.removeFirst();
		
		return j.intValue();
	}
	
	public boolean remover(int i) {
		return super.remove(new Integer(i)) ;
	}
	
	public int getItem(int index) {
		
		Integer j = (Integer) super.get(index);
		
		return j.intValue();
	}
	
}