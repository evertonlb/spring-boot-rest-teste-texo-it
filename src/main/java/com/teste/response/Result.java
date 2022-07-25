package com.teste.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Result  {
    
    List<SubResult> min= new ArrayList<>();
    List<SubResult> max= new ArrayList<>();

    public List<SubResult> getMin() {
        return min;
    }

    public void setMin(List<SubResult> min) {
        this.min = min;
    }
    public List<SubResult> getMax() {
        return max;
    }

    public void setMax(List<SubResult> max) {
        this.max = max;
    }

    public void subResultSort(){
        Collections.sort(min);
    }

    public void subResultReverse(){
        Collections.reverse(max);
    }
   		

}
