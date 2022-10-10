package br.com.daysofcode;

import br.com.daysofcode.extractor.ContentExtractor;
import br.com.daysofcode.extractor.ImdbMoviesContentExtractor;
import br.com.daysofcode.extractor.MarvelSeriesContentExtractor;
import br.com.daysofcode.model.Content;
import br.com.daysofcode.helper.Path;
import br.com.daysofcode.model.ImdbMovie;
import br.com.daysofcode.util.*;

import java.io.*;
import java.net.URI;
import java.util.Collections;
import java.util.List;

public class DaysOfCodeApplication {

    public static void main(String[] args) {

        try {
            run(Path.IMDB_URL.generate("Top250Movies"), new ImdbMoviesContentExtractor()); // imdb api
            run(Path.MARVEL_URL.generate("v1/public/series?"), new MarvelSeriesContentExtractor()); // marvel api
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("Não foi possivel gerar o arquivo content.html", ex);
        }

    }

    public static void run(URI path, ContentExtractor extractor) throws FileNotFoundException {

        List<? extends Content> contents;
        try {
            contents = extractor.extract(ConnectionApi.request(path));
        } catch (IOException | InterruptedException | NullPointerException ex) {
            throw new RuntimeException("Não foi possivel extrair a lista de conteúdo do IMDB", ex);
        }

        Collections.sort(contents);

        PrintWriter writer = new PrintWriter(String.valueOf(contents.get(0).getClass()).split("\\.")[4] + "-content.html");
        new HtmlGenerator(writer).generate(contents);
        writer.close();
    }

}
