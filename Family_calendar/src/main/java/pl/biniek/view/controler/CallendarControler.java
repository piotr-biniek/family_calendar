/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.biniek.view.controler;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import pl.biniek.dto.EventDto;
import pl.biniek.model.Event;

/**
 *
 * @author java
 */
@Named
@SessionScoped
public class CallendarControler implements Serializable {

    @EJB
    EventDto eventDto;
// 

    public List<ScheduleEvent> getSheduleEventList() {
        List<Event> lista = eventDto.getAllEvents();
        List<ScheduleEvent> returnlist = new ArrayList<>(lista.size());
        for (Event event : lista) {
            returnlist.add(eventToPrimeScheduleEvent(event));

        }
        return returnlist;
    }

    public void doSthing() {
    }

    public void saveNev(ScheduleEvent event) {

        eventDto.createEvent(primeScheduleEventToEvent(event));

    }

    private Date convertLDTToDate(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    private LocalDateTime convertDateToLDT(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public void updateEvent(ScheduleEvent event) {
        eventDto.saveAfterEdit(primeScheduleEventToEvent(event));

    }

    public void removeEvent(ScheduleEvent event) {
        eventDto.deleteEvent(primeScheduleEventToEvent(event));
        
    }

    private pl.biniek.model.Event primeScheduleEventToEvent(ScheduleEvent event) {
        Event newEvent = new Event();
        newEvent.setName(event.getTitle());
        newEvent.setDateOfEvent(convertDateToLDT(event.getStartDate()));
        newEvent.setDuration(Duration.between(convertDateToLDT(event.getStartDate()), convertDateToLDT(event.getEndDate())));
        if (event.getId() != null) {
            newEvent.setId(Long.valueOf(event.getId()));
        }

        return newEvent;
    }

    private ScheduleEvent eventToPrimeScheduleEvent(pl.biniek.model.Event event) {
        ScheduleEvent newEvent = new DefaultScheduleEvent(event.getName(), convertLDTToDate(event.getDateOfEvent()),
                convertLDTToDate(event.getDateOfEvent().plus(event.getDuration())));
        newEvent.setId(event.getId().toString());

        return newEvent;
    }
}
