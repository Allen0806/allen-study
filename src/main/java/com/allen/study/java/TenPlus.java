package com.allen.study.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TenPlus {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 11; i++) {
			for (int j = 0; i + j < 11; j++) {
				int n = i + j;
				list.add(i + "+" + j + "= " );
			}
		}
		for (int i = 10; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				int n = i - j;
				list.add(i + "-" + j + "= " );
			}
		}
//		System.out.println(list.size());
		
		List<Integer> intSet = new ArrayList<Integer>();
		while (intSet.size() < 60) {
			Integer index = new Random().nextInt(132);
			if(!intSet.contains(index)) {
				intSet.add(index);
			}
		}
//		System.out.println(intSet);
		int k = 0;
		for(int i = 0; i < intSet.size(); i++) {
			String s = list.get(intSet.get(i));
			System.out.print(s);
			k++;
			if(k < 5) {
				System.out.print("       ");
			}else {
				System.out.println();
				k = 0;
			}
		}

	}

}
