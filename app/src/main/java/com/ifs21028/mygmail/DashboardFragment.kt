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
import com.ifs21028.mygmail.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private val dataVideo = ArrayList<Video>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding
            .inflate(inflater, container, false)

        binding.rvVideo.setHasFixedSize(false)
        dataVideo.addAll(getListVideo())
        showRecyclerList()

        return binding.root
    }

    @SuppressLint("Recycle")
    private fun getListVideo(): ArrayList<Video> {
        val dataIcon = resources.obtainTypedArray(R.array.video_icon)
        val dataName = resources.getStringArray(R.array.video_name)
        val dataTime = resources.getStringArray(R.array.video_time)

        val listVideo = ArrayList<Video>()
        for (i in dataName.indices) {
            val video = Video(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataTime[i],
            )
            listVideo.add(video)
        }
        return listVideo
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvVideo.layoutManager = GridLayoutManager(requireContext(), 2)
        } else {
            binding.rvVideo.layoutManager = LinearLayoutManager(requireContext())
        }

        val videoAdapter = VideoAdapter(dataVideo)
        binding.rvVideo.adapter = videoAdapter
//        mailAdapter.setOnItemClickCallback(object : MailAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: Fruit) {
//                showSelectedFruit(data)
//            }
//        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}
