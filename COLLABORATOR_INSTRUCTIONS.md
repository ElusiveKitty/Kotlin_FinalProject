# Collaborator Instructions - Student Pomodoro App

## Overview
This is a Pomodoro timer app with Task management. Two tabs: **Timer** and **Tasks**.

---

## Task 1: Add Room Database (Persistence)

### Goal
- Tasks should be saved to a database
- Tasks should persist when app is closed/reopened
- Tasks can be deleted

### Steps

1. **Add dependencies** to `app/build.gradle.kts`:
   ```kotlin
   dependencies {
       implementation("androidx.room:room-runtime:2.6.1")
       implementation("androidx.room:room-ktx:2.6.1")
       kapt("androidx.room:room-compiler:2.6.1")
       implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
   }
   ```

2. **Create Task Entity** in `app/src/main/java/com/example/student_pomodoro/data/`:
   ```kotlin
   @Entity(tableName = "tasks")
   data class Task(
       @PrimaryKey(autoGenerate = true) val id: Int = 0,
       val title: String,
       val completedPomodoros: Int = 0,
       val isCompleted: Boolean = false
   )
   ```

3. **Create TaskDao** in `app/src/main/java/com/example/student_pomodoro/data/`:
   ```kotlin
   @Dao
   interface TaskDao {
       @Query("SELECT * FROM tasks ORDER BY id DESC")
       fun getAllTasks(): LiveData<List<Task>>
       
       @Insert
       suspend fun insert(task: Task)
       
       @Delete
       suspend fun delete(task: Task)
       
       @Query("UPDATE tasks SET completedPomodoros = completedPomodoros + 1 WHERE id = :taskId")
       suspend fun incrementPomodoro(taskId: Int)
   }
   ```

4. **Create AppDatabase** in `app/src/main/java/com/example/student_pomodoro/data/`:
   ```kotlin
   @Database(entities = [Task::class], version = 1)
   abstract class AppDatabase : RoomDatabase() {
       abstract fun taskDao(): TaskDao
   }
   ```

5. **Update TaskViewModel** to use database instead of hardcoded list.

6. **Update TaskFragment** to connect UI with ViewModel.

---

## Task 2: Update UI to Look Better

### Goals
- Make Timer screen more visually appealing
- Make Tasks screen more usable

### Suggestions
1. **Timer Screen**:
   - Use a circular progress indicator for the timer
   - Make buttons bigger and more prominent
   - Add a card/background for the timer display
   - Use proper spacing and colors

2. **Tasks Screen**:
   - Add checkbox for each task
   - Add swipe-to-delete or delete button
   - Show pomodoro count per task
   - Use RecyclerView with proper item layout

---

## Task 3: Add Streak Tracking

### Goal
- Track longest streak of completed pomodoro sessions
- Display streak when timer is stopped/reset

### Steps

1. **Add DataStore** to store streak persistently:
   ```kotlin
   implementation("androidx.datastore:datastore-preferences:1.1.1")
   ```

2. **Create StreakManager**:
   - Store current streak count
   - Store longest streak ever
   - Reset current streak on app close without completing session
   - Increment current streak when session completes

3. **Update TimeViewModel**:
   - Track when timer is stopped/reset
   - Calculate and store streak
   - Expose streak to UI

4. **Update TimeFragment**:
   - Add TextView below timer to show "Longest Streak: X"
   - Update when timer is reset

### Streak Logic
- A "streak" = consecutive completed pomodoro sessions
- When user completes a work session → increment streak
- When user resets timer without completing → reset current streak
- Longest streak = max of all time

---

## Code Locations to Modify

| File | Purpose |
|------|---------|
| `app/build.gradle.kts` | Add dependencies |
| `app/src/main/java/com/example/student_pomodoro/data/` | New folder for Room DB |
| `app/src/main/java/com/example/student_pomodoro/ui/tasks/TaskViewModel.kt` | Create this file |
| `app/src/main/java/com/example/student_pomodoro/ui/timer/TimeViewModel.kt` | Add streak logic |
| `app/src/main/res/layout/fragment_time.xml` | Better timer UI |
| `app/src/main/res/layout/fragment_tasks.xml` | Better task list UI |

---

## Testing Checklist

- [ ] Can add a new task
- [ ] Task appears in list after adding
- [ ] Task persists after closing/reopening app
- [ ] Can delete a task
- [ ] Timer starts/pauses/resets correctly
- [ ] Streak updates when session completes
- [ ] Streak resets when timer is reset mid-session
- [ ] Longest streak is displayed below timer

---

## Questions?
Ask the repo owner for clarification.