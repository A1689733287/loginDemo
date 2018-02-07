package com.gpg.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserCheckInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// 获取当前执行的方法名：判断，只有当前方法名不是login，就进行验证
		// 获取actionContext对象
		ActionContext ac = arg0.getInvocationContext();
		// 获取action的代理对象
		ActionProxy proxy = arg0.getProxy();
		// 获取当前执行的方法名
		String methodName = proxy.getMethod();
		if (!"login".equals(methodName)) {
			// 先获取当前登录的用户
			Object obj = ac.getSession().get("userInfo");
			if (obj == null) {
				// 没有登录时返回input
				return "input";
			} else {
				// 当前用户登录
				return arg0.invoke();
			}
		} else {
			// 说明当前用户正在登录
			return arg0.invoke();
		}

	}

}
