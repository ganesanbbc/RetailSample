package com.cognizant.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class CartFragment extends Fragment  {


    private DBHelper productDb;
    private ArrayList<HashMap<String, String>> hashMaps;
    private ListView cartList;
    private Button removeBtn;
    CartListViewAdapter cartListViewAdapter;
    private TextView totalAmount;

    public CartFragment() {
        // Required empty public constructor
    }


    public static CartFragment newInstance() {

        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productDb = new DBHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.cart_page, container, false);
        cartList = (ListView) view.findViewById(R.id.cart_listview);
        totalAmount = (TextView) view.findViewById(R.id.total);
        loadCart();

        cartList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                removeBtn=(Button) view.findViewById(R.id.remove);
                removeBtn.setVisibility(View.VISIBLE);
                removeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> map = hashMaps.get(position);
                        productDb.removeCart(map.get(DBHelper.PRODUCT_NAME));
                        map.remove(position);
                        loadCart();

                    }

               });

                return true;
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        cartListViewAdapter.notifyDataSetInvalidated();
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void loadCart(){

        hashMaps= productDb.displayCart();
        cartListViewAdapter = new CartListViewAdapter(getActivity(),hashMaps);
        cartList.setAdapter(cartListViewAdapter);
        totalAmount.setText(String.valueOf(productDb.getTotal()));
    }
}
