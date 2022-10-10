package br.com.daysofcode.extractor;

import br.com.daysofcode.model.ImdbMovie;

import java.util.ArrayList;
import java.util.List;

public class ImdbMoviesContentExtractor implements ContentExtractor {

    @Override
    public List<ImdbMovie> extract(String json) {
        List<ImdbMovie> movies = new ArrayList<>();
        new ImdbMoviesJsonParser().parsear(json).forEach(value -> {
            String image = value.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            movies.add(new ImdbMovie(value.get("title"), image));
        });

        return movies;
    }
}
