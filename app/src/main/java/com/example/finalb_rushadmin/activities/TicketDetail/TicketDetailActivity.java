package com.example.finalb_rushadmin.activities.TicketDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.example.finalb_rushadmin.R;

import java.util.ArrayList;

public class TicketDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private ArrayList<String> arrayList;
    TextView textView, textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);
        Intent intent = getIntent();
        arrayList = intent.getStringArrayListExtra("data");
        imageView = findViewById(R.id.qr_image);
        textView = findViewById(R.id.seatTextView);
        textView1 = findViewById(R.id.timeTextView);
        textView2 = findViewById(R.id.destTextView);
        textView.setText(arrayList.get(1));
        textView1.setText(arrayList.get(2));
        textView2.setText(arrayList.get(3));
        QRCode(arrayList.get(4));
    }

    public void QRCode(String code) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(code, BarcodeFormat.QR_CODE, 350, 350);
            Bitmap bitMap = Bitmap.createBitmap(350, 350, Bitmap.Config.RGB_565);

            for(int x = 0; x < 350; x++) {
                for(int y = 0; y < 350; y++) {
                    bitMap.setPixel(x, y, bitMatrix.get(x,y) ? Color.BLACK : Color.WHITE);
                }
            }

            imageView.setImageBitmap(bitMap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}