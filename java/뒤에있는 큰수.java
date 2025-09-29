package test;

import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("데이터 내노라 :");
		
		int num = sc.nextInt();
		System.out.println("num:"+num);
		
		System.out.println("문자열 내놔라");
		String str1= sc.next();
		System.out.println("str1:"+str1);
		
		System.out.println("두번쨰문자열 내놔라");
		String str2= sc.next();
		System.out.println("str2:"+str2);
		sc.nextLine();
		System.out.println("한 문장 내놔라");
		String str3= sc.nextLine();
		System.out.println("str3:"+str3);
	}
}