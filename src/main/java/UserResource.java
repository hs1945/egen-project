/**
 * Created by Harjinder Singh on 4/30/2016.
 */

import spark.ResponseTransformer;

import static groovy.json.JsonOutput.toJson;
import static spark.Spark.*;


public class UserResource {

    private static final String API_CONTEXT = "/api/v1";

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
        setupEndpoints();
    }

    private void setupEndpoints() {

        post(API_CONTEXT + "/createUser", "application/json", (request, response) -> {
            String res = userService.createUser(request.body());
            response.body(res);
            return new ResponseMessage(res);
        }, new JsonTransformer());


        get(API_CONTEXT + "/getAllUsers", "application/json", (request, response)
                -> userService.getAllUsers(), new JsonTransformer());

        get(API_CONTEXT + "/user/:id","application/json", (request, response) -> {
            String id = request.params(":id");
            User user = userService.getUser(id);
            if (user != null) {
                return user;
            }
            response.status(400);
            return new ResponseMessage("No user with id '%s' found", id);
        }, new JsonTransformer());

        post(API_CONTEXT + "/updateUser/:id", "application/json", (request, response) ->{
            String id = request.params(":id");
            User user = userService.getUser(id);
            if (user != null) {
                User userUpdated = userService.updateUser(request.body());
                return userUpdated;
            }
            response.status(404);
            return new ResponseMessage("No user with id '%s' found", id);
        }, new JsonTransformer());

        after((req, res) -> {
            res.type("application/json");
        });

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseMessage(e)));
        });

    }


}
