struts2-blog
============

This is a REST-application that expose a blog model with title and body. 

Backend
-------
The backend is a struts2 application that uses the REST-plugin to simply REST-applications in Java. Depends on hibernate and a mysql database.

Frontend
--------
The frontend is backbone.js application that uses the json api to view, create and delete blogs

Build
-----

Build a WAR-file ./dist/:

    ant


Deployment
----------
Copy the war-file to your application server
