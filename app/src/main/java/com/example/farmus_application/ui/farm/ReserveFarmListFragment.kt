package com.example.farmus_application.ui.farm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.FragmentFarmTab1Binding
import com.example.farmus_application.model.reserve.reserve_list.ReserveListResult
import com.example.farmus_application.repository.UserPrefsStorage
import com.example.farmus_application.ui.MainActivity
import com.example.farmus_application.viewmodel.farm.FarmListViewModel
import com.kakao.sdk.user.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReserveFarmListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReserveFarmListFragment : Fragment() {

    private lateinit var binding: FragmentFarmTab1Binding
    private lateinit var farmListViewModel: FarmListViewModel

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val email = UserPrefsStorage.email ?: ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFarmTab1Binding.inflate(layoutInflater, container, false)
        farmListViewModel = ViewModelProvider(this)[FarmListViewModel::class.java]
        farmListViewModel.let {
            it.getCurrentList(email)
            it.getPastList(email)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecentRecyclerView()
        initPastRecyclerView()

    }

    private fun initRecentRecyclerView() {
        val recentAdapter = ReserveFarmListRVAdapter().apply {
            setOnClickListener(object : ReserveFarmListRVAdapter.OnClickListener {
                override fun onClick(view: View, data: ReserveListResult, pos: Int) {
                    // itme click시 해당 farm의 farmId를 가지고 상세정보 페이지로 이동
                }
            })
        }
        binding.rvRecent.apply {
            adapter = recentAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        farmListViewModel.currentFarmList.observe(viewLifecycleOwner) {
            recentAdapter.submitList(it.result)
        }

    }

    private fun initPastRecyclerView() {
        val pastAdapter = ReserveFarmListRVAdapter().apply {
            setOnClickListener(object : ReserveFarmListRVAdapter.OnClickListener {
                override fun onClick(view: View, data: ReserveListResult, pos: Int) {
                    // itme click시 해당 farm의 farmId를 가지고 상세정보 페이지로 이동
                }
            })
        }
        binding.rvPast.apply {
            adapter = pastAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        farmListViewModel.pastFarmList.observe(viewLifecycleOwner) {
            pastAdapter.submitList(it.result)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Tab1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReserveFarmListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}