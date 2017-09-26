package anindya.sample.samplephotopicker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class MainActivity extends AppCompatActivity {

    //Defining Variables
    String TAG = getClass().getName();
    private DrawerLayout drawerLayout;
    TextView mNavHeaderTitle;
    Toolbar mToolbar;
    MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create our manager instance after the content view is set
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
        // set a custom tint color for all system bars
        tintManager.setTintColor(Color.parseColor("#D50000"));

        // Set up the toolbar with actionbar title
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setActionBarTitle(getString(R.string.all_feeds_toolbar));

        // init navigation drawer
        initNavigationDrawer();

        // material search view initialization
        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        // search view listener add
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {

            // add action when user click on the search icon from the keyboard
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                Toast.makeText(getApplicationContext(), getString(R.string.click_action), Toast.LENGTH_SHORT).show();

                // close search view if it's open
                if (searchView.isSearchOpen()) {
                    searchView.closeSearch();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

        // set home fragment as default when this fragment launching
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Home fragment = new Home();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

    }

    // Set up the toolbar title
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    // initialize navigation drawer
    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    //Replacing the main content with item content
                    case R.id.home:
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        Home fragment = new Home();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with item content
                    case R.id.friends:
                        Toast.makeText(getApplicationContext(), getString(R.string.click_action), Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with item content
                    case R.id.profile:
                        Toast.makeText(getApplicationContext(), getString(R.string.click_action), Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with item content
                    case R.id.settings:
                        Toast.makeText(getApplicationContext(), getString(R.string.click_action), Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with item content
                    case R.id.help:
                        Toast.makeText(getApplicationContext(), getString(R.string.click_action), Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    //Replacing the main content with item content
                    case R.id.logout:
                        Toast.makeText(getApplicationContext(), getString(R.string.click_action), Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    //Rest of the case just show toast
                    default:
                        Toast.makeText(getApplicationContext(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                        return true;

                }
                return true;
            }
        });

         // customization for navigation header
        View header = navigationView.getHeaderView(0);
        mNavHeaderTitle = (TextView)header.findViewById(R.id.user_name);
        mNavHeaderTitle.setText("Amelia Silvia");
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        // actionbar toggle listener
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolar_menu, menu);

        // set menu for search bar
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    // back press listener
    @Override
    public void onBackPressed() {

        //close search view it's open
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

}
