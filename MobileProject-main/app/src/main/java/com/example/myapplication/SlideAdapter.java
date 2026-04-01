package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater ;
    public SlideAdapter(Context context){
        this.context= context;
    };
    //Arrays
    public int[] slide_image = {
            R.drawable.mahmoud,
            R.drawable.nourhan,
            R.drawable.merna,
            R.drawable.ziad,
            R.drawable.wesam,
            R.drawable.shahd1,
            R.drawable.mohammed,
            R.drawable.yara1,
    };
    //Arrays
    public String[] slide_headings= {
            "Mahmoud Essam Fathy \n",
            "Nourhan Mohammed Fathy\n",
            "Mirna Samir Mohammed\n",
            "Ziad Ashraf Hafez\n",
            "Wesam Fathy Masoud\n",
            "Shahd Ahmed Saad\n",
            "Mohammed Magdy Hameda\n",
            "Yara Hatem Ibrahim\n"
    };
    public String[] slide_descs= {
            "Team Leader\nfaculty of computers and data science\n senior 2025\n ID:20221460231",
            "faculty of computers and data science\n senior 2025\n ID:20221452411",
            "faculty of computers and data science\n senior 2025\n ID:20221450912",
            "faculty of computers and data science\n senior 2025\n ID:20221374025",
            "faculty of computers and data science\n senior 2025\n ID:20221451271",
            "faculty of computers and data science\n senior 2025\n ID:20221452998",
            "faculty of computers and data science\n senior 2025\n ID:20221460218",
            "faculty of computers and data science\n senior 2025\n ID:20221464983"
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (LinearLayout) o;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        ImageView slideImageView = (ImageView) view.findViewById(R.id.imageView);
        TextView slideHeading  = (TextView) view.findViewById(R.id.textView);
        TextView  slideDescription  = (TextView) view.findViewById(R.id.textView2);
        slideImageView.setImageResource(slide_image[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
