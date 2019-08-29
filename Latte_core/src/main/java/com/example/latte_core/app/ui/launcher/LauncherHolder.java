package com.example.latte_core.app.ui.launcher;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.example.latte_core.R;


public class LauncherHolder extends Holder<Integer> {


    private ImageView imageView;

    public LauncherHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        imageView = itemView.findViewById(R.id.iv_banner);
    }

    @Override
    public void updateUI(Integer data) {
        imageView.setImageResource(data);
    }
}
