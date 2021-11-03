package com.matchmaking.backend.common.algorithm;

import com.matchmaking.backend.common.lang.Degree;
import com.matchmaking.backend.common.lang.JobType;
import com.matchmaking.backend.entity.Post;
import com.matchmaking.backend.entity.Resume;
import com.matchmaking.backend.entity.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Component
public class RecommendAlgorithm {

    @Autowired
    KNNAlgorithm knnAlgorithm;

    @Autowired
    KMPAlgorithm kmpAlgorithm;



    public  List<Post> matchPost(int jobType,int educationalBackground, String location,String industry,List<Post> targetList){

        List<Post> postList =  new ArrayList<>();
        // remove the post that not satisfied educational requirement
        for(Post post : targetList){
            Target target = postCovertToTarget(post);
            if(target.getDegree().getValue() == educationalBackground){
                postList.add(post);
            }
        }

        // sort job type, educational background, salary
        postList.sort(
                    new Comparator<Post>() {
                        @Override
                        // compare distance to sort, short distance more match
                        public int compare(Post o1, Post o2) {
                            // calculate object 1 industry match distance
                            int industryNextO1[] = kmpAlgorithm.kmpNext(o1.getIndustry());
                            int industryDistanceO1 = kmpAlgorithm.kmpSearch(industry,o1.getIndustry(),industryNextO1);
                            // calculate object 2 industry match distance
                            int industryNextO2[] = kmpAlgorithm.kmpNext(o2.getIndustry());
                            int industryDistanceO2 = kmpAlgorithm.kmpSearch(industry,o2.getIndustry(),industryNextO2);

                            // calculate object 1 location match distance
                            int locationNextO1[] = kmpAlgorithm.kmpNext(o1.getAddress());
                            int locationDistanceO1 = kmpAlgorithm.kmpSearch(location,o1.getAddress(),locationNextO1);
                            // calculate object 2 location match distance
                            int locationNextO2[] = kmpAlgorithm.kmpNext(o2.getAddress());
                            int locationDistanceO2 = kmpAlgorithm.kmpSearch(location,o2.getAddress(),locationNextO2);

                            int distance1 = knnAlgorithm.getTotalDistance(jobType,educationalBackground,postCovertToTarget(o1)) + industryDistanceO1*5 + locationDistanceO1*4;

                            int distance2= knnAlgorithm.getTotalDistance(jobType,educationalBackground,postCovertToTarget(o2)) + industryDistanceO2*5 + locationDistanceO2*4;

                            if(distance1 < distance2){
                                return -1;
                            }else {
                                return 0;
                            }
                        }
                    }
            );

    return postList;
    }

    public List<Resume> matchResume(int jobType,int educationalBackground, String location,String industry,List<Resume> targetList){


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
                        int industryNextO1[] = kmpAlgorithm.kmpNext(o1.getWantedIndustry());
                        int industryDistanceO1 = kmpAlgorithm.kmpSearch(industry,o1.getWantedIndustry(),industryNextO1);
                        // calculate object 2 industry match distance
                        int industryNextO2[] = kmpAlgorithm.kmpNext(o2.getWantedIndustry());
                        int industryDistanceO2 = kmpAlgorithm.kmpSearch(industry,o2.getWantedIndustry(),industryNextO2);

                        // calculate object 1 location match distance
                        int locationNextO1[] = kmpAlgorithm.kmpNext(o1.getLocation());
                        int locationDistanceO1 = kmpAlgorithm.kmpSearch(location,o1.getLocation(),locationNextO1);
                        // calculate object 2 location match distance
                        int locationNextO2[] = kmpAlgorithm.kmpNext(o2.getLocation());
                        int locationDistanceO2 = kmpAlgorithm.kmpSearch(location,o2.getLocation(),locationNextO2);
                        int distance1 = knnAlgorithm.getTotalDistance(jobType,educationalBackground,resumeCovertToTarget(o1)) + industryDistanceO1*5 + locationDistanceO1*4;
                        int distance2= knnAlgorithm.getTotalDistance(jobType,educationalBackground,resumeCovertToTarget(o2))  + industryDistanceO2*5 + locationDistanceO2*4;
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


    // convert to target get represented integer
    public  Target postCovertToTarget(Post post){
        Target target = new Target();

        // initial job type number
        target.setJobType(JobType.UNKNOWN);
        target.setDegree(Degree.UNKNOWN);

        // ignore case-sensitive
        for(Degree degree: Degree.values()){
            if(degree.getKey().toLowerCase(Locale.ROOT).equals(post.getEducationalBackground().toLowerCase(Locale.ROOT))){
                target.setDegree(degree);
            }
        }
        for(JobType jobType: JobType.values()){
            if(jobType.getKey().toLowerCase(Locale.ROOT).equals(post.getEmploymentType().toLowerCase(Locale.ROOT))){
                target.setJobType(jobType);
            }

        }
        return target;
    }

    // convert to target get represented integer
    public  Target resumeCovertToTarget(Resume resume){
        Target target = new Target();
        // initial job type number
        target.setJobType(JobType.UNKNOWN);
        target.setDegree(Degree.UNKNOWN);

        // ignore case-sensitive
        String actualDegree = resume.getEducationalBackground().toLowerCase(Locale.ROOT);
        String actualJobType = resume.getJobType().toLowerCase(Locale.ROOT);

        // match degree replace integer
        for(Degree degree: Degree.values()){
            String targetDegree = degree.getKey().toLowerCase(Locale.ROOT);
            if(targetDegree.equals(actualDegree)){
                target.setDegree(degree);
            }
        }
        // match job type replace integer
        for(JobType jobType: JobType.values()){
            String targetType = jobType.getKey().toLowerCase(Locale.ROOT);

            if(targetType.equals(actualJobType)){
                target.setJobType(jobType);
            }

        }
        return target;
    }


}
