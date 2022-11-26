package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;
import java.io.IOException;

public class TestBase {

    public void init() {
        RestAssured.authentication = RestAssured.basic("f2c01c8399d967884390d1e87b46b533", "");
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        if (!status(issueId).equals("Closed")) {
            return true;
        }
        return false;
    }

    public String status(int issue_id) {
        String json = RestAssured.get(String.format("https://bugify.stqa.ru/api/issues/" + issue_id + ".json")).asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
