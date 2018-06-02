QUIZ BACK APPLICATION  (SPRING 5 COURSE AT ATSISTEMAS)

The main goal of this application is provide to users in a course a way to make course quiz providing a administration service such as
add user, course and questionanrie for course. It also save the responses for each quuestionnaire for each course and user 
.
The model take into account next entities: 

*  User for provide alumns  personal details(name, email,..) to be registrated on Course
*  Course with the name of course and start and en date of it
*  Questionnarie for a course to provide questions and answer to be filled by users.
*  Question that will be part of questionnarie and that have linked one or more answers. Only one is assumed to be the correct
*  Answer mapped to Question
*  Result of a questionarie that will contain for each course, questionarie and user uses response with moptes
*  Also Questionarie and Question could be contains Tga and search questionarie and question y tag

   Main Goal and responsabilities requested:

 	+ COMPLETE AT LEAST 3 CRUDS (QUESTION, USER, QUESTIONNAIRE)
 
 	+ COURSE CREATION (USER + QUESTIONNARIE + COURSE). DO ALSO MOCKITO TEST FOR COURSE LAYERS
 
 	+ GET THE RESULS FILTER BY COURSE
 
 	+ GET THE QUESTIONNARIE REQUESTING QUESTION -ANSWER ONE BY ONE.USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION.
 
 	+ GET THE QUESTIONNARIE SHOWING RAMDOM QUESTION -ANSWER ONE BY ONE. USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION.
 
 	+ GET ALL QUESTION-ANSWER FOR QUESTIONNARIE(WITHOUT PAGINATION ??)	
 
 ************************************************
 ASUMPTIONS NOTE OF MY QUIZ BACK APP

+ IF QUESTIONNAIRE DOES NOT EXIST IT IS ALLOWED TO CREATE  IT WHEN COURSE IS TRYING TO ADD A NEW QUESTIONNAIRE TO COURSE
+ IF AN ANSWER DOES NOT EXIST IT IS ALLOWED TO CREATED ON FLY ANSWER WHEN QUESTION IS ADDING A NEW ANSWER.
+ FOR A QUESTION, SUITABLES ANSWERS ONLY ONE IS THE CORRECT ONE. NOT ALLOWED MULTIPLES ANSWERS
+ WHEN AN USER IT ADDED TO A COURSE IS NOT ALLOWED TO CREATE USERS ON THE FLY IF USER PREVIOUSLY USER DOES NOT EXIST 
+ WHEN A QUESTIONNARIE IS DELETED LINKED QUESTIONS AND ANSWERS WILL NOT BE DELETED TO BE REUSABLE FOR OTHER QUESTIONNARIES
************************************************

-------------------------------------------------------------------------------------------------------------------------
CONTAINS OF APLICATION DETAILS
-------------------------------------------------------------------------------------------------------------------------

**************************************************************************************************************************
+ COMPLETE AT LEAST 3 CRUDS (QUESTION, USER, QUESTIONNAIRE). DETAILS
**************************************************************************************************************************

***********  USER  ********************************************
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
	
	Service Method  HTTP method CRUD 	URI 		Description
	create			POST 		Create	/user 		Create a new user given a UserDTO object
	findAll			GET 		Read	/user		Return a list of users paginate.
													Default pagination page=0 y size 10
	
	getUserById		GET			Read	/user/(idU)	Return user details for a specific user id							
	update			PUT			Update	/user/(idU)	Update user,mail, name, surname and /or password
	delete			DELETE		Delete	/user/(idU)	Remove user completely
	

