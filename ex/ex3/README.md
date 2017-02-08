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
Now that you
have your certificate on your machine (you should have gotten this in the step above), you have to
1) include it in the Nginx configuration file and 2) re-route all traffic on port 80 to port 443.

First of all, let's include your certificates in Nginx. From the step above you should have a 
certificate in the folder ``/etc/letsencrypt/live/[IP].xip.io/`` (where ``[IP]`` is the IP of the
server). Here you'll find two important files: your public key and your private key. The public
key is in the file ``fullchain.pem`` (``.pem`` for permission) and your private key is in
``privkey.pem``. Never _ever_ **ever** move your private key outside of your server. Ever.

### 4.1 Enabling TLS/SSL 

So, first of all you need to tell Nginx to enable TLS/SSL. Go to the Nginx configuration file at
``/etc/nginx/sites-enabled/default`` and look at line 17:

    listen 80 default_server;
    
Change this to listen to the ``HTTPS`` port and append ``ssl`` after the port. So the line should say:

    listen XXX ssl default_server;
    
Where XXX is the port for HTTPS traffic. _Actually_ this is already in the file in line 22. So an
alternative to above is to comment out the line listening to port 80 (line 17) and comment in the line 22.
Do not write the line above _and_ comment out line 22. Choose one :-)

### 4.2 Using your certificate
Next we need to tell Nginx where to find the certificates we just created. After your ``listen``
code (somewhere after line 23, where doesn't really matter), create a new line saying:

    ssl on;
    ssl_certificate /etc/letsencrypt/live/XX.XX.XX.XX.xip.io/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/XX.XX.XX.XX.xip.io/privkey.pem;

Where ``XX.XX.XX.XX`` is the IP of your server. Save the file. Then restart the server with the
command ``sudo service nginx restart``.

Write two lines about:

1. The difference between the ``fullchain.pem`` and ``privkey.pem`` file.
2. Why is there no longer traffic on port 80?

**Please note:** If you used the ``--test-cert`` **you will get a warning**. And that's fine. If you 
want to fix it, try running ``letsencrypt`` again, but without the ``--test-cert`` command to get a
new non-test certificate.

### 4.3 (Optional) Redirecting HTTPtraffic to HTTPS
If you'd like to do something extra, you can try to re-route regular ``HTTP`` traffic to the ``HTTPS`` port.
There's a nice tutorial on what to do here: [https://www.cyberciti.biz/faq/linux-unix-nginx-redirect-all-http-to-https/](https://www.cyberciti.biz/faq/linux-unix-nginx-redirect-all-http-to-https/)

## Conclusion
If you successfully made all of the above steps, you now have a secure and verified connection to
your server! Try to make a `HTTPS GET`` request to your server in your browser, and see the beautiful
green lock left of the address bar.
 
Well done!
