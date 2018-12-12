/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.biniek.view.bean;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import pl.biniek.model.Event;
import pl.biniek.view.controler.CalendarController;

/**
 *
 * @author java
 */
@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {

//    @Inject
// private CalendarController calendarControler;
////    
    private ScheduleModel eventModel;
     

 
    private ScheduleEvent event = new DefaultScheduleEvent();
 
    @PostConstruct
    public void init() {
        
    //    List <Event> lista = calendarControler.getAllEvents();
        
        
        eventModel = new DefaultScheduleModel();
        
        
        eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", new Date(), new Date()));
        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", new Date(2018, 12, 15, 9, 30), new Date(2018, 12, 15, 10, 30)));//tmp zmiana na LTD
        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", new Date(2018, 12, 17, 9, 30), new Date(2018, 12, 17, 10, 30)));// tmp todo zmiana na LDT -> converter
        
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
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);
         
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