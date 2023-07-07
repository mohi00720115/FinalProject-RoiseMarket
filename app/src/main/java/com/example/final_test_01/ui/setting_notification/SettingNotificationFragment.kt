package com.example.final_test_01.ui.setting_notification

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.work.WorkManager
import com.example.final_test_01.R
import com.example.final_test_01.databinding.FragmentSettingNotificationBinding
import com.example.final_test_01.util.snack
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingNotificationFragment : Fragment(R.layout.fragment_setting_notification) {

    private lateinit var binding: FragmentSettingNotificationBinding
    private val viewModel: SettingFragmentViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.workManager = WorkManager.getInstance(requireContext())

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {

                R.id.three -> {
                    viewModel.apply {
                        time = "3"
                    }
                }

                R.id.five -> {
                    viewModel.apply {
                        time = "5"
                    }
                }

                R.id.eight -> {
                    viewModel.apply {
                        time = "8"
                    }
                }

                R.id.twelve -> {
                    viewModel.apply {
                        time = "12"
                    }
                }
            }
            viewModel.setTime()
        }

        binding.apply.setOnClickListener {
            if (binding.time.text.toString().isNotBlank()) {
                viewModel.time = binding.time.text.toString()
                viewModel.setTime()
            } else binding.root.snack(getString(R.string.input_is_wrong))
        }

        binding.stop.setOnClickListener {
            viewModel.stopWorker()
        }

        binding.start.setOnClickListener {
            viewModel.startWorker()
        }
    }

}