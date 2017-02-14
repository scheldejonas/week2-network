## Exercises - HTTP

###### Monitoring HTTP Headers 1 - Schelde Jonas

- Question

Create a new NetBeans project (type Web-project -> select all default values in the wizard)
For this exercise we will just use the default index.html generated by NetBeans).

Press the run button. When the file is shown in the browser (Chrome), open the developer window (Ctrl-shift-j) and press F5

Observe and explain each of the values monitored (use view source to see the plain messages).

Hints: In order to better observe the values related to Cashing you might need to:

Go back to NetBeans and rename your file to index1.html

Go back to your browser and (while the developer window is open) change the url to point to the new file.

Observe the values

Press F5 and observe the values again.

Explain what you see.

- Solution

Created Gradle + Web java project in IntelliJ IDEA

Edited Run configuration, to use __Apache Tomcat/9.0.0.M13__

GET /index.jsp HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Encoding: gzip, deflate, sdch, br
Accept-Language: da,en-US;q=0.8,en;q=0.6,de;q=0.4
Cookie: JSESSIONID=E35071C69C78D6D504A718120D17E6E3

I change the index file to index1.jsp

It gave me this HTTP request Headers

GET /index1.jsp HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Encoding: gzip, deflate, sdch, br
Accept-Language: da,en-US;q=0.8,en;q=0.6,de;q=0.4
Cookie: JSESSIONID=E35071C69C78D6D504A718120D17E6E3

The first thing to notice, is of course the Http Request line with:
- GET : what type of command or verb the request is sent from client of reason.
- PATH : "/index.jsp" or "/" or "/index1.jsp" is the uri request on top of the current url, it can also
include paramterers, anchors, type of http and port, so a full version would be
http://domain.com:port/uri?parameter=value&paramter=value#anchorname
- HTTP : version 1.1, is of course for the server to know how to know what "protocol language" to use.
- Header : here the parameters is example, host, cache-control, cookie and so on.
- A blank line : to know for the byte reader of the http requst reciever, where to seperate
header and body
- Body : Here the html page will be, when the response is sent, when sending the request, 
the body is empty

------

###### Monitoring HTTP Headers 2

- Question

Add an image to the page

Add an external style sheet to the page (<link rel="stylesheet" type="text/css" href="myStyle.css">)

Reload the page again, observe the request being made, and explain the purpose of the Connection: header.

- Solution

Inserted image to new folder /static/assets/images

Inserted html img tag on new index2.jsp page

Created app.css file and placed in /static/assets/

Inserted html link tag in head of html page index2.jsp

Ran server

GET /index2.jsp HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Pragma: no-cache
Cache-Control: no-cache
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Encoding: gzip, deflate, sdch, br
Accept-Language: da,en-US;q=0.8,en;q=0.6,de;q=0.4
Cookie: JSESSIONID=72906C364C25162823AB0BEFC50587F5

GET /assets/app.css HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Pragma: no-cache
Cache-Control: no-cache
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36
Accept: text/css,*/*;q=0.1
Referer: http://localhost:8080/index2.jsp
Accept-Encoding: gzip, deflate, sdch, br
Accept-Language: da,en-US;q=0.8,en;q=0.6,de;q=0.4
Cookie: JSESSIONID=72906C364C25162823AB0BEFC50587F5

GET /assets/images/billede-mangler.png HTTP/1.1
Host: localhost:8080
Connection: keep-alive
Pragma: no-cache
Cache-Control: no-cache
User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36
Accept: image/webp,image/*,*/*;q=0.8
Referer: http://localhost:8080/index2.jsp
Accept-Encoding: gzip, deflate, sdch, br
Accept-Language: da,en-US;q=0.8,en;q=0.6,de;q=0.4
Cookie: JSESSIONID=72906C364C25162823AB0BEFC50587F5

The connection header is made "keep-alive" in main request and two next sub requests, which
of course, is just requests of natural character.

The second value for "Connection" parameter is "close" and is to tell the connection-token
no more is valid, it is to tell the session is stopped.

--------

###### Monitoring HTTP Headers 3

- Question

In Google Chrome enter this address (with the developer window open, and exactly as it is spelled):

http://www.fronter.com/cphbusiness

