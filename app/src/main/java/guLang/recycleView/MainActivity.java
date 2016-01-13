package guLang.recycleView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.guLang.recycleView.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    protected Toolbar toolbar;
    protected Spinner spinner;
    private RecyclerView recyclerView;
    private List<ImageItem> beanList;
    private RecyclerAdapter adapter;
    private String des[] = {"girl1", "girl2", "girl3", "girl4", "girl5", "girl6", "girl7", "girl8", "girl9", "girl10", "girl11", "girl12"
            , "girl13", "girl14"};

    private int resId[] = {R.drawable.a36, R.drawable.a40, R.drawable.a47, R.drawable.a57, R.drawable.a60
            , R.drawable.a36, R.drawable.a40, R.drawable.a47, R.drawable.a57, R.drawable.a60, R.drawable.a36, R.drawable.a40, R.drawable.a47, R.drawable.a57
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        initSpinner();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner = (Spinner) findViewById(R.id.toolbar_category);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //设置布局显示方式
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, true));
        //设置添加删除item时候的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        beanList = new ArrayList<>();
        for (int i = 0; i < des.length; i++) {
            ImageItem bean = new ImageItem();
            bean.setResId(resId[i]);
            bean.setTitle(des[i]);
            beanList.add(bean);
        }
        adapter = new RecyclerAdapter(this, beanList);
        recyclerView.setAdapter(adapter);

    }

    private void initSpinner() {
        String data[] = this.getResources().getStringArray(R.array.categorys);
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        // 为adapter设置下拉菜单样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinner设置adapter
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
                break;
            case 1:
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
                break;
            case 2:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                break;
            case 3:
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayout.HORIZONTAL, false));
                break;
            case 4:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
