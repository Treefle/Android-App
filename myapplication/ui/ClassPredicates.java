package com.example.myapplication.ui;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClassPredicates {

    public static Predicate<ClassModel> isSelected(){
        return p -> (p.getTermName().equalsIgnoreCase(MainActivity.selectedTermName));
    }
    public static Predicate<AssessmentModel> isSelectedClass(){
        return p -> (p.assessmentClassName.equalsIgnoreCase(MainActivity.classModel.getClassName()));
    }

    public static Predicate<ClassModel> isNotSelected(){
        return p -> (!p.getTermName().equalsIgnoreCase(MainActivity.selectedTermName));
    }

    public static List<ClassModel> filterClass(List<ClassModel> classModels,
                                               Predicate<ClassModel> predicate){
        return classModels.stream().filter(predicate).collect(Collectors.<ClassModel>toList());
    }

    public static List<AssessmentModel> filterAssessment(List<AssessmentModel> assessmentModels,
                                                         Predicate<AssessmentModel> predicate){
        return assessmentModels.stream().filter(predicate).collect(Collectors.toList());
    }
}
