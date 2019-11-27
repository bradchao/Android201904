package tw.org.iii.android201904;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        initListView();


    }

    private void initListView(){
        data = new LinkedList<>();

        HashMap<String,String> row = new HashMap<>();
        row.put("title", "Title1");
        data.add(row);

        adapter = new SimpleAdapter(this, data, R.layout.item, null, null);
        listView.setAdapter(adapter);
    }
}
