/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.biniek.backend.exceptionsandhandlers;

import javax.ejb.ApplicationException;

/**
 *
 * @author samsung
 */
@ApplicationException(rollback = true)
public class BasicApplicationException extends Exception {
     public static String ENTITY_EXISTS_EXCEPTION="messages.excep.entityexistsexception";
    public static String ENTITY_NOT_FOUND_EXCEPTION="messages.excep.entitynotfoundexception";
    public static String NO_RESULT_EXCEPTION="messages.excep.noresultexception";
    public static String TRANSACTION_REQUIRED_EXCEPTION="messages.excep.transactionrequiredexception";
    public static String OPTIMISTIC_LOCK_EXCEPTION="messages.excep.optimisticlockexception";
    


    static final long serialVersionUID = 10L;

    public BasicApplicationException() {
    }

    public BasicApplicationException(String message) {
        super(message);
    }

    public BasicApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicApplicationException(Throwable cause) {
        super(cause);
    }

}
