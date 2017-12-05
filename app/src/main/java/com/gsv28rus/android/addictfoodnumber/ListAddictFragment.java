package com.gsv28rus.android.addictfoodnumber;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gsv28rus.android.addictfoodnumber.database.RecyclerAddictAdapter;
import com.gsv28rus.android.addictfoodnumber.database.DatabaseHelper;
import com.gsv28rus.android.addictfoodnumber.database.SafeFoodDbSchema;

import java.io.IOException;

public class ListAddictFragment extends Fragment {

    public final static String SELECT_ADDICT_POSITION = "position selected";

    String mGetExtraSearch;
    RecyclerView mRecyclerView;

    Cursor mCursor;

    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addict_list, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_addict_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mDatabaseHelper = new DatabaseHelper(getContext());
        try {
            mDatabaseHelper.updateDataBase();
        } catch (IOException e) {
            throw new Error("UnableToUpdateDatabase");
        }

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mCursor.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        mDatabase = mDatabaseHelper.getReadableDatabase();

        if (getArguments() != null) mGetExtraSearch = getArguments().getString(ListAddictActivity.ARG_SEARCH_STRING);

        if (mGetExtraSearch != null && mGetExtraSearch.length() != 0) {
            mCursor = mDatabase.rawQuery("select * from " + SafeFoodDbSchema.NumbersTable.NAME + " where " + SafeFoodDbSchema.NumbersTable.Cols.NUMBER + " like ?"
                    + " OR " + SafeFoodDbSchema.NumbersTable.Cols.NAME + " like ?", new String[]{"%" + mGetExtraSearch + "%", "%" + mGetExtraSearch + "%"});
        } else {
            mCursor = mDatabase.query(SafeFoodDbSchema.NumbersTable.NAME, null, null, null, null, null, null);
        }
        RecyclerAddictAdapter addictAdapter = new RecyclerAddictAdapter(mCursor);
        mRecyclerView.setAdapter(addictAdapter);

    }
}
