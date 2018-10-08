package com.example.android.inventoryapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.content.ContentValues;

import com.example.android.inventoryapp.data.BookContract;
import com.example.android.inventoryapp.data.BookContract.BookEntry;
import com.example.android.inventoryapp.data.BookDbHelper;


/**
 * Displays list of books that were entered and stored in the app.
 */
public class MainActivity extends AppCompatActivity {

    private BookDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new BookDbHelper(this);
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        BookDbHelper mDbHelper = new BookDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + BookContract.BookEntry.TABLE_NAME,
                null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // books table in the database).
            TextView displayView = (TextView) findViewById(R.id.text_view_book);
            displayView.setText("Number of rows in books database table: " + cursor.getCount());
        } finally {
            cursor.close();
        }
    }

    private void insertBook() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and a certain book's attributes are the values.
        ContentValues values = new ContentValues();
        values.put(BookEntry.COLUMN_PRODUCT_NAME, "Of Mice and Men");
        values.put(BookEntry.COLUMN_PRICE, 10);
        values.put(BookEntry.COLUMN_QUANTITY, 1);
        values.put(BookEntry.COLUMN_SUPPLIER_NAME, "Penguin Publishing");
        values.put(BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER, "626-555-1234");

        long newRowId = db.insert(BookEntry.TABLE_NAME, null, values);
    }

    private void deleteAllBooks() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int deleteRow = db.delete(BookEntry.TABLE_NAME, null, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_list.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertBook();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllBooks();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}