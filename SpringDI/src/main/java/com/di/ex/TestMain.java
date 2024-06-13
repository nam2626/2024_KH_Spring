package com.di.ex;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppContext.class);
		
		Greeting g1 = ctx.getBean("greeter",Greeting.class);
		System.out.println(g1);
		Greeting g2 = ctx.getBean("greeter",Greeting.class);
		System.out.println(g2);
		
		Person p = ctx.getBean("person",Person.class);
		System.out.println(p);
		Person s = ctx.getBean(Person.class);
		System.out.println(s);
		
		System.out.println(p == s);
	}

}





