package com.study;

import java.lang.reflect.Method;

public class InstanceTest {
	public static void main(String[] args) {
		try {
			//Class 자료형 : 클래스에 대한 정보를 담고 있는 클래스
			Class dogClass = Class.forName("com.study.Dog");
			System.out.println("로드성공");
			Method[] methods = dogClass.getMethods();
			for(int i=0;i<methods.length;i++) {
				System.out.println(methods[i].getName());
			}
			//Dog클래스를 new연산자 쓰지 않고 올려보자
			Dog dog = (Dog) dogClass.newInstance();
			System.out.println(dog.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("로드실패");
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
