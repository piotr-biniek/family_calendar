/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.biniek.model;

import static java.awt.SystemColor.info;
import static java.awt.geom.Point2D.distance;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author java
 */
@Entity
@Table(name = "Event")

public class Event extends AbstractEntity implements Serializable {

    @Id
    @Column(name = "id", updatable = false)
    @TableGenerator(name = "CourseGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "Course", initialValue = 200, allocationSize = 7919)
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "CourseGen")
    private long id;

    @NotNull
    @Column(name = "name", unique = true, nullable = false, length = 256)
    private String name;

    
    @NotNull
    @Column(name = "dateOfStart",  nullable = false)
   // @Temporal(javax.persistence.TemporalType.TIMESTAMP)// nie jest wymagane w LDT
    private LocalDateTime dateOfStart;

    
    //@NotNull
    @Column(name="duration", unique = false, nullable = true)
    private Duration furation;

 
     
    




    /**
     * Get the value of id
     *
     * @return the value of id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the value of phone
     *
     * @return the value of phone
     */
    
    /**
     * Get the value of dateOfStart
     *
     * @return the value of dateOfStart
     */
    public LocalDateTime getDateOfStart() {
        return dateOfStart;
    }

    /**
     * Set the value of dateOfStart
     *
     * @param dateOfStart new value of dateOfStart
     */
    public void setDateOfStart(LocalDateTime dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    
       public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }
   /**
     * old method taken from studies - should be rebuilded
     *
     * return value 
     */
    @Override
    protected String getBusinessKey() {//todo
        
        return "id :"+String.valueOf(id)+", name :"+name;
        
    }

   /**
     * Generates informacion for mailing confirmation 
     *
     */
     public String toMail() {
        return "Course{ name=" + name + '}';//todo
    }



}
