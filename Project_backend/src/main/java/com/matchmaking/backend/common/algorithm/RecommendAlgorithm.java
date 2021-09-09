package com.matchmaking.backend.common.algorithm;

import com.matchmaking.backend.common.lang.Degree;
import com.matchmaking.backend.common.lang.JobType;
import com.matchmaking.backend.entity.Post;
import com.matchmaking.backend.entity.Resume;
import com.matchmaking.backend.entity.Target;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class RecommendAlgorithm {

    private  int getDistance(int x,int y){
        return Math.abs(x - y);
    }

    private  int getTotalDistance(int expectedSalary, int jobType, int educationBackground, Target target){

        if(educationBackground != target.getDegree().getValue() || jobType != target.getJobType().getValue()){
            return 10;
        }

        return getDistance(educationBackground,target.getDegree().getValue())*2 + getDistance(expectedSalary, target.getExpectedSalary()) + getDistance(jobType,target.getJobType().getValue())*3;
    }

    public  List<Post> matchPost(int expectedSalary,int jobType,int educationalBackground, List<Post> targetList){

        List<Post> postList =  new ArrayList<>();
        // remove the post that not satisfied educational requirement
        for(Post post : targetList){
            Target target = postCovertToTarget(post);
            if(target.getDegree().getValue() == educationalBackground){
                postList.add(post);
            }
        }

        postList.sort(
                    new Comparator<Post>() {
                        @Override
                        public int compare(Post o1, Post o2) {
                            int distance1 = getTotalDistance(expectedSalary,jobType,educationalBackground,postCovertToTarget(o1));
                            int distance2= getTotalDistance(expectedSalary,jobType,educationalBackground,postCovertToTarget(o2));
                            if(distance1 < distance2){
                                return 0;
                            }else {
                                return -1;
                            }
                        }
                    }


            );


    return postList;
    }

    public List<Resume> matchResume(int expectedSalary,int jobType,int educationalBackground, List<Resume> targetList){


        List<Resume> resumeList =  new ArrayList<>();
        for(Resume resume : targetList){
            Target target = resumeCovertToTarget(resume);
            // setting undefined value to unknown
            if(target.getDegree() == null) target.setDegree(Degree.UNKNOWN);
            if(target.getJobType() == null) target.setJobType(JobType.UNKNOWN);
            // educational background filter, remove unsatisfied degree
            if(target.getDegree().getValue() == educationalBackground){
                if(target.getJobType().getValue() == jobType){
                    resumeList.add(resume);
                }
            }
        }

        resumeList.sort(
                new Comparator<Resume>() {
                    @Override
                    public int compare(Resume o1, Resume o2) {
                        int distance1 = getTotalDistance(expectedSalary,jobType,educationalBackground,resumeCovertToTarget(o1));
                        int distance2= getTotalDistance(expectedSalary,jobType,educationalBackground,resumeCovertToTarget(o2));
                        if(distance1 < distance2){
                            return 0;
                        }else {
                            return -1;
                        }
                    }
                }


        );
        return resumeList;
    }


    public  Target postCovertToTarget(Post post){
        Target target = new Target();
        for(Degree degree: Degree.values()){
            if(degree.getKey().equals(post.getEducationalBackground())){
                target.setDegree(degree);
            }
        }
        for(JobType jobType: JobType.values()){
            if(jobType.getKey().equals(post.getEmploymentType())){
                target.setJobType(jobType);
            }

        }
        target.setExpectedSalary((int) post.getMinSalary());
        return target;
    }

    public  Target resumeCovertToTarget(Resume resume){
        Target target = new Target();
        for(Degree degree: Degree.values()){
            if(degree.getKey().equals(resume.getEducationalBackground())){
                target.setDegree(degree);
            }
        }
        for(JobType jobType: JobType.values()){
            if(jobType.getKey().equals(resume.getJobType())){
                target.setJobType(jobType);
            }

        }
        target.setExpectedSalary((int) resume.getExpectedSalary());

        return target;
    }


}
