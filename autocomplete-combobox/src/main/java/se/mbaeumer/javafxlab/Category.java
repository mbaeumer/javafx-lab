package se.mbaeumer.javafxlab;

public class Category {
    private String name;
    private long id;

    public Category(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
