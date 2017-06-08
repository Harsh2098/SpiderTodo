package com.hmproductions.spidertodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditorActivity extends AppCompatActivity {

    EditText itemName_editText;
    private int itemPosition;

    private static final String DEFAULT_ITEM_NAME = "default-item-name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        itemName_editText = (EditText)findViewById(R.id.itemName_editText);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String itemName = bundle.getString(MainActivity.ITEM_NAME, DEFAULT_ITEM_NAME);
        itemPosition = bundle.getInt(MainActivity.ITEM_POSITION, MainActivity.rowNum);

        itemName_editText.setText(itemName);
    }

    @Override
    protected void onPause() {
        super.onPause();

        MainActivity.items.get(itemPosition).setItemName(itemName_editText.getText().toString());
    }
}
