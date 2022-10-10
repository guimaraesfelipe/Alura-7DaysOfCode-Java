package br.com.daysofcode.extractor;

import br.com.daysofcode.model.MarvelSerie;

import java.util.ArrayList;
import java.util.List;

public class MarvelSeriesContentExtractor implements ContentExtractor {

    @Override
    public List<MarvelSerie> extract(String json) {
        List<MarvelSerie> series = new ArrayList<>();
        new MarvelSeriesJsonParser().parsear(json).forEach(value -> {
            series.add(new MarvelSerie(value.get("title"), value.get("image")));
        });

        return series;
    }
}
