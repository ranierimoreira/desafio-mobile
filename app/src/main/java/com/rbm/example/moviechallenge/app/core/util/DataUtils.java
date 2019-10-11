package com.rbm.example.moviechallenge.app.core.util;

import com.rbm.example.moviechallenge.data.data.MovieDetailData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DataUtils {
    public static String extractYear(String dateString) {
        int year = 2019;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateString);
            Calendar cal = Calendar.getInstance();
            if (date != null) {
                cal.setTime(date);
                year = cal.get(Calendar.YEAR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return Integer.toString(year);
    }

    public static String genresToString(List<MovieDetailData.Genre> genres){
        if(genres == null){
            return "";
        }

        String genresString = "";

        for (MovieDetailData.Genre genre : genres) {
            genresString = genresString.join(",", genre.getName());
        }

        return genresString;
    }
}
