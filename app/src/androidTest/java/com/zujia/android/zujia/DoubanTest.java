package com.zujia.android.zujia;

import android.test.InstrumentationTestCase;
import android.util.Log;

import com.zujia.android.zujia.service.RestApi;

/**
 * Created by lenovo on 15-4-9.
 */
public class DoubanTest extends InstrumentationTestCase {
    public void test() throws Exception{
        RestApi api = new RestApi();
        com.zujia.android.zujia.model.DoubanTest t = new com.zujia.android.zujia.model.DoubanTest();
        t = api.douban();
        Log.i("douban", t.getTags().get(0).getTitle());
    }
}
