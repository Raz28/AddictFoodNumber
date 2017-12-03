package com.gsv28rus.android.addictfoodnumber;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        mDatabaseHelper = new DatabaseHelper(getContext());
        mDatabase = mDatabaseHelper.getReadableDatabase();

        mCursor = mDatabase.query(SafeFoodDbSchema.NumbersTable.NAME, null, SafeFoodDbSchema.NumbersTable.Cols.ID + " = ?", selectionArg, null, null, null);
        mCursor.moveToFirst();
        mTextViewNumber = view.findViewById(R.id.tvAddictNumber);
        mTextViewName = view.findViewById(R.id.tvAddictName);
        mTextViewGeneral = view.findViewById(R.id.tvAddictGeneral);
        mTextViewBenefit = view.findViewById(R.id.tvAddictBenefit);
        mTextViewHarm = view.findViewById(R.id.tvAddictHarm);

        mTextViewNumber.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.NUMBER)));
        mTextViewName.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.NAME)));
        mTextViewGeneral.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.GENERAL)));
        mTextViewBenefit.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.BENEFIT)));
        mTextViewHarm.setText(mCursor.getString(mCursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.HARM)));

        mCursor.close();

        return view;
    }
}
