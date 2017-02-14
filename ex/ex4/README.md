# Exercise 4
This exercise is about virtualization, load-balancing and hosting. This exercise is a 
little more diverse than you are used to.

## Part 1: Deploying a HTTP server
To finalise your epic HTTPS server from exercise 3, let's try to create a new Java server
and deploy it on your server. If everything goes well, you will communicate with your server
via HTTPS!

### 1.1 Creating a web application

First you should create a ``Web Application`` in netbeans. Create one HTTP GET method
which returns a small greeting to yourself as a ``String``. Pack this application into a ``.war``
file.

### 1.2 Installing Tomcat

If you haven't already installed tomcat, do it now by writing

    sudo apt-get install tomcat8

If you experienced a problem with Tomcat, it can be because Nginx is occupying port
80! Fix this by changing the default port of Tomcat in the file
``/etc/tomcat8/server.xml``. If not, Tomcat should start listening on port 8080.

### 1.3 Deploying your web application
Now that Tomcat is ready and you have your ``.war`` file (the web application), you can
now deploy your service to the server! Do this by copying your ``.war`` file into the
``/var/lib/tomcat8/webapps/`` directory. Tomcat should now deploy your app by itself.

Try to go to your server at port 8080 to see if you can find the web app you just deployed.

### 1.4 Redirecting Nginx traffic
Ok. So now we have a web application running on port 8080. But all traffic to that
should go through Nginx, because Nginx secures our communication (with HTTPS). 


         Client
          |
          | HTTPS
          |
    +--- Server ---+
    |     |        |
    |     | HTTP   |
    |     |        |
    |    Tomcat    |
    |              |
    +--------------+

That way our Tomcat server is protected from the outside (and insecure) environment.
Nginx talks HTTPS with the client, but simple plain, old HTTP with Tomcat.

So, that basically means that every request Nginx receives on port 443, it should forward
to Tomcat on port 8080. But locally.

To do that, simply go to the Nginx configuration at ``/etc/nginx/sites-enabled/default``
and change

    try_files $uri $uri/ =404;

to

    proxy_pass http://127.0.0.1:8080;

That's it! Now all clients will talk HTTPS with Nginx, but the communication will silently
pass on to Tomcat. When Tomcat replies, Nginx will wrap the response in HTTPS again to keep
the channel secure. Neat.

### 1.5 (Optional) Closing down Tomcat
Try to run ``sudo netstat -tulpn`` on your server. This will show you that Tomcat probably
listens to ``0.0.0.0:8080``. ``0.0.0.0`` is the public network interface of the server, which
means that you can access the server directly by going to your server on port ``8080``. Try
that now.

This is bad. It circumvents the entire HTTPS protocol through Nginx, so we should close it down,
so only Nginx can see it. To close it down we need to tell Tomcat _only_ to listen to 
``localhost``. Do this by opening ``/etc/tomcat8/server.xml`` and finding the ``Connector``
XML tag (mine is in line 71). Add a ``address="localhost"`` attribute inside the ``Connector``
tag and restart Tomcat. Now it only listens to connections locally. Smart, right?

Congratulations! Your server is now bullet proof!

## Part 2: Virtualisation
Explain with at least three lines of text:

1. What does virtualisation mean?
2. How are you using virtualisation right now?
3. Why is Java using virtualisation? And how does it help you?

## Part 3: XaaS
If we define layers as: data, runtime, virtualisation and hardware, answer the following:

* Which types of XaaS exists?
* Which layer of the virtualised environment do you own in the different types?
* Which layer of the virtualised environmend do you _not_ own in the different types?

## Part 4: Load balancing
Which types of load balancing exists?

If you had to design your server to be able to cope with a sudden rush of clients
sending HTTP requests, which load-balancing technique would you choose? Why?

