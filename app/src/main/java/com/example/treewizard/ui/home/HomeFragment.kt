package com.example.treewizard.ui.home

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.treewizard.databinding.FragmentHomeBinding
import java.io.File
import java.io.FileWriter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var csvNameEditText: EditText
    private lateinit var createCsvButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        csvNameEditText = binding.csvNameEdittext
        createCsvButton = binding.createCsvButton

        createCsvButton.setOnClickListener {
            createCsvFile()
        }

        return root
    }

    private fun createCsvFile() {
        val fileName = csvNameEditText.text.toString()

        // Get the Downloads directory
        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        // Create a CSV file with the specified file name in the Downloads directory
        val file = File(downloadsDir, "$fileName.csv")
        val writer = FileWriter(file)

        // Write the headers for the CSV file
        writer.write("PointID,TreeNumber,SpeciesCode,CommonName,Product,Dbh,MultiProduct,Mht,Defect,TotalHeight,TreeComment,G3Length\n")

        // Write some example data to the CSV file
        writer.write("1,2,3,Red Oak,100,18.5,true,12.5,0,40,This is a tree comment,5\n")
        writer.write("2,3,4,White Pine,50,14.2,false,20.1,1,60,Another tree comment,7\n")

        writer.close()
        Toast.makeText(context, "CSV file created successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        createCsvButton.setOnClickListener(null)
        _binding = null
    }
}
