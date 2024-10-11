package fr.dorian_ferreira.exam.route;

public class UrlRoute {

    private final static String BASE_API = "/api";

    public final static String CREATE = "/create";
    public final static String EDIT = "/edit";
    public final static String DELETE = "/delete";
    public final static String SEARCH = "/search";
    public final static String ADMIN = "/admin";
    public final static String BASE_ADMIN = BASE_API + ADMIN;

    public final static String LOGIN = BASE_API + "/login";
    public final static String REGISTER = BASE_API + "/register";

    public final static String BASE_USER = BASE_API + "/user";
    public final static String BASE_MAP = BASE_API + "/map";
    public final static String BASE_GAME = BASE_API + "/game";
    public final static String BASE_ROUND = BASE_API + "/round";
    public final static String BASE_COORDINATE = BASE_API + "/coordinate";

    public final static String USER_PROFILE = BASE_USER + "/me";
    public final static String USER_ACTIVATION = BASE_USER + "/activation";
    public final static String USER_EDIT = BASE_USER + EDIT;
    public final static String USER_DELETE = BASE_USER + DELETE;

    public final static String MAP_BEST = BASE_MAP + "/best";

    public final static String GAME_SCORES = BASE_GAME + "/scores";
    public final static String GAME_LAST = BASE_GAME + "/last";

}
