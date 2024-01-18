package com.alite.AbstractCheck;


import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckAbs {

//    public static final String regexAbsAtt = "<AbstractText(?=\\s)(?!(?:[^>\"\\']|\"[^\"]*\"|\\'[^\\']*\\')*?(?<=\\s)\\s*=)(?!\\s*/?>)\\s+(?:\".*?\"|\\'.*?\\'|[^>]*?)+>";
//    public static final Pattern patternAbsAt = Pattern.compile(regexAbsAtt);
//    public static final String regexAbsClose = "<[/]AbstractText>";
//    public static final Pattern patternAbsClose = Pattern.compile(regexAbsClose);
//    public static final String regex = "<AbstractText>";
//    public static final Pattern pattern = Pattern.compile(regex);
    public static String getAbstract(String id) throws IOException {
        String puid = null;

        String result = getString(id);
        result = StringEscapeUtils.unescapeHtml4(result);
        String res = null;
        result = ignoreNlm(result);
//        System.out.println(result);
        return extractTest(result);
    }
    public static String extractTest(String xmlContent) {
        String tagName = "AbstractText";
        // Use regex pattern to extract content and Label attribute from all occurrences
        String regex = "<" + tagName + "(?:\\s+Label=\"(.*?)\")?>(.*?)</" + tagName + ">";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(xmlContent);

        String res = "";

        while (matcher.find()) {
            String label = matcher.group(1); // label may be null if not present
            String content = matcher.group(2).trim();

            String getLebel = (label != null ? label.trim()+":" : " ");
//            System.out.println("Label: " + (label != null ? label.trim() : " "));
//            System.out.println("Content: " + content);
//            System.out.println("---");
            res += getLebel+content;
        }
//        System.out.println(res.trim());
        return res.trim();
    }
    static String ignoreNlm(String inputText){
        // Define the pattern to match
        String patternToIgnore = " NlmCategory=\"[^\"]*\"";

        // Create a pattern object
        Pattern pattern = Pattern.compile(patternToIgnore);

        // Create a matcher object
        Matcher matcher = pattern.matcher(inputText);

        // Replace matched patterns with an empty string
        return matcher.replaceAll("");

    }

    public static String getString(String id) throws IOException {
        // Specify the API URL
        String apiUrl = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?&db=pubmed&api_key=d37afa1adef240ff15ffacb7d4e2390a2d09&id="+id+"&retmode=xml&rettype=null&email=tanveer.ullacs@gmail.com";

        // Create a URL object
        URL url = new URL(apiUrl);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Process the response data
//            System.out.println("API Response: " + response.toString());
            return response.toString();
        }
        return null;
    }
}
