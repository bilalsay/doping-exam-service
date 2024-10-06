# doping-exam-service

# CreateStudent
POST http://localhost:8080/students
Content-Type: application/json

{
  "name": "Bilal",
  "surname": "Say",
  "number": "1234567"
}

# GetStudent
GET http://localhost:8080/students/3

# CreateExam
POST http://localhost:8080/exams
Content-Type: application/json

{
  "name": "Osmanlı Tarihi",
  "questions": [
    {
      "number": 1,
      "text": "Osmanlı develetinin kurucusu kimdir?",
      "selections": {"A": "Osman Bey", "B":  "Fatih Sulktan Mehmet", "C":  "Yavus Sultan Selim"},
      "correctSelection": "A"
    },
    {
      "number": 1,
      "text": "Osmanlı develetinin kurucusu kimdir değildir?",
      "selections": {"A": "Osman Bey", "B":  "Fatih Sulktan Mehmet", "C":  "Yavus Sultan Selim"},
      "correctSelection": "B"
    }
  ]
}

# GetExam
GET http://localhost:8080/exams/6

# GetExamQuestions
GET http://localhost:8080/exams/6/questions

# TakeExamByStudent
POST http://localhost:8080/exams/take
Content-Type: application/json

{
  "studentId": 1,
  "examId": 6
}

# StudentExams
GET http://localhost:8080/students/1/exams

# CompleteExamByStudent
POST http://localhost:8080/exams/complete
Content-Type: application/json

{
  "studentId": 1,
  "examId": 6,
  "answers": [{"questionId":  7, "selection": "A"}, {"questionId":  8, "selection": "B"}]
}

# StudentSpecificExamDetail
GET http://localhost:8080/students/1/exam/6
