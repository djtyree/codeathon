package com.tigerstripestech.codeathon.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tigerstripestech.codeathon.R;
import com.tigerstripestech.codeathon.objects.Food;

public class MealDbHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "MealDb.db";
	private static final int DATABASE_VERSION = 1;
	private Resources res;

	public MealDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		res = context.getResources();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		MealDb.onCreate(db);
		populateTestData(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		MealDb.onUpgrade(db, oldVersion, newVersion);
	}

	private void populateTestData(SQLiteDatabase db) {
		Log.d(MealDb.LOG_TAG, "Populating Data");
		
		ContentValues testData;
		String[] food_names = res.getStringArray(R.array.food_name_array);
		String[] food_type = res.getStringArray(R.array.food_type_array);
		String[] food_calorie = res.getStringArray(R.array.food_calorie_array);
		
		for(int i = 0; i < food_names.length; i++) {
			testData = new ContentValues();
			testData.put(MealDb.KEY_FOOD_NAME, food_names[i]);
			testData.put(MealDb.KEY_FOOD_COUNT, food_type[i]);
			testData.put(MealDb.KEY_FOOD_CALORIE, food_calorie[i]);
			db.insert(MealDb.DB_FOOD, null, testData);
		}
	}
	
	// returns a list of names from the people table
	public ArrayList<Food> getAllFood() {
		ArrayList<Food> foodArray = new ArrayList<Food>();

		// Select All Query
		String selectQuery = "SELECT  * FROM " + MealDb.DB_FOOD;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list

		if (cursor.moveToFirst()) {
			do {
				// cursor has values in the format = _id, pid, name, updated
				Food food = new Food();
				String name = cursor.getString(cursor.getColumnIndex(MealDb.KEY_FOOD_NAME));
				int type = cursor.getInt(cursor.getColumnIndex(MealDb.KEY_FOOD_COUNT));
				int calories = cursor.getInt(cursor.getColumnIndex(MealDb.KEY_FOOD_CALORIE));
				food.setName(name);
				food.setType(type);
				food.setCalories(calories);
				foodArray.add(food);

			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();

		// returning people
		return foodArray;

	}
	
	
}