package logtrace;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.BTraceUtils.*;
import com.sun.btrace.annotations.*;

@BTrace
public class LogMethodCallTrace {
	//@TLS static Appendable c = Strings.newStringBuilder(true);
	//@TLS static Appendable r = Strings.newStringBuilder(true);
	
	@OnMethod (clazz="java.util.concurrent.ConcurrentLinkedQueue", method="/add|poll/", location=@Location(value=Kind.ENTRY))
    public static void recordFuncEnter (@ProbeClassName String className, @ProbeMethodName String methodName, AnyType[] objs) {
		Appendable c = Strings.newStringBuilder(true);
		Strings.append(c, BTraceUtils.str(BTraceUtils.threadId(BTraceUtils.currentThread())));
		Strings.append(c, " call ");
		Strings.append(c, className);
		Strings.append(c, " ");
		Strings.append(c, methodName);
		Strings.append(c, " ");
		Strings.append(c, Strings.str(BTraceUtils.timeMillis()));
		Strings.append(c, " - ");
		Strings.append(c, BTraceUtils.printArrayToString(objs));				
		
		// Print
		BTraceUtils.println(Strings.str(c));
	}
	
	@OnMethod (clazz="java.util.concurrent.ConcurrentLinkedQueue", method="add", location=@Location(value=Kind.RETURN))
	public static void recordAddReturn (@ProbeClassName String className, 
			@ProbeMethodName String methodName, @Return boolean returnVal) {
		Appendable r = Strings.newStringBuilder(true);
		Strings.append(r, BTraceUtils.str(BTraceUtils.threadId(BTraceUtils.currentThread())));
		Strings.append(r, " return ");
		Strings.append(r, className);
		Strings.append(r, " ");
		Strings.append(r, methodName);
		Strings.append(r, " ");
		Strings.append(r, Strings.str(BTraceUtils.timeMillis()));
		Strings.append(r, " - ");	
		Strings.append(r, Strings.str(returnVal));

		//Print
		BTraceUtils.println(Strings.str(r));
	}
	
	@OnMethod (clazz="java.util.concurrent.ConcurrentLinkedQueue", method="poll", location=@Location(value=Kind.RETURN))
	public static void recordPollReturn (@ProbeClassName String className, 
			@ProbeMethodName String methodName, @Return Object returnVal) {
		Appendable r = Strings.newStringBuilder(true);
		Strings.append(r, BTraceUtils.str(BTraceUtils.threadId(BTraceUtils.currentThread())));
		Strings.append(r, " return ");
		Strings.append(r, className);
		Strings.append(r, " ");
		Strings.append(r, methodName);
		Strings.append(r, " ");
		Strings.append(r, Strings.str(BTraceUtils.timeMillis()));
		Strings.append(r, " - ");
		if (returnVal == null) {
			Strings.append(r, "null");
		} else {
			Strings.append(r, Strings.str(returnVal));
		}

		//Print
		BTraceUtils.println(Strings.str(r));
	}
/*	@OnMethod (clazz="target.HelloWorld", method="/func.+/", location=@Location(value=Kind.RETURN))
	public static void recordFuncExit (@ProbeClassName String className, 
			@ProbeMethodName String methodName, @Return Object returnVal) {
		Appendable r = Strings.newStringBuilder(true);
		Strings.append(r, BTraceUtils.str(BTraceUtils.threadId(BTraceUtils.currentThread())));
		Strings.append(r, " return ");
		Strings.append(r, className);
		Strings.append(r, " ");
		Strings.append(r, methodName);
		Strings.append(r, " ");
		Strings.append(r, Strings.str(BTraceUtils.timeMillis()));
		//if (returnVal != null) { 
			Strings.append(r, " - ");	
			Strings.append(r, Strings.str(returnVal));
		//}
		
		//Print
		BTraceUtils.println(Strings.str(r));
	}*/
}
