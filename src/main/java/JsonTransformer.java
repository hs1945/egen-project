import com.google.gson.Gson;
import spark.ResponseTransformer;

import javax.xml.ws.ResponseWrapper;

/**
 * Created by Harjinder Singh on 4/30/2016.
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    public String render(Object model) {
        
        return gson.toJson(model);

        //return gson.toJson(((ResponseWrapper)model).getDelegate().response);
    }


}
