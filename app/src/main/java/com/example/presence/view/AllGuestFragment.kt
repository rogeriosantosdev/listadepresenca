package com.example.presence.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.presence.R
import com.example.presence.service.constants.GuestConstants
import com.example.presence.viewmodel.GuestViewModel
import com.example.presence.view.adapter.GuestAdapter
import com.example.presence.view.listener.GuestListener

class AllGuestsFragment : Fragment() {

    private lateinit var mViewModel: GuestViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        mViewModel = ViewModelProvider(this)[GuestViewModel::class.java]
        val root = inflater.inflate(R.layout.fragment_all, container, false)

        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)

        recycler.layoutManager = LinearLayoutManager(root.context)

        recycler.adapter = mAdapter

        mListener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()

                bundle.putInt(GuestConstants.GUESTID, id)

                intent.putExtras(bundle)

                startActivity(intent)

            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.EMPTY)
            }
        }

        mAdapter.attachListener(mListener)

        observe()

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.EMPTY)
    }

    private fun observe() {
        mViewModel.guestList.observe(viewLifecycleOwner) {
            mAdapter.updateGuests(it)
        }
    }
}
