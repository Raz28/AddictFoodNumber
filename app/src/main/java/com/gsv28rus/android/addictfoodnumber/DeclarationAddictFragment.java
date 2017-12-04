package com.gsv28rus.android.addictfoodnumber;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gsv28rus.android.addictfoodnumber.database.DatabaseHelper;
import com.gsv28rus.android.addictfoodnumber.database.SafeFoodDbSchema;

public class DeclarationAddictFragment extends Fragment {


    Cursor mCursor;
    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mDatabase;

    TextView mTextViewNumber;
    TextView mTextViewName;
    TextView mTextViewGeneral;
    TextView mTextViewBenefit;
    TextView mTextViewHarm;
    CardView mCardViewGeneral;
    CardView mCardViewBenefit;
    CardView mCardViewHarm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addict_declaration, container, false);
        int pozition = getActivity().getIntent().getIntExtra(ListAddictFragment.SELECT_ADDICT_POSITION, 0);
        String[] selectionArg = {String.valueOf(pozition)};

        Toolbar toolbarDeclaration = view.findViewById(R.id.my_toolbar_declaration);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarDeclaration);

        mDatabaseHelper = new DatabaseHelper(getContext());
        mDatabase = mDatabaseHelper.getReadableDatabase();

        mCursor = mDatabase.query(SafeFoodDbSchema.NumbersTable.NAME, null, SafeFoodDbSchema.NumbersTable.Cols.ID + " = ?", selectionArg, null, null, null);
        mCursor.moveToFirst();
        mTextViewGeneral = view.findViewById(R.id.tvAddictGeneral);
        mTextViewBenefit = view.findViewById(R.id.tvAddictBenefit);
        mTextViewHarm = view.findViewById(R.id.tvAddictHarm);

        mTextViewGeneral.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.GENERAL)));
        mTextViewBenefit.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.BENEFIT)));
        mTextViewHarm.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.HARM)));

        toolbarDeclaration.setTitle(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.NUMBER)) + " " + mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.NAME)));

        mCardViewGeneral = view.findViewById(R.id.cardViewGeneral);
        mCardViewGeneral.setOnClickListener(mOnClickListener);
        mCardViewBenefit = view.findViewById(R.id.cardViewBenefit);
        mCardViewBenefit.setOnClickListener(mOnClickListener);
        mCardViewHarm = view.findViewById(R.id.cardViewHarm);
        mCardViewHarm.setOnClickListener(mOnClickListener);

        mCursor.close();

        return view;
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {

        LinearLayout.LayoutParams mLayoutParamsOpen = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams mLayoutParamsClose = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cardViewGeneral:
                    mTextViewGeneral.setLayoutParams(mLayoutParamsOpen);
                    mTextViewBenefit.setLayoutParams(mLayoutParamsClose);
                    mTextViewHarm.setLayoutParams(mLayoutParamsClose);
                    break;
                case R.id.cardViewBenefit:
                    mTextViewBenefit.setLayoutParams(mLayoutParamsOpen);
                    mTextViewGeneral.setLayoutParams(mLayoutParamsClose);
                    mTextViewHarm.setLayoutParams(mLayoutParamsClose);
                    break;
                case R.id.cardViewHarm:
                    mTextViewHarm.setLayoutParams(mLayoutParamsOpen);
                    mTextViewGeneral.setLayoutParams(mLayoutParamsClose);
                    mTextViewBenefit.setLayoutParams(mLayoutParamsClose);
                    break;
            }

        }
    };
}
