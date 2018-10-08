package appstage1.com.inventoryappstage1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import appstage1.com.inventoryappstage1.dbHelper.InventoryContract;
import appstage1.com.inventoryappstage1.dbHelper.InventoryDbHelper;


public class AddActivity extends AppCompatActivity {

    private EditText mProductNameEditText;
    private EditText mProductPriceEditText;
    private EditText mProductQuantityEditText;
    private Spinner mProductSupplieNameSpinner;
    private EditText mProductSupplierPhoneNumberEditText;

    private int mSupplieName = InventoryContract.InventoryEntry.SUPPLIER_First;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mProductNameEditText = findViewById(R.id.product_name_edit_text);
        mProductPriceEditText = findViewById(R.id.product_price_edit_text);
        mProductQuantityEditText = findViewById(R.id.product_quantity_edit_text);
        mProductSupplieNameSpinner = findViewById(R.id.product_supplier_name_spinner);
        mProductSupplierPhoneNumberEditText = findViewById(R.id.product_supplier_phone_number_edit_text);
        setupSpinner();
    }

    private void setupSpinner() {

        ArrayAdapter productSupplieNameSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_supplier_options, android.R.layout.simple_spinner_item);

        productSupplieNameSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mProductSupplieNameSpinner.setAdapter(productSupplieNameSpinnerAdapter);

        mProductSupplieNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.supplier_SECOND))) {
                        mSupplieName = InventoryContract.InventoryEntry.SUPPLIER_SECOND;
                    } else if (selection.equals(getString(R.string.supplier_THIRD))) {
                        mSupplieName = InventoryContract.InventoryEntry.SUPPLIER_THIRD;
                    } else if (selection.equals(getString(R.string.supplier_FOURTH))) {
                        mSupplieName = InventoryContract.InventoryEntry.SUPPLIER_FORTH;
                    } else {
                        mSupplieName = InventoryContract.InventoryEntry.SUPPLIER_First;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSupplieName = InventoryContract.InventoryEntry.SUPPLIER_First;
            }
        });
    }


    private void insertProduct() {
        String productNameString = mProductNameEditText.getText().toString().trim();

        String productPriceString = mProductPriceEditText.getText().toString().trim();
        int productPriceInteger = Integer.parseInt(productPriceString);

        String productQuantityString = mProductQuantityEditText.getText().toString().trim();
        int productQuantityInteger = Integer.parseInt(productQuantityString);

        String productSupplierPhoneNumberString = mProductSupplierPhoneNumberEditText.getText().toString().trim();
        int productSupplierPhoneNumberInteger = Integer.parseInt(productSupplierPhoneNumberString);

        InventoryDbHelper mDbHelper = new InventoryDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_NAME, productNameString);
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_PRICE, productPriceInteger);
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_QUANTITY, productQuantityInteger);
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_SUPPLIER_NAME, mSupplieName);
        values.put(InventoryContract.InventoryEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, productSupplierPhoneNumberInteger);

        long newRowId = db.insert(InventoryContract.InventoryEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving product", Toast.LENGTH_SHORT).show();
            Log.d("Error message", "Doesn't insert row on table");

        } else {
            Toast.makeText(this, "Product saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
            Log.d("successfully message", "insert row on table");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        Log.d("message", "open Add Activity");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertProduct();
                finish();
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
