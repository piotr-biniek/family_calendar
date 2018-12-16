package pl.biniek.backend.exceptionsandhandlers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.PersistenceException;

public class ExceptionAndLoggingInterceptorForFACADE {

    @Resource
    private SessionContext sessionContext;

    boolean success = true;

    @AroundInvoke
    public Object interceptorMethodforFacades(InvocationContext invocation) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Method facade called: " + invocation.getTarget().getClass().getName() + "." + invocation.getMethod().getName());
        sb.append("bu user: " + sessionContext.getCallerPrincipal().getName());
        sb.append(", SessionID:  " + FacesContext.getCurrentInstance().getExternalContext().getSessionId(false));

        try {

            Object[] parameters = invocation.getParameters();
            if (null != parameters) {
                for (Object param : parameters) {
                    if (param != null) {
                        sb.append(" with params: " + param.getClass().getName() + "=" + param.toString());
                    } else {
                        sb.append("  with NULL");
                    }
                }
            }

            Object result = invocation.proceed();

            if (result != null) {
                sb.append(" returned " + result.getClass().getName() + "=" + result.toString());
            } else {
                sb.append(" returned null");
            }
            success = true;
            return result;

        } catch (PersistenceException ex) {          
            success = false;
            sb.append(" occured EXCEPTION " + ex);
            throw new BasicApplicationException("Persistance error", ex);

        } catch (Exception ex) {
            sb.append(" occured EXCEPTION in facade " + ex);
            success = false;
             throw new BasicApplicationException("Unknown exception", ex);       

        } finally {
            if (!success) {
                Logger.getGlobal().log(Level.SEVERE, sb.toString());
            } else {
                Logger.getGlobal().log(Level.INFO, sb.toString());
            }
        }
    }
}
