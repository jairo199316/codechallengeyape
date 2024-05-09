package com.example.recetas.vistas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recetas.R
import com.example.recetas.databinding.ActivityMapScreenBinding
import com.example.recetas.modelos.Origen
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapScreenActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapScreenBinding
    private var origen: Origen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        origen = intent.getSerializableExtra(EXTRA_ORIGEN) as Origen
        cargarMapa()
        cargarEventos()
    }

    private fun cargarMapa() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun cargarEventos() {
        binding.irAtras.setOnClickListener { onBackPressed() }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        origen?.let {
            val origen = LatLng(it.latitud.toDouble(), it.longitud.toDouble())
            googleMap.addMarker(
                MarkerOptions().position(origen).icon(
                    BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                )
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(origen))
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(3.8f))
        }
    }

    companion object {
        const val EXTRA_ORIGEN = "extra_origen"
    }
}