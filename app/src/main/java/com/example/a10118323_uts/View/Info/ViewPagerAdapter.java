//5 Juni 2021 - 10118323 - Riffa Alfaridzi Priatna - IF8
package com.example.a10118323_uts.View.Info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.a10118323_uts.R;

public class ViewPagerAdapter extends PagerAdapter {

    String[] text = {
            "You can see your notes",
            "You can make a note",
            "You can edit the note",
            "You can remove the note"
    };
    int[] img = {
            R.drawable.list,
            R.drawable.add,
            R.drawable.edit,
            R.drawable.delete
    };
    Context context;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.item_info, null);

        ImageView imgView = layout.findViewById(R.id.image_viewpager);
        TextView tvPager = layout.findViewById(R.id.text_viewpager);

        imgView.setImageResource(img[position]);
        tvPager.setText(text[position]);
        container.addView(layout);
        return layout;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
