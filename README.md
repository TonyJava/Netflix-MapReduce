# Netflix-MapReduce

## Instruction to run jar

####Get list of movie ratings
```sh
hadoop jar Netflix-MapReduce-0.0.1-SNAPSHOT.jar edu.cpp.cs499.MovieRatings.MovieRatingsDriver TrainingRatings.txt movieOutput
```
####Get top ten movies with title 
```sh
hadoop jar Netflix-MapReduce-0.0.1-SNAPSHOT.jar edu.cpp.cs499.TopTenMovies.TopTenMovies output/part-r-00000 movie_titles.txt movieOutput/toptenMovies.txt
```
####Get list of users and ratings
```sh
hadoop jar Netflix-MapReduce-0.0.1-SNAPSHOT.jar edu.cpp.cs499.UserRatings.UserRatingsDriver TrainingRatings.txt userOutput
```
####Get top ten users
```sh
hadoop jar Netflix-MapReduce-0.0.1-SNAPSHOT.jar edu.cpp.cs499.TopTenUsers.TopTenUsers userOutput/part-r-00000 userOutput/toptenUsers.txt
```
