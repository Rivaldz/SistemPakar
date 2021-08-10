package com.example.model;

import com.example.fuzzy.Fuzzy;
import com.example.diagnosahamapadi.MainActivity;

public class CertaintyFactor {
    MainActivity mainActivity = new MainActivity();

    public CertaintyFactor() {
    }

    public void HitungGejala(){
        if (mainActivity.chkGejala1.isChecked() && mainActivity.chkGejala2.isChecked() && mainActivity.chkGejala4.isChecked()){

            String namaPentakit = "penyakit";

            double valueGejala1 = 0.2;
            double valueGejala2 = 0.6;
            double valueGejala4 = 0.8;

            double valueUserGejala1 = 0;

            valueUserGejala1 = Double.parseDouble(mainActivity.txxtNilaiGejala1.getText().toString());

            double valueUserGejala2 = Double.parseDouble(mainActivity.txxtNilaiGejala2.getText().toString());
            double valueUserGejala4 = Double.parseDouble(mainActivity.txxtNilaiGejala4.getText().toString());

            double resultCalculateGej1 = valueGejala1 * valueUserGejala1;
            double resultCalculateGej2 = valueGejala2 * valueUserGejala2;
            double resultCalculateGej4 = valueGejala4 * valueUserGejala4;

            double combine_CF1_CF2 = resultCalculateGej1 + resultCalculateGej2 * (1 - resultCalculateGej1);
            double combine_CFold_CF4 = combine_CF1_CF2 + resultCalculateGej4 * (1 - combine_CF1_CF2);

            String endResult = String.valueOf((combine_CFold_CF4 * 100));

            namaPentakit = "Test Result Penyakit " + "\n" + endResult + " %";
            // logic for fuzzy mamdani

//            Fuzzy fuzzy= new Fuzzy();
//            fuzzy.diseaseBla(valueUserGejala1);
            String severity = "Tingakt keparahan Penyakit " + "\n" + endResult + " %";
        }
    }
}
