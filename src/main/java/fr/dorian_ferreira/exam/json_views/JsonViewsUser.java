package fr.dorian_ferreira.exam.json_views;

public class JsonViewsUser {
    public interface Uuid { }
    public interface Email { }
    public interface IsAdmin { }
    public interface Active { }
    public interface Games { }
    public interface Level { }
    public interface CreatedAt { }
    public interface BirthedAt { }
    public interface Avatar { }
    public interface Username { }


    public interface UserShow extends
            Uuid,
            Username,
            Email,
            Avatar,
            BirthedAt,
            CreatedAt,
            Level,
            IsAdmin
    { }
}
