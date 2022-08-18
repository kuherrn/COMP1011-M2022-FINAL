package ca.georgiancollege.comp1011m2022finaltest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
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
import java.util.List;

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

    public CustomerArrayList getCustomerList () throws IOException {
        String filePath = new File("COMP1011-M2022-Final-Test/customers.json").getAbsolutePath();

        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            return gson.fromJson(reader, CustomerArrayList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
