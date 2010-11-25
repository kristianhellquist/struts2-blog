$(function(){

  window.Blog = Backbone.Model.extend({
    initialize: function(){
      this.collection = window.archive;
      return this;
    },
    // Default URL for the model's representation on the server -- if you're
    // using Backbone's restful methods, override this to change the endpoint
    // that will be called.
    url : function() {
      var base = '/StrutsBlag/blogs'
      return (this.isNew() ? base : base + '/' + this.id) + ".json";
    },

    validate: function(attrs){
      var errors = {};
      _.each(["title", "body"], function(attr){
        if(_.isEmpty(attrs[attr]))
          errors[attr] = "can't be blank";
      });
      if(!_.isEmpty(errors))
        return errors;
    }
  });




  window.Blogs = Backbone.Collection.extend({
    model: Blog,
    url: "/StrutsBlag/blogs.json",
    initialize: function(){
      return this;
    },
    comparator: function(b){
      return -b.attributes.created_at;
    }
  });

  window.archive = new Blogs();

  var BlogEdit = Backbone.View.extend({
    events: {
      'click input[type=submit]': 'saveButton'
    },
    el: $("#article"),

    initialize: function(){
      this.render();
      this.el.show();
    },

    render: function() {
      var output = _.template($("#template-blog-new").html());
      $(this.el).html(output);
      return this;
    },

    saveButton: function(){
      var attributes = { 
        title: this.$("input[name=title]").attr("value"), 
        body: this.$("input[name=body]").attr("value") }; 
      
      var el = this.el;
      
      this.model.save(attributes, {
        error: function(m, errors){
          _.each(errors, function(message, attr){
          });
        },
        success: function(){
          window.archiveList.render();
          el.hide();
        }
      });
      return this;
    }
  });

 
  var BlogShow = Backbone.View.extend({
    el: $("#article"),

    events: {
      'click .remove': 'remove'
    },

    initialize: function(){
      this.render();
      this.el.show();
    },

    render: function() {
      var t = _.template($('#template-blog-show').html());
      $(this.el).html(t({ blog: this.model }));
      return this;
    },

    remove: function(){
      var view = this;
      this.model.destroy({
        success: function(model, response){
          view.el.hide();
          window.archiveList.render();
        }
      });
    }
  });

  
  var ArchiveList = Backbone.View.extend({

    el: $("#archive"),
    events: {
      'click .blog-new':  'new_blog'
    },

    initialize: function() {
      this.render();
    },

    new_blog: function(){
      var view = new BlogEdit({model: new Blog()});
      this.$("#article").html(view.render());
    },

    render: function() {
      var view = this;
      window.archive.fetch({
        success: function(){
          var t = _.template($('#template-archive').html());
          window.archive.sort();
          $(view.el).html(t({ blogs: window.archive }));
        } 
      });
    
      return this;
    }

  });

  window.archiveList = new ArchiveList();
  
  window.Routes = Backbone.Controller.extend({

    routes: {
      'blogs/:id': 'blog_show'
    },
  
    blog_show: function(id) {

      var blog = new Blog({id: id});
      blog.fetch({
        success: function(){
          (new BlogShow({ model: blog }).render());
        },
        error: function(model, errors){
          alert("Blog doesn't exists");
        }
      });     
    }
  });

  window.routes = new Routes();
  if(!Backbone.history)
    Backbone.history = new Backbone.History();

  Backbone.history.start();



});

