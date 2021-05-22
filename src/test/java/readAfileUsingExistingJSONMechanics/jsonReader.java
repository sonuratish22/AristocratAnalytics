package readAfileUsingExistingJSONMechanics;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class jsonReader {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("./payload/main-reelstrips.json");
        FileReader fileReader = new FileReader(f);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject jsonObject = new JSONObject(jsonTokener);
        JSONArray jsonArray = new JSONArray(jsonObject);
        int length = jsonArray.length();
        System.out.println(length);
        for(int i = 0 ; i<= length; i++){
            Object o = jsonObject.get("reelStripDefinitions[*].stops[*].name");
            System.out.println(o);
        }

    }
}
