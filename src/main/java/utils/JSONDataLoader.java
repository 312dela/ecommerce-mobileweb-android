package utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONDataLoader {
       private static String FILE_PATH = "src/test/resources/test_data.json";
    private static JsonNode rootNode;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            rootNode = mapper.readTree(new File(FILE_PATH));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data from JSON", e);
        }
    }

    public static JsonNode getLoginFlow() {
        return rootNode.get("loginFlow");
    }

    public static JsonNode getOrderFlow() {
        return rootNode.get("orderFlow");
    }

    public static String getLoginEmail() {
        return getLoginFlow().get("email").asText();
    }

    public static String getLoginPassword() {
        return getLoginFlow().get("password").asText();
    }

    public static String getProduct1() {
        return getOrderFlow().get("product1").asText();
    }

    public static String getProduct2() {
        return getOrderFlow().get("product2").asText();
    }

    public static String getProductId() {
        return getOrderFlow().get("productId").asText();
    }

    public static String getOtherUserEmail() {
        return getLoginFlow().get("otherUserEmail").asText();
    }

    public static String getInsertLocation() {
        return getOrderFlow().get("insertLocation").asText();
    }

    public static String getSelectLocation() {
        return getOrderFlow().get("selectLocation").asText();
    }

}
