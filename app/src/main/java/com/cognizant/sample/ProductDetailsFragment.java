package com.cognizant.sample;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link .} interface
 * to handle interaction events.
 * Use the {@link ProductDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private String productName;
    private TextView prdName;
    private DBHelper mydb;
    private String productPrice;
    private TextView prdPrice;
    private String productImage;
    private ImageView prdImg;
    private String uri="@drawable/";
    private Button add_cart;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    public static ProductDetailsFragment newInstance(String param1) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
      mydb = new  DBHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
      //  productName = getResources().getStringArray(R.array.electronics_list);
        Cursor rs = mydb.getData(mParam1);
        rs.moveToFirst();
        productName =rs.getString(rs.getColumnIndex(DBHelper.PRODUCT_NAME));
        productPrice= rs.getString(rs.getColumnIndex(DBHelper.PRODUCT_PRICE));
        productImage=rs.getString(rs.getColumnIndex(DBHelper.PRODUCT_IMG));
        prdName = (TextView) view.findViewById(R.id.prdname);
        prdPrice = (TextView) view.findViewById(R.id.product_price);
        prdImg= (ImageView) view.findViewById(R.id.product_img);
        prdName.setText(productName);
        prdPrice.setText(productPrice);
        uri = uri+productImage;
        int imageResource = getResources().getIdentifier(uri,null,getActivity().getPackageName());
        prdImg.setImageDrawable(getResources().getDrawable(imageResource));
        add_cart= (Button) view.findViewById(R.id.add_cart);
        add_cart.setOnClickListener(this);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onClick(View v) {
        mydb.updateCart(productName);
    }
}
