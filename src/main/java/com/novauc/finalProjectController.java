package com.novauc;

import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dangelojoyce on 4/10/17.
 */
@RestController
public class finalProjectController implements FinalProjectInterface {
    public String eStore ()throws IOException, JAXBException {

            Store store = new Store();
            String uri =
                    "\"http://api.walmartlabs.com/v1/stores?format=json&zip=20744&apiKey=c3exxssx4eme5j56s5zk7xg7\"";
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            JSONParser jp = JSONParser.class.cast(store);
            InputStream json = connection.getInputStream();



            System.out.println("This is:" + json);
            System.out.println("This is" + jp);

            connection.disconnect();


            return "";
        }
    }





