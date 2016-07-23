package com.example.youngchae.topofthe_pen;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.youngchae.topofthe_pen.fragment1.PageFragment1;
import com.example.youngchae.topofthe_pen.fragment1.RecyclerItem;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    BluetoothSPP bt;
    String receive;

    public static String Korean = "1e1603e03";
    public static String Math = "1b8c66001";
    public static String Science = "1e7865dc2";

    public static int korean= 90;
    public static int math = 0;
    public static int science = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PageFragment1());
        adapter.addFragment(new PageFragment2());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(4);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                int result = 0;
                int goal = 0;
                for(RecyclerItem recyclerItem : PageFragment1.items){
                    result += recyclerItem.getGoal();
                    goal += recyclerItem.getIng();
                }
                PageFragment2.circleProgress.setMax(result);
                PageFragment2.circleProgress.setProgress(result/goal);
//                PageFragment2.circleProgress.notify();
            }
        });
        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(pager);

        bt=new BluetoothSPP(this);

        if(!bt.isBluetoothAvailable())
        {
            Toast.makeText(getApplicationContext()
                    , "블루투스를 켜주세요"
                    , Toast.LENGTH_SHORT).show();
            finish();
        }

        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener()

        {
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext()
                        , "연결되었습니다", Toast.LENGTH_SHORT).show();
            }

            public void onDeviceDisconnected() {
                Toast.makeText(getApplicationContext()
                        , "연결이끊겼습니다"
                        , Toast.LENGTH_SHORT).show();
            }

            public void onDeviceConnectionFailed() {
            }
        });

        bt.setAutoConnectionListener(new BluetoothSPP.AutoConnectionListener() {
            public void onNewConnection(String name, String address) {
            }

            public void onAutoConnectionStarted() {
            }
        });
        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            public void onDataReceived(byte[] data, String message) {
                receive = message;
                RecyclerItem page;
                Log.d("dudco",receive);
                if(receive.equals(Korean)){
                    Log.d("dudco", "Korean :"+korean);
                    korean+=5;
                    page = PageFragment1.items.get(0);

                    page.setTime(korean);
                    page.setIng(korean);
                    PageFragment1.adapter.notifyDataSetChanged();
                }else if(receive.equals(Math)){
                    Log.d("dudco", "Math :"+math);
                    math+=5;
                    page = PageFragment1.items.get(1);

                    page.setTime(math);
                    page.setIng(math);
                    PageFragment1.adapter.notifyDataSetChanged();

                }else if(receive.equals(Science)){
                    Log.d("dudco", "Science :"+science);
                    science+=5;
                    page = PageFragment1.items.get(2);

                    page.setTime(science);
                    page.setIng(science);
                    PageFragment1.adapter.notifyDataSetChanged();
                }else{
                    Log.d("dudco","default");
                }
            }
        });

    }

    public void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) {
            bt.enable();
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);
                setup();
            }
        }
    }
    public void setup() {
        bt.autoConnect("main");
    }


}
