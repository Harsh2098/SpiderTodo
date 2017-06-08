package com.hmproductions.spidertodo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    ListAdapter adapter;
    Button addButton, deleteButton;
    public static ArrayList<Item> items = new ArrayList<Item>();
    public static int rowNum=1;

    public static final String ITEM_NAME = "item-name";
    public static final String ITEM_POSITION = "item-position";
    private static final int REQUEST_CODE = 101;

    private static final String ACTIVITY_RESULT = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.listView);
        View emptyListView = findViewById(R.id.emptyListView);
        adapter = new ListAdapter(MainActivity.this, items, 0);

        addButton = (Button)findViewById(R.id.addButton);
        deleteButton = (Button)findViewById(R.id.deleteButton);

        AddButtonClickListener();
        DeleteButtonClickListener();
        ListItemClickListener();

        listView.setAdapter(adapter);
        listView.setEmptyView(emptyListView);
    }

    public void AddButtonClickListener()
    {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                final EditText nameInput = new EditText(MainActivity.this);
                nameInput.setMaxLines(1);
                nameInput.setHint("Eg. Galaxy S7 Edge");

                builder
                        .setTitle("New item Name")
                        .setView(nameInput)
                        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                if(!(nameInput.getText().toString().equals("") || nameInput.getText().toString().isEmpty())) {
                                    items.add(rowNum - 1, new Item(rowNum, nameInput.getText().toString()));
                                    rowNum++;
                                    adapter.notifyDataSetChanged();
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Text field was empty", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

                adapter.notifyDataSetChanged();
            }
        });
    }

    public void DeleteButtonClickListener()
    {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rowNum == 1)
                {
                    Toast.makeText(MainActivity.this, "Todo list is empty", Toast.LENGTH_SHORT).show();
                }

                else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    final EditText rowInput = new EditText(MainActivity.this);
                    rowInput.setMaxLines(1);
                    rowInput.setHint("Eg. 1000");
                    rowInput.setInputType(InputType.TYPE_CLASS_NUMBER);

                    builder.setTitle("Delete item at Row")
                            .setView(rowInput)
                            .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (!(rowInput.getText().toString().equals("") || rowInput.getText().toString().isEmpty())) {
                                        if (Integer.parseInt(rowInput.getText().toString()) >= rowNum || Integer.parseInt(rowInput.getText().toString()) == 0) {
                                            Toast.makeText(MainActivity.this, "Item not found at this position", Toast.LENGTH_SHORT).show();
                                        } else {
                                            items.remove(Integer.parseInt(rowInput.getText().toString()) - 1);
                                            rowNum--;
                                            adapter.notifyDataSetChanged();
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, "Text field is empty", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }
            }
        });
    }

    public void ListItemClickListener()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = items.get(position).getItemName();
                Bundle bundle = new Bundle();
                bundle.putString(ITEM_NAME, name);
                bundle.putInt(ITEM_POSITION, position);

                Intent intent = new Intent(MainActivity.this, EditorActivity.class);

                intent.putExtras(bundle);

                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE)
        {
            Log.v(ACTIVITY_RESULT, "notify()");
            adapter.notifyDataSetChanged();
        }
    }
}
