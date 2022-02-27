package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class configFileReader {
    private Properties properties;
    //private final String propertyFilePath= "src//test//resources//Configuration.properties";
    private final String propertyFilePath = getPropertyFilePath();


    private String getPropertyFilePath() {
        String profileId = System.getProperty("profileId");
        if (profileId == null) profileId = "testProd";
        return "src//test//resources//profiles//" + profileId + "//configuration.properties";
    }

    public configFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if (implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    public String getValidEmail() {
        String email = properties.getProperty("validEmail");
        if (email != null) return email;
        else throw new RuntimeException("validEmail not specified in the Configuration.properties file.");
    }

    public String getValidPassword() {
        String password = properties.getProperty("validPassword");
        if (password != null) return password;
        else throw new RuntimeException("validPassword not specified in the Configuration.properties file.");
    }

//    public String getReportConfigPath() {
//        String reportConfigPath = properties.getProperty("reportConfigPath");
//        if (reportConfigPath != null) return reportConfigPath;
//        else
//            throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
//    }
}
