package com.gsv28rus.android.addictfoodnumber.database;

/**
 * Created by Stepan on 28.11.2017.
 */

public class SafeFoodDbSchema {

    public static final class NumbersTable {

        public final static String NAME = "numbers";

        public static final class Cols {

            public static final String ID = "_id";
            public static final String NUMBER = "number";
            public static final String NAME = "name";
            public static final String GENERAL = "general";
            public static final String BENEFIT = "benefit";
            public static final String HARM = "harm";
            public static final String DANGER = "danger";
            public static final String GROUP = "group";
        }
    }
}
