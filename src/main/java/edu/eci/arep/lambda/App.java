package edu.eci.arep.lambda;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import spark.Spark;

/*
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Spark.port(8080);
        Spark.get("/",
                (req, res) -> "<!DOCTYPE html>" + "<html lang=\"en\">" + "<head>" + "<meta charset=\"UTF-8\">"
                        + "<title>Math</title>" + "</head>" + "<body>" + "<form action=\"/square\" "
                        + "<label for=\"n\">Number</label>" + "<input id=\"n\" name=\"value\" type=\"number\">"
                        + "<input type=\"submit\">" + "</form>" + "</body>" + "</html>");

        Spark.get("/square", (req, res) -> readFromUrl(req.queryParams("value")));

    }
    
    public static String readFromUrl(String value) {
        try {
            URL url = new URL("https://4esupt6ft2.execute-api.us-east-1.amazonaws.com/Beta?value=" + value);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            return reader.readLine();
        } catch (Exception x) {
            System.err.println(x);
        }
        return "-1";
    }
}