Explain, as well as you can (at a conceptual level), the first three request monitored

- Solution

The three requests made is named:

##### Request 1

```
Request URL:https://fronter.com/cphbusiness/
Request Method:GET
Status Code:200 
Remote Address:104.16.70.26:443

:authority:fronter.com
:method:GET
:path:/cphbusiness/
:scheme:https
accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
accept-encoding:gzip, deflate, sdch, br
accept-language:da,en-US;q=0.8,en;q=0.6,de;q=0.4
cache-control:max-age=0
cookie:__cfduid=d61522aa2ff1790f5fae3fe9615eff7761481634907
upgrade-insecure-requests:1
user-agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36
```

##### Response 1

```
cache-control:no-st`ore, no-cache, must-revalidate
cf-ray:32e8d1e288ae3cdd-CPH
content-encoding:gzip
content-type:text/html
date:Thu, 09 Feb 2017 16:50:21 GMT
expires:Mon, 26 Jul 1997 05:00:00 GMT
last-modified:Thu, 09 Feb 2017 16:50:21 GMT
p3p:CP="CAO CUR TAIa OUR NOR CNT PRE"
pragma:no-cache
server:cloudflare-nginx
status:200
strict-transport-security:max-age=15552000; includeSubDomains; preload
vary:Accept-Encoding
```

##### Request 2

```
Request URL:https://cdn.fronter.com/volY12-cache/cache/css/skins/Classic/skin.1ac1f16d4e82d07a4223c148ead33e21.css
Request Method:GET
Status Code:200  (from disk cache)
Remote Address:104.16.230.105:443

Provisional headers are shown
Referer:https://fronter.com/cphbusiness/
User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36
```

##### Response 2

```
access-control-allow-methods:GET
access-control-allow-origin:*
cache-control:public, max-age=86400
cf-cache-status:HIT
cf-ray:32e8d1a30c323d67-CPH
content-encoding:gzip
content-type:text/css
date:Thu, 09 Feb 2017 16:50:11 GMT
etag:W/"589be3a1-28d59"
expires:Fri, 10 Feb 2017 16:50:11 GMT
last-modified:Thu, 09 Feb 2017 03:36:01 GMT
server:cloudflare-nginx
status:200
vary:Accept-Encoding
```

##### Request 3

```
Request URL:https://cdn.fronter.com/volY12-cache/cache/js/links/code/customdialog.3726ad709102390354952c31e9345253.js
Request Method:GET
Status Code:200  (from disk cache)
Remote Address:104.16.230.105:443

