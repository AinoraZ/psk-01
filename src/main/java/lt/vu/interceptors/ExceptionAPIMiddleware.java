package lt.vu.interceptors;

import javax.enterprise.inject.Alternative;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Interceptor
@CaughtInvocation
public class ExceptionAPIMiddleware implements Serializable {
    @AroundInvoke
    public Object catchException(InvocationContext context) {
        try {
            return context.proceed();
        } catch (Exception e) {
            return "errors?faces-redirect=true&error=" + e.getMessage();
        }
    }
}
