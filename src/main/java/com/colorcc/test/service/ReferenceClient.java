package com.colorcc.test.service;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ReferenceClient {

//	private final static long count = 10000000000L;
	private WeakReference<Map<Integer, String>> myMap;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("String ...");
//		for (long i = 0; i < count; i++) {
//			new ReferenceModel(i, "name_" + i, "desc_" + i);
//		}
		
		new ReferenceClient().doFunction();
		System.out.println("End!");
	}

	private void doFunction() {

		Map<Integer, String> map = new HashMap<Integer, String>();
		myMap = new WeakReference<Map<Integer, String>>(map);

		map = null;
		int i = 0;
		while (true) {
			if (myMap != null && myMap.get() != null) {
				myMap.get().put(i++, "test" + i);

				System.out.println("im still working!!!! " + i);
			} else {

				System.out.println("*******im free*******");
				break;
			}
//			System.gc();

		}
	}

}
