package com.company.util;

import org.hibernate.Session;

import java.util.*;

import com.company.models.Blog;

public class BlogManager {

    public static void main(String[] args) {
        BlogManager mgr = new BlogManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreBlog("My Blog", "Blog body", new Date());
            Blog b = mgr.find(args[1]);
            System.out.println(b);
        }

        HibernateUtil.getSessionFactory().close();
    }


    public Blog find(String id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Blog b = (Blog) session.get(Blog.class, Long.parseLong(id));
        session.getTransaction().commit();
        return b;
    }    

    public Blog destroy(String id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Blog b = (Blog) session.get(Blog.class, Long.parseLong(id));
        session.delete(b);
        session.getTransaction().commit();
        return b;
    }    

    
    public List list(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List l = session.createQuery("from Blog").list();
        session.getTransaction().commit();
        return l;
    }

    public void save(Blog b){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(b);
        session.getTransaction().commit();
    }

    private void createAndStoreBlog(String title, String body, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Blog theBlog = new Blog();
        theBlog.setTitle(title);
        theBlog.setCreatedAt(theDate);
        theBlog.setBody(body);
        session.save(theBlog);
        session.getTransaction().commit();
    }

}