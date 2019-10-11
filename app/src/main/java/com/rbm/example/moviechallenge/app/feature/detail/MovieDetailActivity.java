package com.rbm.example.moviechallenge.app.feature.detail;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.rbm.example.moviechallenge.R;
import com.rbm.example.moviechallenge.app.core.databiding.BindingHolder;
import com.rbm.example.moviechallenge.data.api.ApiClient;
import com.rbm.example.moviechallenge.data.data.MovieDetailData;
import com.rbm.example.moviechallenge.databinding.ContentMovieDetailBinding;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();

    public static final String EXTRA_TITLE = "com.rbm.example.moviechallenge.EXTRA_TITLE";
    public static final String EXTRA_OVERVIEW = "com.rbm.example.moviechallenge.EXTRA_OVERVIEW";
    public static final String EXTRA_RELEASE_DATE = "com.rbm.example.moviechallenge.EXTRA_RELEASE_DATE";
    public static final String EXTRA_BACKDROP_PATH = "com.rbm.example.moviechallenge.EXTRA_BACKDROP_PATH";
    public static final String EXTRA_ID = "com.rbm.example.moviechallenge.EXTRA_ID";
    BindingHolder<ContentMovieDetailBinding> bindingHolder;
    MovieDetailData movieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieData = new MovieDetailData();
        ContentMovieDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.content_movie_detail);
        bindingHolder = new BindingHolder<>(binding);
        binding.setMovieDetail(movieData);


        int movieId = getIntent().getIntExtra(EXTRA_ID, 1);
        MovieDetailViewModel viewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        viewModel.loadMovieDetail(movieId);
        viewModel.viewState.observe(this, this::onMovieUpdate);
    }

    private void onMovieUpdate(MovieDetailData movieDetailData) {
        Log.d(TAG, movieDetailData.getTitle());
        bindingHolder.getBinding().setMovieDetail(movieDetailData);

        /*ImageView movieImage = findViewById(R.id.movie_detail_image_view);

        Picasso picasso = Picasso.get();
        picasso.load(ApiClient.IMAGE_LARGE_URL + backdropPath)
                .placeholder(R.drawable.image_not_found)
                .into(movieImage);*/
    }
}
