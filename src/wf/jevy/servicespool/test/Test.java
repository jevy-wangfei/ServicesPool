package wf.jevy.servicespool.test;

import wf.jevy.servicespool.service.ServicePool;

public class Test {

	public Test() {
		ServicePool.getInstance().getService("test", "test", "a", "b", "c");
		
	}
	
	public static void main(String[] args){
		ServicePool.getInstance().getService("test", "test", "a", "b", "c");
		ServicePool.getInstance().getService("test", "test", "a", "b");
		ServicePool.getInstance().getService("test", "test", "a", "b", "c", "D");
		new CopyOfTest();
	}

}
