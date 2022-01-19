package vn.com.tma.training.restaurant.io.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.com.tma.training.restaurant.util.Constant;
import vn.com.tma.training.restaurant.util.Index;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * IndexReader reads the index of the files
 */
public class IndexReader implements Reader<Index> {
    private static final Logger logger = LoggerFactory.getLogger(IndexReader.class);

    /**
     * Reads the index file and returns the index
     *
     * @return The index of the files
     * @throws IOException If there is something wrong when reading file
     */
    @Override
    public Index read() throws IOException {
        logger.info("Reading indexes from file " + Constant.INDEX_FILE.getAbsolutePath());

        Index index;
        try {
            InputStream is;
            is = new FileInputStream(Constant.INDEX_FILE);
            JsonReader reader = Json.createReader(is);
            JsonObject object = reader.readObject();
            index = new Index(object.getInt(Constant.MENU_INDEX), object.getInt(Constant.ORDER_INDEX));
            reader.close();
        } catch (IOException e) {
            logger.error(e.toString());
            throw e;
        }

        return index;
    }
}
