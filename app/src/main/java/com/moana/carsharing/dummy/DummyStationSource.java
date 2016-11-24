package com.moana.carsharing.dummy;

import android.content.ContentValues;

import com.moana.carsharing.station.StationProvider;

import java.util.ArrayList;

public class DummyStationSource {
    public static ArrayList<ContentValues> getRentList() {
        ArrayList<ContentValues> arrayList = new ArrayList<>();

        // Dummy 1
        ContentValues values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "rent1".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "四維行政中心");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "高雄市苓雅區四維三路2號");
        values.put(StationProvider.FIELD_LAT, 22.6204309);
        values.put(StationProvider.FIELD_LNG, 120.3098272);
        values.put(StationProvider.FIELD_STATION_PHOTO, "http://pic.pimg.tw/jende168/1367568101-4022866151.jpg");
        values.put(StationProvider.FIELD_TOTAL, 10);
        values.put(StationProvider.FIELD_USAGE, 5);
        values.put(StationProvider.FIELD_IS_RENT, 1);
        arrayList.add(values);

        // Dummy 2
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "rent2".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "鳳山行政中心");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "高雄市鳳山區光復路二段132號");
        values.put(StationProvider.FIELD_LAT, 22.6299537);
        values.put(StationProvider.FIELD_LNG, 120.3415605);
        values.put(StationProvider.FIELD_STATION_PHOTO, "http://pic.mygonews.com/fetch.php?v=4659399c87213b23732f8ae3cacbf230.jpg&t=newsPhoto");
        values.put(StationProvider.FIELD_TOTAL, 10);
        values.put(StationProvider.FIELD_USAGE, 5);
        values.put(StationProvider.FIELD_IS_RENT, 1);
        arrayList.add(values);

        // Dummy 3
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "rent3".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "高鐵左營站");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "高雄市左營區高鐵路105號");
        values.put(StationProvider.FIELD_LAT, 22.6873814);
        values.put(StationProvider.FIELD_LNG, 120.30588);
        values.put(StationProvider.FIELD_STATION_PHOTO, "http://pic.pimg.tw/dolpuppy1/1336476441-2050761243.jpg");
        values.put(StationProvider.FIELD_TOTAL, 10);
        values.put(StationProvider.FIELD_USAGE, 5);
        values.put(StationProvider.FIELD_IS_RENT, 1);
        arrayList.add(values);

        // Dummy 4
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "rent4".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "高雄火車站");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "高雄市三民區建國320號");
        values.put(StationProvider.FIELD_LAT, 22.6397615);
        values.put(StationProvider.FIELD_LNG, 120.2999183);
        values.put(StationProvider.FIELD_STATION_PHOTO, "http://www.awem.com.tw/11/11/11000122/338280.jpg");
        values.put(StationProvider.FIELD_TOTAL, 10);
        values.put(StationProvider.FIELD_USAGE, 5);
        values.put(StationProvider.FIELD_IS_RENT, 1);
        arrayList.add(values);

        // Dummy 5
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "rent5".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "美麗島站");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "高雄市新興區中山一路115號");
        values.put(StationProvider.FIELD_LAT, 22.631386);
        values.put(StationProvider.FIELD_LNG, 120.2997623);
        values.put(StationProvider.FIELD_STATION_PHOTO, "http://farm4.static.flickr.com/3041/2894573271_e415ba9fee_o.jpg");
        values.put(StationProvider.FIELD_TOTAL, 10);
        values.put(StationProvider.FIELD_USAGE, 5);
        values.put(StationProvider.FIELD_IS_RENT, 1);
        arrayList.add(values);

        // Dummy 6
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "rent6".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "西子灣站");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "高雄市鼓山區");
        values.put(StationProvider.FIELD_LAT, 22.6215405);
        values.put(StationProvider.FIELD_LNG, 120.2723397);
        values.put(StationProvider.FIELD_STATION_PHOTO, "http://pic.pimg.tw/yingoyingo/4a6f0903e6519.jpg");
        values.put(StationProvider.FIELD_TOTAL, 10);
        values.put(StationProvider.FIELD_USAGE, 5);
        values.put(StationProvider.FIELD_IS_RENT, 1);
        arrayList.add(values);

        // Dummy 7
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "rent7".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "凱旋站");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "高雄市前鎮區中山三路2-1號號");
        values.put(StationProvider.FIELD_LAT, 22.5969);
        values.put(StationProvider.FIELD_LNG, 120.3131243);
        values.put(StationProvider.FIELD_STATION_PHOTO, "http://pic.pimg.tw/ice2006/1197213827_n.jpg");
        values.put(StationProvider.FIELD_TOTAL, 10);
        values.put(StationProvider.FIELD_USAGE, 5);
        values.put(StationProvider.FIELD_IS_RENT, 1);
        arrayList.add(values);

        // Dummy 8
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "rent8".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "凹子底站");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "高雄市鼓山區博愛二路21號");
        values.put(StationProvider.FIELD_LAT, 22.6563565);
        values.put(StationProvider.FIELD_LNG, 120.2943339);
        values.put(StationProvider.FIELD_STATION_PHOTO, "https://www.google.com.tw/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=0ahUKEwjU2_SloJbQAhUGwbwKHa02BUUQjBwIBA&url=http%3A%2F%2F5.blog.xuite.net%2F5%2F7%2Fb%2F7%2F17972557%2Fblog_1691199%2Ftxt%2F43994558%2F0.jpg&psig=AFQjCNFrXqz3WKfBq5wJcUYKPZltS-WnXQ&ust=1478594606842442");
        values.put(StationProvider.FIELD_TOTAL, 10);
        values.put(StationProvider.FIELD_USAGE, 5);
        values.put(StationProvider.FIELD_IS_RENT, 1);
        arrayList.add(values);

        // Dummy 9
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "rent9".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "小港站");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "高雄市小港區沿海一路280號");
        values.put(StationProvider.FIELD_LAT, 22.5648119);
        values.put(StationProvider.FIELD_LNG, 120.3516634);
        values.put(StationProvider.FIELD_STATION_PHOTO, "http://pic.pimg.tw/ice2006/1197262071.jpg");
        values.put(StationProvider.FIELD_TOTAL, 10);
        values.put(StationProvider.FIELD_USAGE, 5);
        values.put(StationProvider.FIELD_IS_RENT, 1);
        arrayList.add(values);

        return arrayList;
    }

    public static ArrayList<ContentValues> getPlugList() {
        ArrayList<ContentValues> arrayList = new ArrayList<>();

        // Dummy 1
        ContentValues values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "plug1".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "都會南");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "台中市龍井區都會南街151號");
        values.put(StationProvider.FIELD_LAT, 24.1900696);
        values.put(StationProvider.FIELD_LNG, 120.5839046);
        values.put(StationProvider.FIELD_STATION_PHOTO, "");
        values.put(StationProvider.FIELD_TOTAL, 18);
        values.put(StationProvider.FIELD_USAGE, 5);
        values.put(StationProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        // Dummy 2
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "plug2".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "雲林客運");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "雲林縣斗六市大學路三段100號");
        values.put(StationProvider.FIELD_LAT, 23.6969719);
        values.put(StationProvider.FIELD_LNG, 120.5338262);
        values.put(StationProvider.FIELD_STATION_PHOTO, "");
        values.put(StationProvider.FIELD_TOTAL, 13);
        values.put(StationProvider.FIELD_USAGE, 4);
        values.put(StationProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        // Dummy 3
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "plug3".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "花蓮東華站");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "花蓮縣壽豐鄉大學路二段1號");
        values.put(StationProvider.FIELD_LAT, 23.8943519);
        values.put(StationProvider.FIELD_LNG, 121.5411446);
        values.put(StationProvider.FIELD_STATION_PHOTO, "http://www.walkerland.com.tw/images/upload/poi/p4608/m37349/19031d6da4e425c0e8c211b1219caad7c0fd1283.jpg");
        values.put(StationProvider.FIELD_TOTAL, 10);
        values.put(StationProvider.FIELD_USAGE, 4);
        values.put(StationProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        // Dummy 4
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "plug4".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "桃園大溪");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "桃園市大溪區瑞安路二段235號");
        values.put(StationProvider.FIELD_LAT, 24.8669494);
        values.put(StationProvider.FIELD_LNG, 121.2645276);
        values.put(StationProvider.FIELD_STATION_PHOTO, "");
        values.put(StationProvider.FIELD_TOTAL, 15);
        values.put(StationProvider.FIELD_USAGE, 4);
        values.put(StationProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        // Dummy 5
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "plug5".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "嘉義太保");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "嘉義縣太保市高鐵西路168號");
        values.put(StationProvider.FIELD_LAT, 23.458967);
        values.put(StationProvider.FIELD_LNG, 120.3207159);
        values.put(StationProvider.FIELD_STATION_PHOTO, "http://mw2.google.com/mw-panoramio/photos/medium/16588921.jpg");
        values.put(StationProvider.FIELD_TOTAL, 8);
        values.put(StationProvider.FIELD_USAGE, 0);
        values.put(StationProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        // Dummy 6
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "plug6".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "花連新城站");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "花蓮縣新城鄉新興路30-1號");
        values.put(StationProvider.FIELD_LAT, 24.1310352);
        values.put(StationProvider.FIELD_LNG, 121.6431411);
        values.put(StationProvider.FIELD_STATION_PHOTO, "http://farm9.staticflickr.com/8192/8111989824_8d167d834c.jpg");
        values.put(StationProvider.FIELD_TOTAL, 5);
        values.put(StationProvider.FIELD_USAGE, 4);
        values.put(StationProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        // Dummy 7
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "plug7".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "神岡停車場");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "台中市神岡區大富路20巷47號");
        values.put(StationProvider.FIELD_LAT, 24.2568513);
        values.put(StationProvider.FIELD_LNG, 120.7101598);
        values.put(StationProvider.FIELD_STATION_PHOTO, "");
        values.put(StationProvider.FIELD_TOTAL, 42);
        values.put(StationProvider.FIELD_USAGE, 0);
        values.put(StationProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        // Dummy 8
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "plug8".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "嘉義阿里山客運停車場");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "嘉義縣太保市保鐵七路");
        values.put(StationProvider.FIELD_LAT, 23.453725);
        values.put(StationProvider.FIELD_LNG, 120.3190169);
        values.put(StationProvider.FIELD_STATION_PHOTO, "");
        values.put(StationProvider.FIELD_TOTAL, 10);
        values.put(StationProvider.FIELD_USAGE, 0);
        values.put(StationProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        // Dummy 9
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "plug9".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "高雄南臺灣客運停車場");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "高雄市仁武區高鐵路1999號");
        values.put(StationProvider.FIELD_LAT, 22.6945);
        values.put(StationProvider.FIELD_LNG, 120.3192946);
        values.put(StationProvider.FIELD_STATION_PHOTO, "");
        values.put(StationProvider.FIELD_TOTAL, 5);
        values.put(StationProvider.FIELD_USAGE, 0);
        values.put(StationProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        // Dummy 10
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "plug10".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "苗栗客運-1");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "苗栗縣苑裡鎮苑港里5-1號");
        values.put(StationProvider.FIELD_LAT, 24.561156);
        values.put(StationProvider.FIELD_LNG, 120.8195553);
        values.put(StationProvider.FIELD_STATION_PHOTO, "");
        values.put(StationProvider.FIELD_TOTAL, 3);
        values.put(StationProvider.FIELD_USAGE, 0);
        values.put(StationProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        // Dummy 11
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "plug11".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "苗栗客運-2");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "苗栗市水源里7鄰水源24之2號");
        values.put(StationProvider.FIELD_LAT, 24.5409306);
        values.put(StationProvider.FIELD_LNG, 120.8091391);
        values.put(StationProvider.FIELD_STATION_PHOTO, "");
        values.put(StationProvider.FIELD_TOTAL, 3);
        values.put(StationProvider.FIELD_USAGE, 0);
        values.put(StationProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        // Dummy 11
        values = new ContentValues();
        values.put(StationProvider.FIELD_ID, "plug12".hashCode());
        values.put(StationProvider.FIELD_STATION_NAME, "車麗屋");
        values.put(StationProvider.FIELD_STATION_ADDRESS, "新北市板橋區中山路一段277號");
        values.put(StationProvider.FIELD_LAT, 25.0139465);
        values.put(StationProvider.FIELD_LNG, 121.4670474);
        values.put(StationProvider.FIELD_STATION_PHOTO, "http://www.carshop.com.tw/images/car_quality_goodle_3.jpg");
        values.put(StationProvider.FIELD_TOTAL, 10);
        values.put(StationProvider.FIELD_USAGE, 0);
        values.put(StationProvider.FIELD_IS_RENT, 0);
        arrayList.add(values);

        return arrayList;
    }
}
