/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.ud867.data.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;
import com.ud867.data.utils.jokeFetcher;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.data.ud867.com",
                ownerName = "backend.data.ud867.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "sayJoke")
    public MyBean sayJoke(){
        MyBean response = new MyBean();
        response.setData(jokeFetcher.getJoke());
        return response;
    }
}
