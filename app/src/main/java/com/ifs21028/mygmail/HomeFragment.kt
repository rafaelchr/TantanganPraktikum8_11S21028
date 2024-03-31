package com.ifs21028.mygmail

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21028.mygmail.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val dataMail = ArrayList<Mail>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rvMail.setHasFixedSize(false)
        dataMail.addAll(getListMail())
        showRecyclerList()

        return binding.root
    }

    @SuppressLint("Recycle")
    private fun getListMail(): ArrayList<Mail> {
        val dataIcon = resources.obtainTypedArray(R.array.mail_icon)
        val dataSender = resources.getStringArray(R.array.mail_sender)
        val dataTitle = resources.getStringArray(R.array.mail_title)
        val dataCont = resources.getStringArray(R.array.mail_cont)

        val listMail = ArrayList<Mail>()
        for (i in dataSender.indices) {
            val mail = Mail(
                    dataIcon.getResourceId(i, -1),
                    dataSender[i],
                    dataTitle[i],
                    dataCont[i],
                )
            listMail.add(mail)
        }
        return listMail
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvMail.layoutManager = GridLayoutManager(requireContext(), 2)
        } else {
            binding.rvMail.layoutManager = LinearLayoutManager(requireContext())
        }

        val mailAdapter = MailAdapter(dataMail)
        binding.rvMail.adapter = mailAdapter
//        mailAdapter.setOnItemClickCallback(object : MailAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: Fruit) {
//                showSelectedFruit(data)
//            }
//        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        arguments?.let {
//            val message = it.getString(EXTRA_MESSAGE)
//            binding.tvFragmentHomeMessage.text = message
//        }

        binding.apply {
            svFragmentDashboard.setupWithSearchBar(sbFragmentDashboard)
            svFragmentDashboard
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    sbFragmentDashboard.setText(svFragmentDashboard.text)
                    svFragmentDashboard.hide()
                    val message =
                        "Kamu mencari dengan keywords:\n${svFragmentDashboard.text}"
                    Toast.makeText(
                        requireContext(),
                        message,
                        Toast.LENGTH_LONG
                    ).show()
                    false
                }
        }
    }
    companion object {
        const val EXTRA_MESSAGE = "extra_message"
    }
}
