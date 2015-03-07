package com.yaps.petstore.service.supervision;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PetstoreAspect {

	
	private static final String SEPARATOR = " ";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PetstoreAspect.class);


	/**
	 * 
	 * @param result
	 */
	@Around("execution(* *..service..*ServiceImpl.*(..))")
	public Object logResultAround(final ProceedingJoinPoint joinPoint)
			throws Throwable {

		final StringBuffer sb = new StringBuffer();		
		
		//method name
		sb.append(joinPoint.getTarget().getClass().getCanonicalName()).append(".").append(joinPoint.getSignature().getName());

		//add arguments if necessary
		
		sb.append(SEPARATOR);

		long startTime = System.currentTimeMillis();
		long startNanoTime = System.nanoTime();
		Object obj = null;

		try {
			obj = joinPoint.proceed();

			long elapsedTime = System.currentTimeMillis() - startTime;
			long elapsedNanoTime = (System.nanoTime() - startNanoTime)/1000000 ;			
			sb.append("YES").append(SEPARATOR).append(elapsedNanoTime);
		}
		catch(Exception e){
			long elapsedTime = System.currentTimeMillis() - startTime;
			long elapsedNanoTime = (System.nanoTime() - startNanoTime)/1000000 ;			
			sb.append("NO").append(SEPARATOR).append(elapsedNanoTime);			
		} 
		LOGGER.info(sb.toString());
		return obj;
	}

}
