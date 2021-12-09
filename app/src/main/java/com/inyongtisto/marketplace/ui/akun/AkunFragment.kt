package com.inyongtisto.marketplace.ui.akun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.inyongtisto.marketplace.NavigationActivity
import com.inyongtisto.marketplace.databinding.FragmentAkunBinding
import com.inyongtisto.marketplace.ui.updateProfile.UpdateProfileActivity
import com.inyongtisto.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.pushActivity

class AkunFragment : Fragment() {

    private lateinit var notificationsViewModel: AkunViewModel
    private var _binding: FragmentAkunBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(AkunViewModel::class.java)

        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUser()
        mainButton()
        return root
    }

    private fun mainButton() {
        binding.btnLogout.setOnClickListener {
            Prefs.isLogin = false
            pushActivity(NavigationActivity::class.java)
        }

        binding.btnUpdate.setOnClickListener {
            intentActivity(UpdateProfileActivity::class.java)
        }
    }

    private fun setUser() {
        val user = Prefs.getUser()
        if (user != null) {
            binding.apply {
                tvName.text = user.name
                tvEmail.text = user.email
                tvPhone.text = user.phone
                tvInisial.text = user.name.getInitial()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}