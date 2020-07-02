package com.example.icecreamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button TheWorksButton;
    Button ResetButton;
    Button OrderButton;
    CheckBox PeanutCheckBox;
    CheckBox AlmondCheckBox;
    CheckBox StrawberryCheckBox;
    CheckBox GummyBearCheckBox;
    CheckBox MMCheckBox;
    CheckBox BrownieCheckBox;
    CheckBox OreosCheckBox;
    CheckBox MarshmellowCheckBox;
    SeekBar SeekBar;
    TextView SeekBarTextView;
    TextView PriceTextView;
    TextView TotalTextView;
    Spinner IceCreamSpinner;
    Spinner SizeSpinner;

    String SpinSize;
    String Flavor;
    double totalSale =0.0;
    double peanut;
    double size;
    double strawberry;
    double mm;
    double almond;
    double price;
    double brownie;
    double oreo;
    double gummy;
    double marshmellow;
    String Size;


    ArrayList<OrderItem> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ResetButton = findViewById(R.id.ResetButton);
        OrderButton = findViewById(R.id.OrderButton);
        AlmondCheckBox = findViewById(R.id.AlmondCheckBox);
        StrawberryCheckBox = findViewById(R.id.StrawberryCheckBox);
        PeanutCheckBox = findViewById(R.id.PeanutCheckBox);
        MMCheckBox = findViewById(R.id.MMCheckBox);
        BrownieCheckBox = findViewById(R.id.BrownieCheckBox);
        OreosCheckBox = findViewById(R.id.OreoCheckBox);
        GummyBearCheckBox = findViewById(R.id.GummyBearCheckBox);
        MarshmellowCheckBox = findViewById(R.id.MarshmellowCheckBox);
        SeekBarTextView = findViewById(R.id.SeekBarTextView);
        SeekBar = findViewById(R.id.SeekBar);
        TotalTextView = findViewById(R.id.TotalTextView);
        PriceTextView = findViewById(R.id.PreiceTextView);
        TheWorksButton = findViewById(R.id.TheWorksButton);
        orders = new ArrayList<OrderItem>();


        OrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                Date currentTime = Calendar.getInstance().getTime();

                orders.add(new OrderItem("Date: " + currentTime, "\nFlavor: " + Flavor, "\nSize: " + Size, "\nCost: " + price));
            }
        });


        TheWorksButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "The Works", Toast.LENGTH_SHORT).show();
                PeanutCheckBox.setChecked(true);
                AlmondCheckBox.setChecked(true);
                StrawberryCheckBox.setChecked(true);
                GummyBearCheckBox.setChecked(true);
                MMCheckBox.setChecked(true);
                BrownieCheckBox.setChecked(true);
                OreosCheckBox.setChecked(true);
                MarshmellowCheckBox.setChecked(true);
                calculate();

            }


        });


        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Reset", Toast.LENGTH_SHORT).show();
                PeanutCheckBox.setChecked(false);
                AlmondCheckBox.setChecked(false);
                StrawberryCheckBox.setChecked(false);
                GummyBearCheckBox.setChecked(false);
                MMCheckBox.setChecked(false);
                BrownieCheckBox.setChecked(false);
                OreosCheckBox.setChecked(false);
                MarshmellowCheckBox.setChecked(false);
                SeekBar.setProgress(0);
                SizeSpinner.setSelection(0);
                IceCreamSpinner.setSelection(0);
                calculate();
            }

        });


        SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
                SeekBarTextView.setText(Integer.toString(progress) + " oz");
                calculate();


            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {

            }
        });

        IceCreamSpinner = findViewById(R.id.IceCreamSpinner);
        IceCreamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "Selected Item " + position, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    Flavor = "Vanilla";
                }
                if (position == 1) {
                    Flavor = "Chocolate";
                } else if (position == 2) {
                    Flavor = "Strawberry";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SizeSpinner = findViewById(R.id.SizeSpinner);
        SizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "Selected Item " + position, Toast.LENGTH_SHORT).show();

                if (position == 0) {
                    size = 2.99;
                    Size = "Small";
                }
                if (position == 1) {
                    size = 3.99;
                    Size = "Medium";
                } else if (position == 2) {
                    size = 4.99;
                    Size = "Large";
                }
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        PeanutCheckBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Peanut Checked", Toast.LENGTH_SHORT).show();
                if (PeanutCheckBox.isChecked()) {
                    peanut = 0.15;
                    calculate();
                } else {
                    peanut = 0.00;
                    calculate();
                }
            }
        });
        AlmondCheckBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (AlmondCheckBox.isChecked()) {
                    almond = 0.15;
                    calculate();
                } else {
                    almond = 0.00;
                    calculate();
                }
            }
        });
        StrawberryCheckBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (StrawberryCheckBox.isChecked()) {
                    strawberry = 0.20;
                    calculate();
                } else {
                    strawberry = 0.00;
                    calculate();
                }
            }
        });
        GummyBearCheckBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (GummyBearCheckBox.isChecked()) {
                    gummy = 0.20;
                    calculate();
                } else {
                    gummy = 0.00;
                    calculate();
                }
            }
        });
        MMCheckBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (MMCheckBox.isChecked()) {
                    mm = 0.25;
                    calculate();
                } else {
                    mm = 0.00;
                    calculate();
                }
            }
        });
        BrownieCheckBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (BrownieCheckBox.isChecked()) {
                    brownie = 0.20;
                    calculate();
                } else {
                    brownie = 0.00;
                    calculate();
                }
            }
        });
        OreosCheckBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (OreosCheckBox.isChecked()) {
                    oreo = 0.20;
                    calculate();
                } else {
                    oreo = 0.00;
                    calculate();
                }
            }
        });
        MarshmellowCheckBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (MarshmellowCheckBox.isChecked()) {
                    marshmellow = 0.15;
                    calculate();
                } else {
                    marshmellow = 0.00;
                    calculate();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_order_history) {
            Toast.makeText(this, "History", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, OrderHistoryActivity.class);
            i.putExtra("KEY", orders);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            i.putExtra("KEY", "Marius was here. May the 4th be with you!!");
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculate() {

        totalSale = 0.0;

        if (PeanutCheckBox.isChecked()) {
            totalSale = totalSale + .15;
        }

        if (AlmondCheckBox.isChecked()) {
            totalSale = totalSale + .15;

        }

        if (StrawberryCheckBox.isChecked()) {
            totalSale = totalSale + .20;

        }
        if (GummyBearCheckBox.isChecked()) {
            totalSale = totalSale + .20;

        }
        if (MMCheckBox.isChecked()) {
            totalSale = totalSale + .25;

        }
        if (BrownieCheckBox.isChecked()) {
            totalSale = totalSale + .20;

        }
        if (OreosCheckBox.isChecked()) {
            totalSale = totalSale + .20;

        }
        if (MarshmellowCheckBox.isChecked()) {
            totalSale = totalSale + .15;

        }
        if (SeekBar.getProgress() == 0) {
            totalSale = totalSale + 0;
        }

        if (SeekBar.getProgress() == 1) {
            totalSale = totalSale + .15;
        }
        if (SeekBar.getProgress() == 2) {
            totalSale = totalSale + .25;
        }
        if (SeekBar.getProgress() == 3) {
            totalSale = totalSale + .30;
        }
        if (SizeSpinner.getSelectedItemPosition() == 0) {
            totalSale = totalSale + 2.99;
            SpinSize = "Small";
        }
        if (SizeSpinner.getSelectedItemPosition() == 1) {
            totalSale = totalSale + 3.99;
            SpinSize = "Medium";
        }
        if (SizeSpinner.getSelectedItemPosition() == 2) {
            totalSale = totalSale + 4.99;
            SpinSize = "Large";
        }

        if (SizeSpinner.getSelectedItemPosition() == 0) {
            totalSale = totalSale + 0;
            Flavor = "Vanilla";
        }
        if (SizeSpinner.getSelectedItemPosition() == 1) {
            totalSale = totalSale + 0;
            Flavor = "Strawberry";
        }
        if (SizeSpinner.getSelectedItemPosition() == 2) {
            totalSale = totalSale + 0;
            Flavor = "Chocolate";
        }


        TotalTextView.setText(String.valueOf(totalSale));

    }
}





