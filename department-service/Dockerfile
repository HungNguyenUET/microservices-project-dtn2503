# Sử dụng image cơ sở Java
FROM openjdk:25-jdk

# Thông tin tác giả
LABEL authors="hungnv"

# Tạo thư mục làm việc trong container
WORKDIR /app

# Sao chép tệp JAR vào container
COPY target/department-service-0.0.1-SNAPSHOT.jar /app/department-service-0.0.1-SNAPSHOT.jar

# Expose port của ứng dụng
EXPOSE 8080

# Câu lệnh để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "/app/department-service-0.0.1-SNAPSHOT.jar"]