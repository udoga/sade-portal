package com.sadesoft.portal.base.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Props {
    private final Map<String, String> map;

    public Props(Map<String, String> map) {
        this.map = map;
    }

    public Props(String path) {
        this.map = new HashMap<>();
        Properties properties = new Properties();
        try { properties.load(new FileReader(path)); }
        catch (IOException e) { throw new RuntimeException(e); }
        for (String key: properties.stringPropertyNames()) map.put(key, properties.getProperty(key));
    }

    public int count() {
        return map.size();
    }

    public Props getSection(String prefix) {
        HashMap<String, String> result = new HashMap<>();
        for (Map.Entry<String, String> e: map.entrySet())
            if (e.getKey().startsWith(prefix + "."))
                result.put(e.getKey().replaceFirst(prefix + ".", ""), e.getValue());
        return new Props(result);
    }

    public String get(String key) {
        String value = map.get(key);
        if (value == null) throw new RuntimeException("Property not found: key=" + key);
        return value;
    }

    public String getOrDefault(String key, String defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    public int getInt(String key) {
        String value = get(key);
        return Integer.parseInt(value);
    }

    public boolean getBool(String key) {
        String value = get(key);
        if (value.equals("true")) return true;
        if (value.equals("false")) return false;
        throw new RuntimeException("Invalid boolean: " + value);
    }
}
