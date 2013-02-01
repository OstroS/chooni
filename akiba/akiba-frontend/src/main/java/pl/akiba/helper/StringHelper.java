package pl.akiba.helper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author kostrows
 */
public class StringHelper {
    public Map<String, String> getKeyValueMap(String queryString) {
        Map<String, String> map = new LinkedHashMap<>();
        String[] parameters = queryString.split("&");
        
        for(String p : parameters) {
            String[] kv = p.split("=");
            map.put(kv[0].trim(), kv[1].trim());
        }
        
        return map;
    }
    
    public String getValue(String queryString, String key) {
        String[] parameters = queryString.split("&");
        String value = "";
        for (String p : parameters) {
            if (p.contains(key)) {
                value = p.split("=")[1].trim();
            }
        }
        return value;
    }
}
