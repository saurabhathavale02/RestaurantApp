package com.example.saurabh.restaurantdairy.CartDisplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.saurabh.restaurantdairy.R;
import com.example.saurabh.restaurantdairy.ReceiptDisplay.ReceiptActivity;
import com.example.saurabh.restaurantdairy.root.App;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.lang.Float.valueOf;

public class CartActivity extends AppCompatActivity
{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.selectedrecycleview)
    RecyclerView selectedrecycleview;
    @BindView(R.id.SubTotal)
    TextView SubTotal;
    @BindView(R.id.ServiceTax)
    TextView ServiceTax;
    @BindView(R.id.TotalPrice)
    TextView TotalPrice;
    @BindView(R.id.address)
    EditText Address;
    @BindView(R.id.carryout)
    RadioButton CarryOut;
    @BindView(R.id.delivery)
    RadioButton Delivery;
    @BindView(R.id.radiogroup)
    RadioGroup radioGroup;
    @BindView(R.id.Pay)
    Button Pay;

    RadioButton selectedbutton=null;
    Float total= valueOf(0);

    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;

    CartAdapter cartAdapter;
    //Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Float subtotal= valueOf(0);

        cartAdapter = new CartAdapter(((App)getApplicationContext()).selectedFood);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        selectedrecycleview.setLayoutManager(mLinearLayoutManager);
        selectedrecycleview.setItemAnimator(new DefaultItemAnimator());
        selectedrecycleview.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        Intent intent = new Intent(this, PayPalService.class);

        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        startService(intent);

        for(int i=0;i<((App)getApplicationContext()).selectedFood.size();i++)
        {
            subtotal=subtotal+(((App)getApplicationContext()).selectedFood.get(i).getPrice()*((App)getApplicationContext()).selectedFood.get(i).getQuantity());
        }
        SubTotal.setText("$"+subtotal);
        total=(float) ((float)subtotal+((float) 2.30));
        TotalPrice.setText("$"+total);
        Pay.setText("PAY -: $"+total);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId)
            {

                selectedbutton=(RadioButton)findViewById(checkedId);
                if(selectedbutton.getText().equals("Delivery"))
                {
                    Address.setVisibility(View.VISIBLE);
                }
                else
                {
                    Address.setVisibility(View.GONE);
                }
            }
        });

        Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Creating a paypalpayment
                PayPalPayment payment = new PayPalPayment(new BigDecimal(valueOf(total)), "USD", "Restaurant cost",
                        PayPalPayment.PAYMENT_INTENT_SALE);

                //Creating Paypal Payment activity intent
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);

                //putting the paypal configuration to the intent
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

                //Puting paypal payment to the intent
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

                //Starting the intent activity for result
                //the request code will be used on the method onActivityResult
                startActivityForResult(intent, PAYPAL_REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetails);

                        //Starting a new activity for the payment details and also putting the payment details with intent
                        startActivity(new Intent(this, ReceiptActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", total));

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }
}
