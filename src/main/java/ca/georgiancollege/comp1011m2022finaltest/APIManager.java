package ca.georgiancollege.comp1011m2022finaltest;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class APIManager
{
    /********************* SINGLETON SECTION *****************************/
    // Step 1. private static instance member variable
    private static APIManager m_instance = null;
    // Step 2. make the default constructor private
    private APIManager() {}
    // Step 3. create a public static entry point / instance method
    public static APIManager Instance()
    {
        // Step 4. Check if the private instance member variable is null
        if(m_instance == null)
        {
            // Step 5. Instantiate a new APIManager instance
            m_instance = new APIManager();
        }
        return m_instance;
    }
    /*********************************************************************/

    /* TODO -- Fill in with useful methods to read Customer information */

    public Customer getCustomer () throws IOException {
        URL url = getClass().getResource("customers.json");

        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(String.valueOf(url)));

            return gson.fromJson(reader, Customer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
