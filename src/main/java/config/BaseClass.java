package config;

import org.junit.BeforeClass;
import specification.RickAndMortySpec;

public class BaseClass {

    static final String URI = "https://rickandmortyapi.com/api";

    @BeforeClass
    public static void before() {
        RickAndMortySpec.createSpec(URI);
    }

}
