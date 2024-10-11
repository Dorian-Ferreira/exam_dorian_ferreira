package fr.dorian_ferreira.exam.json_views;

public class JsonViewsGame {
    public interface CreatedAt { }
    public interface Id { }
    public interface Rounds { }
    public interface Map { }
    public interface User { }
    public interface NbRounds { }
    public interface HasZoom { }
    public interface HasPan { }
    public interface HasMove { }
    public interface MaximumTime { }
    public interface TotalPoints { }

    public interface GameList extends
            Id,

            User,
            JsonViewsUser.Username,
            JsonViewsUser.Uuid,
            JsonViewsUser.Level,

            CreatedAt,
            NbRounds,
            TotalPoints
    { }

    public interface GameShow extends
            MaximumTime,
            HasMove,
            HasPan,
            HasZoom,
            CreatedAt,
            NbRounds,
            TotalPoints,

            Rounds,
            JsonViewsRounds.Points,
            JsonViewsRounds.Time,
            JsonViewsRounds.Distance
    { }
}
