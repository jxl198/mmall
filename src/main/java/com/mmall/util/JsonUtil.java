package com.mmall.util;

import com.mmall.pojo.TestPojo;
import com.mmall.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jiangxl
 * @description
 * @date 2018-06-27 11:16
 **/
@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);
        //取消时间转为timestamp形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式统一为yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));

        //反序列化格式
        //忽略在json字符串中存在单是在javabean中不存在对应属性的情况
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("Parse object to String error", e);
            return null;
        }
    }

    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.error("Parse object to String error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) json : objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("Parse string to object error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String json, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(json) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? json : objectMapper.readValue(json, typeReference));
        } catch (IOException e) {
            log.error("Parse string to object error", e);
            return null;
        }
    }

    public static <T> T string2Obj(String json, Class<?> collectionClass, Class<?>... elementClass) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
        try {
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            log.error("Parse string to object error", e);
            return null;
        }
    }

    public static void main(String[] args) {
        User u1 = new User();
        u1.setId(1);
        u1.setUsername("jxl");
        u1.setEmail("jxl192@126.com");
        u1.setCreateTime(new Date());

       /* TestPojo testPojo=  new TestPojo();
        testPojo.setId(1);
        testPojo.setName("jxl");*/


//        User u2 = new User();
//        u2.setId(2);
//        u2.setUsername("jxl2");
//        u2.setEmail("jxl193@126.com");
//        String u1Json = JsonUtil.obj2String(u1);
//        String u1JsonPretty = JsonUtil.obj2StringPretty(testPojo);
//        log.info("userJson:{}", u1Json);
//        log.info("userJsonPretty:{}", u1JsonPretty);

//        User user = JsonUtil.string2Obj(u1Json, User.class);

//        List<User> list = Lists.newArrayList();
//        list.add(u1);
//        list.add(u2);
//        String userListStr = JsonUtil.obj2StringPretty(list);
//        log.info("-----------------------------------------------------------");
//        log.info("userList:{}", userListStr);
//
//        List<User> userList = JsonUtil.string2Obj(userListStr, new TypeReference<List<User>>() {
//
//        });
//
//
//        List<User> userList1 = JsonUtil.string2Obj(userListStr, List.class, User.class);
//        System.out.println("end");


    }


}
