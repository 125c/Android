package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


public class TaipeiSportCenterActivity extends AppCompatActivity {
    TextView taipei_sport_center_textview_title;
    Button taipei_sport_center_btn_getdata,taipei_sport_center_btn_cln;
    ListView taipei_sport_center_listview_apidata;
    ArrayList<String> taipei_sport_center_arrayListOfString;
    ArrayAdapter<String> taipei_sport_center_arrayAdapterOfString;//跟List溝通用的 資料存在這 listview用來顯示的 listview可以接其他種資料
    String stringAPIURL="https://data.taipei/api/v1/dataset/e7c46724-3517-4ce5-844f-5a4404897b7d?scope=resourceAquire";
    String stringTaipeiSportCenter="台北運動中心";
    int i,strStarOfGeo;
    String strCenterName,strCenterAddress,strLat,strLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taipei_sport_center);
        taipei_sport_center_arrayListOfString=new ArrayList<String>();
        taipei_sport_center_textview_title=findViewById(R.id.taipei_sport_center_textview_title);
        taipei_sport_center_btn_getdata=findViewById(R.id.taipei_sport_center_btn_getdata);
        taipei_sport_center_btn_cln=findViewById(R.id.taipei_sport_center_btn_cln);
        taipei_sport_center_listview_apidata=findViewById(R.id.taipei_sport_center_listview_apidata);

        taipei_sport_center_btn_cln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((taipei_sport_center_arrayListOfString!=null)&&(taipei_sport_center_arrayAdapterOfString!=null)){
                    taipei_sport_center_arrayListOfString.clear();
                    taipei_sport_center_arrayAdapterOfString.notifyDataSetChanged();
                    taipei_sport_center_textview_title.setText(stringTaipeiSportCenter+"-api資料已清空");
                }
            }
        });
        taipei_sport_center_btn_getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,stringAPIURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        taipei_sport_center_textview_title.setText(stringTaipeiSportCenter+"\n AAA");
                        try {
                            JSONObject jsonObject_result = response.getJSONObject("result");
                            JSONArray jsonArray_results = jsonObject_result.getJSONArray("results");
                            for (i = 0; i < jsonArray_results.length(); i++) {
                                JSONObject jsonObject_center = jsonArray_results.getJSONObject(i);
                                strCenterName = jsonObject_center.getString("名稱");
                                strCenterAddress = jsonObject_center.getString("地址");
                                strLat=jsonObject_center.getString("緯度");
                                strLng=jsonObject_center.getString("經度");
                                taipei_sport_center_arrayListOfString.add(strCenterName + "-\n" + strCenterAddress+"\n座標:"+strLat+","+strLng);
                            }
                            taipei_sport_center_arrayAdapterOfString = new ArrayAdapter<String>(TaipeiSportCenterActivity.this, android.R.layout.simple_list_item_1, taipei_sport_center_arrayListOfString);
                            taipei_sport_center_listview_apidata.setAdapter(taipei_sport_center_arrayAdapterOfString);
                            taipei_sport_center_textview_title.setText(stringTaipeiSportCenter+"\n API讀取資料成功");
                            taipei_sport_center_listview_apidata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    Toast.makeText(TaipeiSportCenterActivity.this,
//                                            "已讀取:"+taipei_sport_center_arrayListOfString.get(position),
//                                            Toast.LENGTH_SHORT).show();
                                    int intStartOfGeo=taipei_sport_center_arrayListOfString.get(position).indexOf("座標:");
                                    String strGeoPosition=taipei_sport_center_arrayListOfString.get(position).substring(intStartOfGeo+4);

                                    String strlabel=strLat+","+strLng+"(中心位置)";
                                    String strQuery=Uri.decode(strlabel);
                                    Intent intentGeo=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+strGeoPosition+"?q="+strQuery+"&z=16"));
                                    startActivity(intentGeo);

                                }
                            });


                        } catch (JSONException ex) {

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });
                Volley.newRequestQueue(TaipeiSportCenterActivity.this).add(request);
                taipei_sport_center_textview_title.setText(stringTaipeiSportCenter+"\n API資料讀取中～");
            }catch (Exception ex){
                ex.printStackTrace();
            }
            }
        });
    }
}