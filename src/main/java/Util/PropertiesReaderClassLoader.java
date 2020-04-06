package Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReaderClassLoader {
    private static PropertiesReaderClassLoader loadProperties = null;
    private static Properties properties = null;
    private static InputStream inputStream = null;

    private PropertiesReaderClassLoader(String propertyFileName) {
        this.inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(propertyFileName);
    }

    public static PropertiesReaderClassLoader getInstance() {
        if (loadProperties == null) {
            loadProperties = new PropertiesReaderClassLoader("config.properties");
        }
        return loadProperties;
    }

    public String getValueFromProperty(String key) {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties.getProperty(key);
    }

}
