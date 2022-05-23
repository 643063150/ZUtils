package com.android.zpp.zutils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

/**
 * @ProjectName: ZUtils
 * @Package: com.android.zpp.zutils
 * @ClassName: MyDialog
 * @Description:
 * @Author: zpp
 * @CreateDate: 2022/3/11 17:03
 * @UpdateUser:
 * @UpdateDate: 2022/3/11 17:03
 * @UpdateRemark:
 */
public class MyDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
        View view = View.inflate(getActivity(), R.layout.jion_group_dialog, null);
        dialog.setContentView(view);

        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        dialog.getWindow().setDimAmount(0f);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //设置宽
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置高
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        dialogWindow.setAttributes(lp);
        //显示对话框
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        dialog.show();
        return dialog;
    }


}
