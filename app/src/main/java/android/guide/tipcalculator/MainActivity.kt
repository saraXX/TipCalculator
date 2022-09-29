package android.guide.tipcalculator

import android.guide.tipcalculator.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            if(binding.costOfService.text.toString().isNotEmpty())
                calculateTip()
            else
                Toast.makeText(this,"enter a price",Toast.LENGTH_SHORT).show()
        }
    }

    fun calculateTip(){
        val stringinTextField = binding.costOfService.text.toString()
        val cost = stringinTextField.toDouble();
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when(selectedId){
            R.id.option_eighteen_percent ->  0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.20
        }
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}


