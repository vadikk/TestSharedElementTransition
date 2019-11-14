package com.example.testsharedelementtransition


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_detail.view.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    companion object{
        val MODEL_KEY = "MODEL_KEY"

        fun newInstance(model: ItemModel): DetailFragment{
            val detailFragment = DetailFragment()
            val bundle = Bundle().apply {
                putParcelable(MODEL_KEY, model)
            }
            detailFragment.arguments = bundle
            return detailFragment
        }
    }

    private var model: ItemModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.also {
            model = it?.getParcelable(MODEL_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        view.detailHolder.transitionName = model?.title
        view.imageView.transitionName = model?.id.toString()
        view.imageView.setImageDrawable(ContextCompat.getDrawable(context!!, model?.image?.toInt() ?: -1))
        view.textView2.text = model?.title
        view.textView3.text = model?.name

        return view
    }


}
