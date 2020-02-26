package objectClass;

import java.util.Iterator;

public class Test {
	
	
	//从小到大
	public static void main(String[] args) {
		int[] array = {1,34,67,5,100,888,22,45};
		System.out.println("排序前：");
		for (int i : array) {
			System.out.print(i+"\t");
		}
		int temp;
		/*
		 * for (int i = 0; i < array.length-1; i++) { for (int j = 0; j <
		 * array.length-i-1; j++) { if (array[j]>array[j+1]) { temp = array[j]; array[j]
		 * = array[j+1]; array[j+1] = temp; } } }
		 */
		for (int i = 0; i < array.length-1; i++) {
			int index = i;
			for (int j = i+1; j < array.length; j++) {
				if (array[index]>array[j]) {
					index = j;
				}
			}
			if (index!=i) {
				temp = array[i];
				array[i] = array[index];
				array[index] = temp;
			}
		}
		
		System.out.println();
		System.out.println("排序后：");
		for (int i : array) {
			System.out.print(i+"\t");
		}
	}
	
	public void t1() {
		
	}
}
