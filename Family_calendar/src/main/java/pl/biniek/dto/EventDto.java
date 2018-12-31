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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pl.biniek.facade.EventFacade;

import pl.biniek.model.*;

/**
 *
 * @author java pbi moje!!!!!
 */
@Stateless
@Path("/events")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)

public class EventDto implements Serializable {

    @EJB
    private EventFacade eventFacade;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEvents() {
        return eventFacade.findAll();

    }
   @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createEvent(Event event) {

        eventFacade.create(event);
    }

    public void saveAfterEdit(Event event) {

        eventFacade.edit(event);

    }

    public void deleteEvent(Event event) {
        eventFacade.remove(event);
    }
}
