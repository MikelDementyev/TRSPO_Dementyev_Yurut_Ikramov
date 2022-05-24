package com.example.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        observeLiveData()
        viewModel.getAllPersons()

        search_edit.addTextChangedListener {
            viewModel.findByName(it.toString())
        }

        add_person.setOnClickListener {
            val createPersonFragment = BottomSheetCreatePerson(viewModel)
            createPersonFragment.show(this.supportFragmentManager, null)
        }

        recycler_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && add_person.isShown)
                    add_person.hide()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    add_person.show()
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun observeLiveData() {
        viewModel.persons.observe(this) { list ->
            if (list.isNullOrEmpty()) {
                text_empty.visibility = View.VISIBLE
            } else {
                text_empty.visibility = View.INVISIBLE
            }
            initRecycler(list ?: listOf())
        }
    }

    private fun initRecycler(list: List<PersonModel>) {
        recycler_list.layoutManager = LinearLayoutManager(this)
        recycler_list.adapter = PersonAdapter(list, this)
    }
}