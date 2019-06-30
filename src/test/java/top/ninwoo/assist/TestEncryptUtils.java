package top.ninwoo.assist;

import org.junit.Assert;
import org.junit.Test;
import top.ninwoo.selfLearn.assist.EncryptUtils;

public class TestEncryptUtils {

    @Test
    public void test() {
        String a = "abcdefg";

        String first = EncryptUtils.getSHA(a);
        System.out.println(first);
        Assert.assertTrue(first.equals(EncryptUtils.getSHA(a)));
        Assert.assertTrue(!first.equals(EncryptUtils.getSHA(a + "b")));

    }
}
