package com.company.models;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import java.util.Date;


@Entity
@Table(name="blogs")
public class Blog {

    private Long id;
    private Date created_at;
    private String title;
    private String body;
    
    public Blog(){}

    @Id
    @GeneratedValue
    public Long getId(){
        return this.id;
    }

    private void setId(Long l){ 
        this.id = l;
    }
    
    @Column(name="created_at")
    public Date getCreatedAt(){
        return this.created_at;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    public void setCreatedAt(Date d){
        this.created_at = d;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String s){
        this.title = s;
    }

    public String getBody(){
        return this.body;
    }

    public void setBody(String s){
        this.body = s;
    }
}
