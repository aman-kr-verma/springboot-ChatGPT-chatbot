# Use the official OpenJDK image as base image
FROM openjdk:openjdk:17-jre-slim
# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container at /app
COPY target/ChatGPT-chatbot.jar /app/ChatGPT-chatbot.jar

# Expose the port the application runs on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "ChatGPT-chatbot.jar"]
