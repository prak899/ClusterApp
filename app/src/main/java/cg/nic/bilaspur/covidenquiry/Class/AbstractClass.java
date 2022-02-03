package cg.nic.bilaspur.covidenquiry.Class;

import android.util.Log;

public abstract class AbstractClass {
        String name = "nameismajhi";

        public void show(){
            Log.d("name", "show: "+name);
        }
        public abstract void startSound();
        public abstract String checkInfo();
        public abstract void vibeMe();
}
