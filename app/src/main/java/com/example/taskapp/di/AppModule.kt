package com.example.taskapp.di

import android.content.Context
import androidx.room.Room
import com.example.taskapp.data.local.LocalDataSource
import com.example.taskapp.data.local.TaskDao
import com.example.taskapp.data.local.TaskDatabase
import com.example.taskapp.data.remote.ApiService
import com.example.taskapp.data.remote.RemoteDataSource
import com.example.taskapp.data.repository.TaskRepositoryImpl
import com.example.taskapp.domain.repository.TaskRepository
import com.example.taskapp.domain.usecase.AddTaskUseCase
import com.example.taskapp.domain.usecase.DeleteAllTasksUseCase
import com.example.taskapp.domain.usecase.GetTasksUseCase
import com.example.taskapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Database

    @Provides
    fun providesLocalDataSource(taskDao: TaskDao): LocalDataSource {
        return LocalDataSource(taskDao)
    }

    @Provides
    @Singleton
    fun providesTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "task_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesTaskDao(db: TaskDatabase): TaskDao {
        return db.taskDao();
    }

    // Api

    @Provides
    fun providesRemoteDataSource(api: ApiService): RemoteDataSource {
        return RemoteDataSource(api)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(ApiService::class.java)
    }

    // use-case

    @Provides
    fun providesAddTaskUseCase(repo: TaskRepository): AddTaskUseCase {
        return AddTaskUseCase(repo)
    }

    @Provides
    fun providesGetTasksUseCase(repo: TaskRepository): GetTasksUseCase {
        return GetTasksUseCase(repo)
    }

    @Provides
    fun providesTaskRepository(local: LocalDataSource, remote: RemoteDataSource): TaskRepository {
        return TaskRepositoryImpl(local, remote)
    }

    @Provides
    fun providesDeleteAllTasksUseCase(repo: TaskRepository): DeleteAllTasksUseCase {
        return DeleteAllTasksUseCase(repo)
    }

}