package Util;

import au.com.dius.pact.core.model.RequestResponseInteraction;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.util.HashMap;
import org.apache.log4j.Logger;

public class Util {

    static Logger logger = Logger.getLogger(Util.class);

    public static <T> T createClassFromMap(HashMap map, Class<T> c) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String jsonString = gson.toJson(map);
        return gson.fromJson(jsonString, c);
    }


    public static <T> T createClassFromObject(Object object, Class<T> c) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String jsonString = gson.toJson(object);
        return gson.fromJson(jsonString, c);
    }


    public static String createJsonFromClassObject(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String jsonString = gson.toJson(object);
        return jsonString;
    }


    public static void logCurlFromPact(PactVerificationContext context, HttpRequest request) {

        String bodyParam = ((RequestResponseInteraction) context.getInteraction()).getRequest().getBody().valueAsString();

        String bodyResponse = ((RequestResponseInteraction) context.getInteraction()).getResponse().getBody().valueAsString();

        String method = ((RequestResponseInteraction) context.getInteraction()).getRequest().getMethod();

        String baseUrl = "";
        if (method.equals("POST")) {
            baseUrl = ((HttpPost) request).getURI().toString();
        } else if (method.equals("GET")) {
            baseUrl = ((HttpGet) request).getURI().toString();
        }

        Header[] headers = request.getAllHeaders();

        String headersString = "";

        for (Header s : headers) {
            headersString = headersString + "--header " + "'" + s.getName() + ": " + s.getValue() + "'" + "\\" + "\n";
        }

        String curl = "" +
                "curl " +
                "'" + baseUrl + "' \\" + "\n" +
                headersString +
                "--data-binary " + "'" + bodyParam + "' \\" + "\n" +
                "--compressed \\" + "\n" +
                "--insecure \\" + "\n" +
                "--verbose" +
                "";

        logger.debug(curl + "\n\n " + bodyResponse + "\n ---- \n\n");
    }

}