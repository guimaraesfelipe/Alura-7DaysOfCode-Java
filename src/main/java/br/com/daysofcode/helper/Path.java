package br.com.daysofcode.helper;

import java.net.URI;

public enum Path {
    IMDB_URL {
        @Override
        public URI generate(String endpoint) {
            String mainPath = "https://imdb-api.com/en/API/";
            return URI.create(mainPath + endpoint + "/" + Key.IMDB_KEY.get());
        }
    },
    MARVEL_URL {
        @Override
        public URI generate(String endpoint) {
            String mainPath = "https://gateway.marvel.com:443/";
            return URI.create(mainPath + endpoint + Key.MARVEL_KEY.get());
        }
    };

    public abstract URI generate(String endpoint);
}
