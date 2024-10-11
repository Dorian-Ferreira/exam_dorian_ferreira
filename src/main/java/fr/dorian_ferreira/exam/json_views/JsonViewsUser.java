package fr.dorian_ferreira.exam.json_views;

public class JsonViewsUser {
    public interface Uuid { }
    public interface Email { }
    public interface IsAdmin { }
    public interface Active { }


    public interface UserShow extends
            Uuid,
            Email
    { }
}
