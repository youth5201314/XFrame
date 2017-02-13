package com.youth.xf.data;

import com.youth.xf.bean.News;

import java.util.ArrayList;

public class NewDataSource {
    private static ArrayList<News> newsList;

    //测试数据
    public static ArrayList<News> getNewsList(){
        if(null==newsList || newsList.isEmpty()){
            newsList = new ArrayList<>();
            newsList.add(new News(News.TYPE_SINGLE_PICTURE,"智能手机行业正处于关键转折点，下一战场会在哪里？","https://pic.36krcnd.com/avatar/201701/17062818/1ucsedy4pdb4aqyu.jpg!heading","缪定纯•明星公司","58分钟前"));
            newsList.add(new News(News.TYPE_NONE_PICTURE,"创始团队如何保持对公司的控制权？Snapchat这里可能有一份指南","36kr","2017.02.04"));
            newsList.add(new News(News.TYPE_SINGLE_PICTURE,"创投机构回应上海政府风投补偿：返税意义更大","http://n.sinaimg.cn/tech/transform/20160126/KR43-fxnuvxc1994221.jpg","投资，职场","2017-01-16 10:36"));
            newsList.add(new News(News.TYPE_NONE_PICTURE,"上海新规：天使投资有损失可获补偿 最高补六成","段旭","2017.02.04"));
            newsList.add(new News(News.TYPE_SINGLE_PICTURE,"马云说新零售是在拯救线下 实业大佬会同意吗","https://pic.36krcnd.com/avatar/201701/12073251/vw1wo7bw0l73j8zp.jpg","早期项目","2017-01-16 10:36 "));
            newsList.add(new News(News.TYPE_NONE_PICTURE,"中国万吨巡洋舰开始下饺子，俄海军却至少还要等十年","译东西","2017.02.04"));
            newsList.add(new News(News.TYPE_NONE_PICTURE,"高管集体离职致Twitter股价开盘跳水逾6%","社交媒体","2016.02.04"));
            newsList.add(new News(News.TYPE_SINGLE_PICTURE,"传格力为推自家手机与门禁联动 员工不买难进门","http://p2.qhimg.com/t019db1294a2e7eb1db.jpg?size=600x400","前沿科技","2017-01-16 10:36"));
            newsList.add(new News(News.TYPE_SINGLE_PICTURE,"安检繁琐无用？来看看这些缴获的违禁品","http://p9.qhimg.com/t01c2084745dc313fd1.jpg","投资，职场","2013.02.04"));
            newsList.add(new News(News.TYPE_NONE_PICTURE,"清华控股将收购两家半导体制造公司","36kr","2017.02.04"));
            newsList.add(new News(News.TYPE_SINGLE_PICTURE,"雷军称小米今年目标破千亿，是实力还是吹牛皮？","http://p7.qhimg.com/t0139528269ae1ac5a0.jpg","前沿科技","2017-01-16 10:36"));
            newsList.add(new News(News.TYPE_NONE_PICTURE,"平安银行陷年终奖风波 最悲伤年终奖——1.5元","社交媒体","2017.01.04"));
            newsList.add(new News(News.TYPE_SINGLE_PICTURE,"人工智能会取代人类？别担心，你想多了","http://p7.qhimg.com/t01e82f909e7c2c350c.jpg","36kr","2017.02.04"));
        }
        return newsList;
    }
}
