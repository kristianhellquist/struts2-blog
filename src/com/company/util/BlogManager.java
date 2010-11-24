package com.company.util;

import org.hibernate.Session;

import java.util.*;

import com.company.models.Blog;

public class BlogManager {

    public static void main(String[] args) {
        BlogManager mgr = new BlogManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreBlog("My Blog", new Date());
        }

        HibernateUtil.getSessionFactory().close();
    }

    private void createAndStoreBlog(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
         session.beginTransaction();

        Blog theBlog = new Blog();
        theBlog.setTitle(title);
        theBlog.setCreatedAt(theDate);
        session.save(theBlog);
        session.getTransaction().commit();
    }

}