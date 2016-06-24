package com.cognizant.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.Fragment;

public class MainActivity extends FragmentActivity implements OnFragmentInteractionListener {

    private String[] productCategory;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productCategory = getResources().getStringArray(R.array.product_category);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, productCategory));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        Fragment elecProductFragment = ElectronicsProductFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_frame, elecProductFragment).commit();

    }

    @Override
    public void productDetails(String position) {
        Fragment productDetailsFragment = ProductDetailsFragment.newInstance(position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);

        transaction.replace(R.id.content_frame, productDetailsFragment).commit();
    }



    public class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int position) {
            switch (position) {
                case 0:
                    Fragment elecProductFragment = ElectronicsProductFragment.newInstance();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, elecProductFragment).commit();
                    mDrawerList.setItemChecked(position, true);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
                case 1:
                    Fragment furnitureProductFragment = FurnitureProductFragment.newInstance();
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, furnitureProductFragment).commit();
                    mDrawerList.setItemChecked(position, true);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
                default:
                    Fragment cartFragment = CartFragment.newInstance();
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame, cartFragment).commit();
                    mDrawerList.setItemChecked(position, true);
                    mDrawerLayout.closeDrawer(mDrawerList);
                    break;
            }
        }
    }
}
