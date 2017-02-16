package com.stylight.codingtask.utils;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/**
 * Created by muhammadabubakar on 2/15/17.
 */

public class Utils {

    private static Utils INSTANCE;

    public static Utils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Utils();
        }
        return INSTANCE;
    }

    public Utils() {

    }


    public static boolean isNull(Object object){

        if(object == null)
            return true;

        if(object instanceof String)
            return ((String) object).isEmpty();
        else if (object instanceof List)
            return ((List) object).isEmpty();
        else if (object instanceof Map)
            return ((Map) object).isEmpty();
        else if (object instanceof WeakReference)
            return ((WeakReference) object).get() == null;


        return false;

    }
}
