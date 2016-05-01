/**
 * Created by Harjinder Singh on 4/30/2016.
 */

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final DB db;
    private final DBCollection collection;

    public UserService(DB db) {
        this.db = db;
        this.collection = db.getCollection("users");
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            users.add(new User((BasicDBObject) dbObject));
        }

        return users;
    }

    public String createUser(String body) {
        DBObject dbObject = (DBObject) JSON.parse(body);
        String id = (String)((BasicDBObject) dbObject).get("_id");
        User user = getUser(id);

        String response;
        if(user ==null) {
            try {
                collection.insert(dbObject);
                response = "Inserted Object";
            } catch (MongoException e) {
                response = e.toString();
            } catch (Exception e) {
                response = e.toString();
            }
        }else{
            response = "Already exists";
        }

        return response;
    }

    public User getUser(String id) {
        User u = null;
        BasicDBObject dbObject = (BasicDBObject) collection.findOne(new BasicDBObject("_id", id));
        if(dbObject!=null)
            u = new User(dbObject);

        return u;
    }

    public User updateUser(String body) {
        User user = new Gson().fromJson(body, User.class);

        DBObject query = new BasicDBObject("_id", user.getId());

        DBObject history = new BasicDBObject()
                .append("firstName", user.getFirstName())
                .append("lastName", user.getLastName())
                .append("email", user.getEmail())
                .append("address", user.getAddress())
                .append("company", user.getCompany());

        DBObject update = new BasicDBObject("$set", history);

        collection.update(query, update);

        return user;
    }

}
