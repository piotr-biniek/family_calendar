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
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.SessionScoped;
//import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.ScheduleEvent;
import pl.biniek.dto.EventDto;
import pl.biniek.model.Event;
/**
 *
 * @author java
 */

@Named("CallendarControler")
@SessionScoped
public class CallendarControler implements Serializable{
    
    @EJB
    EventDto eventDto;
// 
    public List<ScheduleEvent> getSheduleEvent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void doSthing() {
        System.out.println("############################### OK ###################################");
    }
    


    public void saveNev(ScheduleEvent event) {
        Event newEvent = new Event();
        newEvent.setName(event.getId());
        newEvent.setDateOfEvent(convertToLocalDateTime(event.getStartDate()));
        newEvent.setDuration(Duration.between(convertToLocalDateTime(event.getStartDate()),convertToLocalDateTime(event.getEndDate())));
       eventDto.createEvent(newEvent);
        
    }
    
    
    private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
    return dateToConvert.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDateTime();
}

    public void updateEvent(ScheduleEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeEvent(ScheduleEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
