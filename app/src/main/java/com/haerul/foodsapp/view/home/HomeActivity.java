/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.haerul.foodsapp.view.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.haerul.foodsapp.R;
import com.haerul.foodsapp.Utils;
import com.haerul.foodsapp.adapter.RecyclerViewHomeAdapter;
import com.haerul.foodsapp.adapter.ViewPagerHeaderAdapter;
import com.haerul.foodsapp.model.Categories;
import com.haerul.foodsapp.model.Meal;
import com.haerul.foodsapp.model.Meals;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// TODO 31 implement the HomeView interface at the end
public class HomeActivity extends AppCompatActivity implements HomeView {

    /*
     * TODO 32 Add @BindView Annotation (1)
     *
     * 1. viewPagerHeader
     * 2. recyclerCategory
     *
     */

    /*
     *  TODO 33 Create variable for presenter;
     */

    ViewPager viewPagerMeal;
    RecyclerView recyclerViewCategory;
    View shimmerMeal, shimmerCategory;
    HomePresenter presenter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;
        initViews();
        /*
         *  TODO 34 bind the ButterKnife (2)
         */

        /*
         *  TODO 35 Declare the presenter
         *  new HomePresenter(this)
         */
        presenter = new HomePresenter(this);
        presenter.getMeals();
        presenter.getCategories();
    }

    private void initViews() {
        viewPagerMeal = findViewById(R.id.viewPagerHeader);
        recyclerViewCategory = findViewById(R.id.recyclerCategory);
        shimmerMeal = findViewById(R.id.shimmerMeal);
        shimmerCategory = findViewById(R.id.shimmerCategory);
    }

    @Override
    public void showLoading() {
        shimmerMeal.setVisibility(View.VISIBLE);
        shimmerCategory.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        shimmerMeal.setVisibility(View.GONE);
        shimmerCategory.setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meal> meals) {

        /*for (Meal mealResult : meals) {
            Log.d("MealName", mealResult.getStrMeal());
        }*/

        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meals, this);
        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(20, 0, 150, 0);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener(new ViewPagerHeaderAdapter.ClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(mContext, meals.get(position).getStrCategory(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void setCategory(List<Categories.Category> category) {

        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter(category, this);
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener(new RecyclerViewHomeAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(mContext, category.get(position).getStrCategory(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Title", message);
    }

    // TODO 36 Overriding the interface

}