**************************************************************************************************************************
**********  QUESTION  *******************************************************	

	a) QuestionDAO access to Repository map to Question Entity mainly. 
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
	
	Service Method  	HTTP method CRUD 		URI 							Description
	create				POST 		Create		/question 						Create a new question
						POST		Create		/question/(idQ)/answer			Create a answer link to question
	findAll				GET 		Read		/question						Return a list of questions with answers
	findById			GET			Read		/question/(idQ)					Return answers for a specific question							
	getCorrectAnswer	GET			Read		/question/(IdQ)/showCorrect		Return the corect answer for given quetsion id
	update				PUT			Update
									/Replace	/question/(idQ)					Update question
	updateAnswerQuestionPUT			Update
									/Replace	/question/(idQ)/answer/(idA)	Update answer of the existing question
	deleteAnswer		DELETE		Delete		/question/(idQ)					Remove question
	deleteAnswerQuestionDELETE		Delete		/question/(idQ)/answer/(idA)	Remove answer of the existing question
	
	
	And Validation method:
	isAnswerMapToQuestion(Integer idQuestion, Integer idAnswer) - check if specific Answer belongs to the specific Question

**************************************************************************************************************************
*****************  ANSWER  *************************************
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
	
		-AnswerUpdateDTO purpose is for RequestBody  update/post controller contains Description + isAnswerCorrect
		-AnswerDTO purporse is for HTTP Get methods on controller to show only user data required without password.
		
	d) Mapper:
		-AnswerMapper is used to convert from DTO to Model and from Model to DTO.
	
	e)Answer Controller:	Below is the mapping can be used on Answer Rest Controller 
	
Service Method  	HTTP method 	CRUD 	URI 		Description
create	   	POST 	Create		/answer		Create a new answer given a 									AnswerDTO object

findAll	GET 	Read		/answer		Return a list of answers 										paginate. Default pagination 									page=0 y size 10
findById	GET	Read		/answer/(idAnswer)	Return answer details for a 									specific answer id							
	
update		PUT	Update/Repl  /answer/(idAnswer)	Update description for answer
										
delete		DELETE	Delete		/answer/(idAnswer)	Remove answer completely
	

**************************************************************************************************************************
* COURSE CREATION (USER + QUESTIONNARIE + COURSE). DO ALSO MOCKITO TEST FOR COURSE LAYERS
**************************************************************************************************************************
File src\main\resources\data\CourseDataModel.txt contains data test for course to be added and tets controller

Course Controller:	Below is the mapping can be used on Course Controller 
	
Service Method  		HTTP method 	CRUD 		URI 			Description
create					POST 			Create		/course			Create a new course given a CourseDTO object

findAll					GET 			Read		/course			Return a list of courses paginate.Default pagination page=0 y size 10
findById				GET				Read		/course/(idC)	Return course details for a specific course id							
	
update					PUT				Update	    /course/(idC)   Update details fields for course(description, start date..)
										
delete					DELETE			Delete		/course/(idC)	Remove course and linked questionnarie
	
	Usage Examples tested with filew txt:
	PUT -->http://localhost:8080/course/1/user/1-->This links user 1 with course 1
	PUT -->http://localhost:8080/course/1/user/2-->This links user 2 with course 1
	PUT -->http://localhost:8080/course/1/user/3-->This links user 3 with course 1
	PUT -->http://localhost:8080/course/2/user/1-->This links user 1 with course 1
	PUT -->http://localhost:8080/course/2/user/2-->This links user 2 with course 2
	GET -->http://localhost:8080/course/1/users -->THsi show Course 1 Description + List of Users belong to course 1


	MOCKITO TESTS:
*******************************************************
+ RECUPERACION DE RESULTADOS DEL CURSO
*******************************************************
Controller 
/course/(id)/questionarie/(id)/result
	

+ GET THE QUESTIONNARIE REQUESTING QUESTION -ANSWER ONE BY ONE.USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION.

<<<<<<<TO DO>>>>>>>>
  Controller 
GET -->/questionarie/(id)/onebyone

+ GET THE QUESTIONNARIE SHOWING RAMDOM QUESTION -ANSWER ONE BY ONE. USER MUST RESPONSE, SAVE THE ANSWER AND THEN GET NEXT QUESTION.
 
 <<<<<<<TO DO>>>>>>>>
 Controller 
GET -->/questionarie/(id)/random

+ GET ALL QUESTION-ANSWER FOR QUESTIONNARIE(WITHOUT PAGINATION ??)	
 
 <<<<<<<TO DO>>>>>>>>
 Controller 
GET -->/questionarie/(id)