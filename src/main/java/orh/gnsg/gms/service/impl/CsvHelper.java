package orh.gnsg.gms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import orh.gnsg.gms.domain.Revenue;

@Service
public class CsvHelper {

    public static String ListJson(List<Revenue> allRevenue) {
        //	ListAsAJson(allRevenue);
        JSONObject output;
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            // Gson gson = gsonBuilder.create();

            Gson gson = gsonBuilder
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
            Map<String, Object> revObj = new HashMap<>();
            revObj.put("infile", allRevenue);
            String JSONObject = gson.toJson(revObj);
            System.out.print(JSONObject);
            output = new JSONObject(JSONObject);
            JSONArray docs = output.getJSONArray("infile");
            File file = new File("/tmp2/fromJSON.csv");
            String csv = CDL.toString(docs);
            return csv;
            // FileUtils.writeStringToFile(file, csv);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*
         * catch (IOException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); }
         */

        return null;
    }

    public static String ListAsJson(List<Revenue> allRevenue) {
        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper Obj = new ObjectMapper();

        try {
            // get Oraganisation object as a json string
            String jsonStr = Obj.writeValueAsString(allRevenue);

            // Displaying JSON String
            System.out.println(jsonStr);

            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String ListAsAJson(List<Revenue> allRevenue) {
        // create mapper and schema
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(Revenue.class);
        schema = schema.withColumnSeparator('\t');

        // output writer
        ObjectWriter myObjectWriter = mapper.writer(schema);
        File tempFile = new File("users.csv");
        FileOutputStream tempFileOutputStream;
        try {
            tempFileOutputStream = new FileOutputStream(tempFile);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
            OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
            myObjectWriter.writeValue(writerOutputStream, allRevenue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

class LocalDateAdapter implements JsonSerializer<LocalDate> {

    public JsonElement serialize(LocalDate date, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"))); // "yyyy-mm-dd"
    }
}
