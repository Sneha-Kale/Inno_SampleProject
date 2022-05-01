package com.example.inno_sampleproject.General;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static android.view.Gravity.BOTTOM;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.inno_sampleproject.R;

public class CustomMsg extends AlertDialog
{
    static androidx.appcompat.app.AlertDialog dialog=null;

    public CustomMsg(Context context) {
        super(context);
    }

    public static void CustomPopup(Context context, String msg)
    {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.layout_custom_msg,null);

        Button btn_Ok = layout.findViewById(R.id.btn_Ok);
        TextView txt_msg = layout.findViewById(R.id.txt_msg);
        txt_msg.setText(msg);
        builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setView(layout);
        dialog = builder.create();
        dialog.closeOptionsMenu();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));// *that how i made background transparent so that **Only** rounded border can be seen*
        dialog.show();
        final androidx.appcompat.app.AlertDialog finalDialog = dialog;

        btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(finalDialog!=null && finalDialog.isShowing())
                {
                    finalDialog.dismiss();
                }
            }
        });

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setGravity(BOTTOM);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
    }
}
