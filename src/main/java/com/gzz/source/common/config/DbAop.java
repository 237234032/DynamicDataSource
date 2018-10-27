package com.gzz.source.common.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author gzz_gzz@163.com
 * @date 2018-03-08
 */
@Aspect
@Component
public class DbAop {
	@Around("within(com..*) && @annotation(db)")
	public Object doBasicProfiling(ProceedingJoinPoint pjp, ChangeDs db) {
		DynSource.setDB(db.value());
		Object object = null;
		try {
			object = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			DynSource.setDefalt();
		}
		DynSource.setDefalt();
		return object;
	}

}