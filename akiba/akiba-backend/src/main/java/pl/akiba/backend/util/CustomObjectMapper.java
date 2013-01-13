package pl.akiba.backend.util;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

/**
 * 
 * @author sobczakt
 */
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        super();
        configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

}
