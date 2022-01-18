package vn.com.tma.training.restaurant.io.reader;

import vn.com.tma.training.restaurant.util.Constant;
import vn.com.tma.training.restaurant.util.Index;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class IndexReader implements Reader<Index> {
    @Override
    public Index read() throws IOException {
        InputStream is;
        is = new FileInputStream(Constant.INDEX_FILE);
        JsonReader reader = Json.createReader(is);
        JsonObject object = reader.readObject();
        Index index = new Index(object.getInt(Constant.MENU_INDEX), object.getInt(Constant.ORDER_INDEX));
        reader.close();
        return index;
    }
}
