package com.greenformatics.spring.performance;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;

/**
 * {@link MethodInterceptor} that logs the execution time of intercepted method invocations with Perf4J.
 * 
 * @author gadget
 */
public class ExecutionTimeMonitoringInterceptor implements MethodInterceptor {

	private static final ThreadLocal<ExecutionTimeCallStack> THREAD_LOCAL = new ThreadLocal<ExecutionTimeCallStack>();

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// a call stack is bounded to the current thread to store all the invocations executed by the thread
		ExecutionTimeCallStack callStack = THREAD_LOCAL.get();
		if (callStack == null) {
			callStack = new ExecutionTimeCallStack();
			THREAD_LOCAL.set(callStack);
		}

		callStack.push(invocation);
		StopWatch stopWatch = new Log4JStopWatch(callStack.getExecutionTag());
		try {
			return invocation.proceed();
		} finally {
			stopWatch.stop();
			callStack.pop();

			if (callStack.isEmpty()) {
				THREAD_LOCAL.remove();
			}
		}
	}

}
