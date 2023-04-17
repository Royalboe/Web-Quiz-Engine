package com.royalboe.Web.Quiz.Engine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.concurrent.ConcurrentHashMap


class Quiz(val title: String, val text: String, val options: List<String>) {
}

class QuizResponse(val success: Boolean, val feedback: String) {
}



@SpringBootApplication
@RestController
class WebQuizEngineApplication {
	
	val title = "The Java Logo"
	val text = "What is depicted on the Java logo?"
	val options: List<String> = listOf("Robot", "Tea leaf", "Cup of coffee", "Bug")

	val positiveFeedback = QuizResponse(true, "Congratulations, you're right!")
	val negativeFeedback = QuizResponse(false, "Wrong answer! Please, try again.")

	val quizzes = listOf(
		Quiz(title, text, options)
	)

	@GetMapping("/api/quiz")
	fun getQuiz(): ResponseEntity<Quiz> {
		return ResponseEntity.ok(quizzes[0])
	}

	@PostMapping("/api/quiz")
	fun postQuiz(@RequestParam answer: Int): ResponseEntity<QuizResponse> {
		return if (answer == 2) {
			ResponseEntity.ok(positiveFeedback)
		} else {
			ResponseEntity.ok(negativeFeedback)
		}
	}
}

fun main(args: Array<String>) {
	runApplication<WebQuizEngineApplication>(*args)
}
