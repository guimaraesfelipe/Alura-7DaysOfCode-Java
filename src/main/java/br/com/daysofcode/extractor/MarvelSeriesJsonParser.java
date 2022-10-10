package br.com.daysofcode.extractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarvelSeriesJsonParser implements JsonParser {

    private static final Pattern BEGIN_ARRAY = Pattern.compile(".*\"results\":");
    private static final Pattern END_ARRAY = Pattern.compile(".*\\]}}");

    @Override
    public List<Map<String, String>> parsear(String json) {

        int begin, end;

        Matcher matcher = BEGIN_ARRAY.matcher(json);

        try {
            matcher.find();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Dados n�o localizados.");
        } finally {
            begin = matcher.end();
        }

        matcher = END_ARRAY.matcher(json);

        try {
            matcher.find();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Dados n�o localizados.");
        } finally {
            end = matcher.end();
        }

        json = json.substring(begin, end);
        String[] resources = json.split("\\},\\{\"id\"");

        List<Map<String, String>> data = new ArrayList<>();

        for (String resource : resources) {
            Map<String, String> attribute = new HashMap<>();

            attribute.put("title", parseAttribute(resource, "title"));
            attribute.put("image", parseThumbnailAttribute(resource));

            data.add(attribute);

        }

        return data;
    }

    private String parseAttribute(String json, String attribute) {

        int positionInitAttribute = findInitialPositionOfAttribute(json, attribute);
        json = json.substring(positionInitAttribute);

        int positionEndAttribute = findFinalPositionOfAttribute(json, attribute);
        String attributeValue = json.substring(0 , positionEndAttribute);

        return cleanUp(attributeValue);
    }

    private int findFinalPositionOfAttribute(String json, String attribute) {
        Pattern endPattern = Pattern.compile(",");
        Matcher endMatcher = endPattern.matcher(json);

        if(!endMatcher.find()) {
            throw new IllegalStateException(attribute + " não encontrado");
        }
        int posEndAttribute = endMatcher.start();
        return posEndAttribute;
    }

    private int findInitialPositionOfAttribute(String json, String attribute) {
        Pattern beginPattern = Pattern.compile("\"" + attribute + "\":");

        Matcher beginMatcher = beginPattern.matcher(json);
        if(!beginMatcher.find()) {
            throw new IllegalStateException(attribute + " não encontrado");
        }

        int posIniAttribute = beginMatcher.end();
        return posIniAttribute;
    }

    private static String cleanUp(String attributeValue) {
        if(attributeValue.startsWith("\"")) {
            attributeValue = attributeValue.substring(1);
        }

        if(attributeValue.endsWith(",")) {
            attributeValue = attributeValue.substring(0, attributeValue.length() - 1);
        }

        if(attributeValue.endsWith("\"")) {
            attributeValue = attributeValue.substring(0, attributeValue.length() - 1);
        }

        return attributeValue.trim();
    }

    private String parseThumbnailAttribute(String json) {

        Pattern pattern = Pattern.compile("\"thumbnail\":\\{\"path\":\"");
        Matcher matcher = pattern.matcher(json);

        if(!matcher.find()) {
            throw new IllegalStateException("Thumbnail não encontrado");
        }

        int positionInitAttribute = matcher.end();
        String thumbnail_ext = json.substring(positionInitAttribute);

        pattern = Pattern.compile("\",\"extension\":\"");
        matcher = pattern.matcher(thumbnail_ext);

        if(!matcher.find()) {
            throw new IllegalStateException("Thumbnail extension não encontrado");
        }

        positionInitAttribute = matcher.start();
        String thumbnail = thumbnail_ext.substring(0 , positionInitAttribute);

        return cleanUp(thumbnail) + "." + thumbnail_ext.substring(matcher.end(), matcher.end() + 3);
    }

}