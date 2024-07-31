package com.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class MemberAOP {
	// JoinPoint 이전에 실행
	// 리턴 타입이 BoardMemberDTO, 서비스 패키지 내에 있는 모든 클래스, 
	//모든 메서드, 매개변수는 문자열 두개 형태의 메서드 전에 실행
	@Before("execution(com.aop.dto.BoardMemberDTO com.aop.service.*.*(String, String))")
	public void loginTest(JoinPoint joinPoint) {
		System.out.println(joinPoint.getSignature().getDeclaringType().getName() + " / " + joinPoint.getSignature().getName());
		System.out.println(Arrays.toString(joinPoint.getArgs()));
	}
	
	// JoinPoint 이전에 실행
	// 모든리턴타입 com.aop패키지(하위 패키지 포함)안에있는 모든 클래스의 모든 메서드, 매개변수는 모든형태를 지원
	@Before("execution(* com.aop..*.*(..))")
	public void beforeTest(JoinPoint joinPoint) {
		System.out.println("beforeTest : " + joinPoint.getSignature().getDeclaringType().getSimpleName() + " / "
				+ joinPoint.getSignature().getName());

		System.out.println("beforeTest - args : " + Arrays.toString(joinPoint.getArgs()));
	}
	
	// JoinPoint 이후에 실행
	//모든리턴타입 com.aop.service패키지 내에 있는 모든 클래스, 
	//모든 메서드, 매개변수는 모든 형태를 지원
	@After("execution(* com.aop.service.*.*(..))")
	public void afterTest(JoinPoint joinPoint) {
		System.out.println("afterTest : " + joinPoint.getSignature().getDeclaringType().getSimpleName() + " / "
				+ joinPoint.getSignature().getName());
	}
	
	// 리턴된 데이터가 없으면 null 값을 받아옴
	@AfterReturning(pointcut = "execution(* com.aop.service.*.*(..))", returning = "returnObj")
	public void afterReturningTest(JoinPoint joinPoint, Object returnObj) {
		System.out.println("afterReturningTest : " + joinPoint.getSignature().getDeclaringType().getSimpleName() + " / "
				+ joinPoint.getSignature().getName());
		System.out.println("afterReturningTest : " + (returnObj == null ? "리턴결과 없음" : returnObj.toString()));
	}
	
	//메서드에서 Exception을 throws를 하는 경우 실행
	@AfterThrowing(pointcut = "execution(* com.aop.service.*.*(..))", throwing = "exception")
	public void afterThrowingTest(JoinPoint joinPoint, Exception exception) {
		System.out.println("afterThrowingTest : " + joinPoint.getSignature().getName());
		System.out.println("afterThrowingTest : " + exception.getMessage());
	}
	
	//메서드 호출 전후에 수행
	@Around("execution(* com.aop.mapper.*.*(..))")
	public Object aroundTest(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Around Test Start");
		System.out.println(joinPoint.getSignature().getName());
		StopWatch watch = new StopWatch();
		watch.start();
		Object obj = joinPoint.proceed();//메서드 실행하는 부분
		watch.stop();
		System.out.println(watch.prettyPrint());
		if(obj != null)	System.out.println(obj.toString());
		System.out.println("Around Test End");
		return obj;
	}
}









