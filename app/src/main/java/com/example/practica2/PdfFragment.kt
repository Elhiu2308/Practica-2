package com.example.practica2

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practica2.databinding.FragmentPdfBinding

class PdfFragment : Fragment() {

    private var _binding: FragmentPdfBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPdfBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uri: Uri? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("pdf_uri", Uri::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("pdf_uri")
        }

        if (uri != null) {
            binding.pdfView.fromUri(uri)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .load()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
