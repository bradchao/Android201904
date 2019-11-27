package tw.org.iii.android201904;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String, String>> data;
    private String[] from = {"title", "content", "love"};
    private int[] to = {R.id.itemTitle, R.id.itemContent, R.id.itemLove};

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        initListView();


    }

    private void initListView() {
        data = new LinkedList<>();

        for (int i = 0; i < 100; i++) {
            HashMap<String, String> row = new HashMap<>();
            row.put(from[0], "Title " + (int) (Math.random() * 49 + 1));
            row.put(from[1], "content ... ");
            row.put(from[2], "OK");
            row.put("other", "data ...");
            data.add(row);
        }
        // ----------------------------------
//        adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
//        listView.setAdapter(adapter);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        // -------------------------------------
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
                showData(position);
            }
        });
    }

    private void showData(int index) {
        new AlertDialog.Builder(this)
                .setTitle("Brad")
                .setMessage(data.get(index).get(from[0]) + "\n" +
                        data.get(index).get("other"))
                .setPositiveButton("OK", null)
                .setCancelable(false)
                .create()
                .show();
    }

    public void newdata(View view) {
        HashMap<String, String> row = new HashMap<>();
        row.put(from[0], "Title " + (int) (Math.random() * 49 + 101));
        row.put(from[1], "content ... ");
        row.put(from[2], "NEW");
        data.add(0, row);

        //adapter.notifyDataSetChanged();
        myAdapter.notifyDataSetChanged();
    }

    public void clear(View view) {
        data.clear();
        //adapter.notifyDataSetChanged();
        myAdapter.notifyDataSetChanged();
    }


    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

            convertView = inflater.inflate(R.layout.itemv2, null);

            TextView title  = convertView.findViewById(R.id.itemv2Title);
            TextView content = convertView.findViewById(R.id.itemv2Content);
            ImageView love = convertView.findViewById(R.id.itemv2Love);
            title.setText(data.get(position).get(from[0]));
            content.setText(data.get(position).get(from[1]));
            love.setImageResource(
                    data.get(position).get(from[2]).equals("OK")?R.drawable.ok:R.drawable.xx);

            love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("brad", "click: " + position);
                    data.get(position).put(from[2], data.get(position).get(from[2]).equals("OK")?"NEW":"OK");
                    ((ImageView)v).setImageResource(
                            data.get(position).get(from[2]).equals("OK")?R.drawable.ok:R.drawable.xx);
                }
            });


            return convertView;
        }
    }

}
