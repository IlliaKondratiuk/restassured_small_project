import config.BaseClass;
import objects.Characters;
import objects.Info;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class TestRICK extends BaseClass {

    @Test
    public void test1() {
        System.out.println(when()
                .get("character")//adding a specific word to the base URI to open it
                .then()
                .log()
                .body());
    }

    @Test
    public void test2() { //checking that specific field in a json object is equal to the given value
        when()
                .get("character")//adding a specific word to the base URI to open it
                .then()
                .body("info.pages", equalTo(42));
    }
    @Test
    public void test3() { //extracting a value of a specific field in a json object
        int i = when()
                .get("character")
                .then()
                .extract().body().path("info.pages");
        System.out.println(i);
    }


    @Test
    public void test4() { //parsing a part of a json file to a separate object
        Info info = when()
                .get("character")
                .then()
                .extract().jsonPath().getObject("info", Info.class); //also can use getList instead of getObject
    }

    @Test
    public void test5() { //parsing the whole file to a separate object
        Characters chars = when()
                .get("character")
                .then()
                .extract().as(Characters.class);

        System.out.println(chars.info.next);
    }

    @Test
    public void test6() { //parsing all names in the json file to a string list
        List<String> names = when()
                .get("character")
                .then()
                .extract()
                .jsonPath()
                .getList("results.name", String.class);
        for (String name : names) {
            System.out.println(name);
        }
    }

    @Test
    public void test7() { //parsing all locations in the json file to a string list
        List<String> locations = when()
                .get("location")
                .then()
                .extract()
                .jsonPath()
                .getList("results.dimension", String.class);
        for (String name : locations) {
            System.out.println(name);
        }
    }
}
