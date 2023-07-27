package com.mifmif.common.regex;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;


public class PKWareTests {

    @ParameterizedTest
    @ValueSource(strings = { "a*b", "a{1,}b", "(ab)*c" , "a*b*c"})
    public void DemonstratesRegexMatchingOutputForInfiniteSymbolFollowedByFinite(String regex) {
        Generex generex = new Generex(regex);

        for (int i = 0; i < 100; i++) {
                String result = generex.random(0, 5);
                Assert.assertTrue(result.matches(regex));
        }

    }

    @ParameterizedTest
    @ValueSource(strings = { "a+b","a*b", "a{1,}b", "(ab)*c" , "a*b*c"})
    public void DistributionTest(String regex) {
        Generex generex = new Generex(regex);
        HashMap<String, Integer> instances = new HashMap<String, Integer>();

        for (int i = 0; i < 100; i++) {
            String result = generex.random(0, 5);
            Assert.assertTrue(result.matches(regex));
            if (instances.get(result)==null){
                instances.put(result, 1);
            }else {
                instances.put(result, instances.get(result)+1);
            }
        }
        for (String key : instances.keySet())
        {
            System.out.println(key+" :"+instances.get(key));
        }

    }

}
