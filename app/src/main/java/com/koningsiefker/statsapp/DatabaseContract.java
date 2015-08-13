package com.koningsiefker.statsapp;

import android.provider.BaseColumns;

/**
 * Created by Brendan on 8/12/2015.
 */
public class DatabaseContract {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "catanstats.db";
    private static final String TYPE_TEXT = " TEXT";
    private static final String TYPE_INTEGER = " INTEGER";
    private static final String TYPE_INTEGER_UNIQUE = " INTEGER UNIQUE";
    private static final String TYPE_INTEGER_DEFAULT = " INTEGER DEFAULT 0";
    private static final String COMMA = ", ";
    private static final String EQUALS = " = ";
    private static final String AND = " AND ";

    private DatabaseContract(){}

    public static abstract class TableOne implements BaseColumns{
        public static final String TABLE_NAME  = "tblGames";
        public static final String COLUMN1_NAME = "gameId";
        public static final String COLUMN2_NAME = "turnCount";
        public static final String COLUMN3_NAME = "savedDate";

        public static final String COLUMN_LIST = " (" +
                COLUMN1_NAME + COMMA +
                COLUMN2_NAME + COMMA +
                COLUMN3_NAME  + ")";

        public static final String CREATE_QUERY = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN1_NAME + TYPE_INTEGER_UNIQUE + COMMA +
                COLUMN2_NAME + TYPE_INTEGER  + COMMA +
                COLUMN3_NAME + TYPE_TEXT  + " )";

        public static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;


    }

    public static abstract class TableTwo implements BaseColumns{
        public static final String TABLE_NAME  = "tblGameDetails";
        public static final String COLUMN1_NAME = "gameId";
        public static final String COLUMN2_NAME = "twoCount";
        public static final String COLUMN3_NAME = "threeCount";
        public static final String COLUMN4_NAME = "fourCount";
        public static final String COLUMN5_NAME = "fiveCount";
        public static final String COLUMN6_NAME = "sixCount";
        public static final String COLUMN7_NAME = "sevenCount";
        public static final String COLUMN8_NAME = "eightCount";
        public static final String COLUMN9_NAME = "nineCount";
        public static final String COLUMN10_NAME = "tenCount";
        public static final String COLUMN11_NAME = "elevenCount";
        public static final String COLUMN12_NAME = "twelveCount";
        public static final String COLUMN13_NAME = "yellowCount";
        public static final String COLUMN14_NAME = "blueCount";
        public static final String COLUMN15_NAME = "greenCount";
        public static final String COLUMN16_NAME = "blackCount";


        public static final String COLUMN_LIST = " (" +
                COLUMN1_NAME + COMMA +
                COLUMN2_NAME + COMMA +
                COLUMN3_NAME + COMMA +
                COLUMN4_NAME + COMMA +
                COLUMN5_NAME + COMMA +
                COLUMN6_NAME + COMMA +
                COLUMN7_NAME + COMMA +
                COLUMN8_NAME + COMMA +
                COLUMN9_NAME + COMMA +
                COLUMN10_NAME + COMMA +
                COLUMN11_NAME + COMMA +
                COLUMN12_NAME + COMMA +
                COLUMN13_NAME + COMMA +
                COLUMN14_NAME + COMMA +
                COLUMN15_NAME + COMMA +
                COLUMN16_NAME  + ")";

        public static final String CREATE_QUERY = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN1_NAME + TYPE_INTEGER_UNIQUE + COMMA +
                COLUMN2_NAME + TYPE_INTEGER  + COMMA +
                COLUMN3_NAME + TYPE_INTEGER  + COMMA +
                COLUMN4_NAME + TYPE_INTEGER  + COMMA +
                COLUMN5_NAME + TYPE_INTEGER  + COMMA +
                COLUMN6_NAME + TYPE_INTEGER  + COMMA +
                COLUMN7_NAME + TYPE_INTEGER  + COMMA +
                COLUMN8_NAME + TYPE_INTEGER  + COMMA +
                COLUMN9_NAME + TYPE_INTEGER  + COMMA +
                COLUMN10_NAME + TYPE_INTEGER  + COMMA +
                COLUMN11_NAME + TYPE_INTEGER  + COMMA +
                COLUMN12_NAME + TYPE_INTEGER  + COMMA +
                COLUMN13_NAME + TYPE_INTEGER  + COMMA +
                COLUMN14_NAME + TYPE_INTEGER  + COMMA +
                COLUMN15_NAME + TYPE_INTEGER  + COMMA +
                COLUMN16_NAME + TYPE_INTEGER  + " )";

        public static final String DROP_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;


    }




}
