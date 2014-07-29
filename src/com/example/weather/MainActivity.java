package com.example.weather;

import android.app.Activity;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
 
public class MainActivity extends Activity {
	
	
	Cursor cursor;
	final int STATE_MEDIA = 0;
	final int STATE_SONG  = 1;
	
	
	int currentState = STATE_MEDIA;
	
	
	int key;
    private String[] drawerListViewItems;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

	
	String[] columns = null;
	String[] displayField = null;
	int[] displayViews = new int[] {android.R.id.text1};

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
       
        Bundle bundle = getIntent().getExtras();
    	key = bundle.getInt("KEY");    
        
        
        
        // get list items from strings.xml
        drawerListViewItems = getResources().getStringArray(R.array.items);
        // get ListView defined in activity_main.xml
        drawerListView = (ListView) findViewById(R.id.left_drawer);
 
        
        drawerListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_listview_item, drawerListViewItems));
 
         
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
 
        
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                R.drawable.ic_launcher,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
                );
 
 
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
 
        drawerLayout.setDrawerShadow(R.drawable.ic_launcher, GravityCompat.START);
 
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
    }
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
         actionBarDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
 
         // call ActionBarDrawerToggle.onOptionsItemSelected(), if it returns true
        // then it has handled the app icon touch event
 
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
 
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
    	
    	
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
          
        	
        	switch(position)
        	{
        	case 0:

				columns = new String[] {MediaStore.Audio.Media.DATA,
				MediaStore.Audio.Media.TITLE,
				MediaStore.Audio.Media._ID,
				MediaStore.Audio.Media.DISPLAY_NAME,
				MediaStore.Audio.Media.MIME_TYPE,
				MediaStore.Audio.Media.ALBUM,
				MediaStore.Audio.Media.DURATION
				};
				displayField = new String[] {MediaStore.Audio.Media.DISPLAY_NAME};
				cursor = managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,columns,null,null,null);
				currentState = STATE_SONG;
	
        		
        	}
        		
        		
        	
        	
        	Toast.makeText(MainActivity.this, ((TextView)view).getText(), Toast.LENGTH_LONG).show();
            drawerLayout.closeDrawer(drawerListView);
 
        }
    }
}