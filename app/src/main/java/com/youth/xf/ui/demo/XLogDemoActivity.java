package com.youth.xf.ui.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.youth.xf.BaseActivity;
import com.youth.xf.R;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XLogDemoActivity extends BaseActivity implements View.OnClickListener {
    String json="{ \"success\": true, \"reason\": \"\", \"data\": [ { \"time\": \"2016-04-26 00:21:26\", \"context\": \"到潍坊市【潍坊转运中心】\" }, { \"time\": \"2016-04-25 18:16:34\", \"context\": \"威海市【威海集散仓】，正发往【潍坊转运中心】\" }, { \"time\": \"2016-04-25 18:15:42\", \"context\": \"到威海市【威海集散仓】\" }, { \"time\": \"2016-04-25 15:16:00\", \"context\": \"威海市【荣成】，正发往【威海集散仓】\" }, { \"time\": \"2016-04-25 15:15:27\", \"context\": \"威海市【荣成】，【林波/13863000310】已揽收\" } ], \"status\": 3, \"exname\": \"huitongkuaidi\", \"ico\": \"http://www.kuaidi.com/data/upload/201407/htky_logo.gif\", \"phone\": \"400-956-5656\", \"url\": \"http://www.800bestex.com\", \"nu\": \"70186506140478\", \"company\": \"百世汇通\" }";
    String xml="<index><title>穿衣</title><zs>较冷</zs><tipt>穿衣指数</tipt><des>建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。</des><title>洗车</title><zs>较适宜</zs><tipt>洗车指数</tipt><des>较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。</des><title>旅游</title><zs>适宜</zs><tipt>旅游指数</tipt><des>天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。</des><title>感冒</title><zs>易发</zs><tipt>感冒指数</tipt><des>昼夜温差很大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。</des><title>运动</title><zs>较适宜</zs><tipt>运动指数</tipt><des>天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。</des><title>紫外线强度</title><zs>中等</zs><tipt>紫外线强度指数</tipt><des>属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。</des></index>";
    List<String> list=new ArrayList<>();
    Map<String,String> map=new HashMap<>();
    Button button;
    Button button2;
    Button button3;
    Button button5;
    Button button4;
    TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_log_demo;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        list.add("list数据 测试一");
        list.add("list数据 测试二");

        map.put("name","王兴春");
        map.put("job","Android工程师");
    }

    @Override
    public void initView() {
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button5 = (Button) findViewById(R.id.button5);
        button4 = (Button) findViewById(R.id.button4);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                XLog.v("xlog用法展示");
                XLog.d("xlog用法展示");
                XLog.e("xlog用法展示");
                XLog.i("xlog用法展示");
                XLog.w("xlog用法展示");
                XLog.wtf("xlog用法展示");
                break;
            case R.id.button2:
                XLog.list(list);
                break;
            case R.id.button3:
                XLog.map(map);
                break;
            case R.id.button4:
                XLog.json(json);
                break;
            case R.id.button5:
                XLog.xml(xml);
                break;
        }
        textView.setText(XLog.getFormatLog());
    }
}
