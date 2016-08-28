package cqu.edu.au.chickenlogs.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    static final String KEY_ID = "_id";
    static final String KEY_TYPE = "type";
    static final String KEY_WEIGHT = "weight";
    static final String KEY_EGGS_LAID = "eggs";
    static final String KEY_GRAIN_EATEN = "grain";
    static final String KEY_WATER = "water";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "chickenLogger";
    static final String DATABASE_TABLE = "chickens";
    static final int DATABASE_VERSION = 4;
    static final String DATABASE_CREATE = "create table chickens(_id integer primary key ," +
            "type String not null,weight integer not null,eggs integer not null,grain integer not null, water integer not null,timestamp DATE DEFAULT CURRENT_TIMESTAMP);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "upgrading database from version" + oldVersion + "to" + newVersion + ", which will destroy all old data");
            db.execSQL("Drop Table if exists chickens");
            onCreate(db);

        }
    }

    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;

    }

    public void close() {
        DBHelper.close();
    }

    public long insertLogs(String _id, String type, String weight, String eggs, String grain, String water) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_ID, _id);
        cv.put(KEY_TYPE, type);
        cv.put(KEY_WEIGHT, weight);
        cv.put(KEY_EGGS_LAID, eggs);
        cv.put(KEY_GRAIN_EATEN, grain);
        cv.put(KEY_WATER, water);
        return db.insert(DATABASE_TABLE, null, cv);

    }

    public boolean deleteLogs(long rowID) {
        return db.delete(DATABASE_TABLE, KEY_ID + "=" + rowID, null) > 0;

    }

    public Cursor getAlllogs(String type) {
        String query = "select * from chickens where type='" + type + "'";
        return
                db.rawQuery(query, null);
    }

}
