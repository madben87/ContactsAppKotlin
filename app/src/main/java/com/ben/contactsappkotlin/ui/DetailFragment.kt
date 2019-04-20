package com.ben.contactsappkotlin.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ben.contactsappkotlin.R
import com.ben.contactsappkotlin.data.Person
import com.ben.contactsappkotlin.utils.Constants
import com.ben.contactsappkotlin.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var person: Person? = null

    companion object{
        @JvmStatic
        fun newInstance(person: Person): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(Constants.DETAIL_BUNDLE_KEY, person)
            fragment.setArguments(bundle)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = null// = inflater.inflate(R.layout.fragment_detail, container, false)



        if (arguments!!.containsKey(Constants.DETAIL_BUNDLE_KEY)) {
            this.person = arguments!!.getParcelable(Constants.DETAIL_BUNDLE_KEY)
            if (person != null) {
                val binding: FragmentDetailBinding =
                    DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
                view = binding.root
                binding.person = person
            }
        }

        return view
    }
}