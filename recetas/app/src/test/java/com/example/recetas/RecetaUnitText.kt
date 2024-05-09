package com.example.recetas

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.recetas.modelos.ListaReceta
import com.example.recetas.network.RecetaService
import com.example.recetas.viewmodel.RecetaViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class RecetaUnitText {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockWebServer: MockWebServer

    private lateinit var viewModel: RecetaViewModel

    @Mock
    private lateinit var observer: Observer<ListaReceta>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val servicio = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecetaService::class.java)

        viewModel = RecetaViewModel()
        viewModel.init(servicio)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun consultarRecetasUnitTest() = runBlocking {
        val response = """
            {
              "recetas": [
                {
                  "nombre": "Salteñas",
                  "ingredientes": "<h2>Ingredientes:</h2>",
                  "preparacion": "<h2>Instrucciones:</h2>",
                  "imagen": "https://chipabythedozen.com/wp-content/uploads/2020/08/Saltenas-Bolivianas-de-Pollo18.png",
                  "origen": {
                    "nombrePais": "Bolivia",
                    "banderaPais": "https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Flag_of_Bolivia.svg/264px-Flag_of_Bolivia.svg.png",
                    "latitud": "-16.70235485441954",
                    "longitud": "-64.51438949891879"
                  }
                }
              ]
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(response))

        viewModel.listaRecetas.observeForever(observer)

        viewModel.consultarRecetas()

        delay(100)

        val recetas = viewModel.listaRecetas.value?.recetas
        Assert.assertEquals(1, recetas?.size)

        viewModel.listaRecetas.removeObserver(observer)
    }
}