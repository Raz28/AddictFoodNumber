package com.gsv28rus.android.addictfoodnumber;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.gsv28rus.android.addictfoodnumber.database.DatabaseHelper;
import com.gsv28rus.android.addictfoodnumber.database.SafeFoodDbSchema;

public class DeclarationAddictFragment extends Fragment {

    Boolean mBooleanOpen = true;
    Cursor mCursor;
    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mDatabase;

    TextView mTextViewGeneral;
    TextView mTextViewBenefit;
    TextView mTextViewHarm;
    TextView mTextViewUsing;
    TextView mTextViewLaw;

    CardView mCardViewGeneral;
    CardView mCardViewBenefit;
    CardView mCardViewHarm;
    CardView mCardViewUsing;
    CardView mCardViewLaw;

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
        setHasOptionsMenu(true);

        mDatabaseHelper = new DatabaseHelper(getContext());
        mDatabase = mDatabaseHelper.getReadableDatabase();

        mCursor = mDatabase.query(SafeFoodDbSchema.NumbersTable.NAME, null, SafeFoodDbSchema.NumbersTable.Cols.ID + " = ?", selectionArg, null, null, null);
        mCursor.moveToFirst();
        mTextViewGeneral = view.findViewById(R.id.tvAddictGeneral);
        mTextViewBenefit = view.findViewById(R.id.tvAddictBenefit);
        mTextViewHarm = view.findViewById(R.id.tvAddictHarm);
        mTextViewUsing = view.findViewById(R.id.tvAddictUsing);
        mTextViewLaw = view.findViewById(R.id.tvAddictLaw);

        mTextViewGeneral.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.GENERAL)));
        mTextViewBenefit.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.BENEFIT)));
        mTextViewHarm.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.HARM)));
        mTextViewUsing.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.USING)));
        mTextViewLaw.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.LAW)));


        toolbarDeclaration.setTitle(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.NUMBER)) + " " + mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.NAME)));

        mCardViewGeneral = view.findViewById(R.id.cardViewGeneral);
        mCardViewGeneral.setOnClickListener(mOnClickListener);

        mCardViewBenefit = view.findViewById(R.id.cardViewBenefit);
        mCardViewBenefit.setOnClickListener(mOnClickListener);

        mCardViewHarm = view.findViewById(R.id.cardViewHarm);
        mCardViewHarm.setOnClickListener(mOnClickListener);

        mCardViewUsing = view.findViewById(R.id.cardViewUsing);
        mCardViewUsing.setOnClickListener(mOnClickListener);

        mCardViewLaw = view.findViewById(R.id.cardViewLaw);
        mCardViewLaw.setOnClickListener(mOnClickListener);

        mCursor.close();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.declaration_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open:
                LinearLayout.LayoutParams mLayoutParamsOpen = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                LinearLayout.LayoutParams mLayoutParamsClose = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);

                if (mBooleanOpen) {
                    mTextViewGeneral.setLayoutParams(mLayoutParamsOpen);
                    mTextViewBenefit.setLayoutParams(mLayoutParamsOpen);
                    mTextViewHarm.setLayoutParams(mLayoutParamsOpen);
                    mTextViewUsing.setLayoutParams(mLayoutParamsOpen);
                    mTextViewLaw.setLayoutParams(mLayoutParamsOpen);
                    mBooleanOpen = false;
                } else {
                    mTextViewGeneral.setLayoutParams(mLayoutParamsClose);
                    mTextViewBenefit.setLayoutParams(mLayoutParamsClose);
                    mTextViewHarm.setLayoutParams(mLayoutParamsClose);
                    mTextViewUsing.setLayoutParams(mLayoutParamsClose);
                    mTextViewLaw.setLayoutParams(mLayoutParamsClose);
                    mBooleanOpen = true;
                }

        }
        return true;
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
                    mTextViewUsing.setLayoutParams(mLayoutParamsClose);
                    mTextViewLaw.setLayoutParams(mLayoutParamsClose);
                    break;
                case R.id.cardViewBenefit:
                    mTextViewGeneral.setLayoutParams(mLayoutParamsClose);
                    mTextViewBenefit.setLayoutParams(mLayoutParamsOpen);
                    mTextViewHarm.setLayoutParams(mLayoutParamsClose);
                    mTextViewUsing.setLayoutParams(mLayoutParamsClose);
                    mTextViewLaw.setLayoutParams(mLayoutParamsClose);
                    break;
                case R.id.cardViewHarm:
                    mTextViewGeneral.setLayoutParams(mLayoutParamsClose);
                    mTextViewBenefit.setLayoutParams(mLayoutParamsClose);
                    mTextViewHarm.setLayoutParams(mLayoutParamsOpen);
                    mTextViewUsing.setLayoutParams(mLayoutParamsClose);
                    mTextViewLaw.setLayoutParams(mLayoutParamsClose);
                    break;
                case R.id.cardViewUsing:
                    mTextViewGeneral.setLayoutParams(mLayoutParamsClose);
                    mTextViewBenefit.setLayoutParams(mLayoutParamsClose);
                    mTextViewHarm.setLayoutParams(mLayoutParamsClose);
                    mTextViewUsing.setLayoutParams(mLayoutParamsOpen);
                    mTextViewLaw.setLayoutParams(mLayoutParamsClose);
                    break;
                case R.id.cardViewLaw:
                    mTextViewGeneral.setLayoutParams(mLayoutParamsClose);
                    mTextViewBenefit.setLayoutParams(mLayoutParamsClose);
                    mTextViewHarm.setLayoutParams(mLayoutParamsClose);
                    mTextViewUsing.setLayoutParams(mLayoutParamsClose);
                    mTextViewLaw.setLayoutParams(mLayoutParamsOpen);
                    break;
            }

        }
    };
}
