package com.example.testsharedelementtransition


import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.util.Pair
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_test_fragment2.view.*

/**
 * A simple [Fragment] subclass.
 */
class TestFragment2 : Fragment() {

    private var adapter: ItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_test_fragment2, container, false)

        adapter = ItemAdapter { view, model, secondView ->

            val fragmentManag = fragmentManager
            val previousFragment = fragmentManag?.findFragmentById(R.id.root_frame)
//            val previousFragment = this
            val nextFragment = DetailFragment()

            val transaction = fragmentManag?.beginTransaction()

            val exitFade = Fade().apply {
                duration = 300
            }
            previousFragment?.exitTransition = exitFade

            val enterTransitionSet  = TransitionSet().apply {
                addTransition(TransitionInflater.from(context).inflateTransition(android.R.transition.move))
                duration = 1000
                startDelay = 300
            }
            nextFragment.sharedElementEnterTransition = enterTransitionSet

            val enterFade = Fade().apply {
//                startDelay = 1000 + 300
                duration = 300
            }
            nextFragment.enterTransition = enterFade
//            nextFragment?.exitTransition = exitFade

            val p1: android.util.Pair<View, String> = Pair.create(view, view.transitionName)
            val p2: android.util.Pair<View, String> = Pair.create(secondView, secondView.transitionName)

            transaction
                ?.replace(R.id.root_frame, nextFragment.apply { arguments = Bundle().apply { putParcelable(DetailFragment.MODEL_KEY, model) }  })
                ?.addToBackStack("TAG")
                ?.addSharedElement(secondView, secondView.transitionName)
                ?.addSharedElement(view, view.transitionName)
                ?.commit()
        }

        view.recyclerview.adapter = adapter
        view.recyclerview.addItemDecoration(ItemDecoration())

        var list = mutableListOf<ItemModel>().apply {
            add(ItemModel(0, R.drawable.ic_launcher_background.toString(), "AAAA", "A2222"))
            add(ItemModel(1, R.drawable.ic_launcher_background.toString(), "BBBB", "B2222"))
            add(ItemModel(2, R.drawable.ic_launcher_background.toString(), "CCCC", "C2222"))
            add(ItemModel(3, R.drawable.ic_launcher_background.toString(), "DDDD", "D2222"))
            add(ItemModel(4, R.drawable.ic_launcher_background.toString(), "SSSS", "S2222"))
            add(ItemModel(5, R.drawable.ic_launcher_background.toString(), "ZZZZ", "Z2222"))
        }

        adapter?.addAll(list)

        return view
    }


}
