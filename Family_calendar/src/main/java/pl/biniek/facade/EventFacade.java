/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.biniek.facade;

import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pl.biniek.model.Event;

/**
 *
 * @author java
 */
@Stateless
public class EventFacade extends AbstractFacade<Event> {

    @PersistenceContext(unitName = "com.mycompany_Family_calendar_war_0.1-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventFacade() {
        super(Event.class);
    }

    public List<Event> findBeetwenDates(LocalDateTime start,LocalDateTime end) {
          
        Query tq = em.createNamedQuery("Event.findBeetwenDates");
        tq.setParameter("start", start );
        tq.setParameter("end", end );
        return  tq.getResultList();
        
        
    
    
    }
    
    
}
