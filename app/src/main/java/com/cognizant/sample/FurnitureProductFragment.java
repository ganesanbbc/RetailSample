package com.cognizant.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FurnitureProductFragment extends Fragment implements AdapterView.OnItemClickListener{

    private String[] productList;
    private ListView furnitureProductList;
    private OnFragmentInteractionListener mListener;

    public FurnitureProductFragment() {
    }

    public static FurnitureProductFragment newInstance() {
        FurnitureProductFragment fragment = new FurnitureProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_list_page,container,false);
        productList = getResources().getStringArray(R.array.furniture_products_list);
        furnitureProductList = (ListView) view.findViewById(R.id.product_listview);
        furnitureProductList.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.product_list,productList));
        furnitureProductList.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mListener.productDetails(productList[position]);
    }
}
