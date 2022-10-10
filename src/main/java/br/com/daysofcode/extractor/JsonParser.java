package br.com.daysofcode.extractor;

import java.util.List;
import java.util.Map;

public interface JsonParser {

    List<Map<String, String>> parsear(String json);

}
