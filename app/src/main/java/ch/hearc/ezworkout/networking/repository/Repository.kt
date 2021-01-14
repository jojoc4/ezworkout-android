package ch.hearc.ezworkout.networking.repository

import android.content.SharedPreferences
import ch.hearc.ezworkout.networking.api.RetrofitInstance
import ch.hearc.ezworkout.networking.model.*

class Repository(private val sharedPref: SharedPreferences) {

    private val token = "Bearer " + sharedPref.getString("api", "")

    suspend fun getUser() : User {
        return  RetrofitInstance.api.getUser(token)
    }

    suspend fun getTrainingPlan() : List<TrainingPlan> {
        return  RetrofitInstance.api.getTrainingPlan(token)
    }

    suspend fun getTrainingPlan(id: Int) : TrainingPlan {
        return  RetrofitInstance.api.getTrainingPlan(token, id)
    }

    suspend fun getTraining(TPid: Integer) : List<Training> {
        return  RetrofitInstance.api.getTraining(token, TPid)
    }

    suspend fun getTraining(id: Int) : Training {
        return  RetrofitInstance.api.getTraining(token, id)
    }

    suspend fun getExercise(Tid: Integer) : List<Exercise> {
        return  RetrofitInstance.api.getExercise(token, Tid)
    }

    suspend fun getExercise(id: Int) : Exercise {
        return  RetrofitInstance.api.getExercise(token, id)
    }

    suspend fun getLogbookPage(TPid: Integer) : List<LogbookPage> {
        return  RetrofitInstance.api.getLogbookPage(token, TPid)
    }

    suspend fun getLogbookPage(id: Int) : LogbookPage {
        return  RetrofitInstance.api.getLogbookPage(token, id)
    }

    suspend fun getTrainingEff(LBPid: Integer) : List<TrainingEff> {
        return  RetrofitInstance.api.getTrainingEff(token, LBPid)
    }

    suspend fun getTrainingEff(id: Int) : TrainingEff {
        return  RetrofitInstance.api.getTrainingEff(token, id)
    }

    suspend fun getExerciseEff(TEid: Integer) : List<ExerciseEff> {
        return  RetrofitInstance.api.getExerciseEff(token, TEid)
    }

    suspend fun getExerciseEff(id: Int) : ExerciseEff {
        return  RetrofitInstance.api.getExerciseEff(token, id)
    }

    suspend fun getSeriesEff(EEid: Integer) : List<SeriesEff> {
        return  RetrofitInstance.api.getSeriesEff(token, EEid)
    }

    suspend fun getSeriesEff(id: Int) : SeriesEff {
        return  RetrofitInstance.api.getSeriesEff(token, id)
    }

    suspend fun addLogbookPage(LogbookPage: LogbookPage) : LogbookPage {
        return  RetrofitInstance.api.addLogbookPage(token, Integer(LogbookPage.trainingPlanId))
    }

    suspend fun addTrainingEff(trainingEff: TrainingEff) : TrainingEff {
        return  RetrofitInstance.api.addTrainingEff(token, trainingEff.logbookPageId, trainingEff.date.toString(), trainingEff.skipped, trainingEff.trainingId)
    }

    suspend fun addExerciseEff(exerciseEff: ExerciseEff) : ExerciseEff {
        return  RetrofitInstance.api.addExerciseEff(token, exerciseEff.trainingEffId, exerciseEff.pause, exerciseEff.skipped, exerciseEff.exerciseId, exerciseEff.rating)
    }

    suspend fun addSeriesEff(seriesEff: SeriesEff) : SeriesEff {
        return  RetrofitInstance.api.addSeriesEff(token, seriesEff.exerciseEffId, seriesEff.pause, seriesEff.rep, seriesEff.weight)
    }

    suspend fun updateTrainingPlan(tp: TrainingPlan): TrainingPlan{
        return RetrofitInstance.api.updateTrainingPlan(token, tp.id, tp.name.toString())
    }

    suspend fun updateTraining(tr: Training): Training{
        return RetrofitInstance.api.updateTraining(token, tr.id, tr.name.toString())
    }
}