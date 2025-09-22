	# Stage 1: Build the app
		FROM maven:3.9.6-eclipse-temurin-17 AS build
		WORKDIR /app
		COPY pom.xml .
		COPY src ./src
		RUN mvn clean package

    # Stage 2: Run the app
		FROM eclipse-temurin:17.0.9_9-jdk-jammy
		WORKDIR /app
		COPY --from=build /app/target/notes-saas-backend-0.0.1-SNAPSHOT.jar app.jar
		EXPOSE 8080
		ENTRYPOINT ["java", "-jar", "app.jar"]





