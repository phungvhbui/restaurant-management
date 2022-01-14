package vn.com.tma.training.restaurant.io.reader;

import vn.com.tma.training.restaurant.util.Index;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class IndexReader implements Reader<Index> {
    @Override
    public Index read() {
        Index index = null;
        File jsonInputFile = new File("/run/media/alexb/Work/TMA-Exercise/Java-Core/src/main/java/vn/com/tma/training/javacore/data/index.json");
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
            // Get the JsonObject structure from JsonReader.
            JsonObject object = reader.readObject();
            index = new Index(object.getInt("menu"), object.getInt("order"));
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return index;
    }
}
