package com.and1ss.firebasetestapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.and1ss.firebasetestapp.databinding.ActivityMainBinding
import com.and1ss.firebasetestapp.domain.LoadingState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupListeners()
        setupObservers()
    }

    private fun setupView() {
        binding.textView.apply {
            text = viewModel.messageToSend
        }
    }

    private fun setupListeners() {
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.messageToSend = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.button.setOnClickListener {
            viewModel.saveMessage(
                "message/test_${java.util.Random().nextInt()}",
                viewModel.messageToSend
            )
        }
    }

    private fun setupObservers() {
        viewModel.messagesState.observe(this) {
            when (it) {
                is LoadingState.Loading -> binding.textView.text = it.initialData
                is LoadingState.SuccessfullyFinished -> binding.textView.text = it.result
                is LoadingState.Failed -> Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
            }
        }
    }
}