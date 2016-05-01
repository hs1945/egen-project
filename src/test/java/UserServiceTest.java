import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import spark.Spark;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Harjinder Singh on 4/30/2016.
 */
public class UserServiceTest {

    static final String url = "http://localhost:4567/api/v1/";

    @org.junit.Before
    public void setUp() throws Exception {
        AppController.main(null);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        Spark.stop();
    }


    @org.junit.Test
    public void createUser() throws Exception {
        String body = "{  \n" +
                "   \"_id\":\"1630215c-283713-4434-aab4-9d534d8nbbb\",\n" +
                "   \"firstName\":\"Dorris\",\n" +
                "   \"lastName\":\"Keeling\",\n" +
                "   \"email\":\"Darby_Leffler68@gmail.com\",\n" +
                "   \"address\":{  \n" +
                "      \"street\":\"193 Talon Valley\",\n" +
                "      \"city\":\"South Tate furt\",\n" +
                "      \"zip\":\"47069\",\n" +
                "      \"state\":\"IA\",\n" +
                "      \"country\":\"US\"\n" +
                "   },\n" +
                "   \"dateCreated\":\"2016-03-15T07:02:40.896Z\",\n" +
                "   \"company\":{  \n" +
                "      \"name\":\"Denesik Group\",\n" +
                "      \"website\":\"http://jodie.org\"\n" +
                "   },\n" +
                "   \"profilePic\":\"http://lorempixel.com/640/480/people\"\n" +
                "}";

        String body2 = "{  \n" +
                "   \"_id\":\"1630215c-283713-4434-aab4-9d534d8nbbb\",\n" +
                "   \"firstName\":\"Dorris\",\n" +
                "   \"lastName\":\"Keeling\",\n" +
                "   \"email\":\"Darby_Leffler68@gmail.com\",\n" +
                "   \"address\":{  \n" +
                "      \"street\":\"193 Talon Valley\",\n" +
                "      \"city\":\"South Tate furt\",\n" +
                "      \"zip\":\"47069\",\n" +
                "      \"state\":\"IA\",\n" +
                "      \"country\":\"US\"\n" +
                "   },\n" +
                "   \"dateCreated\":\"2016-03-15T07:02:40.896Z\",\n" +
                "   \"company\":{  \n" +
                "      \"name\":\"Denesik Group\",\n" +
                "      \"website\":\"http://jodie.org\"\n" +
                "   },\n" +
                "   \"profilePic\":\"http://lorempixel.com/640/480/people\"\n" +
                "}";

        String APIUrl = url + "createUser";
        RequestSpecBuilder builder = new RequestSpecBuilder();

        //Creating new user and checking if it is getting inserted
        builder.setBody(body);
        builder.setContentType("application/json; charset=UTF-8");
        RequestSpecification requestSpec = builder.build();
        Response response = given().spec(requestSpec).when().post(APIUrl);
        String actual = response.body().asString();
        Assert.assertEquals(actual,"Inserted Object");

        //Trying to insert the same user again and verifying it doesn't insert
        builder.setBody(body2);
        requestSpec = builder.build();
        response = given().spec(requestSpec).when().post(APIUrl);
        actual = response.body().asString();
        Assert.assertEquals(actual,"Already exists");

    }

    @org.junit.Test
    public void getAllUsers() throws Exception {
        Response resp = get(url + "getAllUsers");
        JSONArray jsonResponse = new JSONArray(resp.asString());

        String firstName = jsonResponse.getJSONObject(0).getString("firstName");
        String lastName = jsonResponse.getJSONObject(1).getString("lastName");
        Assert.assertEquals(firstName, "Dorris");
        Assert.assertEquals(lastName, "Keeling");

    }

    @org.junit.Test
    public void getUser() throws Exception {
        Response resp = get(url + "user/"+"1630215c-283713-4434-aab4-9d534d8nbbb");
        JSONObject jsonResponse = new JSONObject(resp.asString());

        String firstName = jsonResponse.getString("firstName");

        Assert.assertEquals(firstName, "Dorris");

    }

    @org.junit.Test
    public void updateUser() throws Exception {
        String body = "{  \n" +
                "   \"_id\":\"1630215c-283713-4434-aab4-9d534d8nbbb\",\n" +
                "   \"firstName\":\"Morris\",\n" +
                "   \"lastName\":\"Keeling\",\n" +
                "   \"email\":\"Darby_Leffler68@gmail.com\",\n" +
                "   \"address\":{  \n" +
                "      \"street\":\"193 Talon Valley\",\n" +
                "      \"city\":\"South Tate furt\",\n" +
                "      \"zip\":\"47069\",\n" +
                "      \"state\":\"IA\",\n" +
                "      \"country\":\"US\"\n" +
                "   },\n" +
                "   \"dateCreated\":\"2016-03-15T07:02:40.896Z\",\n" +
                "   \"company\":{  \n" +
                "      \"name\":\"Denesik Group\",\n" +
                "      \"website\":\"http://jodie.org\"\n" +
                "   },\n" +
                "   \"profilePic\":\"http://lorempixel.com/640/480/people\"\n" +
                "}";

        String APIUrl = url + "updateUser/" + "1630215c-283713-4434-aab4-9d534d8nbbb";

        //Updating user name from Dorris to Morris
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBody(body);
        builder.setContentType("application/json; charset=UTF-8");
        RequestSpecification requestSpec = builder.build();
        Response response = given().spec(requestSpec).when().post(APIUrl);
        JSONObject JSONResponseBody = new JSONObject(response.body().asString());
        Assert.assertEquals(JSONResponseBody.get("firstName").toString(), "Morris");


        String APIUrl2 = url + "updateUser/" + "1630215c-283613-4434-aab4-9d534d8nbbb";

        //Updating user which doesn't exist
        builder = new RequestSpecBuilder();
        builder.setBody(body);
        builder.setContentType("application/json; charset=UTF-8");
        requestSpec = builder.build();
        response = given().spec(requestSpec).when().post(APIUrl2);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

}