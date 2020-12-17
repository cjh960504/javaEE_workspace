package com.study;

import java.lang.reflect.Method;

public class InstanceTest {
	public static void main(String[] args) {
		try {
			//Class �ڷ��� : Ŭ������ ���� ������ ��� �ִ� Ŭ����
			Class dogClass = Class.forName("com.study.Dog");
			System.out.println("�ε强��");
			Method[] methods = dogClass.getMethods();
			for(int i=0;i<methods.length;i++) {
				System.out.println(methods[i].getName());
			}
			//DogŬ������ new������ ���� �ʰ� �÷�����
			Dog dog = (Dog) dogClass.newInstance();
			System.out.println(dog.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("�ε����");
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
