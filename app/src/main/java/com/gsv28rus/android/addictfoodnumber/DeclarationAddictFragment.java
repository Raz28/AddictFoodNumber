package com.gsv28rus.android.addictfoodnumber;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DeclarationAddictFragment extends Fragment {

    private AddictList addictList;

    TextView mTextViewNumber;
    TextView mTextViewName;
    TextView mTextViewGeneral;
    TextView mTextViewBenefit;
    TextView mTextViewHarm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addictList = AddictList.getAddictList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addict_declaration, container, false);
        int pozition = getActivity().getIntent().getIntExtra(ListAddictFragment.SELECT_ADDICT_POSITION, 99);
        Addict addictSelected = addictList.getAddictByid(pozition);
        mTextViewNumber = view.findViewById(R.id.tvAddictNumber);
        mTextViewName = view.findViewById(R.id.tvAddictName);
        mTextViewGeneral = view.findViewById(R.id.tvAddictGeneral);
        mTextViewBenefit = view.findViewById(R.id.tvAddictBenefit);
        mTextViewHarm = view.findViewById(R.id.tvAddictHarm);

        mTextViewNumber.setText(addictSelected.getNumber());
        mTextViewName.setText(addictSelected.getName());
        mTextViewGeneral.setText(addictSelected.getGeneral());
        mTextViewBenefit.setText(addictSelected.getBenefit());
        mTextViewHarm.setText(addictSelected.getHarm());

        return view;
    }
}
