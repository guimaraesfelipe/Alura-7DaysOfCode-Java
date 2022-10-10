package br.com.daysofcode.model;

public abstract class Content implements Comparable<Content>{
    protected String title;
    protected String image;

    public Content(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int compareTo(Content other) {
        return title.compareTo(other.getTitle());
    }

}
