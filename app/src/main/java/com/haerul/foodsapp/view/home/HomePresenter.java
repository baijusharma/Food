/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.haerul.foodsapp.view.home;

import android.support.annotation.NonNull;

import com.haerul.foodsapp.Utils;
import com.haerul.foodsapp.model.Categories;
import com.haerul.foodsapp.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePresenter {

    private HomeView homeView;

    public HomePresenter(HomeView view) {
        this.homeView = view;
    }

    void getMeals() {
        // TODO 16 do loading before making a request to the server

        homeView.showLoading();

        Call<Meals> mealsCall = Utils.getApi().getMeal();

        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                homeView.hideLoading();

                if (response.isSuccessful() && response.body() != null) {
                    /*
                     * TODO 22 Receive the result
                     * input the results obtained into the setMeals() behavior
                     * and enter response.body() to the parameter
                     */
                    homeView.setMeal(response.body().getMeals());

                } else {
                    // TODO 23 Show an error message if the conditions are not met
                    homeView.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                /*
                 * Failure will be thrown here
                 * for this you must do
                 * 1. closes loading
                 * 2. displays an error message
                 */

                // TODO 24 Close loading
                homeView.hideLoading();
                // TODO 25 Show an error message
                homeView.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }


    void getCategories() {

        // TODO 26 do loading before making a request to the server
        homeView.showLoading();
        // TODO 27 create Call<Categories> categoriesCall = ...
        Call<Categories> categoriesCall = Utils.getApi().getCategories();

        // TODO 28 waiting for enqueue Callback
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call, @NonNull Response<Categories> response) {
                // TODO 29 Non-empty results check & Non-empty results check
                homeView.hideLoading();

                if (response.isSuccessful() && response.body() != null) {
                    /*
                     * TODO 30 Receive the result
                     * input the results obtained into the setMeals() behavior
                     * and enter response.body() to the parameter
                     */
                    homeView.setCategory(response.body().getCategories());

                } else {
                    // TODO 31 Show an error message if the conditions are not met
                    homeView.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                /*
                 * Failure will be thrown here
                 * for this you must do
                 * 1. closes loading
                 * 2. displays an error message
                 */

                // TODO 32 Close loading
                // TODO 33 Show an error message

                // TODO 24 Close loading
                homeView.hideLoading();
                // TODO 25 Show an error message
                homeView.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }
}
