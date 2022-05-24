package com.example.students

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.create_person_fragment.*

class BottomSheetCreatePerson(
    val viewModel: MainActivityViewModel
): BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.create_person_fragment, container, false)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        dialog?.setOnShowListener {
            Handler().postDelayed({
                val d = dialog as BottomSheetDialog
                val bottomSheet = d.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }, 100)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_done.setOnClickListener {
            it.apply { isEnabled = false; postDelayed({isEnabled = true}, 1000) }
            insertPersonMyDebt(
                PersonModel(
                    fio = edit_fio.text.toString(),
                    flg = edit_flg.text.toString(),
                    vKori = edit_v_kovi.text.toString(),
                    rvKori = edit_rv_kovi.text.toString(),
                    covid = edit_covid.text.toString()
                )
            )
        }

        button_cancel.setOnClickListener {
            it.apply { isEnabled = false; postDelayed({isEnabled = true}, 1000) }
            dismiss()
        }

        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        edit_fio.postDelayed({
            edit_fio.requestFocus()
            imm!!.showSoftInput(edit_fio, 0)
        }, 100)
    }

    @SuppressLint("CheckResult")
    private fun insertPersonMyDebt(person: PersonModel) {
        if (person.fio.isNullOrEmpty()) {
            Toast.makeText(context, "Заполните поле 'ФИО'", Toast.LENGTH_SHORT).show()
            return
        }
        if (person.flg.isNullOrEmpty()) {
            Toast.makeText(context, "Заполните поле 'ФЛГ'", Toast.LENGTH_SHORT).show()
            return
        }
        if (person.vKori.isNullOrEmpty()) {
            Toast.makeText(context, "Заполните поле 'в кори'", Toast.LENGTH_SHORT).show()
            return
        }
        if (person.rvKori.isNullOrEmpty()) {
            Toast.makeText(context, "Заполните поле 'рв кори'", Toast.LENGTH_SHORT).show()
            return
        }
        if (person.covid.isNullOrEmpty()) {
            Toast.makeText(context, "Заполните поле 'COVID-19'", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.insertPerson(person)
        dismiss()
    }

    override fun getTheme(): Int =
        R.style.AppBottomSheetDialogTheme

}