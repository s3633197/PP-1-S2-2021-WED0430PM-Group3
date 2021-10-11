package com.matchmaking.backend.common.algorithm;


import com.matchmaking.backend.entity.Target;
import org.springframework.stereotype.Component;

@Component
public class KNNAlgorithm {


    // KNN algorithm
    public static int getDistance(int x,int y){
        return Math.abs(x - y);
    }

    public static int getTotalDistance(int jobType, int educationBackground, Target target){
        // none of this match return  maximum distance
        if(educationBackground != target.getDegree().getValue() || jobType != target.getJobType().getValue()){
            return 10;
        }
        // determine proportion of each attributes (educational background *2, salary*1 ,Job type*3)
        return getDistance(educationBackground,target.getDegree().getValue())*2 + getDistance(jobType,target.getJobType().getValue())*3;

    }


}
