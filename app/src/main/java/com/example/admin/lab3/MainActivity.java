package com.example.admin.lab3;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Switch aSwitch;
    Button button;
    ActionMode mActionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = (Switch) findViewById(R.id.swDate);
        insertFragment();
        button = (Button) findViewById(R.id.bToggle);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode!=null){
                    return false;
                }
                mActionMode = MainActivity.this.startActionMode(mActionModeCallBack);
                v.setSelected(true);
                return true;
            }
        });
    }
    public void insertFragment(){
        Fragment fragment = new PickerFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("dateOK",aSwitch.isChecked());
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.vertLayout,fragment);
        transaction.commit();
    }

    public void togglePicker(View view) {
        insertFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuToggle){
            aSwitch.toggle();
            insertFragment();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_color:
                    button.setBackgroundResource(R.color.colorPrimary);
                    button.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };
}
