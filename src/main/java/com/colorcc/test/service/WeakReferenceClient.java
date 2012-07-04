package com.colorcc.test.service;


public class WeakReferenceClient {
//	private static ReferenceQueue rq = new ReferenceQueue();
//
//	public static void main(String[] args) {
//		WeakReferenceClient wrc = new WeakReferenceClient();
//		// wrc.weakReferenceTest();
//
//		final int size = 10;
//		Set sa = new HashSet();
//		for (int i = 0; i < size; i++) {
//			SoftReference ref = new SoftReference(new Grocery("soft" + i), rq);
//			System.out.println("Just created soft: " + ref.get());
//			sa.add(ref);
//		}
//		checkQueue();
//		System.gc();
//		checkQueue();
//		System.out.println("---------------------------------------------------");
//		// 创建10个Grocery对象以及10个弱引用
//		Set wa = new HashSet();
//		for (int i = 0; i < size; i++) {
//			WeakReference ref = new WeakReference(new Grocery("weak " + i), rq);
//			System.out.println("Just created weak: " + ref.get());
//			wa.add(ref);
//		}
//		checkQueue();
//		System.gc();
//		checkQueue();
//		System.out.println("---------------------------------------------------");
//		// 创建10个Grocery对象以及10个虚引用
//		Set pa = new HashSet();
//		for (int i = 0; i < size; i++) {
//			PhantomReference ref = new PhantomReference(new Grocery("Phantom " + i), rq);
//			System.out.println("Just created Phantom: " + ref.get());
//			pa.add(ref);
//		}
//		System.gc();
//		checkQueue();
//	}
//
//	public static void checkQueue() {
//		Reference inq = rq.poll();
//		// 从队列中取出一个引用
//		if (inq != null)
//			System.out.println("In queue: " + inq + " : " + inq.get());
//	}
//
//	private void weakReferenceTest() {
//		ReferenceQueue<Object> rq = new ReferenceQueue<Object>();
//
//		String str = new String("Hello!");
//		WeakReference<String> strWr = new WeakReference<String>(str, rq);
//		str = null;
//		System.out.println(strWr.get() + "\t" + rq.poll());
//		System.gc();
//		System.out.println(strWr.get() + "\t" + rq.poll());
//	}

}
