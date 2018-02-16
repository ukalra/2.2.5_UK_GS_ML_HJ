package examples.pltw.org.collegeapp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wdumas on 4/8/16.
 */
public class FamilyListFragment extends ListFragment {
    private static final String TAG = FamilyListFragment.class.getName();

    private Family mFamily;

    public FamilyListFragment() {
        mFamily = Family.get();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.family_members_title);

        FamilyMemberAdapter adapter = new FamilyMemberAdapter(mFamily.getFamily());
        setListAdapter(adapter);
        setListAdapter(adapter);
        setHasOptionsMenu(true);
    }

    private class FamilyMemberAdapter extends ArrayAdapter<FamilyMember> {
        public FamilyMemberAdapter(ArrayList<FamilyMember> family) {
            super(getActivity(), 0, family);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_family_member, null);
            }

            FamilyMember f = getItem(position);

            TextView nameTextView = (TextView)convertView.findViewById(R.id.family_member_list_item_nameTextView);
            nameTextView.setText(f.toString());
            Log.d(TAG, "The type of FamilyMember at position " + position + " is " + f.getClass().getName());

            return convertView;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        ListView listView = (ListView)v.findViewById(android.R.id.list);
        registerForContextMenu(listView);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_family_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FamilyMemberAdapter adapter = (FamilyMemberAdapter)getListAdapter();
        switch (item.getItemId()) {
            case R.id.menu_item_new_guardian:
                Log.d(TAG, "Selected add new guardian.");
                Guardian guardian = new Guardian();
                Family.get().addFamilyMember(guardian);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_item_new_sibling:
                Log.d(TAG, "Selected add new sibling.");
                Sibling sibling = new Sibling();
                Family.get().addFamilyMember(sibling);
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        Log.d(TAG, "Creating Context Menu.");
        getActivity().getMenuInflater().inflate(R.menu.family_list_item_context,
                menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d(TAG, "Context item selected.");
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        FamilyMemberAdapter adapter = (FamilyMemberAdapter) getListAdapter();
        final FamilyMember familyMember = adapter.getItem(position);

        switch (item.getItemId()) {
            case R.id.menu_item_delete_family_member:
                Family.get().deleteFamilyMember(familyMember);
                adapter.notifyDataSetChanged();
                Backendless.Data.of(FamilyMember.class).remove(familyMember,new
                        AsyncCallback<Long>() {
                            @Override
                            public void handleResponse(Long response) {
                                Log.i(TAG, familyMember.toString() + " deleted");
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Log.e(TAG, fault.getMessage());
                            }
                        });
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        FamilyMemberAdapter adapter = (FamilyMemberAdapter) getListAdapter();
        adapter.notifyDataSetChanged();
    }

}
