package com.pesan.absensikaryawan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;

import com.pesan.absensikaryawan.BarcodeTest.IntentIntegrator;
import com.pesan.absensikaryawan.BarcodeTest.IntentResult;

public class QRScanner extends Activity {

    private String upc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        //jendela tanpa title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //memulai pemindaian QRCode
        IntentIntegrator.initiateScan(this);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE: {
                if (resultCode != RESULT_CANCELED) {
                    IntentResult scanResult = IntentIntegrator.parseActivityResult (requestCode, resultCode, data);
                    // apabila ada hasil dari pemindaian
                    if (scanResult != null) {
                        // ambil isi dari QRCode
                        upc = scanResult.getContents ();
                        //  tampilkan pada Alert Dialog
                        final AlertDialog.Builder builder = new AlertDialog.Builder (QRScanner.this);
                        builder.setTitle ("Hasil Scan");
                        builder.setMessage (upc + "\n");
                        builder.setIcon (android.R.drawable.ic_dialog_alert);
                        // Tombol untuk simpan data
                        builder.setPositiveButton ("Simpan", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Buat simpan ke database atau ke mana
                                // doSomething();
                                QRScanner.this.finish ();
                                IntentIntegrator.initiateScan (QRScanner.this);

                            }
                        });
                        // Tombol untuk coba lagi/keluar
                        builder.setNegativeButton ("Coba Lagi", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                QRScanner.this.finish ();
                                IntentIntegrator.initiateScan (QRScanner.this);
                            }

                        });
                        // tampilkan alert box
                        builder.show ();
                    }
                    break;
                }
            }
        }
    }
}
