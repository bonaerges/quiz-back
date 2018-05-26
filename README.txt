
Question Service:

	Create:
		addQuestion(Question question) - create a new question
		addAnswer(Integer idQuestion, Answer answer) - add an answer into the question

	Read:
		getAllQuestionsQuestions() - return a list of Questions with Answers
		getQuestion(Integer idQuestion) - return a specific Question with Answers
	Update:

		updateQuestion(Integer idQuestion, Question Question) - update Question
		updateAnswer(Integer idQuestion, Integer idAnswer, Answer Answer) - update Answer of the Question
	Delete:
	deleteQuestion(Integer idQuestion) - remove Question
	deleteAnswer(Integer idQuestion, Integer idAnswer) - remove Question Answer

	And Validation method:
	isAnswerMapToQuestion(Integer idQuestion, Integer idAnswer) - check if specific Answer belongs to the specific Question

	Service Method  		HTTP method 	CRUD 		URI 										Description
	create					POST 			Create		/question 									Create a new question
							POST			Create		/question/(idQuestion)/answer				Create a answer link to question
							GET 			Read		/question									Return a list of questions with answers
							GET				Read		/question/(idQuestion)						Return a specific question with answers
							PUT				Update
											/Replace	/question/(idQuestion)						Update question
	updateAnswerQuestion	PUT				Update
											/Replace	/question/(idQuestion)/answer/(idAnswer)	Update answer of the question
							DELETE			Delete		/question/(idQuestion)						Remove question
							DELETE			Delete		/question/(idQuestion)/answer/(idAnswer)	Remove answer of the question