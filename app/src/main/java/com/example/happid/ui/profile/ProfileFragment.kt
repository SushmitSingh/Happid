package com.example.happid.ui.profile

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.happid.databinding.FragmentProfileBinding
import com.example.happid.model.ProfileData
import com.example.happid.network.ApiResponse
import com.example.happid.utils.SharedPrefManager


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    private val PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123

    private val imagePickerActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Handle the selected image URI
                result.data?.data
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using data binding
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel using ViewModelProvider
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        //Save Session Data in Shared Preferences
        SharedPrefManager.saveLoginCompletion(requireContext(), true)

        // Set click listener for submit button
        binding.submitBtn.setOnClickListener {
            val formData = getFormData()
            viewModel.createProfile(formData)
        }

        // Observe createProfileLiveData
        viewModel.createProfileLiveData.observe(viewLifecycleOwner, Observer { response ->
            //Api Response type APIResponse
            if (response.status == ApiResponse.Status.SUCCESS) {
                Toast.makeText(view.context, response.message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, response.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        // Set click listener for profile image
        binding.profileImage.setOnClickListener {
            imagePicker()
            Glide.with(requireContext())
                .load("https://i.imgur.com/7bMqNv5.png")
                .into(binding.profileImage)
        }
    }

    private fun getFormData(): ProfileData {
        val postalCode = binding.editPostCode.editText?.text?.trim().toString()
        val location = if (postalCode.isEmpty()) "New Delhi" else ""
        return ProfileData(
            imgUrl = "https://i.imgur.com/7bMqNv5.png",
            firstName = binding.firstNameLayout.editText?.text?.trim().toString(),
            lastName = binding.lastNameLayout.editText?.text?.trim().toString(),
            phone = binding.phoneTextLayout.editText?.text?.trim().toString(),
            location = location,
            postCode = postalCode
        )
    }

    private fun imagePicker() {
        // Check if the app has permission to access external storage
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permission to access external storage
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
            )
        } else {
            // Permission already granted, open image picker
        }
    }
}
