package com.hevispring.beans.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hevispring.beans.annotation.controller.UserController;
import com.hevispring.beans.annotation.respository.UserRepository;
import com.hevispring.beans.annotation.service.UserService;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/hevispring/beans/annotation/beans-annotation.xml");
		
		//@Component,@Service,@Controller,@Repository ��ְ����࣬���Ǵ󲿷�����£���һ���� �Զ�ɨ���Ĭ��������ǵ�һ��Сд�������շ����������ɸ�value��
		
		//@Component
		TestObject testObject = (TestObject) context.getBean("testObject");
		testObject.sayHello();
		
		//
		UserController userController = (UserController) context.getBean("userController");
		
		userController.execute();
	}
}