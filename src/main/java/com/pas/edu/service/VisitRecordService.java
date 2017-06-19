package com.pas.edu.service;


import com.pas.edu.entity.VisitRecord;

public interface VisitRecordService {
    public void createVisitRecord(VisitRecord visitRecord);
    public VisitRecord getVisitRecord(int visitId);
}
