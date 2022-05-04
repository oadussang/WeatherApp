package net.klonima.boldWeather.ui.modal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import net.klonima.boldWeather.R
import net.klonima.boldWeather.databinding.FragmentOtherErrorBinding

@AndroidEntryPoint
class NoNetworkErrorModalFragment(private val errorMessage: String? = null) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentOtherErrorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME,R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding =  FragmentOtherErrorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setOnClickListeners()
    }

    private fun setViews() {
        errorMessage?.let {
            binding.tvErrorCause.text = it
            binding.tvErrorCause.isVisible = true
        } ?: run {
            binding.tvErrorCause.isVisible = false
        }


    }

    private fun setOnClickListeners() {
        binding.btnUnderstood.setOnClickListener {
            this.dismiss()
        }
    }
}