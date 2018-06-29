package com.mmall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author jiangxl
 */
@Slf4j
public class PropertiesUtil {


    private static Properties props;

    static {
        String fileName = "mmall.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            log.error("配置文件读取异常", e);
        }
    }

    public static String getProperty(String key) {
        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key, String defaultValue) {

        String value = props.getProperty(key.trim());
        if (StringUtils.isBlank(value)) {
            value = defaultValue;
        }
        return value.trim();
    }


    public static Integer getInt(String key, Integer defaultValue) {
        Integer value = getInt(key);
        if (value == null) {
            return defaultValue;
        }
        return value;


    }

    public static Integer getInt(String key) {
        String value = getProperty(key);
        if (StringUtils.isBlank(value)) {
            return null;
        } else {
            return Integer.parseInt(value);
        }
    }


    public static Boolean getBoolean(String key) {
        String value = getProperty(key);
        if (StringUtils.isBlank(value)) {
            return null;
        } else {
            return Boolean.parseBoolean(value);
        }
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        Boolean value = getBoolean(key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }


}
