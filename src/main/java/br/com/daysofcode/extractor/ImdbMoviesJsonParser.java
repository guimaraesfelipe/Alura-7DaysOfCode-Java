package br.com.daysofcode.extractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImdbMoviesJsonParser implements JsonParser {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    @Override
    public List<Map<String, String>> parsear(String json) {
        Matcher matcher = REGEX_ITEMS.matcher(json);

        try {
            matcher.find();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Dados n�o localizados.");
        }

        String[] resources = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> data = new ArrayList<>();

        for (String resource : resources) {
            Map<String, String> attribute = new HashMap<>();

            Matcher matcherAttributeJson = REGEX_ATRIBUTOS_JSON.matcher(resource);
            while (matcherAttributeJson.find()) {
                attribute.put(matcherAttributeJson.group(1), matcherAttributeJson.group(2));
            }

            data.add(attribute);

        }

        return data;
    }

}