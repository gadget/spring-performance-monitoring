package com.greenformatics.spring.performance;

import java.util.Stack;

import org.aopalliance.intercept.MethodInvocation;

/**
 * Stack to store the call chain for a thread. When a method call is intercepted by the {@link ExecutionTimeMonitoringInterceptor} it is registered in
 * the stack. This way we can track the execution and provide hierarchical tag names for Perf4J.<br>
 * e.g. <code>SomeService#loadSomething().SomeRepository#loadSomethingById()</code>
 * 
 * @author gadget
 */
public class ExecutionTimeCallStack {

	private Stack<String> callStack = new Stack<String>();

	/**
	 * Push an intercepted method invocation into the stack.
	 * 
	 * @param methodInvocation
	 *            the invocation
	 */
	public void push(MethodInvocation methodInvocation) {
		String method = methodInvocation.getMethod().getDeclaringClass().getSimpleName() + "#" + methodInvocation.getMethod().getName();
		callStack.push(method);
	}

	/**
	 * Removes an item from the top of the stack.
	 */
	public void pop() {
		callStack.pop();
	}

	/**
	 * Checks whether the stack is empty or not.
	 * 
	 * @return true if the stack is empty
	 */
	public boolean isEmpty() {
		return callStack.isEmpty();
	}

	/**
	 * Returns the tag name in hierarchical format (dot notation format) based on the current state of the stack.<br>
	 * e.g. <code>SomeService#loadSomething().SomeRepository#loadSomethingById()</code>
	 * 
	 * @return the tag
	 */
	public String getExecutionTag() {
		StringBuilder sb = new StringBuilder(64);
		int idx = callStack.size();
		for (String method : callStack) {
			sb.append(method);
			if (--idx > 0) {
				sb.append(".");
			}
		}

		return sb.toString();
	}

}
