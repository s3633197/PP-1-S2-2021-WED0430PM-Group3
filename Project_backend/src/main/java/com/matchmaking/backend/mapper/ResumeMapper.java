package com.matchmaking.backend.mapper;

import com.matchmaking.backend.entity.Resume;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResumeMapper {

    void createResume(Resume resume);

    Resume getResumeByAccountId(int accountId);

    void updateResume(Resume resume);

}
