/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.webproject;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures the application to automatically discover for servlet classes and
 * places them under the "rest/" URL prefix.
 *
 * @author jens
 */
@ApplicationPath("/rest")
public class RestApplication extends Application {
    
}