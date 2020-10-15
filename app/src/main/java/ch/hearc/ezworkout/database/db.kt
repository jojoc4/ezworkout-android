package ch.hearc.ezworkout.database

@Database(entities = arrayOf(User::class), version = 1)
abstract class db : RoomDatabase() {


}