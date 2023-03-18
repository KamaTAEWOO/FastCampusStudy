package fastcampus.part1.chpter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.widget.addTextChangedListener
import fastcampus.part1.chpter3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val outputTextView = binding.outputTextView
        val inputEditView = binding.inputEditText
        val outputUnitTextView = binding.outputUnitTextView
        val inputUnitTextview = binding.inputUnitTextView
        val swapImageButton = binding.swapImageButton

        var inputNumber: Int = 0

        inputEditView.addTextChangedListener{ text ->
            inputNumber = if (text.isNullOrEmpty()) {
                0
            } else {
                text.toString().toInt()
            }

            if(cmToM) {
                outputTextView.text = inputNumber.times(0.01).toString()
            }else {
                outputTextView.text = inputNumber.times(100).toString()
            }
        }

        swapImageButton.setOnClickListener{
            cmToM = !cmToM
            if(cmToM) {
                inputUnitTextview.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                inputUnitTextview.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if(cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if(cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState)
    }
}