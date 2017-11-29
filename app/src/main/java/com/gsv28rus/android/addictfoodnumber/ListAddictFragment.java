package com.gsv28rus.android.addictfoodnumber;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gsv28rus.android.addictfoodnumber.database.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;

public class ListAddictFragment extends Fragment {

    public final static String SELECT_ADDICT_POSITION = "position selected";

    private RecyclerView mRecyclerView;
    private ArrayList mAddictList;
    private AddictList addictList;

    DatabaseHelper mDatabaseHelper;
    SQLiteDatabase mDatabase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addictList = new AddictList();
        mAddictList = addictList.getAddictArrayList();

        mDatabaseHelper = new DatabaseHelper(getContext());

        try {
            mDatabaseHelper.updateDataBase();
        } catch (IOException e) {
            throw new Error("UnableToUpdateDatabase");
        }

        mDatabase = mDatabaseHelper.getWritableDatabase();

        String product = "";

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM numbers", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Toast.makeText(getContext(), cursor.getString(1), Toast.LENGTH_SHORT).show();
            product = cursor.getString(1) + " | ";
            cursor.moveToNext();
        }
        cursor.close();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addict_list, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_addict_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new AdpaterRecycler());
        return view;
    }

    public class AdpaterRecycler extends RecyclerView.Adapter<ViewHolderAddict> {


        @Override
        public ViewHolderAddict onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolderAddict(LayoutInflater.from(getContext()).inflate(R.layout.item_addict, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolderAddict holder, final int position) {

            holder.mTextViewNumber.setText(addictList.getAddictByid(position).getNumber());
            holder.mTextViewName.setText(addictList.getAddictByid(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), AddictDeclarationActivity.class);
                    intent.putExtra(SELECT_ADDICT_POSITION, position);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mAddictList.size();
        }
    }

    public class ViewHolderAddict extends RecyclerView.ViewHolder {

        TextView mTextViewNumber;
        TextView mTextViewName;

        public ViewHolderAddict(View itemView) {
            super(itemView);
            mTextViewNumber = itemView.findViewById(R.id.textViewName);
            mTextViewName = itemView.findViewById(R.id.textViewDeclaratiom);
        }

        public void Bind() {

        }
    }
}
