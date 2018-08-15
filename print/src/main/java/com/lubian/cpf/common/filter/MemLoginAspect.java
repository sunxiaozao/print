package com.lubian.cpf.common.filter;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.lubian.cpf.common.util.SessionUtil;


/**
 * 处理方法级别的会员登录验证
 * 类级别的在SessionInterceptor中
 * @author Administrator
 *
 */
@Aspect
public class MemLoginAspect {

    @Around("@annotation(com.lubian.cpf.common.annotation.NeedDoctorLogin) && args(request,..)")
    public Object doAround(ProceedingJoinPoint pjp, HttpServletRequest request) throws Throwable {
    	if(SessionUtil.getUser(request)==null){
    		 request.setAttribute("NEED_MEM_LOGIN", "true");
    		 return null;
    	}else{
    		Object retVal = pjp.proceed();
            return retVal;
    	}
    	
        
    }


    
}
