package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.AlarmManager.MyIntentService;
import com.example.myapplication.Database.Repository;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.entities.Assessment;
import com.example.myapplication.entities.ClassEntity;
import com.example.myapplication.entities.Term;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.DeleteTable;
import androidx.room.RoomDatabase;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.example.myapplication.ui.ClassPredicates.*;

import static java.text.DateFormat.getDateInstance;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface,
        TermDialogFragment.OnDialogInputListener,
        ConfirmDialogFragment.OnDialogInputListener,
        EditDeleteDialogFragment.OnEditInputListener,
        ClassDialogFragment.classDialogInputListener,
        AssessmentDialogFragment.assessmentDialogInputListener {
    private static final String CHANNEL_ID = "Channel Five";
    ArrayList<TermModel> termModels = new ArrayList<>();
    public static ArrayList<ClassModel> allClasses = new ArrayList<>();
    ArrayList<ClassModel> matchingClasses = new ArrayList<>();
    public static ArrayList<AssessmentModel> allAssessments = new ArrayList<>();
    ArrayList<AssessmentModel> matchingAssessments = new ArrayList<>();
    TermAdapter adapter;
    ClassAdapter classAdapter;
    AssessmentAdapter assessmentAdapter;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public static String selectedTermName;
    public static String termName;
    public static String termStart;
    public static String termEnd;
    public static ClassModel classModel;
    public static AssessmentModel assessmentModel;
    public int termClickPosition;

    public static int getClassClickPosition() {
        return classClickPosition;
    }

    public static int classClickPosition;
    public int assessmentClickPosition;
    public boolean add = true;
    int type = 0;
    boolean delete = true;


    public void setTermClickPosition(int termClickPosition) {
        this.termClickPosition = termClickPosition;
    }

    public static String getTermName() {
        return termName;
    }

    public static String getTermStart() {
        return termStart;
    }

    public static String getTermEnd() {
        return termEnd;
    }

    @Override
    public void confirmDelete(int input) {
        switch (type) {
            case 1:
                deleteTerm(termClickPosition);
                break;
            case 2:
                deleteClass(classClickPosition);
                break;
            case 3:
                deleteAssessment(assessmentClickPosition);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(view -> Snackbar.make(view, "Adding New Term", Snackbar.LENGTH_LONG)
                .setAction("Action", fabClickListener()).show());
        binding.classFab.setOnClickListener(view -> Snackbar.make(view, "Adding New Class", Snackbar.LENGTH_LONG)
                .setAction("Action", fabClassClickListener()).show());
        binding.assessmentFab.setOnClickListener(view -> Snackbar.make(view, "Adding New Assessment", Snackbar.LENGTH_LONG)
                .setAction("Action", fabAssessmentClickListener()).show());


        setUpRecycler();
        setupTermModelsDB();
        setClassModelsDB();
        setAssessmentModelsDB();
    }
    public void navigateHome(View view){
        Navigation.findNavController(view).navigate(R.id.action_global_mainActivity);
    }
    public void deleteAllTerms(){
        Repository repository = new Repository(getApplication());
            if(repository.getAllTerms().iterator().hasNext()){
                repository.getAllTerms().iterator().forEachRemaining((Term)->{
                    repository.delete(repository.getAllTerms().iterator().next());
                });
            }
        }
    public void deleteAllClasses(){
        Repository repository = new Repository(getApplication());
        if(!repository.getAllClasses().isEmpty()){
            repository.getAllClasses().stream().iterator().forEachRemaining((ClassModel)->{
              repository.delete(repository.getAllClasses().stream().iterator().next());
            });
        }

    }
    public void deleteAllAssessments(){
        Repository repository = new Repository(getApplication());
            repository.getAllAssessments().stream().iterator().forEachRemaining((Assessment->{
                repository.delete(repository.getAllAssessments().stream().iterator().next());
            }));
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onStart(){
        super.onStart();
    }
    @Override
    public void onStop(){
        super.onStop();
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setUpTermModels() {
        //deleteAllTerms();
        String[] termNames = getResources().getStringArray(R.array.term_names);
        String[] termStarts = getResources().getStringArray(R.array.term_starts);
        String[] termEnds = getResources().getStringArray(R.array.term_ends);
        for (int i = 0; i < 4; i++) {
            termModels.add(new TermModel(termNames[i],termStarts[i],termEnds[i]));

        }
    }
    private void setupTermModelsDB(){
        Repository repository = new Repository(getApplication());
        repository.getAllTerms().stream().iterator().forEachRemaining(term -> {
            termModels.add(new TermModel(term.getTermName(),term.getTermStart(),term.getTermEnd()));
        });
    }


    private void setClassModels() {
        deleteAllClasses();
        Repository repository = new Repository(getApplication());
        String[] classTermNames = getResources().getStringArray(R.array.class_term_names);
        String[] classNames = getResources().getStringArray(R.array.class_name);
        String[] classStart = getResources().getStringArray(R.array.classStart);
        String[] classEnd = getResources().getStringArray(R.array.classEnd);
        String[] classStatus = getResources().getStringArray(R.array.classStatus);
        for (int i = 0; i < 4; i++) {
            repository.insert(new ClassModel(classTermNames[i],classNames[i],classStart[i],classEnd[i],classStatus[i], false, false));
            allClasses.add(new ClassModel(classTermNames[i], classNames[i], classStart[i], classEnd[i], classStatus[i], false, false));
            matchingClasses.add(new ClassModel(classTermNames[i], classNames[i], classStart[i], classEnd[i], classStatus[i], false, false));

        }
    }
    private void setClassModelsDB(){
        Repository repository = new Repository(getApplication());
        repository.getAllClasses().stream().iterator().forEachRemaining(classEntity -> {
            allClasses.add(new ClassModel(classEntity.getTermName(),classEntity.getClassName(),classEntity.getClassStart(),
                    classEntity.getClassEnd(),classEntity.getClassStatus(), classEntity.getStartAlerts(), classEntity.getEndAlerts()));
            matchingClasses.add(new ClassModel(classEntity.getTermName(),classEntity.getClassName(),classEntity.getClassStart(),
                    classEntity.getClassEnd(),classEntity.getClassStatus(), classEntity.getStartAlerts(), classEntity.getEndAlerts()));
        });
    }
/*
    private void setAssessmentModels() {
        deleteAllAssessments();
        Repository repository = new Repository(getApplication());
        String[] assessmentClassNames = getResources().getStringArray(R.array.assessmentClassName);
        String[] assessmentTypes = getResources().getStringArray(R.array.assessmentType);
        String[] assessmentNames = getResources().getStringArray(R.array.assessmentName);
        String[] assessmentStarts = getResources().getStringArray(R.array.assessmentStart);
        String[] assessmentEnds = getResources().getStringArray(R.array.assessmentEnd);
        for (int i = 0; i < 4; i++) {
            repository.insert(new Assessment(assessmentClassNames[i], assessmentTypes[i], assessmentNames[i], assessmentStarts[i], assessmentEnds[i]));
            allAssessments.add(new AssessmentModel(assessmentClassNames[i], assessmentTypes[i], assessmentNames[i], assessmentStarts[i]
                    , assessmentEnds[i]));
            matchingAssessments.add(new AssessmentModel(assessmentClassNames[i], assessmentTypes[i], assessmentNames[i], assessmentStarts[i]
                    , assessmentEnds[i]));
        }}**/

    private void setAssessmentModelsDB(){
        Repository repository = new Repository(getApplication());
        repository.getAllAssessments().stream().iterator().forEachRemaining(assessment -> {
            allAssessments.add(new AssessmentModel(assessment.getAssessmentClassName(),assessment.getType(),
                    assessment.getTitle(),assessment.getStartDate(),assessment.getEndDate(),assessment.getStartAlerts(),assessment.getEndAlerts()));
            matchingAssessments.add(new AssessmentModel(assessment.getAssessmentClassName(),assessment.getType(),
                    assessment.getTitle(),assessment.getStartDate(),assessment.getEndDate(),assessment.getStartAlerts(),assessment.getEndAlerts()));
        });
    }


    void setUpRecycler() {
        RecyclerView recyclerView = findViewById(R.id.termRecycler);
        adapter = new TermAdapter(this, termModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView classView = findViewById(R.id.classRecycler);
        classAdapter = new ClassAdapter(this, matchingClasses, this);
        classView.setAdapter(classAdapter);
        classView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView assessmentView = findViewById(R.id.assignmentRecycler);
        assessmentAdapter = new AssessmentAdapter(this, matchingAssessments, this);
        assessmentView.setAdapter(assessmentAdapter);
        assessmentView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onClassClick(int position) {
        classClickPosition = position;
        classModel = matchingClasses.get(position);
        if (filterAssessment(allAssessments, isSelectedClass()).stream().iterator().hasNext()) {
            matchingAssessments.clear();
            filterAssessment(allAssessments, isSelectedClass()).iterator().forEachRemaining((AssessmentModel ->
                    matchingAssessments.add(filterAssessment(allAssessments, isSelectedClass()).iterator().next())));
        Toast.makeText(this, "Showing " + matchingAssessments.size() + " Assessments for " + classModel.className + " Course.", Toast.LENGTH_SHORT).show();
        assessmentAdapter.notifyDataSetChanged();
        }
        else{
            matchingAssessments.clear();
            assessmentAdapter.notifyDataSetChanged();
            Toast.makeText(this, "No matching Assessments", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLongClassClick(int position) {
        classClickPosition = position;
        add = false;
        type = 2;
        openEditDialog();
    }

    @Override
    public void onAssessmentClick() {

    }

    @Override
    public void onLongAssessmentClick(int position) {
        assessmentClickPosition = position;
        add = false;
        type = 3;
        openEditDialog();
    }

    @Override
    public void noteClick(int position) {

    }

    @Override
    public void noteLongClick(int position) {

    }

    @Override
    public void onTermClick(int position) {
        selectedTermName = termModels.get(position).termName;
        Matching();
    }

    public void onLongTermClick(int position) {
        selectedTermName = termModels.get(position).termName;
        setTermClickPosition(position);
        add = false;
        type = 1;
        openEditDialog();
    }

    public void Matching() {
        if(!filterClass(allClasses,isSelected()).isEmpty()){
                matchingClasses.clear();
                filterClass(allClasses, isSelected()).iterator().forEachRemaining((ClassModel) ->
                        matchingClasses.add(filterClass(allClasses, isSelected()).iterator().next()));

                    Toast.makeText(this, "Showing " + matchingClasses.size() + " Classes for " + selectedTermName + " Term", Toast.LENGTH_SHORT).show();
                    classAdapter.notifyDataSetChanged();
            }
        else{
            Toast.makeText(this,"No associated Classes",Toast.LENGTH_SHORT).show();
        }
    }


    public void addTerm(TermModel termModel) {
        Repository repository = new Repository(getApplication());
        Term term = new Term();
        term.setTermName(termModel.getTermName());
        term.setTermStart(termModel.getTermStart());
        term.setTermEnd(termModel.getTermEnd());
        repository.insert(term);
        termModels.add(new TermModel(termModel.termName, termModel.termStart, termModel.termEnd));
        adapter.notifyItemInserted(termModels.size());
    }

    public void replaceTerm(TermModel termModel) {
        Repository repository = new Repository(getApplication());
        Term term = repository.getAllTerms().get(termClickPosition);
        term.setTermName(termModel.getTermName());
        term.setTermStart(termModel.getTermStart());
        term.setTermEnd(termModel.getTermEnd());
        repository.update(term);
        termModels.set(termClickPosition, (new TermModel(termModel.termName, termModel.termStart, termModel.termEnd)));
        adapter.notifyItemChanged(termClickPosition);
    }

    public void deleteTerm(int position) {
        if (filterClass(allClasses, isSelected()).iterator().hasNext()) {
            Toast.makeText(this, "Cannot delete Term with existing classes.", Toast.LENGTH_SHORT).show();
        } else {
            Repository repository = new Repository(getApplication());
            Term term = repository.getAllTerms().get(termClickPosition);
            repository.delete(term);
            termModels.remove(position);
            adapter.notifyItemRemoved(position);
        }
    }

    public void addClass(ClassModel classModel) {

        Repository repository = new Repository(getApplication());
        ClassEntity entity = new ClassEntity();
        entity.setTermName(classModel.termName);
        entity.setClassName(classModel.className);
        entity.setClassStart(classModel.classStart);
        entity.setStartAlerts(classModel.startAlerts);
        entity.setClassEnd(classModel.classEnd);
        entity.setEndAlerts(classModel.endAlerts);
        entity.setClassStatus(classModel.classStatus);
        entity.setInstructorName(classModel.instructorName);
        entity.setInstructorPhone(classModel.instructorPhone);
        entity.setInstructorEmail(classModel.instructorEmail);
        repository.insert(entity);

        allClasses.add(new ClassModel(classModel.termName,
                classModel.className,
                classModel.classStart,
                classModel.startAlerts,
                classModel.classEnd,
                classModel.endAlerts,
                classModel.classStatus,
                classModel.instructorName,
                classModel.instructorPhone,
                classModel.instructorEmail));

        matchingClasses.add(new ClassModel(classModel.termName,
                classModel.className,
                classModel.classStart,
                classModel.startAlerts,
                classModel.classEnd,
                classModel.endAlerts,
                classModel.classStatus,
                classModel.instructorName,
                classModel.instructorPhone,
                classModel.instructorEmail));

        classAdapter.notifyItemInserted(allClasses.size());
    }

    public void replaceClass(int position, ClassModel classModel) {

        Repository repository = new Repository(getApplication());
        ClassEntity entity = repository.getAllClasses().get(position);
        entity.setTermName(classModel.termName);
        entity.setClassName(classModel.className);
        entity.setClassStart(classModel.classStart);
        entity.setStartAlerts(classModel.startAlerts);
        entity.setClassEnd(classModel.classEnd);
        entity.setEndAlerts(classModel.endAlerts);
        entity.setClassStatus(classModel.classStatus);
        entity.setInstructorName(classModel.instructorName);
        entity.setInstructorPhone(classModel.instructorPhone);
        entity.setInstructorEmail(classModel.instructorEmail);
        repository.update(entity);

        allClasses.set(position, new ClassModel(classModel.termName,
                classModel.className,
                classModel.classStart,
                classModel.startAlerts,
                classModel.classEnd,
                classModel.endAlerts,
                classModel.classStatus,
                classModel.instructorName,
                classModel.instructorPhone,
                classModel.instructorEmail));

        matchingClasses.set(position, new ClassModel(classModel.termName,
                classModel.className,
                classModel.classStart,
                classModel.startAlerts,
                classModel.classEnd,
                classModel.endAlerts,
                classModel.classStatus,
                classModel.instructorName,
                classModel.instructorPhone,
                classModel.instructorEmail));

        classAdapter.notifyItemChanged(position);
    }

    public void deleteClass(int position) {
        Repository repository = new Repository(getApplication());
        ClassEntity entity = repository.getAllClasses().get(classClickPosition);
        repository.delete(entity);
        allClasses.remove(position);
        matchingClasses.remove(position);
        classAdapter.notifyItemRemoved(position);

    }

    public void addAssessment(AssessmentModel assessmentModel) {
        Repository repository = new Repository(getApplication());
        Assessment assessment = new Assessment();
        assessment.setAssessmentClassName(assessmentModel.assessmentClassName);
                assessment.setType(assessmentModel.type);
                assessment.setTitle(assessmentModel.title);
                assessment.setStartDate(assessmentModel.startDate);
                assessment.setStartAlerts(assessmentModel.startAlerts);
                assessment.setEndDate(assessmentModel.endDate);
                assessment.setEndAlerts(assessmentModel.endAlerts);
                repository.insert(assessment);
        allAssessments.add(new AssessmentModel(assessmentModel.assessmentClassName,
                assessmentModel.type, assessmentModel.title, assessmentModel.startDate, assessmentModel.endDate,assessmentModel.startAlerts,assessmentModel.endAlerts));
        matchingAssessments.add(new AssessmentModel(assessmentModel.assessmentClassName,
                assessmentModel.type, assessmentModel.title, assessmentModel.startDate, assessmentModel.endDate,assessmentModel.startAlerts,assessmentModel.endAlerts));
        assessmentAdapter.notifyItemInserted(allAssessments.size());

    }

    public void replaceAssessment(int position, AssessmentModel assessmentModel) {
        Repository repository = new Repository(getApplication());
        Assessment assessment =repository.getAllAssessments().get(assessmentClickPosition);
        assessment.setAssessmentClassName(assessmentModel.assessmentClassName);
        assessment.setType(assessmentModel.type);
        assessment.setTitle(assessmentModel.title);
        assessment.setStartDate(assessmentModel.startDate);
        assessment.setStartAlerts(assessmentModel.startAlerts);
        assessment.setEndDate(assessmentModel.endDate);
        assessment.setEndAlerts(assessmentModel.endAlerts);
        repository.update(assessment);
        allAssessments.set(position, new AssessmentModel(assessmentModel.assessmentClassName,
                assessmentModel.type, assessmentModel.title, assessmentModel.startDate, assessmentModel.endDate,assessmentModel.startAlerts,assessmentModel.endAlerts));
        matchingAssessments.set(position, new AssessmentModel(assessmentModel.assessmentClassName,
                assessmentModel.type, assessmentModel.title, assessmentModel.startDate, assessmentModel.endDate,assessmentModel.startAlerts,assessmentModel.endAlerts));
        assessmentAdapter.notifyItemChanged(position);
    }

    public void deleteAssessment(int position) {
        Repository repository = new Repository(getApplication());
        Assessment assessment = repository.getAllAssessments().get(assessmentClickPosition);
        repository.delete(assessment);
        allAssessments.remove(position);
        matchingAssessments.remove(position);
        assessmentAdapter.notifyItemRemoved(position);
    }

    public void openTermDialog() {
        TermDialogFragment termDialogFragment = new TermDialogFragment();
        termDialogFragment.show(getSupportFragmentManager(), "New Term");
    }

    public void openClassDialog(boolean edit) {
        if(!matchingClasses.isEmpty()){
        classModel = matchingClasses.get(classClickPosition);
        }
        ClassDialogFragment classDialogFragment = new ClassDialogFragment();
        classDialogFragment.setEdit(edit);
        classDialogFragment.show(getSupportFragmentManager(), "New Class");
    }

    public void openAssessmentDialog(boolean edit) {
        if(!matchingAssessments.isEmpty()){
        assessmentModel = matchingAssessments.get(assessmentClickPosition);
        }
        AssessmentDialogFragment assessmentDialogFragment = new AssessmentDialogFragment();
        assessmentDialogFragment.setEdit(edit);
        assessmentDialogFragment.show(getFragmentManager(), "New Assessment");
    }

    public void openConfirmDialog() {
        ConfirmDialogFragment confirmDialogFragment = new ConfirmDialogFragment();
        confirmDialogFragment.show(getSupportFragmentManager(), "Confirmation");
    }

    public void openEditDialog() {
        EditDeleteDialogFragment editDeleteDialogFragment = new EditDeleteDialogFragment();
        editDeleteDialogFragment.show(getSupportFragmentManager(), "Edit/Delete");
    }

    public View.OnClickListener fabClickListener() {
        type = 1;
        add = true;
        openTermDialog();
        termName = null;
        termStart = null;
        termEnd = null;
        return null;
    }

    private View.OnClickListener fabClassClickListener() {
        type = 2;
        add = true;
        openClassDialog(false);
        return null;
    }

    private View.OnClickListener fabAssessmentClickListener() {
        type = 3;
        add = true;
        openAssessmentDialog(false);
        return null;
    }

    @Override
    public void editDeleteInput(boolean input) {

        if (input) {
            inputType(type);
        } else {
            openConfirmDialog();
        }
    }

    public void inputType(int type) {
        switch (type) {
            case 1:
                openTermDialog();
                termName = TermAdapter.termModels.get(termClickPosition).termName;
                termStart = TermAdapter.termModels.get(termClickPosition).termStart;
                termEnd = TermAdapter.termModels.get(termClickPosition).termEnd;
                break;

            case 2:
                openClassDialog(true);
                break;

            case 3:
                openAssessmentDialog(true);
                break;
        }
    }

    @Override
    public void saveTermInput(TermModel termModel) {
        if (add) {
            addTerm(termModel);
        } else {
            replaceTerm(termModel);
        }
    }

    @Override
    public void saveClassInput(ClassModel input) {
        if (add) {
            addClass(input);
        } else {
            replaceClass(classClickPosition, input);
        }
    }

    @Override
    public void saveAssessmentInput(AssessmentModel input) {
        if (add) {
            addAssessment(input);
        } else {
            replaceAssessment(assessmentClickPosition, input);
        }
    }
}
