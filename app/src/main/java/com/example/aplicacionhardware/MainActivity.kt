package com.example.aplicacionhardware

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuración del lector QR
        var codigoQRLeido = findViewById<TextView>(R.id.codigoQR)

        val barcodeLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(
            ScanContract()
        ) { result: ScanIntentResult ->
            if (result.contents == null) {
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    this,
                    "Código: " + result.contents,
                    Toast.LENGTH_LONG
                ).show()
                codigoQRLeido.text = result.contents.toString()
            }
        }

        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Lector de códigos QR")
        options.setCameraId(0) // Use a specific camera of the device

        options.setBeepEnabled(false)
        options.setBarcodeImageEnabled(true)

        val btnScan = findViewById<Button>(R.id.leerQR)

        btnScan.setOnClickListener {
            barcodeLauncher.launch(options)
        }
    }
}