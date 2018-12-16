package pl.biniek.view.exceptionhandlers;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import pl.biniek.backend.exceptionsandhandlers.BasicApplicationException;

@Interceptor
@BindingInterfaceWeb
public class InterceptorForWeb implements Serializable {

    private boolean success = true;
    Object result;

    @AroundInvoke
    public Object methodInterceptorForWEB(InvocationContext invocation) throws Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("CONTROLLER Method called: " + invocation.getTarget().getClass().getName() + "." + invocation.getMethod().getName());
        sb.append(", SessionID:  " + fc.getExternalContext().getSessionId(false));
        //  sb.append(" SesionID "+fc.getExternalContext().getSessionId(false));
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
            result = invocation.proceed();
            if (result != null) {
                sb.append(" returned " + result.getClass().getName() + "=" + result.toString());
            } else {
                sb.append(" returned null");
            }

            success = true;
            return result;

        } catch (BasicApplicationException ex) {
            result = null;

            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);

            sb.append(ex.getCause());
            ex.getCause().printStackTrace();

        } catch (Exception ex) {
            result = false;

            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            if (ex.getCause() != null) {
                sb.append(ex.getCause());
                ex.getCause().printStackTrace();
            } else {
                ex.printStackTrace();
            }

//            
//        } catch (Exception ex) {
//            result = "/error/error";
//
//            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
//            success = false;
//            sb.append(" occured exception " + ex);
//            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_ERROR, "error.page.message");
        } finally {

            if (!success) {
                Logger.getGlobal().log(Level.SEVERE, sb.toString());
                showMessage(fc.getMaximumSeverity(), "wystąpił bład, sprawdz logi");
            } else {
                Logger.getGlobal().log(Level.INFO, sb.toString());
            }
        }

        return result;

    }

    public static void showMessage(FacesMessage.Severity info, String value) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(info, "", value));

    }
}
