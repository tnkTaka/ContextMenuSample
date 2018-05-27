package local.hal.st32.android.contextmenusample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextMenuSampleActivity extends AppCompatActivity {

    private List<Map<String,String>> _list;

    private ListView _lvCurry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu_sample);

        _list = createList();
        _lvCurry = findViewById(R.id.lvCurry);

        createListView();
        registerForContextMenu(_lvCurry);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,View view,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,view,menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_mune_sample,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int listId = (int)info.id;
        Map<String,String> curry = _list.get(listId);
        String pointStr = curry.get("point");
        int point = Integer.valueOf(pointStr);

        int itemID = item.getItemId();
        switch (itemID){
            case R.id.menuContextUp:
                point++;
                break;
            case R.id.menuContextDown:
                if(point >= 1){
                    point--;
                }
                break;
        }

        curry.put("point",String.valueOf(point));
        _list.set(listId,curry);

        createListView();

        return super.onContextItemSelected(item);
    }

    private void createListView(){
        String[] from = {"name","point"};
        int[] to = {android.R.id.text1,android.R.id.text2};
        SimpleAdapter adapter = new SimpleAdapter(ContextMenuSampleActivity.this,_list,android.R.layout.simple_list_item_2,from,to);
        _lvCurry.setAdapter(adapter);
    }

    private List<Map<String,String>> createList(){
        List<Map<String,String>> list = new ArrayList<>();

        ArrayList<String> curryList = new ArrayList<>();
        curryList.add("ビーフカレー");
        curryList.add("チキンカレー");
        curryList.add("カツカレー");
        curryList.add("ポークカレー");
        curryList.add("シーフードカレー");
        curryList.add("キーマカレー");
        curryList.add("グリーンカレー");
        curryList.add("ドライカレー");

        for(String curry:curryList){
            Map<String,String> map = new HashMap<>();
            map.put("name",curry);
            map.put("point","0");
            list.add(map);
        }

        return list;
    }

}
