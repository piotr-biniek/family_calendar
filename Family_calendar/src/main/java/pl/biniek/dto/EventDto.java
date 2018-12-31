/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.biniek.dto;


import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebMethod;
import javax.jws.WebService;
import pl.biniek.facade.EventFacade;

import pl.biniek.model.*;

/**
 *
 * @author java pbi moje!!!!!
 */
@Stateless
@WebService(serviceName="EventService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)

public class EventDto implements Serializable {

    @EJB
    private EventFacade eventFacade;

    
 @WebMethod(operationName="getEvents")
    public List<Event> getAllEvents() {
        return eventFacade.findAll();

    }

 @WebMethod(operationName="createEvent")   
    public void createEvent(Event event)  {
       
        eventFacade.create(event);
    }

   @WebMethod(operationName="saveEditedEvent")  
    public void saveAfterEdit(Event event) {

        eventFacade.edit(event);

    }

    
//  
//    @WebMethod(operationName="createEvent")     
//    public void remove(Event event) {
//        eventFacade.remove(event);
//
//    }

    @WebMethod(operationName="deleteEvent")  
    public void deleteEvent(Event event) {
        eventFacade.remove(event);
    }
 }

    

