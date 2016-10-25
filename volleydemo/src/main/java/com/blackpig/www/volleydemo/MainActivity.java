package com.blackpig.www.volleydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit_text) EditText editText;
    @BindView(R.id.button) Button button_query;
    @BindView(R.id.tv1) TextView tv1;
    @BindView(R.id.tv2) TextView tv2;
    @BindView(R.id.tv3) TextView tv3;
    @BindView(R.id.tv4) TextView tv4;

    private String myPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void OnClick() {
        myPhoneNumber = editText.getText().toString();
        if (editText == null) {
            Toast.makeText(this, "号码不能为空", Toast.LENGTH_LONG).show();
        } else {
            Volley_Get();
        }
    }

    private void Volley_Get() {
        //接口地址 myPhoneNumber为我们输入的电话号码  Key:f0120c7f4bd62444c2d97bd8addba527
        String url = "http://apis.juhe.cn/mobile/get?phone=" + myPhoneNumber
                + "&key=f0120c7f4bd62444c2d97bd8addba527";
        //===================Volley StringRequest用法================================
        //1.通过newRequestQueue()获取一个RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);
        //2.new一个StringRequest,并重写接口
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        Volley_Json(json);
                        Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, "失败" + volleyError.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
        });
        //3.将请求添加到请求队列中
        queue.add(request);
    }

    //解析获取到的json
    private void Volley_Json(String json) {
        //返回JSON示例
        // {"resultcode":"200",
        // "reason":"Return Successd!",
        // "result":{"province":"湖北","city":"武汉","areacode":"027","zip":"430000",
        // "company":"中国移动","card":"未知"},
        // "error_code":0}
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject object = jsonObject.getJSONObject("result");
            tv1.setText("归属地:" + object.getString("province") + "-"
                    + object.getString("city"));
            tv2.setText("区号:" + object.getString("areacode"));
            tv3.setText("运营商:" + object.getString("company"));
            tv4.setText("用户类型:" + object.getString("card"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
