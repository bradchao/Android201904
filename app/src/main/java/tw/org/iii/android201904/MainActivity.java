package tw.org.iii.android201904;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data;
    private String[] from = {"title","content","love"};
    private int[] to = {R.id.itemTitle, R.id.itemContent, R.id.itemLove};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        initListView();


    }

    private void initListView(){
        data = new LinkedList<>();

        for(int i=0 ; i<100; i++) {
            HashMap<String, String> row = new HashMap<>();
            row.put(from[0], "Title " + (int)(Math.random()*49+1));
            row.put(from[1], "content ... ");
            row.put(from[2], "OK");
            data.add(row);
        }

        adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        listView.setAdapter(adapter);
    }

    public void newdata(View view) {
        HashMap<String, String> row = new HashMap<>();
        row.put(from[0], "Title " + (int)(Math.random()*49+101)  );
        row.put(from[1], "content ... ");
        row.put(from[2], "NEW");
        data.add(0, row);
        adapter.notifyDataSetChanged();
    }

    public void clear(View view) {
        data.clear();
        adapter.notifyDataSetChanged();
    }
}
