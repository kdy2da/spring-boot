package com.codingbox.springshop.test;

public class Main02 {
	public static void main(String[] args) {
		for( Type t : Type.values() ) {
			System.out.println(t.getName());
		}
		
		System.out.println(Type.HIKING);
		System.out.println(Type.HIKING.getName());
	}
}
