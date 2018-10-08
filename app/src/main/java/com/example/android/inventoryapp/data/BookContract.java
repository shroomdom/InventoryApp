package com.example.android.inventoryapp.data;

import android.provider.BaseColumns;

public final class BookContract {

    private BookContract() {}

    public static abstract class BookEntry implements BaseColumns {

        public static final String TABLE_NAME = "books";
        public final static String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";
    }
}
