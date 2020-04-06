package Properties;

import Util.PropertiesReaderClassLoader;

public class PropertyUsage {

    public static void main(String[] args) {
        String baseUrl = PropertiesReaderClassLoader.getInstance().getValueFromProperty("baseUrl");
        String defaultTimeoutValue = PropertiesReaderClassLoader.getInstance().getValueFromProperty("defaultTimeout");
        String browser = PropertiesReaderClassLoader.getInstance().getValueFromProperty("browser");
        System.out.println("baseUrl = " + baseUrl);
        System.out.println("defaultTimeoutValue = " + defaultTimeoutValue);
        System.out.println("browser = " + browser);
    }
}
