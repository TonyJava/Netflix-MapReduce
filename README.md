# Netflix-MapReduce

# Instruction to run jar

1. Get list of movie ratings

`hadoop jar Netflix-MapReduce-0.0.1-SNAPSHOT.jar edu.cpp.cs499.MovieRatings.MovieRatingsDriver TrainingRatings.txt movieOutput`

2. Get top ten movies with title 

`hadoop jar Netflix-MapReduce-0.0.1-SNAPSHOT.jar edu.cpp.cs499.TopTenMovies.TopTenMovies output/part-r-00000 movie_titles.txt movieOutput/toptenMovies.txt`

3. Get list of users and ratings

`hadoop jar Netflix-MapReduce-0.0.1-SNAPSHOT.jar edu.cpp.cs499.UserRatings.UserRatingsDriver TrainingRatings.txt userOutput`

4. Get top ten users

`hadoop jar Netflix-MapReduce-0.0.1-SNAPSHOT.jar edu.cpp.cs499.TopTenUsers.TopTenUsers userOutput/part-r-00000 userOutput/toptenUsers.txt`
