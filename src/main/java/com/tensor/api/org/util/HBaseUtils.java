package com.tensor.api.org.util;


import java.util.*;

import com.google.gson.JsonObject;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import scala.util.control.Exception;

public class HBaseUtils {

    public static String TABLE_NAME = "News";
    public static final String cf1 = "cf1";
    public static final String cf2 = "cf2";
    public static final String id = "id";
    public static final String cf1_newTitle = "newTitle";
    public static final String cf1_newType = "newType";
    public static final String cf1_author = "author";
    public static final String cf1_text = "text";
    public static final String cf1_publish_date ="public_data";
    public static final String cf1_download_date="download_data";
    public static final String cf1_url="url";
    public static final String cf1_source="source";
    public static final String cf_2_hash_code = "";


    //设置表名
    public static void setTableName(String tableName) {
        TABLE_NAME = tableName;
    }

    //生成rowkey
    public String getGoodId() {

        Random rand = new Random();
        int rad = rand.nextInt(90) + 10;    //两位随机数
        String uuid = UUID.randomUUID().toString().replaceAll("-", ""); //生成uuid，并去掉'-'；
        return String.valueOf(rad) + '-'+uuid;  //随机数与uuid以"-"链接
    }

    //转换Json
    public JsonObject jsonObjectTool(Result result, JsonObject jsonObject1) {

        jsonObject1.addProperty("id",Bytes.toString(result.getRow()));
        jsonObject1.addProperty("author", Bytes.toString(result.getValue(Bytes.toBytes(cf1), Bytes.toBytes(cf1_author))));
        jsonObject1.addProperty("newTitle", Bytes.toString(result.getValue(Bytes.toBytes(cf1), Bytes.toBytes(cf1_newTitle))));
        jsonObject1.addProperty("newType", Bytes.toString(result.getValue(Bytes.toBytes(cf1), Bytes.toBytes(cf1_newType))));
        jsonObject1.addProperty("text", Bytes.toString(result.getValue(Bytes.toBytes(cf1), Bytes.toBytes(cf1_text))));
        jsonObject1.addProperty("public_data", Bytes.toString(result.getValue(Bytes.toBytes(cf1), Bytes.toBytes(cf1_publish_date))));
        jsonObject1.addProperty("download_data", Bytes.toString(result.getValue(Bytes.toBytes(cf1), Bytes.toBytes(cf1_download_date))));
        jsonObject1.addProperty("url", Bytes.toString(result.getValue(Bytes.toBytes(cf1), Bytes.toBytes(cf1_url))));
        jsonObject1.addProperty("source", Bytes.toString(result.getValue(Bytes.toBytes(cf1), Bytes.toBytes(cf1_source))));
        return jsonObject1;
    }


}


