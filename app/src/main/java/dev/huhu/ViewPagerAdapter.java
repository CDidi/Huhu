package dev.huhu;

/**
 * Created by Clara on 03/06/2015.
 */
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables

    Context context;
    String[] texttoshow;
    LayoutInflater inflater;


    public ViewPagerAdapter(Context context, String[] texttoshow) {
        this.context = context;
        this.texttoshow = texttoshow;
    }

    @Override
    public int getCount() {
        return texttoshow.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        TextView txtoshow;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        txtoshow = (TextView) itemView.findViewById(R.id.texttoshow);


        txtoshow.setText(texttoshow[position]);

        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}