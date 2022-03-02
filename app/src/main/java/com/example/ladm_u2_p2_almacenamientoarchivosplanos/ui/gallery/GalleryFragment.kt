package com.example.ladm_u2_p2_almacenamientoarchivosplanos.ui.gallery

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ladm_u2_p2_almacenamientoarchivosplanos.databinding.FragmentGalleryBinding
import java.io.OutputStreamWriter
import java.nio.channels.AsynchronousFileChannel.open

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.agendar.setOnClickListener {
            guardar()
        }

        return root
    }

    /*try {

    }catch (e:Exception){
        AlertDialog.Builder(requireContext())
            .setMessage(e.message).show()
    }*/

    private fun guardar() {
        try {
            val archivo = OutputStreamWriter(requireActivity().openFileOutput("archivo.txt",0))
            var cadena = binding.nombrecompleto.text.toString()+"\n"+
                    binding.edad.text.toString()+"\n"+
                    binding.fecha.text.toString()

            archivo.write(cadena)
            archivo.flush()
            archivo.close()
            binding.nombrecompleto.setText("")
            binding.edad.setText("")
            binding.fecha.setText("")
            AlertDialog.Builder(requireContext())
                .setMessage("SE AGENDÓ LA CITA CORRECTAMENTE").show()
        }catch (e:Exception){
            AlertDialog.Builder(requireContext())
                .setMessage(e.message).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}