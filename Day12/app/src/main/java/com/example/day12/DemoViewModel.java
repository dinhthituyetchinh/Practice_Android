package com.example.day12;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class DemoViewModel extends BaseObservable
{
    private String demoText;

    @Bindable
    public String getDemoText() {
        return demoText;
    }

    public void setDemoText(String demoText) {
        this.demoText = demoText;
        Log.e("MainActivity3", "DemoText sau khi chinh: "+this.demoText);
        notifyPropertyChanged(BR.demoText);
    }

    public String getDemoTextWithSuffix(String suffix)
    {
        return getDemoText()+" "+suffix;
    }

}
