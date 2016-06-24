package com.cognizant.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ElectronicsProductFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView electricProductList;
    private String[] productList;
    private OnFragmentInteractionListener mListener;



    public static ElectronicsProductFragment newInstance(){
        ElectronicsProductFragment electronicsProductFragment = new ElectronicsProductFragment();
        Bundle args = new Bundle();
        electronicsProductFragment.setArguments(args);
        return electronicsProductFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_list_page,container,false);
        productList = getResources().getStringArray(R.array.electronic_product_list);
        electricProductList = (ListView) view.findViewById(R.id.product_listview);
        electricProductList.setAdapter(new  ArrayAdapter<String>(getActivity(),R.layout.product_list,productList));
        electricProductList.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      mListener.productDetails(productList[position]);
    }

}
