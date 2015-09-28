package digital.ryanbrown.costofliving;

import android.os.Bundle;
import android.sax.TextElementListener;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ryan on 9/26/2015.
 */
public class Cable extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Cable newInstance(int sectionNumber) {
        Cable fragment = new Cable();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Cable() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cable, container, false);

        final TextView tv = (TextView) rootView.findViewById(R.id.cable);
        final TextView quote = (TextView) rootView.findViewById(R.id.cable_quote_label);
        quote.setText(getRandomQuote());

        tv.addTextChangedListener(new TextWatcher(){

            @Override
            public void afterTextChanged(Editable s) {
                Data.data.put("cable", tv.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){

            }

        });

        return rootView;
    }

    private int getRandomQuote(){
        Random rand = new Random();
        ArrayList<Integer> quotes = new ArrayList();

        quotes.add(R.string.CABLE_1);
        quotes.add(R.string.CABLE_2);
        quotes.add(R.string.CABLE_3);

        int quote = rand.nextInt(quotes.size());
        return quotes.get(quote);
    }


}
