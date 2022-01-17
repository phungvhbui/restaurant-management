package vn.com.tma.training.restaurant.io.writer;

import vn.com.tma.training.restaurant.util.Constant;
import vn.com.tma.training.restaurant.util.Index;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class IndexWriter implements Writer<Index> {
    @Override
    public void write(Index data) {
        OutputStream os;
        try {
            os = new FileOutputStream(Constant.INDEX_FILE);
            JsonWriter writer = Json.createWriter(os);
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                    .add(Constant.MENU_INDEX, data.getMenuIndex())
                    .add(Constant.ORDER_INDEX, data.getOrderIndex());
            writer.writeObject(jsonObjectBuilder.build());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
