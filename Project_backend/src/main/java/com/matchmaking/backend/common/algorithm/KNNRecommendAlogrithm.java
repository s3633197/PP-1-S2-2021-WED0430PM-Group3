package com.matchmaking.backend.common.algorithm;

import com.matchmaking.backend.common.lang.Degree;
import com.matchmaking.backend.common.lang.JobType;
import com.matchmaking.backend.entity.Post;
import com.matchmaking.backend.entity.Resume;
import com.matchmaking.backend.entity.Target;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class KNNRecommendAlogrithm {

    private static int getDistance(int x,int y){
        return Math.abs(x - y);
    }

    private static int getTotalDistance(int expectedSalary, int jobType, int educationBackground, Target target){
        return getDistance(educationBackground,target.getDegree().getValue())*2 + getDistance(expectedSalary, target.getExpectedSalary()) + getDistance(jobType,target.getJobType().getValue())*3;
    }

    public static List<Post> matchPost(int expectedSalary,int jobType,int educationBackground, List<Post> targetList){
        targetList.sort(
                new Comparator<Post>() {
                    @Override
                    public int compare(Post o1, Post o2) {
                        int distance1 = getTotalDistance(expectedSalary,jobType,educationBackground,postCovertToTarget(o1));
                        int distance2= getTotalDistance(expectedSalary,jobType,educationBackground,postCovertToTarget(o2));
                        if(distance1 > distance2){
                            return 0;
                        }else {
                            return -1;
                        }
                    }
                }


        );
    return targetList;
    }


    public static Target postCovertToTarget(Post post){
        Target target = new Target();
        for(Degree degree: Degree.values()){
            if(degree.getKey().equals(post.getEducationalBackground())){
                target.setDegree(degree);
            }
        }
        for(JobType jobType: JobType.values()){
            if(jobType.equals(post.getEmploymentType())){
                target.setJobType(jobType);
            }
        }
        target.setExpectedSalary((int) post.getMinSalary());
        return target;
    }

    public static Target resumeCovertToTarget(Resume resume){
        Target target = new Target();
        for(Degree degree: Degree.values()){
            if(degree.getKey().equals(resume.getEducationalBackground())){
                target.setDegree(degree);
            }
        }
        for(JobType jobType: JobType.values()){
            if(jobType.equals(resume.getJobType())){
                target.setJobType(jobType);
            }
        }
        target.setExpectedSalary((int) resume.getExpectedSalary());
        return target;
    }


}
