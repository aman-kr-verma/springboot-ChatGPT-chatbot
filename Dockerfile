# Stage 1: Build the application
FROM maven AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /app/src/
RUN mvn package

# Stage 2: Run the application
FROM eclipse-temurin
WORKDIR /app
COPY --from=builder /app/target/ChatGPT-chatbot.jar /app/ChatGPT-chatbot.jar
CMD ["java", "-jar", "ChatGPT-chatbot.jar"]
