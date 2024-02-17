package com.example.myapplication.Database;

import android.app.Application;

import com.example.myapplication.DAOs.AssessmentDAO;
import com.example.myapplication.DAOs.ClassDAO;
import com.example.myapplication.DAOs.NoteDAO;
import com.example.myapplication.DAOs.TermDAO;
import com.example.myapplication.entities.Assessment;
import com.example.myapplication.entities.ClassEntity;
import com.example.myapplication.entities.Note;
import com.example.myapplication.entities.Term;
import com.example.myapplication.ui.ClassModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private TermDAO termDAO;
    private ClassDAO classDAO;
    private AssessmentDAO assessmentDAO;
    private NoteDAO noteDAO;
    private List<Term> allTerms;
    private List<ClassEntity> allClasses;
    private List<Assessment> allAssessments;
    private List<Note> allNotes;
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        TermDatabaseBuilder db = TermDatabaseBuilder.getDatabase(application);
        termDAO = db.termDAO();
        ClassDatabaseBuilder classDatabaseBuilder = ClassDatabaseBuilder.getDatabase(application);
        classDAO = classDatabaseBuilder.classDAO();
        AssessmentDatabaseBuilder assessmentDatabaseBuilder = AssessmentDatabaseBuilder.getDatabase(application);
        assessmentDAO = assessmentDatabaseBuilder.assessmentDAO();
        NoteDatabaseBuilder noteDatabaseBuilder = NoteDatabaseBuilder.getDatabase(application);
        noteDAO = noteDatabaseBuilder.noteDAO();
    }
    public boolean isEmpty (List list){
        if(list.isEmpty()){
            return true;
        }
        return false;
    }
    public List<Term> getAllTerms(){
      databaseExecutor.execute(()->{
          allTerms = termDAO.getAll();
      });
      try{
          Thread.sleep(1000);
      }catch (InterruptedException e){
          e.printStackTrace();
      }
      return allTerms;
    }
    public List<ClassEntity> getAllClasses(){
        databaseExecutor.execute(()->{
            allClasses = classDAO.getAll();
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return allClasses;
    }
    public List<Assessment> getAllAssessments() {
        databaseExecutor.execute(()->{
            allAssessments = assessmentDAO.getAll();
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return allAssessments;
    }
    public List<Note> getAllNotes() {
        databaseExecutor.execute(() -> {
            allNotes = noteDAO.getAll();
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return allNotes;
    }
    public void delete(Term term){
        databaseExecutor.execute(()->{
            termDAO.delete(term);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void insert(Term term){
        databaseExecutor.execute(()->{
            termDAO.insert(term);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void update(Term term){
        databaseExecutor.execute(()->{
            termDAO.update(term);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void insert(ClassEntity classEntity){
        databaseExecutor.execute(()->{
            classDAO.insert(classEntity);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete(ClassEntity classEntity) {
        databaseExecutor.execute(()->{
            classDAO.delete(classEntity);
        });
        try
        {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(ClassEntity classEntity) {
        databaseExecutor.execute(()->{
            classDAO.update(classEntity);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void insert (Assessment assessment){
        databaseExecutor.execute(()->{
            assessmentDAO.insert(assessment);
        });
        try
        {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();

        }
    }
    public void delete(Assessment assessment) {
        databaseExecutor.execute(()->{
            assessmentDAO.delete(assessment);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Assessment assessment) {
        databaseExecutor.execute(()->{
            assessmentDAO.update(assessment);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Note note) {
        databaseExecutor.execute(() -> {
            noteDAO.update(note);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insert(Note note){
        databaseExecutor.execute(()->{
            noteDAO.insert(note);
        });
        try
        {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete(Note note){
        databaseExecutor.execute(()-> {
            noteDAO.delete(note);
        });
        try
        {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
