/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.biniek.view.bean;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import pl.biniek.view.controler.CallendarControler;
import pl.biniek.view.exceptionhandlers.BindingInterfaceWeb;

/**
 *
 * @author java
 */
@Named
@SessionScoped
@BindingInterfaceWeb
public class ScheduleView implements Serializable {

    @Inject
    private CallendarControler callendarControler;

    private ScheduleModel eventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    @PostConstruct
    public void init() {
      //  List <ScheduleEvent> lista = callendarControler.getSheduleEvent();
      //     eventModel = new DefaultScheduleModel();
          eventModel = new DefaultScheduleModel(callendarControler.getSheduleEventList());
       callendarControler.doSthing();
        

    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent() {
        if (event.getId() == null) {
            callendarControler.saveNev(event);
            eventModel.addEvent(event);
        } else {
            callendarControler.updateEvent(event);

            eventModel.updateEvent(event);
        }

        event = new DefaultScheduleEvent();
        eventModel = new DefaultScheduleModel(callendarControler.getSheduleEventList());
    }

    public void removeEvent() {
            callendarControler.removeEvent(event);
            eventModel.deleteEvent(event);
        
        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
