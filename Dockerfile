FROM openjdk:11-jdk
COPY target/authorization-1.0.0.jar /home
WORKDIR /home
EXPOSE 8082
ARG profile
ENV spring_profile=${profile}
RUN echo $spring_profile
CMD ["sh", "-c", "java -Dspring.profiles.active=$spring_profile -jar authorization-1.0.0.jar"]