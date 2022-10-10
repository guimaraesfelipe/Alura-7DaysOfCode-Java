package br.com.daysofcode.extractor;

import br.com.daysofcode.model.Content;

import java.util.List;

public interface ContentExtractor {

    List<? extends Content> extract(String json);

}
