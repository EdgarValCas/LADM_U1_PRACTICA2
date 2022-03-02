package com.example.ladm_u2_p2_almacenamientoarchivosplanos.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ladm_u2_p2_almacenamientoarchivosplanos.CustomAdapter
import com.example.ladm_u2_p2_almacenamientoarchivosplanos.Datos
import com.example.ladm_u2_p2_almacenamientoarchivosplanos.databinding.FragmentSlideshowBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val vector = ArrayList<Datos>()
    val citas = ArrayList<String>()
    val citasList =ArrayList<String>()
    var aux : List<String> = listOf("Nombres","0","dd/mm/aaaa")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        leer()

        val recyclerView = androidx.recyclerview.widget.RecyclerView(requireContext())

        val adapter = CustomAdapter(vector)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun leer() {
        try {
            citas.clear()
            val archivo = BufferedReader(InputStreamReader(context?.openFileInput("archivo.txt")))
            val br = BufferedReader(archivo)

            var linea = br.readLine()
            val cadena = StringBuilder()
            while (linea != null) {
                cadena.append(linea + "\n")
                linea = br.readLine()
            }
            br.close()
            archivo.close()

            var separar = cadena.split("\n")
            var total = separar.size-1

            (0..total).forEach {
                citas.add(separar[it])
            }

            var index = citas.size-1
            citas.removeAt(index)

            //Hasta aqui el txt ya esta en una lista pero aun los valores no estan separados por parametro

            (0..citas.size-1).forEach {
                citasList.add(citas[it].split(",").toString())
            }

            //Filtrado de cadena

            (0..citasList.size-1).forEach {
                citasList[it] = citasList.get(it).toString().replace("[","")
                citasList[it] = citasList.get(it).toString().replace("]","")

                aux = citasList.get(it).split(",")

                var nombre = aux[0].toString().trim()
                var edad = aux[1].toString().trim().toInt()
                var fecha = aux[2].toString().trim()

                var objetoDatos = Datos()
                objetoDatos.nombre = nombre
                objetoDatos.edad = edad
                objetoDatos.fecha = fecha
                vector.add(objetoDatos)
            }
        } catch (e:Exception) {
            Toast.makeText(requireContext(),e.message, Toast.LENGTH_LONG)
        }
    }

}