package digital.ryanbrown.costofliving;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Ryan on 9/26/2015.
 */
public class Optional extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Optional newInstance(int sectionNumber) {
        Optional fragment = new Optional();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Optional() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_optional, container, false);

        final TextView tvIncome = (TextView) rootView.findViewById(R.id.income);
        final TextView tvAge = (TextView) rootView.findViewById(R.id.age);
        final RadioGroup rGender = (RadioGroup) rootView.findViewById(R.id.gender_picker);

        rGender.check(R.id.gender_other);

        rGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.gender_female:
                        Data.data.put("gender", "female");
                        break;
                    case R.id.gender_male:
                        Data.data.put("gender", "male");
                        break;
                    case R.id.gender_other:
                    default:
                        Data.data.put("gender", "");
                        break;
                }
            }
        });

        tvIncome.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                Data.data.put("income", tvIncome.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        tvAge.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                Data.data.put("age", tvAge.getText().toString());
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
}
