package vn.com.tma.training.restaurant.io.writer;

import vn.com.tma.training.restaurant.util.Constant;
import vn.com.tma.training.restaurant.util.Index;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IndexWriter implements Writer<Index> {
    @Override
    public void write(Index data) throws IOException {
        OutputStream os;
        os = new FileOutputStream(Constant.INDEX_FILE);
        JsonWriter writer = Json.createWriter(os);
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                .add(Constant.MENU_INDEX, data.getMenuIndex())
                .add(Constant.ORDER_INDEX, data.getOrderIndex());
        writer.writeObject(jsonObjectBuilder.build());
    }
}
