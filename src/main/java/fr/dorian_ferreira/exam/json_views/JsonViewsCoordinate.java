    package fr.dorian_ferreira.exam.json_views;

public class JsonViewsCoordinate {
    public interface Id { }
    public interface Longitude { }
    public interface Latitude { }

    public interface CoordinateShow extends
            Id,
            Longitude,
            Latitude
    { }
}
