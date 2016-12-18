package com.koryeumnoi.koryeumnoi;

import android.content.ContentValues;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class EntityModel {

    private ContentValues values;

    public EntityModel()
    {
        values = new ContentValues();
    }

    public ContentValues getValues()
    {
        return values;
    }

    public byte[] getDataEncode()
    {
        StringBuilder postData = new StringBuilder();
        byte[]        result = null;
        try {

            for (Map.Entry<String,Object> entry : values.valueSet())
            {
                if(postData.length()!=0)
                    postData.append('&');

                postData.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(""+entry.getValue(),"UTF-8"));
            }

            result = postData.toString().getBytes("UTF-8");

        }catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}
