package cz.fi.muni.pa165.musiclibrary.utils;

import cz.fi.muni.pa165.musiclibrary.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchHelperTest {

    @Test
    public void searchEmptyString() {
        Assert.assertEquals(new ArrayList<String>(), SearchHelper.splitSearchQuery(""));
        Assert.assertEquals(new ArrayList<String>(), SearchHelper.splitSearchQuery("  "));
    }

    @Test
    public void searchOneWord() {
        Assert.assertEquals(Arrays.asList("%hello%"), SearchHelper.splitSearchQuery("hello"));
        Assert.assertEquals(Arrays.asList("%hello%"), SearchHelper.splitSearchQuery(" hello "));
    }

    @Test
    public void searchTwoWords() {
        Assert.assertEquals(Arrays.asList("%hello%", "%world%"), SearchHelper.splitSearchQuery("hello world"));
        Assert.assertEquals(Arrays.asList("%hello%", "%world%"), SearchHelper.splitSearchQuery(" hello world "));
    }
}
