package edu.guet.jjhome.indexsp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import edu.guet.jjhome.indexsp.R;
import edu.guet.jjhome.indexsp.adapter.ItemAdapter;
import edu.guet.jjhome.indexsp.model.Item;
import edu.guet.jjhome.indexsp.util.AppConstants;
import edu.guet.jjhome.indexsp.util.WebService;

public class MessageFragment extends Fragment {
    private ItemAdapter itemAdapter;

    private int selectedCount = 0;

    private Handler handler;
    private TextView txt_status;
    private ListView lv_items;

    WebService web;
    private String msg_type;
    private String read_status;
    private FloatingActionButton fab;

    public static MessageFragment newInstance(String msg_type, String read_status) {
        MessageFragment fragment = new MessageFragment();

        Bundle args = new Bundle();
        args.putString("msg_type", msg_type);
        args.putString("read_status", read_status);
        fragment.setArguments(args);

        Log.d("new Instance msg_type", " ");

        return fragment;
    }

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        ArrayList<Item> items = new ArrayList<>();
        Item item;
        for (int i=1; i<10; i++) {
            item = new Item("sender" + String.valueOf(i), "标题" + String.valueOf(i), "content" + String.valueOf(i), new DateTime().getMillis());
            item.msg_type = "report";
//            item.save();
            items.add(item);
        }

        Log.d("item size:", String.valueOf(items.size()));

        msg_type = getActivity().getIntent().getStringExtra("msg_type");
        if (msg_type == null) {
            msg_type = "report";
        }

        itemAdapter = new ItemAdapter(getActivity().getBaseContext(), items);

        handler = new Handler(new MsgHandler());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);

        lv_items = (ListView) rootView.findViewById(R.id.listView);
        txt_status = (TextView) rootView.findViewById(R.id.text_status);

        lv_items.setAdapter(itemAdapter);

        lv_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = itemAdapter.getItem(position);
                if (selectedCount > 0) {
                    //TODO: multi-selection
                } else {
                    Intent intent = new Intent(".Details");
                    intent.putExtra("item", item);
                    startActivity(intent);
                }
            }
        });

//        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
//        fab.setOnClickListener(clickListener);

//        Toast.makeText(getActivity().getBaseContext(), R.string.action_refresh_status, Toast.LENGTH_SHORT).show();
//        web = new WebService(getActivity().getBaseContext(), handler);
//        web.fetchContent(msg_type);

        web = new WebService(getActivity().getBaseContext(), handler);
        web.fetcchReport();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private class MsgHandler implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case AppConstants.STAGE_LOGIN:
                    break;
                case AppConstants.STAGE_GET_PAGE:
                    txt_status.setText(R.string.action_refresh_status);
                    txt_status.setVisibility(View.VISIBLE);
                    break;
                case AppConstants.STAGE_GET_ERROR:
                    txt_status.setText(R.string.state_get_error);
                    txt_status.setVisibility(View.VISIBLE);
                    break;
                case AppConstants.STAGE_GET_SUCCESS:
                    List<Item> items = Item.getItemsByTypeAndStatus(msg_type, read_status);
                    Log.d("success, test msg_type", msg_type);

                    if (items.size() > 0) {
                        itemAdapter.clear();
                        itemAdapter.addAll(items);
                        itemAdapter.notifyDataSetChanged();

                        txt_status.setVisibility(View.INVISIBLE);
                        lv_items.setVisibility(View.VISIBLE);
                    }
                    else {
                        txt_status.setText(R.string.list_no_data);
                        txt_status.setVisibility(View.VISIBLE);
                        lv_items.setVisibility(View.INVISIBLE);
                    }
                    break;
                case AppConstants.STAGE_NOT_LOGIN:
                    txt_status.setText(R.string.stage_not_login);
                    txt_status.setVisibility(View.VISIBLE);
                    lv_items.setVisibility(View.INVISIBLE);
                    break;
                case AppConstants.STAGE_LOGOUT:
                    Toast.makeText(getActivity(), R.string.stage_logout_success, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
            }
            return false;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_message, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_overview_refresh:
                Toast.makeText(getActivity(), R.string.action_refresh, Toast.LENGTH_SHORT).show();
//                web = new WebService(getActivity().getBaseContext(), handler);
//                web.fetchContent(msg_type);
                break;
            default:
                getActivity().onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

//    private View.OnClickListener clickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.fab:
//                    startActivity(new Intent(getActivity().getBaseContext(), CreateMessageActivity.class));
//                    break;
//            }
//        }
//    };

}
