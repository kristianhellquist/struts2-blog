package com.company.controllers;

import com.company.models.Blog;
import com.company.util.BlogManager;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import java.util.Collection;

public class BlogsController implements ModelDriven<Object> {

    private BlogManager blogManager = new BlogManager();
    private String id;
    private Blog model = new Blog();
    private Collection list;

    
    public Object getModel(){
        if(list==null){
            return model;
        }else{
            return list;
        }
    }
    
    public HttpHeaders create() {
        blogManager.save(model);
        return new DefaultHttpHeaders("show");
    }


    public HttpHeaders show() {
        model = blogManager.find(id);
        return new DefaultHttpHeaders("show");
    }

    public HttpHeaders destroy() {
        model = blogManager.destroy(id);
        return new DefaultHttpHeaders("show");
    }
  
  
    public HttpHeaders index() {
        list = blogManager.list();
        return new DefaultHttpHeaders("show");
    }
   
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
}

