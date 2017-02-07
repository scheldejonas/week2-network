/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.webproject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Creates a new rest service under the "/hello" URL (full path: "/rest/hello").
 */
@Path("/hello")
public class NewServlet {

    /**
     * A HTTP GET method which returns "Hello world". JAX-RS wraps this in a
     * HTTP response for us. Thanks JAX-RS.
     *
     * @return A String which will be wrapped and sent as a Http response.
     */
    @GET
    public String get() {
        return "Hello world";
    }

}
