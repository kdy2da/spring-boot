package com.codingbox.springshop.test;

public class Main03 {
	public static void main(String[] args) {
		/*
		 *  # 자바 가변길이 인자
		 *   - 자바에서는 파라미터 개수가 다르면 다른 메소드로 인식을 합니다.
		 *   - 오버로드는 파라미터 개수에 맞춰 메소드가 계속 늘어나는 구조이다.
		 *  
		 *  // 파라미터가 1개인 경우
		 *  public void search(String one) {...}
		 *  
		 *  // 파라미터가 2개인 경우
		 *  public void search(String one, String two) {...}
		 *  
		 *  // 파라미터가 3개인 경우
		 *  public void search(String one, String two, String three) {...}
		 *  
		 *  // map, List, 배열, 객체 배열 등을 파라미터를 전달하기도 한다.
		 *  // Map사용
		 *  public void search(Map<String, String> param) {}
		 *  
		 *  // List사용
		 *  public void search(List<String> param) {}
		 *  
		 *  // 가변인자
		 *  -> 가변인자를 사용하면 동적으로 파라미터를 받을 수 있다.
		 *  -> 사용법은 변수 타입 뒤에 기호(...)를 붙여주면 됩니다.
		 *  -> 가변인자를 가지고 있는 메소드를 호출할 때는 아래 방법이 모두 가능하다.
		 */
		test();
		test("고대열", "조태희", "이하연");
		test(1, "고대열", "조태희", "이하연", "박병훈", "김비오");
		test(new String[] {"A","B","C"});
		test(20, new String[] {"A","B","C"});
	}
	
	public static void test(String... param) {
		System.out.println("===test 호출===");
		for(String str : param) {
			System.out.println(str);
		}
	}
	// 다른 파라미터와 가변인자를 같이 사용하는 경우에는 가변인자를 제일 뒤에 위치 시켜야 합니다.
	public static void test(int num, String... param) {
		System.out.println("===test2 호출===");
		System.out.println("num : " + num);
		for(String str : param) {
			System.out.println(str);
		}
	}
}
