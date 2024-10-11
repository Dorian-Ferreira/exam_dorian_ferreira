package fr.dorian_ferreira.exam.json_views;

public class JsonViews {
    public interface CustomResponse { }
    public interface CustomPageResponse extends CustomResponse { }

    public interface UserShow extends CustomResponse,
        JsonViewsUser.UserShow
    { }

    public interface MapList extends CustomResponse,
            JsonViewsMap.MapList
    { }

    public interface MapShow extends CustomResponse,
            JsonViewsMap.MapShow
    { }
}
