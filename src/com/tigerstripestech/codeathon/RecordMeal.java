package com.tigerstripestech.codeathon;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tigerstripestech.codeathon.db.MealDbHelper;
import com.tigerstripestech.codeathon.objects.Food;

public class RecordMeal extends Activity {
	
	Spinner spinMeal;
	MealDbHelper dbHelper = App.getDbHelper();
	private AppPreferences _appPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_meal);
		// Show the Up button in the action bar.
		setupActionBar();
		
		spinMeal = (Spinner) findViewById(R.id.spinMeal);
		
		_appPrefs = new AppPreferences(getBaseContext());
		
		Log.d("CODEATHON", _appPrefs.getHeight());
		Log.d("CODEATHON", _appPrefs.getWeight());
		
		populateSpinner();
	}
	
	private void populateSpinner(){
		
		//ArrayList<String> meals = dbHelper.getAllFood();
		ArrayList<Food> allFood = dbHelper.getAllFood();
		ArrayList<String> meals = new ArrayList<String>();
		
		for(int i=0;i<allFood.size();i++){
			meals.add(allFood.get(i).getName());
		}
		
		ArrayAdapter<String> mealAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, meals);
		
		spinMeal.setAdapter(mealAdapter);
	}
	
	public void onClickSaveMeal(View v) {
		//Intent intent = new Intent(this, SaveMeal.class);
		//startActivity(intent);
		
		// TODO: Save meal information logic here
		finish();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.record_meal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
