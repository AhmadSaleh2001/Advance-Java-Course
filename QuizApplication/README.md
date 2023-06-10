# Quiz Application

Quiz Application is a Java Swing-based program that allows users to take quizzes by reading questions from a file. The application utilizes Java serialization to save and load questions as objects of the Quiz class. Each question consists of a title, an array of four choices, and the index of the correct answer within the array. The program incorporates multithreading concepts to implement a timer for each quiz, allowing users to select their choices without blocking the program until the timer finishes.

![2023-06-10-15-54-53](https://github.com/AhmadSaleh2001/Advance-Java-Course/assets/79485253/43f37b85-9ea4-4d93-86e8-3ddcfb1f0539)


## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
- [Serialization](#serialization)
- [Multithreading](#multithreading)
- [Contributing](#contributing)

## Introduction

Quiz Application is designed to provide an interactive quiz-taking experience. By leveraging the power of Java Swing, serialization, and multithreading, the application enables users to answer questions within a specific time limit while ensuring smooth program execution.

## Features

- Quiz Taking: The application allows users to take quizzes by reading questions from a file.
- Object Serialization: Questions are saved as objects of the Quiz class using Java serialization, enabling easy storage and retrieval.
- Multiple Choice Questions: Each question contains a title and an array of four choices, with the correct answer identified by its index within the array.
- Timer Functionality: For each quiz, a timer with a 15-second limit is implemented, providing users with a specific time frame to answer each question.
- Multithreading: The program utilizes multithreading concepts in Java to enable concurrent execution of the timer and user interaction, preventing the program from blocking until the timer finishes.

## Getting Started

To get started with the Quiz Application, follow these steps:

1. Clone the repository to your local machine or download the source code as a ZIP file.
2. Open the project in your preferred Java development environment (e.g., Eclipse, IntelliJ IDEA).
3. Build and run the application.
4. The program will load the quiz questions from the file automatically.
5. Each question will be displayed with the available choices.
6. Users can select their answers within the given time limit for each question.

## Serialization

The Quiz Application uses Java serialization to save and load quiz questions as objects. Serialization allows questions to be stored in a file in their object form, simplifying the storage and retrieval process. The application uses the Java `Serializable` interface to mark the Quiz class as serializable, enabling the transformation of objects into a byte stream and vice versa.

## Multithreading

The Quiz Application employs multithreading to achieve concurrent execution of the quiz timer and user interaction. By utilizing Java threads, the program ensures that the timer runs independently of user input, preventing the application from becoming unresponsive while the timer is active. This enables users to select their choices for each question within the allotted time frame without blocking the program's execution.

## Contributing

Contributions to the Quiz Application project are welcome! If you have any ideas for improvements, bug fixes, or additional features, please submit a pull request.
