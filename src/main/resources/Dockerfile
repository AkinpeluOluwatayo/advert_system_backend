FROM eclipse-temurin:17-jdk

WORKDIR /app
# Copy everything
COPY . .

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/*.jar"]