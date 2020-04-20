package edu.Hillel.models;

public enum Courses {
    JAVA(1, "Java"),
    PYTHON(2, "Python"),
    JS(3, "JavaScript"),
    CuttingAndSewing(4, "Cutting and sewing");

    private int id;
    private String name;

    Courses(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
