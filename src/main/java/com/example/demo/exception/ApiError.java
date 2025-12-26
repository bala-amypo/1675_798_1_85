// package com.example.demo.exception;

// import java.time.LocalDateTime;

// public class ApiError {
//     private String message;
//     private int status;
//     private LocalDateTime timestamp;
//     private String path;

//     public ApiError(String message, int status, String path) {
//         this.message = message;
//         this.status = status;
//         this.path = path;
//         this.timestamp = LocalDateTime.now();
//     }

//     public String getMessage() { return message; }
//     public void setMessage(String message) { this.message = message; }

//     public int getStatus() { return status; }
//     public void setStatus(int status) { this.status = status; }

//     public LocalDateTime getTimestamp() { return timestamp; }
//     public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

//     public String getPath() { return path; }
//     public void setPath(String path) { this.path = path; }
// }