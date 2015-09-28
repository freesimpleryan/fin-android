package digital.ryanbrown.costofliving;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Ryan on 9/26/2015.
 */
public class Housing extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Housing newInstance(int sectionNumber) {
        Housing fragment = new Housing();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Housing() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Data.NEW_SESSION = true;
        View rootView = inflater.inflate(R.layout.fragment_housing, container, false);

        final TextView tv = (TextView) rootView.findViewById(R.id.housing);
        final TextView quote = (TextView) rootView.findViewById(R.id.housing_quote_label);
        quote.setText(getRandomQuote());

        tv.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                Data.data.put("housing", tv.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        return rootView;
    }

    private int getRandomQuote(){
        Random rand = new Random();
        ArrayList<Integer> quotes = new ArrayList();

        quotes.add(R.string.HOUSING_1);
        quotes.add(R.string.HOUSING_2);
        quotes.add(R.string.HOUSING_3);
        quotes.add(R.string.HOUSING_4);
        quotes.add(R.string.HOUSING_5);

        int quote = rand.nextInt(quotes.size());
        return quotes.get(quote);
    }

}
