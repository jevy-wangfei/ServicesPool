package io.jevy.servicespool.test;

import io.jevy.servicespool.service.ServicePool;

public class TestGetService {

	public TestGetService() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sql = ServicePool.getInstance().getService("student", "StudentInfo2", "2", "Male");
		System.out.println(sql);
	}

}
