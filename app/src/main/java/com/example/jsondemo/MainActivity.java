package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jsondemo.bean.DataInfo;
import com.example.jsondemo.bean.FilmInfo;
import com.example.jsondemo.bean.ShopBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_native_ToJavaObject,btn_native_ToJavaList,btn_native_complex,btn_native_special;
    private TextView tv_native_original,tv_native_last;
    private ShopBean shopBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        btn_native_ToJavaObject.setOnClickListener(this);
        btn_native_ToJavaList.setOnClickListener(this);
        btn_native_complex.setOnClickListener(this);
        btn_native_special.setOnClickListener(this);
    }

    private void initView() {
        btn_native_ToJavaObject = findViewById(R.id.btn_native_ToJavaObject);
        btn_native_ToJavaList = findViewById(R.id.btn_native_ToJavaList);
        btn_native_complex = findViewById(R.id.btn_native_complex);
        btn_native_special = findViewById(R.id.btn_native_special);
        tv_native_original = findViewById(R.id.tv_native_original);
        tv_native_last = findViewById(R.id.tv_native_last);
    }
/**
 *(1)将json格式的字符串{}转化为JAVA对象
 *(2)将json格式的字符串[]转化为JAVA对象的List
 *(3)复杂json数据的解析
 *(4)特殊json数据解析
 */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_native_ToJavaObject:
                jsonToJavaObjectByNative();//将json格式的字符串{}转化为JAVA对象
                break;
            case R.id.btn_native_ToJavaList:
                jsonToJavaListByNative();//将json格式的字符串[]转化为JAVA对象的List
                break;
            case R.id.btn_native_complex:
                jsonToJavaOfComplex();//复杂json数据的解析

                break;
            case R.id.btn_native_special:
                jsonToJavaOfSpecial();//特殊json数据解析
                break;
        }
    }

    private void jsonToJavaOfSpecial() {
        //第一步：获取或创建Json数据
        String json = "{\n" +
                "    \"code\":0,\n" +
                "    \"list\":{\n" +
                "        \"0\":{\n" +
                "            \"aid\":\"8900789\",\n" +
                "            \"author\":\"春江水暖鸭先知\",\n" +
                "            \"coins\":188,\n" +
                "            \"copyright\":\"Copy\",\n" +
                "            \"create\":\"2022-04-11 16:30\"\n" +
                "        },\n" +
                "        \"1\":{\n" +
                "            \"aid\":\"8900788\",\n" +
                "            \"author\":\"清明时节雨纷纷\",\n" +
                "            \"coins\":189,\n" +
                "            \"copyright\":\"Copy\",\n" +
                "            \"create\":\"2022-04-11 16:40\" \n" +
                "        }\n" +
                "    }\n" +
                "}";

        //创建封装的java对象
        FilmInfo filmInfo = new FilmInfo();
        //第二步：解析json数据
        try {
            JSONObject jsonObject = new JSONObject(json);
            //第一层解析
            int code = jsonObject.optInt("code");
            JSONObject list = jsonObject.optJSONObject("list");
            //第一层封装
            filmInfo.setCode(code);
            List<FilmInfo.FilmBean> lists = new ArrayList<>();
            filmInfo.setList(lists);
            //第二层解析
            for (int i = 0; i < list.length(); i++) {

                JSONObject jsonObject1 = list.optJSONObject(i + "");

                if (jsonObject1 != null){
                    String aid = jsonObject1.optString("aid");
                    String author = jsonObject1.optString("author");
                    int coins = jsonObject1.optInt("coins");
                    String copyright = jsonObject1.optString("copyright");
                    String create = jsonObject1.optString("create");
                    //第二层数据封装
                    FilmInfo.FilmBean filmBean = new FilmInfo.FilmBean();
                    filmBean.setAid(aid);
                    filmBean.setAuthor(author);
                    filmBean.setCoins(coins);
                    filmBean.setCopyright(copyright);
                    filmBean.setCreate(create);
                    lists.add(filmBean);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //第三步：显示数据
        tv_native_original.setText(json);
        tv_native_last.setText(filmInfo.toString());
    }

    private void jsonToJavaOfComplex() {
        //第一步：获取或创建Json数据
        String json = "{\n" +
                "    \"data\":{\n" +
                "        \"count\":5,\n" +
                "        \"items\":[\n" +
                "            {\n" +
                "                \"id\":45,\n" +
                "                \"title\":\"坚果\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\":46,\n" +
                "                \"title\":\"炒货\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"rs_code\":\"1000\",\n" +
                "    \"rs_msg\":\"success\"\n" +
                "}";
        //封装Java对象
        DataInfo dataInfo = new DataInfo();

        //第二步：解析json数据
        try {
            JSONObject jsonObject = new JSONObject(json);
            //第一层解析
            JSONObject object = jsonObject.optJSONObject("data");
            String rs_code = jsonObject.optString("rs_code");
            String rs_msg = jsonObject.optString("rs_msg");
            //第一层封装
            dataInfo.setRs_code(rs_code);
            dataInfo.setRs_msg(rs_msg);
            DataInfo.DataDTO dataBean = new DataInfo.DataDTO();
            dataInfo.setData(dataBean);

            //第二层解析
            int count = object.optInt("count");
            JSONArray items = object.optJSONArray("items");
            //第二层数据的封装
            dataBean.setCount(count);
            List<DataInfo.DataDTO.ItemsDTO> itemsBean = new ArrayList<>();
            dataBean.setItems(itemsBean);
            //第三层解析
            for (int i = 0; i < items.length(); i++) {
                if (items != null){
                    JSONObject jsonObject1 = items.optJSONObject(i);
                    int id = jsonObject1.optInt("id");
                    String title = jsonObject1.optString("title");
                    //第三层数据的封装
                    DataInfo.DataDTO.ItemsDTO itemsDTO = new DataInfo.DataDTO.ItemsDTO();
                    itemsDTO.setId(id);
                    itemsDTO.setTitle(title);
                    itemsBean.add(itemsDTO);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //第三步：显示数据
        tv_native_original.setText(json);
        tv_native_last.setText(dataInfo.toString());
    }

    private void jsonToJavaListByNative() {
        //第一步：获取或创建Json数据
        String json = "[\n" +
                "    {\n" +
                "        \"id\":1,\n" +
                "        \"name\":\"小龙虾1\",\n" +
                "        \"price\":12.3,\n" +
                "        \"imagePath\":\"https://192.168.10.165:8080/f1.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\":2,\n" +
                "        \"name\":\"小龙虾2\",\n" +
                "        \"price\":22.3,\n" +
                "        \"imagePath\":\"https://192.168.10.165:8080/f2.jpg\"\n" +
                "    },\n" +
                "]";
        //第二步：解析json数据
        List<ShopBean> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject != null){
                    int id = jsonObject.optInt("id");
                    String name = jsonObject.optString("name");
                    double price = jsonObject.optDouble("price");
                    String imagePath = jsonObject.optString("imagePath");
                    shopBean = new ShopBean(id,name,price,imagePath);
                    list.add(shopBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //第三步：显示数据
        tv_native_original.setText(json);//显示原始数据
        tv_native_last.setText(list.toString());//转换后数据
    }

    private void jsonToJavaObjectByNative() {
        //第一步：获取或创建Json数据
        String json = "{\n" +
                "            \"id\":2,\"name\":\"小龙虾\",\n" +
                "            \"price\":12.3,\n" +
                "            \"imagePath\":\"https://192.168.10.165:8080/L05 Server/images/f1.jpg\"\n" +
                "        }";
        //第二步：解析json数据
        try {
            //JSONObject(json)将json字符串解析为json对象
            JSONObject jsonObject = new JSONObject(json);
            //optXXX方法会在对应的key中的值不存在的时候返回一个空字符串或者返回你指定的默认值
            //但是getString方法会出现空指针异常的错误
            //int id = jsonObject.getInt("id");
            int id1 = jsonObject.optInt("id");
            String name = jsonObject.optString("name");
            double price = jsonObject.optDouble("price");
            String imagePath = jsonObject.optString("imagePath");
            shopBean = new ShopBean(id1, name, price, imagePath);//封装java对象
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //第三步：显示数据
        tv_native_original.setText(json);//显示原始数据
        tv_native_last.setText(shopBean.toString());//转换后数据
    }
}
























