# HTTPS certificate and reverse proxies
In this assignment we'll use Nginx as a reverse proxy to handle the ``TLS`` handshake to
establish a secure connection (HTTPS) between the server and clients. This requires two
things: A certificate and a proxy server.

## Installing Nginx
Nginx is a very very fast server which can act as a file server, proxy and much else.

For this assignment we'll use it as a proxy only. In Ubuntu systems you can install
the server by simply running ``sudo apt-get install nginx``.

## Assignment 1: Explaining Nginx
Nginx is installed with some default configuration. The configuration is located in
``/etc/nginx/``. Find the default configuration and open it with a text editor.

Write at least two lines for each question:

* Which ports are Nginx listening to per default?
* What happens when you do a ``HTTP GET`` request to that port?

## Assignment 2: Changing the HTML files
Because it's a web server Nginx actually serves ``.html`` by default. The files
are located in the directory listed in the configuration file.

Go to that directory and try to change the main ``HTML`` file. Do a new ``HTTP GET`` request
to the server. What happened?

## Assignment 3: Installing the HTTPS certificate
 
For this assignment we'll be using _Let's encrypt_. This is just one of many certificate authorities,
but it separates itself from many by being free. They have an installer for many different systems
available via [https://certbot.eff.org](https://certbot.eff.org).

Certificates are (normally) only issued to domains and Let's encrypt only support certificates
for domain names. Hence, we need a DNS (Domain Name Service) hack to persuade Let's encrypt
that you have a domain. For this we'll use the ``xip.io`` service.

The hack is simply that you 'invent' your own domain using your ``IP`` address.
So if your server has the ``IP`` 10.10.10.10 for instance, you cannot issue a certificate to
``10.10.10.10``, but you can issue a certificate for ``10.10.10.10.xip.io``!

The ``xip.io`` service simply just forwards all ``DNS`` requests to subdomains to the ``IP``
value of the subdomain. ``10.10.10.10.xip.io`` will simply be forwarded to the server with
the ``IP`` ``10.10.10.10``. This is also really handy when working and debugging network by the way.

Install the certificate on the server now by following the instructions on the certbot site above.

## Assignment 4: Installing the certificate in Nginx
The guide above should tell you how to install it into the Nginx configuration file. Now that you
have your certificate on your machine (you should have gotten this in the step above), you have to
1) include it in the Nginx configuration file and 2) re-route all traffic on port 80 to port 443.
If you haven't already done it, do it now by following the guide on the certbot site above.

## Conclusion
If you successfully made all of the above steps, you now have a secure and verified connection to
your server! Try to make a `HTTP GET`` request to your server in your browser, and see the beautiful
green lock left of the address bar.
 
Well done!
