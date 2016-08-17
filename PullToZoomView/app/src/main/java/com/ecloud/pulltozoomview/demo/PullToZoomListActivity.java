package com.ecloud.pulltozoomview.demo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.ecloud.pulltozoomview.PullToZoomBase;
import com.ecloud.pulltozoomview.PullToZoomListViewEx;

/**
*
*@author yangliqiang
*@date 2016/8/16
*/
public class PullToZoomListActivity extends ActionBarActivity {

    private PullToZoomListViewEx listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom_list_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (PullToZoomListViewEx) findViewById(R.id.listview);

        String[] adapterData = new String[]{"Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient",
                "DDMS", "Android Studio", "Fragment", "Loader", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient"};

        listView.setAdapter(new ArrayAdapter<String>(PullToZoomListActivity.this, android.R.layout.simple_list_item_1, adapterData));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("ylq", "position = " + position);
            }
        });

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        AbsListView.LayoutParams localObject = new AbsListView.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        listView.setHeaderLayoutParams(localObject);

        listView.setOnPullZoomListener(new PullToZoomBase.OnPullZoomListener() {
            @Override
            public void onPullZooming(int newScrollValue) {
                Log.e("ylq", "onPullZooming");
            }

            @Override
            public void onPullZoomEnd() {
                Log.e("ylq", "onPullZoomEnd");

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_normal) {
            listView.setParallax(false);
            return true;
        } else if (id == R.id.action_parallax) {
            listView.setParallax(true);
            return true;
        } else if (id == R.id.action_show_head) {
            listView.setHideHeader(false);
            return true;
        } else if (id == R.id.action_hide_head) {
            listView.setHideHeader(true);
            return true;
        } else if (id == R.id.action_disable_zoom) {
            listView.setZoomEnabled(false);
            return true;
        } else if (id == R.id.action_enable_zoom) {
            listView.setZoomEnabled(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
