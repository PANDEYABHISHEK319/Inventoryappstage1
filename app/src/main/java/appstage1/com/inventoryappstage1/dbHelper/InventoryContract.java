package appstage1.com.inventoryappstage1.dbHelper;

import android.provider.BaseColumns;

public class InventoryContract {


    //constructor
    public InventoryContract() {
    }


    public final static class InventoryEntry implements BaseColumns {


        //Name of table DB
        public final static String TABLE_NAME = "product";

        //Column of table
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME = "product_name";
        public final static String COLUMN_PRODUCT_PRICE = "price";
        public final static String COLUMN_PRODUCT_QUANTITY = "quantity";
        public final static String COLUMN_PRODUCT_SUPPLIER_NAME = "supplier_name";
        public final static String COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";

        // SUPPLIER_NAME LIST VALUES
        public final static int SUPPLIER_First = 0;
        public final static int SUPPLIER_SECOND = 1;
        public final static int SUPPLIER_THIRD = 2;
        public final static int SUPPLIER_FORTH = 3;


    }
}
