package uds.samplepanel.base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Props {
    Properties properties;

    public Props() {
        properties = new Properties();
    }

    public Props(String path) {
        this();
        load(path);
    }

    public int count() {
        return properties.size();
    }

    public String get(String key) {
        String result = properties.getProperty(key);
        if (result == null) throw new RuntimeException("Property not found: key=" + key);
        return result;
    }

    public int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    private void load(String path) {
        try {
            properties.load(new FileReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
