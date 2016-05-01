/**
 * Created by Harjinder Singh on 4/30/2016.
 */
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    private BasicDBObject address;
    private BasicDBObject company;
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String city;
    private String zip;
    private String state;
    private String country;
    private String name;
    private String website;
    private String profilePic;
    private Date dateCreated = new Date();

    public User(){

    }
    public User(BasicDBObject dbObject) {
        this.id = dbObject.getString("_id");
        this.firstName = dbObject.getString("firstName");
        this.lastName = dbObject.getString("lastName");
        this.email = dbObject.getString("email");
        this.address = (BasicDBObject) dbObject.get("address");
        this.street = address.getString("street");
        this.city = address.getString("city");
        this.zip = address.getString("zip");
        this.state = address.getString("state");
        this.country = address.getString("country");
        this.company = (BasicDBObject) dbObject.get("company");
        this.name = company.getString("name");
        this.website = company.getString("website");
        this.profilePic = dbObject.getString("profilePic");
    }

    public BasicDBObject getAddress() {
        return address;
    }

    public void setAddress(BasicDBObject address) {
        this.address = address;
    }

    public BasicDBObject getCompany() {
        return company;
    }

    public void setCompany(BasicDBObject company) {
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
