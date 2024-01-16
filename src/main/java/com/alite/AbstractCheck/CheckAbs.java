package com.alite.AbstractCheck;


import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.web.client.RestTemplate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckAbs {

//    public static final String regexAbsAtt = "<AbstractText(?=\\s)(?!(?:[^>\"\\']|\"[^\"]*\"|\\'[^\\']*\\')*?(?<=\\s)\\s*=)(?!\\s*/?>)\\s+(?:\".*?\"|\\'.*?\\'|[^>]*?)+>";
//    public static final Pattern patternAbsAt = Pattern.compile(regexAbsAtt);
//    public static final String regexAbsClose = "<[/]AbstractText>";
//    public static final Pattern patternAbsClose = Pattern.compile(regexAbsClose);
//    public static final String regex = "<AbstractText>";
//    public static final Pattern pattern = Pattern.compile(regex);
    public static String getAbstract(String id){
        String puid = null;
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?&db=pubmed&api_key=d37afa1adef240ff15ffacb7d4e2390a2d09&id="+id+"&retmode=xml&rettype=null&email=tanveer.ullacs@gmail.com";
        String result = restTemplate.getForObject(apiUrl, String.class);
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
}
