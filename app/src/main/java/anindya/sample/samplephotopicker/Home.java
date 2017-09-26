package anindya.sample.samplephotopicker;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Home extends Fragment {

    //Defining Variables
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Handler handler;
    FloatingActionButton fab;

    public static Home newInstance() {
        Home fragment = new Home();
        fragment.setRetainInstance(true);
        return fragment;
    }


    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view initialize and functionality declare

        // Set title bar
        ((MainActivity) getActivity()).setActionBarTitle(getActivity().getString(R.string.all_feeds_toolbar));

        // initialize tab layout with tab icon
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_feeds));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_profile));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // initialize handler to hide and show fab button
        handler = new Handler();

        // fab button initialize
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // simple action with text
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.click_action), Toast.LENGTH_SHORT).show();
            }
        });

        // initialize view pager
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        // for smooth transition between tabs
        viewPager.setOffscreenPageLimit(3);
        // initialize view pager adapter and setting that adapter
        final PagerAdapter adapter = new PageAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        // add tab layout listener into view pager listener
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // tab listener
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // set the current item for which tab is selected
                viewPager.setCurrentItem(tab.getPosition());
                // animate fab button when new tab selected
                fab.hide();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        fab.show();
                    }
                }, 200);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // do something with only view pager listener
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    // do something when view pager appeared
                    // Set title bar
                    ((MainActivity) getActivity()).setActionBarTitle(getActivity().getString(R.string.all_feeds_toolbar));
                }
                if (position == 1) {
                    // do something when view pager appeared
                    // Set title bar
                    ((MainActivity) getActivity()).setActionBarTitle(getActivity().getString(R.string.friends_feeds_toolbar));
                }
                if (position == 2) {
                    // do something when view pager appeared
                    // Set title bar
                    ((MainActivity) getActivity()).setActionBarTitle(getActivity().getString(R.string.profile_toolbar));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    // view pager adapter class to call different fragments
    class PageAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PageAdapter(FragmentManager fm, int numTabs) {
            super(fm);
            this.mNumOfTabs = numTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    FragmentA FragmentA = new FragmentA();
                    return FragmentA;
                case 1:
                    FragmentB FragmentB = new FragmentB();
                    return FragmentB;
                case 2:
                    FragmentC FragmentC = new FragmentC();
                    return FragmentC;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

}
