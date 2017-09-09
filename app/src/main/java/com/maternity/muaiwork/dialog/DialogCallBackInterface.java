package com.maternity.muaiwork.dialog;

import java.util.Objects;

/**
 * Created by apple on 2016/10/19.
 */

public interface DialogCallBackInterface {
    public void selectShure(int tag,Object result);
    public void selectCancle(int tag);
    public void selectIndex(int tag, int index, Object object);
}
