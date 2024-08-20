
package com.reactlibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import java.util.Optional;
import org.json.JSONObject;
import android.util.Log;

/**
 * Checkout implementation for the app
 */

 public class RNGooglePayActivity extends AppCompatActivity {
     /**
   * A client for interacting with the Google Pay API
   *
   * @see <a
   *     href="https://developers.google.com/android/reference/com/google/android/gms/wallet/PaymentsClient">PaymentsClient</a>
   */
  private PaymentsClient mPaymentsClient;

  /**
   * A Google Pay payment button presented to the viewer for interaction
   *
   * @see <a href="https://developers.google.com/pay/api/android/guides/brand-guidelines">Google Pay
   *     payment button brand guidelines</a>
   */
  private View mGooglePayButton;

  /** A constant integer you define to track a request for payment data activity */
  private static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 42;

  /**
   * Initialize the Google Pay API on creation of the activity
   *
   * @see Activity#onCreate(android.os.Bundle)
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_main);

    // initialize a Google Pay API client for an environment suitable for testing
    mPaymentsClient =
        Wallet.getPaymentsClient(
            this,
            new Wallet.WalletOptions.Builder()
                .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                .build());

   // possiblyShowGooglePayButton();
  }

  

  /**
   * Handle a resolved activity from the Google Pay payment sheet
   *
   * @param requestCode the request code originally supplied to AutoResolveHelper in
   *     requestPayment()
   * @param resultCode the result code returned by the Google Pay API
   * @param data an Intent from the Google Pay API containing payment or error data
   * @see <a href="https://developer.android.com/training/basics/intents/result">Getting a result
   *     from an Activity</a>
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
        // value passed in AutoResolveHelper
      case LOAD_PAYMENT_DATA_REQUEST_CODE:
        JSONObject dumyObj = null;
        switch (resultCode) {
          case Activity.RESULT_OK:
            PaymentData paymentData = PaymentData.getFromIntent(data);
            String json = paymentData.toJson();
            Log.e("test",json);
            // if using gateway tokenization, pass this token without modification
            /*JSONObject paymentMethodData = dumyObj.getJSONObject("paymentMethodData");
            String paymentToken = paymentMethodData.getJSONObject("tokenizationData")
                                    .getString("token"); */  
            /*Log.e("Token : ", paymentToken);
            Log.e("Response", paymentMethodData.toString());    */                   
            break;
          case Activity.RESULT_CANCELED:
            break;
          case AutoResolveHelper.RESULT_ERROR:
            Status status = AutoResolveHelper.getStatusFromIntent(data);
            // Log the status for debugging.
            // Generally, there is no need to show an error to the user.
            // The Google Pay payment sheet will present any account errors.
            break;
          default:
            // Do nothing.
        }
        break;
      default:
        // Do nothing.
    }
  }
 }
