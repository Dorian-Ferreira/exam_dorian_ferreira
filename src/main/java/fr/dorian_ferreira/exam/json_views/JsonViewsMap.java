package fr.dorian_ferreira.exam.json_views;

public class JsonViewsMap {
    public interface CreatedAt { }
    public interface Name { }
    public interface Id { }

    public interface MapList extends
            Id,
            Name
    { }

    public interface MapShow extends
            Id,
            Name,
            CreatedAt
    { }
}
