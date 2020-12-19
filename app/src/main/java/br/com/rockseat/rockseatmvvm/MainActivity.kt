package br.com.rockseat.rockseatmvvm

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.rockseat.rockseatmvvm.address.Address
import br.com.rockseat.rockseatmvvm.address.AddressViewModel
import br.com.rockseat.rockseatmvvm.service.Resource
import br.com.rockseat.rockseatmvvm.service.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    lateinit var textViewCep: TextView
    lateinit var textViewStreet: TextView
    lateinit var textViewCity: TextView
    lateinit var textViewState: TextView
    lateinit var llProgressBar: LinearLayout

    private val viewModel: AddressViewModel by viewModel()

    private val observer = Observer<Resource<Address>> {
        when (it.status) {
            Status.SUCCESS -> updateTextView(it)
            Status.ERROR -> showError(it)
            Status.LOADING -> showLoading(true)
        }
    }

    private fun showError(resource: Resource<Address>) {
        showLoading(false)
        Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            llProgressBar.visibility = View.VISIBLE
        } else {
            llProgressBar.visibility = View.GONE
        }
    }

    private fun updateTextView(resource: Resource<Address>) {
        showLoading(false)
        textViewStreet.text = resource.data?.street
        textViewCity.text = resource.data?.city
        textViewState.text = resource.data?.state
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewCep = findViewById(R.id.textViewCep)
        textViewStreet = findViewById(R.id.textViewStreet)
        textViewCity = findViewById(R.id.textViewCity)
        textViewState = findViewById(R.id.textViewState)
        llProgressBar = findViewById(R.id.llProgressBar)

        textViewCep.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                viewModel.getCep(textViewCep.text.toString())
            }
        }

        viewModel.hehe.observe(this, observer)

        viewModel.addressResult.observe(this, Observer {
            if (it.status == Status.SUCCESS) {
                textViewStreet.text = it.data?.street
                textViewCity.text = it.data?.city
                textViewState.text = it.data?.state
            }

            if (it.status == Status.ERROR) {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}