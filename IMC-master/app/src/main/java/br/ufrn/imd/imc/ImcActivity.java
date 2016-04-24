package br.ufrn.imd.imc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ImcActivity extends ActionBarActivity {

    /** Save instance state weight key */
    private static final String WEIGHT = "weight";

    /** Save instance state tallness key */
    private static final String TALLNESS = "tallness";

    /** Focused view keu */
    private static final String LAST_FOCUSED = "lastFocused";

    /** The weight input */
    private EditText mInputWeight;

    /** The tallness input */
    private EditText mInputTallness;

    /** The result text */
    private TextView mTvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
        mInputWeight = (EditText) findViewById(R.id.imc_input_weight);
        mInputTallness = (EditText) findViewById(R.id.imc_input_tallness);
        mTvResult = (TextView) findViewById(R.id.result);
        /* The evaluate button */
        Button mEvaluateButton = (Button) findViewById(R.id.imc_bt_evaluate);

        EvaluateButtonOnClick evaluateButtonOnClick = new EvaluateButtonOnClick();

        InputWatcher inputWatcher = new InputWatcher();

        mEvaluateButton.setOnClickListener(evaluateButtonOnClick);
        mInputTallness.addTextChangedListener(inputWatcher);
        mInputWeight.addTextChangedListener(inputWatcher);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(WEIGHT, mInputWeight.getText().toString());
        outState.putString(TALLNESS, mInputTallness.getText().toString());
        View focusedView = getCurrentFocus();
        if (focusedView != null) {
            outState.putInt(LAST_FOCUSED, getCurrentFocus().getId());
        }

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // restoring saved state
        String weight = savedInstanceState.getString(WEIGHT);
        String tallness = savedInstanceState.getString(TALLNESS);
        int lastFocusedId = savedInstanceState.getInt(LAST_FOCUSED);
        if (weight != null) {
            mInputWeight.setText(weight);
        }
        if (tallness != null) {
            mInputTallness.setText(tallness);
        }
        if (lastFocusedId != View.NO_ID) {
            View focusedView = findViewById(lastFocusedId);
            if (focusedView != null) {
                focusedView.requestFocus();
            }
        }
    }

    private final class InputWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mTvResult.getVisibility() == View.VISIBLE) {
                mTvResult.setVisibility(View.GONE);
            }
        }
    }

    private final class EvaluateButtonOnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // Imc evaluation
            float weight = Float.parseFloat(mInputWeight.getText().toString());
            float tallness = Float.parseFloat(mInputTallness.getText().toString());
            float imc = weight / (tallness * tallness);

            // clear focus
            View focusedView = getCurrentFocus();
            if (focusedView != null) {
                getCurrentFocus().clearFocus();
            }

            // send intent to result activity
            Intent intent = new Intent(ImcActivity.this, ResultActivity.class);
            intent.putExtra("result", imc);
            intent.putExtra("height", tallness);
            intent.putExtra("weight", weight);
            startActivity(intent);
        }
    }
}
