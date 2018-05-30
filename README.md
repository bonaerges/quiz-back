QUIZ BACK APPLICATION  (SPRING 5 COURSE AT ATSISTEMAS)

The main goal of this application is provide a way to add user, course and questionanrie for course .
The model take into account next entiies: 

*  User for provide alumns  personal details(name, email,..) to be regsitated on Course
*  Course with the name of course and start and en date of it
*  Questionnarie for a course to provide questions and answer to be filled by users.
*  Question that will be part of quetsionnarie adn that have linked one or more answers. ONly one is the correct
*  Answer mapped to Question
*  Result of a questionarie that will contain for each course, questionarie and user uses response with moptes
*  Also Questionarie and Question could be contains Tga and search questionarie and question y tag

Main Goal and responsabilities requested:

 + COMPLETE AT LEAST 3 CRUDS (QUESTION, USER, QUESTIONNAIRE)
 
 + COURSE CREATION (USER + QUESTIONNARIE + COURSE). DO ALSO MOCKITO TEST FOR COURSE LAYERS
 
 + GET THE RESULS FILTER BY COURSE
 
 + GET THE QUESTIONNARIE REQUESTING QUESTION -ANSWER ONE BY ONE.. USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION.
 
 + GET THE QUESTIONNARIE REQUESTING AND SHOWING RAMDOM QUESTION -ANSWER ONE BY ONE.. USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION.
 
 + GET ALL QUESTION-ANSWER FOR QUESTIONNARIE(WITHOUT PAGINATION ??)
 
 C
 
-------------------------------------------------------------------------------------------------------------------------
CONTAINS OF APLICATION DETAILS

**************************************************************************************************************************
***************************************************  USER  ***************************************************************
**************************************************************************************************************************
a) UserDAO access to Repository map to User Entity mainly. 
	-Create(C)	
	-Read(R)
	-Update(C)
	-Delete(C)
 
	b)Question Service:
	c) DTO and Mapper
	
		-UserPostDTO purporse is for RequestBody  update/post controller contains USERDTO + password
		-UserDTO purporse is for HTTP Get methods on controller to show only user data required without password.
		
	d) Mapper:
		-UserMapper is used to convert from DTO to Model and from Model to DTO.
	
	e)User Controller:	Below is the mapping can be used on User Rest Controller 
	
	Service Method  		HTTP method 	CRUD 		URI 										Description
	create					POST 			Create		/user 										Create a new user given a UserDTO object

	findAll					GET 			Read		/user										Return a list of users paginate.Default pagination page=0 y size 10
	getUserById				GET				Read		/user/(idUser)								Return user details for a specific user id							
	
	update					PUT				Update
											/Replace	/user/(idUser)								Update user email, name, surname and /or password
										
	delete					DELETE			Delete		/user/(idUser)								Remove user completly
	

**************************************************************************************************************************
QUESTION
**************************************************************************************************************************	

	a) QuestionDAO access to Repository map to Questiob Entity mainly. 
		-Create(C)	
		-Read(R)
		-Update(C)
		-Delete(C)
	
	b)Question Service:
	c) DTO and Mapper
	
		-QuestionUpdateDTO purporse is for RequestBody  update/post controller 
		-QuestionDTO purporse is for HTTP Get methos on controller to show all question and answer linked.
		
	d) Mapper:
		-QuestioNMapper is used to convert from DTO to Model and from Model to DTO.
	
	e)Question Controller:	Below is the mapping can be used on Question Rest Controller 
	
	Service Method  		HTTP method 	CRUD 		URI 										Description
	create					POST 			Create		/question 									Create a new question
							POST			Create		/question/(idQuestion)/answer				Create a answer link to question
	findAll					GET 			Read		/question									Return a list of questions with answers
	findById				GET				Read		/question/(idQuestion)						Return answers for a specific question							
	getCorrectAnswer		GET				Read		/question/(IdQuestion)/showCorrectAnswer	Return the corect answer for given quetsion id
	update					PUT				Update
											/Replace	/question/(idQuestion)						Update question
	updateAnswerQuestion	PUT				Update
											/Replace	/question/(idQuestion)/answer/(idAnswer)	Update answer of the existing question
	deleteAnswer			DELETE			Delete		/question/(idQuestion)						Remove question
	deleteAnswerQuestion	DELETE			Delete		/question/(idQuestion)/answer/(idAnswer)	Remove answer of the existing question
	
	
	And Validation method:
	isAnswerMapToQuestion(Integer idQuestion, Integer idAnswer) - check if specific Answer belongs to the specific Question

**************************************************************************************************************************
***************************************************  ANSWER  *************************************************************
**************************************************************************************************************************
a) AnswerDAO access to Repository map to Answer Entity mainly. 
	-Create(C)	
	-Read(R)
	-Update(C)
	-Delete(C)
 
	b)Answer Service:
	Implemeted extended AbstractCrossService taht received a Answer Model object: create Answer, update Answer, delet Answer, findById given answerd id,
	findAll that return ala List of Answer Model
	
	Another methods implemented on Answer service but not in AbstractCrossService:
		addAnswerQuestion->Given a Answer model and an Id Question save into answer Repository this new link mapped idQuestion,idAnswer
		updatedAnswerQuestion->Given a Answer model and an Id Question udpate for idAnswer on answer Repository the idQuestion linked using addAnswerQuestion
		deleteAnswerQuestion-->Delete and answer mapped to an existing question id
		isAnswerMapToQuestion->check if an idQuestion is linked to idAnswer
		findOneByDescriptionOrderByIdDesc-_> return all answer order by description and sor tin desceding order
		
	c) DTO and Mapper
	
		-AnswerUpdateDTO purporse is for RequestBody  update/post controller contains Description + isAnswerCorrect
		-AnswerDTO purporse is for HTTP Get methods on controller to show only user data required without password.
		
	d) Mapper:
		-AnswerMapper is used to convert from DTO to Model and from Model to DTO.
	
	e)Answer Controller:	Below is the mapping can be used on Answer Rest Controller 
	
	Service Method  		HTTP method 	CRUD 		URI 										Description
	create					POST 			Create		/answer										Create a new aswer given a AnswerDTO object

	findAll					GET 			Read		/answer										Return a list of answers paginate.Default pagination page=0 y size 10
	findById				GET				Read		/answer/(idAnswer)							Return answer details for a specific answer id							
	
	update					PUT				Update
											/Replace	/answer/(idAnswer)							Update description for answer
										
	delete					DELETE			Delete		/answer/(idAnswer)							Remove answer completly
	



*******************************************************
+ RECUPERACION DE RESULTADOS DEL CURSO
*******************************************************
Controller 
/course/(id)/questionarie/(id)/result
	