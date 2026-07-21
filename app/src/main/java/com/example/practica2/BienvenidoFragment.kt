package com.example.practica2

import android.graphics.Paint
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.practica2.databinding.FragmentBienvenidoBinding
import java.io.File
import java.io.FileOutputStream

class BienvenidoFragment : Fragment() {

    private var _binding: FragmentBienvenidoBinding? = null
    private val binding get() = _binding!!

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBienvenidoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCalendarioText.paintFlags = binding.tvCalendarioText.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        Glide.with(this).asGif().load(R.drawable.messi).into(binding.imgJugador1)
        Glide.with(this).asGif().load(R.drawable.yamal).into(binding.imgJugador2)

        try {
            val resId = resources.getIdentifier("fifa", "raw", requireContext().packageName)
            if (resId != 0) {
                mediaPlayer = MediaPlayer.create(requireContext(), resId)
                mediaPlayer?.isLooping = true
                mediaPlayer?.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val usuario = arguments?.getString("usuario", "")

        binding.tvBienvenida.text = getString(R.string.mensaje_bienvenida, usuario)

        binding.llPdf.setOnClickListener {
            openCalendarioPdf()
        }

        binding.btnCerrarSesion.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commit()
        }
    }

    private fun openCalendarioPdf() {
        try {
            val fileName = "calendario.pdf"
            val cacheFile = File(requireContext().cacheDir, fileName)

            resources.openRawResource(R.raw.calendario).use { input ->
                FileOutputStream(cacheFile).use { output ->
                    input.copyTo(output)
                }
            }

            val uri: Uri = FileProvider.getUriForFile(
                requireContext(),
                "${requireContext().packageName}.fileprovider",
                cacheFile
            )

            val pdfFragment = PdfFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("pdf_uri", uri)
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, pdfFragment)
                .addToBackStack(null)
                .commit()

        } catch (e: Exception) {
            Toast.makeText(requireContext(), "No se encontró el calendario", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer?.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        _binding = null
    }
}