Provisional headers are shown
Referer:https://fronter.com/cphbusiness/
User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36
```

##### Response 3

```
access-control-allow-methods:GET
access-control-allow-origin:*
cache-control:public, max-age=86400
cf-cache-status:HIT
cf-ray:32e8d1a30c343d67-CPH
content-encoding:gzip
content-type:application/javascript
date:Thu, 09 Feb 2017 16:50:11 GMT
etag:W/"4ffd6d52-5a4"
expires:Fri, 10 Feb 2017 16:50:11 GMT
last-modified:Wed, 11 Jul 2012 12:10:58 GMT
server:cloudflare-nginx
status:200
vary:Accept-Encoding
```

##### My answer

Response on the last 2 requests are caught from the cache of my computers disk.

I have of course visisted this url before, and there for the cache is here to take over.

and load the css file and js file, which is request in 2. and 3. faster.

----

###### Get HTTP Request Headers on the Server

- Question

We have just seen that a HTTP request from a Browser typically includes a lot of headers with information related to the client.

This information is available to the servlet via the request object. 

Create a Servlet, which should output this information in a table as sketched below (or in any way you like, don’t think about presentation).

Hints: Use the request objects getHeaderXXX methods.

- Solution

I implementet this code an got a lot of delicious values found in the request object.

```
    private void processGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.printf("Request Line | Path Info: %s \n",request.getPathInfo() );

        System.out.printf("Request Line | Servlet Path: %s \n",request.getServletPath() );
        System.out.printf("Request Line | Session Creation Time: %s \n",request.getSession().getCreationTime() );
        System.out.printf("Request Line | Session id: %s \n",request.getSession().getId() );
        System.out.printf("Request Line | Session Last Accessed Time: %s \n",request.getSession().getLastAccessedTime() );
        System.out.printf("Request Line | Session Max Inactive Interval: %s \n",request.getSession().getMaxInactiveInterval() );
        System.out.printf("Request Line | Session Servlet Context Path: %s \n",request.getSession().getServletContext().getContextPath() );
        System.out.printf("Request Line | Local Addresss: %s \n",request.getLocalAddr() );
        System.out.printf("Request Line | Is Requested Session id From Cookie: %s \n",request.isRequestedSessionIdFromCookie() );
        System.out.printf("Request Line | Is Requested Session id From Url: %s \n",request.isRequestedSessionIdFromURL() );
        System.out.printf("Request Line | Is Requested Session id Valid: %s \n",request.isRequestedSessionIdValid() );
        System.out.printf("Request Line | Path Info: %s \n",request.getPathInfo() );

        System.out.printf("Request Line | Protocol: %s \n",request.getProtocol() );
        System.out.printf("Request Line | Request URI: %s \n",request.getRequestURI() );
        System.out.printf("Request Line | Context Path: %s \n",request.getContextPath() );
        System.out.printf("Request Line | Auth Type: %s \n",request.getAuthType() );
        System.out.printf("Request Line | Local Port: %s \n",request.getLocalPort() );
        System.out.printf("Request Line | Method: %s \n",request.getMethod() );
        System.out.printf("Header | Host: %s \n",request.getHeader("host") );
        System.out.printf("Header | Connection: %s \n",request.getHeader("connection") );
        System.out.printf("Header | cache-control: %s \n",request.getHeader("cache-control") );
        System.out.printf("Header | Upgrade-Insecure-Requests: %s \n",request.getHeader("Upgrade-Insecure-Requests") );
        System.out.printf("Header | User-Agent: %s \n",request.getHeader("user-agent") );
        System.out.printf("Header | Accept: %s \n",request.getHeader("accept") );
        System.out.printf("Header | Accept-Encoding: %s \n",request.getHeader("accept-encoding") );
        System.out.printf("Header | Accept-Language: %s \n",request.getHeader("accept-language") );
        System.out.printf("Header | Cookie: %s \n",request.getHeader("cookie") );

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index_two.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```

---

###### Get/Post parameters

- Question

Create a new html-file in the web-project made in exercise 1.

Add a form to the file, including two text input boxes and a submit button as sketched below:

Add an extra input field to the form with type=”hidden”, name=”hidden” and value=12345678.

Add the value “#” for the forms action attribute.

Set the forms method-attribute to the value “GET” (actually the default value) and test the form. 

Observe what happens in your browsers address field.

Change the forms method-attribute to the value “POST” and test the form. 

Observe the change in your browsers address field. See whether you can find out, how the parameters are passed in, for a POST request.

__Make sure you understand the difference between GET and POST, also the non-idempotent part.__

- Solution

I implementet this code

```
        <div class="row">
            <form method="get" action="#" class="col s12">
                <input type="hidden" name="hidden" value="12345678" />
                <div class="row">
                    <div class="input-field col s6">
                        <input id="first_name" name="firstName" type="text" class="validate" />
                        <label for="first_name">First Name</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="last_name" name="lastName" type="text" class="validate" />
                        <label for="last_name">Last Name</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12 ">
                        <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <form method="post" action="#" class="col s12">
                <input type="hidden" name="hidden" value="12345678" />
                <div class="row">
                    <div class="input-field col s6">
                        <input id="first_name" name="firstName"  type="text" class="validate" />
                        <label for="first_name">First Name</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="last_name" name="lastName" type="text" class="validate" />
                        <label for="last_name">Last Name</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12 ">
                        <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
