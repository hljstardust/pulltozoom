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
import android.widget.FrameLayout;
import android.widget.ListAdapter;

import com.ecloud.pulltozoomview.GridViewWithHeaderAndFooter;
import com.ecloud.pulltozoomview.PullToZoomBase;
import com.ecloud.pulltozoomview.PullToZoomGridViewEx;

/**
*
*@author yangliqiang
*@date 2016/8/16
*/
public class PullToZoomGridViewActivity extends ActionBarActivity {

    private PullToZoomGridViewEx gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_zoom_grid_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView = (PullToZoomGridViewEx) findViewById(R.id.gridview);

        String[] adapterData = new String[]{"Activity", "Service", "Content Provider", "Intent", "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient",
                "DDMS", "Android Studio", "Fragment", "Loader", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient", "Activity", "Service", "Content Provider", "Intent",
                "BroadcastReceiver", "ADT", "Sqlite3", "HttpClient"};

        gridView.setAdapter(new ArrayAdapter<String>(PullToZoomGridViewActivity.this, android.R.layout.simple_list_item_1, adapterData));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("ylq", "position = " + position);
            }
        });
        gridView.getPullRootView().setNumColumns(2);

        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenWidth = localDisplayMetrics.widthPixels;
        FrameLayout.LayoutParams localObject = new FrameLayout.LayoutParams(mScreenWidth, (int) (9.0F * (mScreenWidth / 16.0F)));
        gridView.setHeaderLayoutParams(localObject);
        gridView.setOnPullZoomListener(new PullToZoomBase.OnPullZoomListener() {
            @Override
            public void onPullZooming(int newScrollValue) {
            }

            @Override
            public void onPullZoomEnd() {

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
            gridView.setParallax(false);
            return true;
        } else if (id == R.id.action_parallax) {
            gridView.setParallax(true);
            return true;
        } else if (id == R.id.action_show_head) {
            gridView.setHideHeader(false);
            return true;
        } else if (id == R.id.action_hide_head) {
            gridView.setHideHeader(true);
            return true;
        } else if (id == R.id.action_disable_zoom) {
            gridView.setZoomEnabled(false);
            return true;
        } else if (id == R.id.action_enable_zoom) {
            gridView.setZoomEnabled(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
