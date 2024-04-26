package com.sadesoft.portal.base.model;

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

    public String getString(String key) {
        String value = map.get(key);
        if (value == null) throw new RuntimeException("Property not found: key=" + key);
        return value;
    }

    public String getString(String key, String defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    public int getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    public int getInt(String key, int defaultValue) {
        String value = getString(key, "");
        return value.isEmpty()? defaultValue : Integer.parseInt(value);
    }

    public boolean getBool(String key) {
        return toBool(getString(key));
    }

    public boolean getBool(String key, boolean defaultValue) {
        String value = getString(key, "");
        return value.isEmpty()? defaultValue : toBool(value);
    }

    private boolean toBool(String s) {
        if (s.equals("true")) return true;
        if (s.equals("false")) return false;
        throw new RuntimeException("Invalid boolean: " + s);
    }
}
