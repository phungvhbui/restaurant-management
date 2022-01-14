package vn.com.tma.training.restaurant.io.writer;

import vn.com.tma.training.restaurant.util.Index;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class IndexWriter implements Writer<Index> {
    @Override
    public void write(Index data) {
        File jsonOutputFile = new File("/run/media/alexb/Work/TMA-Exercise/Java-Core/src/main/java/vn/com/tma/training/javacore/data/index.json");
        OutputStream os;
        try {
            os = new FileOutputStream(jsonOutputFile);
            JsonWriter writer = Json.createWriter(os);
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                    .add("menu", data.getMenuIndex())
                    .add("order", data.getOrderIndex());
            writer.writeObject(jsonObjectBuilder.build());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
