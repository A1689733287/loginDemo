package com.gpg.intercept;

import com.gpg.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		ActionProxy proxy = invocation.getProxy();
		String method = proxy.getMethod();
		if (!"login".equals(method)) {
			User user = (User) context.getSession().get("user");
			if (user == null) {
				return "input";
			} else {
				return invocation.invoke();
			}
		} else {
			return invocation.invoke();
		}
	}

}
