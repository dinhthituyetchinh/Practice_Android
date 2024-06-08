package database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Pokemon;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "pokemon.db";
    private static String DB_PATH = "";
    private SQLiteDatabase mDataBase;
    private final Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }

    public void createDataBase() throws IOException {
        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDataBase();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        String mPath = DB_PATH + DB_NAME;
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    // Phương thức này truy vấn cơ sở dữ liệu và trả về danh sách Pokemon
    public List<Pokemon> getPokemonList() {
        List<Pokemon> pokemonList = new ArrayList<>();
        try {
            openDataBase();
            Cursor cursor = mDataBase.rawQuery(
                    "SELECT pokemons.id, pokemons.name, pokemons.image, GROUP_CONCAT(types.name) AS types, pokemons.background_color " +
                            "FROM pokemons " +
                            "JOIN pokemon_types ON pokemon_types.id_pokemon = pokemons.id " +
                            "JOIN types ON pokemon_types.id_type = types.id " +
                            "GROUP BY pokemons.id, pokemons.name, pokemons.image, pokemons.background_color", null);

            // Log column names to verify they are as expected
            String[] columnNames = cursor.getColumnNames();
            for (String columnName : columnNames) {
                Log.d("DatabaseHelper", "Column name in cursor: " + columnName);
            }

            if (cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex("id");
                    int nameIndex = cursor.getColumnIndex("name");
                    int imageIndex = cursor.getColumnIndex("image");
                    int backgroundColorIndex = cursor.getColumnIndex("color");
                    int typesIndex = cursor.getColumnIndex("type");

                    // Check if any column index is -1 (indicating the column does not exist)
                    if (idIndex == -1 || nameIndex == -1 || imageIndex == -1 || backgroundColorIndex == -1 || typesIndex == -1) {
                        Log.e("DatabaseHelper", "One or more column indices are invalid.");
                        break;
                    }

                    String id = cursor.getString(idIndex);
                    String name = cursor.getString(nameIndex);
                    String image = cursor.getString(imageIndex);
                    String backgroundColor = cursor.getString(backgroundColorIndex);
                    String typesString = cursor.getString(typesIndex);

                    // Handle potential null values and split the types string
                    List<String> types = typesString != null ? Arrays.asList(typesString.split(",")) : new ArrayList<>();

                    Pokemon pokemon = new Pokemon(id, name, image, types, backgroundColor);
                    pokemonList.add(pokemon);
                } while (cursor.moveToNext());
            }
            cursor.close();
            close();
        } catch (SQLException e) {
            Log.e("DatabaseHelper", "Error retrieving pokemon list: " + e.getMessage());
        }
        return pokemonList;
    }


}
