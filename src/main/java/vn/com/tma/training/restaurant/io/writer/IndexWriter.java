package vn.com.tma.training.restaurant.io.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.com.tma.training.restaurant.util.Constant;
import vn.com.tma.training.restaurant.util.Index;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * IndexWriter writes the index of the files
 */
public class IndexWriter implements Writer<Index> {
    private static final Logger logger = LoggerFactory.getLogger(IndexWriter.class);

    /**
     * Writes the index that passed in to file
     *
     * @param data The index that needs to be saved
     * @throws IOException If there is something wrong when writing file
     */
    @Override
    public void write(Index data) throws IOException {
        logger.info("Writing indexes to file " + Constant.INDEX_FILE.getAbsolutePath());

        try {
            OutputStream os;
            os = new FileOutputStream(Constant.INDEX_FILE);
            JsonWriter writer = Json.createWriter(os);
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                    .add(Constant.MENU_INDEX, data.getMenuIndex())
                    .add(Constant.ORDER_INDEX, data.getOrderIndex());
            writer.writeObject(jsonObjectBuilder.build());
        } catch (IOException e) {
            logger.error(e.toString());
            throw e;
        }
    }
}
