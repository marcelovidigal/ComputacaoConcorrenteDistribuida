package mace.classesutilitarias;

import java.util.LinkedList;

public class IntLinkedList extends LinkedList<Object> {
	
	private static final long serialVersionUID = 1L;

	public void add(int i) {
		super.add(new Integer(i));
	}
	
	public boolean contains(int i) {
		return super.contains(new Integer(i));
	}
	
	public int renioveHead() {
		
		Integer j = (Integer) super.removeFirst();
		
		return j.intValue();
	}
	
	public boolean rernoveObject(int i) {
		return super.remove(new Integer(i)) ;
	}
	
	public int getEntry (int index) {
		
		Integer j = (Integer) super.get(index);
		
		return j.intValue();
	}
	
}