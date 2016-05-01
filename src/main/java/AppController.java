import com.mongodb.*;
import freemarker.template.Configuration;
import static spark.Spark.get;

public class AppController {
    private static final MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));


    public static void main(String[] args) {
        if(mongoClient == null)
            throw new IllegalStateException("MongoDb Client not initialized!");

        DB db = mongoClient.getDB("egen-userdb");
        new UserResource(new UserService(db));

    }

}