```

I found out on the get method this came:

http://localhost:8080/index?hidden=12345678&firstName=Jonas&lastName=Schelde&action=#

I found out on the post method this came:
 
http://localhost:8080/index?hidden=12345678&firstName=Jonas&lastName=Schelde&action=#
 
So my conclusion is the url Query String Parameters is the same. But how do it send the names?
 
Lokking to the network tab, in chrome dev tools, i see that there is the form data, sent on POST

Which means it can me catchable from the body data, instead of the url parameters.

Which means post can send parameters and values in the body of the request, in opposite to get request.

---

##### Session and Cookies

For the next two exercises/demoes you should create a new web-project.

Both the demoes uses a Servlet and this will hopefully be the last time where you will use a Servlet for presentation 

Sessions (Session Cookies) Class exercise/demo

1. In your web project use the wizard to generate a new Servlet

2. Enter SessionDemo as the name of the Servlet and servlets as package name

3. Right click the file and select Run to see “what is does”

4. Change the generated processRequest(..) method as sketched below.

```
protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  String name = request.getParameter("name");
  if (name != null) {
   request.getSession().setAttribute("name", name);
  } else {
   name = (String) request.getSession().getAttribute("name");
  }
  response.setContentType("text/html;charset=UTF-8");
  try (PrintWriter out = response.getWriter()) {
   out.println("<!DOCTYPE html>");
   out.println("<html>");
   out.println("<head>");
   out.println("<title>Servlet SessionDemo</title>");
   out.println("</head>");
   out.println("<body>");
   if (name != null) {
    name = (String) request.getSession().getAttribute("name");
    out.println("<p> Welcome " + name + " !</p>");
   } else {
    out.println("<h2>Please enter your name, and submit</h2>");
    out.println("<form action='SessionDemo'>");
    out.println("<input type='input' name='name'>");
    out.println("<input type='submit'></form>");
   }
   out.println("</body>");
   out.println("</html>");
  }
}
```

5. Enter your name and press submit, copy the URL in the browser into your clipboard, close the tab (but not the browser) and load the page again in a new tab using the URL in the clipboard.

6. While doing the things in step e, you should monitor the content of your local cookies and the HTTP requests being sent, using the development tools in Chrome.

7. Most import part of this exercise:

Explain (on paper) using both words and images how the Server can maintain state between subsequent calls even when using a stateless protocol

The posibillity is here that with the same url, it takes the same cookie ID even thoug i change tab.

And there for because html is stateless, it makes it possible to keep a session, which makes it have a sort of state, when on a web domain as user. through the cookie id,
stashed on your local computer.

---

##### Persistent Cookies

- Question

1. In your web project use the wizard to generate a new servlet
2. Enter CookieDemo as the name of the Servlet and servlets as package name
3. Change the generated processRequest(..) method as sketched below.

```
protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 String name = request.getParameter("name");
 if (name != null) {
  Cookie cookie = new Cookie("username", name);
  cookie.setMaxAge(60 * 60 * 24 * 365);
  response.addCookie(cookie);
 }
 Cookie[] cookies = request.getCookies();
 if (cookies != null) {
  for (Cookie cookie: request.getCookies()) {
   if (cookie.getName().equals("username")) {
    name = cookie.getValue();
   }
  }
 }
 response.setContentType("text/html;charset=UTF-8");
 try (PrintWriter out = response.getWriter()) { /* TODO output your page here. You may use following sample code. */
  out.println("<!DOCTYPE html>");
  out.println("<html>");
  out.println("<head>");
  out.println("<title>Servlet CookieDemo</title>");
  out.println("</head>");
  out.println("<body>");
  if (name != null) {
   out.println("<p> Welcome " + name + " !</p>");
  } else {
   out.println("<h2>Please enter your name, and submit</h2>");
   out.println("<form action='CookieDemo'>");
   out.println("<input type='input' name='name'>");
   out.println("<input type='submit'></form>");
  }
  out.println("</body>");
  out.println("</html>");
 }
}
```

4. Enter your name and press submit, copy the URL in the browser into your clipboard, close the tab (but not the browser) and load the page again in a new tab using the URL in the clipboard.

5. Now close your browser (you could even close your laptop, but don’t ;-) , open it again and load the page again using the URL in the clipboard

6. While doing the things in step e, you should monitor the content of your local cookies and the HTTP requests being sent, using the development tools in Chrome.

7. Most import part of this exercise:

8. Explain (on paper) how Cookies can be used to maintain “state” on the client between subsequent calls to a server, even when a browser has been closed down.

- Solution

Cookies, can be used to know what is recieved from the server before.