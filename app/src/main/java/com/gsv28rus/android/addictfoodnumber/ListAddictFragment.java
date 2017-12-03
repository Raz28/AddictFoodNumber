package com.gsv28rus.android.addictfoodnumber;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gsv28rus.android.addictfoodnumber.database.RecyclerAddictAdapter;
import com.gsv28rus.android.addictfoodnumber.database.DatabaseHelper;
import com.gsv28rus.android.addictfoodnumber.database.SafeFoodDbSchema;

import java.io.IOException;

public class ListAddictFragment extends Fragment {

    public final static String SELECT_ADDICT_POSITION = "position selected";

    RecyclerView mRecyclerView;

    Cursor mCursor;

    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabaseHelper = new DatabaseHelper(getContext());
        try {
            mDatabaseHelper.updateDataBase();
        } catch (IOException e) {
            throw new Error("UnableToUpdateDatabase");
        }

        mDatabase = mDatabaseHelper.getReadableDatabase();

        String[] columns = {SafeFoodDbSchema.NumbersTable.Cols.ID, SafeFoodDbSchema.NumbersTable.Cols.NUMBER, SafeFoodDbSchema.NumbersTable.Cols.NAME};
        mCursor = mDatabase.query(SafeFoodDbSchema.NumbersTable.NAME, columns, null, null, null, null, null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addict_list, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_addict_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new RecyclerAddictAdapter(mCursor));
        return view;
    }
}
