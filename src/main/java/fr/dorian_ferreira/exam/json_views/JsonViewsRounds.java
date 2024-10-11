package fr.dorian_ferreira.exam.json_views;

public class JsonViewsRounds {
    public interface CreatedAt { }
    public interface Points { }
    public interface Time { }
    public interface Distance { }
    public interface Id { }

    public interface RoundShow extends
            Id,
            Points,
            Time,
            Distance,
            CreatedAt
    { }
}
