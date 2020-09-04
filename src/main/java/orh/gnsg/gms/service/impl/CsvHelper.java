package orh.gnsg.gms.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import orh.gnsg.gms.domain.Revenue;

@Service
public class CsvHelper {

	public static  void ListJson(List<Revenue> allRevenue){
        JSONObject output; 
        try {
        	GsonBuilder gsonBuilder = new GsonBuilder();
    		Gson gson = gsonBuilder.create();
			 Map<String, Object> revObj= new HashMap<>(); revObj.put("infile",
			 allRevenue );
    		String JSONObject = gson.toJson(revObj);
    		System.out.print(JSONObject);
            output = new JSONObject(JSONObject);
            JSONArray docs = output.getJSONArray("infile");
            File file=new File("/tmp2/fromJSON.csv");
            String csv = CDL.toString(docs);
            FileUtils.writeStringToFile(file, csv);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }
}
