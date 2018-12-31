/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.biniek.backend.webservice;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import pl.biniek.dto.EventDto;
import pl.biniek.model.Event;

/**
 *
 * @author java
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {

    @EJB
    private EventDto ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"
    

    @WebMethod(operationName = "getAllEvents")
    public List<Event> getAllEvents() {
        return ejbRef.getAllEvents();
    }

    @WebMethod(operationName = "createEvent")
    @Oneway
    public void createEvent(@WebParam(name = "event") Event event) {
        ejbRef.createEvent(event);
    }

    @WebMethod(operationName = "saveAfterEdit")
    @Oneway
    public void saveAfterEdit(@WebParam(name = "event") Event event) {
        ejbRef.saveAfterEdit(event);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "event") Event event) {
        ejbRef.remove(event);
    }

    @WebMethod(operationName = "deleteEvent")
    @Oneway
    public void deleteEvent(@WebParam(name = "event") Event event) {
        ejbRef.deleteEvent(event);
    }
    
}
