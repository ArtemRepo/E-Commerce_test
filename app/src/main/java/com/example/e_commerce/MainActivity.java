package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.e_commerce.adapter.CategoryAdapter;
import com.example.e_commerce.adapter.CourseAdapter;
import com.example.e_commerce.model.Category;
import com.example.e_commerce.model.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    static CourseAdapter courseAdapter;
    static List<Course> courseList = new ArrayList<>();
    static List<Course> fullCoursesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "РосПотребНадзор"));
        categoryList.add(new Category(2, "СЭС"));
        categoryList.add(new Category(3, "МЧС"));
        categoryList.add(new Category(4, "Налоговая"));


        setCategoryRecycler(categoryList);


        courseList.add(new Course(1, "lenta_1", "Гипермаркет\nЛента", "1 января", "Аксай", "#FFB803", "Test", 3));
        courseList.add(new Course(2, "magnit_1", "Гипермаркет\nМагнит", "10 января", "Ростов", "#610505", "Test", 3));
        courseList.add(new Course(2, "okay_1", "Гипермаркет\nОкей", "10 января", "Шахты", "#38AB2F", "Test", 1));
        courseList.add(new Course(2, "pyaterochka_1", "Супермаркет\nПятерочка", "10 января", "Новочеркасск", "#2F9243", "Test", 2));
        courseList.add(new Course(2, "lenta_1", "Гипермаркет\nЛента", "10 января", "Ростов", "#FFB803", "Test", 2));
        courseList.add(new Course(2, "perekrestok1", "Гипермаркет\nПерекресток", "10 января", "Аксай", "#2D9341", "Test",2));

        fullCoursesList.addAll(courseList);

        setCourseRecycler(courseList);
    }

    public void openEmployee(View view) {
        Intent intent = new Intent(this,Tasks.class);
        startActivity(intent);
    }

    public void openCalculator(View v) {
            Intent intent = new Intent(this,Calculator.class);
            startActivity(intent);
    }

    public void openShoppingCart(View view) {
        Intent intent = new Intent(this,OrderPage.class);
        startActivity(intent);
    }

    private void setCourseRecycler(List<Course> courseList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, courseList);
        courseRecycler.setAdapter(courseAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    public static void showCoursesByCategory(int category) {

        courseList.clear();
        courseList.addAll(fullCoursesList);

        List<Course> filterCourses = new ArrayList<>();

        for(Course c : courseList) {
            if(c.getCategory() == category)
                filterCourses.add(c);
        }

        courseList.clear();
        courseList.addAll(filterCourses);

        courseAdapter.notifyDataSetChanged();

    }
}