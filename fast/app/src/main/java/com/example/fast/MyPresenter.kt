package com.example.fast

class MyPresenter {
    private var view: MyView? = null

    fun OnButtonClick(){
        view?.showData("성공??")
    }

    fun attachView(view: MyView) {
        this.view = view
    }
}